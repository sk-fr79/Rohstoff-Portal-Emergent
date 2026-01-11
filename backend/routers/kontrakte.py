"""
Kontrakte Router - CRUD für Einkaufs- und Verkaufskontrakte
"""

from fastapi import APIRouter, HTTPException, Depends
from pydantic import BaseModel, Field
from typing import Optional, List, Dict, Any
from datetime import datetime
import uuid

from services.database import get_db
from services.validation import KontraktValidator
from utils.auth import get_current_user, require_permission

router = APIRouter(prefix="/api", tags=["Kontrakte"])


class KontraktCreate(BaseModel):
    name1: str = Field(..., max_length=40)
    vorgang_typ: str = Field("EK", max_length=2)
    buchungsnummer: Optional[str] = None
    name2: Optional[str] = None
    strasse: Optional[str] = None
    hausnummer: Optional[str] = None
    plz: Optional[str] = None
    ort: Optional[str] = None
    land: Optional[str] = None
    gueltig_von: Optional[str] = None
    gueltig_bis: Optional[str] = None
    waehrung_kurz: str = "EUR"
    status: str = "OFFEN"
    aktiv: bool = True
    id_adresse: Optional[str] = None
    id_artikel: Optional[str] = None
    bemerkungen: Optional[str] = None
    positionen: Optional[List[Dict[str, Any]]] = None


class KontraktUpdate(BaseModel):
    name1: Optional[str] = None
    vorgang_typ: Optional[str] = None
    buchungsnummer: Optional[str] = None
    name2: Optional[str] = None
    strasse: Optional[str] = None
    hausnummer: Optional[str] = None
    plz: Optional[str] = None
    ort: Optional[str] = None
    land: Optional[str] = None
    gueltig_von: Optional[str] = None
    gueltig_bis: Optional[str] = None
    waehrung_kurz: Optional[str] = None
    status: Optional[str] = None
    aktiv: Optional[bool] = None
    abgeschlossen: Optional[bool] = None
    id_adresse: Optional[str] = None
    id_artikel: Optional[str] = None
    bemerkungen: Optional[str] = None
    positionen: Optional[List[Dict[str, Any]]] = None


async def generate_buchungsnummer(mandant_id: str, ist_einkauf: bool) -> str:
    """Generiert eine eindeutige Buchungsnummer"""
    db = get_db()
    mandant = await db.mandanten.find_one({"_id": mandant_id})
    prefix = "EKK" if ist_einkauf else "VKK"
    
    if mandant:
        prefix = mandant.get("buchungsprefix_ekk" if ist_einkauf else "buchungsprefix_vkk", prefix)
    
    year = datetime.now().year
    count = await db.kontrakte.count_documents({
        "mandant_id": mandant_id,
        "buchungsnummer": {"$regex": f"^{prefix}{year}"}
    })
    
    return f"{prefix}{year}{count + 1:04d}"


@router.get("/kontrakte")
async def get_kontrakte(
    suche: Optional[str] = None,
    typ: Optional[str] = None,
    status: Optional[str] = None,
    page: int = 1,
    limit: int = 20,
    user = Depends(require_permission("kontrakte", "read"))
):
    """Kontrakte suchen"""
    db = get_db()
    query = {"mandant_id": user["mandant_id"], "deleted": {"$ne": True}}
    
    if suche:
        query["$or"] = [
            {"name1": {"$regex": suche, "$options": "i"}},
            {"buchungsnummer": {"$regex": suche, "$options": "i"}},
        ]
    if typ:
        query["vorgang_typ"] = typ
    if status:
        query["status"] = status
    
    total = await db.kontrakte.count_documents(query)
    cursor = db.kontrakte.find(query).sort("erstellt_am", -1).skip((page - 1) * limit).limit(limit)
    kontrakte = await cursor.to_list(length=limit)
    
    for k in kontrakte:
        k["id"] = k.pop("_id")
    
    return {
        "success": True,
        "data": kontrakte,
        "pagination": {"page": page, "limit": limit, "total": total}
    }


@router.get("/kontrakte/{kontrakt_id}")
async def get_kontrakt(kontrakt_id: str, user = Depends(require_permission("kontrakte", "read"))):
    """Kontrakt nach ID"""
    db = get_db()
    kontrakt = await db.kontrakte.find_one({
        "_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kontrakt:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    kontrakt["id"] = kontrakt.pop("_id")
    return {"success": True, "data": kontrakt}


@router.post("/kontrakte")
async def create_kontrakt(
    data: KontraktCreate,
    skip_validation: bool = False,
    user = Depends(require_permission("kontrakte", "write"))
):
    """Neuen Kontrakt erstellen"""
    db = get_db()
    
    if not skip_validation:
        result = await KontraktValidator.validate(data.model_dump(), db)
        if not result.is_valid:
            raise HTTPException(status_code=400, detail=result.to_dict())
    
    ist_einkauf = data.vorgang_typ == "EK"
    buchungsnummer = data.buchungsnummer or await generate_buchungsnummer(user["mandant_id"], ist_einkauf)
    
    kontrakt = {
        "_id": str(uuid.uuid4()),
        "mandant_id": user["mandant_id"],
        "buchungsnummer": buchungsnummer,
        **data.model_dump(exclude={"buchungsnummer"}),
        "erstellt_am": datetime.utcnow(),
        "deleted": False,
    }
    
    await db.kontrakte.insert_one(kontrakt)
    kontrakt["id"] = kontrakt.pop("_id")
    
    return {"success": True, "data": kontrakt}


@router.put("/kontrakte/{kontrakt_id}")
async def update_kontrakt(
    kontrakt_id: str,
    data: KontraktUpdate,
    skip_validation: bool = False,
    user = Depends(require_permission("kontrakte", "write"))
):
    """Kontrakt aktualisieren"""
    db = get_db()
    existing = await db.kontrakte.find_one({
        "_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not existing:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    
    if not skip_validation and update_data:
        merged = {**existing, **update_data}
        result = await KontraktValidator.validate(merged, db)
        if not result.is_valid:
            raise HTTPException(status_code=400, detail=result.to_dict())
    
    update_data["letzte_aenderung"] = datetime.utcnow()
    
    await db.kontrakte.update_one({"_id": kontrakt_id}, {"$set": update_data})
    
    updated = await db.kontrakte.find_one({"_id": kontrakt_id})
    updated["id"] = updated.pop("_id")
    
    return {"success": True, "data": updated}


@router.delete("/kontrakte/{kontrakt_id}")
async def delete_kontrakt(kontrakt_id: str, user = Depends(require_permission("kontrakte", "full"))):
    """Kontrakt löschen (Hard Delete)"""
    db = get_db()
    
    # Prüfen ob Kontrakt existiert
    kontrakt = await db.kontrakte.find_one({
        "_id": kontrakt_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if not kontrakt:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    # Kontrakt endgültig löschen
    result = await db.kontrakte.delete_one({
        "_id": kontrakt_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="Kontrakt konnte nicht gelöscht werden")
    
    return {"success": True, "message": "Kontrakt gelöscht"}


@router.post("/kontrakte/{kontrakt_id}/positionen")
async def add_kontrakt_position(
    kontrakt_id: str,
    data: dict,
    user = Depends(require_permission("kontrakte", "write"))
):
    """Position zum Kontrakt hinzufügen"""
    db = get_db()
    position = {
        "id": str(uuid.uuid4()),
        **data,
        "erstellt_am": datetime.utcnow().isoformat()
    }
    
    await db.kontrakte.update_one(
        {"_id": kontrakt_id, "mandant_id": user["mandant_id"]},
        {"$push": {"positionen": position}}
    )
    
    return {"success": True, "data": position}


@router.post("/kontrakte/{kontrakt_id}/abschliessen")
async def abschliessen_kontrakt(kontrakt_id: str, user = Depends(require_permission("kontrakte", "write"))):
    """Kontrakt abschließen"""
    db = get_db()
    await db.kontrakte.update_one(
        {"_id": kontrakt_id, "mandant_id": user["mandant_id"]},
        {"$set": {"abgeschlossen": True, "status": "ERFUELLT"}}
    )
    return {"success": True}


@router.post("/kontrakte/validieren")
async def validate_kontrakt(data: dict, user = Depends(require_permission("kontrakte", "read"))):
    """Kontrakt validieren"""
    db = get_db()
    result = await KontraktValidator.validate(data, db)
    return {"success": True, "validierung": result.to_dict()}
