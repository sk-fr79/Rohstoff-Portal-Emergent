/**
 * Rate-Limiting Middleware
 * Schutz vor Brute-Force und DoS-Angriffen
 */

import rateLimit from 'express-rate-limit';
import { Request, Response } from 'express';

// Standard Rate-Limiter (100 Anfragen pro 15 Minuten)
export const standardLimiter = rateLimit({
  windowMs: 15 * 60 * 1000, // 15 Minuten
  max: 100, // Max. Anfragen pro Fenster
  message: {
    success: false,
    error: 'Zu viele Anfragen. Bitte versuchen Sie es später erneut.',
    code: 'RATE_LIMIT_EXCEEDED',
  },
  standardHeaders: true,
  legacyHeaders: false,
  keyGenerator: (req: Request) => {
    // IP + User-ID für angemeldete Benutzer
    const userId = (req as { user?: { id: string } }).user?.id;
    return userId ? `${req.ip}-${userId}` : req.ip || 'unknown';
  },
});

// Strenger Rate-Limiter für Auth-Endpunkte (10 Anfragen pro 15 Minuten)
export const authLimiter = rateLimit({
  windowMs: 15 * 60 * 1000,
  max: 10,
  message: {
    success: false,
    error: 'Zu viele Login-Versuche. Bitte versuchen Sie es später erneut.',
    code: 'AUTH_RATE_LIMIT_EXCEEDED',
  },
  standardHeaders: true,
  legacyHeaders: false,
  skipSuccessfulRequests: true, // Erfolgreiche Anfragen nicht zählen
});

// Sehr strenger Rate-Limiter für Passwort-Reset (5 Anfragen pro Stunde)
export const passwordResetLimiter = rateLimit({
  windowMs: 60 * 60 * 1000, // 1 Stunde
  max: 5,
  message: {
    success: false,
    error: 'Zu viele Passwort-Reset-Anfragen. Bitte versuchen Sie es später erneut.',
    code: 'PASSWORD_RESET_RATE_LIMIT_EXCEEDED',
  },
  standardHeaders: true,
  legacyHeaders: false,
});

// API Rate-Limiter (1000 Anfragen pro Minute für intensive Operationen)
export const apiLimiter = rateLimit({
  windowMs: 60 * 1000, // 1 Minute
  max: 1000,
  message: {
    success: false,
    error: 'API-Limit überschritten. Bitte reduzieren Sie die Anfragehäufigkeit.',
    code: 'API_RATE_LIMIT_EXCEEDED',
  },
  standardHeaders: true,
  legacyHeaders: false,
});
