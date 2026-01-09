"""
Auth Models - Login/Logout Schemas
"""

from pydantic import BaseModel
from typing import Optional, Dict, Any


class LoginRequest(BaseModel):
    benutzername: str
    passwort: str


class LoginResponse(BaseModel):
    success: bool
    access_token: Optional[str] = None
    user: Optional[Dict[str, Any]] = None
    error: Optional[str] = None
