import { useState, useEffect } from 'react';
import { useAuthStore } from '@/store/authStore';
import { toast } from 'sonner';
import { 
  Cog, Save, Loader2, Building2, Globe, Database, 
  Shield, Mail, Palette, RefreshCw
} from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Switch } from '@/components/ui/switch';
import { 
  Select, SelectContent, SelectItem, SelectTrigger, SelectValue 
} from '@/components/ui/select';

const API_URL = import.meta.env.VITE_API_URL || '/api';

interface SystemSettings {
  firmenname: string;
  waehrung: string;
  sprache: string;
  zeitzone: string;
  datumsformat: string;
  email_host: string;
  email_port: number;
  email_user: string;
  email_ssl: boolean;
  backup_enabled: boolean;
  backup_interval: string;
  dark_mode_default: boolean;
}

export function SystemeinstellungenPage() {
  const { user, token } = useAuthStore();
  const [isLoading, setIsLoading] = useState(false);
  const [isFetching, setIsFetching] = useState(true);
  const [settings, setSettings] = useState<SystemSettings>({
    firmenname: 'MV Rohstoff Portal GmbH',
    waehrung: 'EUR',
    sprache: 'de',
    zeitzone: 'Europe/Berlin',
    datumsformat: 'DD.MM.YYYY',
    email_host: '',
    email_port: 587,
    email_user: '',
    email_ssl: true,
    backup_enabled: true,
    backup_interval: 'daily',
    dark_mode_default: false,
  });

  // Einstellungen vom Backend laden
  const fetchSettings = async () => {
    setIsFetching(true);
    try {
      const res = await fetch(`${API_URL}/admin/systemeinstellungen`, {
        headers: { 'Authorization': `Bearer ${token}` }
      });
      const data = await res.json();
      
      if (data.success && data.data) {
        setSettings(data.data);
      }
    } catch (error) {
      console.error('Fehler beim Laden der Einstellungen:', error);
      toast.error('Fehler beim Laden der Einstellungen');
    } finally {
      setIsFetching(false);
    }
  };

  useEffect(() => {
    if (user?.ist_admin) {
      fetchSettings();
    } else {
      setIsFetching(false);
    }
  }, [user?.ist_admin]);

  // Nur Admins haben Zugriff (prüfe authStore direkt)
  if (!user?.ist_admin) {
    return (
      <div className="p-6 flex items-center justify-center min-h-[400px]">
        <div className="text-center">
          <Shield className="h-16 w-16 mx-auto text-gray-300 mb-4" />
          <h2 className="text-xl font-semibold text-gray-700">Kein Zugriff</h2>
          <p className="text-gray-500 mt-2">
            Sie benötigen Administrator-Rechte, um diese Seite anzuzeigen.
          </p>
        </div>
      </div>
    );
  }

  const handleSave = async () => {
    setIsLoading(true);
    try {
      const res = await fetch(`${API_URL}/admin/systemeinstellungen`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(settings)
      });
      
      const data = await res.json();
      
      if (data.success) {
        toast.success('Systemeinstellungen gespeichert');
      } else {
        toast.error(data.detail || 'Fehler beim Speichern');
      }
    } catch (error) {
      console.error('Fehler:', error);
      toast.error('Fehler beim Speichern der Einstellungen');
    } finally {
      setIsLoading(false);
    }
  };

  if (isFetching) {
    return (
      <div className="p-6 flex items-center justify-center min-h-[400px]">
        <Loader2 className="h-8 w-8 animate-spin text-blue-600" />
      </div>
    );
  }

  return (
    <div className="p-6 max-w-4xl mx-auto" data-testid="systemeinstellungen-page">
      <div className="mb-6 flex justify-between items-center">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">Systemeinstellungen</h1>
          <p className="text-gray-500">Konfigurieren Sie die globalen Systemparameter</p>
        </div>
        <Button variant="outline" size="sm" onClick={fetchSettings} disabled={isFetching}>
          <RefreshCw className={`h-4 w-4 mr-2 ${isFetching ? 'animate-spin' : ''}`} />
          Aktualisieren
        </Button>
      </div>

      <div className="grid gap-6">
        {/* Firmeneinstellungen */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <Building2 className="h-5 w-5" />
              Firmeneinstellungen
            </CardTitle>
          </CardHeader>
          <CardContent className="grid gap-4">
            <div>
              <Label htmlFor="firmenname">Firmenname</Label>
              <Input
                id="firmenname"
                data-testid="settings-firmenname"
                value={settings.firmenname}
                onChange={(e) => setSettings({ ...settings, firmenname: e.target.value })}
              />
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="waehrung">Standardwährung</Label>
                <Select 
                  value={settings.waehrung} 
                  onValueChange={(v) => setSettings({ ...settings, waehrung: v })}
                >
                  <SelectTrigger data-testid="settings-waehrung">
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="EUR">EUR - Euro</SelectItem>
                    <SelectItem value="USD">USD - US Dollar</SelectItem>
                    <SelectItem value="CHF">CHF - Schweizer Franken</SelectItem>
                    <SelectItem value="GBP">GBP - Britisches Pfund</SelectItem>
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="sprache">Sprache</Label>
                <Select 
                  value={settings.sprache} 
                  onValueChange={(v) => setSettings({ ...settings, sprache: v })}
                >
                  <SelectTrigger data-testid="settings-sprache">
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="de">Deutsch</SelectItem>
                    <SelectItem value="en">English</SelectItem>
                    <SelectItem value="fr">Français</SelectItem>
                  </SelectContent>
                </Select>
              </div>
            </div>
          </CardContent>
        </Card>

        {/* Regionale Einstellungen */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <Globe className="h-5 w-5" />
              Regionale Einstellungen
            </CardTitle>
          </CardHeader>
          <CardContent className="grid gap-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="zeitzone">Zeitzone</Label>
                <Select 
                  value={settings.zeitzone} 
                  onValueChange={(v) => setSettings({ ...settings, zeitzone: v })}
                >
                  <SelectTrigger data-testid="settings-zeitzone">
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="Europe/Berlin">Europe/Berlin (CET)</SelectItem>
                    <SelectItem value="Europe/London">Europe/London (GMT)</SelectItem>
                    <SelectItem value="Europe/Paris">Europe/Paris (CET)</SelectItem>
                    <SelectItem value="Europe/Zurich">Europe/Zurich (CET)</SelectItem>
                    <SelectItem value="America/New_York">America/New_York (EST)</SelectItem>
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="datumsformat">Datumsformat</Label>
                <Select 
                  value={settings.datumsformat} 
                  onValueChange={(v) => setSettings({ ...settings, datumsformat: v })}
                >
                  <SelectTrigger data-testid="settings-datumsformat">
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="DD.MM.YYYY">DD.MM.YYYY (31.12.2024)</SelectItem>
                    <SelectItem value="MM/DD/YYYY">MM/DD/YYYY (12/31/2024)</SelectItem>
                    <SelectItem value="YYYY-MM-DD">YYYY-MM-DD (2024-12-31)</SelectItem>
                  </SelectContent>
                </Select>
              </div>
            </div>
          </CardContent>
        </Card>

        {/* E-Mail Einstellungen */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <Mail className="h-5 w-5" />
              E-Mail Server
            </CardTitle>
            <CardDescription>
              Konfiguration für ausgehende E-Mails
            </CardDescription>
          </CardHeader>
          <CardContent className="grid gap-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="email_host">SMTP Host</Label>
                <Input
                  id="email_host"
                  data-testid="settings-email-host"
                  value={settings.email_host}
                  onChange={(e) => setSettings({ ...settings, email_host: e.target.value })}
                  placeholder="smtp.example.com"
                />
              </div>
              <div>
                <Label htmlFor="email_port">Port</Label>
                <Input
                  id="email_port"
                  data-testid="settings-email-port"
                  type="number"
                  value={settings.email_port}
                  onChange={(e) => setSettings({ ...settings, email_port: parseInt(e.target.value) || 587 })}
                />
              </div>
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="email_user">Benutzername</Label>
                <Input
                  id="email_user"
                  data-testid="settings-email-user"
                  value={settings.email_user}
                  onChange={(e) => setSettings({ ...settings, email_user: e.target.value })}
                  placeholder="noreply@example.com"
                />
              </div>
              <div className="flex items-center gap-4 pt-6">
                <Switch 
                  id="email_ssl"
                  checked={settings.email_ssl} 
                  onCheckedChange={(v) => setSettings({ ...settings, email_ssl: v })}
                />
                <Label htmlFor="email_ssl">SSL/TLS verwenden</Label>
              </div>
            </div>
          </CardContent>
        </Card>

        {/* Backup Einstellungen */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <Database className="h-5 w-5" />
              Backup & Datensicherung
            </CardTitle>
          </CardHeader>
          <CardContent className="space-y-4">
            <div className="flex items-center justify-between">
              <div>
                <Label>Automatische Backups</Label>
                <p className="text-sm text-gray-500">Regelmäßige Sicherung der Datenbank</p>
              </div>
              <Switch 
                checked={settings.backup_enabled} 
                onCheckedChange={(v) => setSettings({ ...settings, backup_enabled: v })}
              />
            </div>
            {settings.backup_enabled && (
              <div>
                <Label>Backup-Intervall</Label>
                <Select 
                  value={settings.backup_interval} 
                  onValueChange={(v) => setSettings({ ...settings, backup_interval: v })}
                >
                  <SelectTrigger className="w-48" data-testid="settings-backup-interval">
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="hourly">Stündlich</SelectItem>
                    <SelectItem value="daily">Täglich</SelectItem>
                    <SelectItem value="weekly">Wöchentlich</SelectItem>
                  </SelectContent>
                </Select>
              </div>
            )}
          </CardContent>
        </Card>

        {/* Design */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <Palette className="h-5 w-5" />
              Design
            </CardTitle>
          </CardHeader>
          <CardContent>
            <div className="flex items-center justify-between">
              <div>
                <Label>Dark Mode als Standard</Label>
                <p className="text-sm text-gray-500">Dunkles Farbschema für neue Benutzer</p>
              </div>
              <Switch 
                checked={settings.dark_mode_default} 
                onCheckedChange={(v) => setSettings({ ...settings, dark_mode_default: v })}
              />
            </div>
          </CardContent>
        </Card>

        {/* Speichern */}
        <div className="flex justify-end">
          <Button onClick={handleSave} disabled={isLoading} data-testid="settings-save-btn">
            {isLoading ? (
              <Loader2 className="h-4 w-4 mr-2 animate-spin" />
            ) : (
              <Save className="h-4 w-4 mr-2" />
            )}
            Einstellungen speichern
          </Button>
        </div>
      </div>
    </div>
  );
}
