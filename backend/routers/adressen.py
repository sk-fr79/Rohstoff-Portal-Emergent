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
from utils.auth import get_current_user

router = APIRouter(prefix="/api", tags=["Adressen"])

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
    
    # Sperren
    rechnungen_sperren: bool = False
    gutschriften_sperren: bool = False
    wareneingang_sperren: bool = False
    warenausgang_sperren: bool = False
    wird_nicht_gemahnt: bool = False
    
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
    "Schweiz": {"ust_praefix": "CH", "ist_eu": False, "ist_homeland": False},
    "Großbritannien": {"ust_praefix": "GB", "ist_eu": False, "ist_homeland": False},
    "Norwegen": {"ust_praefix": "NO", "ist_eu": False, "ist_homeland": False},
}


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
) -> AdresseValidierungsErgebnis:
    """Geschäftslogik-Validierung für Adressen"""
    fehler = []
    warnungen = []
    
    land_info = EU_LAENDER.get(land, {"ust_praefix": None, "ist_eu": False, "ist_homeland": False})
    ist_homeland = land_info.get("ist_homeland", False)
    ist_eu = land_info.get("ist_eu", False)
    ust_praefix = land_info.get("ust_praefix")
    
    hat_ustid = bool(umsatzsteuer_lkz) or bool(umsatzsteuer_id)
    hat_komplette_ustid = bool(umsatzsteuer_lkz) and bool(umsatzsteuer_id)
    hat_steuernummer = bool(steuernummer)
    hat_ausweis = bool(ausweis_nummer)
    
    # Grundvalidierung
    if (not ist_firma and not ist_privat) or (ist_firma and ist_privat):
        fehler.append(AdresseValidierungsFehler(
            meldung="Eine Adresse muss entweder als PRIVAT oder als FIRMA eingestuft werden!",
            schweregrad="fehler",
            betroffene_felder=["ist_firma"]
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
    
    # FIRMA-Validierung
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
        elif ist_eu:  # EU-Ausland
            if not hat_komplette_ustid:
                fehler.append(AdresseValidierungsFehler(
                    meldung="Firma im EU-Ausland MUSS eine vollständige UST-ID haben!",
                    schweregrad="fehler",
                    betroffene_felder=["umsatzsteuer_lkz", "umsatzsteuer_id"]
                ))
    
    # PRIVAT-Validierung
    if ist_privat:
        if hat_ustid and not privat_mit_ustid:
            fehler.append(AdresseValidierungsFehler(
                meldung="Privatperson darf keine UST-ID haben! Falls doch, Sonderschalter setzen.",
                schweregrad="fehler",
                betroffene_felder=["umsatzsteuer_id", "privat_mit_ustid"]
            ))
        if not ist_homeland and not hat_ausweis:
            warnungen.append(AdresseValidierungsFehler(
                meldung="Für Privatpersonen im Ausland wird eine Ausweisnummer empfohlen.",
                schweregrad="warnung",
                betroffene_felder=["ausweis_nummer"]
            ))
    
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
    user = Depends(get_current_user)
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
async def get_adresse(adresse_id: str, user = Depends(get_current_user)):
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
    user = Depends(get_current_user)
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
    user = Depends(get_current_user)
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
    
    updated = await db.adressen.find_one({"_id": adresse_id})
    updated["id"] = updated.pop("_id")
    
    return {
        "success": True, 
        "data": updated,
        "warnungen": warnungen if warnungen else None
    }


@router.delete("/adressen/{adresse_id}")
async def delete_adresse(adresse_id: str, user = Depends(get_current_user)):
    """Adresse löschen (Soft-Delete)"""
    db = get_db()
    result = await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"$set": {"aktiv": False, "geloescht_am": datetime.utcnow()}}
    )
    
    if result.modified_count == 0:
        raise HTTPException(status_code=404, detail="Adresse nicht gefunden")
    
    return {"success": True, "message": "Adresse deaktiviert"}


@router.post("/adressen/validieren")
async def validiere_adresse(data: AdresseCreate, user = Depends(get_current_user)):
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
    )
    return {"success": True, "validierung": validierung.model_dump()}


@router.get("/laender")
async def get_laender(user = Depends(get_current_user)):
    """Liste der konfigurierten Länder"""
    laender = []
    for name, info in EU_LAENDER.items():
        laender.append({
            "name": name,
            "ust_praefix": info["ust_praefix"],
            "ist_eu": info["ist_eu"],
            "ist_homeland": info["ist_homeland"],
        })
    return {"success": True, "data": sorted(laender, key=lambda x: x["name"])}


# ============================================================
# ANSPRECHPARTNER
# ============================================================

@router.post("/adressen/{adresse_id}/ansprechpartner")
async def add_ansprechpartner(
    adresse_id: str,
    data: dict,
    user = Depends(get_current_user)
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
    user = Depends(get_current_user)
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
    user = Depends(get_current_user)
):
    """Ansprechpartner löschen"""
    db = get_db()
    await db.adressen.update_one(
        {"_id": adresse_id, "mandant_id": user["mandant_id"]},
        {"$pull": {"ansprechpartner": {"id": ap_id}}}
    )
    
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
    user = Depends(get_current_user)
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
    user = Depends(get_current_user)
):
    """UST-ID Validierungsprotokoll abrufen"""
    db = get_db()
    cursor = db.ustid_protokoll.find({
        "adresse_id": adresse_id,
        "mandant_id": user["mandant_id"]
    }).sort("geprueft_am", -1)
    
    protokolle = await cursor.to_list(length=100)
    for p in protokolle:
        p["id"] = p.pop("_id")
    
    return {"success": True, "data": protokolle}


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
