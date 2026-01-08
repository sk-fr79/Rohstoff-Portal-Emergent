/**
 * Umgebungsvariablen-Konfiguration
 * Zentrale Verwaltung aller Environment-Variablen mit Validierung
 */

import { z } from 'zod';

// Schema für Umgebungsvariablen
const envSchema = z.object({
  // Server-Konfiguration
  NODE_ENV: z.enum(['development', 'production', 'test']).default('development'),
  PORT: z.string().transform(Number).default('8001'),
  
  // Datenbank-Konfiguration
  DATABASE_URL: z.string().url(),
  
  // Redis-Konfiguration
  REDIS_URL: z.string(),
  
  // JWT-Konfiguration
  JWT_SECRET: z.string().min(32, 'JWT_SECRET muss mindestens 32 Zeichen haben'),
  JWT_REFRESH_SECRET: z.string().min(32, 'JWT_REFRESH_SECRET muss mindestens 32 Zeichen haben'),
  JWT_EXPIRES_IN: z.string().default('15m'),
  JWT_REFRESH_EXPIRES_IN: z.string().default('7d'),
  
  // CORS-Konfiguration
  CORS_ORIGIN: z.string().default('http://localhost:3000'),
  
  // Logging
  LOG_LEVEL: z.enum(['error', 'warn', 'info', 'debug']).default('info'),
});

// Validiere und exportiere Umgebungsvariablen
const parseEnv = () => {
  try {
    return envSchema.parse(process.env);
  } catch (error) {
    if (error instanceof z.ZodError) {
      const missingVars = error.errors.map(e => e.path.join('.')).join(', ');
      throw new Error(`Fehlende oder ungültige Umgebungsvariablen: ${missingVars}`);
    }
    throw error;
  }
};

export const env = parseEnv();

// Typ-Export für TypeScript-Unterstützung
export type Env = z.infer<typeof envSchema>;
