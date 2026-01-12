import { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { 
  Hash, Plus, Pencil, Trash2, Save, X, Loader2, RefreshCw,
  FileText, Truck, Receipt, Package
} from 'lucide-react';
import { api } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Switch } from '@/components/ui/switch';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from '@/components/ui/dialog';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Badge } from '@/components/ui/badge';
import { cn } from '@/lib/utils';

interface Nummernkreis {
  id: string;
  modul: string;
  bezeichnung: string;
  feldname: string;
  prefix: string;
  suffix: string;
  jahr_im_prefix: boolean;
  startnummer: number;
  aktuelle_nummer: number;
  stellen: number;
  filter_typ?: string;
  aktiv: boolean;
  beschreibung?: string;
}

const MODULE_ICONS: Record<string, any> = {
  kontrakte: FileText,
  rechnungen: Receipt,
  fuhren: Truck,
  lieferscheine: Package,
};

const MODULE_LABELS: Record<string, string> = {
  kontrakte: 'Kontrakte',
  rechnungen: 'Rechnungen',
  fuhren: 'Fuhren / Wiegekarten',
  lieferscheine: 'Lieferscheine',
};

export function NummernkreisePage() {
  const queryClient = useQueryClient();
  const [showDialog, setShowDialog] = useState(false);
  const [editingItem, setEditingItem] = useState<Nummernkreis | null>(null);
  const [formData, setFormData] = useState<Partial<Nummernkreis>>({
    modul: 'kontrakte',
    bezeichnung: '',
    feldname: 'kontraktnummer',
    prefix: '',
    suffix: '',
    jahr_im_prefix: true,
    startnummer: 1,
    stellen: 4,
    filter_typ: '',
    aktiv: true,
    beschreibung: '',
  });

  const { data: nummernkreise, isLoading } = useQuery({
    queryKey: ['nummernkreise'],
    queryFn: async () => {
      const response = await api.get('/system/nummernkreise');
      return response.data.data as Nummernkreis[];
    },
  });

  const createMutation = useMutation({
    mutationFn: (data: Partial<Nummernkreis>) => api.post('/system/nummernkreise', data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['nummernkreise'] });
      toast.success('Nummernkreis erstellt');
      setShowDialog(false);
      resetForm();
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Erstellen');
    },
  });

  const updateMutation = useMutation({
    mutationFn: ({ id, data }: { id: string; data: Partial<Nummernkreis> }) => 
      api.put(`/system/nummernkreise/${id}`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['nummernkreise'] });
      toast.success('Nummernkreis aktualisiert');
      setShowDialog(false);
      resetForm();
    },
    onError: () => toast.error('Fehler beim Aktualisieren'),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: string) => api.delete(`/system/nummernkreise/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['nummernkreise'] });
      toast.success('Nummernkreis gelöscht');
    },
    onError: () => toast.error('Fehler beim Löschen'),
  });

  const initMutation = useMutation({
    mutationFn: () => api.post('/system/nummernkreise/initialisieren'),
    onSuccess: (response) => {
      queryClient.invalidateQueries({ queryKey: ['nummernkreise'] });
      toast.success(response.data.message);
    },
    onError: () => toast.error('Fehler bei der Initialisierung'),
  });

  const resetForm = () => {
    setFormData({
      modul: 'kontrakte',
      bezeichnung: '',
      feldname: 'kontraktnummer',
      prefix: '',
      suffix: '',
      jahr_im_prefix: true,
      startnummer: 1,
      stellen: 4,
      filter_typ: '',
      aktiv: true,
      beschreibung: '',
    });
    setEditingItem(null);
  };

  const handleEdit = (item: Nummernkreis) => {
    setEditingItem(item);
    setFormData(item);
    setShowDialog(true);
  };

  const handleSave = () => {
    if (!formData.bezeichnung?.trim()) {
      toast.error('Bezeichnung ist erforderlich');
      return;
    }

    if (editingItem) {
      updateMutation.mutate({ id: editingItem.id, data: formData });
    } else {
      createMutation.mutate(formData);
    }
  };

  // Vorschau der Nummer generieren
  const generatePreview = () => {
    const prefix = formData.prefix || '';
    const suffix = formData.suffix || '';
    const stellen = formData.stellen || 4;
    const nummer = (formData.aktuelle_nummer || 0) + 1;
    const jahr = formData.jahr_im_prefix ? new Date().getFullYear().toString() : '';
    
    return `${prefix}${jahr}${nummer.toString().padStart(stellen, '0')}${suffix}`;
  };

  // Gruppieren nach Modul
  const groupedNummernkreise = (nummernkreise || []).reduce((acc, nk) => {
    if (!acc[nk.modul]) acc[nk.modul] = [];
    acc[nk.modul].push(nk);
    return acc;
  }, {} as Record<string, Nummernkreis[]>);

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h2 className="text-xl font-semibold text-gray-900 flex items-center gap-2">
            <Hash className="h-5 w-5 text-emerald-600" />
            Nummernkreise
          </h2>
          <p className="text-sm text-gray-500 mt-1">
            Konfigurieren Sie die automatische Nummernvergabe für alle Module
          </p>
        </div>
        <div className="flex gap-2">
          <Button
            variant="outline"
            size="sm"
            onClick={() => initMutation.mutate()}
            disabled={initMutation.isPending}
          >
            <RefreshCw className={cn("h-4 w-4 mr-2", initMutation.isPending && "animate-spin")} />
            Standard-Kreise erstellen
          </Button>
          <Button
            size="sm"
            onClick={() => { resetForm(); setShowDialog(true); }}
            className="bg-emerald-600 hover:bg-emerald-700"
          >
            <Plus className="h-4 w-4 mr-2" />
            Neuer Nummernkreis
          </Button>
        </div>
      </div>

      {/* Info Box */}
      <div className="p-4 bg-blue-50 rounded-lg border border-blue-200">
        <p className="text-sm text-blue-800">
          <strong>Hinweis:</strong> Nummernkreise garantieren eindeutige, fortlaufende Nummern für Kontrakte, 
          Rechnungen, Fuhren und andere Belege. Ändern Sie die Einstellungen nur mit Bedacht, da dies 
          Auswirkungen auf die Belegverfolgung haben kann.
        </p>
      </div>

      {/* Liste */}
      {isLoading ? (
        <div className="flex items-center justify-center py-12">
          <Loader2 className="h-8 w-8 animate-spin text-emerald-600" />
        </div>
      ) : !nummernkreise || nummernkreise.length === 0 ? (
        <div className="text-center py-12 bg-gray-50 rounded-lg border-2 border-dashed border-gray-200">
          <Hash className="h-12 w-12 mx-auto text-gray-300 mb-3" />
          <h3 className="text-lg font-medium text-gray-900 mb-1">Keine Nummernkreise definiert</h3>
          <p className="text-gray-500 mb-4">Erstellen Sie Standard-Nummernkreise für alle Module</p>
          <Button onClick={() => initMutation.mutate()} disabled={initMutation.isPending}>
            <RefreshCw className={cn("h-4 w-4 mr-2", initMutation.isPending && "animate-spin")} />
            Standard-Kreise erstellen
          </Button>
        </div>
      ) : (
        <div className="space-y-6">
          {Object.entries(groupedNummernkreise).map(([modul, items]) => {
            const ModulIcon = MODULE_ICONS[modul] || Hash;
            const modulLabel = MODULE_LABELS[modul] || modul;
            
            return (
              <div key={modul} className="bg-white rounded-lg border shadow-sm overflow-hidden">
                <div className="px-4 py-3 bg-gray-50 border-b flex items-center gap-2">
                  <ModulIcon className="h-5 w-5 text-gray-600" />
                  <h3 className="font-semibold text-gray-900">{modulLabel}</h3>
                  <Badge variant="secondary" className="ml-2">{items.length}</Badge>
                </div>
                <div className="divide-y">
                  {items.map((nk) => (
                    <div key={nk.id} className="p-4 flex items-center justify-between hover:bg-gray-50">
                      <div className="flex-1">
                        <div className="flex items-center gap-3">
                          <span className="font-medium text-gray-900">{nk.bezeichnung}</span>
                          {nk.filter_typ && (
                            <Badge variant="outline" className="text-xs">{nk.filter_typ}</Badge>
                          )}
                          {!nk.aktiv && (
                            <Badge variant="secondary" className="text-xs">Inaktiv</Badge>
                          )}
                        </div>
                        <div className="text-sm text-gray-500 mt-1 flex items-center gap-4">
                          <span>
                            Format: <code className="bg-gray-100 px-1.5 py-0.5 rounded text-xs">
                              {nk.prefix}{nk.jahr_im_prefix ? 'JJJJ' : ''}{`${'0'.repeat(nk.stellen - 1)}1`}{nk.suffix}
                            </code>
                          </span>
                          <span>Aktuell bei: <strong>{nk.aktuelle_nummer}</strong></span>
                        </div>
                        {nk.beschreibung && (
                          <p className="text-xs text-gray-400 mt-1">{nk.beschreibung}</p>
                        )}
                      </div>
                      <div className="flex items-center gap-2">
                        <Button variant="ghost" size="icon" onClick={() => handleEdit(nk)}>
                          <Pencil className="h-4 w-4" />
                        </Button>
                        <Button 
                          variant="ghost" 
                          size="icon" 
                          className="text-red-600 hover:text-red-700 hover:bg-red-50"
                          onClick={() => {
                            if (confirm('Nummernkreis wirklich löschen?')) {
                              deleteMutation.mutate(nk.id);
                            }
                          }}
                        >
                          <Trash2 className="h-4 w-4" />
                        </Button>
                      </div>
                    </div>
                  ))}
                </div>
              </div>
            );
          })}
        </div>
      )}

      {/* Dialog */}
      <Dialog open={showDialog} onOpenChange={setShowDialog}>
        <DialogContent className="max-w-lg">
          <DialogHeader>
            <DialogTitle>
              {editingItem ? 'Nummernkreis bearbeiten' : 'Neuer Nummernkreis'}
            </DialogTitle>
          </DialogHeader>

          <div className="space-y-4 py-4">
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-1.5">
                <Label>Modul</Label>
                <Select
                  value={formData.modul}
                  onValueChange={(v) => setFormData({ ...formData, modul: v })}
                  disabled={!!editingItem}
                >
                  <SelectTrigger>
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="kontrakte">Kontrakte</SelectItem>
                    <SelectItem value="rechnungen">Rechnungen</SelectItem>
                    <SelectItem value="fuhren">Fuhren</SelectItem>
                    <SelectItem value="lieferscheine">Lieferscheine</SelectItem>
                  </SelectContent>
                </Select>
              </div>
              <div className="space-y-1.5">
                <Label>Filter-Typ (optional)</Label>
                <Input
                  value={formData.filter_typ || ''}
                  onChange={(e) => setFormData({ ...formData, filter_typ: e.target.value })}
                  placeholder="z.B. EK oder VK"
                />
              </div>
            </div>

            <div className="space-y-1.5">
              <Label>Bezeichnung *</Label>
              <Input
                value={formData.bezeichnung || ''}
                onChange={(e) => setFormData({ ...formData, bezeichnung: e.target.value })}
                placeholder="z.B. Einkaufskontrakte"
              />
            </div>

            <div className="grid grid-cols-3 gap-4">
              <div className="space-y-1.5">
                <Label>Prefix</Label>
                <Input
                  value={formData.prefix || ''}
                  onChange={(e) => setFormData({ ...formData, prefix: e.target.value.toUpperCase() })}
                  placeholder="EKK"
                />
              </div>
              <div className="space-y-1.5">
                <Label>Stellen</Label>
                <Input
                  type="number"
                  min="1"
                  max="10"
                  value={formData.stellen || 4}
                  onChange={(e) => setFormData({ ...formData, stellen: parseInt(e.target.value) })}
                />
              </div>
              <div className="space-y-1.5">
                <Label>Suffix</Label>
                <Input
                  value={formData.suffix || ''}
                  onChange={(e) => setFormData({ ...formData, suffix: e.target.value })}
                />
              </div>
            </div>

            <div className="flex items-center gap-6">
              <div className="flex items-center gap-2">
                <Switch
                  checked={formData.jahr_im_prefix || false}
                  onCheckedChange={(v) => setFormData({ ...formData, jahr_im_prefix: v })}
                />
                <Label className="text-sm">Jahr einbeziehen (z.B. 2025)</Label>
              </div>
              <div className="flex items-center gap-2">
                <Switch
                  checked={formData.aktiv !== false}
                  onCheckedChange={(v) => setFormData({ ...formData, aktiv: v })}
                />
                <Label className="text-sm">Aktiv</Label>
              </div>
            </div>

            <div className="space-y-1.5">
              <Label>Startnummer</Label>
              <Input
                type="number"
                min="1"
                value={formData.startnummer || 1}
                onChange={(e) => setFormData({ ...formData, startnummer: parseInt(e.target.value) })}
              />
            </div>

            <div className="space-y-1.5">
              <Label>Beschreibung</Label>
              <Input
                value={formData.beschreibung || ''}
                onChange={(e) => setFormData({ ...formData, beschreibung: e.target.value })}
                placeholder="Optionale Beschreibung..."
              />
            </div>

            {/* Vorschau */}
            <div className="p-4 bg-emerald-50 rounded-lg border border-emerald-200">
              <Label className="text-xs text-emerald-700">Vorschau der nächsten Nummer:</Label>
              <p className="text-2xl font-mono font-bold text-emerald-800 mt-1">
                {generatePreview()}
              </p>
            </div>
          </div>

          <DialogFooter>
            <Button variant="outline" onClick={() => { setShowDialog(false); resetForm(); }}>
              Abbrechen
            </Button>
            <Button 
              onClick={handleSave}
              disabled={createMutation.isPending || updateMutation.isPending}
            >
              {(createMutation.isPending || updateMutation.isPending) && (
                <Loader2 className="h-4 w-4 mr-2 animate-spin" />
              )}
              <Save className="h-4 w-4 mr-2" />
              Speichern
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
