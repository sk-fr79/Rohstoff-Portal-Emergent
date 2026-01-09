"""
Rohstoff Portal - FastAPI Backend
Modernisierte Version des Java/Echo2 ERP-Systems

Modulare Struktur:
- routers/  -> API-Endpunkte
- models/   -> Pydantic-Schemas
- services/ -> Geschäftslogik & Datenbank
- utils/    -> Hilfsfunktionen
"""

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from contextlib import asynccontextmanager
import os
import uuid
from pathlib import Path
from datetime import datetime
from dotenv import load_dotenv

# Load environment variables
load_dotenv()

# Upload-Verzeichnis erstellen
UPLOAD_DIR = Path("/app/backend/uploads")
UPLOAD_DIR.mkdir(parents=True, exist_ok=True)
(UPLOAD_DIR / "logos").mkdir(exist_ok=True)
(UPLOAD_DIR / "profiles").mkdir(exist_ok=True)
(UPLOAD_DIR / "visitenkarten").mkdir(exist_ok=True)

# Services importieren
from services.database import init_db, close_db, get_db
from utils.auth import hash_password

# Router importieren
from routers import auth, dashboard, adressen, artikel, kontrakte, wiegekarten, fuhren, rechnungen, kreditversicherung


@asynccontextmanager
async def lifespan(app: FastAPI):
    """Lifecycle-Management für Datenbankverbindung"""
    db = await init_db()
    
    # Indizes erstellen
    await db.benutzer.create_index("benutzername", unique=True)
    await db.benutzer.create_index("email", unique=True)
    await db.adressen.create_index([("mandant_id", 1), ("kdnr", 1)])
    await db.adressen.create_index([("mandant_id", 1), ("name1", 1)])
    await db.artikel.create_index([("mandant_id", 1), ("artbez1", 1)])
    await db.kontrakte.create_index([("mandant_id", 1), ("buchungsnummer", 1)])
    await db.fuhren.create_index([("mandant_id", 1), ("fuhren_nr", 1)])
    await db.rechnungen.create_index([("mandant_id", 1), ("rechnungs_nr", 1)])
    await db.wiegekarten.create_index([("mandant_id", 1), ("wiegekarten_nr", 1)])
    await db.kreditversicherungen.create_index([("mandant_id", 1), ("versicherte_adressen.adresse_id", 1)])
    
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
    
    await close_db()


# App Setup
app = FastAPI(
    title="Rohstoff Portal API",
    description="Enterprise ERP für Rohstoffhandel - Modernisierte Version",
    version="2.0.0",
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

# Router registrieren
app.include_router(auth.router)
app.include_router(dashboard.router)
app.include_router(adressen.router)
app.include_router(artikel.router)
app.include_router(kontrakte.router)
app.include_router(wiegekarten.router)
app.include_router(fuhren.router)
app.include_router(rechnungen.router)
app.include_router(kreditversicherung.router)


@app.get("/api/health")
async def health_check():
    """Gesundheitsprüfung für Load-Balancer"""
    db = get_db()
    try:
        await db.command("ping")
        return {"status": "healthy", "database": "connected"}
    except Exception as e:
        return {"status": "unhealthy", "database": str(e)}
