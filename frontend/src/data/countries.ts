// Vollständige Liste aller Länder der Welt mit ISO-Codes und UST-Präfixen
export interface Country {
  name: string;
  iso: string;
  ustPraefix: string | null;
  istEu: boolean;
  region: string;
}

// Alle Länder der Welt (alphabetisch nach deutschem Namen)
export const ALL_COUNTRIES: Country[] = [
  // A
  { name: "Afghanistan", iso: "AF", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Ägypten", iso: "EG", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Albanien", iso: "AL", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Algerien", iso: "DZ", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Andorra", iso: "AD", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Angola", iso: "AO", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Antigua und Barbuda", iso: "AG", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Äquatorialguinea", iso: "GQ", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Argentinien", iso: "AR", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Armenien", iso: "AM", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Aserbaidschan", iso: "AZ", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Äthiopien", iso: "ET", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Australien", iso: "AU", ustPraefix: null, istEu: false, region: "Ozeanien" },
  // B
  { name: "Bahamas", iso: "BS", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Bahrain", iso: "BH", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Bangladesch", iso: "BD", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Barbados", iso: "BB", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Belarus", iso: "BY", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Belgien", iso: "BE", ustPraefix: "BE", istEu: true, region: "Europa" },
  { name: "Belize", iso: "BZ", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Benin", iso: "BJ", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Bhutan", iso: "BT", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Bolivien", iso: "BO", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Bosnien und Herzegowina", iso: "BA", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Botswana", iso: "BW", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Brasilien", iso: "BR", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Brunei", iso: "BN", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Bulgarien", iso: "BG", ustPraefix: "BG", istEu: true, region: "Europa" },
  { name: "Burkina Faso", iso: "BF", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Burundi", iso: "BI", ustPraefix: null, istEu: false, region: "Afrika" },
  // C
  { name: "Chile", iso: "CL", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "China", iso: "CN", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Costa Rica", iso: "CR", ustPraefix: null, istEu: false, region: "Amerika" },
  // D
  { name: "Dänemark", iso: "DK", ustPraefix: "DK", istEu: true, region: "Europa" },
  { name: "Deutschland", iso: "DE", ustPraefix: "DE", istEu: true, region: "Europa" },
  { name: "Dominica", iso: "DM", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Dominikanische Republik", iso: "DO", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Dschibuti", iso: "DJ", ustPraefix: null, istEu: false, region: "Afrika" },
  // E
  { name: "Ecuador", iso: "EC", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "El Salvador", iso: "SV", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Elfenbeinküste", iso: "CI", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Eritrea", iso: "ER", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Estland", iso: "EE", ustPraefix: "EE", istEu: true, region: "Europa" },
  { name: "Eswatini", iso: "SZ", ustPraefix: null, istEu: false, region: "Afrika" },
  // F
  { name: "Fidschi", iso: "FJ", ustPraefix: null, istEu: false, region: "Ozeanien" },
  { name: "Finnland", iso: "FI", ustPraefix: "FI", istEu: true, region: "Europa" },
  { name: "Frankreich", iso: "FR", ustPraefix: "FR", istEu: true, region: "Europa" },
  // G
  { name: "Gabun", iso: "GA", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Gambia", iso: "GM", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Georgien", iso: "GE", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Ghana", iso: "GH", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Grenada", iso: "GD", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Griechenland", iso: "GR", ustPraefix: "EL", istEu: true, region: "Europa" },
  { name: "Großbritannien", iso: "GB", ustPraefix: "GB", istEu: false, region: "Europa" },
  { name: "Guatemala", iso: "GT", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Guinea", iso: "GN", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Guinea-Bissau", iso: "GW", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Guyana", iso: "GY", ustPraefix: null, istEu: false, region: "Amerika" },
  // H
  { name: "Haiti", iso: "HT", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Honduras", iso: "HN", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Hongkong", iso: "HK", ustPraefix: null, istEu: false, region: "Asien" },
  // I
  { name: "Indien", iso: "IN", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Indonesien", iso: "ID", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Irak", iso: "IQ", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Iran", iso: "IR", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Irland", iso: "IE", ustPraefix: "IE", istEu: true, region: "Europa" },
  { name: "Island", iso: "IS", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Israel", iso: "IL", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Italien", iso: "IT", ustPraefix: "IT", istEu: true, region: "Europa" },
  // J
  { name: "Jamaika", iso: "JM", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Japan", iso: "JP", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Jemen", iso: "YE", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Jordanien", iso: "JO", ustPraefix: null, istEu: false, region: "Asien" },
  // K
  { name: "Kambodscha", iso: "KH", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Kamerun", iso: "CM", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Kanada", iso: "CA", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Kap Verde", iso: "CV", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Kasachstan", iso: "KZ", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Katar", iso: "QA", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Kenia", iso: "KE", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Kirgisistan", iso: "KG", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Kiribati", iso: "KI", ustPraefix: null, istEu: false, region: "Ozeanien" },
  { name: "Kolumbien", iso: "CO", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Komoren", iso: "KM", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Kongo", iso: "CG", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Kongo (Dem. Rep.)", iso: "CD", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Kosovo", iso: "XK", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Kroatien", iso: "HR", ustPraefix: "HR", istEu: true, region: "Europa" },
  { name: "Kuba", iso: "CU", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Kuwait", iso: "KW", ustPraefix: null, istEu: false, region: "Asien" },
  // L
  { name: "Laos", iso: "LA", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Lesotho", iso: "LS", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Lettland", iso: "LV", ustPraefix: "LV", istEu: true, region: "Europa" },
  { name: "Libanon", iso: "LB", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Liberia", iso: "LR", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Libyen", iso: "LY", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Liechtenstein", iso: "LI", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Litauen", iso: "LT", ustPraefix: "LT", istEu: true, region: "Europa" },
  { name: "Luxemburg", iso: "LU", ustPraefix: "LU", istEu: true, region: "Europa" },
  // M
  { name: "Madagaskar", iso: "MG", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Malawi", iso: "MW", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Malaysia", iso: "MY", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Malediven", iso: "MV", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Mali", iso: "ML", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Malta", iso: "MT", ustPraefix: "MT", istEu: true, region: "Europa" },
  { name: "Marokko", iso: "MA", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Marshallinseln", iso: "MH", ustPraefix: null, istEu: false, region: "Ozeanien" },
  { name: "Mauretanien", iso: "MR", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Mauritius", iso: "MU", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Mexiko", iso: "MX", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Mikronesien", iso: "FM", ustPraefix: null, istEu: false, region: "Ozeanien" },
  { name: "Moldau", iso: "MD", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Monaco", iso: "MC", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Mongolei", iso: "MN", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Montenegro", iso: "ME", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Mosambik", iso: "MZ", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Myanmar", iso: "MM", ustPraefix: null, istEu: false, region: "Asien" },
  // N
  { name: "Namibia", iso: "NA", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Nauru", iso: "NR", ustPraefix: null, istEu: false, region: "Ozeanien" },
  { name: "Nepal", iso: "NP", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Neuseeland", iso: "NZ", ustPraefix: null, istEu: false, region: "Ozeanien" },
  { name: "Nicaragua", iso: "NI", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Niederlande", iso: "NL", ustPraefix: "NL", istEu: true, region: "Europa" },
  { name: "Niger", iso: "NE", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Nigeria", iso: "NG", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Nordkorea", iso: "KP", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Nordmazedonien", iso: "MK", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Norwegen", iso: "NO", ustPraefix: "NO", istEu: false, region: "Europa" },
  // O
  { name: "Oman", iso: "OM", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Österreich", iso: "AT", ustPraefix: "AT", istEu: true, region: "Europa" },
  // P
  { name: "Pakistan", iso: "PK", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Palau", iso: "PW", ustPraefix: null, istEu: false, region: "Ozeanien" },
  { name: "Panama", iso: "PA", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Papua-Neuguinea", iso: "PG", ustPraefix: null, istEu: false, region: "Ozeanien" },
  { name: "Paraguay", iso: "PY", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Peru", iso: "PE", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Philippinen", iso: "PH", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Polen", iso: "PL", ustPraefix: "PL", istEu: true, region: "Europa" },
  { name: "Portugal", iso: "PT", ustPraefix: "PT", istEu: true, region: "Europa" },
  // R
  { name: "Ruanda", iso: "RW", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Rumänien", iso: "RO", ustPraefix: "RO", istEu: true, region: "Europa" },
  { name: "Russland", iso: "RU", ustPraefix: null, istEu: false, region: "Europa" },
  // S
  { name: "Salomonen", iso: "SB", ustPraefix: null, istEu: false, region: "Ozeanien" },
  { name: "Sambia", iso: "ZM", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Samoa", iso: "WS", ustPraefix: null, istEu: false, region: "Ozeanien" },
  { name: "San Marino", iso: "SM", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "São Tomé und Príncipe", iso: "ST", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Saudi-Arabien", iso: "SA", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Schweden", iso: "SE", ustPraefix: "SE", istEu: true, region: "Europa" },
  { name: "Schweiz", iso: "CH", ustPraefix: "CH", istEu: false, region: "Europa" },
  { name: "Senegal", iso: "SN", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Serbien", iso: "RS", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Seychellen", iso: "SC", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Sierra Leone", iso: "SL", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Simbabwe", iso: "ZW", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Singapur", iso: "SG", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Slowakei", iso: "SK", ustPraefix: "SK", istEu: true, region: "Europa" },
  { name: "Slowenien", iso: "SI", ustPraefix: "SI", istEu: true, region: "Europa" },
  { name: "Somalia", iso: "SO", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Spanien", iso: "ES", ustPraefix: "ES", istEu: true, region: "Europa" },
  { name: "Sri Lanka", iso: "LK", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "St. Kitts und Nevis", iso: "KN", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "St. Lucia", iso: "LC", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "St. Vincent und die Grenadinen", iso: "VC", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Südafrika", iso: "ZA", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Sudan", iso: "SD", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Südkorea", iso: "KR", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Südsudan", iso: "SS", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Suriname", iso: "SR", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Syrien", iso: "SY", ustPraefix: null, istEu: false, region: "Asien" },
  // T
  { name: "Tadschikistan", iso: "TJ", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Taiwan", iso: "TW", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Tansania", iso: "TZ", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Thailand", iso: "TH", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Timor-Leste", iso: "TL", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Togo", iso: "TG", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Tonga", iso: "TO", ustPraefix: null, istEu: false, region: "Ozeanien" },
  { name: "Trinidad und Tobago", iso: "TT", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Tschad", iso: "TD", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Tschechien", iso: "CZ", ustPraefix: "CZ", istEu: true, region: "Europa" },
  { name: "Tunesien", iso: "TN", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Türkei", iso: "TR", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Turkmenistan", iso: "TM", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Tuvalu", iso: "TV", ustPraefix: null, istEu: false, region: "Ozeanien" },
  // U
  { name: "Uganda", iso: "UG", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Ukraine", iso: "UA", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Ungarn", iso: "HU", ustPraefix: "HU", istEu: true, region: "Europa" },
  { name: "Uruguay", iso: "UY", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "USA", iso: "US", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Usbekistan", iso: "UZ", ustPraefix: null, istEu: false, region: "Asien" },
  // V
  { name: "Vanuatu", iso: "VU", ustPraefix: null, istEu: false, region: "Ozeanien" },
  { name: "Vatikanstadt", iso: "VA", ustPraefix: null, istEu: false, region: "Europa" },
  { name: "Venezuela", iso: "VE", ustPraefix: null, istEu: false, region: "Amerika" },
  { name: "Vereinigte Arabische Emirate", iso: "AE", ustPraefix: null, istEu: false, region: "Asien" },
  { name: "Vietnam", iso: "VN", ustPraefix: null, istEu: false, region: "Asien" },
  // Z
  { name: "Zentralafrikanische Republik", iso: "CF", ustPraefix: null, istEu: false, region: "Afrika" },
  { name: "Zypern", iso: "CY", ustPraefix: "CY", istEu: true, region: "Europa" },
];

// ISO zu Land Map für schnellen Zugriff
export const ISO_TO_COUNTRY: Record<string, Country> = {};
ALL_COUNTRIES.forEach(c => {
  ISO_TO_COUNTRY[c.iso] = c;
});

// Land zu ISO Map
export const COUNTRY_TO_ISO: Record<string, string> = {};
ALL_COUNTRIES.forEach(c => {
  COUNTRY_TO_ISO[c.name] = c.iso;
});

// EU-Länder
export const EU_COUNTRIES = ALL_COUNTRIES.filter(c => c.istEu);

// Länder nach Region gruppiert
export const COUNTRIES_BY_REGION: Record<string, Country[]> = {};
ALL_COUNTRIES.forEach(c => {
  if (!COUNTRIES_BY_REGION[c.region]) {
    COUNTRIES_BY_REGION[c.region] = [];
  }
  COUNTRIES_BY_REGION[c.region].push(c);
});
