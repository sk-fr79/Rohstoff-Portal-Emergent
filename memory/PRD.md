# Rohstoff Portal - Product Requirements Document

## Projektstatus: ENTWICKLUNG AKTIV (Stand: 12.01.2026)

## Ausstehende Aufgaben (Priorisiert)

### P1 - Anstehend
- **API-System Phase 7:** Import/Export und Audit-Logs für Referenztabellen
- **PDF-Export:** Implementierung der PDF-Erstellung für Rechnungen und Lieferscheine
- **Sammelrechnungen:** Funktion implementieren, mit der mehrere Fuhren zu einer einzigen Sammelrechnung zusammengefasst werden können

### P2 - Backlog
- Datenimport aus dem Altsystem
- Vollständige PWA-Funktionen (Offline-Fähigkeit)
- Umfassendes Unit- und E2E-Testing für alle Module
- Zusätzliche Features für das API-System (Webhooks, Daten-Transformationen)

### Bekannte Technische Schulden
- TypeScript-Warnungen in `FuhrenPage.tsx` und anderen Core-Feature-Seiten
- Layout-Problem in der Einstellungsseite (`ReferenceTablesTab.tsx`) bei vielen Einträgen

---

## Original Problem Statement
Modernisierung einer veralteten Java/Echo2-Anwendung "Rohstoff Portal" zu einer modernen Architektur mit:
- **Backend:** FastAPI (Python)
- **Frontend:** React + TypeScript + Vite
- **Datenbank:** MongoDB
- **UI Framework:** TailwindCSS + shadcn/ui

Die gesamte Geschäftslogik der bestehenden Anwendung soll 1:1 übernommen werden.

---

## Implementiert (Stand: 12.01.2026)

### ✅ Ansprechpartner-Modernisierung (NEU - 12.01.2026)
Neue, moderne Tabellenansicht für Ansprechpartner im Adress-Modul:

**Features:**
- **Kompakte Grid-Tabelle:** Name/Funktion, Kontakt, Abteilung, Aktionen
- **Wildcard-Suche:** Unterstützt `*` (beliebige Zeichen) und `?` (einzelnes Zeichen)
- **Doppelklick-Details:** Öffnet Modal mit vollständigen Kontaktinformationen
- **Klickbare Kontaktdaten:** Telefon und E-Mail sind als Links ausgeführt
- **Avatar-System:** Zeigt Initialen oder Profilbild
- **Hauptkontakt:** Stern-Icon für den primären Ansprechpartner
- **Hover-Aktionen:** Details, Bearbeiten, Hauptkontakt setzen, Löschen

**Dateien:**
- `/app/frontend/src/features/adressen/components/AnsprechpartnerSection.tsx`

**Code-Bereinigung:**
- Obsolete Dateien entfernt: `dynamic-field.tsx`, `useFieldBinding.ts`
- Diese Dateien wurden durch `SmartInput` und `fieldBindingsStore` ersetzt

---

### ✅ Intelligente Referenztabellen-Verknüpfung (NEU - 11.01.2026)
Zentrales System zur Verknüpfung von Referenztabellen **ODER Live APIs** mit Modul-Feldern:

**Konzept:**
- In den Einstellungen konfiguriert der Admin, welche Datenquelle mit welchem Modul-Feld verknüpft wird
- **2 Datenquellen:** Referenztabelle (lokale Daten) oder Live API (Echtzeit-Abfragen)
- Im jeweiligen Modul wird das Textfeld automatisch durch ein durchsuchbares Dropdown ersetzt
- Nur Werte aus der Datenquelle sind erlaubt (Validierung)
- Mandanten-spezifische Konfiguration

**Live API Features:**
- Mindest-Suchzeichen (Standard: 3) - API wird erst bei ausreichender Eingabe abgefragt
- Caching (Standard: 5 Min) - Reduziert API-Aufrufe
- Fallback auf Referenztabelle - Bei API-Fehler werden lokale Daten genutzt
- Debouncing (400ms) - Verhindert zu viele Anfragen

**Backend-Endpoints:**
- `GET /api/system/modules` - Liste verfügbarer Module und Felder
- `GET /api/system/field-bindings` - Liste aller Verknüpfungen
- `POST /api/system/field-bindings` - Neue Verknüpfung erstellen (mit source_type)
- `DELETE /api/system/field-bindings/{id}` - Verknüpfung löschen
- `GET /api/system/field-binding/lookup` - Verknüpfungsdetails für ein Feld
- `GET /api/system/reference-select/{module}/{field_name}` - Dropdown-Optionen (Referenztabelle oder Live API)
- `POST /api/system/validate-reference-value` - Validierung eines Werts

**Frontend-Komponenten:**
- **ReferenceSelect:** Wiederverwendbare Dropdown-Komponente mit:
  - Automatische Erkennung der Datenquelle (Referenztabelle vs. API)
  - Globe-Icon für Live API, Database-Icon für Referenztabelle
  - "Live" Badge bei Echtzeit-Abfragen
  - "Fallback" Badge wenn auf lokale Daten zurückgefallen wurde
- **FieldBindingsManager:** Admin-UI mit Tab-Auswahl (Referenztabelle / Live API)

**Verfügbare Module:**
- `artikel`: zolltarifnr, avv_code_eingang, avv_code_ausgang, basel_code, oecd_code, artikelgruppe
- `adressen`: land, branche, adressklasse
- `kontrakte`: lieferbedingung, zahlungsbedingung
- `fuhren`: transportmittel, entsorgungsart

**Integration:**
- Artikel-Modul: Zolltarifnummer-Feld zeigt Dropdown mit Live API + Fallback auf Referenztabelle

**Dateien:**
- Backend: `/app/backend/routers/system_apis.py` (Zeilen 1510-2100)
- Frontend: `/app/frontend/src/components/ui/reference-select.tsx`
- Frontend: `/app/frontend/src/features/einstellungen/pages/components/FieldBindingsManager.tsx`

---

### ✅ CSV/Excel Upload für Referenztabellen (NEU - 11.01.2026)
Intelligentes Upload-System zur manuellen Erstellung von Referenztabellen aus CSV/Excel-Dateien:

**Backend-Features:**
- **POST `/api/system/reference-upload/analyze`:** Analysiert hochgeladene CSV/XLS/XLSX-Dateien
  - Automatische Spaltenerkennung
  - Datentyp-Erkennung: string, number (deutsches Format), boolean (ja/nein), date
  - Unterstützung für verschiedene CSV-Delimiter (`;`, `,`, Tab)
  - Mehrere Encodings (UTF-8, Latin-1, CP1252)
- **POST `/api/system/reference-upload/import`:** Importiert analysierte Daten
  - Update-Strategien: Merge (Upsert), Replace (Alles ersetzen), Append (Nur hinzufügen)
  - Flexibles Spalten-Mapping mit Primärschlüssel-Auswahl
  - Deutsche Zahlenformate werden korrekt konvertiert (8500,50 → 8500.5)

**Frontend-Features (FileUploadWizard):**
- **4-Schritte-Wizard:** Datei hochladen → Spalten zuordnen → Konfiguration → Import
- **Modernes Drag & Drop UI:** Mit Fortschrittsanzeige und Animationen
- **Intelligente Spalten-Erkennung:** Datentyp-Icons und Badges
- **Flexibles Mapping:** Spalten umbenennen, Typen ändern, Primärschlüssel setzen
- **Sofortige Integration:** Importierte Tabellen erscheinen in der Referenztabellen-Übersicht

**Dateien:**
- Backend: `/app/backend/routers/system_apis.py` (Zeilen 1143-1509)
- Frontend: `/app/frontend/src/features/einstellungen/pages/components/FileUploadWizard.tsx`
- Integration: `/app/frontend/src/features/einstellungen/pages/components/ReferenceTablesTab.tsx`

**Tests:** 13/13 Backend-Tests bestanden (100%), Frontend vollständig getestet

---

### ✅ API-Konfigurations- & Referenztabellen-System (NEU - 11.01.2026)
Umfassendes System zur Verwaltung externer APIs und Referenzdaten:

**Backend-Features:**
- **CRUD für API-Konfigurationen:** `/api/system/apis` (GET/POST/PUT/DELETE)
- **AES-256 Verschlüsselung:** Alle Credentials werden sicher verschlüsselt gespeichert
- **Authentifizierungstypen:** None, API-Key, Bearer Token, Basic Auth, OAuth2
- **Response-Mapping:** JSON-Pfad-Extraktion mit Feld-Mapping
- **Referenztabellen:** Automatische Erstellung und Sync mit Update-Strategien (Replace/Append/Merge)
- **Sync-Historie:** Detailliertes Logging aller Synchronisierungen

**Frontend-Features:**
- **6-Schritte-Wizard:** Grundlagen → Authentifizierung → Request → Response-Mapping → Referenztabelle → Testen
- **Zolltarifnummern.de Preset:** Vorkonfigurierte Vorlage für deutsche Zolltarifnummern-API
- **3 Tabs:** Übersicht (API-Karten), Referenztabellen, Sync-Historie
- **Admin-only Zugriff:** Nur Administratoren haben Zugriff (403 für andere)

**Dateien:**
- Backend Router: `/app/backend/routers/system_apis.py`
- Frontend Page: `/app/frontend/src/features/einstellungen/pages/ApisPage.tsx`
- Wizard: `/app/frontend/src/features/einstellungen/pages/components/ApiConfigWizard.tsx`
- Collections: `system_api_configs`, `system_reference_tables`, `system_reference_data`, `system_api_sync_log`

**Tests:** 27/27 Backend-Tests bestanden (100%), Frontend vollständig getestet

---

### ✅ Resizable Sidebar-Panel (11.01.2026)
Benutzer können die Breite der Detail-Sidebar per Drag & Drop anpassen:

**Features:**
- **Drag Handle:** Vertikale Linie zwischen Tabelle und Sidebar mit Grip-Icon
- **Standard:** 50/50 Aufteilung (Tabelle : Sidebar)
- **Grenzen:** Min 30% / Max 70% pro Seite
- **Visuelles Feedback:** 
  - Cursor wird zu `col-resize` beim Hover
  - Handle wird grün während des Ziehens
  - Grip-Icon erscheint beim Hover
- **Bei Reload:** Zurück auf Standard 50/50 (keine Persistenz)

**Implementierte Module:**
- Adressen
- Artikel
- Fuhren
- Kontrakte
- Wiegekarten
- Rechnungen

**Wiederverwendbare Komponenten:**
- `useResizablePanel` Hook: `/app/frontend/src/hooks/useResizablePanel.ts`
- `ResizeHandle` Komponente: `/app/frontend/src/components/ui/resize-handle.tsx`

### ✅ UX-Refactoring: "Neu"-Button → Sidebar (09.01.2026)
Einheitliches Benutzererlebnis für das Erstellen und Bearbeiten von Datensätzen:
- **Alle 6 Module** verwenden jetzt das gleiche Pattern: Der "Neu"-Button öffnet die Detail-Sidebar (nicht mehr ein Popup-Dialog)
- **Module:**
  - Artikel: `handleNewArtikel()`, data-testid="new-artikel-btn"
  - Kontrakte: `handleNewKontrakt()`, data-testid="new-kontrakt-btn"
  - Wiegekarten: `handleNewWiegekarte()`, data-testid="new-wiegekarte-btn"
  - Adressen: `handleNewAdresse()`, data-testid="new-adresse-btn"
  - Fuhren: `handleNewFuhre()`, data-testid="new-fuhre-btn"
  - Rechnungen: `handleNewRechnung()`, data-testid="new-rechnung-btn"
- **Verhalten:**
  - "Neu"-Button öffnet Sidebar mit `isNewRecord=true`
  - "Abbrechen"-Button schließt Sidebar bei neuem Eintrag
  - "Speichern" unterscheidet automatisch zwischen POST (neu) und PUT (update)
  - Doppelklick auf bestehenden Eintrag öffnet Sidebar zur Bearbeitung
- **Test-Ergebnis:** 6/6 Module bestanden (100%)

### ✅ Adressformular-Redesign: Smart Fields mit Firma/Privat-Toggle (NEU - 09.01.2026)
Komplette Neugestaltung des Adress-Stammdaten-Formulars für eine intuitivere Benutzererfahrung:

**Firma/Privat-Toggle:**
- Visueller Switch zwischen "Geschäftskunde" und "Privatperson" mit farbigem Header
- Dynamisches Ein-/Ausblenden von Feldern je nach gewähltem Typ:
  - **Firma:** Firmenname, Rechtsform, Zusatz/Abteilung, Anrede "Firma"
  - **Privatperson:** Vorname, Nachname, Anrede (Herr/Frau/Divers), Adresszusatz

**SVG-Länderflaggen:**
- Verwendung der `country-flag-icons` Library für hochwertige SVG-Flaggen
- Flaggen werden neben dem Land im Dropdown und im Trigger angezeigt
- 30 EU- und europäische Länder unterstützt (DE, AT, CH, NL, BE, FR, IT, ES, etc.)
- Ersetzt die Emoji-Flaggen, die in vielen Browsern nicht korrekt dargestellt wurden

**Umbenannte Felder:**
- "Betreuer 1" → "Händler" (mit Briefcase-Icon)
- "Betreuer 2" → "Sachbearbeiter" (mit ClipboardList-Icon)
- Gruppiert unter "Zuständigkeit"-Sektion

**Test-Ergebnis:** 8/8 Features bestanden (100%)
- Firma-Formular: ✓
- Privatperson-Formular: ✓
- Dynamischer Toggle: ✓
- SVG-Länderflaggen: ✓
- Händler/Sachbearbeiter-Felder: ✓
- Speichern (Firma): ✓
- Speichern (Privatperson): ✓
- Bestehende Adresse bearbeiten: ✓

### ✅ Geschäftslogik-Validierung (09.01.2026)
Portierung der Validierungslogik aus dem Java/Echo2-Code:

**ArtikelValidator:**
- Typ-Kombinationen: Gefahrgut+Leergut, Gefahrgut+Dienstleistung, Produkt+Dienstleistung = FEHLER
- Einheiten-Divisor: Wenn Einheit==Preiseinheit, muss Divisor=1 sein
- AVV/Basel/OECD-Codes: Produkte/Dienstleistungen/EndOfWaste dürfen keine haben
- Zolltarifnummer: Warnung wenn Rohstoff ohne Zolltarifnummer

**KontraktValidator:**
- Pflichtfeld: name1 (Vertragspartner) erforderlich
- Gültigkeitsdaten: gueltig_bis darf nicht vor gueltig_von liegen
- Positions-Prüfung: Warnung wenn Position ohne Menge/Preis

**FuhreValidator:**
- Pflichtfelder: Lieferant, Abnehmer, Artikel erforderlich
- Datumslogik: Anlieferungsdatum >= Abholdatum
- Mengenabweichung: Warnung wenn Lade-/Ablademenge > 5% abweicht
- AVV-Code-Pflicht: Rohstoffe ohne AVV-Code = FEHLER
- Status-Workflow: Nur erlaubte Übergänge (OFFEN→IN_TRANSPORT→GELIEFERT→ABGERECHNET)

**Integration:**
- Alle Create/Update-Endpunkte rufen Validierung auf
- Parameter `skip_validation=true` zum Überspringen
- Validierungs-Endpunkte: POST /api/artikel/validieren, POST /api/kontrakte/validieren, POST /api/fuhren/validieren
- **30 Tests bestanden (100%)**

### ✅ Fuhren-Modul (Transporte) - NEU - 09.01.2026
Komplettes Transportmodul mit Verknüpfung zu Wiegekarten:
- **Tabellen-Ansicht:** DataTable mit Spalten Nr., Abholdatum, Lieferant, Abnehmer, Artikel, Menge, Kennzeichen, Status
- **Detailansicht:** Slide-In Panel (600px) von rechts mit Sidebar-Navigation
  - Sektionen: Stammdaten, Lieferant, Abnehmer, Artikel & Mengen, Transport, Wiegekarten, Bemerkungen
- **Bearbeitungsmodus:** Toggle Bearbeiten/Speichern Button
- **Filter:** Status-Dropdown (OFFEN, IN_TRANSPORT, GELIEFERT, ABGERECHNET, STORNIERT)
- **Create-Dialog:** Formular mit Lieferant, Abnehmer, Artikel, Datum, Planmenge, Transportmittel
- **Verknüpfungen:** Wiegekarten (Start/Ziel), Kontrakte
- **Backend-Endpunkte:**
  - GET /api/fuhren - Liste mit Suche, Pagination, Status-Filter
  - GET /api/fuhren/{id} - Einzelne Fuhre
  - POST /api/fuhren - Neue Fuhre (Fuhren-Nr. automatisch)
  - PUT /api/fuhren/{id} - Fuhre aktualisieren (Preise automatisch berechnet)
  - DELETE /api/fuhren/{id} - Soft-Delete
  - POST /api/fuhren/{id}/storno - Fuhre stornieren
- **32 Tests bestanden (100%)**

### ✅ Rechnungen-Modul (Fakturierung) - NEU - 09.01.2026
Komplettes Rechnungsmodul mit automatischer Erstellung aus Fuhren:
- **Tabellen-Ansicht:** DataTable mit Spalten Nr., Typ, Adressat, Datum, Netto, Brutto, Status
- **Detailansicht:** Slide-In Panel (600px) von rechts mit Sidebar-Navigation
  - Sektionen: Kopfdaten, Adressat, Termine, Positionen, Zahlungen, Bemerkungen
- **Bearbeitungsmodus:** Toggle Bearbeiten/Speichern Button
- **Filter:** Typ-Dropdown (RECHNUNG/GUTSCHRIFT) und Status-Dropdown
- **Summen-Bereich:** Netto + MwSt = Brutto (automatisch berechnet)
- **Positionen:** Tabelle mit Artikel, Menge, Einzelpreis, Gesamt
- **Automatische Erstellung:** Rechnung/Gutschrift aus Fuhre generieren
- **Backend-Endpunkte:**
  - GET /api/rechnungen - Liste mit Suche, Pagination, Typ-/Status-Filter
  - GET /api/rechnungen/{id} - Einzelne Rechnung mit Positionen und Summen
  - POST /api/rechnungen - Neue Rechnung/Gutschrift (RG-/GS-Nr. automatisch)
  - PUT /api/rechnungen/{id} - Rechnung aktualisieren
  - DELETE /api/rechnungen/{id} - Soft-Delete
  - POST /api/rechnungen/{id}/positionen - Position hinzufügen (Summen automatisch)
  - POST /api/rechnungen/aus-fuhre/{fuhre_id}?vorgang_typ=RECHNUNG|GUTSCHRIFT - Automatische Erstellung

### ✅ Artikel-Modul - Redesign mit Sidebar-Layout (NEU - 09.01.2026)
Komplette Neugestaltung der Artikel-Seite im Stil der Adressen-Seite:
- **Tabellen-Ansicht:** DataTable mit Spalten ANR, Bezeichnung, Einheit, Gruppe, Gefahrgut, Status
- **Detailansicht:** Slide-In Panel (600px) von rechts mit Sidebar-Navigation
  - Sektionen: Stammdaten, Einheiten, Klassifizierung, AVV-Codes, Zoll & Export, Bemerkungen
- **Bearbeitungsmodus:** Toggle Bearbeiten/Speichern Button
- **Filter:** Toggle für inaktive Artikel
- **Create-Dialog:** Formular mit ANR, Artikelbezeichnung, Einheit, Artikelgruppe, Aktiv/Gefahrgut
- **Backend-Endpunkte:**
  - GET /api/artikel - Liste mit Suche und Pagination
  - GET /api/artikel/{id} - Einzelner Artikel
  - POST /api/artikel - Neuer Artikel
  - PUT /api/artikel/{id} - Artikel aktualisieren
  - DELETE /api/artikel/{id} - Soft-Delete (aktiv=false)
- **24 Tests bestanden (100%)**

### ✅ Kontrakte-Modul - Redesign mit Sidebar-Layout (NEU - 09.01.2026)
Komplette Neugestaltung der Kontrakte-Seite im Stil der Adressen-Seite:
- **Tabellen-Ansicht:** DataTable mit Spalten Nr., Typ, Vertragspartner, Gültig bis, Status, Positionen
- **Detailansicht:** Slide-In Panel (600px) von rechts mit Sidebar-Navigation
  - Sektionen: Kopfdaten, Vertragspartner, Termine, Konditionen, Positionen, Bemerkungen
- **Bearbeitungsmodus:** Toggle Bearbeiten/Speichern Button
- **Filter:** Dropdown für Kontrakttyp (Alle/Einkauf/Verkauf)
- **Status-Badges:** Farbcodiert (OFFEN=blau, AKTIV=grün, TEILERFUELLT=gelb, ERFUELLT=emerald, STORNO=rot)
- **Create-Dialog:** Formular mit Kontrakttyp, Buchungsnummer, Vertragspartner, PLZ, Ort, Gültigkeitsdatum
- **Backend-Endpunkte:**
  - GET /api/kontrakte - Liste mit Suche, Pagination und Typfilter
  - GET /api/kontrakte/{id} - Einzelner Kontrakt
  - POST /api/kontrakte - Neuer Kontrakt (erweitertes Schema)
  - PUT /api/kontrakte/{id} - Kontrakt aktualisieren
  - DELETE /api/kontrakte/{id} - Soft-Delete (deleted=true)
  - POST /api/kontrakte/{id}/positionen - Position hinzufügen

### ✅ Wiegekarten-Modul (NEU - 09.01.2026)
Komplettes Modul für Fahrzeugwaage mit Systec IT 4000 IP-Wiegeterminal:
- **Tabellen-Ansicht:** Grid mit Doppelklick zur Detailansicht
- **Spalten:** Nr., Status, Richtung, Kennzeichen, Adresse, Netto (kg), Datum
- **Detailansicht:** Slide-In Panel mit Sidebar-Navigation (wie Adressen)
  - Sektionen: Stammdaten, Fahrzeug, Artikel/Material, Wägung, Mengen/Stück, Bemerkungen
- **Gewichte-Anzeige:** Brutto | Tara | Netto (große Zahlen oben)
- **Status-System:** NEU → WAEGUNG1 → WAEGUNG2 → GEDRUCKT | STORNO
- **Wägungen:** 
  - Wareneingang: Wägung1=Brutto (voll), Wägung2=Tara (leer)
  - Warenausgang: Wägung1=Tara (leer), Wägung2=Brutto (voll)
- **Waage-Demo-Modus:** POST /api/waage/lesen (zufällige Gewichte 5000-35000 kg)
- **Backend-Endpunkte:**
  - GET/POST /api/wiegekarten
  - GET/PUT/DELETE /api/wiegekarten/{id}
  - POST /api/wiegekarten/{id}/waegung/{nr}
  - POST /api/wiegekarten/{id}/storno
  - GET /api/waage/status, POST /api/waage/lesen
- **19 Tests bestanden (100%)**

### ✅ UI/UX Redesign - Enterprise Light Theme (09.01.2026)
Komplette Umstellung auf ein helles, professionelles Enterprise-Design:
- **Haupt-Sidebar:** Dunkle Navy-Farbe mit Emerald-Akzenten, aufklappbare Gruppen
- **Dashboard:** Statistik-Karten, Umsatz-Chart, Schnellzugriff, Aktivitäten-Feed
- **Login-Seite:** Modernes Split-Layout mit Branding-Bereich
- **Adressen-Detail:** Neue Sidebar-Navigation statt Top-Tabs
  - Sektionen: Stammdaten, Kontakt, Finanzen, Steuer, Sperren, Bemerkungen
  - Übersichtliche Feldanordnung mit Gruppierung
  - Slide-In Panel von rechts

### ✅ Ansprechpartner-Verwaltung (NEU - 09.01.2026)
Unter Kontakt-Tab in Adress-Details:
- **Ansprechpartner anlegen/bearbeiten/löschen**
- Felder: Vorname, Nachname, Funktion, Sprache
- Adresse: Straße, PLZ, Ort  
- Kontaktdaten: Telefon, Mobil, E-Mail
- **Profilbild-Upload** mit modernem UserCircle-Icon als Standard
- **Visitenkarten-Upload mit Drag & Drop**
- Backend-Endpunkte: POST/PUT/DELETE `/api/adressen/{id}/ansprechpartner`

### ✅ Dynamische UST-IDs (NEU - 09.01.2026)
Unter Steuer-Tab in Adress-Details:
- Basis UST-ID (LKZ + Nummer)
- **"Weitere UST-IDs"** dynamisch hinzufügen via Plus-Button
- Jede Zeile: Land-Dropdown (16 EU-Länder), LKZ-Feld, UST-ID Nummer
- Löschen-Button (Papierkorb) pro Zeile
- Ersetzt statische ust_at, ust_nl, ust_ch Felder

### ✅ Firmenlogo-Upload (09.01.2026)
In Stammdaten > Grundinformationen:
- **Firmenlogo hochladen** (für Firmen-Adressen)
- Modernes Building2-Icon als Standard-Platzhalter
- **Transparente Einbettung** im Header (weißer Hintergrund, Logo freigestellt)
- Hover-Effekt mit Kamera-Icon zum Hochladen
- Backend-Endpunkt: POST `/api/upload/logo/{adresse_id}`
- Base64-Speicherung in MongoDB

### ✅ OCR für Visitenkarten (NEU - 09.01.2026)
- **Automatische Texterkennung** aus Visitenkarten-Bildern
- Extrahiert: Vorname, Nachname, Firma, Funktion, Telefon, Mobil, E-Mail, Adresse
- Verwendet OpenAI GPT-4o Vision via Emergent LLM Key
- Backend-Endpunkt: POST `/api/ocr/visitenkarte`
- Hinweis im Upload-Bereich: "Die Kontaktdaten werden automatisch erkannt (OCR)"

### ✅ Filter für inaktive Adressen (NEU - 09.01.2026)
- **Inaktive Adressen werden standardmäßig ausgeblendet**
- Toggle-Schalter "Inaktive" in der Filterleiste oben rechts
- Anzeige der Anzahl ausgeblendeter Adressen: "(X inaktive ausgeblendet)"

### ✅ UST-ID Validierung via EU VIES (NEU - 09.01.2026)
Offizielle EU-Schnittstelle zur Validierung von Umsatzsteuer-Identifikationsnummern:
- **API-Integration:** REST-API der EU-Kommission (https://ec.europa.eu/taxation_customs/vies/rest-api/check-vat-number)
- **Protokollierung:** Jede Validierung wird gespeichert mit:
  - Datum und Uhrzeit der Abfrage
  - Ergebnis (gültig/ungültig)
  - Firmenname und Adresse (wenn von VIES zurückgegeben)
  - Benutzer, der die Prüfung durchgeführt hat
  - Request-Identifier für Audit-Zwecke
- **Frontend:**
  - Suchbutton (Lupe) neben der UST-ID → Löst Validierung aus
  - Protokoll-Button (Uhren-Symbol) → Öffnet Dialog mit Validierungshistorie
  - Toast-Benachrichtigungen für Ergebnisse (grün=gültig, rot=ungültig, gelb=Fehler)
- **Backend-Endpunkte:**
  - `POST /api/ustid/validate` - Validiert UST-ID über EU VIES und protokolliert
  - `GET /api/ustid/protokoll/{adresse_id}` - Ruft Validierungshistorie ab
  - `DELETE /api/ustid/protokoll/{protokoll_id}` - Löscht Protokolleintrag (Admin-only)
- **MongoDB Collection:** `ustid_protokoll`

### ✅ Geschäftslogik - Adress-Validierung (09.01.2026)
Portiert aus Java-Code: `rohstoff.Echo2BusinessLogic.FIRMENSTAMM.__FS_Adress_Check`

**Validierungsregeln:**
- FIRMA im Inland ohne UST-ID: Sonderschalter "Firma ohne UST-ID" + Steuernummer erforderlich
- FIRMA im EU-Ausland: UST-ID MUSS vorhanden sein
- FIRMA im Nicht-EU-Ausland: UST-Länderkürzel muss mit Land übereinstimmen
- PRIVAT im Inland: Ausweis ODER Steuernummer erforderlich
- PRIVAT im Ausland: Ausweisnummer erforderlich
- PRIVAT im Ausland: Keine UST-ID erlaubt
- UST-ID muss vollständig sein (LKZ + Nummer)
- UST-Länderkürzel muss mit Land übereinstimmen
- Sonderschalter nur im Inland sinnvoll
- Beide Sonderschalter nicht gleichzeitig aktiv

**Neue Endpunkte:**
- `POST /api/adressen/validieren` - Validiert Adresse ohne zu speichern
- `GET /api/laender` - Liste der 30 konfigurierten Länder mit UST-Präfixen

**Neue Felder:**
- `firma_ohne_ustid` - Sonderschalter für Firmen ohne UST-ID im Inland
- `privat_mit_ustid` - Sonderschalter für Privatpersonen mit UST-ID im Inland

**Frontend:**
- Neue Sektion "Steuerliche Einstufung (Geschäftslogik)" im Finanz/Handel-Tab
- FIRMA/PRIVAT Toggle mit kontextabhängiger Beschreibung
- Sonderschalter werden automatisch aktiviert/deaktiviert basierend auf Land und Typ

### ✅ Echo2 Steuer-Validierungslogik vollständig portiert (NEU - 09.01.2026)
Komplette Geschäftslogik aus `__FS_Adress_Check.java` nach Python migriert:

**Validierungsregeln:**
1. **Firma/Privat-Exklusivität:** Adresse muss entweder FIRMA oder PRIVAT sein (nie beides/keines)
2. **Teilweise UST-ID:** Wenn UST-Länderkürzel ODER UST-ID gesetzt, müssen beide ausgefüllt sein
3. **UST-Länderkürzel-Konsistenz:** Präfix muss zum Land passen (z.B. AT für Österreich)
4. **Ausnahmeschalter nur im Inland:** `firma_ohne_ustid` und `privat_mit_ustid` nur für Deutschland sinnvoll
5. **Keine doppelten Ausnahmeschalter:** Beide können nicht gleichzeitig aktiv sein

**FIRMA-Validierung:**
- Inland ohne UST-ID: Nur mit `firma_ohne_ustid=True` UND Steuernummer erlaubt
- EU-Ausland: UST-ID ist Pflicht
- Mit UST-ID: `firma_ohne_ustid` darf nicht gesetzt sein
- `privat_mit_ustid` darf bei FIRMA nie gesetzt sein

**PRIVAT-Validierung:**
- Inland mit UST-ID: Nur mit `privat_mit_ustid=True` erlaubt
- Inland ohne UST-ID: Ausweisnummer ODER Steuernummer erforderlich
- Ausland: UST-ID nicht erlaubt, Ausweisnummer ist Pflicht
- `firma_ohne_ustid` darf bei PRIVAT nie gesetzt sein

**Korrigierte Default-Werte:**
- `wareneingang_sperren: bool = True` (war: False)
- `warenausgang_sperren: bool = True` (war: False)

**Neue Felder hinzugefügt:**
- `erstkontakt`: Datum des Erstkontakts
- `branche`: Branche/Industrie
- `adressklasse`: Klassifizierung (A/B/C)
- `potential`: Potentialklasse
- `kredit_limit`: Kreditlimit
- `kredit_limit_waehrung`: Währung des Kreditlimits
- `ist_lieferant`: Einkaufsfreigabe
- `ist_abnehmer`: Verkaufsfreigabe

**Test-Ergebnis:** 37/37 Tests bestanden (100%)
- 20 neue Tests für Steuer-Validierung
- Alle Szenarien aus Echo2-Java-Code abgedeckt

### ✅ Adressen-Stammdaten - Vollständige Feldstruktur aus Java-System
- **Tab 1 - Adresse:**
  - Status: Aktiv, Wareneingang, Warenausgang, Firma, Barkunde, Scheckdruck
  - Anrede, Vorname, Name 1-3, Rechtsform
  - Straße, Hausnr., Land, PLZ, Ort, Ortzusatz, Sprache
  - Betreuer 1+2, Postfach aktiv
  - Geolocation: Breitengrad, Längengrad, Wartezeit
- **Tab 2 - Finanz/Handel:**
  - Nummern: Kreditor-Nr, Debitor-Nr, BetriebsNr. SAA, Alt. Lief.-Nr, Alt. Abn.-Nr, Sondernummer
  - Währung, Handelsregister, Lieferbedingungen EK/VK (Incoterms® 2020)
  - Zahlungsbedingungen EK/VK
  - UST-IDs: Basis + AT, NL, CH
  - Sperren: Rechnungen, Gutschriften, Wareneingang, Warenausgang, wird nicht gemahnt
- **Tab 3 - Kontakt:**
  - Telefon, Telefax, E-Mail, Webseite
  - Postfach: PLZ Postfach, Postfach
  - Ausweis Privatkunden: Ausweisnummer, Ablaufdatum, Geburtsdatum
- **Tab 4 - Sonstiges:**
  - Adresstyp, Bemerkungen, Bemerkung Fahrplan, Lieferinfo TPA
- **Tab 1 - Artikel-Angaben:**
  - Status-Flags: Aktiv, Gefahrgut, Leergut, Elektro/Elektronik, Produkt, Dienstleistung, End of Waste, EoW (Lager)
  - Artikelgruppe, Artikelgruppe (FiBu)
  - ANR1 (Artikelnummer), EAK-Code (ID-Artikel)
  - Artikelbez. 1 + 2 (intern)
  - Einheit, Faktor, Preiseinheit, Nachkommastellen
  - AVV-Code Bar-Anlieferer, AVV-Code Ausgang mvg
- **Tab 2 - Nummern-Codes:**
  - Basel-Code + Basel-Notiz
  - OECD-Code + OECD-Notiz
  - Zolltarif-Nummer + Zolltarif-Text
  - Anhang 7 (IIIA) Nummer/Text
  - Anhang 7 (IIIB) Nummer/Text
  - Österreichische AVV
- **Tab 3 - Bemerkungen:**
  - Bemerkung (intern) - großes Textfeld für Sortenanforderungen

### ✅ Backend (FastAPI)
- **Authentifizierung:** JWT-basierte Auth mit Login, Logout, /auth/me
- **Benutzer-Management:** Admin-Benutzer automatisch erstellt
- **Adressen-Modul:** Vollständiges CRUD mit Suche, Pagination, KDNR-Generierung
- **Artikel-Modul:** CRUD mit Gefahrgut-Flag, EAK-Code, Einheiten
- **Kontrakte-Modul:** EK/VK-Kontrakte mit Buchungsnummer-Generierung, Positionen, Abschluss
- **Dashboard-Statistiken:** Echtzeitdaten für Adressen, Artikel, offene Kontrakte
- **MongoDB-Integration:** Vollständig funktionsfähig mit Indizes

### ✅ Frontend (React + TypeScript)
- **Login-Seite:** Modernes Design mit Animation
- **Dashboard:** Statistik-Karten + Recharts Umsatz-Chart + Aktivitäten-Feed
- **Adressen-Modul:** Kartenansicht, Suche, Filter, Erstellung
- **Artikel-Modul:** Vollständige UI mit Erstellungsdialog, Gefahrgut-Anzeige
- **Kontrakte-Modul:** Tabs (Einkauf/Verkauf), Filter, Erstellungsdialog mit Adressauswahl
- **Navigation:** Sidebar mit allen Modulen
- **UI-Komponenten:** Dialog, Dropdown, Select, Switch, Tabs, Popover, Command, Textarea

### ✅ Tests
- **Backend:** 46/46 Tests bestanden (100%)
  - Authentifizierung: Login, Token-Validierung
  - Adress-Validierung: 17 Tests für Geschäftslogik aus Java
  - CRUD-Operationen: Adressen, Artikel, Kontrakte
- **Frontend:** Alle Module funktionsfähig

---

## Tech Stack

| Komponente | Technologie |
|------------|-------------|
| Backend | FastAPI, Python 3.11 |
| Frontend | React 18, TypeScript, Vite |
| UI | TailwindCSS, shadcn/ui |
| Charts | Recharts |
| State | Zustand, TanStack Query |
| Datenbank | MongoDB |
| Auth | JWT (HS256) |

---

## Login-Daten

```
Benutzername: admin
Passwort: Admin123!
```

---

## API-Endpunkte

### Auth
- `POST /api/auth/login` - Anmeldung
- `GET /api/auth/me` - Aktueller Benutzer
- `POST /api/auth/logout` - Abmeldung

### Adressen
- `GET /api/adressen` - Liste mit Suche/Pagination
- `GET /api/adressen/{id}` - Einzelne Adresse
- `POST /api/adressen` - Neue Adresse (mit Validierung, skip_validation=true zum Umgehen)
- `PUT /api/adressen/{id}` - Aktualisieren (mit Validierung)
- `DELETE /api/adressen/{id}` - Soft-Delete
- `POST /api/adressen/validieren` - Validiert Adresse nach Geschäftslogik
- `GET /api/laender` - Liste der konfigurierten Länder mit UST-Präfixen

### Bankverbindungen (NEU - 09.01.2026)
- `GET /api/adressen/{id}/bankverbindungen` - Bankverbindungen einer Adresse abrufen
- `POST /api/adressen/{id}/bankverbindungen` - Neue Bankverbindung hinzufügen
- `PUT /api/adressen/{id}/bankverbindungen/{bank_id}` - Bankverbindung aktualisieren
- `DELETE /api/adressen/{id}/bankverbindungen/{bank_id}` - Bankverbindung löschen

### Lieferadressen (NEU - 09.01.2026)
- `GET /api/adressen/{id}/lieferadressen` - Lieferadressen einer Adresse abrufen
- `POST /api/adressen/{id}/lieferadressen` - Neue Lieferadresse hinzufügen
- `PUT /api/adressen/{id}/lieferadressen/{liefer_id}` - Lieferadresse aktualisieren
- `DELETE /api/adressen/{id}/lieferadressen/{liefer_id}` - Lieferadresse löschen

### Artikel (NEU erweitert - 09.01.2026)
- `GET /api/artikel` - Liste mit Suche/Pagination
- `GET /api/artikel/{id}` - Einzelner Artikel ✅
- `POST /api/artikel` - Neuer Artikel (mit Validierung, skip_validation=true)
- `PUT /api/artikel/{id}` - Artikel aktualisieren (mit Validierung) ✅
- `DELETE /api/artikel/{id}` - Soft-Delete (aktiv=false) ✅
- `POST /api/artikel/validieren` - Validiert Artikel nach Geschäftslogik ✅

### Kontrakte (NEU erweitert - 09.01.2026)
- `GET /api/kontrakte` - Liste mit Filter
- `GET /api/kontrakte/{id}` - Einzelner Kontrakt
- `POST /api/kontrakte` - Neuer Kontrakt (mit Validierung, skip_validation=true)
- `PUT /api/kontrakte/{id}` - Kontrakt aktualisieren (mit Validierung) ✅
- `DELETE /api/kontrakte/{id}` - Soft-Delete (deleted=true) ✅
- `POST /api/kontrakte/{id}/positionen` - Position hinzufügen
- `POST /api/kontrakte/{id}/abschliessen` - Kontrakt abschließen
- `POST /api/kontrakte/validieren` - Validiert Kontrakt nach Geschäftslogik ✅

### Dashboard
- `GET /api/dashboard/stats` - Statistiken

### Fuhren (NEU - 09.01.2026)
- `GET /api/fuhren` - Liste mit Suche/Pagination/Status-Filter
- `GET /api/fuhren/{id}` - Einzelne Fuhre
- `POST /api/fuhren` - Neue Fuhre (mit Validierung, skip_validation=true)
- `PUT /api/fuhren/{id}` - Fuhre aktualisieren (mit Validierung + Status-Workflow)
- `DELETE /api/fuhren/{id}` - Soft-Delete
- `POST /api/fuhren/{id}/storno` - Fuhre stornieren
- `POST /api/fuhren/validieren` - Validiert Fuhre nach Geschäftslogik ✅

### Rechnungen (NEU - 09.01.2026)
- `GET /api/rechnungen` - Liste mit Suche/Pagination/Typ-/Status-Filter
- `GET /api/rechnungen/{id}` - Einzelne Rechnung mit Positionen
- `POST /api/rechnungen` - Neue Rechnung/Gutschrift
- `PUT /api/rechnungen/{id}` - Rechnung aktualisieren
- `DELETE /api/rechnungen/{id}` - Soft-Delete
- `POST /api/rechnungen/{id}/positionen` - Position hinzufügen
- `POST /api/rechnungen/aus-fuhre/{fuhre_id}` - Rechnung/Gutschrift aus Fuhre erstellen

---

## Offene/Kommende Aufgaben

### P0 - Aktueller Fokus
- [x] Geschäftslogik aus Java-Code integrieren (Adress-Validierung) ✅ ERLEDIGT
- [x] Artikel-Modul mit Sidebar-Layout redesignen ✅ ERLEDIGT (09.01.2026)
- [x] Kontrakte-Modul mit Sidebar-Layout redesignen ✅ ERLEDIGT (09.01.2026)
- [x] Fuhren-Modul implementieren ✅ ERLEDIGT (09.01.2026)
- [x] Rechnungen-Modul implementieren ✅ ERLEDIGT (09.01.2026)
- [x] Geschäftslogik-Validierung portieren (Artikel, Kontrakte, Fuhren) ✅ ERLEDIGT (09.01.2026)
- [x] UX-Refactoring: "Neu"-Button → Sidebar in allen Modulen ✅ ERLEDIGT (09.01.2026)
- [x] Backend-Refactoring: `server.py` in Module aufteilen ✅ ERLEDIGT (09.01.2026)
- [x] Adressformular-Redesign mit Firma/Privat-Toggle, SVG-Flaggen, Händler/Sachbearbeiter ✅ ERLEDIGT (09.01.2026)
- [x] Echo2 Steuer-Validierungslogik (__FS_Adress_Check.java) implementiert ✅ ERLEDIGT (09.01.2026)
- [x] Default-Werte für Sperren korrigiert (wareneingang/warenausgang = True) ✅ ERLEDIGT (09.01.2026)
- [x] Bankverbindungen-Modul (KONTEN aus Echo2) ✅ ERLEDIGT (09.01.2026)
- [x] Lieferadressen-Modul (LIEFERADRESSEN aus Echo2) ✅ ERLEDIGT (09.01.2026)
- [x] Frontend-Anzeige von Validierungsfehlern ✅ ERLEDIGT (09.01.2026)

### P1 - Hohe Priorität
- [ ] PDF-Export für Rechnungen und Lieferscheine
- [ ] Sammelrechnungen (mehrere Fuhren → eine Rechnung)

### P2 - Mittlere Priorität
- [x] **Kreditversicherungs-Verwaltung NEUGESTALTET** (Echo2: KREDITVERSICH) ✅ ERLEDIGT (09.01.2026)
  - **NEUE STRUKTUR**: 1 Hauptvertrag (Kopf) → n Kundenpositionen (1:n Beziehung)
  - **Hauptvertrag (Kopf)**: Gesamtlimit, Vertragsbeginn/-ende, Versicherer
  - **Kundenposition**: Eigenes Kreditlimit, Unterversicherungsnr., Fakturierungsfrist, Gültigkeitsdatum
  - **Auslastung** = Summe Kundenlimits / Gesamtlimit × 100%
  - **Enddatum Hauptvertrag sticht** immer das Enddatum der Unterverträge
  - Endpunkte: CRUD + /api/kreditversicherungen/{id}/positionen + /api/adressen/{id}/kreditlimits
  - Tab in AdressenPage + eigenständige Verwaltungsseite /kreditversicherungen
  - Smarte Grid-Tabelle mit Suchfunktion für Kundenpositionen
  - Prüfung neuer Beträge gegen Limits (POST /api/kreditpruefung)
  - Test: 14/14 Backend-Tests, 100% Frontend bestanden
- [x] **Einstellungen-Bereich implementiert** ✅ ERLEDIGT (09.01.2026)
  - **Zwei Hauptansichten**: ERP-Ansicht und Einstellungen-Ansicht
  - **Eigene Sidebar** für Einstellungen
  - **Benutzer-Einstellungen**: Profil, Kontaktdaten, Adresse, Passwort, Profilbild, E-Mail Signatur, Unterschrift
  - **Admin-Einstellungen** (nur für Administratoren): Systemeinstellungen, Benutzerverwaltung, Mandanten, Sicherheit, Datenbank
  - **Navigation**: "MV-Portal" Button oben rechts führt zurück zur ERP-Ansicht
- [x] **Hard Delete implementiert** ✅ ERLEDIGT (09.01.2026)
  - Alle DELETE-Endpunkte auf Hard Delete umgestellt
  - Gelöschte Datensätze werden vollständig aus der DB entfernt
  - Cleanup-Endpunkt für soft-deleted Altdaten: DELETE /api/cleanup/soft-deleted
- [x] **Benutzer- und Berechtigungssystem implementiert** ✅ ERLEDIGT (09.01.2026)
  - **Benutzerverwaltung**: Grid mit allen Benutzern, CRUD, Passwort-Reset
  - **Benutzerrollen**: Administrator (System-Rolle, nicht löschbar), Sachbearbeiter, Einkäufer, Verkäufer, Buchhalter, Lager
  - **Abteilungen**: Geschäftsführung, Einkauf, Verkauf, Disposition, Buchhaltung, Lager (Benutzer kann N Abteilungen haben)
  - **Granulare Zugriffssteuerung**: Modulbasiert, Rollen-/Abteilungs-/Benutzerbasiert
  - **Berechtigungsstufen**: Read, Write, Full, Denied (Ausblenden)
  - **Vererbung**: Benutzer > Rolle > Abteilung (bei mehreren Abteilungen höchstes Recht)
  - **Admin-Only**: Nur Administratoren haben Zugriff auf /api/admin/* Endpunkte
  - Test: 23/23 Backend-Tests, 100% Frontend bestanden
- [x] **Berechtigungsdurchsetzung VERIFIZIERT** ✅ ERLEDIGT (11.01.2026)
  - **Backend**: `require_permission()` Dependency auf allen CRUD-Endpunkten
  - **Frontend**: `ProtectedModule` Komponente für Routenschutz, Sidebar-Filterung
  - **Test-Ergebnis**: 20/20 Backend-Tests, 100% Frontend-Routenschutz
  - **Test-Datei**: `/app/tests/test_berechtigungen_system.py`
- [ ] Positionen im Kontrakt bearbeiten (UI zum Hinzufügen/Löschen)
- [ ] Positionen in Rechnungen bearbeiten/löschen
- [ ] Suchfeld in der globalen Navigation funktionsfähig machen
- [ ] Dark/Light Mode Toggle
- [ ] Datumsauswahl mit shadcn Calendar statt native Input

### P3 - Niedrige Priorität
- [ ] PWA Service Worker
- [ ] Offline-Fähigkeit
- [ ] Echte Aktivitäten-Historie im Dashboard
- [x] Benutzereinstellungen-Seite ✅ ERLEDIGT (Teil von Einstellungen-Bereich)

---

## Dateistruktur (NEU - Modulare Architektur)

```
/app/
├── backend/
│   ├── server.py          # Haupt-Entry-Point (~130 Zeilen)
│   ├── server_backup.py   # Backup der alten monolithischen Datei
│   ├── routers/           # API-Endpunkte (~2200 Zeilen)
│   │   ├── auth.py        # Login, Logout, User-Info
│   │   ├── dashboard.py   # Dashboard-Statistiken
│   │   ├── adressen.py    # Adressen-CRUD, UST-ID, Ansprechpartner
│   │   ├── artikel.py     # Artikel-CRUD mit Validierung
│   │   ├── kontrakte.py   # Kontrakte-CRUD mit Positionen
│   │   ├── wiegekarten.py # Wiegekarten-CRUD, Waage-Integration
│   │   ├── fuhren.py      # Fuhren-CRUD mit Status-Workflow
│   │   └── rechnungen.py  # Rechnungen/Gutschriften-CRUD
│   ├── services/          # Geschäftslogik (~320 Zeilen)
│   │   ├── database.py    # MongoDB-Verbindung
│   │   └── validation.py  # Artikel-, Kontrakt-, Fuhren-Validierung
│   ├── models/            # Pydantic-Schemas
│   │   └── auth.py        # Login/Logout-Modelle
│   ├── utils/             # Hilfsfunktionen
│   │   └── auth.py        # JWT, Password-Hashing
│   └── tests/             # Pytest Tests
│       └── test_artikel_kontrakte.py
│   └── requirements.txt
├── frontend/
│   ├── src/
│   │   ├── components/ui/  # shadcn Komponenten
│   │   ├── features/
│   │   │   ├── auth/       # Login
│   │   │   ├── dashboard/  # Dashboard mit Chart
│   │   │   ├── adressen/   # Adressverwaltung
│   │   │   ├── artikel/    # Artikelverwaltung (NEU: Sidebar-Layout)
│   │   │   ├── kontrakte/  # Kontraktverwaltung (NEU: Sidebar-Layout)
│   │   │   └── wiegekarten/ # Wiegekartenverwaltung
│   │   ├── services/api/   # API Client
│   │   └── store/          # Zustand Store
│   └── package.json
└── test_reports/           # Test-Ergebnisse
```

---

## Referenz-Dokumente

- `/app/rohstoff-portal-modern/docs/MODUL_ANALYSE.md` - Analyse der Java-Anwendung
- `/app/rohstoff-portal-modern/backend/prisma/schema.prisma` - Referenz für Datenbankmodell

---

**Earlier issues found/mentioned but not fixed**
-   Keine.

## CHANGELOG (2026-01-12)

### Klickbarer Link in Warennotiz (Zolltarifnummer) ✅
1. **Feature implementiert:**
   - Unterhalb des "Warennotiz"-Textfeldes im Artikel-Modul wird ein ansprechender, klickbarer Link angezeigt
   - Link öffnet die externe Seite (zolltarifnummern.de) in neuem Tab
   - Modernes Design mit ExternalLink-Icon und Emerald-Farben
   - Link ist nur sichtbar, wenn eine URL vorhanden ist
   - URL wird automatisch aus der ausgewählten Zolltarifnummer extrahiert
   - Bei bestehenden Artikeln wird die URL aus der gespeicherten Notiz extrahiert

2. **Änderungen:**
   - `ArtikelPage.tsx`: Neuer State `zolltarifUrl`, Link-Element mit Icon
   - Warennotiz-Text enthält keine URL mehr (wird separat angezeigt)

3. **UI-Element:**
   - Button-Style mit `bg-emerald-50 hover:bg-emerald-100`
   - Text: "Details auf zolltarifnummern.de ansehen"
   - data-testid="zolltarif-external-link"

---

## CHANGELOG (2026-01-12)

### Kontrakt-Modul Erweitert (Major Update) ✅
Basierend auf Echo2-Altsystem-Analyse wurden folgende Erweiterungen implementiert:

**1. Neue Felder im Kontrakt-Kopf:**
- `ist_fixierung` - Fixierungskontrakt-Toggle
- `fix_von`, `fix_bis` - Fixierungszeitraum
- `fix_menge_gesamt` - Gesamt-Fixierungsmenge
- `fix_id_artikel` - Hauptartikel für Fixierung
- `boerse_diff_abs`, `boerse_diff_proz` - Börsendifferenz
- `formulartext_anfang`, `formulartext_ende` - Formulartexte
- `waehrungskurs` - Umrechnungskurs

**2. Erweiterte Positions-Felder:**
- `mengen_toleranz_prozent` - Toleranz für Über-/Unterlieferung
- `ueberliefer_ok` - Überlieferung erlaubt?
- `lieferort` - Abweichender Lieferort (SmartInput)
- `gueltig_von`, `gueltig_bis` - Positions-Gültigkeit
- `einzelpreis_fw`, `gesamtpreis_fw` - Fremdwährungspreise
- `waehrungskurs` - Kurs für Position

**3. Validatoren (aus Echo2 portiert):**
- `VALID_KEINE_FUHREN_ZUGEORDNET` - Prüft zugeordnete Fuhren
- `VALID_KEINE_EK_VK_ZUORDNUNG` - Prüft EK-VK-Zuordnungen
- `VALID_KOPF_OFFEN` - Kontrakt noch bearbeitbar?
- `VALID_MENGE_PREIS_KOMPLETT` - Artikelposition vollständig
- `VALID_UEBERLIEFER_PRUEFUNG` - Warnung bei Überlieferung
- `VALID_DUBLETTEN_PRUEFUNG` - Ähnliche Kontrakte warnen
- `calculate_lieferstatus()` - Berechnet Lieferstatus

**4. Neue Sektionen im Frontend:**
- **Fixierung** - Komplett neue Sektion für Börsenpreisfixierung
- **Formulartexte** - Texte vor/nach Positionen
- **Währung mit OECD-Dropdown** (aus waehrungen.ts)

**5. Positions-Dialog:**
- Vollständig neuer Dialog mit allen Feldern
- SmartInput für Artikel und Lieferort
- Toleranz-Einstellungen pro Position

**Dateien:**
- `/app/backend/routers/kontrakte.py` - HEAVILY MODIFIED
- `/app/frontend/src/features/kontrakte/KontraktePage.tsx` - HEAVILY MODIFIED

---

### Bankverbindungen mit Währung ✅
- Währungs-Dropdown mit 18 OECD-Währungen (€, $, £, ¥, CHF...)
- Zentrale Datei `/app/frontend/src/data/waehrungen.ts` für Referenzierung

---

### Ansprechpartner-Ansicht Modernisiert (P0) ✅
1. **Neue Komponente `AnsprechpartnerSection.tsx` implementiert:**
   - **Verbessertes Tabellenlayout:** Separate Spalten für Name/Funktion, E-Mail, Telefon, Abteilung
   - Namen werden jetzt vollständig angezeigt (kein Abschneiden mehr)
   - Wildcard-Suche (* und ? als Platzhalter)
   - **Doppelklick öffnet Detail-Modal** mit allen Kontaktinformationen
   - Avatar mit Initialen oder Profilbild
   - Klickbare Kontaktdaten (Telefon, E-Mail)
   - Hover-Aktionen: Details, Bearbeiten, Hauptkontakt setzen, Löschen
   - Hauptkontakt wird mit Stern-Badge hervorgehoben
2. **Neue Felder im Bearbeitungsformular:**
   - **Adresse (falls abweichend):** Straße, PLZ, Ort
   - **Korrespondenzsprache:** Dropdown mit OECD-Ländern und Flaggen (🇩🇪 🇬🇧 🇫🇷 🇪🇸 etc.)
3. **Code-Bereinigung:**
   - Obsolete Dateien entfernt: `dynamic-field.tsx`, `useFieldBinding.ts`
   - Diese wurden durch `SmartInput` und `fieldBindingsStore` ersetzt
4. **Test-Ergebnis**: Manuell per Screenshot verifiziert ✅

---

## CHANGELOG (2026-01-11)

### CSV/Excel Upload für Referenztabellen (NEU) ✅
1. **Backend-Endpunkte erstellt:**
   - `POST /api/system/reference-upload/analyze` - Dateianalyse mit Spaltenerkennung
   - `POST /api/system/reference-upload/import` - Import in Referenztabelle
2. **Frontend FileUploadWizard implementiert:**
   - 4-Schritte-Wizard mit modernem Drag & Drop UI
   - Automatische Datentyperkennung (string, number, date, boolean)
   - Flexibles Spalten-Mapping mit Primary Key Auswahl
3. **Test-Ergebnis**: 13/13 Backend-Tests bestanden (100%)
4. **Test-Report**: `/app/test_reports/iteration_18.json`
5. **Neue Dependencies**: `openpyxl`, `xlrd` (für Excel-Support)

### Intelligente Referenztabellen-Verknüpfung (NEU) ✅
1. **Backend-Endpoints erstellt:**
   - `GET /api/system/modules` - Verfügbare Module und Felder
   - `GET/POST/DELETE /api/system/field-bindings` - CRUD für Verknüpfungen
   - `GET /api/system/field-binding/lookup` - Verknüpfungsdetails
   - `GET /api/system/reference-select/{module}/{field_name}` - Dropdown-Optionen
   - `POST /api/system/validate-reference-value` - Wert-Validierung
2. **Frontend-Komponenten:**
   - `ReferenceSelect` - Wiederverwendbares Dropdown mit automatischer Bindungs-Erkennung
   - `FieldBindingsManager` - Admin-UI für Verknüpfungsverwaltung
3. **Integration in Artikel-Modul:**
   - Zolltarifnummer-Feld zeigt jetzt durchsuchbares Dropdown mit Zolltarifnummern
4. **Test-Ergebnis**: 22/22 Backend-Tests bestanden (100%)
5. **Test-Report**: `/app/test_reports/iteration_19.json`

### P0 Berechtigungsdurchsetzung - VERIFIZIERT ✅
1. **Backend-Tests**: 20/20 Tests bestanden (100%)
2. **Frontend-Tests**: Routenschutz + Sidebar-Filterung funktionieren korrekt
3. **Test-Report**: `/app/test_reports/iteration_15.json`

### P1 Einstellungs-Formulare an Backend angebunden ✅
1. **Neue Backend-Endpunkte erstellt:**
   - `GET/PUT /api/profil` - Eigenes Benutzerprofil verwalten
   - `POST /api/profil/passwort` - Eigenes Passwort ändern
   - `PUT/DELETE /api/profil/profilbild` - Profilbild hochladen/löschen
   - `PUT/DELETE /api/profil/unterschrift` - Unterschrift hochladen/löschen
   - `GET/PUT /api/admin/systemeinstellungen` - Systemweite Einstellungen (Admin only)

2. **Frontend-Seiten mit Backend verbunden:**
   - `ProfilPage.tsx` - Lädt und speichert Profildaten
   - `PasswortPage.tsx` - Passwort ändern mit Stärke-Indikator
   - `SystemeinstellungenPage.tsx` - Systemeinstellungen (nur für Admins)

3. **Test-Ergebnis**: 16/16 Backend-Tests bestanden (100%)
4. **Test-Report**: `/app/test_reports/iteration_16.json`
5. **Test-Dateien**: `/app/tests/test_profil_einstellungen.py`

## CHANGELOG (2026-01-09)
### Neue Features:
1. **Kreditversicherungs-Modul KOMPLETT NEUGESTALTET**
   - **Struktur**: 1 Hauptvertrag (Kopf) mit Gesamtlimit → n Kundenpositionen (Unterverträge)
   - **Hauptvertrag**: Versicherungsnummer, Versicherer, Gesamtlimit, Vertragsbeginn/-ende
   - **Kundenposition**: Kreditlimit, Unterversicherungsnummer, Fakturierungsfrist, Gültigkeitsdatum
   - **Geschäftslogik**: Auslastung = Summe Kundenlimits / Gesamtlimit × 100%
   - **Gültigkeitsregel**: Enddatum Hauptvertrag sticht IMMER das Enddatum der Unterverträge
   - **Frontend**: Smarte Grid-Tabelle mit Suchfunktion, Auslastungsbalken, Detail-Panel
   - **Tests**: 14/14 Backend-Tests bestanden (100%), Frontend vollständig getestet

### Neue Komponenten:
- `/app/frontend/src/components/ui/progress.tsx` - Progress-Balken für Auslastungsanzeige

## CHANGELOG (2026-01-08)
### Bugfixes:
1. **Speicher-Button funktioniert nicht (BEHOBEN)**
   - **Ursache:** Das Zod-Schema für die Adressvalidierung verwendete `.optional()`, was nur `undefined` erlaubte, nicht aber `null`. Datenbankdaten enthielten jedoch `null`-Werte.
   - **Lösung:** Schema auf `.nullish()` umgestellt, das sowohl `null` als auch `undefined` akzeptiert.
   - **Zusätzlich:** Verbesserte Fehlerbehandlung mit Toast-Meldungen bei Validierungsfehlern hinzugefügt.

2. **OCR-Funktion wirft Fehler (BEHOBEN)**
   - **Ursache:** Die `emergentintegrations`-Bibliothek API hatte sich geändert. Der Code verwendete `image_contents` als Parameter, aber der korrekte Parameter ist `file_contents`.
   - **Lösung:** Parameter von `image_contents` auf `file_contents` geändert.
