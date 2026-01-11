import { ReactNode, useEffect } from 'react';
import { Navigate, useLocation } from 'react-router-dom';
import { usePermissionsStore, ModuleKey } from '@/store/permissionsStore';
import { useAuthStore } from '@/store/authStore';
import { Shield, Loader2 } from 'lucide-react';

interface ProtectedModuleProps {
  children: ReactNode;
  modul: ModuleKey;
  requiredLevel?: 'read' | 'write' | 'full';
}

/**
 * Schützt ein Modul basierend auf Berechtigungen.
 * Zeigt eine "Kein Zugriff" Seite wenn der Benutzer keine Berechtigung hat.
 */
export function ProtectedModule({ children, modul, requiredLevel = 'read' }: ProtectedModuleProps) {
  const { isAuthenticated } = useAuthStore();
  const { 
    permissions, 
    istAdmin, 
    isLoading, 
    fetchPermissions,
    lastFetched,
    canRead,
    canWrite,
    hasFull,
    isDenied
  } = usePermissionsStore();
  const location = useLocation();

  // Berechtigungen laden wenn noch nicht vorhanden oder älter als 5 Minuten
  useEffect(() => {
    if (isAuthenticated && (!lastFetched || Date.now() - lastFetched > 5 * 60 * 1000)) {
      fetchPermissions();
    }
  }, [isAuthenticated, lastFetched, fetchPermissions]);

  // Nicht eingeloggt -> Login
  if (!isAuthenticated) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  // Laden - Warte auf Berechtigungen wenn sie noch nicht abgerufen wurden
  if (isLoading || !lastFetched) {
    return (
      <div className="flex items-center justify-center min-h-[400px]">
        <Loader2 className="h-8 w-8 animate-spin text-gray-400" />
      </div>
    );
  }

  // Admin hat immer Zugriff (prüfe auch authStore als Fallback)
  const { user } = useAuthStore.getState();
  if (istAdmin || user?.istAdmin) {
    return <>{children}</>;
  }

  // Berechtigung prüfen
  let hasPermission = false;
  
  if (isDenied(modul)) {
    hasPermission = false;
  } else if (requiredLevel === 'read') {
    hasPermission = canRead(modul);
  } else if (requiredLevel === 'write') {
    hasPermission = canWrite(modul);
  } else if (requiredLevel === 'full') {
    hasPermission = hasFull(modul);
  }

  // Kein Zugriff
  if (!hasPermission) {
    return (
      <div className="flex items-center justify-center min-h-[400px] p-6">
        <div className="text-center max-w-md">
          <div className="w-20 h-20 mx-auto mb-6 rounded-full bg-red-100 flex items-center justify-center">
            <Shield className="h-10 w-10 text-red-500" />
          </div>
          <h2 className="text-2xl font-bold text-gray-800 mb-2">Kein Zugriff</h2>
          <p className="text-gray-600 mb-6">
            Sie haben keine Berechtigung, auf dieses Modul zuzugreifen.
            Bitte wenden Sie sich an Ihren Administrator.
          </p>
          <div className="p-4 bg-gray-50 rounded-lg text-sm text-gray-500">
            <p><strong>Modul:</strong> {modul}</p>
            <p><strong>Erforderlich:</strong> {requiredLevel}</p>
            <p><strong>Ihre Berechtigung:</strong> {permissions[modul] || 'Keine'}</p>
          </div>
        </div>
      </div>
    );
  }

  return <>{children}</>;
}

/**
 * Hook für bedingte Anzeige von UI-Elementen basierend auf Berechtigungen
 */
export function useModuleAccess(modul: ModuleKey) {
  const { istAdmin, canRead, canWrite, hasFull, isDenied, getLevel } = usePermissionsStore();
  
  return {
    hasAccess: istAdmin || (canRead(modul) && !isDenied(modul)),
    canRead: istAdmin || canRead(modul),
    canWrite: istAdmin || canWrite(modul),
    hasFull: istAdmin || hasFull(modul),
    isDenied: !istAdmin && isDenied(modul),
    level: getLevel(modul),
    istAdmin,
  };
}
