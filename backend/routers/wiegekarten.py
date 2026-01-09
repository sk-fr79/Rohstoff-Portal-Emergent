"""
Wiegekarten Router - Fahrzeugwaage und Wiegescheine
"""

from fastapi import APIRouter, HTTPException, Depends
from pydantic import BaseModel, Field
from typing import Optional, List, Dict, Any
from datetime import datetime
import uuid
import random

from services.database import get_db
from utils.auth import get_current_user

router = APIRouter(prefix="/api", tags=["Wiegekarten"])


class WiegekarteCreate(BaseModel):
    typ_wiegekarte: str = "W"
    ist_lieferant: bool = True
    kennzeichen: str = Field(..., max_length=15)
    trailer: Optional[str] = None
    adresse_lieferant: Optional[str] = None
    id_adresse_lieferant: Optional[str] = None
    id_artikel: Optional[str] = None
    artikel_bezeichnung: Optional[str] = None
    strahlung_geprueft: bool = False
    fremdcontainer: bool = False
    sorte_hand: bool = False
    bemerkungen: Optional[str] = None


class WiegekarteUpdate(BaseModel):
    kennzeichen: Optional[str] = None
    trailer: Optional[str] = None
    adresse_lieferant: Optional[str] = None
    id_adresse_lieferant: Optional[str] = None
    id_artikel: Optional[str] = None
    artikel_bezeichnung: Optional[str] = None
    strahlung_geprueft: Optional[bool] = None
    bemerkungen: Optional[str] = None


async def generate_wiegekarten_nr(mandant_id: str) -> tuple:
    """Generiert Wiegekartennummer"""
    db = get_db()
    year = datetime.now().year
    count = await db.wiegekarten.count_documents({
        "mandant_id": mandant_id,
        "jahr": year
    })
    lfd_nr = count + 1
    return f"WK{year}-{lfd_nr:05d}", lfd_nr


@router.get("/wiegekarten")
async def get_wiegekarten(
    suche: Optional[str] = None,
    zustand: Optional[str] = None,
    nur_offene: bool = False,
    page: int = 1,
    limit: int = 20,
    user = Depends(get_current_user)
):
    """Wiegekarten suchen"""
    db = get_db()
    query = {"mandant_id": user["mandant_id"], "storno": {"$ne": True}}
    
    if suche:
        query["$or"] = [
            {"wiegekarten_nr": {"$regex": suche, "$options": "i"}},
            {"kennzeichen": {"$regex": suche, "$options": "i"}},
        ]
    if zustand:
        query["zustand"] = zustand
    if nur_offene:
        query["zustand"] = {"$in": ["NEU", "WAEGUNG1"]}
    
    total = await db.wiegekarten.count_documents(query)
    cursor = db.wiegekarten.find(query).sort("erstellt_am", -1).skip((page - 1) * limit).limit(limit)
    wiegekarten = await cursor.to_list(length=limit)
    
    # IDs normalisieren und ObjectId-Problem beheben
    result = []
    for w in wiegekarten:
        item = {k: (str(v) if k == "_id" or hasattr(v, '__str__') and 'ObjectId' in str(type(v)) else v) for k, v in w.items()}
        item["id"] = str(item.pop("_id"))
        result.append(item)
    
    return {"success": True, "data": result, "pagination": {"page": page, "limit": limit, "total": total}}


@router.get("/wiegekarten/{wiegekarte_id}")
async def get_wiegekarte(wiegekarte_id: str, user = Depends(get_current_user)):
    """Wiegekarte nach ID"""
    db = get_db()
    wk = await db.wiegekarten.find_one({"_id": wiegekarte_id, "mandant_id": user["mandant_id"]})
    
    if not wk:
        raise HTTPException(status_code=404, detail="Wiegekarte nicht gefunden")
    
    wk["id"] = wk.pop("_id")
    return {"success": True, "data": wk}


@router.post("/wiegekarten")
async def create_wiegekarte(data: WiegekarteCreate, user = Depends(get_current_user)):
    """Neue Wiegekarte erstellen"""
    db = get_db()
    
    wk_nr, lfd_nr = await generate_wiegekarten_nr(user["mandant_id"])
    
    wiegekarte = {
        "_id": str(uuid.uuid4()),
        "mandant_id": user["mandant_id"],
        "wiegekarten_nr": wk_nr,
        "lfd_nr": lfd_nr,
        "jahr": datetime.now().year,
        "zustand": "NEU",
        "storno": False,
        **data.model_dump(),
        "erstellt_am": datetime.utcnow(),
        "erstellt_von": user.get("benutzername"),
    }
    
    await db.wiegekarten.insert_one(wiegekarte)
    wiegekarte["id"] = wiegekarte.pop("_id")
    
    return {"success": True, "data": wiegekarte}


@router.put("/wiegekarten/{wiegekarte_id}")
async def update_wiegekarte(wiegekarte_id: str, data: WiegekarteUpdate, user = Depends(get_current_user)):
    """Wiegekarte aktualisieren"""
    db = get_db()
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    update_data["letzte_aenderung"] = datetime.utcnow()
    
    result = await db.wiegekarten.update_one(
        {"_id": wiegekarte_id, "mandant_id": user["mandant_id"]},
        {"$set": update_data}
    )
    
    if result.modified_count == 0:
        raise HTTPException(status_code=404, detail="Wiegekarte nicht gefunden")
    
    updated = await db.wiegekarten.find_one({"_id": wiegekarte_id})
    updated["id"] = updated.pop("_id")
    
    return {"success": True, "data": updated}


@router.post("/wiegekarten/{wiegekarte_id}/waegung/{waegung_nr}")
async def speichere_waegung(wiegekarte_id: str, waegung_nr: int, data: dict, user = Depends(get_current_user)):
    """Wägung speichern"""
    db = get_db()
    
    wk = await db.wiegekarten.find_one({"_id": wiegekarte_id, "mandant_id": user["mandant_id"]})
    if not wk:
        raise HTTPException(status_code=404, detail="Wiegekarte nicht gefunden")
    
    gewicht = data.get("gewicht", 0)
    
    update = {
        f"waegung{waegung_nr}_gewicht": gewicht,
        f"waegung{waegung_nr}_datum": datetime.utcnow(),
        f"waegung{waegung_nr}_benutzer": user.get("benutzername"),
        "zustand": f"WAEGUNG{waegung_nr}",
    }
    
    # Netto berechnen bei zweiter Wägung
    if waegung_nr == 2:
        w1 = wk.get("waegung1_gewicht", 0)
        w2 = gewicht
        brutto = max(w1, w2)
        tara = min(w1, w2)
        netto = brutto - tara
        update.update({
            "brutto_kg": brutto,
            "tara_kg": tara,
            "netto_kg": netto,
            "zustand": "GEDRUCKT"
        })
    
    await db.wiegekarten.update_one({"_id": wiegekarte_id}, {"$set": update})
    
    updated = await db.wiegekarten.find_one({"_id": wiegekarte_id})
    updated["id"] = updated.pop("_id")
    
    return {"success": True, "data": updated}


@router.post("/wiegekarten/{wiegekarte_id}/storno")
async def storniere_wiegekarte(wiegekarte_id: str, user = Depends(get_current_user)):
    """Wiegekarte stornieren"""
    db = get_db()
    await db.wiegekarten.update_one(
        {"_id": wiegekarte_id, "mandant_id": user["mandant_id"]},
        {"$set": {"storno": True, "zustand": "STORNO"}}
    )
    return {"success": True}


@router.delete("/wiegekarten/{wiegekarte_id}")
async def delete_wiegekarte(wiegekarte_id: str, user = Depends(get_current_user)):
    """Wiegekarte löschen"""
    db = get_db()
    result = await db.wiegekarten.delete_one({"_id": wiegekarte_id, "mandant_id": user["mandant_id"]})
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="Wiegekarte nicht gefunden")
    
    return {"success": True}


# Waage-Endpunkte
@router.get("/waage/status")
async def get_waage_status(user = Depends(get_current_user)):
    """Waage-Status (Demo)"""
    return {"success": True, "data": {"connected": True, "model": "Systec IT 4000", "mode": "demo"}}


@router.post("/waage/lesen")
async def lese_waage(user = Depends(get_current_user)):
    """Waage auslesen (Demo: Zufallsgewicht)"""
    gewicht = random.randint(5000, 35000)
    return {"success": True, "data": {"gewicht_kg": gewicht, "stabil": True}}


@router.get("/waage/config")
async def get_waage_config(user = Depends(get_current_user)):
    """Waage-Konfiguration"""
    return {
        "success": True,
        "data": {
            "ip": "192.168.1.100",
            "port": 4001,
            "demo_modus": True,
            "model": "Systec IT 4000"
        }
    }


@router.get("/wiegekarten/statistik")
async def get_wiegekarten_statistik(user = Depends(get_current_user)):
    """Wiegekarten-Statistik"""
    db = get_db()
    heute = datetime.now().replace(hour=0, minute=0, second=0, microsecond=0)
    
    gesamt_heute = await db.wiegekarten.count_documents({
        "mandant_id": user["mandant_id"],
        "erstellt_am": {"$gte": heute}
    })
    
    offen = await db.wiegekarten.count_documents({
        "mandant_id": user["mandant_id"],
        "zustand": {"$in": ["NEU", "WAEGUNG1"]}
    })
    
    return {"success": True, "data": {"heute": gesamt_heute, "offen": offen}}
