/**
 * Hook zum dynamischen Laden von Feld-Verknüpfungen
 * Prüft, ob für ein bestimmtes Modul/Feld eine Verknüpfung existiert
 * und lädt die entsprechenden Optionen
 */
import { useState, useEffect, useCallback } from 'react';
import { useAuthStore } from '@/store/authStore';

const API_URL = import.meta.env.VITE_API_URL || '/api';

export interface FieldBindingInfo {
  id: string;
  source_type: 'reference_table' | 'api_query';
  reference_table_id?: string;
  reference_table_name?: string;
  api_config_id?: string;
  api_name?: string;
  display_field: string;
  value_field: string;
  min_search_chars?: number;
  cache_ttl_seconds?: number;
  is_required: boolean;
  allow_search: boolean;
}

interface UseFieldBindingResult {
  hasBinding: boolean;
  binding: FieldBindingInfo | null;
  isLoading: boolean;
  error: string | null;
}

// Cache für Feld-Verknüpfungen (vermeidet wiederholte API-Calls)
const bindingsCache: Record<string, FieldBindingInfo | null> = {};
const cacheTimestamps: Record<string, number> = {};
const CACHE_DURATION = 60000; // 1 Minute Cache

/**
 * Hook zum Prüfen und Laden einer Feld-Verknüpfung
 */
export function useFieldBinding(module: string, fieldName: string): UseFieldBindingResult {
  const { accessToken: token } = useAuthStore();
  const [binding, setBinding] = useState<FieldBindingInfo | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const cacheKey = `${module}.${fieldName}`;

  useEffect(() => {
    const fetchBinding = async () => {
      // Check cache first
      const now = Date.now();
      if (bindingsCache[cacheKey] !== undefined && 
          cacheTimestamps[cacheKey] && 
          (now - cacheTimestamps[cacheKey]) < CACHE_DURATION) {
        setBinding(bindingsCache[cacheKey]);
        setIsLoading(false);
        return;
      }

      setIsLoading(true);
      setError(null);

      try {
        const response = await fetch(
          `${API_URL}/system/field-binding/${module}/${fieldName}`,
          { headers: { 'Authorization': `Bearer ${token}` } }
        );

        if (response.ok) {
          const data = await response.json();
          if (data.success && data.data) {
            bindingsCache[cacheKey] = data.data;
            cacheTimestamps[cacheKey] = now;
            setBinding(data.data);
          } else {
            bindingsCache[cacheKey] = null;
            cacheTimestamps[cacheKey] = now;
            setBinding(null);
          }
        } else if (response.status === 404) {
          // Keine Verknüpfung gefunden - das ist OK
          bindingsCache[cacheKey] = null;
          cacheTimestamps[cacheKey] = now;
          setBinding(null);
        } else {
          throw new Error('Fehler beim Laden der Feld-Verknüpfung');
        }
      } catch (err) {
        setError(err instanceof Error ? err.message : 'Unbekannter Fehler');
        setBinding(null);
      } finally {
        setIsLoading(false);
      }
    };

    if (module && fieldName && token) {
      fetchBinding();
    } else {
      // Wenn kein Token vorhanden, Loading beenden
      setIsLoading(false);
    }
  }, [module, fieldName, token, cacheKey]);

  return {
    hasBinding: binding !== null,
    binding,
    isLoading,
    error,
  };
}

/**
 * Cache invalidieren (z.B. nach dem Erstellen einer neuen Verknüpfung)
 */
export function invalidateFieldBindingCache(module?: string, fieldName?: string) {
  if (module && fieldName) {
    const cacheKey = `${module}.${fieldName}`;
    delete bindingsCache[cacheKey];
    delete cacheTimestamps[cacheKey];
  } else {
    // Gesamten Cache leeren
    Object.keys(bindingsCache).forEach(key => {
      delete bindingsCache[key];
      delete cacheTimestamps[key];
    });
  }
}
