import { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { Plus, Pencil, Trash2, MapPin, Star, Loader2, Phone, Check, Navigation } from 'lucide-react';
import { api } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Switch } from '@/components/ui/switch';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from '@/components/ui/dialog';
import { Textarea } from '@/components/ui/textarea';
import { Badge } from '@/components/ui/badge';
import { cn } from '@/lib/utils';

// Importiere die Flaggen-Komponente
import DE from 'country-flag-icons/react/3x2/DE';
import AT from 'country-flag-icons/react/3x2/AT';
import CH from 'country-flag-icons/react/3x2/CH';
import NL from 'country-flag-icons/react/3x2/NL';
import FR from 'country-flag-icons/react/3x2/FR';

const COUNTRY_ISO_MAP: Record<string, string> = {
  'Deutschland': 'DE', 'Österreich': 'AT', 'Schweiz': 'CH', 'Niederlande': 'NL', 'Frankreich': 'FR'
};

const FLAG_COMPONENTS: Record<string, React.FC<{ className?: string }>> = {
  DE, AT, CH, NL, FR
};

const CountryFlag = ({ country, className = "w-5 h-3" }: { country: string; className?: string }) => {
  const isoCode = COUNTRY_ISO_MAP[country];
  const FlagComponent = isoCode ? FLAG_COMPONENTS[isoCode] : null;
  if (!FlagComponent) return null;
  return <FlagComponent className={cn("inline-block rounded shadow-sm", className)} />;
};

const LAENDER = ['Deutschland', 'Österreich', 'Schweiz', 'Niederlande', 'Frankreich'];

interface Lieferadresse {
  id: string;
  bezeichnung: string;
  name1?: string;
  name2?: string;
  strasse?: string;
  hausnummer?: string;
  plz?: string;
  ort?: string;
  land?: string;
  latitude?: number;
  longitude?: number;
  ansprechpartner?: string;
  telefon?: string;
  bemerkungen?: string;
  aktiv: boolean;
  ist_standard: boolean;
}

interface LieferadressenTabProps {
  adresseId: string;
  isEditing: boolean;
}

export function LieferadressenTab({ adresseId, isEditing }: LieferadressenTabProps) {
  const queryClient = useQueryClient();
  const [showDialog, setShowDialog] = useState(false);
  const [editingLiefer, setEditingLiefer] = useState<Lieferadresse | null>(null);
  const [formData, setFormData] = useState<Partial<Lieferadresse>>({
    bezeichnung: '',
    strasse: '',
    plz: '',
    ort: '',
    land: 'Deutschland',
    aktiv: true,
    ist_standard: false,
  });

  const { data: lieferadressen, isLoading } = useQuery({
    queryKey: ['lieferadressen', adresseId],
    queryFn: async () => {
      const response = await api.get(`/adressen/${adresseId}/lieferadressen`);
      return response.data.data || [];
    },
    enabled: !!adresseId,
  });

  const addMutation = useMutation({
    mutationFn: async (data: Partial<Lieferadresse>) => {
      return api.post(`/adressen/${adresseId}/lieferadressen`, data);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['lieferadressen', adresseId] });
      toast.success('Lieferadresse hinzugefügt');
      setShowDialog(false);
      resetForm();
    },
    onError: () => {
      toast.error('Fehler beim Hinzufügen');
    },
  });

  const updateMutation = useMutation({
    mutationFn: async ({ id, data }: { id: string; data: Partial<Lieferadresse> }) => {
      return api.put(`/adressen/${adresseId}/lieferadressen/${id}`, data);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['lieferadressen', adresseId] });
      toast.success('Lieferadresse aktualisiert');
      setShowDialog(false);
      resetForm();
    },
    onError: () => {
      toast.error('Fehler beim Aktualisieren');
    },
  });

  const deleteMutation = useMutation({
    mutationFn: async (id: string) => {
      return api.delete(`/adressen/${adresseId}/lieferadressen/${id}`);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['lieferadressen', adresseId] });
      toast.success('Lieferadresse gelöscht');
    },
    onError: () => {
      toast.error('Fehler beim Löschen');
    },
  });

  const resetForm = () => {
    setFormData({
      bezeichnung: '',
      strasse: '',
      plz: '',
      ort: '',
      land: 'Deutschland',
      aktiv: true,
      ist_standard: false,
    });
    setEditingLiefer(null);
  };

  const handleEdit = (liefer: Lieferadresse) => {
    setEditingLiefer(liefer);
    setFormData(liefer);
    setShowDialog(true);
  };

  const handleSubmit = () => {
    if (!formData.bezeichnung) {
      toast.error('Bezeichnung ist ein Pflichtfeld');
      return;
    }

    if (editingLiefer) {
      updateMutation.mutate({ id: editingLiefer.id, data: formData });
    } else {
      addMutation.mutate(formData);
    }
  };

  if (isLoading) {
    return (
      <div className="flex items-center justify-center py-8">
        <Loader2 className="w-6 h-6 animate-spin text-gray-400" />
      </div>
    );
  }

  return (
    <div className="space-y-4">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div className="flex items-center gap-2">
          <MapPin className="w-5 h-5 text-green-600" />
          <h3 className="font-medium text-gray-900">Lieferadressen</h3>
          <Badge variant="secondary" className="ml-2">
            {lieferadressen?.length || 0}
          </Badge>
        </div>
        {isEditing && (
          <Button 
            size="sm" 
            onClick={() => { resetForm(); setShowDialog(true); }}
            className="bg-green-600 hover:bg-green-700"
          >
            <Plus className="w-4 h-4 mr-1" />
            Neu
          </Button>
        )}
      </div>

      {/* Liste */}
      {lieferadressen?.length === 0 ? (
        <div className="text-center py-8 text-gray-500 bg-gray-50 rounded-lg border-2 border-dashed">
          <MapPin className="w-10 h-10 mx-auto mb-2 text-gray-300" />
          <p>Keine Lieferadressen vorhanden</p>
          {isEditing && (
            <Button 
              variant="link" 
              onClick={() => { resetForm(); setShowDialog(true); }}
              className="mt-2"
            >
              Erste Lieferadresse hinzufügen
            </Button>
          )}
        </div>
      ) : (
        <div className="space-y-3">
          {lieferadressen?.map((liefer: Lieferadresse) => (
            <div 
              key={liefer.id}
              className={cn(
                "p-4 rounded-lg border bg-white hover:shadow-sm transition-shadow",
                liefer.ist_standard && "border-green-200 bg-green-50/50",
                !liefer.aktiv && "opacity-60"
              )}
            >
              <div className="flex items-start justify-between">
                <div className="flex-1">
                  <div className="flex items-center gap-2 mb-1">
                    {liefer.ist_standard && (
                      <Badge className="bg-green-600 text-xs">
                        <Star className="w-3 h-3 mr-1" />
                        Standard
                      </Badge>
                    )}
                    {!liefer.aktiv && (
                      <Badge variant="secondary" className="text-xs">Inaktiv</Badge>
                    )}
                  </div>
                  <p className="font-medium text-gray-900">{liefer.bezeichnung}</p>
                  <div className="text-sm text-gray-600 mt-1">
                    {liefer.name1 && <p>{liefer.name1}</p>}
                    <p>
                      {liefer.strasse} {liefer.hausnummer}
                    </p>
                    <p className="flex items-center gap-1">
                      {liefer.land && <CountryFlag country={liefer.land} />}
                      {liefer.plz} {liefer.ort}
                    </p>
                  </div>
                  {liefer.telefon && (
                    <p className="text-sm text-gray-500 mt-1 flex items-center gap-1">
                      <Phone className="w-3 h-3" />
                      {liefer.telefon}
                    </p>
                  )}
                  {liefer.latitude && liefer.longitude && (
                    <p className="text-xs text-gray-400 mt-1 flex items-center gap-1">
                      <Navigation className="w-3 h-3" />
                      {liefer.latitude.toFixed(4)}, {liefer.longitude.toFixed(4)}
                    </p>
                  )}
                </div>
                {isEditing && (
                  <div className="flex gap-1">
                    <Button
                      variant="ghost"
                      size="icon"
                      onClick={() => handleEdit(liefer)}
                      className="h-8 w-8"
                    >
                      <Pencil className="w-4 h-4" />
                    </Button>
                    <Button
                      variant="ghost"
                      size="icon"
                      onClick={() => deleteMutation.mutate(liefer.id)}
                      className="h-8 w-8 text-red-600 hover:text-red-700 hover:bg-red-50"
                    >
                      <Trash2 className="w-4 h-4" />
                    </Button>
                  </div>
                )}
              </div>
            </div>
          ))}
        </div>
      )}

      {/* Dialog */}
      <Dialog open={showDialog} onOpenChange={setShowDialog}>
        <DialogContent className="sm:max-w-lg max-h-[90vh] overflow-y-auto">
          <DialogHeader>
            <DialogTitle>
              {editingLiefer ? 'Lieferadresse bearbeiten' : 'Neue Lieferadresse'}
            </DialogTitle>
          </DialogHeader>

          <div className="space-y-4 py-4">
            <div className="grid gap-4">
              <div>
                <Label className="text-sm font-medium">Bezeichnung *</Label>
                <Input
                  value={formData.bezeichnung || ''}
                  onChange={(e) => setFormData({ ...formData, bezeichnung: e.target.value })}
                  placeholder="z.B. Lager Süd, Werk 2"
                />
              </div>

              <div>
                <Label className="text-sm font-medium">Name/Firma</Label>
                <Input
                  value={formData.name1 || ''}
                  onChange={(e) => setFormData({ ...formData, name1: e.target.value })}
                  placeholder="Firmenname"
                />
              </div>

              <div className="grid grid-cols-3 gap-2">
                <div className="col-span-2">
                  <Label className="text-sm font-medium">Straße</Label>
                  <Input
                    value={formData.strasse || ''}
                    onChange={(e) => setFormData({ ...formData, strasse: e.target.value })}
                  />
                </div>
                <div>
                  <Label className="text-sm font-medium">Nr.</Label>
                  <Input
                    value={formData.hausnummer || ''}
                    onChange={(e) => setFormData({ ...formData, hausnummer: e.target.value })}
                  />
                </div>
              </div>

              <div className="grid grid-cols-3 gap-2">
                <div>
                  <Label className="text-sm font-medium">PLZ</Label>
                  <Input
                    value={formData.plz || ''}
                    onChange={(e) => setFormData({ ...formData, plz: e.target.value })}
                  />
                </div>
                <div className="col-span-2">
                  <Label className="text-sm font-medium">Ort</Label>
                  <Input
                    value={formData.ort || ''}
                    onChange={(e) => setFormData({ ...formData, ort: e.target.value })}
                  />
                </div>
              </div>

              <div>
                <Label className="text-sm font-medium">Land</Label>
                <Select
                  value={formData.land || 'Deutschland'}
                  onValueChange={(v) => setFormData({ ...formData, land: v })}
                >
                  <SelectTrigger>
                    <div className="flex items-center gap-2">
                      {formData.land && <CountryFlag country={formData.land} />}
                      <span>{formData.land || 'Deutschland'}</span>
                    </div>
                  </SelectTrigger>
                  <SelectContent>
                    {LAENDER.map(l => (
                      <SelectItem key={l} value={l}>
                        <div className="flex items-center gap-2">
                          <CountryFlag country={l} />
                          <span>{l}</span>
                        </div>
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>

              <div className="grid grid-cols-2 gap-4">
                <div>
                  <Label className="text-sm font-medium">Ansprechpartner</Label>
                  <Input
                    value={formData.ansprechpartner || ''}
                    onChange={(e) => setFormData({ ...formData, ansprechpartner: e.target.value })}
                  />
                </div>
                <div>
                  <Label className="text-sm font-medium">Telefon</Label>
                  <Input
                    value={formData.telefon || ''}
                    onChange={(e) => setFormData({ ...formData, telefon: e.target.value })}
                  />
                </div>
              </div>

              <div>
                <Label className="text-sm font-medium">Bemerkungen</Label>
                <Textarea
                  value={formData.bemerkungen || ''}
                  onChange={(e) => setFormData({ ...formData, bemerkungen: e.target.value })}
                  rows={2}
                />
              </div>

              <div className="flex items-center justify-between py-2">
                <div className="flex items-center gap-2">
                  <Star className="w-4 h-4 text-amber-500" />
                  <Label className="text-sm">Standard-Lieferadresse</Label>
                </div>
                <Switch
                  checked={formData.ist_standard || false}
                  onCheckedChange={(v) => setFormData({ ...formData, ist_standard: v })}
                />
              </div>

              <div className="flex items-center justify-between py-2">
                <div className="flex items-center gap-2">
                  <Check className="w-4 h-4 text-green-500" />
                  <Label className="text-sm">Aktiv</Label>
                </div>
                <Switch
                  checked={formData.aktiv !== false}
                  onCheckedChange={(v) => setFormData({ ...formData, aktiv: v })}
                />
              </div>
            </div>
          </div>

          <DialogFooter>
            <Button variant="outline" onClick={() => setShowDialog(false)}>
              Abbrechen
            </Button>
            <Button 
              onClick={handleSubmit}
              disabled={addMutation.isPending || updateMutation.isPending}
            >
              {(addMutation.isPending || updateMutation.isPending) && (
                <Loader2 className="w-4 h-4 mr-2 animate-spin" />
              )}
              {editingLiefer ? 'Speichern' : 'Hinzufügen'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
