import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import { api } from '@/services/api/client';

// Berechtigungsstufen
export type PermissionLevel = 'read' | 'write' | 'full' | 'denied' | null;

// Modulschlüssel müssen mit Backend übereinstimmen
export const MODULE_KEYS = [
  'dashboard',
  'adressen',
  'artikel',
  'kreditversicherungen',
  'kontrakte',
  'fuhren',
  'wiegekarten',
  'rechnungen',
  'berichte',
  'einstellungen',
  'admin',
] as const;

export type ModuleKey = typeof MODULE_KEYS[number];

interface PermissionsState {
  permissions: Record<string, PermissionLevel>;
  istAdmin: boolean;
  isLoading: boolean;
  lastFetched: number | null;
  
  // Actions
  fetchPermissions: () => Promise<void>;
  setPermissions: (permissions: Record<string, PermissionLevel>, istAdmin: boolean) => void;
  clearPermissions: () => void;
  
  // Helpers
  hasAccess: (modul: ModuleKey) => boolean;
  canRead: (modul: ModuleKey) => boolean;
  canWrite: (modul: ModuleKey) => boolean;
  hasFull: (modul: ModuleKey) => boolean;
  isDenied: (modul: ModuleKey) => boolean;
  getLevel: (modul: ModuleKey) => PermissionLevel;
}

export const usePermissionsStore = create<PermissionsState>()(
  persist(
    (set, get) => ({
      permissions: {},
      istAdmin: false,
      isLoading: false,
      lastFetched: null,
      
      fetchPermissions: async () => {
        set({ isLoading: true });
        try {
          const response = await api.get('/admin/meine-berechtigungen');
          if (response.data.success) {
            const { ist_admin, berechtigungen } = response.data.data;
            set({
              permissions: berechtigungen || {},
              istAdmin: ist_admin || false,
              lastFetched: Date.now(),
              isLoading: false,
            });
          }
        } catch (error) {
          console.error('Fehler beim Laden der Berechtigungen:', error);
          set({ isLoading: false });
        }
      },
      
      setPermissions: (permissions, istAdmin) => set({
        permissions,
        istAdmin,
        lastFetched: Date.now(),
      }),
      
      clearPermissions: () => set({
        permissions: {},
        istAdmin: false,
        lastFetched: null,
      }),
      
      // Admin hat immer Zugriff
      hasAccess: (modul) => {
        const state = get();
        if (state.istAdmin) return true;
        const level = state.permissions[modul];
        return level !== 'denied' && level !== null;
      },
      
      canRead: (modul) => {
        const state = get();
        if (state.istAdmin) return true;
        const level = state.permissions[modul];
        return level === 'read' || level === 'write' || level === 'full';
      },
      
      canWrite: (modul) => {
        const state = get();
        if (state.istAdmin) return true;
        const level = state.permissions[modul];
        return level === 'write' || level === 'full';
      },
      
      hasFull: (modul) => {
        const state = get();
        if (state.istAdmin) return true;
        return state.permissions[modul] === 'full';
      },
      
      isDenied: (modul) => {
        const state = get();
        if (state.istAdmin) return false;
        return state.permissions[modul] === 'denied';
      },
      
      getLevel: (modul) => {
        const state = get();
        if (state.istAdmin) return 'full';
        return state.permissions[modul] || null;
      },
    }),
    {
      name: 'rohstoff-permissions',
      partialize: (state) => ({
        permissions: state.permissions,
        istAdmin: state.istAdmin,
        lastFetched: state.lastFetched,
      }),
    }
  )
);

// Hook für einfache Verwendung
export function usePermission(modul: ModuleKey) {
  const store = usePermissionsStore();
  return {
    hasAccess: store.hasAccess(modul),
    canRead: store.canRead(modul),
    canWrite: store.canWrite(modul),
    hasFull: store.hasFull(modul),
    isDenied: store.isDenied(modul),
    level: store.getLevel(modul),
    istAdmin: store.istAdmin,
  };
}
