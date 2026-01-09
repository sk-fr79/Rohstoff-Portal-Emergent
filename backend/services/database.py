"""
Database Service - MongoDB Connection Management
"""

from motor.motor_asyncio import AsyncIOMotorClient
from typing import Optional
import os

# Globale Datenbank-Instanzen
client: Optional[AsyncIOMotorClient] = None
db = None

MONGO_URL = os.environ.get("MONGO_URL", "mongodb://localhost:27017")
DB_NAME = os.environ.get("DB_NAME", "rohstoff_portal")


async def init_db():
    """Initialisiert die Datenbankverbindung"""
    global client, db
    client = AsyncIOMotorClient(MONGO_URL)
    db = client[DB_NAME]
    return db


async def close_db():
    """Schließt die Datenbankverbindung"""
    global client
    if client:
        client.close()


def get_db():
    """Gibt die aktuelle Datenbankinstanz zurück"""
    return db
