# Rohstoff Portal - Technische Modernisierung

## Übersicht

Dieses Projekt modernisiert die bestehende Java/Echo2-Anwendung "Rohstoff Portal" zu einer modernen Node.js/TypeScript-Architektur mit PostgreSQL-Datenbank und einem state-of-the-art Enterprise Frontend.

## Technologie-Stack

### Backend
- **Runtime:** Node.js 20 LTS
- **Sprache:** TypeScript 5.x
- **Framework:** Express.js
- **ORM:** Prisma
- **Datenbank:** PostgreSQL 16
- **Cache/Sessions:** Redis 7
- **Validierung:** Zod
- **Logging:** Winston
- **Sicherheit:** Helmet, Argon2, JWT

### Frontend
- **Framework:** React 18 mit TypeScript
- **Build:** Vite
- **Styling:** TailwindCSS + shadcn/ui
- **Animationen:** Framer Motion
- **State:** Zustand + TanStack Query
- **Routing:** React Router v6
- **PWA:** Vite PWA Plugin + Workbox

## Projektstruktur

```
rohstoff-portal-modern/
├── docker-compose.yml       # Container-Orchestrierung
├── backend/
│   ├── src/
│   │   ├── config/          # Konfiguration
│   │   ├── database/        # Migrationen & Seeds
│   │   ├── modules/         # Feature-Module
│   │   │   ├── auth/        # Authentifizierung
│   │   │   ├── mandant/     # Mandantenverwaltung
│   │   │   ├── adresse/     # Adressen/Firmen
│   │   │   ├── artikel/     # Artikelstamm
│   │   │   ├── kontrakt/    # EK/VK-Kontrakte
│   │   │   ├── fuhre/       # Transport
│   │   │   └── rechnung/    # Rechnungen/Gutschriften
│   │   ├── middleware/      # Express Middleware
│   │   ├── utils/           # Hilfsfunktionen
│   │   └── app.ts           # Express Setup
│   ├── prisma/
│   │   └── schema.prisma    # Datenbankschema
│   ├── package.json
│   └── Dockerfile
└── frontend/
    ├── src/
    │   ├── components/      # UI-Komponenten
    │   ├── features/        # Feature-Module
    │   ├── hooks/           # Custom Hooks
    │   ├── services/        # API & Offline
    │   ├── store/           # Zustand Store
    │   └── styles/          # Design Tokens
    ├── public/
    ├── package.json
    └── Dockerfile
```

## Schnellstart

### Voraussetzungen
- Docker & Docker Compose
- Node.js 20+ (für lokale Entwicklung)
- Yarn

### Mit Docker starten

```bash
# Alle Services starten
docker-compose up -d

# Logs anzeigen
docker-compose logs -f

# Services stoppen
docker-compose down
```

### Lokale Entwicklung

```bash
# Backend
cd backend
yarn install
yarn db:generate
yarn dev

# Frontend (in neuem Terminal)
cd frontend
yarn install
yarn dev
```

## Umgebungsvariablen

### Backend (.env)
```env
NODE_ENV=development
PORT=8001
DATABASE_URL=postgresql://user:password@localhost:5432/rohstoff_portal
REDIS_URL=redis://:password@localhost:6379
JWT_SECRET=your_jwt_secret_min_32_chars
JWT_REFRESH_SECRET=your_refresh_secret_min_32_chars
JWT_EXPIRES_IN=15m
JWT_REFRESH_EXPIRES_IN=7d
CORS_ORIGIN=http://localhost:3000
LOG_LEVEL=info
```

## Datenbankmigrationen

```bash
# Neue Migration erstellen
yarn db:migrate

# Prisma Client generieren
yarn db:generate

# Datenbank synchronisieren (Entwicklung)
yarn db:push

# Seeds ausführen
yarn db:seed
```

## API-Endpunkte

### Authentifizierung
| Methode | Endpunkt | Beschreibung |
|---------|----------|-------------|
| POST | `/api/auth/login` | Anmeldung |
| POST | `/api/auth/register` | Registrierung |
| POST | `/api/auth/refresh` | Token erneuern |
| POST | `/api/auth/logout` | Abmeldung |
| POST | `/api/auth/change-password` | Passwort ändern |
| GET | `/api/auth/me` | Aktueller Benutzer |

### Health Check
| Methode | Endpunkt | Beschreibung |
|---------|----------|-------------|
| GET | `/api/health` | Server-Status |

## Sicherheit (NIS2.0 & DSGVO)

- **Passwort-Hashing:** Argon2id (OWASP-Empfehlung)
- **Verschlüsselung:** AES-256-GCM für sensible Daten
- **JWT:** Access-Token (15min) + Refresh-Token-Rotation (7d)
- **Rate Limiting:** Schutz vor Brute-Force
- **Helmet.js:** Sicherheits-Header
- **CORS:** Strikte Origin-Prüfung
- **Input-Validierung:** Zod Schema-Validierung
- **Audit-Logging:** Alle sicherheitsrelevanten Aktionen

## Module (MVP)

### 1. Firmenstamm (Adressen)
- Kunden- und Lieferantenverwaltung
- Kontakte/Ansprechpartner
- Bankverbindungen
- Firmeninformationen

### 2. Artikelstamm
- Artikel und Sorten
- EAK-Codes (Abfallkatalog)
- Artikelgruppen
- Einheiten

### 3. Kontrakte
- Einkaufs- und Verkaufskontrakte
- Positionen mit Preisen
- Gültigkeitszeiträume

### 4. Fuhren/Transport
- Transportaufträge
- Start-/Zieladressen
- Mengen und Wiegungen
- EU-Amtsblatt-Daten

### 5. Rechnungen/Gutschriften
- Rechnungserstellung
- Gutschriften
- Positionen
- Fremdwährung

## Lizenz

Propritäre Software - Alle Rechte vorbehalten.
