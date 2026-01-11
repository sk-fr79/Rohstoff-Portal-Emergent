"""
Profil Router - Eigenes Benutzerprofil verwalten
"""

from fastapi import APIRouter, HTTPException, Depends, UploadFile, File
from pydantic import BaseModel, EmailStr, Field
from typing import Optional
from datetime import datetime
import base64

from services.database import get_db
from utils.auth import get_current_user, hash_password, verify_password

router = APIRouter(prefix="/api/profil", tags=["Profil"])


# ============================================================
# PYDANTIC MODELS
# ============================================================

class ProfilUpdate(BaseModel):
    vorname: Optional[str] = Field(None, max_length=100)
    nachname: Optional[str] = Field(None, max_length=100)
    email: Optional[EmailStr] = None
    telefon: Optional[str] = Field(None, max_length=50)
    mobil: Optional[str] = Field(None, max_length=50)
    position: Optional[str] = Field(None, max_length=100)
    email_signatur: Optional[str] = Field(None, max_length=2000)


class PasswortAendern(BaseModel):
    aktuelles_passwort: str = Field(..., min_length=1)
    neues_passwort: str = Field(..., min_length=6)


class ProfilbildUpdate(BaseModel):
    profilbild: str = Field(..., description="Base64-kodiertes Bild")


class UnterschriftUpdate(BaseModel):
    unterschrift: str = Field(..., description="Base64-kodiertes Unterschriftsbild")


# ============================================================
# PROFIL ENDPOINTS
# ============================================================

@router.get("")
async def get_profil(user = Depends(get_current_user)):
    """Eigenes Profil abrufen"""
    db = get_db()
    
    benutzer = await db.benutzer.find_one({"_id": user["id"]})
    if not benutzer:
        raise HTTPException(status_code=404, detail="Benutzer nicht gefunden")
    
    # Rolle und Abteilungen laden
    rolle = None
    if benutzer.get("rolle_id"):
        rolle_doc = await db.rollen.find_one({"_id": benutzer["rolle_id"]})
        if rolle_doc:
            rolle = {"id": rolle_doc["_id"], "name": rolle_doc.get("name")}
    
    abteilungen = []
    for abt_id in benutzer.get("abteilung_ids", []):
        abt_doc = await db.abteilungen.find_one({"_id": abt_id})
        if abt_doc:
            abteilungen.append({"id": abt_doc["_id"], "name": abt_doc.get("name")})
    
    return {
        "success": True,
        "data": {
            "id": benutzer["_id"],
            "benutzername": benutzer.get("benutzername"),
            "email": benutzer.get("email"),
            "vorname": benutzer.get("vorname"),
            "nachname": benutzer.get("nachname"),
            "kuerzel": benutzer.get("kuerzel"),
            "telefon": benutzer.get("telefon"),
            "mobil": benutzer.get("mobil"),
            "position": benutzer.get("position"),
            "email_signatur": benutzer.get("email_signatur"),
            "profilbild": benutzer.get("profilbild"),
            "unterschrift": benutzer.get("unterschrift"),
            "ist_admin": benutzer.get("ist_admin", False),
            "rolle": rolle,
            "abteilungen": abteilungen,
            "letzter_login": benutzer.get("letzter_login"),
            "erstellt_am": benutzer.get("erstellt_am"),
        }
    }


@router.put("")
async def update_profil(data: ProfilUpdate, user = Depends(get_current_user)):
    """Eigenes Profil aktualisieren"""
    db = get_db()
    
    # Nur nicht-None Felder aktualisieren
    update_data = {}
    for field, value in data.model_dump(exclude_unset=True).items():
        if value is not None:
            update_data[field] = value
    
    if not update_data:
        raise HTTPException(status_code=400, detail="Keine Daten zum Aktualisieren")
    
    # Email-Duplikat prüfen
    if "email" in update_data:
        existing = await db.benutzer.find_one({
            "email": update_data["email"],
            "_id": {"$ne": user["id"]}
        })
        if existing:
            raise HTTPException(status_code=400, detail="E-Mail wird bereits verwendet")
    
    update_data["aktualisiert_am"] = datetime.utcnow()
    
    result = await db.benutzer.update_one(
        {"_id": user["id"]},
        {"$set": update_data}
    )
    
    if result.modified_count == 0:
        raise HTTPException(status_code=404, detail="Benutzer nicht gefunden")
    
    return {"success": True, "message": "Profil aktualisiert"}


@router.post("/passwort")
async def change_password(data: PasswortAendern, user = Depends(get_current_user)):
    """Eigenes Passwort ändern"""
    db = get_db()
    
    # Aktuellen Benutzer laden
    benutzer = await db.benutzer.find_one({"_id": user["id"]})
    if not benutzer:
        raise HTTPException(status_code=404, detail="Benutzer nicht gefunden")
    
    # Aktuelles Passwort prüfen
    if not verify_password(data.aktuelles_passwort, benutzer.get("passwort_hash", "")):
        raise HTTPException(status_code=400, detail="Aktuelles Passwort ist falsch")
    
    # Neues Passwort setzen
    neuer_hash = hash_password(data.neues_passwort)
    
    await db.benutzer.update_one(
        {"_id": user["id"]},
        {"$set": {
            "passwort_hash": neuer_hash,
            "passwort_geaendert_am": datetime.utcnow()
        }}
    )
    
    return {"success": True, "message": "Passwort erfolgreich geändert"}


@router.put("/profilbild")
async def update_profilbild(data: ProfilbildUpdate, user = Depends(get_current_user)):
    """Profilbild aktualisieren"""
    db = get_db()
    
    # Validiere Base64
    try:
        # Prüfe ob es ein gültiges Base64-Bild ist
        if not data.profilbild.startswith("data:image/"):
            raise ValueError("Kein gültiges Bildformat")
    except Exception:
        raise HTTPException(status_code=400, detail="Ungültiges Bildformat")
    
    await db.benutzer.update_one(
        {"_id": user["id"]},
        {"$set": {
            "profilbild": data.profilbild,
            "aktualisiert_am": datetime.utcnow()
        }}
    )
    
    return {"success": True, "message": "Profilbild aktualisiert"}


@router.delete("/profilbild")
async def delete_profilbild(user = Depends(get_current_user)):
    """Profilbild löschen"""
    db = get_db()
    
    await db.benutzer.update_one(
        {"_id": user["id"]},
        {"$unset": {"profilbild": ""}}
    )
    
    return {"success": True, "message": "Profilbild gelöscht"}


@router.put("/unterschrift")
async def update_unterschrift(data: UnterschriftUpdate, user = Depends(get_current_user)):
    """Unterschrift aktualisieren (für digitale Signatur)"""
    db = get_db()
    
    # Validiere Base64
    try:
        if not data.unterschrift.startswith("data:image/"):
            raise ValueError("Kein gültiges Bildformat")
    except Exception:
        raise HTTPException(status_code=400, detail="Ungültiges Bildformat")
    
    await db.benutzer.update_one(
        {"_id": user["id"]},
        {"$set": {
            "unterschrift": data.unterschrift,
            "aktualisiert_am": datetime.utcnow()
        }}
    )
    
    return {"success": True, "message": "Unterschrift aktualisiert"}


@router.delete("/unterschrift")
async def delete_unterschrift(user = Depends(get_current_user)):
    """Unterschrift löschen"""
    db = get_db()
    
    await db.benutzer.update_one(
        {"_id": user["id"]},
        {"$unset": {"unterschrift": ""}}
    )
    
    return {"success": True, "message": "Unterschrift gelöscht"}
