"""
Validation Service - Business Logic Validators
Portiert aus Java/Echo2:
- AS_MAP_ValidatorEinheiten.java
- ARTIKEL_MAP_SET_AND_VALID_TYPEN.java
- BSK_P_MASK_MapValidator_CheckMengeInLieferdaten.java
- FU_MAP_SET_AND_VALID__Check_AVV_CodeAllowed.java
- __FU_Pruefer_auf_AVV_UND_NOTI.java
"""

from typing import List
from datetime import datetime


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
