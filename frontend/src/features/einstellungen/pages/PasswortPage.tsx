import { useState } from 'react';
import { useAuthStore } from '@/store/authStore';
import { toast } from 'sonner';
import { Lock, Eye, EyeOff, Save, Loader2, AlertTriangle, CheckCircle } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { cn } from '@/lib/utils';

const API_URL = import.meta.env.VITE_API_URL || '/api';

export function PasswortPage() {
  const { accessToken: token } = useAuthStore();
  const [isLoading, setIsLoading] = useState(false);
  const [showPasswords, setShowPasswords] = useState({
    current: false,
    new: false,
    confirm: false,
  });
  const [formData, setFormData] = useState({
    currentPassword: '',
    newPassword: '',
    confirmPassword: '',
  });

  const passwordStrength = (password: string) => {
    let strength = 0;
    if (password.length >= 8) strength++;
    if (/[A-Z]/.test(password)) strength++;
    if (/[a-z]/.test(password)) strength++;
    if (/[0-9]/.test(password)) strength++;
    if (/[^A-Za-z0-9]/.test(password)) strength++;
    return strength;
  };

  const getStrengthLabel = (strength: number) => {
    if (strength <= 1) return { label: 'Schwach', color: 'bg-red-500' };
    if (strength <= 2) return { label: 'Mäßig', color: 'bg-orange-500' };
    if (strength <= 3) return { label: 'Gut', color: 'bg-yellow-500' };
    if (strength <= 4) return { label: 'Stark', color: 'bg-green-500' };
    return { label: 'Sehr stark', color: 'bg-emerald-500' };
  };

  const handleSave = async () => {
    // Client-side Validierung
    if (formData.newPassword !== formData.confirmPassword) {
      toast.error('Passwörter stimmen nicht überein');
      return;
    }
    if (passwordStrength(formData.newPassword) < 3) {
      toast.error('Passwort ist zu schwach');
      return;
    }
    if (!formData.currentPassword) {
      toast.error('Bitte aktuelles Passwort eingeben');
      return;
    }
    
    setIsLoading(true);
    try {
      const res = await fetch(`${API_URL}/profil/passwort`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify({
          aktuelles_passwort: formData.currentPassword,
          neues_passwort: formData.newPassword
        })
      });
      
      const data = await res.json();
      
      if (data.success) {
        toast.success('Passwort erfolgreich geändert');
        setFormData({ currentPassword: '', newPassword: '', confirmPassword: '' });
      } else {
        toast.error(data.detail || 'Fehler beim Ändern des Passworts');
      }
    } catch (error) {
      console.error('Fehler:', error);
      toast.error('Fehler beim Ändern des Passworts');
    } finally {
      setIsLoading(false);
    }
  };

  const strength = passwordStrength(formData.newPassword);
  const strengthInfo = getStrengthLabel(strength);

  return (
    <div className="p-6 max-w-2xl mx-auto" data-testid="passwort-page">
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-gray-900">Passwort ändern</h1>
        <p className="text-gray-500">Aktualisieren Sie Ihr Passwort regelmäßig</p>
      </div>

      <Card>
        <CardHeader>
          <CardTitle className="flex items-center gap-2">
            <Lock className="h-5 w-5" />
            Neues Passwort setzen
          </CardTitle>
          <CardDescription>
            Wählen Sie ein sicheres Passwort mit mindestens 8 Zeichen
          </CardDescription>
        </CardHeader>
        <CardContent className="space-y-4">
          {/* Aktuelles Passwort */}
          <div>
            <Label htmlFor="current">Aktuelles Passwort</Label>
            <div className="relative">
              <Input
                id="current"
                data-testid="current-password"
                type={showPasswords.current ? 'text' : 'password'}
                value={formData.currentPassword}
                onChange={(e) => setFormData({ ...formData, currentPassword: e.target.value })}
              />
              <Button
                type="button"
                variant="ghost"
                size="icon"
                className="absolute right-2 top-1/2 -translate-y-1/2"
                onClick={() => setShowPasswords({ ...showPasswords, current: !showPasswords.current })}
              >
                {showPasswords.current ? <EyeOff className="h-4 w-4" /> : <Eye className="h-4 w-4" />}
              </Button>
            </div>
          </div>

          {/* Neues Passwort */}
          <div>
            <Label htmlFor="new">Neues Passwort</Label>
            <div className="relative">
              <Input
                id="new"
                data-testid="new-password"
                type={showPasswords.new ? 'text' : 'password'}
                value={formData.newPassword}
                onChange={(e) => setFormData({ ...formData, newPassword: e.target.value })}
              />
              <Button
                type="button"
                variant="ghost"
                size="icon"
                className="absolute right-2 top-1/2 -translate-y-1/2"
                onClick={() => setShowPasswords({ ...showPasswords, new: !showPasswords.new })}
              >
                {showPasswords.new ? <EyeOff className="h-4 w-4" /> : <Eye className="h-4 w-4" />}
              </Button>
            </div>
            
            {/* Password Strength Indicator */}
            {formData.newPassword && (
              <div className="mt-2 space-y-2">
                <div className="flex gap-1">
                  {[1, 2, 3, 4, 5].map((i) => (
                    <div
                      key={i}
                      className={cn(
                        "h-1 flex-1 rounded-full transition-colors",
                        i <= strength ? strengthInfo.color : "bg-gray-200"
                      )}
                    />
                  ))}
                </div>
                <p className={cn(
                  "text-xs font-medium",
                  strength <= 2 ? "text-red-600" : strength <= 3 ? "text-yellow-600" : "text-green-600"
                )}>
                  Passwortstärke: {strengthInfo.label}
                </p>
              </div>
            )}
          </div>

          {/* Passwort bestätigen */}
          <div>
            <Label htmlFor="confirm">Passwort bestätigen</Label>
            <div className="relative">
              <Input
                id="confirm"
                data-testid="confirm-password"
                type={showPasswords.confirm ? 'text' : 'password'}
                value={formData.confirmPassword}
                onChange={(e) => setFormData({ ...formData, confirmPassword: e.target.value })}
              />
              <Button
                type="button"
                variant="ghost"
                size="icon"
                className="absolute right-2 top-1/2 -translate-y-1/2"
                onClick={() => setShowPasswords({ ...showPasswords, confirm: !showPasswords.confirm })}
              >
                {showPasswords.confirm ? <EyeOff className="h-4 w-4" /> : <Eye className="h-4 w-4" />}
              </Button>
            </div>
            {formData.confirmPassword && formData.newPassword !== formData.confirmPassword && (
              <p className="text-xs text-red-600 mt-1 flex items-center gap-1">
                <AlertTriangle className="h-3 w-3" />
                Passwörter stimmen nicht überein
              </p>
            )}
            {formData.confirmPassword && formData.newPassword === formData.confirmPassword && (
              <p className="text-xs text-green-600 mt-1 flex items-center gap-1">
                <CheckCircle className="h-3 w-3" />
                Passwörter stimmen überein
              </p>
            )}
          </div>

          {/* Anforderungen */}
          <div className="p-4 bg-gray-50 rounded-lg text-sm text-gray-600">
            <p className="font-medium mb-2">Passwort-Anforderungen:</p>
            <ul className="space-y-1">
              <li className={cn(formData.newPassword.length >= 8 && "text-green-600")}>
                • Mindestens 8 Zeichen
              </li>
              <li className={cn(/[A-Z]/.test(formData.newPassword) && "text-green-600")}>
                • Mindestens ein Großbuchstabe
              </li>
              <li className={cn(/[a-z]/.test(formData.newPassword) && "text-green-600")}>
                • Mindestens ein Kleinbuchstabe
              </li>
              <li className={cn(/[0-9]/.test(formData.newPassword) && "text-green-600")}>
                • Mindestens eine Zahl
              </li>
              <li className={cn(/[^A-Za-z0-9]/.test(formData.newPassword) && "text-green-600")}>
                • Mindestens ein Sonderzeichen
              </li>
            </ul>
          </div>

          <div className="flex justify-end pt-4">
            <Button 
              onClick={handleSave} 
              disabled={isLoading || !formData.currentPassword || !formData.newPassword || !formData.confirmPassword}
              data-testid="change-password-btn"
            >
              {isLoading ? (
                <Loader2 className="h-4 w-4 mr-2 animate-spin" />
              ) : (
                <Save className="h-4 w-4 mr-2" />
              )}
              Passwort ändern
            </Button>
          </div>
        </CardContent>
      </Card>
    </div>
  );
}
