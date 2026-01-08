/**
 * Verschlüsselung für sensible Daten
 * AES-256-GCM Verschlüsselung at rest
 */

import { createCipheriv, createDecipheriv, randomBytes, scryptSync } from 'crypto';
import { env } from '../config/env.js';
import { logger } from './logger.js';

// Algorithmus und Konfiguration
const ALGORITHM = 'aes-256-gcm';
const IV_LENGTH = 16;
const SALT_LENGTH = 32;
const TAG_LENGTH = 16;
const KEY_LENGTH = 32;

// Schlüssel aus Environment-Secret ableiten
function deriveKey(salt: Buffer): Buffer {
  return scryptSync(env.JWT_SECRET, salt, KEY_LENGTH);
}

/**
 * Daten verschlüsseln
 * @param plaintext - Zu verschlüsselnde Daten
 * @returns Verschlüsselter String (Base64)
 */
export function encrypt(plaintext: string): string {
  try {
    // Zufällige Salt und IV generieren
    const salt = randomBytes(SALT_LENGTH);
    const iv = randomBytes(IV_LENGTH);
    
    // Schlüssel ableiten
    const key = deriveKey(salt);
    
    // Cipher erstellen und verschlüsseln
    const cipher = createCipheriv(ALGORITHM, key, iv);
    let encrypted = cipher.update(plaintext, 'utf8', 'hex');
    encrypted += cipher.final('hex');
    
    // Auth-Tag holen
    const tag = cipher.getAuthTag();
    
    // Alles zusammenfügen: salt + iv + tag + encrypted
    const combined = Buffer.concat([
      salt,
      iv,
      tag,
      Buffer.from(encrypted, 'hex')
    ]);
    
    return combined.toString('base64');
  } catch (error) {
    logger.error('Verschlüsselungsfehler:', error);
    throw new Error('Verschlüsselung fehlgeschlagen');
  }
}

/**
 * Daten entschlüsseln
 * @param ciphertext - Verschlüsselter String (Base64)
 * @returns Entschlüsselter Klartext
 */
export function decrypt(ciphertext: string): string {
  try {
    // Base64 dekodieren
    const combined = Buffer.from(ciphertext, 'base64');
    
    // Komponenten extrahieren
    const salt = combined.subarray(0, SALT_LENGTH);
    const iv = combined.subarray(SALT_LENGTH, SALT_LENGTH + IV_LENGTH);
    const tag = combined.subarray(SALT_LENGTH + IV_LENGTH, SALT_LENGTH + IV_LENGTH + TAG_LENGTH);
    const encrypted = combined.subarray(SALT_LENGTH + IV_LENGTH + TAG_LENGTH);
    
    // Schlüssel ableiten
    const key = deriveKey(salt);
    
    // Decipher erstellen und entschlüsseln
    const decipher = createDecipheriv(ALGORITHM, key, iv);
    decipher.setAuthTag(tag);
    
    let decrypted = decipher.update(encrypted);
    decrypted = Buffer.concat([decrypted, decipher.final()]);
    
    return decrypted.toString('utf8');
  } catch (error) {
    logger.error('Entschlüsselungsfehler:', error);
    throw new Error('Entschlüsselung fehlgeschlagen');
  }
}

/**
 * Sensible Daten für Logging maskieren
 * @param data - Zu maskierendes Objekt
 * @param sensitiveFields - Liste der sensiblen Feldnamen
 * @returns Maskiertes Objekt
 */
export function maskSensitiveData<T extends Record<string, unknown>>(
  data: T,
  sensitiveFields: string[] = ['password', 'passwort', 'token', 'secret', 'iban', 'kontonummer']
): T {
  const masked = { ...data };
  
  for (const field of sensitiveFields) {
    if (field in masked && masked[field]) {
      masked[field] = '***MASKIERT***' as T[keyof T];
    }
  }
  
  return masked;
}
