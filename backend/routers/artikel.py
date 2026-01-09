"""
Artikel Router - CRUD und Validierung für Artikel/Sorten
"""

from fastapi import APIRouter, HTTPException, Depends
from pydantic import BaseModel, Field
from typing import Optional, List
from datetime import datetime
import uuid

from services.database import get_db
from services.validation import ArtikelValidator
from utils.auth import get_current_user

router = APIRouter(prefix="/api", tags=["Artikel"])


class ArtikelCreate(BaseModel):
    anr1: Optional[str] = Field(None, max_length=10)
    artbez1: str = Field(..., max_length=80)
    artbez2: Optional[str] = Field(None, max_length=1000)
    einheit: str = Field("kg", max_length=10)
    einheit_preis: str = Field("t", max_length=10)
    mengendivisor: int = Field(1000, ge=1)
    genauigkeit_mengen: int = Field(3, ge=0, le=6)
    artikelgruppe: Optional[str] = Field(None, max_length=100)
    artikelgruppe_fibu: Optional[str] = Field(None, max_length=100)
    aktiv: bool = True
    gefahrgut: bool = False
    ist_leergut: bool = False
    elektro_elektronik: bool = False
    ist_produkt: bool = False
    dienstleistung: bool = False
    end_of_waste: bool = False
    end_of_waste_lager: bool = False
    avv_code_eingang: Optional[str] = Field(None, max_length=50)
    avv_code_ausgang: Optional[str] = Field(None, max_length=50)
    eakcode: Optional[str] = Field(None, max_length=20)
    zolltarifnr: Optional[str] = Field(None, max_length=50)
    zolltarifnotiz: Optional[str] = Field(None, max_length=500)
    basel_code: Optional[str] = Field(None, max_length=80)
    basel_notiz: Optional[str] = Field(None, max_length=500)
    oecd_code: Optional[str] = Field(None, max_length=50)
    oecd_notiz: Optional[str] = Field(None, max_length=500)
    anhang7_3a_code: Optional[str] = Field(None, max_length=20)
    anhang7_3a_text: Optional[str] = Field(None, max_length=1000)
    anhang7_3b_code: Optional[str] = Field(None, max_length=20)
    anhang7_3b_text: Optional[str] = Field(None, max_length=1000)
    oesterreichische_avv: Optional[str] = Field(None, max_length=50)
    bemerkung_intern: Optional[str] = Field(None, max_length=1000)


class ArtikelUpdate(BaseModel):
    anr1: Optional[str] = None
    artbez1: Optional[str] = None
    artbez2: Optional[str] = None
    einheit: Optional[str] = None
    einheit_preis: Optional[str] = None
    mengendivisor: Optional[int] = None
    genauigkeit_mengen: Optional[int] = None
    artikelgruppe: Optional[str] = None
    artikelgruppe_fibu: Optional[str] = None
    aktiv: Optional[bool] = None
    gefahrgut: Optional[bool] = None
    ist_leergut: Optional[bool] = None
    elektro_elektronik: Optional[bool] = None
    ist_produkt: Optional[bool] = None
    dienstleistung: Optional[bool] = None
    end_of_waste: Optional[bool] = None
    end_of_waste_lager: Optional[bool] = None
    avv_code_eingang: Optional[str] = None
    avv_code_ausgang: Optional[str] = None
    eakcode: Optional[str] = None
    zolltarifnr: Optional[str] = None
    zolltarifnotiz: Optional[str] = None
    basel_code: Optional[str] = None
    basel_notiz: Optional[str] = None
    oecd_code: Optional[str] = None
    oecd_notiz: Optional[str] = None
    anhang7_3a_code: Optional[str] = None
    anhang7_3a_text: Optional[str] = None
    anhang7_3b_code: Optional[str] = None
    anhang7_3b_text: Optional[str] = None
    oesterreichische_avv: Optional[str] = None
    bemerkung_intern: Optional[str] = None


@router.get("/artikel")
async def get_artikel(
    suche: Optional[str] = None,
    aktiv: Optional[bool] = None,
    page: int = 1,
    limit: int = 20,
    user = Depends(get_current_user)
):
    """Artikel suchen mit Pagination"""
    db = get_db()
    query = {"mandant_id": user["mandant_id"]}
    
    if suche:
        query["$or"] = [
            {"artbez1": {"$regex": suche, "$options": "i"}},
            {"anr1": {"$regex": suche, "$options": "i"}},
            {"artikelgruppe": {"$regex": suche, "$options": "i"}},
        ]
    if aktiv is not None:
        query["aktiv"] = aktiv
    
    total = await db.artikel.count_documents(query)
    cursor = db.artikel.find(query).sort("artbez1", 1).skip((page - 1) * limit).limit(limit)
    artikel = await cursor.to_list(length=limit)
    
    for a in artikel:
        a["id"] = a.pop("_id")
    
    return {
        "success": True,
        "data": artikel,
        "pagination": {
            "page": page,
            "limit": limit,
            "total": total,
            "total_pages": (total + limit - 1) // limit,
        }
    }


@router.get("/artikel/{artikel_id}")
async def get_artikel_by_id(artikel_id: str, user = Depends(get_current_user)):
    """Artikel nach ID"""
    db = get_db()
    artikel = await db.artikel.find_one({
        "_id": artikel_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not artikel:
        raise HTTPException(status_code=404, detail="Artikel nicht gefunden")
    
    artikel["id"] = artikel.pop("_id")
    return {"success": True, "data": artikel}


@router.post("/artikel")
async def create_artikel(
    data: ArtikelCreate, 
    skip_validation: bool = False, 
    user = Depends(get_current_user)
):
    """Neuen Artikel erstellen"""
    db = get_db()
    
    if not skip_validation:
        result = await ArtikelValidator.validate(data.model_dump(), db)
        if not result.is_valid:
            raise HTTPException(status_code=400, detail=result.to_dict())
    
    artikel = {
        "_id": str(uuid.uuid4()),
        "mandant_id": user["mandant_id"],
        **data.model_dump(),
        "erstellt_am": datetime.utcnow(),
    }
    
    await db.artikel.insert_one(artikel)
    artikel["id"] = artikel.pop("_id")
    
    return {"success": True, "data": artikel}


@router.put("/artikel/{artikel_id}")
async def update_artikel(
    artikel_id: str,
    data: ArtikelUpdate,
    skip_validation: bool = False,
    user = Depends(get_current_user)
):
    """Artikel aktualisieren"""
    db = get_db()
    existing = await db.artikel.find_one({
        "_id": artikel_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not existing:
        raise HTTPException(status_code=404, detail="Artikel nicht gefunden")
    
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    
    if not skip_validation and update_data:
        merged = {**existing, **update_data}
        result = await ArtikelValidator.validate(merged, db)
        if not result.is_valid:
            raise HTTPException(status_code=400, detail=result.to_dict())
    
    update_data["letzte_aenderung"] = datetime.utcnow()
    
    await db.artikel.update_one(
        {"_id": artikel_id},
        {"$set": update_data}
    )
    
    updated = await db.artikel.find_one({"_id": artikel_id})
    updated["id"] = updated.pop("_id")
    
    return {"success": True, "data": updated}


@router.delete("/artikel/{artikel_id}")
async def delete_artikel(artikel_id: str, user = Depends(get_current_user)):
    """Artikel löschen (Hard Delete)"""
    db = get_db()
    
    # Prüfen ob Artikel existiert
    artikel = await db.artikel.find_one({
        "_id": artikel_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if not artikel:
        raise HTTPException(status_code=404, detail="Artikel nicht gefunden")
    
    # Artikel endgültig löschen
    result = await db.artikel.delete_one({
        "_id": artikel_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="Artikel konnte nicht gelöscht werden")
    
    return {"success": True, "message": "Artikel gelöscht"}


@router.post("/artikel/validieren")
async def validate_artikel(data: dict, user = Depends(get_current_user)):
    """Artikel validieren ohne zu speichern"""
    db = get_db()
    result = await ArtikelValidator.validate(data, db)
    return {"success": True, "validierung": result.to_dict()}
