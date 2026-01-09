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
  Save, Phone, Mail, Globe, CreditCard, FileText, Users, X, Upload,
  Banknote, Shield, Clock, MessageSquare, AlertTriangle, UserCircle,
  Image as ImageIcon, Camera, Scan, Loader2, EyeOff, CheckCircle, XCircle,
  History, Search, RefreshCw, Briefcase, ClipboardList
} from 'lucide-react';
import { adressenApi, api } from '@/services/api/client';
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

// Länderflaggen Mapping
const COUNTRY_FLAGS: Record<string, string> = {
  'Deutschland': '🇩🇪',
  'Österreich': '🇦🇹',
  'Schweiz': '🇨🇭',
  'Niederlande': '🇳🇱',
  'Belgien': '🇧🇪',
  'Frankreich': '🇫🇷',
  'Italien': '🇮🇹',
  'Spanien': '🇪🇸',
  'Polen': '🇵🇱',
  'Tschechien': '🇨🇿',
  'Dänemark': '🇩🇰',
  'Schweden': '🇸🇪',
  'Finnland': '🇫🇮',
  'Griechenland': '🇬🇷',
  'Portugal': '🇵🇹',
  'Irland': '🇮🇪',
  'Luxemburg': '🇱🇺',
  'Ungarn': '🇭🇺',
  'Rumänien': '🇷🇴',
  'Bulgarien': '🇧🇬',
  'Kroatien': '🇭🇷',
  'Slowakei': '🇸🇰',
  'Slowenien': '🇸🇮',
  'Estland': '🇪🇪',
  'Lettland': '🇱🇻',
  'Litauen': '🇱🇹',
  'Malta': '🇲🇹',
  'Zypern': '🇨🇾',
  'Großbritannien': '🇬🇧',
  'Norwegen': '🇳🇴',
};

const getCountryFlag = (country: string): string => {
  return COUNTRY_FLAGS[country] || '🏳️';
};

// ========================== SCHEMA ==========================
const adresseSchema = z.object({
  anrede: z.string().max(20).nullish(),
  vorname: z.string().max(30).nullish(),
  name1: z.string().min(1, 'Name/Firma erforderlich').max(40),
  name2: z.string().max(40).nullish(),
  name3: z.string().max(40).nullish(),
  rechtsform: z.string().max(30).nullish(),
  strasse: z.string().max(45).nullish(),
  hausnummer: z.string().max(10).nullish(),
  plz: z.string().max(10).nullish(),
  ort: z.string().max(30).nullish(),
  ortzusatz: z.string().max(30).nullish(),
  land: z.string().max(30).nullish(),
  sprache: z.string().max(20).nullish(),
  plz_postfach: z.string().max(10).nullish(),
  postfach: z.string().max(20).nullish(),
  postfach_aktiv: z.boolean().nullish().default(false),
  telefon: z.string().max(30).nullish(),
  telefax: z.string().max(30).nullish(),
  email: z.string().email().nullish().or(z.literal('')),
  webseite: z.string().max(100).nullish(),
  latitude: z.number().nullish(),
  longitude: z.number().nullish(),
  wartezeit_min: z.number().nullish(),
  adresstyp: z.number().min(1).max(5).default(1),
  aktiv: z.boolean().nullish().default(true),
  wareneingang: z.boolean().nullish().default(true),
  warenausgang: z.boolean().nullish().default(true),
  barkunde: z.boolean().nullish().default(false),
  scheckdruck: z.boolean().nullish().default(false),
  ist_firma: z.boolean().nullish().default(true),
  firma_ohne_ustid: z.boolean().nullish().default(false),
  privat_mit_ustid: z.boolean().nullish().default(false),
  betreuer: z.string().max(20).nullish(),
  betreuer2: z.string().max(20).nullish(),
  kreditor_nr: z.string().max(30).nullish(),
  debitor_nr: z.string().max(30).nullish(),
  lief_nr: z.string().max(60).nullish(),
  abn_nr: z.string().max(60).nullish(),
  betriebs_nr_saa: z.string().max(30).nullish(),
  sondernummer: z.string().max(30).nullish(),
  umsatzsteuer_lkz: z.string().max(3).nullish(),
  umsatzsteuer_id: z.string().max(20).nullish(),
  steuernummer: z.string().max(20).nullish(),
  handelsregister: z.string().max(50).nullish(),
  waehrung: z.string().max(3).nullish(),
  zahlungsbedingung_ek: z.string().max(100).nullish(),
  zahlungsbedingung_vk: z.string().max(100).nullish(),
  lieferbedingung_ek: z.string().max(50).nullish(),
  lieferbedingung_vk: z.string().max(50).nullish(),
  rechnungen_sperren: z.boolean().nullish().default(false),
  gutschriften_sperren: z.boolean().nullish().default(false),
  wareneingang_sperren: z.boolean().nullish().default(false),
  warenausgang_sperren: z.boolean().nullish().default(false),
  wird_nicht_gemahnt: z.boolean().nullish().default(false),
  ausweis_nummer: z.string().max(30).nullish(),
  ausweis_ablauf: z.string().max(10).nullish(),
  geburtsdatum: z.string().max(10).nullish(),
  bemerkungen: z.string().max(700).nullish(),
  bemerkung_fahrplan: z.string().max(300).nullish(),
  lieferinfo_tpa: z.string().max(300).nullish(),
});

type AdresseForm = z.infer<typeof adresseSchema>;

interface UstId {
  id: string;
  land: string;
  lkz: string;
  ustid: string;
}

interface Ansprechpartner {
  id: string;
  vorname: string;
  nachname: string;
  funktion?: string;
  sprache?: string;
  strasse?: string;
  plz?: string;
  ort?: string;
  telefon?: string;
  mobil?: string;
  email?: string;
  profilbild?: string;
  visitenkarte?: string;
}

interface Adresse extends AdresseForm {
  id: string;
  kdnr: string;
  firmenlogo?: string;
  weitere_ustids?: UstId[];
  ansprechpartner?: Ansprechpartner[];
  erstellt_am?: string;
  letzte_aenderung?: string;
}

// Interface für UST-ID Validierungsprotokoll
interface UstIdProtokoll {
  id: string;
  adresse_id: string;
  laenderkennzeichen: string;
  ustid: string;
  gueltig: boolean;
  firmenname?: string;
  adresse?: string;
  strasse?: string;
  plz?: string;
  ort?: string;
  abfrage_datum: string;
  request_identifier?: string;
  fehler_code?: string;
  abgefragt_von?: string;
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

// EU-Länder für UST-ID Dropdown
const EU_LAENDER = [
  { land: 'Deutschland', lkz: 'DE' },
  { land: 'Österreich', lkz: 'AT' },
  { land: 'Niederlande', lkz: 'NL' },
  { land: 'Schweiz', lkz: 'CHE' },
  { land: 'Belgien', lkz: 'BE' },
  { land: 'Frankreich', lkz: 'FR' },
  { land: 'Italien', lkz: 'IT' },
  { land: 'Spanien', lkz: 'ES' },
  { land: 'Polen', lkz: 'PL' },
  { land: 'Tschechien', lkz: 'CZ' },
  { land: 'Dänemark', lkz: 'DK' },
  { land: 'Schweden', lkz: 'SE' },
  { land: 'Ungarn', lkz: 'HU' },
  { land: 'Rumänien', lkz: 'RO' },
  { land: 'Slowakei', lkz: 'SK' },
  { land: 'Großbritannien', lkz: 'GB' },
];

// ========================== COMPONENT ==========================
export function AdressenPage() {
  const queryClient = useQueryClient();
  const [selectedAdresse, setSelectedAdresse] = useState<Adresse | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [isNewRecord, setIsNewRecord] = useState(false);
  const [activeSection, setActiveSection] = useState('stamm');
  const [showInactive, setShowInactive] = useState(false); // Filter für inaktive Adressen
  
  // States für neue Features
  const [weitereUstIds, setWeitereUstIds] = useState<UstId[]>([]);
  const [ansprechpartnerList, setAnsprechpartnerList] = useState<Ansprechpartner[]>([]);
  const [showApDialog, setShowApDialog] = useState(false);
  const [editingAp, setEditingAp] = useState<Ansprechpartner | null>(null);
  
  // States für UST-ID Validierung
  const [showUstIdProtokoll, setShowUstIdProtokoll] = useState(false);
  const [ustIdProtokoll, setUstIdProtokoll] = useState<UstIdProtokoll[]>([]);
  const [ustIdProtokollLoading, setUstIdProtokollLoading] = useState(false);
  const [validatingUstId, setValidatingUstId] = useState(false);
  const [validatingUstIdIndex, setValidatingUstIdIndex] = useState<number | null>(null); // für weitere UST-IDs

  const { register, handleSubmit, reset, setValue, watch, formState: { errors } } = useForm<AdresseForm>({
    resolver: zodResolver(adresseSchema),
    defaultValues: { adresstyp: 1, aktiv: true, wareneingang: true, warenausgang: true, ist_firma: true, land: 'Deutschland', sprache: 'Deutsch', waehrung: 'EUR', firma_ohne_ustid: false, privat_mit_ustid: false },
  });

  const watchFields = watch();
  
  // Helper: Firma-Status zuverlässig ermitteln (watchFields hat Vorrang vor selectedAdresse)
  const istFirma = istFirma !== undefined ? istFirma : (selectedAdresse?.ist_firma ?? true);

  // Queries & Mutations
  const { data: adressenData, isLoading } = useQuery({
    queryKey: ['adressen'],
    queryFn: () => adressenApi.getAll(),
  });

  const createMutation = useMutation({
    mutationFn: (data: AdresseForm) => adressenApi.create(data),
    onSuccess: (response) => {
      queryClient.invalidateQueries({ queryKey: ['adressen'] });
      toast.success('Adresse erfolgreich erstellt');
      // Nach erfolgreicher Erstellung: Sidebar bleibt offen mit neuem Datensatz
      if (response.data?.data) {
        setSelectedAdresse(response.data.data);
        Object.entries(response.data.data).forEach(([key, value]) => {
          if (key in adresseSchema.shape) {
            setValue(key as keyof AdresseForm, value as any);
          }
        });
      }
      setIsNewRecord(false);
      setIsEditing(false);
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
    mutationFn: ({ id, data }: { id: string; data: any }) => adressenApi.update(id, data),
    onSuccess: (response) => {
      queryClient.invalidateQueries({ queryKey: ['adressen'] });
      if (response.data?.data) {
        setSelectedAdresse(response.data.data);
      }
      setIsEditing(false);
      toast.success('Adresse erfolgreich aktualisiert');
    },
    onError: (error: any) => {
      console.error('Update error:', error);
      const detail = error.response?.data?.detail;
      if (detail?.fehler) {
        detail.fehler.forEach((f: string) => toast.error(f));
      } else if (typeof detail === 'string') {
        toast.error(detail);
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
      setWeitereUstIds(selectedAdresse.weitere_ustids || []);
      setAnsprechpartnerList(selectedAdresse.ansprechpartner || []);
    }
  }, [selectedAdresse, setValue]);

  // FIXED: onSubmit function
  const onSubmit = async (data: AdresseForm) => {
    const fullData = {
      ...data,
      weitere_ustids: weitereUstIds,
    };
    
    if (isNewRecord) {
      createMutation.mutate(fullData as any);
    } else if (selectedAdresse) {
      updateMutation.mutate({ id: selectedAdresse.id, data: fullData });
    }
  };

  // Manual save handler for the button - includes error logging
  const handleSave = () => {
    handleSubmit(
      onSubmit,
      (validationErrors) => {
        console.error('Validation errors:', validationErrors);
        Object.values(validationErrors).forEach((err: any) => {
          if (err?.message) {
            toast.error(err.message);
          }
        });
      }
    )();
  };

  // Neue Adresse anlegen - öffnet Sidebar mit leerem Datensatz
  const handleNewAdresse = () => {
    const emptyAdresse: Partial<Adresse> & { id: string; name1: string } = {
      id: 'NEU',
      kdnr: '(wird automatisch vergeben)',
      name1: '',
      adresstyp: 1,
      aktiv: true,
      wareneingang: true,
      warenausgang: true,
      ist_firma: true,
      land: 'Deutschland',
      sprache: 'Deutsch',
      waehrung: 'EUR',
    };
    setSelectedAdresse(emptyAdresse as Adresse);
    reset({
      adresstyp: 1,
      aktiv: true,
      wareneingang: true,
      warenausgang: true,
      ist_firma: true,
      land: 'Deutschland',
      sprache: 'Deutsch',
      waehrung: 'EUR',
      firma_ohne_ustid: false,
      privat_mit_ustid: false,
    });
    setWeitereUstIds([]);
    setAnsprechpartnerList([]);
    setIsNewRecord(true);
    setIsEditing(true);
    setActiveSection('stamm');
  };

  // Sidebar schließen
  const handleClose = () => {
    setSelectedAdresse(null);
    setIsEditing(false);
    setIsNewRecord(false);
    reset();
  };

  // Abbrechen
  const handleCancel = () => {
    if (isNewRecord) {
      setSelectedAdresse(null);
      setIsNewRecord(false);
      reset();
    } else {
      setIsEditing(false);
      if (selectedAdresse) {
        Object.entries(selectedAdresse).forEach(([key, value]) => {
          if (key in adresseSchema.shape) {
            setValue(key as keyof AdresseForm, value as any);
          }
        });
      }
    }
  };

  const handleRowDoubleClick = (adresse: Adresse) => {
    setSelectedAdresse(adresse);
    setIsNewRecord(false);
    setIsEditing(false);
    setActiveSection('stamm');
  };

  // Logo Upload Handler
  const handleLogoUpload = async (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (!file || !selectedAdresse) return;
    
    const formData = new FormData();
    formData.append('file', file);
    
    try {
      const response = await api.post(`/upload/logo/${selectedAdresse.id}`, formData, {
        headers: { 'Content-Type': 'multipart/form-data' }
      });
      if (response.data.success) {
        setSelectedAdresse({ ...selectedAdresse, firmenlogo: response.data.data.firmenlogo });
        queryClient.invalidateQueries({ queryKey: ['adressen'] });
        toast.success('Logo hochgeladen');
      }
    } catch (error) {
      toast.error('Fehler beim Hochladen');
    }
  };

  // UST-ID Handlers
  const addUstId = () => {
    setWeitereUstIds([...weitereUstIds, { id: crypto.randomUUID(), land: '', lkz: '', ustid: '' }]);
  };

  const updateUstId = (id: string, field: keyof UstId, value: string) => {
    setWeitereUstIds(weitereUstIds.map(u => {
      if (u.id === id) {
        if (field === 'land') {
          const selected = EU_LAENDER.find(l => l.land === value);
          return { ...u, land: value, lkz: selected?.lkz || '' };
        }
        return { ...u, [field]: value };
      }
      return u;
    }));
  };

  const removeUstId = (id: string) => {
    setWeitereUstIds(weitereUstIds.filter(u => u.id !== id));
  };

  // UST-ID VIES Validierung
  const validateUstId = async (lkz: string, ustid: string, index?: number) => {
    if (!selectedAdresse || !lkz || !ustid) {
      toast.error('Bitte Länderkennzeichen und UST-ID eingeben');
      return;
    }

    if (index !== undefined) {
      setValidatingUstIdIndex(index);
    } else {
      setValidatingUstId(true);
    }

    try {
      const response = await api.post('/ustid/validate', {
        adresse_id: selectedAdresse.id,
        laenderkennzeichen: lkz,
        ustid: ustid
      });

      if (response.data.success) {
        const result = response.data.data;
        if (result.gueltig) {
          toast.success('✓ UST-ID ist gültig', {
            description: result.firmenname || undefined
          });
        } else {
          toast.warning('UST-ID ist ungültig');
        }
      } else {
        toast.error(response.data.error || 'Validierung fehlgeschlagen');
      }
    } catch (error: any) {
      toast.error(error.response?.data?.detail || 'Fehler bei der Validierung');
    } finally {
      setValidatingUstId(false);
      setValidatingUstIdIndex(null);
    }
  };

  // UST-ID Protokoll laden
  const loadUstIdProtokoll = async () => {
    if (!selectedAdresse) return;

    setUstIdProtokollLoading(true);
    try {
      const response = await api.get(`/ustid/protokoll/${selectedAdresse.id}`);
      if (response.data.success) {
        setUstIdProtokoll(response.data.data);
      }
    } catch (error) {
      toast.error('Fehler beim Laden des Protokolls');
    } finally {
      setUstIdProtokollLoading(false);
    }
  };

  // Protokoll öffnen
  const openUstIdProtokoll = () => {
    loadUstIdProtokoll();
    setShowUstIdProtokoll(true);
  };

  // Ansprechpartner Handlers
  const saveAnsprechpartner = async (ap: Ansprechpartner) => {
    if (!selectedAdresse) return;
    
    try {
      if (editingAp?.id) {
        await api.put(`/adressen/${selectedAdresse.id}/ansprechpartner/${ap.id}`, ap);
        setAnsprechpartnerList(ansprechpartnerList.map(a => a.id === ap.id ? ap : a));
      } else {
        const response = await api.post(`/adressen/${selectedAdresse.id}/ansprechpartner`, ap);
        setAnsprechpartnerList([...ansprechpartnerList, response.data.data]);
      }
      setShowApDialog(false);
      setEditingAp(null);
      queryClient.invalidateQueries({ queryKey: ['adressen'] });
      toast.success('Ansprechpartner gespeichert');
    } catch (error) {
      toast.error('Fehler beim Speichern');
    }
  };

  const deleteAnsprechpartner = async (apId: string) => {
    if (!selectedAdresse) return;
    
    try {
      await api.delete(`/adressen/${selectedAdresse.id}/ansprechpartner/${apId}`);
      setAnsprechpartnerList(ansprechpartnerList.filter(a => a.id !== apId));
      toast.success('Ansprechpartner gelöscht');
    } catch (error) {
      toast.error('Fehler beim Löschen');
    }
  };

  // Table Columns
  const columns: ColumnDef<Adresse>[] = useMemo(() => [
    { accessorKey: 'kdnr', header: 'Kd.Nr', size: 100 },
    { 
      accessorKey: 'name1', 
      header: 'Name/Firma', 
      size: 200,
      cell: ({ row }) => (
        <div className="flex items-center gap-2">
          {row.original.firmenlogo ? (
            <img src={row.original.firmenlogo} alt="" className="h-6 w-6 rounded object-contain" />
          ) : null}
          <span>{row.original.name1}</span>
        </div>
      ),
    },
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

  // Gefilterte Adressen (inaktive ausblenden wenn nicht aktiviert)
  const allAdressen = adressenData?.data?.data || adressenData?.data || [];
  const adressen = showInactive ? allAdressen : allAdressen.filter((a: Adresse) => a.aktiv !== false);
  const inactiveCount = allAdressen.filter((a: Adresse) => a.aktiv === false).length;

  // ========================== RENDER ==========================
  return (
    <div className="h-full flex flex-col">
      {/* Header */}
      <div className="bg-white border-b border-gray-200 px-6 py-4">
        <div className="flex items-center justify-between">
          <div>
            <h1 className="text-xl font-semibold text-gray-900">Adressen</h1>
            <p className="text-sm text-gray-500 mt-0.5">{adressen.length} Einträge {!showInactive && inactiveCount > 0 && `(${inactiveCount} inaktive ausgeblendet)`}</p>
          </div>
          <div className="flex items-center gap-4">
            {/* Inaktive Toggle */}
            <div className="flex items-center gap-2 px-3 py-1.5 bg-gray-100 rounded-lg">
              <EyeOff className="h-4 w-4 text-gray-500" />
              <span className="text-sm text-gray-600">Inaktive</span>
              <Switch 
                checked={showInactive} 
                onCheckedChange={setShowInactive}
                className="data-[state=checked]:bg-emerald-500"
              />
            </div>
            <Button onClick={handleNewAdresse} className="bg-emerald-500 hover:bg-emerald-600" data-testid="new-adresse-btn">
              <Plus className="h-4 w-4 mr-2" />Neue Adresse
            </Button>
          </div>
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

        {/* Detail Panel */}
        <AnimatePresence>
          {selectedAdresse && (
            <motion.div
              initial={{ x: '100%', opacity: 0 }}
              animate={{ x: 0, opacity: 1 }}
              exit={{ x: '100%', opacity: 0 }}
              transition={{ type: 'spring', damping: 25, stiffness: 200 }}
              className="w-full lg:w-1/2 border-l border-gray-200 bg-white flex flex-col overflow-hidden"
            >
              {/* Detail Header - mit transparentem Logo */}
              <div className="bg-white border-b border-gray-200 px-4 py-3 flex items-center justify-between">
                <div className="flex items-center gap-3">
                  {/* Firmenlogo / Avatar - transparent embedded */}
                  <div className="relative group">
                    {selectedAdresse.firmenlogo ? (
                      <div className="h-12 w-12 flex items-center justify-center">
                        <img 
                          src={selectedAdresse.firmenlogo} 
                          alt="Logo" 
                          className="max-h-12 max-w-12 object-contain"
                        />
                      </div>
                    ) : (
                      <div className={cn(
                        "h-12 w-12 rounded-lg flex items-center justify-center",
                        istFirma ? "bg-blue-50" : "bg-purple-50"
                      )}>
                        {istFirma ? 
                          <Building2 className="h-6 w-6 text-blue-400" /> : 
                          <User className="h-6 w-6 text-purple-400" />
                        }
                      </div>
                    )}
                    {isEditing && istFirma && (
                      <label className="absolute inset-0 flex items-center justify-center bg-black/50 rounded-lg opacity-0 group-hover:opacity-100 cursor-pointer transition-opacity">
                        <Camera className="h-5 w-5 text-white" />
                        <input type="file" accept="image/*" className="hidden" onChange={handleLogoUpload} />
                      </label>
                    )}
                  </div>
                  <div>
                    <h2 className="font-semibold text-gray-900">
                      {isNewRecord ? 'Neue Adresse' : selectedAdresse.name1}
                    </h2>
                    <p className="text-sm text-gray-500">
                      {isNewRecord ? '(wird automatisch vergeben)' : selectedAdresse.kdnr}
                    </p>
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
                        disabled={createMutation.isPending || updateMutation.isPending}
                        className="bg-emerald-500 hover:bg-emerald-600"
                      >
                        {(createMutation.isPending || updateMutation.isPending) ? (
                          <Loader2 className="h-4 w-4 mr-1 animate-spin" />
                        ) : (
                          <Save className="h-4 w-4 mr-1" />
                        )}
                        Speichern
                      </Button>
                    </>
                  ) : (
                    <Button variant="outline" size="sm" onClick={() => setIsEditing(true)}>
                      <Pencil className="h-4 w-4 mr-1" />Bearbeiten
                    </Button>
                  )}
                  <Button variant="ghost" size="icon" onClick={handleClose}>
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
                  {/* Stammdaten Section */}
                  {activeSection === 'stamm' && (() => {
                    // Helper: Firma-Status sicher ermitteln
                    const istFirma = istFirma !== undefined 
                      ? istFirma 
                      : (selectedAdresse?.ist_firma ?? true);
                    
                    return (
                    <div className="space-y-6">
                      {/* Typ-Auswahl mit visuellem Feedback */}
                      <div className="relative">
                        <div className={cn(
                          "absolute inset-0 rounded-xl transition-all duration-300",
                          istFirma 
                            ? "bg-gradient-to-r from-blue-50 to-indigo-50" 
                            : "bg-gradient-to-r from-purple-50 to-pink-50"
                        )} />
                        <div className="relative p-4 flex items-center justify-between">
                          <div className="flex items-center gap-4">
                            <div className={cn(
                              "h-12 w-12 rounded-xl flex items-center justify-center transition-all duration-300",
                              istFirma ? "bg-blue-100" : "bg-purple-100"
                            )}>
                              {istFirma ? (
                                <Building2 className="h-6 w-6 text-blue-600" />
                              ) : (
                                <User className="h-6 w-6 text-purple-600" />
                              )}
                            </div>
                            <div>
                              <span className={cn(
                                "font-semibold text-lg",
                                istFirma ? "text-blue-700" : "text-purple-700"
                              )}>
                                {istFirma ? 'Geschäftskunde' : 'Privatperson'}
                              </span>
                              <p className="text-sm text-gray-500">
                                {istFirma 
                                  ? 'Firmenkonto mit UST-ID und Handelsregister' 
                                  : 'Privatkonto mit persönlichen Daten'}
                              </p>
                            </div>
                          </div>
                          {isEditing && (
                            <div className="flex items-center gap-3 bg-white/80 backdrop-blur-sm px-4 py-2 rounded-lg shadow-sm">
                              <span className={cn("text-sm font-medium", istFirma === false && "text-purple-600")}>Privat</span>
                              <Switch 
                                checked={Boolean(istFirma)} 
                                onCheckedChange={(checked) => {
                                  setValue('ist_firma', checked);
                                }}
                              />
                              <span className={cn("text-sm font-medium", istFirma === true && "text-blue-600")}>Firma</span>
                            </div>
                          )}
                        </div>
                      </div>

                      {/* Firmenlogo Upload - nur für Firmen */}
                      {istFirma && isEditing && (
                        <div className="flex items-center gap-4 p-4 bg-gray-50 rounded-lg border border-gray-100">
                          {selectedAdresse?.firmenlogo ? (
                            <img 
                              src={selectedAdresse.firmenlogo} 
                              alt="Logo" 
                              className="h-16 max-w-32 object-contain rounded-lg"
                            />
                          ) : (
                            <div className="h-16 w-16 rounded-lg bg-white border-2 border-dashed border-gray-300 flex items-center justify-center">
                              <Building2 className="h-8 w-8 text-gray-300" />
                            </div>
                          )}
                          <label className="flex-1 flex items-center justify-center gap-2 px-4 py-3 border-2 border-dashed border-gray-300 rounded-lg cursor-pointer hover:border-emerald-400 hover:bg-emerald-50/50 transition-colors">
                            <Upload className="h-5 w-5 text-gray-400" />
                            <span className="text-sm text-gray-600">Logo hochladen (PNG, JPG)</span>
                            <input type="file" accept="image/*" className="hidden" onChange={handleLogoUpload} />
                          </label>
                        </div>
                      )}

                      {/* Grundinformationen - Smart Layout */}
                      <div className="bg-white rounded-xl border border-gray-100 p-5 space-y-5">
                        <h3 className="text-sm font-semibold text-gray-900 flex items-center gap-2">
                          {istFirma ? (
                            <Building2 className="h-4 w-4 text-blue-500" />
                          ) : (
                            <User className="h-4 w-4 text-purple-500" />
                          )}
                          {istFirma ? 'Firmendaten' : 'Persönliche Daten'}
                        </h3>
                        
                        <div className="grid grid-cols-6 gap-4">
                          {/* Anrede - immer sichtbar */}
                          <div className="col-span-2 space-y-1.5">
                            <Label className="text-sm text-gray-600">Anrede</Label>
                            <Select 
                              value={watchFields.anrede || ""} 
                              onValueChange={(v) => setValue('anrede', v)} 
                              disabled={!isEditing}
                            >
                              <SelectTrigger className="bg-white">
                                <SelectValue placeholder="Wählen..." />
                              </SelectTrigger>
                              <SelectContent>
                                {istFirma ? (
                                  <SelectItem value="Firma">Firma</SelectItem>
                                ) : (
                                  <>
                                    <SelectItem value="Herr">Herr</SelectItem>
                                    <SelectItem value="Frau">Frau</SelectItem>
                                    <SelectItem value="Divers">Divers</SelectItem>
                                  </>
                                )}
                              </SelectContent>
                            </Select>
                          </div>

                          {/* Firma - nur bei Geschäftskunden */}
                          {istFirma && (
                            <div className="col-span-4 space-y-1.5">
                              <Label className="text-sm text-gray-600">Firmenname *</Label>
                              <Input 
                                {...register('name1')} 
                                disabled={!isEditing} 
                                className="bg-white font-medium"
                                placeholder="z.B. Mustermann GmbH"
                              />
                              {errors.name1 && <p className="text-xs text-red-500">{errors.name1.message}</p>}
                            </div>
                          )}

                          {/* Vorname & Nachname - nur bei Privatpersonen */}
                          {!istFirma && (
                            <>
                              <div className="col-span-2 space-y-1.5">
                                <Label className="text-sm text-gray-600">Vorname</Label>
                                <Input 
                                  {...register('vorname')} 
                                  disabled={!isEditing} 
                                  className="bg-white"
                                  placeholder="Max"
                                />
                              </div>
                              <div className="col-span-2 space-y-1.5">
                                <Label className="text-sm text-gray-600">Nachname *</Label>
                                <Input 
                                  {...register('name1')} 
                                  disabled={!isEditing} 
                                  className="bg-white font-medium"
                                  placeholder="Mustermann"
                                />
                                {errors.name1 && <p className="text-xs text-red-500">{errors.name1.message}</p>}
                              </div>
                            </>
                          )}

                          {/* Adresszusatz / Name 2 - immer sichtbar */}
                          <div className={cn("space-y-1.5", istFirma ? "col-span-3" : "col-span-6")}>
                            <Label className="text-sm text-gray-600">
                              {istFirma ? 'Zusatz / Abteilung' : 'Adresszusatz'}
                            </Label>
                            <Input 
                              {...register('name2')} 
                              disabled={!isEditing} 
                              className="bg-white"
                              placeholder={istFirma ? "z.B. Einkaufsabteilung" : "z.B. c/o Familie Schmidt"}
                            />
                          </div>

                          {/* Rechtsform - nur bei Firmen */}
                          {istFirma && (
                            <div className="col-span-3 space-y-1.5">
                              <Label className="text-sm text-gray-600">Rechtsform</Label>
                              <Select 
                                value={watchFields.rechtsform || ""} 
                                onValueChange={(v) => setValue('rechtsform', v)} 
                                disabled={!isEditing}
                              >
                                <SelectTrigger className="bg-white">
                                  <SelectValue placeholder="Wählen..." />
                                </SelectTrigger>
                                <SelectContent>
                                  <SelectItem value="GmbH">GmbH</SelectItem>
                                  <SelectItem value="AG">AG</SelectItem>
                                  <SelectItem value="KG">KG</SelectItem>
                                  <SelectItem value="OHG">OHG</SelectItem>
                                  <SelectItem value="GmbH & Co. KG">GmbH & Co. KG</SelectItem>
                                  <SelectItem value="UG">UG (haftungsbeschränkt)</SelectItem>
                                  <SelectItem value="e.K.">e.K.</SelectItem>
                                  <SelectItem value="GbR">GbR</SelectItem>
                                  <SelectItem value="Einzelunternehmen">Einzelunternehmen</SelectItem>
                                  <SelectItem value="e.V.">e.V.</SelectItem>
                                </SelectContent>
                              </Select>
                            </div>
                          )}
                        </div>
                      </div>

                      {/* Adresse mit Flagge */}
                      <div className="bg-white rounded-xl border border-gray-100 p-5 space-y-4">
                        <h3 className="text-sm font-semibold text-gray-900 flex items-center gap-2">
                          <MapPin className="h-4 w-4 text-emerald-500" />
                          Adresse
                        </h3>
                        <div className="grid grid-cols-6 gap-4">
                          <div className="col-span-4 space-y-1.5">
                            <Label className="text-sm text-gray-600">Straße</Label>
                            <Input {...register('strasse')} disabled={!isEditing} className="bg-white" placeholder="Musterstraße" />
                          </div>
                          <div className="col-span-2 space-y-1.5">
                            <Label className="text-sm text-gray-600">Hausnr.</Label>
                            <Input {...register('hausnummer')} disabled={!isEditing} className="bg-white" placeholder="123" />
                          </div>
                          <div className="col-span-2 space-y-1.5">
                            <Label className="text-sm text-gray-600">PLZ</Label>
                            <Input {...register('plz')} disabled={!isEditing} className="bg-white" placeholder="12345" />
                          </div>
                          <div className="col-span-4 space-y-1.5">
                            <Label className="text-sm text-gray-600">Ort</Label>
                            <Input {...register('ort')} disabled={!isEditing} className="bg-white" placeholder="Musterstadt" />
                          </div>
                          <div className="col-span-6 space-y-1.5">
                            <Label className="text-sm text-gray-600">Land</Label>
                            <Select 
                              value={watchFields.land || "Deutschland"} 
                              onValueChange={(v) => setValue('land', v)} 
                              disabled={!isEditing}
                            >
                              <SelectTrigger className="bg-white">
                                <div className="flex items-center gap-2">
                                  {watchFields.land && (
                                    <span className="text-lg">{getCountryFlag(watchFields.land)}</span>
                                  )}
                                  <SelectValue />
                                </div>
                              </SelectTrigger>
                              <SelectContent>
                                {EU_LAENDER.map(l => (
                                  <SelectItem key={l.land} value={l.land}>
                                    <div className="flex items-center gap-2">
                                      <span className="text-lg">{getCountryFlag(l.land)}</span>
                                      <span>{l.land}</span>
                                    </div>
                                  </SelectItem>
                                ))}
                              </SelectContent>
                            </Select>
                          </div>
                        </div>
                      </div>

                      {/* Zuständigkeit */}
                      <div className="bg-white rounded-xl border border-gray-100 p-5 space-y-4">
                        <h3 className="text-sm font-semibold text-gray-900 flex items-center gap-2">
                          <Users className="h-4 w-4 text-emerald-500" />
                          Zuständigkeit
                        </h3>
                        <div className="grid grid-cols-2 gap-4">
                          <div className="space-y-1.5">
                            <Label className="text-sm text-gray-600 flex items-center gap-1.5">
                              <Briefcase className="h-3.5 w-3.5" />
                              Händler
                            </Label>
                            <Input 
                              {...register('betreuer')} 
                              disabled={!isEditing} 
                              className="bg-white"
                              placeholder="Verantwortlicher Händler"
                            />
                          </div>
                          <div className="space-y-1.5">
                            <Label className="text-sm text-gray-600 flex items-center gap-1.5">
                              <ClipboardList className="h-3.5 w-3.5" />
                              Sachbearbeiter
                            </Label>
                            <Input 
                              {...register('betreuer2')} 
                              disabled={!isEditing} 
                              className="bg-white"
                              placeholder="Zuständiger Sachbearbeiter"
                            />
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

                      {/* Ansprechpartner */}
                      <div>
                        <div className="flex items-center justify-between mb-4">
                          <h3 className="text-sm font-semibold text-gray-900 flex items-center gap-2">
                            <Users className="h-4 w-4 text-emerald-500" />
                            Ansprechpartner
                          </h3>
                          {isEditing && (
                            <Button 
                              type="button" 
                              size="sm" 
                              variant="outline"
                              onClick={() => { setEditingAp(null); setShowApDialog(true); }}
                            >
                              <Plus className="h-4 w-4 mr-1" />Hinzufügen
                            </Button>
                          )}
                        </div>
                        
                        <div className="space-y-3">
                          {ansprechpartnerList.length === 0 ? (
                            <p className="text-sm text-gray-500 text-center py-4 bg-gray-50 rounded-lg">
                              Keine Ansprechpartner vorhanden
                            </p>
                          ) : (
                            ansprechpartnerList.map((ap) => (
                              <div 
                                key={ap.id} 
                                className="flex items-center gap-4 p-4 bg-gray-50 rounded-lg hover:bg-gray-100 transition-colors"
                              >
                                {ap.profilbild ? (
                                  <img 
                                    src={ap.profilbild} 
                                    alt={`${ap.vorname} ${ap.nachname}`}
                                    className="h-12 w-12 rounded-full object-cover border-2 border-white shadow"
                                  />
                                ) : (
                                  <div className="h-12 w-12 rounded-full bg-gradient-to-br from-emerald-400 to-emerald-600 flex items-center justify-center shadow">
                                    <UserCircle className="h-7 w-7 text-white" />
                                  </div>
                                )}
                                
                                <div className="flex-1 min-w-0">
                                  <p className="font-medium text-gray-900">{ap.vorname} {ap.nachname}</p>
                                  {ap.funktion && <p className="text-sm text-gray-500">{ap.funktion}</p>}
                                  <div className="flex gap-4 mt-1 text-xs text-gray-500">
                                    {ap.telefon && <span className="flex items-center gap-1"><Phone className="h-3 w-3" />{ap.telefon}</span>}
                                    {ap.email && <span className="flex items-center gap-1"><Mail className="h-3 w-3" />{ap.email}</span>}
                                  </div>
                                </div>
                                
                                {isEditing && (
                                  <div className="flex gap-1">
                                    <Button 
                                      type="button" 
                                      variant="ghost" 
                                      size="icon"
                                      onClick={() => { setEditingAp(ap); setShowApDialog(true); }}
                                    >
                                      <Pencil className="h-4 w-4" />
                                    </Button>
                                    <Button 
                                      type="button" 
                                      variant="ghost" 
                                      size="icon"
                                      className="text-red-500 hover:text-red-700"
                                      onClick={() => deleteAnsprechpartner(ap.id)}
                                    >
                                      <Trash2 className="h-4 w-4" />
                                    </Button>
                                  </div>
                                )}
                              </div>
                            ))
                          )}
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
                        </div>
                      </div>
                    </div>
                  )}

                  {/* Steuer Section */}
                  {activeSection === 'steuer' && (
                    <div className="space-y-6">
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
                                disabled={!isEditing || !istFirma || watchFields.land !== 'Deutschland'} 
                              />
                              <Label className="text-sm text-gray-700">Firma ohne UST-ID</Label>
                            </div>
                          </div>
                          <div className="space-y-2">
                            <div className="flex items-center gap-2">
                              <Switch 
                                checked={watchFields.privat_mit_ustid} 
                                onCheckedChange={(c) => setValue('privat_mit_ustid', c)} 
                                disabled={!isEditing || istFirma || watchFields.land !== 'Deutschland'} 
                              />
                              <Label className="text-sm text-gray-700">Privat mit UST-ID</Label>
                            </div>
                          </div>
                        </div>
                      </div>

                      <div>
                        <h3 className="text-sm font-semibold text-gray-900 mb-4 flex items-center gap-2">
                          <FileText className="h-4 w-4 text-emerald-500" />
                          Basis UST-ID
                          <TooltipProvider>
                            <Tooltip>
                              <TooltipTrigger asChild>
                                <Button 
                                  type="button" 
                                  variant="ghost" 
                                  size="icon" 
                                  className="h-6 w-6 ml-auto"
                                  onClick={openUstIdProtokoll}
                                >
                                  <History className="h-4 w-4 text-gray-500" />
                                </Button>
                              </TooltipTrigger>
                              <TooltipContent>
                                <p>Validierungsprotokoll anzeigen</p>
                              </TooltipContent>
                            </Tooltip>
                          </TooltipProvider>
                        </h3>
                        <div className="grid grid-cols-4 gap-4">
                          <div className="space-y-1.5">
                            <Label className="text-sm text-gray-600">UST-LKZ</Label>
                            <Input {...register('umsatzsteuer_lkz')} disabled={!isEditing} className="bg-white" placeholder="DE" maxLength={3} />
                          </div>
                          <div className="col-span-2 space-y-1.5">
                            <Label className="text-sm text-gray-600">UST-ID</Label>
                            <Input {...register('umsatzsteuer_id')} disabled={!isEditing} className="bg-white" placeholder="123456789" />
                          </div>
                          <div className="space-y-1.5">
                            <Label className="text-sm text-gray-600">&nbsp;</Label>
                            <div className="flex gap-1">
                              <TooltipProvider>
                                <Tooltip>
                                  <TooltipTrigger asChild>
                                    <Button 
                                      type="button" 
                                      variant="outline" 
                                      size="sm"
                                      className="flex-1"
                                      onClick={() => validateUstId(watchFields.umsatzsteuer_lkz || '', watchFields.umsatzsteuer_id || '')}
                                      disabled={validatingUstId || !watchFields.umsatzsteuer_lkz || !watchFields.umsatzsteuer_id}
                                    >
                                      {validatingUstId ? (
                                        <Loader2 className="h-4 w-4 animate-spin" />
                                      ) : (
                                        <Search className="h-4 w-4" />
                                      )}
                                    </Button>
                                  </TooltipTrigger>
                                  <TooltipContent>
                                    <p>UST-ID bei EU VIES prüfen</p>
                                  </TooltipContent>
                                </Tooltip>
                              </TooltipProvider>
                            </div>
                          </div>
                          <div className="col-span-4 space-y-1.5">
                            <Label className="text-sm text-gray-600">Steuernummer</Label>
                            <Input {...register('steuernummer')} disabled={!isEditing} className="bg-white" />
                          </div>
                        </div>
                      </div>

                      {/* Weitere UST-IDs (dynamisch) */}
                      <div>
                        <div className="flex items-center justify-between mb-4">
                          <h3 className="text-sm font-semibold text-gray-900">Weitere UST-IDs</h3>
                          {isEditing && (
                            <Button type="button" size="sm" variant="outline" onClick={addUstId}>
                              <Plus className="h-4 w-4 mr-1" />Hinzufügen
                            </Button>
                          )}
                        </div>
                        
                        <div className="space-y-3">
                          {weitereUstIds.length === 0 ? (
                            <p className="text-sm text-gray-500 text-center py-4 bg-gray-50 rounded-lg">
                              Keine weiteren UST-IDs vorhanden
                            </p>
                          ) : (
                            weitereUstIds.map((ust, index) => (
                              <div key={ust.id} className="flex items-center gap-3 p-3 bg-gray-50 rounded-lg">
                                <div className="flex-1 grid grid-cols-4 gap-3">
                                  <Select 
                                    value={ust.land} 
                                    onValueChange={(v) => updateUstId(ust.id, 'land', v)}
                                    disabled={!isEditing}
                                  >
                                    <SelectTrigger className="bg-white">
                                      <SelectValue placeholder="Land wählen" />
                                    </SelectTrigger>
                                    <SelectContent>
                                      {EU_LAENDER.map(l => (
                                        <SelectItem key={l.land} value={l.land}>{l.land}</SelectItem>
                                      ))}
                                    </SelectContent>
                                  </Select>
                                  <Input 
                                    value={ust.lkz}
                                    onChange={(e) => updateUstId(ust.id, 'lkz', e.target.value)}
                                    disabled={!isEditing}
                                    className="bg-white"
                                    placeholder="LKZ"
                                    maxLength={4}
                                  />
                                  <Input 
                                    value={ust.ustid}
                                    onChange={(e) => updateUstId(ust.id, 'ustid', e.target.value)}
                                    disabled={!isEditing}
                                    className="bg-white"
                                    placeholder="UST-ID Nummer"
                                  />
                                  <TooltipProvider>
                                    <Tooltip>
                                      <TooltipTrigger asChild>
                                        <Button 
                                          type="button" 
                                          variant="outline" 
                                          size="sm"
                                          onClick={() => validateUstId(ust.lkz, ust.ustid, index)}
                                          disabled={validatingUstIdIndex === index || !ust.lkz || !ust.ustid}
                                        >
                                          {validatingUstIdIndex === index ? (
                                            <Loader2 className="h-4 w-4 animate-spin" />
                                          ) : (
                                            <Search className="h-4 w-4" />
                                          )}
                                        </Button>
                                      </TooltipTrigger>
                                      <TooltipContent>
                                        <p>UST-ID bei EU VIES prüfen</p>
                                      </TooltipContent>
                                    </Tooltip>
                                  </TooltipProvider>
                                </div>
                                {isEditing && (
                                  <Button 
                                    type="button" 
                                    variant="ghost" 
                                    size="icon"
                                    className="text-red-500 hover:text-red-700"
                                    onClick={() => removeUstId(ust.id)}
                                  >
                                    <Trash2 className="h-4 w-4" />
                                  </Button>
                                )}
                              </div>
                            ))
                          )}
                        </div>
                      </div>
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
                    </div>
                  )}
                </div>
              </div>
            </motion.div>
          )}
        </AnimatePresence>
      </div>

      {/* UST-ID Protokoll Dialog */}
      <UstIdProtokollDialog
        open={showUstIdProtokoll}
        onOpenChange={setShowUstIdProtokoll}
        protokoll={ustIdProtokoll}
        loading={ustIdProtokollLoading}
        onRefresh={loadUstIdProtokoll}
      />

      {/* Ansprechpartner Dialog */}
      <AnsprechpartnerDialog
        open={showApDialog}
        onOpenChange={setShowApDialog}
        ansprechpartner={editingAp}
        adresseId={selectedAdresse?.id || ''}
        onSave={saveAnsprechpartner}
      />
    </div>
  );
}

// ========================== UST-ID PROTOKOLL DIALOG ==========================
interface UstIdProtokollDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  protokoll: UstIdProtokoll[];
  loading: boolean;
  onRefresh: () => void;
}

function UstIdProtokollDialog({ open, onOpenChange, protokoll, loading, onRefresh }: UstIdProtokollDialogProps) {
  const formatDate = (dateStr: string) => {
    return new Date(dateStr).toLocaleString('de-DE', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-2xl max-h-[80vh]">
        <DialogHeader>
          <DialogTitle className="flex items-center gap-2">
            <History className="h-5 w-5 text-emerald-500" />
            UST-ID Validierungsprotokoll
          </DialogTitle>
          <DialogDescription>
            Alle durchgeführten UST-ID Prüfungen über die EU VIES Schnittstelle
          </DialogDescription>
        </DialogHeader>

        <div className="flex justify-end mb-2">
          <Button variant="ghost" size="sm" onClick={onRefresh} disabled={loading}>
            <RefreshCw className={cn("h-4 w-4 mr-1", loading && "animate-spin")} />
            Aktualisieren
          </Button>
        </div>

        <ScrollArea className="h-[400px] pr-4">
          {loading ? (
            <div className="flex items-center justify-center py-8">
              <Loader2 className="h-8 w-8 animate-spin text-emerald-500" />
            </div>
          ) : protokoll.length === 0 ? (
            <div className="text-center py-8 text-gray-500">
              <History className="h-12 w-12 mx-auto mb-4 text-gray-300" />
              <p>Noch keine Validierungen durchgeführt</p>
            </div>
          ) : (
            <div className="space-y-3">
              {protokoll.map((p) => (
                <div 
                  key={p.id} 
                  className={cn(
                    "border rounded-lg p-4 transition-colors",
                    p.gueltig ? "bg-green-50 border-green-200" : 
                    p.fehler_code ? "bg-yellow-50 border-yellow-200" : "bg-red-50 border-red-200"
                  )}
                >
                  <div className="flex items-start justify-between mb-2">
                    <div className="flex items-center gap-2">
                      {p.gueltig ? (
                        <CheckCircle className="h-5 w-5 text-green-600" />
                      ) : p.fehler_code ? (
                        <AlertTriangle className="h-5 w-5 text-yellow-600" />
                      ) : (
                        <XCircle className="h-5 w-5 text-red-600" />
                      )}
                      <span className="font-mono font-semibold">
                        {p.laenderkennzeichen}{p.ustid}
                      </span>
                      <Badge variant={p.gueltig ? "default" : p.fehler_code ? "secondary" : "destructive"}>
                        {p.gueltig ? "Gültig" : p.fehler_code ? "Fehler" : "Ungültig"}
                      </Badge>
                    </div>
                    <span className="text-xs text-gray-500">
                      {formatDate(p.abfrage_datum)}
                    </span>
                  </div>

                  {p.firmenname && (
                    <div className="text-sm text-gray-700 mt-2">
                      <strong>Firma:</strong> {p.firmenname}
                    </div>
                  )}
                  
                  {p.adresse && (
                    <div className="text-sm text-gray-600">
                      <strong>Adresse:</strong> {p.adresse}
                    </div>
                  )}

                  {p.fehler_code && (
                    <div className="text-sm text-yellow-700 mt-1">
                      <strong>Fehler:</strong> {p.fehler_code}
                    </div>
                  )}

                  <div className="text-xs text-gray-400 mt-2 flex items-center gap-4">
                    <span>Geprüft von: {p.abgefragt_von || 'System'}</span>
                    {p.request_identifier && (
                      <span>ID: {p.request_identifier}</span>
                    )}
                  </div>
                </div>
              ))}
            </div>
          )}
        </ScrollArea>

        <DialogFooter>
          <Button variant="outline" onClick={() => onOpenChange(false)}>
            Schließen
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}

// ========================== ANSPRECHPARTNER DIALOG MIT OCR ==========================
interface ApDialogProps {
  open: boolean;
  onOpenChange: (open: boolean) => void;
  ansprechpartner: Ansprechpartner | null;
  adresseId: string;
  onSave: (ap: Ansprechpartner) => void;
}

function AnsprechpartnerDialog({ open, onOpenChange, ansprechpartner, adresseId, onSave }: ApDialogProps) {
  const [formData, setFormData] = useState<Partial<Ansprechpartner>>({});
  const [profilbild, setProfilbild] = useState<string | null>(null);
  const [visitenkarte, setVisitenkarte] = useState<string | null>(null);
  const [isDragging, setIsDragging] = useState(false);
  const [isOcrLoading, setIsOcrLoading] = useState(false);

  useEffect(() => {
    if (ansprechpartner) {
      setFormData(ansprechpartner);
      setProfilbild(ansprechpartner.profilbild || null);
      setVisitenkarte(ansprechpartner.visitenkarte || null);
    } else {
      setFormData({});
      setProfilbild(null);
      setVisitenkarte(null);
    }
  }, [ansprechpartner, open]);

  const handleFileChange = async (e: React.ChangeEvent<HTMLInputElement>, type: 'profilbild' | 'visitenkarte') => {
    const file = e.target.files?.[0];
    if (!file) return;
    
    const reader = new FileReader();
    reader.onloadend = () => {
      if (type === 'profilbild') {
        setProfilbild(reader.result as string);
      } else {
        setVisitenkarte(reader.result as string);
        // OCR für Visitenkarte aufrufen
        runOcr(file);
      }
    };
    reader.readAsDataURL(file);
  };

  const handleDrop = (e: React.DragEvent) => {
    e.preventDefault();
    setIsDragging(false);
    
    const file = e.dataTransfer.files[0];
    if (file && file.type.startsWith('image/')) {
      const reader = new FileReader();
      reader.onloadend = () => {
        setVisitenkarte(reader.result as string);
        runOcr(file);
      };
      reader.readAsDataURL(file);
    }
  };

  // OCR Funktion
  const runOcr = async (file: File) => {
    setIsOcrLoading(true);
    try {
      const formData = new FormData();
      formData.append('file', file);
      
      const response = await api.post('/ocr/visitenkarte', formData, {
        headers: { 'Content-Type': 'multipart/form-data' }
      });
      
      if (response.data.success && response.data.data) {
        const ocr = response.data.data;
        setFormData(prev => ({
          ...prev,
          vorname: ocr.vorname || prev.vorname,
          nachname: ocr.nachname || prev.nachname,
          funktion: ocr.funktion || prev.funktion,
          telefon: ocr.telefon || prev.telefon,
          mobil: ocr.mobil || prev.mobil,
          email: ocr.email || prev.email,
          strasse: ocr.strasse || prev.strasse,
          plz: ocr.plz || prev.plz,
          ort: ocr.ort || prev.ort,
        }));
        toast.success('Visitenkarte wurde erkannt');
      }
    } catch (error) {
      console.error('OCR Error:', error);
      toast.error('Visitenkarte konnte nicht analysiert werden');
    } finally {
      setIsOcrLoading(false);
    }
  };

  const handleSave = () => {
    const ap: Ansprechpartner = {
      id: ansprechpartner?.id || crypto.randomUUID(),
      vorname: formData.vorname || '',
      nachname: formData.nachname || '',
      funktion: formData.funktion,
      sprache: formData.sprache,
      strasse: formData.strasse,
      plz: formData.plz,
      ort: formData.ort,
      telefon: formData.telefon,
      mobil: formData.mobil,
      email: formData.email,
      profilbild: profilbild || undefined,
      visitenkarte: visitenkarte || undefined,
    };
    onSave(ap);
  };

  return (
    <Dialog open={open} onOpenChange={onOpenChange}>
      <DialogContent className="max-w-2xl max-h-[90vh] overflow-auto">
        <DialogHeader>
          <DialogTitle>{ansprechpartner ? 'Ansprechpartner bearbeiten' : 'Neuer Ansprechpartner'}</DialogTitle>
        </DialogHeader>
        
        <div className="space-y-6">
          {/* Profilbild */}
          <div className="flex items-start gap-6">
            <div className="relative group">
              {profilbild ? (
                <img 
                  src={profilbild} 
                  alt="Profilbild" 
                  className="h-24 w-24 rounded-full object-cover border-4 border-white shadow-lg"
                />
              ) : (
                <div className="h-24 w-24 rounded-full bg-gradient-to-br from-emerald-400 to-emerald-600 flex items-center justify-center shadow-lg">
                  <UserCircle className="h-14 w-14 text-white" />
                </div>
              )}
              <label className="absolute inset-0 flex items-center justify-center bg-black/50 rounded-full opacity-0 group-hover:opacity-100 cursor-pointer transition-opacity">
                <Camera className="h-6 w-6 text-white" />
                <input type="file" accept="image/*" className="hidden" onChange={(e) => handleFileChange(e, 'profilbild')} />
              </label>
            </div>
            <div className="flex-1 space-y-4">
              <div className="grid grid-cols-2 gap-4">
                <div className="space-y-1.5">
                  <Label>Vorname *</Label>
                  <Input 
                    value={formData.vorname || ''}
                    onChange={(e) => setFormData({ ...formData, vorname: e.target.value })}
                  />
                </div>
                <div className="space-y-1.5">
                  <Label>Nachname *</Label>
                  <Input 
                    value={formData.nachname || ''}
                    onChange={(e) => setFormData({ ...formData, nachname: e.target.value })}
                  />
                </div>
              </div>
              <div className="grid grid-cols-2 gap-4">
                <div className="space-y-1.5">
                  <Label>Funktion</Label>
                  <Input 
                    value={formData.funktion || ''}
                    onChange={(e) => setFormData({ ...formData, funktion: e.target.value })}
                    placeholder="z.B. Geschäftsführer, Einkauf"
                  />
                </div>
                <div className="space-y-1.5">
                  <Label>Sprache</Label>
                  <Select value={formData.sprache || ""} onValueChange={(v) => setFormData({ ...formData, sprache: v })}>
                    <SelectTrigger><SelectValue placeholder="Wählen..." /></SelectTrigger>
                    <SelectContent>
                      <SelectItem value="Deutsch">Deutsch</SelectItem>
                      <SelectItem value="Englisch">Englisch</SelectItem>
                      <SelectItem value="Französisch">Französisch</SelectItem>
                      <SelectItem value="Niederländisch">Niederländisch</SelectItem>
                    </SelectContent>
                  </Select>
                </div>
              </div>
            </div>
          </div>

          {/* Adresse */}
          <div>
            <h4 className="text-sm font-semibold text-gray-900 mb-3">Adresse</h4>
            <div className="grid grid-cols-3 gap-4">
              <div className="col-span-3 space-y-1.5">
                <Label>Straße</Label>
                <Input 
                  value={formData.strasse || ''}
                  onChange={(e) => setFormData({ ...formData, strasse: e.target.value })}
                />
              </div>
              <div className="space-y-1.5">
                <Label>PLZ</Label>
                <Input 
                  value={formData.plz || ''}
                  onChange={(e) => setFormData({ ...formData, plz: e.target.value })}
                />
              </div>
              <div className="col-span-2 space-y-1.5">
                <Label>Ort</Label>
                <Input 
                  value={formData.ort || ''}
                  onChange={(e) => setFormData({ ...formData, ort: e.target.value })}
                />
              </div>
            </div>
          </div>

          {/* Kontaktdaten */}
          <div>
            <h4 className="text-sm font-semibold text-gray-900 mb-3">Kontaktdaten</h4>
            <div className="grid grid-cols-3 gap-4">
              <div className="space-y-1.5">
                <Label>Telefon</Label>
                <Input 
                  value={formData.telefon || ''}
                  onChange={(e) => setFormData({ ...formData, telefon: e.target.value })}
                  placeholder="+49 123 456789"
                />
              </div>
              <div className="space-y-1.5">
                <Label>Mobil</Label>
                <Input 
                  value={formData.mobil || ''}
                  onChange={(e) => setFormData({ ...formData, mobil: e.target.value })}
                  placeholder="+49 171 1234567"
                />
              </div>
              <div className="space-y-1.5">
                <Label>E-Mail</Label>
                <Input 
                  type="email"
                  value={formData.email || ''}
                  onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                  placeholder="name@firma.de"
                />
              </div>
            </div>
          </div>

          {/* Visitenkarte Upload mit Drag & Drop + OCR */}
          <div>
            <div className="flex items-center justify-between mb-3">
              <h4 className="text-sm font-semibold text-gray-900">Visitenkarte</h4>
              {isOcrLoading && (
                <div className="flex items-center gap-2 text-sm text-emerald-600">
                  <Loader2 className="h-4 w-4 animate-spin" />
                  Wird analysiert...
                </div>
              )}
            </div>
            <div
              className={cn(
                "border-2 border-dashed rounded-lg p-6 text-center transition-colors",
                isDragging ? "border-emerald-500 bg-emerald-50" : "border-gray-300 hover:border-gray-400",
                visitenkarte && "border-solid border-gray-200"
              )}
              onDragOver={(e) => { e.preventDefault(); setIsDragging(true); }}
              onDragLeave={() => setIsDragging(false)}
              onDrop={handleDrop}
            >
              {visitenkarte ? (
                <div className="relative">
                  <img 
                    src={visitenkarte} 
                    alt="Visitenkarte" 
                    className="max-h-40 mx-auto rounded-lg shadow"
                  />
                  <Button 
                    type="button" 
                    variant="ghost" 
                    size="icon"
                    className="absolute top-2 right-2 bg-white/80 hover:bg-white"
                    onClick={() => setVisitenkarte(null)}
                  >
                    <X className="h-4 w-4" />
                  </Button>
                </div>
              ) : (
                <label className="cursor-pointer">
                  <div className="flex flex-col items-center">
                    <div className="h-12 w-12 rounded-full bg-emerald-100 flex items-center justify-center mb-3">
                      <Scan className="h-6 w-6 text-emerald-600" />
                    </div>
                    <p className="text-sm text-gray-600">
                      Visitenkarte hierher ziehen oder <span className="text-emerald-600 font-medium">klicken</span>
                    </p>
                    <p className="text-xs text-gray-400 mt-1">Die Kontaktdaten werden automatisch erkannt (OCR)</p>
                  </div>
                  <input type="file" accept="image/*" className="hidden" onChange={(e) => handleFileChange(e, 'visitenkarte')} />
                </label>
              )}
            </div>
          </div>
        </div>

        <DialogFooter>
          <Button type="button" variant="outline" onClick={() => onOpenChange(false)}>Abbrechen</Button>
          <Button 
            type="button" 
            onClick={handleSave}
            disabled={!formData.vorname || !formData.nachname}
            className="bg-emerald-500 hover:bg-emerald-600"
          >
            Speichern
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}
