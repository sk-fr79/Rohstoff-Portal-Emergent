/**
 * Artikel-Controller
 * HTTP-Handler für Artikelstamm
 */

import { Response, NextFunction } from 'express';
import * as artikelService from './service.js';
import { createArtikelSchema, updateArtikelSchema, searchArtikelSchema, createArtikelBezSchema } from './types.js';
import { AuthRequest } from '../../middleware/auth.js';

export async function search(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const params = searchArtikelSchema.parse({
      ...req.query,
      page: req.query.page ? parseInt(req.query.page as string, 10) : 1,
      limit: req.query.limit ? parseInt(req.query.limit as string, 10) : 20,
      aktiv: req.query.aktiv === 'true' ? true : req.query.aktiv === 'false' ? false : undefined,
      gefahrgut: req.query.gefahrgut === 'true' ? true : req.query.gefahrgut === 'false' ? false : undefined,
    });
    
    const result = await artikelService.search(req.user!.mandantId, params);
    res.json({ success: true, ...result });
  } catch (error) {
    next(error);
  }
}

export async function getById(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const artikel = await artikelService.getById(req.user!.mandantId, req.params.id);
    res.json({ success: true, data: artikel });
  } catch (error) {
    next(error);
  }
}

export async function create(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const input = createArtikelSchema.parse(req.body);
    const artikel = await artikelService.create(req.user!.mandantId, input, req.user?.kuerzel);
    res.status(201).json({ success: true, data: artikel });
  } catch (error) {
    next(error);
  }
}

export async function update(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const input = updateArtikelSchema.parse(req.body);
    const artikel = await artikelService.update(
      req.user!.mandantId,
      req.params.id,
      input,
      req.user?.kuerzel
    );
    res.json({ success: true, data: artikel });
  } catch (error) {
    next(error);
  }
}

export async function remove(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    await artikelService.remove(req.user!.mandantId, req.params.id);
    res.json({ success: true, message: 'Artikel gelöscht' });
  } catch (error) {
    next(error);
  }
}

// Artikelbezeichnungen (Sorten)
export async function getArtikelBezeichnungen(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const bezeichnungen = await artikelService.getArtikelBezeichnungen(
      req.user!.mandantId,
      req.params.id
    );
    res.json({ success: true, data: bezeichnungen });
  } catch (error) {
    next(error);
  }
}

export async function createArtikelBez(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const input = createArtikelBezSchema.parse({
      ...req.body,
      artikelId: req.params.id,
    });
    const bez = await artikelService.createArtikelBez(
      req.user!.mandantId,
      input,
      req.user?.kuerzel
    );
    res.status(201).json({ success: true, data: bez });
  } catch (error) {
    next(error);
  }
}
