# Echo2 Kreditversicherung → Neue Stammdaten-App: Migrations-Analyse

**Erstellt:** 2026-01-09  
**Analysierte Quellen:** `/app/rohstoff/Echo2BusinessLogic/FIRMENSTAMM/KREDITVERSICH/`

---

## 1. ÜBERSICHT

Die Kreditversicherungs-Verwaltung im Echo2-System ist ein komplexes Modul mit drei Tabellen und mehreren Geschäftslogik-Klassen. Es ermöglicht die Verwaltung von Kreditversicherungsverträgen für Kunden und deren Überwachung bei Geschäftsvorgängen.

### 1.1 Datenbank-Struktur (aus Java-Code extrahiert)

```
┌─────────────────────────────┐
│  JT_KREDITVERS_KOPF         │  (Versicherungs-Kopf/Vertrag)
├─────────────────────────────┤
│  ID_KREDITVERS_KOPF (PK)    │
│  ID_MANDANT                 │
│  ID_ADRESSE_VERS (FK)       │  → Versicherungsgesellschaft
│  VERSICHERUNGSNUMMER        │
│  FAKTURIERUNGSFRIST         │  → Verlängerte Fakturierungsfrist in Tagen
│  AKTIV                      │
│  BEMERKUNGEN                │
└─────────────────────────────┘
              │
              │ 1:n
              ▼
┌─────────────────────────────┐
│  JT_KREDITVERS_ADRESSE      │  (Zugeordnete Kunden)
├─────────────────────────────┤
│  ID_KREDITVERS_ADRESSE (PK) │
│  ID_KREDITVERS_KOPF (FK)    │
│  ID_ADRESSE (FK)            │  → Versicherter Kunde
│  ID_MANDANT                 │
└─────────────────────────────┘
              │
              │ (Kopf hat auch)
              ▼
┌─────────────────────────────┐
│  JT_KREDITVERS_POS          │  (Versicherungs-Positionen/Limits)
├─────────────────────────────┤
│  ID_KREDITVERS_POS (PK)     │
│  ID_KREDITVERS_KOPF (FK)    │
│  ID_MANDANT                 │
│  GUELTIG_AB                 │  → Gültigkeitszeitraum
│  GUELTIG_BIS                │
│  BETRAG                     │  → Versicherungslimit
│  BETRAG_ANFRAGE             │  → Angefragter Betrag
│  AKTIV                      │
│  BEMERKUNGEN                │
└─────────────────────────────┘
```

### 1.2 Besonderheit: Ein Vertrag kann mehrere Firmen abdecken

Ein Kreditversicherungsvertrag (KOPF) kann **mehrere Adressen** (Firmen) über die Verknüpfungstabelle `JT_KREDITVERS_ADRESSE` abdecken. Die Forderungen aller verknüpften Firmen werden **summiert** und gegen das Limit geprüft.

---

## 2. GESCHÄFTSLOGIK (aus Java-Code)

### 2.1 `KV_Info.java` - Kreditlimit-Abfrage

```java
// Ermittelt alle aktiven Kreditlimits für einen Kunden
public Vector<KV_Info_Entry> getKreditlimitsFor(String sIDAdresse) {
    // 1. Externe Kreditversicherungen abfragen
    SELECT GUELTIG_AB, GUELTIG_BIS, BETRAG, BETRAG_ANFRAGE, ID_KREDITVERS_KOPF
    FROM JT_KREDITVERS_POS P
    INNER JOIN JT_KREDITVERS_KOPF K ON P.ID_KREDITVERS_KOPF = K.ID_KREDITVERS_KOPF
    INNER JOIN JT_KREDITVERS_ADRESSE A ON A.ID_KREDITVERS_KOPF = K.ID_KREDITVERS_KOPF
    WHERE K.AKTIV = 'Y' AND P.AKTIV = 'Y' AND A.ID_ADRESSE = ?
    
    // 2. Zusätzlich: Internes Kreditlimit aus FIRMENINFO
    // (KREDITBETRAG_INTERN, KREDITBETRAG_INTERN_VALID_TO)
}
```

### 2.2 `KV_Info_Entry.java` - Einzelner Kreditversicherungs-Eintrag

```java
// Datenstruktur für einen KV-Eintrag
class KV_Info_Entry {
    BigDecimal _Betrag;           // Versicherungslimit
    BigDecimal _Anfragebetrag;    // Angefragter Betrag
    Date       _Gueltig_ab;       // Gültig ab
    Date       _Gueltig_bis;      // Gültig bis
    String     _Description;      // Beschreibung (z.B. "Interner Firmenkredit")
    String     _idKVKopf;         // ID des Versicherungskopfes
}
```

### 2.3 `STATKD_StatusErmittlung_Kreditversicherung.java` - Fuhren-Prüfung

Bei jeder Fuhre wird geprüft, ob die Kundenforderungen das Kreditlimit überschreiten:

```java
public boolean pruefeFuhre(String sIDFuhre) {
    // 1. Alle beteiligten Adressen der Fuhre ermitteln (Start, Ziel, Zwischenstopps)
    // 2. Für jede Adresse:
    //    a) Kreditlimit ermitteln (getKreditlimitInfos)
    //    b) Alle verknüpften Adressen des KV-Vertrags ermitteln
    //    c) Forderungen aller verknüpften Adressen summieren
    //    d) Wenn Forderung > Kreditlimit: WARNUNG senden
    
    // Bei Überschreitung:
    // - Meldung an definierte Mitarbeiter (MELDUNG_KREDITVERS_BETRAG = 'Y')
    // - Warnung im System anzeigen
    // - Fuhre kann trotzdem freigegeben werden (mit Vermerk)
}
```

### 2.4 `KV_Lib.java` - Bibliothek für erweiterte Abfragen

Enthält Funktionen für:
- Prüfung ob KV mit Fakturierungsfrist existiert
- Ermittlung der verlängerten Fakturierungsfrist
- Abfrage aller KV-Positionen mit Fakturierungsfrist
- Prüfung auf offene Abschlussdaten

---

## 3. IMPLEMENTIERUNGS-VORSCHLAG FÜR NEUE APP

### 3.1 MongoDB-Schema (Embedded Documents)

```python
# Option A: Embedded in Adresse (für einfache Fälle)
# NICHT EMPFOHLEN wegen n:m-Beziehung

# Option B: Separate Collection (EMPFOHLEN)
kreditversicherungen_schema = {
    "_id": str,  # Auto-generated
    "mandant_id": str,
    "versicherungsnummer": str,
    "versicherer_id": str,  # FK zu Adressen (Versicherungsgesellschaft)
    "versicherer_name": str,  # Denormalisiert für Anzeige
    "fakturierungsfrist": int,  # Tage
    "aktiv": bool,
    "bemerkungen": str,
    "created_at": datetime,
    "updated_at": datetime,
    
    # Embedded: Verknüpfte Adressen (versicherte Kunden)
    "versicherte_adressen": [
        {
            "id": str,  # UUID
            "adresse_id": str,  # FK zu Adressen
            "adresse_name": str,  # Denormalisiert
            "adresse_ort": str,
        }
    ],
    
    # Embedded: Positionen (Limits)
    "positionen": [
        {
            "id": str,  # UUID
            "gueltig_ab": str,  # ISO-Date
            "gueltig_bis": str,  # ISO-Date (optional)
            "betrag": float,  # Versicherungslimit
            "betrag_anfrage": float,  # Angefragter Betrag
            "waehrung": str,  # EUR
            "aktiv": bool,
            "bemerkungen": str,
        }
    ]
}

# Zusätzlich in Adresse (für internes Kreditlimit)
adresse_erweiterung = {
    "kredit_limit": float,  # Internes Kreditlimit (bereits vorhanden)
    "kredit_limit_waehrung": str,
    "kredit_limit_gueltig_bis": str,  # ISO-Date
}
```

### 3.2 Backend-Endpunkte

```python
# Kreditversicherungen CRUD
GET    /api/kreditversicherungen                    # Liste aller KV
GET    /api/kreditversicherungen/{id}               # Einzelne KV
POST   /api/kreditversicherungen                    # Neue KV anlegen
PUT    /api/kreditversicherungen/{id}               # KV aktualisieren
DELETE /api/kreditversicherungen/{id}               # KV löschen (soft)

# Verknüpfte Adressen
GET    /api/kreditversicherungen/{id}/adressen      # Verknüpfte Adressen
POST   /api/kreditversicherungen/{id}/adressen      # Adresse verknüpfen
DELETE /api/kreditversicherungen/{id}/adressen/{aid} # Verknüpfung lösen

# Positionen (Limits)
GET    /api/kreditversicherungen/{id}/positionen    # Positionen abrufen
POST   /api/kreditversicherungen/{id}/positionen    # Position hinzufügen
PUT    /api/kreditversicherungen/{id}/positionen/{pid}  # Position aktualisieren
DELETE /api/kreditversicherungen/{id}/positionen/{pid}  # Position löschen

# Abfragen (aus KV_Info.java)
GET    /api/adressen/{id}/kreditlimits              # Alle KV-Limits einer Adresse
GET    /api/adressen/{id}/kreditstatus              # Aktueller Kredit-Status

# Prüfung (aus STATKD_StatusErmittlung_Kreditversicherung.java)
POST   /api/fuhren/{id}/kreditpruefung              # Prüft Fuhre gegen KV
```

### 3.3 Pydantic-Models

```python
from pydantic import BaseModel, Field
from typing import Optional, List
from datetime import datetime

class KreditversicherungPosition(BaseModel):
    id: str = Field(default_factory=lambda: str(uuid.uuid4()))
    gueltig_ab: str
    gueltig_bis: Optional[str] = None
    betrag: float = Field(..., description="Versicherungslimit in EUR")
    betrag_anfrage: Optional[float] = None
    waehrung: str = "EUR"
    aktiv: bool = True
    bemerkungen: Optional[str] = None

class VersicherteAdresse(BaseModel):
    id: str = Field(default_factory=lambda: str(uuid.uuid4()))
    adresse_id: str
    adresse_name: Optional[str] = None
    adresse_ort: Optional[str] = None

class KreditversicherungCreate(BaseModel):
    versicherungsnummer: Optional[str] = Field(None, max_length=50)
    versicherer_id: Optional[str] = None
    versicherer_name: Optional[str] = None
    fakturierungsfrist: Optional[int] = None  # Tage
    aktiv: bool = True
    bemerkungen: Optional[str] = None
    versicherte_adressen: List[VersicherteAdresse] = []
    positionen: List[KreditversicherungPosition] = []

class KreditlimitInfo(BaseModel):
    """Entspricht KV_Info_Entry aus Java"""
    betrag: float
    betrag_anfrage: Optional[float] = None
    gueltig_ab: Optional[str] = None
    gueltig_bis: Optional[str] = None
    beschreibung: Optional[str] = None
    kv_kopf_id: Optional[str] = None
    ist_intern: bool = False  # True für internes Firmenkreditlimit

class KreditstatusResponse(BaseModel):
    """Antwort für Kreditstatus-Abfrage"""
    adresse_id: str
    gesamtlimit: float
    aktuelle_forderungen: float
    verfuegbar: float
    status: str  # "ok", "warnung", "ueberschritten"
    limits: List[KreditlimitInfo]
```

### 3.4 Service-Logik (Python)

```python
# /app/backend/services/kreditversicherung.py

class KreditversicherungService:
    """
    Portiert aus:
    - KV_Info.java
    - STATKD_StatusErmittlung_Kreditversicherung.java
    - KV_Lib.java
    """
    
    @staticmethod
    async def get_kreditlimits_for_adresse(adresse_id: str, db) -> List[KreditlimitInfo]:
        """
        Ermittelt alle aktiven Kreditlimits für einen Kunden.
        Entspricht KV_Info.getKreditlimitsFor()
        """
        limits = []
        
        # 1. Externe Kreditversicherungen abfragen
        pipeline = [
            {"$match": {"versicherte_adressen.adresse_id": adresse_id, "aktiv": True}},
            {"$unwind": "$positionen"},
            {"$match": {"positionen.aktiv": True}},
            {"$project": {
                "betrag": "$positionen.betrag",
                "betrag_anfrage": "$positionen.betrag_anfrage",
                "gueltig_ab": "$positionen.gueltig_ab",
                "gueltig_bis": "$positionen.gueltig_bis",
                "kv_kopf_id": "$_id",
                "versicherungsnummer": 1
            }}
        ]
        
        async for doc in db.kreditversicherungen.aggregate(pipeline):
            limits.append(KreditlimitInfo(
                betrag=doc["betrag"],
                betrag_anfrage=doc.get("betrag_anfrage"),
                gueltig_ab=doc.get("gueltig_ab"),
                gueltig_bis=doc.get("gueltig_bis"),
                kv_kopf_id=str(doc["kv_kopf_id"]),
                beschreibung=f"KV {doc.get('versicherungsnummer', '')}",
                ist_intern=False
            ))
        
        # 2. Internes Kreditlimit aus Adresse
        adresse = await db.adressen.find_one({"_id": adresse_id})
        if adresse and adresse.get("kredit_limit"):
            limits.append(KreditlimitInfo(
                betrag=adresse["kredit_limit"],
                gueltig_bis=adresse.get("kredit_limit_gueltig_bis"),
                beschreibung="Interner Firmenkredit",
                ist_intern=True
            ))
        
        return limits
    
    @staticmethod
    async def get_alle_verknuepfte_adressen(kv_id: str, db) -> List[str]:
        """
        Ermittelt alle Adress-IDs die zu einer Kreditversicherung gehören.
        Entspricht KV_Info.getAllAddressIDsConnected()
        """
        kv = await db.kreditversicherungen.find_one({"_id": kv_id})
        if not kv:
            return []
        return [a["adresse_id"] for a in kv.get("versicherte_adressen", [])]
    
    @staticmethod
    async def pruefe_kreditlimit(
        adresse_ids: List[str], 
        neuer_betrag: float,
        db
    ) -> dict:
        """
        Prüft ob ein neuer Betrag das Kreditlimit überschreiten würde.
        Entspricht STATKD_StatusErmittlung_Kreditversicherung.pruefeFuhre()
        """
        gesamtlimit = 0.0
        aktuelle_forderungen = 0.0  # TODO: Aus Rechnungen/Fuhren berechnen
        
        # Alle verknüpften Adressen ermitteln und Limits summieren
        alle_adressen = set(adresse_ids)
        
        for adresse_id in adresse_ids:
            limits = await KreditversicherungService.get_kreditlimits_for_adresse(
                adresse_id, db
            )
            for limit in limits:
                gesamtlimit = max(gesamtlimit, limit.betrag)
                
                # Alle verknüpften Adressen der KV hinzufügen
                if limit.kv_kopf_id:
                    verknuepfte = await KreditversicherungService.get_alle_verknuepfte_adressen(
                        limit.kv_kopf_id, db
                    )
                    alle_adressen.update(verknuepfte)
        
        # TODO: Forderungen aller verknüpften Adressen summieren
        # aktuelle_forderungen = await ForderungenService.get_summe(alle_adressen, db)
        
        neue_summe = aktuelle_forderungen + neuer_betrag
        verfuegbar = gesamtlimit - aktuelle_forderungen
        
        return {
            "gesamtlimit": gesamtlimit,
            "aktuelle_forderungen": aktuelle_forderungen,
            "neuer_betrag": neuer_betrag,
            "neue_summe": neue_summe,
            "verfuegbar": verfuegbar,
            "status": "ok" if neue_summe <= gesamtlimit else "ueberschritten",
            "ueberschreitung": max(0, neue_summe - gesamtlimit)
        }
```

---

## 4. FRONTEND-KOMPONENTEN

### 4.1 Kreditversicherungen-Tab in AdressenPage

```tsx
// Zeigt alle KV-Verträge an, bei denen die aktuelle Adresse verknüpft ist
interface KreditversicherungenTabProps {
  adresseId: string;
  isEditing: boolean;
}

function KreditversicherungenTab({ adresseId, isEditing }: Props) {
  // GET /api/adressen/{id}/kreditlimits
  // Anzeige: Liste mit Betrag, Gültig ab/bis, Versicherer, Status
}
```

### 4.2 Kreditversicherungs-Modul (eigenständige Seite)

```tsx
// /kreditversicherungen - Vollständige CRUD-Verwaltung
// - Liste aller Verträge
// - Detail-Sidebar mit:
//   - Kopfdaten (Versicherer, Nummer, Fakturierungsfrist)
//   - Verknüpfte Adressen (mit Suche/Hinzufügen)
//   - Positionen (Limits mit Gültigkeitszeitraum)
```

### 4.3 Kreditstatus-Badge in Fuhren

```tsx
// Zeigt Warnung wenn Kreditlimit überschritten
<KreditstatusBadge adresseIds={[startAdresse, zielAdresse]} />
```

---

## 5. MIGRATIONS-CHECKLISTE

### Phase 1: Datenmodell (P2)
- [ ] Collection `kreditversicherungen` anlegen
- [ ] Felder in `adressen` erweitern: `kredit_limit_gueltig_bis`
- [ ] Index auf `versicherte_adressen.adresse_id`

### Phase 2: Backend-Endpunkte (P2)
- [ ] CRUD für Kreditversicherungen
- [ ] GET /api/adressen/{id}/kreditlimits
- [ ] GET /api/adressen/{id}/kreditstatus

### Phase 3: Frontend (P2)
- [ ] Tab "Kreditversicherung" in AdressenPage
- [ ] Eigene Seite `/kreditversicherungen` (optional)

### Phase 4: Integration (P3)
- [ ] Prüfung bei Fuhren-Erstellung
- [ ] Warnung bei Limit-Überschreitung
- [ ] Benachrichtigung an definierte Benutzer

---

## 6. BESONDERE HINWEISE

1. **n:m-Beziehung:** Ein Vertrag kann mehrere Kunden abdecken, ein Kunde kann mehrere Verträge haben.

2. **Forderungen-Summe:** Die Forderungen aller verknüpften Adressen werden summiert und gegen das Limit geprüft.

3. **Internes Kreditlimit:** Zusätzlich zum externen KV-Vertrag kann ein internes Firmenkreditlimit gepflegt werden.

4. **Fakturierungsfrist:** Einige KV-Verträge gewähren eine verlängerte Fakturierungsfrist (in Tagen).

5. **Zeitliche Gültigkeit:** Positionen haben einen Gültigkeitszeitraum (`gueltig_ab`, `gueltig_bis`).

---

*Dokument erstellt durch Code-Analyse am 2026-01-09*
