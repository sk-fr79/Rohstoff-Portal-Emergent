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
from pathlib import Path
from motor.motor_asyncio import AsyncIOMotorClient
from contextlib import asynccontextmanager

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
    
    # Weitere UST-IDs (für andere Länder)
    ust_at: Optional[str] = Field(None, max_length=20)  # Österreich
    ust_nl: Optional[str] = Field(None, max_length=20)  # Niederlande
    ust_ch: Optional[str] = Field(None, max_length=20)  # Schweiz
    
    # Handelsregister
    handelsregister: Optional[str] = Field(None, max_length=50)
    
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
    ust_at: Optional[str] = Field(None, max_length=20)
    ust_nl: Optional[str] = Field(None, max_length=20)
    ust_ch: Optional[str] = Field(None, max_length=20)
    handelsregister: Optional[str] = Field(None, max_length=50)
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

if __name__ == "__main__":
    import uvicorn
    uvicorn.run(app, host="0.0.0.0", port=8001)
