"""
Adressen Router - CRUD und Validierung für Adressen
Enthält auch Ansprechpartner-Verwaltung, UST-ID-Prüfung und OCR
"""

from fastapi import APIRouter, HTTPException, Depends, status, UploadFile, File, Form
from pydantic import BaseModel, Field
from typing import Optional, List, Dict, Any
from datetime import datetime
import uuid
import base64
import httpx
import os
import re
import json

from services.database import get_db
from services.validation import AdresseValidator
from utils.auth import get_current_user, require_permission

router = APIRouter(prefix="/api", tags=["Adressen"])


# ============================================================
# FIRMA-SYNC HELPER
# ============================================================

async def sync_firma_wenn_firmenadresse(adresse_id: str, mandant_id: str):
    """
    Synchronisiert die Firmendaten, wenn die geänderte Adresse die Firmenadresse ist.
    Wird automatisch nach Adress-Updates aufgerufen.
    """
    db = get_db()
    
    # Prüfen ob diese Adresse die Firmenadresse ist
    firma = await db.firma.find_one({
        "mandant_id": mandant_id,
        "id_adresse": adresse_id
    })
    
    if not firma:
        return None  # Diese Adresse ist nicht die Firmenadresse
    
    # Aktuelle Adressdaten laden
    adresse = await db.adressen.find_one({"_id": adresse_id})
    if not adresse:
        return None
    
    # Firmendaten aus Adresse aktualisieren
    sync_data = {
        # Stammdaten
        "name1": adresse.get("name1"),
        "name2": adresse.get("name2"),
        
        # Adresse
        "strasse": adresse.get("strasse"),
        "hausnummer": adresse.get("hausnummer"),
        "plz": adresse.get("plz"),
        "ort": adresse.get("ort"),
        "land": adresse.get("land"),
        "land_code": adresse.get("land_code"),
        
        # Kontakt
        "telefon": adresse.get("telefon"),
        "telefax": adresse.get("telefax"),
        "email": adresse.get("email"),
        "website": adresse.get("webseite"),
        
        # Steuer
        "ust_id": adresse.get("umsatzsteuer_id") or adresse.get("ust_id"),
        "steuernummer": adresse.get("steuernummer"),
        "handelsregister": adresse.get("handelsregister"),
        "handelsregister_gericht": adresse.get("handelsregister_gericht"),
        
        # Nummern
        "debitoren_nummer": adresse.get("debitoren_nummer") or adresse.get("kundennummer"),
        "kreditoren_nummer": adresse.get("kreditoren_nummer") or adresse.get("lieferantennummer"),
        
        # Dynamische Daten (immer live aus Adresse)
        "weitere_ustids": adresse.get("weitere_ustids", []),
        "lieferadressen": adresse.get("lieferadressen", []),
        "bankverbindungen": adresse.get("bankverbindungen", []),
        
        # Sync-Metadaten
        "letzter_sync": datetime.utcnow().isoformat(),
        "sync_quelle": "auto"
    }
    
    await db.firma.update_one(
        {"mandant_id": mandant_id},
        {"$set": sync_data}
    )
    
    return sync_data


# ============================================================
# PYDANTIC MODELS
# ============================================================

class AdresseCreate(BaseModel):
    # Grunddaten
    anrede: Optional[str] = Field(None, max_length=20)
    vorname: Optional[str] = Field(None, max_length=30)
    name1: str = Field(..., max_length=40)
    name2: Optional[str] = Field(None, max_length=40)
    name3: Optional[str] = Field(None, max_length=40)
    rechtsform: Optional[str] = Field(None, max_length=30)
    
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
    ist_firma: bool = True
    
    # Betreuer
    betreuer: Optional[str] = Field(None, max_length=20)
    betreuer2: Optional[str] = Field(None, max_length=20)
    
    # Nummern
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
    weitere_ustids: Optional[List[Dict[str, str]]] = None
    
    # Handelsregister
    handelsregister: Optional[str] = Field(None, max_length=50)
    firmenlogo: Optional[str] = None
    ansprechpartner: Optional[List[Dict[str, Any]]] = None
    
    # Zahlungs-/Lieferbedingungen
    waehrung: Optional[str] = Field("EUR", max_length=3)
    zahlungsbedingung_ek: Optional[str] = Field(None, max_length=100)
    zahlungsbedingung_vk: Optional[str] = Field(None, max_length=100)
    lieferbedingung_ek: Optional[str] = Field(None, max_length=50)
    lieferbedingung_vk: Optional[str] = Field(None, max_length=50)
    
    # Sperren - WICHTIG: Default TRUE gemäß Echo2-Logik (bis zur Freigabe gesperrt!)
    rechnungen_sperren: bool = False
    gutschriften_sperren: bool = False
    wareneingang_sperren: bool = True  # Default: gesperrt bis Freigabe
    warenausgang_sperren: bool = True  # Default: gesperrt bis Freigabe
    wird_nicht_gemahnt: bool = False
    
    # Einkauf/Verkauf Freigabe (aus Echo2: IST_LIEFERANT, IST_ABNEHMER)
    ist_lieferant: bool = False  # Einkaufsfreigabe
    ist_abnehmer: bool = False   # Verkaufsfreigabe
    
    # Ausweis
    ausweis_nummer: Optional[str] = Field(None, max_length=30)
    ausweis_ablauf: Optional[str] = Field(None, max_length=10)
    geburtsdatum: Optional[str] = Field(None, max_length=10)
    
    # Sonderschalter
    firma_ohne_ustid: bool = False
    privat_mit_ustid: bool = False
    
    # Bemerkungen
    bemerkungen: Optional[str] = Field(None, max_length=700)
    bemerkung_fahrplan: Optional[str] = Field(None, max_length=300)
    lieferinfo_tpa: Optional[str] = Field(None, max_length=300)
    
    # Zusätzliche Felder aus Echo2 (NEU)
    erstkontakt: Optional[str] = Field(None, max_length=10)  # Datum des Erstkontakts
    branche: Optional[str] = Field(None, max_length=50)       # Branche/Industrie
    adressklasse: Optional[str] = Field(None, max_length=10)  # Klassifizierung (A/B/C)
    potential: Optional[str] = Field(None, max_length=20)     # Potentialklasse
    kredit_limit: Optional[float] = None                      # Kreditlimit
    kredit_limit_waehrung: Optional[str] = Field("EUR", max_length=3)


class AdresseUpdate(BaseModel):
    anrede: Optional[str] = None
    vorname: Optional[str] = None
    name1: Optional[str] = None
    name2: Optional[str] = None
    name3: Optional[str] = None
    rechtsform: Optional[str] = None
    strasse: Optional[str] = None
    hausnummer: Optional[str] = None
    plz: Optional[str] = None
    ort: Optional[str] = None
    ortzusatz: Optional[str] = None
    land: Optional[str] = None
    sprache: Optional[str] = None
    plz_postfach: Optional[str] = None
    postfach: Optional[str] = None
    postfach_aktiv: Optional[bool] = None
    telefon: Optional[str] = None
    telefax: Optional[str] = None
    email: Optional[str] = None
    webseite: Optional[str] = None
    latitude: Optional[float] = None
    longitude: Optional[float] = None
    wartezeit_min: Optional[int] = None
    adresstyp: Optional[int] = None
    aktiv: Optional[bool] = None
    wareneingang: Optional[bool] = None
    warenausgang: Optional[bool] = None
    barkunde: Optional[bool] = None
    scheckdruck: Optional[bool] = None
    ist_firma: Optional[bool] = None
    betreuer: Optional[str] = None
    betreuer2: Optional[str] = None
    kreditor_nr: Optional[str] = None
    debitor_nr: Optional[str] = None
    lief_nr: Optional[str] = None
    abn_nr: Optional[str] = None
    betriebs_nr_saa: Optional[str] = None
    sondernummer: Optional[str] = None
    umsatzsteuer_lkz: Optional[str] = None
    umsatzsteuer_id: Optional[str] = None
    steuernummer: Optional[str] = None
    weitere_ustids: Optional[List[Dict[str, str]]] = None
    handelsregister: Optional[str] = None
    firmenlogo: Optional[str] = None
    ansprechpartner: Optional[List[Dict[str, Any]]] = None
    waehrung: Optional[str] = None
    zahlungsbedingung_ek: Optional[str] = None
    zahlungsbedingung_vk: Optional[str] = None
    lieferbedingung_ek: Optional[str] = None
    lieferbedingung_vk: Optional[str] = None
    rechnungen_sperren: Optional[bool] = None
    gutschriften_sperren: Optional[bool] = None
    wareneingang_sperren: Optional[bool] = None
    warenausgang_sperren: Optional[bool] = None
    wird_nicht_gemahnt: Optional[bool] = None
    ausweis_nummer: Optional[str] = None
    ausweis_ablauf: Optional[str] = None
    geburtsdatum: Optional[str] = None
    firma_ohne_ustid: Optional[bool] = None
    privat_mit_ustid: Optional[bool] = None
    bemerkungen: Optional[str] = None
    bemerkung_fahrplan: Optional[str] = None
    lieferinfo_tpa: Optional[str] = None


# EU-Länder mit UST-Präfixen
# Vollständige Länderliste (Welt)
ALLE_LAENDER = {
    # Europa - EU
    "Deutschland": {"ust_praefix": "DE", "ist_eu": True, "ist_homeland": True, "region": "Europa"},
    "Österreich": {"ust_praefix": "AT", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Niederlande": {"ust_praefix": "NL", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Belgien": {"ust_praefix": "BE", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Frankreich": {"ust_praefix": "FR", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Italien": {"ust_praefix": "IT", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Spanien": {"ust_praefix": "ES", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Polen": {"ust_praefix": "PL", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Tschechien": {"ust_praefix": "CZ", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Dänemark": {"ust_praefix": "DK", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Schweden": {"ust_praefix": "SE", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Finnland": {"ust_praefix": "FI", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Griechenland": {"ust_praefix": "EL", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Portugal": {"ust_praefix": "PT", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Irland": {"ust_praefix": "IE", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Luxemburg": {"ust_praefix": "LU", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Ungarn": {"ust_praefix": "HU", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Rumänien": {"ust_praefix": "RO", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Bulgarien": {"ust_praefix": "BG", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Kroatien": {"ust_praefix": "HR", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Slowakei": {"ust_praefix": "SK", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Slowenien": {"ust_praefix": "SI", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Estland": {"ust_praefix": "EE", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Lettland": {"ust_praefix": "LV", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Litauen": {"ust_praefix": "LT", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Malta": {"ust_praefix": "MT", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    "Zypern": {"ust_praefix": "CY", "ist_eu": True, "ist_homeland": False, "region": "Europa"},
    # Europa - Nicht-EU
    "Schweiz": {"ust_praefix": "CH", "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Großbritannien": {"ust_praefix": "GB", "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Norwegen": {"ust_praefix": "NO", "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Island": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Liechtenstein": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Monaco": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Andorra": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "San Marino": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Vatikanstadt": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Albanien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Bosnien und Herzegowina": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Kosovo": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Nordmazedonien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Montenegro": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Serbien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Moldau": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Ukraine": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Belarus": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    "Russland": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Europa"},
    # Amerika
    "USA": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Kanada": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Mexiko": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Brasilien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Argentinien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Chile": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Kolumbien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Peru": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Venezuela": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Ecuador": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Bolivien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Paraguay": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Uruguay": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Kuba": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Jamaika": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Haiti": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Dominikanische Republik": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Guatemala": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Honduras": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "El Salvador": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Nicaragua": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Costa Rica": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    "Panama": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Amerika"},
    # Asien
    "China": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Japan": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Südkorea": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Nordkorea": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Indien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Pakistan": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Bangladesch": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Indonesien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Thailand": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Vietnam": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Malaysia": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Singapur": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Philippinen": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Taiwan": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Hongkong": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Türkei": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Saudi-Arabien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Vereinigte Arabische Emirate": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Israel": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Iran": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Irak": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Kuwait": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Katar": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Bahrain": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Oman": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Jordanien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Libanon": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Syrien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Afghanistan": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Kasachstan": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Usbekistan": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Georgien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Armenien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Aserbaidschan": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Myanmar": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Kambodscha": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Laos": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Nepal": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Sri Lanka": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    "Mongolei": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Asien"},
    # Afrika
    "Ägypten": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Südafrika": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Nigeria": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Kenia": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Äthiopien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Ghana": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Marokko": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Tunesien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Algerien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Libyen": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Sudan": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Tansania": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Uganda": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Angola": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Mosambik": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Kamerun": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Elfenbeinküste": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Senegal": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Namibia": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Botswana": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Simbabwe": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Sambia": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    "Mauritius": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Afrika"},
    # Ozeanien
    "Australien": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Ozeanien"},
    "Neuseeland": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Ozeanien"},
    "Fidschi": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Ozeanien"},
    "Papua-Neuguinea": {"ust_praefix": None, "ist_eu": False, "ist_homeland": False, "region": "Ozeanien"},
}

# Alias für Rückwärtskompatibilität
EU_LAENDER = {k: v for k, v in ALLE_LAENDER.items() if v.get("ist_eu", False) or v.get("ist_homeland", False) or k in ["Schweiz", "Großbritannien", "Norwegen"]}


# ============================================================
# HILFSFUNKTIONEN
# ============================================================

async def generate_kdnr(mandant_id: str, adresstyp: int) -> str:
    """Generiert eine eindeutige Kundennummer"""
    db = get_db()
    mandant = await db.mandanten.find_one({"_id": mandant_id})
    
    if not mandant:
        # Fallback
        count = await db.adressen.count_documents({"mandant_id": mandant_id})
        return f"K{10000 + count:05d}"
    
    if adresstyp == 2:  # Lieferant
        von = mandant.get("numkreis_kreditor_inland_von", 40000)
        bis = mandant.get("numkreis_kreditor_inland_bis", 49999)
    else:  # Kunde/sonstige
        von = mandant.get("numkreis_debitor_inland_von", 10000)
        bis = mandant.get("numkreis_debitor_inland_bis", 19999)
    
    # Nächste freie Nummer finden
    existing = await db.adressen.find(
        {"mandant_id": mandant_id, "adresstyp": adresstyp},
        {"kdnr": 1}
    ).to_list(length=10000)
    
    used_numbers = set()
    for a in existing:
        try:
            num = int(''.join(filter(str.isdigit, a.get("kdnr", ""))))
            used_numbers.add(num)
        except:
            pass
    
    for num in range(von, bis + 1):
        if num not in used_numbers:
            return str(num)
    
    return str(bis + 1)


class AdresseValidierungsFehler(BaseModel):
    meldung: str
    schweregrad: str
    betroffene_felder: List[str]


class AdresseValidierungsErgebnis(BaseModel):
    ist_gueltig: bool
    fehler: List[AdresseValidierungsFehler]
    warnungen: List[AdresseValidierungsFehler]
    steuer_status: Optional[str] = None


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
    name1: Optional[str] = None,
    strasse: Optional[str] = None,
    plz: Optional[str] = None,
    ort: Optional[str] = None,
) -> AdresseValidierungsErgebnis:
    """
    Geschäftslogik-Validierung für Adressen
    Portiert aus Echo2: __FS_Adress_Check.java
    """
    fehler = []
    warnungen = []
    
    # =============================================
    # PFLICHTFELDER (aus FS_MASK_SQLFieldMap_ADRESSE.java)
    # =============================================
    if name1 is not None and not (name1 or "").strip():
        fehler.append(AdresseValidierungsFehler(
            meldung="Firmenname/Name1 ist ein Pflichtfeld!",
            schweregrad="fehler",
            betroffene_felder=["name1"]
        ))
    
    if strasse is not None and not (strasse or "").strip():
        fehler.append(AdresseValidierungsFehler(
            meldung="Straße ist ein Pflichtfeld!",
            schweregrad="fehler",
            betroffene_felder=["strasse"]
        ))
    
    if plz is not None and not (plz or "").strip():
        fehler.append(AdresseValidierungsFehler(
            meldung="PLZ ist ein Pflichtfeld!",
            schweregrad="fehler",
            betroffene_felder=["plz"]
        ))
    
    if ort is not None and not (ort or "").strip():
        fehler.append(AdresseValidierungsFehler(
            meldung="Ort ist ein Pflichtfeld!",
            schweregrad="fehler",
            betroffene_felder=["ort"]
        ))
    
    land_info = ALLE_LAENDER.get(land, {"ust_praefix": None, "ist_eu": False, "ist_homeland": False})
    ist_homeland = land_info.get("ist_homeland", False)
    ist_eu = land_info.get("ist_eu", False)
    ust_praefix = land_info.get("ust_praefix")
    
    hat_ustid = bool(umsatzsteuer_lkz) or bool(umsatzsteuer_id)
    hat_komplette_ustid = bool(umsatzsteuer_lkz) and bool(umsatzsteuer_id)
    hat_teilweise_ustid = bool(umsatzsteuer_lkz) != bool(umsatzsteuer_id)
    hat_steuernummer = bool(steuernummer)
    hat_ausweis = bool(ausweis_nummer)
    
    # =============================================
    # GRUNDVALIDIERUNG (aus __FS_Adress_Check.java)
    # =============================================
    if (not ist_firma and not ist_privat) or (ist_firma and ist_privat):
        fehler.append(AdresseValidierungsFehler(
            meldung="Eine Adresse muss entweder als PRIVAT oder als FIRMA eingestuft werden!",
            schweregrad="fehler",
            betroffene_felder=["ist_firma"]
        ))
    
    # NEU: Teilweise UST-ID prüfen
    if hat_teilweise_ustid:
        fehler.append(AdresseValidierungsFehler(
            meldung="Die Basis-UST-ID ist nur teilweise ausgefüllt. Bitte komplettieren oder komplett leeren!",
            schweregrad="fehler",
            betroffene_felder=["umsatzsteuer_lkz", "umsatzsteuer_id"]
        ))
    
    if not land:
        warnungen.append(AdresseValidierungsFehler(
            meldung="Das Land ist noch nicht gesetzt.",
            schweregrad="warnung",
            betroffene_felder=["land"]
        ))
        return AdresseValidierungsErgebnis(
            ist_gueltig=len(fehler) == 0,
            fehler=fehler,
            warnungen=warnungen,
            steuer_status=None
        )
    
    # NEU: UST-Länderkürzel muss zum Land passen
    if hat_komplette_ustid and ust_praefix and umsatzsteuer_lkz != ust_praefix:
        fehler.append(AdresseValidierungsFehler(
            meldung=f"Das UST-Länderkürzel '{umsatzsteuer_lkz}' stimmt nicht mit dem Land '{land}' überein (erwartet: {ust_praefix})!",
            schweregrad="fehler",
            betroffene_felder=["umsatzsteuer_lkz", "land"]
        ))
    
    # NEU: Ausnahmeschalter nur im Inland
    if not ist_homeland and (firma_ohne_ustid or privat_mit_ustid):
        warnungen.append(AdresseValidierungsFehler(
            meldung="Die Ausnahmeschalter 'FIRMA ohne UST-ID' und 'PRIVAT mit UST-ID' sind nur bei Adressen in Deutschland sinnvoll!",
            schweregrad="warnung",
            betroffene_felder=["firma_ohne_ustid", "privat_mit_ustid"]
        ))
    
    # NEU: Beide Ausnahmeschalter gleichzeitig
    if ist_homeland and firma_ohne_ustid and privat_mit_ustid:
        fehler.append(AdresseValidierungsFehler(
            meldung="Die Ausnahmeschalter 'FIRMA ohne UST-ID' und 'PRIVAT mit UST-ID' können nicht gleichzeitig aktiv sein!",
            schweregrad="fehler",
            betroffene_felder=["firma_ohne_ustid", "privat_mit_ustid"]
        ))
    
    # =============================================
    # FIRMA-Validierung
    # =============================================
    if ist_firma:
        if ist_homeland:  # Inland
            if not hat_ustid and not firma_ohne_ustid:
                fehler.append(AdresseValidierungsFehler(
                    meldung="Firma im Inland ohne UST-ID: Bitte Sonderschalter 'Firma ohne UST-ID' setzen und Steuernummer eingeben!",
                    schweregrad="fehler",
                    betroffene_felder=["umsatzsteuer_id", "firma_ohne_ustid"]
                ))
            elif firma_ohne_ustid and not hat_steuernummer:
                fehler.append(AdresseValidierungsFehler(
                    meldung="Bei 'Firma ohne UST-ID' muss eine Steuernummer angegeben werden!",
                    schweregrad="fehler",
                    betroffene_felder=["steuernummer"]
                ))
            # NEU: FIRMA mit UST-ID darf nicht den Sonderschalter haben
            if hat_komplette_ustid and firma_ohne_ustid:
                fehler.append(AdresseValidierungsFehler(
                    meldung="Bei einer Firma mit UST-ID darf der Schalter 'FIRMA ohne UST-ID' nicht gesetzt sein!",
                    schweregrad="fehler",
                    betroffene_felder=["firma_ohne_ustid"]
                ))
            # NEU: FIRMA darf nicht PRIVAT_MIT_USTID haben
            if privat_mit_ustid:
                fehler.append(AdresseValidierungsFehler(
                    meldung="Bei als FIRMA eingestuften Adressen darf der Schalter 'PRIVAT mit UST-ID' nicht gesetzt sein!",
                    schweregrad="fehler",
                    betroffene_felder=["privat_mit_ustid"]
                ))
        elif ist_eu:  # EU-Ausland
            if not hat_komplette_ustid:
                fehler.append(AdresseValidierungsFehler(
                    meldung="Firma im EU-Ausland MUSS eine vollständige UST-ID haben!",
                    schweregrad="fehler",
                    betroffene_felder=["umsatzsteuer_lkz", "umsatzsteuer_id"]
                ))
    
    # =============================================
    # PRIVAT-Validierung
    # =============================================
    if ist_privat:
        # PRIVAT mit UST-ID im Inland nur mit Sonderschalter
        if ist_homeland and hat_ustid and not privat_mit_ustid:
            fehler.append(AdresseValidierungsFehler(
                meldung="Privatperson mit UST-ID im Inland: Bitte Sonderschalter 'PRIVAT mit UST-ID' setzen!",
                schweregrad="fehler",
                betroffene_felder=["umsatzsteuer_id", "privat_mit_ustid"]
            ))
        
        # NEU: PRIVAT im Ausland darf keine UST-ID haben
        if not ist_homeland and hat_ustid:
            fehler.append(AdresseValidierungsFehler(
                meldung="Bei als PRIVAT eingestuften Adressen im Ausland darf keine UST-ID erfasst sein!",
                schweregrad="fehler",
                betroffene_felder=["umsatzsteuer_id"]
            ))
        
        # NEU: PRIVAT im Inland braucht Ausweis ODER Steuernummer (außer PRIVAT_MIT_USTID)
        if ist_homeland and not hat_ausweis and not hat_steuernummer:
            if not (privat_mit_ustid and hat_ustid):
                fehler.append(AdresseValidierungsFehler(
                    meldung="Bei Privatpersonen im Inland muss Ausweisnummer oder Steuernummer vorliegen!",
                    schweregrad="fehler",
                    betroffene_felder=["ausweis_nummer", "steuernummer"]
                ))
        
        # NEU: PRIVAT im Ausland braucht Ausweis (strenger als vorher)
        if not ist_homeland and not hat_ausweis:
            fehler.append(AdresseValidierungsFehler(
                meldung="Bei Privatpersonen im Ausland MUSS die Ausweisnummer vorliegen!",
                schweregrad="fehler",
                betroffene_felder=["ausweis_nummer"]
            ))
        
        # NEU: PRIVAT darf nicht FIRMA_OHNE_USTID haben
        if ist_homeland and firma_ohne_ustid:
            fehler.append(AdresseValidierungsFehler(
                meldung="Bei als PRIVAT eingestuften Adressen darf der Schalter 'FIRMA ohne UST-ID' nicht gesetzt sein!",
                schweregrad="fehler",
                betroffene_felder=["firma_ohne_ustid"]
            ))
    
    # =============================================
    # Ausweis-Ablaufdatum prüfen
    # =============================================
    if hat_ausweis and ausweis_ablauf:
        try:
            from datetime import datetime as dt
            ablauf_date = None
            for fmt in ["%Y-%m-%d", "%d.%m.%Y", "%d/%m/%Y"]:
                try:
                    ablauf_date = dt.strptime(ausweis_ablauf, fmt).date()
                    break
                except:
                    continue
            
            if ablauf_date:
                from datetime import date
                heute = date.today()
                if ablauf_date < heute:
                    warnungen.append(AdresseValidierungsFehler(
                        meldung=f"Das Ausweis-Ablaufdatum ({ausweis_ablauf}) ist abgelaufen!",
                        schweregrad="warnung",
                        betroffene_felder=["ausweis_ablauf"]
                    ))
        except:
            pass
    
    # Steuer-Status ermitteln
    steuer_status = None
    if ist_firma:
        if ist_homeland:
            steuer_status = "FIRMA_INLAND"
        elif ist_eu:
            steuer_status = "FIRMA_EU"
        else:
            steuer_status = "FIRMA_DRITTLAND"
    else:
        if ist_homeland:
            steuer_status = "PRIVAT_INLAND"
        else:
            steuer_status = "PRIVAT_AUSLAND"
    
    return AdresseValidierungsErgebnis(
        ist_gueltig=len(fehler) == 0,
        fehler=fehler,
        warnungen=warnungen,
        steuer_status=steuer_status
    )


# ============================================================
# ENDPUNKTE
# ============================================================

@router.get("/adressen")
async def get_adressen(
    suche: Optional[str] = None,
    adresstyp: Optional[int] = None,
    aktiv: Optional[bool] = None,
    page: int = 1,
    limit: int = 20,
    user = Depends(require_permission("adressen", "read"))
):
    """Adressen suchen mit Pagination"""
    db = get_db()
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


@router.get("/adressen/{adresse_id}")
async def get_adresse(adresse_id: str, user = Depends(require_permission("adressen", "read"))):
    """Adresse nach ID"""
    db = get_db()
    adresse = await db.adressen.find_one({
        "_id": adresse_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not adresse:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    adresse["id"] = adresse.pop("_id")
    return {"success": True, "data": adresse}


@router.post("/adressen")
async def create_adresse(
    data: AdresseCreate, 
    skip_validation: bool = False,
    user = Depends(require_permission("adressen", "write"))
):
    """Neue Adresse erstellen"""
    db = get_db()
    warnungen = []
    
    if not skip_validation:
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
            name1=data.name1,
            strasse=data.strasse,
            plz=data.plz,
            ort=data.ort,
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


@router.put("/adressen/{adresse_id}")
async def update_adresse(
    adresse_id: str, 
    data: AdresseUpdate, 
    skip_validation: bool = False,
    user = Depends(require_permission("adressen", "write"))
):
    """Adresse aktualisieren"""
    db = get_db()
    existing = await db.adressen.find_one({
        "_id": adresse_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not existing:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    warnungen = []
    
    if not skip_validation and update_data:
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
            name1=merged.get("name1"),
            strasse=merged.get("strasse"),
            plz=merged.get("plz"),
            ort=merged.get("ort"),
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
    
    update_data["letzte_aenderung"] = datetime.utcnow()
    update_data["geaendert_von"] = user.get("kuerzel")
    
    await db.adressen.update_one(
        {"_id": adresse_id},
        {"$set": update_data}
    )
    
    # Auto-Sync: Falls diese Adresse die Firmenadresse ist, Firmendaten aktualisieren
    firma_synced = await sync_firma_wenn_firmenadresse(adresse_id, user["mandant_id"])
    
    updated = await db.adressen.find_one({"_id": adresse_id})
    updated["id"] = updated.pop("_id")
    
    response = {
        "success": True, 
        "data": updated,
        "warnungen": warnungen if warnungen else None
    }
    
    if firma_synced:
        response["firma_synced"] = True
        response["sync_message"] = "Firmendaten wurden automatisch aktualisiert"
    
    return response


@router.delete("/adressen/{adresse_id}")
async def delete_adresse(adresse_id: str, user = Depends(require_permission("adressen", "full"))):
    """Adresse löschen (Hard-Delete) - löscht auch verknüpfte Daten"""
    db = get_db()
    
    # Prüfen ob Adresse existiert
    adresse = await db.adressen.find_one({
        "_id": adresse_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if not adresse:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    # 1. Adresse aus allen Kreditversicherungen entfernen
    await db.kreditversicherungen.update_many(
        {"mandant_id": user["mandant_id"]},
        {"$pull": {"kunden_positionen": {"adresse_id": adresse_id}}}
    )
    
    # 2. Alte Struktur auch bereinigen (versicherte_adressen)
    await db.kreditversicherungen.update_many(
        {"mandant_id": user["mandant_id"]},
        {"$pull": {"versicherte_adressen": {"adresse_id": adresse_id}}}
    )
    
    # 3. Adresse endgültig löschen (Hard Delete)
    result = await db.adressen.delete_one({
        "_id": adresse_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if result.deleted_count == 0:
        raise HTTPException(status_code=404, detail="Adresse konnte nicht gelöscht werden")
    
    return {"success": True, "message": "Adresse und verknüpfte Daten gelöscht"}


@router.post("/adressen/validieren")
async def validiere_adresse(data: AdresseCreate, user = Depends(require_permission("adressen", "read"))):
    """Adresse validieren ohne zu speichern"""
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
        name1=data.name1,
        strasse=data.strasse,
        plz=data.plz,
        ort=data.ort,
    )
    return {"success": True, "validierung": validierung.model_dump()}


@router.get("/laender")
async def get_laender(user = Depends(require_permission("adressen", "read"))):
    """Liste aller Länder der Welt, sortiert nach Region und Name"""
    laender = []
    for name, info in ALLE_LAENDER.items():
        laender.append({
            "name": name,
            "ust_praefix": info.get("ust_praefix"),
            "ist_eu": info.get("ist_eu", False),
            "ist_homeland": info.get("ist_homeland", False),
            "region": info.get("region", "Sonstige"),
        })
    # Sortiere nach Region, dann nach Name
    return {"success": True, "data": sorted(laender, key=lambda x: (x["region"], x["name"]))}


# ============================================================
# ANSPRECHPARTNER
# ============================================================

@router.post("/adressen/{adresse_id}/ansprechpartner")
async def add_ansprechpartner(
    adresse_id: str,
    data: dict,
    user = Depends(require_permission("adressen", "write"))
):
    """Ansprechpartner hinzufügen"""
    db = get_db()
    ap_id = str(uuid.uuid4())
    ansprechpartner = {
        "id": ap_id,
        **data,
        "erstellt_am": datetime.utcnow().isoformat()
    }
    
    await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"$push": {"ansprechpartner": ansprechpartner}}
    )
    
    return {"success": True, "data": ansprechpartner}


@router.put("/adressen/{adresse_id}/ansprechpartner/{ap_id}")
async def update_ansprechpartner(
    adresse_id: str,
    ap_id: str,
    data: dict,
    user = Depends(require_permission("adressen", "write"))
):
    """Ansprechpartner aktualisieren"""
    db = get_db()
    await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"], "ansprechpartner.id": ap_id},
        {"$set": {f"ansprechpartner.$.{k}": v for k, v in data.items()}}
    )
    
    return {"success": True}


@router.delete("/adressen/{adresse_id}/ansprechpartner/{ap_id}")
async def delete_ansprechpartner(
    adresse_id: str,
    ap_id: str,
    user = Depends(require_permission("adressen", "full"))
):
    """Ansprechpartner löschen"""
    db = get_db()
    await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"$pull": {"ansprechpartner": {"id": ap_id}}}
    )
    
    return {"success": True}


# ============================================================
# BANKVERBINDUNGEN (Echo2: KONTEN)
# ============================================================

class BankverbindungCreate(BaseModel):
    """Bankverbindung-Datenmodell (aus Echo2 JT_KONTO)"""
    iban: str = Field(..., max_length=34, description="IBAN")
    bic: Optional[str] = Field(None, max_length=11, description="BIC/SWIFT-Code")
    bank_name: Optional[str] = Field(None, max_length=100, description="Name der Bank")
    kontonummer: Optional[str] = Field(None, max_length=20, description="Kontonummer (alt)")
    bankleitzahl: Optional[str] = Field(None, max_length=10, description="BLZ (alt)")
    kontoinhaber: Optional[str] = Field(None, max_length=100, description="Kontoinhaber")
    verwendungszweck: Optional[str] = Field(None, max_length=10, description="EK/VK/Beides")
    ist_hauptkonto: bool = False
    aktiv: bool = True
    bemerkungen: Optional[str] = Field(None, max_length=200)


@router.get("/adressen/{adresse_id}/bankverbindungen")
async def get_bankverbindungen(adresse_id: str, user = Depends(require_permission("adressen", "read"))):
    """Bankverbindungen einer Adresse abrufen"""
    db = get_db()
    adresse = await db.adressen.find_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"bankverbindungen": 1}
    )
    
    if not adresse:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    return {"success": True, "data": adresse.get("bankverbindungen", [])}


@router.post("/adressen/{adresse_id}/bankverbindungen")
async def add_bankverbindung(
    adresse_id: str,
    data: BankverbindungCreate,
    user = Depends(require_permission("adressen", "write"))
):
    """Bankverbindung hinzufügen"""
    db = get_db()
    
    bankverbindung = {
        "id": str(uuid.uuid4()),
        **data.model_dump(),
        "erstellt_am": datetime.utcnow(),
    }
    
    # Wenn Hauptkonto, andere auf nicht-Haupt setzen (nur wenn Array existiert)
    if data.ist_hauptkonto:
        # Check if bankverbindungen array exists and has items
        adresse = await db.adressen.find_one(
            {"_id": adresse_id, "mandant_id": user["mandant_id"]},
            {"bankverbindungen": 1}
        )
        if adresse and adresse.get("bankverbindungen"):
            await db.adressen.update_one(
                {"_id": adresse_id, "mandant_id": user["mandant_id"]},
                {"$set": {"bankverbindungen.$[].ist_hauptkonto": False}}
            )
    
    await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"$push": {"bankverbindungen": bankverbindung}}
    )
    
    return {"success": True, "data": bankverbindung}


@router.put("/adressen/{adresse_id}/bankverbindungen/{bank_id}")
async def update_bankverbindung(
    adresse_id: str,
    bank_id: str,
    data: dict,
    user = Depends(require_permission("adressen", "write"))
):
    """Bankverbindung aktualisieren"""
    db = get_db()
    
    # Wenn Hauptkonto, andere auf nicht-Haupt setzen
    if data.get("ist_hauptkonto"):
        await db.adressen.update_one(
            {"_id": adresse_id, "mandant_id": user["mandant_id"]},
            {"$set": {"bankverbindungen.$[].ist_hauptkonto": False}}
        )
    
    await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"], "bankverbindungen.id": bank_id},
        {"$set": {f"bankverbindungen.$.{k}": v for k, v in data.items()}}
    )
    
    return {"success": True}


@router.delete("/adressen/{adresse_id}/bankverbindungen/{bank_id}")
async def delete_bankverbindung(
    adresse_id: str,
    bank_id: str,
    user = Depends(require_permission("adressen", "full"))
):
    """Bankverbindung löschen"""
    db = get_db()
    await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"$pull": {"bankverbindungen": {"id": bank_id}}}
    )
    
    return {"success": True}


# ============================================================
# LIEFERADRESSEN (Echo2: LIEFERADRESSEN)
# ============================================================

class LieferadresseCreate(BaseModel):
    """Lieferadresse-Datenmodell (aus Echo2 JT_LIEFERADRESSE)"""
    bezeichnung: str = Field(..., max_length=50, description="Bezeichnung/Name der Lieferadresse")
    name1: Optional[str] = Field(None, max_length=40)
    name2: Optional[str] = Field(None, max_length=40)
    strasse: Optional[str] = Field(None, max_length=45)
    hausnummer: Optional[str] = Field(None, max_length=10)
    plz: Optional[str] = Field(None, max_length=10)
    ort: Optional[str] = Field(None, max_length=30)
    land: Optional[str] = Field("Deutschland", max_length=30)
    latitude: Optional[float] = None
    longitude: Optional[float] = None
    ansprechpartner: Optional[str] = Field(None, max_length=100)
    telefon: Optional[str] = Field(None, max_length=30)
    bemerkungen: Optional[str] = Field(None, max_length=300)
    aktiv: bool = True
    ist_standard: bool = False


@router.get("/adressen/{adresse_id}/lieferadressen")
async def get_lieferadressen(adresse_id: str, user = Depends(require_permission("adressen", "read"))):
    """Lieferadressen einer Adresse abrufen"""
    db = get_db()
    adresse = await db.adressen.find_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"lieferadressen": 1}
    )
    
    if not adresse:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    return {"success": True, "data": adresse.get("lieferadressen", [])}


@router.post("/adressen/{adresse_id}/lieferadressen")
async def add_lieferadresse(
    adresse_id: str,
    data: LieferadresseCreate,
    user = Depends(require_permission("adressen", "write"))
):
    """Lieferadresse hinzufügen"""
    db = get_db()
    
    lieferadresse = {
        "id": str(uuid.uuid4()),
        **data.model_dump(),
        "erstellt_am": datetime.utcnow(),
    }
    
    # Wenn Standard, andere auf nicht-Standard setzen (nur wenn Array existiert)
    if data.ist_standard:
        # Check if lieferadressen array exists and has items
        adresse = await db.adressen.find_one(
            {"_id": adresse_id, "mandant_id": user["mandant_id"]},
            {"lieferadressen": 1}
        )
        if adresse and adresse.get("lieferadressen"):
            await db.adressen.update_one(
                {"_id": adresse_id, "mandant_id": user["mandant_id"]},
                {"$set": {"lieferadressen.$[].ist_standard": False}}
            )
    
    await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"$push": {"lieferadressen": lieferadresse}}
    )
    
    # Auto-Sync Firmendaten
    await sync_firma_wenn_firmenadresse(adresse_id, user["mandant_id"])
    
    return {"success": True, "data": lieferadresse}


@router.put("/adressen/{adresse_id}/lieferadressen/{liefer_id}")
async def update_lieferadresse(
    adresse_id: str,
    liefer_id: str,
    data: dict,
    user = Depends(require_permission("adressen", "write"))
):
    """Lieferadresse aktualisieren"""
    db = get_db()
    
    # Wenn Standard, andere auf nicht-Standard setzen
    if data.get("ist_standard"):
        await db.adressen.update_one(
            {"_id": adresse_id, "mandant_id": user["mandant_id"]},
            {"$set": {"lieferadressen.$[].ist_standard": False}}
        )
    
    await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"], "lieferadressen.id": liefer_id},
        {"$set": {f"lieferadressen.$.{k}": v for k, v in data.items()}}
    )
    
    # Auto-Sync Firmendaten
    await sync_firma_wenn_firmenadresse(adresse_id, user["mandant_id"])
    
    return {"success": True}


@router.delete("/adressen/{adresse_id}/lieferadressen/{liefer_id}")
async def delete_lieferadresse(
    adresse_id: str,
    liefer_id: str,
    user = Depends(require_permission("adressen", "full"))
):
    """Lieferadresse löschen"""
    db = get_db()
    await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"$pull": {"lieferadressen": {"id": liefer_id}}}
    )
    
    # Auto-Sync Firmendaten
    await sync_firma_wenn_firmenadresse(adresse_id, user["mandant_id"])
    
    return {"success": True}


# ============================================================
# UST-ID VALIDIERUNG
# ============================================================

class UstIdValidationRequest(BaseModel):
    ustid: str
    lkz: Optional[str] = None
    laenderkennzeichen: Optional[str] = None  # Alternative Feldname für Frontend-Kompatibilität
    adresse_id: Optional[str] = None
    
    @property
    def country_code(self) -> str:
        """Gibt das Länderkennzeichen zurück (lkz oder laenderkennzeichen)"""
        return self.lkz or self.laenderkennzeichen or ""


@router.post("/ustid/validate")
async def validate_ustid(
    request: UstIdValidationRequest,
    user = Depends(require_permission("adressen", "read"))
):
    """UST-ID via EU VIES validieren"""
    db = get_db()
    
    lkz = request.country_code
    if not lkz:
        raise HTTPException(status_code=400, detail="Länderkennzeichen (lkz) erforderlich")
    
    full_ustid = f"{lkz}{request.ustid}"
    
    try:
        async with httpx.AsyncClient() as client:
            response = await client.post(
                "https://ec.europa.eu/taxation_customs/vies/rest-api/check-vat-number",
                json={
                    "countryCode": lkz,
                    "vatNumber": request.ustid
                },
                timeout=10.0
            )
            
            if response.status_code == 200:
                data = response.json()
                
                # Protokollieren
                protokoll = {
                    "_id": str(uuid.uuid4()),
                    "adresse_id": request.adresse_id,
                    "mandant_id": user["mandant_id"],
                    "ustid": full_ustid,
                    "lkz": lkz,
                    "ist_gueltig": data.get("valid", False),
                    "firmenname": data.get("name"),
                    "firmenadresse": data.get("address"),
                    "geprueft_am": datetime.utcnow(),
                    "geprueft_von": user.get("benutzername"),
                    "request_identifier": data.get("requestIdentifier"),
                }
                await db.ustid_protokoll.insert_one(protokoll)
                
                # Response in Frontend-kompatiblem Format
                return {
                    "success": True,
                    "data": {
                        "gueltig": data.get("valid", False),
                        "firmenname": data.get("name"),
                        "firmenadresse": data.get("address"),
                        "request_identifier": data.get("requestIdentifier"),
                    },
                    # Auch alte Felder für Abwärtskompatibilität
                    "valid": data.get("valid", False),
                    "name": data.get("name"),
                    "address": data.get("address"),
                }
            else:
                return {
                    "success": False,
                    "error": f"VIES-Fehler: {response.status_code}"
                }
                
    except Exception as e:
        return {
            "success": False,
            "error": f"Verbindungsfehler: {str(e)}"
        }


@router.get("/ustid/protokoll/{adresse_id}")
async def get_ustid_protokoll(
    adresse_id: str,
    user = Depends(require_permission("adressen", "read"))
):
    """UST-ID Validierungsprotokoll für eine bestimmte Adresse abrufen"""
    db = get_db()
    
    # Nur Protokolle für diese spezifische Adresse abrufen
    cursor = db.ustid_protokoll.find({
        "adresse_id": adresse_id,
        "mandant_id": user["mandant_id"]
    }).sort("geprueft_am", -1)
    
    protokolle = await cursor.to_list(length=100)
    
    # Feldnamen für Frontend-Kompatibilität transformieren
    result = []
    for p in protokolle:
        # Datum formatieren (falls None, aktuelles Datum verwenden)
        geprueft_am = p.get("geprueft_am")
        if geprueft_am:
            if hasattr(geprueft_am, 'isoformat'):
                abfrage_datum = geprueft_am.isoformat()
            else:
                abfrage_datum = str(geprueft_am)
        else:
            abfrage_datum = datetime.utcnow().isoformat()
        
        result.append({
            "id": str(p.get("_id", "")),
            "adresse_id": p.get("adresse_id"),
            "laenderkennzeichen": p.get("lkz", ""),
            "ustid": p.get("ustid", "").replace(p.get("lkz", ""), "", 1),  # Nur Nummer ohne LKZ
            "gueltig": p.get("ist_gueltig", False),
            "firmenname": p.get("firmenname"),
            "adresse": p.get("firmenadresse"),
            "abfrage_datum": abfrage_datum,
            "request_identifier": p.get("request_identifier"),
            "abgefragt_von": p.get("geprueft_von"),
        })
    
    return {"success": True, "data": result}


# ============================================================
# UPLOADS
# ============================================================

@router.post("/upload/logo/{adresse_id}")
async def upload_firmenlogo(
    adresse_id: str,
    file: UploadFile = File(...),
    user = Depends(get_current_user)
):
    """Firmenlogo hochladen"""
    db = get_db()
    
    content = await file.read()
    base64_content = base64.b64encode(content).decode('utf-8')
    content_type = file.content_type or 'image/png'
    
    data_url = f"data:{content_type};base64,{base64_content}"
    
    await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"$set": {"firmenlogo": data_url}}
    )
    
    return {"success": True, "logo_url": data_url}


@router.delete("/upload/logo/{adresse_id}")
async def delete_firmenlogo(adresse_id: str, user = Depends(get_current_user)):
    """Firmenlogo löschen"""
    db = get_db()
    await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"$unset": {"firmenlogo": ""}}
    )
    return {"success": True}


# ============================================================
# VISITENKARTEN
# ============================================================

@router.post("/adressen/{adresse_id}/ansprechpartner/{ap_id}/visitenkarte")
async def upload_visitenkarte(
    adresse_id: str,
    ap_id: str,
    file: UploadFile = File(...),
    user = Depends(get_current_user)
):
    """Visitenkarte für Ansprechpartner hochladen"""
    if not file.content_type or not file.content_type.startswith("image/"):
        raise HTTPException(status_code=400, detail="Nur Bilddateien erlaubt")
    
    db = get_db()
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

@router.post("/ocr/visitenkarte")
async def ocr_visitenkarte(
    file: UploadFile = File(...),
    user = Depends(get_current_user)
):
    """
    OCR-Erkennung für Visitenkarten
    Extrahiert automatisch: Name, Vorname, Firma, Funktion, Telefon, Mobil, E-Mail
    Verwendet OpenAI GPT-4o Vision via Emergent LLM Key
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
            raise HTTPException(status_code=500, detail="OCR-Service nicht konfiguriert (EMERGENT_LLM_KEY fehlt)")
        
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
        raise HTTPException(status_code=500, detail="OCR-Modul nicht installiert (emergentintegrations)")
    except json.JSONDecodeError:
        return {
            "success": True,
            "data": {},
            "raw_response": response,
            "error": "JSON-Parsing fehlgeschlagen"
        }
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"OCR-Fehler: {str(e)}")
