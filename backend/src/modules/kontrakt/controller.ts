/**
 * Kontrakt-Controller
 */

import { Response, NextFunction } from 'express';
import * as kontraktService from './service.js';
import { createKontraktSchema, updateKontraktSchema, searchKontraktSchema, createKontraktPosSchema } from './types.js';
import { AuthRequest } from '../../middleware/auth.js';
import { z } from 'zod';

export async function search(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const params = searchKontraktSchema.parse({
      ...req.query,
      page: req.query.page ? parseInt(req.query.page as string, 10) : 1,
      limit: req.query.limit ? parseInt(req.query.limit as string, 10) : 20,
      istEinkauf: req.query.istEinkauf === 'true' ? true : req.query.istEinkauf === 'false' ? false : undefined,
      abgeschlossen: req.query.abgeschlossen === 'true' ? true : req.query.abgeschlossen === 'false' ? false : undefined,
    });
    
    const result = await kontraktService.search(req.user!.mandantId, params);
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
    const kontrakt = await kontraktService.getById(req.user!.mandantId, req.params.id);
    res.json({ success: true, data: kontrakt });
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
    const input = createKontraktSchema.parse(req.body);
    const kontrakt = await kontraktService.create(
      req.user!.mandantId,
      input,
      req.user?.kuerzel
    );
    res.status(201).json({ success: true, data: kontrakt });
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
    const input = updateKontraktSchema.parse(req.body);
    const kontrakt = await kontraktService.update(
      req.user!.mandantId,
      req.params.id,
      input,
      req.user?.kuerzel
    );
    res.json({ success: true, data: kontrakt });
  } catch (error) {
    next(error);
  }
}

export async function abschliessen(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const kontrakt = await kontraktService.abschliessen(
      req.user!.mandantId,
      req.params.id,
      req.user?.kuerzel
    );
    res.json({ success: true, data: kontrakt });
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
    const { grund } = z.object({ grund: z.string().min(1, 'Löschgrund erforderlich') }).parse(req.body);
    await kontraktService.remove(
      req.user!.mandantId,
      req.params.id,
      grund,
      req.user?.kuerzel
    );
    res.json({ success: true, message: 'Kontrakt gelöscht' });
  } catch (error) {
    next(error);
  }
}

export async function addPosition(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const input = createKontraktPosSchema.parse(req.body);
    const position = await kontraktService.addPosition(
      req.user!.mandantId,
      req.params.id,
      input,
      req.user?.kuerzel
    );
    res.status(201).json({ success: true, data: position });
  } catch (error) {
    next(error);
  }
}
