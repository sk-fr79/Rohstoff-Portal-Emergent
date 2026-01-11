"""
Auth Router - Login, Logout, User Info
"""

from fastapi import APIRouter, HTTPException, Depends, status
from pydantic import BaseModel
from typing import Optional, Dict, Any
from datetime import datetime

from services.database import get_db
from utils.auth import (
    hash_password, 
    verify_password, 
    create_token, 
    get_current_user
)
from models.auth import LoginRequest, LoginResponse

router = APIRouter(prefix="/api/auth", tags=["Auth"])


@router.post("/login", response_model=LoginResponse)
async def login(request: LoginRequest):
    """Benutzer anmelden"""
    db = get_db()
    user = await db.benutzer.find_one({"benutzername": request.benutzername})
    
    if not user:
        return LoginResponse(success=False, error="Ungültige Anmeldedaten")
    
    if not user.get("aktiv", True):
        return LoginResponse(success=False, error="Benutzer ist deaktiviert")
    
    if not verify_password(request.passwort, user.get("passwort_hash", "")):
        return LoginResponse(success=False, error="Ungültige Anmeldedaten")
    
    # Token erstellen
    token = create_token(user["_id"], user["mandant_id"], user.get("email", ""))
    
    # Letzter Login aktualisieren
    await db.benutzer.update_one(
        {"_id": user["_id"]},
        {"$set": {"letzter_login": datetime.utcnow()}}
    )
    
    return LoginResponse(
        success=True,
        access_token=token,
        user={
            "id": user["_id"],
            "mandant_id": user["mandant_id"],
            "benutzername": user["benutzername"],
            "email": user.get("email"),
            "vorname": user.get("vorname"),
            "nachname": user.get("nachname"),
            "kuerzel": user.get("kuerzel"),
            "ist_admin": user.get("ist_admin", False),
            "profilbild": user.get("profilbild"),
        }
    )


@router.get("/me")
async def get_me(user = Depends(get_current_user)):
    """Aktuellen Benutzer abrufen"""
    db = get_db()
    mandant = await db.mandanten.find_one({"_id": user["mandant_id"]})
    return {
        "success": True,
        "user": user,
        "mandant": {
            "id": mandant["_id"],
            "name": mandant.get("name1", ""),
            "kurzname": mandant.get("kurzname", ""),
        } if mandant else None
    }


@router.post("/logout")
async def logout(user = Depends(get_current_user)):
    """Benutzer abmelden"""
    return {"success": True, "message": "Erfolgreich abgemeldet"}
