/**
 * Globaler Store für Feld-Verknüpfungen
 * 
 * Lädt alle Verknüpfungen einmalig beim Login und cached sie.
 * SmartInput-Komponenten greifen auf diesen Cache zu.
 */
import { create } from 'zustand';

const API_URL = import.meta.env.VITE_API_URL || '/api';

export interface FieldBinding {
  id: string;
  module: string;
  field_name: string;
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

interface FieldBindingsState {
  // Alle Verknüpfungen als Map: "module.field" -> FieldBinding
  bindings: Map<string, FieldBinding>;
  isLoaded: boolean;
  isLoading: boolean;
  error: string | null;
  lastLoadedAt: number | null;
  
  // Actions
  loadBindings: (token: string) => Promise<void>;
  getBinding: (module: string, fieldName: string) => FieldBinding | null;
  hasBinding: (module: string, fieldName: string) => boolean;
  invalidateCache: () => void;
  addBinding: (binding: FieldBinding) => void;
  removeBinding: (module: string, fieldName: string) => void;
}

export const useFieldBindingsStore = create<FieldBindingsState>((set, get) => ({
  bindings: new Map(),
  isLoaded: false,
  isLoading: false,
  error: null,
  lastLoadedAt: null,

  loadBindings: async (token: string) => {
    // Nicht neu laden, wenn bereits geladen (max 5 Minuten Cache)
    const state = get();
    const now = Date.now();
    if (state.isLoaded && state.lastLoadedAt && (now - state.lastLoadedAt) < 300000) {
      return;
    }

    set({ isLoading: true, error: null });

    try {
      const response = await fetch(`${API_URL}/system/field-bindings`, {
        headers: { 'Authorization': `Bearer ${token}` }
      });

      if (!response.ok) {
        throw new Error('Fehler beim Laden der Feld-Verknüpfungen');
      }

      const data = await response.json();
      const bindingsMap = new Map<string, FieldBinding>();

      if (data.success && Array.isArray(data.data)) {
        data.data.forEach((binding: FieldBinding) => {
          const key = `${binding.module}.${binding.field_name}`;
          bindingsMap.set(key, binding);
        });
      }

      console.log(`[FieldBindingsStore] Loaded ${bindingsMap.size} field bindings`);
      
      set({
        bindings: bindingsMap,
        isLoaded: true,
        isLoading: false,
        lastLoadedAt: now,
      });
    } catch (err) {
      console.error('[FieldBindingsStore] Error loading bindings:', err);
      set({
        error: err instanceof Error ? err.message : 'Unbekannter Fehler',
        isLoading: false,
      });
    }
  },

  getBinding: (module: string, fieldName: string): FieldBinding | null => {
    const key = `${module}.${fieldName}`;
    return get().bindings.get(key) || null;
  },

  hasBinding: (module: string, fieldName: string): boolean => {
    const key = `${module}.${fieldName}`;
    return get().bindings.has(key);
  },

  invalidateCache: () => {
    set({
      bindings: new Map(),
      isLoaded: false,
      lastLoadedAt: null,
    });
  },

  addBinding: (binding: FieldBinding) => {
    const key = `${binding.module}.${binding.field_name}`;
    const newBindings = new Map(get().bindings);
    newBindings.set(key, binding);
    set({ bindings: newBindings });
  },

  removeBinding: (module: string, fieldName: string) => {
    const key = `${module}.${fieldName}`;
    const newBindings = new Map(get().bindings);
    newBindings.delete(key);
    set({ bindings: newBindings });
  },
}));

/**
 * Hook zum einfachen Zugriff auf eine Feld-Verknüpfung
 */
export function useFieldBinding(module: string, fieldName: string) {
  const binding = useFieldBindingsStore((state) => state.getBinding(module, fieldName));
  const hasBinding = useFieldBindingsStore((state) => state.hasBinding(module, fieldName));
  const isLoaded = useFieldBindingsStore((state) => state.isLoaded);
  
  return {
    binding,
    hasBinding,
    isLoaded,
  };
}
