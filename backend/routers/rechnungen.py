"""
Rechnungen Router - Rechnungen und Gutschriften
"""

from fastapi import APIRouter, HTTPException, Depends
from pydantic import BaseModel, Field
from typing import Optional, List, Dict, Any
from datetime import datetime
import uuid

from services.database import get_db
from utils.auth import get_current_user

router = APIRouter(prefix="/api", tags=["Rechnungen"])


class RechnungCreate(BaseModel):
    vorgang_typ: str = "RECHNUNG"  # RECHNUNG oder GUTSCHRIFT
    id_adresse: Optional[str] = None
    name1: Optional[str] = None
    name2: Optional[str] = None
    strasse: Optional[str] = None
    plz: Optional[str] = None
    ort: Optional[str] = None
    land: Optional[str] = None
    rechnungsdatum: Optional[str] = None
    lieferdatum: Optional[str] = None
    zahlungsziel: Optional[str] = None
    waehrung: str = "EUR"
    mwst_satz: float = 19.0
    status: str = "OFFEN"
    bemerkungen: Optional[str] = None
    positionen: Optional[List[Dict[str, Any]]] = None


class RechnungUpdate(BaseModel):
    id_adresse: Optional[str] = None
    name1: Optional[str] = None
    name2: Optional[str] = None
    strasse: Optional[str] = None
    plz: Optional[str] = None
    ort: Optional[str] = None
    land: Optional[str] = None
    rechnungsdatum: Optional[str] = None
    lieferdatum: Optional[str] = None
    zahlungsziel: Optional[str] = None
    mwst_satz: Optional[float] = None
    status: Optional[str] = None
    bemerkungen: Optional[str] = None


class RechnungPositionCreate(BaseModel):
    id_artikel: Optional[str] = None
    artikel_bezeichnung: str
    menge: float
    einheit: str = "kg"
    einzelpreis: float
    mwst_satz: float = 19.0


async def generate_rechnungs_nr(mandant_id: str, ist_gutschrift: bool) -> str:
    """Generiert Rechnungs-/Gutschriftsnummer"""
    db = get_db()
    prefix = "GS" if ist_gutschrift else "RG"
    year = datetime.now().year
    count = await db.rechnungen.count_documents({
        "mandant_id": mandant_id,
        "rechnungs_nr": {"$regex": f"^{prefix}{year}"}
    })
    return f"{prefix}{year}-{count + 1:05d}"


def berechne_summen(positionen: List[Dict], mwst_satz: float) -> Dict:
    """Berechnet Rechnungssummen"""
    netto = sum(p.get("menge", 0) * p.get("einzelpreis", 0) for p in positionen)
    mwst = netto * mwst_satz / 100
    brutto = netto + mwst
    return {"netto": round(netto, 2), "mwst": round(mwst, 2), "brutto": round(brutto, 2)}


@router.get("/rechnungen")
async def get_rechnungen(
    suche: Optional[str] = None,
    typ: Optional[str] = None,
    status: Optional[str] = None,
    page: int = 1,
    limit: int = 20,
    user = Depends(get_current_user)
):
    """Rechnungen suchen"""
    db = get_db()
    query = {"mandant_id": user["mandant_id"], "deleted": {"$ne": True}}
    
    if suche:
        query["$or"] = [
            {"rechnungs_nr": {"$regex": suche, "$options": "i"}},
            {"name1": {"$regex": suche, "$options": "i"}},
        ]
    if typ:
        query["vorgang_typ"] = typ
    if status:
        query["status"] = status
    
    total = await db.rechnungen.count_documents(query)
    cursor = db.rechnungen.find(query).sort("erstellt_am", -1).skip((page - 1) * limit).limit(limit)
    rechnungen = await cursor.to_list(length=limit)
    
    for r in rechnungen:
        r["id"] = r.pop("_id")
    
    return {"success": True, "data": rechnungen, "pagination": {"page": page, "limit": limit, "total": total}}


@router.get("/rechnungen/{rechnung_id}")
async def get_rechnung_by_id(rechnung_id: str, user = Depends(get_current_user)):
    """Rechnung nach ID"""
    db = get_db()
    rechnung = await db.rechnungen.find_one({"_id": rechnung_id, "mandant_id": user["mandant_id"]})
    
    if not rechnung:
        raise HTTPException(status_code=404, detail="Rechnung nicht gefunden")
    
    rechnung["id"] = rechnung.pop("_id")
    return {"success": True, "data": rechnung}


@router.post("/rechnungen")
async def create_rechnung(data: RechnungCreate, user = Depends(get_current_user)):
    """Neue Rechnung/Gutschrift erstellen"""
    db = get_db()
    
    ist_gutschrift = data.vorgang_typ == "GUTSCHRIFT"
    rechnungs_nr = await generate_rechnungs_nr(user["mandant_id"], ist_gutschrift)
    
    positionen = data.positionen or []
    summen = berechne_summen(positionen, data.mwst_satz)
    
    rechnung = {
        "_id": str(uuid.uuid4()),
        "mandant_id": user["mandant_id"],
        "rechnungs_nr": rechnungs_nr,
        **data.model_dump(),
        **summen,
        "erstellt_am": datetime.utcnow(),
        "deleted": False,
    }
    
    await db.rechnungen.insert_one(rechnung)
    rechnung["id"] = rechnung.pop("_id")
    
    return {"success": True, "data": rechnung}


@router.put("/rechnungen/{rechnung_id}")
async def update_rechnung(rechnung_id: str, data: RechnungUpdate, user = Depends(get_current_user)):
    """Rechnung aktualisieren"""
    db = get_db()
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    update_data["letzte_aenderung"] = datetime.utcnow()
    
    result = await db.rechnungen.update_one(
        {"_id": rechnung_id, "mandant_id": user["mandant_id"]},
        {"$set": update_data}
    )
    
    if result.modified_count == 0:
        raise HTTPException(status_code=404, detail="Rechnung nicht gefunden")
    
    updated = await db.rechnungen.find_one({"_id": rechnung_id})
    updated["id"] = updated.pop("_id")
    
    return {"success": True, "data": updated}


@router.delete("/rechnungen/{rechnung_id}")
async def delete_rechnung(rechnung_id: str, user = Depends(get_current_user)):
    """Rechnung löschen (Hard Delete)"""
    db = get_db()
    
    # Prüfen ob Rechnung existiert
    rechnung = await db.rechnungen.find_one({
        "_id": rechnung_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if not rechnung:
        raise HTTPException(status_code=404, detail="Rechnung nicht gefunden")
    
    # Rechnung endgültig löschen
    result = await db.rechnungen.delete_one({
        "_id": rechnung_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="Rechnung konnte nicht gelöscht werden")
    
    return {"success": True, "message": "Rechnung gelöscht"}


@router.post("/rechnungen/{rechnung_id}/positionen")
async def add_rechnung_position(rechnung_id: str, data: RechnungPositionCreate, user = Depends(get_current_user)):
    """Position hinzufügen"""
    db = get_db()
    
    rechnung = await db.rechnungen.find_one({"_id": rechnung_id, "mandant_id": user["mandant_id"]})
    if not rechnung:
        raise HTTPException(status_code=404, detail="Rechnung nicht gefunden")
    
    position = {
        "id": str(uuid.uuid4()),
        **data.model_dump(),
        "gesamt": round(data.menge * data.einzelpreis, 2)
    }
    
    positionen = rechnung.get("positionen", [])
    positionen.append(position)
    
    summen = berechne_summen(positionen, rechnung.get("mwst_satz", 19.0))
    
    await db.rechnungen.update_one(
        {"_id": rechnung_id},
        {"$set": {"positionen": positionen, **summen}}
    )
    
    return {"success": True, "data": position}


@router.post("/rechnungen/aus-fuhre/{fuhre_id}")
async def create_rechnung_aus_fuhre(
    fuhre_id: str,
    vorgang_typ: str = "RECHNUNG",
    user = Depends(get_current_user)
):
    """Rechnung/Gutschrift aus Fuhre erstellen"""
    db = get_db()
    
    fuhre = await db.fuhren.find_one({"_id": fuhre_id, "mandant_id": user["mandant_id"]})
    if not fuhre:
        raise HTTPException(status_code=404, detail="Fuhre nicht gefunden")
    
    ist_gutschrift = vorgang_typ == "GUTSCHRIFT"
    
    # Adresse bestimmen
    if ist_gutschrift:
        adresse_id = fuhre.get("id_adresse_start")
        adresse_name = fuhre.get("name_start")
    else:
        adresse_id = fuhre.get("id_adresse_ziel")
        adresse_name = fuhre.get("name_ziel")
    
    # Adresse laden
    adresse_data = {}
    if adresse_id:
        adresse = await db.adressen.find_one({"_id": adresse_id})
        if adresse:
            adresse_data = {
                "name1": adresse.get("name1"),
                "name2": adresse.get("name2"),
                "strasse": adresse.get("strasse"),
                "plz": adresse.get("plz"),
                "ort": adresse.get("ort"),
                "land": adresse.get("land"),
            }
    
    rechnungs_nr = await generate_rechnungs_nr(user["mandant_id"], ist_gutschrift)
    
    menge = fuhre.get("menge_abladen") or fuhre.get("menge_aufladen") or fuhre.get("menge_vorgabe") or 0
    einzelpreis = fuhre.get("preis_pro_einheit") or 0
    mwst_satz = 19.0
    
    position = {
        "id": str(uuid.uuid4()),
        "id_artikel": fuhre.get("id_artikel"),
        "artikel_bezeichnung": fuhre.get("artikel_bezeichnung") or "Aus Fuhre",
        "menge": menge,
        "einheit": fuhre.get("einheit", "kg"),
        "einzelpreis": einzelpreis,
        "mwst_satz": mwst_satz,
        "gesamt": round(menge * einzelpreis, 2)
    }
    
    summen = berechne_summen([position], mwst_satz)
    
    rechnung = {
        "_id": str(uuid.uuid4()),
        "mandant_id": user["mandant_id"],
        "rechnungs_nr": rechnungs_nr,
        "vorgang_typ": vorgang_typ,
        "id_fuhre": fuhre_id,
        "id_adresse": adresse_id,
        **adresse_data,
        "rechnungsdatum": datetime.utcnow().strftime("%Y-%m-%d"),
        "waehrung": "EUR",
        "mwst_satz": mwst_satz,
        "status": "OFFEN",
        "positionen": [position],
        **summen,
        "erstellt_am": datetime.utcnow(),
        "deleted": False,
    }
    
    await db.rechnungen.insert_one(rechnung)
    
    # Fuhre als abgerechnet markieren
    await db.fuhren.update_one(
        {"_id": fuhre_id},
        {"$set": {"status": "ABGERECHNET", "id_rechnung": rechnung["_id"]}}
    )
    
    rechnung["id"] = rechnung.pop("_id")
    
    return {"success": True, "data": rechnung}
