import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { motion } from 'framer-motion';
import { toast } from 'sonner';
import { Building2, Loader2, Eye, EyeOff } from 'lucide-react';
import { useAuthStore } from '@/store/authStore';
import { usePermissionsStore } from '@/store/permissionsStore';
import { authApi } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';

const loginSchema = z.object({
  benutzername: z.string().min(1, 'Benutzername erforderlich'),
  passwort: z.string().min(1, 'Passwort erforderlich'),
});

type LoginForm = z.infer<typeof loginSchema>;

export function LoginPage() {
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);
  const navigate = useNavigate();
  const { setAuth } = useAuthStore();
  const { fetchPermissions } = usePermissionsStore();

  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<LoginForm>({
    resolver: zodResolver(loginSchema),
  });

  const onSubmit = async (data: LoginForm) => {
    setIsLoading(true);
    try {
      const response = await authApi.login(data.benutzername, data.passwort);
      
      if (response.data.success) {
        // Map snake_case from backend to camelCase for frontend
        const user = response.data.user;
        const mappedUser = {
          id: user.id,
          mandantId: user.mandant_id,
          benutzername: user.benutzername,
          email: user.email,
          vorname: user.vorname,
          nachname: user.nachname,
          kuerzel: user.kuerzel,
          istAdmin: user.ist_admin,
          profilbild: user.profilbild || null,
        };
        
        setAuth(
          mappedUser,
          response.data.access_token,
          response.data.refresh_token || ''
        );
        
        // Berechtigungen nach Login abrufen
        await fetchPermissions();
        
        toast.success('Erfolgreich angemeldet');
        navigate('/dashboard');
      }
    } catch (error: unknown) {
      const err = error as { response?: { data?: { error?: string } } };
      toast.error(err.response?.data?.error || 'Anmeldung fehlgeschlagen');
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <div className="min-h-screen flex bg-gray-50">
      {/* Left Side - Branding */}
      <div className="hidden lg:flex lg:w-1/2 bg-gradient-to-br from-slate-800 to-slate-900 p-12 flex-col justify-between">
        <div>
          <div className="flex items-center gap-3">
            <img 
              src="/mv_logo.png" 
              alt="MV Logo" 
              className="h-12 w-auto object-contain"
            />
            <span className="font-bold text-xl text-white">Rohstoff Portal</span>
          </div>
        </div>
        
        <div className="space-y-6">
          <h1 className="text-4xl font-bold text-white leading-tight">
            Ihr modernes ERP-System für die Rohstoff-Branche
          </h1>
          <p className="text-lg text-gray-300">
            Verwalten Sie Adressen, Kontrakte, Fuhren und Rechnungen effizient in einer zentralen Plattform.
          </p>
          <div className="flex gap-8 pt-4">
            <div>
              <div className="text-3xl font-bold text-emerald-400">500+</div>
              <div className="text-sm text-gray-400">Aktive Nutzer</div>
            </div>
            <div>
              <div className="text-3xl font-bold text-emerald-400">99.9%</div>
              <div className="text-sm text-gray-400">Verfügbarkeit</div>
            </div>
            <div>
              <div className="text-3xl font-bold text-emerald-400">24/7</div>
              <div className="text-sm text-gray-400">Support</div>
            </div>
          </div>
        </div>

        <div className="text-sm text-gray-500">
          &copy; {new Date().getFullYear()} Rohstoff Portal. Alle Rechte vorbehalten.
        </div>
      </div>

      {/* Right Side - Login Form */}
      <div className="flex-1 flex items-center justify-center p-8">
        <motion.div
          initial={{ opacity: 0, x: 20 }}
          animate={{ opacity: 1, x: 0 }}
          transition={{ duration: 0.5 }}
          className="w-full max-w-md"
        >
          {/* Mobile Logo */}
          <div className="lg:hidden flex items-center gap-3 mb-8 justify-center">
            <div className="h-10 w-10 rounded-lg bg-emerald-500 flex items-center justify-center">
              <Building2 className="h-6 w-6 text-white" />
            </div>
            <span className="font-bold text-xl text-gray-900">Rohstoff ERP</span>
          </div>

          <div className="text-center mb-8">
            <h2 className="text-2xl font-bold text-gray-900">Willkommen zurück</h2>
            <p className="text-gray-500 mt-2">Melden Sie sich an, um fortzufahren</p>
          </div>

          <form onSubmit={handleSubmit(onSubmit)} className="space-y-5">
            <div className="space-y-2">
              <Label htmlFor="benutzername" className="text-gray-700">Benutzername</Label>
              <Input
                id="benutzername"
                placeholder="Benutzername oder E-Mail"
                {...register('benutzername')}
                className={`h-11 bg-white border-gray-200 ${errors.benutzername ? 'border-red-500' : ''}`}
                data-testid="login-username"
              />
              {errors.benutzername && (
                <p className="text-sm text-red-500">{errors.benutzername.message}</p>
              )}
            </div>

            <div className="space-y-2">
              <Label htmlFor="passwort" className="text-gray-700">Passwort</Label>
              <div className="relative">
                <Input
                  id="passwort"
                  type={showPassword ? 'text' : 'password'}
                  placeholder="••••••••"
                  {...register('passwort')}
                  className={`h-11 bg-white border-gray-200 pr-10 ${errors.passwort ? 'border-red-500' : ''}`}
                  data-testid="login-password"
                />
                <Button
                  type="button"
                  variant="ghost"
                  size="icon"
                  className="absolute right-0 top-0 h-full px-3 hover:bg-transparent text-gray-400"
                  onClick={() => setShowPassword(!showPassword)}
                >
                  {showPassword ? (
                    <EyeOff className="h-4 w-4" />
                  ) : (
                    <Eye className="h-4 w-4" />
                  )}
                </Button>
              </div>
              {errors.passwort && (
                <p className="text-sm text-red-500">{errors.passwort.message}</p>
              )}
            </div>

            <div className="flex items-center justify-between">
              <label className="flex items-center gap-2 cursor-pointer">
                <input type="checkbox" className="rounded border-gray-300 text-emerald-500 focus:ring-emerald-500" />
                <span className="text-sm text-gray-600">Angemeldet bleiben</span>
              </label>
              <a href="#" className="text-sm text-emerald-600 hover:text-emerald-700 font-medium">
                Passwort vergessen?
              </a>
            </div>

            <Button
              type="submit"
              className="w-full h-11 bg-emerald-500 hover:bg-emerald-600 text-white font-medium"
              disabled={isLoading}
              data-testid="login-submit"
            >
              {isLoading ? (
                <>
                  <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                  Anmelden...
                </>
              ) : (
                'Anmelden'
              )}
            </Button>
          </form>

          <p className="text-center text-sm text-gray-500 mt-8 lg:hidden">
            &copy; {new Date().getFullYear()} Rohstoff Portal. Alle Rechte vorbehalten.
          </p>
        </motion.div>
      </div>
    </div>
  );
}
