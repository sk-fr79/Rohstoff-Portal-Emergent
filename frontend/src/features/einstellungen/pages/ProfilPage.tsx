import { useState } from 'react';
import { useAuthStore } from '@/store/authStore';
import { toast } from 'sonner';
import { 
  User, Mail, Phone, MapPin, Building2, Camera, Save, Loader2 
} from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';

export function ProfilPage() {
  const { user } = useAuthStore();
  const [isLoading, setIsLoading] = useState(false);
  const [formData, setFormData] = useState({
    vorname: user?.vorname || '',
    nachname: user?.nachname || '',
    email: user?.email || '',
    telefon: '',
    mobil: '',
    position: '',
    abteilung: '',
  });

  const handleSave = async () => {
    setIsLoading(true);
    // Simuliere Speicherung
    await new Promise(resolve => setTimeout(resolve, 1000));
    setIsLoading(false);
    toast.success('Profil gespeichert');
  };

  return (
    <div className="p-6 max-w-4xl mx-auto" data-testid="profil-page">
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-gray-900">Mein Profil</h1>
        <p className="text-gray-500">Verwalten Sie Ihre persönlichen Informationen</p>
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
              <div className="h-24 w-24 rounded-full bg-blue-100 flex items-center justify-center">
                <span className="text-3xl font-bold text-blue-600">
                  {user?.kuerzel || user?.benutzername?.charAt(0).toUpperCase() || 'A'}
                </span>
              </div>
              <div className="space-y-2">
                <Button variant="outline" size="sm">
                  <Camera className="h-4 w-4 mr-2" />
                  Bild ändern
                </Button>
                <p className="text-xs text-gray-500">JPG, PNG oder GIF. Max. 2MB</p>
              </div>
            </div>
          </CardContent>
        </Card>

        {/* Persönliche Daten */}
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
                  value={formData.vorname}
                  onChange={(e) => setFormData({ ...formData, vorname: e.target.value })}
                />
              </div>
              <div>
                <Label htmlFor="nachname">Nachname</Label>
                <Input
                  id="nachname"
                  value={formData.nachname}
                  onChange={(e) => setFormData({ ...formData, nachname: e.target.value })}
                />
              </div>
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="position">Position</Label>
                <Input
                  id="position"
                  value={formData.position}
                  onChange={(e) => setFormData({ ...formData, position: e.target.value })}
                  placeholder="z.B. Sachbearbeiter"
                />
              </div>
              <div>
                <Label htmlFor="abteilung">Abteilung</Label>
                <Input
                  id="abteilung"
                  value={formData.abteilung}
                  onChange={(e) => setFormData({ ...formData, abteilung: e.target.value })}
                  placeholder="z.B. Einkauf"
                />
              </div>
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
                value={formData.email}
                onChange={(e) => setFormData({ ...formData, email: e.target.value })}
              />
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label htmlFor="telefon">Telefon</Label>
                <Input
                  id="telefon"
                  value={formData.telefon}
                  onChange={(e) => setFormData({ ...formData, telefon: e.target.value })}
                  placeholder="+49 123 456789"
                />
              </div>
              <div>
                <Label htmlFor="mobil">Mobil</Label>
                <Input
                  id="mobil"
                  value={formData.mobil}
                  onChange={(e) => setFormData({ ...formData, mobil: e.target.value })}
                  placeholder="+49 170 1234567"
                />
              </div>
            </div>
          </CardContent>
        </Card>

        {/* Speichern Button */}
        <div className="flex justify-end">
          <Button onClick={handleSave} disabled={isLoading}>
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
