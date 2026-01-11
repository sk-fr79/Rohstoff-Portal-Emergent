import { create } from 'zustand';
import { persist } from 'zustand/middleware';
import { usePermissionsStore } from './permissionsStore';

interface User {
  id: string;
  mandantId: string;
  benutzername: string;
  email: string;
  vorname?: string;
  nachname?: string;
  kuerzel?: string;
  istAdmin: boolean;
  profilbild?: string | null;
}

interface AuthState {
  user: User | null;
  accessToken: string | null;
  refreshToken: string | null;
  isAuthenticated: boolean;
  
  // Actions
  setAuth: (user: User, accessToken: string, refreshToken: string) => void;
  setTokens: (accessToken: string, refreshToken: string) => void;
  logout: () => void;
}

export const useAuthStore = create<AuthState>()(
  persist(
    (set) => ({
      user: null,
      accessToken: null,
      refreshToken: null,
      isAuthenticated: false,
      
      setAuth: (user, accessToken, refreshToken) => set({
        user,
        accessToken,
        refreshToken,
        isAuthenticated: true,
      }),
      
      setTokens: (accessToken, refreshToken) => set({
        accessToken,
        refreshToken,
      }),
      
      logout: () => {
        // Berechtigungen auch löschen
        usePermissionsStore.getState().clearPermissions();
        set({
          user: null,
          accessToken: null,
          refreshToken: null,
          isAuthenticated: false,
        });
      },
    }),
    {
      name: 'rohstoff-auth',
      partialize: (state) => ({
        user: state.user,
        accessToken: state.accessToken,
        refreshToken: state.refreshToken,
        isAuthenticated: state.isAuthenticated,
      }),
    }
  )
);
