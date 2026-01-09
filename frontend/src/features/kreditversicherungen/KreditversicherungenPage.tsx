import { useState, useMemo } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion, AnimatePresence } from 'framer-motion';
import { toast } from 'sonner';
import { 
  Plus, Pencil, Trash2, Shield, Building2, Users, Euro, Calendar,
  X, Save, Loader2, Search, ChevronRight, CheckCircle, AlertTriangle,
  ExternalLink, FileText
} from 'lucide-react';
import { api } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { DataTable } from '@/components/ui/data-table';
import { Switch } from '@/components/ui/switch';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from '@/components/ui/dialog';
import { Badge } from '@/components/ui/badge';
import { Textarea } from '@/components/ui/textarea';
import { cn } from '@/lib/utils';

// Types
interface VersicherteAdresse {
  id: string;
  adresse_id: string;
  adresse_name?: string;
  adresse_ort?: string;
  adresse_kdnr?: string;
}

interface KVPosition {
  id: string;
  gueltig_ab: string;
  gueltig_bis?: string;
  betrag: number;
  betrag_anfrage?: number;
  waehrung: string;
  aktiv: boolean;
  bemerkungen?: string;
}

interface Kreditversicherung {
  id: string;
  versicherungsnummer?: string;
  versicherer_id?: string;
  versicherer_name?: string;
  fakturierungsfrist?: number;
  aktiv: boolean;
  bemerkungen?: string;
  versicherte_adressen: VersicherteAdresse[];
  positionen: KVPosition[];
  created_at?: string;
}

const formatCurrency = (value: number): string => {
  return new Intl.NumberFormat('de-DE', {
    style: 'currency',
    currency: 'EUR'
  }).format(value);
};

const formatDate = (dateStr?: string): string => {
  if (!dateStr) return '-';
  try {
    return new Date(dateStr).toLocaleDateString('de-DE');
  } catch {
    return dateStr;
  }
};

export default function KreditversicherungenPage() {
  const queryClient = useQueryClient();
  const [selectedKV, setSelectedKV] = useState<Kreditversicherung | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [isNewRecord, setIsNewRecord] = useState(false);
  const [showAddresseDialog, setShowAddresseDialog] = useState(false);
  const [showPositionDialog, setShowPositionDialog] = useState(false);
  const [addressSearchQuery, setAddressSearchQuery] = useState('');
  const [editingPosition, setEditingPosition] = useState<KVPosition | null>(null);

  // Form state
  const [formData, setFormData] = useState({
    versicherungsnummer: '',
    versicherer_name: '',
    fakturierungsfrist: 0,
    aktiv: true,
    bemerkungen: '',
  });

  const [positionForm, setPositionForm] = useState({
    gueltig_ab: new Date().toISOString().split('T')[0],
    gueltig_bis: '',
    betrag: 0,
    betrag_anfrage: 0,
    aktiv: true,
    bemerkungen: '',
  });

  // Queries
  const { data: kreditversicherungen, isLoading } = useQuery({
    queryKey: ['kreditversicherungen'],
    queryFn: async () => {
      const response = await api.get('/kreditversicherungen');
      return response.data.data || [];
    },
  });

  const { data: addressResults } = useQuery({
    queryKey: ['adressen-search', addressSearchQuery],
    queryFn: async () => {
      if (!addressSearchQuery || addressSearchQuery.length < 2) return [];
      const response = await api.get(`/adressen?search=${encodeURIComponent(addressSearchQuery)}&limit=10`);
      return response.data.data || [];
    },
    enabled: addressSearchQuery.length >= 2,
  });

  // Mutations
  const createMutation = useMutation({
    mutationFn: (data: any) => api.post('/kreditversicherungen', data),
    onSuccess: (response) => {
      queryClient.invalidateQueries({ queryKey: ['kreditversicherungen'] });
      toast.success('Kreditversicherung erstellt');
      setSelectedKV(response.data.data);
      setIsNewRecord(false);
      setIsEditing(false);
    },
    onError: () => toast.error('Fehler beim Erstellen'),
  });

  const updateMutation = useMutation({
    mutationFn: ({ id, data }: { id: string; data: any }) => 
      api.put(`/kreditversicherungen/${id}`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kreditversicherungen'] });
      toast.success('Kreditversicherung aktualisiert');
      setIsEditing(false);
    },
    onError: () => toast.error('Fehler beim Aktualisieren'),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: string) => api.delete(`/kreditversicherungen/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kreditversicherungen'] });
      toast.success('Kreditversicherung deaktiviert');
      setSelectedKV(null);
    },
    onError: () => toast.error('Fehler beim Löschen'),
  });

  const addAdresseMutation = useMutation({
    mutationFn: ({ kvId, adresseId }: { kvId: string; adresseId: string }) =>
      api.post(`/kreditversicherungen/${kvId}/adressen`, { adresse_id: adresseId }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kreditversicherungen'] });
      toast.success('Adresse hinzugefügt');
      setShowAddresseDialog(false);
      setAddressSearchQuery('');
    },
    onError: () => toast.error('Fehler beim Hinzufügen'),
  });

  const removeAdresseMutation = useMutation({
    mutationFn: ({ kvId, linkId }: { kvId: string; linkId: string }) =>
      api.delete(`/kreditversicherungen/${kvId}/adressen/${linkId}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kreditversicherungen'] });
      toast.success('Adresse entfernt');
    },
    onError: () => toast.error('Fehler beim Entfernen'),
  });

  const addPositionMutation = useMutation({
    mutationFn: ({ kvId, data }: { kvId: string; data: any }) =>
      api.post(`/kreditversicherungen/${kvId}/positionen`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kreditversicherungen'] });
      toast.success('Position hinzugefügt');
      setShowPositionDialog(false);
      resetPositionForm();
    },
    onError: () => toast.error('Fehler beim Hinzufügen'),
  });

  const updatePositionMutation = useMutation({
    mutationFn: ({ kvId, posId, data }: { kvId: string; posId: string; data: any }) =>
      api.put(`/kreditversicherungen/${kvId}/positionen/${posId}`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kreditversicherungen'] });
      toast.success('Position aktualisiert');
      setShowPositionDialog(false);
      resetPositionForm();
    },
    onError: () => toast.error('Fehler beim Aktualisieren'),
  });

  const deletePositionMutation = useMutation({
    mutationFn: ({ kvId, posId }: { kvId: string; posId: string }) =>
      api.delete(`/kreditversicherungen/${kvId}/positionen/${posId}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kreditversicherungen'] });
      toast.success('Position gelöscht');
    },
    onError: () => toast.error('Fehler beim Löschen'),
  });

  // Helpers
  const resetPositionForm = () => {
    setPositionForm({
      gueltig_ab: new Date().toISOString().split('T')[0],
      gueltig_bis: '',
      betrag: 0,
      betrag_anfrage: 0,
      aktiv: true,
      bemerkungen: '',
    });
    setEditingPosition(null);
  };

  const handleNewKV = () => {
    setSelectedKV({
      id: '',
      versicherungsnummer: '',
      versicherer_name: '',
      fakturierungsfrist: 0,
      aktiv: true,
      bemerkungen: '',
      versicherte_adressen: [],
      positionen: [],
    });
    setFormData({
      versicherungsnummer: '',
      versicherer_name: '',
      fakturierungsfrist: 0,
      aktiv: true,
      bemerkungen: '',
    });
    setIsNewRecord(true);
    setIsEditing(true);
  };

  const handleSelectKV = (kv: Kreditversicherung) => {
    setSelectedKV(kv);
    setFormData({
      versicherungsnummer: kv.versicherungsnummer || '',
      versicherer_name: kv.versicherer_name || '',
      fakturierungsfrist: kv.fakturierungsfrist || 0,
      aktiv: kv.aktiv,
      bemerkungen: kv.bemerkungen || '',
    });
    setIsNewRecord(false);
    setIsEditing(false);
  };

  const handleSave = () => {
    if (isNewRecord) {
      createMutation.mutate(formData);
    } else if (selectedKV) {
      updateMutation.mutate({ id: selectedKV.id, data: formData });
    }
  };

  const handleEditPosition = (pos: KVPosition) => {
    setEditingPosition(pos);
    setPositionForm({
      gueltig_ab: pos.gueltig_ab,
      gueltig_bis: pos.gueltig_bis || '',
      betrag: pos.betrag,
      betrag_anfrage: pos.betrag_anfrage || 0,
      aktiv: pos.aktiv,
      bemerkungen: pos.bemerkungen || '',
    });
    setShowPositionDialog(true);
  };

  const handleSavePosition = () => {
    if (!selectedKV) return;
    
    const data = {
      ...positionForm,
      gueltig_bis: positionForm.gueltig_bis || null,
      betrag_anfrage: positionForm.betrag_anfrage || null,
    };

    if (editingPosition) {
      updatePositionMutation.mutate({ 
        kvId: selectedKV.id, 
        posId: editingPosition.id, 
        data 
      });
    } else {
      addPositionMutation.mutate({ kvId: selectedKV.id, data });
    }
  };

  // Table columns
  const columns: ColumnDef<Kreditversicherung>[] = useMemo(() => [
    {
      accessorKey: 'versicherungsnummer',
      header: 'Nummer',
      cell: ({ row }) => (
        <span className="font-mono">{row.original.versicherungsnummer || row.original.id.slice(0, 8)}</span>
      ),
    },
    {
      accessorKey: 'versicherer_name',
      header: 'Versicherer',
    },
    {
      id: 'limit',
      header: 'Aktuelles Limit',
      cell: ({ row }) => {
        const aktivePosi = row.original.positionen?.find(p => p.aktiv);
        return aktivePosi ? formatCurrency(aktivePosi.betrag) : '-';
      },
    },
    {
      id: 'adressen',
      header: 'Kunden',
      cell: ({ row }) => (
        <Badge variant="secondary">
          <Users className="w-3 h-3 mr-1" />
          {row.original.versicherte_adressen?.length || 0}
        </Badge>
      ),
    },
    {
      accessorKey: 'aktiv',
      header: 'Status',
      cell: ({ row }) => (
        <Badge className={row.original.aktiv ? 'bg-green-100 text-green-800' : 'bg-gray-100 text-gray-600'}>
          {row.original.aktiv ? 'Aktiv' : 'Inaktiv'}
        </Badge>
      ),
    },
  ], []);

  return (
    <div className="flex h-full bg-gray-50">
      {/* Linke Seite: Tabelle */}
      <div className="flex-1 p-6 overflow-auto">
        <div className="flex items-center justify-between mb-6">
          <div>
            <h1 className="text-2xl font-bold text-gray-900">Kreditversicherungen</h1>
            <p className="text-gray-500">Verwaltung von Kreditversicherungsverträgen</p>
          </div>
          <Button onClick={handleNewKV} className="bg-purple-600 hover:bg-purple-700">
            <Plus className="w-4 h-4 mr-2" />
            Neue Kreditversicherung
          </Button>
        </div>

        {isLoading ? (
          <div className="flex items-center justify-center py-12">
            <Loader2 className="w-8 h-8 animate-spin text-gray-400" />
          </div>
        ) : (
          <DataTable
            columns={columns}
            data={kreditversicherungen || []}
            onRowDoubleClick={(row) => handleSelectKV(row.original)}
            searchPlaceholder="Suchen..."
          />
        )}
      </div>

      {/* Rechte Seite: Detail-Panel */}
      <AnimatePresence>
        {selectedKV && (
          <motion.div
            initial={{ x: '100%', opacity: 0 }}
            animate={{ x: 0, opacity: 1 }}
            exit={{ x: '100%', opacity: 0 }}
            transition={{ type: 'spring', damping: 25, stiffness: 200 }}
            className="w-[500px] bg-white border-l border-gray-200 flex flex-col shadow-xl"
          >
            {/* Header */}
            <div className="p-4 border-b border-gray-200 flex items-center justify-between bg-gradient-to-r from-purple-50 to-white">
              <div className="flex items-center gap-3">
                <div className="w-10 h-10 rounded-full bg-purple-100 flex items-center justify-center">
                  <Shield className="w-5 h-5 text-purple-600" />
                </div>
                <div>
                  <h2 className="font-semibold text-gray-900">
                    {isNewRecord ? 'Neue Kreditversicherung' : formData.versicherungsnummer || selectedKV.id.slice(0, 8)}
                  </h2>
                  <p className="text-sm text-gray-500">
                    {isNewRecord ? 'Wird erstellt...' : formData.versicherer_name || 'Kein Versicherer'}
                  </p>
                </div>
              </div>
              <div className="flex items-center gap-2">
                {!isEditing && !isNewRecord && (
                  <Button variant="outline" size="sm" onClick={() => setIsEditing(true)}>
                    <Pencil className="w-4 h-4 mr-1" />
                    Bearbeiten
                  </Button>
                )}
                {isEditing && (
                  <>
                    <Button variant="outline" size="sm" onClick={() => {
                      setIsEditing(false);
                      if (isNewRecord) setSelectedKV(null);
                    }}>
                      Abbrechen
                    </Button>
                    <Button size="sm" onClick={handleSave} disabled={createMutation.isPending || updateMutation.isPending}>
                      {(createMutation.isPending || updateMutation.isPending) && (
                        <Loader2 className="w-4 h-4 mr-1 animate-spin" />
                      )}
                      <Save className="w-4 h-4 mr-1" />
                      Speichern
                    </Button>
                  </>
                )}
                <Button variant="ghost" size="icon" onClick={() => setSelectedKV(null)}>
                  <X className="w-4 h-4" />
                </Button>
              </div>
            </div>

            {/* Content */}
            <div className="flex-1 overflow-auto p-4 space-y-6">
              {/* Kopfdaten */}
              <div className="space-y-4">
                <h3 className="font-medium text-gray-900 flex items-center gap-2">
                  <FileText className="w-4 h-4" />
                  Vertragsdaten
                </h3>
                <div className="grid gap-4">
                  <div>
                    <Label>Versicherungsnummer</Label>
                    <Input
                      value={formData.versicherungsnummer}
                      onChange={(e) => setFormData({ ...formData, versicherungsnummer: e.target.value })}
                      disabled={!isEditing}
                      placeholder="z.B. KV-2024-001"
                    />
                  </div>
                  <div>
                    <Label>Versicherer</Label>
                    <Input
                      value={formData.versicherer_name}
                      onChange={(e) => setFormData({ ...formData, versicherer_name: e.target.value })}
                      disabled={!isEditing}
                      placeholder="Name der Versicherung"
                    />
                  </div>
                  <div>
                    <Label>Verlängerte Fakturierungsfrist (Tage)</Label>
                    <Input
                      type="number"
                      value={formData.fakturierungsfrist}
                      onChange={(e) => setFormData({ ...formData, fakturierungsfrist: parseInt(e.target.value) || 0 })}
                      disabled={!isEditing}
                    />
                  </div>
                  <div className="flex items-center justify-between">
                    <Label>Aktiv</Label>
                    <Switch
                      checked={formData.aktiv}
                      onCheckedChange={(v) => setFormData({ ...formData, aktiv: v })}
                      disabled={!isEditing}
                    />
                  </div>
                  <div>
                    <Label>Bemerkungen</Label>
                    <Textarea
                      value={formData.bemerkungen}
                      onChange={(e) => setFormData({ ...formData, bemerkungen: e.target.value })}
                      disabled={!isEditing}
                      rows={2}
                    />
                  </div>
                </div>
              </div>

              {/* Positionen (Limits) */}
              {!isNewRecord && (
                <div className="space-y-3">
                  <div className="flex items-center justify-between">
                    <h3 className="font-medium text-gray-900 flex items-center gap-2">
                      <Euro className="w-4 h-4" />
                      Limits / Positionen
                    </h3>
                    {isEditing && (
                      <Button size="sm" variant="outline" onClick={() => {
                        resetPositionForm();
                        setShowPositionDialog(true);
                      }}>
                        <Plus className="w-4 h-4 mr-1" />
                        Hinzufügen
                      </Button>
                    )}
                  </div>

                  {selectedKV.positionen?.length === 0 ? (
                    <p className="text-sm text-gray-500 text-center py-4">Keine Positionen</p>
                  ) : (
                    <div className="space-y-2">
                      {selectedKV.positionen?.map((pos) => (
                        <div 
                          key={pos.id}
                          className={cn(
                            "p-3 rounded-lg border bg-white",
                            pos.aktiv ? "border-green-200" : "border-gray-200 opacity-60"
                          )}
                        >
                          <div className="flex items-center justify-between">
                            <div>
                              <p className="font-semibold">{formatCurrency(pos.betrag)}</p>
                              <p className="text-sm text-gray-500">
                                {formatDate(pos.gueltig_ab)} - {formatDate(pos.gueltig_bis)}
                              </p>
                            </div>
                            {isEditing && (
                              <div className="flex gap-1">
                                <Button variant="ghost" size="icon" onClick={() => handleEditPosition(pos)}>
                                  <Pencil className="w-4 h-4" />
                                </Button>
                                <Button 
                                  variant="ghost" 
                                  size="icon" 
                                  className="text-red-600"
                                  onClick={() => deletePositionMutation.mutate({ 
                                    kvId: selectedKV.id, 
                                    posId: pos.id 
                                  })}
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
                </div>
              )}

              {/* Versicherte Adressen */}
              {!isNewRecord && (
                <div className="space-y-3">
                  <div className="flex items-center justify-between">
                    <h3 className="font-medium text-gray-900 flex items-center gap-2">
                      <Users className="w-4 h-4" />
                      Versicherte Kunden
                    </h3>
                    {isEditing && (
                      <Button size="sm" variant="outline" onClick={() => setShowAddresseDialog(true)}>
                        <Plus className="w-4 h-4 mr-1" />
                        Hinzufügen
                      </Button>
                    )}
                  </div>

                  {selectedKV.versicherte_adressen?.length === 0 ? (
                    <p className="text-sm text-gray-500 text-center py-4">Keine Kunden verknüpft</p>
                  ) : (
                    <div className="space-y-2">
                      {selectedKV.versicherte_adressen?.map((addr) => (
                        <div 
                          key={addr.id}
                          className="p-3 rounded-lg border bg-white flex items-center justify-between"
                        >
                          <div>
                            <p className="font-medium">{addr.adresse_name || addr.adresse_id}</p>
                            <p className="text-sm text-gray-500">
                              {addr.adresse_kdnr && <span className="font-mono mr-2">{addr.adresse_kdnr}</span>}
                              {addr.adresse_ort}
                            </p>
                          </div>
                          {isEditing && (
                            <Button 
                              variant="ghost" 
                              size="icon" 
                              className="text-red-600"
                              onClick={() => removeAdresseMutation.mutate({ 
                                kvId: selectedKV.id, 
                                linkId: addr.id 
                              })}
                            >
                              <Trash2 className="w-4 h-4" />
                            </Button>
                          )}
                        </div>
                      ))}
                    </div>
                  )}
                </div>
              )}
            </div>
          </motion.div>
        )}
      </AnimatePresence>

      {/* Dialog: Adresse hinzufügen */}
      <Dialog open={showAddresseDialog} onOpenChange={setShowAddresseDialog}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Kunde zur Kreditversicherung hinzufügen</DialogTitle>
          </DialogHeader>
          <div className="space-y-4 py-4">
            <div>
              <Label>Kunde suchen</Label>
              <div className="relative">
                <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-gray-400" />
                <Input
                  value={addressSearchQuery}
                  onChange={(e) => setAddressSearchQuery(e.target.value)}
                  placeholder="Name, Ort oder Kundennummer..."
                  className="pl-9"
                />
              </div>
            </div>
            {addressResults && addressResults.length > 0 && (
              <div className="max-h-60 overflow-auto border rounded-lg divide-y">
                {addressResults.map((addr: any) => (
                  <button
                    key={addr.id}
                    className="w-full p-3 text-left hover:bg-gray-50 flex items-center justify-between"
                    onClick={() => {
                      if (selectedKV) {
                        addAdresseMutation.mutate({ kvId: selectedKV.id, adresseId: addr.id });
                      }
                    }}
                  >
                    <div>
                      <p className="font-medium">{addr.name1}</p>
                      <p className="text-sm text-gray-500">{addr.kdnr} · {addr.ort}</p>
                    </div>
                    <ChevronRight className="w-4 h-4 text-gray-400" />
                  </button>
                ))}
              </div>
            )}
          </div>
        </DialogContent>
      </Dialog>

      {/* Dialog: Position hinzufügen/bearbeiten */}
      <Dialog open={showPositionDialog} onOpenChange={setShowPositionDialog}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>
              {editingPosition ? 'Position bearbeiten' : 'Neue Position / Limit'}
            </DialogTitle>
          </DialogHeader>
          <div className="space-y-4 py-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label>Gültig ab *</Label>
                <Input
                  type="date"
                  value={positionForm.gueltig_ab}
                  onChange={(e) => setPositionForm({ ...positionForm, gueltig_ab: e.target.value })}
                />
              </div>
              <div>
                <Label>Gültig bis</Label>
                <Input
                  type="date"
                  value={positionForm.gueltig_bis}
                  onChange={(e) => setPositionForm({ ...positionForm, gueltig_bis: e.target.value })}
                />
              </div>
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label>Kreditlimit (EUR) *</Label>
                <Input
                  type="number"
                  value={positionForm.betrag}
                  onChange={(e) => setPositionForm({ ...positionForm, betrag: parseFloat(e.target.value) || 0 })}
                />
              </div>
              <div>
                <Label>Angefragter Betrag</Label>
                <Input
                  type="number"
                  value={positionForm.betrag_anfrage}
                  onChange={(e) => setPositionForm({ ...positionForm, betrag_anfrage: parseFloat(e.target.value) || 0 })}
                />
              </div>
            </div>
            <div className="flex items-center justify-between">
              <Label>Aktiv</Label>
              <Switch
                checked={positionForm.aktiv}
                onCheckedChange={(v) => setPositionForm({ ...positionForm, aktiv: v })}
              />
            </div>
            <div>
              <Label>Bemerkungen</Label>
              <Textarea
                value={positionForm.bemerkungen}
                onChange={(e) => setPositionForm({ ...positionForm, bemerkungen: e.target.value })}
                rows={2}
              />
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setShowPositionDialog(false)}>
              Abbrechen
            </Button>
            <Button onClick={handleSavePosition} disabled={addPositionMutation.isPending || updatePositionMutation.isPending}>
              {(addPositionMutation.isPending || updatePositionMutation.isPending) && (
                <Loader2 className="w-4 h-4 mr-1 animate-spin" />
              )}
              {editingPosition ? 'Speichern' : 'Hinzufügen'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
