/**
 * Auth-Controller
 * HTTP-Handler für Authentifizierung
 */

import { Request, Response, NextFunction } from 'express';
import * as authService from './service.js';
import { loginSchema, registerSchema, refreshTokenSchema, changePasswordSchema } from './types.js';
import { AuthRequest } from '../../middleware/auth.js';

/**
 * POST /api/auth/login
 * Benutzer anmelden
 */
export async function login(
  req: Request,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const input = loginSchema.parse(req.body);
    const ipAdresse = req.ip;
    const userAgent = req.headers['user-agent'];
    
    const result = await authService.login(input, ipAdresse, userAgent);
    
    // Refresh-Token als HTTP-Only Cookie setzen
    if (result.refreshToken) {
      res.cookie('refreshToken', result.refreshToken, {
        httpOnly: true,
        secure: process.env.NODE_ENV === 'production',
        sameSite: 'strict',
        maxAge: 7 * 24 * 60 * 60 * 1000, // 7 Tage
      });
    }
    
    res.json(result);
  } catch (error) {
    next(error);
  }
}

/**
 * POST /api/auth/register
 * Neuen Benutzer registrieren
 */
export async function register(
  req: Request,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const input = registerSchema.parse(req.body);
    const result = await authService.register(input);
    res.status(201).json(result);
  } catch (error) {
    next(error);
  }
}

/**
 * POST /api/auth/refresh
 * Access-Token mit Refresh-Token erneuern
 */
export async function refresh(
  req: Request,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    // Token aus Cookie oder Body
    const refreshToken = req.cookies?.refreshToken || req.body?.refreshToken;
    
    if (!refreshToken) {
      res.status(400).json({
        success: false,
        error: 'Refresh-Token erforderlich',
        code: 'REFRESH_TOKEN_REQUIRED',
      });
      return;
    }
    
    const ipAdresse = req.ip;
    const userAgent = req.headers['user-agent'];
    
    const result = await authService.refreshTokens(refreshToken, ipAdresse, userAgent);
    
    // Neuen Refresh-Token als Cookie setzen
    if (result.refreshToken) {
      res.cookie('refreshToken', result.refreshToken, {
        httpOnly: true,
        secure: process.env.NODE_ENV === 'production',
        sameSite: 'strict',
        maxAge: 7 * 24 * 60 * 60 * 1000,
      });
    }
    
    res.json(result);
  } catch (error) {
    next(error);
  }
}

/**
 * POST /api/auth/logout
 * Benutzer abmelden
 */
export async function logout(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    if (req.user) {
      await authService.logout(req.user.id);
    }
    
    // Cookie löschen
    res.clearCookie('refreshToken');
    
    res.json({
      success: true,
      message: 'Erfolgreich abgemeldet',
    });
  } catch (error) {
    next(error);
  }
}

/**
 * POST /api/auth/change-password
 * Passwort ändern
 */
export async function changePassword(
  req: AuthRequest,
  res: Response,
  next: NextFunction
): Promise<void> {
  try {
    const input = changePasswordSchema.parse(req.body);
    
    if (!req.user) {
      res.status(401).json({
        success: false,
        error: 'Nicht authentifiziert',
        code: 'NOT_AUTHENTICATED',
      });
      return;
    }
    
    await authService.changePassword(
      req.user.id,
      input.altesPasswort,
      input.neuesPasswort
    );
    
    // Cookie löschen (Benutzer muss sich neu anmelden)
    res.clearCookie('refreshToken');
    
    res.json({
      success: true,
      message: 'Passwort erfolgreich geändert. Bitte melden Sie sich erneut an.',
    });
  } catch (error) {
    next(error);
  }
}

/**
 * GET /api/auth/me
 * Aktuellen Benutzer abrufen
 */
export async function me(
  req: AuthRequest,
  res: Response
): Promise<void> {
  res.json({
    success: true,
    user: req.user,
  });
}
