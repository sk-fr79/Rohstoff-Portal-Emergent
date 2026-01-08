/**
 * Kontrakt-Modul Typdefinitionen
 * Enthält EK- und VK-Kontrakte
 */

import { z } from 'zod';

// Kontrakt erstellen Schema
export const createKontraktSchema = z.object({
  // Adresse
  adresseId: z.string().uuid('Adresse erforderlich'),
  
  // Gültigkeitszeitraum
  gueltigVon: z.string().datetime().optional(),
  gueltigBis: z.string().datetime().optional(),
  
  // Konditionen
  zahlungsbedingungenId: z.string().uuid().optional(),
  waehrungFremdId: z.string().uuid().optional(),
  lieferbedingungen: z.string().max(200).optional(),
  skontoProzent: z.number().min(0).max(100).optional(),
  
  // Typ
  istEinkauf: z.boolean().default(true), // true = EK, false = VK
  istFixierung: z.boolean().default(false),
  
  // Bemerkungen
  bemerkungenIntern: z.string().max(500).optional(),
  bemerkungFix1: z.string().max(2000).optional(),
  formulartextAnfang: z.string().max(2000).optional(),
  formulartextEnde: z.string().max(2000).optional(),
});

export type CreateKontraktInput = z.infer<typeof createKontraktSchema>;

// Kontrakt aktualisieren
export const updateKontraktSchema = createKontraktSchema.partial();
export type UpdateKontraktInput = z.infer<typeof updateKontraktSchema>;

// Kontraktposition erstellen
export const createKontraktPosSchema = z.object({
  // Artikel
  artikelId: z.string().uuid().optional(),
  artikelBezId: z.string().uuid().optional(),
  artbez1: z.string().max(80).optional(),
  artbez2: z.string().max(1000).optional(),
  
  // Mengen und Preise
  menge: z.number().optional(),
  einzelpreis: z.number().optional(),
  steuersatz: z.number().min(0).max(100).optional(),
  
  // Einheiten
  einheitKurz: z.string().max(10).optional(),
  einheitPreisKurz: z.string().max(10).optional(),
  mengendivisor: z.number().min(1).default(1),
  
  // Gültigkeit
  gueltigVon: z.string().datetime().optional(),
  gueltigBis: z.string().datetime().optional(),
  
  // Zusatz
  bemerkung: z.string().max(500).optional(),
  lieferbedingungen: z.string().max(200).optional(),
});

export type CreateKontraktPosInput = z.infer<typeof createKontraktPosSchema>;

// Kontrakt Suchparameter
export const searchKontraktSchema = z.object({
  suche: z.string().optional(),
  adresseId: z.string().uuid().optional(),
  istEinkauf: z.boolean().optional(),
  abgeschlossen: z.boolean().optional(),
  gueltigAm: z.string().datetime().optional(), // Kontrakte gültig an diesem Datum
  page: z.number().min(1).default(1),
  limit: z.number().min(1).max(100).default(20),
  sortBy: z.enum(['buchungsnummer', 'erstelltAm', 'gueltigVon']).default('buchungsnummer'),
  sortOrder: z.enum(['asc', 'desc']).default('desc'),
});

export type SearchKontraktParams = z.infer<typeof searchKontraktSchema>;

// Response Typen
export interface KontraktResponse {
  id: string;
  mandantId: string;
  buchungsnummer?: string;
  adresseId?: string;
  kdnr?: string;
  name1?: string;
  name2?: string;
  name3?: string;
  strasse?: string;
  plz?: string;
  ort?: string;
  gueltigVon?: Date;
  gueltigBis?: Date;
  istEinkauf: boolean;
  istFixierung: boolean;
  abgeschlossen: boolean;
  deleted: boolean;
  skontoProzent?: number;
  bemerkungenIntern?: string;
  erstelltAm: Date;
  letzteAenderung: Date;
  positionen?: KontraktPosResponse[];
}

export interface KontraktPosResponse {
  id: string;
  kontraktId: string;
  positionsnummer: number;
  artikelId?: string;
  artikelBezId?: string;
  artbez1?: string;
  artbez2?: string;
  menge?: number;
  einzelpreis?: number;
  steuersatz?: number;
  einheitKurz?: string;
  einheitPreisKurz?: string;
  gueltigVon?: Date;
  gueltigBis?: Date;
  deleted: boolean;
}
