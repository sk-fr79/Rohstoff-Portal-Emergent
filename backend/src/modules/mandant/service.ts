/**
 * Mandant-Service
 * Business-Logic für Mandantenverwaltung
 */

import { prisma } from '../../config/database.js';
import { logger } from '../../utils/logger.js';
import { NotFoundError, ConflictError } from '../../middleware/errorHandler.js';
import type { CreateMandantInput, UpdateMandantInput, MandantResponse } from './types.js';

/**
 * Alle Mandanten abrufen
 */
export async function getAll(): Promise<MandantResponse[]> {
  const mandanten = await prisma.mandant.findMany({
    orderBy: { name1: 'asc' },
  });
  
  return mandanten.map(mapToResponse);
}

/**
 * Mandant nach ID abrufen
 */
export async function getById(id: string): Promise<MandantResponse> {
  const mandant = await prisma.mandant.findUnique({
    where: { id },
  });
  
  if (!mandant) {
    throw new NotFoundError('Mandant');
  }
  
  return mapToResponse(mandant);
}

/**
 * Mandant erstellen
 */
export async function create(
  input: CreateMandantInput,
  erstelltVon?: string
): Promise<MandantResponse> {
  const mandant = await prisma.mandant.create({
    data: {
      ...input,
      erstelltVon,
    },
  });
  
  logger.info('Mandant erstellt', { mandantId: mandant.id, name: mandant.name1 });
  
  return mapToResponse(mandant);
}

/**
 * Mandant aktualisieren
 */
export async function update(
  id: string,
  input: UpdateMandantInput,
  geaendertVon?: string
): Promise<MandantResponse> {
  // Prüfen ob Mandant existiert
  const existing = await prisma.mandant.findUnique({ where: { id } });
  if (!existing) {
    throw new NotFoundError('Mandant');
  }
  
  const mandant = await prisma.mandant.update({
    where: { id },
    data: {
      ...input,
      geaendertVon,
    },
  });
  
  logger.info('Mandant aktualisiert', { mandantId: id });
  
  return mapToResponse(mandant);
}

/**
 * Mandant löschen
 */
export async function remove(id: string): Promise<void> {
  // Prüfen ob Mandant existiert
  const existing = await prisma.mandant.findUnique({ where: { id } });
  if (!existing) {
    throw new NotFoundError('Mandant');
  }
  
  // Prüfen ob noch Benutzer/Daten vorhanden
  const benutzerCount = await prisma.benutzer.count({ where: { mandantId: id } });
  if (benutzerCount > 0) {
    throw new ConflictError('Mandant kann nicht gelöscht werden - noch Benutzer vorhanden');
  }
  
  await prisma.mandant.delete({ where: { id } });
  
  logger.info('Mandant gelöscht', { mandantId: id });
}

// Helper: Mandant zu Response mappen
function mapToResponse(mandant: {
  id: string;
  name1: string;
  name2: string | null;
  name3: string | null;
  strasse: string | null;
  hausnummer: string | null;
  plz: string;
  ort: string;
  kurzname: string | null;
  info: string | null;
  erstelltAm: Date;
  letzteAenderung: Date;
}): MandantResponse {
  return {
    id: mandant.id,
    name1: mandant.name1,
    name2: mandant.name2 ?? undefined,
    name3: mandant.name3 ?? undefined,
    strasse: mandant.strasse ?? undefined,
    hausnummer: mandant.hausnummer ?? undefined,
    plz: mandant.plz,
    ort: mandant.ort,
    kurzname: mandant.kurzname ?? undefined,
    info: mandant.info ?? undefined,
    erstelltAm: mandant.erstelltAm,
    letzteAenderung: mandant.letzteAenderung,
  };
}
