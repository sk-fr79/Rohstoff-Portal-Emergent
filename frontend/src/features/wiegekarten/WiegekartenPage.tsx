import { useState, useMemo } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion, AnimatePresence } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import { 
  Plus, Scale, Truck, Building2, Package, MapPin, 
  Save, X, MoreHorizontal, Pencil, Trash2, Eye,
  AlertTriangle, CheckCircle, Clock, Printer, Ban,
  ArrowDownToLine, ArrowUpFromLine, Weight, FileText,
  Loader2, RefreshCw, Search
} from 'lucide-react';
import { api } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { DataTable } from '@/components/ui/data-table';
import { Switch } from '@/components/ui/switch';
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuSeparator, DropdownMenuTrigger } from '@/components/ui/dropdown-menu';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Textarea } from '@/components/ui/textarea';
import { cn } from '@/lib/utils';
import { Badge } from '@/components/ui/badge';
import { ScrollArea } from '@/components/ui/scroll-area';
import { Tooltip, TooltipContent, TooltipProvider, TooltipTrigger } from '@/components/ui/tooltip';

// ========================== SCHEMA ==========================
const wiegekarteSchema = z.object({
  typ_wiegekarte: z.string().default('W'),
  ist_lieferant: z.boolean().default(true),
  kennzeichen: z.string().min(1, 'Kennzeichen erforderlich').max(20),
  trailer: z.string().max(20).nullish(),
  id_adresse_lieferant: z.string().nullish(),
  adresse_lieferant: z.string().nullish(),
  id_adresse_spedition: z.string().nullish(),
  adresse_spedition: z.string().nullish(),
  id_artikel_sorte: z.string().nullish(),
  artikel_bezeichnung: z.string().nullish(),
  sorte_hand: z.boolean().default(false),
  container_nr: z.string().nullish(),
  container_tara: z.number().nullish(),
  fremdcontainer: z.boolean().default(false),
  gueterkategorie: z.string().nullish(),
  anz_paletten: z.number().nullish(),
  anz_bigbags: z.number().nullish(),
  anz_gitterboxen: z.number().nullish(),
  anz_behaelter: z.number().nullish(),
  bemerkung1: z.string().nullish(),
  bemerkung_intern: z.string().nullish(),
  siegel_nr: z.string().nullish(),
  befund: z.string().nullish(),
  strahlung_geprueft: z.boolean().default(false),
});

type WiegekarteForm = z.infer<typeof wiegekarteSchema>;

// ========================== TYPES ==========================
interface Wiegekarte {
  id: string;
  wiegekarten_nr: string;
  lfd_nr: number;
  typ_wiegekarte: string;
  zustand: string;
  ist_lieferant: boolean;
  kennzeichen: string;
  trailer?: string;
  adresse_lieferant?: string;
  artikel_bezeichnung?: string;
  bruttogewicht?: number;
  taragewicht?: number;
  nettogewicht?: number;
  waegung1?: {
    gewicht: number;
    datum: string;
    zeit: string;
    manuell: boolean;
  };
  waegung2?: {
    gewicht: number;
    datum: string;
    zeit: string;
    manuell: boolean;
  };
  storno: boolean;
  erstellt_am: string;
  erstellt_von?: string;
}

// ========================== SIDEBAR SECTIONS ==========================
const sidebarSections = [
  { id: 'stamm', label: 'Stammdaten', icon: FileText },
  { id: 'fahrzeug', label: 'Fahrzeug', icon: Truck },
  { id: 'artikel', label: 'Artikel/Material', icon: Package },
  { id: 'waegung', label: 'Wägung', icon: Scale },
  { id: 'mengen', label: 'Mengen/Stück', icon: Package },
  { id: 'bemerkungen', label: 'Bemerkungen', icon: FileText },
];

// Zustand Badge Farben
const zustandColors: Record<string, { bg: string; text: string }> = {
  NEU: { bg: 'bg-blue-100', text: 'text-blue-700' },
  STAMMDATEN: { bg: 'bg-yellow-100', text: 'text-yellow-700' },
  WAEGUNG1: { bg: 'bg-orange-100', text: 'text-orange-700' },
  WAEGUNG2: { bg: 'bg-green-100', text: 'text-green-700' },
  GEDRUCKT: { bg: 'bg-emerald-100', text: 'text-emerald-700' },
  STORNO: { bg: 'bg-red-100', text: 'text-red-700' },
};

// ========================== COMPONENT ==========================
export function WiegekartenPage() {
  const queryClient = useQueryClient();
  const [selectedWiegekarte, setSelectedWiegekarte] = useState<Wiegekarte | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [isNewRecord, setIsNewRecord] = useState(false);
  const [activeSection, setActiveSection] = useState('stamm');
  const [nurOffene, setNurOffene] = useState(false);
  
  // Waage-Status
  const [waageGewicht, setWaageGewicht] = useState<number | null>(null);
  const [waageLoading, setWaageLoading] = useState(false);

  const { register, handleSubmit, reset, setValue, watch, formState: { errors } } = useForm<WiegekarteForm>({
    resolver: zodResolver(wiegekarteSchema),
    defaultValues: {
      typ_wiegekarte: 'W',
      ist_lieferant: true,
      strahlung_geprueft: false,
      fremdcontainer: false,
      sorte_hand: false,
    }
  });

  const watchFields = watch();

  // Queries & Mutations
  const { data: wiegekartenData, isLoading } = useQuery({
    queryKey: ['wiegekarten', nurOffene],
    queryFn: async () => {
      const response = await api.get('/wiegekarten', { params: { nur_offene: nurOffene } });
      return response.data;
    }
  });

  const createMutation = useMutation({
    mutationFn: (data: WiegekarteForm) => api.post('/wiegekarten', data),
    onSuccess: (response) => {
      queryClient.invalidateQueries({ queryKey: ['wiegekarten'] });
      toast.success(`Wiegekarte ${response.data.data.wiegekarten_nr} erstellt`);
      // Nach erfolgreicher Erstellung: Sidebar bleibt offen mit neuem Datensatz
      if (response.data?.data) {
        setSelectedWiegekarte(response.data.data);
        Object.entries(response.data.data).forEach(([key, value]) => {
          if (key in wiegekarteSchema.shape) {
            setValue(key as keyof WiegekarteForm, value as any);
          }
        });
      }
      setIsNewRecord(false);
      setIsEditing(false);
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Erstellen');
    }
  });

  const updateMutation = useMutation({
    mutationFn: ({ id, data }: { id: string; data: Partial<WiegekarteForm> }) => 
      api.put(`/wiegekarten/${id}`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['wiegekarten'] });
      toast.success('Wiegekarte aktualisiert');
      setIsEditing(false);
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Aktualisieren');
    }
  });

  const stornoMutation = useMutation({
    mutationFn: (id: string) => api.post(`/wiegekarten/${id}/storno`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['wiegekarten'] });
      toast.success('Wiegekarte storniert');
      setSelectedWiegekarte(null);
    }
  });

  const deleteMutation = useMutation({
    mutationFn: (id: string) => api.delete(`/wiegekarten/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['wiegekarten'] });
      toast.success('Wiegekarte gelöscht');
      setSelectedWiegekarte(null);
    }
  });

  // Wägung speichern
  const waegungMutation = useMutation({
    mutationFn: ({ id, nr, gewicht }: { id: string; nr: number; gewicht: number }) => 
      api.post(`/wiegekarten/${id}/waegung/${nr}`, { gewicht, manuell: true }),
    onSuccess: (response) => {
      queryClient.invalidateQueries({ queryKey: ['wiegekarten'] });
      if (selectedWiegekarte) {
        setSelectedWiegekarte(response.data.data);
      }
      toast.success('Wägung gespeichert');
    }
  });

  // Waage lesen (Demo)
  const leseWaage = async () => {
    setWaageLoading(true);
    try {
      const response = await api.post('/waage/lesen');
      if (response.data.success) {
        setWaageGewicht(response.data.data.brutto_gewicht);
        toast.success(`Gewicht gelesen: ${response.data.data.brutto_gewicht} kg`);
      }
    } catch {
      toast.error('Fehler beim Lesen der Waage');
    } finally {
      setWaageLoading(false);
    }
  };

  // Tabellen-Spalten
  const columns: ColumnDef<Wiegekarte>[] = useMemo(() => [
    {
      accessorKey: 'wiegekarten_nr',
      header: 'Nr.',
      cell: ({ row }) => (
        <span className="font-mono font-semibold text-sm">{row.original.wiegekarten_nr}</span>
      )
    },
    {
      accessorKey: 'zustand',
      header: 'Status',
      cell: ({ row }) => {
        const zustand = row.original.zustand;
        const colors = zustandColors[zustand] || zustandColors.NEU;
        return (
          <Badge className={cn(colors.bg, colors.text, 'font-medium')}>
            {zustand}
          </Badge>
        );
      }
    },
    {
      accessorKey: 'ist_lieferant',
      header: 'Richtung',
      cell: ({ row }) => (
        <div className="flex items-center gap-1">
          {row.original.ist_lieferant ? (
            <>
              <ArrowDownToLine className="h-4 w-4 text-green-600" />
              <span className="text-sm">Eingang</span>
            </>
          ) : (
            <>
              <ArrowUpFromLine className="h-4 w-4 text-blue-600" />
              <span className="text-sm">Ausgang</span>
            </>
          )}
        </div>
      )
    },
    {
      accessorKey: 'kennzeichen',
      header: 'Kennzeichen',
      cell: ({ row }) => (
        <div className="flex items-center gap-2">
          <Truck className="h-4 w-4 text-gray-400" />
          <span className="font-semibold">{row.original.kennzeichen}</span>
          {row.original.trailer && (
            <span className="text-gray-500">/ {row.original.trailer}</span>
          )}
        </div>
      )
    },
    {
      accessorKey: 'adresse_lieferant',
      header: 'Adresse',
      cell: ({ row }) => (
        <span className="text-sm truncate max-w-[200px] block">
          {row.original.adresse_lieferant || '-'}
        </span>
      )
    },
    {
      accessorKey: 'nettogewicht',
      header: 'Netto (kg)',
      cell: ({ row }) => (
        <span className={cn(
          "font-mono font-bold",
          row.original.nettogewicht ? "text-emerald-600" : "text-gray-400"
        )}>
          {row.original.nettogewicht?.toLocaleString('de-DE') || '-'}
        </span>
      )
    },
    {
      accessorKey: 'erstellt_am',
      header: 'Datum',
      cell: ({ row }) => (
        <span className="text-sm text-gray-600">
          {new Date(row.original.erstellt_am).toLocaleDateString('de-DE', {
            day: '2-digit', month: '2-digit', year: '2-digit', hour: '2-digit', minute: '2-digit'
          })}
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
            <DropdownMenuItem onClick={() => setSelectedWiegekarte(row.original)}>
              <Eye className="h-4 w-4 mr-2" />Anzeigen
            </DropdownMenuItem>
            {!row.original.storno && (
              <>
                <DropdownMenuItem onClick={() => {
                  setSelectedWiegekarte(row.original);
                  setIsEditing(true);
                }}>
                  <Pencil className="h-4 w-4 mr-2" />Bearbeiten
                </DropdownMenuItem>
                <DropdownMenuSeparator />
                <DropdownMenuItem 
                  className="text-red-600"
                  onClick={() => {
                    if (confirm('Wiegekarte wirklich stornieren?')) {
                      stornoMutation.mutate(row.original.id);
                    }
                  }}
                >
                  <Ban className="h-4 w-4 mr-2" />Stornieren
                </DropdownMenuItem>
              </>
            )}
          </DropdownMenuContent>
        </DropdownMenu>
      )
    }
  ], [stornoMutation]);

  // Submit Handler
  const onSubmit = async (data: WiegekarteForm) => {
    if (selectedWiegekarte && isEditing) {
      updateMutation.mutate({ id: selectedWiegekarte.id, data });
    } else {
      createMutation.mutate(data);
    }
  };

  // Neue Wiegekarte anlegen - öffnet Sidebar mit leerem Datensatz
  const handleNewWiegekarte = () => {
    const emptyWiegekarte: Wiegekarte = {
      id: 'NEU',
      wiegekarten_nr: '(wird automatisch vergeben)',
      lfd_nr: 0,
      typ_wiegekarte: 'W',
      zustand: 'NEU',
      ist_lieferant: true,
      kennzeichen: '',
      storno: false,
      erstellt_am: new Date().toISOString(),
    };
    setSelectedWiegekarte(emptyWiegekarte);
    reset({
      typ_wiegekarte: 'W',
      ist_lieferant: true,
      kennzeichen: '',
      strahlung_geprueft: false,
      fremdcontainer: false,
      sorte_hand: false,
    });
    setIsNewRecord(true);
    setIsEditing(true);
    setActiveSection('stamm');
  };

  // Sidebar schließen
  const handleClose = () => {
    setSelectedWiegekarte(null);
    setIsEditing(false);
    setIsNewRecord(false);
    reset();
  };

  // Abbrechen
  const handleCancel = () => {
    if (isNewRecord) {
      setSelectedWiegekarte(null);
      setIsNewRecord(false);
      reset();
    } else {
      setIsEditing(false);
      if (selectedWiegekarte) {
        Object.entries(selectedWiegekarte).forEach(([key, value]) => {
          if (key in wiegekarteSchema.shape) {
            setValue(key as keyof WiegekarteForm, value as any);
          }
        });
      }
    }
  };

  // Detailansicht öffnen
  const openDetail = (wk: Wiegekarte) => {
    setSelectedWiegekarte(wk);
    setIsNewRecord(false);
    setIsEditing(false);
    setActiveSection('stamm');
    // Form mit Daten füllen
    Object.entries(wk).forEach(([key, value]) => {
      if (key in wiegekarteSchema.shape) {
        setValue(key as keyof WiegekarteForm, value as any);
      }
    });
  };

  return (
    <div className="flex h-full">
      {/* Hauptbereich - Liste */}
      <div className={cn(
        "flex-1 p-6 overflow-auto transition-all duration-300",
        selectedWiegekarte ? "mr-[600px]" : ""
      )}>
        {/* Header */}
        <div className="flex items-center justify-between mb-6">
          <div>
            <h1 className="text-2xl font-bold text-gray-900">Wiegekarten</h1>
            <p className="text-sm text-gray-500 mt-1">Fahrzeugwaage · Systec IT 4000</p>
          </div>
          <div className="flex items-center gap-3">
            <div className="flex items-center gap-2 bg-gray-100 px-3 py-1.5 rounded-lg">
              <Switch
                checked={nurOffene}
                onCheckedChange={setNurOffene}
                id="nur-offene"
              />
              <Label htmlFor="nur-offene" className="text-sm cursor-pointer">
                Nur offene
              </Label>
            </div>
            <Button onClick={handleNewWiegekarte} className="bg-emerald-600 hover:bg-emerald-700" data-testid="new-wiegekarte-btn">
              <Plus className="h-4 w-4 mr-2" />
              Neue Wiegekarte
            </Button>
          </div>
        </div>

        {/* Tabelle */}
        <div className="bg-white rounded-xl shadow-sm border">
          <DataTable
            columns={columns}
            data={wiegekartenData?.data || []}
            searchKey="kennzeichen"
            searchPlaceholder="Kennzeichen suchen..."
            onRowDoubleClick={openDetail}
          />
        </div>
      </div>

      {/* Detail-Panel (Slide-In) */}
      <AnimatePresence>
        {selectedWiegekarte && (
          <motion.div
            initial={{ x: 600 }}
            animate={{ x: 0 }}
            exit={{ x: 600 }}
            transition={{ type: 'spring', stiffness: 300, damping: 30 }}
            className="fixed right-0 top-0 h-full w-[600px] bg-white border-l shadow-xl z-40 flex flex-col"
          >
            {/* Header */}
            <div className="flex items-center justify-between p-4 border-b bg-gray-50">
              <div className="flex items-center gap-3">
                <div className={cn(
                  "h-10 w-10 rounded-lg flex items-center justify-center",
                  watchFields.ist_lieferant ? "bg-green-100" : "bg-blue-100"
                )}>
                  <Scale className={cn(
                    "h-5 w-5",
                    watchFields.ist_lieferant ? "text-green-600" : "text-blue-600"
                  )} />
                </div>
                <div>
                  <h2 className="font-bold text-lg">
                    {isNewRecord ? 'Neue Wiegekarte' : selectedWiegekarte.wiegekarten_nr}
                  </h2>
                  <div className="flex items-center gap-2">
                    <Badge className={cn(
                      zustandColors[selectedWiegekarte.zustand]?.bg,
                      zustandColors[selectedWiegekarte.zustand]?.text
                    )}>
                      {selectedWiegekarte.zustand}
                    </Badge>
                    <span className="text-sm text-gray-500">
                      {watchFields.ist_lieferant ? 'Wareneingang' : 'Warenausgang'}
                    </span>
                  </div>
                </div>
              </div>
              <div className="flex items-center gap-2">
                {!selectedWiegekarte.storno && (
                  isEditing ? (
                    <>
                      <Button variant="outline" size="sm" onClick={handleCancel}>
                        Abbrechen
                      </Button>
                      <Button
                        size="sm"
                        onClick={() => handleSubmit(onSubmit)()}
                        className="bg-emerald-600 hover:bg-emerald-700"
                      >
                        <Save className="h-4 w-4 mr-1" />
                        Speichern
                      </Button>
                    </>
                  ) : (
                    <Button variant="outline" size="sm" onClick={() => setIsEditing(true)}>
                      <Pencil className="h-4 w-4 mr-1" />
                      Bearbeiten
                    </Button>
                  )
                )}
                <Button variant="ghost" size="icon" onClick={handleClose}>
                  <X className="h-5 w-5" />
                </Button>
              </div>
            </div>

            {/* Gewichte Anzeige */}
            <div className="grid grid-cols-3 gap-4 p-4 bg-gradient-to-r from-gray-50 to-white border-b">
              <div className="text-center">
                <div className="text-xs text-gray-500 uppercase tracking-wide">Brutto</div>
                <div className="text-2xl font-bold text-gray-700">
                  {selectedWiegekarte.bruttogewicht?.toLocaleString('de-DE') || '-'}
                </div>
                <div className="text-xs text-gray-400">kg</div>
              </div>
              <div className="text-center">
                <div className="text-xs text-gray-500 uppercase tracking-wide">Tara</div>
                <div className="text-2xl font-bold text-gray-700">
                  {selectedWiegekarte.taragewicht?.toLocaleString('de-DE') || '-'}
                </div>
                <div className="text-xs text-gray-400">kg</div>
              </div>
              <div className="text-center">
                <div className="text-xs text-gray-500 uppercase tracking-wide">Netto</div>
                <div className="text-2xl font-bold text-emerald-600">
                  {selectedWiegekarte.nettogewicht?.toLocaleString('de-DE') || '-'}
                </div>
                <div className="text-xs text-gray-400">kg</div>
              </div>
            </div>

            {/* Sidebar Navigation + Content */}
            <div className="flex flex-1 overflow-hidden">
              {/* Sidebar */}
              <div className="w-48 bg-gray-50 border-r p-2 space-y-1">
                {sidebarSections.map((section) => (
                  <button
                    key={section.id}
                    onClick={() => setActiveSection(section.id)}
                    className={cn(
                      "w-full flex items-center gap-2 px-3 py-2 rounded-lg text-sm transition-colors",
                      activeSection === section.id
                        ? "bg-emerald-100 text-emerald-700 font-medium"
                        : "text-gray-600 hover:bg-gray-100"
                    )}
                  >
                    <section.icon className="h-4 w-4" />
                    {section.label}
                  </button>
                ))}
              </div>

              {/* Content */}
              <ScrollArea className="flex-1 p-4">
                <form onSubmit={handleSubmit(onSubmit)}>
                  {/* Stammdaten */}
                  {activeSection === 'stamm' && (
                    <div className="space-y-4">
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1.5">
                          <Label>Typ</Label>
                          <Select 
                            value={watchFields.typ_wiegekarte} 
                            onValueChange={(v) => setValue('typ_wiegekarte', v)}
                            disabled={!isEditing}
                          >
                            <SelectTrigger className="bg-white">
                              <SelectValue />
                            </SelectTrigger>
                            <SelectContent>
                              <SelectItem value="W">Wiegeschein</SelectItem>
                              <SelectItem value="E">Eingangsschein</SelectItem>
                              <SelectItem value="H">Hofschein</SelectItem>
                              <SelectItem value="F">Fremdverwiegung</SelectItem>
                            </SelectContent>
                          </Select>
                        </div>
                        <div className="space-y-1.5">
                          <Label>Richtung</Label>
                          <div className="flex items-center gap-3 h-10">
                            <Switch
                              checked={watchFields.ist_lieferant}
                              onCheckedChange={(v) => setValue('ist_lieferant', v)}
                              disabled={!isEditing}
                            />
                            <span className={cn(
                              "text-sm font-medium",
                              watchFields.ist_lieferant ? "text-green-600" : "text-blue-600"
                            )}>
                              {watchFields.ist_lieferant ? 'Wareneingang' : 'Warenausgang'}
                            </span>
                          </div>
                        </div>
                      </div>
                      <div className="flex items-center gap-2 p-3 bg-blue-50 rounded-lg">
                        <CheckCircle className="h-5 w-5 text-blue-500" />
                        <div className="flex-1">
                          <Switch
                            checked={watchFields.strahlung_geprueft}
                            onCheckedChange={(v) => setValue('strahlung_geprueft', v)}
                            disabled={!isEditing}
                          />
                        </div>
                        <Label className="text-sm text-blue-700">Auf Radioaktivität geprüft</Label>
                      </div>
                    </div>
                  )}

                  {/* Fahrzeug */}
                  {activeSection === 'fahrzeug' && (
                    <div className="space-y-4">
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1.5">
                          <Label>LKW-Kennzeichen *</Label>
                          <Input 
                            {...register('kennzeichen')} 
                            disabled={!isEditing} 
                            className="bg-white font-mono uppercase"
                            placeholder="XX-XX 1234"
                          />
                          {errors.kennzeichen && (
                            <p className="text-xs text-red-500">{errors.kennzeichen.message}</p>
                          )}
                        </div>
                        <div className="space-y-1.5">
                          <Label>Anhänger</Label>
                          <Input 
                            {...register('trailer')} 
                            disabled={!isEditing} 
                            className="bg-white font-mono uppercase"
                            placeholder="XX-XX 5678"
                          />
                        </div>
                      </div>
                      <div className="space-y-1.5">
                        <Label>Adresse Lieferant/Abnehmer</Label>
                        <Textarea 
                          {...register('adresse_lieferant')} 
                          disabled={!isEditing} 
                          className="bg-white"
                          rows={3}
                        />
                      </div>
                      <div className="space-y-1.5">
                        <Label>Spedition</Label>
                        <Textarea 
                          {...register('adresse_spedition')} 
                          disabled={!isEditing} 
                          className="bg-white"
                          rows={2}
                        />
                      </div>
                    </div>
                  )}

                  {/* Artikel */}
                  {activeSection === 'artikel' && (
                    <div className="space-y-4">
                      <div className="space-y-1.5">
                        <Label>Artikel/Sorte</Label>
                        <Input 
                          {...register('artikel_bezeichnung')} 
                          disabled={!isEditing} 
                          className="bg-white"
                        />
                      </div>
                      <div className="flex items-center gap-2">
                        <Switch
                          checked={watchFields.sorte_hand}
                          onCheckedChange={(v) => setValue('sorte_hand', v)}
                          disabled={!isEditing}
                        />
                        <Label>Sorte Hand (manuelle Eingabe)</Label>
                      </div>
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1.5">
                          <Label>Güterkategorie</Label>
                          <Select 
                            value={watchFields.gueterkategorie || ''} 
                            onValueChange={(v) => setValue('gueterkategorie', v)}
                            disabled={!isEditing}
                          >
                            <SelectTrigger className="bg-white">
                              <SelectValue placeholder="Auswählen..." />
                            </SelectTrigger>
                            <SelectContent>
                              <SelectItem value="S">Schüttgut</SelectItem>
                              <SelectItem value="P">Stückgut</SelectItem>
                            </SelectContent>
                          </Select>
                        </div>
                        <div className="space-y-1.5">
                          <Label>Container-Nr.</Label>
                          <Input 
                            {...register('container_nr')} 
                            disabled={!isEditing} 
                            className="bg-white"
                          />
                        </div>
                      </div>
                    </div>
                  )}

                  {/* Wägung */}
                  {activeSection === 'waegung' && (
                    <div className="space-y-6">
                      {/* Waage lesen */}
                      <div className="p-4 bg-gradient-to-r from-emerald-50 to-green-50 rounded-xl border border-emerald-200">
                        <div className="flex items-center justify-between mb-3">
                          <div className="flex items-center gap-2">
                            <Scale className="h-5 w-5 text-emerald-600" />
                            <span className="font-semibold text-emerald-800">Waage</span>
                          </div>
                          <Button
                            type="button"
                            variant="outline"
                            size="sm"
                            onClick={leseWaage}
                            disabled={waageLoading}
                            className="bg-white"
                          >
                            {waageLoading ? (
                              <Loader2 className="h-4 w-4 animate-spin mr-1" />
                            ) : (
                              <RefreshCw className="h-4 w-4 mr-1" />
                            )}
                            Gewicht lesen
                          </Button>
                        </div>
                        {waageGewicht && (
                          <div className="text-center py-4">
                            <div className="text-4xl font-bold text-emerald-600">
                              {waageGewicht.toLocaleString('de-DE')}
                            </div>
                            <div className="text-sm text-emerald-600">kg (aktuell)</div>
                          </div>
                        )}
                      </div>

                      {/* Wägung 1 */}
                      <div className="p-4 bg-white rounded-lg border">
                        <div className="flex items-center justify-between mb-3">
                          <h4 className="font-semibold">
                            Wägung 1 ({selectedWiegekarte?.ist_lieferant ? 'Brutto/Voll' : 'Tara/Leer'})
                          </h4>
                          {!selectedWiegekarte?.waegung1 && waageGewicht && (
                            <Button
                              type="button"
                              size="sm"
                              onClick={() => waegungMutation.mutate({
                                id: selectedWiegekarte!.id,
                                nr: 1,
                                gewicht: waageGewicht
                              })}
                            >
                              Übernehmen
                            </Button>
                          )}
                        </div>
                        {selectedWiegekarte?.waegung1 ? (
                          <div className="flex items-center gap-4">
                            <div className="text-2xl font-bold">
                              {selectedWiegekarte.waegung1.gewicht.toLocaleString('de-DE')} kg
                            </div>
                            <div className="text-sm text-gray-500">
                              {selectedWiegekarte.waegung1.datum && new Date(selectedWiegekarte.waegung1.datum).toLocaleString('de-DE')}
                              {selectedWiegekarte.waegung1.manuell && ' (manuell)'}
                            </div>
                          </div>
                        ) : (
                          <div className="text-gray-400 text-center py-4">Noch keine Wägung</div>
                        )}
                      </div>

                      {/* Wägung 2 */}
                      <div className="p-4 bg-white rounded-lg border">
                        <div className="flex items-center justify-between mb-3">
                          <h4 className="font-semibold">
                            Wägung 2 ({selectedWiegekarte?.ist_lieferant ? 'Tara/Leer' : 'Brutto/Voll'})
                          </h4>
                          {selectedWiegekarte?.waegung1 && !selectedWiegekarte?.waegung2 && waageGewicht && (
                            <Button
                              type="button"
                              size="sm"
                              onClick={() => waegungMutation.mutate({
                                id: selectedWiegekarte!.id,
                                nr: 2,
                                gewicht: waageGewicht
                              })}
                            >
                              Übernehmen
                            </Button>
                          )}
                        </div>
                        {selectedWiegekarte?.waegung2 ? (
                          <div className="flex items-center gap-4">
                            <div className="text-2xl font-bold">
                              {selectedWiegekarte.waegung2.gewicht.toLocaleString('de-DE')} kg
                            </div>
                            <div className="text-sm text-gray-500">
                              {selectedWiegekarte.waegung2.datum && new Date(selectedWiegekarte.waegung2.datum).toLocaleString('de-DE')}
                              {selectedWiegekarte.waegung2.manuell && ' (manuell)'}
                            </div>
                          </div>
                        ) : (
                          <div className="text-gray-400 text-center py-4">Noch keine Wägung</div>
                        )}
                      </div>
                    </div>
                  )}

                  {/* Mengen */}
                  {activeSection === 'mengen' && (
                    <div className="space-y-4">
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1.5">
                          <Label>Paletten</Label>
                          <Input type="number" {...register('anz_paletten', { valueAsNumber: true })} disabled={!isEditing} className="bg-white" />
                        </div>
                        <div className="space-y-1.5">
                          <Label>BigBags</Label>
                          <Input type="number" {...register('anz_bigbags', { valueAsNumber: true })} disabled={!isEditing} className="bg-white" />
                        </div>
                        <div className="space-y-1.5">
                          <Label>Gitterboxen</Label>
                          <Input type="number" {...register('anz_gitterboxen', { valueAsNumber: true })} disabled={!isEditing} className="bg-white" />
                        </div>
                        <div className="space-y-1.5">
                          <Label>Behälter</Label>
                          <Input type="number" {...register('anz_behaelter', { valueAsNumber: true })} disabled={!isEditing} className="bg-white" />
                        </div>
                      </div>
                    </div>
                  )}

                  {/* Bemerkungen */}
                  {activeSection === 'bemerkungen' && (
                    <div className="space-y-4">
                      <div className="space-y-1.5">
                        <Label>Bemerkung (extern)</Label>
                        <Textarea {...register('bemerkung1')} disabled={!isEditing} className="bg-white" rows={3} />
                      </div>
                      <div className="space-y-1.5">
                        <Label>Bemerkung (intern)</Label>
                        <Textarea {...register('bemerkung_intern')} disabled={!isEditing} className="bg-white" rows={3} />
                      </div>
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1.5">
                          <Label>Siegel-Nr.</Label>
                          <Input {...register('siegel_nr')} disabled={!isEditing} className="bg-white" />
                        </div>
                        <div className="space-y-1.5">
                          <Label>Befund</Label>
                          <Input {...register('befund')} disabled={!isEditing} className="bg-white" />
                        </div>
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
  );
}
