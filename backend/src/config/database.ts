/**
 * Datenbank-Konfiguration
 * Prisma Client Initialisierung mit Connection Pooling
 */

import { PrismaClient } from '@prisma/client';
import { env } from './env.js';
import { logger } from '../utils/logger.js';

// Singleton-Pattern für Prisma Client
const globalForPrisma = globalThis as unknown as {
  prisma: PrismaClient | undefined;
};

// Prisma Client mit Logging-Konfiguration
export const prisma = globalForPrisma.prisma ?? new PrismaClient({
  log: env.NODE_ENV === 'development' 
    ? [
        { level: 'query', emit: 'event' },
        { level: 'error', emit: 'event' },
        { level: 'warn', emit: 'event' },
      ]
    : [{ level: 'error', emit: 'event' }],
  datasources: {
    db: {
      url: env.DATABASE_URL,
    },
  },
});

// Event-Handler für Logging
if (env.NODE_ENV === 'development') {
  prisma.$on('query' as never, (e: { query: string; duration: number }) => {
    logger.debug(`Query: ${e.query}`);
    logger.debug(`Duration: ${e.duration}ms`);
  });
}

prisma.$on('error' as never, (e: { message: string }) => {
  logger.error(`Prisma Fehler: ${e.message}`);
});

// Singleton in Development speichern
if (env.NODE_ENV !== 'production') {
  globalForPrisma.prisma = prisma;
}

/**
 * Datenbankverbindung testen
 */
export async function testDatabaseConnection(): Promise<boolean> {
  try {
    await prisma.$queryRaw`SELECT 1`;
    logger.info('Datenbankverbindung erfolgreich hergestellt');
    return true;
  } catch (error) {
    logger.error('Datenbankverbindung fehlgeschlagen:', error);
    return false;
  }
}

/**
 * Datenbankverbindung schließen
 */
export async function disconnectDatabase(): Promise<void> {
  await prisma.$disconnect();
  logger.info('Datenbankverbindung geschlossen');
}
