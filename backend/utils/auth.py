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
    except:
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
    }
