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

### ✅ UI/UX Redesign - Enterprise Light Theme (NEU - 09.01.2026)
Komplette Umstellung auf ein helles, professionelles Enterprise-Design:
- **Haupt-Sidebar:** Dunkle Navy-Farbe mit Emerald-Akzenten, aufklappbare Gruppen
- **Dashboard:** Statistik-Karten, Umsatz-Chart, Schnellzugriff, Aktivitäten-Feed
- **Login-Seite:** Modernes Split-Layout mit Branding-Bereich
- **Adressen-Detail:** Neue Sidebar-Navigation statt Top-Tabs
  - Sektionen: Stammdaten, Kontakt, Finanzen, Steuer, Sperren, Bemerkungen
  - Übersichtliche Feldanordnung mit Gruppierung
  - Slide-In Panel von rechts

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

### Artikel
- `GET /api/artikel` - Liste mit Suche/Pagination
- `POST /api/artikel` - Neuer Artikel

### Kontrakte
- `GET /api/kontrakte` - Liste mit Filter
- `GET /api/kontrakte/{id}` - Einzelner Kontrakt
- `POST /api/kontrakte` - Neuer Kontrakt
- `POST /api/kontrakte/{id}/positionen` - Position hinzufügen
- `POST /api/kontrakte/{id}/abschliessen` - Kontrakt abschließen

### Dashboard
- `GET /api/dashboard/stats` - Statistiken

---

## Offene/Kommende Aufgaben

### P0 - Aktueller Fokus
- [x] Geschäftslogik aus Java-Code integrieren (Adress-Validierung) ✅ ERLEDIGT
- [ ] Geschäftslogik für Artikel-Validierung integrieren
- [ ] Geschäftslogik für Kontrakt-Validierung integrieren

### P1 - Hohe Priorität
- [ ] Backend-Refactoring: `server.py` in Module aufteilen (routers, models, services)
- [ ] Fuhren-Modul implementieren
- [ ] Rechnungen-Modul implementieren
- [ ] Adresse bearbeiten mit PUT-API verbinden
- [ ] Artikel bearbeiten/löschen im Frontend

### P2 - Mittlere Priorität
- [ ] Detailansicht für Kontrakte
- [ ] Positionen im Kontrakt bearbeiten
- [ ] Suchfeld in der globalen Navigation funktionsfähig machen
- [ ] Dark/Light Mode Toggle

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
│   ├── server.py          # Haupt-API mit allen Endpunkten
│   └── requirements.txt
├── frontend/
│   ├── src/
│   │   ├── components/ui/  # shadcn Komponenten
│   │   ├── features/
│   │   │   ├── auth/       # Login
│   │   │   ├── dashboard/  # Dashboard mit Chart
│   │   │   ├── adressen/   # Adressverwaltung
│   │   │   ├── artikel/    # Artikelverwaltung
│   │   │   └── kontrakte/  # Kontraktverwaltung
│   │   ├── services/api/   # API Client
│   │   └── store/          # Zustand Store
│   └── package.json
└── test_reports/           # Test-Ergebnisse
```

---

## Referenz-Dokumente

- `/app/rohstoff-portal-modern/docs/MODUL_ANALYSE.md` - Analyse der Java-Anwendung
- `/app/rohstoff-portal-modern/backend/prisma/schema.prisma` - Referenz für Datenbankmodell
