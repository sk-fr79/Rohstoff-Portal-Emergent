/**
 * Kontrakt-Service
 * Business-Logic für EK/VK-Kontrakte
 * 
 * Kritische Business-Logic aus Java:
 * - Buchungsnummer-Generierung (EKK/VKK-JJMM-NNNNN)
 * - Adress-Snapshot bei Erstellung
 * - Positionspreise für Fuhren
 */

import { prisma } from '../../config/database.js';
import { logger } from '../../utils/logger.js';
import { NotFoundError, ValidationError, ConflictError } from '../../middleware/errorHandler.js';
import type {
  CreateKontraktInput,
  UpdateKontraktInput,
  CreateKontraktPosInput,
  SearchKontraktParams,
  KontraktResponse,
  KontraktPosResponse,
} from './types.js';
import type { PaginatedResponse } from '../adresse/types.js';

/**
 * Buchungsnummer generieren
 * Format: PREFIX-JJMM-NNNNN (z.B. EKK-2408-00001)
 */
async function generateBuchungsnummer(
  mandantId: string,
  istEinkauf: boolean
): Promise<string> {
  const mandant = await prisma.mandant.findUnique({
    where: { id: mandantId },
  });
  
  if (!mandant) {
    throw new ValidationError('Mandant nicht gefunden');
  }
  
  const prefix = istEinkauf 
    ? (mandant.buchungsprefixEkk || 'EKK') 
    : (mandant.buchungsprefixVkk || 'VKK');
  
  const now = new Date();
  const jahr = now.getFullYear().toString().slice(-2);
  const monat = (now.getMonth() + 1).toString().padStart(2, '0');
  const jahrMonat = `${jahr}${monat}`;
  
  // Höchste Nummer im aktuellen Monat finden
  const pattern = `${prefix}-${jahrMonat}-%`;
  const letzte = await prisma.kontrakt.findFirst({
    where: {
      mandantId,
      buchungsnummer: { startsWith: `${prefix}-${jahrMonat}-` },
    },
    orderBy: { buchungsnummer: 'desc' },
    select: { buchungsnummer: true },
  });
  
  let laufnummer = 1;
  if (letzte?.buchungsnummer) {
    const teile = letzte.buchungsnummer.split('-');
    if (teile.length === 3) {
      const nr = parseInt(teile[2], 10);
      if (!isNaN(nr)) {
        laufnummer = nr + 1;
      }
    }
  }
  
  return `${prefix}-${jahrMonat}-${laufnummer.toString().padStart(5, '0')}`;
}

/**
 * Kontrakte suchen
 */
export async function search(
  mandantId: string,
  params: SearchKontraktParams
): Promise<PaginatedResponse<KontraktResponse>> {
  const { suche, adresseId, istEinkauf, abgeschlossen, gueltigAm, page, limit, sortBy, sortOrder } = params;
  
  const where: Record<string, unknown> = { 
    mandantId,
    deleted: false,
  };
  
  if (suche) {
    where.OR = [
      { buchungsnummer: { contains: suche, mode: 'insensitive' } },
      { name1: { contains: suche, mode: 'insensitive' } },
      { kdnr: { contains: suche, mode: 'insensitive' } },
    ];
  }
  
  if (adresseId) where.adresseId = adresseId;
  if (istEinkauf !== undefined) where.istEinkauf = istEinkauf;
  if (abgeschlossen !== undefined) where.abgeschlossen = abgeschlossen;
  
  // Gültig am bestimmten Datum
  if (gueltigAm) {
    const datum = new Date(gueltigAm);
    where.AND = [
      { OR: [{ gueltigVon: null }, { gueltigVon: { lte: datum } }] },
      { OR: [{ gueltigBis: null }, { gueltigBis: { gte: datum } }] },
    ];
  }
  
  const total = await prisma.kontrakt.count({ where });
  
  const kontrakte = await prisma.kontrakt.findMany({
    where,
    include: {
      positionen: {
        where: { deleted: false },
        orderBy: { positionsnummer: 'asc' },
      },
    },
    orderBy: { [sortBy]: sortOrder },
    skip: (page - 1) * limit,
    take: limit,
  });
  
  return {
    data: kontrakte.map(mapKontraktToResponse),
    pagination: {
      page,
      limit,
      total,
      totalPages: Math.ceil(total / limit),
    },
  };
}

/**
 * Kontrakt nach ID
 */
export async function getById(
  mandantId: string,
  id: string
): Promise<KontraktResponse> {
  const kontrakt = await prisma.kontrakt.findFirst({
    where: { id, mandantId },
    include: {
      positionen: {
        orderBy: { positionsnummer: 'asc' },
      },
    },
  });
  
  if (!kontrakt) {
    throw new NotFoundError('Kontrakt');
  }
  
  return mapKontraktToResponse(kontrakt);
}

/**
 * Kontrakt erstellen
 * Wichtig: Adressdaten werden als Snapshot kopiert
 */
export async function create(
  mandantId: string,
  input: CreateKontraktInput,
  erstelltVon?: string
): Promise<KontraktResponse> {
  // Adresse laden für Snapshot
  const adresse = await prisma.adresse.findFirst({
    where: { id: input.adresseId, mandantId },
  });
  
  if (!adresse) {
    throw new NotFoundError('Adresse');
  }
  
  // Buchungsnummer generieren
  const buchungsnummer = await generateBuchungsnummer(mandantId, input.istEinkauf);
  
  const kontrakt = await prisma.kontrakt.create({
    data: {
      mandantId,
      buchungsnummer,
      adresseId: input.adresseId,
      // Adress-Snapshot (Denormalisierung für historische Korrektheit)
      kdnr: adresse.kdnr,
      name1: adresse.name1,
      name2: adresse.name2,
      name3: adresse.name3,
      strasse: adresse.strasse,
      hausnummer: adresse.hausnummer,
      plz: adresse.plz,
      ort: adresse.ort,
      ortzusatz: adresse.ortzusatz,
      // Eingabedaten
      gueltigVon: input.gueltigVon ? new Date(input.gueltigVon) : undefined,
      gueltigBis: input.gueltigBis ? new Date(input.gueltigBis) : undefined,
      zahlungsbedingungenId: input.zahlungsbedingungenId,
      waehrungFremdId: input.waehrungFremdId,
      lieferbedingungen: input.lieferbedingungen,
      skontoProzent: input.skontoProzent,
      istEinkauf: input.istEinkauf,
      istFixierung: input.istFixierung,
      bemerkungenIntern: input.bemerkungenIntern,
      bemerkungFix1: input.bemerkungFix1,
      formulartextAnfang: input.formulartextAnfang,
      formulartextEnde: input.formulartextEnde,
      erstelltVon,
    },
    include: {
      positionen: true,
    },
  });
  
  logger.info('Kontrakt erstellt', {
    kontraktId: kontrakt.id,
    buchungsnummer,
    istEinkauf: input.istEinkauf,
  });
  
  return mapKontraktToResponse(kontrakt);
}

/**
 * Kontrakt aktualisieren
 */
export async function update(
  mandantId: string,
  id: string,
  input: UpdateKontraktInput,
  geaendertVon?: string
): Promise<KontraktResponse> {
  const existing = await prisma.kontrakt.findFirst({
    where: { id, mandantId },
  });
  
  if (!existing) {
    throw new NotFoundError('Kontrakt');
  }
  
  if (existing.abgeschlossen) {
    throw new ConflictError('Abgeschlossener Kontrakt kann nicht bearbeitet werden');
  }
  
  const kontrakt = await prisma.kontrakt.update({
    where: { id },
    data: {
      gueltigVon: input.gueltigVon ? new Date(input.gueltigVon) : undefined,
      gueltigBis: input.gueltigBis ? new Date(input.gueltigBis) : undefined,
      zahlungsbedingungenId: input.zahlungsbedingungenId,
      waehrungFremdId: input.waehrungFremdId,
      lieferbedingungen: input.lieferbedingungen,
      skontoProzent: input.skontoProzent,
      bemerkungenIntern: input.bemerkungenIntern,
      bemerkungFix1: input.bemerkungFix1,
      formulartextAnfang: input.formulartextAnfang,
      formulartextEnde: input.formulartextEnde,
      geaendertVon,
    },
    include: {
      positionen: true,
    },
  });
  
  logger.info('Kontrakt aktualisiert', { kontraktId: id });
  
  return mapKontraktToResponse(kontrakt);
}

/**
 * Kontrakt abschließen
 */
export async function abschliessen(
  mandantId: string,
  id: string,
  geaendertVon?: string
): Promise<KontraktResponse> {
  const existing = await prisma.kontrakt.findFirst({
    where: { id, mandantId },
  });
  
  if (!existing) {
    throw new NotFoundError('Kontrakt');
  }
  
  const kontrakt = await prisma.kontrakt.update({
    where: { id },
    data: {
      abgeschlossen: true,
      geaendertVon,
    },
    include: {
      positionen: true,
    },
  });
  
  logger.info('Kontrakt abgeschlossen', { kontraktId: id });
  
  return mapKontraktToResponse(kontrakt);
}

/**
 * Kontrakt löschen (Soft-Delete)
 */
export async function remove(
  mandantId: string,
  id: string,
  grund: string,
  geloeschtVon?: string
): Promise<void> {
  const existing = await prisma.kontrakt.findFirst({
    where: { id, mandantId },
  });
  
  if (!existing) {
    throw new NotFoundError('Kontrakt');
  }
  
  await prisma.kontrakt.update({
    where: { id },
    data: {
      deleted: true,
      delGrund: grund,
      delDate: new Date(),
      delKuerzel: geloeschtVon,
    },
  });
  
  logger.info('Kontrakt gelöscht', { kontraktId: id, grund });
}

/**
 * Position hinzufügen
 */
export async function addPosition(
  mandantId: string,
  kontraktId: string,
  input: CreateKontraktPosInput,
  erstelltVon?: string
): Promise<KontraktPosResponse> {
  const kontrakt = await prisma.kontrakt.findFirst({
    where: { id: kontraktId, mandantId },
  });
  
  if (!kontrakt) {
    throw new NotFoundError('Kontrakt');
  }
  
  if (kontrakt.abgeschlossen) {
    throw new ConflictError('Position kann nicht zu abgeschlossenem Kontrakt hinzugefügt werden');
  }
  
  // Nächste Positionsnummer ermitteln
  const lastPos = await prisma.kontraktPosition.findFirst({
    where: { kontraktId },
    orderBy: { positionsnummer: 'desc' },
    select: { positionsnummer: true },
  });
  
  const positionsnummer = (lastPos?.positionsnummer || 0) + 1;
  
  // Artikelbezeichnung übernehmen wenn Artikel angegeben
  let artbez1 = input.artbez1;
  let artbez2 = input.artbez2;
  
  if (input.artikelId && !artbez1) {
    const artikel = await prisma.artikel.findUnique({
      where: { id: input.artikelId },
      select: { artbez1: true, artbez2: true },
    });
    if (artikel) {
      artbez1 = artikel.artbez1;
      artbez2 = artikel.artbez2 ?? undefined;
    }
  }
  
  if (input.artikelBezId && !artbez1) {
    const bez = await prisma.artikelBezeichnung.findUnique({
      where: { id: input.artikelBezId },
      select: { artbez1: true, artbez2: true },
    });
    if (bez) {
      artbez1 = bez.artbez1;
      artbez2 = bez.artbez2 ?? undefined;
    }
  }
  
  const position = await prisma.kontraktPosition.create({
    data: {
      mandantId,
      kontraktId,
      positionsnummer,
      artikelId: input.artikelId,
      artikelBezId: input.artikelBezId,
      artbez1,
      artbez2,
      menge: input.menge,
      einzelpreis: input.einzelpreis,
      steuersatz: input.steuersatz,
      einheitKurz: input.einheitKurz,
      einheitPreisKurz: input.einheitPreisKurz,
      mengendivisor: input.mengendivisor,
      gueltigVon: input.gueltigVon ? new Date(input.gueltigVon) : undefined,
      gueltigBis: input.gueltigBis ? new Date(input.gueltigBis) : undefined,
      bemerkung: input.bemerkung,
      lieferbedingungen: input.lieferbedingungen,
      erstelltVon,
    },
  });
  
  logger.info('Kontraktposition erstellt', {
    kontraktId,
    positionId: position.id,
    positionsnummer,
  });
  
  return mapPositionToResponse(position);
}

// Helper Funktionen
function mapKontraktToResponse(kontrakt: {
  id: string;
  mandantId: string;
  buchungsnummer: string | null;
  adresseId: string | null;
  kdnr: string | null;
  name1: string | null;
  name2: string | null;
  name3: string | null;
  strasse: string | null;
  plz: string | null;
  ort: string | null;
  gueltigVon: Date | null;
  gueltigBis: Date | null;
  istEinkauf: boolean;
  istFixierung: boolean;
  abgeschlossen: boolean;
  deleted: boolean;
  skontoProzent: unknown;
  bemerkungenIntern: string | null;
  erstelltAm: Date;
  letzteAenderung: Date;
  positionen?: Array<{
    id: string;
    kontraktId: string;
    positionsnummer: number;
    artikelId: string | null;
    artikelBezId: string | null;
    artbez1: string | null;
    artbez2: string | null;
    menge: unknown;
    einzelpreis: unknown;
    steuersatz: unknown;
    einheitKurz: string | null;
    einheitPreisKurz: string | null;
    gueltigVon: Date | null;
    gueltigBis: Date | null;
    deleted: boolean;
  }>;
}): KontraktResponse {
  return {
    id: kontrakt.id,
    mandantId: kontrakt.mandantId,
    buchungsnummer: kontrakt.buchungsnummer ?? undefined,
    adresseId: kontrakt.adresseId ?? undefined,
    kdnr: kontrakt.kdnr ?? undefined,
    name1: kontrakt.name1 ?? undefined,
    name2: kontrakt.name2 ?? undefined,
    name3: kontrakt.name3 ?? undefined,
    strasse: kontrakt.strasse ?? undefined,
    plz: kontrakt.plz ?? undefined,
    ort: kontrakt.ort ?? undefined,
    gueltigVon: kontrakt.gueltigVon ?? undefined,
    gueltigBis: kontrakt.gueltigBis ?? undefined,
    istEinkauf: kontrakt.istEinkauf,
    istFixierung: kontrakt.istFixierung,
    abgeschlossen: kontrakt.abgeschlossen,
    deleted: kontrakt.deleted,
    skontoProzent: kontrakt.skontoProzent ? Number(kontrakt.skontoProzent) : undefined,
    bemerkungenIntern: kontrakt.bemerkungenIntern ?? undefined,
    erstelltAm: kontrakt.erstelltAm,
    letzteAenderung: kontrakt.letzteAenderung,
    positionen: kontrakt.positionen?.map(mapPositionToResponse),
  };
}

function mapPositionToResponse(pos: {
  id: string;
  kontraktId: string;
  positionsnummer: number;
  artikelId: string | null;
  artikelBezId: string | null;
  artbez1: string | null;
  artbez2: string | null;
  menge: unknown;
  einzelpreis: unknown;
  steuersatz: unknown;
  einheitKurz: string | null;
  einheitPreisKurz: string | null;
  gueltigVon: Date | null;
  gueltigBis: Date | null;
  deleted: boolean;
}): KontraktPosResponse {
  return {
    id: pos.id,
    kontraktId: pos.kontraktId,
    positionsnummer: pos.positionsnummer,
    artikelId: pos.artikelId ?? undefined,
    artikelBezId: pos.artikelBezId ?? undefined,
    artbez1: pos.artbez1 ?? undefined,
    artbez2: pos.artbez2 ?? undefined,
    menge: pos.menge ? Number(pos.menge) : undefined,
    einzelpreis: pos.einzelpreis ? Number(pos.einzelpreis) : undefined,
    steuersatz: pos.steuersatz ? Number(pos.steuersatz) : undefined,
    einheitKurz: pos.einheitKurz ?? undefined,
    einheitPreisKurz: pos.einheitPreisKurz ?? undefined,
    gueltigVon: pos.gueltigVon ?? undefined,
    gueltigBis: pos.gueltigBis ?? undefined,
    deleted: pos.deleted,
  };
}
