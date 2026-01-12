"""
Kontrakte Router - CRUD für Einkaufs- und Verkaufskontrakte
Erweitert mit Fixierungslogik und Validatoren aus dem Echo2-Altsystem
"""

from fastapi import APIRouter, HTTPException, Depends, Query, Request
from pydantic import BaseModel, Field
from typing import Optional, List, Dict, Any
from datetime import datetime, date
from decimal import Decimal
import uuid

from services.database import get_db
from utils.auth import get_current_user, require_permission
from routers.nummernkreise import get_naechste_nummer

router = APIRouter(prefix="/api", tags=["Kontrakte"])


# ========================== AUDIT LOG HELPER ==========================

AKTION_TYPEN = {
    "ERSTELLT": {"icon": "plus-circle", "farbe": "emerald", "label": "Erstellt"},
    "BEARBEITET": {"icon": "pencil", "farbe": "blue", "label": "Bearbeitet"},
    "STATUS_GEAENDERT": {"icon": "refresh-cw", "farbe": "amber", "label": "Status geändert"},
    "POSITION_HINZUGEFUEGT": {"icon": "package-plus", "farbe": "green", "label": "Position hinzugefügt"},
    "POSITION_BEARBEITET": {"icon": "package", "farbe": "blue", "label": "Position bearbeitet"},
    "POSITION_GELOESCHT": {"icon": "package-x", "farbe": "red", "label": "Position gelöscht"},
    "GEDRUCKT": {"icon": "printer", "farbe": "gray", "label": "Gedruckt"},
    "EXPORTIERT": {"icon": "download", "farbe": "violet", "label": "Exportiert"},
    "GEOEFFNET": {"icon": "eye", "farbe": "slate", "label": "Geöffnet"},
    "PARTNER_GEAENDERT": {"icon": "building", "farbe": "orange", "label": "Partner geändert"},
    "LAGER_GEAENDERT": {"icon": "warehouse", "farbe": "cyan", "label": "Lager geändert"},
    "KOPIERT": {"icon": "copy", "farbe": "indigo", "label": "Kopiert"},
    "STORNIERT": {"icon": "x-circle", "farbe": "red", "label": "Storniert"},
    "FREIGEGEBEN": {"icon": "check-circle", "farbe": "green", "label": "Freigegeben"},
    "ABGESCHLOSSEN": {"icon": "lock", "farbe": "gray", "label": "Abgeschlossen"},
}

async def audit_log_erstellen(
    kontrakt_id: str,
    mandant_id: str,
    aktion: str,
    benutzer: dict,
    details: dict = None,
    aenderungen: list = None
):
    """Erstellt einen Audit-Log Eintrag für einen Kontrakt"""
    db = get_db()
    
    log_eintrag = {
        "_id": str(uuid.uuid4()),
        "kontrakt_id": kontrakt_id,
        "mandant_id": mandant_id,
        "aktion": aktion,
        "aktion_meta": AKTION_TYPEN.get(aktion, {"icon": "activity", "farbe": "gray", "label": aktion}),
        "benutzer_id": benutzer.get("id") or benutzer.get("_id"),
        "benutzer_name": benutzer.get("name") or f"{benutzer.get('vorname', '')} {benutzer.get('nachname', '')}".strip() or benutzer.get("benutzername"),
        "benutzer_kuerzel": benutzer.get("kuerzel") or benutzer.get("benutzername", "")[:2].upper(),
        "zeitstempel": datetime.utcnow().isoformat(),
        "details": details or {},
        "aenderungen": aenderungen or [],
    }
    
    await db.kontrakt_audit_log.insert_one(log_eintrag)
    return log_eintrag


def berechne_aenderungen(alt: dict, neu: dict, felder_whitelist: list = None) -> list:
    """Vergleicht zwei Dictionaries und gibt eine Liste der Änderungen zurück"""
    aenderungen = []
    
    # Felder die verglichen werden sollen
    relevante_felder = felder_whitelist or [
        "status", "name1", "name2", "strasse", "plz", "ort", "land",
        "kontraktnummer", "datum_kontrakt", "datum_lieferung_von", "datum_lieferung_bis",
        "bemerkung_extern", "bemerkung_intern", "zahlungsbedingung", "waehrung_kurz",
        "id_ansprechpartner", "id_zustaendiger", "id_adresse",
        "id_abhollager", "abhollager_name", "id_ziellager", "ziellager_name",
        "id_ustid", "ustid_text", "id_bankverbindung", "bankverbindung_text",
        "formulartext_anfang", "formulartext_ende"
    ]
    
    feld_labels = {
        "status": "Status",
        "name1": "Firmenname",
        "name2": "Zusatz",
        "strasse": "Straße",
        "plz": "PLZ",
        "ort": "Ort",
        "land": "Land",
        "kontraktnummer": "Kontraktnummer",
        "datum_kontrakt": "Kontraktdatum",
        "datum_lieferung_von": "Lieferzeitraum von",
        "datum_lieferung_bis": "Lieferzeitraum bis",
        "bemerkung_extern": "Externe Bemerkung",
        "bemerkung_intern": "Interne Bemerkung",
        "zahlungsbedingung": "Zahlungsbedingung",
        "waehrung_kurz": "Währung",
        "id_ansprechpartner": "Ansprechpartner",
        "id_zustaendiger": "Interner Zuständiger",
        "id_adresse": "Vertragspartner",
        "id_abhollager": "Abhollager",
        "abhollager_name": "Abhollager Name",
        "id_ziellager": "Ziellager",
        "ziellager_name": "Ziellager Name",
        "id_ustid": "USt-ID",
        "ustid_text": "USt-ID Text",
        "id_bankverbindung": "Bankverbindung",
        "bankverbindung_text": "Bankverbindung Text",
        "formulartext_anfang": "Formulartext Anfang",
        "formulartext_ende": "Formulartext Ende",
    }
    
    for feld in relevante_felder:
        alt_wert = alt.get(feld)
        neu_wert = neu.get(feld)
        
        # Nur wenn sich wirklich etwas geändert hat
        if alt_wert != neu_wert:
            # Leere Strings und None als gleich behandeln
            if (alt_wert in [None, "", []] and neu_wert in [None, "", []]):
                continue
                
            aenderungen.append({
                "feld": feld,
                "feld_label": feld_labels.get(feld, feld),
                "alt": alt_wert,
                "neu": neu_wert
            })
    
    return aenderungen


# ========================== SCHEMAS ==========================

class KontraktPositionCreate(BaseModel):
    """Schema für Kontraktposition"""
    positionsnummer: Optional[int] = None  # Auto-increment if not provided
    position_typ: str = "ARTIKEL"  # ARTIKEL, DIENSTLEISTUNG, TEXT
    
    # Artikel-Referenz
    id_artikel: Optional[str] = None
    anr1: Optional[str] = Field(None, max_length=10)
    anr2: Optional[str] = Field(None, max_length=10)
    artbez1: Optional[str] = Field(None, max_length=80)
    artbez2: Optional[str] = Field(None, max_length=80)
    
    # Mengen
    anzahl: Optional[float] = None
    einheitkurz: str = "t"
    mengen_toleranz_prozent: float = 10.0  # Toleranz für Über-/Unterlieferung
    
    # Preise (Hauswährung)
    einzelpreis: Optional[float] = None
    gesamtpreis: Optional[float] = None
    einheit_preis_kurz: Optional[str] = None
    
    # Fremdwährung
    id_waehrung_fremd: Optional[str] = None
    waehrung_fremd_kurz: Optional[str] = None
    waehrungskurs: float = 1.0
    einzelpreis_fw: Optional[float] = None
    gesamtpreis_fw: Optional[float] = None
    
    # Lieferung
    lieferort: Optional[str] = None  # ID der Lieferadresse
    lieferort_name: Optional[str] = None
    
    # Gültigkeit (Trakt-Daten)
    gueltig_von: Optional[str] = None
    gueltig_bis: Optional[str] = None
    ueberliefer_ok: bool = True  # Überlieferung erlaubt?
    
    # Status
    position_abgeschlossen: bool = False
    lager_vorzeichen: int = 0  # -1 = Abgang, 0 = Neutral, 1 = Zugang
    
    # Sonstiges
    bemerkung: Optional[str] = Field(None, max_length=2000)
    deleted: bool = False


class KontraktCreate(BaseModel):
    """Schema für Kontrakt-Kopf"""
    # Typ & Nummer
    vorgang_typ: str = Field("EK", max_length=2)  # EK oder VK
    kontraktnummer: Optional[str] = Field(None, max_length=30)  # Aus Nummernkreis
    
    # === VERTRAGSPARTNER (aus Adressen) ===
    id_adresse: Optional[str] = None  # Referenz zur Adresse
    name1: str = Field(..., max_length=40)
    name2: Optional[str] = Field(None, max_length=40)
    strasse: Optional[str] = Field(None, max_length=45)
    hausnummer: Optional[str] = Field(None, max_length=10)
    plz: Optional[str] = Field(None, max_length=10)
    ort: Optional[str] = Field(None, max_length=30)
    land: Optional[str] = Field(None, max_length=30)
    land_code: Optional[str] = Field(None, max_length=3)
    ust_id: Optional[str] = Field(None, max_length=20)  # USt-ID
    steuernummer: Optional[str] = Field(None, max_length=20)
    
    # Kontaktdaten Partner
    telefon: Optional[str] = Field(None, max_length=30)
    telefax: Optional[str] = Field(None, max_length=30)
    email: Optional[str] = Field(None, max_length=100)
    
    # === BANKVERBINDUNG (aus Vertragspartner) ===
    id_bankverbindung: Optional[str] = None
    bank_iban: Optional[str] = Field(None, max_length=34)
    bank_bic: Optional[str] = Field(None, max_length=11)
    bank_name: Optional[str] = Field(None, max_length=100)
    bank_waehrung: Optional[str] = Field(None, max_length=3)
    
    # === ANSPRECHPARTNER beim Partner ===
    id_ansprechpartner: Optional[str] = None  # Referenz zum Ansprechpartner
    ansprechpartner_name: Optional[str] = Field(None, max_length=80)
    ansprechpartner_telefon: Optional[str] = Field(None, max_length=30)
    ansprechpartner_email: Optional[str] = Field(None, max_length=100)
    
    # === SACHBEARBEITER (aus Benutzerliste) ===
    id_sachbearbeiter: Optional[str] = None  # Referenz zum Benutzer
    sachbearbeiter_name: Optional[str] = Field(None, max_length=80)
    sachbearbeiter_telefon: Optional[str] = Field(None, max_length=30)
    sachbearbeiter_email: Optional[str] = Field(None, max_length=100)
    
    # === HÄNDLER (aus Benutzerliste) ===
    id_haendler: Optional[str] = None  # Referenz zum Benutzer
    haendler_name: Optional[str] = Field(None, max_length=80)
    
    # === LÄGER ===
    id_abhollager: Optional[str] = None
    abhollager_typ: Optional[str] = Field(None, max_length=20)  # 'haupt', 'liefer', 'mandant'
    abhollager_name: Optional[str] = Field(None, max_length=100)
    abhollager_strasse: Optional[str] = Field(None, max_length=100)
    abhollager_plz: Optional[str] = Field(None, max_length=10)
    abhollager_ort: Optional[str] = Field(None, max_length=50)
    abhollager_land: Optional[str] = Field(None, max_length=50)
    
    id_ziellager: Optional[str] = None
    ziellager_typ: Optional[str] = Field(None, max_length=20)
    ziellager_name: Optional[str] = Field(None, max_length=100)
    ziellager_strasse: Optional[str] = Field(None, max_length=100)
    ziellager_plz: Optional[str] = Field(None, max_length=10)
    ziellager_ort: Optional[str] = Field(None, max_length=50)
    ziellager_land: Optional[str] = Field(None, max_length=50)
    
    # Termine
    erstellungsdatum: Optional[str] = None
    gueltig_von: Optional[str] = None
    gueltig_bis: Optional[str] = None
    
    # Währung & Konditionen
    id_waehrung_fremd: Optional[str] = None
    waehrung_kurz: str = Field("EUR", max_length=5)
    waehrungskurs: float = 1.0
    zahlungsbedingung: Optional[str] = Field(None, max_length=100)
    lieferbedingung: Optional[str] = Field(None, max_length=50)
    
    # === FIXIERUNG (NEU) ===
    ist_fixierung: bool = False  # Ist dies ein Fixierungskontrakt?
    fix_von: Optional[str] = None  # Fixierungszeitraum Start
    fix_bis: Optional[str] = None  # Fixierungszeitraum Ende
    fix_menge_gesamt: Optional[float] = None  # Gesamt-Fixierungsmenge
    fix_id_artikel: Optional[str] = None  # Hauptartikel für Fixierung
    fix_id_artbez: Optional[str] = None  # Artikelbezeichnung
    fix_einheit: Optional[str] = Field(None, max_length=10)  # Einheit
    boerse_diff_abs: Optional[float] = None  # Börsendifferenz absolut (€/t)
    boerse_diff_proz: Optional[float] = None  # Börsendifferenz prozentual (%)
    
    # Status
    status: str = Field("OFFEN", max_length=20)
    aktiv: bool = True
    abgeschlossen: bool = False
    
    # Formulartexte
    formulartext_anfang: Optional[str] = Field(None, max_length=4000)
    formulartext_ende: Optional[str] = Field(None, max_length=4000)
    kopie_bemerkung_auf_pos: bool = False  # Bemerkung auf Positionen kopieren
    
    # Bemerkungen
    bemerkung_extern: Optional[str] = Field(None, max_length=2000)
    bemerkung_intern: Optional[str] = Field(None, max_length=2000)
    bemerkung_fix1: Optional[str] = Field(None, max_length=2000)  # Fixierungs-Bemerkung
    
    # Positionen (optional beim Erstellen)
    positionen: Optional[List[Dict[str, Any]]] = None


class KontraktUpdate(BaseModel):
    """Schema für Kontrakt-Update"""
    vorgang_typ: Optional[str] = None
    kontraktnummer: Optional[str] = None
    # Vertragspartner
    id_adresse: Optional[str] = None
    name1: Optional[str] = None
    name2: Optional[str] = None
    strasse: Optional[str] = None
    hausnummer: Optional[str] = None
    plz: Optional[str] = None
    ort: Optional[str] = None
    land: Optional[str] = None
    land_code: Optional[str] = None
    ust_id: Optional[str] = None
    steuernummer: Optional[str] = None
    telefon: Optional[str] = None
    telefax: Optional[str] = None
    email: Optional[str] = None
    # Bankverbindung
    id_bankverbindung: Optional[str] = None
    bank_iban: Optional[str] = None
    bank_bic: Optional[str] = None
    bank_name: Optional[str] = None
    bank_waehrung: Optional[str] = None
    # Ansprechpartner
    id_ansprechpartner: Optional[str] = None
    ansprechpartner_name: Optional[str] = None
    ansprechpartner_telefon: Optional[str] = None
    ansprechpartner_email: Optional[str] = None
    # Sachbearbeiter
    id_sachbearbeiter: Optional[str] = None
    sachbearbeiter_name: Optional[str] = None
    sachbearbeiter_telefon: Optional[str] = None
    sachbearbeiter_email: Optional[str] = None
    # Händler
    id_haendler: Optional[str] = None
    haendler_name: Optional[str] = None
    # Läger
    id_abhollager: Optional[str] = None
    abhollager_typ: Optional[str] = None
    abhollager_name: Optional[str] = None
    abhollager_strasse: Optional[str] = None
    abhollager_plz: Optional[str] = None
    abhollager_ort: Optional[str] = None
    abhollager_land: Optional[str] = None
    id_ziellager: Optional[str] = None
    ziellager_typ: Optional[str] = None
    ziellager_name: Optional[str] = None
    ziellager_strasse: Optional[str] = None
    ziellager_plz: Optional[str] = None
    ziellager_ort: Optional[str] = None
    ziellager_land: Optional[str] = None
    # Termine
    erstellungsdatum: Optional[str] = None
    gueltig_von: Optional[str] = None
    gueltig_bis: Optional[str] = None
    id_waehrung_fremd: Optional[str] = None
    waehrung_kurz: Optional[str] = None
    waehrungskurs: Optional[float] = None
    zahlungsbedingung: Optional[str] = None
    lieferbedingung: Optional[str] = None
    # Fixierung
    ist_fixierung: Optional[bool] = None
    fix_von: Optional[str] = None
    fix_bis: Optional[str] = None
    fix_menge_gesamt: Optional[float] = None
    fix_id_artikel: Optional[str] = None
    fix_id_artbez: Optional[str] = None
    fix_einheit: Optional[str] = None
    boerse_diff_abs: Optional[float] = None
    boerse_diff_proz: Optional[float] = None
    # Status
    status: Optional[str] = None
    aktiv: Optional[bool] = None
    abgeschlossen: Optional[bool] = None
    # Formulartexte
    formulartext_anfang: Optional[str] = None
    formulartext_ende: Optional[str] = None
    kopie_bemerkung_auf_pos: Optional[bool] = None
    # Bemerkungen
    bemerkung_extern: Optional[str] = None
    bemerkung_intern: Optional[str] = None
    bemerkung_fix1: Optional[str] = None
    # Positionen
    positionen: Optional[List[Dict[str, Any]]] = None


# ========================== VALIDATORS ==========================

class KontraktValidationResult:
    """Validierungsergebnis mit Fehlern und Warnungen"""
    def __init__(self):
        self.errors: List[str] = []
        self.warnings: List[str] = []
    
    @property
    def is_valid(self) -> bool:
        return len(self.errors) == 0
    
    def add_error(self, message: str):
        self.errors.append(message)
    
    def add_warning(self, message: str):
        self.warnings.append(message)
    
    def to_dict(self) -> dict:
        return {
            "valid": self.is_valid,
            "errors": self.errors,
            "warnings": self.warnings
        }


class KontraktValidator:
    """
    Kontrakt-Validierung basierend auf Echo2-Altsystem:
    - BSK__CONST.java
    - BSK_P_MASK_MapValidator_CheckMengeInLieferdaten.java
    - PRUEF_RECORD_VPOS_KON.java
    """
    
    @staticmethod
    async def validate(kontrakt: dict, db, kontrakt_id: str = None) -> KontraktValidationResult:
        result = KontraktValidationResult()
        
        # === PFLICHTFELDER ===
        name1 = (kontrakt.get("name1") or "").strip()
        if not name1:
            result.add_error("Vertragspartner (Name1) ist ein Pflichtfeld!")
        
        # === GÜLTIGKEITSDATEN ===
        gueltig_von = kontrakt.get("gueltig_von")
        gueltig_bis = kontrakt.get("gueltig_bis")
        
        if gueltig_von and gueltig_bis:
            try:
                von = datetime.fromisoformat(str(gueltig_von).replace('Z', '+00:00'))
                bis = datetime.fromisoformat(str(gueltig_bis).replace('Z', '+00:00'))
                if bis < von:
                    result.add_error("'Gültig bis' darf nicht vor 'Gültig von' liegen!")
            except:
                pass
        
        # === FIXIERUNGS-VALIDIERUNG ===
        ist_fixierung = kontrakt.get("ist_fixierung", False)
        if ist_fixierung:
            fix_von = kontrakt.get("fix_von")
            fix_bis = kontrakt.get("fix_bis")
            fix_menge = kontrakt.get("fix_menge_gesamt")
            fix_artikel = kontrakt.get("fix_id_artikel")
            
            if not fix_von or not fix_bis:
                result.add_error("Bei Fixierungskontrakten muss der Fixierungszeitraum angegeben werden!")
            
            if not fix_menge or fix_menge <= 0:
                result.add_error("Bei Fixierungskontrakten muss die Gesamt-Fixierungsmenge > 0 sein!")
            
            if not fix_artikel:
                result.add_warning("Bei Fixierungskontrakten sollte ein Hauptartikel angegeben werden.")
            
            # Fixierungszeitraum prüfen
            if fix_von and fix_bis:
                try:
                    f_von = datetime.fromisoformat(str(fix_von).replace('Z', '+00:00'))
                    f_bis = datetime.fromisoformat(str(fix_bis).replace('Z', '+00:00'))
                    if f_bis < f_von:
                        result.add_error("'Fixierung bis' darf nicht vor 'Fixierung von' liegen!")
                except:
                    pass
        
        # === STATUS-PRÜFUNG ===
        status = kontrakt.get("status", "OFFEN")
        abgeschlossen = kontrakt.get("abgeschlossen", False)
        
        if abgeschlossen and status == "OFFEN":
            result.add_warning("Kontrakt ist als abgeschlossen markiert, Status ist aber noch 'OFFEN'.")
        
        # === POSITIONEN-VALIDIERUNG ===
        positionen = kontrakt.get("positionen", [])
        if positionen:
            for i, pos in enumerate(positionen):
                pos_nr = pos.get("positionsnummer", i + 1)
                pos_typ = pos.get("position_typ", "ARTIKEL")
                
                # Bei Artikelpositionen: Menge und Preis prüfen
                if pos_typ == "ARTIKEL":
                    anzahl = pos.get("anzahl")
                    preis = pos.get("einzelpreis")
                    
                    if anzahl is None or anzahl <= 0:
                        result.add_warning(f"Position {pos_nr}: Menge sollte angegeben werden.")
                    
                    if preis is None:
                        result.add_warning(f"Position {pos_nr}: Einzelpreis sollte angegeben werden.")
                
                # Toleranz prüfen
                toleranz = pos.get("mengen_toleranz_prozent", 10)
                if toleranz < 0 or toleranz > 100:
                    result.add_error(f"Position {pos_nr}: Mengentoleranz muss zwischen 0% und 100% liegen!")
                
                # Währungskurs prüfen
                kurs = pos.get("waehrungskurs", 1)
                if kurs <= 0:
                    result.add_error(f"Position {pos_nr}: Währungskurs muss größer 0 sein!")
        
        # === DUBLETTEN-PRÜFUNG (nur bei Neuanlage) ===
        if not kontrakt_id and db is not None:
            await KontraktValidator._check_dubletten(kontrakt, db, result)
        
        return result
    
    @staticmethod
    async def _check_dubletten(kontrakt: dict, db, result: KontraktValidationResult):
        """Prüft auf ähnliche existierende Kontrakte"""
        try:
            id_adresse = kontrakt.get("id_adresse")
            vorgang_typ = kontrakt.get("vorgang_typ")
            gueltig_von = kontrakt.get("gueltig_von")
            gueltig_bis = kontrakt.get("gueltig_bis")
            
            if id_adresse and gueltig_von and gueltig_bis:
                # Suche nach Kontrakten mit gleichem Partner und überschneidendem Zeitraum
                existing = await db.kontrakte.find_one({
                    "id_adresse": id_adresse,
                    "vorgang_typ": vorgang_typ,
                    "status": {"$nin": ["STORNO", "ERFUELLT"]},
                    "deleted": {"$ne": True},
                    "$or": [
                        {"gueltig_von": {"$lte": gueltig_bis}, "gueltig_bis": {"$gte": gueltig_von}},
                    ]
                })
                
                if existing:
                    result.add_warning(
                        f"Es gibt bereits einen ähnlichen Kontrakt mit diesem Partner "
                        f"im überschneidenden Zeitraum (Nr: {existing.get('buchungsnummer', 'N/A')})"
                    )
        except Exception as e:
            pass  # Fehler bei Dublettenprüfung ignorieren
    
    @staticmethod
    async def validate_position_delete(kontrakt_id: str, position_id: str, db) -> KontraktValidationResult:
        """Validiert ob eine Position gelöscht werden darf"""
        result = KontraktValidationResult()
        
        # Prüfen ob Fuhren zugeordnet sind
        fuhren_count = await db.fuhren.count_documents({
            "$or": [
                {"id_vpos_kon_ek": position_id},
                {"id_vpos_kon_vk": position_id}
            ],
            "deleted": {"$ne": True}
        })
        
        if fuhren_count > 0:
            result.add_error(
                f"Aktion ist verboten! Die Kontraktposition hat bereits {fuhren_count} zugeordnete Fuhre(n)!"
            )
        
        # Prüfen ob EK-VK Zuordnungen existieren
        zuordnungen = await db.ek_vk_zuordnungen.count_documents({
            "$or": [
                {"id_vpos_kon_ek": position_id},
                {"id_vpos_kon_vk": position_id}
            ],
            "deleted": {"$ne": True}
        })
        
        if zuordnungen > 0:
            result.add_error(
                f"Aktion ist verboten! Die Kontraktposition hat bereits {zuordnungen} EK-VK-Zuordnung(en)!"
            )
        
        return result
    
    @staticmethod
    async def validate_kontrakt_edit(kontrakt_id: str, db) -> KontraktValidationResult:
        """Validiert ob ein Kontrakt bearbeitet werden darf"""
        result = KontraktValidationResult()
        
        kontrakt = await db.kontrakte.find_one({"_id": kontrakt_id})
        if not kontrakt:
            result.add_error("Kontrakt nicht gefunden!")
            return result
        
        if kontrakt.get("abgeschlossen"):
            result.add_error("Aktion ist verboten! Der Kontrakt ist bereits abgeschlossen!")
        
        if kontrakt.get("status") == "STORNO":
            result.add_error("Aktion ist verboten! Der Kontrakt wurde storniert!")
        
        return result
    
    @staticmethod
    def calculate_lieferstatus(position: dict, gelieferte_menge: float) -> dict:
        """
        Berechnet den Lieferstatus einer Position
        Basierend auf PRUEF_RECORD_VPOS_KON.java
        """
        anzahl = position.get("anzahl", 0) or 0
        toleranz = position.get("mengen_toleranz_prozent", 10) or 10
        ueberliefer_ok = position.get("ueberliefer_ok", True)
        
        if anzahl <= 0:
            return {
                "status": "KEINE_MENGE",
                "message": "Keine Sollmenge definiert",
                "prozent": 0
            }
        
        prozent = (gelieferte_menge / anzahl) * 100
        max_erlaubt = 100 + toleranz
        
        if gelieferte_menge <= 0:
            return {
                "status": "OFFEN",
                "message": "Noch keine Lieferungen",
                "prozent": 0
            }
        elif prozent < 100:
            return {
                "status": "TEILGELIEFERT",
                "message": f"{prozent:.1f}% geliefert",
                "prozent": prozent
            }
        elif prozent == 100:
            return {
                "status": "VOLLSTAENDIG",
                "message": "Vollständig geliefert",
                "prozent": 100
            }
        elif prozent <= max_erlaubt:
            if ueberliefer_ok:
                return {
                    "status": "UEBERLIEFERT_OK",
                    "message": f"Überliefert ({prozent:.1f}%), aber im Rahmen der Toleranz",
                    "prozent": prozent,
                    "warning": True
                }
            else:
                return {
                    "status": "UEBERLIEFERT_WARNUNG",
                    "message": f"Überliefert ({prozent:.1f}%), Toleranz {toleranz}%",
                    "prozent": prozent,
                    "warning": True
                }
        else:
            return {
                "status": "UEBERLIEFERT_FEHLER",
                "message": f"Stark überliefert! ({prozent:.1f}% > {max_erlaubt}%)",
                "prozent": prozent,
                "error": True
            }


# ========================== HELPER FUNCTIONS ==========================

async def generate_kontraktnummer(mandant_id: str, vorgang_typ: str, db) -> str:
    """Generiert eine eindeutige Kontraktnummer aus dem Nummernkreis"""
    # Nutze den Nummernkreis
    try:
        return await get_naechste_nummer("kontrakte", vorgang_typ, mandant_id, db)
    except Exception:
        # Fallback falls Nummernkreis nicht verfügbar
        prefix = "EKK" if vorgang_typ == "EK" else "VKK"
        year = datetime.now().year
        count = await db.kontrakte.count_documents({
            "mandant_id": mandant_id,
            "kontraktnummer": {"$regex": f"^{prefix}{year}"}
        })
        return f"{prefix}{year}{count + 1:04d}"


async def berechne_kontrakt_summen(kontrakt: dict) -> dict:
    """Berechnet Summen für einen Kontrakt"""
    positionen = kontrakt.get("positionen", [])
    
    summe_menge = 0
    summe_wert = 0
    summe_wert_fw = 0
    
    for pos in positionen:
        if pos.get("deleted"):
            continue
        if pos.get("position_typ") == "ARTIKEL":
            anzahl = pos.get("anzahl", 0) or 0
            preis = pos.get("einzelpreis", 0) or 0
            preis_fw = pos.get("einzelpreis_fw", 0) or 0
            
            summe_menge += anzahl
            summe_wert += anzahl * preis
            summe_wert_fw += anzahl * preis_fw
    
    return {
        "summe_menge": summe_menge,
        "summe_wert": round(summe_wert, 2),
        "summe_wert_fw": round(summe_wert_fw, 2),
        "anzahl_positionen": len([p for p in positionen if not p.get("deleted")])
    }


# ========================== ROUTES ==========================

@router.get("/kontrakte")
async def get_kontrakte(
    suche: Optional[str] = None,
    typ: Optional[str] = None,
    status: Optional[str] = None,
    nur_fixierung: bool = False,
    page: int = 1,
    limit: int = 20,
    user = Depends(require_permission("kontrakte", "read"))
):
    """Kontrakte suchen und auflisten"""
    db = get_db()
    query = {"mandant_id": user["mandant_id"], "deleted": {"$ne": True}}
    
    if suche:
        query["$or"] = [
            {"name1": {"$regex": suche, "$options": "i"}},
            {"kontraktnummer": {"$regex": suche, "$options": "i"}},
            {"ort": {"$regex": suche, "$options": "i"}},
        ]
    if typ:
        query["vorgang_typ"] = typ
    if status:
        query["status"] = status
    if nur_fixierung:
        query["ist_fixierung"] = True
    
    total = await db.kontrakte.count_documents(query)
    cursor = db.kontrakte.find(query).sort("erstellt_am", -1).skip((page - 1) * limit).limit(limit)
    kontrakte = await cursor.to_list(length=limit)
    
    for k in kontrakte:
        k["id"] = k.pop("_id")
        # Summen berechnen
        summen = await berechne_kontrakt_summen(k)
        k["summen"] = summen
    
    return {
        "success": True,
        "data": kontrakte,
        "pagination": {"page": page, "limit": limit, "total": total}
    }


@router.get("/kontrakte/lookup/benutzer")
async def get_benutzer_fuer_auswahl(
    user = Depends(require_permission("kontrakte", "read"))
):
    """Benutzer für Sachbearbeiter/Händler-Auswahl laden"""
    db = get_db()
    
    cursor = db.benutzer.find(
        {"mandant_id": user["mandant_id"], "aktiv": {"$ne": False}},
        {"_id": 1, "benutzername": 1, "vorname": 1, "nachname": 1, "email": 1, "telefon": 1, "rolle_name": 1}
    ).sort("nachname", 1)
    
    benutzer = await cursor.to_list(length=100)
    
    result = []
    for b in benutzer:
        name = f"{b.get('vorname', '')} {b.get('nachname', '')}".strip()
        if not name:
            name = b.get("benutzername", "")
        result.append({
            "id": b["_id"],
            "name": name,
            "email": b.get("email"),
            "telefon": b.get("telefon"),
            "rolle": b.get("rolle_name")
        })
    
    return {"success": True, "data": result}


@router.get("/kontrakte/lookup/adressen")
async def get_adressen_fuer_auswahl(
    suche: Optional[str] = None,
    limit: int = 50,
    user = Depends(require_permission("kontrakte", "read"))
):
    """Aktive Adressen für Vertragspartner-Auswahl laden (inkl. Bankverbindungen & USt-IDs)"""
    db = get_db()
    
    query = {
        "mandant_id": user["mandant_id"],
        "aktiv": True,
        "deleted": {"$ne": True}
    }
    
    if suche:
        query["$or"] = [
            {"name1": {"$regex": suche, "$options": "i"}},
            {"name2": {"$regex": suche, "$options": "i"}},
            {"kundennummer": {"$regex": suche, "$options": "i"}},
            {"ort": {"$regex": suche, "$options": "i"}},
        ]
    
    cursor = db.adressen.find(query).sort("name1", 1).limit(limit)
    adressen = await cursor.to_list(length=limit)
    
    result = []
    for a in adressen:
        # USt-IDs sammeln: Haupt-ID + weitere
        ust_ids = []
        haupt_ust_id = a.get("umsatzsteuer_id") or a.get("ust_id")
        haupt_ust_lkz = a.get("umsatzsteuer_lkz", "")
        if haupt_ust_id:
            full_haupt_id = f"{haupt_ust_lkz}{haupt_ust_id}" if haupt_ust_lkz and not haupt_ust_id.startswith(haupt_ust_lkz) else haupt_ust_id
            ust_ids.append({
                "id": "main",
                "ust_id": full_haupt_id,
                "ist_hauptid": True
            })
        # Weitere USt-IDs aus dem Array
        for idx, weitere in enumerate(a.get("weitere_ustids", [])):
            if isinstance(weitere, dict):
                # Kombiniere lkz + ustid
                lkz = weitere.get("lkz", "")
                ustid_val = weitere.get("ustid", "") or weitere.get("ust_id", "")
                if ustid_val:
                    full_id = f"{lkz}{ustid_val}" if lkz and not ustid_val.startswith(lkz) else ustid_val
                    ust_ids.append({
                        "id": weitere.get("id", f"extra_{idx}"),
                        "ust_id": full_id,
                        "ist_hauptid": False
                    })
            elif isinstance(weitere, str) and weitere:
                ust_ids.append({
                    "id": f"extra_{idx}",
                    "ust_id": weitere,
                    "ist_hauptid": False
                })
        
        # Bankverbindungen aus dem eingebetteten Array der Adresse laden
        bankverbindungen_raw = a.get("bankverbindungen", [])
        bankverbindungen = []
        for bv in bankverbindungen_raw:
            if isinstance(bv, dict) and bv.get("aktiv", True):
                bankverbindungen.append({
                    "id": bv.get("id", ""),
                    "iban": bv.get("iban", ""),
                    "bic": bv.get("bic", ""),
                    "bank_name": bv.get("bank_name", ""),
                    "kontoinhaber": bv.get("kontoinhaber", ""),
                    "waehrung": bv.get("waehrung", "EUR"),
                    "ist_hauptkonto": bv.get("ist_hauptkonto", False)
                })
        
        result.append({
            "id": a["_id"],
            "name1": a.get("name1"),
            "name2": a.get("name2"),
            "strasse": a.get("strasse"),
            "hausnummer": a.get("hausnummer"),
            "plz": a.get("plz"),
            "ort": a.get("ort"),
            "land": a.get("land"),
            "land_code": a.get("land_code"),
            "ust_id": ust_ids[0]["ust_id"] if ust_ids else None,
            "steuernummer": a.get("steuernummer"),
            "telefon": a.get("telefon"),
            "telefax": a.get("telefax"),
            "email": a.get("email"),
            "kundennummer": a.get("kundennummer") or a.get("kdnr"),
            "ansprechpartner": a.get("ansprechpartner", []),
            "ust_ids": ust_ids,
            "bankverbindungen": bankverbindungen,
            "lieferadressen": a.get("lieferadressen", [])
        })
    
    return {"success": True, "data": result}


@router.get("/kontrakte/lookup/mandant")
async def get_mandant_adresse(user = Depends(require_permission("kontrakte", "read"))):
    """Firmenadresse für Lager-Auswahl laden (aus Firmeneinstellungen)"""
    db = get_db()
    
    # Firmendaten laden
    firma = await db.firma.find_one({"mandant_id": user["mandant_id"]})
    
    if firma:
        return {
            "success": True,
            "data": {
                "id": firma.get("id_adresse") or firma["_id"],
                "name1": firma.get("name1"),
                "name2": firma.get("name2"),
                "strasse": firma.get("strasse"),
                "hausnummer": firma.get("hausnummer"),
                "plz": firma.get("plz"),
                "ort": firma.get("ort"),
                "land": firma.get("land", "Deutschland"),
                "telefon": firma.get("telefon"),
                "email": firma.get("email"),
                "lieferadressen": firma.get("lieferadressen", [])
            }
        }
    
    # Fallback auf Mandant-Tabelle
    mandant = await db.mandanten.find_one({"_id": user["mandant_id"]})
    if not mandant:
        return {"success": True, "data": None}
    
    return {
        "success": True,
        "data": {
            "id": mandant["_id"],
            "name1": mandant.get("firma") or mandant.get("name"),
            "name2": mandant.get("zusatz"),
            "strasse": mandant.get("strasse"),
            "hausnummer": mandant.get("hausnummer"),
            "plz": mandant.get("plz"),
            "ort": mandant.get("ort"),
            "land": mandant.get("land", "Deutschland"),
            "telefon": mandant.get("telefon"),
            "email": mandant.get("email"),
            "lieferadressen": []
        }
    }


@router.get("/kontrakte/{kontrakt_id}")
async def get_kontrakt(kontrakt_id: str, user = Depends(require_permission("kontrakte", "read"))):
    """Einzelnen Kontrakt abrufen"""
    db = get_db()
    kontrakt = await db.kontrakte.find_one({
        "_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kontrakt:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    kontrakt["id"] = kontrakt.pop("_id")
    kontrakt["summen"] = await berechne_kontrakt_summen(kontrakt)
    
    return {"success": True, "data": kontrakt}


@router.post("/kontrakte")
async def create_kontrakt(
    data: KontraktCreate,
    skip_validation: bool = False,
    user = Depends(require_permission("kontrakte", "write"))
):
    """Neuen Kontrakt erstellen"""
    db = get_db()
    
    # Validierung
    if not skip_validation:
        result = await KontraktValidator.validate(data.model_dump(), db)
        if not result.is_valid:
            raise HTTPException(status_code=400, detail=result.to_dict())
    
    # Kontraktnummer aus Nummernkreis generieren
    kontraktnummer = data.kontraktnummer or await generate_kontraktnummer(user["mandant_id"], data.vorgang_typ, db)
    
    # Positionen mit IDs versehen
    positionen = []
    if data.positionen:
        for i, pos in enumerate(data.positionen):
            pos["id"] = str(uuid.uuid4())
            pos["positionsnummer"] = pos.get("positionsnummer", i + 1)
            positionen.append(pos)
    
    kontrakt = {
        "_id": str(uuid.uuid4()),
        "mandant_id": user["mandant_id"],
        "kontraktnummer": kontraktnummer,
        **data.model_dump(exclude={"kontraktnummer", "positionen"}),
        "positionen": positionen,
        "erstellt_am": datetime.utcnow(),
        "erstellt_von": user.get("username"),
        "deleted": False,
    }
    
    await db.kontrakte.insert_one(kontrakt)
    
    # Audit-Log: Kontrakt erstellt
    await audit_log_erstellen(
        kontrakt_id=kontrakt["_id"],
        mandant_id=user["mandant_id"],
        aktion="ERSTELLT",
        benutzer=user,
        details={
            "kontraktnummer": kontrakt.get("kontraktnummer"),
            "vorgang_typ": kontrakt.get("vorgang_typ"),
            "partner": kontrakt.get("name1"),
            "anzahl_positionen": len(positionen)
        }
    )
    
    kontrakt["id"] = kontrakt.pop("_id")
    kontrakt["summen"] = await berechne_kontrakt_summen(kontrakt)
    
    return {"success": True, "data": kontrakt}


@router.put("/kontrakte/{kontrakt_id}")
async def update_kontrakt(
    kontrakt_id: str,
    data: KontraktUpdate,
    skip_validation: bool = False,
    user = Depends(require_permission("kontrakte", "write"))
):
    """Kontrakt aktualisieren"""
    db = get_db()
    
    # Prüfen ob Bearbeitung erlaubt
    edit_result = await KontraktValidator.validate_kontrakt_edit(kontrakt_id, db)
    if not edit_result.is_valid:
        raise HTTPException(status_code=400, detail=edit_result.to_dict())
    
    existing = await db.kontrakte.find_one({
        "_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not existing:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    update_data = {k: v for k, v in data.model_dump().items() if v is not None}
    
    # Positionen mit IDs versehen falls neue
    if "positionen" in update_data and update_data["positionen"]:
        for i, pos in enumerate(update_data["positionen"]):
            if not pos.get("id"):
                pos["id"] = str(uuid.uuid4())
            pos["positionsnummer"] = pos.get("positionsnummer", i + 1)
    
    # Validierung
    if not skip_validation and update_data:
        merged = {**existing, **update_data}
        result = await KontraktValidator.validate(merged, db, kontrakt_id)
        if not result.is_valid:
            raise HTTPException(status_code=400, detail=result.to_dict())
    
    # Änderungen berechnen für Audit-Log
    aenderungen = berechne_aenderungen(existing, update_data)
    
    # Spezielle Aktionen erkennen
    aktion = "BEARBEITET"
    details = {}
    
    if "status" in update_data and update_data["status"] != existing.get("status"):
        aktion = "STATUS_GEAENDERT"
        details["alter_status"] = existing.get("status")
        details["neuer_status"] = update_data["status"]
    elif "id_adresse" in update_data and update_data["id_adresse"] != existing.get("id_adresse"):
        aktion = "PARTNER_GEAENDERT"
        details["alter_partner"] = existing.get("name1")
        details["neuer_partner"] = update_data.get("name1")
    elif ("id_abhollager" in update_data or "id_ziellager" in update_data):
        if update_data.get("id_abhollager") != existing.get("id_abhollager") or update_data.get("id_ziellager") != existing.get("id_ziellager"):
            aktion = "LAGER_GEAENDERT"
    
    update_data["letzte_aenderung"] = datetime.utcnow()
    update_data["geaendert_von"] = user.get("username")
    
    await db.kontrakte.update_one({"_id": kontrakt_id}, {"$set": update_data})
    
    # Audit-Log: Kontrakt bearbeitet (nur wenn Änderungen vorhanden)
    if aenderungen or aktion != "BEARBEITET":
        await audit_log_erstellen(
            kontrakt_id=kontrakt_id,
            mandant_id=user["mandant_id"],
            aktion=aktion,
            benutzer=user,
            details=details,
            aenderungen=aenderungen
        )
    
    updated = await db.kontrakte.find_one({"_id": kontrakt_id})
    updated["id"] = updated.pop("_id")
    updated["summen"] = await berechne_kontrakt_summen(updated)
    
    return {"success": True, "data": updated}


@router.delete("/kontrakte/{kontrakt_id}")
async def delete_kontrakt(kontrakt_id: str, user = Depends(require_permission("kontrakte", "full"))):
    """Kontrakt löschen"""
    db = get_db()
    
    kontrakt = await db.kontrakte.find_one({
        "_id": kontrakt_id, 
        "mandant_id": user["mandant_id"]
    })
    
    if not kontrakt:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    # Prüfen ob Positionen zugeordnete Fuhren haben
    for pos in kontrakt.get("positionen", []):
        pos_id = pos.get("id")
        if pos_id:
            result = await KontraktValidator.validate_position_delete(kontrakt_id, pos_id, db)
            if not result.is_valid:
                raise HTTPException(status_code=400, detail=result.to_dict())
    
    # Hard Delete
    await db.kontrakte.delete_one({"_id": kontrakt_id, "mandant_id": user["mandant_id"]})
    
    return {"success": True, "message": "Kontrakt gelöscht"}


@router.post("/kontrakte/{kontrakt_id}/positionen")
async def add_position(
    kontrakt_id: str,
    data: KontraktPositionCreate,
    user = Depends(require_permission("kontrakte", "write"))
):
    """Position zum Kontrakt hinzufügen"""
    db = get_db()
    
    # Prüfen ob Bearbeitung erlaubt
    edit_result = await KontraktValidator.validate_kontrakt_edit(kontrakt_id, db)
    if not edit_result.is_valid:
        raise HTTPException(status_code=400, detail=edit_result.to_dict())
    
    kontrakt = await db.kontrakte.find_one({
        "_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kontrakt:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    # Nächste Positionsnummer ermitteln
    existing_positions = kontrakt.get("positionen", [])
    max_pos_nr = max([p.get("positionsnummer", 0) for p in existing_positions], default=0)
    
    position = {
        "id": str(uuid.uuid4()),
        **data.model_dump(),
        "positionsnummer": data.positionsnummer or max_pos_nr + 1,
        "erstellt_am": datetime.utcnow().isoformat()
    }
    
    # Gesamtpreis berechnen
    if position.get("anzahl") and position.get("einzelpreis"):
        position["gesamtpreis"] = round(position["anzahl"] * position["einzelpreis"], 2)
    if position.get("anzahl") and position.get("einzelpreis_fw"):
        position["gesamtpreis_fw"] = round(position["anzahl"] * position["einzelpreis_fw"], 2)
    
    await db.kontrakte.update_one(
        {"_id": kontrakt_id},
        {"$push": {"positionen": position}}
    )
    
    # Audit-Log: Position hinzugefügt
    await audit_log_erstellen(
        kontrakt_id=kontrakt_id,
        mandant_id=user["mandant_id"],
        aktion="POSITION_HINZUGEFUEGT",
        benutzer=user,
        details={
            "position_id": position["id"],
            "positionsnummer": position.get("positionsnummer"),
            "artikel": position.get("artbez1"),
            "artikel_nr": position.get("anr1"),
            "menge": position.get("anzahl"),
            "einheit": position.get("einheitkurz"),
            "einzelpreis": position.get("einzelpreis"),
            "gesamtpreis": position.get("gesamtpreis")
        }
    )
    
    return {"success": True, "data": position}


@router.put("/kontrakte/{kontrakt_id}/positionen/{position_id}")
async def update_position(
    kontrakt_id: str,
    position_id: str,
    data: dict,
    user = Depends(require_permission("kontrakte", "write"))
):
    """Position aktualisieren"""
    db = get_db()
    
    # Prüfen ob Bearbeitung erlaubt
    edit_result = await KontraktValidator.validate_kontrakt_edit(kontrakt_id, db)
    if not edit_result.is_valid:
        raise HTTPException(status_code=400, detail=edit_result.to_dict())
    
    # Gesamtpreis neu berechnen
    if data.get("anzahl") and data.get("einzelpreis"):
        data["gesamtpreis"] = round(data["anzahl"] * data["einzelpreis"], 2)
    if data.get("anzahl") and data.get("einzelpreis_fw"):
        data["gesamtpreis_fw"] = round(data["anzahl"] * data["einzelpreis_fw"], 2)
    
    # Update position in array
    update_fields = {f"positionen.$.{k}": v for k, v in data.items() if v is not None}
    
    result = await db.kontrakte.update_one(
        {"_id": kontrakt_id, "positionen.id": position_id, "mandant_id": user["mandant_id"]},
        {"$set": update_fields}
    )
    
    if result.modified_count == 0:
        raise HTTPException(status_code=404, detail="Position nicht gefunden")
    
    return {"success": True, "message": "Position aktualisiert"}


@router.delete("/kontrakte/{kontrakt_id}/positionen/{position_id}")
async def delete_position(
    kontrakt_id: str,
    position_id: str,
    user = Depends(require_permission("kontrakte", "write"))
):
    """Position löschen"""
    db = get_db()
    
    # Validieren ob Löschung erlaubt
    result = await KontraktValidator.validate_position_delete(kontrakt_id, position_id, db)
    if not result.is_valid:
        raise HTTPException(status_code=400, detail=result.to_dict())
    
    await db.kontrakte.update_one(
        {"_id": kontrakt_id, "mandant_id": user["mandant_id"]},
        {"$pull": {"positionen": {"id": position_id}}}
    )
    
    return {"success": True, "message": "Position gelöscht"}


@router.post("/kontrakte/{kontrakt_id}/abschliessen")
async def abschliessen_kontrakt(
    kontrakt_id: str,
    user = Depends(require_permission("kontrakte", "write"))
):
    """Kontrakt abschließen"""
    db = get_db()
    
    kontrakt = await db.kontrakte.find_one({
        "_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kontrakt:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    if kontrakt.get("abgeschlossen"):
        raise HTTPException(status_code=400, detail="Kontrakt ist bereits abgeschlossen")
    
    await db.kontrakte.update_one(
        {"_id": kontrakt_id},
        {"$set": {
            "abgeschlossen": True,
            "status": "ERFUELLT",
            "abgeschlossen_am": datetime.utcnow(),
            "abgeschlossen_von": user.get("username")
        }}
    )
    
    return {"success": True, "message": "Kontrakt abgeschlossen"}


@router.post("/kontrakte/{kontrakt_id}/stornieren")
async def stornieren_kontrakt(
    kontrakt_id: str,
    grund: str = Query(None, description="Stornierungsgrund"),
    user = Depends(require_permission("kontrakte", "full"))
):
    """Kontrakt stornieren"""
    db = get_db()
    
    await db.kontrakte.update_one(
        {"_id": kontrakt_id, "mandant_id": user["mandant_id"]},
        {"$set": {
            "status": "STORNO",
            "storno_grund": grund,
            "storniert_am": datetime.utcnow(),
            "storniert_von": user.get("username")
        }}
    )
    
    return {"success": True, "message": "Kontrakt storniert"}


@router.post("/kontrakte/validieren")
async def validate_kontrakt(
    data: dict,
    user = Depends(require_permission("kontrakte", "read"))
):
    """Kontrakt validieren ohne zu speichern"""
    db = get_db()
    result = await KontraktValidator.validate(data, db)
    return {"success": True, "validierung": result.to_dict()}


@router.get("/kontrakte/{kontrakt_id}/lieferstatus")
async def get_lieferstatus(
    kontrakt_id: str,
    user = Depends(require_permission("kontrakte", "read"))
):
    """Lieferstatus aller Positionen abrufen"""
    db = get_db()
    
    kontrakt = await db.kontrakte.find_one({
        "_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kontrakt:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    positionen_status = []
    for pos in kontrakt.get("positionen", []):
        pos_id = pos.get("id")
        
        # Gelieferte Menge aus Fuhren ermitteln
        pipeline = [
            {"$match": {
                "$or": [
                    {"id_vpos_kon_ek": pos_id},
                    {"id_vpos_kon_vk": pos_id}
                ],
                "deleted": {"$ne": True}
            }},
            {"$group": {
                "_id": None,
                "gelieferte_menge": {"$sum": "$menge_netto"}
            }}
        ]
        
        result = await db.fuhren.aggregate(pipeline).to_list(1)
        gelieferte_menge = result[0]["gelieferte_menge"] if result else 0
        
        status = KontraktValidator.calculate_lieferstatus(pos, gelieferte_menge)
        
        positionen_status.append({
            "position_id": pos_id,
            "positionsnummer": pos.get("positionsnummer"),
            "artikel": pos.get("artbez1"),
            "soll_menge": pos.get("anzahl", 0),
            "gelieferte_menge": gelieferte_menge,
            **status
        })
    
    return {"success": True, "data": positionen_status}


# ========================== AUDIT LOG ENDPOINTS ==========================

@router.get("/kontrakte/{kontrakt_id}/audit-log")
async def get_kontrakt_audit_log(
    kontrakt_id: str,
    limit: int = Query(100, ge=1, le=500),
    skip: int = Query(0, ge=0),
    aktion_filter: Optional[str] = Query(None, description="Filter nach Aktionstyp"),
    user = Depends(require_permission("kontrakte", "read"))
):
    """Audit-Log für einen Kontrakt abrufen"""
    db = get_db()
    
    # Prüfen ob Kontrakt existiert und Zugriff erlaubt
    kontrakt = await db.kontrakte.find_one({
        "_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kontrakt:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    # Query aufbauen
    query = {
        "kontrakt_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    }
    
    if aktion_filter:
        query["aktion"] = aktion_filter
    
    # Audit-Logs laden (neueste zuerst)
    cursor = db.kontrakt_audit_log.find(query).sort("zeitstempel", -1).skip(skip).limit(limit)
    logs = await cursor.to_list(limit)
    
    # Gesamtanzahl für Pagination
    total = await db.kontrakt_audit_log.count_documents(query)
    
    # _id in id umwandeln
    for log in logs:
        log["id"] = log.pop("_id")
    
    return {
        "success": True,
        "data": logs,
        "total": total,
        "limit": limit,
        "skip": skip,
        "aktionen_typen": AKTION_TYPEN
    }


@router.post("/kontrakte/{kontrakt_id}/audit-log")
async def add_audit_log_entry(
    kontrakt_id: str,
    aktion: str = Query(..., description="Aktionstyp"),
    details: Optional[Dict[str, Any]] = None,
    user = Depends(require_permission("kontrakte", "read"))
):
    """Manuellen Audit-Log Eintrag hinzufügen (z.B. für Druck, Export, Ansicht)"""
    db = get_db()
    
    # Prüfen ob Kontrakt existiert
    kontrakt = await db.kontrakte.find_one({
        "_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kontrakt:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    # Nur bestimmte Aktionen erlauben (keine Manipulation von Bearbeitungs-Logs)
    erlaubte_aktionen = ["GEDRUCKT", "EXPORTIERT", "GEOEFFNET"]
    if aktion not in erlaubte_aktionen:
        raise HTTPException(status_code=400, detail=f"Aktion '{aktion}' nicht erlaubt. Erlaubt: {erlaubte_aktionen}")
    
    log = await audit_log_erstellen(
        kontrakt_id=kontrakt_id,
        mandant_id=user["mandant_id"],
        aktion=aktion,
        benutzer=user,
        details=details or {}
    )
    
    log["id"] = log.pop("_id")
    
    return {"success": True, "data": log}


@router.get("/kontrakte/{kontrakt_id}/audit-log/statistik")
async def get_audit_log_statistik(
    kontrakt_id: str,
    user = Depends(require_permission("kontrakte", "read"))
):
    """Statistik über Audit-Log Einträge"""
    db = get_db()
    
    # Prüfen ob Kontrakt existiert
    kontrakt = await db.kontrakte.find_one({
        "_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    if not kontrakt:
        raise HTTPException(status_code=404, detail="Kontrakt nicht gefunden")
    
    # Aggregation für Statistiken
    pipeline = [
        {"$match": {"kontrakt_id": kontrakt_id, "mandant_id": user["mandant_id"]}},
        {"$group": {
            "_id": "$aktion",
            "anzahl": {"$sum": 1},
            "letzter": {"$max": "$zeitstempel"}
        }},
        {"$sort": {"anzahl": -1}}
    ]
    
    stats = await db.kontrakt_audit_log.aggregate(pipeline).to_list(100)
    
    # Beteiligte Benutzer
    benutzer_pipeline = [
        {"$match": {"kontrakt_id": kontrakt_id, "mandant_id": user["mandant_id"]}},
        {"$group": {
            "_id": "$benutzer_id",
            "name": {"$first": "$benutzer_name"},
            "kuerzel": {"$first": "$benutzer_kuerzel"},
            "aktionen": {"$sum": 1},
            "erste_aktion": {"$min": "$zeitstempel"},
            "letzte_aktion": {"$max": "$zeitstempel"}
        }},
        {"$sort": {"aktionen": -1}}
    ]
    
    benutzer = await db.kontrakt_audit_log.aggregate(benutzer_pipeline).to_list(50)
    
    # Gesamtanzahl
    total = await db.kontrakt_audit_log.count_documents({
        "kontrakt_id": kontrakt_id,
        "mandant_id": user["mandant_id"]
    })
    
    return {
        "success": True,
        "data": {
            "total_eintraege": total,
            "nach_aktion": stats,
            "beteiligte_benutzer": benutzer,
            "aktionen_typen": AKTION_TYPEN
        }
    }
