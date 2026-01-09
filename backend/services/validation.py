"""
Validation Service - Business Logic Validators
Portiert aus Java/Echo2:
- AS_MAP_ValidatorEinheiten.java
- ARTIKEL_MAP_SET_AND_VALID_TYPEN.java
- BSK_P_MASK_MapValidator_CheckMengeInLieferdaten.java
- FU_MAP_SET_AND_VALID__Check_AVV_CodeAllowed.java
- __FU_Pruefer_auf_AVV_UND_NOTI.java
- __FS_Adress_Check.java (Steuer-Validierung)
"""

from typing import List, Dict, Any, Optional
from datetime import datetime, date


class ValidationResult:
    """Ergebnis einer Validierung"""
    def __init__(self):
        self.errors: List[str] = []  # Fehler (verhindern Speichern)
        self.warnings: List[str] = []  # Warnungen (erlauben Speichern)
    
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


class ArtikelValidator:
    """
    Artikel-Validierung basierend auf Java Echo2:
    - AS_MAP_ValidatorEinheiten.java
    - ARTIKEL_MAP_SET_AND_VALID_TYPEN.java
    """
    
    @staticmethod
    async def validate(artikel: dict, db_ref) -> ValidationResult:
        result = ValidationResult()
        
        # === Typ-Validierung ===
        ist_gefahrgut = artikel.get("gefahrgut", False)
        ist_produkt = artikel.get("ist_produkt", False)
        ist_dienstleistung = artikel.get("dienstleistung", False)
        ist_end_of_waste = artikel.get("end_of_waste", False)
        ist_leergut = artikel.get("ist_leergut", False)
        
        # Sinnlose Kombinationen prüfen (aus ARTIKEL_MAP_SET_AND_VALID_TYPEN.java)
        if ist_gefahrgut and ist_leergut:
            result.add_error("Leergut kann kein Gefahrgut sein!")
        
        if ist_gefahrgut and ist_dienstleistung:
            result.add_error("Eine Dienstleistung kann kein Gefahrgut sein!")
        
        # Nur eine der Kategorien: Produkt, Dienstleistung oder End of Waste
        type_count = sum([ist_produkt, ist_dienstleistung, ist_end_of_waste])
        if type_count > 1:
            result.add_error("Eine Sorte kann nur Produkt, Dienstleistung oder 'End of Waste' sein, keine Kombination!")
        
        # === Einheiten-Validierung (aus AS_MAP_ValidatorEinheiten.java) ===
        einheit = artikel.get("einheit", "")
        einheit_preis = artikel.get("einheit_preis", einheit)  # Wenn leer, gleich Mengeneinheit
        mengendivisor = artikel.get("mengendivisor", 1)
        
        if not mengendivisor or mengendivisor < 1:
            result.add_error("Einheiten und Mengendivisor MÜSSEN gesetzt werden!")
        else:
            # Wenn Einheit == Preiseinheit, muss Divisor 1 sein
            if einheit == einheit_preis and mengendivisor != 1:
                result.add_error("Wenn die Einheit = Preiseinheit ist, dann MUSS der Divisor 1 sein!")
            # Wenn Einheit != Preiseinheit, darf Divisor NICHT 1 sein
            elif einheit != einheit_preis and mengendivisor == 1:
                result.add_error("Wenn die Einheit <> Preiseinheit ist, dann DARF der Divisor NICHT 1 sein!")
        
        # === Nummern/Codes-Validierung (aus pruefe_NummernCodes) ===
        zolltarifnummer = (artikel.get("zolltarifnr") or "").strip()
        basel_code = (artikel.get("basel_code") or "").strip()
        basel_notiz = (artikel.get("basel_notiz") or "").strip()
        oecd_code = (artikel.get("oecd_code") or "").strip()
        oecd_notiz = (artikel.get("oecd_notiz") or "").strip()
        avv_code_eingang = artikel.get("avv_code_eingang")
        avv_code_ausgang = artikel.get("avv_code_ausgang")
        
        # Rohstoff ohne Zolltarifnummer nur bei Produkt/Dienstleistung/EndOfWaste erlaubt
        if not zolltarifnummer:
            if not (ist_produkt or ist_dienstleistung or ist_end_of_waste):
                result.add_warning("Eine Sorte darf nur ohne Zolltarifnummer sein, wenn er ein Produkt, 'End of Waste' oder eine Dienstleistung ist!")
        
        # Produkt/Dienstleistung/EndOfWaste dürfen keine OECD/Basel/AVV-Codes haben
        if ist_produkt or ist_dienstleistung or ist_end_of_waste:
            if basel_code or basel_notiz or oecd_code or oecd_notiz or avv_code_eingang or avv_code_ausgang:
                result.add_error("Ein Artikel mit Merkmal Produkt, Dienstleistung oder 'End of Waste' darf keinen OECD-, Basel- oder AVV-Code tragen!")
        
        return result


class KontraktValidator:
    """
    Kontrakt-Validierung basierend auf Java Echo2:
    - BSK_P_MASK_MapValidator_CheckMengeInLieferdaten.java
    - BSK__CONST.java (Validatoren)
    """
    
    @staticmethod
    async def validate(kontrakt: dict, db_ref) -> ValidationResult:
        result = ValidationResult()
        
        # === Pflichtfelder prüfen ===
        name1 = (kontrakt.get("name1") or "").strip()
        if not name1:
            result.add_error("Vertragspartner (Name1) ist ein Pflichtfeld!")
        
        # === Gültigkeitsdaten prüfen ===
        gueltig_von = kontrakt.get("gueltig_von")
        gueltig_bis = kontrakt.get("gueltig_bis")
        
        if gueltig_von and gueltig_bis:
            try:
                von_date = datetime.fromisoformat(gueltig_von.replace('Z', '+00:00')) if isinstance(gueltig_von, str) else gueltig_von
                bis_date = datetime.fromisoformat(gueltig_bis.replace('Z', '+00:00')) if isinstance(gueltig_bis, str) else gueltig_bis
                
                if bis_date < von_date:
                    result.add_error("'Gültig bis' darf nicht vor 'Gültig von' liegen!")
            except:
                pass  # Fehlerhafte Datumsformate werden ignoriert
        
        # === Status-Prüfung ===
        status = kontrakt.get("status", "OFFEN")
        abgeschlossen = kontrakt.get("abgeschlossen", False)
        
        if abgeschlossen and status == "OFFEN":
            result.add_warning("Kontrakt ist als abgeschlossen markiert, Status ist aber noch 'OFFEN'")
        
        # === Positionen-Prüfung (falls vorhanden) ===
        positionen = kontrakt.get("positionen", [])
        if positionen:
            for i, pos in enumerate(positionen):
                pos_menge = pos.get("menge") or pos.get("anzahl")
                pos_preis = pos.get("einzelpreis")
                
                # Position ohne Menge oder Preis
                if pos_menge is None or pos_preis is None:
                    result.add_warning(f"Position {i+1}: Menge und Einzelpreis sollten ausgefüllt sein")
        
        return result
    
    @staticmethod
    async def validate_position(position: dict, kontrakt: dict, db_ref) -> ValidationResult:
        """Validierung einer Kontrakt-Position"""
        result = ValidationResult()
        
        # Prüfen ob Kontrakt noch offen ist (aus VALID_VPOS_KON_KOPF_OFFEN)
        if kontrakt.get("abgeschlossen", False):
            result.add_error("Aktion ist verboten, da der Kontrakt-Kopf bereits geschlossen ist!")
        
        # Prüfen ob Kontrakt bereits Fuhren hat (aus VALID_VPOS_KON_KEINEN_FUHREN_ZUGEORDNET)
        kontrakt_id = kontrakt.get("id") or kontrakt.get("_id")
        if kontrakt_id:
            fuhren_count = await db_ref.fuhren.count_documents({
                "id_kontrakt": kontrakt_id,
                "deleted": {"$ne": True}
            })
            if fuhren_count > 0:
                result.add_warning(f"Achtung: Dieser Kontrakt hat bereits {fuhren_count} zugeordnete Fuhre(n)")
        
        return result


class FuhreValidator:
    """
    Fuhren-Validierung basierend auf Java Echo2:
    - FU_MAP_SET_AND_VALID__Check_AVV_CodeAllowed.java
    - __FU_Pruefer_auf_AVV_UND_NOTI.java
    """
    
    @staticmethod
    async def validate(fuhre: dict, db_ref) -> ValidationResult:
        result = ValidationResult()
        
        # === Pflichtfelder prüfen ===
        id_adresse_start = fuhre.get("id_adresse_start")
        id_adresse_ziel = fuhre.get("id_adresse_ziel")
        id_artikel = fuhre.get("id_artikel")
        
        if not id_adresse_start:
            result.add_error("Lieferant (Start-Adresse) ist ein Pflichtfeld!")
        
        if not id_adresse_ziel:
            result.add_error("Abnehmer (Ziel-Adresse) ist ein Pflichtfeld!")
        
        if not id_artikel:
            result.add_error("Artikel/Sorte ist ein Pflichtfeld!")
        
        # === Datum-Validierung ===
        datum_abholung = fuhre.get("datum_abholung")
        datum_anlieferung = fuhre.get("datum_anlieferung")
        
        if datum_abholung and datum_anlieferung:
            try:
                abholung = datetime.fromisoformat(datum_abholung) if isinstance(datum_abholung, str) else datum_abholung
                anlieferung = datetime.fromisoformat(datum_anlieferung) if isinstance(datum_anlieferung, str) else datum_anlieferung
                
                if anlieferung < abholung:
                    result.add_error("Anlieferungsdatum darf nicht vor dem Abholdatum liegen!")
            except:
                pass
        
        # === Artikel-bezogene Validierung ===
        if id_artikel:
            artikel = await db_ref.artikel.find_one({"_id": id_artikel})
            if artikel:
                ist_produkt = artikel.get("ist_produkt", False)
                ist_dienstleistung = artikel.get("dienstleistung", False)
                ist_end_of_waste = artikel.get("end_of_waste", False)
                avv_code = fuhre.get("avv_code") or artikel.get("avv_code_eingang")
                
                # Rohstoffe ohne AVV-Code sind nicht zulässig (aus __FU_Pruefer_auf_AVV_UND_NOTI.java)
                if not ist_produkt and not ist_dienstleistung and not ist_end_of_waste:
                    if not avv_code:
                        result.add_error("Achtung! Rohstoffe ohne AVV-Code sind nicht zulässig!")
        
        # === Mengen-Validierung ===
        menge_vorgabe = fuhre.get("menge_vorgabe") or 0
        menge_aufladen = fuhre.get("menge_aufladen") or 0
        menge_abladen = fuhre.get("menge_abladen") or 0
        
        # Warnung wenn Abweichung zwischen Lade- und Ablademenge > 5%
        if menge_aufladen > 0 and menge_abladen > 0:
            abweichung = abs(menge_aufladen - menge_abladen) / menge_aufladen * 100
            if abweichung > 5:
                result.add_warning(f"Abweichung zwischen Lade- und Ablademenge beträgt {abweichung:.1f}%")
        
        # === Status-Workflow-Validierung ===
        status = fuhre.get("status", "OFFEN")
        ist_storniert = fuhre.get("ist_storniert", False)
        
        if ist_storniert and status != "STORNIERT":
            result.add_warning("Fuhre ist als storniert markiert, aber Status ist nicht 'STORNIERT'")
        
        # === AVV-Code-Prüfung bei Lager (aus FU_MAP_SET_AND_VALID__Check_AVV_CodeAllowed.java) ===
        id_adresse_lager_ziel = fuhre.get("id_adresse_lager_ziel")
        avv_code_fuhre = fuhre.get("avv_code")
        
        if id_adresse_lager_ziel and avv_code_fuhre:
            # Prüfen ob AVV-Code für dieses Lager erlaubt ist
            lager_adresse = await db_ref.adressen.find_one({"_id": id_adresse_lager_ziel})
            if lager_adresse:
                erlaubte_avv_codes = lager_adresse.get("erlaubte_avv_codes", [])
                if erlaubte_avv_codes and avv_code_fuhre not in erlaubte_avv_codes:
                    result.add_error(f"Kombination AVV-Code und Abladeadresse ist nicht erlaubt!")
        
        return result
    
    @staticmethod
    async def validate_status_transition(old_status: str, new_status: str) -> ValidationResult:
        """Prüft ob Statusübergang erlaubt ist"""
        result = ValidationResult()
        
        # Erlaubte Statusübergänge
        allowed_transitions = {
            "OFFEN": ["IN_TRANSPORT", "STORNIERT"],
            "IN_TRANSPORT": ["GELIEFERT", "STORNIERT"],
            "GELIEFERT": ["ABGERECHNET", "STORNIERT"],
            "ABGERECHNET": [],  # Endstatus
            "STORNIERT": [],    # Endstatus
        }
        
        if old_status == new_status:
            return result  # Keine Änderung = kein Problem
        
        allowed = allowed_transitions.get(old_status, [])
        if new_status not in allowed:
            result.add_error(f"Statusübergang von '{old_status}' nach '{new_status}' ist nicht erlaubt!")
        
        return result



class AdresseValidator:
    """
    Adress-Validierung basierend auf Java Echo2:
    - __FS_Adress_Check.java (Steuer-Einstufungslogik)
    - FS_MASK_SQLFieldMap_ADRESSE.java (Pflichtfelder)
    - FS_VALID_PRIVAT_GESCHAEFTSKUNDEN.java
    """
    
    # Heimatland des Mandanten (konfigurierbar)
    HOMELAND = "Deutschland"
    
    # EU-Länder mit UST-Präfix (aus RECORD_LAND)
    EU_LAENDER: Dict[str, str] = {
        "Deutschland": "DE",
        "Österreich": "AT",
        "Schweiz": "CH",  # Nicht EU, aber hat UST-Präfix
        "Niederlande": "NL",
        "Belgien": "BE",
        "Frankreich": "FR",
        "Italien": "IT",
        "Spanien": "ES",
        "Polen": "PL",
        "Tschechien": "CZ",
        "Dänemark": "DK",
        "Schweden": "SE",
        "Finnland": "FI",
        "Griechenland": "GR",
        "Portugal": "PT",
        "Irland": "IE",
        "Luxemburg": "LU",
        "Ungarn": "HU",
        "Rumänien": "RO",
        "Bulgarien": "BG",
        "Kroatien": "HR",
        "Slowakei": "SK",
        "Slowenien": "SI",
        "Estland": "EE",
        "Lettland": "LV",
        "Litauen": "LT",
        "Malta": "MT",
        "Zypern": "CY",
    }
    
    # Nicht-EU-Länder (ohne UST-Pflicht)
    NICHT_EU_LAENDER = ["Schweiz", "Norwegen", "Großbritannien", "USA", "China", "Russland"]
    
    @staticmethod
    async def validate(adresse: dict, db_ref=None) -> ValidationResult:
        """
        Vollständige Validierung einer Adresse.
        Kombiniert Pflichtfeld-Prüfung und Steuer-Einstufung.
        """
        result = ValidationResult()
        
        # 1. Pflichtfelder prüfen
        pflichtfeld_result = AdresseValidator.validate_pflichtfelder(adresse)
        result.errors.extend(pflichtfeld_result.errors)
        result.warnings.extend(pflichtfeld_result.warnings)
        
        # 2. Steuer-Einstufung prüfen
        steuer_result = AdresseValidator.validate_steuer_einstufung(adresse)
        result.errors.extend(steuer_result.errors)
        result.warnings.extend(steuer_result.warnings)
        
        # 3. Ausweis-Validierung
        ausweis_result = AdresseValidator.validate_ausweis(adresse)
        result.errors.extend(ausweis_result.errors)
        result.warnings.extend(ausweis_result.warnings)
        
        return result
    
    @staticmethod
    def validate_pflichtfelder(adresse: dict) -> ValidationResult:
        """
        Prüft Pflichtfelder aus FS_MASK_SQLFieldMap_ADRESSE.java:
        - NAME1, STRASSE, PLZ, ORT, ID_LAND, ID_SPRACHE, AKTIV, ID_WAEHRUNG
        """
        result = ValidationResult()
        
        # Pflichtfelder
        if not (adresse.get("name1") or "").strip():
            result.add_error("Firmenname/Name1 ist ein Pflichtfeld!")
        
        if not (adresse.get("strasse") or "").strip():
            result.add_error("Straße ist ein Pflichtfeld!")
        
        if not (adresse.get("plz") or "").strip():
            result.add_error("PLZ ist ein Pflichtfeld!")
        
        if not (adresse.get("ort") or "").strip():
            result.add_error("Ort ist ein Pflichtfeld!")
        
        if not (adresse.get("land") or "").strip():
            result.add_error("Land ist ein Pflichtfeld!")
        
        # Sprache und Währung haben Defaults, daher nur Warnung
        if not (adresse.get("sprache") or "").strip():
            result.add_warning("Sprache sollte gesetzt werden (Default: Deutsch)")
        
        if not (adresse.get("waehrung") or "").strip():
            result.add_warning("Währung sollte gesetzt werden (Default: EUR)")
        
        return result
    
    @staticmethod
    def validate_steuer_einstufung(adresse: dict) -> ValidationResult:
        """
        Validiert die steuerliche Einstufung einer Adresse.
        Portiert aus __FS_Adress_Check.java
        
        Kernlogik:
        - FIRMA oder PRIVAT (nie beides/keines)
        - Inland-PRIVAT braucht Ausweis ODER Steuernummer
        - Ausland-PRIVAT braucht Ausweis
        - Inland-FIRMA braucht UST-ID (oder Sonderschalter + Steuernummer)
        - EU-Ausland-FIRMA braucht UST-ID
        - UST-Länderkürzel muss zum Land passen
        """
        result = ValidationResult()
        
        # Flags auslesen
        ist_firma = adresse.get("ist_firma", True)
        ist_privat = not ist_firma
        land = adresse.get("land", "")
        
        # Standort-Bestimmung
        ist_inland = land == AdresseValidator.HOMELAND
        ist_eu = land in AdresseValidator.EU_LAENDER
        ist_nicht_eu = land in AdresseValidator.NICHT_EU_LAENDER or not ist_eu
        
        # UST-ID-Status
        ust_lkz = (adresse.get("umsatzsteuer_lkz") or "").strip()
        ust_id = (adresse.get("umsatzsteuer_id") or "").strip()
        hat_ustid = bool(ust_lkz and ust_id)
        hat_teilweise_ustid = bool(ust_lkz) != bool(ust_id)  # Nur eins von beiden
        
        # Weitere Felder
        steuernummer = (adresse.get("steuernummer") or "").strip()
        ausweis_nummer = (adresse.get("ausweis_nummer") or "").strip()
        hat_ausweis = bool(ausweis_nummer)
        hat_steuernr = bool(steuernummer)
        
        # Sonderschalter
        firma_ohne_ustid = adresse.get("firma_ohne_ustid", False)
        privat_mit_ustid = adresse.get("privat_mit_ustid", False)
        
        # Weitere UST-IDs (Array)
        weitere_ustids = adresse.get("weitere_ustids") or []
        hat_zusatz_ustid = len(weitere_ustids) > 0
        
        # =============================================
        # REGEL 1: Teilweise UST-ID ist nicht erlaubt
        # =============================================
        if hat_teilweise_ustid:
            result.add_error(
                "Die Basis-UST-ID ist nur teilweise ausgefüllt. "
                "Bitte komplettieren (Länderkürzel UND Nummer) oder komplett leeren!"
            )
        
        # =============================================
        # REGEL 2: UST-Länderkürzel muss zum Land passen
        # =============================================
        if hat_ustid and ist_eu:
            expected_prefix = AdresseValidator.EU_LAENDER.get(land)
            if expected_prefix and ust_lkz != expected_prefix:
                result.add_error(
                    f"Das UST-Länderkürzel '{ust_lkz}' stimmt nicht mit dem Land '{land}' überein "
                    f"(erwartet: {expected_prefix})!"
                )
        
        # =============================================
        # REGEL 3: Ausnahmeschalter nur im Inland sinnvoll
        # =============================================
        if not ist_inland and (firma_ohne_ustid or privat_mit_ustid):
            result.add_warning(
                f"Die Ausnahmeschalter 'FIRMA ohne UST-ID' und 'PRIVAT mit UST-ID' "
                f"sind nur bei Adressen in {AdresseValidator.HOMELAND} sinnvoll!"
            )
        
        # =============================================
        # REGEL 4: Beide Ausnahmeschalter gleichzeitig
        # =============================================
        if ist_inland and firma_ohne_ustid and privat_mit_ustid:
            result.add_error(
                "Die Ausnahmeschalter 'FIRMA ohne UST-ID' und 'PRIVAT mit UST-ID' "
                "können nicht gleichzeitig aktiv sein!"
            )
        
        # =============================================
        # REGELN FÜR PRIVATPERSONEN
        # =============================================
        if ist_privat:
            # PRIVAT mit UST-ID im Inland: nur mit Sonderschalter
            if ist_inland and hat_ustid and not privat_mit_ustid:
                result.add_error(
                    "Die Einstufung einer Adresse mit Basis-UST-ID als PRIVAT "
                    "ist nur mit dem Sonderschalter 'PRIVAT mit UST-ID' möglich!"
                )
            
            # PRIVAT im Ausland darf keine UST-ID haben
            if not ist_inland and hat_ustid:
                result.add_error(
                    "Bei als PRIVAT eingestuften Adressen im Ausland "
                    "darf keine UST-ID erfasst sein!"
                )
            
            # PRIVAT im Inland braucht Ausweis oder Steuernummer (außer PRIVAT_MIT_USTID)
            if ist_inland and not hat_ausweis and not hat_steuernr:
                if not (privat_mit_ustid and hat_ustid):
                    result.add_error(
                        "Bei als PRIVAT eingestuften Adressen im Inland "
                        "MUSS die Ausweisnummer oder die Steuernummer vorliegen!"
                    )
            
            # PRIVAT im Ausland braucht Ausweis
            if not ist_inland and not hat_ausweis:
                result.add_error(
                    "Bei als PRIVAT eingestuften Adressen aus dem Ausland "
                    "MUSS die Ausweisnummer vorliegen!"
                )
            
            # PRIVAT darf nicht den FIRMA_OHNE_USTID-Schalter haben
            if ist_inland and firma_ohne_ustid:
                result.add_error(
                    f"Bei als PRIVAT eingestuften Adressen in {AdresseValidator.HOMELAND} "
                    "darf der Sonderschalter 'FIRMA ohne UST-ID' nicht gesetzt sein!"
                )
        
        # =============================================
        # REGELN FÜR FIRMEN
        # =============================================
        if ist_firma:
            # FIRMA im Inland ohne UST-ID: nur mit Sonderschalter UND Steuernummer
            if ist_inland and not hat_ustid:
                if not firma_ohne_ustid or not hat_steuernr:
                    result.add_error(
                        "Die Einstufung einer Adresse ohne UST-ID als FIRMA "
                        "ist nur mit dem Sonderschalter 'FIRMA ohne UST-ID' "
                        "sowie der Angabe der Steuernummer möglich!"
                    )
            
            # FIRMA im EU-Ausland braucht UST-ID
            if not ist_inland and ist_eu and not hat_ustid:
                result.add_error(
                    "Eine Adresse mit Einstufung als FIRMA im EU-Ausland "
                    "MUSS eine korrekte Basis-UST-ID haben!"
                )
            
            # FIRMA mit UST-ID darf nicht den FIRMA_OHNE_USTID-Schalter haben
            if ist_inland and hat_ustid and firma_ohne_ustid:
                result.add_error(
                    "Bei einer Adresse mit Einstufung als FIRMA, die eine UST-ID hat, "
                    "darf der Schalter 'FIRMA ohne UST-ID' nicht gesetzt sein!"
                )
            
            # FIRMA darf nicht den PRIVAT_MIT_USTID-Schalter haben
            if ist_inland and privat_mit_ustid:
                result.add_error(
                    f"Bei als FIRMA eingestuften Adressen in {AdresseValidator.HOMELAND} "
                    "darf der Sonderschalter 'PRIVAT mit UST-ID' nicht gesetzt sein!"
                )
            
            # FIRMA im Nicht-EU-Ausland mit UST-ID: Länderkürzel muss stimmen
            if not ist_inland and ist_nicht_eu and hat_ustid:
                land_prefix = AdresseValidator.EU_LAENDER.get(land)
                if land_prefix and ust_lkz != land_prefix:
                    result.add_error(
                        "Bei einer Adresse als FIRMA im Nicht-EU-Ausland mit einer UST-ID "
                        "MUSS das UST-Länderkürzel mit dem Eintrag im Länderstamm übereinstimmen!"
                    )
        
        return result
    
    @staticmethod
    def validate_ausweis(adresse: dict) -> ValidationResult:
        """
        Validiert Ausweisdaten (Nummer und Ablaufdatum).
        """
        result = ValidationResult()
        
        ausweis_nummer = (adresse.get("ausweis_nummer") or "").strip()
        ausweis_ablauf = (adresse.get("ausweis_ablauf") or "").strip()
        
        if ausweis_nummer and ausweis_ablauf:
            try:
                # Verschiedene Datumsformate unterstützen
                ablauf_date = None
                for fmt in ["%Y-%m-%d", "%d.%m.%Y", "%d/%m/%Y"]:
                    try:
                        ablauf_date = datetime.strptime(ausweis_ablauf, fmt).date()
                        break
                    except:
                        continue
                
                if ablauf_date:
                    heute = date.today()
                    if ablauf_date < heute:
                        result.add_warning(
                            f"Das Ausweis-Ablaufdatum ({ausweis_ablauf}) ist abgelaufen! "
                            "Bitte aktualisieren."
                        )
                    elif ablauf_date < heute.replace(month=heute.month + 3 if heute.month <= 9 else 1):
                        result.add_warning(
                            f"Das Ausweis-Ablaufdatum ({ausweis_ablauf}) läuft bald ab!"
                        )
            except Exception:
                pass  # Datum-Parsing-Fehler ignorieren
        
        elif ausweis_nummer and not ausweis_ablauf:
            result.add_warning(
                "Ausweisnummer ist angegeben, aber das Ablaufdatum fehlt!"
            )
        
        return result
    
    @staticmethod
    def get_steuer_status(adresse: dict) -> Dict[str, Any]:
        """
        Ermittelt den steuerlichen Status einer Adresse.
        Nützlich für UI-Anzeige und Berichtswesen.
        
        Returns:
            Dict mit: typ, land_typ, ustid_status, warnungen
        """
        ist_firma = adresse.get("ist_firma", True)
        land = adresse.get("land", "")
        ist_inland = land == AdresseValidator.HOMELAND
        ist_eu = land in AdresseValidator.EU_LAENDER
        
        ust_lkz = (adresse.get("umsatzsteuer_lkz") or "").strip()
        ust_id = (adresse.get("umsatzsteuer_id") or "").strip()
        hat_ustid = bool(ust_lkz and ust_id)
        
        firma_ohne_ustid = adresse.get("firma_ohne_ustid", False)
        privat_mit_ustid = adresse.get("privat_mit_ustid", False)
        
        # Typ bestimmen
        if ist_firma:
            if ist_inland and firma_ohne_ustid:
                typ = "Firma (Inland, ohne UST-ID - Sonderfall)"
            elif ist_inland:
                typ = "Firma (Inland)"
            elif ist_eu:
                typ = "Firma (EU-Ausland)"
            else:
                typ = "Firma (Drittland)"
        else:
            if ist_inland and privat_mit_ustid:
                typ = "Privatperson (Inland, mit UST-ID - Sonderfall)"
            elif ist_inland:
                typ = "Privatperson (Inland)"
            else:
                typ = "Privatperson (Ausland)"
        
        # Land-Typ
        if ist_inland:
            land_typ = "Inland"
        elif ist_eu:
            land_typ = "EU-Ausland"
        else:
            land_typ = "Drittland"
        
        # UST-ID-Status
        if hat_ustid:
            ustid_status = f"Vorhanden ({ust_lkz}{ust_id})"
        elif firma_ohne_ustid:
            ustid_status = "Nicht vorhanden (Sonderfall)"
        else:
            ustid_status = "Nicht vorhanden"
        
        return {
            "typ": typ,
            "land_typ": land_typ,
            "ustid_status": ustid_status,
            "ist_firma": ist_firma,
            "ist_inland": ist_inland,
            "ist_eu": ist_eu,
            "hat_ustid": hat_ustid,
            "sonderschalter_aktiv": firma_ohne_ustid or privat_mit_ustid
        }
