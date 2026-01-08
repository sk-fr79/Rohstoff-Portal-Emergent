/**
 * Auth-Service
 * Business-Logic für Authentifizierung
 */

import jwt from 'jsonwebtoken';
import { v4 as uuidv4 } from 'uuid';
import { prisma } from '../../config/database.js';
import { env } from '../../config/env.js';
import { hashPassword, verifyPassword, needsRehash } from '../../utils/hashing.js';
import { logger } from '../../utils/logger.js';
import { AppError, UnauthorizedError, ConflictError } from '../../middleware/errorHandler.js';
import type { LoginInput, RegisterInput, TokenPayload, AuthResponse } from './types.js';

// Token-Lebensdauern parsen
function parseExpiration(exp: string): number {
  const match = exp.match(/(\d+)(m|h|d)/);
  if (!match) return 15 * 60 * 1000; // Default: 15 Minuten
  
  const value = parseInt(match[1]);
  switch (match[2]) {
    case 'm': return value * 60 * 1000;
    case 'h': return value * 60 * 60 * 1000;
    case 'd': return value * 24 * 60 * 60 * 1000;
    default: return 15 * 60 * 1000;
  }
}

/**
 * Access-Token generieren
 */
function generateAccessToken(payload: TokenPayload): string {
  return jwt.sign(payload, env.JWT_SECRET, {
    expiresIn: env.JWT_EXPIRES_IN,
  });
}

/**
 * Refresh-Token generieren
 */
function generateRefreshToken(): string {
  return uuidv4() + '-' + uuidv4();
}

/**
 * Benutzer anmelden
 */
export async function login(
  input: LoginInput,
  ipAdresse?: string,
  userAgent?: string
): Promise<AuthResponse> {
  const { benutzername, passwort } = input;
  
  // Benutzer suchen
  const benutzer = await prisma.benutzer.findFirst({
    where: {
      OR: [
        { benutzername },
        { email: benutzername },
      ],
    },
  });
  
  if (!benutzer) {
    // Timing-Attack verhindern
    await verifyPassword('$argon2id$v=19$m=65536,t=3,p=4$dummy$dummy', passwort);
    throw new UnauthorizedError('Ungültige Anmeldedaten');
  }
  
  // Prüfen ob Konto gesperrt
  if (benutzer.gesperrtBis && benutzer.gesperrtBis > new Date()) {
    const verbleibendeMinuten = Math.ceil(
      (benutzer.gesperrtBis.getTime() - Date.now()) / 60000
    );
    throw new AppError(
      `Konto temporär gesperrt. Versuchen Sie es in ${verbleibendeMinuten} Minuten erneut.`,
      403,
      'ACCOUNT_LOCKED'
    );
  }
  
  // Prüfen ob Konto aktiv
  if (!benutzer.aktiv) {
    throw new AppError('Konto deaktiviert', 403, 'ACCOUNT_DISABLED');
  }
  
  // Passwort verifizieren
  const passwortKorrekt = await verifyPassword(benutzer.passwortHash, passwort);
  
  if (!passwortKorrekt) {
    // Login-Versuche erhöhen
    const neueVersuche = benutzer.loginVersuche + 1;
    let gesperrtBis: Date | null = null;
    
    // Nach 5 fehlgeschlagenen Versuchen für 15 Minuten sperren
    if (neueVersuche >= 5) {
      gesperrtBis = new Date(Date.now() + 15 * 60 * 1000);
    }
    
    await prisma.benutzer.update({
      where: { id: benutzer.id },
      data: {
        loginVersuche: neueVersuche,
        gesperrtBis,
      },
    });
    
    throw new UnauthorizedError('Ungültige Anmeldedaten');
  }
  
  // Passwort-Rehash prüfen
  if (needsRehash(benutzer.passwortHash)) {
    const neuerHash = await hashPassword(passwort);
    await prisma.benutzer.update({
      where: { id: benutzer.id },
      data: { passwortHash: neuerHash },
    });
  }
  
  // Login-Versuche zurücksetzen und letzten Login aktualisieren
  await prisma.benutzer.update({
    where: { id: benutzer.id },
    data: {
      loginVersuche: 0,
      gesperrtBis: null,
      letzterLogin: new Date(),
    },
  });
  
  // Tokens generieren
  const tokenPayload: TokenPayload = {
    userId: benutzer.id,
    mandantId: benutzer.mandantId,
    email: benutzer.email,
  };
  
  const accessToken = generateAccessToken(tokenPayload);
  const refreshToken = generateRefreshToken();
  
  // Refresh-Token speichern
  const refreshExpiration = parseExpiration(env.JWT_REFRESH_EXPIRES_IN);
  await prisma.refreshToken.create({
    data: {
      benutzerId: benutzer.id,
      token: refreshToken,
      gueltigBis: new Date(Date.now() + refreshExpiration),
      ipAdresse,
      geraeteInfo: userAgent,
    },
  });
  
  logger.info(`Erfolgreicher Login: ${benutzer.benutzername}`, {
    userId: benutzer.id,
    mandantId: benutzer.mandantId,
  });
  
  return {
    success: true,
    accessToken,
    refreshToken,
    expiresIn: parseExpiration(env.JWT_EXPIRES_IN) / 1000,
    user: {
      id: benutzer.id,
      mandantId: benutzer.mandantId,
      benutzername: benutzer.benutzername,
      email: benutzer.email,
      vorname: benutzer.vorname ?? undefined,
      nachname: benutzer.nachname ?? undefined,
      kuerzel: benutzer.kuerzel ?? undefined,
      istAdmin: benutzer.istAdmin,
    },
  };
}

/**
 * Benutzer registrieren
 */
export async function register(input: RegisterInput): Promise<AuthResponse> {
  const { mandantId, benutzername, email, passwort, vorname, nachname, kuerzel } = input;
  
  // Prüfen ob Mandant existiert
  const mandant = await prisma.mandant.findUnique({
    where: { id: mandantId },
  });
  
  if (!mandant) {
    throw new AppError('Mandant nicht gefunden', 404, 'MANDANT_NOT_FOUND');
  }
  
  // Prüfen ob Benutzername oder E-Mail bereits existiert
  const existierend = await prisma.benutzer.findFirst({
    where: {
      OR: [
        { benutzername },
        { email },
      ],
    },
  });
  
  if (existierend) {
    if (existierend.benutzername === benutzername) {
      throw new ConflictError('Benutzername bereits vergeben');
    }
    throw new ConflictError('E-Mail-Adresse bereits registriert');
  }
  
  // Passwort hashen
  const passwortHash = await hashPassword(passwort);
  
  // Benutzer erstellen
  const benutzer = await prisma.benutzer.create({
    data: {
      mandantId,
      benutzername,
      email,
      passwortHash,
      vorname,
      nachname,
      kuerzel,
    },
  });
  
  logger.info(`Neuer Benutzer registriert: ${benutzername}`, {
    userId: benutzer.id,
    mandantId,
  });
  
  return {
    success: true,
    user: {
      id: benutzer.id,
      mandantId: benutzer.mandantId,
      benutzername: benutzer.benutzername,
      email: benutzer.email,
      vorname: benutzer.vorname ?? undefined,
      nachname: benutzer.nachname ?? undefined,
      kuerzel: benutzer.kuerzel ?? undefined,
      istAdmin: benutzer.istAdmin,
    },
  };
}

/**
 * Token auffrischen (Refresh-Token-Rotation)
 */
export async function refreshTokens(
  refreshToken: string,
  ipAdresse?: string,
  userAgent?: string
): Promise<AuthResponse> {
  // Refresh-Token suchen
  const storedToken = await prisma.refreshToken.findUnique({
    where: { token: refreshToken },
    include: { benutzer: true },
  });
  
  if (!storedToken) {
    throw new UnauthorizedError('Ungültiger Refresh-Token');
  }
  
  // Prüfen ob Token widerrufen
  if (storedToken.widerrufenAm) {
    // Potentieller Token-Diebstahl - alle Tokens des Benutzers widerrufen
    await prisma.refreshToken.updateMany({
      where: { benutzerId: storedToken.benutzerId },
      data: { widerrufenAm: new Date() },
    });
    logger.warn('Möglicher Token-Diebstahl erkannt', {
      userId: storedToken.benutzerId,
    });
    throw new UnauthorizedError('Token-Sicherheitsproblem erkannt');
  }
  
  // Prüfen ob Token abgelaufen
  if (storedToken.gueltigBis < new Date()) {
    await prisma.refreshToken.update({
      where: { id: storedToken.id },
      data: { widerrufenAm: new Date() },
    });
    throw new UnauthorizedError('Refresh-Token abgelaufen');
  }
  
  const benutzer = storedToken.benutzer;
  
  // Prüfen ob Benutzer noch aktiv
  if (!benutzer.aktiv) {
    throw new AppError('Konto deaktiviert', 403, 'ACCOUNT_DISABLED');
  }
  
  // Alten Token widerrufen (Rotation)
  await prisma.refreshToken.update({
    where: { id: storedToken.id },
    data: { widerrufenAm: new Date() },
  });
  
  // Neue Tokens generieren
  const tokenPayload: TokenPayload = {
    userId: benutzer.id,
    mandantId: benutzer.mandantId,
    email: benutzer.email,
  };
  
  const newAccessToken = generateAccessToken(tokenPayload);
  const newRefreshToken = generateRefreshToken();
  
  // Neuen Refresh-Token speichern
  const refreshExpiration = parseExpiration(env.JWT_REFRESH_EXPIRES_IN);
  await prisma.refreshToken.create({
    data: {
      benutzerId: benutzer.id,
      token: newRefreshToken,
      gueltigBis: new Date(Date.now() + refreshExpiration),
      ipAdresse,
      geraeteInfo: userAgent,
    },
  });
  
  return {
    success: true,
    accessToken: newAccessToken,
    refreshToken: newRefreshToken,
    expiresIn: parseExpiration(env.JWT_EXPIRES_IN) / 1000,
  };
}

/**
 * Abmelden (alle Tokens widerrufen)
 */
export async function logout(userId: string): Promise<void> {
  await prisma.refreshToken.updateMany({
    where: {
      benutzerId: userId,
      widerrufenAm: null,
    },
    data: { widerrufenAm: new Date() },
  });
  
  logger.info('Benutzer abgemeldet', { userId });
}

/**
 * Passwort ändern
 */
export async function changePassword(
  userId: string,
  altesPasswort: string,
  neuesPasswort: string
): Promise<void> {
  const benutzer = await prisma.benutzer.findUnique({
    where: { id: userId },
  });
  
  if (!benutzer) {
    throw new AppError('Benutzer nicht gefunden', 404, 'USER_NOT_FOUND');
  }
  
  // Altes Passwort verifizieren
  const altesKorrekt = await verifyPassword(benutzer.passwortHash, altesPasswort);
  if (!altesKorrekt) {
    throw new UnauthorizedError('Aktuelles Passwort falsch');
  }
  
  // Neues Passwort hashen und speichern
  const neuerHash = await hashPassword(neuesPasswort);
  await prisma.benutzer.update({
    where: { id: userId },
    data: { passwortHash: neuerHash },
  });
  
  // Alle Refresh-Tokens widerrufen
  await prisma.refreshToken.updateMany({
    where: {
      benutzerId: userId,
      widerrufenAm: null,
    },
    data: { widerrufenAm: new Date() },
  });
  
  logger.info('Passwort geändert', { userId });
}
