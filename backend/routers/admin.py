"""
Admin Router - Benutzerverwaltung, Rollen, Abteilungen, Berechtigungen
Nur für Administratoren zugänglich
"""

from fastapi import APIRouter, HTTPException, Depends
from pydantic import BaseModel, Field, EmailStr
from typing import Optional, List
from datetime import datetime
import uuid
import hashlib

from services.database import get_db
from utils.auth import get_current_user, hash_password

router = APIRouter(prefix="/api/admin", tags=["Administration"])


# ============================================================
# HELPER: Admin-Check
# ============================================================

async def require_admin(user = Depends(get_current_user)):
    """Stellt sicher, dass nur Admins Zugriff haben"""
    if not user.get("ist_admin", False):
        raise HTTPException(status_code=403, detail="Nur Administratoren haben Zugriff")
    return user


# ============================================================
# PYDANTIC MODELS
# ============================================================

# --- Rollen ---
class RolleCreate(BaseModel):
    name: str = Field(..., min_length=1, max_length=50)
    beschreibung: Optional[str] = None
    farbe: Optional[str] = Field(None, description="Hex-Farbcode für UI")


class RolleUpdate(BaseModel):
    name: Optional[str] = None
    beschreibung: Optional[str] = None
    farbe: Optional[str] = None


# --- Abteilungen ---
class AbteilungCreate(BaseModel):
    name: str = Field(..., min_length=1, max_length=100)
    kuerzel: Optional[str] = Field(None, max_length=10)
    beschreibung: Optional[str] = None
    leiter_id: Optional[str] = None


class AbteilungUpdate(BaseModel):
    name: Optional[str] = None
    kuerzel: Optional[str] = None
    beschreibung: Optional[str] = None
    leiter_id: Optional[str] = None


# --- Benutzer ---
class BenutzerCreate(BaseModel):
    benutzername: str = Field(..., min_length=3, max_length=50)
    email: EmailStr
    passwort: str = Field(..., min_length=6)
    vorname: Optional[str] = None
    nachname: Optional[str] = None
    kuerzel: Optional[str] = Field(None, max_length=5)
    rolle_id: Optional[str] = None
    abteilung_ids: List[str] = Field(default_factory=list)
    aktiv: bool = True


class BenutzerUpdate(BaseModel):
    email: Optional[EmailStr] = None
    vorname: Optional[str] = None
    nachname: Optional[str] = None
    kuerzel: Optional[str] = None
    rolle_id: Optional[str] = None
    abteilung_ids: Optional[List[str]] = None
    aktiv: Optional[bool] = None
    profilbild_url: Optional[str] = None


class PasswortReset(BaseModel):
    neues_passwort: str = Field(..., min_length=6)


# --- Berechtigungen ---
class BerechtigungCreate(BaseModel):
    modul: str = Field(..., description="Modul-Schlüssel (z.B. 'adressen', 'kontrakte')")
    ziel_typ: str = Field(..., description="'rolle', 'abteilung' oder 'benutzer'")
    ziel_id: str = Field(..., description="ID der Rolle/Abteilung/Benutzer")
    level: str = Field(..., description="'read', 'write', 'full', 'denied'")


class BerechtigungUpdate(BaseModel):
    level: str = Field(..., description="'read', 'write', 'full', 'denied'")


# ============================================================
# VERFÜGBARE MODULE (für Berechtigungssteuerung)
# ============================================================

VERFUEGBARE_MODULE = [
    {"key": "dashboard", "name": "Dashboard", "gruppe": "Allgemein"},
    {"key": "adressen", "name": "Adressen", "gruppe": "Stammdaten"},
    {"key": "artikel", "name": "Artikel", "gruppe": "Stammdaten"},
    {"key": "kreditversicherungen", "name": "Kreditversicherung", "gruppe": "Stammdaten"},
    {"key": "kontrakte", "name": "Kontrakte", "gruppe": "Bewegungsdaten"},
    {"key": "fuhren", "name": "Fuhren", "gruppe": "Lager"},
    {"key": "wiegekarten", "name": "Wiegekarten", "gruppe": "Waage"},
    {"key": "rechnungen", "name": "Rechnungen", "gruppe": "Finanzen"},
    {"key": "berichte", "name": "Berichte", "gruppe": "Auswertungen"},
    {"key": "einstellungen", "name": "Einstellungen", "gruppe": "System"},
    {"key": "admin", "name": "Administration", "gruppe": "System"},
]

BERECHTIGUNG_LEVELS = ["read", "write", "full", "denied"]


# ============================================================
# ROLLEN ENDPOINTS
# ============================================================

@router.get("/rollen")
async def list_rollen(user = Depends(require_admin)):
    """Liste aller Rollen"""
    db = get_db()
    
    cursor = db.rollen.find({"mandant_id": user["mandant_id"]}).sort("name", 1)
    
    items = []
    async for doc in cursor:
        doc["id"] = doc.pop("_id")
        # Anzahl Benutzer mit dieser Rolle
        benutzer_count = await db.benutzer.count_documents({
            "mandant_id": user["mandant_id"],
            "rolle_id": doc["id"]
        })
        doc["benutzer_count"] = benutzer_count
        items.append(doc)
    
    return {"success": True, "data": items}


@router.post("/rollen")
async def create_rolle(data: RolleCreate, user = Depends(require_admin)):
    """Neue Rolle anlegen"""
    db = get_db()
    
    # Prüfen ob Name bereits existiert
    existing = await db.rollen.find_one({
        "mandant_id": user["mandant_id"],
        "name": {"$regex": f"^{data.name}$", "$options": "i"}
    })
    if existing:
        raise HTTPException(status_code=400, detail="Rolle mit diesem Namen existiert bereits")
    
    rolle_id = "ROL-" + str(uuid.uuid4())[:8].upper()
    
    rolle = {
        "_id": rolle_id,
        "mandant_id": user["mandant_id"],
        "name": data.name,
        "beschreibung": data.beschreibung,
        "farbe": data.farbe or "#6B7280",
        "ist_system": False,
        "created_at": datetime.utcnow().isoformat(),
    }
    
    await db.rollen.insert_one(rolle)
    
    rolle["id"] = rolle.pop("_id")
    return {"success": True, "data": rolle}


@router.put("/rollen/{rolle_id}")
async def update_rolle(rolle_id: str, data: RolleUpdate, user = Depends(require_admin)):
    """Rolle aktualisieren"""
    db = get_db()
    
    # System-Rolle prüfen
    rolle = await db.rollen.find_one({
        "_id": rolle_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not rolle:
        raise HTTPException(status_code=404, detail="Rolle nicht gefunden")
    
    if rolle.get("ist_system") and data.name and data.name != rolle["name"]:
        raise HTTPException(status_code=400, detail="System-Rolle kann nicht umbenannt werden")
    
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    update_data["updated_at"] = datetime.utcnow().isoformat()
    
    await db.rollen.update_one(
        {"_id": rolle_id, "mandant_id": user["mandant_id"]},
        {"$set": update_data}
    )
    
    return {"success": True}


@router.delete("/rollen/{rolle_id}")
async def delete_rolle(rolle_id: str, user = Depends(require_admin)):
    """Rolle löschen"""
    db = get_db()
    
    rolle = await db.rollen.find_one({
        "_id": rolle_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not rolle:
        raise HTTPException(status_code=404, detail="Rolle nicht gefunden")
    
    if rolle.get("ist_system"):
        raise HTTPException(status_code=400, detail="System-Rolle kann nicht gelöscht werden")
    
    # Prüfen ob Benutzer diese Rolle haben
    benutzer_count = await db.benutzer.count_documents({
        "mandant_id": user["mandant_id"],
        "rolle_id": rolle_id
    })
    
    if benutzer_count > 0:
        raise HTTPException(
            status_code=400, 
            detail=f"Rolle wird von {benutzer_count} Benutzer(n) verwendet"
        )
    
    await db.rollen.delete_one({"_id": rolle_id, "mandant_id": user["mandant_id"]})
    
    # Berechtigungen für diese Rolle löschen
    await db.berechtigungen.delete_many({
        "mandant_id": user["mandant_id"],
        "ziel_typ": "rolle",
        "ziel_id": rolle_id
    })
    
    return {"success": True}


# ============================================================
# ABTEILUNGEN ENDPOINTS
# ============================================================

@router.get("/abteilungen")
async def list_abteilungen(user = Depends(require_admin)):
    """Liste aller Abteilungen"""
    db = get_db()
    
    cursor = db.abteilungen.find({"mandant_id": user["mandant_id"]}).sort("name", 1)
    
    items = []
    async for doc in cursor:
        doc["id"] = doc.pop("_id")
        # Anzahl Benutzer in dieser Abteilung
        benutzer_count = await db.benutzer.count_documents({
            "mandant_id": user["mandant_id"],
            "abteilung_ids": doc["id"]
        })
        doc["benutzer_count"] = benutzer_count
        items.append(doc)
    
    return {"success": True, "data": items}


@router.post("/abteilungen")
async def create_abteilung(data: AbteilungCreate, user = Depends(require_admin)):
    """Neue Abteilung anlegen"""
    db = get_db()
    
    abt_id = "ABT-" + str(uuid.uuid4())[:8].upper()
    
    abteilung = {
        "_id": abt_id,
        "mandant_id": user["mandant_id"],
        "name": data.name,
        "kuerzel": data.kuerzel,
        "beschreibung": data.beschreibung,
        "leiter_id": data.leiter_id,
        "created_at": datetime.utcnow().isoformat(),
    }
    
    await db.abteilungen.insert_one(abteilung)
    
    abteilung["id"] = abteilung.pop("_id")
    return {"success": True, "data": abteilung}


@router.put("/abteilungen/{abt_id}")
async def update_abteilung(abt_id: str, data: AbteilungUpdate, user = Depends(require_admin)):
    """Abteilung aktualisieren"""
    db = get_db()
    
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    update_data["updated_at"] = datetime.utcnow().isoformat()
    
    result = await db.abteilungen.update_one(
        {"_id": abt_id, "mandant_id": user["mandant_id"]},
        {"$set": update_data}
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Abteilung nicht gefunden")
    
    return {"success": True}


@router.delete("/abteilungen/{abt_id}")
async def delete_abteilung(abt_id: str, user = Depends(require_admin)):
    """Abteilung löschen"""
    db = get_db()
    
    # Prüfen ob Benutzer in dieser Abteilung sind
    benutzer_count = await db.benutzer.count_documents({
        "mandant_id": user["mandant_id"],
        "abteilung_ids": abt_id
    })
    
    if benutzer_count > 0:
        raise HTTPException(
            status_code=400, 
            detail=f"Abteilung hat noch {benutzer_count} Mitarbeiter"
        )
    
    result = await db.abteilungen.delete_one({
        "_id": abt_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="Abteilung nicht gefunden")
    
    # Berechtigungen für diese Abteilung löschen
    await db.berechtigungen.delete_many({
        "mandant_id": user["mandant_id"],
        "ziel_typ": "abteilung",
        "ziel_id": abt_id
    })
    
    return {"success": True}


# ============================================================
# BENUTZER ENDPOINTS
# ============================================================

def hash_password(password: str) -> str:
    """Einfaches Password-Hashing (in Produktion bcrypt verwenden)"""
    return hashlib.sha256(password.encode()).hexdigest()


@router.get("/benutzer")
async def list_benutzer(
    aktiv: Optional[bool] = None,
    rolle_id: Optional[str] = None,
    abteilung_id: Optional[str] = None,
    search: Optional[str] = None,
    user = Depends(require_admin)
):
    """Liste aller Benutzer"""
    db = get_db()
    
    query = {"mandant_id": user["mandant_id"]}
    
    if aktiv is not None:
        query["aktiv"] = aktiv
    
    if rolle_id:
        query["rolle_id"] = rolle_id
    
    if abteilung_id:
        query["abteilung_ids"] = abteilung_id
    
    if search:
        query["$or"] = [
            {"benutzername": {"$regex": search, "$options": "i"}},
            {"email": {"$regex": search, "$options": "i"}},
            {"vorname": {"$regex": search, "$options": "i"}},
            {"nachname": {"$regex": search, "$options": "i"}},
        ]
    
    cursor = db.benutzer.find(query, {"passwort_hash": 0}).sort("benutzername", 1)
    
    items = []
    async for doc in cursor:
        doc["id"] = doc.pop("_id")
        
        # Rolle laden
        if doc.get("rolle_id"):
            rolle = await db.rollen.find_one({"_id": doc["rolle_id"]})
            doc["rolle_name"] = rolle.get("name") if rolle else None
            doc["rolle_farbe"] = rolle.get("farbe") if rolle else None
        
        # Abteilungen laden
        abteilung_names = []
        for abt_id in doc.get("abteilung_ids", []):
            abt = await db.abteilungen.find_one({"_id": abt_id})
            if abt:
                abteilung_names.append(abt.get("name"))
        doc["abteilung_names"] = abteilung_names
        
        items.append(doc)
    
    return {"success": True, "data": items}


@router.get("/benutzer/{benutzer_id}")
async def get_benutzer(benutzer_id: str, user = Depends(require_admin)):
    """Einzelnen Benutzer abrufen"""
    db = get_db()
    
    doc = await db.benutzer.find_one(
        {"_id": benutzer_id, "mandant_id": user["mandant_id"]},
        {"passwort_hash": 0}
    )
    
    if not doc:
        raise HTTPException(status_code=404, detail="Benutzer nicht gefunden")
    
    doc["id"] = doc.pop("_id")
    
    # Rolle laden
    if doc.get("rolle_id"):
        rolle = await db.rollen.find_one({"_id": doc["rolle_id"]})
        doc["rolle_name"] = rolle.get("name") if rolle else None
    
    # Abteilungen laden
    abteilungen = []
    for abt_id in doc.get("abteilung_ids", []):
        abt = await db.abteilungen.find_one({"_id": abt_id})
        if abt:
            abteilungen.append({"id": abt["_id"], "name": abt.get("name")})
    doc["abteilungen"] = abteilungen
    
    return {"success": True, "data": doc}


@router.post("/benutzer")
async def create_benutzer(data: BenutzerCreate, user = Depends(require_admin)):
    """Neuen Benutzer anlegen"""
    db = get_db()
    
    # Prüfen ob Benutzername bereits existiert
    existing = await db.benutzer.find_one({
        "mandant_id": user["mandant_id"],
        "$or": [
            {"benutzername": data.benutzername},
            {"email": data.email}
        ]
    })
    
    if existing:
        if existing.get("benutzername") == data.benutzername:
            raise HTTPException(status_code=400, detail="Benutzername bereits vergeben")
        raise HTTPException(status_code=400, detail="E-Mail bereits registriert")
    
    benutzer_id = str(uuid.uuid4())
    
    benutzer = {
        "_id": benutzer_id,
        "mandant_id": user["mandant_id"],
        "benutzername": data.benutzername,
        "email": data.email,
        "passwort_hash": hash_password(data.passwort),
        "vorname": data.vorname,
        "nachname": data.nachname,
        "kuerzel": data.kuerzel or data.benutzername[:3].upper(),
        "rolle_id": data.rolle_id,
        "abteilung_ids": data.abteilung_ids,
        "ist_admin": False,
        "aktiv": data.aktiv,
        "profilbild_url": None,
        "created_at": datetime.utcnow().isoformat(),
    }
    
    await db.benutzer.insert_one(benutzer)
    
    # Passwort-Hash nicht zurückgeben
    del benutzer["passwort_hash"]
    benutzer["id"] = benutzer.pop("_id")
    
    return {"success": True, "data": benutzer}


@router.put("/benutzer/{benutzer_id}")
async def update_benutzer(
    benutzer_id: str, 
    data: BenutzerUpdate, 
    user = Depends(require_admin)
):
    """Benutzer aktualisieren"""
    db = get_db()
    
    # Prüfen ob Benutzer existiert
    benutzer = await db.benutzer.find_one({
        "_id": benutzer_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not benutzer:
        raise HTTPException(status_code=404, detail="Benutzer nicht gefunden")
    
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    
    # E-Mail-Eindeutigkeit prüfen
    if data.email and data.email != benutzer.get("email"):
        existing = await db.benutzer.find_one({
            "mandant_id": user["mandant_id"],
            "email": data.email,
            "_id": {"$ne": benutzer_id}
        })
        if existing:
            raise HTTPException(status_code=400, detail="E-Mail bereits registriert")
    
    # Admin-Rolle synchronisieren
    if data.rolle_id:
        rolle = await db.rollen.find_one({"_id": data.rolle_id})
        if rolle and rolle.get("name") == "Administrator":
            update_data["ist_admin"] = True
        else:
            # Nur setzen wenn nicht der einzige Admin
            if benutzer.get("ist_admin"):
                admin_count = await db.benutzer.count_documents({
                    "mandant_id": user["mandant_id"],
                    "ist_admin": True
                })
                if admin_count <= 1:
                    raise HTTPException(
                        status_code=400, 
                        detail="Es muss mindestens ein Administrator existieren"
                    )
            update_data["ist_admin"] = False
    
    update_data["updated_at"] = datetime.utcnow().isoformat()
    
    await db.benutzer.update_one(
        {"_id": benutzer_id, "mandant_id": user["mandant_id"]},
        {"$set": update_data}
    )
    
    return {"success": True}


@router.post("/benutzer/{benutzer_id}/passwort-reset")
async def reset_benutzer_passwort(
    benutzer_id: str,
    data: PasswortReset,
    user = Depends(require_admin)
):
    """Passwort eines Benutzers zurücksetzen"""
    db = get_db()
    
    result = await db.benutzer.update_one(
        {"_id": benutzer_id, "mandant_id": user["mandant_id"]},
        {"$set": {
            "passwort_hash": hash_password(data.neues_passwort),
            "updated_at": datetime.utcnow().isoformat()
        }}
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Benutzer nicht gefunden")
    
    return {"success": True, "message": "Passwort zurückgesetzt"}


@router.delete("/benutzer/{benutzer_id}")
async def delete_benutzer(benutzer_id: str, user = Depends(require_admin)):
    """Benutzer löschen"""
    db = get_db()
    
    benutzer = await db.benutzer.find_one({
        "_id": benutzer_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not benutzer:
        raise HTTPException(status_code=404, detail="Benutzer nicht gefunden")
    
    # Kann sich nicht selbst löschen
    if benutzer_id == user["id"]:
        raise HTTPException(status_code=400, detail="Sie können sich nicht selbst löschen")
    
    # Prüfen ob letzter Admin
    if benutzer.get("ist_admin"):
        admin_count = await db.benutzer.count_documents({
            "mandant_id": user["mandant_id"],
            "ist_admin": True
        })
        if admin_count <= 1:
            raise HTTPException(
                status_code=400, 
                detail="Der letzte Administrator kann nicht gelöscht werden"
            )
    
    await db.benutzer.delete_one({"_id": benutzer_id, "mandant_id": user["mandant_id"]})
    
    # Benutzer-spezifische Berechtigungen löschen
    await db.berechtigungen.delete_many({
        "mandant_id": user["mandant_id"],
        "ziel_typ": "benutzer",
        "ziel_id": benutzer_id
    })
    
    return {"success": True}


# ============================================================
# BERECHTIGUNGEN ENDPOINTS
# ============================================================

@router.get("/module")
async def get_module(user = Depends(require_admin)):
    """Liste aller verfügbaren Module für Berechtigungen"""
    return {"success": True, "data": VERFUEGBARE_MODULE}


@router.get("/berechtigungen")
async def list_berechtigungen(
    modul: Optional[str] = None,
    ziel_typ: Optional[str] = None,
    ziel_id: Optional[str] = None,
    user = Depends(require_admin)
):
    """Liste aller Berechtigungen"""
    db = get_db()
    
    query = {"mandant_id": user["mandant_id"]}
    
    if modul:
        query["modul"] = modul
    if ziel_typ:
        query["ziel_typ"] = ziel_typ
    if ziel_id:
        query["ziel_id"] = ziel_id
    
    cursor = db.berechtigungen.find(query)
    
    items = []
    async for doc in cursor:
        doc["id"] = doc.pop("_id")
        
        # Ziel-Name laden
        if doc["ziel_typ"] == "rolle":
            ziel = await db.rollen.find_one({"_id": doc["ziel_id"]})
            doc["ziel_name"] = ziel.get("name") if ziel else None
        elif doc["ziel_typ"] == "abteilung":
            ziel = await db.abteilungen.find_one({"_id": doc["ziel_id"]})
            doc["ziel_name"] = ziel.get("name") if ziel else None
        elif doc["ziel_typ"] == "benutzer":
            ziel = await db.benutzer.find_one({"_id": doc["ziel_id"]})
            doc["ziel_name"] = ziel.get("benutzername") if ziel else None
        
        items.append(doc)
    
    return {"success": True, "data": items}


@router.get("/berechtigungen/matrix")
async def get_berechtigungen_matrix(user = Depends(require_admin)):
    """Vollständige Berechtigungsmatrix für UI"""
    db = get_db()
    
    # Alle Rollen, Abteilungen und Berechtigungen laden
    rollen = []
    async for r in db.rollen.find({"mandant_id": user["mandant_id"]}):
        rollen.append({"id": r["_id"], "name": r["name"], "farbe": r.get("farbe")})
    
    abteilungen = []
    async for a in db.abteilungen.find({"mandant_id": user["mandant_id"]}):
        abteilungen.append({"id": a["_id"], "name": a["name"]})
    
    berechtigungen = []
    async for b in db.berechtigungen.find({"mandant_id": user["mandant_id"]}):
        berechtigungen.append({
            "id": b["_id"],
            "modul": b["modul"],
            "ziel_typ": b["ziel_typ"],
            "ziel_id": b["ziel_id"],
            "level": b["level"]
        })
    
    return {
        "success": True,
        "data": {
            "module": VERFUEGBARE_MODULE,
            "rollen": rollen,
            "abteilungen": abteilungen,
            "berechtigungen": berechtigungen,
            "levels": BERECHTIGUNG_LEVELS
        }
    }


@router.post("/berechtigungen")
async def create_or_update_berechtigung(data: BerechtigungCreate, user = Depends(require_admin)):
    """Berechtigung erstellen oder aktualisieren"""
    db = get_db()
    
    # Validierung
    if data.ziel_typ not in ["rolle", "abteilung", "benutzer"]:
        raise HTTPException(status_code=400, detail="Ungültiger Ziel-Typ")
    
    if data.level not in BERECHTIGUNG_LEVELS:
        raise HTTPException(status_code=400, detail="Ungültiges Berechtigungs-Level")
    
    # Prüfen ob Modul gültig
    valid_modules = [m["key"] for m in VERFUEGBARE_MODULE]
    if data.modul not in valid_modules:
        raise HTTPException(status_code=400, detail="Ungültiges Modul")
    
    # Prüfen ob bereits existiert -> Update
    existing = await db.berechtigungen.find_one({
        "mandant_id": user["mandant_id"],
        "modul": data.modul,
        "ziel_typ": data.ziel_typ,
        "ziel_id": data.ziel_id
    })
    
    if existing:
        await db.berechtigungen.update_one(
            {"_id": existing["_id"]},
            {"$set": {"level": data.level, "updated_at": datetime.utcnow().isoformat()}}
        )
        return {"success": True, "message": "Berechtigung aktualisiert"}
    
    # Neu erstellen
    ber_id = "BER-" + str(uuid.uuid4())[:8].upper()
    
    berechtigung = {
        "_id": ber_id,
        "mandant_id": user["mandant_id"],
        "modul": data.modul,
        "ziel_typ": data.ziel_typ,
        "ziel_id": data.ziel_id,
        "level": data.level,
        "created_at": datetime.utcnow().isoformat(),
    }
    
    await db.berechtigungen.insert_one(berechtigung)
    
    berechtigung["id"] = berechtigung.pop("_id")
    return {"success": True, "data": berechtigung}


@router.delete("/berechtigungen/{ber_id}")
async def delete_berechtigung(ber_id: str, user = Depends(require_admin)):
    """Berechtigung löschen"""
    db = get_db()
    
    result = await db.berechtigungen.delete_one({
        "_id": ber_id,
        "mandant_id": user["mandant_id"]
    })
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="Berechtigung nicht gefunden")
    
    return {"success": True}


# ============================================================
# BERECHTIGUNGSPRÜFUNG (für andere Router)
# ============================================================

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
        return {m["key"]: "full" for m in VERFUEGBARE_MODULE}
    
    permissions = {}
    
    # 1. Abteilungs-Berechtigungen (niedrigste Priorität, werden kombiniert)
    for abt_id in benutzer.get("abteilung_ids", []):
        cursor = db.berechtigungen.find({
            "mandant_id": mandant_id,
            "ziel_typ": "abteilung",
            "ziel_id": abt_id
        })
        async for ber in cursor:
            modul = ber["modul"]
            level = ber["level"]
            
            # Bei mehreren Abteilungen: höchstes Level gewinnt (außer denied)
            if modul not in permissions:
                permissions[modul] = level
            elif level == "denied":
                # denied überschreibt nur wenn explizit gesetzt
                pass
            elif BERECHTIGUNG_LEVELS.index(level) > BERECHTIGUNG_LEVELS.index(permissions[modul]):
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


@router.get("/meine-berechtigungen")
async def get_my_permissions(user = Depends(get_current_user)):
    """Berechtigungen des aktuellen Benutzers abrufen"""
    permissions = await get_user_permissions(user["id"], user["mandant_id"])
    
    return {
        "success": True,
        "data": {
            "ist_admin": user.get("ist_admin", False),
            "berechtigungen": permissions
        }
    }


# ============================================================
# INITIALISIERUNG (System-Rollen/Daten anlegen)
# ============================================================

@router.post("/init-system")
async def init_system_data(user = Depends(require_admin)):
    """Initialisiert System-Rollen und -Daten falls nicht vorhanden"""
    db = get_db()
    mandant_id = user["mandant_id"]
    
    created = {"rollen": 0, "abteilungen": 0}
    
    # System-Rolle "Administrator" anlegen
    admin_rolle = await db.rollen.find_one({
        "mandant_id": mandant_id,
        "name": "Administrator"
    })
    
    if not admin_rolle:
        await db.rollen.insert_one({
            "_id": "ROL-ADMIN",
            "mandant_id": mandant_id,
            "name": "Administrator",
            "beschreibung": "Vollzugriff auf alle Funktionen",
            "farbe": "#DC2626",
            "ist_system": True,
            "created_at": datetime.utcnow().isoformat(),
        })
        created["rollen"] += 1
    
    # Standard-Rollen anlegen
    standard_rollen = [
        {"name": "Sachbearbeiter", "farbe": "#2563EB", "beschreibung": "Standard-Benutzerrolle"},
        {"name": "Einkäufer", "farbe": "#059669", "beschreibung": "Einkaufs-Mitarbeiter"},
        {"name": "Verkäufer", "farbe": "#7C3AED", "beschreibung": "Vertriebs-Mitarbeiter"},
        {"name": "Buchhalter", "farbe": "#D97706", "beschreibung": "Buchhaltung"},
        {"name": "Lager", "farbe": "#0891B2", "beschreibung": "Lagermitarbeiter"},
    ]
    
    for rolle in standard_rollen:
        existing = await db.rollen.find_one({
            "mandant_id": mandant_id,
            "name": rolle["name"]
        })
        if not existing:
            await db.rollen.insert_one({
                "_id": "ROL-" + str(uuid.uuid4())[:8].upper(),
                "mandant_id": mandant_id,
                "ist_system": False,
                "created_at": datetime.utcnow().isoformat(),
                **rolle
            })
            created["rollen"] += 1
    
    # Standard-Abteilungen anlegen
    standard_abteilungen = [
        {"name": "Geschäftsführung", "kuerzel": "GF"},
        {"name": "Einkauf", "kuerzel": "EK"},
        {"name": "Verkauf", "kuerzel": "VK"},
        {"name": "Disposition", "kuerzel": "DIS"},
        {"name": "Buchhaltung", "kuerzel": "BH"},
        {"name": "Lager", "kuerzel": "LAG"},
    ]
    
    for abt in standard_abteilungen:
        existing = await db.abteilungen.find_one({
            "mandant_id": mandant_id,
            "name": abt["name"]
        })
        if not existing:
            await db.abteilungen.insert_one({
                "_id": "ABT-" + str(uuid.uuid4())[:8].upper(),
                "mandant_id": mandant_id,
                "created_at": datetime.utcnow().isoformat(),
                **abt
            })
            created["abteilungen"] += 1
    
    # Admin-Benutzer mit Administrator-Rolle verknüpfen
    await db.benutzer.update_one(
        {"mandant_id": mandant_id, "ist_admin": True},
        {"$set": {"rolle_id": "ROL-ADMIN"}}
    )
    
    return {
        "success": True,
        "message": "System initialisiert",
        "created": created
    }
