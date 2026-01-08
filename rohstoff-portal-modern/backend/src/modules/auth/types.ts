/**
 * Auth-Modul Typdefinitionen
 */

import { z } from 'zod';

// Login-Schema
export const loginSchema = z.object({
  benutzername: z.string().min(3, 'Benutzername muss mindestens 3 Zeichen haben'),
  passwort: z.string().min(8, 'Passwort muss mindestens 8 Zeichen haben'),
});

export type LoginInput = z.infer<typeof loginSchema>;

// Registrierung-Schema
export const registerSchema = z.object({
  mandantId: z.string().uuid('Ungültige Mandanten-ID'),
  benutzername: z.string().min(3, 'Benutzername muss mindestens 3 Zeichen haben').max(50),
  email: z.string().email('Ungültige E-Mail-Adresse'),
  passwort: z.string()
    .min(8, 'Passwort muss mindestens 8 Zeichen haben')
    .regex(/[A-Z]/, 'Passwort muss mindestens einen Großbuchstaben enthalten')
    .regex(/[a-z]/, 'Passwort muss mindestens einen Kleinbuchstaben enthalten')
    .regex(/[0-9]/, 'Passwort muss mindestens eine Zahl enthalten')
    .regex(/[^A-Za-z0-9]/, 'Passwort muss mindestens ein Sonderzeichen enthalten'),
  vorname: z.string().max(50).optional(),
  nachname: z.string().max(50).optional(),
  kuerzel: z.string().max(10).optional(),
});

export type RegisterInput = z.infer<typeof registerSchema>;

// Token-Refresh-Schema
export const refreshTokenSchema = z.object({
  refreshToken: z.string().min(1, 'Refresh-Token erforderlich'),
});

export type RefreshTokenInput = z.infer<typeof refreshTokenSchema>;

// Passwort-Änderung-Schema
export const changePasswordSchema = z.object({
  altesPasswort: z.string().min(1, 'Altes Passwort erforderlich'),
  neuesPasswort: z.string()
    .min(8, 'Passwort muss mindestens 8 Zeichen haben')
    .regex(/[A-Z]/, 'Passwort muss mindestens einen Großbuchstaben enthalten')
    .regex(/[a-z]/, 'Passwort muss mindestens einen Kleinbuchstaben enthalten')
    .regex(/[0-9]/, 'Passwort muss mindestens eine Zahl enthalten')
    .regex(/[^A-Za-z0-9]/, 'Passwort muss mindestens ein Sonderzeichen enthalten'),
});

export type ChangePasswordInput = z.infer<typeof changePasswordSchema>;

// Auth-Response-Typen
export interface AuthResponse {
  success: boolean;
  accessToken?: string;
  refreshToken?: string;
  expiresIn?: number;
  user?: {
    id: string;
    mandantId: string;
    benutzername: string;
    email: string;
    vorname?: string;
    nachname?: string;
    kuerzel?: string;
    istAdmin: boolean;
  };
  error?: string;
  code?: string;
}

export interface TokenPayload {
  userId: string;
  mandantId: string;
  email: string;
}
