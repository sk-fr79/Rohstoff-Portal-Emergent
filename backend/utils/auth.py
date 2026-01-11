"""
Auth Utilities - Password Hashing, JWT Token Management
"""

import hashlib
import secrets
import jwt
import os
from datetime import datetime, timedelta
from typing import Dict, Any
from fastapi import HTTPException, status, Depends
from fastapi.security import HTTPBearer, HTTPAuthorizationCredentials

from services.database import get_db

JWT_SECRET = os.environ.get("JWT_SECRET", "rohstoff_jwt_secret_key_2024_very_secure")
JWT_ALGORITHM = "HS256"
JWT_EXPIRE_MINUTES = 60 * 24  # 24 Stunden

security = HTTPBearer(auto_error=False)


def hash_password(password: str) -> str:
    """Passwort hashen mit SHA-256 + Salt"""
    salt = secrets.token_hex(16)
    hash_obj = hashlib.sha256((password + salt).encode())
    return f"{salt}${hash_obj.hexdigest()}"


def verify_password(password: str, stored_hash: str) -> bool:
    """Überprüft Passwort gegen gespeicherten Hash"""
    try:
        salt, hash_value = stored_hash.split('$')
        hash_obj = hashlib.sha256((password + salt).encode())
        return hash_obj.hexdigest() == hash_value
    except (ValueError, AttributeError):
        return False


def create_token(user_id: str, mandant_id: str, email: str) -> str:
    """Erstellt JWT-Token"""
    payload = {
        "sub": user_id,
        "mandant_id": mandant_id,
        "email": email,
        "exp": datetime.utcnow() + timedelta(minutes=JWT_EXPIRE_MINUTES),
        "iat": datetime.utcnow(),
    }
    return jwt.encode(payload, JWT_SECRET, algorithm=JWT_ALGORITHM)


def decode_token(token: str) -> Dict[str, Any]:
    """Dekodiert JWT-Token"""
    try:
        return jwt.decode(token, JWT_SECRET, algorithms=[JWT_ALGORITHM])
    except jwt.ExpiredSignatureError:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Token abgelaufen")
    except jwt.InvalidTokenError:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Ungültiger Token")


async def get_current_user(credentials: HTTPAuthorizationCredentials = Depends(security)):
    """Abhängigkeit für authentifizierte Endpunkte"""
    if not credentials:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Nicht authentifiziert")
    
    payload = decode_token(credentials.credentials)
    
    db = get_db()
    user = await db.benutzer.find_one({"_id": payload["sub"]})
    if not user:
        raise HTTPException(status_code=status.HTTP_401_UNAUTHORIZED, detail="Benutzer nicht gefunden")
    
    return {
        "id": user["_id"],
        "mandant_id": user["mandant_id"],
        "benutzername": user["benutzername"],
        "email": user["email"],
        "vorname": user.get("vorname", ""),
        "nachname": user.get("nachname", ""),
        "ist_admin": user.get("ist_admin", False),
        "rolle_id": user.get("rolle_id"),
        "abteilung_ids": user.get("abteilung_ids", []),
    }


# Berechtigungs-Levels
BERECHTIGUNG_LEVELS = ["read", "write", "full", "denied"]


async def get_user_permissions(user_id: str, mandant_id: str) -> dict:
    """
    Ermittelt alle effektiven Berechtigungen eines Benutzers.
    Berücksichtigt: Benutzer-spezifisch > Rolle > Abteilungen
    """
    db = get_db()
    
    benutzer = await db.benutzer.find_one({"_id": user_id})
    if not benutzer:
        return {}
    
    # Admin hat immer vollen Zugriff
    if benutzer.get("ist_admin"):
        return {"_ist_admin": True}
    
    permissions = {}
    
    # 1. Abteilungs-Berechtigungen (niedrigste Priorität)
    for abt_id in benutzer.get("abteilung_ids", []):
        cursor = db.berechtigungen.find({
            "mandant_id": mandant_id,
            "ziel_typ": "abteilung",
            "ziel_id": abt_id
        })
        async for ber in cursor:
            modul = ber["modul"]
            level = ber["level"]
            if modul not in permissions:
                permissions[modul] = level
            elif level != "denied" and BERECHTIGUNG_LEVELS.index(level) > BERECHTIGUNG_LEVELS.index(permissions[modul]):
                permissions[modul] = level
    
    # 2. Rollen-Berechtigungen (überschreiben Abteilungen)
    if benutzer.get("rolle_id"):
        cursor = db.berechtigungen.find({
            "mandant_id": mandant_id,
            "ziel_typ": "rolle",
            "ziel_id": benutzer["rolle_id"]
        })
        async for ber in cursor:
            permissions[ber["modul"]] = ber["level"]
    
    # 3. Benutzer-spezifische Berechtigungen (höchste Priorität)
    cursor = db.berechtigungen.find({
        "mandant_id": mandant_id,
        "ziel_typ": "benutzer",
        "ziel_id": user_id
    })
    async for ber in cursor:
        permissions[ber["modul"]] = ber["level"]
    
    return permissions


def require_permission(modul: str, min_level: str = "read"):
    """
    Dependency-Factory für Berechtigungsprüfung.
    Wirft HTTPException 403 wenn Berechtigung fehlt.
    
    Verwendung:
        @router.get("/", dependencies=[Depends(require_permission("adressen", "read"))])
        async def list_adressen(...):
    
    Oder in der Funktion:
        async def create_adresse(user = Depends(require_permission("adressen", "write"))):
    """
    async def check_permission(user = Depends(get_current_user)):
        # Admin hat immer Zugriff
        if user.get("ist_admin"):
            return user
        
        permissions = await get_user_permissions(user["id"], user["mandant_id"])
        
        if permissions.get("_ist_admin"):
            return user
        
        user_level = permissions.get(modul)
        
        # Kein Zugriff wenn:
        # 1. Keine Berechtigung definiert
        # 2. Level ist "denied"
        # 3. Level ist niedriger als erforderlich
        if not user_level or user_level == "denied":
            raise HTTPException(
                status_code=status.HTTP_403_FORBIDDEN,
                detail=f"Keine Berechtigung für Modul '{modul}'"
            )
        
        # Level-Check: read < write < full
        level_order = {"read": 1, "write": 2, "full": 3}
        required_order = level_order.get(min_level, 1)
        user_order = level_order.get(user_level, 0)
        
        if user_order < required_order:
            raise HTTPException(
                status_code=status.HTTP_403_FORBIDDEN,
                detail=f"Mindestens '{min_level}' Berechtigung erforderlich für '{modul}'"
            )
        
        return user
    
    return check_permission
