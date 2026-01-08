/**
 * Mandant-Modul Typdefinitionen
 */

import { z } from 'zod';

// Mandant erstellen Schema
export const createMandantSchema = z.object({
  name1: z.string().min(1, 'Name erforderlich').max(30),
  name2: z.string().max(30).optional(),
  name3: z.string().max(30).optional(),
  strasse: z.string().max(30).optional(),
  hausnummer: z.string().max(10).optional(),
  plz: z.string().min(1, 'PLZ erforderlich').max(10),
  ort: z.string().min(1, 'Ort erforderlich').max(30),
  kurzname: z.string().max(20).optional(),
  info: z.string().max(1000).optional(),
});

export type CreateMandantInput = z.infer<typeof createMandantSchema>;

// Mandant aktualisieren Schema
export const updateMandantSchema = createMandantSchema.partial();
export type UpdateMandantInput = z.infer<typeof updateMandantSchema>;

// Mandant Response
export interface MandantResponse {
  id: string;
  name1: string;
  name2?: string;
  name3?: string;
  strasse?: string;
  hausnummer?: string;
  plz: string;
  ort: string;
  kurzname?: string;
  info?: string;
  erstelltAm: Date;
  letzteAenderung: Date;
}
