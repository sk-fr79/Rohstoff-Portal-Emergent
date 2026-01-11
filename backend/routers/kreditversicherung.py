"""
Kreditversicherung Router - CRUD und Geschäftslogik
NEUGESTALTUNG gemäß Benutzeranforderungen:

Struktur:
- 1 Hauptvertrag (Kopf) mit Gesamtlimit + Start-/Enddatum
- n Kundenpositionen (Unterverträge) mit eigenem Limit pro Kunde
- Auslastung = Summe Kundenlimits / Gesamtlimit * 100%
- Enddatum Hauptvertrag sticht immer das Enddatum der Unterverträge
"""

from fastapi import APIRouter, HTTPException, Depends, Query
from pydantic import BaseModel, Field
from typing import Optional, List
from datetime import datetime, date
import uuid

from services.database import get_db
from utils.auth import get_current_user, require_permission

router = APIRouter(prefix="/api", tags=["Kreditversicherung"])


# ============================================================
# PYDANTIC MODELS - NEUE STRUKTUR
# ============================================================

class KundenPosition(BaseModel):
    """Untervertrag pro Kunde (1:n zum Hauptvertrag)"""
    id: str = Field(default_factory=lambda: str(uuid.uuid4()))
    adresse_id: str = Field(..., description="FK zu Adressen (Kunde)")
    adresse_name: Optional[str] = None
    adresse_ort: Optional[str] = None
    adresse_kdnr: Optional[str] = None
    
    # Kreditlimit pro Kunde
    kreditlimit: float = Field(0, description="Kreditlimit für diesen Kunden in EUR")
    unterversicherungsnummer: Optional[str] = Field(None, description="Unterversicherungsnummer beim Versicherer")
    fakturierungsfrist: Optional[int] = Field(None, description="Verlängerte Fakturierungsfrist in Tagen")
    
    # Gültigkeit (Enddatum Hauptvertrag sticht)
    gueltig_bis: Optional[str] = Field(None, description="Eigenes Gültigkeitsdatum (ISO-Date, Hauptvertrag sticht)")
    
    aktiv: bool = True
    bemerkungen: Optional[str] = None
    
    # Audit
    erstellt_am: Optional[str] = None
    geaendert_am: Optional[str] = None


class KundenPositionCreate(BaseModel):
    """Kundenposition erstellen"""
    adresse_id: str
    kreditlimit: float = 0
    unterversicherungsnummer: Optional[str] = None
    fakturierungsfrist: Optional[int] = None
    gueltig_bis: Optional[str] = None
    aktiv: bool = True
    bemerkungen: Optional[str] = None


class KundenPositionUpdate(BaseModel):
    """Kundenposition aktualisieren"""
    kreditlimit: Optional[float] = None
    unterversicherungsnummer: Optional[str] = None
    fakturierungsfrist: Optional[int] = None
    gueltig_bis: Optional[str] = None
    aktiv: Optional[bool] = None
    bemerkungen: Optional[str] = None


class KreditversicherungKopfCreate(BaseModel):
    """Hauptvertrag erstellen"""
    versicherungsnummer: Optional[str] = Field(None, max_length=50)
    versicherer_id: Optional[str] = Field(None, description="FK zu Adressen (Versicherungsgesellschaft)")
    versicherer_name: Optional[str] = None
    
    # Gesamtvolumen des Vertrags
    gesamtlimit: float = Field(0, description="Gesamtvolumen/Gesamtlimit des Vertrags in EUR")
    
    # Vertragslaufzeit
    gueltig_von: Optional[str] = Field(None, description="Vertragsbeginn (ISO-Date)")
    gueltig_bis: Optional[str] = Field(None, description="Vertragsende (ISO-Date) - sticht Unterverträge")
    
    aktiv: bool = True
    bemerkungen: Optional[str] = None


class KreditversicherungKopfUpdate(BaseModel):
    """Hauptvertrag aktualisieren"""
    versicherungsnummer: Optional[str] = None
    versicherer_id: Optional[str] = None
    versicherer_name: Optional[str] = None
    gesamtlimit: Optional[float] = None
    gueltig_von: Optional[str] = None
    gueltig_bis: Optional[str] = None
    aktiv: Optional[bool] = None
    bemerkungen: Optional[str] = None


class KreditlimitInfo(BaseModel):
    """Einzelnes Kreditlimit für Adress-Abfrage"""
    betrag: float
    beschreibung: Optional[str] = None
    kv_id: Optional[str] = None
    versicherungsnummer: Optional[str] = None
    unterversicherungsnummer: Optional[str] = None
    gueltig_ab: Optional[str] = None
    gueltig_bis: Optional[str] = None  # Effektives Datum (min aus Kopf und Position)
    ist_intern: bool = False
    ist_aktiv: bool = True
    fakturierungsfrist: Optional[int] = None


class KreditstatusResponse(BaseModel):
    """Kreditstatus einer Adresse"""
    adresse_id: str
    gesamtlimit: float
    aktuelle_forderungen: float
    verfuegbar: float
    status: str  # "ok", "warnung", "ueberschritten", "kein_limit"
    limits: List[KreditlimitInfo]


# ============================================================
# HELPER FUNCTIONS
# ============================================================

def berechne_effektives_enddatum(kopf_gueltig_bis: Optional[str], position_gueltig_bis: Optional[str]) -> Optional[str]:
    """
    Berechnet das effektive Enddatum einer Kundenposition.
    Hauptvertrag-Enddatum sticht IMMER das Positions-Enddatum.
    """
    if not kopf_gueltig_bis and not position_gueltig_bis:
        return None
    if not kopf_gueltig_bis:
        return position_gueltig_bis
    if not position_gueltig_bis:
        return kopf_gueltig_bis
    
    # Beide vorhanden -> das frühere nehmen (Kopf sticht)
    try:
        kopf_date = datetime.fromisoformat(kopf_gueltig_bis).date()
        pos_date = datetime.fromisoformat(position_gueltig_bis).date()
        return kopf_gueltig_bis if kopf_date <= pos_date else position_gueltig_bis
    except (ValueError, TypeError):
        return kopf_gueltig_bis


def ist_position_gueltig(kopf: dict, position: dict, stichtag: date = None) -> bool:
    """
    Prüft ob eine Kundenposition zum Stichtag gültig ist.
    Berücksichtigt sowohl Kopf- als auch Positions-Enddatum.
    """
    if stichtag is None:
        stichtag = date.today()
    
    # Position muss aktiv sein
    if not position.get("aktiv", True):
        return False
    
    # Kopf-Startdatum prüfen
    if kopf.get("gueltig_von"):
        try:
            von_date = datetime.fromisoformat(kopf["gueltig_von"]).date()
            if von_date > stichtag:
                return False
        except (ValueError, TypeError):
            pass
    
    # Effektives Enddatum prüfen (Kopf sticht)
    effektives_ende = berechne_effektives_enddatum(
        kopf.get("gueltig_bis"),
        position.get("gueltig_bis")
    )
    
    if effektives_ende:
        try:
            bis_date = datetime.fromisoformat(effektives_ende).date()
            if bis_date < stichtag:
                return False
        except (ValueError, TypeError):
            pass
    
    return True


def berechne_auslastung(gesamtlimit: float, summe_kundenlimits: float) -> float:
    """Berechnet die Auslastung in Prozent"""
    if gesamtlimit <= 0:
        return 0.0 if summe_kundenlimits == 0 else 100.0
    return round((summe_kundenlimits / gesamtlimit) * 100, 2)


async def get_kreditlimits_for_adresse(adresse_id: str, db, stichtag: date = None) -> List[KreditlimitInfo]:
    """
    Ermittelt alle aktiven Kreditlimits für einen Kunden.
    """
    if stichtag is None:
        stichtag = date.today()
    
    limits = []
    
    # Externe Kreditversicherungen abfragen
    cursor = db.kreditversicherungen.find({
        "kunden_positionen.adresse_id": adresse_id,
        "aktiv": True
    })
    
    async for kv in cursor:
        for position in kv.get("kunden_positionen", []):
            if position.get("adresse_id") != adresse_id:
                continue
                
            if ist_position_gueltig(kv, position, stichtag):
                effektives_ende = berechne_effektives_enddatum(
                    kv.get("gueltig_bis"),
                    position.get("gueltig_bis")
                )
                
                limits.append(KreditlimitInfo(
                    betrag=position.get("kreditlimit", 0),
                    beschreibung=f"KV {kv.get('versicherungsnummer', kv['_id'][:8])}",
                    kv_id=kv["_id"],
                    versicherungsnummer=kv.get("versicherungsnummer"),
                    unterversicherungsnummer=position.get("unterversicherungsnummer"),
                    gueltig_ab=kv.get("gueltig_von"),
                    gueltig_bis=effektives_ende,
                    ist_intern=False,
                    ist_aktiv=True,
                    fakturierungsfrist=position.get("fakturierungsfrist")
                ))
    
    # Internes Kreditlimit aus Adresse
    adresse = await db.adressen.find_one({"_id": adresse_id})
    if adresse and adresse.get("kredit_limit"):
        kredit_limit = adresse.get("kredit_limit", 0)
        gueltig_bis = adresse.get("kredit_limit_gueltig_bis")
        
        ist_gueltig = True
        if gueltig_bis:
            try:
                bis_date = datetime.fromisoformat(gueltig_bis).date()
                ist_gueltig = bis_date >= stichtag
            except (ValueError, TypeError):
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


# ============================================================
# CRUD ENDPOINTS - HAUPTVERTRAG (KOPF)
# ============================================================

@router.get("/kreditversicherungen")
async def list_kreditversicherungen(
    aktiv: Optional[bool] = None,
    adresse_id: Optional[str] = None,
    search: Optional[str] = None,
    skip: int = 0,
    limit: int = 100,
    user = Depends(require_permission("kreditversicherungen", "read"))
):
    """Liste aller Kreditversicherungen mit Auslastung"""
    db = get_db()
    
    query = {"mandant_id": user["mandant_id"]}
    
    if aktiv is not None:
        query["aktiv"] = aktiv
    
    if adresse_id:
        query["kunden_positionen.adresse_id"] = adresse_id
    
    if search:
        query["$or"] = [
            {"versicherungsnummer": {"$regex": search, "$options": "i"}},
            {"versicherer_name": {"$regex": search, "$options": "i"}},
            {"kunden_positionen.adresse_name": {"$regex": search, "$options": "i"}},
        ]
    
    cursor = db.kreditversicherungen.find(query).skip(skip).limit(limit).sort("created_at", -1)
    
    items = []
    async for doc in cursor:
        doc["id"] = doc.pop("_id")
        
        # Auslastung berechnen
        positionen = doc.get("kunden_positionen", [])
        summe_kundenlimits = sum(p.get("kreditlimit", 0) for p in positionen if p.get("aktiv", True))
        gesamtlimit = doc.get("gesamtlimit", 0)
        doc["auslastung_prozent"] = berechne_auslastung(gesamtlimit, summe_kundenlimits)
        doc["summe_kundenlimits"] = summe_kundenlimits
        doc["anzahl_kunden"] = len([p for p in positionen if p.get("aktiv", True)])
        
        items.append(doc)
    
    total = await db.kreditversicherungen.count_documents(query)
    
    return {
        "success": True,
        "data": items,
        "total": total
    }


@router.get("/kreditversicherungen/{kv_id}")
async def get_kreditversicherung(kv_id: str, user = Depends(require_permission("kreditversicherungen", "read"))):
    """Einzelne Kreditversicherung mit Kundenpositionen und Auslastung abrufen"""
    db = get_db()
    
    kv = await db.kreditversicherungen.find_one({
        "_id": kv_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kv:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    kv["id"] = kv.pop("_id")
    
    # Auslastung berechnen
    positionen = kv.get("kunden_positionen", [])
    summe_kundenlimits = sum(p.get("kreditlimit", 0) for p in positionen if p.get("aktiv", True))
    gesamtlimit = kv.get("gesamtlimit", 0)
    kv["auslastung_prozent"] = berechne_auslastung(gesamtlimit, summe_kundenlimits)
    kv["summe_kundenlimits"] = summe_kundenlimits
    kv["anzahl_kunden"] = len([p for p in positionen if p.get("aktiv", True)])
    
    return {"success": True, "data": kv}


@router.post("/kreditversicherungen")
async def create_kreditversicherung(data: KreditversicherungKopfCreate, user = Depends(require_permission("kreditversicherungen", "write"))):
    """Neuen Hauptvertrag anlegen"""
    db = get_db()
    
    kv_id = "KV-" + str(uuid.uuid4())[:8].upper()
    
    kv = {
        "_id": kv_id,
        "mandant_id": user["mandant_id"],
        **data.model_dump(),
        "kunden_positionen": [],
        "created_at": datetime.utcnow().isoformat(),
        "updated_at": datetime.utcnow().isoformat(),
        "created_by": user.get("benutzername", "system"),
    }
    
    await db.kreditversicherungen.insert_one(kv)
    
    kv["id"] = kv.pop("_id")
    kv["auslastung_prozent"] = 0.0
    kv["summe_kundenlimits"] = 0.0
    kv["anzahl_kunden"] = 0
    
    return {"success": True, "data": kv}


@router.put("/kreditversicherungen/{kv_id}")
async def update_kreditversicherung(
    kv_id: str,
    data: KreditversicherungKopfUpdate,
    user = Depends(require_permission("kreditversicherungen", "write"))
):
    """Hauptvertrag aktualisieren"""
    db = get_db()
    
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    update_data["updated_at"] = datetime.utcnow().isoformat()
    
    result = await db.kreditversicherungen.update_one(
        {"_id": kv_id, "mandant_id": user["mandant_id"]},
        {"$set": update_data}
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    # Aktualisierten Datensatz zurückgeben
    return await get_kreditversicherung(kv_id, user)


@router.delete("/kreditversicherungen/{kv_id}")
async def delete_kreditversicherung(kv_id: str, user = Depends(require_permission("kreditversicherungen", "full"))):
    """Kreditversicherung löschen (Hard Delete)"""
    db = get_db()
    
    # Prüfen ob KV existiert
    kv = await db.kreditversicherungen.find_one({
        "_id": kv_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if not kv:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    # Kreditversicherung endgültig löschen (Hard Delete)
    result = await db.kreditversicherungen.delete_one({
        "_id": kv_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="Kreditversicherung konnte nicht gelöscht werden")
    
    return {"success": True, "message": "Kreditversicherung gelöscht"}


# ============================================================
# CRUD ENDPOINTS - KUNDENPOSITIONEN (UNTERVERTRÄGE)
# ============================================================

@router.get("/kreditversicherungen/{kv_id}/positionen")
async def get_kv_positionen(
    kv_id: str, 
    search: Optional[str] = None,
    aktiv: Optional[bool] = None,
    user = Depends(require_permission("kreditversicherungen", "read"))
):
    """Kundenpositionen einer Kreditversicherung abrufen"""
    db = get_db()
    
    kv = await db.kreditversicherungen.find_one({
        "_id": kv_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kv:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    positionen = kv.get("kunden_positionen", [])
    
    # Filter anwenden
    if aktiv is not None:
        positionen = [p for p in positionen if p.get("aktiv", True) == aktiv]
    
    if search:
        search_lower = search.lower()
        positionen = [
            p for p in positionen 
            if search_lower in (p.get("adresse_name") or "").lower()
            or search_lower in (p.get("adresse_kdnr") or "").lower()
            or search_lower in (p.get("adresse_ort") or "").lower()
            or search_lower in (p.get("unterversicherungsnummer") or "").lower()
        ]
    
    return {
        "success": True, 
        "data": positionen,
        "kopf_gueltig_bis": kv.get("gueltig_bis")
    }


@router.post("/kreditversicherungen/{kv_id}/positionen")
async def add_kv_position(
    kv_id: str,
    data: KundenPositionCreate,
    user = Depends(require_permission("kreditversicherungen", "write"))
):
    """Kundenposition zum Hauptvertrag hinzufügen"""
    db = get_db()
    
    # Prüfen ob KV existiert
    kv = await db.kreditversicherungen.find_one({
        "_id": kv_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kv:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    # Prüfen ob Adresse bereits verknüpft ist
    existing = [p for p in kv.get("kunden_positionen", []) if p.get("adresse_id") == data.adresse_id]
    if existing:
        raise HTTPException(status_code=400, detail="Kunde ist bereits bei dieser Kreditversicherung hinterlegt")
    
    # Adresse laden für Namen
    adresse = await db.adressen.find_one({"_id": data.adresse_id})
    if not adresse:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    position = KundenPosition(
        id=str(uuid.uuid4()),
        adresse_id=data.adresse_id,
        adresse_name=adresse.get("name1", ""),
        adresse_ort=adresse.get("ort", ""),
        adresse_kdnr=adresse.get("kdnr", ""),
        kreditlimit=data.kreditlimit,
        unterversicherungsnummer=data.unterversicherungsnummer,
        fakturierungsfrist=data.fakturierungsfrist,
        gueltig_bis=data.gueltig_bis,
        aktiv=data.aktiv,
        bemerkungen=data.bemerkungen,
        erstellt_am=datetime.utcnow().isoformat(),
        geaendert_am=datetime.utcnow().isoformat(),
    )
    
    result = await db.kreditversicherungen.update_one(
        {"_id": kv_id, "mandant_id": user["mandant_id"]},
        {
            "$push": {"kunden_positionen": position.model_dump()},
            "$set": {"updated_at": datetime.utcnow().isoformat()}
        }
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    return {"success": True, "data": position.model_dump()}


@router.put("/kreditversicherungen/{kv_id}/positionen/{position_id}")
async def update_kv_position(
    kv_id: str,
    position_id: str,
    data: KundenPositionUpdate,
    user = Depends(require_permission("kreditversicherungen", "write"))
):
    """Kundenposition aktualisieren"""
    db = get_db()
    
    # Erlaubte Felder
    update_fields = {}
    for field, value in data.model_dump().items():
        if value is not None:
            update_fields[f"kunden_positionen.$.{field}"] = value
    
    update_fields["kunden_positionen.$.geaendert_am"] = datetime.utcnow().isoformat()
    update_fields["updated_at"] = datetime.utcnow().isoformat()
    
    if not update_fields:
        raise HTTPException(status_code=400, detail="Keine gültigen Felder zum Aktualisieren")
    
    result = await db.kreditversicherungen.update_one(
        {
            "_id": kv_id,
            "mandant_id": user["mandant_id"],
            "kunden_positionen.id": position_id
        },
        {"$set": update_fields}
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Position nicht gefunden")
    
    return {"success": True}


@router.delete("/kreditversicherungen/{kv_id}/positionen/{position_id}")
async def delete_kv_position(
    kv_id: str,
    position_id: str,
    user = Depends(require_permission("kreditversicherungen", "full"))
):
    """Kundenposition entfernen"""
    db = get_db()
    
    result = await db.kreditversicherungen.update_one(
        {"_id": kv_id, "mandant_id": user["mandant_id"]},
        {
            "$pull": {"kunden_positionen": {"id": position_id}},
            "$set": {"updated_at": datetime.utcnow().isoformat()}
        }
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Kreditversicherung nicht gefunden")
    
    return {"success": True}


# ============================================================
# ABFRAGEN FÜR ADRESSEN
# ============================================================

@router.get("/adressen/{adresse_id}/kreditlimits")
async def get_adresse_kreditlimits(adresse_id: str, user = Depends(require_permission("kreditversicherungen", "read"))):
    """
    Alle Kreditlimits einer Adresse abrufen.
    """
    db = get_db()
    
    limits = await get_kreditlimits_for_adresse(adresse_id, db)
    
    # Gesamtlimit berechnen (Summe aller aktiven Limits)
    aktive_limits = [lim for lim in limits if lim.ist_aktiv]
    gesamtlimit = sum([lim.betrag for lim in aktive_limits])
    
    return {
        "success": True,
        "data": {
            "adresse_id": adresse_id,
            "gesamtlimit": gesamtlimit,
            "limits": [lim.model_dump() for lim in limits]
        }
    }


@router.get("/adressen/{adresse_id}/kreditstatus")
async def get_adresse_kreditstatus(adresse_id: str, user = Depends(require_permission("kreditversicherungen", "read"))):
    """
    Aktueller Kreditstatus einer Adresse.
    Berechnet verfügbares Limit abzüglich offener Forderungen.
    """
    db = get_db()
    
    # Limits abrufen
    limits = await get_kreditlimits_for_adresse(adresse_id, db)
    aktive_limits = [lim for lim in limits if lim.ist_aktiv]
    gesamtlimit = sum([lim.betrag for lim in aktive_limits])
    
    # Offene Forderungen berechnen (aus Rechnungen)
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
# KREDITPRÜFUNG FÜR FUHREN
# ============================================================

class KreditpruefungRequest(BaseModel):
    """Anfrage für Kreditprüfung"""
    adresse_ids: List[str] = Field(..., description="Beteiligte Adressen")
    neuer_betrag: float = Field(..., description="Neuer Betrag der hinzukommt")


class KreditpruefungResponse(BaseModel):
    """Ergebnis der Kreditprüfung"""
    geprueft: bool
    status: str  # "ok", "warnung", "ueberschritten", "kein_limit"
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
    user = Depends(require_permission("kreditversicherungen", "read"))
):
    """
    Prüft ob ein neuer Betrag das Kreditlimit überschreiten würde.
    """
    db = get_db()
    
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
            "limits": [lim.model_dump() for lim in limits]
        }
        betroffene_adressen.append(adresse_info)
        
        for limit in limits:
            if limit.ist_aktiv:
                gesamtlimit += limit.betrag
    
    # Forderungen berechnen
    aktuelle_forderungen = 0.0
    for aid in data.adresse_ids:
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
async def pruefe_fuhre_kreditlimit(fuhre_id: str, user = Depends(require_permission("kreditversicherungen", "read"))):
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
    if fuhre.get("lieferant_id"):
        adresse_ids.append(fuhre["lieferant_id"])
    if fuhre.get("abnehmer_id"):
        adresse_ids.append(fuhre["abnehmer_id"])
    
    # Betrag aus Fuhre
    neuer_betrag = fuhre.get("preis_netto", 0) or fuhre.get("geschaetzter_wert", 0)
    
    # Kreditprüfung durchführen
    request = KreditpruefungRequest(adresse_ids=adresse_ids, neuer_betrag=neuer_betrag)
    result = await pruefe_kreditlimit(request, user)
    
    return result


# ============================================================
# CLEANUP ENDPOINTS
# ============================================================

@router.delete("/cleanup/soft-deleted")
async def cleanup_soft_deleted_records(user = Depends(require_permission("admin", "full"))):
    """
    Bereinigt alle Soft-Deleted Datensätze (Hard Delete).
    Entfernt:
    - Adressen mit aktiv=false
    - Kreditversicherungen mit aktiv=false
    - Kontrakte mit deleted=true
    - Fuhren mit deleted=true
    - Rechnungen mit deleted=true
    """
    db = get_db()
    mandant_id = user["mandant_id"]
    
    results = {}
    
    # 1. Soft-gelöschte Adressen finden und löschen
    adressen_to_delete = await db.adressen.find(
        {"mandant_id": mandant_id, "aktiv": False}
    ).to_list(1000)
    
    for addr in adressen_to_delete:
        addr_id = addr["_id"]
        # Aus Kreditversicherungen entfernen
        await db.kreditversicherungen.update_many(
            {"mandant_id": mandant_id},
            {"$pull": {
                "kunden_positionen": {"adresse_id": addr_id},
                "versicherte_adressen": {"adresse_id": addr_id}
            }}
        )
    
    result_adressen = await db.adressen.delete_many(
        {"mandant_id": mandant_id, "aktiv": False}
    )
    results["adressen_geloescht"] = result_adressen.deleted_count
    
    # 2. Soft-gelöschte Kreditversicherungen löschen
    result_kv = await db.kreditversicherungen.delete_many(
        {"mandant_id": mandant_id, "aktiv": False}
    )
    results["kreditversicherungen_geloescht"] = result_kv.deleted_count
    
    # 3. Soft-gelöschte Kontrakte löschen
    result_kontrakte = await db.kontrakte.delete_many(
        {"mandant_id": mandant_id, "deleted": True}
    )
    results["kontrakte_geloescht"] = result_kontrakte.deleted_count
    
    # 4. Soft-gelöschte Fuhren löschen
    result_fuhren = await db.fuhren.delete_many(
        {"mandant_id": mandant_id, "deleted": True}
    )
    results["fuhren_geloescht"] = result_fuhren.deleted_count
    
    # 5. Soft-gelöschte Rechnungen löschen
    result_rechnungen = await db.rechnungen.delete_many(
        {"mandant_id": mandant_id, "deleted": True}
    )
    results["rechnungen_geloescht"] = result_rechnungen.deleted_count
    
    return {
        "success": True,
        "message": "Soft-Deleted Datensätze bereinigt",
        "details": results
    }

