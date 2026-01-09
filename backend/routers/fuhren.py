"""
Fuhren Router - Transportverwaltung
"""

from fastapi import APIRouter, HTTPException, Depends
from pydantic import BaseModel, Field
from typing import Optional, List, Dict, Any
from datetime import datetime
import uuid

from services.database import get_db
from services.validation import FuhreValidator
from utils.auth import get_current_user

router = APIRouter(prefix="/api", tags=["Fuhren"])


class FuhreCreate(BaseModel):
    id_adresse_start: Optional[str] = None
    name_start: Optional[str] = None
    id_adresse_ziel: Optional[str] = None
    name_ziel: Optional[str] = None
    id_artikel: Optional[str] = None
    artikel_bezeichnung: Optional[str] = None
    datum_abholung: Optional[str] = None
    datum_anlieferung: Optional[str] = None
    menge_vorgabe: Optional[float] = None
    menge_aufladen: Optional[float] = None
    menge_abladen: Optional[float] = None
    einheit: str = "kg"
    kennzeichen: Optional[str] = None
    fahrer: Optional[str] = None
    id_kontrakt: Optional[str] = None
    avv_code: Optional[str] = None
    bemerkungen: Optional[str] = None
    status: str = "OFFEN"


class FuhreUpdate(BaseModel):
    id_adresse_start: Optional[str] = None
    name_start: Optional[str] = None
    id_adresse_ziel: Optional[str] = None
    name_ziel: Optional[str] = None
    id_artikel: Optional[str] = None
    artikel_bezeichnung: Optional[str] = None
    datum_abholung: Optional[str] = None
    datum_anlieferung: Optional[str] = None
    menge_vorgabe: Optional[float] = None
    menge_aufladen: Optional[float] = None
    menge_abladen: Optional[float] = None
    einheit: Optional[str] = None
    kennzeichen: Optional[str] = None
    fahrer: Optional[str] = None
    id_kontrakt: Optional[str] = None
    avv_code: Optional[str] = None
    bemerkungen: Optional[str] = None
    status: Optional[str] = None


async def generate_fuhren_nr(mandant_id: str) -> str:
    """Generiert Fuhren-Nummer"""
    db = get_db()
    year = datetime.now().year
    count = await db.fuhren.count_documents({
        "mandant_id": mandant_id,
        "fuhren_nr": {"$regex": f"^FU{year}"}
    })
    return f"FU{year}-{count + 1:05d}"


@router.get("/fuhren")
async def get_fuhren(
    suche: Optional[str] = None,
    status: Optional[str] = None,
    page: int = 1,
    limit: int = 20,
    user = Depends(get_current_user)
):
    """Fuhren suchen"""
    db = get_db()
    query = {"mandant_id": user["mandant_id"], "deleted": {"$ne": True}}
    
    if suche:
        query["$or"] = [
            {"fuhren_nr": {"$regex": suche, "$options": "i"}},
            {"kennzeichen": {"$regex": suche, "$options": "i"}},
            {"name_start": {"$regex": suche, "$options": "i"}},
            {"name_ziel": {"$regex": suche, "$options": "i"}},
        ]
    if status:
        query["status"] = status
    
    total = await db.fuhren.count_documents(query)
    cursor = db.fuhren.find(query).sort("erstellt_am", -1).skip((page - 1) * limit).limit(limit)
    fuhren = await cursor.to_list(length=limit)
    
    for f in fuhren:
        f["id"] = f.pop("_id")
    
    return {"success": True, "data": fuhren, "pagination": {"page": page, "limit": limit, "total": total}}


@router.get("/fuhren/{fuhre_id}")
async def get_fuhre_by_id(fuhre_id: str, user = Depends(get_current_user)):
    """Fuhre nach ID"""
    db = get_db()
    fuhre = await db.fuhren.find_one({"_id": fuhre_id, "mandant_id": user["mandant_id"]})
    
    if not fuhre:
        raise HTTPException(status_code=404, detail="Fuhre nicht gefunden")
    
    fuhre["id"] = fuhre.pop("_id")
    return {"success": True, "data": fuhre}


@router.post("/fuhren")
async def create_fuhre(data: FuhreCreate, skip_validation: bool = False, user = Depends(get_current_user)):
    """Neue Fuhre erstellen"""
    db = get_db()
    
    if not skip_validation:
        result = await FuhreValidator.validate(data.model_dump(), db)
        if not result.is_valid:
            raise HTTPException(status_code=400, detail=result.to_dict())
    
    fuhren_nr = await generate_fuhren_nr(user["mandant_id"])
    
    fuhre = {
        "_id": str(uuid.uuid4()),
        "mandant_id": user["mandant_id"],
        "fuhren_nr": fuhren_nr,
        **data.model_dump(),
        "erstellt_am": datetime.utcnow(),
        "deleted": False,
    }
    
    await db.fuhren.insert_one(fuhre)
    fuhre["id"] = fuhre.pop("_id")
    
    return {"success": True, "data": fuhre}


@router.put("/fuhren/{fuhre_id}")
async def update_fuhre(fuhre_id: str, data: FuhreUpdate, skip_validation: bool = False, user = Depends(get_current_user)):
    """Fuhre aktualisieren"""
    db = get_db()
    existing = await db.fuhren.find_one({"_id": fuhre_id, "mandant_id": user["mandant_id"]})
    
    if not existing:
        raise HTTPException(status_code=404, detail="Fuhre nicht gefunden")
    
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    
    # Status-Validierung
    if "status" in update_data and update_data["status"] != existing.get("status"):
        result = await FuhreValidator.validate_status_transition(
            existing.get("status", "OFFEN"),
            update_data["status"]
        )
        if not result.is_valid:
            raise HTTPException(status_code=400, detail=result.to_dict())
    
    if not skip_validation and update_data:
        merged = {**existing, **update_data}
        result = await FuhreValidator.validate(merged, db)
        if not result.is_valid:
            raise HTTPException(status_code=400, detail=result.to_dict())
    
    update_data["letzte_aenderung"] = datetime.utcnow()
    
    await db.fuhren.update_one({"_id": fuhre_id}, {"$set": update_data})
    
    updated = await db.fuhren.find_one({"_id": fuhre_id})
    updated["id"] = updated.pop("_id")
    
    return {"success": True, "data": updated}


@router.delete("/fuhren/{fuhre_id}")
async def delete_fuhre(fuhre_id: str, user = Depends(get_current_user)):
    """Fuhre löschen (Hard Delete)"""
    db = get_db()
    
    # Prüfen ob Fuhre existiert
    fuhre = await db.fuhren.find_one({
        "_id": fuhre_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if not fuhre:
        raise HTTPException(status_code=404, detail="Fuhre nicht gefunden")
    
    # Fuhre endgültig löschen
    result = await db.fuhren.delete_one({
        "_id": fuhre_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="Fuhre konnte nicht gelöscht werden")
    
    return {"success": True, "message": "Fuhre gelöscht"}


@router.post("/fuhren/{fuhre_id}/storno")
async def storno_fuhre(fuhre_id: str, grund: str = "", user = Depends(get_current_user)):
    """Fuhre stornieren"""
    db = get_db()
    await db.fuhren.update_one(
        {"_id": fuhre_id, "mandant_id": user["mandant_id"]},
        {"$set": {
            "status": "STORNIERT",
            "ist_storniert": True,
            "storno_grund": grund,
            "storno_datum": datetime.utcnow()
        }}
    )
    return {"success": True}


@router.post("/fuhren/validieren")
async def validate_fuhre(data: dict, user = Depends(get_current_user)):
    """Fuhre validieren"""
    db = get_db()
    result = await FuhreValidator.validate(data, db)
    return {"success": True, "validierung": result.to_dict()}
