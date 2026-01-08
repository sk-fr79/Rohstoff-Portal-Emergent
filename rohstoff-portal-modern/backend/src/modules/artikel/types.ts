/**
 * Artikel-Modul Typdefinitionen
 */

import { z } from 'zod';

// Artikel erstellen Schema
export const createArtikelSchema = z.object({
  anr1: z.string().max(10).optional(),
  artbez1: z.string().min(1, 'Artikelbezeichnung erforderlich').max(80),
  artbez2: z.string().max(1000).optional(),
  
  // Einheiten (Pflicht)
  einheitId: z.string().uuid('Einheit erforderlich'),
  einheitPreisId: z.string().uuid('Preiseinheit erforderlich'),
  mengendivisor: z.number().min(1).default(1),
  genauigkeitMengen: z.number().min(0).max(6).default(3),
  
  // Klassifikation
  artikelGruppeId: z.string().uuid().optional(),
  eakCodeId: z.string().uuid().optional(),
  
  // Zoll
  zolltarifnr: z.string().max(50).optional(),
  zolltarifnotiz: z.string().max(500).optional(),
  
  // EU-Codes
  euNotiz: z.string().max(500).optional(),
  euCode: z.string().max(50).optional(),
  
  // Basel-Code
  baselCode: z.string().max(80).optional(),
  baselNotiz: z.string().max(500).optional(),
  
  // Anhang 7
  anhang7_3aCode: z.string().max(20).optional(),
  anhang7_3aText: z.string().max(1000).optional(),
  anhang7_3bCode: z.string().max(20).optional(),
  anhang7_3bText: z.string().max(1000).optional(),
  
  // Eigenschaften
  aktiv: z.boolean().default(true),
  dienstleistung: z.boolean().default(false),
  istProdukt: z.boolean().default(false),
  istLeergut: z.boolean().default(false),
  gefahrgut: z.boolean().default(false),
  endOfWaste: z.boolean().default(false),
  
  // Bemerkungen
  bemerkungIntern: z.string().max(1000).optional(),
});

export type CreateArtikelInput = z.infer<typeof createArtikelSchema>;

// Artikel aktualisieren Schema
export const updateArtikelSchema = createArtikelSchema.partial();
export type UpdateArtikelInput = z.infer<typeof updateArtikelSchema>;

// Artikelbezeichnung (Sorte) Schema
export const createArtikelBezSchema = z.object({
  artikelId: z.string().uuid('Artikel-ID erforderlich'),
  anr2: z.string().max(10).optional(),
  artbez1: z.string().min(1, 'Bezeichnung erforderlich').max(80),
  artbez2: z.string().max(1000).optional(),
  eakCodeId: z.string().uuid().optional(),
  aktiv: z.boolean().default(true),
});

export type CreateArtikelBezInput = z.infer<typeof createArtikelBezSchema>;

// Artikel Suchparameter
export const searchArtikelSchema = z.object({
  suche: z.string().optional(),
  artikelGruppeId: z.string().uuid().optional(),
  aktiv: z.boolean().optional(),
  gefahrgut: z.boolean().optional(),
  page: z.number().min(1).default(1),
  limit: z.number().min(1).max(100).default(20),
  sortBy: z.enum(['artbez1', 'anr1', 'erstelltAm']).default('artbez1'),
  sortOrder: z.enum(['asc', 'desc']).default('asc'),
});

export type SearchArtikelParams = z.infer<typeof searchArtikelSchema>;

// Artikel Response
export interface ArtikelResponse {
  id: string;
  mandantId: string;
  anr1?: string;
  artbez1: string;
  artbez2?: string;
  einheitId: string;
  einheitPreisId: string;
  mengendivisor: number;
  genauigkeitMengen: number;
  artikelGruppeId?: string;
  eakCodeId?: string;
  zolltarifnr?: string;
  euCode?: string;
  baselCode?: string;
  aktiv: boolean;
  dienstleistung: boolean;
  istProdukt: boolean;
  gefahrgut: boolean;
  endOfWaste: boolean;
  bemerkungIntern?: string;
  erstelltAm: Date;
  letzteAenderung: Date;
  // Nested
  artikelBezeichnungen?: ArtikelBezResponse[];
}

export interface ArtikelBezResponse {
  id: string;
  artikelId: string;
  anr2?: string;
  artbez1: string;
  artbez2?: string;
  eakCodeId?: string;
  aktiv: boolean;
}
