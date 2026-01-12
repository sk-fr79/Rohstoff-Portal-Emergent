import { useState, useMemo, useRef, useCallback, useEffect } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion, AnimatePresence } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import { 
  Plus, MoreHorizontal, Pencil, Trash2, Package, Eye, 
  Save, X, AlertTriangle, FileText, Scale, Globe, GripVertical, Recycle, ExternalLink
} from 'lucide-react';
import { artikelApi } from '@/services/api/client';
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
import { ReferenceSelect, ReferenceOption } from '@/components/ui/reference-select';

// ========================== SCHEMA ==========================
const artikelSchema = z.object({
  anr1: z.string().max(10).nullish(),
  artbez1: z.string().min(1, 'Artikelbezeichnung erforderlich').max(80),
  artbez2: z.string().max(1000).nullish(),
  einheit: z.string().max(10).default('kg'),
  einheit_preis: z.string().max(10).default('t'),
  mengendivisor: z.number().min(1).default(1000),
  genauigkeit_mengen: z.number().min(0).max(6).default(3),
  artikelgruppe: z.string().max(100).nullish(),
  artikelgruppe_fibu: z.string().max(100).nullish(),
  aktiv: z.boolean().default(true),
  gefahrgut: z.boolean().default(false),
  ist_leergut: z.boolean().default(false),
  elektro_elektronik: z.boolean().default(false),
  ist_produkt: z.boolean().default(false),
  dienstleistung: z.boolean().default(false),
  end_of_waste: z.boolean().default(false),
  end_of_waste_lager: z.boolean().default(false),
  avv_code_eingang: z.string().max(50).nullish(),
  avv_code_ausgang: z.string().max(50).nullish(),
  eakcode: z.string().max(20).nullish(),
  zolltarifnr: z.string().max(50).nullish(),
  zolltarifnotiz: z.string().max(500).nullish(),
  basel_code: z.string().max(80).nullish(),
  basel_notiz: z.string().max(500).nullish(),
  oecd_code: z.string().max(50).nullish(),
  oecd_notiz: z.string().max(500).nullish(),
  anhang7_3a_code: z.string().max(20).nullish(),
  anhang7_3a_text: z.string().max(1000).nullish(),
  anhang7_3b_code: z.string().max(20).nullish(),
  anhang7_3b_text: z.string().max(1000).nullish(),
  oesterreichische_avv: z.string().max(50).nullish(),
  bemerkung_intern: z.string().max(1000).nullish(),
});

type ArtikelForm = z.infer<typeof artikelSchema>;

interface Artikel extends ArtikelForm {
  id: string;
  erstellt_am?: string;
  letzte_aenderung?: string;
  zolltarifnotiz_url?: string;
}

// ========================== SIDEBAR SECTIONS ==========================
const sidebarSections = [
  { id: 'stamm', label: 'Stammdaten', icon: Package },
  { id: 'einheiten', label: 'Einheiten', icon: Scale },
  { id: 'klassifizierung', label: 'Klassifizierung', icon: FileText },
  { id: 'avv', label: 'AVV-Codes', icon: Recycle },
  { id: 'zoll', label: 'Zoll & Export', icon: Globe },
  { id: 'bemerkungen', label: 'Bemerkungen', icon: FileText },
];

// ========================== COMPONENT ==========================
export function ArtikelPage() {
  const queryClient = useQueryClient();
  const [selectedArtikel, setSelectedArtikel] = useState<Artikel | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [isNewRecord, setIsNewRecord] = useState(false);
  const [activeSection, setActiveSection] = useState('stamm');
  const [showInactive, setShowInactive] = useState(false);
  const [zolltarifUrl, setZolltarifUrl] = useState<string | null>(null);
  
  // State für resizable Sidebar
  const [panelWidth, setPanelWidth] = useState(50); // Standard 50%
  const [isDragging, setIsDragging] = useState(false);
  const containerRef = useRef<HTMLDivElement>(null);
  
  // Resizable Panel Logik
  const handleMouseMove = useCallback((e: MouseEvent) => {
    if (!isDragging || !containerRef.current) return;
    
    const container = containerRef.current;
    const rect = container.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const percentage = (x / rect.width) * 100;
    
    // Grenzen: Min 30%, Max 70%
    const clampedPercentage = Math.min(Math.max(percentage, 30), 70);
    setPanelWidth(clampedPercentage);
  }, [isDragging]);
  
  const handleMouseUp = useCallback(() => {
    setIsDragging(false);
  }, []);
  
  useEffect(() => {
    if (isDragging) {
      document.addEventListener('mousemove', handleMouseMove);
      document.addEventListener('mouseup', handleMouseUp);
      document.body.style.userSelect = 'none';
      document.body.style.cursor = 'col-resize';
    } else {
      document.body.style.userSelect = '';
      document.body.style.cursor = '';
    }
    
    return () => {
      document.removeEventListener('mousemove', handleMouseMove);
      document.removeEventListener('mouseup', handleMouseUp);
      document.body.style.userSelect = '';
      document.body.style.cursor = '';
    };
  }, [isDragging, handleMouseMove, handleMouseUp]);

  const { register, handleSubmit, reset, setValue, watch, formState: { errors } } = useForm<ArtikelForm>({
    resolver: zodResolver(artikelSchema),
    defaultValues: {
      aktiv: true,
      gefahrgut: false,
      einheit: 'kg',
      einheit_preis: 't',
      mengendivisor: 1000,
      genauigkeit_mengen: 3,
    }
  });

  const watchFields = watch();

  // Queries & Mutations
  const { data: artikelData, isLoading } = useQuery({
    queryKey: ['artikel'],
    queryFn: async () => {
      const response = await artikelApi.list();
      return response.data;
    }
  });

  // Filter data
  const filteredData = useMemo(() => {
    if (!artikelData?.data) return [];
    if (showInactive) return artikelData.data;
    return artikelData.data.filter((a: Artikel) => a.aktiv !== false);
  }, [artikelData, showInactive]);

  const inactiveCount = useMemo(() => {
    if (!artikelData?.data) return 0;
    return artikelData.data.filter((a: Artikel) => a.aktiv === false).length;
  }, [artikelData]);

  const createMutation = useMutation({
    mutationFn: (data: ArtikelForm) => artikelApi.create(data),
    onSuccess: (response) => {
      queryClient.invalidateQueries({ queryKey: ['artikel'] });
      toast.success('Artikel erfolgreich erstellt');
      // Nach erfolgreicher Erstellung: Sidebar bleibt offen mit neuem Datensatz
      if (response.data?.data) {
        setSelectedArtikel(response.data.data);
        Object.entries(response.data.data).forEach(([key, value]) => {
          if (key in artikelSchema.shape) {
            setValue(key as keyof ArtikelForm, value as any);
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
    mutationFn: ({ id, data }: { id: string; data: Partial<ArtikelForm> }) => 
      artikelApi.update(id, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['artikel'] });
      toast.success('Artikel erfolgreich aktualisiert');
      setIsEditing(false);
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Aktualisieren');
    }
  });

  const deleteMutation = useMutation({
    mutationFn: (id: string) => artikelApi.delete(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['artikel'] });
      toast.success('Artikel erfolgreich gelöscht');
      setSelectedArtikel(null);
    }
  });

  // Submit Handler
  const onSubmit = async (data: ArtikelForm) => {
    if (selectedArtikel && isEditing) {
      updateMutation.mutate({ id: selectedArtikel.id, data });
    } else {
      createMutation.mutate(data);
    }
  };

  // Manual save handler
  const handleSave = () => {
    handleSubmit(
      onSubmit,
      (validationErrors) => {
        Object.values(validationErrors).forEach((err: any) => {
          if (err?.message) toast.error(err.message);
        });
      }
    )();
  };

  // Neue Artikel anlegen - öffnet Sidebar mit leerem Datensatz
  const handleNewArtikel = () => {
    const emptyArtikel: Artikel = {
      id: 'NEU',
      artbez1: '',
      einheit: 'kg',
      einheit_preis: 't',
      mengendivisor: 1000,
      genauigkeit_mengen: 3,
      aktiv: true,
      gefahrgut: false,
      ist_leergut: false,
      elektro_elektronik: false,
      ist_produkt: false,
      dienstleistung: false,
      end_of_waste: false,
      end_of_waste_lager: false,
    };
    setSelectedArtikel(emptyArtikel);
    reset(emptyArtikel);
    setIsNewRecord(true);
    setIsEditing(true);
    setActiveSection('stamm');
    setZolltarifUrl(null);
  };

  // Sidebar schließen
  const handleClose = () => {
    setSelectedArtikel(null);
    setIsEditing(false);
    setIsNewRecord(false);
    setZolltarifUrl(null);
    reset();
  };

  // Abbrechen
  const handleCancel = () => {
    if (isNewRecord) {
      setSelectedArtikel(null);
      setIsNewRecord(false);
      reset();
    } else {
      setIsEditing(false);
      if (selectedArtikel) {
        Object.entries(selectedArtikel).forEach(([key, value]) => {
          if (key in artikelSchema.shape) {
            setValue(key as keyof ArtikelForm, value as any);
          }
        });
      }
    }
  };

  // Detailansicht öffnen
  const openDetail = (artikel: Artikel) => {
    setSelectedArtikel(artikel);
    setIsNewRecord(false);
    setIsEditing(false);
    setActiveSection('stamm');
    
    // URL aus gespeicherter Notiz extrahieren (falls vorhanden)
    if (artikel.zolltarifnotiz) {
      // Versuche URL aus [URL]...[/URL] Tag zu extrahieren
      const tagMatch = artikel.zolltarifnotiz.match(/\[URL\](.*?)\[\/URL\]/);
      if (tagMatch) {
        setZolltarifUrl(tagMatch[1]);
      } else {
        // Fallback: Versuche normale URL zu finden (für alte Daten)
        const urlMatch = artikel.zolltarifnotiz.match(/https?:\/\/[^\s]+/);
        setZolltarifUrl(urlMatch ? urlMatch[0] : null);
      }
    } else {
      setZolltarifUrl(null);
    }
    
    Object.entries(artikel).forEach(([key, value]) => {
      if (key in artikelSchema.shape) {
        setValue(key as keyof ArtikelForm, value as any);
      }
    });
  };

  // Tabellen-Spalten
  const columns: ColumnDef<Artikel>[] = useMemo(() => [
    {
      accessorKey: 'anr1',
      header: 'ANR',
      cell: ({ row }) => (
        <span className="font-mono text-sm font-semibold">
          {row.original.anr1 || '-'}
        </span>
      )
    },
    {
      accessorKey: 'artbez1',
      header: 'Bezeichnung',
      cell: ({ row }) => (
        <div>
          <div className="font-medium">{row.original.artbez1}</div>
          {row.original.artbez2 && (
            <div className="text-xs text-gray-500 truncate max-w-[300px]">
              {row.original.artbez2}
            </div>
          )}
        </div>
      )
    },
    {
      accessorKey: 'einheit',
      header: 'Einheit',
      cell: ({ row }) => (
        <span className="text-sm">{row.original.einheit}</span>
      )
    },
    {
      accessorKey: 'artikelgruppe',
      header: 'Gruppe',
      cell: ({ row }) => (
        <span className="text-sm text-gray-600">
          {row.original.artikelgruppe || '-'}
        </span>
      )
    },
    {
      accessorKey: 'gefahrgut',
      header: 'Gefahrgut',
      cell: ({ row }) => (
        row.original.gefahrgut ? (
          <Badge className="bg-red-100 text-red-700">
            <AlertTriangle className="h-3 w-3 mr-1" />
            Ja
          </Badge>
        ) : (
          <span className="text-gray-400 text-sm">-</span>
        )
      )
    },
    {
      accessorKey: 'aktiv',
      header: 'Status',
      cell: ({ row }) => (
        <Badge className={row.original.aktiv ? 'bg-green-100 text-green-700' : 'bg-gray-100 text-gray-600'}>
          {row.original.aktiv ? 'Aktiv' : 'Inaktiv'}
        </Badge>
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
                if (confirm('Artikel wirklich löschen?')) {
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

  return (
    <div className="flex h-full flex-col">
      {/* Header */}
      <div className="bg-white border-b border-gray-200 px-6 py-4">
        <div className="flex items-center justify-between">
          <div>
            <h1 className="text-xl font-semibold text-gray-900">Artikel</h1>
            <p className="text-sm text-gray-500 mt-0.5">Artikelstamm / Sorten verwalten</p>
          </div>
          <div className="flex items-center gap-3">
            <div className="flex items-center gap-2 bg-gray-100 px-3 py-1.5 rounded-lg">
              <Switch
                checked={showInactive}
                onCheckedChange={setShowInactive}
                id="show-inactive"
              />
              <Label htmlFor="show-inactive" className="text-sm cursor-pointer">
                Inaktive {inactiveCount > 0 && `(${inactiveCount})`}
              </Label>
            </div>
            <Button onClick={handleNewArtikel} className="bg-emerald-600 hover:bg-emerald-700" data-testid="new-artikel-btn">
              <Plus className="h-4 w-4 mr-2" />
              Neuer Artikel
            </Button>
          </div>
        </div>
      </div>

      {/* Content Area */}
      <div ref={containerRef} className="flex-1 flex overflow-hidden">
        {/* Main Table */}
        <div 
          className="p-6 overflow-auto transition-none"
          style={{ width: selectedArtikel ? `${panelWidth}%` : '100%' }}
        >
          <div className="bg-white rounded-xl shadow-sm border">
            <DataTable
              columns={columns}
              data={filteredData}
              searchKey="artbez1"
              searchPlaceholder="Artikel suchen..."
              onRowDoubleClick={openDetail}
            />
          </div>
        </div>

        {/* Resizable Handle */}
        {selectedArtikel && (
          <div
            className={cn(
              "relative flex w-1.5 items-center justify-center bg-gray-100 transition-colors cursor-col-resize select-none",
              "hover:bg-emerald-200 active:bg-emerald-300",
              isDragging && "bg-emerald-400"
            )}
            onMouseDown={(e) => {
              e.preventDefault();
              setIsDragging(true);
            }}
            data-testid="resize-handle"
          >
            <div className={cn(
              "absolute flex h-10 w-5 items-center justify-center rounded-sm",
              "bg-gray-200/80 backdrop-blur-sm opacity-0 transition-opacity",
              "hover:opacity-100",
              isDragging && "opacity-100 bg-emerald-400"
            )}>
              <GripVertical className="h-4 w-4 text-gray-500" />
            </div>
            <div className="absolute inset-y-0 -left-2 -right-2" />
          </div>
        )}

        {/* Detail-Panel */}
        <AnimatePresence>
          {selectedArtikel && (
            <motion.div
              initial={{ x: '100%', opacity: 0 }}
              animate={{ x: 0, opacity: 1 }}
              exit={{ x: '100%', opacity: 0 }}
              transition={{ type: 'spring', damping: 25, stiffness: 200 }}
              className="border-l border-gray-200 bg-white flex flex-col overflow-hidden"
              style={{ width: `${100 - panelWidth}%` }}
            >
            {/* Header */}
            <div className="flex items-center justify-between p-4 border-b bg-gray-50">
              <div className="flex items-center gap-3">
                <div className={cn(
                  "h-10 w-10 rounded-lg flex items-center justify-center",
                  watchFields.gefahrgut ? "bg-red-100" : "bg-emerald-100"
                )}>
                  {watchFields.gefahrgut ? (
                    <AlertTriangle className="h-5 w-5 text-red-600" />
                  ) : (
                    <Package className="h-5 w-5 text-emerald-600" />
                  )}
                </div>
                <div>
                  <h2 className="font-bold text-lg">
                    {isNewRecord ? 'Neuer Artikel' : selectedArtikel.artbez1}
                  </h2>
                  <div className="flex items-center gap-2">
                    <span className="text-sm text-gray-500">
                      {isNewRecord ? '(wird automatisch vergeben)' : selectedArtikel.anr1 || 'Ohne ANR'}
                    </span>
                    <Badge className={watchFields.aktiv ? 'bg-green-100 text-green-700' : 'bg-gray-100 text-gray-600'}>
                      {watchFields.aktiv ? 'Aktiv' : 'Inaktiv'}
                    </Badge>
                  </div>
                </div>
              </div>
              <div className="flex items-center gap-2">
                {isEditing ? (
                  <>
                    <Button variant="outline" size="sm" onClick={handleCancel}>
                      Abbrechen
                    </Button>
                    <Button
                      size="sm"
                      onClick={handleSave}
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
                )}
                <Button variant="ghost" size="icon" onClick={handleClose}>
                  <X className="h-5 w-5" />
                </Button>
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
                      <div className="grid grid-cols-3 gap-4">
                        <div className="space-y-1.5">
                          <Label>ANR (Artikelnummer)</Label>
                          <Input {...register('anr1')} disabled={!isEditing} className="bg-white font-mono" />
                        </div>
                        <div className="col-span-2 space-y-1.5">
                          <Label>Artikelbezeichnung 1 *</Label>
                          <Input {...register('artbez1')} disabled={!isEditing} className="bg-white" />
                          {errors.artbez1 && <p className="text-xs text-red-500">{errors.artbez1.message}</p>}
                        </div>
                      </div>
                      <div className="space-y-1.5">
                        <Label>Artikelbezeichnung 2 (erweitert)</Label>
                        <Textarea {...register('artbez2')} disabled={!isEditing} className="bg-white" rows={3} />
                      </div>
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1.5">
                          <Label>Artikelgruppe</Label>
                          <Input {...register('artikelgruppe')} disabled={!isEditing} className="bg-white" />
                        </div>
                        <div className="space-y-1.5">
                          <Label>Artikelgruppe (Fibu)</Label>
                          <Input {...register('artikelgruppe_fibu')} disabled={!isEditing} className="bg-white" />
                        </div>
                      </div>
                      <div className="flex items-center gap-6 pt-4">
                        <div className="flex items-center gap-2">
                          <Switch
                            checked={watchFields.aktiv}
                            onCheckedChange={(v) => setValue('aktiv', v)}
                            disabled={!isEditing}
                          />
                          <Label>Aktiv</Label>
                        </div>
                        <div className="flex items-center gap-2">
                          <Switch
                            checked={watchFields.gefahrgut}
                            onCheckedChange={(v) => setValue('gefahrgut', v)}
                            disabled={!isEditing}
                          />
                          <Label className="text-red-600">Gefahrgut</Label>
                        </div>
                      </div>
                    </div>
                  )}

                  {/* Einheiten */}
                  {activeSection === 'einheiten' && (
                    <div className="space-y-4">
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1.5">
                          <Label>Mengeneinheit</Label>
                          <Select 
                            value={watchFields.einheit || 'kg'} 
                            onValueChange={(v) => setValue('einheit', v)}
                            disabled={!isEditing}
                          >
                            <SelectTrigger className="bg-white">
                              <SelectValue />
                            </SelectTrigger>
                            <SelectContent>
                              <SelectItem value="kg">kg (Kilogramm)</SelectItem>
                              <SelectItem value="t">t (Tonne)</SelectItem>
                              <SelectItem value="Stk">Stk (Stück)</SelectItem>
                              <SelectItem value="m³">m³ (Kubikmeter)</SelectItem>
                              <SelectItem value="l">l (Liter)</SelectItem>
                            </SelectContent>
                          </Select>
                        </div>
                        <div className="space-y-1.5">
                          <Label>Preiseinheit</Label>
                          <Select 
                            value={watchFields.einheit_preis || 't'} 
                            onValueChange={(v) => setValue('einheit_preis', v)}
                            disabled={!isEditing}
                          >
                            <SelectTrigger className="bg-white">
                              <SelectValue />
                            </SelectTrigger>
                            <SelectContent>
                              <SelectItem value="kg">kg (Kilogramm)</SelectItem>
                              <SelectItem value="t">t (Tonne)</SelectItem>
                              <SelectItem value="Stk">Stk (Stück)</SelectItem>
                              <SelectItem value="m³">m³ (Kubikmeter)</SelectItem>
                              <SelectItem value="l">l (Liter)</SelectItem>
                            </SelectContent>
                          </Select>
                        </div>
                      </div>
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1.5">
                          <Label>Mengendivisor</Label>
                          <Input 
                            type="number"
                            {...register('mengendivisor', { valueAsNumber: true })} 
                            disabled={!isEditing} 
                            className="bg-white" 
                          />
                          <p className="text-xs text-gray-500">z.B. 1000 für kg → t Umrechnung</p>
                        </div>
                        <div className="space-y-1.5">
                          <Label>Nachkommastellen</Label>
                          <Select 
                            value={String(watchFields.genauigkeit_mengen ?? 3)} 
                            onValueChange={(v) => setValue('genauigkeit_mengen', Number(v))}
                            disabled={!isEditing}
                          >
                            <SelectTrigger className="bg-white">
                              <SelectValue />
                            </SelectTrigger>
                            <SelectContent>
                              <SelectItem value="0">0 (Ganze Zahlen)</SelectItem>
                              <SelectItem value="1">1 Nachkommastelle</SelectItem>
                              <SelectItem value="2">2 Nachkommastellen</SelectItem>
                              <SelectItem value="3">3 Nachkommastellen</SelectItem>
                            </SelectContent>
                          </Select>
                        </div>
                      </div>
                    </div>
                  )}

                  {/* Klassifizierung */}
                  {activeSection === 'klassifizierung' && (
                    <div className="space-y-4">
                      <h3 className="font-semibold text-gray-900">Artikeleigenschaften</h3>
                      <div className="grid grid-cols-2 gap-4">
                        <div className="flex items-center gap-2 p-3 bg-gray-50 rounded-lg">
                          <Switch
                            checked={watchFields.ist_produkt}
                            onCheckedChange={(v) => setValue('ist_produkt', v)}
                            disabled={!isEditing}
                          />
                          <Label>Produkt</Label>
                        </div>
                        <div className="flex items-center gap-2 p-3 bg-gray-50 rounded-lg">
                          <Switch
                            checked={watchFields.dienstleistung}
                            onCheckedChange={(v) => setValue('dienstleistung', v)}
                            disabled={!isEditing}
                          />
                          <Label>Dienstleistung</Label>
                        </div>
                        <div className="flex items-center gap-2 p-3 bg-gray-50 rounded-lg">
                          <Switch
                            checked={watchFields.ist_leergut}
                            onCheckedChange={(v) => setValue('ist_leergut', v)}
                            disabled={!isEditing}
                          />
                          <Label>Leergut-Artikel</Label>
                        </div>
                        <div className="flex items-center gap-2 p-3 bg-gray-50 rounded-lg">
                          <Switch
                            checked={watchFields.elektro_elektronik}
                            onCheckedChange={(v) => setValue('elektro_elektronik', v)}
                            disabled={!isEditing}
                          />
                          <Label>Elektro/Elektronik</Label>
                        </div>
                        <div className="flex items-center gap-2 p-3 bg-green-50 rounded-lg">
                          <Switch
                            checked={watchFields.end_of_waste}
                            onCheckedChange={(v) => setValue('end_of_waste', v)}
                            disabled={!isEditing}
                          />
                          <Label className="text-green-700">End of Waste</Label>
                        </div>
                        <div className="flex items-center gap-2 p-3 bg-green-50 rounded-lg">
                          <Switch
                            checked={watchFields.end_of_waste_lager}
                            onCheckedChange={(v) => setValue('end_of_waste_lager', v)}
                            disabled={!isEditing}
                          />
                          <Label className="text-green-700">End of Waste (Lager)</Label>
                        </div>
                      </div>
                    </div>
                  )}

                  {/* AVV-Codes */}
                  {activeSection === 'avv' && (
                    <div className="space-y-4">
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1.5">
                          <Label>AVV-Code Eingang</Label>
                          <Input {...register('avv_code_eingang')} disabled={!isEditing} className="bg-white font-mono" />
                        </div>
                        <div className="space-y-1.5">
                          <Label>AVV-Code Ausgang</Label>
                          <Input {...register('avv_code_ausgang')} disabled={!isEditing} className="bg-white font-mono" />
                        </div>
                      </div>
                      <div className="space-y-1.5">
                        <Label>EAK-Code (Baranlieferungen)</Label>
                        <Input {...register('eakcode')} disabled={!isEditing} className="bg-white font-mono" />
                        <p className="text-xs text-gray-500">AVV-Code für Baranlieferungen / Kleinanlieferer</p>
                      </div>
                      <div className="space-y-1.5">
                        <Label>Österreichische AVV</Label>
                        <Input {...register('oesterreichische_avv')} disabled={!isEditing} className="bg-white font-mono" />
                      </div>
                    </div>
                  )}

                  {/* Zoll & Export */}
                  {activeSection === 'zoll' && (
                    <div className="space-y-6">
                      {/* Zolltarif */}
                      <div className="space-y-3">
                        <h3 className="font-semibold text-gray-900">Zolltarifnummer</h3>
                        <div className="space-y-1.5">
                          <Label>Warennummer</Label>
                          <ReferenceSelect
                            module="artikel"
                            fieldName="zolltarifnr"
                            value={watch('zolltarifnr')}
                            onChange={(val) => setValue('zolltarifnr', val)}
                            onSelectOption={(option: ReferenceOption | null) => {
                              // Automatisch Warennotiz mit vollständigem Text + Link füllen
                              if (option) {
                                // HTML-Tags entfernen für sauberen Text
                                const cleanText = (text: string) => {
                                  if (!text) return '';
                                  return text
                                    .replace(/<[^>]+>/g, '')
                                    .replace(/&hellip;/g, '...')
                                    .replace(/&amp;/g, '&')
                                    .trim();
                                };
                                
                                const code = option.value || '';
                                const description = cleanText(option.data?.value || option.display || '');
                                const detailUrl = option.data?.detail_url || option.data?.data || '';
                                
                                // URL separat speichern für klickbaren Link
                                setZolltarifUrl(detailUrl || null);
                                
                                // Formatierte Notiz erstellen MIT URL (für Persistenz nach Speichern)
                                let notiz = `${code}`;
                                if (description && description !== code) {
                                  notiz += `\n${description}`;
                                }
                                // URL am Ende speichern (wird beim Laden extrahiert)
                                if (detailUrl) {
                                  notiz += `\n\n[URL]${detailUrl}[/URL]`;
                                }
                                
                                setValue('zolltarifnotiz', notiz);
                              } else {
                                setValue('zolltarifnotiz', '');
                                setZolltarifUrl(null);
                              }
                            }}
                            placeholder="Zolltarifnummer auswählen..."
                            disabled={!isEditing}
                            className="bg-white"
                          />
                        </div>
                        <div className="space-y-1.5">
                          <Label className="flex items-center gap-2">
                            Warennotiz
                            <span className="text-xs text-muted-foreground font-normal">(automatisch aus Zolltarifnummer)</span>
                          </Label>
                          <Textarea 
                            value={(watch('zolltarifnotiz') || '').replace(/\n\n\[URL\].*?\[\/URL\]/g, '')}
                            readOnly
                            className="bg-gray-50 text-gray-700 cursor-not-allowed resize-none" 
                            rows={4}
                            placeholder="Wählen Sie eine Zolltarifnummer aus..."
                          />
                          {/* Klickbarer Link zur externen Zolltarifnummer-Seite */}
                          {zolltarifUrl && (
                            <a
                              href={zolltarifUrl}
                              target="_blank"
                              rel="noopener noreferrer"
                              className="inline-flex items-center gap-2 px-3 py-2 mt-1 text-sm font-medium text-emerald-700 bg-emerald-50 hover:bg-emerald-100 border border-emerald-200 rounded-lg transition-colors group"
                              data-testid="zolltarif-external-link"
                            >
                              <ExternalLink className="h-4 w-4 group-hover:scale-110 transition-transform" />
                              <span>Details auf zolltarifnummern.de ansehen</span>
                            </a>
                          )}
                        </div>
                      </div>

                      {/* Basel */}
                      <div className="space-y-3">
                        <h3 className="font-semibold text-gray-900">Basel-Konvention</h3>
                        <div className="grid grid-cols-3 gap-4">
                          <div className="space-y-1.5">
                            <Label>Basel-Code</Label>
                            <Input {...register('basel_code')} disabled={!isEditing} className="bg-white font-mono" />
                          </div>
                          <div className="col-span-2 space-y-1.5">
                            <Label>Basel-Notiz</Label>
                            <Textarea {...register('basel_notiz')} disabled={!isEditing} className="bg-white" rows={2} />
                          </div>
                        </div>
                      </div>

                      {/* OECD */}
                      <div className="space-y-3">
                        <h3 className="font-semibold text-gray-900">OECD-Code</h3>
                        <div className="grid grid-cols-3 gap-4">
                          <div className="space-y-1.5">
                            <Label>OECD-Code</Label>
                            <Input {...register('oecd_code')} disabled={!isEditing} className="bg-white font-mono" />
                          </div>
                          <div className="col-span-2 space-y-1.5">
                            <Label>OECD-Notiz</Label>
                            <Textarea {...register('oecd_notiz')} disabled={!isEditing} className="bg-white" rows={2} />
                          </div>
                        </div>
                      </div>

                      {/* Anhang 7 */}
                      <div className="space-y-3">
                        <h3 className="font-semibold text-gray-900">Anhang 7</h3>
                        <div className="grid grid-cols-2 gap-4">
                          <div className="space-y-1.5">
                            <Label>Anhang 7 (IIIA) Code</Label>
                            <Input {...register('anhang7_3a_code')} disabled={!isEditing} className="bg-white font-mono" />
                          </div>
                          <div className="space-y-1.5">
                            <Label>Anhang 7 (IIIB) Code</Label>
                            <Input {...register('anhang7_3b_code')} disabled={!isEditing} className="bg-white font-mono" />
                          </div>
                        </div>
                        <div className="space-y-1.5">
                          <Label>Anhang 7 (IIIA) Text</Label>
                          <Textarea {...register('anhang7_3a_text')} disabled={!isEditing} className="bg-white" rows={2} />
                        </div>
                        <div className="space-y-1.5">
                          <Label>Anhang 7 (IIIB) Text</Label>
                          <Textarea {...register('anhang7_3b_text')} disabled={!isEditing} className="bg-white" rows={2} />
                        </div>
                      </div>
                    </div>
                  )}

                  {/* Bemerkungen */}
                  {activeSection === 'bemerkungen' && (
                    <div className="space-y-4">
                      <div className="space-y-1.5">
                        <Label>Interne Bemerkungen</Label>
                        <Textarea 
                          {...register('bemerkung_intern')} 
                          disabled={!isEditing} 
                          className="bg-white" 
                          rows={8}
                          placeholder="Interne Notizen zum Artikel..."
                        />
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
    </div>
  );
}
