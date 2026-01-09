import { Routes, Route, Navigate } from 'react-router-dom';
import { Toaster } from 'sonner';
import { useAuthStore } from '@/store/authStore';
import { MainLayout } from '@/components/layout/MainLayout';
import { SettingsLayout } from '@/components/layout/SettingsLayout';
import { LoginPage } from '@/features/auth/LoginPage';
import { DashboardPage } from '@/features/dashboard/DashboardPage';
import { AdressenPage } from '@/features/adressen/AdressenPage';
import { ArtikelPage } from '@/features/artikel/ArtikelPage';
import { KontraktePage } from '@/features/kontrakte/KontraktePage';
import { WiegekartenPage } from '@/features/wiegekarten/WiegekartenPage';
import FuhrenPage from '@/features/fuhren/FuhrenPage';
import RechnungenPage from '@/features/rechnungen/RechnungenPage';
import KreditversicherungenPage from '@/features/kreditversicherungen/KreditversicherungenPage';
import { 
  ProfilPage, 
  PasswortPage, 
  SignaturPage, 
  UnterschriftPage,
  SystemeinstellungenPage 
} from '@/features/einstellungen';

// Protected Route Wrapper
function ProtectedRoute({ children }: { children: React.ReactNode }) {
  const { isAuthenticated } = useAuthStore();
  
  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }
  
  return <>{children}</>;
}

export default function App() {
  return (
    <>
      <Routes>
        {/* Öffentliche Routen */}
        <Route path="/login" element={<LoginPage />} />
        
        {/* ERP Hauptbereich - Geschützte Routen */}
        <Route
          path="/"
          element={
            <ProtectedRoute>
              <MainLayout />
            </ProtectedRoute>
          }
        >
          <Route index element={<Navigate to="/dashboard" replace />} />
          <Route path="dashboard" element={<DashboardPage />} />
          <Route path="adressen" element={<AdressenPage />} />
          <Route path="artikel" element={<ArtikelPage />} />
          <Route path="kontrakte" element={<KontraktePage />} />
          <Route path="wiegekarten" element={<WiegekartenPage />} />
          <Route path="fuhren" element={<FuhrenPage />} />
          <Route path="rechnungen" element={<RechnungenPage />} />
          <Route path="kreditversicherungen" element={<KreditversicherungenPage />} />
        </Route>

        {/* Einstellungen-Bereich - Geschützte Routen */}
        <Route
          path="/einstellungen"
          element={
            <ProtectedRoute>
              <SettingsLayout />
            </ProtectedRoute>
          }
        >
          <Route index element={<Navigate to="/einstellungen/profil" replace />} />
          {/* Benutzer-Einstellungen */}
          <Route path="profil" element={<ProfilPage />} />
          <Route path="kontakt" element={<ProfilPage />} />
          <Route path="adresse" element={<ProfilPage />} />
          <Route path="passwort" element={<PasswortPage />} />
          <Route path="profilbild" element={<ProfilPage />} />
          <Route path="signatur" element={<SignaturPage />} />
          <Route path="unterschrift" element={<UnterschriftPage />} />
          {/* Admin-Einstellungen */}
          <Route path="system" element={<SystemeinstellungenPage />} />
          <Route path="benutzer" element={<SystemeinstellungenPage />} />
          <Route path="mandanten" element={<SystemeinstellungenPage />} />
          <Route path="sicherheit" element={<SystemeinstellungenPage />} />
          <Route path="datenbank" element={<SystemeinstellungenPage />} />
        </Route>
        
        {/* Fallback */}
        <Route path="*" element={<Navigate to="/" replace />} />
      </Routes>
      
      {/* Toast Notifications */}
      <Toaster 
        position="top-right" 
        richColors 
        closeButton
        toastOptions={{
          duration: 4000,
        }}
      />
    </>
  );
}
