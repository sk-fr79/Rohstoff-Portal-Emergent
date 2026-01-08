/**
 * Adresse-Controller
 * HTTP-Handler für Adressenverwaltung
 */

import { Response, NextFunction } from 'express';
import * as adresseService from './service.js';
import { createAdresseSchema, updateAdresseSchema, searchAdresseSchema } from './types.js';
import { AuthRequest } from '../../middleware/auth.js';

/**
 * GET /api/adressen
 * Adressen suchen mit Pagination
 */
export async function search(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const params = searchAdresseSchema.parse({
      ...req.query,
      page: req.query.page ? parseInt(req.query.page as string, 10) : 1,
      limit: req.query.limit ? parseInt(req.query.limit as string, 10) : 20,
      adresstyp: req.query.adresstyp ? parseInt(req.query.adresstyp as string, 10) : undefined,
      aktiv: req.query.aktiv === 'true' ? true : req.query.aktiv === 'false' ? false : undefined,
    });
    
    const result = await adresseService.search(req.user!.mandantId, params);
    res.json({ success: true, ...result });
  } catch (error) {
    next(error);
  }
}

/**
 * GET /api/adressen/:id
 * Adresse nach ID abrufen
 */
export async function getById(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const adresse = await adresseService.getById(req.user!.mandantId, req.params.id);
    res.json({ success: true, data: adresse });
  } catch (error) {
    next(error);
  }
}

/**
 * POST /api/adressen
 * Adresse erstellen
 */
export async function create(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const input = createAdresseSchema.parse(req.body);
    const adresse = await adresseService.create(
      req.user!.mandantId,
      input,
      req.user?.kuerzel
    );
    res.status(201).json({ success: true, data: adresse });
  } catch (error) {
    next(error);
  }
}

/**
 * PUT /api/adressen/:id
 * Adresse aktualisieren
 */
export async function update(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const input = updateAdresseSchema.parse(req.body);
    const adresse = await adresseService.update(
      req.user!.mandantId,
      req.params.id,
      input,
      req.user?.kuerzel
    );
    res.json({ success: true, data: adresse });
  } catch (error) {
    next(error);
  }
}

/**
 * DELETE /api/adressen/:id
 * Adresse löschen
 */
export async function remove(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    await adresseService.remove(req.user!.mandantId, req.params.id);
    res.json({ success: true, message: 'Adresse gelöscht' });
  } catch (error) {
    next(error);
  }
}
