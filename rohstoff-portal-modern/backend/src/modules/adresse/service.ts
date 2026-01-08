/**
 * Adresse-Service
 * Business-Logic für Adressenverwaltung (Firmenstamm)
 * 
 * Übernommene Business-Logic aus Java:
 * - Automatische KDNR-Generierung
 * - Mandantentrennung
 * - Soft-Delete Pattern
 */

import { prisma } from '../../config/database.js';
import { logger } from '../../utils/logger.js';
import { NotFoundError, ValidationError } from '../../middleware/errorHandler.js';
import type {
  CreateAdresseInput,
  UpdateAdresseInput,
  SearchAdresseParams,
  AdresseResponse,
  PaginatedResponse,
} from './types.js';

/**
 * Adressen suchen mit Pagination
 */
export async function search(
  mandantId: string,
  params: SearchAdresseParams
): Promise<PaginatedResponse<AdresseResponse>> {
  const { suche, adresstyp, aktiv, plz, ort, page, limit, sortBy, sortOrder } = params;
  
  // Where-Bedingungen aufbauen
  const where: Record<string, unknown> = { mandantId };
  
  if (suche) {
    where.OR = [
      { name1: { contains: suche, mode: 'insensitive' } },
      { name2: { contains: suche, mode: 'insensitive' } },
      { kdnr: { contains: suche, mode: 'insensitive' } },
      { ort: { contains: suche, mode: 'insensitive' } },
      { email: { contains: suche, mode: 'insensitive' } },
    ];
  }
  
  if (adresstyp !== undefined) where.adresstyp = adresstyp;
  if (aktiv !== undefined) where.aktiv = aktiv;
  if (plz) where.plz = { startsWith: plz };
  if (ort) where.ort = { contains: ort, mode: 'insensitive' };
  
  // Gesamtanzahl für Pagination
  const total = await prisma.adresse.count({ where });
  
  // Daten abrufen
  const adressen = await prisma.adresse.findMany({
    where,
    orderBy: { [sortBy]: sortOrder },
    skip: (page - 1) * limit,
    take: limit,
  });
  
  return {
    data: adressen.map(mapToResponse),
    pagination: {
      page,
      limit,
      total,
      totalPages: Math.ceil(total / limit),
    },
  };
}

/**
 * Adresse nach ID abrufen
 */
export async function getById(
  mandantId: string,
  id: string
): Promise<AdresseResponse> {
  const adresse = await prisma.adresse.findFirst({
    where: { id, mandantId },
  });
  
  if (!adresse) {
    throw new NotFoundError('Adresse');
  }
  
  return mapToResponse(adresse);
}

/**
 * Nächste freie KDNR generieren
 * Business-Logic aus Java übernommen:
 * - Inland: 10000-19999 (Debitor), 40000-49999 (Kreditor)
 * - EU: 20000-29999 (Debitor), 50000-59999 (Kreditor)
 * - Nicht-EU: 30000-39999 (Debitor), 60000-69999 (Kreditor)
 */
async function generateKdnr(
  mandantId: string,
  adresstyp: number,
  landId?: string
): Promise<string> {
  // Mandant-Konfiguration laden
  const mandant = await prisma.mandant.findUnique({
    where: { id: mandantId },
  });
  
  if (!mandant) {
    throw new ValidationError('Mandant nicht gefunden');
  }
  
  // Nummernkreis basierend auf Adresstyp und Land ermitteln
  let vonNr: number;
  let bisNr: number;
  
  // Vereinfachte Logik: Kunde = Debitor, Lieferant = Kreditor
  const istDebitor = adresstyp === 1 || adresstyp === 4; // Kunde oder Interessent
  
  // Land prüfen (vereinfacht - Inland als Standard)
  // TODO: EU/Nicht-EU Unterscheidung basierend auf Land
  if (istDebitor) {
    vonNr = mandant.numkreisDebitorInlandVon;
    bisNr = mandant.numkreisDebitorInlandBis;
  } else {
    vonNr = mandant.numkreisKreditorInlandVon;
    bisNr = mandant.numkreisKreditorInlandBis;
  }
  
  // Höchste KDNR im Bereich finden
  const letzte = await prisma.adresse.findFirst({
    where: {
      mandantId,
      kdnr: {
        gte: vonNr.toString(),
        lte: bisNr.toString(),
      },
    },
    orderBy: { kdnr: 'desc' },
    select: { kdnr: true },
  });
  
  let neueNr = vonNr;
  if (letzte?.kdnr) {
    const letzteNr = parseInt(letzte.kdnr, 10);
    if (!isNaN(letzteNr)) {
      neueNr = letzteNr + 1;
    }
  }
  
  if (neueNr > bisNr) {
    throw new ValidationError('Nummernkreis erschöpft');
  }
  
  return neueNr.toString();
}

/**
 * Adresse erstellen
 */
export async function create(
  mandantId: string,
  input: CreateAdresseInput,
  erstelltVon?: string
): Promise<AdresseResponse> {
  // KDNR generieren wenn nicht angegeben
  let kdnr = input.kdnr;
  if (!kdnr && input.adresstyp) {
    kdnr = await generateKdnr(mandantId, input.adresstyp, input.landId);
  }
  
  const adresse = await prisma.adresse.create({
    data: {
      mandantId,
      ...input,
      kdnr,
      erstelltVon,
    },
  });
  
  logger.info('Adresse erstellt', {
    adresseId: adresse.id,
    mandantId,
    kdnr: adresse.kdnr,
  });
  
  return mapToResponse(adresse);
}

/**
 * Adresse aktualisieren
 */
export async function update(
  mandantId: string,
  id: string,
  input: UpdateAdresseInput,
  geaendertVon?: string
): Promise<AdresseResponse> {
  // Prüfen ob Adresse existiert
  const existing = await prisma.adresse.findFirst({
    where: { id, mandantId },
  });
  
  if (!existing) {
    throw new NotFoundError('Adresse');
  }
  
  const adresse = await prisma.adresse.update({
    where: { id },
    data: {
      ...input,
      geaendertVon,
    },
  });
  
  logger.info('Adresse aktualisiert', { adresseId: id, mandantId });
  
  return mapToResponse(adresse);
}

/**
 * Adresse löschen (Soft-Delete prüfen)
 */
export async function remove(
  mandantId: string,
  id: string
): Promise<void> {
  const existing = await prisma.adresse.findFirst({
    where: { id, mandantId },
  });
  
  if (!existing) {
    throw new NotFoundError('Adresse');
  }
  
  // Prüfen ob Adresse in Verwendung (Kontrakte, Fuhren, Rechnungen)
  const kontrakteCount = await prisma.kontrakt.count({
    where: { adresseId: id },
  });
  
  if (kontrakteCount > 0) {
    // Soft-Delete: Adresse deaktivieren statt löschen
    await prisma.adresse.update({
      where: { id },
      data: { aktiv: false },
    });
    logger.info('Adresse deaktiviert (Soft-Delete)', { adresseId: id });
    return;
  }
  
  // Hart löschen wenn keine Referenzen
  await prisma.adresse.delete({ where: { id } });
  logger.info('Adresse gelöscht', { adresseId: id });
}

// Helper: Adresse zu Response mappen
function mapToResponse(adresse: {
  id: string;
  mandantId: string;
  kdnr: string | null;
  vorname: string | null;
  name1: string;
  name2: string | null;
  name3: string | null;
  strasse: string | null;
  hausnummer: string | null;
  plz: string | null;
  ort: string | null;
  ortzusatz: string | null;
  telefon: string | null;
  telefax: string | null;
  email: string | null;
  webseite: string | null;
  adresstyp: number | null;
  aktiv: boolean;
  barkunde: boolean;
  transfer: boolean;
  rechnungenSperren: boolean;
  gutschriftenSperren: boolean;
  wareneingangSperren: boolean;
  warenausgangSperren: boolean;
  bemerkungen: string | null;
  erstelltAm: Date;
  letzteAenderung: Date;
}): AdresseResponse {
  return {
    id: adresse.id,
    mandantId: adresse.mandantId,
    kdnr: adresse.kdnr ?? undefined,
    vorname: adresse.vorname ?? undefined,
    name1: adresse.name1,
    name2: adresse.name2 ?? undefined,
    name3: adresse.name3 ?? undefined,
    strasse: adresse.strasse ?? undefined,
    hausnummer: adresse.hausnummer ?? undefined,
    plz: adresse.plz ?? undefined,
    ort: adresse.ort ?? undefined,
    ortzusatz: adresse.ortzusatz ?? undefined,
    telefon: adresse.telefon ?? undefined,
    telefax: adresse.telefax ?? undefined,
    email: adresse.email ?? undefined,
    webseite: adresse.webseite ?? undefined,
    adresstyp: adresse.adresstyp ?? undefined,
    aktiv: adresse.aktiv,
    barkunde: adresse.barkunde,
    transfer: adresse.transfer,
    rechnungenSperren: adresse.rechnungenSperren,
    gutschriftenSperren: adresse.gutschriftenSperren,
    wareneingangSperren: adresse.wareneingangSperren,
    warenausgangSperren: adresse.warenausgangSperren,
    bemerkungen: adresse.bemerkungen ?? undefined,
    erstelltAm: adresse.erstelltAm,
    letzteAenderung: adresse.letzteAenderung,
  };
}
