import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { motion } from 'framer-motion';
import { toast } from 'sonner';
import { Package, Loader2, Eye, EyeOff } from 'lucide-react';
import { useAuthStore } from '@/store/authStore';
import { authApi } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';

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
        setAuth(
          response.data.user,
          response.data.accessToken,
          response.data.refreshToken
        );
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
    <div className="min-h-screen flex items-center justify-center p-4 bg-gradient-to-br from-background via-background to-primary/10">
      {/* Background Effects */}
      <div className="absolute inset-0 overflow-hidden pointer-events-none">
        <div className="absolute -top-40 -right-40 w-80 h-80 bg-primary/20 rounded-full blur-3xl" />
        <div className="absolute -bottom-40 -left-40 w-80 h-80 bg-primary/10 rounded-full blur-3xl" />
      </div>

      <motion.div
        initial={{ opacity: 0, y: 20 }}
        animate={{ opacity: 1, y: 0 }}
        transition={{ duration: 0.5 }}
        className="w-full max-w-md relative z-10"
      >
        <Card className="glass border-border/50">
          <CardHeader className="text-center">
            <motion.div
              initial={{ scale: 0 }}
              animate={{ scale: 1 }}
              transition={{ delay: 0.2, type: 'spring', stiffness: 200 }}
              className="mx-auto mb-4 h-16 w-16 rounded-2xl bg-primary flex items-center justify-center"
            >
              <Package className="h-8 w-8 text-primary-foreground" />
            </motion.div>
            <CardTitle className="text-2xl font-bold">Rohstoff Portal</CardTitle>
            <CardDescription>Melden Sie sich an, um fortzufahren</CardDescription>
          </CardHeader>
          <CardContent>
            <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
              <div className="space-y-2">
                <Label htmlFor="benutzername">Benutzername</Label>
                <Input
                  id="benutzername"
                  placeholder="Benutzername oder E-Mail"
                  {...register('benutzername')}
                  className={errors.benutzername ? 'border-destructive' : ''}
                  data-testid="login-username"
                />
                {errors.benutzername && (
                  <p className="text-sm text-destructive">{errors.benutzername.message}</p>
                )}
              </div>

              <div className="space-y-2">
                <Label htmlFor="passwort">Passwort</Label>
                <div className="relative">
                  <Input
                    id="passwort"
                    type={showPassword ? 'text' : 'password'}
                    placeholder="••••••••"
                    {...register('passwort')}
                    className={errors.passwort ? 'border-destructive pr-10' : 'pr-10'}
                    data-testid="login-password"
                  />
                  <Button
                    type="button"
                    variant="ghost"
                    size="icon"
                    className="absolute right-0 top-0 h-full px-3 hover:bg-transparent"
                    onClick={() => setShowPassword(!showPassword)}
                  >
                    {showPassword ? (
                      <EyeOff className="h-4 w-4 text-muted-foreground" />
                    ) : (
                      <Eye className="h-4 w-4 text-muted-foreground" />
                    )}
                  </Button>
                </div>
                {errors.passwort && (
                  <p className="text-sm text-destructive">{errors.passwort.message}</p>
                )}
              </div>

              <Button
                type="submit"
                className="w-full"
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
          </CardContent>
        </Card>

        <p className="text-center text-sm text-muted-foreground mt-6">
          &copy; {new Date().getFullYear()} Rohstoff Portal. Alle Rechte vorbehalten.
        </p>
      </motion.div>
    </div>
  );
}
