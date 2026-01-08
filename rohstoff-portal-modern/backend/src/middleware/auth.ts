/**
 * Authentifizierung-Middleware
 * JWT-Validierung und Benutzer-Extraktion
 */

import { Request, Response, NextFunction } from 'express';
import jwt from 'jsonwebtoken';
import { env } from '../config/env.js';
import { logger } from '../utils/logger.js';
import { prisma } from '../config/database.js';

// Erweiterte Request-Typisierung
export interface AuthRequest extends Request {
  user?: {
    id: string;
    mandantId: string;
    benutzername: string;
    email: string;
    kuerzel?: string;
    istAdmin: boolean;
  };
}

// JWT-Payload Typisierung
interface JWTPayload {
  userId: string;
  mandantId: string;
  email: string;
  iat: number;
  exp: number;
}

/**
 * Authentifizierung prüfen
 * Validiert JWT-Token und lädt Benutzerinformationen
 */
export async function authenticate(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    // Token aus Header extrahieren
    const authHeader = req.headers.authorization;
    if (!authHeader || !authHeader.startsWith('Bearer ')) {
      res.status(401).json({
        success: false,
        error: 'Authentifizierung erforderlich',
        code: 'AUTH_REQUIRED'
      });
      return;
    }

    const token = authHeader.substring(7);

    // Token verifizieren
    let payload: JWTPayload;
    try {
      payload = jwt.verify(token, env.JWT_SECRET) as JWTPayload;
    } catch (error) {
      if (error instanceof jwt.TokenExpiredError) {
        res.status(401).json({
          success: false,
          error: 'Token abgelaufen',
          code: 'TOKEN_EXPIRED'
        });
        return;
      }
      res.status(401).json({
        success: false,
        error: 'Ungültiger Token',
        code: 'INVALID_TOKEN'
      });
      return;
    }

    // Benutzer aus Datenbank laden
    const benutzer = await prisma.benutzer.findUnique({
      where: { id: payload.userId },
      select: {
        id: true,
        mandantId: true,
        benutzername: true,
        email: true,
        kuerzel: true,
        aktiv: true,
        istAdmin: true,
        gesperrtBis: true,
      },
    });

    if (!benutzer) {
      res.status(401).json({
        success: false,
        error: 'Benutzer nicht gefunden',
        code: 'USER_NOT_FOUND'
      });
      return;
    }

    // Prüfen ob Benutzer aktiv
    if (!benutzer.aktiv) {
      res.status(403).json({
        success: false,
        error: 'Benutzerkonto deaktiviert',
        code: 'ACCOUNT_DISABLED'
      });
      return;
    }

    // Prüfen ob Benutzer gesperrt
    if (benutzer.gesperrtBis && benutzer.gesperrtBis > new Date()) {
      res.status(403).json({
        success: false,
        error: 'Benutzerkonto temporär gesperrt',
        code: 'ACCOUNT_LOCKED'
      });
      return;
    }

    // Benutzer an Request anhängen
    req.user = {
      id: benutzer.id,
      mandantId: benutzer.mandantId,
      benutzername: benutzer.benutzername,
      email: benutzer.email,
      kuerzel: benutzer.kuerzel ?? undefined,
      istAdmin: benutzer.istAdmin,
    };

    next();
  } catch (error) {
    logger.error('Authentifizierungsfehler:', error);
    res.status(500).json({
      success: false,
      error: 'Interner Serverfehler',
      code: 'INTERNAL_ERROR'
    });
  }
}

/**
 * Admin-Berechtigung prüfen
 */
export function requireAdmin(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): void {
  if (!req.user?.istAdmin) {
    res.status(403).json({
      success: false,
      error: 'Administratorrechte erforderlich',
      code: 'ADMIN_REQUIRED'
    });
    return;
  }
  next();
}

/**
 * Mandanten-Zugriff prüfen
 * Stellt sicher, dass Benutzer nur auf eigenen Mandanten zugreifen
 */
export function requireMandant(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): void {
  const requestedMandantId = req.params.mandantId || req.body?.mandantId;
  
  if (requestedMandantId && requestedMandantId !== req.user?.mandantId && !req.user?.istAdmin) {
    res.status(403).json({
      success: false,
      error: 'Zugriff auf diesen Mandanten nicht erlaubt',
      code: 'MANDANT_ACCESS_DENIED'
    });
    return;
  }
  next();
}
