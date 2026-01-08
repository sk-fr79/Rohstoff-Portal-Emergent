import { useState, useMemo, useEffect } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import { Plus, MoreHorizontal, Pencil, Trash2, Eye, Building2, User, MapPin, Save, Phone, Mail, Globe, CreditCard, FileText, Users } from 'lucide-react';
import { adressenApi } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { DataTable } from '@/components/ui/data-table';
import { Switch } from '@/components/ui/switch';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuSeparator, DropdownMenuTrigger } from '@/components/ui/dropdown-menu';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Textarea } from '@/components/ui/textarea';

const adresseSchema = z.object({
  // Grunddaten
  anrede: z.string().max(20).optional(),
  vorname: z.string().max(30).optional(),
  name1: z.string().min(1, 'Name/Firma erforderlich').max(40),
  name2: z.string().max(40).optional(),
  name3: z.string().max(40).optional(),
  rechtsform: z.string().max(30).optional(),
  // Adresse
  strasse: z.string().max(45).optional(),
  hausnummer: z.string().max(10).optional(),
  plz: z.string().max(10).optional(),
  ort: z.string().max(30).optional(),
  ortzusatz: z.string().max(30).optional(),
  land: z.string().max(30).optional(),
  sprache: z.string().max(20).optional(),
  // Postfach
  plz_postfach: z.string().max(10).optional(),
  postfach: z.string().max(20).optional(),
  postfach_aktiv: z.boolean().default(false),
  // Kontakt
  telefon: z.string().max(30).optional(),
  telefax: z.string().max(30).optional(),
  email: z.string().email().optional().or(z.literal('')),
  webseite: z.string().max(100).optional(),
  // Geolocation
  latitude: z.number().optional().nullable(),
  longitude: z.number().optional().nullable(),
  wartezeit_min: z.number().optional().nullable(),
  // Status
  adresstyp: z.number().min(1).max(5).default(1),
  aktiv: z.boolean().default(true),
  wareneingang: z.boolean().default(true),
  warenausgang: z.boolean().default(true),
  barkunde: z.boolean().default(false),
  scheckdruck: z.boolean().default(false),
  ist_firma: z.boolean().default(true),
  // Sonderschalter für UST-Ausnahmen (Geschäftslogik aus Java)
  firma_ohne_ustid: z.boolean().default(false),
  privat_mit_ustid: z.boolean().default(false),
  // Betreuer
  betreuer: z.string().max(20).optional(),
  betreuer2: z.string().max(20).optional(),
  // Nummern
  kreditor_nr: z.string().max(30).optional(),
  debitor_nr: z.string().max(30).optional(),
  lief_nr: z.string().max(60).optional(),
  abn_nr: z.string().max(60).optional(),
  betriebs_nr_saa: z.string().max(30).optional(),
  sondernummer: z.string().max(30).optional(),
  // Steuer
  umsatzsteuer_lkz: z.string().max(3).optional(),
  umsatzsteuer_id: z.string().max(20).optional(),
  steuernummer: z.string().max(20).optional(),
  ust_at: z.string().max(20).optional(),
  ust_nl: z.string().max(20).optional(),
  ust_ch: z.string().max(20).optional(),
  handelsregister: z.string().max(50).optional(),
  // Zahlungs-/Lieferbedingungen
  waehrung: z.string().max(3).optional(),
  zahlungsbedingung_ek: z.string().max(100).optional(),
  zahlungsbedingung_vk: z.string().max(100).optional(),
  lieferbedingung_ek: z.string().max(50).optional(),
  lieferbedingung_vk: z.string().max(50).optional(),
  // Sperren
  rechnungen_sperren: z.boolean().default(false),
  gutschriften_sperren: z.boolean().default(false),
  wareneingang_sperren: z.boolean().default(false),
  warenausgang_sperren: z.boolean().default(false),
  wird_nicht_gemahnt: z.boolean().default(false),
  // Ausweis
  ausweis_nummer: z.string().max(30).optional(),
  ausweis_ablauf: z.string().max(10).optional(),
  geburtsdatum: z.string().max(10).optional(),
  // Bemerkungen
  bemerkungen: z.string().max(700).optional(),
  bemerkung_fahrplan: z.string().max(300).optional(),
  lieferinfo_tpa: z.string().max(300).optional(),
});

type AdresseForm = z.infer<typeof adresseSchema>;

interface Adresse extends AdresseForm {
  id: string;
  kdnr?: string;
}

const adresstypLabels: Record<number, string> = { 1: 'Kunde', 2: 'Lieferant', 3: 'Spedition', 4: 'Interessent', 5: 'Sonstige' };

export function AdressenPage() {
  const [searchTerm, setSearchTerm] = useState('');
  const [page, setPage] = useState(1);
  const [limit, setLimit] = useState(20);
  const [showCreateDialog, setShowCreateDialog] = useState(false);
  const [selectedAdresse, setSelectedAdresse] = useState<Adresse | null>(null);
  const [isEditMode, setIsEditMode] = useState(false);
  const queryClient = useQueryClient();

  const { data, isLoading } = useQuery({
    queryKey: ['adressen', { suche: searchTerm, page, limit }],
    queryFn: () => adressenApi.search({ suche: searchTerm, page, limit }),
    select: (res) => res.data,
  });

  const createMutation = useMutation({
    mutationFn: (data: AdresseForm) => adressenApi.create(data),
    onSuccess: () => {
      toast.success('Adresse erfolgreich erstellt');
      queryClient.invalidateQueries({ queryKey: ['adressen'] });
      setShowCreateDialog(false);
      reset();
    },
    onError: () => toast.error('Fehler beim Erstellen der Adresse'),
  });

  const deleteMutation = useMutation({
    mutationFn: (id: string) => adressenApi.delete(id),
    onSuccess: () => {
      toast.success('Adresse deaktiviert');
      queryClient.invalidateQueries({ queryKey: ['adressen'] });
    },
    onError: () => toast.error('Fehler beim Deaktivieren'),
  });

  const { register, handleSubmit, reset, setValue, watch, formState: { errors } } = useForm<AdresseForm>({
    resolver: zodResolver(adresseSchema),
    defaultValues: { adresstyp: 1, aktiv: true, wareneingang: true, warenausgang: true, ist_firma: true, land: 'Deutschland', sprache: 'Deutsch', waehrung: 'EUR', firma_ohne_ustid: false, privat_mit_ustid: false },
  });

  const watchFields = watch(['aktiv', 'wareneingang', 'warenausgang', 'barkunde', 'scheckdruck', 'ist_firma', 'postfach_aktiv', 'rechnungen_sperren', 'gutschriften_sperren', 'wareneingang_sperren', 'warenausgang_sperren', 'wird_nicht_gemahnt', 'firma_ohne_ustid', 'privat_mit_ustid', 'land']);

  useEffect(() => {
    if (selectedAdresse && isEditMode) {
      Object.entries(selectedAdresse).forEach(([key, value]) => {
        if (key !== 'id' && key !== 'kdnr' && value !== undefined) {
          setValue(key as keyof AdresseForm, value);
        }
      });
    }
  }, [selectedAdresse, isEditMode, setValue]);

  const handleRowDoubleClick = (adresse: Adresse) => {
    setSelectedAdresse(adresse);
    setIsEditMode(false);
  };

  const handleCloseDetail = () => {
    setSelectedAdresse(null);
    setIsEditMode(false);
    reset();
  };

  const handleSaveEdit = (data: AdresseForm) => {
    toast.success('Änderungen gespeichert (Demo)');
    setIsEditMode(false);
    queryClient.invalidateQueries({ queryKey: ['adressen'] });
  };

  const columns: ColumnDef<Adresse>[] = useMemo(() => [
    { accessorKey: 'kdnr', header: 'KDNR', size: 100, cell: ({ row }) => <span className="font-mono text-primary font-medium">{row.getValue('kdnr') || '-'}</span> },
    { accessorKey: 'name1', header: 'Firma/Name', cell: ({ row }) => (
      <div className="flex items-center gap-2">
        <Building2 className="h-4 w-4 text-muted-foreground" />
        <div>
          <span className="font-medium">{row.getValue('name1')}</span>
          {row.original.name2 && <span className="text-muted-foreground ml-1">{row.original.name2}</span>}
        </div>
      </div>
    )},
    { accessorKey: 'vorname', header: 'Vorname', cell: ({ row }) => row.getValue('vorname') || '-' },
    { id: 'adresse', header: 'Straße', cell: ({ row }) => row.original.strasse ? `${row.original.strasse} ${row.original.hausnummer || ''}`.trim() : '-' },
    { id: 'ortDisplay', header: 'Ort', cell: ({ row }) => row.original.plz || row.original.ort ? `${row.original.plz || ''} ${row.original.ort || ''}`.trim() : '-' },
    { accessorKey: 'land', header: 'Land', size: 80, cell: ({ row }) => row.getValue('land') || 'DE' },
    { accessorKey: 'telefon', header: 'Telefon', cell: ({ row }) => row.getValue('telefon') || '-' },
    { accessorKey: 'betreuer', header: 'Betreuer', cell: ({ row }) => row.getValue('betreuer') ? <span className="inline-flex items-center gap-1"><User className="h-3 w-3 text-muted-foreground" />{row.getValue('betreuer')}</span> : '-' },
    { accessorKey: 'adresstyp', header: 'Typ', size: 100, cell: ({ row }) => {
      const typ = row.getValue('adresstyp') as number;
      const colors: Record<number, string> = { 1: 'bg-blue-500/10 text-blue-500', 2: 'bg-green-500/10 text-green-500', 3: 'bg-purple-500/10 text-purple-500', 4: 'bg-yellow-500/10 text-yellow-500', 5: 'bg-gray-500/10 text-gray-500' };
      return <span className={`inline-flex px-2 py-0.5 rounded-full text-xs font-medium ${colors[typ] || colors[5]}`}>{adresstypLabels[typ] || 'Sonstige'}</span>;
    }},
    { id: 'actions', size: 60, cell: ({ row }) => (
      <DropdownMenu>
        <DropdownMenuTrigger asChild><Button variant="ghost" size="icon" className="h-8 w-8"><MoreHorizontal className="h-4 w-4" /></Button></DropdownMenuTrigger>
        <DropdownMenuContent align="end">
          <DropdownMenuItem onClick={() => handleRowDoubleClick(row.original)}><Eye className="h-4 w-4 mr-2" />Details</DropdownMenuItem>
          <DropdownMenuItem onClick={() => { setSelectedAdresse(row.original); setIsEditMode(true); }}><Pencil className="h-4 w-4 mr-2" />Bearbeiten</DropdownMenuItem>
          <DropdownMenuSeparator />
          <DropdownMenuItem className="text-destructive" onClick={() => deleteMutation.mutate(row.original.id)}><Trash2 className="h-4 w-4 mr-2" />Deaktivieren</DropdownMenuItem>
        </DropdownMenuContent>
      </DropdownMenu>
    )},
  ], [deleteMutation]);

  const renderFormContent = (readOnly: boolean = false) => (
    <Tabs defaultValue="adresse" className="w-full">
      <TabsList className="grid w-full grid-cols-4 mb-4">
        <TabsTrigger value="adresse"><MapPin className="h-4 w-4 mr-1" />Adresse</TabsTrigger>
        <TabsTrigger value="finanz"><CreditCard className="h-4 w-4 mr-1" />Finanz/Handel</TabsTrigger>
        <TabsTrigger value="kontakt"><Phone className="h-4 w-4 mr-1" />Kontakt</TabsTrigger>
        <TabsTrigger value="sonstiges"><FileText className="h-4 w-4 mr-1" />Sonstiges</TabsTrigger>
      </TabsList>

      {/* Tab 1: Adresse */}
      <TabsContent value="adresse" className="space-y-4">
        {/* Status-Bereich */}
        <div className="grid grid-cols-3 gap-3 p-3 bg-muted/50 rounded-lg">
          <div className="flex items-center space-x-2"><Switch checked={watchFields[0]} onCheckedChange={(c) => setValue('aktiv', c)} disabled={readOnly} /><Label className="text-sm">Aktiv</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[1]} onCheckedChange={(c) => setValue('wareneingang', c)} disabled={readOnly} /><Label className="text-sm">Wareneingang</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[2]} onCheckedChange={(c) => setValue('warenausgang', c)} disabled={readOnly} /><Label className="text-sm">Warenausgang</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[5]} onCheckedChange={(c) => setValue('ist_firma', c)} disabled={readOnly} /><Label className="text-sm text-blue-500">Firma</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[3]} onCheckedChange={(c) => setValue('barkunde', c)} disabled={readOnly} /><Label className="text-sm">Barkunde</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[4]} onCheckedChange={(c) => setValue('scheckdruck', c)} disabled={readOnly} /><Label className="text-sm">Scheckdruck</Label></div>
        </div>

        {/* Anrede & Namen */}
        <div className="grid grid-cols-4 gap-4">
          <div className="space-y-2"><Label>Anrede</Label><Select defaultValue={selectedAdresse?.anrede || "Firma"} onValueChange={(v) => setValue('anrede', v)} disabled={readOnly}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="Firma">Firma</SelectItem><SelectItem value="Herr">Herr</SelectItem><SelectItem value="Frau">Frau</SelectItem></SelectContent></Select></div>
          <div className="space-y-2"><Label>Vorname</Label><Input {...register('vorname')} disabled={readOnly} /></div>
          <div className="col-span-2 space-y-2"><Label>Name 1 *</Label><Input {...register('name1')} disabled={readOnly} />{errors.name1 && <p className="text-sm text-destructive">{errors.name1.message}</p>}</div>
        </div>
        <div className="grid grid-cols-3 gap-4">
          <div className="space-y-2"><Label>Name 2</Label><Input {...register('name2')} disabled={readOnly} placeholder="z.B. mbH" /></div>
          <div className="space-y-2"><Label>Name 3</Label><Input {...register('name3')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Rechtsform</Label><Select defaultValue={selectedAdresse?.rechtsform || ""} onValueChange={(v) => setValue('rechtsform', v)} disabled={readOnly}><SelectTrigger><SelectValue placeholder="Auswählen" /></SelectTrigger><SelectContent><SelectItem value="GmbH">GmbH</SelectItem><SelectItem value="AG">AG</SelectItem><SelectItem value="KG">KG</SelectItem><SelectItem value="OHG">OHG</SelectItem><SelectItem value="GbR">GbR</SelectItem><SelectItem value="e.K.">e.K.</SelectItem><SelectItem value="UG">UG</SelectItem></SelectContent></Select></div>
        </div>

        {/* Adresse */}
        <div className="grid grid-cols-4 gap-4">
          <div className="col-span-3 space-y-2"><Label>Straße</Label><Input {...register('strasse')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Hausnr.</Label><Input {...register('hausnummer')} disabled={readOnly} /></div>
        </div>
        <div className="grid grid-cols-4 gap-4">
          <div className="space-y-2"><Label>Land</Label><Select defaultValue={selectedAdresse?.land || "Deutschland"} onValueChange={(v) => setValue('land', v)} disabled={readOnly}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="Deutschland">DE (Deutschland)</SelectItem><SelectItem value="Österreich">AT (Österreich)</SelectItem><SelectItem value="Schweiz">CH (Schweiz)</SelectItem><SelectItem value="Niederlande">NL (Niederlande)</SelectItem><SelectItem value="Frankreich">FR (Frankreich)</SelectItem></SelectContent></Select></div>
          <div className="space-y-2"><Label>PLZ</Label><Input {...register('plz')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Ort</Label><Input {...register('ort')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Ortzusatz</Label><Input {...register('ortzusatz')} disabled={readOnly} /></div>
        </div>
        <div className="grid grid-cols-4 gap-4">
          <div className="space-y-2"><Label>Sprache</Label><Select defaultValue={selectedAdresse?.sprache || "Deutsch"} onValueChange={(v) => setValue('sprache', v)} disabled={readOnly}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="Deutsch">Deutsch</SelectItem><SelectItem value="Englisch">Englisch</SelectItem><SelectItem value="Französisch">Französisch</SelectItem></SelectContent></Select></div>
          <div className="space-y-2"><Label>Betreuer</Label><Input {...register('betreuer')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Betreuer 2</Label><Input {...register('betreuer2')} disabled={readOnly} /></div>
          <div className="flex items-center space-x-2 pt-6"><Switch checked={watchFields[6]} onCheckedChange={(c) => setValue('postfach_aktiv', c)} disabled={readOnly} /><Label className="text-sm">Postfach aktiv</Label></div>
        </div>

        {/* Geolocation */}
        <div className="grid grid-cols-3 gap-4 p-3 border rounded-lg">
          <div className="space-y-2"><Label>Breitengrad</Label><Input type="number" step="any" {...register('latitude', { valueAsNumber: true })} disabled={readOnly} placeholder="48,05239525" /></div>
          <div className="space-y-2"><Label>Längengrad</Label><Input type="number" step="any" {...register('longitude', { valueAsNumber: true })} disabled={readOnly} placeholder="7,73556766" /></div>
          <div className="space-y-2"><Label>Wartezeit (min)</Label><Input type="number" {...register('wartezeit_min', { valueAsNumber: true })} disabled={readOnly} /></div>
        </div>
      </TabsContent>

      {/* Tab 2: Finanz/Handel */}
      <TabsContent value="finanz" className="space-y-4">
        {/* Nummern/Codes */}
        <div className="grid grid-cols-3 gap-4 p-3 border rounded-lg">
          <div className="col-span-3"><h4 className="font-medium text-sm mb-2">Nummern/Codes</h4></div>
          <div className="space-y-2"><Label>Kreditor-Nr</Label><Input {...register('kreditor_nr')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Debitor-Nr</Label><Input {...register('debitor_nr')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>BetriebsNr. SAA</Label><Input {...register('betriebs_nr_saa')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Alt. Lief.-Nr</Label><Input {...register('lief_nr')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Alt. Abn.-Nr</Label><Input {...register('abn_nr')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Sondernummer</Label><Input {...register('sondernummer')} disabled={readOnly} /></div>
        </div>

        {/* Währung & Bedingungen */}
        <div className="grid grid-cols-2 gap-4">
          <div className="space-y-2"><Label>Währung</Label><Select defaultValue={selectedAdresse?.waehrung || "EUR"} onValueChange={(v) => setValue('waehrung', v)} disabled={readOnly}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="EUR">EUR</SelectItem><SelectItem value="CHF">CHF</SelectItem><SelectItem value="USD">USD</SelectItem></SelectContent></Select></div>
          <div className="space-y-2"><Label>Handelsregister</Label><Input {...register('handelsregister')} disabled={readOnly} /></div>
        </div>
        <div className="grid grid-cols-2 gap-4">
          <div className="space-y-2"><Label>Lieferbedingungen (EK)</Label><Select defaultValue={selectedAdresse?.lieferbedingung_ek || ""} onValueChange={(v) => setValue('lieferbedingung_ek', v)} disabled={readOnly}><SelectTrigger><SelectValue placeholder="Incoterms wählen" /></SelectTrigger><SelectContent><SelectItem value="EXW">EXW (Incoterms® 2020)</SelectItem><SelectItem value="FCA">FCA (Incoterms® 2020)</SelectItem><SelectItem value="CPT">CPT (Incoterms® 2020)</SelectItem><SelectItem value="DAP">DAP (Incoterms® 2020)</SelectItem><SelectItem value="DDP">DDP (Incoterms® 2020)</SelectItem></SelectContent></Select></div>
          <div className="space-y-2"><Label>Lieferbedingungen (VK)</Label><Select defaultValue={selectedAdresse?.lieferbedingung_vk || ""} onValueChange={(v) => setValue('lieferbedingung_vk', v)} disabled={readOnly}><SelectTrigger><SelectValue placeholder="Incoterms wählen" /></SelectTrigger><SelectContent><SelectItem value="EXW">EXW (Incoterms® 2020)</SelectItem><SelectItem value="FCA">FCA (Incoterms® 2020)</SelectItem><SelectItem value="CPT">CPT (Incoterms® 2020)</SelectItem><SelectItem value="DAP">DAP (Incoterms® 2020)</SelectItem><SelectItem value="DDP">DDP (Incoterms® 2020)</SelectItem></SelectContent></Select></div>
        </div>
        <div className="grid grid-cols-2 gap-4">
          <div className="space-y-2"><Label>Zahlungsbedingungen (EK)</Label><Input {...register('zahlungsbedingung_ek')} disabled={readOnly} placeholder="30 Tage nach Lieferung, netto" /></div>
          <div className="space-y-2"><Label>Zahlungsbedingungen (VK)</Label><Input {...register('zahlungsbedingung_vk')} disabled={readOnly} placeholder="30 Tage nach Lieferung, netto" /></div>
        </div>

        {/* Steuer */}
        <div className="grid grid-cols-3 gap-4 p-3 border rounded-lg">
          <div className="col-span-3"><h4 className="font-medium text-sm mb-2">Umsatzsteuer-IDs</h4></div>
          <div className="space-y-2"><Label>UST-LKZ (Basis)</Label><Input {...register('umsatzsteuer_lkz')} disabled={readOnly} placeholder="DE" /></div>
          <div className="space-y-2"><Label>UST-ID (Basis)</Label><Input {...register('umsatzsteuer_id')} disabled={readOnly} placeholder="142213487" /></div>
          <div className="space-y-2"><Label>Steuernummer</Label><Input {...register('steuernummer')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>UST-ID Österreich</Label><Input {...register('ust_at')} disabled={readOnly} placeholder="ATU63551917" /></div>
          <div className="space-y-2"><Label>UST-ID Niederlande</Label><Input {...register('ust_nl')} disabled={readOnly} placeholder="NL821970355B01" /></div>
          <div className="space-y-2"><Label>UST-ID Schweiz</Label><Input {...register('ust_ch')} disabled={readOnly} placeholder="CHE112.409.939" /></div>
        </div>

        {/* Sperren */}
        <div className="grid grid-cols-3 gap-3 p-3 bg-red-500/5 rounded-lg">
          <div className="col-span-3"><h4 className="font-medium text-sm mb-2 text-red-500">Sperren</h4></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[7]} onCheckedChange={(c) => setValue('rechnungen_sperren', c)} disabled={readOnly} /><Label className="text-sm">Rechnungen sperren</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[8]} onCheckedChange={(c) => setValue('gutschriften_sperren', c)} disabled={readOnly} /><Label className="text-sm">Gutschriften sperren</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[11]} onCheckedChange={(c) => setValue('wird_nicht_gemahnt', c)} disabled={readOnly} /><Label className="text-sm">Wird nicht gemahnt</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[9]} onCheckedChange={(c) => setValue('wareneingang_sperren', c)} disabled={readOnly} /><Label className="text-sm">Wareneingang sperren</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[10]} onCheckedChange={(c) => setValue('warenausgang_sperren', c)} disabled={readOnly} /><Label className="text-sm">Warenausgang sperren</Label></div>
        </div>
      </TabsContent>

      {/* Tab 3: Kontakt */}
      <TabsContent value="kontakt" className="space-y-4">
        <div className="grid grid-cols-2 gap-4">
          <div className="space-y-2"><Label>Telefon</Label><Input {...register('telefon')} disabled={readOnly} placeholder="+49 7665 98000" /></div>
          <div className="space-y-2"><Label>Telefax</Label><Input {...register('telefax')} disabled={readOnly} placeholder="+49 7665 980020" /></div>
        </div>
        <div className="grid grid-cols-2 gap-4">
          <div className="space-y-2"><Label>E-Mail</Label><Input type="email" {...register('email')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Webseite</Label><Input {...register('webseite')} disabled={readOnly} placeholder="www.firma.de" /></div>
        </div>

        {/* Postfach */}
        <div className="grid grid-cols-2 gap-4 p-3 border rounded-lg">
          <div className="col-span-2"><h4 className="font-medium text-sm mb-2">Postfach</h4></div>
          <div className="space-y-2"><Label>PLZ Postfach</Label><Input {...register('plz_postfach')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Postfach</Label><Input {...register('postfach')} disabled={readOnly} /></div>
        </div>

        {/* Ausweis (für Privatkunden) */}
        <div className="grid grid-cols-3 gap-4 p-3 border rounded-lg">
          <div className="col-span-3"><h4 className="font-medium text-sm mb-2">Ausweis (Privatkunden)</h4></div>
          <div className="space-y-2"><Label>Ausweisnummer</Label><Input {...register('ausweis_nummer')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Ablaufdatum</Label><Input type="date" {...register('ausweis_ablauf')} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Geburtsdatum</Label><Input type="date" {...register('geburtsdatum')} disabled={readOnly} /></div>
        </div>
      </TabsContent>

      {/* Tab 4: Sonstiges */}
      <TabsContent value="sonstiges" className="space-y-4">
        <div className="space-y-2"><Label>Adresstyp</Label><Select defaultValue={String(selectedAdresse?.adresstyp || 1)} onValueChange={(v) => setValue('adresstyp', Number(v))} disabled={readOnly}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="1">Kunde</SelectItem><SelectItem value="2">Lieferant</SelectItem><SelectItem value="3">Spedition</SelectItem><SelectItem value="4">Interessent</SelectItem><SelectItem value="5">Sonstige</SelectItem></SelectContent></Select></div>
        <div className="space-y-2"><Label>Bemerkungen</Label><Textarea {...register('bemerkungen')} disabled={readOnly} rows={4} /></div>
        <div className="space-y-2"><Label>Bemerkung Fahrplan</Label><Textarea {...register('bemerkung_fahrplan')} disabled={readOnly} rows={2} /></div>
        <div className="space-y-2"><Label>Lieferinfo TPA</Label><Textarea {...register('lieferinfo_tpa')} disabled={readOnly} rows={2} /></div>
      </TabsContent>
    </Tabs>
  );

  return (
    <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="space-y-6">
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold tracking-tight">Adressen-Stammdaten</h1>
          <p className="text-muted-foreground">{data?.pagination?.total || 0} Adressen · Doppelklick für Details</p>
        </div>
        <Button onClick={() => setShowCreateDialog(true)} data-testid="create-address-btn"><Plus className="h-4 w-4 mr-2" />Neue Adresse</Button>
      </div>

      <DataTable columns={columns} data={data?.data || []} isLoading={isLoading} searchPlaceholder="Suchen nach KDNR, Firma, Ort..."
        onSearchChange={(s) => { setSearchTerm(s); setPage(1); }}
        onRowDoubleClick={handleRowDoubleClick}
        pagination={data?.pagination ? { page: data.pagination.page, limit: data.pagination.limit, total: data.pagination.total, totalPages: data.pagination.total_pages } : undefined}
        onPageChange={setPage} onLimitChange={(l) => { setLimit(l); setPage(1); }} />

      {/* Create Dialog */}
      <Dialog open={showCreateDialog} onOpenChange={setShowCreateDialog}>
        <DialogContent className="sm:max-w-[800px] max-h-[90vh] overflow-y-auto">
          <DialogHeader>
            <DialogTitle>Neue Adresse erstellen</DialogTitle>
            <DialogDescription>Erfassen Sie die Stammdaten.</DialogDescription>
          </DialogHeader>
          <form onSubmit={handleSubmit((d) => createMutation.mutate(d))} className="space-y-4">
            {renderFormContent(false)}
            <DialogFooter><Button type="button" variant="outline" onClick={() => setShowCreateDialog(false)}>Abbrechen</Button><Button type="submit" disabled={createMutation.isPending}>{createMutation.isPending ? 'Speichern...' : 'Adresse erstellen'}</Button></DialogFooter>
          </form>
        </DialogContent>
      </Dialog>

      {/* Detail/Edit Dialog */}
      <Dialog open={!!selectedAdresse} onOpenChange={handleCloseDetail}>
        <DialogContent className="sm:max-w-[800px] max-h-[90vh] overflow-y-auto">
          <DialogHeader>
            <DialogTitle className="flex items-center gap-2">
              <Building2 className="h-5 w-5" />
              {selectedAdresse?.name1} {selectedAdresse?.name2}
              {isEditMode && <span className="text-sm font-normal text-muted-foreground ml-2">(Bearbeitungsmodus)</span>}
            </DialogTitle>
            <DialogDescription>KDNR: {selectedAdresse?.kdnr || '-'}</DialogDescription>
          </DialogHeader>
          {selectedAdresse && (
            <form onSubmit={handleSubmit(handleSaveEdit)} className="space-y-4">
              {renderFormContent(!isEditMode)}
              <DialogFooter>
                {!isEditMode ? (
                  <><Button type="button" variant="outline" onClick={handleCloseDetail}>Schließen</Button><Button type="button" onClick={() => setIsEditMode(true)}><Pencil className="h-4 w-4 mr-2" />Bearbeiten</Button></>
                ) : (
                  <><Button type="button" variant="outline" onClick={() => setIsEditMode(false)}>Abbrechen</Button><Button type="submit"><Save className="h-4 w-4 mr-2" />Speichern</Button></>
                )}
              </DialogFooter>
            </form>
          )}
        </DialogContent>
      </Dialog>
    </motion.div>
  );
}
