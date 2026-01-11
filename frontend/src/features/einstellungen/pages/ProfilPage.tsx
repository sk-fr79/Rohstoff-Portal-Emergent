import { useState, useEffect } from 'react';
import { useAuthStore } from '@/store/authStore';
import { toast } from 'sonner';
import { 
  User, Mail, Phone, Camera, Save, Loader2, RefreshCw
} from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';

const API_URL = import.meta.env.VITE_API_URL || '/api';

interface ProfilData {
  id: string;
  benutzername: string;
  email: string;
  vorname: string;
  nachname: string;
  kuerzel: string;
  telefon: string;
  mobil: string;
  position: string;
  email_signatur: string;
  profilbild: string | null;
  ist_admin: boolean;
  rolle: { id: string; name: string } | null;
  abteilungen: { id: string; name: string }[];
}

export function ProfilPage() {
  const { user, token } = useAuthStore();
  const [isLoading, setIsLoading] = useState(false);
  const [isFetching, setIsFetching] = useState(true);
  const [profilData, setProfilData] = useState<ProfilData | null>(null);
  const [formData, setFormData] = useState({
    vorname: '',
    nachname: '',
    email: '',
    telefon: '',
    mobil: '',
    position: '',
    email_signatur: '',
  });

  // Profil vom Backend laden
  const fetchProfil = async () => {
    setIsFetching(true);
    try {
      const res = await fetch(`${API_URL}/profil`, {
        headers: { 'Authorization': `Bearer ${token}` }
      });
      const data = await res.json();
      
      if (data.success && data.data) {
        setProfilData(data.data);
        setFormData({
          vorname: data.data.vorname || '',
          nachname: data.data.nachname || '',
          email: data.data.email || '',
          telefon: data.data.telefon || '',
          mobil: data.data.mobil || '',
          position: data.data.position || '',
          email_signatur: data.data.email_signatur || '',
        });
      }
    } catch (error) {
      console.error('Fehler beim Laden des Profils:', error);
      toast.error('Fehler beim Laden des Profils');
    } finally {
      setIsFetching(false);
    }
  };

  useEffect(() => {
    fetchProfil();
  }, []);

  const handleSave = async () => {
    setIsLoading(true);
    try {
      const res = await fetch(`${API_URL}/profil`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        },
        body: JSON.stringify(formData)
      });
      
      const data = await res.json();
      
      if (data.success) {
        toast.success('Profil gespeichert');
        fetchProfil(); // Neu laden
      } else {
        toast.error(data.detail || 'Fehler beim Speichern');
      }
    } catch (error) {
      console.error('Fehler beim Speichern:', error);
      toast.error('Fehler beim Speichern des Profils');
    } finally {
      setIsLoading(false);
    }
  };

  const handleImageUpload = async (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0];
    if (!file) return;
    
    // Größe prüfen (max 2MB)
    if (file.size > 2 * 1024 * 1024) {
      toast.error('Bild darf maximal 2MB groß sein');
      return;
    }
    
    // Als Base64 konvertieren
    const reader = new FileReader();
    reader.onload = async (e) => {
      const base64 = e.target?.result as string;
      
      try {
        const res = await fetch(`${API_URL}/profil/profilbild`, {
          method: 'PUT',
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}`
          },
          body: JSON.stringify({ profilbild: base64 })
        });
        
        const data = await res.json();
        
        if (data.success) {
          toast.success('Profilbild aktualisiert');
          fetchProfil();
        } else {
          toast.error(data.detail || 'Fehler beim Hochladen');
        }
      } catch (error) {
        toast.error('Fehler beim Hochladen des Profilbilds');
      }
    };
    reader.readAsDataURL(file);
  };

  if (isFetching) {
    return (
      <div className="p-6 flex items-center justify-center min-h-[400px]">
        <Loader2 className="h-8 w-8 animate-spin text-blue-600" />
      </div>
    );
  }

  return (
    <div className="p-6 max-w-4xl mx-auto" data-testid="profil-page">
      <div className="mb-6 flex justify-between items-center">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">Mein Profil</h1>
          <p className="text-gray-500">Verwalten Sie Ihre persönlichen Informationen</p>
        </div>
        <Button variant="outline" size="sm" onClick={fetchProfil} disabled={isFetching}>
          <RefreshCw className={`h-4 w-4 mr-2 ${isFetching ? 'animate-spin' : ''}`} />
          Aktualisieren
        </Button>
      </div>

      <div className="grid gap-6">
        {/* Profilbild Card */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <Camera className="h-5 w-5" />
              Profilbild
            </CardTitle>
            <CardDescription>
              Ihr Profilbild wird in der Anwendung angezeigt
            </CardDescription>
          </CardHeader>
          <CardContent>
            <div className="flex items-center gap-6">
              <div className="h-24 w-24 rounded-full bg-blue-100 flex items-center justify-center overflow-hidden">
                {profilData?.profilbild ? (
                  <img 
                    src={profilData.profilbild} 
                    alt="Profilbild" 
                    className="h-full w-full object-cover"
                  />
                ) : (
                  <span className="text-3xl font-bold text-blue-600">
                    {profilData?.kuerzel || profilData?.benutzername?.charAt(0).toUpperCase() || 'A'}
                  </span>
                )}
              </div>
              <div className="space-y-2">
                <label htmlFor="profilbild-upload">
                  <Button variant="outline" size="sm" asChild>
                    <span className="cursor-pointer">
                      <Camera className="h-4 w-4 mr-2" />
                      Bild ändern
                    </span>
                  </Button>
                </label>
                <input
                  id="profilbild-upload"
                  type="file"
                  accept="image/jpeg,image/png,image/gif"
                  className="hidden"
                  onChange={handleImageUpload}
                />
                <p className="text-xs text-gray-500">JPG, PNG oder GIF. Max. 2MB</p>
              </div>
            </div>
          </CardContent>
        </Card>

        {/* Account-Info (nur Anzeige) */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <User className="h-5 w-5" />
              Account-Informationen
            </CardTitle>
            <CardDescription>Diese Daten können nur vom Administrator geändert werden</CardDescription>
          </CardHeader>
          <CardContent className="grid gap-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label className="text-gray-500">Benutzername</Label>
                <p className="font-medium">{profilData?.benutzername}</p>
              </div>
              <div>
                <Label className="text-gray-500">Kürzel</Label>
                <p className="font-medium">{profilData?.kuerzel || '-'}</p>
              </div>
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label className="text-gray-500">Rolle</Label>
                <p className="font-medium">{profilData?.rolle?.name || '-'}</p>
              </div>
              <div>
                <Label className="text-gray-500">Abteilungen</Label>
                <p className="font-medium">
                  {profilData?.abteilungen?.map(a => a.name).join(', ') || '-'}
                </p>
              </div>
            </div>
            {profilData?.ist_admin && (
              <div className="mt-2">
                <span className="px-2 py-1 bg-red-100 text-red-700 text-xs font-medium rounded">
                  Administrator
                </span>
              </div>
            )}
          </CardContent>
        </Card>

        {/* Persönliche Daten (bearbeitbar) */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <User className="h-5 w-5" />
              Persönliche Daten
            </CardTitle>
          </CardHeader>
          <CardContent className="grid gap-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="vorname">Vorname</Label>
                <Input
                  id="vorname"
                  data-testid="profil-vorname"
                  value={formData.vorname}
                  onChange={(e) => setFormData({ ...formData, vorname: e.target.value })}
                />
              </div>
              <div>
                <Label htmlFor="nachname">Nachname</Label>
                <Input
                  id="nachname"
                  data-testid="profil-nachname"
                  value={formData.nachname}
                  onChange={(e) => setFormData({ ...formData, nachname: e.target.value })}
                />
              </div>
            </div>
            <div>
              <Label htmlFor="position">Position</Label>
              <Input
                id="position"
                data-testid="profil-position"
                value={formData.position}
                onChange={(e) => setFormData({ ...formData, position: e.target.value })}
                placeholder="z.B. Sachbearbeiter Einkauf"
              />
            </div>
          </CardContent>
        </Card>

        {/* Kontaktdaten */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <Mail className="h-5 w-5" />
              Kontaktdaten
            </CardTitle>
          </CardHeader>
          <CardContent className="grid gap-4">
            <div>
              <Label htmlFor="email">E-Mail</Label>
              <Input
                id="email"
                type="email"
                data-testid="profil-email"
                value={formData.email}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
              />
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="telefon">Telefon</Label>
                <Input
                  id="telefon"
                  data-testid="profil-telefon"
                  value={formData.telefon}
                  onChange={(e) => setFormData({ ...formData, telefon: e.target.value })}
                  placeholder="+49 123 456789"
                />
              </div>
              <div>
                <Label htmlFor="mobil">Mobil</Label>
                <Input
                  id="mobil"
                  data-testid="profil-mobil"
                  value={formData.mobil}
                  onChange={(e) => setFormData({ ...formData, mobil: e.target.value })}
                  placeholder="+49 170 1234567"
                />
              </div>
            </div>
          </CardContent>
        </Card>

        {/* E-Mail Signatur */}
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <Mail className="h-5 w-5" />
              E-Mail Signatur
            </CardTitle>
            <CardDescription>
              Wird automatisch an ausgehende E-Mails angehängt
            </CardDescription>
          </CardHeader>
          <CardContent>
            <Textarea
              id="email_signatur"
              data-testid="profil-signatur"
              value={formData.email_signatur}
              onChange={(e) => setFormData({ ...formData, email_signatur: e.target.value })}
              placeholder="Mit freundlichen Grüßen&#10;Max Mustermann&#10;Tel: +49 123 456789"
              rows={5}
            />
          </CardContent>
        </Card>

        {/* Speichern Button */}
        <div className="flex justify-end">
          <Button onClick={handleSave} disabled={isLoading} data-testid="profil-save-btn">
            {isLoading ? (
              <Loader2 className="h-4 w-4 mr-2 animate-spin" />
            ) : (
              <Save className="h-4 w-4 mr-2" />
            )}
            Speichern
          </Button>
        </div>
      </div>
    </div>
  );
}
