# Rohstoff Portal - Product Requirements Document

## Projektstatus: ENTWICKLUNG AKTIV

## Original Problem Statement
Modernisierung einer veralteten Java/Echo2-Anwendung "Rohstoff Portal" zu einer modernen Architektur mit:
- **Backend:** FastAPI (Python)
- **Frontend:** React + TypeScript + Vite
- **Datenbank:** MongoDB
- **UI Framework:** TailwindCSS + shadcn/ui

Die gesamte Geschäftslogik der bestehenden Anwendung soll 1:1 übernommen werden.

---

## Implementiert (Stand: 09.01.2026)

### ✅ UX-Refactoring: "Neu"-Button → Sidebar (NEU - 09.01.2026)
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
- [ ] Backend-Refactoring: `server.py` in Module aufteilen (routers, models, services) - DRINGEND (>3600 Zeilen!)

### P1 - Hohe Priorität
- [ ] PDF-Export für Rechnungen und Lieferscheine
- [ ] Validierungs-Feedback im Frontend anzeigen (Fehler/Warnungen)

### P2 - Mittlere Priorität
- [ ] Positionen im Kontrakt bearbeiten (UI zum Hinzufügen/Löschen)
- [ ] Positionen in Rechnungen bearbeiten/löschen
- [ ] Suchfeld in der globalen Navigation funktionsfähig machen
- [ ] Dark/Light Mode Toggle
- [ ] Datumsauswahl mit shadcn Calendar statt native Input

### P3 - Niedrige Priorität
- [ ] PWA Service Worker
- [ ] Offline-Fähigkeit
- [ ] Echte Aktivitäten-Historie im Dashboard
- [ ] Benutzereinstellungen-Seite

---

## Dateistruktur

```
/app/
├── backend/
│   ├── server.py          # Haupt-API mit allen Endpunkten (>2500 Zeilen - Refactoring nötig!)
│   ├── tests/             # Pytest Tests
│   │   └── test_artikel_kontrakte.py  # 24 Tests für Artikel/Kontrakte
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

## CHANGELOG (2026-01-08)
### Bugfixes:
1. **Speicher-Button funktioniert nicht (BEHOBEN)**
   - **Ursache:** Das Zod-Schema für die Adressvalidierung verwendete `.optional()`, was nur `undefined` erlaubte, nicht aber `null`. Datenbankdaten enthielten jedoch `null`-Werte.
   - **Lösung:** Schema auf `.nullish()` umgestellt, das sowohl `null` als auch `undefined` akzeptiert.
   - **Zusätzlich:** Verbesserte Fehlerbehandlung mit Toast-Meldungen bei Validierungsfehlern hinzugefügt.

2. **OCR-Funktion wirft Fehler (BEHOBEN)**
   - **Ursache:** Die `emergentintegrations`-Bibliothek API hatte sich geändert. Der Code verwendete `image_contents` als Parameter, aber der korrekte Parameter ist `file_contents`.
   - **Lösung:** Parameter von `image_contents` auf `file_contents` geändert.
