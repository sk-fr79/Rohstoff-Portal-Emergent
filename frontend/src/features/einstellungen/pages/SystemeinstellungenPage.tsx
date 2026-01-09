import { useState } from 'react';
import { useAuthStore } from '@/store/authStore';
import { toast } from 'sonner';
import { 
  Cog, Save, Loader2, Building2, Globe, Clock, Database, 
  Shield, Mail, FileText, Palette
} from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Switch } from '@/components/ui/switch';
import { 
  Select, SelectContent, SelectItem, SelectTrigger, SelectValue 
} from '@/components/ui/select';

export function SystemeinstellungenPage() {
  const { user } = useAuthStore();
  const [isLoading, setIsLoading] = useState(false);
  const [settings, setSettings] = useState({
    firmenname: 'MV Rohstoff Portal GmbH',
    waehrung: 'EUR',
    sprache: 'de',
    zeitzone: 'Europe/Berlin',
    datumsformat: 'DD.MM.YYYY',
    emailHost: 'smtp.example.com',
    emailPort: '587',
    backupEnabled: true,
    backupInterval: 'daily',
    darkMode: false,
  });

  // Nur Admins haben Zugriff
  if (!user?.istAdmin) {
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
    await new Promise(resolve => setTimeout(resolve, 1000));
    setIsLoading(false);
    toast.success('Systemeinstellungen gespeichert');
  };

  return (
    <div className="p-6 max-w-4xl mx-auto" data-testid="systemeinstellungen-page">
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-gray-900">Systemeinstellungen</h1>
        <p className="text-gray-500">Konfigurieren Sie die globalen Systemparameter</p>
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
                  <SelectTrigger>
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
                  <SelectTrigger>
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
                  <SelectTrigger>
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="Europe/Berlin">Europe/Berlin (CET)</SelectItem>
                    <SelectItem value="Europe/London">Europe/London (GMT)</SelectItem>
                    <SelectItem value="Europe/Paris">Europe/Paris (CET)</SelectItem>
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
                  <SelectTrigger>
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
                <Label htmlFor="emailHost">SMTP Host</Label>
                <Input
                  id="emailHost"
                  value={settings.emailHost}
                  onChange={(e) => setSettings({ ...settings, emailHost: e.target.value })}
                />
              </div>
              <div>
                <Label htmlFor="emailPort">Port</Label>
                <Input
                  id="emailPort"
                  value={settings.emailPort}
                  onChange={(e) => setSettings({ ...settings, emailPort: e.target.value })}
                />
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
                checked={settings.backupEnabled} 
                onCheckedChange={(v) => setSettings({ ...settings, backupEnabled: v })}
              />
            </div>
            {settings.backupEnabled && (
              <div>
                <Label>Backup-Intervall</Label>
                <Select 
                  value={settings.backupInterval} 
                  onValueChange={(v) => setSettings({ ...settings, backupInterval: v })}
                >
                  <SelectTrigger className="w-48">
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
                <Label>Dark Mode</Label>
                <p className="text-sm text-gray-500">Dunkles Farbschema verwenden</p>
              </div>
              <Switch 
                checked={settings.darkMode} 
                onCheckedChange={(v) => setSettings({ ...settings, darkMode: v })}
              />
            </div>
          </CardContent>
        </Card>

        {/* Speichern */}
        <div className="flex justify-end">
          <Button onClick={handleSave} disabled={isLoading}>
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
