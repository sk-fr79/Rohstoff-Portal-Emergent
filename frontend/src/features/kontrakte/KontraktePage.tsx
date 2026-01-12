import { useState, useMemo } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion, AnimatePresence } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import { 
  Plus, MoreHorizontal, Pencil, Trash2, FileText, Eye, 
  Save, X, Building2, Package, Calendar, Truck,
  ArrowDownToLine, ArrowUpFromLine, CheckCircle, Clock,
  AlertTriangle, Ban, Loader2, TrendingUp, Lock, Unlock,
  DollarSign, FileCheck, MessageSquare, Percent, Scale
} from 'lucide-react';
import { kontrakteApi } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { DataTable } from '@/components/ui/data-table';
import { Switch } from '@/components/ui/switch';
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuSeparator, DropdownMenuTrigger } from '@/components/ui/dropdown-menu';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Textarea } from '@/components/ui/textarea';
import { SmartInput } from '@/components/ui/smart-input';
import { cn } from '@/lib/utils';
import { Badge } from '@/components/ui/badge';
import { ScrollArea } from '@/components/ui/scroll-area';
import { useResizablePanel } from '@/hooks/useResizablePanel';
import { ResizeHandle } from '@/components/ui/resize-handle';
import { WAEHRUNGEN, getWaehrung } from '@/data/waehrungen';

// ========================== SCHEMA ==========================
const kontraktSchema = z.object({
  vorgang_typ: z.string().default('EK'),
  buchungsnummer: z.string().max(30).nullish(),
  
  // Adresse (Kopfdaten)
  id_adresse: z.string().nullish(),
  name1: z.string().min(1, 'Firma/Name erforderlich').max(40),
  name2: z.string().max(40).nullish(),
  strasse: z.string().max(45).nullish(),
  hausnummer: z.string().max(10).nullish(),
  plz: z.string().max(10).nullish(),
  ort: z.string().max(30).nullish(),
  land: z.string().max(30).nullish(),
  
  // Kontaktdaten
  telefon: z.string().max(30).nullish(),
  telefax: z.string().max(30).nullish(),
  email: z.string().email().nullish().or(z.literal('')),
  
  // Bearbeiter
  name_bearbeiter_intern: z.string().max(80).nullish(),
  tel_bearbeiter_intern: z.string().max(30).nullish(),
  fax_bearbeiter_intern: z.string().max(30).nullish(),
  
  // Termine
  erstellungsdatum: z.string().nullish(),
  gueltig_von: z.string().nullish(),
  gueltig_bis: z.string().nullish(),
  
  // Währung & Konditionen
  id_waehrung_fremd: z.string().nullish(),
  waehrung_kurz: z.string().max(5).default('EUR'),
  waehrungskurs: z.number().default(1),
  zahlungsbedingung: z.string().max(100).nullish(),
  lieferbedingung: z.string().max(50).nullish(),
  
  // === FIXIERUNG (NEU) ===
  ist_fixierung: z.boolean().default(false),
  fix_von: z.string().nullish(),
  fix_bis: z.string().nullish(),
  fix_menge_gesamt: z.number().nullish(),
  fix_id_artikel: z.string().nullish(),
  fix_einheit: z.string().max(10).nullish(),
  boerse_diff_abs: z.number().nullish(),
  boerse_diff_proz: z.number().nullish(),
  bemerkung_fix1: z.string().max(2000).nullish(),
  
  // Status
  status: z.string().default('OFFEN'),
  aktiv: z.boolean().default(true),
  abgeschlossen: z.boolean().default(false),
  
  // Formulartexte
  formulartext_anfang: z.string().max(4000).nullish(),
  formulartext_ende: z.string().max(4000).nullish(),
  kopie_bemerkung_auf_pos: z.boolean().default(false),
  
  // Bemerkungen
  bemerkung_extern: z.string().max(2000).nullish(),
  bemerkung_intern: z.string().max(2000).nullish(),
});

type KontraktForm = z.infer<typeof kontraktSchema>;

interface Position {
  id: string;
  positionsnummer: number;
  position_typ: string;
  id_artikel?: string;
  anr1?: string;
  artbez1?: string;
  anzahl?: number;
  einheitkurz: string;
  einzelpreis?: number;
  gesamtpreis?: number;
  einzelpreis_fw?: number;
  gesamtpreis_fw?: number;
  waehrung_fremd_kurz?: string;
  waehrungskurs: number;
  mengen_toleranz_prozent: number;
  ueberliefer_ok: boolean;
  lieferort?: string;
  lieferort_name?: string;
  gueltig_von?: string;
  gueltig_bis?: string;
  position_abgeschlossen: boolean;
  bemerkung?: string;
}

interface Kontrakt extends KontraktForm {
  id: string;
  positionen?: Position[];
  erstellt_am?: string;
  letzte_aenderung?: string;
  summen?: {
    summe_menge: number;
    summe_wert: number;
    summe_wert_fw: number;
    anzahl_positionen: number;
  };
}

// ========================== SIDEBAR SECTIONS ==========================
const sidebarSections = [
  { id: 'kopf', label: 'Kopfdaten', icon: FileText },
  { id: 'partner', label: 'Vertragspartner', icon: Building2 },
  { id: 'termine', label: 'Termine', icon: Calendar },
  { id: 'konditionen', label: 'Konditionen', icon: DollarSign },
  { id: 'fixierung', label: 'Fixierung', icon: TrendingUp },
  { id: 'positionen', label: 'Positionen', icon: Package },
  { id: 'formulartexte', label: 'Formulartexte', icon: FileCheck },
  { id: 'bemerkungen', label: 'Bemerkungen', icon: MessageSquare },
];

// Status-Farben
const statusColors: Record<string, { bg: string; text: string; icon: any }> = {
  OFFEN: { bg: 'bg-blue-100', text: 'text-blue-700', icon: Clock },
  AKTIV: { bg: 'bg-green-100', text: 'text-green-700', icon: CheckCircle },
  TEILERFUELLT: { bg: 'bg-yellow-100', text: 'text-yellow-700', icon: AlertTriangle },
  ERFUELLT: { bg: 'bg-emerald-100', text: 'text-emerald-700', icon: CheckCircle },
  STORNO: { bg: 'bg-red-100', text: 'text-red-700', icon: Ban },
  ABGELAUFEN: { bg: 'bg-gray-100', text: 'text-gray-700', icon: Clock },
};

// ========================== POSITIONS-DIALOG ==========================
function PositionDialog({ 
  open, 
  onClose, 
  onSave, 
  position, 
  waehrung 
}: { 
  open: boolean; 
  onClose: () => void; 
  onSave: (pos: Partial<Position>) => void;
  position?: Position | null;
  waehrung: string;
}) {
  const [formData, setFormData] = useState<Partial<Position>>(
    position || {
      position_typ: 'ARTIKEL',
      einheitkurz: 't',
      waehrungskurs: 1,
      mengen_toleranz_prozent: 10,
      ueberliefer_ok: true,
      position_abgeschlossen: false,
    }
  );

  const handleSave = () => {
    // Gesamtpreis berechnen
    if (formData.anzahl && formData.einzelpreis) {
      formData.gesamtpreis = Math.round(formData.anzahl * formData.einzelpreis * 100) / 100;
    }
    if (formData.anzahl && formData.einzelpreis_fw) {
      formData.gesamtpreis_fw = Math.round(formData.anzahl * formData.einzelpreis_fw * 100) / 100;
    }
    onSave(formData);
    onClose();
  };

  const selectedWaehrung = getWaehrung(formData.waehrung_fremd_kurz || waehrung);

  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent className="max-w-2xl max-h-[90vh] overflow-y-auto">
        <DialogHeader>
          <DialogTitle>
            {position ? 'Position bearbeiten' : 'Neue Position'}
          </DialogTitle>
        </DialogHeader>

        <div className="space-y-4 py-4">
          {/* Artikel */}
          <div className="space-y-3">
            <h4 className="font-medium text-sm text-gray-700 flex items-center gap-2">
              <Package className="h-4 w-4" />
              Artikel
            </h4>
            <div className="grid grid-cols-4 gap-3">
              <div>
                <Label className="text-xs">Pos.Nr</Label>
                <Input 
                  type="number"
                  value={formData.positionsnummer || 1}
                  onChange={(e) => setFormData({ ...formData, positionsnummer: parseInt(e.target.value) })}
                />
              </div>
              <div className="col-span-3">
                <Label className="text-xs">Artikel *</Label>
                <SmartInput
                  module="kontrakte"
                  fieldName="positionen.id_artikel"
                  value={formData.id_artikel || ''}
                  onChange={(val, item) => {
                    setFormData({ 
                      ...formData, 
                      id_artikel: val || '',
                      anr1: item?.anr1 || '',
                      artbez1: item?.artbez1 || item?.label || ''
                    });
                  }}
                  placeholder="Artikel auswählen..."
                />
              </div>
            </div>
            <div>
              <Label className="text-xs">Artikelbezeichnung</Label>
              <Input 
                value={formData.artbez1 || ''}
                onChange={(e) => setFormData({ ...formData, artbez1: e.target.value })}
              />
            </div>
          </div>

          {/* Mengen & Preise */}
          <div className="space-y-3">
            <h4 className="font-medium text-sm text-gray-700 flex items-center gap-2">
              <Scale className="h-4 w-4" />
              Mengen & Preise
            </h4>
            <div className="grid grid-cols-4 gap-3">
              <div>
                <Label className="text-xs">Menge</Label>
                <Input 
                  type="number"
                  step="0.01"
                  value={formData.anzahl || ''}
                  onChange={(e) => setFormData({ ...formData, anzahl: parseFloat(e.target.value) || undefined })}
                />
              </div>
              <div>
                <Label className="text-xs">Einheit</Label>
                <SmartInput
                  module="kontrakte"
                  fieldName="positionen.einheitkurz"
                  value={formData.einheitkurz || 't'}
                  onChange={(val) => setFormData({ ...formData, einheitkurz: val || 't' })}
                />
              </div>
              <div>
                <Label className="text-xs">Einzelpreis ({selectedWaehrung.symbol})</Label>
                <Input 
                  type="number"
                  step="0.01"
                  value={formData.einzelpreis || ''}
                  onChange={(e) => setFormData({ ...formData, einzelpreis: parseFloat(e.target.value) || undefined })}
                />
              </div>
              <div>
                <Label className="text-xs">Gesamtpreis</Label>
                <Input 
                  type="number"
                  value={formData.anzahl && formData.einzelpreis 
                    ? (formData.anzahl * formData.einzelpreis).toFixed(2) 
                    : ''}
                  disabled
                  className="bg-gray-50"
                />
              </div>
            </div>

            {/* Toleranz */}
            <div className="grid grid-cols-2 gap-3">
              <div>
                <Label className="text-xs flex items-center gap-1">
                  <Percent className="h-3 w-3" />
                  Mengentoleranz (%)
                </Label>
                <Input 
                  type="number"
                  min="0"
                  max="100"
                  value={formData.mengen_toleranz_prozent || 10}
                  onChange={(e) => setFormData({ ...formData, mengen_toleranz_prozent: parseFloat(e.target.value) })}
                />
              </div>
              <div className="flex items-end pb-2">
                <div className="flex items-center gap-2">
                  <Switch
                    checked={formData.ueberliefer_ok !== false}
                    onCheckedChange={(v) => setFormData({ ...formData, ueberliefer_ok: v })}
                  />
                  <Label className="text-sm">Überlieferung erlaubt</Label>
                </div>
              </div>
            </div>
          </div>

          {/* Lieferung */}
          <div className="space-y-3">
            <h4 className="font-medium text-sm text-gray-700 flex items-center gap-2">
              <Truck className="h-4 w-4" />
              Lieferung
            </h4>
            <div>
              <Label className="text-xs">Abweichender Lieferort</Label>
              <SmartInput
                module="kontrakte"
                fieldName="positionen.lieferort"
                value={formData.lieferort || ''}
                onChange={(val, item) => setFormData({ 
                  ...formData, 
                  lieferort: val || '',
                  lieferort_name: item?.name1 || ''
                })}
                placeholder="Adresse auswählen..."
              />
            </div>
            <div className="grid grid-cols-2 gap-3">
              <div>
                <Label className="text-xs">Gültig von</Label>
                <Input 
                  type="date"
                  value={formData.gueltig_von || ''}
                  onChange={(e) => setFormData({ ...formData, gueltig_von: e.target.value })}
                />
              </div>
              <div>
                <Label className="text-xs">Gültig bis</Label>
                <Input 
                  type="date"
                  value={formData.gueltig_bis || ''}
                  onChange={(e) => setFormData({ ...formData, gueltig_bis: e.target.value })}
                />
              </div>
            </div>
          </div>

          {/* Bemerkung */}
          <div>
            <Label className="text-xs">Bemerkung zur Position</Label>
            <Textarea 
              value={formData.bemerkung || ''}
              onChange={(e) => setFormData({ ...formData, bemerkung: e.target.value })}
              rows={2}
            />
          </div>
        </div>

        <DialogFooter>
          <Button variant="outline" onClick={onClose}>Abbrechen</Button>
          <Button onClick={handleSave}>Speichern</Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}

// ========================== MAIN COMPONENT ==========================
export function KontraktePage() {
  const queryClient = useQueryClient();
  const [selectedKontrakt, setSelectedKontrakt] = useState<Kontrakt | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [isNewRecord, setIsNewRecord] = useState(false);
  const [activeSection, setActiveSection] = useState('kopf');
  const [filterTyp, setFilterTyp] = useState<string>('');
  const [showPositionDialog, setShowPositionDialog] = useState(false);
  const [editingPosition, setEditingPosition] = useState<Position | null>(null);
  
  const { panelWidth, isDragging, containerRef, startDragging, leftPanelStyle, rightPanelStyle } = useResizablePanel();

  const { register, handleSubmit, reset, setValue, watch, formState: { errors } } = useForm<KontraktForm>({
    resolver: zodResolver(kontraktSchema),
    defaultValues: {
      vorgang_typ: 'EK',
      waehrung_kurz: 'EUR',
      waehrungskurs: 1,
      status: 'OFFEN',
      aktiv: true,
      ist_fixierung: false,
    }
  });

  const watchFields = watch();

  // Queries
  const { data: kontrakteData, isLoading } = useQuery({
    queryKey: ['kontrakte'],
    queryFn: async () => {
      const response = await kontrakteApi.list();
      return response.data;
    }
  });

  const filteredData = useMemo(() => {
    if (!kontrakteData?.data) return [];
    if (!filterTyp) return kontrakteData.data;
    return kontrakteData.data.filter((k: Kontrakt) => k.vorgang_typ === filterTyp);
  }, [kontrakteData, filterTyp]);

  // Mutations
  const createMutation = useMutation({
    mutationFn: (data: KontraktForm) => kontrakteApi.create(data),
    onSuccess: (response) => {
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
      toast.success('Kontrakt erfolgreich erstellt');
      if (response.data?.data) {
        setSelectedKontrakt(response.data.data);
        Object.entries(response.data.data).forEach(([key, value]) => {
          if (key in kontraktSchema.shape) {
            setValue(key as keyof KontraktForm, value as any);
          }
        });
      }
      setIsNewRecord(false);
      setIsEditing(false);
    },
    onError: (error: any) => {
      const detail = error.response?.data?.detail;
      if (detail?.errors) {
        detail.errors.forEach((e: string) => toast.error(e));
      } else if (detail?.warnings) {
        detail.warnings.forEach((w: string) => toast.warning(w));
      } else {
        toast.error(typeof detail === 'string' ? detail : 'Fehler beim Erstellen');
      }
    }
  });

  const updateMutation = useMutation({
    mutationFn: ({ id, data }: { id: string; data: Partial<KontraktForm> }) => 
      kontrakteApi.update(id, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
      toast.success('Kontrakt erfolgreich aktualisiert');
      setIsEditing(false);
    },
    onError: (error: any) => {
      const detail = error.response?.data?.detail;
      if (detail?.errors) {
        detail.errors.forEach((e: string) => toast.error(e));
      } else {
        toast.error('Fehler beim Aktualisieren');
      }
    }
  });

  const deleteMutation = useMutation({
    mutationFn: (id: string) => kontrakteApi.delete(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
      toast.success('Kontrakt erfolgreich gelöscht');
      setSelectedKontrakt(null);
    },
    onError: (error: any) => {
      const detail = error.response?.data?.detail;
      if (detail?.errors) {
        detail.errors.forEach((e: string) => toast.error(e));
      } else {
        toast.error('Fehler beim Löschen');
      }
    }
  });

  // Handlers
  const onSubmit = async (data: KontraktForm) => {
    // Positionen beibehalten
    const submitData = {
      ...data,
      positionen: selectedKontrakt?.positionen || []
    };
    
    if (selectedKontrakt && !isNewRecord) {
      updateMutation.mutate({ id: selectedKontrakt.id, data: submitData });
    } else {
      createMutation.mutate(submitData as any);
    }
  };

  const handleSave = () => {
    handleSubmit(onSubmit, (validationErrors) => {
      Object.values(validationErrors).forEach((err: any) => {
        if (err?.message) toast.error(err.message);
      });
    })();
  };

  const handleNewKontrakt = () => {
    const today = new Date().toISOString().split('T')[0];
    const emptyKontrakt: Kontrakt = {
      id: 'NEU',
      vorgang_typ: 'EK',
      name1: '',
      waehrung_kurz: 'EUR',
      waehrungskurs: 1,
      status: 'OFFEN',
      aktiv: true,
      erstellungsdatum: today,
      ist_fixierung: false,
      positionen: [],
    };
    setSelectedKontrakt(emptyKontrakt);
    reset(emptyKontrakt);
    setIsNewRecord(true);
    setIsEditing(true);
    setActiveSection('kopf');
  };

  const handleClose = () => {
    setSelectedKontrakt(null);
    setIsEditing(false);
    setIsNewRecord(false);
    reset();
  };

  const handleCancel = () => {
    if (isNewRecord) {
      handleClose();
    } else {
      setIsEditing(false);
      if (selectedKontrakt) {
        Object.entries(selectedKontrakt).forEach(([key, value]) => {
          if (key in kontraktSchema.shape) {
            setValue(key as keyof KontraktForm, value as any);
          }
        });
      }
    }
  };

  const openDetail = (kontrakt: Kontrakt) => {
    setSelectedKontrakt(kontrakt);
    setIsNewRecord(false);
    setIsEditing(false);
    setActiveSection('kopf');
    Object.entries(kontrakt).forEach(([key, value]) => {
      if (key in kontraktSchema.shape) {
        setValue(key as keyof KontraktForm, value as any);
      }
    });
  };

  // Position handlers
  const handleAddPosition = () => {
    setEditingPosition(null);
    setShowPositionDialog(true);
  };

  const handleEditPosition = (pos: Position) => {
    setEditingPosition(pos);
    setShowPositionDialog(true);
  };

  const handleSavePosition = (posData: Partial<Position>) => {
    if (!selectedKontrakt) return;
    
    let newPositionen = [...(selectedKontrakt.positionen || [])];
    
    if (editingPosition) {
      // Update existing
      newPositionen = newPositionen.map(p => 
        p.id === editingPosition.id ? { ...p, ...posData } : p
      );
    } else {
      // Add new
      const maxNr = Math.max(...newPositionen.map(p => p.positionsnummer || 0), 0);
      newPositionen.push({
        id: `temp-${Date.now()}`,
        positionsnummer: maxNr + 1,
        position_typ: 'ARTIKEL',
        einheitkurz: 't',
        waehrungskurs: 1,
        mengen_toleranz_prozent: 10,
        ueberliefer_ok: true,
        position_abgeschlossen: false,
        ...posData,
      } as Position);
    }
    
    setSelectedKontrakt({ ...selectedKontrakt, positionen: newPositionen });
  };

  const handleDeletePosition = (posId: string) => {
    if (!selectedKontrakt || !confirm('Position wirklich löschen?')) return;
    
    const newPositionen = (selectedKontrakt.positionen || []).filter(p => p.id !== posId);
    setSelectedKontrakt({ ...selectedKontrakt, positionen: newPositionen });
  };

  // Table columns
  const columns: ColumnDef<Kontrakt>[] = useMemo(() => [
    {
      accessorKey: 'buchungsnummer',
      header: 'Nr.',
      cell: ({ row }) => (
        <div className="flex items-center gap-2">
          <span className="font-mono text-sm font-semibold">
            {row.original.buchungsnummer || '-'}
          </span>
          {row.original.ist_fixierung && (
            <Badge variant="outline" className="text-xs bg-purple-50 text-purple-700 border-purple-200">
              <TrendingUp className="h-3 w-3 mr-1" />
              FIX
            </Badge>
          )}
        </div>
      )
    },
    {
      accessorKey: 'vorgang_typ',
      header: 'Typ',
      cell: ({ row }) => (
        <div className="flex items-center gap-1">
          {row.original.vorgang_typ === 'EK' ? (
            <>
              <ArrowDownToLine className="h-4 w-4 text-green-600" />
              <span className="text-sm font-medium text-green-700">EK</span>
            </>
          ) : (
            <>
              <ArrowUpFromLine className="h-4 w-4 text-blue-600" />
              <span className="text-sm font-medium text-blue-700">VK</span>
            </>
          )}
        </div>
      )
    },
    {
      accessorKey: 'name1',
      header: 'Vertragspartner',
      cell: ({ row }) => (
        <div>
          <div className="font-medium">{row.original.name1}</div>
          <div className="text-xs text-gray-500">
            {[row.original.plz, row.original.ort].filter(Boolean).join(' ')}
          </div>
        </div>
      )
    },
    {
      accessorKey: 'gueltig_bis',
      header: 'Gültig bis',
      cell: ({ row }) => (
        <span className="text-sm">
          {row.original.gueltig_bis 
            ? new Date(row.original.gueltig_bis).toLocaleDateString('de-DE')
            : '-'
          }
        </span>
      )
    },
    {
      accessorKey: 'waehrung_kurz',
      header: 'Währung',
      cell: ({ row }) => {
        const w = getWaehrung(row.original.waehrung_kurz);
        return (
          <Badge variant="secondary" className="font-mono">
            {w.symbol} {w.code}
          </Badge>
        );
      }
    },
    {
      accessorKey: 'status',
      header: 'Status',
      cell: ({ row }) => {
        const status = row.original.status || 'OFFEN';
        const colors = statusColors[status] || statusColors.OFFEN;
        const Icon = colors.icon;
        return (
          <Badge className={cn(colors.bg, colors.text, "gap-1")}>
            <Icon className="h-3 w-3" />
            {status}
          </Badge>
        );
      }
    },
    {
      accessorKey: 'summen',
      header: 'Positionen',
      cell: ({ row }) => (
        <span className="text-sm">
          {row.original.summen?.anzahl_positionen || row.original.positionen?.length || 0}
        </span>
      )
    },
    {
      id: 'actions',
      cell: ({ row }) => (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" size="icon" className="h-8 w-8">
              <MoreHorizontal className="h-4 w-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuItem onClick={() => openDetail(row.original)}>
              <Eye className="h-4 w-4 mr-2" />Anzeigen
            </DropdownMenuItem>
            <DropdownMenuItem onClick={() => { openDetail(row.original); setIsEditing(true); }}>
              <Pencil className="h-4 w-4 mr-2" />Bearbeiten
            </DropdownMenuItem>
            <DropdownMenuSeparator />
            <DropdownMenuItem 
              className="text-red-600"
              onClick={() => {
                if (confirm('Kontrakt wirklich löschen?')) {
                  deleteMutation.mutate(row.original.id);
                }
              }}
            >
              <Trash2 className="h-4 w-4 mr-2" />Löschen
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      )
    }
  ], [deleteMutation]);

  const selectedWaehrung = getWaehrung(watchFields.waehrung_kurz);

  return (
    <div className="h-full flex flex-col">
      {/* Header */}
      <div className="bg-white border-b border-gray-200 px-6 py-4">
        <div className="flex items-center justify-between">
          <div>
            <h1 className="text-xl font-semibold text-gray-900">Kontrakte</h1>
            <p className="text-sm text-gray-500 mt-0.5">Einkaufs- und Verkaufskontrakte verwalten</p>
          </div>
          <div className="flex items-center gap-3">
            <Select value={filterTyp || "ALL"} onValueChange={(v) => setFilterTyp(v === "ALL" ? "" : v)}>
              <SelectTrigger className="w-[150px]">
                <SelectValue placeholder="Alle Typen" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="ALL">Alle Typen</SelectItem>
                <SelectItem value="EK">Einkauf</SelectItem>
                <SelectItem value="VK">Verkauf</SelectItem>
              </SelectContent>
            </Select>
            <Button onClick={handleNewKontrakt} className="bg-emerald-600 hover:bg-emerald-700" data-testid="new-kontrakt-btn">
              <Plus className="h-4 w-4 mr-2" />
              Neuer Kontrakt
            </Button>
          </div>
        </div>
      </div>

      {/* Content */}
      <div ref={containerRef} className="flex-1 flex overflow-hidden">
        {/* Table */}
        <div className="p-6 overflow-auto transition-none" style={selectedKontrakt ? leftPanelStyle : { width: '100%' }}>
          <div className="bg-white rounded-xl shadow-sm border">
            <DataTable
              columns={columns}
              data={filteredData}
              searchKey="name1"
              searchPlaceholder="Vertragspartner suchen..."
              onRowDoubleClick={openDetail}
            />
          </div>
        </div>

        {selectedKontrakt && <ResizeHandle isDragging={isDragging} onMouseDown={startDragging} />}

        {/* Detail Panel */}
        <AnimatePresence>
          {selectedKontrakt && (
            <motion.div
              initial={{ x: '100%', opacity: 0 }}
              animate={{ x: 0, opacity: 1 }}
              exit={{ x: '100%', opacity: 0 }}
              transition={{ type: 'spring', damping: 25, stiffness: 200 }}
              className="border-l border-gray-200 bg-white flex flex-col overflow-hidden"
              style={rightPanelStyle}
            >
              {/* Panel Header */}
              <div className="flex items-center justify-between p-4 border-b bg-gray-50">
                <div className="flex items-center gap-3">
                  <div className={cn(
                    "h-10 w-10 rounded-lg flex items-center justify-center",
                    watchFields.vorgang_typ === 'EK' ? "bg-green-100" : "bg-blue-100"
                  )}>
                    {watchFields.vorgang_typ === 'EK' ? (
                      <ArrowDownToLine className="h-5 w-5 text-green-600" />
                    ) : (
                      <ArrowUpFromLine className="h-5 w-5 text-blue-600" />
                    )}
                  </div>
                  <div>
                    <div className="flex items-center gap-2">
                      <h2 className="font-bold text-lg">
                        {isNewRecord ? 'Neuer Kontrakt' : (selectedKontrakt.buchungsnummer || 'Kontrakt')}
                      </h2>
                      {watchFields.ist_fixierung && (
                        <Badge className="bg-purple-100 text-purple-700">
                          <TrendingUp className="h-3 w-3 mr-1" />
                          Fixierung
                        </Badge>
                      )}
                    </div>
                    <div className="flex items-center gap-2">
                      <span className="text-sm text-gray-500">
                        {watchFields.vorgang_typ === 'EK' ? 'Einkaufskontrakt' : 'Verkaufskontrakt'}
                      </span>
                      <Badge className={cn(
                        statusColors[watchFields.status || 'OFFEN']?.bg,
                        statusColors[watchFields.status || 'OFFEN']?.text
                      )}>
                        {watchFields.status || 'OFFEN'}
                      </Badge>
                      {selectedKontrakt.abgeschlossen && (
                        <Lock className="h-4 w-4 text-gray-500" />
                      )}
                    </div>
                  </div>
                </div>
                <div className="flex items-center gap-2">
                  {isEditing ? (
                    <>
                      <Button variant="outline" size="sm" onClick={handleCancel}>Abbrechen</Button>
                      <Button size="sm" onClick={handleSave} className="bg-emerald-600 hover:bg-emerald-700">
                        <Save className="h-4 w-4 mr-1" />Speichern
                      </Button>
                    </>
                  ) : (
                    <Button variant="outline" size="sm" onClick={() => setIsEditing(true)} disabled={selectedKontrakt.abgeschlossen}>
                      <Pencil className="h-4 w-4 mr-1" />Bearbeiten
                    </Button>
                  )}
                  <Button variant="ghost" size="icon" onClick={handleClose}>
                    <X className="h-5 w-5" />
                  </Button>
                </div>
              </div>

              {/* Sidebar + Content */}
              <div className="flex flex-1 overflow-hidden">
                <div className="w-44 bg-gray-50 border-r p-2 space-y-1">
                  {sidebarSections.map((section) => (
                    <button
                      key={section.id}
                      onClick={() => setActiveSection(section.id)}
                      className={cn(
                        "w-full flex items-center gap-2 px-3 py-2 rounded-lg text-sm transition-colors",
                        activeSection === section.id
                          ? "bg-emerald-100 text-emerald-700 font-medium"
                          : "text-gray-600 hover:bg-gray-100",
                        section.id === 'fixierung' && !watchFields.ist_fixierung && "opacity-50"
                      )}
                    >
                      <section.icon className="h-4 w-4" />
                      {section.label}
                    </button>
                  ))}
                </div>

                <ScrollArea className="flex-1 p-4">
                  <form onSubmit={handleSubmit(onSubmit)}>
                    
                    {/* === KOPFDATEN === */}
                    {activeSection === 'kopf' && (
                      <div className="space-y-4">
                        <div className="grid grid-cols-2 gap-4">
                          <div className="space-y-1.5">
                            <Label>Kontrakttyp</Label>
                            <Select value={watchFields.vorgang_typ || 'EK'} onValueChange={(v) => setValue('vorgang_typ', v)} disabled={!isEditing}>
                              <SelectTrigger><SelectValue /></SelectTrigger>
                              <SelectContent>
                                <SelectItem value="EK">Einkaufskontrakt</SelectItem>
                                <SelectItem value="VK">Verkaufskontrakt</SelectItem>
                              </SelectContent>
                            </Select>
                          </div>
                          <div className="space-y-1.5">
                            <Label>Buchungsnummer</Label>
                            <Input {...register('buchungsnummer')} disabled={!isEditing} className="font-mono" />
                          </div>
                        </div>
                        <div className="grid grid-cols-2 gap-4">
                          <div className="space-y-1.5">
                            <Label>Status</Label>
                            <Select value={watchFields.status || 'OFFEN'} onValueChange={(v) => setValue('status', v)} disabled={!isEditing}>
                              <SelectTrigger><SelectValue /></SelectTrigger>
                              <SelectContent>
                                <SelectItem value="OFFEN">Offen</SelectItem>
                                <SelectItem value="AKTIV">Aktiv</SelectItem>
                                <SelectItem value="TEILERFUELLT">Teilerfüllt</SelectItem>
                                <SelectItem value="ERFUELLT">Erfüllt</SelectItem>
                                <SelectItem value="STORNO">Storniert</SelectItem>
                                <SelectItem value="ABGELAUFEN">Abgelaufen</SelectItem>
                              </SelectContent>
                            </Select>
                          </div>
                          <div className="flex items-end pb-2 gap-4">
                            <div className="flex items-center gap-2">
                              <Switch checked={watchFields.ist_fixierung || false} onCheckedChange={(v) => setValue('ist_fixierung', v)} disabled={!isEditing} />
                              <Label className="text-sm">Fixierungskontrakt</Label>
                            </div>
                          </div>
                        </div>
                      </div>
                    )}

                    {/* === VERTRAGSPARTNER === */}
                    {activeSection === 'partner' && (
                      <div className="space-y-4">
                        <div className="space-y-1.5">
                          <Label>Adresse auswählen</Label>
                          <SmartInput
                            module="kontrakte"
                            fieldName="id_adresse"
                            value={watchFields.id_adresse || ''}
                            onChange={(val, item) => {
                              setValue('id_adresse', val || '');
                              if (item) {
                                setValue('name1', item.name1 || '');
                                setValue('name2', item.name2 || '');
                                setValue('strasse', item.strasse || '');
                                setValue('plz', item.plz || '');
                                setValue('ort', item.ort || '');
                                setValue('land', item.land || '');
                                setValue('telefon', item.telefon || '');
                                setValue('telefax', item.telefax || '');
                                setValue('email', item.email || '');
                              }
                            }}
                            disabled={!isEditing}
                            placeholder="Adresse suchen..."
                          />
                        </div>
                        <div className="space-y-1.5">
                          <Label>Firma/Name *</Label>
                          <Input {...register('name1')} disabled={!isEditing} />
                          {errors.name1 && <p className="text-xs text-red-500">{errors.name1.message}</p>}
                        </div>
                        <div className="space-y-1.5">
                          <Label>Name 2 / Abteilung</Label>
                          <Input {...register('name2')} disabled={!isEditing} />
                        </div>
                        <div className="grid grid-cols-3 gap-4">
                          <div className="col-span-2 space-y-1.5">
                            <Label>Straße</Label>
                            <Input {...register('strasse')} disabled={!isEditing} />
                          </div>
                          <div className="space-y-1.5">
                            <Label>Hausnummer</Label>
                            <Input {...register('hausnummer')} disabled={!isEditing} />
                          </div>
                        </div>
                        <div className="grid grid-cols-3 gap-4">
                          <div className="space-y-1.5">
                            <Label>PLZ</Label>
                            <Input {...register('plz')} disabled={!isEditing} />
                          </div>
                          <div className="col-span-2 space-y-1.5">
                            <Label>Ort</Label>
                            <Input {...register('ort')} disabled={!isEditing} />
                          </div>
                        </div>
                        <div className="space-y-1.5">
                          <Label>Land</Label>
                          <Input {...register('land')} disabled={!isEditing} />
                        </div>
                        <div className="grid grid-cols-2 gap-4 pt-4 border-t">
                          <div className="space-y-1.5">
                            <Label>Telefon</Label>
                            <Input {...register('telefon')} disabled={!isEditing} />
                          </div>
                          <div className="space-y-1.5">
                            <Label>Telefax</Label>
                            <Input {...register('telefax')} disabled={!isEditing} />
                          </div>
                        </div>
                        <div className="space-y-1.5">
                          <Label>E-Mail</Label>
                          <Input {...register('email')} type="email" disabled={!isEditing} />
                        </div>
                      </div>
                    )}

                    {/* === TERMINE === */}
                    {activeSection === 'termine' && (
                      <div className="space-y-4">
                        <div className="space-y-1.5">
                          <Label>Erstellungsdatum</Label>
                          <Input {...register('erstellungsdatum')} type="date" disabled={!isEditing} />
                        </div>
                        <div className="grid grid-cols-2 gap-4">
                          <div className="space-y-1.5">
                            <Label>Gültig von</Label>
                            <Input {...register('gueltig_von')} type="date" disabled={!isEditing} />
                          </div>
                          <div className="space-y-1.5">
                            <Label>Gültig bis</Label>
                            <Input {...register('gueltig_bis')} type="date" disabled={!isEditing} />
                          </div>
                        </div>
                        <div className="pt-4 space-y-4 border-t">
                          <h3 className="font-semibold text-gray-900">Interner Bearbeiter</h3>
                          <div className="space-y-1.5">
                            <Label>Name</Label>
                            <Input {...register('name_bearbeiter_intern')} disabled={!isEditing} />
                          </div>
                          <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-1.5">
                              <Label>Telefon</Label>
                              <Input {...register('tel_bearbeiter_intern')} disabled={!isEditing} />
                            </div>
                            <div className="space-y-1.5">
                              <Label>Fax</Label>
                              <Input {...register('fax_bearbeiter_intern')} disabled={!isEditing} />
                            </div>
                          </div>
                        </div>
                      </div>
                    )}

                    {/* === KONDITIONEN === */}
                    {activeSection === 'konditionen' && (
                      <div className="space-y-4">
                        <div className="grid grid-cols-2 gap-4">
                          <div className="space-y-1.5">
                            <Label>Währung</Label>
                            <Select value={watchFields.waehrung_kurz || 'EUR'} onValueChange={(v) => setValue('waehrung_kurz', v)} disabled={!isEditing}>
                              <SelectTrigger>
                                <SelectValue>
                                  <span className="flex items-center gap-2">
                                    <span className="font-mono">{selectedWaehrung.symbol}</span>
                                    {selectedWaehrung.code}
                                  </span>
                                </SelectValue>
                              </SelectTrigger>
                              <SelectContent>
                                {WAEHRUNGEN.map((w) => (
                                  <SelectItem key={w.code} value={w.code}>
                                    <span className="flex items-center gap-2">
                                      <span className="font-mono font-semibold w-8">{w.symbol}</span>
                                      {w.code} - {w.name}
                                    </span>
                                  </SelectItem>
                                ))}
                              </SelectContent>
                            </Select>
                          </div>
                          <div className="space-y-1.5">
                            <Label>Währungskurs</Label>
                            <Input type="number" step="0.0001" {...register('waehrungskurs', { valueAsNumber: true })} disabled={!isEditing} />
                          </div>
                        </div>
                        <div className="space-y-1.5">
                          <Label>Zahlungsbedingung</Label>
                          <SmartInput module="kontrakte" fieldName="zahlungsbedingung" value={watch('zahlungsbedingung')} onChange={(val) => setValue('zahlungsbedingung', val || '')} disabled={!isEditing} placeholder="z.B. 30 Tage netto" />
                        </div>
                        <div className="space-y-1.5">
                          <Label>Lieferbedingung</Label>
                          <SmartInput module="kontrakte" fieldName="lieferbedingung" value={watch('lieferbedingung')} onChange={(val) => setValue('lieferbedingung', val || '')} disabled={!isEditing} placeholder="z.B. FCA, DAP, CIF" />
                        </div>
                      </div>
                    )}

                    {/* === FIXIERUNG === */}
                    {activeSection === 'fixierung' && (
                      <div className="space-y-4">
                        {!watchFields.ist_fixierung ? (
                          <div className="text-center py-8 text-gray-500 bg-gray-50 rounded-lg border-2 border-dashed">
                            <TrendingUp className="h-12 w-12 mx-auto mb-2 text-gray-300" />
                            <p>Kein Fixierungskontrakt</p>
                            <p className="text-sm mt-1">Aktivieren Sie "Fixierungskontrakt" in den Kopfdaten</p>
                          </div>
                        ) : (
                          <>
                            <div className="p-3 bg-purple-50 rounded-lg border border-purple-200">
                              <p className="text-sm text-purple-700">
                                <TrendingUp className="h-4 w-4 inline mr-1" />
                                Fixierungskontrakte ermöglichen die Festlegung von Börsenpreisen für einen bestimmten Zeitraum.
                              </p>
                            </div>
                            <div className="grid grid-cols-2 gap-4">
                              <div className="space-y-1.5">
                                <Label>Fixierung von</Label>
                                <Input {...register('fix_von')} type="date" disabled={!isEditing} />
                              </div>
                              <div className="space-y-1.5">
                                <Label>Fixierung bis</Label>
                                <Input {...register('fix_bis')} type="date" disabled={!isEditing} />
                              </div>
                            </div>
                            <div className="grid grid-cols-2 gap-4">
                              <div className="space-y-1.5">
                                <Label>Fixierungsmenge (gesamt)</Label>
                                <Input type="number" step="0.01" {...register('fix_menge_gesamt', { valueAsNumber: true })} disabled={!isEditing} />
                              </div>
                              <div className="space-y-1.5">
                                <Label>Einheit</Label>
                                <SmartInput module="kontrakte" fieldName="fix_einheit" value={watch('fix_einheit')} onChange={(val) => setValue('fix_einheit', val || '')} disabled={!isEditing} placeholder="t" />
                              </div>
                            </div>
                            <div className="space-y-1.5">
                              <Label>Hauptartikel für Fixierung</Label>
                              <SmartInput module="kontrakte" fieldName="fix_id_artikel" value={watch('fix_id_artikel')} onChange={(val) => setValue('fix_id_artikel', val || '')} disabled={!isEditing} placeholder="Artikel auswählen..." />
                            </div>
                            <div className="grid grid-cols-2 gap-4">
                              <div className="space-y-1.5">
                                <Label>Börsendifferenz absolut ({selectedWaehrung.symbol}/t)</Label>
                                <Input type="number" step="0.01" {...register('boerse_diff_abs', { valueAsNumber: true })} disabled={!isEditing} />
                              </div>
                              <div className="space-y-1.5">
                                <Label>Börsendifferenz prozentual (%)</Label>
                                <Input type="number" step="0.01" {...register('boerse_diff_proz', { valueAsNumber: true })} disabled={!isEditing} />
                              </div>
                            </div>
                            <div className="space-y-1.5">
                              <Label>Fixierungs-Bemerkung</Label>
                              <Textarea {...register('bemerkung_fix1')} disabled={!isEditing} rows={3} />
                            </div>
                          </>
                        )}
                      </div>
                    )}

                    {/* === POSITIONEN === */}
                    {activeSection === 'positionen' && (
                      <div className="space-y-4">
                        <div className="flex items-center justify-between">
                          <h3 className="font-semibold text-gray-900">Kontraktpositionen</h3>
                          {isEditing && (
                            <Button type="button" size="sm" variant="outline" onClick={handleAddPosition}>
                              <Plus className="h-4 w-4 mr-1" />Position hinzufügen
                            </Button>
                          )}
                        </div>
                        
                        {selectedKontrakt.positionen && selectedKontrakt.positionen.length > 0 ? (
                          <div className="space-y-3">
                            {selectedKontrakt.positionen.map((pos) => (
                              <div key={pos.id} className={cn(
                                "p-4 rounded-lg border bg-white hover:shadow-sm transition-shadow",
                                pos.position_abgeschlossen && "bg-gray-50 opacity-75"
                              )}>
                                <div className="flex items-start justify-between">
                                  <div className="flex-1">
                                    <div className="flex items-center gap-2 mb-1">
                                      <span className="font-semibold text-emerald-600">Pos. {pos.positionsnummer}</span>
                                      <span className="text-sm text-gray-500 font-mono">{pos.anr1}</span>
                                      {pos.position_abgeschlossen && (
                                        <Badge variant="secondary" className="text-xs">
                                          <Lock className="h-3 w-3 mr-1" />Abgeschlossen
                                        </Badge>
                                      )}
                                      {!pos.ueberliefer_ok && (
                                        <Badge variant="outline" className="text-xs text-orange-600 border-orange-300">
                                          <AlertTriangle className="h-3 w-3 mr-1" />Keine Überlieferung
                                        </Badge>
                                      )}
                                    </div>
                                    <div className="text-sm font-medium">{pos.artbez1}</div>
                                    <div className="flex items-center gap-4 mt-2 text-sm text-gray-600">
                                      <span className="font-medium">{pos.anzahl?.toLocaleString('de-DE')} {pos.einheitkurz}</span>
                                      <span>{pos.einzelpreis?.toFixed(2)} {selectedWaehrung.symbol}/{pos.einheitkurz}</span>
                                      <span className="font-semibold text-gray-900">= {pos.gesamtpreis?.toLocaleString('de-DE', { minimumFractionDigits: 2 })} {selectedWaehrung.symbol}</span>
                                    </div>
                                    {pos.lieferort_name && (
                                      <div className="text-xs text-gray-500 mt-1">
                                        <Truck className="h-3 w-3 inline mr-1" />
                                        Lieferort: {pos.lieferort_name}
                                      </div>
                                    )}
                                  </div>
                                  {isEditing && (
                                    <div className="flex gap-1">
                                      <Button variant="ghost" size="icon" className="h-8 w-8" onClick={() => handleEditPosition(pos)}>
                                        <Pencil className="h-4 w-4" />
                                      </Button>
                                      <Button variant="ghost" size="icon" className="h-8 w-8 text-red-600" onClick={() => handleDeletePosition(pos.id)}>
                                        <Trash2 className="h-4 w-4" />
                                      </Button>
                                    </div>
                                  )}
                                </div>
                              </div>
                            ))}
                          </div>
                        ) : (
                          <div className="text-center py-8 text-gray-500 bg-gray-50 rounded-lg border-2 border-dashed">
                            <Package className="h-12 w-12 mx-auto mb-2 text-gray-300" />
                            <p>Keine Positionen vorhanden</p>
                            {isEditing && (
                              <Button variant="link" size="sm" className="mt-2" onClick={handleAddPosition}>
                                Erste Position hinzufügen
                              </Button>
                            )}
                          </div>
                        )}
                      </div>
                    )}

                    {/* === FORMULARTEXTE === */}
                    {activeSection === 'formulartexte' && (
                      <div className="space-y-4">
                        <div className="space-y-1.5">
                          <Label>Text am Anfang des Formulars</Label>
                          <Textarea {...register('formulartext_anfang')} disabled={!isEditing} rows={5} placeholder="Dieser Text erscheint vor den Positionen..." />
                        </div>
                        <div className="space-y-1.5">
                          <Label>Text am Ende des Formulars</Label>
                          <Textarea {...register('formulartext_ende')} disabled={!isEditing} rows={5} placeholder="Dieser Text erscheint nach den Positionen..." />
                        </div>
                        <div className="flex items-center gap-2 pt-2">
                          <Switch checked={watchFields.kopie_bemerkung_auf_pos || false} onCheckedChange={(v) => setValue('kopie_bemerkung_auf_pos', v)} disabled={!isEditing} />
                          <Label className="text-sm">Bemerkung auf Positionen kopieren</Label>
                        </div>
                      </div>
                    )}

                    {/* === BEMERKUNGEN === */}
                    {activeSection === 'bemerkungen' && (
                      <div className="space-y-4">
                        <div className="space-y-1.5">
                          <Label>Externe Bemerkungen (für Partner sichtbar)</Label>
                          <Textarea {...register('bemerkung_extern')} disabled={!isEditing} rows={5} />
                        </div>
                        <div className="space-y-1.5">
                          <Label>Interne Bemerkungen</Label>
                          <Textarea {...register('bemerkung_intern')} disabled={!isEditing} rows={5} />
                        </div>
                      </div>
                    )}

                  </form>
                </ScrollArea>
              </div>
            </motion.div>
          )}
        </AnimatePresence>
      </div>

      {/* Position Dialog */}
      <PositionDialog
        open={showPositionDialog}
        onClose={() => { setShowPositionDialog(false); setEditingPosition(null); }}
        onSave={handleSavePosition}
        position={editingPosition}
        waehrung={watchFields.waehrung_kurz || 'EUR'}
      />
    </div>
  );
}
