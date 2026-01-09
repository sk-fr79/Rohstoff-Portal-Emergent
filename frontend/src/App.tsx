import { Routes, Route, Navigate } from 'react-router-dom';
import { Toaster } from 'sonner';
import { useAuthStore } from '@/store/authStore';
import { MainLayout } from '@/components/layout/MainLayout';
import { LoginPage } from '@/features/auth/LoginPage';
import { DashboardPage } from '@/features/dashboard/DashboardPage';
import { AdressenPage } from '@/features/adressen/AdressenPage';
import { ArtikelPage } from '@/features/artikel/ArtikelPage';
import { KontraktePage } from '@/features/kontrakte/KontraktePage';
import { WiegekartenPage } from '@/features/wiegekarten/WiegekartenPage';
import FuhrenPage from '@/features/fuhren/FuhrenPage';
import RechnungenPage from '@/features/rechnungen/RechnungenPage';
import KreditversicherungenPage from '@/features/kreditversicherungen/KreditversicherungenPage';

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
        
        {/* Geschützte Routen */}
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
