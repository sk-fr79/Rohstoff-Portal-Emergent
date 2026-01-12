"""
Firma Router - Verwaltung der Firmenstammdaten (Mandant)
Die Firma wird aus einer bestehenden Adresse übernommen und dient als Referenz für andere Module.
"""

from fastapi import APIRouter, HTTPException, Depends
from pydantic import BaseModel, Field
from typing import Optional, List, Dict, Any
from datetime import datetime
import uuid

from services.database import get_db
from utils.auth import get_current_user, require_permission

router = APIRouter(prefix="/api/system", tags=["Firma"])


# ========================== SCHEMAS ==========================

class FirmaUpdate(BaseModel):
    """Schema für Firmen-Update"""
    id_adresse: Optional[str] = None  # Referenz zur Quelladresse
    
    # Basisdaten
    name1: Optional[str] = Field(None, max_length=100)
    name2: Optional[str] = Field(None, max_length=100)
    
    # Adresse
    strasse: Optional[str] = Field(None, max_length=100)
    hausnummer: Optional[str] = Field(None, max_length=20)
    plz: Optional[str] = Field(None, max_length=10)
    ort: Optional[str] = Field(None, max_length=100)
    land: Optional[str] = Field(None, max_length=100)
    land_code: Optional[str] = Field(None, max_length=3)
    
    # Kontakt
    telefon: Optional[str] = Field(None, max_length=50)
    telefax: Optional[str] = Field(None, max_length=50)
    email: Optional[str] = Field(None, max_length=100)
    website: Optional[str] = Field(None, max_length=200)
    
    # Steuer & Handelsregister
    ust_id: Optional[str] = Field(None, max_length=30)
    steuernummer: Optional[str] = Field(None, max_length=30)
    handelsregister: Optional[str] = Field(None, max_length=100)
    handelsregister_gericht: Optional[str] = Field(None, max_length=100)
    
    # Nummern
    debitoren_nummer: Optional[str] = Field(None, max_length=30)
    kreditoren_nummer: Optional[str] = Field(None, max_length=30)
    
    # Zusätzliche Daten (aus Adresse übernommen)
    weitere_ustids: Optional[List[Dict[str, Any]]] = None
    lieferadressen: Optional[List[Dict[str, Any]]] = None
    bankverbindungen: Optional[List[Dict[str, Any]]] = None


# ========================== ENDPOINTS ==========================

@router.get("/firma")
async def get_firma(user = Depends(require_permission("system", "read"))):
    """Firmendaten des aktuellen Mandanten laden"""
    db = get_db()
    
    firma = await db.firma.find_one({"mandant_id": user["mandant_id"]})
    
    if not firma:
        return {"success": True, "data": None}
    
    # _id entfernen
    firma_data = {k: v for k, v in firma.items() if k != "_id"}
    
    return {"success": True, "data": firma_data}


@router.post("/firma")
async def create_or_update_firma(
    data: FirmaUpdate,
    user = Depends(require_permission("system", "write"))
):
    """Firmendaten erstellen oder aktualisieren"""
    db = get_db()
    
    firma_data = data.model_dump(exclude_none=True)
    firma_data["mandant_id"] = user["mandant_id"]
    firma_data["aktualisiert_am"] = datetime.utcnow().isoformat()
    firma_data["aktualisiert_von"] = user.get("benutzername", "system")
    
    # Prüfen ob bereits eine Firma existiert
    existing = await db.firma.find_one({"mandant_id": user["mandant_id"]})
    
    if existing:
        # Update
        await db.firma.update_one(
            {"mandant_id": user["mandant_id"]},
            {"$set": firma_data}
        )
        firma_data["id"] = existing["_id"]
    else:
        # Create
        firma_data["_id"] = str(uuid.uuid4())
        firma_data["erstellt_am"] = datetime.utcnow().isoformat()
        firma_data["erstellt_von"] = user.get("benutzername", "system")
        await db.firma.insert_one(firma_data)
        firma_data["id"] = firma_data["_id"]
    
    # Wenn eine Quelladresse angegeben wurde, diese als Firmenadresse markieren
    if data.id_adresse:
        # Alte Markierung entfernen
        await db.adressen.update_many(
            {"mandant_id": user["mandant_id"], "ist_firmenadresse": True},
            {"$set": {"ist_firmenadresse": False}}
        )
        # Neue Markierung setzen
        await db.adressen.update_one(
            {"_id": data.id_adresse, "mandant_id": user["mandant_id"]},
            {"$set": {"ist_firmenadresse": True}}
        )
    
    return {"success": True, "data": firma_data, "message": "Firmendaten gespeichert"}


@router.post("/firma/aus-adresse/{adresse_id}")
async def firma_aus_adresse_uebernehmen(
    adresse_id: str,
    user = Depends(require_permission("system", "write"))
):
    """Firmendaten aus einer bestehenden Adresse übernehmen"""
    db = get_db()
    
    # Adresse laden
    adresse = await db.adressen.find_one({
        "_id": adresse_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not adresse:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    # Bankverbindungen der Adresse laden
    bankverbindungen = adresse.get("bankverbindungen", [])
    
    # Firmendaten aus Adresse erstellen
    firma_data = {
        "mandant_id": user["mandant_id"],
        "id_adresse": adresse_id,
        
        # Basisdaten
        "name1": adresse.get("name1"),
        "name2": adresse.get("name2"),
        
        # Adresse
        "strasse": adresse.get("strasse"),
        "hausnummer": adresse.get("hausnummer"),
        "plz": adresse.get("plz"),
        "ort": adresse.get("ort"),
        "land": adresse.get("land"),
        "land_code": adresse.get("land_code"),
        
        # Kontakt
        "telefon": adresse.get("telefon"),
        "telefax": adresse.get("telefax"),
        "email": adresse.get("email"),
        "website": adresse.get("website"),
        
        # Steuer & Handelsregister
        "ust_id": adresse.get("umsatzsteuer_id") or adresse.get("ust_id"),
        "steuernummer": adresse.get("steuernummer"),
        "handelsregister": adresse.get("handelsregister"),
        "handelsregister_gericht": adresse.get("handelsregister_gericht"),
        
        # Nummern
        "debitoren_nummer": adresse.get("debitoren_nummer") or adresse.get("kundennummer"),
        "kreditoren_nummer": adresse.get("kreditoren_nummer") or adresse.get("lieferantennummer"),
        
        # Zusätzliche Daten
        "weitere_ustids": adresse.get("weitere_ustids", []),
        "lieferadressen": adresse.get("lieferadressen", []),
        "bankverbindungen": bankverbindungen,
        
        # Metadaten
        "aktualisiert_am": datetime.utcnow().isoformat(),
        "aktualisiert_von": user.get("benutzername", "system")
    }
    
    # Prüfen ob bereits eine Firma existiert
    existing = await db.firma.find_one({"mandant_id": user["mandant_id"]})
    
    if existing:
        await db.firma.update_one(
            {"mandant_id": user["mandant_id"]},
            {"$set": firma_data}
        )
        firma_data["id"] = existing["_id"]
    else:
        firma_data["_id"] = str(uuid.uuid4())
        firma_data["erstellt_am"] = datetime.utcnow().isoformat()
        firma_data["erstellt_von"] = user.get("benutzername", "system")
        await db.firma.insert_one(firma_data)
        firma_data["id"] = firma_data["_id"]
    
    # Alte Firmenmarkierung entfernen
    await db.adressen.update_many(
        {"mandant_id": user["mandant_id"], "ist_firmenadresse": True},
        {"$set": {"ist_firmenadresse": False}}
    )
    
    # Adresse als Firmenadresse markieren
    await db.adressen.update_one(
        {"_id": adresse_id},
        {"$set": {"ist_firmenadresse": True}}
    )
    
    return {
        "success": True, 
        "data": {k: v for k, v in firma_data.items() if k != "_id"},
        "message": f"Firmendaten aus '{adresse.get('name1')}' übernommen"
    }


@router.delete("/firma")
async def delete_firma(user = Depends(require_permission("system", "write"))):
    """Firmendaten löschen"""
    db = get_db()
    
    # Firmenmarkierung bei Adressen entfernen
    await db.adressen.update_many(
        {"mandant_id": user["mandant_id"], "ist_firmenadresse": True},
        {"$set": {"ist_firmenadresse": False}}
    )
    
    result = await db.firma.delete_one({"mandant_id": user["mandant_id"]})
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="Keine Firmendaten gefunden")
    
    return {"success": True, "message": "Firmendaten gelöscht"}


@router.post("/firma/sync")
async def sync_firma_manuell(user = Depends(require_permission("system", "write"))):
    """Firmendaten manuell aus der Quelladresse synchronisieren"""
    db = get_db()
    
    # Firma laden
    firma = await db.firma.find_one({"mandant_id": user["mandant_id"]})
    if not firma:
        raise HTTPException(status_code=404, detail="Keine Firmendaten gefunden")
    
    if not firma.get("id_adresse"):
        raise HTTPException(status_code=400, detail="Keine Quelladresse verknüpft")
    
    # Quelladresse laden
    adresse = await db.adressen.find_one({
        "_id": firma["id_adresse"],
        "mandant_id": user["mandant_id"]
    })
    
    if not adresse:
        raise HTTPException(status_code=404, detail="Quelladresse nicht gefunden")
    
    # Firmendaten synchronisieren
    sync_data = {
        # Stammdaten
        "name1": adresse.get("name1"),
        "name2": adresse.get("name2"),
        
        # Adresse
        "strasse": adresse.get("strasse"),
        "hausnummer": adresse.get("hausnummer"),
        "plz": adresse.get("plz"),
        "ort": adresse.get("ort"),
        "land": adresse.get("land"),
        "land_code": adresse.get("land_code"),
        
        # Kontakt
        "telefon": adresse.get("telefon"),
        "telefax": adresse.get("telefax"),
        "email": adresse.get("email"),
        "website": adresse.get("webseite"),
        
        # Steuer
        "ust_id": adresse.get("umsatzsteuer_id") or adresse.get("ust_id"),
        "steuernummer": adresse.get("steuernummer"),
        "handelsregister": adresse.get("handelsregister"),
        "handelsregister_gericht": adresse.get("handelsregister_gericht"),
        
        # Nummern
        "debitoren_nummer": adresse.get("debitoren_nummer") or adresse.get("kundennummer"),
        "kreditoren_nummer": adresse.get("kreditoren_nummer") or adresse.get("lieferantennummer"),
        
        # Dynamische Daten
        "weitere_ustids": adresse.get("weitere_ustids", []),
        "lieferadressen": adresse.get("lieferadressen", []),
        "bankverbindungen": adresse.get("bankverbindungen", []),
        
        # Sync-Metadaten
        "letzter_sync": datetime.utcnow().isoformat(),
        "sync_quelle": "manuell",
        "sync_von": user.get("benutzername", "system")
    }
    
    await db.firma.update_one(
        {"mandant_id": user["mandant_id"]},
        {"$set": sync_data}
    )
    
    # Aktualisierte Firma zurückgeben
    firma = await db.firma.find_one({"mandant_id": user["mandant_id"]})
    firma_data = {k: v for k, v in firma.items() if k != "_id"}
    
    return {
        "success": True, 
        "data": firma_data,
        "message": "Firmendaten erfolgreich synchronisiert"
    }
