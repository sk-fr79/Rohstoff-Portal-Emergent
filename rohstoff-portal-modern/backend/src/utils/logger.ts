/**
 * Logger-Utility
 * Strukturiertes Logging mit Winston
 */

import winston from 'winston';
import { env } from '../config/env.js';

// Log-Format definieren
const logFormat = winston.format.combine(
  winston.format.timestamp({ format: 'YYYY-MM-DD HH:mm:ss' }),
  winston.format.errors({ stack: true }),
  winston.format.printf(({ level, message, timestamp, stack, ...meta }) => {
    let log = `${timestamp} [${level.toUpperCase()}]: ${message}`;
    
    // Metadaten hinzufügen wenn vorhanden
    if (Object.keys(meta).length > 0) {
      log += ` ${JSON.stringify(meta)}`;
    }
    
    // Stack-Trace bei Fehlern
    if (stack) {
      log += `\n${stack}`;
    }
    
    return log;
  })
);

// Logger erstellen
export const logger = winston.createLogger({
  level: env.LOG_LEVEL,
  format: logFormat,
  transports: [
    // Konsole
    new winston.transports.Console({
      format: winston.format.combine(
        winston.format.colorize(),
        logFormat
      ),
    }),
    // Fehler-Datei
    new winston.transports.File({
      filename: 'logs/error.log',
      level: 'error',
      maxsize: 5242880, // 5MB
      maxFiles: 5,
    }),
    // Kombinierte Datei
    new winston.transports.File({
      filename: 'logs/combined.log',
      maxsize: 5242880,
      maxFiles: 5,
    }),
  ],
});

// Verzeichnis erstellen falls nicht vorhanden
import { mkdirSync, existsSync } from 'fs';
if (!existsSync('logs')) {
  mkdirSync('logs');
}
