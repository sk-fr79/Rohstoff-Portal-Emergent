/**
 * Währungen - OECD Währungsliste
 * 
 * Referenzierbar aus allen Modulen:
 * - Bankverbindungen (Adressen)
 * - Rechnungen
 * - Kontrakte
 * - Fuhren
 */

export interface Waehrung {
  code: string;      // ISO 4217 Code (z.B. "EUR")
  symbol: string;    // Symbol (z.B. "€")
  name: string;      // Deutscher Name
  land: string;      // Hauptland
}

// OECD Primärwährungen
export const WAEHRUNGEN: Waehrung[] = [
  { code: 'EUR', symbol: '€', name: 'Euro', land: 'Eurozone' },
  { code: 'USD', symbol: '$', name: 'US-Dollar', land: 'USA' },
  { code: 'CHF', symbol: 'CHF', name: 'Schweizer Franken', land: 'Schweiz' },
  { code: 'GBP', symbol: '£', name: 'Britisches Pfund', land: 'Großbritannien' },
  { code: 'JPY', symbol: '¥', name: 'Japanischer Yen', land: 'Japan' },
  { code: 'CAD', symbol: 'C$', name: 'Kanadischer Dollar', land: 'Kanada' },
  { code: 'AUD', symbol: 'A$', name: 'Australischer Dollar', land: 'Australien' },
  { code: 'NZD', symbol: 'NZ$', name: 'Neuseeland-Dollar', land: 'Neuseeland' },
  { code: 'SEK', symbol: 'kr', name: 'Schwedische Krone', land: 'Schweden' },
  { code: 'NOK', symbol: 'kr', name: 'Norwegische Krone', land: 'Norwegen' },
  { code: 'DKK', symbol: 'kr', name: 'Dänische Krone', land: 'Dänemark' },
  { code: 'PLN', symbol: 'zł', name: 'Polnischer Złoty', land: 'Polen' },
  { code: 'CZK', symbol: 'Kč', name: 'Tschechische Krone', land: 'Tschechien' },
  { code: 'HUF', symbol: 'Ft', name: 'Ungarischer Forint', land: 'Ungarn' },
  { code: 'TRY', symbol: '₺', name: 'Türkische Lira', land: 'Türkei' },
  { code: 'MXN', symbol: 'Mex$', name: 'Mexikanischer Peso', land: 'Mexiko' },
  { code: 'KRW', symbol: '₩', name: 'Südkoreanischer Won', land: 'Südkorea' },
  { code: 'ILS', symbol: '₪', name: 'Israelischer Schekel', land: 'Israel' },
];

// Type für Währungscodes
export type WaehrungCode = typeof WAEHRUNGEN[number]['code'];

/**
 * Findet Währung nach Code
 * @param code ISO 4217 Code
 * @returns Währungsobjekt oder EUR als Fallback
 */
export const getWaehrung = (code: string | undefined | null): Waehrung => {
  if (!code) return WAEHRUNGEN[0]; // EUR
  return WAEHRUNGEN.find(w => w.code === code) || WAEHRUNGEN[0];
};

/**
 * Formatiert Währung für Anzeige
 * @param code ISO 4217 Code
 * @returns z.B. "€ EUR"
 */
export const formatWaehrung = (code: string | undefined | null): string => {
  const w = getWaehrung(code);
  return `${w.symbol} ${w.code}`;
};

/**
 * Formatiert Betrag mit Währung
 * @param betrag Numerischer Betrag
 * @param code ISO 4217 Code
 * @returns z.B. "1.234,56 €"
 */
export const formatBetragMitWaehrung = (
  betrag: number | undefined | null, 
  code: string | undefined | null
): string => {
  if (betrag === undefined || betrag === null) return '-';
  const w = getWaehrung(code);
  
  // Deutsche Formatierung
  const formatted = new Intl.NumberFormat('de-DE', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  }).format(betrag);
  
  return `${formatted} ${w.symbol}`;
};

/**
 * Gibt alle Währungs-Optionen für Select zurück
 */
export const getWaehrungsOptionen = () => WAEHRUNGEN.map(w => ({
  value: w.code,
  label: `${w.symbol} ${w.code} - ${w.name}`,
  symbol: w.symbol,
  name: w.name,
}));

// Default Export
export default WAEHRUNGEN;
