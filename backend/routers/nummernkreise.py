"""
Nummernkreise Router - Verwaltung von Nummernkreisen für alle Module
"""

from fastapi import APIRouter, HTTPException, Depends
from pydantic import BaseModel, Field
from typing import Optional, List
from datetime import datetime
import uuid

from services.database import get_db
from utils.auth import get_current_user, require_permission

router = APIRouter(prefix="/api/system", tags=["Nummernkreise"])


class NummernkreisCreate(BaseModel):
    """Schema für Nummernkreis"""
    modul: str = Field(..., max_length=50)  # z.B. "kontrakte", "rechnungen", "fuhren"
    bezeichnung: str = Field(..., max_length=100)  # z.B. "Kontraktnummer EK"
    feldname: str = Field(..., max_length=50)  # z.B. "kontraktnummer"
    
    # Format-Einstellungen
    prefix: str = Field("", max_length=20)  # z.B. "EKK", "VKK", "RE"
    suffix: str = Field("", max_length=20)
    jahr_im_prefix: bool = True  # Jahr in Nummer aufnehmen?
    
    # Nummernbereich
    startnummer: int = 1
    aktuelle_nummer: int = 0  # Letzte vergebene Nummer
    stellen: int = 4  # Anzahl Stellen für die Nummer (mit führenden Nullen)
    
    # Bedingungen
    filter_typ: Optional[str] = None  # z.B. "EK" oder "VK" für Kontrakte
    
    aktiv: bool = True
    beschreibung: Optional[str] = None


class NummernkreisUpdate(BaseModel):
    bezeichnung: Optional[str] = None
    prefix: Optional[str] = None
    suffix: Optional[str] = None
    jahr_im_prefix: Optional[bool] = None
    startnummer: Optional[int] = None
    stellen: Optional[int] = None
    filter_typ: Optional[str] = None
    aktiv: Optional[bool] = None
    beschreibung: Optional[str] = None


# ========================== HELPER ==========================

async def get_naechste_nummer(modul: str, filter_typ: Optional[str], mandant_id: str, db) -> str:
    """
    Generiert die nächste eindeutige Nummer für ein Modul.
    Diese Funktion ist thread-safe durch atomare MongoDB-Operationen.
    """
    # Finde den passenden Nummernkreis
    query = {
        "mandant_id": mandant_id,
        "modul": modul,
        "aktiv": True,
        "deleted": {"$ne": True}
    }
    
    if filter_typ:
        query["$or"] = [
            {"filter_typ": filter_typ},
            {"filter_typ": None},
            {"filter_typ": ""}
        ]
    
    nummernkreis = await db.nummernkreise.find_one(query, sort=[("filter_typ", -1)])
    
    if not nummernkreis:
        # Fallback: Einfache Zählung
        jahr = datetime.now().year
        count = await db[modul].count_documents({"mandant_id": mandant_id})
        return f"{modul.upper()[:3]}{jahr}{count + 1:04d}"
    
    # Atomares Inkrement der aktuellen Nummer
    result = await db.nummernkreise.find_one_and_update(
        {"_id": nummernkreis["_id"]},
        {"$inc": {"aktuelle_nummer": 1}},
        return_document=True
    )
    
    neue_nummer = result["aktuelle_nummer"]
    if neue_nummer < result.get("startnummer", 1):
        neue_nummer = result.get("startnummer", 1)
        await db.nummernkreise.update_one(
            {"_id": nummernkreis["_id"]},
            {"$set": {"aktuelle_nummer": neue_nummer}}
        )
    
    # Nummer formatieren
    prefix = result.get("prefix", "")
    suffix = result.get("suffix", "")
    stellen = result.get("stellen", 4)
    jahr_im_prefix = result.get("jahr_im_prefix", True)
    
    if jahr_im_prefix:
        jahr = str(datetime.now().year)
        nummer_str = f"{prefix}{jahr}{neue_nummer:0{stellen}d}{suffix}"
    else:
        nummer_str = f"{prefix}{neue_nummer:0{stellen}d}{suffix}"
    
    return nummer_str


async def pruefe_nummer_eindeutig(modul: str, nummer: str, mandant_id: str, db, exclude_id: str = None) -> bool:
    """Prüft ob eine Nummer bereits vergeben ist"""
    # Mapping von Modul zu Collection und Feldname
    modul_mapping = {
        "kontrakte": ("kontrakte", "kontraktnummer"),
        "rechnungen": ("rechnungen", "rechnungsnummer"),
        "fuhren": ("fuhren", "fuhrnummer"),
        "lieferscheine": ("lieferscheine", "lieferscheinnummer"),
    }
    
    if modul not in modul_mapping:
        return True
    
    collection, feldname = modul_mapping[modul]
    
    query = {
        "mandant_id": mandant_id,
        feldname: nummer,
        "deleted": {"$ne": True}
    }
    
    if exclude_id:
        query["_id"] = {"$ne": exclude_id}
    
    existing = await db[collection].find_one(query)
    return existing is None


# ========================== ROUTES ==========================

@router.get("/nummernkreise")
async def get_nummernkreise(
    modul: Optional[str] = None,
    user = Depends(require_permission("einstellungen", "read"))
):
    """Alle Nummernkreise abrufen"""
    db = get_db()
    query = {"mandant_id": user["mandant_id"], "deleted": {"$ne": True}}
    
    if modul:
        query["modul"] = modul
    
    cursor = db.nummernkreise.find(query).sort("modul", 1)
    nummernkreise = await cursor.to_list(length=100)
    
    for nk in nummernkreise:
        nk["id"] = nk.pop("_id")
    
    return {"success": True, "data": nummernkreise}


@router.get("/nummernkreise/{nummernkreis_id}")
async def get_nummernkreis(
    nummernkreis_id: str,
    user = Depends(require_permission("einstellungen", "read"))
):
    """Einzelnen Nummernkreis abrufen"""
    db = get_db()
    nk = await db.nummernkreise.find_one({
        "_id": nummernkreis_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not nk:
        raise HTTPException(status_code=404, detail="Nummernkreis nicht gefunden")
    
    nk["id"] = nk.pop("_id")
    return {"success": True, "data": nk}


@router.post("/nummernkreise")
async def create_nummernkreis(
    data: NummernkreisCreate,
    user = Depends(require_permission("einstellungen", "full"))
):
    """Neuen Nummernkreis erstellen"""
    db = get_db()
    
    # Prüfen ob bereits ein Nummernkreis für dieses Modul/Filter existiert
    existing = await db.nummernkreise.find_one({
        "mandant_id": user["mandant_id"],
        "modul": data.modul,
        "filter_typ": data.filter_typ,
        "deleted": {"$ne": True}
    })
    
    if existing:
        raise HTTPException(
            status_code=400, 
            detail=f"Es existiert bereits ein Nummernkreis für {data.modul}" + 
                   (f" mit Typ {data.filter_typ}" if data.filter_typ else "")
        )
    
    nk = {
        "_id": str(uuid.uuid4()),
        "mandant_id": user["mandant_id"],
        **data.model_dump(),
        "erstellt_am": datetime.utcnow(),
        "erstellt_von": user.get("username"),
        "deleted": False
    }
    
    await db.nummernkreise.insert_one(nk)
    nk["id"] = nk.pop("_id")
    
    return {"success": True, "data": nk}


@router.put("/nummernkreise/{nummernkreis_id}")
async def update_nummernkreis(
    nummernkreis_id: str,
    data: NummernkreisUpdate,
    user = Depends(require_permission("einstellungen", "full"))
):
    """Nummernkreis aktualisieren"""
    db = get_db()
    
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    update_data["letzte_aenderung"] = datetime.utcnow()
    update_data["geaendert_von"] = user.get("username")
    
    result = await db.nummernkreise.update_one(
        {"_id": nummernkreis_id, "mandant_id": user["mandant_id"]},
        {"$set": update_data}
    )
    
    if result.modified_count == 0:
        raise HTTPException(status_code=404, detail="Nummernkreis nicht gefunden")
    
    updated = await db.nummernkreise.find_one({"_id": nummernkreis_id})
    updated["id"] = updated.pop("_id")
    
    return {"success": True, "data": updated}


@router.delete("/nummernkreise/{nummernkreis_id}")
async def delete_nummernkreis(
    nummernkreis_id: str,
    user = Depends(require_permission("einstellungen", "full"))
):
    """Nummernkreis löschen (Soft-Delete)"""
    db = get_db()
    
    await db.nummernkreise.update_one(
        {"_id": nummernkreis_id, "mandant_id": user["mandant_id"]},
        {"$set": {"deleted": True, "geloescht_am": datetime.utcnow()}}
    )
    
    return {"success": True, "message": "Nummernkreis gelöscht"}


@router.post("/nummernkreise/naechste-nummer")
async def get_next_number(
    modul: str,
    filter_typ: Optional[str] = None,
    user = Depends(require_permission("einstellungen", "read"))
):
    """Nächste verfügbare Nummer für ein Modul abrufen (ohne zu reservieren)"""
    db = get_db()
    
    # Aktuellen Stand ermitteln (ohne Inkrement)
    query = {
        "mandant_id": user["mandant_id"],
        "modul": modul,
        "aktiv": True,
        "deleted": {"$ne": True}
    }
    
    if filter_typ:
        query["$or"] = [
            {"filter_typ": filter_typ},
            {"filter_typ": None},
            {"filter_typ": ""}
        ]
    
    nk = await db.nummernkreise.find_one(query, sort=[("filter_typ", -1)])
    
    if not nk:
        jahr = datetime.now().year
        count = await db[modul].count_documents({"mandant_id": user["mandant_id"]})
        return {"success": True, "nummer": f"{modul.upper()[:3]}{jahr}{count + 1:04d}"}
    
    # Vorschau auf nächste Nummer
    naechste = nk.get("aktuelle_nummer", 0) + 1
    if naechste < nk.get("startnummer", 1):
        naechste = nk.get("startnummer", 1)
    
    prefix = nk.get("prefix", "")
    suffix = nk.get("suffix", "")
    stellen = nk.get("stellen", 4)
    jahr_im_prefix = nk.get("jahr_im_prefix", True)
    
    if jahr_im_prefix:
        jahr = str(datetime.now().year)
        nummer_str = f"{prefix}{jahr}{naechste:0{stellen}d}{suffix}"
    else:
        nummer_str = f"{prefix}{naechste:0{stellen}d}{suffix}"
    
    return {"success": True, "nummer": nummer_str, "nummernkreis_id": nk["_id"]}


@router.post("/nummernkreise/initialisieren")
async def init_default_nummernkreise(
    user = Depends(require_permission("einstellungen", "full"))
):
    """Standard-Nummernkreise für alle Module erstellen"""
    db = get_db()
    
    defaults = [
        {
            "modul": "kontrakte",
            "bezeichnung": "Einkaufskontrakte",
            "feldname": "kontraktnummer",
            "prefix": "EKK",
            "filter_typ": "EK",
            "stellen": 4,
            "jahr_im_prefix": True,
            "beschreibung": "Nummernkreis für Einkaufskontrakte"
        },
        {
            "modul": "kontrakte",
            "bezeichnung": "Verkaufskontrakte",
            "feldname": "kontraktnummer",
            "prefix": "VKK",
            "filter_typ": "VK",
            "stellen": 4,
            "jahr_im_prefix": True,
            "beschreibung": "Nummernkreis für Verkaufskontrakte"
        },
        {
            "modul": "rechnungen",
            "bezeichnung": "Rechnungen",
            "feldname": "rechnungsnummer",
            "prefix": "RE",
            "stellen": 5,
            "jahr_im_prefix": True,
            "beschreibung": "Nummernkreis für Ausgangsrechnungen"
        },
        {
            "modul": "fuhren",
            "bezeichnung": "Fuhren",
            "feldname": "fuhrnummer",
            "prefix": "FU",
            "stellen": 5,
            "jahr_im_prefix": True,
            "beschreibung": "Nummernkreis für Fuhren/Wiegescheine"
        },
        {
            "modul": "lieferscheine",
            "bezeichnung": "Lieferscheine",
            "feldname": "lieferscheinnummer",
            "prefix": "LS",
            "stellen": 5,
            "jahr_im_prefix": True,
            "beschreibung": "Nummernkreis für Lieferscheine"
        },
    ]
    
    created = 0
    for default in defaults:
        existing = await db.nummernkreise.find_one({
            "mandant_id": user["mandant_id"],
            "modul": default["modul"],
            "filter_typ": default.get("filter_typ"),
            "deleted": {"$ne": True}
        })
        
        if not existing:
            nk = {
                "_id": str(uuid.uuid4()),
                "mandant_id": user["mandant_id"],
                **default,
                "startnummer": 1,
                "aktuelle_nummer": 0,
                "aktiv": True,
                "erstellt_am": datetime.utcnow(),
                "erstellt_von": user.get("username"),
                "deleted": False
            }
            await db.nummernkreise.insert_one(nk)
            created += 1
    
    return {"success": True, "message": f"{created} Nummernkreise erstellt"}
