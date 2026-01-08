/**
 * Passwort-Hashing mit Argon2id
 * NIS2.0 konform
 */

import argon2 from 'argon2';
import { logger } from './logger.js';

// Argon2id Konfiguration (OWASP-Empfehlungen)
const ARGON2_CONFIG = {
  type: argon2.argon2id,
  memoryCost: 65536,    // 64 MB
  timeCost: 3,          // Iterationen
  parallelism: 4,       // Paralelle Threads
  hashLength: 32,       // 256 Bit
};

/**
 * Passwort hashen mit Argon2id
 * @param password - Klartext-Passwort
 * @returns Gehashtes Passwort
 */
export async function hashPassword(password: string): Promise<string> {
  try {
    const hash = await argon2.hash(password, ARGON2_CONFIG);
    return hash;
  } catch (error) {
    logger.error('Fehler beim Passwort-Hashing:', error);
    throw new Error('Passwort-Hashing fehlgeschlagen');
  }
}

/**
 * Passwort verifizieren
 * @param hash - Gespeicherter Hash
 * @param password - Zu prüfendes Passwort
 * @returns True wenn Passwort korrekt
 */
export async function verifyPassword(hash: string, password: string): Promise<boolean> {
  try {
    const valid = await argon2.verify(hash, password);
    return valid;
  } catch (error) {
    logger.error('Fehler bei Passwort-Verifikation:', error);
    return false;
  }
}

/**
 * Prüfen ob Rehash notwendig
 * @param hash - Aktueller Hash
 * @returns True wenn Rehash empfohlen
 */
export function needsRehash(hash: string): boolean {
  return argon2.needsRehash(hash, ARGON2_CONFIG);
}
