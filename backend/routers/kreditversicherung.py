"""
Kreditversicherung Router - CRUD und Geschäftslogik
Portiert aus Echo2:
- KV_Info.java
- KV_Info_Entry.java
- KV_Lib.java
- STATKD_StatusErmittlung_Kreditversicherung.java
"""

from fastapi import APIRouter, HTTPException, Depends
from pydantic import BaseModel, Field
from typing import Optional, List
from datetime import datetime, date
import uuid

from services.database import get_db
from utils.auth import get_current_user

router = APIRouter(prefix="/api", tags=["Kreditversicherung"])


# ============================================================
# PYDANTIC MODELS
# ============================================================

class KreditversicherungPosition(BaseModel):
    """Position/Limit einer Kreditversicherung (aus JT_KREDITVERS_POS)"""
    id: str = Field(default_factory=lambda: str(uuid.uuid4()))
    gueltig_ab: str = Field(..., description="Gültig ab (ISO-Date)")
    gueltig_bis: Optional[str] = Field(None, description="Gültig bis (ISO-Date, optional)")
    betrag: float = Field(..., description="Versicherungslimit in EUR")
    betrag_anfrage: Optional[float] = Field(None, description="Angefragter Betrag")
    waehrung: str = "EUR"
    aktiv: bool = True
    bemerkungen: Optional[str] = None


class VersicherteAdresse(BaseModel):
    """Verknüpfte Adresse (aus JT_KREDITVERS_ADRESSE)"""
    id: str = Field(default_factory=lambda: str(uuid.uuid4()))
    adresse_id: str = Field(..., description="FK zu Adressen")
    adresse_name: Optional[str] = None
    adresse_ort: Optional[str] = None
    adresse_kdnr: Optional[str] = None


class KreditversicherungCreate(BaseModel):
    """Kreditversicherung erstellen (aus JT_KREDITVERS_KOPF)"""
    versicherungsnummer: Optional[str] = Field(None, max_length=50)
    versicherer_id: Optional[str] = Field(None, description="FK zu Adressen (Versicherungsgesellschaft)")
    versicherer_name: Optional[str] = None
    fakturierungsfrist: Optional[int] = Field(None, description="Verlängerte Fakturierungsfrist in Tagen")
    aktiv: bool = True
    bemerkungen: Optional[str] = None


class KreditversicherungUpdate(BaseModel):
    """Kreditversicherung aktualisieren"""
    versicherungsnummer: Optional[str] = None
    versicherer_id: Optional[str] = None
    versicherer_name: Optional[str] = None
    fakturierungsfrist: Optional[int] = None
    aktiv: Optional[bool] = None
    bemerkungen: Optional[str] = None


class KreditlimitInfo(BaseModel):
    """Einzelnes Kreditlimit (entspricht KV_Info_Entry)"""
    betrag: float
    betrag_anfrage: Optional[float] = None
    gueltig_ab: Optional[str] = None
    gueltig_bis: Optional[str] = None
    beschreibung: Optional[str] = None
    kv_id: Optional[str] = None
    versicherungsnummer: Optional[str] = None
    ist_intern: bool = False
    ist_aktiv: bool = True


class KreditstatusResponse(BaseModel):
    """Kreditstatus einer Adresse"""
    adresse_id: str
    gesamtlimit: float
    aktuelle_forderungen: float
    verfuegbar: float
    status: str  # "ok", "warnung", "ueberschritten"
    limits: List[KreditlimitInfo]


# ============================================================
# HELPER FUNCTIONS
# ============================================================

def ist_position_gueltig(position: dict, stichtag: date = None) -> bool:
    """Prüft ob eine KV-Position zum Stichtag gültig ist"""
    if stichtag is None:
        stichtag = date.today()
    
    gueltig_ab = position.get("gueltig_ab")
    gueltig_bis = position.get("gueltig_bis")
    
    if gueltig_ab:
        try:
            ab_date = datetime.fromisoformat(gueltig_ab).date()
            if ab_date > stichtag:
                return False
        except:
            pass
    
    if gueltig_bis:
        try:
            bis_date = datetime.fromisoformat(gueltig_bis).date()
            if bis_date < stichtag:
                return False
        except:
            pass
    
    return position.get("aktiv", True)


async def get_kreditlimits_for_adresse(adresse_id: str, db, stichtag: date = None) -> List[KreditlimitInfo]:
    """
    Ermittelt alle aktiven Kreditlimits für einen Kunden.
    Entspricht KV_Info.getKreditlimitsFor() aus Java.
    """
    if stichtag is None:
        stichtag = date.today()
    
    limits = []
    
    # 1. Externe Kreditversicherungen abfragen
    cursor = db.kreditversicherungen.find({
        "versicherte_adressen.adresse_id": adresse_id,
        "aktiv": True
    })
    
    async for kv in cursor:
        for position in kv.get("positionen", []):
            if ist_position_gueltig(position, stichtag):
                limits.append(KreditlimitInfo(
                    betrag=position.get("betrag", 0),
                    betrag_anfrage=position.get("betrag_anfrage"),
                    gueltig_ab=position.get("gueltig_ab"),
                    gueltig_bis=position.get("gueltig_bis"),
                    beschreibung=f"KV {kv.get('versicherungsnummer', kv['_id'][:8])}",
                    kv_id=kv["_id"],
                    versicherungsnummer=kv.get("versicherungsnummer"),
                    ist_intern=False,
                    ist_aktiv=True
                ))
    
    # 2. Internes Kreditlimit aus Adresse (KREDITBETRAG_INTERN)
    adresse = await db.adressen.find_one({"_id": adresse_id})
    if adresse and adresse.get("kredit_limit"):
        kredit_limit = adresse.get("kredit_limit", 0)
        gueltig_bis = adresse.get("kredit_limit_gueltig_bis")
        
        # Prüfen ob noch gültig
        ist_gueltig = True
        if gueltig_bis:
            try:
                bis_date = datetime.fromisoformat(gueltig_bis).date()
                ist_gueltig = bis_date >= stichtag
            except:
                pass
        
        if kredit_limit > 0:
            limits.append(KreditlimitInfo(
                betrag=kredit_limit,
                gueltig_bis=gueltig_bis,
                beschreibung="Interner Firmenkredit",
                ist_intern=True,
                ist_aktiv=ist_gueltig
            ))
    
    return limits


async def get_alle_verknuepfte_adressen(kv_id: str, db) -> List[str]:
    """
    Ermittelt alle Adress-IDs die zu einer Kreditversicherung gehören.
    Entspricht KV_Info.getAllAddressIDsConnected() aus Java.
    """
    kv = await db.kreditversicherungen.find_one({"_id": kv_id})
    if not kv:
        return []
    return [a["adresse_id"] for a in kv.get("versicherte_adressen", [])]


# ============================================================
# CRUD ENDPOINTS - KREDITVERSICHERUNGEN
# ============================================================

@router.get("/kreditversicherungen")
async def list_kreditversicherungen(
    aktiv: Optional[bool] = None,
    adresse_id: Optional[str] = None,
    skip: int = 0,
    limit: int = 100,
    user = Depends(get_current_user)
):
    """Liste aller Kreditversicherungen"""
    db = get_db()
    
    query = {"mandant_id": user["mandant_id"]}
    
    if aktiv is not None:
        query["aktiv"] = aktiv
    
    if adresse_id:
        query["versicherte_adressen.adresse_id"] = adresse_id
    
    cursor = db.kreditversicherungen.find(query).skip(skip).limit(limit).sort("created_at", -1)
    
    items = []
    async for doc in cursor:
        doc["id"] = doc.pop("_id")
        items.append(doc)
    
    total = await db.kreditversicherungen.count_documents(query)
    
    return {
        "success": True,
        "data": items,
        "total": total
    }


@router.get("/kreditversicherungen/{kv_id}")
async def get_kreditversicherung(kv_id: str, user = Depends(get_current_user)):
    """Einzelne Kreditversicherung abrufen"""
    db = get_db()
    
    kv = await db.kreditversicherungen.find_one({
        "_id": kv_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kv:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    kv["id"] = kv.pop("_id")
    return {"success": True, "data": kv}


@router.post("/kreditversicherungen")
async def create_kreditversicherung(data: KreditversicherungCreate, user = Depends(get_current_user)):
    """Neue Kreditversicherung anlegen"""
    db = get_db()
    
    kv_id = str(uuid.uuid4())[:8].upper()
    
    kv = {
        "_id": kv_id,
        "mandant_id": user["mandant_id"],
        **data.model_dump(),
        "versicherte_adressen": [],
        "positionen": [],
        "created_at": datetime.utcnow(),
        "updated_at": datetime.utcnow(),
        "created_by": user.get("benutzername", "system"),
    }
    
    await db.kreditversicherungen.insert_one(kv)
    
    kv["id"] = kv.pop("_id")
    return {"success": True, "data": kv}


@router.put("/kreditversicherungen/{kv_id}")
async def update_kreditversicherung(
    kv_id: str,
    data: KreditversicherungUpdate,
    user = Depends(get_current_user)
):
    """Kreditversicherung aktualisieren"""
    db = get_db()
    
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    update_data["updated_at"] = datetime.utcnow()
    
    result = await db.kreditversicherungen.update_one(
        {"_id": kv_id, "mandant_id": user["mandant_id"]},
        {"$set": update_data}
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    kv = await db.kreditversicherungen.find_one({"_id": kv_id})
    kv["id"] = kv.pop("_id")
    
    return {"success": True, "data": kv}


@router.delete("/kreditversicherungen/{kv_id}")
async def delete_kreditversicherung(kv_id: str, user = Depends(get_current_user)):
    """Kreditversicherung löschen (soft delete)"""
    db = get_db()
    
    result = await db.kreditversicherungen.update_one(
        {"_id": kv_id, "mandant_id": user["mandant_id"]},
        {"$set": {"aktiv": False, "deleted_at": datetime.utcnow()}}
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    return {"success": True}


# ============================================================
# VERKNÜPFTE ADRESSEN
# ============================================================

@router.get("/kreditversicherungen/{kv_id}/adressen")
async def get_kv_adressen(kv_id: str, user = Depends(get_current_user)):
    """Verknüpfte Adressen einer Kreditversicherung abrufen"""
    db = get_db()
    
    kv = await db.kreditversicherungen.find_one({
        "_id": kv_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kv:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    return {"success": True, "data": kv.get("versicherte_adressen", [])}


@router.post("/kreditversicherungen/{kv_id}/adressen")
async def add_kv_adresse(
    kv_id: str,
    data: VersicherteAdresse,
    user = Depends(get_current_user)
):
    """Adresse zu Kreditversicherung hinzufügen"""
    db = get_db()
    
    # Adresse laden für Namen
    adresse = await db.adressen.find_one({"_id": data.adresse_id})
    if adresse:
        data.adresse_name = adresse.get("name1", "")
        data.adresse_ort = adresse.get("ort", "")
        data.adresse_kdnr = adresse.get("kdnr", "")
    
    adresse_doc = data.model_dump()
    
    result = await db.kreditversicherungen.update_one(
        {"_id": kv_id, "mandant_id": user["mandant_id"]},
        {
            "$push": {"versicherte_adressen": adresse_doc},
            "$set": {"updated_at": datetime.utcnow()}
        }
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    return {"success": True, "data": adresse_doc}


@router.delete("/kreditversicherungen/{kv_id}/adressen/{adresse_link_id}")
async def remove_kv_adresse(
    kv_id: str,
    adresse_link_id: str,
    user = Depends(get_current_user)
):
    """Adresse von Kreditversicherung entfernen"""
    db = get_db()
    
    result = await db.kreditversicherungen.update_one(
        {"_id": kv_id, "mandant_id": user["mandant_id"]},
        {
            "$pull": {"versicherte_adressen": {"id": adresse_link_id}},
            "$set": {"updated_at": datetime.utcnow()}
        }
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    return {"success": True}


# ============================================================
# POSITIONEN (LIMITS)
# ============================================================

@router.get("/kreditversicherungen/{kv_id}/positionen")
async def get_kv_positionen(kv_id: str, user = Depends(get_current_user)):
    """Positionen einer Kreditversicherung abrufen"""
    db = get_db()
    
    kv = await db.kreditversicherungen.find_one({
        "_id": kv_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kv:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    return {"success": True, "data": kv.get("positionen", [])}


@router.post("/kreditversicherungen/{kv_id}/positionen")
async def add_kv_position(
    kv_id: str,
    data: KreditversicherungPosition,
    user = Depends(get_current_user)
):
    """Position zu Kreditversicherung hinzufügen"""
    db = get_db()
    
    position_doc = data.model_dump()
    
    result = await db.kreditversicherungen.update_one(
        {"_id": kv_id, "mandant_id": user["mandant_id"]},
        {
            "$push": {"positionen": position_doc},
            "$set": {"updated_at": datetime.utcnow()}
        }
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    return {"success": True, "data": position_doc}


@router.put("/kreditversicherungen/{kv_id}/positionen/{position_id}")
async def update_kv_position(
    kv_id: str,
    position_id: str,
    data: dict,
    user = Depends(get_current_user)
):
    """Position aktualisieren"""
    db = get_db()
    
    # Erlaubte Felder
    allowed_fields = ["gueltig_ab", "gueltig_bis", "betrag", "betrag_anfrage", "waehrung", "aktiv", "bemerkungen"]
    update_fields = {f"positionen.$.{k}": v for k, v in data.items() if k in allowed_fields}
    
    if not update_fields:
        raise HTTPException(status_code=400, detail="Keine gültigen Felder zum Aktualisieren")
    
    result = await db.kreditversicherungen.update_one(
        {
            "_id": kv_id,
            "mandant_id": user["mandant_id"],
            "positionen.id": position_id
        },
        {"$set": {**update_fields, "updated_at": datetime.utcnow()}}
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Position nicht gefunden")
    
    return {"success": True}


@router.delete("/kreditversicherungen/{kv_id}/positionen/{position_id}")
async def delete_kv_position(
    kv_id: str,
    position_id: str,
    user = Depends(get_current_user)
):
    """Position löschen"""
    db = get_db()
    
    result = await db.kreditversicherungen.update_one(
        {"_id": kv_id, "mandant_id": user["mandant_id"]},
        {
            "$pull": {"positionen": {"id": position_id}},
            "$set": {"updated_at": datetime.utcnow()}
        }
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    return {"success": True}


# ============================================================
# ABFRAGEN (aus KV_Info.java)
# ============================================================

@router.get("/adressen/{adresse_id}/kreditlimits")
async def get_adresse_kreditlimits(adresse_id: str, user = Depends(get_current_user)):
    """
    Alle Kreditlimits einer Adresse abrufen.
    Entspricht KV_Info.getKreditlimitsFor() aus Java.
    """
    db = get_db()
    
    limits = await get_kreditlimits_for_adresse(adresse_id, db)
    
    # Gesamtlimit berechnen (höchstes aktives Limit)
    aktive_limits = [l for l in limits if l.ist_aktiv]
    gesamtlimit = max([l.betrag for l in aktive_limits], default=0)
    
    return {
        "success": True,
        "data": {
            "adresse_id": adresse_id,
            "gesamtlimit": gesamtlimit,
            "limits": [l.model_dump() for l in limits]
        }
    }


@router.get("/adressen/{adresse_id}/kreditstatus")
async def get_adresse_kreditstatus(adresse_id: str, user = Depends(get_current_user)):
    """
    Aktueller Kreditstatus einer Adresse.
    Berechnet verfügbares Limit abzüglich offener Forderungen.
    """
    db = get_db()
    
    # Limits abrufen
    limits = await get_kreditlimits_for_adresse(adresse_id, db)
    aktive_limits = [l for l in limits if l.ist_aktiv]
    gesamtlimit = max([l.betrag for l in aktive_limits], default=0)
    
    # Offene Forderungen berechnen (aus Rechnungen)
    # TODO: Forderungen aus allen verknüpften Adressen summieren
    pipeline = [
        {"$match": {
            "mandant_id": user["mandant_id"],
            "adresse_id": adresse_id,
            "status": {"$in": ["offen", "teilbezahlt"]}
        }},
        {"$group": {"_id": None, "summe": {"$sum": "$brutto_summe"}}}
    ]
    
    result = await db.rechnungen.aggregate(pipeline).to_list(1)
    aktuelle_forderungen = result[0]["summe"] if result else 0
    
    verfuegbar = gesamtlimit - aktuelle_forderungen
    
    # Status bestimmen
    if gesamtlimit == 0:
        status = "kein_limit"
    elif aktuelle_forderungen >= gesamtlimit:
        status = "ueberschritten"
    elif aktuelle_forderungen >= gesamtlimit * 0.8:
        status = "warnung"
    else:
        status = "ok"
    
    return {
        "success": True,
        "data": KreditstatusResponse(
            adresse_id=adresse_id,
            gesamtlimit=gesamtlimit,
            aktuelle_forderungen=aktuelle_forderungen,
            verfuegbar=verfuegbar,
            status=status,
            limits=limits
        ).model_dump()
    }


# ============================================================
# PRÜFUNG (aus STATKD_StatusErmittlung_Kreditversicherung.java)
# ============================================================

class KreditpruefungRequest(BaseModel):
    """Anfrage für Kreditprüfung"""
    adresse_ids: List[str] = Field(..., description="Beteiligte Adressen")
    neuer_betrag: float = Field(..., description="Neuer Betrag der hinzukommt")


class KreditpruefungResponse(BaseModel):
    """Ergebnis der Kreditprüfung"""
    geprueft: bool
    status: str  # "ok", "warnung", "ueberschritten"
    gesamtlimit: float
    aktuelle_forderungen: float
    neuer_betrag: float
    neue_summe: float
    verfuegbar: float
    ueberschreitung: float
    warnung_text: Optional[str] = None
    betroffene_adressen: List[dict]


@router.post("/kreditpruefung")
async def pruefe_kreditlimit(
    data: KreditpruefungRequest,
    user = Depends(get_current_user)
):
    """
    Prüft ob ein neuer Betrag das Kreditlimit überschreiten würde.
    Entspricht STATKD_StatusErmittlung_Kreditversicherung.pruefeFuhre() aus Java.
    
    Bei Fuhren werden alle beteiligten Adressen (Start, Ziel, Zwischenstopps) übergeben.
    """
    db = get_db()
    
    # Alle verknüpften Adressen ermitteln
    alle_adressen = set(data.adresse_ids)
    betroffene_adressen = []
    gesamtlimit = 0.0
    
    for adresse_id in data.adresse_ids:
        limits = await get_kreditlimits_for_adresse(adresse_id, db)
        
        # Adresse laden
        adresse = await db.adressen.find_one({"_id": adresse_id})
        adresse_info = {
            "adresse_id": adresse_id,
            "name": adresse.get("name1", "") if adresse else "",
            "ort": adresse.get("ort", "") if adresse else "",
            "limits": [l.model_dump() for l in limits]
        }
        betroffene_adressen.append(adresse_info)
        
        for limit in limits:
            if limit.ist_aktiv:
                gesamtlimit = max(gesamtlimit, limit.betrag)
            
            # Alle verknüpften Adressen der KV hinzufügen
            if limit.kv_id:
                verknuepfte = await get_alle_verknuepfte_adressen(limit.kv_id, db)
                alle_adressen.update(verknuepfte)
    
    # Forderungen aller verknüpften Adressen summieren
    aktuelle_forderungen = 0.0
    for aid in alle_adressen:
        pipeline = [
            {"$match": {
                "mandant_id": user["mandant_id"],
                "adresse_id": aid,
                "status": {"$in": ["offen", "teilbezahlt"]}
            }},
            {"$group": {"_id": None, "summe": {"$sum": "$brutto_summe"}}}
        ]
        result = await db.rechnungen.aggregate(pipeline).to_list(1)
        if result:
            aktuelle_forderungen += result[0]["summe"]
    
    # Berechnung
    neue_summe = aktuelle_forderungen + data.neuer_betrag
    verfuegbar = gesamtlimit - aktuelle_forderungen
    ueberschreitung = max(0, neue_summe - gesamtlimit)
    
    # Status bestimmen
    if gesamtlimit == 0:
        status = "kein_limit"
        warnung_text = None
    elif neue_summe > gesamtlimit:
        status = "ueberschritten"
        warnung_text = (
            f"ACHTUNG: Die Forderungen überschreiten das Kreditlimit!\n"
            f"Kreditlimit: {gesamtlimit:,.2f} EUR\n"
            f"Aktuelle Forderungen: {aktuelle_forderungen:,.2f} EUR\n"
            f"Neuer Betrag: {data.neuer_betrag:,.2f} EUR\n"
            f"Überschreitung: {ueberschreitung:,.2f} EUR"
        )
    elif neue_summe > gesamtlimit * 0.8:
        status = "warnung"
        warnung_text = (
            f"Hinweis: Das Kreditlimit ist zu über 80% ausgeschöpft.\n"
            f"Verfügbar: {verfuegbar:,.2f} EUR von {gesamtlimit:,.2f} EUR"
        )
    else:
        status = "ok"
        warnung_text = None
    
    return {
        "success": True,
        "data": KreditpruefungResponse(
            geprueft=True,
            status=status,
            gesamtlimit=gesamtlimit,
            aktuelle_forderungen=aktuelle_forderungen,
            neuer_betrag=data.neuer_betrag,
            neue_summe=neue_summe,
            verfuegbar=verfuegbar,
            ueberschreitung=ueberschreitung,
            warnung_text=warnung_text,
            betroffene_adressen=betroffene_adressen
        ).model_dump()
    }


@router.post("/fuhren/{fuhre_id}/kreditpruefung")
async def pruefe_fuhre_kreditlimit(fuhre_id: str, user = Depends(get_current_user)):
    """
    Prüft eine Fuhre gegen Kreditlimits aller beteiligten Adressen.
    """
    db = get_db()
    
    # Fuhre laden
    fuhre = await db.fuhren.find_one({
        "_id": fuhre_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not fuhre:
        raise HTTPException(status_code=404, detail="Fuhre nicht gefunden")
    
    # Beteiligte Adressen sammeln
    adresse_ids = []
    if fuhre.get("start_adresse_id"):
        adresse_ids.append(fuhre["start_adresse_id"])
    if fuhre.get("ziel_adresse_id"):
        adresse_ids.append(fuhre["ziel_adresse_id"])
    
    # Zwischenstopps
    for ort in fuhre.get("zwischenorte", []):
        if ort.get("adresse_id"):
            adresse_ids.append(ort["adresse_id"])
    
    # Betrag aus Fuhre (geschätzter Wert)
    neuer_betrag = fuhre.get("preis_netto", 0) or fuhre.get("geschaetzter_wert", 0)
    
    # Kreditprüfung durchführen
    request = KreditpruefungRequest(adresse_ids=adresse_ids, neuer_betrag=neuer_betrag)
    result = await pruefe_kreditlimit(request, user)
    
    return result
