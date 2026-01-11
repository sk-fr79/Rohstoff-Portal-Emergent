"""
API-Konfiguration & Referenztabellen-System
Router für externe API-Verwaltung und Referenzdaten
"""

from fastapi import APIRouter, HTTPException, Depends, Query
from pydantic import BaseModel, Field, validator
from typing import Optional, List, Dict, Any, Literal
from datetime import datetime
from enum import Enum
import uuid
import httpx
import json
import re
import base64
from cryptography.fernet import Fernet
from cryptography.hazmat.primitives import hashes
from cryptography.hazmat.primitives.kdf.pbkdf2 import PBKDF2HMAC
import os

from services.database import get_db
from utils.auth import get_current_user

router = APIRouter(prefix="/api/system", tags=["API-Konfiguration"])


# ============================================================
# ENCRYPTION UTILITIES
# ============================================================

def get_encryption_key() -> bytes:
    """Generiert einen Verschlüsselungsschlüssel basierend auf einem Secret"""
    secret = os.environ.get("API_ENCRYPTION_SECRET", "rohstoff-portal-default-secret-key-32")
    salt = b'rohstoff_portal_salt'
    kdf = PBKDF2HMAC(
        algorithm=hashes.SHA256(),
        length=32,
        salt=salt,
        iterations=480000,
    )
    key = base64.urlsafe_b64encode(kdf.derive(secret.encode()))
    return key


def encrypt_credentials(data: dict) -> str:
    """Verschlüsselt Credentials mit Fernet (AES-256)"""
    if not data:
        return ""
    key = get_encryption_key()
    f = Fernet(key)
    json_data = json.dumps(data)
    encrypted = f.encrypt(json_data.encode())
    return encrypted.decode()


def decrypt_credentials(encrypted_data: str) -> dict:
    """Entschlüsselt Credentials"""
    if not encrypted_data:
        return {}
    try:
        key = get_encryption_key()
        f = Fernet(key)
        decrypted = f.decrypt(encrypted_data.encode())
        return json.loads(decrypted.decode())
    except Exception:
        return {}


# ============================================================
# HELPER: Admin-Check
# ============================================================

async def require_admin(user = Depends(get_current_user)):
    """Stellt sicher, dass nur Admins Zugriff haben"""
    if not user.get("ist_admin", False):
        raise HTTPException(status_code=403, detail="Nur Administratoren haben Zugriff")
    return user


# ============================================================
# ENUMS & CONSTANTS
# ============================================================

class ApiType(str, Enum):
    REST = "REST"
    GRAPHQL = "GraphQL"
    SOAP = "SOAP"


class AuthType(str, Enum):
    NONE = "none"
    API_KEY = "api_key"
    BEARER = "bearer"
    BASIC = "basic"
    OAUTH2 = "oauth2"


class HttpMethod(str, Enum):
    GET = "GET"
    POST = "POST"
    PUT = "PUT"
    DELETE = "DELETE"


class UpdateStrategy(str, Enum):
    REPLACE = "replace"
    APPEND = "append"
    MERGE = "merge"


class SyncStatus(str, Enum):
    PENDING = "pending"
    RUNNING = "running"
    SUCCESS = "success"
    ERROR = "error"


# ============================================================
# PYDANTIC MODELS
# ============================================================

class AuthConfig(BaseModel):
    """Authentifizierungs-Konfiguration"""
    api_key: Optional[str] = None
    api_key_name: Optional[str] = Field(None, description="Header/Query-Parameter Name")
    api_key_location: Optional[Literal["header", "query"]] = "header"
    bearer_token: Optional[str] = None
    basic_username: Optional[str] = None
    basic_password: Optional[str] = None
    oauth2_client_id: Optional[str] = None
    oauth2_client_secret: Optional[str] = None
    oauth2_token_url: Optional[str] = None


class RequestConfig(BaseModel):
    """Request-Konfiguration"""
    method: HttpMethod = HttpMethod.GET
    headers: Dict[str, str] = Field(default_factory=dict)
    query_params: Dict[str, str] = Field(default_factory=dict)
    body_template: Optional[str] = None


class FieldMapping(BaseModel):
    """Einzelnes Feld-Mapping"""
    source_path: str = Field(..., description="JSON-Pfad im Response (z.B. 'code')")
    target_field: str = Field(..., description="Interner Feldname")
    is_primary_key: bool = False
    data_type: Literal["string", "number", "boolean", "date"] = "string"


class ResponseMapping(BaseModel):
    """Response-Mapping Konfiguration"""
    data_path: str = Field("", description="Pfad zu den Daten im Response (z.B. 'data.items', 'suggestions')")
    field_mappings: List[FieldMapping] = Field(default_factory=list)


class ReferenceTableConfig(BaseModel):
    """Referenztabellen-Konfiguration"""
    enabled: bool = False
    table_name: str = Field("", description="Technischer Tabellenname (z.B. 'ref_zolltarif')")
    display_name: str = Field("", description="Anzeigename")
    update_strategy: UpdateStrategy = UpdateStrategy.MERGE
    track_history: bool = False


class ApiConfigCreate(BaseModel):
    """API-Konfiguration erstellen"""
    name: str = Field(..., min_length=1, max_length=255)
    description: Optional[str] = None
    api_type: ApiType = ApiType.REST
    base_url: str = Field(..., min_length=1)
    auth_type: AuthType = AuthType.NONE
    auth_config: Optional[AuthConfig] = None
    request_config: Optional[RequestConfig] = None
    response_mapping: Optional[ResponseMapping] = None
    reference_table: Optional[ReferenceTableConfig] = None
    is_active: bool = True

    @validator('base_url')
    def validate_url(cls, v):
        if not v.startswith(('http://', 'https://')):
            raise ValueError('URL muss mit http:// oder https:// beginnen')
        return v.rstrip('/')


class ApiConfigUpdate(BaseModel):
    """API-Konfiguration aktualisieren"""
    name: Optional[str] = None
    description: Optional[str] = None
    api_type: Optional[ApiType] = None
    base_url: Optional[str] = None
    auth_type: Optional[AuthType] = None
    auth_config: Optional[AuthConfig] = None
    request_config: Optional[RequestConfig] = None
    response_mapping: Optional[ResponseMapping] = None
    reference_table: Optional[ReferenceTableConfig] = None
    is_active: Optional[bool] = None


class ApiTestRequest(BaseModel):
    """Request für API-Test"""
    test_params: Dict[str, str] = Field(default_factory=dict, description="Test-Parameter für den Request")


class ReferenceDataUpdate(BaseModel):
    """Referenzdaten aktualisieren"""
    data: Dict[str, Any] = Field(..., description="Die zu speichernden Daten")


# ============================================================
# API CONFIGURATION ENDPOINTS
# ============================================================

@router.get("/apis")
async def list_api_configs(
    is_active: Optional[bool] = None,
    search: Optional[str] = None,
    user = Depends(require_admin)
):
    """Liste aller API-Konfigurationen"""
    db = get_db()
    
    query = {"mandant_id": user["mandant_id"]}
    
    if is_active is not None:
        query["is_active"] = is_active
    
    if search:
        query["$or"] = [
            {"name": {"$regex": search, "$options": "i"}},
            {"description": {"$regex": search, "$options": "i"}},
            {"base_url": {"$regex": search, "$options": "i"}},
        ]
    
    cursor = db.system_api_configs.find(query).sort("name", 1)
    
    items = []
    async for doc in cursor:
        doc["id"] = doc.pop("_id")
        # Credentials nicht zurückgeben
        doc.pop("auth_config_encrypted", None)
        
        # Referenztabellen-Info laden
        if doc.get("reference_table", {}).get("enabled"):
            ref_table = await db.system_reference_tables.find_one({
                "api_config_id": doc["id"]
            })
            if ref_table:
                doc["reference_table_info"] = {
                    "id": ref_table["_id"],
                    "record_count": ref_table.get("record_count", 0),
                    "last_updated_at": ref_table.get("last_updated_at")
                }
        
        items.append(doc)
    
    return {"success": True, "data": items}


@router.get("/apis/{api_id}")
async def get_api_config(api_id: str, user = Depends(require_admin)):
    """Einzelne API-Konfiguration abrufen"""
    db = get_db()
    
    doc = await db.system_api_configs.find_one({
        "_id": api_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not doc:
        raise HTTPException(status_code=404, detail="API-Konfiguration nicht gefunden")
    
    doc["id"] = doc.pop("_id")
    
    # Auth-Config entschlüsseln (aber sensible Daten maskieren)
    if doc.get("auth_config_encrypted"):
        auth_config = decrypt_credentials(doc["auth_config_encrypted"])
        # Sensible Daten maskieren
        if auth_config.get("api_key"):
            auth_config["api_key"] = "***" + auth_config["api_key"][-4:] if len(auth_config["api_key"]) > 4 else "****"
        if auth_config.get("bearer_token"):
            auth_config["bearer_token"] = "***" + auth_config["bearer_token"][-4:] if len(auth_config["bearer_token"]) > 4 else "****"
        if auth_config.get("basic_password"):
            auth_config["basic_password"] = "****"
        if auth_config.get("oauth2_client_secret"):
            auth_config["oauth2_client_secret"] = "****"
        doc["auth_config"] = auth_config
    
    doc.pop("auth_config_encrypted", None)
    
    return {"success": True, "data": doc}


@router.post("/apis")
async def create_api_config(data: ApiConfigCreate, user = Depends(require_admin)):
    """Neue API-Konfiguration erstellen"""
    db = get_db()
    
    # Slug generieren
    slug = re.sub(r'[^a-z0-9]+', '-', data.name.lower()).strip('-')
    
    # Prüfen ob Name bereits existiert
    existing = await db.system_api_configs.find_one({
        "mandant_id": user["mandant_id"],
        "$or": [
            {"name": {"$regex": f"^{re.escape(data.name)}$", "$options": "i"}},
            {"slug": slug}
        ]
    })
    if existing:
        raise HTTPException(status_code=400, detail="API mit diesem Namen existiert bereits")
    
    api_id = "API-" + str(uuid.uuid4())[:8].upper()
    
    # Auth-Config verschlüsseln
    auth_config_encrypted = ""
    if data.auth_config:
        auth_config_encrypted = encrypt_credentials(data.auth_config.model_dump())
    
    api_config = {
        "_id": api_id,
        "mandant_id": user["mandant_id"],
        "name": data.name,
        "slug": slug,
        "description": data.description,
        "api_type": data.api_type.value,
        "base_url": data.base_url,
        "auth_type": data.auth_type.value,
        "auth_config_encrypted": auth_config_encrypted,
        "request_config": data.request_config.model_dump() if data.request_config else None,
        "response_mapping": data.response_mapping.model_dump() if data.response_mapping else None,
        "reference_table": data.reference_table.model_dump() if data.reference_table else None,
        "is_active": data.is_active,
        "last_sync_at": None,
        "last_sync_status": None,
        "last_sync_message": None,
        "created_at": datetime.utcnow().isoformat(),
        "created_by": user["id"],
    }
    
    await db.system_api_configs.insert_one(api_config)
    
    # Referenztabelle erstellen wenn aktiviert
    if data.reference_table and data.reference_table.enabled:
        ref_table_id = "REF-" + str(uuid.uuid4())[:8].upper()
        ref_table = {
            "_id": ref_table_id,
            "mandant_id": user["mandant_id"],
            "api_config_id": api_id,
            "table_name": data.reference_table.table_name,
            "display_name": data.reference_table.display_name,
            "update_strategy": data.reference_table.update_strategy.value,
            "track_history": data.reference_table.track_history,
            "columns_config": [fm.model_dump() for fm in data.response_mapping.field_mappings] if data.response_mapping else [],
            "record_count": 0,
            "last_updated_at": None,
            "created_at": datetime.utcnow().isoformat(),
        }
        await db.system_reference_tables.insert_one(ref_table)
    
    api_config["id"] = api_config.pop("_id")
    api_config.pop("auth_config_encrypted", None)
    
    return {"success": True, "data": api_config}


@router.put("/apis/{api_id}")
async def update_api_config(api_id: str, data: ApiConfigUpdate, user = Depends(require_admin)):
    """API-Konfiguration aktualisieren"""
    db = get_db()
    
    existing = await db.system_api_configs.find_one({
        "_id": api_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not existing:
        raise HTTPException(status_code=404, detail="API-Konfiguration nicht gefunden")
    
    update_data = {}
    
    for field, value in data.model_dump(exclude_unset=True).items():
        if value is not None:
            if field == "auth_config":
                # Nur aktualisieren wenn nicht maskiert
                if value.get("api_key") and not value["api_key"].startswith("***"):
                    update_data["auth_config_encrypted"] = encrypt_credentials(value)
                elif value.get("bearer_token") and not value["bearer_token"].startswith("***"):
                    update_data["auth_config_encrypted"] = encrypt_credentials(value)
                else:
                    # Bestehende Credentials behalten, nur andere Felder updaten
                    existing_auth = decrypt_credentials(existing.get("auth_config_encrypted", ""))
                    merged = {**existing_auth, **{k: v for k, v in value.items() if v and not str(v).startswith("***")}}
                    update_data["auth_config_encrypted"] = encrypt_credentials(merged)
            elif field == "request_config" and value:
                update_data["request_config"] = value if isinstance(value, dict) else value.model_dump()
            elif field == "response_mapping" and value:
                update_data["response_mapping"] = value if isinstance(value, dict) else value.model_dump()
            elif field == "reference_table" and value:
                update_data["reference_table"] = value if isinstance(value, dict) else value.model_dump()
            elif field in ["api_type", "auth_type"]:
                update_data[field] = value.value if hasattr(value, 'value') else value
            else:
                update_data[field] = value
    
    if update_data:
        update_data["updated_at"] = datetime.utcnow().isoformat()
        update_data["updated_by"] = user["id"]
        
        await db.system_api_configs.update_one(
            {"_id": api_id},
            {"$set": update_data}
        )
        
        # Referenztabelle aktualisieren wenn nötig
        if data.reference_table:
            ref_table = await db.system_reference_tables.find_one({"api_config_id": api_id})
            if data.reference_table.enabled:
                ref_update = {
                    "table_name": data.reference_table.table_name,
                    "display_name": data.reference_table.display_name,
                    "update_strategy": data.reference_table.update_strategy.value if hasattr(data.reference_table.update_strategy, 'value') else data.reference_table.update_strategy,
                    "track_history": data.reference_table.track_history,
                    "updated_at": datetime.utcnow().isoformat(),
                }
                
                if ref_table:
                    await db.system_reference_tables.update_one(
                        {"_id": ref_table["_id"]},
                        {"$set": ref_update}
                    )
                else:
                    # Neue Referenztabelle erstellen
                    ref_table_id = "REF-" + str(uuid.uuid4())[:8].upper()
                    ref_update["_id"] = ref_table_id
                    ref_update["mandant_id"] = user["mandant_id"]
                    ref_update["api_config_id"] = api_id
                    ref_update["record_count"] = 0
                    ref_update["created_at"] = datetime.utcnow().isoformat()
                    await db.system_reference_tables.insert_one(ref_update)
    
    return {"success": True, "message": "API-Konfiguration aktualisiert"}


@router.delete("/apis/{api_id}")
async def delete_api_config(api_id: str, user = Depends(require_admin)):
    """API-Konfiguration löschen"""
    db = get_db()
    
    existing = await db.system_api_configs.find_one({
        "_id": api_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not existing:
        raise HTTPException(status_code=404, detail="API-Konfiguration nicht gefunden")
    
    # Referenztabelle und Daten löschen
    ref_table = await db.system_reference_tables.find_one({"api_config_id": api_id})
    if ref_table:
        await db.system_reference_data.delete_many({"reference_table_id": ref_table["_id"]})
        await db.system_reference_tables.delete_one({"_id": ref_table["_id"]})
    
    # Sync-Logs löschen
    await db.system_api_sync_log.delete_many({"api_config_id": api_id})
    
    # API-Config löschen
    await db.system_api_configs.delete_one({"_id": api_id})
    
    return {"success": True, "message": "API-Konfiguration gelöscht"}


# ============================================================
# API TEST & SYNC ENDPOINTS
# ============================================================

@router.post("/apis/{api_id}/test")
async def test_api_connection(
    api_id: str, 
    test_request: ApiTestRequest,
    user = Depends(require_admin)
):
    """API-Verbindung testen und Response anzeigen"""
    db = get_db()
    
    api_config = await db.system_api_configs.find_one({
        "_id": api_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not api_config:
        raise HTTPException(status_code=404, detail="API-Konfiguration nicht gefunden")
    
    # Auth-Config entschlüsseln
    auth_config = decrypt_credentials(api_config.get("auth_config_encrypted", ""))
    request_config = api_config.get("request_config", {}) or {}
    
    # URL bauen
    url = api_config["base_url"]
    
    # Headers vorbereiten
    headers = dict(request_config.get("headers", {}))
    
    # Auth hinzufügen
    auth_type = api_config.get("auth_type", "none")
    if auth_type == "api_key" and auth_config.get("api_key"):
        key_name = auth_config.get("api_key_name", "X-API-Key")
        key_location = auth_config.get("api_key_location", "header")
        if key_location == "header":
            headers[key_name] = auth_config["api_key"]
    elif auth_type == "bearer" and auth_config.get("bearer_token"):
        headers["Authorization"] = f"Bearer {auth_config['bearer_token']}"
    elif auth_type == "basic":
        import base64
        credentials = f"{auth_config.get('basic_username', '')}:{auth_config.get('basic_password', '')}"
        encoded = base64.b64encode(credentials.encode()).decode()
        headers["Authorization"] = f"Basic {encoded}"
    
    # Query-Parameter
    params = dict(request_config.get("query_params", {}))
    params.update(test_request.test_params)
    
    # Request ausführen
    try:
        async with httpx.AsyncClient(timeout=30.0, follow_redirects=True) as client:
            method = request_config.get("method", "GET")
            
            if method == "GET":
                response = await client.get(url, headers=headers, params=params)
            elif method == "POST":
                body = request_config.get("body_template")
                response = await client.post(url, headers=headers, params=params, json=json.loads(body) if body else None)
            else:
                response = await client.request(method, url, headers=headers, params=params)
            
            # Response parsen
            try:
                response_data = response.json()
            except:
                response_data = {"raw": response.text[:2000]}
            
            # Response-Mapping anwenden wenn konfiguriert
            mapped_data = None
            response_mapping = api_config.get("response_mapping")
            if response_mapping and response_mapping.get("data_path"):
                data_path = response_mapping["data_path"]
                extracted = response_data
                for key in data_path.split("."):
                    if isinstance(extracted, dict):
                        extracted = extracted.get(key, {})
                    elif isinstance(extracted, list) and key.isdigit():
                        extracted = extracted[int(key)] if int(key) < len(extracted) else {}
                
                if isinstance(extracted, list) and len(extracted) > 0:
                    # Mapping auf ersten Eintrag anwenden
                    sample = extracted[0] if extracted else {}
                    mapped_sample = {}
                    for field_map in response_mapping.get("field_mappings", []):
                        source = field_map.get("source_path", "")
                        target = field_map.get("target_field", source)
                        value = sample.get(source)
                        mapped_sample[target] = value
                    
                    mapped_data = {
                        "total_records": len(extracted),
                        "sample_mapped": mapped_sample,
                        "sample_raw": sample
                    }
            
            return {
                "success": True,
                "data": {
                    "status_code": response.status_code,
                    "headers": dict(response.headers),
                    "response": response_data,
                    "mapped_preview": mapped_data,
                    "request_info": {
                        "url": str(response.url),
                        "method": method,
                    }
                }
            }
            
    except httpx.TimeoutException:
        return {
            "success": False,
            "error": "Timeout - API antwortet nicht innerhalb von 30 Sekunden"
        }
    except httpx.RequestError as e:
        return {
            "success": False,
            "error": f"Verbindungsfehler: {str(e)}"
        }
    except Exception as e:
        return {
            "success": False,
            "error": f"Fehler: {str(e)}"
        }


@router.post("/apis/{api_id}/sync")
async def sync_api_data(api_id: str, user = Depends(require_admin)):
    """Daten von API abrufen und in Referenztabelle speichern"""
    db = get_db()
    
    api_config = await db.system_api_configs.find_one({
        "_id": api_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not api_config:
        raise HTTPException(status_code=404, detail="API-Konfiguration nicht gefunden")
    
    ref_config = api_config.get("reference_table", {})
    if not ref_config or not ref_config.get("enabled"):
        raise HTTPException(status_code=400, detail="Keine Referenztabelle für diese API konfiguriert")
    
    # Sync-Log starten
    sync_log_id = "SYNC-" + str(uuid.uuid4())[:8].upper()
    sync_log = {
        "_id": sync_log_id,
        "mandant_id": user["mandant_id"],
        "api_config_id": api_id,
        "started_at": datetime.utcnow().isoformat(),
        "status": "running",
        "triggered_by": user["id"],
    }
    await db.system_api_sync_log.insert_one(sync_log)
    
    try:
        # API aufrufen
        auth_config = decrypt_credentials(api_config.get("auth_config_encrypted", ""))
        request_config = api_config.get("request_config", {}) or {}
        
        url = api_config["base_url"]
        headers = dict(request_config.get("headers", {}))
        params = dict(request_config.get("query_params", {}))
        
        # Auth
        auth_type = api_config.get("auth_type", "none")
        if auth_type == "api_key" and auth_config.get("api_key"):
            key_name = auth_config.get("api_key_name", "X-API-Key")
            if auth_config.get("api_key_location", "header") == "header":
                headers[key_name] = auth_config["api_key"]
            else:
                params[key_name] = auth_config["api_key"]
        elif auth_type == "bearer" and auth_config.get("bearer_token"):
            headers["Authorization"] = f"Bearer {auth_config['bearer_token']}"
        
        async with httpx.AsyncClient(timeout=60.0) as client:
            response = await client.get(url, headers=headers, params=params)
            response.raise_for_status()
            response_data = response.json()
        
        # Daten extrahieren
        response_mapping = api_config.get("response_mapping", {})
        data_path = response_mapping.get("data_path", "")
        
        extracted = response_data
        if data_path:
            for key in data_path.split("."):
                if isinstance(extracted, dict):
                    extracted = extracted.get(key, [])
        
        if not isinstance(extracted, list):
            extracted = [extracted] if extracted else []
        
        # Referenztabelle finden/erstellen
        ref_table = await db.system_reference_tables.find_one({"api_config_id": api_id})
        if not ref_table:
            ref_table_id = "REF-" + str(uuid.uuid4())[:8].upper()
            ref_table = {
                "_id": ref_table_id,
                "mandant_id": user["mandant_id"],
                "api_config_id": api_id,
                "table_name": ref_config.get("table_name", f"ref_{api_config['slug']}"),
                "display_name": ref_config.get("display_name", api_config["name"]),
                "update_strategy": ref_config.get("update_strategy", "merge"),
                "track_history": ref_config.get("track_history", False),
                "record_count": 0,
                "created_at": datetime.utcnow().isoformat(),
            }
            await db.system_reference_tables.insert_one(ref_table)
        
        ref_table_id = ref_table["_id"]
        field_mappings = response_mapping.get("field_mappings", [])
        
        # Primary Key finden
        pk_field = next((fm["source_path"] for fm in field_mappings if fm.get("is_primary_key")), None)
        
        # Update-Strategie
        strategy = ref_config.get("update_strategy", "merge")
        
        records_created = 0
        records_updated = 0
        
        if strategy == "replace":
            # Alle bestehenden Daten löschen
            await db.system_reference_data.delete_many({"reference_table_id": ref_table_id})
        
        for item in extracted:
            # Mapping anwenden
            mapped_item = {}
            for fm in field_mappings:
                source = fm.get("source_path", "")
                target = fm.get("target_field", source)
                value = item.get(source)
                mapped_item[target] = value
            
            external_id = str(item.get(pk_field, "")) if pk_field else str(uuid.uuid4())
            
            if strategy == "append":
                # Immer neu einfügen
                await db.system_reference_data.insert_one({
                    "_id": str(uuid.uuid4()),
                    "reference_table_id": ref_table_id,
                    "external_id": external_id,
                    "data": mapped_item,
                    "created_at": datetime.utcnow().isoformat(),
                })
                records_created += 1
            else:
                # Merge oder Replace: Upsert
                result = await db.system_reference_data.update_one(
                    {"reference_table_id": ref_table_id, "external_id": external_id},
                    {
                        "$set": {
                            "data": mapped_item,
                            "updated_at": datetime.utcnow().isoformat(),
                        },
                        "$setOnInsert": {
                            "_id": str(uuid.uuid4()),
                            "reference_table_id": ref_table_id,
                            "external_id": external_id,
                            "created_at": datetime.utcnow().isoformat(),
                        }
                    },
                    upsert=True
                )
                if result.upserted_id:
                    records_created += 1
                elif result.modified_count > 0:
                    records_updated += 1
        
        # Record-Count aktualisieren
        total_count = await db.system_reference_data.count_documents({"reference_table_id": ref_table_id})
        await db.system_reference_tables.update_one(
            {"_id": ref_table_id},
            {"$set": {
                "record_count": total_count,
                "last_updated_at": datetime.utcnow().isoformat()
            }}
        )
        
        # Sync-Log abschließen
        await db.system_api_sync_log.update_one(
            {"_id": sync_log_id},
            {"$set": {
                "completed_at": datetime.utcnow().isoformat(),
                "status": "success",
                "records_fetched": len(extracted),
                "records_created": records_created,
                "records_updated": records_updated,
            }}
        )
        
        # API-Config Status updaten
        await db.system_api_configs.update_one(
            {"_id": api_id},
            {"$set": {
                "last_sync_at": datetime.utcnow().isoformat(),
                "last_sync_status": "success",
                "last_sync_message": f"{len(extracted)} Datensätze synchronisiert"
            }}
        )
        
        return {
            "success": True,
            "data": {
                "records_fetched": len(extracted),
                "records_created": records_created,
                "records_updated": records_updated,
                "total_records": total_count
            }
        }
        
    except Exception as e:
        # Sync-Log mit Fehler abschließen
        await db.system_api_sync_log.update_one(
            {"_id": sync_log_id},
            {"$set": {
                "completed_at": datetime.utcnow().isoformat(),
                "status": "error",
                "error_message": str(e)
            }}
        )
        
        # API-Config Status updaten
        await db.system_api_configs.update_one(
            {"_id": api_id},
            {"$set": {
                "last_sync_at": datetime.utcnow().isoformat(),
                "last_sync_status": "error",
                "last_sync_message": str(e)
            }}
        )
        
        raise HTTPException(status_code=500, detail=f"Sync fehlgeschlagen: {str(e)}")


@router.get("/apis/{api_id}/logs")
async def get_api_sync_logs(
    api_id: str,
    limit: int = Query(20, ge=1, le=100),
    user = Depends(require_admin)
):
    """Sync-Historie für eine API abrufen"""
    db = get_db()
    
    cursor = db.system_api_sync_log.find({
        "mandant_id": user["mandant_id"],
        "api_config_id": api_id
    }).sort("started_at", -1).limit(limit)
    
    items = []
    async for doc in cursor:
        doc["id"] = doc.pop("_id")
        items.append(doc)
    
    return {"success": True, "data": items}


# ============================================================
# REFERENCE TABLES ENDPOINTS
# ============================================================

@router.get("/reference-tables")
async def list_reference_tables(
    search: Optional[str] = None,
    user = Depends(require_admin)
):
    """Liste aller Referenztabellen"""
    db = get_db()
    
    query = {"mandant_id": user["mandant_id"]}
    
    if search:
        query["$or"] = [
            {"table_name": {"$regex": search, "$options": "i"}},
            {"display_name": {"$regex": search, "$options": "i"}},
        ]
    
    cursor = db.system_reference_tables.find(query).sort("display_name", 1)
    
    items = []
    async for doc in cursor:
        doc["id"] = doc.pop("_id")
        
        # API-Name laden
        if doc.get("api_config_id"):
            api = await db.system_api_configs.find_one({"_id": doc["api_config_id"]})
            doc["api_name"] = api.get("name") if api else None
        else:
            doc["api_name"] = None
            doc["is_manual"] = True
        
        # Verwendung ermitteln (vereinfacht)
        doc["usage"] = ["Artikel", "Kontrakte"]  # TODO: Dynamisch ermitteln
        
        items.append(doc)
    
    return {"success": True, "data": items}


@router.get("/reference-tables/{table_id}")
async def get_reference_table(
    table_id: str,
    page: int = Query(1, ge=1),
    page_size: int = Query(50, ge=10, le=500),
    search: Optional[str] = None,
    user = Depends(require_admin)
):
    """Referenztabelle mit Daten abrufen"""
    db = get_db()
    
    ref_table = await db.system_reference_tables.find_one({
        "_id": table_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not ref_table:
        raise HTTPException(status_code=404, detail="Referenztabelle nicht gefunden")
    
    ref_table["id"] = ref_table.pop("_id")
    
    # Daten laden mit Pagination
    query = {"reference_table_id": table_id}
    
    if search:
        # Suche in JSONB data
        query["$or"] = [
            {f"data.{field}": {"$regex": search, "$options": "i"}}
            for field in ["code", "bezeichnung", "name", "description"]  # Häufige Felder
        ]
    
    total = await db.system_reference_data.count_documents(query)
    
    skip = (page - 1) * page_size
    cursor = db.system_reference_data.find(query).skip(skip).limit(page_size).sort("external_id", 1)
    
    data = []
    async for doc in cursor:
        item = doc.get("data", {})
        item["_id"] = doc["_id"]
        item["_external_id"] = doc.get("external_id")
        data.append(item)
    
    return {
        "success": True,
        "data": {
            "table": ref_table,
            "records": data,
            "pagination": {
                "page": page,
                "page_size": page_size,
                "total": total,
                "total_pages": (total + page_size - 1) // page_size
            }
        }
    }


@router.post("/reference-tables")
async def create_manual_reference_table(
    name: str = Query(..., min_length=1),
    display_name: str = Query(..., min_length=1),
    user = Depends(require_admin)
):
    """Manuelle Referenztabelle erstellen (ohne API)"""
    db = get_db()
    
    # Slug/Name prüfen
    table_name = re.sub(r'[^a-z0-9_]+', '_', name.lower()).strip('_')
    if not table_name.startswith("ref_"):
        table_name = f"ref_{table_name}"
    
    existing = await db.system_reference_tables.find_one({
        "mandant_id": user["mandant_id"],
        "table_name": table_name
    })
    
    if existing:
        raise HTTPException(status_code=400, detail="Tabelle mit diesem Namen existiert bereits")
    
    ref_table_id = "REF-" + str(uuid.uuid4())[:8].upper()
    ref_table = {
        "_id": ref_table_id,
        "mandant_id": user["mandant_id"],
        "api_config_id": None,  # Manuelle Tabelle
        "table_name": table_name,
        "display_name": display_name,
        "update_strategy": "merge",
        "track_history": False,
        "columns_config": [],
        "record_count": 0,
        "created_at": datetime.utcnow().isoformat(),
        "created_by": user["id"],
    }
    
    await db.system_reference_tables.insert_one(ref_table)
    
    ref_table["id"] = ref_table.pop("_id")
    return {"success": True, "data": ref_table}


@router.post("/reference-tables/{table_id}/data")
async def add_reference_data(
    table_id: str,
    data: ReferenceDataUpdate,
    user = Depends(require_admin)
):
    """Datensatz zu Referenztabelle hinzufügen"""
    db = get_db()
    
    ref_table = await db.system_reference_tables.find_one({
        "_id": table_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not ref_table:
        raise HTTPException(status_code=404, detail="Referenztabelle nicht gefunden")
    
    record_id = str(uuid.uuid4())
    external_id = data.data.get("code") or data.data.get("id") or record_id
    
    record = {
        "_id": record_id,
        "reference_table_id": table_id,
        "external_id": str(external_id),
        "data": data.data,
        "created_at": datetime.utcnow().isoformat(),
        "created_by": user["id"],
    }
    
    await db.system_reference_data.insert_one(record)
    
    # Count aktualisieren
    count = await db.system_reference_data.count_documents({"reference_table_id": table_id})
    await db.system_reference_tables.update_one(
        {"_id": table_id},
        {"$set": {"record_count": count, "last_updated_at": datetime.utcnow().isoformat()}}
    )
    
    return {"success": True, "data": {"id": record_id}}


@router.put("/reference-tables/{table_id}/data/{record_id}")
async def update_reference_data(
    table_id: str,
    record_id: str,
    data: ReferenceDataUpdate,
    user = Depends(require_admin)
):
    """Datensatz in Referenztabelle aktualisieren"""
    db = get_db()
    
    result = await db.system_reference_data.update_one(
        {"_id": record_id, "reference_table_id": table_id},
        {"$set": {
            "data": data.data,
            "updated_at": datetime.utcnow().isoformat(),
            "updated_by": user["id"],
        }}
    )
    
    if result.matched_count == 0:
        raise HTTPException(status_code=404, detail="Datensatz nicht gefunden")
    
    return {"success": True}


@router.delete("/reference-tables/{table_id}/data/{record_id}")
async def delete_reference_data(
    table_id: str,
    record_id: str,
    user = Depends(require_admin)
):
    """Datensatz aus Referenztabelle löschen"""
    db = get_db()
    
    result = await db.system_reference_data.delete_one({
        "_id": record_id,
        "reference_table_id": table_id
    })
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="Datensatz nicht gefunden")
    
    # Count aktualisieren
    count = await db.system_reference_data.count_documents({"reference_table_id": table_id})
    await db.system_reference_tables.update_one(
        {"_id": table_id},
        {"$set": {"record_count": count, "last_updated_at": datetime.utcnow().isoformat()}}
    )
    
    return {"success": True}


@router.delete("/reference-tables/{table_id}")
async def delete_reference_table(table_id: str, user = Depends(require_admin)):
    """Referenztabelle und alle Daten löschen"""
    db = get_db()
    
    ref_table = await db.system_reference_tables.find_one({
        "_id": table_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not ref_table:
        raise HTTPException(status_code=404, detail="Referenztabelle nicht gefunden")
    
    # Daten löschen
    await db.system_reference_data.delete_many({"reference_table_id": table_id})
    
    # Tabelle löschen
    await db.system_reference_tables.delete_one({"_id": table_id})
    
    # Falls mit API verknüpft, reference_table config deaktivieren
    if ref_table.get("api_config_id"):
        await db.system_api_configs.update_one(
            {"_id": ref_table["api_config_id"]},
            {"$set": {"reference_table.enabled": False}}
        )
    
    return {"success": True}


# ============================================================
# LOOKUP ENDPOINT (für ReferenceSelect Komponente)
# ============================================================

@router.get("/reference-lookup/{table_name}")
async def lookup_reference_data(
    table_name: str,
    search: Optional[str] = None,
    limit: int = Query(50, ge=1, le=200),
    user = Depends(get_current_user)  # Alle authentifizierten Benutzer
):
    """Schnelle Suche in Referenztabelle (für Autocomplete/Select)"""
    db = get_db()
    
    ref_table = await db.system_reference_tables.find_one({
        "mandant_id": user["mandant_id"],
        "table_name": table_name
    })
    
    if not ref_table:
        raise HTTPException(status_code=404, detail="Referenztabelle nicht gefunden")
    
    query = {"reference_table_id": ref_table["_id"]}
    
    if search:
        # Suche in typischen Feldern
        query["$or"] = [
            {"data.code": {"$regex": search, "$options": "i"}},
            {"data.bezeichnung": {"$regex": search, "$options": "i"}},
            {"data.name": {"$regex": search, "$options": "i"}},
            {"data.description": {"$regex": search, "$options": "i"}},
            {"external_id": {"$regex": search, "$options": "i"}},
        ]
    
    cursor = db.system_reference_data.find(query).limit(limit)
    
    items = []
    async for doc in cursor:
        items.append({
            "id": doc["_id"],
            "external_id": doc.get("external_id"),
            **doc.get("data", {})
        })
    
    return {"success": True, "data": items}
