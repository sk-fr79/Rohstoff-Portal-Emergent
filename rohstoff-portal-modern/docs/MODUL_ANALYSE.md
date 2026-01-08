# Modul-Analyse - Rohstoff Portal

## Übersicht der identifizierten Module

Basierend auf der Analyse des Java/Echo2-Repositories wurden folgende Hauptmodule identifiziert:

---

## 1. FIRMENSTAMM (Adressen)

**Pfad:** `/rohstoff/Echo2BusinessLogic/FIRMENSTAMM/`

### Kernentitäten
- **Adresse (JT_ADRESSE):** Haupttabelle für alle Geschäftspartner
- **Firmeninfo (JT_FIRMENINFO):** Erweiterte Firmendaten (1:1 zu Adresse)
- **Mitarbeiter/Kontakt (JT_MITARBEITER):** Ansprechpartner pro Adresse
- **Bankverbindung (JT_BANKVERBINDUNG):** Bankdaten pro Adresse
- **Kommunikation:** Telefon, Fax, Email, Web

### Business-Logic Highlights
- Nummernkreis-Verwaltung für Debitoren/Kreditoren (Inland, EU, Nicht-EU)
- Automatische KDNR-Generierung basierend auf Adresstyp und Land
- Kreditlimit-Prüfung und Verwaltung
- USt-ID-Validierung
- Sperren für Rechnungen, Gutschriften, Warenein-/ausgang
- EU-Beiblatt-Daten für grenzüberschreitende Transporte
- Anhang 7 (Abfallrecht) Quelle/Ziel-Sicherheit

### Kritische Felder
```typescript
// Adresstypen
1 = Kunde
2 = Lieferant
3 = Spedition
4 = Interessent
5 = Sonstige

// Sperren
rechnungen_sperren: boolean
gutschriften_sperren: boolean
wareneingang_sperren: boolean
warenausgang_sperren: boolean
```

---

## 2. ARTIKELSTAMM

**Pfad:** `/rohstoff/Echo2BusinessLogic/ARTIKELSTAMM/`

### Kernentitäten
- **Artikel (JT_ARTIKEL):** Hauptartikel/Warengrupppen
- **Artikelbezeichnung (JT_ARTIKEL_BEZ):** Sorten/Varianten eines Artikels
- **ArtikelGruppe (JT_ARTIKEL_GRUPPE):** Kategorisierung
- **EAK-Code (JT_EAK_CODE):** Europäischer Abfallkatalog
- **Einheit (JT_EINHEIT):** Mengeneinheiten

### Business-Logic Highlights
- Hierarchische Struktur: Artikel -> Artikelbezeichnungen (Sorten)
- EAK-Code-Zuordnung für Abfallwirtschaft
- Zolltarifnummern und EU-Codes
- Basel-Codes für internationale Abfallverbringung
- Anhang 7 (3a, 3b) Codes
- Mengendivisor für Preisberechnung
- Gefahrgut-Kennzeichnung
- End-of-Waste Status

### Kritische Berechnungen
```typescript
// Preisberechnung
effektiver_preis = einzelpreis / mengendivisor

// Beispiel: Preis pro Tonne
mengendivisor = 1000 (für kg -> t Umrechnung)
```

---

## 3. KONTRAKTE (EK/VK)

**Pfad:** `/rohstoff/Echo2BusinessLogic/BEWEGUNG/KONTRAKTE/`

### Kernentitäten
- **Vkopf_Kon (JT_VKOPF_KON):** Kontrakt-Kopf
- **Vpos_Kon (JT_VPOS_KON):** Kontrakt-Positionen
- **Vpos_Kon_Term:** Terminpositionen
- **Vpos_Kon_Trakt:** Traktierungen

### Business-Logic Highlights
- Unterscheidung EK-Kontrakt / VK-Kontrakt
- Fixierungen (Börsenpreisbindung)
- Gültigkeitszeiträume
- Preisgleitklauseln
- Automatische Buchungsnummer-Generierung
- Adress-Snapshot im Kontrakt (Denormalisierung für historische Daten)
- Lieferadressen-Verwaltung
- Skonto und Zahlungsbedingungen

### Buchungsnummer-Format
```typescript
// EK-Kontrakt: EKK-JJMM-NNNNN
// VK-Kontrakt: VKK-JJMM-NNNNN
buchungsnummer = `${prefix}-${jahr}${monat}-${laufnummer}`
```

---

## 4. FUHREN/TRANSPORT (TPA)

**Pfad:** `/rohstoff/Echo2BusinessLogic/BEWEGUNG/TPA/`

### Kernentitäten
- **Vpos_Tpa_Fuhre (JT_VPOS_TPA_FUHRE):** Haupttabelle für Fuhren
- **Vpos_Tpa_Fuhre_Ort:** Zwischenstationen
- **Vpos_Tpa_Fuhre_Kosten:** Zusatzkosten
- **Vpos_Tpa_Fuhre_Abzug:** Abzüge

### Business-Logic Highlights
- Start- und Zieladresse (Lieferant -> Abnehmer)
- Kontrakt-Zuordnung (EK und VK)
- Mengenerfassung: Vorgabe, Aufladen, Abladen
- Wiegekarten-Integration
- EU-Amtsblatt-Daten für grenzüberschreitende Transporte
- Transit-Länder
- Automatische Preisermittlung aus Kontrakten
- Storno-Handling

### Mengenlogik
```typescript
// Mengenhierarchie
menge_vorgabe_ko    // Erwartete Menge aus Kontrakt
menge_aufladen_ko   // Tatsächliche Menge beim Aufladen
menge_abladen_ko    // Tatsächliche Menge beim Abladen (maßgeblich für Rechnung)

// Wiegung
aufladen_brutto - aufladen_tara = menge_aufladen_ko
abladen_brutto - abladen_tara = menge_abladen_ko
```

### EU-Amtsblatt Felder
```typescript
print_eu_amtsblatt: boolean  // EU-Beiblatt erforderlich
notifikation_nr: string      // Notifikationsnummer
eu_blatt_menge: number       // Menge für EU-Formular
eu_blatt_volumen: number     // Volumen für EU-Formular
laendercode_transit1-3       // Transitländer
```

---

## 5. RECHNUNGEN/GUTSCHRIFTEN

**Pfad:** `/rohstoff/Echo2BusinessLogic/BEWEGUNG/RECH_GUT/`

### Kernentitäten
- **Vkopf_Rg (JT_VKOPF_RG):** Rechnungs-/Gutschrifts-Kopf
- **Vpos_Rg (JT_VPOS_RG):** Rechnungspositionen
- **Vpos_Rg_Abzug:** Abzugspositionen
- **Vpos_Rg_Vl:** Vorleistungen

### Business-Logic Highlights
- Unterscheidung Rechnung / Gutschrift (ist_gutschrift)
- Fuhren-Zuordnung (automatische Übernahme)
- Betragsberechnung (Netto, MwSt, Brutto)
- Fremdwährung mit Umrechnung
- Skonto-Berechnung
- Storno-Handling
- EU-Steuervermerk für innergemeinschaftliche Lieferungen
- Buchung in FIBU

### Betragsberechnung
```typescript
// Pro Position
gesamtpreis = anzahl * einzelpreis / mengendivisor
steuer = gesamtpreis * steuersatz / 100

// Kopf-Summen
nettosumme = SUM(positionen.gesamtpreis)
steuersumme = SUM(positionen.steuer)
bruttosumme = nettosumme + steuersumme

// Skonto
skonto_betrag = bruttosumme * skonto_prozent / 100
zahlbetrag = bruttosumme - skonto_betrag
```

---

## 6. STAMMDATEN (Lookup-Tabellen)

### Länder & Währungen
- **Land (JT_LAND):** Länderstamm mit Intrastat-Kennzeichen
- **Währung (JT_WAEHRUNG):** Währungen mit Kursen
- **Sprache (JT_SPRACHE):** Sprachen für Belege

### Konditionen
- **Zahlungsbedingungen (JT_ZAHLUNGSBEDINGUNGEN):** Zahltage, Skonto
- **Lieferbedingungen (JT_LIEFERBEDINGUNGEN):** Incoterms etc.

### Abfallwirtschaft
- **EAK-Code (JT_EAK_CODE):** 6-stellige Abfallschlüssel
- **Anhang7-Codes:** Spezielle Abfall-Klassifikationen

### Sonstiges
- **Anrede (JT_ANREDE):** Herr, Frau, Firma etc.
- **Einheit (JT_EINHEIT):** kg, t, Stück, m³ etc.
- **Rechtsform:** GmbH, AG, etc.
- **Branche:** Branchenzuordnung

---

## 7. MANDANTEN-SYSTEM

### Multi-Tenant-Architektur
- Jede Tabelle hat `id_mandant` als Pflichtfeld
- Benutzer sind einem Mandanten zugeordnet
- Nummernkreise sind mandantenspezifisch
- Buchungsprefixe sind mandantenspezifisch

### Mandant-Konfiguration
```typescript
// Nummernkreise
numkreis_debitor_inland_von/bis   // 10000-19999
numkreis_debitor_eu_von/bis       // 20000-29999
numkreis_debitor_nicht_eu_von/bis // 30000-39999
// Analog für Kreditoren

// Buchungsprefixe
buchungsprefix_rech  // z.B. "RE"
buchungsprefix_gut   // z.B. "GS"
buchungsprefix_tpa   // z.B. "TPA"
buchungsprefix_ekk   // z.B. "EKK"
buchungsprefix_vkk   // z.B. "VKK"
```

---

## Business-Logic Regeln (Kritisch)

### 1. Preisermittlung
```typescript
// Fuhre übernimmt Preis aus Kontrakt wenn nicht manuell
if (!fuhre.manuell_preis_ek) {
  fuhre.einzelpreis_ek = kontrakt_ek.position.einzelpreis;
}
if (!fuhre.manuell_preis_vk) {
  fuhre.einzelpreis_vk = kontrakt_vk.position.einzelpreis;
}
```

### 2. Buchungsnummer-Generierung
```typescript
// Format: PREFIX-JJMM-LAUFNUMMER
function generateBuchungsnummer(prefix: string, mandantId: string): string {
  const jahr = new Date().getFullYear().toString().slice(-2);
  const monat = (new Date().getMonth() + 1).toString().padStart(2, '0');
  const laufnummer = getNextLaufnummer(prefix, mandantId);
  return `${prefix}-${jahr}${monat}-${laufnummer.toString().padStart(5, '0')}`;
}
```

### 3. Kreditlimit-Prüfung
```typescript
function pruefeKreditlimit(adresseId: string): boolean {
  const firmeninfo = getFirmeninfo(adresseId);
  if (!firmeninfo.kreditlimit) return true; // Kein Limit gesetzt
  
  const offenePosten = getOffenePosten(adresseId);
  return offenePosten <= firmeninfo.kreditlimit;
}
```

### 4. MwSt-Berechnung
```typescript
// Steuerbefreiung prüfen
function getSteuersatz(adresse: Adresse, artikel: Artikel): number {
  // Innergemeinschaftliche Lieferung (EU)
  if (adresse.firmeninfo?.innergemein_lief_eu && adresse.land?.intrastat_jn) {
    return 0;
  }
  // Drittland (Nicht-EU)
  if (!adresse.land?.eu_mitglied) {
    return 0;
  }
  // Standard-Steuersatz
  return artikel.steuersatz;
}
```

### 5. Soft-Delete Pattern
```typescript
// Alle wichtigen Tabellen nutzen Soft-Delete
interface SoftDelete {
  deleted: boolean;       // Gelöscht-Flag
  del_grund: string;      // Löschgrund (Pflicht)
  del_date: Date;         // Löschdatum
  del_kuerzel: string;    // Benutzer der gelöscht hat
}
```

---

## Datenintegritätsregeln

1. **Referentielle Integrität:** Alle FK-Beziehungen müssen eingehalten werden
2. **Mandantentrennung:** Keine mandantenübergreifenden Zugriffe
3. **Audit-Trail:** Alle Änderungen protokollieren (erstellt_von/am, geaendert_von/letzte_aenderung)
4. **Historisierung:** Gelöschte Daten bleiben erhalten (Soft-Delete)
5. **Denormalisierung:** Adressen werden in Belegen kopiert für historische Korrektheit
