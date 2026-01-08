"""
Rohstoff Portal - FastAPI Backend
Modernisierte Version des Java/Echo2 ERP-Systems
"""

from fastapi import FastAPI, HTTPException, Depends, status, UploadFile, File, Form
from fastapi.middleware.cors import CORSMiddleware
from fastapi.security import HTTPBearer, HTTPAuthorizationCredentials
from fastapi.staticfiles import StaticFiles
from pydantic import BaseModel, Field, EmailStr
from typing import Optional, List, Dict, Any
from datetime import datetime, timedelta
from enum import IntEnum
import os
import uuid
import hashlib
import secrets
import jwt
import base64
import shutil
import re
import json
from pathlib import Path
from motor.motor_asyncio import AsyncIOMotorClient
from contextlib import asynccontextmanager
from dotenv import load_dotenv

# Load environment variables
load_dotenv()

# Upload-Verzeichnis erstellen
UPLOAD_DIR = Path("/app/backend/uploads")
UPLOAD_DIR.mkdir(parents=True, exist_ok=True)
(UPLOAD_DIR / "logos").mkdir(exist_ok=True)
(UPLOAD_DIR / "profiles").mkdir(exist_ok=True)
(UPLOAD_DIR / "visitenkarten").mkdir(exist_ok=True)

# ============================================================
# KONFIGURATION
# ============================================================

MONGO_URL = os.environ.get("MONGO_URL", "mongodb://localhost:27017")
JWT_SECRET = os.environ.get("JWT_SECRET", "rohstoff_jwt_secret_key_2024_very_secure")
JWT_ALGORITHM = "HS256"
JWT_EXPIRE_MINUTES = 60 * 24  # 24 Stunden

# ============================================================
# DATENBANK
# ============================================================

client: AsyncIOMotorClient = None
db = None

@asynccontextmanager
async def lifespan(app: FastAPI):
    """Lifecycle-Management für Datenbankverbindung"""
    global client, db
    client = AsyncIOMotorClient(MONGO_URL)
    db = client.rohstoff_portal
    
    # Indizes erstellen
    await db.benutzer.create_index("benutzername", unique=True)
    await db.benutzer.create_index("email", unique=True)
    await db.adressen.create_index([("mandant_id", 1), ("kdnr", 1)])
    await db.adressen.create_index([("mandant_id", 1), ("name1", 1)])
    await db.artikel.create_index([("mandant_id", 1), ("artbez1", 1)])
    await db.kontrakte.create_index([("mandant_id", 1), ("buchungsnummer", 1)])
    
    # Demo-Mandant erstellen wenn nicht vorhanden
    existing = await db.mandanten.find_one({"kurzname": "DEMO"})
    if not existing:
        demo_mandant = {
            "_id": str(uuid.uuid4()),
            "name1": "Demo Mandant GmbH",
            "plz": "12345",
            "ort": "Musterstadt",
            "kurzname": "DEMO",
            "numkreis_debitor_inland_von": 10000,
            "numkreis_debitor_inland_bis": 19999,
            "numkreis_kreditor_inland_von": 40000,
            "numkreis_kreditor_inland_bis": 49999,
            "buchungsprefix_ekk": "EKK",
            "buchungsprefix_vkk": "VKK",
            "erstellt_am": datetime.utcnow(),
        }
        await db.mandanten.insert_one(demo_mandant)
        
        # Demo-Admin erstellen
        admin_user = {
            "_id": str(uuid.uuid4()),
            "mandant_id": demo_mandant["_id"],
            "benutzername": "admin",
            "email": "admin@demo.local",
            "passwort_hash": hash_password("Admin123!"),
            "vorname": "System",
            "nachname": "Administrator",
            "kuerzel": "ADM",
            "ist_admin": True,
            "aktiv": True,
            "erstellt_am": datetime.utcnow(),
        }
        await db.benutzer.insert_one(admin_user)
        print("Demo-Mandant und Admin erstellt (admin / Admin123!)")
    
    yield
    
    client.close()

# ============================================================
# APP SETUP
# ============================================================

app = FastAPI(
    title="Rohstoff Portal API",
    description="Enterprise ERP für Rohstoffhandel - Modernisierte Version",
    version="1.0.0",
    lifespan=lifespan,
)

# CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

security = HTTPBearer(auto_error=False)

# ============================================================
# HILFSFUNKTIONEN
# ============================================================

def hash_password(password: str) -> str:
    """Passwort hashen mit SHA-256 + Salt"""
    salt = secrets.token_hex(16)
    hash_obj = hashlib.sha256((password + salt).encode())
    return f"{salt}${hash_obj.hexdigest()}"

def verify_password(password: str, stored_hash: str) -> bool:
    """Passwort verifizieren"""
    try:
        salt, hash_value = stored_hash.split("$")
        hash_obj = hashlib.sha256((password + salt).encode())
        return hash_obj.hexdigest() == hash_value
    except:
        return False

def create_token(user_id: str, mandant_id: str, email: str) -> str:
    """JWT Token erstellen"""
    payload = {
        "user_id": user_id,
        "mandant_id": mandant_id,
        "email": email,
        "exp": datetime.utcnow() + timedelta(minutes=JWT_EXPIRE_MINUTES),
        "iat": datetime.utcnow(),
    }
    return jwt.encode(payload, JWT_SECRET, algorithm=JWT_ALGORITHM)

def decode_token(token: str) -> Dict[str, Any]:
    """JWT Token dekodieren"""
    try:
        return jwt.decode(token, JWT_SECRET, algorithms=[JWT_ALGORITHM])
    except jwt.ExpiredSignatureError:
        raise HTTPException(status_code=401, detail="Token abgelaufen")
    except jwt.InvalidTokenError:
        raise HTTPException(status_code=401, detail="Ungültiger Token")

async def get_current_user(credentials: HTTPAuthorizationCredentials = Depends(security)):
    """Aktuellen Benutzer aus Token extrahieren"""
    if not credentials:
        raise HTTPException(status_code=401, detail="Authentifizierung erforderlich")
    
    payload = decode_token(credentials.credentials)
    user = await db.benutzer.find_one({"_id": payload["user_id"]})
    
    if not user:
        raise HTTPException(status_code=401, detail="Benutzer nicht gefunden")
    if not user.get("aktiv", False):
        raise HTTPException(status_code=403, detail="Benutzer deaktiviert")
    
    return user

# ============================================================
# PYDANTIC MODELLE
# ============================================================

class AdressTyp(IntEnum):
    KUNDE = 1
    LIEFERANT = 2
    SPEDITION = 3
    INTERESSENT = 4
    SONSTIGE = 5

# Auth
class LoginRequest(BaseModel):
    benutzername: str
    passwort: str

class LoginResponse(BaseModel):
    success: bool
    access_token: Optional[str] = None
    user: Optional[Dict[str, Any]] = None
    error: Optional[str] = None

# Adresse
class AdresseCreate(BaseModel):
    # Grunddaten
    anrede: Optional[str] = Field(None, max_length=20)  # Firma, Herr, Frau
    vorname: Optional[str] = Field(None, max_length=30)
    name1: str = Field(..., max_length=40)
    name2: Optional[str] = Field(None, max_length=40)
    name3: Optional[str] = Field(None, max_length=40)
    rechtsform: Optional[str] = Field(None, max_length=30)  # GmbH, AG, etc.
    
    # Adresse
    strasse: Optional[str] = Field(None, max_length=45)
    hausnummer: Optional[str] = Field(None, max_length=10)
    plz: Optional[str] = Field(None, max_length=10)
    ort: Optional[str] = Field(None, max_length=30)
    ortzusatz: Optional[str] = Field(None, max_length=30)
    land: Optional[str] = Field(None, max_length=30)
    sprache: Optional[str] = Field(None, max_length=20)
    
    # Postfach
    plz_postfach: Optional[str] = Field(None, max_length=10)
    postfach: Optional[str] = Field(None, max_length=20)
    postfach_aktiv: bool = False
    
    # Kontakt
    telefon: Optional[str] = Field(None, max_length=30)
    telefax: Optional[str] = Field(None, max_length=30)
    email: Optional[str] = Field(None, max_length=100)
    webseite: Optional[str] = Field(None, max_length=100)
    
    # Geolocation
    latitude: Optional[float] = None
    longitude: Optional[float] = None
    wartezeit_min: Optional[int] = None
    
    # Status & Typ
    adresstyp: Optional[int] = Field(1, ge=1, le=5)
    aktiv: bool = True
    wareneingang: bool = True
    warenausgang: bool = True
    barkunde: bool = False
    scheckdruck: bool = False
    ist_firma: bool = True  # JA=Firma, NEIN=Privat
    
    # Betreuer/Sachbearbeiter
    betreuer: Optional[str] = Field(None, max_length=20)
    betreuer2: Optional[str] = Field(None, max_length=20)
    
    # Nummern/Codes
    kreditor_nr: Optional[str] = Field(None, max_length=30)
    debitor_nr: Optional[str] = Field(None, max_length=30)
    lief_nr: Optional[str] = Field(None, max_length=60)
    abn_nr: Optional[str] = Field(None, max_length=60)
    betriebs_nr_saa: Optional[str] = Field(None, max_length=30)
    sondernummer: Optional[str] = Field(None, max_length=30)
    
    # Steuer
    umsatzsteuer_lkz: Optional[str] = Field(None, max_length=3)
    umsatzsteuer_id: Optional[str] = Field(None, max_length=20)
    steuernummer: Optional[str] = Field(None, max_length=20)
    
    # Weitere UST-IDs (dynamisches Array von {land, lkz, ustid})
    weitere_ustids: Optional[List[Dict[str, str]]] = None
    
    # Handelsregister
    handelsregister: Optional[str] = Field(None, max_length=50)
    
    # Firmenlogo (als base64 oder URL)
    firmenlogo: Optional[str] = None
    
    # Ansprechpartner (Array von Kontaktpersonen)
    ansprechpartner: Optional[List[Dict[str, Any]]] = None
    
    # Zahlungs-/Lieferbedingungen
    waehrung: Optional[str] = Field("EUR", max_length=3)
    zahlungsbedingung_ek: Optional[str] = Field(None, max_length=100)
    zahlungsbedingung_vk: Optional[str] = Field(None, max_length=100)
    lieferbedingung_ek: Optional[str] = Field(None, max_length=50)  # Incoterms
    lieferbedingung_vk: Optional[str] = Field(None, max_length=50)
    
    # Sperren
    rechnungen_sperren: bool = False
    gutschriften_sperren: bool = False
    wareneingang_sperren: bool = False
    warenausgang_sperren: bool = False
    wird_nicht_gemahnt: bool = False
    
    # Ausweis (für Privatkunden)
    ausweis_nummer: Optional[str] = Field(None, max_length=30)
    ausweis_ablauf: Optional[str] = Field(None, max_length=10)
    geburtsdatum: Optional[str] = Field(None, max_length=10)
    
    # Sonderschalter für UST-Ausnahmen (aus Java: __FS_Adress_Check)
    firma_ohne_ustid: bool = False  # Für Firmen im Inland ohne UST-ID
    privat_mit_ustid: bool = False  # Für Privatpersonen im Inland mit UST-ID
    
    # Bemerkungen
    bemerkungen: Optional[str] = Field(None, max_length=700)
    bemerkung_fahrplan: Optional[str] = Field(None, max_length=300)
    lieferinfo_tpa: Optional[str] = Field(None, max_length=300)

class AdresseUpdate(BaseModel):
    anrede: Optional[str] = Field(None, max_length=20)
    vorname: Optional[str] = Field(None, max_length=30)
    name1: Optional[str] = Field(None, max_length=40)
    name2: Optional[str] = Field(None, max_length=40)
    name3: Optional[str] = Field(None, max_length=40)
    rechtsform: Optional[str] = Field(None, max_length=30)
    strasse: Optional[str] = Field(None, max_length=45)
    hausnummer: Optional[str] = Field(None, max_length=10)
    plz: Optional[str] = Field(None, max_length=10)
    ort: Optional[str] = Field(None, max_length=30)
    ortzusatz: Optional[str] = Field(None, max_length=30)
    land: Optional[str] = Field(None, max_length=30)
    sprache: Optional[str] = Field(None, max_length=20)
    plz_postfach: Optional[str] = Field(None, max_length=10)
    postfach: Optional[str] = Field(None, max_length=20)
    postfach_aktiv: Optional[bool] = None
    telefon: Optional[str] = Field(None, max_length=30)
    telefax: Optional[str] = Field(None, max_length=30)
    email: Optional[str] = Field(None, max_length=100)
    webseite: Optional[str] = Field(None, max_length=100)
    latitude: Optional[float] = None
    longitude: Optional[float] = None
    wartezeit_min: Optional[int] = None
    adresstyp: Optional[int] = Field(None, ge=1, le=5)
    aktiv: Optional[bool] = None
    wareneingang: Optional[bool] = None
    warenausgang: Optional[bool] = None
    barkunde: Optional[bool] = None
    scheckdruck: Optional[bool] = None
    ist_firma: Optional[bool] = None
    betreuer: Optional[str] = Field(None, max_length=20)
    betreuer2: Optional[str] = Field(None, max_length=20)
    kreditor_nr: Optional[str] = Field(None, max_length=30)
    debitor_nr: Optional[str] = Field(None, max_length=30)
    lief_nr: Optional[str] = Field(None, max_length=60)
    abn_nr: Optional[str] = Field(None, max_length=60)
    betriebs_nr_saa: Optional[str] = Field(None, max_length=30)
    sondernummer: Optional[str] = Field(None, max_length=30)
    umsatzsteuer_lkz: Optional[str] = Field(None, max_length=3)
    umsatzsteuer_id: Optional[str] = Field(None, max_length=20)
    steuernummer: Optional[str] = Field(None, max_length=20)
    weitere_ustids: Optional[List[Dict[str, str]]] = None
    handelsregister: Optional[str] = Field(None, max_length=50)
    firmenlogo: Optional[str] = None
    ansprechpartner: Optional[List[Dict[str, Any]]] = None
    waehrung: Optional[str] = Field(None, max_length=3)
    zahlungsbedingung_ek: Optional[str] = Field(None, max_length=100)
    zahlungsbedingung_vk: Optional[str] = Field(None, max_length=100)
    lieferbedingung_ek: Optional[str] = Field(None, max_length=50)
    lieferbedingung_vk: Optional[str] = Field(None, max_length=50)
    rechnungen_sperren: Optional[bool] = None
    gutschriften_sperren: Optional[bool] = None
    wareneingang_sperren: Optional[bool] = None
    warenausgang_sperren: Optional[bool] = None
    wird_nicht_gemahnt: Optional[bool] = None
    ausweis_nummer: Optional[str] = Field(None, max_length=30)
    ausweis_ablauf: Optional[str] = Field(None, max_length=10)
    geburtsdatum: Optional[str] = Field(None, max_length=10)
    # Sonderschalter
    firma_ohne_ustid: Optional[bool] = None
    privat_mit_ustid: Optional[bool] = None
    bemerkungen: Optional[str] = Field(None, max_length=700)
    bemerkung_fahrplan: Optional[str] = Field(None, max_length=300)
    lieferinfo_tpa: Optional[str] = Field(None, max_length=300)

# Artikel
class ArtikelCreate(BaseModel):
    # Grunddaten
    anr1: Optional[str] = Field(None, max_length=10)  # Artikelnummer 1
    artbez1: str = Field(..., max_length=80)  # Artikelbezeichnung 1
    artbez2: Optional[str] = Field(None, max_length=1000)  # Artikelbezeichnung 2
    
    # Einheiten
    einheit: str = Field("kg", max_length=10)
    einheit_preis: str = Field("t", max_length=10)
    mengendivisor: int = Field(1000, ge=1)
    genauigkeit_mengen: int = Field(3, ge=0, le=6)  # Nachkommastellen
    
    # Klassifikation
    artikelgruppe: Optional[str] = Field(None, max_length=100)
    artikelgruppe_fibu: Optional[str] = Field(None, max_length=100)
    
    # Status-Flags
    aktiv: bool = True
    gefahrgut: bool = False
    ist_leergut: bool = False
    elektro_elektronik: bool = False
    ist_produkt: bool = False
    dienstleistung: bool = False
    end_of_waste: bool = False
    end_of_waste_lager: bool = False
    
    # AVV-Codes
    avv_code_eingang: Optional[str] = Field(None, max_length=50)  # AVV-Code Bar-Anlieferer
    avv_code_ausgang: Optional[str] = Field(None, max_length=50)  # AVV-Code Ausgang mvg
    eakcode: Optional[str] = Field(None, max_length=20)
    
    # Zoll
    zolltarifnr: Optional[str] = Field(None, max_length=50)
    zolltarifnotiz: Optional[str] = Field(None, max_length=500)
    
    # Basel-Code
    basel_code: Optional[str] = Field(None, max_length=80)
    basel_notiz: Optional[str] = Field(None, max_length=500)
    
    # OECD
    oecd_code: Optional[str] = Field(None, max_length=50)
    oecd_notiz: Optional[str] = Field(None, max_length=500)
    
    # Anhang 7
    anhang7_3a_code: Optional[str] = Field(None, max_length=20)
    anhang7_3a_text: Optional[str] = Field(None, max_length=1000)
    anhang7_3b_code: Optional[str] = Field(None, max_length=20)
    anhang7_3b_text: Optional[str] = Field(None, max_length=1000)
    
    # Österreichische AVV
    oesterreichische_avv: Optional[str] = Field(None, max_length=50)
    
    # Bemerkungen
    bemerkung_intern: Optional[str] = Field(None, max_length=1000)

# Kontrakt
class KontraktCreate(BaseModel):
    adresse_id: str
    ist_einkauf: bool = True
    gueltig_von: Optional[datetime] = None
    gueltig_bis: Optional[datetime] = None
    bemerkungen_intern: Optional[str] = Field(None, max_length=500)

class KontraktPosCreate(BaseModel):
    artikel_id: Optional[str] = None
    artbez1: Optional[str] = Field(None, max_length=80)
    menge: Optional[float] = None
    einzelpreis: Optional[float] = None
    steuersatz: Optional[float] = Field(19.0)
    einheit: Optional[str] = Field("kg", max_length=10)

# ============================================================
# HEALTH CHECK
# ============================================================

@app.get("/api/health")
async def health_check():
    return {
        "status": "ok",
        "timestamp": datetime.utcnow().isoformat(),
        "version": "1.0.0",
    }

# ============================================================
# AUTH ENDPOINTS
# ============================================================

@app.post("/api/auth/login", response_model=LoginResponse)
async def login(request: LoginRequest):
    """Benutzer anmelden"""
    user = await db.benutzer.find_one({
        "$or": [
            {"benutzername": request.benutzername},
            {"email": request.benutzername}
        ]
    })
    
    if not user:
        return LoginResponse(success=False, error="Ungültige Anmeldedaten")
    
    if not verify_password(request.passwort, user["passwort_hash"]):
        return LoginResponse(success=False, error="Ungültige Anmeldedaten")
    
    if not user.get("aktiv", False):
        return LoginResponse(success=False, error="Benutzer deaktiviert")
    
    token = create_token(user["_id"], user["mandant_id"], user["email"])
    
    # Letzten Login aktualisieren
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
            "email": user["email"],
            "vorname": user.get("vorname"),
            "nachname": user.get("nachname"),
            "kuerzel": user.get("kuerzel"),
            "ist_admin": user.get("ist_admin", False),
        }
    )

@app.get("/api/auth/me")
async def get_me(user = Depends(get_current_user)):
    """Aktuellen Benutzer abrufen"""
    return {
        "success": True,
        "user": {
            "id": user["_id"],
            "mandant_id": user["mandant_id"],
            "benutzername": user["benutzername"],
            "email": user["email"],
            "vorname": user.get("vorname"),
            "nachname": user.get("nachname"),
            "kuerzel": user.get("kuerzel"),
            "ist_admin": user.get("ist_admin", False),
        }
    }

@app.post("/api/auth/logout")
async def logout(user = Depends(get_current_user)):
    """Benutzer abmelden"""
    return {"success": True, "message": "Erfolgreich abgemeldet"}

# ============================================================
# GESCHÄFTSLOGIK - ADRESS-VALIDIERUNG (aus Java: __FS_Adress_Check.java)
# ============================================================

# EU-Länder mit UST-Präfixen (aus Java-Länderstamm)
EU_LAENDER = {
    "Deutschland": {"ust_praefix": "DE", "ist_eu": True, "ist_homeland": True},
    "Österreich": {"ust_praefix": "AT", "ist_eu": True, "ist_homeland": False},
    "Niederlande": {"ust_praefix": "NL", "ist_eu": True, "ist_homeland": False},
    "Belgien": {"ust_praefix": "BE", "ist_eu": True, "ist_homeland": False},
    "Frankreich": {"ust_praefix": "FR", "ist_eu": True, "ist_homeland": False},
    "Italien": {"ust_praefix": "IT", "ist_eu": True, "ist_homeland": False},
    "Spanien": {"ust_praefix": "ES", "ist_eu": True, "ist_homeland": False},
    "Polen": {"ust_praefix": "PL", "ist_eu": True, "ist_homeland": False},
    "Tschechien": {"ust_praefix": "CZ", "ist_eu": True, "ist_homeland": False},
    "Dänemark": {"ust_praefix": "DK", "ist_eu": True, "ist_homeland": False},
    "Schweden": {"ust_praefix": "SE", "ist_eu": True, "ist_homeland": False},
    "Finnland": {"ust_praefix": "FI", "ist_eu": True, "ist_homeland": False},
    "Griechenland": {"ust_praefix": "EL", "ist_eu": True, "ist_homeland": False},
    "Portugal": {"ust_praefix": "PT", "ist_eu": True, "ist_homeland": False},
    "Irland": {"ust_praefix": "IE", "ist_eu": True, "ist_homeland": False},
    "Luxemburg": {"ust_praefix": "LU", "ist_eu": True, "ist_homeland": False},
    "Ungarn": {"ust_praefix": "HU", "ist_eu": True, "ist_homeland": False},
    "Rumänien": {"ust_praefix": "RO", "ist_eu": True, "ist_homeland": False},
    "Bulgarien": {"ust_praefix": "BG", "ist_eu": True, "ist_homeland": False},
    "Kroatien": {"ust_praefix": "HR", "ist_eu": True, "ist_homeland": False},
    "Slowakei": {"ust_praefix": "SK", "ist_eu": True, "ist_homeland": False},
    "Slowenien": {"ust_praefix": "SI", "ist_eu": True, "ist_homeland": False},
    "Estland": {"ust_praefix": "EE", "ist_eu": True, "ist_homeland": False},
    "Lettland": {"ust_praefix": "LV", "ist_eu": True, "ist_homeland": False},
    "Litauen": {"ust_praefix": "LT", "ist_eu": True, "ist_homeland": False},
    "Malta": {"ust_praefix": "MT", "ist_eu": True, "ist_homeland": False},
    "Zypern": {"ust_praefix": "CY", "ist_eu": True, "ist_homeland": False},
    # Nicht-EU mit UST
    "Schweiz": {"ust_praefix": "CH", "ist_eu": False, "ist_homeland": False},
    "Großbritannien": {"ust_praefix": "GB", "ist_eu": False, "ist_homeland": False},
    "Norwegen": {"ust_praefix": "NO", "ist_eu": False, "ist_homeland": False},
}

class AdresseValidierungsFehler(BaseModel):
    """Validierungsfehler mit Schweregrad (aus Java: Validation_Error)"""
    meldung: str
    schweregrad: str  # "warnung", "fehler"
    betroffene_felder: List[str]

class AdresseValidierungsErgebnis(BaseModel):
    """Ergebnis der Adress-Validierung"""
    ist_gueltig: bool
    fehler: List[AdresseValidierungsFehler]
    warnungen: List[AdresseValidierungsFehler]
    steuer_status: Optional[str] = None  # PRIVAT_INLAND, FIRMA_EU, etc.

def validate_adresse_geschaeftslogik(
    ist_firma: bool,
    ist_privat: bool,
    land: Optional[str],
    umsatzsteuer_lkz: Optional[str],
    umsatzsteuer_id: Optional[str],
    steuernummer: Optional[str],
    ausweis_nummer: Optional[str],
    ausweis_ablauf: Optional[str],
    firma_ohne_ustid: bool = False,
    privat_mit_ustid: bool = False,
) -> AdresseValidierungsErgebnis:
    """
    Geschäftslogik-Validierung für Adressen 
    Portiert aus Java: rohstoff.Echo2BusinessLogic.FIRMENSTAMM.__FS_Adress_Check
    
    Validiert:
    - PRIVAT/FIRMA Einstufung
    - UST-ID Anforderungen basierend auf Land und Typ
    - Ausweisangaben für Privatpersonen
    - Sonderschalter-Logik
    """
    fehler = []
    warnungen = []
    
    # Land-Info ermitteln
    land_info = EU_LAENDER.get(land, {"ust_praefix": None, "ist_eu": False, "ist_homeland": False})
    ist_homeland = land_info.get("ist_homeland", False)
    ist_eu = land_info.get("ist_eu", False)
    ust_praefix = land_info.get("ust_praefix")
    
    # UST-ID Status ermitteln
    hat_ustid = bool(umsatzsteuer_lkz) or bool(umsatzsteuer_id)
    hat_komplette_ustid = bool(umsatzsteuer_lkz) and bool(umsatzsteuer_id)
    hat_steuernummer = bool(steuernummer)
    hat_ausweis = bool(ausweis_nummer)
    hat_ausweis_oder_steuernummer = hat_ausweis or hat_steuernummer
    
    # Ausweis-Gültigkeit prüfen (vereinfacht)
    ausweis_gueltig = False
    if ausweis_ablauf:
        try:
            from datetime import datetime
            ablauf = datetime.strptime(ausweis_ablauf, "%d.%m.%Y")
            ausweis_gueltig = ablauf >= datetime.now()
        except:
            pass
    
    # ========== GRUNDVALIDIERUNG ==========
    
    # 1. PRIVAT/FIRMA muss exklusiv sein
    if (not ist_firma and not ist_privat) or (ist_firma and ist_privat):
        fehler.append(AdresseValidierungsFehler(
            meldung="Eine Adresse muss entweder als PRIVAT oder als FIRMA eingestuft werden!",
            schweregrad="fehler",
            betroffene_felder=["ist_firma"]
        ))
    
    # 2. Land muss gesetzt sein
    if not land:
        warnungen.append(AdresseValidierungsFehler(
            meldung="Das Land ist noch nicht gesetzt. Bitte zuerst definieren.",
            schweregrad="warnung",
            betroffene_felder=["land"]
        ))
        return AdresseValidierungsErgebnis(
            ist_gueltig=len(fehler) == 0,
            fehler=fehler,
            warnungen=warnungen,
            steuer_status=None
        )
    
    # ========== UST-ID VALIDIERUNG ==========
    
    # 3. Wenn EU-Land, muss UST-Präfix definiert sein
    if ist_eu and not ust_praefix:
        warnungen.append(AdresseValidierungsFehler(
            meldung=f"Das EU-Land '{land}' besitzt kein UST-Länderkürzel, bitte korrigieren!",
            schweregrad="warnung",
            betroffene_felder=["land"]
        ))
    
    # 4. UST-ID nur teilweise ausgefüllt
    if hat_ustid and not hat_komplette_ustid:
        fehler.append(AdresseValidierungsFehler(
            meldung="Die Basis-UST-ID der Adresse ist nur teilweise ausgefüllt. Bitte komplettieren oder komplett leeren!",
            schweregrad="fehler",
            betroffene_felder=["umsatzsteuer_lkz", "umsatzsteuer_id"]
        ))
    
    # 5. UST-LKZ muss mit Land übereinstimmen
    if hat_ustid and ust_praefix and umsatzsteuer_lkz != ust_praefix:
        fehler.append(AdresseValidierungsFehler(
            meldung=f"Das Länderkürzel der Basis-UST-ID ({umsatzsteuer_lkz}) stimmt nicht mit der Angabe im Land ({ust_praefix}) überein!",
            schweregrad="fehler",
            betroffene_felder=["umsatzsteuer_lkz", "land"]
        ))
    
    # ========== SONDERSCHALTER VALIDIERUNG ==========
    
    # 6. Sonderschalter nur im Inland sinnvoll
    if not ist_homeland and (firma_ohne_ustid or privat_mit_ustid):
        warnungen.append(AdresseValidierungsFehler(
            meldung="Die Ausnahmeschalter 'Firma ohne UST-ID' und 'Privat mit UST-ID' sind nur bei Adressen in Deutschland sinnvoll!",
            schweregrad="warnung",
            betroffene_felder=["firma_ohne_ustid", "privat_mit_ustid"]
        ))
    
    # 7. Beide Sonderschalter gleichzeitig nicht erlaubt
    if ist_homeland and firma_ohne_ustid and privat_mit_ustid:
        fehler.append(AdresseValidierungsFehler(
            meldung="Die Ausnahmeschalter 'Firma ohne UST-ID' und 'Privat mit UST-ID' können nicht gleichzeitig aktiv sein!",
            schweregrad="fehler",
            betroffene_felder=["firma_ohne_ustid", "privat_mit_ustid"]
        ))
    
    # ========== PRIVAT-SPEZIFISCHE VALIDIERUNG ==========
    
    if ist_privat:
        # 8. Privat + Inland + UST-ID erfordert Sonderschalter
        if ist_homeland and hat_ustid and not privat_mit_ustid:
            fehler.append(AdresseValidierungsFehler(
                meldung="Die Einstufung einer Adresse mit Basis-UST-ID als PRIVAT ist nur mit dem Sonderschalter 'Privat mit UST-ID' möglich!",
                schweregrad="fehler",
                betroffene_felder=["privat_mit_ustid"]
            ))
        
        # Spezialfälle (nicht Privat+Homeland+UST+Sonderschalter)
        if not (hat_ustid and ist_homeland and privat_mit_ustid):
            # 9. Privat + Ausland: Ausweis erforderlich
            if not ist_homeland and not hat_ausweis:
                fehler.append(AdresseValidierungsFehler(
                    meldung="Bei als PRIVAT eingestuften Adressen aus dem Ausland MUSS die Ausweisnummer vorliegen!",
                    schweregrad="fehler",
                    betroffene_felder=["ausweis_nummer"]
                ))
            # 10. Privat + Inland: Ausweis oder Steuernummer erforderlich
            elif ist_homeland and not hat_ausweis_oder_steuernummer:
                fehler.append(AdresseValidierungsFehler(
                    meldung="Bei als PRIVAT eingestuften Adressen aus dem Inland MUSS die Ausweisnummer oder die Steuernummer vorliegen!",
                    schweregrad="fehler",
                    betroffene_felder=["ausweis_nummer", "steuernummer"]
                ))
        
        # 11. Privat + Ausland + UST-ID nicht erlaubt
        if not ist_homeland and hat_ustid:
            fehler.append(AdresseValidierungsFehler(
                meldung="Bei als PRIVAT eingestuften Adressen im Ausland darf keine UST-ID erfasst sein!",
                schweregrad="fehler",
                betroffene_felder=["umsatzsteuer_id"]
            ))
        
        # 12. Privat + Inland + Firma-ohne-UST-ID Schalter nicht erlaubt
        if ist_homeland and firma_ohne_ustid:
            fehler.append(AdresseValidierungsFehler(
                meldung="Bei als PRIVAT eingestuften Adressen in Deutschland darf der Sonderschalter 'Firma ohne UST-ID' nicht gesetzt sein!",
                schweregrad="fehler",
                betroffene_felder=["firma_ohne_ustid"]
            ))
    
    # ========== FIRMA-SPEZIFISCHE VALIDIERUNG ==========
    
    if ist_firma:
        # 13. Firma + Inland + keine UST-ID: Sonderschalter + Steuernummer erforderlich
        if ist_homeland and not hat_ustid and (not firma_ohne_ustid or not hat_steuernummer):
            fehler.append(AdresseValidierungsFehler(
                meldung="Die Einstufung einer Adresse ohne UST-ID als FIRMA ist nur mit dem Sonderschalter 'Firma ohne UST-ID', sowie der Angabe der Steuernummer möglich!",
                schweregrad="fehler",
                betroffene_felder=["firma_ohne_ustid", "steuernummer"]
            ))
        
        # 14. Firma + EU-Ausland: UST-ID erforderlich
        if not ist_homeland and ist_eu and not hat_ustid:
            fehler.append(AdresseValidierungsFehler(
                meldung="Eine Adresse mit Einstufung als FIRMA im EU-Ausland MUSS eine korrekte Basis-UST-ID haben!",
                schweregrad="fehler",
                betroffene_felder=["umsatzsteuer_lkz", "umsatzsteuer_id"]
            ))
        
        # 15. Firma + Nicht-EU + UST-ID: Länderkürzel muss stimmen
        if not ist_homeland and not ist_eu and hat_ustid:
            if not (umsatzsteuer_lkz == ust_praefix and ust_praefix):
                warnungen.append(AdresseValidierungsFehler(
                    meldung="Bei einer Adresse mit Einstufung als FIRMA im NICHT-EU-Ausland mit einer Basis-UST-ID MUSS das UST-Länderkürzel mit dem Eintrag im Länderstamm übereinstimmen!",
                    schweregrad="warnung",
                    betroffene_felder=["umsatzsteuer_lkz", "land"]
                ))
        
        # 16. Firma + Inland + UST-ID + Firma-ohne-UST-ID Schalter nicht erlaubt
        if ist_homeland and hat_ustid and firma_ohne_ustid:
            fehler.append(AdresseValidierungsFehler(
                meldung="Bei einer Adresse mit Einstufung als FIRMA, die eine UST-ID hat, darf der Schalter 'Firma ohne UST-ID' nicht gesetzt sein!",
                schweregrad="fehler",
                betroffene_felder=["firma_ohne_ustid"]
            ))
        
        # 17. Firma + Inland + Privat-mit-UST-ID Schalter nicht erlaubt
        if ist_homeland and privat_mit_ustid:
            fehler.append(AdresseValidierungsFehler(
                meldung="Bei als FIRMA eingestuften Adressen in Deutschland darf der Sonderschalter 'Privat mit UST-ID' nicht gesetzt sein!",
                schweregrad="fehler",
                betroffene_felder=["privat_mit_ustid"]
            ))
    
    # Steuer-Status ermitteln
    steuer_status = None
    if ist_privat:
        if ist_homeland:
            steuer_status = "PRIVAT_INLAND"
        elif ist_eu:
            steuer_status = "PRIVAT_EU"
        else:
            steuer_status = "PRIVAT_DRITTLAND"
    elif ist_firma:
        if ist_homeland:
            steuer_status = "FIRMA_INLAND"
        elif ist_eu:
            steuer_status = "FIRMA_EU"
        else:
            steuer_status = "FIRMA_DRITTLAND"
    
    return AdresseValidierungsErgebnis(
        ist_gueltig=len(fehler) == 0,
        fehler=fehler,
        warnungen=warnungen,
        steuer_status=steuer_status
    )

# ============================================================
# ADRESSEN ENDPOINTS
# ============================================================

@app.post("/api/adressen/validieren")
async def validiere_adresse(data: AdresseCreate, user = Depends(get_current_user)):
    """
    Validiert eine Adresse nach Geschäftslogik (aus Java: __FS_Adress_Check)
    Gibt Fehler und Warnungen zurück ohne zu speichern
    """
    result = validate_adresse_geschaeftslogik(
        ist_firma=data.ist_firma,
        ist_privat=not data.ist_firma,
        land=data.land,
        umsatzsteuer_lkz=data.umsatzsteuer_lkz,
        umsatzsteuer_id=data.umsatzsteuer_id,
        steuernummer=data.steuernummer,
        ausweis_nummer=data.ausweis_nummer,
        ausweis_ablauf=data.ausweis_ablauf,
        firma_ohne_ustid=data.firma_ohne_ustid,
        privat_mit_ustid=data.privat_mit_ustid,
    )
    
    return {
        "success": True,
        "validierung": result.model_dump()
    }

@app.get("/api/laender")
async def get_laender(user = Depends(get_current_user)):
    """Gibt die Liste der konfigurierten Länder mit UST-Präfixen zurück"""
    laender_liste = []
    for name, info in EU_LAENDER.items():
        laender_liste.append({
            "name": name,
            "ust_praefix": info["ust_praefix"],
            "ist_eu": info["ist_eu"],
            "ist_homeland": info["ist_homeland"]
        })
    
    return {
        "success": True,
        "data": sorted(laender_liste, key=lambda x: x["name"])
    }

async def generate_kdnr(mandant_id: str, adresstyp: int) -> str:
    """Automatische KDNR-Generierung (Business-Logic aus Java)"""
    mandant = await db.mandanten.find_one({"_id": mandant_id})
    if not mandant:
        raise HTTPException(status_code=400, detail="Mandant nicht gefunden")
    
    # Debitor (Kunde/Interessent) vs Kreditor (Lieferant)
    ist_debitor = adresstyp in [1, 4]
    
    if ist_debitor:
        von_nr = mandant.get("numkreis_debitor_inland_von", 10000)
        bis_nr = mandant.get("numkreis_debitor_inland_bis", 19999)
    else:
        von_nr = mandant.get("numkreis_kreditor_inland_von", 40000)
        bis_nr = mandant.get("numkreis_kreditor_inland_bis", 49999)
    
    # Höchste KDNR finden
    letzte = await db.adressen.find_one(
        {
            "mandant_id": mandant_id,
            "kdnr": {"$gte": str(von_nr), "$lte": str(bis_nr)}
        },
        sort=[("kdnr", -1)]
    )
    
    if letzte and letzte.get("kdnr"):
        try:
            neue_nr = int(letzte["kdnr"]) + 1
        except:
            neue_nr = von_nr
    else:
        neue_nr = von_nr
    
    if neue_nr > bis_nr:
        raise HTTPException(status_code=400, detail="Nummernkreis erschöpft")
    
    return str(neue_nr)

@app.get("/api/adressen")
async def get_adressen(
    suche: Optional[str] = None,
    adresstyp: Optional[int] = None,
    aktiv: Optional[bool] = None,
    page: int = 1,
    limit: int = 20,
    user = Depends(get_current_user)
):
    """Adressen suchen mit Pagination"""
    query = {"mandant_id": user["mandant_id"]}
    
    if suche:
        query["$or"] = [
            {"name1": {"$regex": suche, "$options": "i"}},
            {"name2": {"$regex": suche, "$options": "i"}},
            {"kdnr": {"$regex": suche, "$options": "i"}},
            {"ort": {"$regex": suche, "$options": "i"}},
        ]
    
    if adresstyp is not None:
        query["adresstyp"] = adresstyp
    if aktiv is not None:
        query["aktiv"] = aktiv
    
    total = await db.adressen.count_documents(query)
    
    cursor = db.adressen.find(query).sort("name1", 1).skip((page - 1) * limit).limit(limit)
    adressen = await cursor.to_list(length=limit)
    
    # IDs normalisieren
    for a in adressen:
        a["id"] = a.pop("_id")
    
    return {
        "success": True,
        "data": adressen,
        "pagination": {
            "page": page,
            "limit": limit,
            "total": total,
            "total_pages": (total + limit - 1) // limit,
        }
    }

@app.get("/api/adressen/{adresse_id}")
async def get_adresse(adresse_id: str, user = Depends(get_current_user)):
    """Adresse nach ID"""
    adresse = await db.adressen.find_one({
        "_id": adresse_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not adresse:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    adresse["id"] = adresse.pop("_id")
    return {"success": True, "data": adresse}

@app.post("/api/adressen")
async def create_adresse(
    data: AdresseCreate, 
    skip_validation: bool = False,
    user = Depends(get_current_user)
):
    """
    Neue Adresse erstellen
    
    Die Geschäftslogik-Validierung wird durchgeführt:
    - Bei Fehlern wird die Erstellung verweigert
    - Bei Warnungen wird die Adresse erstellt, aber Warnungen zurückgegeben
    - Mit skip_validation=True kann die Validierung übersprungen werden
    """
    warnungen = []
    
    if not skip_validation:
        # Geschäftslogik-Validierung durchführen
        validierung = validate_adresse_geschaeftslogik(
            ist_firma=data.ist_firma,
            ist_privat=not data.ist_firma,
            land=data.land,
            umsatzsteuer_lkz=data.umsatzsteuer_lkz,
            umsatzsteuer_id=data.umsatzsteuer_id,
            steuernummer=data.steuernummer,
            ausweis_nummer=data.ausweis_nummer,
            ausweis_ablauf=data.ausweis_ablauf,
            firma_ohne_ustid=data.firma_ohne_ustid,
            privat_mit_ustid=data.privat_mit_ustid,
        )
        
        # Bei kritischen Fehlern abbrechen
        if not validierung.ist_gueltig:
            fehler_texte = [f.meldung for f in validierung.fehler]
            raise HTTPException(
                status_code=400, 
                detail={
                    "message": "Validierungsfehler bei der Adresse",
                    "fehler": fehler_texte,
                    "validierung": validierung.model_dump()
                }
            )
        
        # Warnungen merken
        warnungen = [w.model_dump() for w in validierung.warnungen]
    
    kdnr = await generate_kdnr(user["mandant_id"], data.adresstyp or 1)
    
    adresse = {
        "_id": str(uuid.uuid4()),
        "mandant_id": user["mandant_id"],
        "kdnr": kdnr,
        **data.model_dump(),
        "erstellt_von": user.get("kuerzel"),
        "erstellt_am": datetime.utcnow(),
        "letzte_aenderung": datetime.utcnow(),
    }
    
    await db.adressen.insert_one(adresse)
    adresse["id"] = adresse.pop("_id")
    
    return {
        "success": True, 
        "data": adresse,
        "warnungen": warnungen if warnungen else None
    }

@app.put("/api/adressen/{adresse_id}")
async def update_adresse(
    adresse_id: str, 
    data: AdresseUpdate, 
    skip_validation: bool = False,
    user = Depends(get_current_user)
):
    """
    Adresse aktualisieren
    
    Die Geschäftslogik-Validierung wird durchgeführt (wie bei create)
    """
    existing = await db.adressen.find_one({
        "_id": adresse_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not existing:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    
    warnungen = []
    if not skip_validation:
        # Merge existing data with updates for validation
        merged = {**existing, **update_data}
        
        validierung = validate_adresse_geschaeftslogik(
            ist_firma=merged.get("ist_firma", True),
            ist_privat=not merged.get("ist_firma", True),
            land=merged.get("land"),
            umsatzsteuer_lkz=merged.get("umsatzsteuer_lkz"),
            umsatzsteuer_id=merged.get("umsatzsteuer_id"),
            steuernummer=merged.get("steuernummer"),
            ausweis_nummer=merged.get("ausweis_nummer"),
            ausweis_ablauf=merged.get("ausweis_ablauf"),
            firma_ohne_ustid=merged.get("firma_ohne_ustid", False),
            privat_mit_ustid=merged.get("privat_mit_ustid", False),
        )
        
        if not validierung.ist_gueltig:
            fehler_texte = [f.meldung for f in validierung.fehler]
            raise HTTPException(
                status_code=400, 
                detail={
                    "message": "Validierungsfehler bei der Adresse",
                    "fehler": fehler_texte,
                    "validierung": validierung.model_dump()
                }
            )
        
        warnungen = [w.model_dump() for w in validierung.warnungen]
    
    update_data["geaendert_von"] = user.get("kuerzel")
    update_data["letzte_aenderung"] = datetime.utcnow()
    
    await db.adressen.update_one({"_id": adresse_id}, {"$set": update_data})
    
    adresse = await db.adressen.find_one({"_id": adresse_id})
    adresse["id"] = adresse.pop("_id")
    
    return {
        "success": True, 
        "data": adresse,
        "warnungen": warnungen if warnungen else None
    }

@app.delete("/api/adressen/{adresse_id}")
async def delete_adresse(adresse_id: str, user = Depends(get_current_user)):
    """Adresse löschen (Soft-Delete)"""
    existing = await db.adressen.find_one({
        "_id": adresse_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not existing:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    # Soft-Delete
    await db.adressen.update_one(
        {"_id": adresse_id},
        {"$set": {"aktiv": False, "letzte_aenderung": datetime.utcnow()}}
    )
    
    return {"success": True, "message": "Adresse deaktiviert"}

# ============================================================
# ARTIKEL ENDPOINTS
# ============================================================

@app.get("/api/artikel")
async def get_artikel(
    suche: Optional[str] = None,
    aktiv: Optional[bool] = None,
    page: int = 1,
    limit: int = 20,
    user = Depends(get_current_user)
):
    """Artikel suchen"""
    query = {"mandant_id": user["mandant_id"]}
    
    if suche:
        query["$or"] = [
            {"artbez1": {"$regex": suche, "$options": "i"}},
            {"artbez2": {"$regex": suche, "$options": "i"}},
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

@app.post("/api/artikel")
async def create_artikel(data: ArtikelCreate, user = Depends(get_current_user)):
    """Neuen Artikel erstellen"""
    artikel = {
        "_id": str(uuid.uuid4()),
        "mandant_id": user["mandant_id"],
        **data.model_dump(),
        "erstellt_von": user.get("kuerzel"),
        "erstellt_am": datetime.utcnow(),
        "letzte_aenderung": datetime.utcnow(),
    }
    
    await db.artikel.insert_one(artikel)
    artikel["id"] = artikel.pop("_id")
    
    return {"success": True, "data": artikel}

# ============================================================
# KONTRAKTE ENDPOINTS
# ============================================================

async def generate_buchungsnummer(mandant_id: str, ist_einkauf: bool) -> str:
    """Buchungsnummer generieren (EKK-JJMM-NNNNN oder VKK-JJMM-NNNNN)"""
    mandant = await db.mandanten.find_one({"_id": mandant_id})
    prefix = mandant.get("buchungsprefix_ekk", "EKK") if ist_einkauf else mandant.get("buchungsprefix_vkk", "VKK")
    
    now = datetime.utcnow()
    jahr_monat = f"{now.year % 100:02d}{now.month:02d}"
    
    # Höchste Nummer im aktuellen Monat finden
    pattern = f"^{prefix}-{jahr_monat}-"
    letzte = await db.kontrakte.find_one(
        {"mandant_id": mandant_id, "buchungsnummer": {"$regex": pattern}},
        sort=[("buchungsnummer", -1)]
    )
    
    laufnummer = 1
    if letzte:
        teile = letzte["buchungsnummer"].split("-")
        if len(teile) == 3:
            try:
                laufnummer = int(teile[2]) + 1
            except:
                pass
    
    return f"{prefix}-{jahr_monat}-{laufnummer:05d}"

@app.get("/api/kontrakte")
async def get_kontrakte(
    suche: Optional[str] = None,
    ist_einkauf: Optional[bool] = None,
    abgeschlossen: Optional[bool] = None,
    page: int = 1,
    limit: int = 20,
    user = Depends(get_current_user)
):
    """Kontrakte suchen"""
    query = {"mandant_id": user["mandant_id"], "deleted": {"$ne": True}}
    
    if suche:
        query["$or"] = [
            {"buchungsnummer": {"$regex": suche, "$options": "i"}},
            {"name1": {"$regex": suche, "$options": "i"}},
        ]
    if ist_einkauf is not None:
        query["ist_einkauf"] = ist_einkauf
    if abgeschlossen is not None:
        query["abgeschlossen"] = abgeschlossen
    
    total = await db.kontrakte.count_documents(query)
    cursor = db.kontrakte.find(query).sort("buchungsnummer", -1).skip((page - 1) * limit).limit(limit)
    kontrakte = await cursor.to_list(length=limit)
    
    for k in kontrakte:
        k["id"] = k.pop("_id")
    
    return {
        "success": True,
        "data": kontrakte,
        "pagination": {
            "page": page,
            "limit": limit,
            "total": total,
            "total_pages": (total + limit - 1) // limit,
        }
    }

@app.post("/api/kontrakte")
async def create_kontrakt(data: KontraktCreate, user = Depends(get_current_user)):
    """Neuen Kontrakt erstellen"""
    # Adresse laden für Snapshot
    adresse = await db.adressen.find_one({
        "_id": data.adresse_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not adresse:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    buchungsnummer = await generate_buchungsnummer(user["mandant_id"], data.ist_einkauf)
    
    kontrakt = {
        "_id": str(uuid.uuid4()),
        "mandant_id": user["mandant_id"],
        "buchungsnummer": buchungsnummer,
        "adresse_id": data.adresse_id,
        # Adress-Snapshot
        "kdnr": adresse.get("kdnr"),
        "name1": adresse.get("name1"),
        "name2": adresse.get("name2"),
        "strasse": adresse.get("strasse"),
        "plz": adresse.get("plz"),
        "ort": adresse.get("ort"),
        # Daten
        "ist_einkauf": data.ist_einkauf,
        "gueltig_von": data.gueltig_von,
        "gueltig_bis": data.gueltig_bis,
        "bemerkungen_intern": data.bemerkungen_intern,
        "abgeschlossen": False,
        "deleted": False,
        "positionen": [],
        "erstellt_von": user.get("kuerzel"),
        "erstellt_am": datetime.utcnow(),
        "letzte_aenderung": datetime.utcnow(),
    }
    
    await db.kontrakte.insert_one(kontrakt)
    kontrakt["id"] = kontrakt.pop("_id")
    
    return {"success": True, "data": kontrakt}

@app.get("/api/kontrakte/{kontrakt_id}")
async def get_kontrakt(kontrakt_id: str, user = Depends(get_current_user)):
    """Kontrakt nach ID"""
    kontrakt = await db.kontrakte.find_one({
        "_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kontrakt:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    kontrakt["id"] = kontrakt.pop("_id")
    return {"success": True, "data": kontrakt}

@app.post("/api/kontrakte/{kontrakt_id}/positionen")
async def add_kontrakt_position(
    kontrakt_id: str,
    data: KontraktPosCreate,
    user = Depends(get_current_user)
):
    """Position zu Kontrakt hinzufügen"""
    kontrakt = await db.kontrakte.find_one({
        "_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kontrakt:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    if kontrakt.get("abgeschlossen"):
        raise HTTPException(status_code=400, detail="Kontrakt bereits abgeschlossen")
    
    positionen = kontrakt.get("positionen", [])
    pos_nr = len(positionen) + 1
    
    position = {
        "id": str(uuid.uuid4()),
        "positionsnummer": pos_nr,
        **data.model_dump(),
        "erstellt_am": datetime.utcnow(),
    }
    
    await db.kontrakte.update_one(
        {"_id": kontrakt_id},
        {
            "$push": {"positionen": position},
            "$set": {"letzte_aenderung": datetime.utcnow()}
        }
    )
    
    return {"success": True, "data": position}

@app.post("/api/kontrakte/{kontrakt_id}/abschliessen")
async def abschliessen_kontrakt(kontrakt_id: str, user = Depends(get_current_user)):
    """Kontrakt abschließen"""
    result = await db.kontrakte.update_one(
        {
            "_id": kontrakt_id,
            "mandant_id": user["mandant_id"],
            "abgeschlossen": False
        },
        {"$set": {"abgeschlossen": True, "letzte_aenderung": datetime.utcnow()}}
    )
    
    if result.modified_count == 0:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden oder bereits abgeschlossen")
    
    return {"success": True, "message": "Kontrakt abgeschlossen"}

# ============================================================
# DASHBOARD / STATISTIKEN
# ============================================================

@app.get("/api/dashboard/stats")
async def get_dashboard_stats(user = Depends(get_current_user)):
    """Dashboard-Statistiken"""
    mandant_id = user["mandant_id"]
    
    adressen_count = await db.adressen.count_documents({"mandant_id": mandant_id, "aktiv": True})
    artikel_count = await db.artikel.count_documents({"mandant_id": mandant_id, "aktiv": True})
    kontrakte_offen = await db.kontrakte.count_documents({
        "mandant_id": mandant_id,
        "abgeschlossen": False,
        "deleted": {"$ne": True}
    })
    
    return {
        "success": True,
        "data": {
            "adressen": adressen_count,
            "artikel": artikel_count,
            "kontrakte_offen": kontrakte_offen,
        }
    }

# ============================================================
# FILE UPLOAD ENDPOINTS
# ============================================================

@app.post("/api/upload/logo/{adresse_id}")
async def upload_firmenlogo(
    adresse_id: str,
    file: UploadFile = File(...),
    user = Depends(get_current_user)
):
    """Firmenlogo hochladen"""
    # Adresse prüfen
    adresse = await db.adressen.find_one({
        "_id": adresse_id,
        "mandant_id": user["mandant_id"]
    })
    if not adresse:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    # Datei validieren
    if not file.content_type or not file.content_type.startswith("image/"):
        raise HTTPException(status_code=400, detail="Nur Bilddateien erlaubt")
    
    # Dateigröße prüfen (max 5MB)
    content = await file.read()
    if len(content) > 5 * 1024 * 1024:
        raise HTTPException(status_code=400, detail="Datei zu groß (max 5MB)")
    
    # Base64 kodieren und speichern
    ext = file.filename.split(".")[-1] if file.filename else "png"
    logo_data = f"data:{file.content_type};base64,{base64.b64encode(content).decode('utf-8')}"
    
    await db.adressen.update_one(
        {"_id": adresse_id},
        {"$set": {"firmenlogo": logo_data, "letzte_aenderung": datetime.utcnow()}}
    )
    
    return {"success": True, "data": {"firmenlogo": logo_data}}

@app.delete("/api/upload/logo/{adresse_id}")
async def delete_firmenlogo(adresse_id: str, user = Depends(get_current_user)):
    """Firmenlogo löschen"""
    result = await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"$set": {"firmenlogo": None, "letzte_aenderung": datetime.utcnow()}}
    )
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    return {"success": True}

@app.post("/api/adressen/{adresse_id}/ansprechpartner")
async def add_ansprechpartner(
    adresse_id: str,
    ansprechpartner: Dict[str, Any],
    user = Depends(get_current_user)
):
    """Ansprechpartner zu Adresse hinzufügen"""
    adresse = await db.adressen.find_one({
        "_id": adresse_id,
        "mandant_id": user["mandant_id"]
    })
    if not adresse:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    # Ansprechpartner-ID generieren
    ansprechpartner["id"] = str(uuid.uuid4())
    ansprechpartner["erstellt_am"] = datetime.utcnow().isoformat()
    
    await db.adressen.update_one(
        {"_id": adresse_id},
        {
            "$push": {"ansprechpartner": ansprechpartner},
            "$set": {"letzte_aenderung": datetime.utcnow()}
        }
    )
    
    return {"success": True, "data": ansprechpartner}

@app.put("/api/adressen/{adresse_id}/ansprechpartner/{ap_id}")
async def update_ansprechpartner(
    adresse_id: str,
    ap_id: str,
    ansprechpartner: Dict[str, Any],
    user = Depends(get_current_user)
):
    """Ansprechpartner aktualisieren"""
    ansprechpartner["id"] = ap_id
    ansprechpartner["geaendert_am"] = datetime.utcnow().isoformat()
    
    result = await db.adressen.update_one(
        {
            "_id": adresse_id,
            "mandant_id": user["mandant_id"],
            "ansprechpartner.id": ap_id
        },
        {
            "$set": {"ansprechpartner.$": ansprechpartner, "letzte_aenderung": datetime.utcnow()}
        }
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Ansprechpartner nicht gefunden")
    
    return {"success": True, "data": ansprechpartner}

@app.delete("/api/adressen/{adresse_id}/ansprechpartner/{ap_id}")
async def delete_ansprechpartner(
    adresse_id: str,
    ap_id: str,
    user = Depends(get_current_user)
):
    """Ansprechpartner löschen"""
    result = await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {
            "$pull": {"ansprechpartner": {"id": ap_id}},
            "$set": {"letzte_aenderung": datetime.utcnow()}
        }
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    return {"success": True}

@app.post("/api/adressen/{adresse_id}/ansprechpartner/{ap_id}/profilbild")
async def upload_ansprechpartner_profilbild(
    adresse_id: str,
    ap_id: str,
    file: UploadFile = File(...),
    user = Depends(get_current_user)
):
    """Profilbild für Ansprechpartner hochladen"""
    if not file.content_type or not file.content_type.startswith("image/"):
        raise HTTPException(status_code=400, detail="Nur Bilddateien erlaubt")
    
    content = await file.read()
    if len(content) > 2 * 1024 * 1024:
        raise HTTPException(status_code=400, detail="Datei zu groß (max 2MB)")
    
    profilbild = f"data:{file.content_type};base64,{base64.b64encode(content).decode('utf-8')}"
    
    result = await db.adressen.update_one(
        {
            "_id": adresse_id,
            "mandant_id": user["mandant_id"],
            "ansprechpartner.id": ap_id
        },
        {
            "$set": {
                "ansprechpartner.$.profilbild": profilbild,
                "letzte_aenderung": datetime.utcnow()
            }
        }
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Ansprechpartner nicht gefunden")
    
    return {"success": True, "data": {"profilbild": profilbild}}

@app.post("/api/adressen/{adresse_id}/ansprechpartner/{ap_id}/visitenkarte")
async def upload_visitenkarte(
    adresse_id: str,
    ap_id: str,
    file: UploadFile = File(...),
    user = Depends(get_current_user)
):
    """Visitenkarte für Ansprechpartner hochladen"""
    if not file.content_type or not file.content_type.startswith("image/"):
        raise HTTPException(status_code=400, detail="Nur Bilddateien erlaubt")
    
    content = await file.read()
    if len(content) > 5 * 1024 * 1024:
        raise HTTPException(status_code=400, detail="Datei zu groß (max 5MB)")
    
    visitenkarte = f"data:{file.content_type};base64,{base64.b64encode(content).decode('utf-8')}"
    
    result = await db.adressen.update_one(
        {
            "_id": adresse_id,
            "mandant_id": user["mandant_id"],
            "ansprechpartner.id": ap_id
        },
        {
            "$set": {
                "ansprechpartner.$.visitenkarte": visitenkarte,
                "letzte_aenderung": datetime.utcnow()
            }
        }
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Ansprechpartner nicht gefunden")
    
    return {"success": True, "data": {"visitenkarte": visitenkarte}}

# ============================================================
# OCR - VISITENKARTEN-ERKENNUNG
# ============================================================

@app.post("/api/ocr/visitenkarte")
async def ocr_visitenkarte(
    file: UploadFile = File(...),
    user = Depends(get_current_user)
):
    """
    OCR-Erkennung für Visitenkarten
    Extrahiert automatisch: Name, Vorname, Firma, Funktion, Telefon, Mobil, E-Mail
    Verwendet OpenAI GPT-4o Vision
    """
    if not file.content_type or not file.content_type.startswith("image/"):
        raise HTTPException(status_code=400, detail="Nur Bilddateien erlaubt")
    
    content = await file.read()
    if len(content) > 5 * 1024 * 1024:
        raise HTTPException(status_code=400, detail="Datei zu groß (max 5MB)")
    
    # Base64 kodieren
    image_base64 = base64.b64encode(content).decode('utf-8')
    
    try:
        from emergentintegrations.llm.chat import LlmChat, UserMessage, ImageContent
        
        api_key = os.environ.get("EMERGENT_LLM_KEY")
        if not api_key:
            raise HTTPException(status_code=500, detail="OCR-Service nicht konfiguriert")
        
        chat = LlmChat(
            api_key=api_key,
            session_id=f"ocr-{uuid.uuid4()}",
            system_message="""Du bist ein OCR-Spezialist für Visitenkarten. 
Analysiere das Bild der Visitenkarte und extrahiere alle relevanten Kontaktdaten.
Antworte NUR im JSON-Format ohne zusätzlichen Text. Verwende dieses Schema:
{
    "vorname": "string oder null",
    "nachname": "string oder null", 
    "firma": "string oder null",
    "funktion": "string oder null (z.B. Geschäftsführer, Einkauf)",
    "telefon": "string oder null (Festnetz)",
    "mobil": "string oder null (Handy)",
    "email": "string oder null",
    "strasse": "string oder null",
    "plz": "string oder null",
    "ort": "string oder null",
    "webseite": "string oder null"
}
Wenn ein Feld nicht auf der Visitenkarte zu finden ist, setze es auf null."""
        ).with_model("openai", "gpt-4o")
        
        image_content = ImageContent(image_base64=image_base64)
        
        user_message = UserMessage(
            text="Analysiere diese Visitenkarte und extrahiere alle Kontaktdaten im JSON-Format.",
            file_contents=[image_content]
        )
        
        response = await chat.send_message(user_message)
        
        # JSON aus Response extrahieren
        json_match = re.search(r'\{[\s\S]*\}', response)
        if json_match:
            extracted_data = json.loads(json_match.group())
        else:
            extracted_data = {}
        
        return {
            "success": True,
            "data": extracted_data,
            "raw_response": response
        }
        
    except ImportError:
        raise HTTPException(status_code=500, detail="OCR-Modul nicht installiert")
    except json.JSONDecodeError:
        return {
            "success": True,
            "data": {},
            "raw_response": response,
            "error": "JSON-Parsing fehlgeschlagen"
        }
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"OCR-Fehler: {str(e)}")


# ============================================================
# UST-ID VALIDIERUNG (EU VIES)
# ============================================================

class UstIdValidationRequest(BaseModel):
    """Anfrage für UST-ID Validierung"""
    adresse_id: str
    laenderkennzeichen: str = Field(..., min_length=2, max_length=2, description="2-stelliger Ländercode (z.B. DE, AT, FR)")
    ustid: str = Field(..., min_length=1, description="UST-ID ohne Länderprefix")

class UstIdValidationResult(BaseModel):
    """Ergebnis einer UST-ID Validierung"""
    id: Optional[str] = None
    adresse_id: str
    laenderkennzeichen: str
    ustid: str
    gueltig: bool
    firmenname: Optional[str] = None
    adresse: Optional[str] = None
    strasse: Optional[str] = None
    plz: Optional[str] = None
    ort: Optional[str] = None
    abfrage_datum: datetime
    request_identifier: Optional[str] = None
    fehler_code: Optional[str] = None
    abgefragt_von: Optional[str] = None

@app.post("/api/ustid/validate")
async def validate_ustid(
    request: UstIdValidationRequest,
    user = Depends(get_current_user)
):
    """
    Validiert eine UST-ID über die offizielle EU VIES REST API.
    Das Ergebnis wird in der Datenbank protokolliert.
    """
    import httpx
    
    # EU VIES REST API Endpoint
    VIES_API_URL = "https://ec.europa.eu/taxation_customs/vies/rest-api/check-vat-number"
    
    try:
        # UST-ID bereinigen (Leerzeichen und Punkte entfernen)
        clean_ustid = re.sub(r'[\s\.\-]', '', request.ustid)
        
        # VIES API aufrufen
        async with httpx.AsyncClient(timeout=30.0) as client:
            response = await client.post(
                VIES_API_URL,
                json={
                    "countryCode": request.laenderkennzeichen.upper(),
                    "vatNumber": clean_ustid
                },
                headers={"Content-Type": "application/json"}
            )
        
        vies_data = response.json()
        
        # Fehlerprüfung
        if "errorWrappers" in vies_data:
            error_code = vies_data["errorWrappers"][0].get("error", "UNKNOWN_ERROR")
            error_messages = {
                "INVALID_INPUT": "Ungültige Eingabe",
                "GLOBAL_MAX_CONCURRENT_REQ": "Zu viele Anfragen, bitte später erneut versuchen",
                "MS_UNAVAILABLE": "Der Dienst des Mitgliedsstaates ist derzeit nicht verfügbar",
                "MS_MAX_CONCURRENT_REQ": "Zu viele Anfragen an den Mitgliedsstaat",
                "SERVICE_UNAVAILABLE": "VIES-Dienst nicht verfügbar",
                "TIMEOUT": "Zeitüberschreitung bei der Anfrage",
            }
            
            # Protokoll auch bei Fehlern speichern
            protokoll = {
                "_id": str(uuid.uuid4()),
                "adresse_id": request.adresse_id,
                "laenderkennzeichen": request.laenderkennzeichen.upper(),
                "ustid": clean_ustid,
                "gueltig": False,
                "firmenname": None,
                "adresse": None,
                "strasse": None,
                "plz": None,
                "ort": None,
                "abfrage_datum": datetime.utcnow(),
                "request_identifier": None,
                "fehler_code": error_code,
                "abgefragt_von": user.get("kuerzel"),
                "mandant_id": user["mandant_id"]
            }
            await db.ustid_protokoll.insert_one(protokoll)
            protokoll["id"] = protokoll.pop("_id")
            
            return {
                "success": False,
                "error": error_messages.get(error_code, f"VIES-Fehler: {error_code}"),
                "error_code": error_code,
                "data": protokoll
            }
        
        # Erfolgreiches Ergebnis verarbeiten
        is_valid = vies_data.get("valid", False)
        
        # Protokoll erstellen
        protokoll = {
            "_id": str(uuid.uuid4()),
            "adresse_id": request.adresse_id,
            "laenderkennzeichen": request.laenderkennzeichen.upper(),
            "ustid": clean_ustid,
            "gueltig": is_valid,
            "firmenname": vies_data.get("name") if vies_data.get("name") != "---" else None,
            "adresse": vies_data.get("address") if vies_data.get("address") != "---" else None,
            "strasse": vies_data.get("traderStreet") if vies_data.get("traderStreet") != "---" else None,
            "plz": vies_data.get("traderPostalCode") if vies_data.get("traderPostalCode") != "---" else None,
            "ort": vies_data.get("traderCity") if vies_data.get("traderCity") != "---" else None,
            "abfrage_datum": datetime.utcnow(),
            "request_identifier": vies_data.get("requestIdentifier"),
            "fehler_code": None,
            "abgefragt_von": user.get("kuerzel"),
            "mandant_id": user["mandant_id"],
            "vies_response": vies_data  # Vollständige Antwort für Audit-Zwecke
        }
        
        await db.ustid_protokoll.insert_one(protokoll)
        protokoll["id"] = protokoll.pop("_id")
        del protokoll["vies_response"]  # Nicht in Response zurückgeben
        
        return {
            "success": True,
            "data": protokoll,
            "message": "UST-ID ist gültig" if is_valid else "UST-ID ist ungültig"
        }
        
    except httpx.TimeoutException:
        raise HTTPException(status_code=504, detail="Zeitüberschreitung bei der VIES-Anfrage")
    except httpx.RequestError as e:
        raise HTTPException(status_code=502, detail=f"Fehler bei der Verbindung zum VIES-Dienst: {str(e)}")
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Interner Fehler bei der UST-ID Validierung: {str(e)}")


@app.get("/api/ustid/protokoll/{adresse_id}")
async def get_ustid_protokoll(
    adresse_id: str,
    user = Depends(get_current_user)
):
    """
    Ruft das Validierungsprotokoll für eine Adresse ab.
    Sortiert nach Datum (neueste zuerst).
    """
    protokolle = await db.ustid_protokoll.find(
        {
            "adresse_id": adresse_id,
            "mandant_id": user["mandant_id"]
        }
    ).sort("abfrage_datum", -1).to_list(100)
    
    # _id zu id umwandeln
    for p in protokolle:
        p["id"] = p.pop("_id")
        if "vies_response" in p:
            del p["vies_response"]
    
    return {
        "success": True,
        "data": protokolle,
        "count": len(protokolle)
    }


@app.delete("/api/ustid/protokoll/{protokoll_id}")
async def delete_ustid_protokoll(
    protokoll_id: str,
    user = Depends(get_current_user)
):
    """
    Löscht einen einzelnen Protokolleintrag (nur für Admins).
    """
    if not user.get("ist_admin"):
        raise HTTPException(status_code=403, detail="Nur Administratoren können Protokolleinträge löschen")
    
    result = await db.ustid_protokoll.delete_one({
        "_id": protokoll_id,
        "mandant_id": user["mandant_id"]
    })
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="Protokolleintrag nicht gefunden")
    
    return {"success": True, "message": "Protokolleintrag gelöscht"}


# ============================================================
# WIEGEKARTEN (Weighing Cards) - Fahrzeugwaage / Systec IT 4000
# ============================================================

class WiegekartenTyp(str):
    """Typ der Wiegekarte"""
    WIEGESCHEIN = "W"       # Standard Wiegeschein
    EINGANGSSCHEIN = "E"    # Eingangsschein
    HOFSCHEIN = "H"         # Hofschein
    FREMDVERWIEGUNG = "F"   # Fremdverwiegung

class Gueterkategorie(str):
    """Güterkategorie für Lagerplatz"""
    SCHUETTGUT = "S"        # Schüttgut
    STUECKGUT = "P"         # Stückgut (Paletten, Container)

class ZustandWiegekarte(str):
    """Zustand/Status der Wiegekarte"""
    NEU = "NEU"
    STAMMDATEN = "STAMMDATEN"
    WAEGUNG1 = "WAEGUNG1"
    WAEGUNG2 = "WAEGUNG2"
    GEDRUCKT = "GEDRUCKT"
    STORNO = "STORNO"

class WaegungBase(BaseModel):
    """Basismodell für eine Wägung"""
    gewicht: Optional[int] = Field(None, description="Gewicht in kg")
    datum: Optional[datetime] = None
    zeit: Optional[str] = None
    waage_nr: Optional[str] = None
    ident_nr: Optional[str] = None
    terminal_nr: Optional[str] = None
    waage_status: Optional[str] = None
    fehlercode: Optional[str] = None
    einheit: str = "kg"
    tara_code: Optional[str] = None
    waegebereich: Optional[str] = None
    manuell: bool = False
    benutzer: Optional[str] = None

class WiegekarteCreate(BaseModel):
    """Model für das Erstellen einer Wiegekarte"""
    typ_wiegekarte: str = Field("W", description="Typ: W=Wiegeschein, E=Eingangsschein, H=Hofschein, F=Fremdverwiegung")
    ist_lieferant: bool = Field(True, description="True=Wareneingang (Lieferant), False=Warenausgang (Abnehmer)")
    
    # Fahrzeug
    kennzeichen: str = Field(..., min_length=1, max_length=20, description="LKW-Kennzeichen")
    trailer: Optional[str] = Field(None, max_length=20, description="Anhänger-Kennzeichen")
    
    # Adressen
    id_adresse_lieferant: Optional[str] = None
    adresse_lieferant: Optional[str] = None
    id_adresse_spedition: Optional[str] = None
    adresse_spedition: Optional[str] = None
    id_adresse_abn_strecke: Optional[str] = None
    
    # Artikel/Sorte
    id_artikel_sorte: Optional[str] = None
    artikel_bezeichnung: Optional[str] = None
    sorte_hand: bool = False
    
    # Container
    id_container_eigen: Optional[str] = None
    container_nr: Optional[str] = None
    container_tara: Optional[int] = None
    fremdcontainer: bool = False
    container_absetz_grund: Optional[str] = None
    
    # Lagerplatz
    id_lagerplatz_schuett: Optional[str] = None
    id_lagerplatz_absetz: Optional[str] = None
    gueterkategorie: Optional[str] = Field(None, description="S=Schüttgut, P=Stückgut")
    
    # Fuhre-Referenz
    id_vpos_tpa_fuhre: Optional[str] = None
    id_vpos_tpa_fuhre_ort: Optional[str] = None
    
    # Mengen/Stückzahlen
    anz_paletten: Optional[int] = None
    anz_bigbags: Optional[int] = None
    anz_gitterboxen: Optional[int] = None
    anz_behaelter: Optional[int] = None
    anz_allg: Optional[int] = None
    bez_allg: Optional[str] = None
    
    # Bemerkungen
    bemerkung1: Optional[str] = None
    bemerkung2: Optional[str] = None
    bemerkung_intern: Optional[str] = None
    siegel_nr: Optional[str] = None
    befund: Optional[str] = None
    
    # Spezielle Flags
    strahlung_geprueft: bool = False
    ist_gesamtverwiegung: bool = False
    mehrfachverwiegung: bool = False

class WiegekarteUpdate(BaseModel):
    """Model für das Aktualisieren einer Wiegekarte"""
    typ_wiegekarte: Optional[str] = None
    ist_lieferant: Optional[bool] = None
    kennzeichen: Optional[str] = None
    trailer: Optional[str] = None
    id_adresse_lieferant: Optional[str] = None
    adresse_lieferant: Optional[str] = None
    id_adresse_spedition: Optional[str] = None
    adresse_spedition: Optional[str] = None
    id_adresse_abn_strecke: Optional[str] = None
    id_artikel_sorte: Optional[str] = None
    artikel_bezeichnung: Optional[str] = None
    sorte_hand: Optional[bool] = None
    id_container_eigen: Optional[str] = None
    container_nr: Optional[str] = None
    container_tara: Optional[int] = None
    fremdcontainer: Optional[bool] = None
    container_absetz_grund: Optional[str] = None
    id_lagerplatz_schuett: Optional[str] = None
    id_lagerplatz_absetz: Optional[str] = None
    gueterkategorie: Optional[str] = None
    id_vpos_tpa_fuhre: Optional[str] = None
    id_vpos_tpa_fuhre_ort: Optional[str] = None
    anz_paletten: Optional[int] = None
    anz_bigbags: Optional[int] = None
    anz_gitterboxen: Optional[int] = None
    anz_behaelter: Optional[int] = None
    anz_allg: Optional[int] = None
    bez_allg: Optional[str] = None
    bemerkung1: Optional[str] = None
    bemerkung2: Optional[str] = None
    bemerkung_intern: Optional[str] = None
    siegel_nr: Optional[str] = None
    befund: Optional[str] = None
    strahlung_geprueft: Optional[bool] = None
    ist_gesamtverwiegung: Optional[bool] = None
    mehrfachverwiegung: Optional[bool] = None
    # Gewichte (nur bei manueller Eingabe)
    gewicht_abzug: Optional[int] = None
    gewicht_abzug_fuhre: Optional[int] = None
    grund_abzug: Optional[str] = None

class WaegungInput(BaseModel):
    """Eingabe für eine Wägung (manuell oder von Waage)"""
    gewicht: int = Field(..., description="Gewicht in kg")
    manuell: bool = Field(False, description="Manuelle Eingabe statt Waage")
    waage_daten: Optional[str] = Field(None, description="Rohdaten von der Waage (Systec IT 4000)")


@app.get("/api/wiegekarten")
async def get_wiegekarten(
    skip: int = 0,
    limit: int = 100,
    nur_offene: bool = False,
    datum_von: Optional[str] = None,
    datum_bis: Optional[str] = None,
    kennzeichen: Optional[str] = None,
    user = Depends(get_current_user)
):
    """Liste aller Wiegekarten mit Filteroptionen"""
    query = {"mandant_id": user["mandant_id"]}
    
    if nur_offene:
        query["zustand"] = {"$nin": [ZustandWiegekarte.GEDRUCKT, ZustandWiegekarte.STORNO]}
    
    if datum_von:
        query["erstellt_am"] = {"$gte": datetime.fromisoformat(datum_von)}
    if datum_bis:
        if "erstellt_am" not in query:
            query["erstellt_am"] = {}
        query["erstellt_am"]["$lte"] = datetime.fromisoformat(datum_bis)
    
    if kennzeichen:
        query["kennzeichen"] = {"$regex": kennzeichen, "$options": "i"}
    
    cursor = db.wiegekarten.find(query, {"_id": 0}).sort("erstellt_am", -1).skip(skip).limit(limit)
    wiegekarten = await cursor.to_list(limit)
    
    total = await db.wiegekarten.count_documents(query)
    
    return {
        "success": True,
        "data": wiegekarten,
        "total": total,
        "skip": skip,
        "limit": limit
    }


@app.get("/api/wiegekarten/{wiegekarte_id}")
async def get_wiegekarte(wiegekarte_id: str, user = Depends(get_current_user)):
    """Einzelne Wiegekarte abrufen"""
    wiegekarte = await db.wiegekarten.find_one(
        {"id": wiegekarte_id, "mandant_id": user["mandant_id"]},
        {"_id": 0}
    )
    
    if not wiegekarte:
        raise HTTPException(status_code=404, detail="Wiegekarte nicht gefunden")
    
    return {"success": True, "data": wiegekarte}


@app.post("/api/wiegekarten")
async def create_wiegekarte(data: WiegekarteCreate, user = Depends(get_current_user)):
    """Neue Wiegekarte anlegen"""
    
    # Laufende Nummer ermitteln
    year = datetime.utcnow().year
    count = await db.wiegekarten.count_documents({
        "mandant_id": user["mandant_id"],
        "jahr": year
    })
    lfd_nr = count + 1
    
    wiegekarte = {
        "id": str(uuid.uuid4()),
        "mandant_id": user["mandant_id"],
        "lfd_nr": lfd_nr,
        "jahr": year,
        "wiegekarten_nr": f"WK-{year}-{lfd_nr:05d}",
        "zustand": ZustandWiegekarte.NEU,
        
        # Kopfdaten
        "typ_wiegekarte": data.typ_wiegekarte,
        "ist_lieferant": data.ist_lieferant,
        
        # Fahrzeug
        "kennzeichen": data.kennzeichen.upper(),
        "trailer": data.trailer.upper() if data.trailer else None,
        
        # Adressen
        "id_adresse_lieferant": data.id_adresse_lieferant,
        "adresse_lieferant": data.adresse_lieferant,
        "id_adresse_spedition": data.id_adresse_spedition,
        "adresse_spedition": data.adresse_spedition,
        "id_adresse_abn_strecke": data.id_adresse_abn_strecke,
        
        # Artikel
        "id_artikel_sorte": data.id_artikel_sorte,
        "artikel_bezeichnung": data.artikel_bezeichnung,
        "sorte_hand": data.sorte_hand,
        
        # Container
        "id_container_eigen": data.id_container_eigen,
        "container_nr": data.container_nr,
        "container_tara": data.container_tara,
        "fremdcontainer": data.fremdcontainer,
        "container_absetz_grund": data.container_absetz_grund,
        
        # Lagerplatz
        "id_lagerplatz_schuett": data.id_lagerplatz_schuett,
        "id_lagerplatz_absetz": data.id_lagerplatz_absetz,
        "gueterkategorie": data.gueterkategorie,
        
        # Fuhre
        "id_vpos_tpa_fuhre": data.id_vpos_tpa_fuhre,
        "id_vpos_tpa_fuhre_ort": data.id_vpos_tpa_fuhre_ort,
        
        # Mengen
        "anz_paletten": data.anz_paletten,
        "anz_bigbags": data.anz_bigbags,
        "anz_gitterboxen": data.anz_gitterboxen,
        "anz_behaelter": data.anz_behaelter,
        "anz_allg": data.anz_allg,
        "bez_allg": data.bez_allg,
        
        # Bemerkungen
        "bemerkung1": data.bemerkung1,
        "bemerkung2": data.bemerkung2,
        "bemerkung_intern": data.bemerkung_intern,
        "siegel_nr": data.siegel_nr,
        "befund": data.befund,
        
        # Flags
        "strahlung_geprueft": data.strahlung_geprueft,
        "ist_gesamtverwiegung": data.ist_gesamtverwiegung,
        "mehrfachverwiegung": data.mehrfachverwiegung,
        "storno": False,
        
        # Wägungen (initial leer)
        "waegung1": None,
        "waegung2": None,
        
        # Gewichte
        "bruttogewicht": None,
        "taragewicht": None,
        "nettogewicht": None,
        "gewicht_abzug": None,
        "gewicht_abzug_fuhre": None,
        "gewicht_nach_abzug": None,
        "gewicht_nach_abzug_fuhre": None,
        "grund_abzug": None,
        
        # Druck
        "gedruckt_am": None,
        "druckzaehler": 0,
        "druckzaehler_eingangsschein": 0,
        
        # Meta
        "erstellt_am": datetime.utcnow(),
        "erstellt_von": user.get("kuerzel"),
        "geaendert_am": None,
        "geaendert_von": None
    }
    
    await db.wiegekarten.insert_one(wiegekarte)
    del wiegekarte["_id"]
    
    return {"success": True, "data": wiegekarte, "message": f"Wiegekarte {wiegekarte['wiegekarten_nr']} erstellt"}


@app.put("/api/wiegekarten/{wiegekarte_id}")
async def update_wiegekarte(
    wiegekarte_id: str, 
    data: WiegekarteUpdate, 
    user = Depends(get_current_user)
):
    """Wiegekarte aktualisieren"""
    
    existing = await db.wiegekarten.find_one({
        "id": wiegekarte_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not existing:
        raise HTTPException(status_code=404, detail="Wiegekarte nicht gefunden")
    
    if existing.get("storno"):
        raise HTTPException(status_code=400, detail="Stornierte Wiegekarten können nicht bearbeitet werden")
    
    update_data = data.dict(exclude_unset=True)
    update_data["geaendert_am"] = datetime.utcnow()
    update_data["geaendert_von"] = user.get("kuerzel")
    
    # Kennzeichen in Großbuchstaben
    if "kennzeichen" in update_data and update_data["kennzeichen"]:
        update_data["kennzeichen"] = update_data["kennzeichen"].upper()
    if "trailer" in update_data and update_data["trailer"]:
        update_data["trailer"] = update_data["trailer"].upper()
    
    await db.wiegekarten.update_one(
        {"id": wiegekarte_id},
        {"$set": update_data}
    )
    
    updated = await db.wiegekarten.find_one({"id": wiegekarte_id}, {"_id": 0})
    
    return {"success": True, "data": updated}


@app.post("/api/wiegekarten/{wiegekarte_id}/waegung/{waegung_nr}")
async def speichere_waegung(
    wiegekarte_id: str,
    waegung_nr: int,
    data: WaegungInput,
    user = Depends(get_current_user)
):
    """
    Wägung speichern (1 oder 2).
    Bei Wareneingang: Wägung 1 = Brutto (voll), Wägung 2 = Tara (leer)
    Bei Warenausgang: Wägung 1 = Tara (leer), Wägung 2 = Brutto (voll)
    """
    if waegung_nr not in [1, 2]:
        raise HTTPException(status_code=400, detail="Wägungsnummer muss 1 oder 2 sein")
    
    wiegekarte = await db.wiegekarten.find_one({
        "id": wiegekarte_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not wiegekarte:
        raise HTTPException(status_code=404, detail="Wiegekarte nicht gefunden")
    
    if wiegekarte.get("storno"):
        raise HTTPException(status_code=400, detail="Stornierte Wiegekarten können nicht gewogen werden")
    
    # Wägungsdaten erstellen
    waegung = {
        "gewicht": data.gewicht,
        "datum": datetime.utcnow(),
        "zeit": datetime.utcnow().strftime("%H:%M"),
        "manuell": data.manuell,
        "benutzer": user.get("kuerzel"),
        "einheit": "kg"
    }
    
    # Wenn Waage-Rohdaten vorhanden (Systec IT 4000)
    if data.waage_daten:
        waegung["rohdaten"] = data.waage_daten
        # Parse Systec Daten
        if len(data.waage_daten) >= 50:
            waegung["fehlercode"] = data.waage_daten[0:2]
            waegung["waage_status"] = data.waage_daten[2:4]
            waegung["waage_datum"] = data.waage_daten[4:12]
            waegung["waage_zeit"] = data.waage_daten[12:17]
            waegung["ident_nr"] = data.waage_daten[17:21]
            waegung["waage_nr"] = data.waage_daten[21:22]
            waegung["terminal_nr"] = data.waage_daten[46:49]
    
    # Update Dokument
    update_data = {
        f"waegung{waegung_nr}": waegung,
        "geaendert_am": datetime.utcnow(),
        "geaendert_von": user.get("kuerzel")
    }
    
    # Gewichte berechnen basierend auf beiden Wägungen
    waegung1 = waegung if waegung_nr == 1 else wiegekarte.get("waegung1")
    waegung2 = waegung if waegung_nr == 2 else wiegekarte.get("waegung2")
    
    ist_lieferant = wiegekarte.get("ist_lieferant", True)
    
    if waegung1 and waegung2:
        gew1 = waegung1.get("gewicht", 0) or 0
        gew2 = waegung2.get("gewicht", 0) or 0
        
        if ist_lieferant:
            # Wareneingang: Wägung1 = Brutto, Wägung2 = Tara
            update_data["bruttogewicht"] = gew1
            update_data["taragewicht"] = gew2
        else:
            # Warenausgang: Wägung1 = Tara, Wägung2 = Brutto
            update_data["taragewicht"] = gew1
            update_data["bruttogewicht"] = gew2
        
        update_data["nettogewicht"] = abs(update_data["bruttogewicht"] - update_data["taragewicht"])
        
        # Abzüge berücksichtigen
        abzug = (wiegekarte.get("gewicht_abzug") or 0) + (wiegekarte.get("gewicht_abzug_fuhre") or 0)
        update_data["gewicht_nach_abzug"] = update_data["nettogewicht"] - abzug
        
        update_data["zustand"] = ZustandWiegekarte.WAEGUNG2
    elif waegung1:
        if ist_lieferant:
            update_data["bruttogewicht"] = waegung1.get("gewicht")
        else:
            update_data["taragewicht"] = waegung1.get("gewicht")
        update_data["zustand"] = ZustandWiegekarte.WAEGUNG1
    
    await db.wiegekarten.update_one({"id": wiegekarte_id}, {"$set": update_data})
    
    updated = await db.wiegekarten.find_one({"id": wiegekarte_id}, {"_id": 0})
    
    return {
        "success": True, 
        "data": updated,
        "message": f"Wägung {waegung_nr} gespeichert: {data.gewicht} kg"
    }


@app.post("/api/wiegekarten/{wiegekarte_id}/storno")
async def storniere_wiegekarte(wiegekarte_id: str, user = Depends(get_current_user)):
    """Wiegekarte stornieren"""
    
    result = await db.wiegekarten.update_one(
        {
            "id": wiegekarte_id,
            "mandant_id": user["mandant_id"],
            "storno": False
        },
        {
            "$set": {
                "storno": True,
                "zustand": ZustandWiegekarte.STORNO,
                "storniert_am": datetime.utcnow(),
                "storniert_von": user.get("kuerzel")
            }
        }
    )
    
    if result.modified_count == 0:
        raise HTTPException(status_code=404, detail="Wiegekarte nicht gefunden oder bereits storniert")
    
    return {"success": True, "message": "Wiegekarte storniert"}


@app.delete("/api/wiegekarten/{wiegekarte_id}")
async def delete_wiegekarte(wiegekarte_id: str, user = Depends(get_current_user)):
    """Wiegekarte löschen (nur wenn noch keine Wägung erfolgt)"""
    
    wiegekarte = await db.wiegekarten.find_one({
        "id": wiegekarte_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not wiegekarte:
        raise HTTPException(status_code=404, detail="Wiegekarte nicht gefunden")
    
    if wiegekarte.get("waegung1") or wiegekarte.get("waegung2"):
        raise HTTPException(status_code=400, detail="Wiegekarten mit Wägungen können nicht gelöscht werden. Bitte stornieren.")
    
    await db.wiegekarten.delete_one({"id": wiegekarte_id})
    
    return {"success": True, "message": "Wiegekarte gelöscht"}


# ============================================================
# WAAGE - Systec IT 4000 IP Terminal
# ============================================================

class WaageConfig(BaseModel):
    """Konfiguration für ein Wiegeterminal"""
    id: Optional[str] = None
    name: str
    typ: str = "SYSTEC_IP"  # SYSTEC_IP, SYSTEC_USB, BUL_MINIPOND
    ip_adresse: Optional[str] = None
    port: int = 8000
    waage_nr: str = "1"
    aktiv: bool = True
    standort: Optional[str] = None

@app.get("/api/waage/config")
async def get_waage_config(user = Depends(get_current_user)):
    """Waage-Konfiguration abrufen"""
    config = await db.waage_config.find_one(
        {"mandant_id": user["mandant_id"]},
        {"_id": 0}
    )
    
    if not config:
        # Standard-Konfiguration
        config = {
            "id": str(uuid.uuid4()),
            "mandant_id": user["mandant_id"],
            "waagen": [{
                "id": str(uuid.uuid4()),
                "name": "Fahrzeugwaage 1",
                "typ": "SYSTEC_IP",
                "ip_adresse": "192.168.1.100",
                "port": 8000,
                "waage_nr": "1",
                "aktiv": True,
                "standort": "Einfahrt"
            }]
        }
        await db.waage_config.insert_one(config)
        del config["_id"]
    
    return {"success": True, "data": config}


@app.get("/api/waage/status")
async def get_waage_status(user = Depends(get_current_user)):
    """
    Waage-Status abfragen.
    In einer echten Implementierung würde hier eine Socket-Verbindung
    zum Systec IT 4000 Terminal hergestellt.
    """
    # Demo-Status (in Produktion: echte Waage-Kommunikation)
    return {
        "success": True,
        "data": {
            "verbunden": True,
            "waage_nr": "1",
            "status": "bereit",
            "letztes_gewicht": None,
            "fehler": None,
            "demo_modus": True
        }
    }


@app.post("/api/waage/lesen")
async def lese_waage(user = Depends(get_current_user)):
    """
    Aktuelles Gewicht von der Waage lesen.
    Sendet <RN1> Kommando an Systec IT 4000.
    """
    import random
    
    # Demo-Daten (in Produktion: Socket-Kommunikation mit Waage)
    demo_gewicht = random.randint(5000, 35000)
    demo_datum = datetime.utcnow()
    
    # Simulierter Systec-Datensatz
    waage_satz = {
        "fehlercode": "00",
        "waage_status": "01",  # 01 = Ruhe
        "datum": demo_datum.strftime("%d.%m.%y"),
        "zeit": demo_datum.strftime("%H:%M"),
        "ident_nr": "0001",
        "waage_nr": "1",
        "brutto_gewicht": demo_gewicht,
        "tara_gewicht": 0,
        "netto_gewicht": demo_gewicht,
        "einheit": "kg",
        "tara_code": "PT",
        "waegebereich": "2",
        "terminal_nr": "001",
        "rohdaten": f"0001{demo_datum.strftime('%d.%m.%y%H:%M')}   11{demo_gewicht:08d}00000000{demo_gewicht:08d}kgPT2001",
        "demo_modus": True
    }
    
    return {
        "success": True,
        "data": waage_satz,
        "message": f"Gewicht gelesen: {demo_gewicht} kg"
    }


@app.get("/api/wiegekarten/statistik")
async def get_wiegekarten_statistik(user = Depends(get_current_user)):
    """Statistiken für das Dashboard"""
    today = datetime.utcnow().replace(hour=0, minute=0, second=0, microsecond=0)
    
    # Heute
    heute_count = await db.wiegekarten.count_documents({
        "mandant_id": user["mandant_id"],
        "erstellt_am": {"$gte": today}
    })
    
    # Offene Wiegekarten
    offene_count = await db.wiegekarten.count_documents({
        "mandant_id": user["mandant_id"],
        "zustand": {"$nin": [ZustandWiegekarte.GEDRUCKT, ZustandWiegekarte.STORNO]}
    })
    
    # Gesamtgewicht heute
    pipeline = [
        {
            "$match": {
                "mandant_id": user["mandant_id"],
                "erstellt_am": {"$gte": today},
                "nettogewicht": {"$ne": None}
            }
        },
        {
            "$group": {
                "_id": None,
                "gesamt_netto": {"$sum": "$nettogewicht"}
            }
        }
    ]
    
    result = await db.wiegekarten.aggregate(pipeline).to_list(1)
    gesamt_netto_heute = result[0]["gesamt_netto"] if result else 0
    
    return {
        "success": True,
        "data": {
            "heute": heute_count,
            "offen": offene_count,
            "gesamt_netto_heute_kg": gesamt_netto_heute,
            "gesamt_netto_heute_t": round(gesamt_netto_heute / 1000, 2)
        }
    }


if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8001)
