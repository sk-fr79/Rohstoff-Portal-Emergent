import { useState, useMemo, useEffect } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion, AnimatePresence } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import { 
  Plus, MoreHorizontal, Pencil, Trash2, FileText, Eye, 
  Save, X, Building2, Package, Calendar, Euro, Truck,
  ArrowDownToLine, ArrowUpFromLine, CheckCircle, Clock,
  AlertTriangle, Ban, Loader2
} from 'lucide-react';
import { kontrakteApi, api } from '@/services/api/client';
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
  zahlungsbedingung: z.string().max(100).nullish(),
  lieferbedingung: z.string().max(50).nullish(),
  
  // Status
  status: z.string().default('OFFEN'),
  aktiv: z.boolean().default(true),
  deleted: z.boolean().default(false),
  
  // Bemerkungen
  bemerkung_extern: z.string().max(2000).nullish(),
  bemerkung_intern: z.string().max(2000).nullish(),
});

// Kontraktposition Schema
const positionSchema = z.object({
  positionsnummer: z.number().default(1),
  id_artikel_sorte: z.string().nullish(),
  anr1: z.string().max(10).nullish(),
  artbez1: z.string().max(80).nullish(),
  anzahl: z.number().nullish(),
  einheitkurz: z.string().max(10).default('t'),
  einzelpreis: z.number().nullish(),
  gesamtpreis: z.number().nullish(),
  mengen_toleranz_prozent: z.number().default(10),
  bemerkung: z.string().max(1000).nullish(),
});

type KontraktForm = z.infer<typeof kontraktSchema>;
type PositionForm = z.infer<typeof positionSchema>;

interface Kontrakt extends KontraktForm {
  id: string;
  kontraktnr?: string;
  positionen?: Position[];
  erstellt_am?: string;
  letzte_aenderung?: string;
}

interface Position extends PositionForm {
  id: string;
}

// ========================== SIDEBAR SECTIONS ==========================
const sidebarSections = [
  { id: 'kopf', label: 'Kopfdaten', icon: FileText },
  { id: 'partner', label: 'Vertragspartner', icon: Building2 },
  { id: 'termine', label: 'Termine', icon: Calendar },
  { id: 'konditionen', label: 'Konditionen', icon: Euro },
  { id: 'positionen', label: 'Positionen', icon: Package },
  { id: 'bemerkungen', label: 'Bemerkungen', icon: FileText },
];

// Status-Farben
const statusColors: Record<string, { bg: string; text: string }> = {
  OFFEN: { bg: 'bg-blue-100', text: 'text-blue-700' },
  AKTIV: { bg: 'bg-green-100', text: 'text-green-700' },
  TEILERFUELLT: { bg: 'bg-yellow-100', text: 'text-yellow-700' },
  ERFUELLT: { bg: 'bg-emerald-100', text: 'text-emerald-700' },
  STORNO: { bg: 'bg-red-100', text: 'text-red-700' },
  ABGELAUFEN: { bg: 'bg-gray-100', text: 'text-gray-700' },
};

// ========================== COMPONENT ==========================
export function KontraktePage() {
  const queryClient = useQueryClient();
  const [showCreateDialog, setShowCreateDialog] = useState(false);
  const [selectedKontrakt, setSelectedKontrakt] = useState<Kontrakt | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [activeSection, setActiveSection] = useState('kopf');
  const [filterTyp, setFilterTyp] = useState<string>('');

  const { register, handleSubmit, reset, setValue, watch, formState: { errors } } = useForm<KontraktForm>({
    resolver: zodResolver(kontraktSchema),
    defaultValues: {
      vorgang_typ: 'EK',
      waehrung_kurz: 'EUR',
      status: 'OFFEN',
      aktiv: true,
    }
  });

  const watchFields = watch();

  // Queries & Mutations
  const { data: kontrakteData, isLoading } = useQuery({
    queryKey: ['kontrakte'],
    queryFn: async () => {
      const response = await kontrakteApi.list();
      return response.data;
    }
  });

  // Filter data
  const filteredData = useMemo(() => {
    if (!kontrakteData?.data) return [];
    if (!filterTyp) return kontrakteData.data;
    return kontrakteData.data.filter((k: Kontrakt) => k.vorgang_typ === filterTyp);
  }, [kontrakteData, filterTyp]);

  const createMutation = useMutation({
    mutationFn: (data: KontraktForm) => kontrakteApi.create(data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
      toast.success('Kontrakt erfolgreich erstellt');
      setShowCreateDialog(false);
      reset();
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Erstellen');
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
      toast.error(error.response?.data?.detail || 'Fehler beim Aktualisieren');
    }
  });

  const deleteMutation = useMutation({
    mutationFn: (id: string) => kontrakteApi.delete(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
      toast.success('Kontrakt erfolgreich gelöscht');
      setSelectedKontrakt(null);
    }
  });

  // Submit Handler
  const onSubmit = async (data: KontraktForm) => {
    if (selectedKontrakt && isEditing) {
      updateMutation.mutate({ id: selectedKontrakt.id, data });
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

  // Detailansicht öffnen
  const openDetail = (kontrakt: Kontrakt) => {
    setSelectedKontrakt(kontrakt);
    setIsEditing(false);
    setActiveSection('kopf');
    Object.entries(kontrakt).forEach(([key, value]) => {
      if (key in kontraktSchema.shape) {
        setValue(key as keyof KontraktForm, value as any);
      }
    });
  };

  // Tabellen-Spalten
  const columns: ColumnDef<Kontrakt>[] = useMemo(() => [
    {
      accessorKey: 'kontraktnr',
      header: 'Nr.',
      cell: ({ row }) => (
        <span className="font-mono text-sm font-semibold">
          {row.original.kontraktnr || row.original.buchungsnummer || '-'}
        </span>
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
              <span className="text-sm font-medium text-green-700">Einkauf</span>
            </>
          ) : (
            <>
              <ArrowUpFromLine className="h-4 w-4 text-blue-600" />
              <span className="text-sm font-medium text-blue-700">Verkauf</span>
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
      accessorKey: 'status',
      header: 'Status',
      cell: ({ row }) => {
        const status = row.original.status || 'OFFEN';
        const colors = statusColors[status] || statusColors.OFFEN;
        return (
          <Badge className={cn(colors.bg, colors.text)}>
            {status}
          </Badge>
        );
      }
    },
    {
      accessorKey: 'positionen',
      header: 'Positionen',
      cell: ({ row }) => (
        <span className="text-sm">
          {row.original.positionen?.length || 0}
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

  return (
    <div className="flex h-full">
      {/* Hauptbereich - Liste */}
      <div className={cn(
        "flex-1 p-6 overflow-auto transition-all duration-300",
        selectedKontrakt ? "mr-[600px]" : ""
      )}>
        {/* Header */}
        <div className="flex items-center justify-between mb-6">
          <div>
            <h1 className="text-2xl font-bold text-gray-900">Kontrakte</h1>
            <p className="text-sm text-gray-500 mt-1">Einkaufs- und Verkaufskontrakte verwalten</p>
          </div>
          <div className="flex items-center gap-3">
            <Select value={filterTyp} onValueChange={setFilterTyp}>
              <SelectTrigger className="w-[150px]">
                <SelectValue placeholder="Alle Typen" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="">Alle Typen</SelectItem>
                <SelectItem value="EK">Einkauf</SelectItem>
                <SelectItem value="VK">Verkauf</SelectItem>
              </SelectContent>
            </Select>
            <Button onClick={() => setShowCreateDialog(true)} className="bg-emerald-600 hover:bg-emerald-700">
              <Plus className="h-4 w-4 mr-2" />
              Neuer Kontrakt
            </Button>
          </div>
        </div>

        {/* Tabelle */}
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

      {/* Detail-Panel (Slide-In) */}
      <AnimatePresence>
        {selectedKontrakt && (
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
                  selectedKontrakt.vorgang_typ === 'EK' ? "bg-green-100" : "bg-blue-100"
                )}>
                  {selectedKontrakt.vorgang_typ === 'EK' ? (
                    <ArrowDownToLine className="h-5 w-5 text-green-600" />
                  ) : (
                    <ArrowUpFromLine className="h-5 w-5 text-blue-600" />
                  )}
                </div>
                <div>
                  <h2 className="font-bold text-lg">
                    {selectedKontrakt.kontraktnr || selectedKontrakt.buchungsnummer || 'Neuer Kontrakt'}
                  </h2>
                  <div className="flex items-center gap-2">
                    <span className="text-sm text-gray-500">
                      {selectedKontrakt.vorgang_typ === 'EK' ? 'Einkaufskontrakt' : 'Verkaufskontrakt'}
                    </span>
                    <Badge className={cn(
                      statusColors[selectedKontrakt.status || 'OFFEN']?.bg,
                      statusColors[selectedKontrakt.status || 'OFFEN']?.text
                    )}>
                      {selectedKontrakt.status || 'OFFEN'}
                    </Badge>
                  </div>
                </div>
              </div>
              <div className="flex items-center gap-2">
                <Button
                  variant={isEditing ? "default" : "outline"}
                  size="sm"
                  onClick={() => isEditing ? handleSave() : setIsEditing(true)}
                  className={isEditing ? "bg-emerald-600 hover:bg-emerald-700" : ""}
                >
                  {isEditing ? <Save className="h-4 w-4 mr-1" /> : <Pencil className="h-4 w-4 mr-1" />}
                  {isEditing ? 'Speichern' : 'Bearbeiten'}
                </Button>
                <Button variant="ghost" size="icon" onClick={() => setSelectedKontrakt(null)}>
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
                  {/* Kopfdaten */}
                  {activeSection === 'kopf' && (
                    <div className="space-y-4">
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1.5">
                          <Label>Kontrakttyp</Label>
                          <Select 
                            value={watchFields.vorgang_typ || 'EK'} 
                            onValueChange={(v) => setValue('vorgang_typ', v)}
                            disabled={!isEditing}
                          >
                            <SelectTrigger className="bg-white">
                              <SelectValue />
                            </SelectTrigger>
                            <SelectContent>
                              <SelectItem value="EK">Einkaufskontrakt</SelectItem>
                              <SelectItem value="VK">Verkaufskontrakt</SelectItem>
                            </SelectContent>
                          </Select>
                        </div>
                        <div className="space-y-1.5">
                          <Label>Buchungsnummer</Label>
                          <Input {...register('buchungsnummer')} disabled={!isEditing} className="bg-white font-mono" />
                        </div>
                      </div>
                      <div className="space-y-1.5">
                        <Label>Status</Label>
                        <Select 
                          value={watchFields.status || 'OFFEN'} 
                          onValueChange={(v) => setValue('status', v)}
                          disabled={!isEditing}
                        >
                          <SelectTrigger className="bg-white">
                            <SelectValue />
                          </SelectTrigger>
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
                    </div>
                  )}

                  {/* Vertragspartner */}
                  {activeSection === 'partner' && (
                    <div className="space-y-4">
                      <div className="space-y-1.5">
                        <Label>Firma/Name *</Label>
                        <Input {...register('name1')} disabled={!isEditing} className="bg-white" />
                        {errors.name1 && <p className="text-xs text-red-500">{errors.name1.message}</p>}
                      </div>
                      <div className="space-y-1.5">
                        <Label>Name 2 / Abteilung</Label>
                        <Input {...register('name2')} disabled={!isEditing} className="bg-white" />
                      </div>
                      <div className="grid grid-cols-3 gap-4">
                        <div className="col-span-2 space-y-1.5">
                          <Label>Straße</Label>
                          <Input {...register('strasse')} disabled={!isEditing} className="bg-white" />
                        </div>
                        <div className="space-y-1.5">
                          <Label>Hausnummer</Label>
                          <Input {...register('hausnummer')} disabled={!isEditing} className="bg-white" />
                        </div>
                      </div>
                      <div className="grid grid-cols-3 gap-4">
                        <div className="space-y-1.5">
                          <Label>PLZ</Label>
                          <Input {...register('plz')} disabled={!isEditing} className="bg-white" />
                        </div>
                        <div className="col-span-2 space-y-1.5">
                          <Label>Ort</Label>
                          <Input {...register('ort')} disabled={!isEditing} className="bg-white" />
                        </div>
                      </div>
                      <div className="space-y-1.5">
                        <Label>Land</Label>
                        <Input {...register('land')} disabled={!isEditing} className="bg-white" />
                      </div>
                      <div className="grid grid-cols-2 gap-4 pt-4">
                        <div className="space-y-1.5">
                          <Label>Telefon</Label>
                          <Input {...register('telefon')} disabled={!isEditing} className="bg-white" />
                        </div>
                        <div className="space-y-1.5">
                          <Label>Telefax</Label>
                          <Input {...register('telefax')} disabled={!isEditing} className="bg-white" />
                        </div>
                      </div>
                      <div className="space-y-1.5">
                        <Label>E-Mail</Label>
                        <Input {...register('email')} type="email" disabled={!isEditing} className="bg-white" />
                      </div>
                    </div>
                  )}

                  {/* Termine */}
                  {activeSection === 'termine' && (
                    <div className="space-y-4">
                      <div className="space-y-1.5">
                        <Label>Erstellungsdatum</Label>
                        <Input {...register('erstellungsdatum')} type="date" disabled={!isEditing} className="bg-white" />
                      </div>
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1.5">
                          <Label>Gültig von</Label>
                          <Input {...register('gueltig_von')} type="date" disabled={!isEditing} className="bg-white" />
                        </div>
                        <div className="space-y-1.5">
                          <Label>Gültig bis</Label>
                          <Input {...register('gueltig_bis')} type="date" disabled={!isEditing} className="bg-white" />
                        </div>
                      </div>
                      <div className="pt-4 space-y-4">
                        <h3 className="font-semibold text-gray-900">Interner Bearbeiter</h3>
                        <div className="space-y-1.5">
                          <Label>Name</Label>
                          <Input {...register('name_bearbeiter_intern')} disabled={!isEditing} className="bg-white" />
                        </div>
                        <div className="grid grid-cols-2 gap-4">
                          <div className="space-y-1.5">
                            <Label>Telefon</Label>
                            <Input {...register('tel_bearbeiter_intern')} disabled={!isEditing} className="bg-white" />
                          </div>
                          <div className="space-y-1.5">
                            <Label>Fax</Label>
                            <Input {...register('fax_bearbeiter_intern')} disabled={!isEditing} className="bg-white" />
                          </div>
                        </div>
                      </div>
                    </div>
                  )}

                  {/* Konditionen */}
                  {activeSection === 'konditionen' && (
                    <div className="space-y-4">
                      <div className="grid grid-cols-2 gap-4">
                        <div className="space-y-1.5">
                          <Label>Währung</Label>
                          <Select 
                            value={watchFields.waehrung_kurz || 'EUR'} 
                            onValueChange={(v) => setValue('waehrung_kurz', v)}
                            disabled={!isEditing}
                          >
                            <SelectTrigger className="bg-white">
                              <SelectValue />
                            </SelectTrigger>
                            <SelectContent>
                              <SelectItem value="EUR">EUR (Euro)</SelectItem>
                              <SelectItem value="USD">USD (US-Dollar)</SelectItem>
                              <SelectItem value="CHF">CHF (Schweizer Franken)</SelectItem>
                              <SelectItem value="GBP">GBP (Britisches Pfund)</SelectItem>
                            </SelectContent>
                          </Select>
                        </div>
                      </div>
                      <div className="space-y-1.5">
                        <Label>Zahlungsbedingung</Label>
                        <Input {...register('zahlungsbedingung')} disabled={!isEditing} className="bg-white" placeholder="z.B. 30 Tage netto" />
                      </div>
                      <div className="space-y-1.5">
                        <Label>Lieferbedingung</Label>
                        <Input {...register('lieferbedingung')} disabled={!isEditing} className="bg-white" placeholder="z.B. FCA, DAP, CIF" />
                      </div>
                    </div>
                  )}

                  {/* Positionen */}
                  {activeSection === 'positionen' && (
                    <div className="space-y-4">
                      <div className="flex items-center justify-between">
                        <h3 className="font-semibold text-gray-900">Kontraktpositionen</h3>
                        {isEditing && (
                          <Button type="button" size="sm" variant="outline">
                            <Plus className="h-4 w-4 mr-1" />
                            Position hinzufügen
                          </Button>
                        )}
                      </div>
                      
                      {selectedKontrakt.positionen && selectedKontrakt.positionen.length > 0 ? (
                        <div className="space-y-3">
                          {selectedKontrakt.positionen.map((pos, idx) => (
                            <div key={pos.id || idx} className="p-4 bg-gray-50 rounded-lg border">
                              <div className="flex items-center justify-between mb-2">
                                <span className="font-semibold">Position {pos.positionsnummer || idx + 1}</span>
                                <span className="text-sm text-gray-500">{pos.anr1}</span>
                              </div>
                              <div className="text-sm">{pos.artbez1}</div>
                              <div className="flex items-center gap-4 mt-2 text-sm text-gray-600">
                                <span>{pos.anzahl} {pos.einheitkurz}</span>
                                <span>{pos.einzelpreis?.toFixed(2)} €/{pos.einheitkurz}</span>
                              </div>
                            </div>
                          ))}
                        </div>
                      ) : (
                        <div className="text-center py-8 text-gray-500 bg-gray-50 rounded-lg">
                          <Package className="h-12 w-12 mx-auto mb-2 text-gray-300" />
                          <p>Keine Positionen vorhanden</p>
                        </div>
                      )}
                    </div>
                  )}

                  {/* Bemerkungen */}
                  {activeSection === 'bemerkungen' && (
                    <div className="space-y-4">
                      <div className="space-y-1.5">
                        <Label>Externe Bemerkungen (für Partner sichtbar)</Label>
                        <Textarea 
                          {...register('bemerkung_extern')} 
                          disabled={!isEditing} 
                          className="bg-white" 
                          rows={5}
                        />
                      </div>
                      <div className="space-y-1.5">
                        <Label>Interne Bemerkungen</Label>
                        <Textarea 
                          {...register('bemerkung_intern')} 
                          disabled={!isEditing} 
                          className="bg-white" 
                          rows={5}
                        />
                      </div>
                    </div>
                  )}

                  {isEditing && (
                    <div className="mt-6 pt-4 border-t">
                      <Button 
                        type="button" 
                        onClick={handleSave}
                        className="w-full bg-emerald-600 hover:bg-emerald-700"
                      >
                        <Save className="h-4 w-4 mr-2" />
                        Speichern
                      </Button>
                    </div>
                  )}
                </form>
              </ScrollArea>
            </div>
          </motion.div>
        )}
      </AnimatePresence>

      {/* Create Dialog */}
      <Dialog open={showCreateDialog} onOpenChange={setShowCreateDialog}>
        <DialogContent className="max-w-lg">
          <DialogHeader>
            <DialogTitle className="flex items-center gap-2">
              <FileText className="h-5 w-5 text-emerald-500" />
              Neuer Kontrakt
            </DialogTitle>
            <DialogDescription>
              Legen Sie einen neuen Einkaufs- oder Verkaufskontrakt an.
            </DialogDescription>
          </DialogHeader>

          <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-1.5">
                <Label>Kontrakttyp *</Label>
                <Select value={watchFields.vorgang_typ || 'EK'} onValueChange={(v) => setValue('vorgang_typ', v)}>
                  <SelectTrigger>
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="EK">Einkaufskontrakt</SelectItem>
                    <SelectItem value="VK">Verkaufskontrakt</SelectItem>
                  </SelectContent>
                </Select>
              </div>
              <div className="space-y-1.5">
                <Label>Buchungsnummer</Label>
                <Input {...register('buchungsnummer')} className="font-mono" />
              </div>
            </div>

            <div className="space-y-1.5">
              <Label>Vertragspartner (Firma/Name) *</Label>
              <Input {...register('name1')} placeholder="Firma GmbH" />
              {errors.name1 && <p className="text-xs text-red-500">{errors.name1.message}</p>}
            </div>

            <div className="grid grid-cols-3 gap-4">
              <div className="space-y-1.5">
                <Label>PLZ</Label>
                <Input {...register('plz')} />
              </div>
              <div className="col-span-2 space-y-1.5">
                <Label>Ort</Label>
                <Input {...register('ort')} />
              </div>
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-1.5">
                <Label>Gültig von</Label>
                <Input {...register('gueltig_von')} type="date" />
              </div>
              <div className="space-y-1.5">
                <Label>Gültig bis</Label>
                <Input {...register('gueltig_bis')} type="date" />
              </div>
            </div>

            <DialogFooter>
              <Button type="button" variant="outline" onClick={() => { setShowCreateDialog(false); reset(); }}>
                Abbrechen
              </Button>
              <Button type="submit" className="bg-emerald-600 hover:bg-emerald-700">
                <Plus className="h-4 w-4 mr-2" />
                Erstellen
              </Button>
            </DialogFooter>
          </form>
        </DialogContent>
      </Dialog>
    </div>
  );
}
