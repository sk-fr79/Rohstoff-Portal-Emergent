/**
 * Adresse-Modul Typdefinitionen
 * Basierend auf der Business-Logic aus dem Java-Code
 */

import { z } from 'zod';

// Adresstypen
export enum AdressTyp {
  KUNDE = 1,
  LIEFERANT = 2,
  SPEDITION = 3,
  INTERESSENT = 4,
  SONSTIGE = 5,
}

// Adresse erstellen Schema
export const createAdresseSchema = z.object({
  kdnr: z.string().max(20).optional(),
  vorname: z.string().max(30).optional(),
  name1: z.string().min(1, 'Name erforderlich').max(40),
  name2: z.string().max(40).optional(),
  name3: z.string().max(40).optional(),
  strasse: z.string().max(45).optional(),
  hausnummer: z.string().max(10).optional(),
  plz: z.string().max(10).optional(),
  ort: z.string().max(30).optional(),
  ortzusatz: z.string().max(30).optional(),
  landId: z.string().uuid().optional(),
  spracheId: z.string().uuid().optional(),
  waehrungId: z.string().uuid().optional(),
  
  // Kontaktdaten
  telefon: z.string().max(30).optional(),
  telefax: z.string().max(30).optional(),
  email: z.string().email().max(100).optional().or(z.literal('')),
  webseite: z.string().max(100).optional(),
  
  // Adresstyp und Status
  adresstyp: z.number().min(1).max(5).optional(),
  aktiv: z.boolean().default(true),
  barkunde: z.boolean().default(false),
  transfer: z.boolean().default(false),
  
  // Nummern
  liefNr: z.string().max(60).optional(),
  abnNr: z.string().max(60).optional(),
  
  // Sperren
  rechnungenSperren: z.boolean().default(false),
  gutschriftenSperren: z.boolean().default(false),
  wareneingangSperren: z.boolean().default(false),
  warenausgangSperren: z.boolean().default(false),
  
  // Konditionen
  zahlungsbedingungenId: z.string().uuid().optional(),
  zahlungsbedingungenVkId: z.string().uuid().optional(),
  lieferbedingungenId: z.string().uuid().optional(),
  lieferbedingungenVkId: z.string().uuid().optional(),
  
  // Postfach
  plzPob: z.string().max(10).optional(),
  pob: z.string().max(20).optional(),
  isPobActive: z.boolean().default(false),
  
  // Bemerkungen
  bemerkungen: z.string().max(700).optional(),
  bemerkungFahrplan: z.string().max(300).optional(),
  lieferinfoTpa: z.string().max(300).optional(),
  
  // Anhang 7
  ah7QuelleSicher: z.boolean().default(false),
  ah7ZielSicher: z.boolean().default(false),
});

export type CreateAdresseInput = z.infer<typeof createAdresseSchema>;

// Adresse aktualisieren Schema
export const updateAdresseSchema = createAdresseSchema.partial();
export type UpdateAdresseInput = z.infer<typeof updateAdresseSchema>;

// Adresse Suchparameter
export const searchAdresseSchema = z.object({
  suche: z.string().optional(),
  adresstyp: z.number().optional(),
  aktiv: z.boolean().optional(),
  plz: z.string().optional(),
  ort: z.string().optional(),
  page: z.number().min(1).default(1),
  limit: z.number().min(1).max(100).default(20),
  sortBy: z.enum(['name1', 'kdnr', 'ort', 'plz', 'erstelltAm']).default('name1'),
  sortOrder: z.enum(['asc', 'desc']).default('asc'),
});

export type SearchAdresseParams = z.infer<typeof searchAdresseSchema>;

// Adresse Response
export interface AdresseResponse {
  id: string;
  mandantId: string;
  kdnr?: string;
  vorname?: string;
  name1: string;
  name2?: string;
  name3?: string;
  strasse?: string;
  hausnummer?: string;
  plz?: string;
  ort?: string;
  ortzusatz?: string;
  telefon?: string;
  telefax?: string;
  email?: string;
  webseite?: string;
  adresstyp?: number;
  aktiv: boolean;
  barkunde: boolean;
  transfer: boolean;
  rechnungenSperren: boolean;
  gutschriftenSperren: boolean;
  wareneingangSperren: boolean;
  warenausgangSperren: boolean;
  bemerkungen?: string;
  erstelltAm: Date;
  letzteAenderung: Date;
}

// Paginated Response
export interface PaginatedResponse<T> {
  data: T[];
  pagination: {
    page: number;
    limit: number;
    total: number;
    totalPages: number;
  };
}
