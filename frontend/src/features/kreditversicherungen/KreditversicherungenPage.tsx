import { useState, useMemo, useEffect } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion, AnimatePresence } from 'framer-motion';
import { toast } from 'sonner';
import { 
  Plus, Pencil, Trash2, Shield, Users, Euro, TrendingUp,
  X, Save, Loader2, Search, ChevronRight, FileText, Calendar,
  Building2, AlertTriangle, CheckCircle, ChevronDown, MoreHorizontal
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
import { Progress } from '@/components/ui/progress';
import { cn } from '@/lib/utils';
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu';

// Types
interface KundenPosition {
  id: string;
  adresse_id: string;
  adresse_name?: string;
  adresse_ort?: string;
  adresse_kdnr?: string;
  kreditlimit: number;
  unterversicherungsnummer?: string;
  fakturierungsfrist?: number;
  gueltig_bis?: string;
  aktiv: boolean;
  bemerkungen?: string;
  erstellt_am?: string;
  geaendert_am?: string;
}

interface Kreditversicherung {
  id: string;
  versicherungsnummer?: string;
  versicherer_id?: string;
  versicherer_name?: string;
  gesamtlimit: number;
  gueltig_von?: string;
  gueltig_bis?: string;
  aktiv: boolean;
  bemerkungen?: string;
  kunden_positionen: KundenPosition[];
  created_at?: string;
  // Berechnete Felder
  auslastung_prozent?: number;
  summe_kundenlimits?: number;
  anzahl_kunden?: number;
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
  const [showAddKundeDialog, setShowAddKundeDialog] = useState(false);
  const [showEditKundeDialog, setShowEditKundeDialog] = useState(false);
  const [addressSearchQuery, setAddressSearchQuery] = useState('');
  const [kundenSearchQuery, setKundenSearchQuery] = useState('');
  const [editingKunde, setEditingKunde] = useState<KundenPosition | null>(null);

  // Form state für Hauptvertrag
  const [formData, setFormData] = useState({
    versicherungsnummer: '',
    versicherer_name: '',
    gesamtlimit: 0,
    gueltig_von: '',
    gueltig_bis: '',
    aktiv: true,
    bemerkungen: '',
  });

  // Form state für Kundenposition
  const [kundeForm, setKundeForm] = useState({
    adresse_id: '',
    adresse_name: '',
    kreditlimit: 0,
    unterversicherungsnummer: '',
    fakturierungsfrist: 0,
    gueltig_bis: '',
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

  // Einzelne KV nachladen für aktuelle Daten
  const { data: selectedKVDetails } = useQuery({
    queryKey: ['kreditversicherung', selectedKV?.id],
    queryFn: async () => {
      if (!selectedKV?.id) return null;
      const response = await api.get(`/kreditversicherungen/${selectedKV.id}`);
      return response.data.data;
    },
    enabled: !!selectedKV?.id && !isNewRecord,
  });

  // Sync selectedKV mit Details
  useEffect(() => {
    if (selectedKVDetails && !isNewRecord) {
      setSelectedKV(selectedKVDetails);
    }
  }, [selectedKVDetails, isNewRecord]);

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
      setIsEditing(true); // Weiter bearbeiten für Kunden hinzufügen
    },
    onError: () => toast.error('Fehler beim Erstellen'),
  });

  const updateMutation = useMutation({
    mutationFn: ({ id, data }: { id: string; data: any }) => 
      api.put(`/kreditversicherungen/${id}`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kreditversicherungen'] });
      queryClient.invalidateQueries({ queryKey: ['kreditversicherung', selectedKV?.id] });
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
    onError: () => toast.error('Fehler beim Deaktivieren'),
  });

  const addKundeMutation = useMutation({
    mutationFn: ({ kvId, data }: { kvId: string; data: any }) =>
      api.post(`/kreditversicherungen/${kvId}/positionen`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kreditversicherungen'] });
      queryClient.invalidateQueries({ queryKey: ['kreditversicherung', selectedKV?.id] });
      toast.success('Kunde hinzugefügt');
      setShowAddKundeDialog(false);
      resetKundeForm();
    },
    onError: (error: any) => {
      const msg = error.response?.data?.detail || 'Fehler beim Hinzufügen';
      toast.error(msg);
    },
  });

  const updateKundeMutation = useMutation({
    mutationFn: ({ kvId, posId, data }: { kvId: string; posId: string; data: any }) =>
      api.put(`/kreditversicherungen/${kvId}/positionen/${posId}`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kreditversicherungen'] });
      queryClient.invalidateQueries({ queryKey: ['kreditversicherung', selectedKV?.id] });
      toast.success('Kunde aktualisiert');
      setShowEditKundeDialog(false);
      resetKundeForm();
    },
    onError: () => toast.error('Fehler beim Aktualisieren'),
  });

  const deleteKundeMutation = useMutation({
    mutationFn: ({ kvId, posId }: { kvId: string; posId: string }) =>
      api.delete(`/kreditversicherungen/${kvId}/positionen/${posId}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kreditversicherungen'] });
      queryClient.invalidateQueries({ queryKey: ['kreditversicherung', selectedKV?.id] });
      toast.success('Kunde entfernt');
    },
    onError: () => toast.error('Fehler beim Entfernen'),
  });

  // Helpers
  const resetKundeForm = () => {
    setKundeForm({
      adresse_id: '',
      adresse_name: '',
      kreditlimit: 0,
      unterversicherungsnummer: '',
      fakturierungsfrist: 0,
      gueltig_bis: '',
      aktiv: true,
      bemerkungen: '',
    });
    setEditingKunde(null);
    setAddressSearchQuery('');
  };

  const handleNewKV = () => {
    setSelectedKV({
      id: '',
      versicherungsnummer: '',
      versicherer_name: '',
      gesamtlimit: 0,
      gueltig_von: new Date().toISOString().split('T')[0],
      gueltig_bis: '',
      aktiv: true,
      bemerkungen: '',
      kunden_positionen: [],
    });
    setFormData({
      versicherungsnummer: '',
      versicherer_name: '',
      gesamtlimit: 0,
      gueltig_von: new Date().toISOString().split('T')[0],
      gueltig_bis: '',
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
      gesamtlimit: kv.gesamtlimit || 0,
      gueltig_von: kv.gueltig_von || '',
      gueltig_bis: kv.gueltig_bis || '',
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

  const handleEditKunde = (kunde: KundenPosition) => {
    setEditingKunde(kunde);
    setKundeForm({
      adresse_id: kunde.adresse_id,
      adresse_name: kunde.adresse_name || '',
      kreditlimit: kunde.kreditlimit,
      unterversicherungsnummer: kunde.unterversicherungsnummer || '',
      fakturierungsfrist: kunde.fakturierungsfrist || 0,
      gueltig_bis: kunde.gueltig_bis || '',
      aktiv: kunde.aktiv,
      bemerkungen: kunde.bemerkungen || '',
    });
    setShowEditKundeDialog(true);
  };

  const handleSaveKunde = () => {
    if (!selectedKV) return;
    
    if (editingKunde) {
      updateKundeMutation.mutate({ 
        kvId: selectedKV.id, 
        posId: editingKunde.id, 
        data: {
          kreditlimit: kundeForm.kreditlimit,
          unterversicherungsnummer: kundeForm.unterversicherungsnummer || null,
          fakturierungsfrist: kundeForm.fakturierungsfrist || null,
          gueltig_bis: kundeForm.gueltig_bis || null,
          aktiv: kundeForm.aktiv,
          bemerkungen: kundeForm.bemerkungen || null,
        }
      });
    } else {
      addKundeMutation.mutate({ 
        kvId: selectedKV.id, 
        data: {
          adresse_id: kundeForm.adresse_id,
          kreditlimit: kundeForm.kreditlimit,
          unterversicherungsnummer: kundeForm.unterversicherungsnummer || null,
          fakturierungsfrist: kundeForm.fakturierungsfrist || null,
          gueltig_bis: kundeForm.gueltig_bis || null,
          aktiv: kundeForm.aktiv,
          bemerkungen: kundeForm.bemerkungen || null,
        }
      });
    }
  };

  const handleSelectAddress = (addr: any) => {
    setKundeForm({
      ...kundeForm,
      adresse_id: addr.id,
      adresse_name: addr.name1,
    });
    setAddressSearchQuery('');
  };

  // Gefilterte Kundenliste
  const filteredKunden = useMemo(() => {
    if (!selectedKV?.kunden_positionen) return [];
    let kunden = [...selectedKV.kunden_positionen];
    
    if (kundenSearchQuery) {
      const q = kundenSearchQuery.toLowerCase();
      kunden = kunden.filter(k => 
        (k.adresse_name || '').toLowerCase().includes(q) ||
        (k.adresse_kdnr || '').toLowerCase().includes(q) ||
        (k.adresse_ort || '').toLowerCase().includes(q) ||
        (k.unterversicherungsnummer || '').toLowerCase().includes(q)
      );
    }
    
    return kunden;
  }, [selectedKV?.kunden_positionen, kundenSearchQuery]);

  // Auslastungs-Farbe
  const getAuslastungColor = (prozent: number) => {
    if (prozent >= 100) return 'bg-red-500';
    if (prozent >= 80) return 'bg-amber-500';
    if (prozent >= 50) return 'bg-blue-500';
    return 'bg-green-500';
  };

  // Table columns
  const columns: ColumnDef<Kreditversicherung>[] = useMemo(() => [
    {
      accessorKey: 'versicherungsnummer',
      header: 'Nummer',
      cell: ({ row }) => (
        <span className="font-mono text-sm">{row.original.versicherungsnummer || row.original.id}</span>
      ),
    },
    {
      accessorKey: 'versicherer_name',
      header: 'Versicherer',
      cell: ({ row }) => (
        <span className="font-medium">{row.original.versicherer_name || '-'}</span>
      ),
    },
    {
      id: 'gesamtlimit',
      header: 'Gesamtlimit',
      cell: ({ row }) => (
        <span className="font-semibold">{formatCurrency(row.original.gesamtlimit || 0)}</span>
      ),
    },
    {
      id: 'auslastung',
      header: 'Auslastung',
      cell: ({ row }) => {
        const prozent = row.original.auslastung_prozent || 0;
        return (
          <div className="w-32">
            <div className="flex items-center gap-2 mb-1">
              <span className="text-sm font-medium">{prozent.toFixed(1)}%</span>
            </div>
            <Progress 
              value={Math.min(prozent, 100)} 
              className={cn("h-2", getAuslastungColor(prozent))}
            />
          </div>
        );
      },
    },
    {
      id: 'kunden',
      header: 'Kunden',
      cell: ({ row }) => (
        <Badge variant="secondary">
          <Users className="w-3 h-3 mr-1" />
          {row.original.anzahl_kunden || 0}
        </Badge>
      ),
    },
    {
      id: 'gueltig_bis',
      header: 'Gültig bis',
      cell: ({ row }) => (
        <span className="text-sm text-gray-600">{formatDate(row.original.gueltig_bis)}</span>
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

  // Kunden-Tabelle Columns
  const kundenColumns: ColumnDef<KundenPosition>[] = useMemo(() => [
    {
      accessorKey: 'adresse_kdnr',
      header: 'KdNr',
      cell: ({ row }) => (
        <span className="font-mono text-sm">{row.original.adresse_kdnr || '-'}</span>
      ),
    },
    {
      accessorKey: 'adresse_name',
      header: 'Kunde',
      cell: ({ row }) => (
        <div>
          <p className="font-medium">{row.original.adresse_name}</p>
          <p className="text-xs text-gray-500">{row.original.adresse_ort}</p>
        </div>
      ),
    },
    {
      accessorKey: 'unterversicherungsnummer',
      header: 'Unter-Nr.',
      cell: ({ row }) => (
        <span className="font-mono text-sm">{row.original.unterversicherungsnummer || '-'}</span>
      ),
    },
    {
      accessorKey: 'kreditlimit',
      header: 'Kreditlimit',
      cell: ({ row }) => (
        <span className="font-semibold">{formatCurrency(row.original.kreditlimit)}</span>
      ),
    },
    {
      accessorKey: 'fakturierungsfrist',
      header: 'Fakt.-Frist',
      cell: ({ row }) => (
        <span>{row.original.fakturierungsfrist ? `${row.original.fakturierungsfrist} Tage` : '-'}</span>
      ),
    },
    {
      accessorKey: 'gueltig_bis',
      header: 'Gültig bis',
      cell: ({ row }) => (
        <span className="text-sm">{formatDate(row.original.gueltig_bis)}</span>
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
    {
      id: 'actions',
      header: '',
      cell: ({ row }) => (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" size="icon" className="h-8 w-8">
              <MoreHorizontal className="w-4 h-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuItem onClick={() => handleEditKunde(row.original)}>
              <Pencil className="w-4 h-4 mr-2" />
              Bearbeiten
            </DropdownMenuItem>
            <DropdownMenuItem 
              className="text-red-600"
              onClick={() => {
                if (selectedKV && confirm('Kunde wirklich entfernen?')) {
                  deleteKundeMutation.mutate({ kvId: selectedKV.id, posId: row.original.id });
                }
              }}
            >
              <Trash2 className="w-4 h-4 mr-2" />
              Entfernen
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      ),
    },
  ], [selectedKV]);

  return (
    <div className="flex h-full bg-gray-50" data-testid="kreditversicherungen-page">
      {/* Linke Seite: Hauptverträge-Liste */}
      <div className="flex-1 p-6 overflow-auto">
        <div className="flex items-center justify-between mb-6">
          <div>
            <h1 className="text-2xl font-bold text-gray-900">Kreditversicherungen</h1>
            <p className="text-gray-500">Verwaltung von Kreditversicherungsverträgen und Kundenlimits</p>
          </div>
          <Button 
            onClick={handleNewKV} 
            className="bg-purple-600 hover:bg-purple-700"
            data-testid="new-kv-btn"
          >
            <Plus className="w-4 h-4 mr-2" />
            Neuer Vertrag
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
            onRowDoubleClick={(row) => handleSelectKV(row)}
            searchPlaceholder="Suche nach Nummer, Versicherer oder Kunde..."
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
            className="w-[700px] bg-white border-l border-gray-200 flex flex-col shadow-xl"
            data-testid="kv-detail-panel"
          >
            {/* Header */}
            <div className="p-4 border-b border-gray-200 flex items-center justify-between bg-gradient-to-r from-purple-50 to-white">
              <div className="flex items-center gap-3">
                <div className="w-10 h-10 rounded-full bg-purple-100 flex items-center justify-center">
                  <Shield className="w-5 h-5 text-purple-600" />
                </div>
                <div>
                  <h2 className="font-semibold text-gray-900">
                    {isNewRecord ? 'Neuer Hauptvertrag' : formData.versicherungsnummer || selectedKV.id}
                  </h2>
                  <p className="text-sm text-gray-500">
                    {isNewRecord ? 'Wird erstellt...' : formData.versicherer_name || 'Kein Versicherer'}
                  </p>
                </div>
              </div>
              <div className="flex items-center gap-2">
                {!isEditing && !isNewRecord && (
                  <>
                    <Button variant="outline" size="sm" onClick={() => setIsEditing(true)}>
                      <Pencil className="w-4 h-4 mr-1" />
                      Bearbeiten
                    </Button>
                    <Button 
                      variant="outline" 
                      size="sm" 
                      className="text-red-600 hover:bg-red-50"
                      onClick={() => {
                        if (confirm('Kreditversicherung wirklich deaktivieren?')) {
                          deleteMutation.mutate(selectedKV.id);
                        }
                      }}
                    >
                      <Trash2 className="w-4 h-4" />
                    </Button>
                  </>
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

            {/* Auslastungs-Anzeige */}
            {!isNewRecord && (
              <div className="p-4 border-b border-gray-200 bg-gray-50">
                <div className="flex items-center justify-between mb-2">
                  <span className="text-sm font-medium text-gray-700">Auslastung des Gesamtlimits</span>
                  <span className="text-lg font-bold">
                    {(selectedKV.auslastung_prozent || 0).toFixed(1)}%
                  </span>
                </div>
                <Progress 
                  value={Math.min(selectedKV.auslastung_prozent || 0, 100)} 
                  className="h-3"
                />
                <div className="flex justify-between mt-2 text-sm text-gray-500">
                  <span>Summe Kundenlimits: {formatCurrency(selectedKV.summe_kundenlimits || 0)}</span>
                  <span>Gesamtlimit: {formatCurrency(selectedKV.gesamtlimit || 0)}</span>
                </div>
              </div>
            )}

            {/* Content */}
            <div className="flex-1 overflow-auto p-4 space-y-6">
              {/* Hauptvertrag-Daten */}
              <div className="space-y-4">
                <h3 className="font-medium text-gray-900 flex items-center gap-2">
                  <FileText className="w-4 h-4" />
                  Hauptvertrag (Kopf)
                </h3>
                <div className="grid grid-cols-2 gap-4">
                  <div>
                    <Label>Versicherungsnummer</Label>
                    <Input
                      value={formData.versicherungsnummer}
                      onChange={(e) => setFormData({ ...formData, versicherungsnummer: e.target.value })}
                      disabled={!isEditing}
                      placeholder="z.B. KV-2024-001"
                      data-testid="kv-nummer-input"
                    />
                  </div>
                  <div>
                    <Label>Versicherer</Label>
                    <Input
                      value={formData.versicherer_name}
                      onChange={(e) => setFormData({ ...formData, versicherer_name: e.target.value })}
                      disabled={!isEditing}
                      placeholder="Name der Versicherung"
                      data-testid="kv-versicherer-input"
                    />
                  </div>
                  <div>
                    <Label>Gesamtlimit (EUR) *</Label>
                    <Input
                      type="number"
                      value={formData.gesamtlimit}
                      onChange={(e) => setFormData({ ...formData, gesamtlimit: parseFloat(e.target.value) || 0 })}
                      disabled={!isEditing}
                      data-testid="kv-gesamtlimit-input"
                    />
                  </div>
                  <div className="flex items-center justify-between pt-6">
                    <Label>Aktiv</Label>
                    <Switch
                      checked={formData.aktiv}
                      onCheckedChange={(v) => setFormData({ ...formData, aktiv: v })}
                      disabled={!isEditing}
                    />
                  </div>
                  <div>
                    <Label>Vertragsbeginn</Label>
                    <Input
                      type="date"
                      value={formData.gueltig_von}
                      onChange={(e) => setFormData({ ...formData, gueltig_von: e.target.value })}
                      disabled={!isEditing}
                      data-testid="kv-von-input"
                    />
                  </div>
                  <div>
                    <Label>Vertragsende (sticht Unterverträge)</Label>
                    <Input
                      type="date"
                      value={formData.gueltig_bis}
                      onChange={(e) => setFormData({ ...formData, gueltig_bis: e.target.value })}
                      disabled={!isEditing}
                      data-testid="kv-bis-input"
                    />
                  </div>
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

              {/* Kundenpositionen (Grid-Tabelle) */}
              {!isNewRecord && (
                <div className="space-y-3">
                  <div className="flex items-center justify-between">
                    <h3 className="font-medium text-gray-900 flex items-center gap-2">
                      <Users className="w-4 h-4" />
                      Kundenpositionen ({selectedKV.anzahl_kunden || 0})
                    </h3>
                    <Button 
                      size="sm" 
                      variant="outline" 
                      onClick={() => {
                        resetKundeForm();
                        setShowAddKundeDialog(true);
                      }}
                      data-testid="add-kunde-btn"
                    >
                      <Plus className="w-4 h-4 mr-1" />
                      Kunde hinzufügen
                    </Button>
                  </div>

                  {/* Suchfeld für Kunden */}
                  <div className="relative">
                    <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-gray-400" />
                    <Input
                      value={kundenSearchQuery}
                      onChange={(e) => setKundenSearchQuery(e.target.value)}
                      placeholder="Kunden suchen..."
                      className="pl-9"
                      data-testid="kunden-search-input"
                    />
                  </div>

                  {/* Kunden-Grid */}
                  {filteredKunden.length === 0 ? (
                    <div className="text-center py-8 text-gray-500 bg-gray-50 rounded-lg border-2 border-dashed">
                      <Users className="w-10 h-10 mx-auto mb-2 text-gray-300" />
                      <p>Keine Kunden hinterlegt</p>
                      <p className="text-sm text-gray-400 mt-1">
                        Fügen Sie Kunden mit individuellem Kreditlimit hinzu
                      </p>
                    </div>
                  ) : (
                    <div className="border rounded-lg overflow-hidden">
                      <DataTable
                        columns={kundenColumns}
                        data={filteredKunden}
                        searchPlaceholder=""
                      />
                    </div>
                  )}
                </div>
              )}
            </div>
          </motion.div>
        )}
      </AnimatePresence>

      {/* Dialog: Kunde hinzufügen */}
      <Dialog open={showAddKundeDialog} onOpenChange={setShowAddKundeDialog}>
        <DialogContent className="max-w-lg">
          <DialogHeader>
            <DialogTitle>Kunde zur Kreditversicherung hinzufügen</DialogTitle>
          </DialogHeader>
          <div className="space-y-4 py-4">
            {/* Kunde suchen */}
            {!kundeForm.adresse_id ? (
              <div className="space-y-2">
                <Label>Kunde suchen *</Label>
                <div className="relative">
                  <Search className="absolute left-3 top-1/2 -translate-y-1/2 w-4 h-4 text-gray-400" />
                  <Input
                    value={addressSearchQuery}
                    onChange={(e) => setAddressSearchQuery(e.target.value)}
                    placeholder="Name, Ort oder Kundennummer..."
                    className="pl-9"
                    data-testid="kunde-search-input"
                  />
                </div>
                {addressResults && addressResults.length > 0 && (
                  <div className="max-h-60 overflow-auto border rounded-lg divide-y">
                    {addressResults.map((addr: any) => (
                      <button
                        key={addr.id}
                        className="w-full p-3 text-left hover:bg-gray-50 flex items-center justify-between"
                        onClick={() => handleSelectAddress(addr)}
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
            ) : (
              <div className="p-3 bg-purple-50 rounded-lg border border-purple-200 flex items-center justify-between">
                <div className="flex items-center gap-3">
                  <Building2 className="w-5 h-5 text-purple-600" />
                  <span className="font-medium">{kundeForm.adresse_name}</span>
                </div>
                <Button variant="ghost" size="sm" onClick={() => setKundeForm({ ...kundeForm, adresse_id: '', adresse_name: '' })}>
                  Ändern
                </Button>
              </div>
            )}

            {kundeForm.adresse_id && (
              <>
                <div className="grid grid-cols-2 gap-4">
                  <div>
                    <Label>Kreditlimit (EUR) *</Label>
                    <Input
                      type="number"
                      value={kundeForm.kreditlimit}
                      onChange={(e) => setKundeForm({ ...kundeForm, kreditlimit: parseFloat(e.target.value) || 0 })}
                      data-testid="kunde-limit-input"
                    />
                  </div>
                  <div>
                    <Label>Unterversicherungsnummer</Label>
                    <Input
                      value={kundeForm.unterversicherungsnummer}
                      onChange={(e) => setKundeForm({ ...kundeForm, unterversicherungsnummer: e.target.value })}
                      placeholder="z.B. UV-001"
                    />
                  </div>
                </div>
                <div className="grid grid-cols-2 gap-4">
                  <div>
                    <Label>Fakturierungsfrist (Tage)</Label>
                    <Input
                      type="number"
                      value={kundeForm.fakturierungsfrist}
                      onChange={(e) => setKundeForm({ ...kundeForm, fakturierungsfrist: parseInt(e.target.value) || 0 })}
                    />
                  </div>
                  <div>
                    <Label>Gültig bis (optional)</Label>
                    <Input
                      type="date"
                      value={kundeForm.gueltig_bis}
                      onChange={(e) => setKundeForm({ ...kundeForm, gueltig_bis: e.target.value })}
                    />
                    <p className="text-xs text-gray-500 mt-1">Hauptvertrag-Ende sticht</p>
                  </div>
                </div>
                <div className="flex items-center justify-between">
                  <Label>Aktiv</Label>
                  <Switch
                    checked={kundeForm.aktiv}
                    onCheckedChange={(v) => setKundeForm({ ...kundeForm, aktiv: v })}
                  />
                </div>
                <div>
                  <Label>Bemerkungen</Label>
                  <Textarea
                    value={kundeForm.bemerkungen}
                    onChange={(e) => setKundeForm({ ...kundeForm, bemerkungen: e.target.value })}
                    rows={2}
                  />
                </div>
              </>
            )}
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setShowAddKundeDialog(false)}>
              Abbrechen
            </Button>
            <Button 
              onClick={handleSaveKunde} 
              disabled={!kundeForm.adresse_id || addKundeMutation.isPending}
              data-testid="save-kunde-btn"
            >
              {addKundeMutation.isPending && <Loader2 className="w-4 h-4 mr-1 animate-spin" />}
              Hinzufügen
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>

      {/* Dialog: Kunde bearbeiten */}
      <Dialog open={showEditKundeDialog} onOpenChange={setShowEditKundeDialog}>
        <DialogContent className="max-w-lg">
          <DialogHeader>
            <DialogTitle>Kundenposition bearbeiten</DialogTitle>
          </DialogHeader>
          <div className="space-y-4 py-4">
            <div className="p-3 bg-purple-50 rounded-lg border border-purple-200 flex items-center gap-3">
              <Building2 className="w-5 h-5 text-purple-600" />
              <span className="font-medium">{editingKunde?.adresse_name}</span>
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label>Kreditlimit (EUR) *</Label>
                <Input
                  type="number"
                  value={kundeForm.kreditlimit}
                  onChange={(e) => setKundeForm({ ...kundeForm, kreditlimit: parseFloat(e.target.value) || 0 })}
                />
              </div>
              <div>
                <Label>Unterversicherungsnummer</Label>
                <Input
                  value={kundeForm.unterversicherungsnummer}
                  onChange={(e) => setKundeForm({ ...kundeForm, unterversicherungsnummer: e.target.value })}
                />
              </div>
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label>Fakturierungsfrist (Tage)</Label>
                <Input
                  type="number"
                  value={kundeForm.fakturierungsfrist}
                  onChange={(e) => setKundeForm({ ...kundeForm, fakturierungsfrist: parseInt(e.target.value) || 0 })}
                />
              </div>
              <div>
                <Label>Gültig bis (optional)</Label>
                <Input
                  type="date"
                  value={kundeForm.gueltig_bis}
                  onChange={(e) => setKundeForm({ ...kundeForm, gueltig_bis: e.target.value })}
                />
              </div>
            </div>
            <div className="flex items-center justify-between">
              <Label>Aktiv</Label>
              <Switch
                checked={kundeForm.aktiv}
                onCheckedChange={(v) => setKundeForm({ ...kundeForm, aktiv: v })}
              />
            </div>
            <div>
              <Label>Bemerkungen</Label>
              <Textarea
                value={kundeForm.bemerkungen}
                onChange={(e) => setKundeForm({ ...kundeForm, bemerkungen: e.target.value })}
                rows={2}
              />
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setShowEditKundeDialog(false)}>
              Abbrechen
            </Button>
            <Button 
              onClick={handleSaveKunde} 
              disabled={updateKundeMutation.isPending}
            >
              {updateKundeMutation.isPending && <Loader2 className="w-4 h-4 mr-1 animate-spin" />}
              Speichern
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
