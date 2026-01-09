# Echo2 FIRMENSTAMM → Neue Stammdaten-App: Migrations-Analyse

**Erstellt:** 2026-01-09  
**Analysierte Quellen:** `/app/rohstoff/Echo2BusinessLogic/FIRMENSTAMM/`

---

## 1. EXECUTIVE SUMMARY

### Gesamtstatus: 🟡 Teilweise migriert

| Kategorie | Echo2 | Neue App | Status |
|-----------|-------|----------|--------|
| Basis-Adressdaten | 45+ Felder | 40+ Felder | ✅ 90% OK |
| Firmeninfo/Steuer | 15+ Felder | 10 Felder | 🟡 Erweiterung nötig |
| Sub-Module | 12 Module | 3 Module | ⚠️ Migration nötig |
| Validierungslogik | Komplex | Basic | ⚠️ Migration nötig |
| GPS/Geolocation | Vorhanden | Teilweise | 🟡 Erweiterung nötig |

---

## 2. DATENMODELL-VERGLEICH

### 2.1 ADRESSE-Tabelle (JT_ADRESSE)

| Feld Echo2 | Typ | Neue App | Status | Aktion |
|------------|-----|----------|--------|--------|
| ID_ADRESSE | NUMBER | id (ObjectId) | ✅ OK | - |
| NAME1 | VARCHAR(40) | name1 | ✅ OK | - |
| NAME2 | VARCHAR(40) | name2 | ✅ OK | - |
| NAME3 | VARCHAR(40) | name3 | ✅ OK | - |
| STRASSE | VARCHAR(45) | strasse | ✅ OK | - |
| HAUSNUMMER | VARCHAR(10) | hausnummer | ✅ OK | - |
| PLZ | VARCHAR(10) | plz | ✅ OK | - |
| ORT | VARCHAR(30) | ort | ✅ OK | - |
| ID_LAND | NUMBER (FK) | land (String) | 🟡 Anpassen | Referenztabelle erstellen |
| ID_SPRACHE | NUMBER (FK) | sprache (String) | 🟡 Anpassen | Referenztabelle erstellen |
| ID_WAEHRUNG | NUMBER (FK) | waehrung (String) | 🟡 Anpassen | - |
| AKTIV | CHAR(1) Y/N | aktiv (bool) | ✅ OK | - |
| ADRESSTYP | NUMBER | adresstyp | ✅ OK | - |
| AUSWEIS_NUMMER | VARCHAR(30) | ausweis_nummer | ✅ OK | - |
| AUSWEIS_ABLAUF_DATUM | DATE | ausweis_ablauf | ✅ OK | - |
| WARENEINGANG_SPERREN | CHAR(1) | wareneingang_sperren | ✅ OK | - |
| WARENAUSGANG_SPERREN | CHAR(1) | warenausgang_sperren | ✅ OK | - |
| ERSTKONTAKT | DATE | ❌ fehlt | ⚠️ Hinzufügen | Neues Feld |
| ID_ADRESSE_POTENTIAL | NUMBER (FK) | ❌ fehlt | ⚠️ Hinzufügen | Potentialklasse |
| LATITUDE | NUMBER | latitude | ✅ OK | - |
| LONGITUDE | NUMBER | longitude | ✅ OK | - |
| ID_BETREUER | NUMBER (FK) | betreuer | 🟡 Umbenannt | Händler |
| ID_BETREUER2 | NUMBER (FK) | betreuer2 | 🟡 Umbenannt | Sachbearbeiter |
| GEAENDERT_VON | VARCHAR | ❌ fehlt | ⚠️ Hinzufügen | Audit-Trail |
| LETZTE_AENDERUNG | DATE | updated_at | ✅ OK | - |
| ERZEUGT_VON | VARCHAR | ❌ fehlt | ⚠️ Hinzufügen | Audit-Trail |
| ERZEUGT_AM | DATE | created_at | ✅ OK | - |

### 2.2 FIRMENINFO-Tabelle (JT_FIRMENINFO)

| Feld Echo2 | Typ | Neue App | Status | Aktion |
|------------|-----|----------|--------|--------|
| ID_FIRMENINFO | NUMBER | - (embedded) | ✅ OK | MongoDB embedded |
| PRIVAT | CHAR(1) Y/N | ist_firma (inverted) | ✅ OK | - |
| FIRMA | CHAR(1) Y/N | ist_firma | ✅ OK | - |
| FIRMA_OHNE_USTID | CHAR(1) | firma_ohne_ustid | ✅ OK | - |
| PRIVAT_MIT_USTID | CHAR(1) | privat_mit_ustid | ✅ OK | - |
| UMSATZSTEUERLKZ | VARCHAR(3) | umsatzsteuer_lkz | ✅ OK | - |
| UMSATZSTEUERID | VARCHAR(20) | umsatzsteuer_id | ✅ OK | - |
| STEUERNUMMER | VARCHAR(20) | steuernummer | ✅ OK | - |
| ID_BRANCHE | NUMBER (FK) | ❌ fehlt | ⚠️ Hinzufügen | Branche-Referenz |
| ID_ADRESSKLASSE | NUMBER (FK) | ❌ fehlt | ⚠️ Hinzufügen | Klassifizierung |
| KREDIT_LIMIT | NUMBER | ❌ fehlt | ⚠️ Hinzufügen | Kreditmanagement |
| KREDIT_LIMIT_WAEHRUNG | NUMBER (FK) | ❌ fehlt | ⚠️ Hinzufügen | - |
| IST_LIEFERANT | CHAR(1) | ❌ fehlt | ⚠️ Hinzufügen | Einkaufsfreigabe |
| IST_ABNEHMER | CHAR(1) | ❌ fehlt | ⚠️ Hinzufügen | Verkaufsfreigabe |
| BARZAHLUNG | CHAR(1) | barkunde | ✅ OK | - |

---

## 3. FEHLENDE SUB-MODULE

### 3.1 Kritisch (P0)

| Modul | Echo2-Ordner | Status | Funktionen |
|-------|--------------|--------|------------|
| **KONTEN** (Bankverbindungen) | `KONTEN/` | ❌ Fehlt | IBAN, BIC, Bank-Name, Kontonummer |
| **MITARBEITER** (Ansprechpartner) | `MITARBEITER/` | 🟡 Teilweise | Existiert als embedded Array |
| **LIEFERADRESSEN** | `LIEFERADRESSEN/` | ❌ Fehlt | Alternative Lieferorte pro Adresse |
| **ZUSATZ_UST_IDS** | `FS_Component_MASK_DAUGHTER_UST_IDS` | 🟡 Teilweise | `weitere_ustids` Array vorhanden |

### 3.2 Wichtig (P1)

| Modul | Echo2-Ordner | Status | Funktionen |
|-------|--------------|--------|------------|
| **ZUSATZINFOS** (Meldungen) | `ZUSATZINFOS/` | ❌ Fehlt | Interne Notizen, Warnungen |
| **MATSPEZ** (Materialspezifikation) | `MATSPEZ/` | ❌ Fehlt | Materialanforderungen je Adresse |
| **KREDITVERSICHERUNG** | `KREDITVERSICH/` | ❌ Fehlt | Versicherungslimits, Ablaufdaten |
| **KOSTEN_TP** (Transportkosten) | `KOSTEN_TP/` | ❌ Fehlt | Transportkostenmatrix |

### 3.3 Optional (P2)

| Modul | Echo2-Ordner | Status | Funktionen |
|-------|--------------|--------|------------|
| **ARTIKELBEZ_LIEF** | `ARTIKELBEZ_LIEF/` | ❌ Fehlt | Kundenspezifische Artikelbezeichnungen |
| **EU_VERTRAG** | `EU_VERTRAG/` | ❌ Fehlt | EU-Vertragsmanagement |
| **EAK_CODES** | `__FS_Component_MASK_DAUGHTER_EAK_CODES` | ❌ Fehlt | Abfall-Schlüsselnummern |
| **CONTAINERTYPEN** | `FS_Component_MASK_DAUGHTER_CONTAINERTYPEN` | ❌ Fehlt | Container pro Adresse |
| **GPS** | `GPS/` | 🟡 Teilweise | Geocoding, OSM-Karten |

---

## 4. VALIDIERUNGSLOGIK

### 4.1 `__FS_Adress_Check.java` - Steuerlogik (KRITISCH)

Die Echo2-App hat eine komplexe Validierungsklasse für die steuerliche Einstufung von Adressen:

#### Implementierte Regeln (müssen migriert werden):

```java
// Regel 1: Entweder FIRMA oder PRIVAT (nie beides/keines)
if ((!B_SchalterFirma && !B_SchalterPrivat) || (B_SchalterFirma && B_SchalterPrivat)) {
    ERROR: "Eine Adresse muss entweder als PRIVAT oder als FIRMA eingestuft werden!"
}

// Regel 2: PRIVAT im Inland ohne Ausweis/Steuernummer
if (B_SchalterPrivat && B_HOMELAND && !b_HAT_Ausweis_oder_steuernummer) {
    ERROR: "Bei PRIVAT-Adressen aus dem Inland MUSS die Ausweisnummer oder Steuernummer vorliegen!"
}

// Regel 3: PRIVAT im Ausland ohne Ausweis
if (B_SchalterPrivat && !B_HOMELAND && !b_AusweisAngabenVorhanden) {
    ERROR: "Bei PRIVAT-Adressen aus dem Ausland MUSS die Ausweisnummer vorliegen!"
}

// Regel 4: FIRMA im Inland ohne UST-ID
if (B_SchalterFirma && B_HOMELAND && !B_HAT_USTID && (!B_SchalterFirmaOhne_UST_ID || isEmpty(STEUERNUMMER))) {
    ERROR: "Einstufung als FIRMA ohne UST-ID nur mit Sonderschalter + Steuernummer möglich!"
}

// Regel 5: FIRMA im EU-Ausland ohne UST-ID
if (B_SchalterFirma && !B_HOMELAND && b_EU_LAND && !B_HAT_USTID) {
    ERROR: "Eine FIRMA im EU-Ausland MUSS eine korrekte Basis-UST-ID haben!"
}

// Regel 6: UST-Länderkürzel muss zum Land passen
if (B_HAT_USTID && !b_BASIS_UST_LKZ_STIMMT_MIT_LAND_EINTRAG_UEBEREIN) {
    ERROR: "Das Länderkürzel der UST-ID stimmt nicht mit dem Land überein!"
}

// Regel 7: Ausnahmeschalter nur im Inland
if (!B_HOMELAND && (B_SchalterFirmaOhne_UST_ID || B_SchalterPrivatMit_UST_ID)) {
    ERROR: "Ausnahmeschalter sind nur bei Adressen im Inland sinnvoll!"
}

// Regel 8: Ausweis-Ablaufdatum prüfen
if (b_AusweisAngabenVorhanden && !b_DatumAusweisGueltig) {
    WARNING: "Das Ausweis-Ablaufdatum ist abgelaufen!"
}
```

**Status in neuer App:** ❌ Nicht implementiert  
**Priorität:** P0 - KRITISCH für korrekten Handel

### 4.2 Pflichtfelder (aus `FS_MASK_SQLFieldMap_ADRESSE.java`)

```java
// Pflichtfelder:
- NAME1 (Firmenname/Nachname)
- STRASSE
- PLZ
- ORT
- ID_LAND
- ID_SPRACHE
- ID_ADRESSE_POTENTIAL
- AKTIV
- ID_WAEHRUNG

// Default-Werte:
- AKTIV = 'Y'
- ERSTKONTAKT = Aktuelles Datum
- WARENEINGANG_SPERREN = 'Y' (gesperrt bis Freigabe!)
- WARENAUSGANG_SPERREN = 'Y' (gesperrt bis Freigabe!)
```

**Status in neuer App:** 🟡 Teilweise (Pflichtfelder nicht enforced, Sperren-Defaults falsch)

---

## 5. MIGRATIONS-CHECKLISTE

### Phase 1: Datenmodell erweitern (P0)

- [ ] **Felder zu `AdresseCreate` hinzufügen:**
  - [ ] `erstkontakt: Optional[str]` - Datum des Erstkontakts
  - [ ] `adressklasse: Optional[str]` - Klassifizierung (A/B/C)
  - [ ] `branche: Optional[str]` - Branche/Industrie
  - [ ] `potential: Optional[str]` - Potentialklasse
  - [ ] `ist_lieferant: bool = False` - Einkaufsfreigabe
  - [ ] `ist_abnehmer: bool = False` - Verkaufsfreigabe
  - [ ] `kredit_limit: Optional[float]` - Kreditlimit
  - [ ] `geaendert_von: Optional[str]` - Audit-Trail
  - [ ] `erzeugt_von: Optional[str]` - Audit-Trail

- [ ] **Default-Werte korrigieren:**
  - [ ] `wareneingang_sperren: bool = True` (war: False)
  - [ ] `warenausgang_sperren: bool = True` (war: False)

### Phase 2: Validierungslogik implementieren (P0)

- [ ] **Backend-Validierung in `/app/backend/services/validation.py`:**
  - [ ] Funktion `validate_adresse_steuer()` erstellen
  - [ ] FIRMA/PRIVAT-Exklusivität prüfen
  - [ ] UST-ID-Plausibilität prüfen
  - [ ] Ausweisdaten für Privatpersonen prüfen
  - [ ] Land-UST-Konsistenz prüfen

- [ ] **Frontend-Validierung anpassen:**
  - [ ] Dynamische Pflichtfelder je nach FIRMA/PRIVAT
  - [ ] Warnungen bei unvollständigen Daten anzeigen

### Phase 3: Sub-Module implementieren (P1)

- [ ] **Bankverbindungen-Modul:**
  - [ ] Backend: `/api/adressen/{id}/konten` CRUD
  - [ ] Frontend: Tab "Bankverbindungen" in AdressenPage
  - [ ] Felder: IBAN, BIC, Bankname, Kontoinhaber, Hauptkonto

- [ ] **Lieferadressen-Modul:**
  - [ ] Backend: `/api/adressen/{id}/lieferadressen` CRUD
  - [ ] Frontend: Tab "Lieferadressen"
  - [ ] Felder: Wie Hauptadresse, aber mit Referenz

- [ ] **Zusatzinfos/Meldungen-Modul:**
  - [ ] Backend: `/api/adressen/{id}/meldungen` CRUD
  - [ ] Frontend: Tab "Meldungen/Infos"
  - [ ] Felder: Typ (Warnung/Info/Fehler), Text, Gültig bis, Anlass

### Phase 4: Erweiterte Features (P2)

- [ ] **GPS/Geocoding:**
  - [ ] Integration mit OSM/Google Maps API
  - [ ] Automatische Koordinatenermittlung
  - [ ] Karten-Ansicht aller Adressen

- [ ] **Kreditversicherung:**
  - [ ] Versicherungskopf pro Adresse
  - [ ] Positionsweise Limits
  - [ ] Ablaufdatum-Überwachung

- [ ] **Transportkosten-Matrix:**
  - [ ] Kosten je Relation (Von-Nach)
  - [ ] Artikelabhängige Staffeln

---

## 6. IMPLEMENTIERUNGS-VORSCHLÄGE

### 6.1 Steuer-Validierung (Python)

```python
# /app/backend/services/validation.py

class AdresseValidator:
    """Validiert Adressdaten nach Echo2-Geschäftslogik"""
    
    # Heimatland des Mandanten (konfigurierbar)
    HOMELAND = "Deutschland"
    
    # EU-Länder mit UST-Präfix
    EU_LAENDER = {
        "Deutschland": "DE", "Österreich": "AT", "Belgien": "BE",
        "Bulgarien": "BG", "Dänemark": "DK", "Estland": "EE",
        # ... vollständige Liste
    }
    
    def validate_steuer_einstufung(self, adresse: dict) -> list[dict]:
        """
        Validiert die steuerliche Einstufung einer Adresse.
        Returns: Liste von Fehlern/Warnungen
        """
        errors = []
        ist_firma = adresse.get("ist_firma", True)
        ist_privat = not ist_firma
        ist_inland = adresse.get("land") == self.HOMELAND
        ist_eu = adresse.get("land") in self.EU_LAENDER
        
        hat_ustid = bool(adresse.get("umsatzsteuer_lkz") and adresse.get("umsatzsteuer_id"))
        hat_ausweis = bool(adresse.get("ausweis_nummer"))
        hat_steuernr = bool(adresse.get("steuernummer"))
        
        firma_ohne_ustid = adresse.get("firma_ohne_ustid", False)
        privat_mit_ustid = adresse.get("privat_mit_ustid", False)
        
        # Regel 1: Ausnahmeschalter nur im Inland
        if not ist_inland and (firma_ohne_ustid or privat_mit_ustid):
            errors.append({
                "type": "warning",
                "field": "firma_ohne_ustid",
                "message": f"Ausnahmeschalter sind nur bei Adressen in {self.HOMELAND} sinnvoll!"
            })
        
        # Regel 2: PRIVAT braucht Ausweis/Steuernummer
        if ist_privat:
            if ist_inland and not hat_ausweis and not hat_steuernr and not privat_mit_ustid:
                errors.append({
                    "type": "error",
                    "field": "ausweis_nummer",
                    "message": "Bei Privatpersonen im Inland muss Ausweisnummer oder Steuernummer vorliegen!"
                })
            elif not ist_inland and not hat_ausweis:
                errors.append({
                    "type": "error",
                    "field": "ausweis_nummer", 
                    "message": "Bei Privatpersonen im Ausland muss Ausweisnummer vorliegen!"
                })
        
        # Regel 3: FIRMA braucht UST-ID oder Sonderfall
        if ist_firma:
            if ist_inland and not hat_ustid:
                if not firma_ohne_ustid or not hat_steuernr:
                    errors.append({
                        "type": "error",
                        "field": "umsatzsteuer_id",
                        "message": "Firma ohne UST-ID nur mit Sonderschalter UND Steuernummer möglich!"
                    })
            elif ist_eu and not ist_inland and not hat_ustid:
                errors.append({
                    "type": "error",
                    "field": "umsatzsteuer_id",
                    "message": "Firma im EU-Ausland MUSS eine korrekte UST-ID haben!"
                })
        
        # Regel 4: UST-Länderkürzel prüfen
        if hat_ustid:
            expected_prefix = self.EU_LAENDER.get(adresse.get("land"))
            actual_prefix = adresse.get("umsatzsteuer_lkz")
            if expected_prefix and actual_prefix != expected_prefix:
                errors.append({
                    "type": "error",
                    "field": "umsatzsteuer_lkz",
                    "message": f"UST-Länderkürzel '{actual_prefix}' passt nicht zum Land (erwartet: {expected_prefix})!"
                })
        
        return errors
```

### 6.2 Bankverbindungen-Schema (MongoDB)

```python
# Embedded in Adresse-Dokument
class Bankverbindung(BaseModel):
    id: str = Field(default_factory=lambda: str(uuid.uuid4()))
    iban: str = Field(..., max_length=34)
    bic: Optional[str] = Field(None, max_length=11)
    bank_name: Optional[str] = Field(None, max_length=100)
    kontoinhaber: Optional[str] = Field(None, max_length=100)
    ist_hauptkonto: bool = False
    aktiv: bool = True
    verwendungszweck: Optional[str] = Field(None, max_length=50)  # EK/VK/Beides
```

---

## 7. RISIKEN & EMPFEHLUNGEN

### Hohe Risiken

1. **Steuer-Compliance:** Ohne korrekte Validierung können falsch eingestufte Adressen zu Steuerproblemen führen.
   - **Empfehlung:** Implementierung der Validierungslogik vor Go-Live

2. **Datenintegrität:** Fehlende Pflichtfeld-Prüfungen können zu unvollständigen Daten führen.
   - **Empfehlung:** Backend-Validierung enforced, Frontend-Validierung als UX

### Mittlere Risiken

3. **Bankverbindungen:** Ohne IBAN-Feld keine Zahlungsabwicklung möglich.
   - **Empfehlung:** Bankverbindungen-Modul als P1 implementieren

4. **Audit-Trail:** Ohne `geaendert_von`/`erzeugt_von` keine Nachvollziehbarkeit.
   - **Empfehlung:** Audit-Felder bei allen Schreiboperationen setzen

### Niedrige Risiken

5. **GPS/Geocoding:** Nice-to-have für Routenplanung.
   - **Empfehlung:** Kann später nachgerüstet werden

---

## 8. NÄCHSTE SCHRITTE

1. **Sofort (P0):**
   - Steuer-Validierung im Backend implementieren
   - Default-Werte für Sperren korrigieren
   - Pflichtfelder im Frontend markieren

2. **Diese Woche (P1):**
   - Bankverbindungen-Tab hinzufügen
   - Zusatzinfos/Meldungen-Tab hinzufügen
   - Audit-Trail aktivieren

3. **Nächste Woche (P2):**
   - Lieferadressen-Modul
   - Erweiterte Felder (Branche, Potential, Klassifikation)
   - Kreditlimit-Verwaltung

---

*Dokument erstellt durch Code-Analyse am 2026-01-09*
