import { useEffect } from 'react';
import { Routes, Route, Navigate } from 'react-router-dom';
import { Toaster } from 'sonner';
import { useAuthStore } from '@/store/authStore';
import { useFieldBindingsStore } from '@/store/fieldBindingsStore';
import { MainLayout } from '@/components/layout/MainLayout';
import { SettingsLayout } from '@/components/layout/SettingsLayout';
import { ProtectedModule } from '@/components/auth/ProtectedModule';
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
  SystemeinstellungenPage,
  BenutzerPage,
  RollenPage,
  AbteilungenPage,
  BerechtigungenPage,
  ApisPage,
  NummernkreisePage,
  FirmeneinstellungenPage
} from '@/features/einstellungen';

// Protected Route Wrapper
function ProtectedRoute({ children }: { children: React.ReactNode }) {
  const { isAuthenticated, accessToken } = useAuthStore();
  const { loadBindings, isLoaded } = useFieldBindingsStore();
  
  // Lade Feld-Verknüpfungen beim Login
  useEffect(() => {
    if (isAuthenticated && accessToken && !isLoaded) {
      loadBindings(accessToken);
    }
  }, [isAuthenticated, accessToken, isLoaded, loadBindings]);
  
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
          <Route path="dashboard" element={
            <ProtectedModule modul="dashboard">
              <DashboardPage />
            </ProtectedModule>
          } />
          <Route path="adressen" element={
            <ProtectedModule modul="adressen">
              <AdressenPage />
            </ProtectedModule>
          } />
          <Route path="artikel" element={
            <ProtectedModule modul="artikel">
              <ArtikelPage />
            </ProtectedModule>
          } />
          <Route path="kontrakte" element={
            <ProtectedModule modul="kontrakte">
              <KontraktePage />
            </ProtectedModule>
          } />
          <Route path="wiegekarten" element={
            <ProtectedModule modul="wiegekarten">
              <WiegekartenPage />
            </ProtectedModule>
          } />
          <Route path="fuhren" element={
            <ProtectedModule modul="fuhren">
              <FuhrenPage />
            </ProtectedModule>
          } />
          <Route path="rechnungen" element={
            <ProtectedModule modul="rechnungen">
              <RechnungenPage />
            </ProtectedModule>
          } />
          <Route path="kreditversicherungen" element={
            <ProtectedModule modul="kreditversicherungen">
              <KreditversicherungenPage />
            </ProtectedModule>
          } />
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
          {/* Benutzer-Einstellungen - Jeder eingeloggte Benutzer */}
          <Route path="profil" element={
            <ProtectedModule modul="einstellungen">
              <ProfilPage />
            </ProtectedModule>
          } />
          <Route path="kontakt" element={
            <ProtectedModule modul="einstellungen">
              <ProfilPage />
            </ProtectedModule>
          } />
          <Route path="adresse" element={
            <ProtectedModule modul="einstellungen">
              <ProfilPage />
            </ProtectedModule>
          } />
          <Route path="passwort" element={
            <ProtectedModule modul="einstellungen">
              <PasswortPage />
            </ProtectedModule>
          } />
          <Route path="profilbild" element={
            <ProtectedModule modul="einstellungen">
              <ProfilPage />
            </ProtectedModule>
          } />
          <Route path="signatur" element={
            <ProtectedModule modul="einstellungen">
              <SignaturPage />
            </ProtectedModule>
          } />
          <Route path="unterschrift" element={
            <ProtectedModule modul="einstellungen">
              <UnterschriftPage />
            </ProtectedModule>
          } />
          {/* Admin-Einstellungen - Nur Admins (haben eigenen Admin-Check) */}
          <Route path="system" element={<SystemeinstellungenPage />} />
          <Route path="apis" element={<ApisPage />} />
          <Route path="benutzer" element={<BenutzerPage />} />
          <Route path="rollen" element={<RollenPage />} />
          <Route path="abteilungen" element={<AbteilungenPage />} />
          <Route path="berechtigungen" element={<BerechtigungenPage />} />
          <Route path="nummernkreise" element={<NummernkreisePage />} />
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
