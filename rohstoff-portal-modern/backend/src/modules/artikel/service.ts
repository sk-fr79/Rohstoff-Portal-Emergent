/**
 * Artikel-Service
 * Business-Logic für Artikelstamm
 */

import { prisma } from '../../config/database.js';
import { logger } from '../../utils/logger.js';
import { NotFoundError } from '../../middleware/errorHandler.js';
import type {
  CreateArtikelInput,
  UpdateArtikelInput,
  CreateArtikelBezInput,
  SearchArtikelParams,
  ArtikelResponse,
  ArtikelBezResponse,
} from './types.js';
import type { PaginatedResponse } from '../adresse/types.js';

/**
 * Artikel suchen mit Pagination
 */
export async function search(
  mandantId: string,
  params: SearchArtikelParams
): Promise<PaginatedResponse<ArtikelResponse>> {
  const { suche, artikelGruppeId, aktiv, gefahrgut, page, limit, sortBy, sortOrder } = params;
  
  const where: Record<string, unknown> = { mandantId };
  
  if (suche) {
    where.OR = [
      { artbez1: { contains: suche, mode: 'insensitive' } },
      { artbez2: { contains: suche, mode: 'insensitive' } },
      { anr1: { contains: suche, mode: 'insensitive' } },
    ];
  }
  
  if (artikelGruppeId) where.artikelGruppeId = artikelGruppeId;
  if (aktiv !== undefined) where.aktiv = aktiv;
  if (gefahrgut !== undefined) where.gefahrgut = gefahrgut;
  
  const total = await prisma.artikel.count({ where });
  
  const artikel = await prisma.artikel.findMany({
    where,
    include: {
      artikelBezeichnungen: {
        where: { aktiv: true },
        orderBy: { artbez1: 'asc' },
      },
    },
    orderBy: { [sortBy]: sortOrder },
    skip: (page - 1) * limit,
    take: limit,
  });
  
  return {
    data: artikel.map(mapArtikelToResponse),
    pagination: {
      page,
      limit,
      total,
      totalPages: Math.ceil(total / limit),
    },
  };
}

/**
 * Artikel nach ID abrufen
 */
export async function getById(
  mandantId: string,
  id: string
): Promise<ArtikelResponse> {
  const artikel = await prisma.artikel.findFirst({
    where: { id, mandantId },
    include: {
      artikelBezeichnungen: {
        orderBy: { artbez1: 'asc' },
      },
    },
  });
  
  if (!artikel) {
    throw new NotFoundError('Artikel');
  }
  
  return mapArtikelToResponse(artikel);
}

/**
 * Artikel erstellen
 */
export async function create(
  mandantId: string,
  input: CreateArtikelInput,
  erstelltVon?: string
): Promise<ArtikelResponse> {
  const artikel = await prisma.artikel.create({
    data: {
      mandantId,
      ...input,
      erstelltVon,
    },
    include: {
      artikelBezeichnungen: true,
    },
  });
  
  logger.info('Artikel erstellt', { artikelId: artikel.id, artbez1: artikel.artbez1 });
  
  return mapArtikelToResponse(artikel);
}

/**
 * Artikel aktualisieren
 */
export async function update(
  mandantId: string,
  id: string,
  input: UpdateArtikelInput,
  geaendertVon?: string
): Promise<ArtikelResponse> {
  const existing = await prisma.artikel.findFirst({
    where: { id, mandantId },
  });
  
  if (!existing) {
    throw new NotFoundError('Artikel');
  }
  
  const artikel = await prisma.artikel.update({
    where: { id },
    data: {
      ...input,
      geaendertVon,
    },
    include: {
      artikelBezeichnungen: true,
    },
  });
  
  logger.info('Artikel aktualisiert', { artikelId: id });
  
  return mapArtikelToResponse(artikel);
}

/**
 * Artikel löschen
 */
export async function remove(
  mandantId: string,
  id: string
): Promise<void> {
  const existing = await prisma.artikel.findFirst({
    where: { id, mandantId },
  });
  
  if (!existing) {
    throw new NotFoundError('Artikel');
  }
  
  // Prüfen ob in Verwendung
  const fuhrenCount = await prisma.fuhre.count({
    where: { artikelId: id },
  });
  
  if (fuhrenCount > 0) {
    // Soft-Delete
    await prisma.artikel.update({
      where: { id },
      data: { aktiv: false },
    });
    logger.info('Artikel deaktiviert', { artikelId: id });
    return;
  }
  
  await prisma.artikel.delete({ where: { id } });
  logger.info('Artikel gelöscht', { artikelId: id });
}

/**
 * Artikelbezeichnung (Sorte) erstellen
 */
export async function createArtikelBez(
  mandantId: string,
  input: CreateArtikelBezInput,
  erstelltVon?: string
): Promise<ArtikelBezResponse> {
  // Prüfen ob Artikel existiert
  const artikel = await prisma.artikel.findFirst({
    where: { id: input.artikelId, mandantId },
  });
  
  if (!artikel) {
    throw new NotFoundError('Artikel');
  }
  
  const bez = await prisma.artikelBezeichnung.create({
    data: {
      mandantId,
      ...input,
      erstelltVon,
    },
  });
  
  logger.info('Artikelbezeichnung erstellt', {
    artikelBezId: bez.id,
    artikelId: input.artikelId,
  });
  
  return mapArtikelBezToResponse(bez);
}

/**
 * Artikelbezeichnungen eines Artikels abrufen
 */
export async function getArtikelBezeichnungen(
  mandantId: string,
  artikelId: string
): Promise<ArtikelBezResponse[]> {
  const bezeichnungen = await prisma.artikelBezeichnung.findMany({
    where: { mandantId, artikelId },
    orderBy: { artbez1: 'asc' },
  });
  
  return bezeichnungen.map(mapArtikelBezToResponse);
}

// Helper
function mapArtikelToResponse(artikel: {
  id: string;
  mandantId: string;
  anr1: string | null;
  artbez1: string;
  artbez2: string | null;
  einheitId: string;
  einheitPreisId: string;
  mengendivisor: number;
  genauigkeitMengen: number;
  artikelGruppeId: string | null;
  eakCodeId: string | null;
  zolltarifnr: string | null;
  euCode: string | null;
  baselCode: string | null;
  aktiv: boolean;
  dienstleistung: boolean;
  istProdukt: boolean;
  gefahrgut: boolean;
  endOfWaste: boolean;
  bemerkungIntern: string | null;
  erstelltAm: Date;
  letzteAenderung: Date;
  artikelBezeichnungen?: Array<{
    id: string;
    artikelId: string;
    anr2: string | null;
    artbez1: string;
    artbez2: string | null;
    eakCodeId: string | null;
    aktiv: boolean;
  }>;
}): ArtikelResponse {
  return {
    id: artikel.id,
    mandantId: artikel.mandantId,
    anr1: artikel.anr1 ?? undefined,
    artbez1: artikel.artbez1,
    artbez2: artikel.artbez2 ?? undefined,
    einheitId: artikel.einheitId,
    einheitPreisId: artikel.einheitPreisId,
    mengendivisor: artikel.mengendivisor,
    genauigkeitMengen: artikel.genauigkeitMengen,
    artikelGruppeId: artikel.artikelGruppeId ?? undefined,
    eakCodeId: artikel.eakCodeId ?? undefined,
    zolltarifnr: artikel.zolltarifnr ?? undefined,
    euCode: artikel.euCode ?? undefined,
    baselCode: artikel.baselCode ?? undefined,
    aktiv: artikel.aktiv,
    dienstleistung: artikel.dienstleistung,
    istProdukt: artikel.istProdukt,
    gefahrgut: artikel.gefahrgut,
    endOfWaste: artikel.endOfWaste,
    bemerkungIntern: artikel.bemerkungIntern ?? undefined,
    erstelltAm: artikel.erstelltAm,
    letzteAenderung: artikel.letzteAenderung,
    artikelBezeichnungen: artikel.artikelBezeichnungen?.map(mapArtikelBezToResponse),
  };
}

function mapArtikelBezToResponse(bez: {
  id: string;
  artikelId: string;
  anr2: string | null;
  artbez1: string;
  artbez2: string | null;
  eakCodeId: string | null;
  aktiv: boolean;
}): ArtikelBezResponse {
  return {
    id: bez.id,
    artikelId: bez.artikelId,
    anr2: bez.anr2 ?? undefined,
    artbez1: bez.artbez1,
    artbez2: bez.artbez2 ?? undefined,
    eakCodeId: bez.eakCodeId ?? undefined,
    aktiv: bez.aktiv,
  };
}
