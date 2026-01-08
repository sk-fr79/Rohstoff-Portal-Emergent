/**
 * Fehlerbehandlung-Middleware
 * Zentrale Fehlerverarbeitung und Logging
 */

import { Request, Response, NextFunction } from 'express';
import { ZodError } from 'zod';
import { logger } from '../utils/logger.js';
import { env } from '../config/env.js';

// Benutzerdefinierte Fehlerklasse
export class AppError extends Error {
  public readonly statusCode: number;
  public readonly code: string;
  public readonly isOperational: boolean;

  constructor(
    message: string,
    statusCode: number = 500,
    code: string = 'INTERNAL_ERROR',
    isOperational: boolean = true
  ) {
    super(message);
    this.statusCode = statusCode;
    this.code = code;
    this.isOperational = isOperational;
    
    Error.captureStackTrace(this, this.constructor);
  }
}

// Häufig verwendete Fehler
export class NotFoundError extends AppError {
  constructor(resource: string = 'Ressource') {
    super(`${resource} nicht gefunden`, 404, 'NOT_FOUND');
  }
}

export class ValidationError extends AppError {
  constructor(message: string = 'Validierungsfehler') {
    super(message, 400, 'VALIDATION_ERROR');
  }
}

export class UnauthorizedError extends AppError {
  constructor(message: string = 'Nicht autorisiert') {
    super(message, 401, 'UNAUTHORIZED');
  }
}

export class ForbiddenError extends AppError {
  constructor(message: string = 'Zugriff verweigert') {
    super(message, 403, 'FORBIDDEN');
  }
}

export class ConflictError extends AppError {
  constructor(message: string = 'Konflikt bei der Verarbeitung') {
    super(message, 409, 'CONFLICT');
  }
}

// Fehler-Response-Format
interface ErrorResponse {
  success: false;
  error: string;
  code: string;
  details?: unknown;
  stack?: string;
}

/**
 * Globale Fehlerbehandlung-Middleware
 */
export function errorHandler(
  err: Error,
  req: Request,
  res: Response,
  _next: NextFunction
): void {
  // Standard-Werte
  let statusCode = 500;
  let code = 'INTERNAL_ERROR';
  let message = 'Ein interner Fehler ist aufgetreten';
  let details: unknown = undefined;

  // Fehlertyp ermitteln
  if (err instanceof AppError) {
    statusCode = err.statusCode;
    code = err.code;
    message = err.message;
  } else if (err instanceof ZodError) {
    // Zod-Validierungsfehler
    statusCode = 400;
    code = 'VALIDATION_ERROR';
    message = 'Validierungsfehler';
    details = err.errors.map(e => ({
      field: e.path.join('.'),
      message: e.message,
    }));
  } else if (err.name === 'PrismaClientKnownRequestError') {
    // Prisma-Datenbankfehler
    statusCode = 400;
    const prismaError = err as { code?: string };
    
    switch (prismaError.code) {
      case 'P2002':
        code = 'DUPLICATE_ENTRY';
        message = 'Ein Eintrag mit diesen Daten existiert bereits';
        break;
      case 'P2025':
        code = 'NOT_FOUND';
        message = 'Der angeforderte Datensatz wurde nicht gefunden';
        statusCode = 404;
        break;
      default:
        code = 'DATABASE_ERROR';
        message = 'Datenbankfehler';
    }
  }

  // Logging
  const logData = {
    method: req.method,
    url: req.originalUrl,
    statusCode,
    code,
    userId: (req as { user?: { id: string } }).user?.id,
    ip: req.ip,
  };

  if (statusCode >= 500) {
    logger.error(`${message}`, { ...logData, stack: err.stack });
  } else {
    logger.warn(`${message}`, logData);
  }

  // Response erstellen
  const response: ErrorResponse = {
    success: false,
    error: message,
    code,
  };

  if (details) {
    response.details = details;
  }

  // Stack-Trace nur in Development
  if (env.NODE_ENV === 'development' && err.stack) {
    response.stack = err.stack;
  }

  res.status(statusCode).json(response);
}

/**
 * 404-Handler für nicht gefundene Routen
 */
export function notFoundHandler(req: Request, res: Response): void {
  res.status(404).json({
    success: false,
    error: `Route ${req.method} ${req.originalUrl} nicht gefunden`,
    code: 'ROUTE_NOT_FOUND',
  });
}
