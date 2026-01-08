/**
 * Mandant-Controller
 * HTTP-Handler für Mandantenverwaltung
 */

import { Request, Response, NextFunction } from 'express';
import * as mandantService from './service.js';
import { createMandantSchema, updateMandantSchema } from './types.js';
import { AuthRequest } from '../../middleware/auth.js';

/**
 * GET /api/mandanten
 * Alle Mandanten abrufen
 */
export async function getAll(
  req: Request,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const mandanten = await mandantService.getAll();
    res.json({ success: true, data: mandanten });
  } catch (error) {
    next(error);
  }
}

/**
 * GET /api/mandanten/:id
 * Mandant nach ID abrufen
 */
export async function getById(
  req: Request,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const mandant = await mandantService.getById(req.params.id);
    res.json({ success: true, data: mandant });
  } catch (error) {
    next(error);
  }
}

/**
 * POST /api/mandanten
 * Mandant erstellen
 */
export async function create(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const input = createMandantSchema.parse(req.body);
    const mandant = await mandantService.create(input, req.user?.kuerzel);
    res.status(201).json({ success: true, data: mandant });
  } catch (error) {
    next(error);
  }
}

/**
 * PUT /api/mandanten/:id
 * Mandant aktualisieren
 */
export async function update(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const input = updateMandantSchema.parse(req.body);
    const mandant = await mandantService.update(
      req.params.id,
      input,
      req.user?.kuerzel
    );
    res.json({ success: true, data: mandant });
  } catch (error) {
    next(error);
  }
}

/**
 * DELETE /api/mandanten/:id
 * Mandant löschen
 */
export async function remove(
  req: Request,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    await mandantService.remove(req.params.id);
    res.json({ success: true, message: 'Mandant gelöscht' });
  } catch (error) {
    next(error);
  }
}
