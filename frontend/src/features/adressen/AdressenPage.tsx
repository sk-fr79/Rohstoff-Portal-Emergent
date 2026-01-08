import { useState, useMemo, useEffect } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion, AnimatePresence } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import { 
  Plus, MoreHorizontal, Pencil, Trash2, Eye, Building2, User, MapPin, 
  Save, Phone, Mail, Globe, CreditCard, FileText, Users, X, ChevronRight,
  Banknote, Shield, Clock, MessageSquare, AlertTriangle
} from 'lucide-react';
import { adressenApi } from '@/services/api/client';
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

// ========================== SCHEMA ==========================
const adresseSchema = z.object({
  anrede: z.string().max(20).optional(),
  vorname: z.string().max(30).optional(),
  name1: z.string().min(1, 'Name/Firma erforderlich').max(40),
  name2: z.string().max(40).optional(),
  name3: z.string().max(40).optional(),
  rechtsform: z.string().max(30).optional(),
  strasse: z.string().max(45).optional(),
  hausnummer: z.string().max(10).optional(),
  plz: z.string().max(10).optional(),
  ort: z.string().max(30).optional(),
  ortzusatz: z.string().max(30).optional(),
  land: z.string().max(30).optional(),
  sprache: z.string().max(20).optional(),
  plz_postfach: z.string().max(10).optional(),
  postfach: z.string().max(20).optional(),
  postfach_aktiv: z.boolean().default(false),
  telefon: z.string().max(30).optional(),
  telefax: z.string().max(30).optional(),
  email: z.string().email().optional().or(z.literal('')),
  webseite: z.string().max(100).optional(),
  latitude: z.number().optional().nullable(),
  longitude: z.number().optional().nullable(),
  wartezeit_min: z.number().optional().nullable(),
  adresstyp: z.number().min(1).max(5).default(1),
  aktiv: z.boolean().default(true),
  wareneingang: z.boolean().default(true),
  warenausgang: z.boolean().default(true),
  barkunde: z.boolean().default(false),
  scheckdruck: z.boolean().default(false),
  ist_firma: z.boolean().default(true),
  firma_ohne_ustid: z.boolean().default(false),
  privat_mit_ustid: z.boolean().default(false),
  betreuer: z.string().max(20).optional(),
  betreuer2: z.string().max(20).optional(),
  kreditor_nr: z.string().max(30).optional(),
  debitor_nr: z.string().max(30).optional(),
  lief_nr: z.string().max(60).optional(),
  abn_nr: z.string().max(60).optional(),
  betriebs_nr_saa: z.string().max(30).optional(),
  sondernummer: z.string().max(30).optional(),
  umsatzsteuer_lkz: z.string().max(3).optional(),
  umsatzsteuer_id: z.string().max(20).optional(),
  steuernummer: z.string().max(20).optional(),
  ust_at: z.string().max(20).optional(),
  ust_nl: z.string().max(20).optional(),
  ust_ch: z.string().max(20).optional(),
  handelsregister: z.string().max(50).optional(),
  waehrung: z.string().max(3).optional(),
  zahlungsbedingung_ek: z.string().max(100).optional(),
  zahlungsbedingung_vk: z.string().max(100).optional(),
  lieferbedingung_ek: z.string().max(50).optional(),
  lieferbedingung_vk: z.string().max(50).optional(),
  rechnungen_sperren: z.boolean().default(false),
  gutschriften_sperren: z.boolean().default(false),
  wareneingang_sperren: z.boolean().default(false),
  warenausgang_sperren: z.boolean().default(false),
  wird_nicht_gemahnt: z.boolean().default(false),
  ausweis_nummer: z.string().max(30).optional(),
  ausweis_ablauf: z.string().max(10).optional(),
  geburtsdatum: z.string().max(10).optional(),
  bemerkungen: z.string().max(700).optional(),
  bemerkung_fahrplan: z.string().max(300).optional(),
  lieferinfo_tpa: z.string().max(300).optional(),
});

type AdresseForm = z.infer<typeof adresseSchema>;

interface Adresse extends AdresseForm {
  id: string;
  kdnr: string;
  erstellt_am?: string;
  letzte_aenderung?: string;
}

// ========================== SIDEBAR SECTIONS ==========================
const detailSections = [
  { id: 'stamm', label: 'Stammdaten', icon: Building2 },
  { id: 'kontakt', label: 'Kontakt', icon: Phone },
  { id: 'finanzen', label: 'Finanzen', icon: CreditCard },
  { id: 'steuer', label: 'Steuer', icon: FileText },
  { id: 'sperren', label: 'Sperren', icon: Shield },
  { id: 'bemerkungen', label: 'Bemerkungen', icon: MessageSquare },
];

// ========================== COMPONENT ==========================
export function AdressenPage() {
  const queryClient = useQueryClient();
  const [showCreateDialog, setShowCreateDialog] = useState(false);
  const [selectedAdresse, setSelectedAdresse] = useState<Adresse | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [activeSection, setActiveSection] = useState('stamm');

  const { register, handleSubmit, reset, setValue, watch, formState: { errors } } = useForm<AdresseForm>({
    resolver: zodResolver(adresseSchema),
    defaultValues: { adresstyp: 1, aktiv: true, wareneingang: true, warenausgang: true, ist_firma: true, land: 'Deutschland', sprache: 'Deutsch', waehrung: 'EUR', firma_ohne_ustid: false, privat_mit_ustid: false },
  });

  const watchFields = watch();

  // Queries & Mutations
  const { data: adressenData, isLoading } = useQuery({
    queryKey: ['adressen'],
    queryFn: () => adressenApi.getAll(),
  });

  const createMutation = useMutation({
    mutationFn: (data: AdresseForm) => adressenApi.create(data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['adressen'] });
      setShowCreateDialog(false);
      reset();
      toast.success('Adresse erfolgreich erstellt');
    },
    onError: (error: any) => {
      const detail = error.response?.data?.detail;
      if (detail?.fehler) {
        detail.fehler.forEach((f: string) => toast.error(f));
      } else {
        toast.error('Fehler beim Erstellen der Adresse');
      }
    },
  });

  const updateMutation = useMutation({
    mutationFn: ({ id, data }: { id: string; data: AdresseForm }) => adressenApi.update(id, data),
    onSuccess: (response) => {
      queryClient.invalidateQueries({ queryKey: ['adressen'] });
      setSelectedAdresse(response.data.data);
      setIsEditing(false);
      toast.success('Adresse erfolgreich aktualisiert');
    },
    onError: (error: any) => {
      const detail = error.response?.data?.detail;
      if (detail?.fehler) {
        detail.fehler.forEach((f: string) => toast.error(f));
      } else {
        toast.error('Fehler beim Aktualisieren');
      }
    },
  });

  const deleteMutation = useMutation({
    mutationFn: (id: string) => adressenApi.delete(id),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['adressen'] });
      setSelectedAdresse(null);
      toast.success('Adresse gelöscht');
    },
    onError: () => toast.error('Fehler beim Löschen'),
  });

  // Load selected address into form
  useEffect(() => {
    if (selectedAdresse) {
      Object.entries(selectedAdresse).forEach(([key, value]) => {
        if (key in adresseSchema.shape) {
          setValue(key as keyof AdresseForm, value as any);
        }
      });
    }
  }, [selectedAdresse, setValue]);

  const onSubmit = (data: AdresseForm) => {
    if (isEditing && selectedAdresse) {
      updateMutation.mutate({ id: selectedAdresse.id, data });
    } else {
      createMutation.mutate(data);
    }
  };

  const handleRowDoubleClick = (adresse: Adresse) => {
    setSelectedAdresse(adresse);
    setIsEditing(false);
    setActiveSection('stamm');
  };

  // Table Columns
  const columns: ColumnDef<Adresse>[] = useMemo(() => [
    { accessorKey: 'kdnr', header: 'Kd.Nr', size: 100 },
    { accessorKey: 'name1', header: 'Name/Firma', size: 200 },
    { accessorKey: 'ort', header: 'Ort', size: 150 },
    { accessorKey: 'telefon', header: 'Telefon', size: 150 },
    {
      accessorKey: 'ist_firma',
      header: 'Typ',
      size: 80,
      cell: ({ row }) => (
        <span className={cn(
          "px-2 py-1 rounded-full text-xs font-medium",
          row.original.ist_firma 
            ? "bg-blue-100 text-blue-700" 
            : "bg-purple-100 text-purple-700"
        )}>
          {row.original.ist_firma ? 'Firma' : 'Privat'}
        </span>
      ),
    },
    {
      accessorKey: 'aktiv',
      header: 'Status',
      size: 80,
      cell: ({ row }) => (
        <span className={cn(
          "px-2 py-1 rounded-full text-xs font-medium",
          row.original.aktiv 
            ? "bg-emerald-100 text-emerald-700" 
            : "bg-gray-100 text-gray-500"
        )}>
          {row.original.aktiv ? 'Aktiv' : 'Inaktiv'}
        </span>
      ),
    },
    {
      id: 'actions',
      size: 60,
      cell: ({ row }) => (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" size="icon" className="h-8 w-8">
              <MoreHorizontal className="h-4 w-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuItem onClick={() => handleRowDoubleClick(row.original)}>
              <Eye className="h-4 w-4 mr-2" />Details
            </DropdownMenuItem>
            <DropdownMenuItem onClick={() => { setSelectedAdresse(row.original); setIsEditing(true); }}>
              <Pencil className="h-4 w-4 mr-2" />Bearbeiten
            </DropdownMenuItem>
            <DropdownMenuSeparator />
            <DropdownMenuItem className="text-red-600" onClick={() => deleteMutation.mutate(row.original.id)}>
              <Trash2 className="h-4 w-4 mr-2" />Löschen
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      ),
    },
  ], []);

  const adressen = adressenData?.data?.data || adressenData?.data || [];

  // ========================== RENDER ==========================
  return (
    <div className="h-full flex flex-col">
      {/* Header */}
      <div className="bg-white border-b border-gray-200 px-6 py-4">
        <div className="flex items-center justify-between">
          <div>
            <h1 className="text-xl font-semibold text-gray-900">Adressen</h1>
            <p className="text-sm text-gray-500 mt-0.5">{adressen.length} Einträge</p>
          </div>
          <Button onClick={() => { reset(); setShowCreateDialog(true); }} className="bg-emerald-500 hover:bg-emerald-600">
            <Plus className="h-4 w-4 mr-2" />Neue Adresse
          </Button>
        </div>
      </div>

      {/* Content Area */}
      <div className="flex-1 flex overflow-hidden">
        {/* Main Table */}
        <div className={cn(
          "flex-1 p-6 overflow-auto transition-all duration-300",
          selectedAdresse && "lg:w-1/2"
        )}>
          <div className="bg-white rounded-xl shadow-sm border border-gray-100">
            <DataTable 
              columns={columns} 
              data={adressen}
              searchKey="name1"
              onRowDoubleClick={handleRowDoubleClick}
            />
          </div>
        </div>

        {/* Detail Panel - Slide-in from right */}
        <AnimatePresence>
          {selectedAdresse && (
            <motion.div
              initial={{ x: '100%', opacity: 0 }}
              animate={{ x: 0, opacity: 1 }}
              exit={{ x: '100%', opacity: 0 }}
              transition={{ type: 'spring', damping: 25, stiffness: 200 }}
              className="w-full lg:w-1/2 border-l border-gray-200 bg-white flex flex-col overflow-hidden"
            >
              {/* Detail Header */}
              <div className="bg-gray-50 border-b border-gray-200 px-4 py-3 flex items-center justify-between">
                <div className="flex items-center gap-3">
                  <div className={cn(
                    "h-10 w-10 rounded-lg flex items-center justify-center",
                    watchFields.ist_firma ? "bg-blue-100" : "bg-purple-100"
                  )}>
                    {watchFields.ist_firma ? <Building2 className="h-5 w-5 text-blue-600" /> : <User className="h-5 w-5 text-purple-600" />}
                  </div>
                  <div>
                    <h2 className="font-semibold text-gray-900">{selectedAdresse.name1}</h2>
                    <p className="text-sm text-gray-500">{selectedAdresse.kdnr}</p>
                  </div>
                </div>
                <div className="flex items-center gap-2">
                  {!isEditing ? (
                    <Button variant="outline" size="sm" onClick={() => setIsEditing(true)}>
                      <Pencil className="h-4 w-4 mr-1" />Bearbeiten
                    </Button>
                  ) : (
                    <Button size="sm" onClick={handleSubmit(onSubmit)} className="bg-emerald-500 hover:bg-emerald-600">
                      <Save className="h-4 w-4 mr-1" />Speichern
                    </Button>
                  )}
                  <Button variant="ghost" size="icon" onClick={() => { setSelectedAdresse(null); setIsEditing(false); }}>
                    <X className="h-4 w-4" />
                  </Button>
                </div>
              </div>

              {/* Detail Content with Sidebar */}
              <div className="flex-1 flex overflow-hidden">
                {/* Sidebar Navigation */}
                <div className="w-48 bg-gray-50 border-r border-gray-200 py-4 flex-shrink-0">
                  {detailSections.map((section) => (
                    <button
                      key={section.id}
                      onClick={() => setActiveSection(section.id)}
                      className={cn(
                        "w-full flex items-center gap-3 px-4 py-2.5 text-sm transition-colors",
                        activeSection === section.id
                          ? "bg-white text-emerald-700 font-medium border-l-2 border-emerald-500 shadow-sm"
                          : "text-gray-600 hover:bg-gray-100"
                      )}
                    >
                      <section.icon className="h-4 w-4" />
                      {section.label}
                    </button>
                  ))}
                </div>

                {/* Section Content */}
                <div className="flex-1 overflow-auto p-6">
                  <form onSubmit={handleSubmit(onSubmit)}>
                    {/* Stammdaten Section */}
                    {activeSection === 'stamm' && (
                      <div className="space-y-6">
                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                            <Building2 className="h-4 w-4 text-emerald-500" />
                            Grundinformationen
                          </h3>
                          <div className="grid grid-cols-2 gap-4">
                            <div className="col-span-2 flex items-center gap-4 p-3 bg-gray-50 rounded-lg">
                              <Label className="text-sm font-medium text-gray-700">Typ:</Label>
                              <div className="flex items-center gap-2">
                                <Switch 
                                  checked={watchFields.ist_firma} 
                                  onCheckedChange={(c) => setValue('ist_firma', c)} 
                                  disabled={!isEditing} 
                                />
                                <span className={cn(
                                  "text-sm font-medium",
                                  watchFields.ist_firma ? "text-blue-600" : "text-purple-600"
                                )}>
                                  {watchFields.ist_firma ? 'Firma' : 'Privatperson'}
                                </span>
                              </div>
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Anrede</Label>
                              <Select value={watchFields.anrede || ""} onValueChange={(v) => setValue('anrede', v)} disabled={!isEditing}>
                                <SelectTrigger className="bg-white"><SelectValue placeholder="Wählen..." /></SelectTrigger>
                                <SelectContent>
                                  <SelectItem value="Herr">Herr</SelectItem>
                                  <SelectItem value="Frau">Frau</SelectItem>
                                  <SelectItem value="Firma">Firma</SelectItem>
                                </SelectContent>
                              </Select>
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Vorname</Label>
                              <Input {...register('vorname')} disabled={!isEditing} className="bg-white" />
                            </div>
                            <div className="col-span-2 space-y-1.5">
                              <Label className="text-sm text-gray-600">Name / Firma *</Label>
                              <Input {...register('name1')} disabled={!isEditing} className="bg-white" />
                              {errors.name1 && <p className="text-xs text-red-500">{errors.name1.message}</p>}
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Name 2</Label>
                              <Input {...register('name2')} disabled={!isEditing} className="bg-white" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Rechtsform</Label>
                              <Select value={watchFields.rechtsform || ""} onValueChange={(v) => setValue('rechtsform', v)} disabled={!isEditing}>
                                <SelectTrigger className="bg-white"><SelectValue placeholder="Wählen..." /></SelectTrigger>
                                <SelectContent>
                                  <SelectItem value="GmbH">GmbH</SelectItem>
                                  <SelectItem value="AG">AG</SelectItem>
                                  <SelectItem value="KG">KG</SelectItem>
                                  <SelectItem value="OHG">OHG</SelectItem>
                                  <SelectItem value="e.K.">e.K.</SelectItem>
                                  <SelectItem value="GbR">GbR</SelectItem>
                                </SelectContent>
                              </Select>
                            </div>
                          </div>
                        </div>

                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                            <MapPin className="h-4 w-4 text-emerald-500" />
                            Adresse
                          </h3>
                          <div className="grid grid-cols-3 gap-4">
                            <div className="col-span-2 space-y-1.5">
                              <Label className="text-sm text-gray-600">Straße</Label>
                              <Input {...register('strasse')} disabled={!isEditing} className="bg-white" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Hausnr.</Label>
                              <Input {...register('hausnummer')} disabled={!isEditing} className="bg-white" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">PLZ</Label>
                              <Input {...register('plz')} disabled={!isEditing} className="bg-white" />
                            </div>
                            <div className="col-span-2 space-y-1.5">
                              <Label className="text-sm text-gray-600">Ort</Label>
                              <Input {...register('ort')} disabled={!isEditing} className="bg-white" />
                            </div>
                            <div className="col-span-3 space-y-1.5">
                              <Label className="text-sm text-gray-600">Land</Label>
                              <Select value={watchFields.land || "Deutschland"} onValueChange={(v) => setValue('land', v)} disabled={!isEditing}>
                                <SelectTrigger className="bg-white"><SelectValue /></SelectTrigger>
                                <SelectContent>
                                  <SelectItem value="Deutschland">Deutschland</SelectItem>
                                  <SelectItem value="Österreich">Österreich</SelectItem>
                                  <SelectItem value="Schweiz">Schweiz</SelectItem>
                                  <SelectItem value="Niederlande">Niederlande</SelectItem>
                                  <SelectItem value="Belgien">Belgien</SelectItem>
                                  <SelectItem value="Frankreich">Frankreich</SelectItem>
                                  <SelectItem value="Polen">Polen</SelectItem>
                                  <SelectItem value="Tschechien">Tschechien</SelectItem>
                                </SelectContent>
                              </Select>
                            </div>
                          </div>
                        </div>

                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                            <Users className="h-4 w-4 text-emerald-500" />
                            Betreuer
                          </h3>
                          <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Betreuer 1</Label>
                              <Input {...register('betreuer')} disabled={!isEditing} className="bg-white" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Betreuer 2</Label>
                              <Input {...register('betreuer2')} disabled={!isEditing} className="bg-white" />
                            </div>
                          </div>
                        </div>
                      </div>
                    )}

                    {/* Kontakt Section */}
                    {activeSection === 'kontakt' && (
                      <div className="space-y-6">
                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                            <Phone className="h-4 w-4 text-emerald-500" />
                            Telefon & Fax
                          </h3>
                          <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Telefon</Label>
                              <Input {...register('telefon')} disabled={!isEditing} className="bg-white" placeholder="+49 123 456789" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Telefax</Label>
                              <Input {...register('telefax')} disabled={!isEditing} className="bg-white" placeholder="+49 123 456789" />
                            </div>
                          </div>
                        </div>

                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                            <Mail className="h-4 w-4 text-emerald-500" />
                            E-Mail & Web
                          </h3>
                          <div className="grid grid-cols-1 gap-4">
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">E-Mail</Label>
                              <Input type="email" {...register('email')} disabled={!isEditing} className="bg-white" placeholder="kontakt@firma.de" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Webseite</Label>
                              <Input {...register('webseite')} disabled={!isEditing} className="bg-white" placeholder="https://www.firma.de" />
                            </div>
                          </div>
                        </div>

                        {/* Postfach */}
                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                            <Mail className="h-4 w-4 text-emerald-500" />
                            Postfach
                          </h3>
                          <div className="grid grid-cols-2 gap-4">
                            <div className="col-span-2 flex items-center gap-2 mb-2">
                              <Switch checked={watchFields.postfach_aktiv} onCheckedChange={(c) => setValue('postfach_aktiv', c)} disabled={!isEditing} />
                              <Label className="text-sm text-gray-600">Postfach verwenden</Label>
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">PLZ Postfach</Label>
                              <Input {...register('plz_postfach')} disabled={!isEditing || !watchFields.postfach_aktiv} className="bg-white" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Postfach</Label>
                              <Input {...register('postfach')} disabled={!isEditing || !watchFields.postfach_aktiv} className="bg-white" />
                            </div>
                          </div>
                        </div>
                      </div>
                    )}

                    {/* Finanzen Section */}
                    {activeSection === 'finanzen' && (
                      <div className="space-y-6">
                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                            <Banknote className="h-4 w-4 text-emerald-500" />
                            Nummern & Codes
                          </h3>
                          <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Kreditor-Nr</Label>
                              <Input {...register('kreditor_nr')} disabled={!isEditing} className="bg-white" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Debitor-Nr</Label>
                              <Input {...register('debitor_nr')} disabled={!isEditing} className="bg-white" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Alt. Lief.-Nr</Label>
                              <Input {...register('lief_nr')} disabled={!isEditing} className="bg-white" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Alt. Abn.-Nr</Label>
                              <Input {...register('abn_nr')} disabled={!isEditing} className="bg-white" />
                            </div>
                          </div>
                        </div>

                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                            <CreditCard className="h-4 w-4 text-emerald-500" />
                            Zahlungsbedingungen
                          </h3>
                          <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Währung</Label>
                              <Select value={watchFields.waehrung || "EUR"} onValueChange={(v) => setValue('waehrung', v)} disabled={!isEditing}>
                                <SelectTrigger className="bg-white"><SelectValue /></SelectTrigger>
                                <SelectContent>
                                  <SelectItem value="EUR">EUR</SelectItem>
                                  <SelectItem value="CHF">CHF</SelectItem>
                                  <SelectItem value="USD">USD</SelectItem>
                                </SelectContent>
                              </Select>
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Handelsregister</Label>
                              <Input {...register('handelsregister')} disabled={!isEditing} className="bg-white" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Zahlungsbedingung (EK)</Label>
                              <Input {...register('zahlungsbedingung_ek')} disabled={!isEditing} className="bg-white" placeholder="30 Tage netto" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Zahlungsbedingung (VK)</Label>
                              <Input {...register('zahlungsbedingung_vk')} disabled={!isEditing} className="bg-white" placeholder="30 Tage netto" />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Lieferbedingung (EK)</Label>
                              <Select value={watchFields.lieferbedingung_ek || ""} onValueChange={(v) => setValue('lieferbedingung_ek', v)} disabled={!isEditing}>
                                <SelectTrigger className="bg-white"><SelectValue placeholder="Incoterms wählen" /></SelectTrigger>
                                <SelectContent>
                                  <SelectItem value="EXW">EXW</SelectItem>
                                  <SelectItem value="FCA">FCA</SelectItem>
                                  <SelectItem value="DAP">DAP</SelectItem>
                                  <SelectItem value="DDP">DDP</SelectItem>
                                </SelectContent>
                              </Select>
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">Lieferbedingung (VK)</Label>
                              <Select value={watchFields.lieferbedingung_vk || ""} onValueChange={(v) => setValue('lieferbedingung_vk', v)} disabled={!isEditing}>
                                <SelectTrigger className="bg-white"><SelectValue placeholder="Incoterms wählen" /></SelectTrigger>
                                <SelectContent>
                                  <SelectItem value="EXW">EXW</SelectItem>
                                  <SelectItem value="FCA">FCA</SelectItem>
                                  <SelectItem value="DAP">DAP</SelectItem>
                                  <SelectItem value="DDP">DDP</SelectItem>
                                </SelectContent>
                              </Select>
                            </div>
                          </div>
                        </div>
                      </div>
                    )}

                    {/* Steuer Section */}
                    {activeSection === 'steuer' && (
                      <div className="space-y-6">
                        {/* UST Einstufung */}
                        <div className="p-4 bg-amber-50 border border-amber-200 rounded-lg">
                          <h3 className="text-sm font-semibold text-amber-800 mb-3 flex items-center gap-2">
                            <AlertTriangle className="h-4 w-4" />
                            Steuerliche Einstufung
                          </h3>
                          <div className="grid grid-cols-2 gap-4">
                            <div className="space-y-2">
                              <div className="flex items-center gap-2">
                                <Switch 
                                  checked={watchFields.firma_ohne_ustid} 
                                  onCheckedChange={(c) => setValue('firma_ohne_ustid', c)} 
                                  disabled={!isEditing || !watchFields.ist_firma || watchFields.land !== 'Deutschland'} 
                                />
                                <Label className="text-sm text-gray-700">Firma ohne UST-ID</Label>
                              </div>
                              <p className="text-xs text-gray-500">Nur für Firmen im Inland</p>
                            </div>
                            <div className="space-y-2">
                              <div className="flex items-center gap-2">
                                <Switch 
                                  checked={watchFields.privat_mit_ustid} 
                                  onCheckedChange={(c) => setValue('privat_mit_ustid', c)} 
                                  disabled={!isEditing || watchFields.ist_firma || watchFields.land !== 'Deutschland'} 
                                />
                                <Label className="text-sm text-gray-700">Privat mit UST-ID</Label>
                              </div>
                              <p className="text-xs text-gray-500">Nur für Privatpersonen im Inland</p>
                            </div>
                          </div>
                        </div>

                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                            <FileText className="h-4 w-4 text-emerald-500" />
                            Umsatzsteuer-IDs
                          </h3>
                          <div className="grid grid-cols-3 gap-4">
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">UST-LKZ</Label>
                              <Input {...register('umsatzsteuer_lkz')} disabled={!isEditing} className="bg-white" placeholder="DE" maxLength={3} />
                            </div>
                            <div className="col-span-2 space-y-1.5">
                              <Label className="text-sm text-gray-600">UST-ID (Basis)</Label>
                              <Input {...register('umsatzsteuer_id')} disabled={!isEditing} className="bg-white" placeholder="123456789" />
                            </div>
                            <div className="col-span-3 space-y-1.5">
                              <Label className="text-sm text-gray-600">Steuernummer</Label>
                              <Input {...register('steuernummer')} disabled={!isEditing} className="bg-white" />
                            </div>
                          </div>
                        </div>

                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4">Weitere UST-IDs</h3>
                          <div className="grid grid-cols-3 gap-4">
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">UST-ID AT</Label>
                              <Input {...register('ust_at')} disabled={!isEditing} className="bg-white" placeholder="ATU..." />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">UST-ID NL</Label>
                              <Input {...register('ust_nl')} disabled={!isEditing} className="bg-white" placeholder="NL..." />
                            </div>
                            <div className="space-y-1.5">
                              <Label className="text-sm text-gray-600">UST-ID CH</Label>
                              <Input {...register('ust_ch')} disabled={!isEditing} className="bg-white" placeholder="CHE..." />
                            </div>
                          </div>
                        </div>

                        {/* Ausweis bei Privatpersonen */}
                        {!watchFields.ist_firma && (
                          <div>
                            <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                              <User className="h-4 w-4 text-emerald-500" />
                              Identifikation (Privatperson)
                            </h3>
                            <div className="grid grid-cols-3 gap-4">
                              <div className="space-y-1.5">
                                <Label className="text-sm text-gray-600">Ausweisnummer</Label>
                                <Input {...register('ausweis_nummer')} disabled={!isEditing} className="bg-white" />
                              </div>
                              <div className="space-y-1.5">
                                <Label className="text-sm text-gray-600">Ausweis gültig bis</Label>
                                <Input {...register('ausweis_ablauf')} disabled={!isEditing} className="bg-white" placeholder="TT.MM.JJJJ" />
                              </div>
                              <div className="space-y-1.5">
                                <Label className="text-sm text-gray-600">Geburtsdatum</Label>
                                <Input {...register('geburtsdatum')} disabled={!isEditing} className="bg-white" placeholder="TT.MM.JJJJ" />
                              </div>
                            </div>
                          </div>
                        )}
                      </div>
                    )}

                    {/* Sperren Section */}
                    {activeSection === 'sperren' && (
                      <div className="space-y-6">
                        <div className="p-4 bg-red-50 border border-red-200 rounded-lg">
                          <h3 className="text-sm font-semibold text-red-800 mb-4 flex items-center gap-2">
                            <Shield className="h-4 w-4" />
                            Sperren & Einschränkungen
                          </h3>
                          <div className="grid grid-cols-2 gap-4">
                            <div className="flex items-center gap-3 p-2 rounded hover:bg-red-100/50">
                              <Switch checked={watchFields.rechnungen_sperren} onCheckedChange={(c) => setValue('rechnungen_sperren', c)} disabled={!isEditing} />
                              <Label className="text-sm text-gray-700">Rechnungen sperren</Label>
                            </div>
                            <div className="flex items-center gap-3 p-2 rounded hover:bg-red-100/50">
                              <Switch checked={watchFields.gutschriften_sperren} onCheckedChange={(c) => setValue('gutschriften_sperren', c)} disabled={!isEditing} />
                              <Label className="text-sm text-gray-700">Gutschriften sperren</Label>
                            </div>
                            <div className="flex items-center gap-3 p-2 rounded hover:bg-red-100/50">
                              <Switch checked={watchFields.wareneingang_sperren} onCheckedChange={(c) => setValue('wareneingang_sperren', c)} disabled={!isEditing} />
                              <Label className="text-sm text-gray-700">Wareneingang sperren</Label>
                            </div>
                            <div className="flex items-center gap-3 p-2 rounded hover:bg-red-100/50">
                              <Switch checked={watchFields.warenausgang_sperren} onCheckedChange={(c) => setValue('warenausgang_sperren', c)} disabled={!isEditing} />
                              <Label className="text-sm text-gray-700">Warenausgang sperren</Label>
                            </div>
                            <div className="col-span-2 flex items-center gap-3 p-2 rounded hover:bg-red-100/50">
                              <Switch checked={watchFields.wird_nicht_gemahnt} onCheckedChange={(c) => setValue('wird_nicht_gemahnt', c)} disabled={!isEditing} />
                              <Label className="text-sm text-gray-700">Wird nicht gemahnt</Label>
                            </div>
                          </div>
                        </div>

                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4">Status</h3>
                          <div className="grid grid-cols-2 gap-4">
                            <div className="flex items-center gap-3 p-3 bg-gray-50 rounded-lg">
                              <Switch checked={watchFields.aktiv} onCheckedChange={(c) => setValue('aktiv', c)} disabled={!isEditing} />
                              <Label className="text-sm text-gray-700">Adresse aktiv</Label>
                            </div>
                            <div className="flex items-center gap-3 p-3 bg-gray-50 rounded-lg">
                              <Switch checked={watchFields.barkunde} onCheckedChange={(c) => setValue('barkunde', c)} disabled={!isEditing} />
                              <Label className="text-sm text-gray-700">Barkunde</Label>
                            </div>
                            <div className="flex items-center gap-3 p-3 bg-gray-50 rounded-lg">
                              <Switch checked={watchFields.wareneingang} onCheckedChange={(c) => setValue('wareneingang', c)} disabled={!isEditing} />
                              <Label className="text-sm text-gray-700">Wareneingang erlaubt</Label>
                            </div>
                            <div className="flex items-center gap-3 p-3 bg-gray-50 rounded-lg">
                              <Switch checked={watchFields.warenausgang} onCheckedChange={(c) => setValue('warenausgang', c)} disabled={!isEditing} />
                              <Label className="text-sm text-gray-700">Warenausgang erlaubt</Label>
                            </div>
                          </div>
                        </div>
                      </div>
                    )}

                    {/* Bemerkungen Section */}
                    {activeSection === 'bemerkungen' && (
                      <div className="space-y-6">
                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                            <MessageSquare className="h-4 w-4 text-emerald-500" />
                            Allgemeine Bemerkungen
                          </h3>
                          <Textarea 
                            {...register('bemerkungen')} 
                            disabled={!isEditing} 
                            className="bg-white min-h-[120px]" 
                            placeholder="Allgemeine Notizen zur Adresse..."
                          />
                        </div>

                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                            <Clock className="h-4 w-4 text-emerald-500" />
                            Fahrplan / Logistik
                          </h3>
                          <Textarea 
                            {...register('bemerkung_fahrplan')} 
                            disabled={!isEditing} 
                            className="bg-white min-h-[100px]" 
                            placeholder="Hinweise für Transport und Logistik..."
                          />
                        </div>

                        <div>
                          <h3 className="text-sm font-semibold text-gray-900 mb-4">Lieferinfo TPA</h3>
                          <Textarea 
                            {...register('lieferinfo_tpa')} 
                            disabled={!isEditing} 
                            className="bg-white min-h-[100px]" 
                            placeholder="Spezielle Lieferinformationen..."
                          />
                        </div>
                      </div>
                    )}
                  </form>
                </div>
              </div>
            </motion.div>
          )}
        </AnimatePresence>
      </div>

      {/* Create Dialog */}
      <Dialog open={showCreateDialog} onOpenChange={setShowCreateDialog}>
        <DialogContent className="max-w-2xl max-h-[90vh] overflow-auto">
          <DialogHeader>
            <DialogTitle>Neue Adresse anlegen</DialogTitle>
            <DialogDescription>Erfassen Sie die Grunddaten für eine neue Adresse</DialogDescription>
          </DialogHeader>
          <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
            <div className="flex items-center gap-4 p-3 bg-gray-50 rounded-lg">
              <Label>Typ:</Label>
              <Switch checked={watchFields.ist_firma} onCheckedChange={(c) => setValue('ist_firma', c)} />
              <span className="font-medium">{watchFields.ist_firma ? 'Firma' : 'Privatperson'}</span>
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-1.5">
                <Label>Anrede</Label>
                <Select value={watchFields.anrede || ""} onValueChange={(v) => setValue('anrede', v)}>
                  <SelectTrigger><SelectValue placeholder="Wählen..." /></SelectTrigger>
                  <SelectContent>
                    <SelectItem value="Herr">Herr</SelectItem>
                    <SelectItem value="Frau">Frau</SelectItem>
                    <SelectItem value="Firma">Firma</SelectItem>
                  </SelectContent>
                </Select>
              </div>
              <div className="space-y-1.5">
                <Label>Vorname</Label>
                <Input {...register('vorname')} />
              </div>
              <div className="col-span-2 space-y-1.5">
                <Label>Name / Firma *</Label>
                <Input {...register('name1')} />
                {errors.name1 && <p className="text-xs text-red-500">{errors.name1.message}</p>}
              </div>
            </div>
            <div className="grid grid-cols-3 gap-4">
              <div className="col-span-2 space-y-1.5">
                <Label>Straße</Label>
                <Input {...register('strasse')} />
              </div>
              <div className="space-y-1.5">
                <Label>Hausnr.</Label>
                <Input {...register('hausnummer')} />
              </div>
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
                <Label>Telefon</Label>
                <Input {...register('telefon')} />
              </div>
              <div className="space-y-1.5">
                <Label>E-Mail</Label>
                <Input type="email" {...register('email')} />
              </div>
            </div>
            <DialogFooter>
              <Button type="button" variant="outline" onClick={() => setShowCreateDialog(false)}>Abbrechen</Button>
              <Button type="submit" className="bg-emerald-500 hover:bg-emerald-600">Erstellen</Button>
            </DialogFooter>
          </form>
        </DialogContent>
      </Dialog>
    </div>
  );
}
