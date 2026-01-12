/**
 * Datum-Utility-Funktionen für konsistente Zeitzonendarstellung
 * Alle Backend-Zeitstempel sind in UTC gespeichert und werden hier in lokale Zeit konvertiert
 */

/**
 * Konvertiert einen ISO-Zeitstempel zu einem Date-Objekt
 * Behandelt Zeitstempel ohne 'Z' Suffix als UTC
 */
export function parseUTCDate(isoString: string | null | undefined): Date | null {
  if (!isoString) return null;
  // Stelle sicher, dass der Zeitstempel als UTC interpretiert wird
  const utcString = isoString.endsWith('Z') ? isoString : isoString + 'Z';
  return new Date(utcString);
}

/**
 * Formatiert einen Zeitstempel als relative Zeit (z.B. "vor 5 Min.")
 */
export function formatRelativeTime(isoString: string | null | undefined): string {
  if (!isoString) return 'Nie';
  
  const date = parseUTCDate(isoString);
  if (!date) return 'Ungültig';
  
  const now = new Date();
  const diffMs = now.getTime() - date.getTime();
  const diffSecs = Math.floor(diffMs / 1000);
  const diffMins = Math.floor(diffMs / 60000);
  const diffHours = Math.floor(diffMs / 3600000);
  const diffDays = Math.floor(diffMs / 86400000);
  
  if (diffSecs < 0) return 'In der Zukunft';
  if (diffSecs < 60) return 'Gerade eben';
  if (diffMins < 60) return `vor ${diffMins} Min.`;
  if (diffHours < 24) return `vor ${diffHours} Std.`;
  if (diffDays === 1) return 'Gestern';
  if (diffDays < 7) return `vor ${diffDays} Tagen`;
  if (diffDays < 30) return `vor ${Math.floor(diffDays / 7)} Wochen`;
  
  return date.toLocaleDateString('de-DE');
}

/**
 * Formatiert Datum und Uhrzeit (z.B. "12.01.2026, 16:25")
 */
export function formatDateTime(isoString: string | null | undefined): string {
  if (!isoString) return '-';
  
  const date = parseUTCDate(isoString);
  if (!date) return 'Ungültig';
  
  return date.toLocaleString('de-DE', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric',
    hour: '2-digit',
    minute: '2-digit'
  });
}

/**
 * Formatiert nur das Datum (z.B. "12.01.2026")
 */
export function formatDate(isoString: string | null | undefined): string {
  if (!isoString) return '-';
  
  const date = parseUTCDate(isoString);
  if (!date) return 'Ungültig';
  
  return date.toLocaleDateString('de-DE', {
    day: '2-digit',
    month: '2-digit',
    year: 'numeric'
  });
}

/**
 * Formatiert das Datum ausführlich (z.B. "Montag, 12. Januar 2026")
 */
export function formatDateLong(isoString: string | null | undefined): string {
  if (!isoString) return '-';
  
  const date = parseUTCDate(isoString);
  if (!date) return 'Ungültig';
  
  return date.toLocaleDateString('de-DE', {
    weekday: 'long',
    day: '2-digit',
    month: 'long',
    year: 'numeric'
  });
}

/**
 * Formatiert nur die Uhrzeit (z.B. "16:25")
 */
export function formatTime(isoString: string | null | undefined): string {
  if (!isoString) return '-';
  
  const date = parseUTCDate(isoString);
  if (!date) return 'Ungültig';
  
  return date.toLocaleTimeString('de-DE', {
    hour: '2-digit',
    minute: '2-digit'
  });
}
