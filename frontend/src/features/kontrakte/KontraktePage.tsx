import { useState, useMemo } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion, AnimatePresence } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import { format } from 'date-fns';
import { de } from 'date-fns/locale';
import { 
  Plus, MoreHorizontal, Pencil, Trash2, FileText, Eye, 
  Save, X, Building2, Package, Truck,
  ArrowDownToLine, ArrowUpFromLine, CheckCircle, Clock,
  AlertTriangle, Ban, TrendingUp,
  DollarSign, FileCheck, MessageSquare,
  User, Search, ChevronDown, CreditCard,
  History, Copy,
  XCircle, ChevronRight, ArrowRightLeft,
  Link2, Unlink, ArrowRight, SlidersHorizontal, CalendarRange
} from 'lucide-react';
import { api } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { DataTable } from '@/components/ui/data-table';
import { Switch } from '@/components/ui/switch';
import { Dialog, DialogContent, DialogFooter, DialogHeader, DialogTitle, DialogDescription } from '@/components/ui/dialog';
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
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover';
import { Command, CommandEmpty, CommandGroup, CommandInput, CommandItem, CommandList } from '@/components/ui/command';
import { Calendar } from '@/components/ui/calendar';
import { formatRelativeTime, formatDateTime, formatDateLong } from '@/lib/dateUtils';

// ========================== TYPES ==========================
interface Benutzer {
  id: string;
  name: string;
  email?: string;
  telefon?: string;
  rolle?: string;
}

interface Ansprechpartner {
  id: string;
  vorname?: string;
  nachname?: string;
  funktion?: string;
  telefon?: string;
  email?: string;
}

interface UstIdOption {
  id: string;
  ust_id: string;
  ist_hauptid: boolean;
}

interface BankverbindungOption {
  id: string;
  iban: string;
  bic?: string;
  bank_name?: string;
  kontoinhaber?: string;
  waehrung: string;
  ist_hauptkonto: boolean;
}

interface Lieferadresse {
  id: string;
  bezeichnung?: string;
  name1?: string;
  name2?: string;
  strasse?: string;
  hausnummer?: string;
  plz?: string;
  ort?: string;
  land?: string;
  ist_standard?: boolean;
}

interface LagerOption {
  id: string;
  typ: 'haupt' | 'liefer' | 'mandant';
  bezeichnung: string;
  name1?: string;
  name2?: string;
  strasse?: string;
  hausnummer?: string;
  plz?: string;
  ort?: string;
  land?: string;
}

interface MandantData extends LagerOption {
  lieferadressen?: Array<{
    id?: string;
    bezeichnung?: string;
    name1?: string;
    strasse?: string;
    hausnummer?: string;
    plz?: string;
    ort?: string;
    land?: string;
  }>;
}

interface AdresseOption {
  id: string;
  name1: string;
  name2?: string;
  strasse?: string;
  hausnummer?: string;
  plz?: string;
  ort?: string;
  land?: string;
  land_code?: string;
  ust_id?: string;
  steuernummer?: string;
  telefon?: string;
  telefax?: string;
  email?: string;
  kundennummer?: string;
  ansprechpartner?: Ansprechpartner[];
  ust_ids?: UstIdOption[];
  bankverbindungen?: BankverbindungOption[];
  lieferadressen?: Lieferadresse[];
}

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

interface AuditLogEintrag {
  id: string;
  kontrakt_id: string;
  aktion: string;
  aktion_meta: {
    icon: string;
    farbe: string;
    label: string;
  };
  benutzer_id: string;
  benutzer_name: string;
  benutzer_kuerzel: string;
  zeitstempel: string;
  details: Record<string, any>;
  aenderungen: Array<{
    feld: string;
    feld_label?: string;
    alt: any;
    neu: any;
  }>;
}

// ========================== SCHEMA ==========================
const kontraktSchema = z.object({
  vorgang_typ: z.string().default('EK'),
  kontraktnummer: z.string().max(30).nullish(),
  // Streckengeschäft
  ist_strecke: z.boolean().default(false),
  strecken_id: z.string().nullish(),
  strecken_rolle: z.string().nullish(),
  // Vertragspartner
  id_adresse: z.string().nullish(),
  name1: z.string().min(1, 'Vertragspartner erforderlich').max(40),
  name2: z.string().max(40).nullish(),
  strasse: z.string().max(45).nullish(),
  hausnummer: z.string().max(10).nullish(),
  plz: z.string().max(10).nullish(),
  ort: z.string().max(30).nullish(),
  land: z.string().max(30).nullish(),
  ust_id: z.string().max(20).nullish(),
  telefon: z.string().max(30).nullish(),
  telefax: z.string().max(30).nullish(),
  email: z.string().email().nullish().or(z.literal('')),
  // Bankverbindung für Kontrakt
  id_bankverbindung: z.string().nullish(),
  bank_iban: z.string().max(34).nullish(),
  bank_bic: z.string().max(11).nullish(),
  bank_name: z.string().max(100).nullish(),
  bank_waehrung: z.string().max(3).nullish(),
  // Ansprechpartner
  id_ansprechpartner: z.string().nullish(),
  ansprechpartner_name: z.string().max(80).nullish(),
  ansprechpartner_telefon: z.string().max(30).nullish(),
  ansprechpartner_email: z.string().max(100).nullish(),
  // Sachbearbeiter & Händler
  id_sachbearbeiter: z.string().nullish(),
  sachbearbeiter_name: z.string().max(80).nullish(),
  sachbearbeiter_telefon: z.string().max(30).nullish(),
  sachbearbeiter_email: z.string().max(100).nullish(),
  id_haendler: z.string().nullish(),
  haendler_name: z.string().max(80).nullish(),
  // Termine (jetzt in Kopfdaten)
  erstellungsdatum: z.string().nullish(),
  gueltig_von: z.string().nullish(),
  gueltig_bis: z.string().nullish(),
  // Läger
  id_abhollager: z.string().nullish(),
  abhollager_typ: z.string().nullish(), // 'haupt', 'liefer', 'mandant', 'lieferant', 'abnehmer'
  abhollager_name: z.string().max(100).nullish(),
  abhollager_strasse: z.string().max(100).nullish(),
  abhollager_plz: z.string().max(10).nullish(),
  abhollager_ort: z.string().max(50).nullish(),
  abhollager_land: z.string().max(50).nullish(),
  id_ziellager: z.string().nullish(),
  ziellager_typ: z.string().nullish(),
  ziellager_name: z.string().max(100).nullish(),
  ziellager_strasse: z.string().max(100).nullish(),
  ziellager_plz: z.string().max(10).nullish(),
  ziellager_ort: z.string().max(50).nullish(),
  ziellager_land: z.string().max(50).nullish(),
  // Währung
  waehrung_kurz: z.string().max(5).default('EUR'),
  waehrungskurs: z.number().default(1),
  zahlungsbedingung: z.string().max(100).nullish(),
  lieferbedingung: z.string().max(50).nullish(),
  // Fixierung
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

interface StreckenPartner {
  id: string;
  kontraktnummer?: string;
  name1?: string;
  status?: string;
}

interface Kontrakt extends KontraktForm {
  id: string;
  positionen?: Position[];
  erstellt_am?: string;
  summen?: { summe_menge: number; summe_wert: number; anzahl_positionen: number };
  // Streckengeschäft
  ist_strecke?: boolean;
  strecken_id?: string;
  strecken_rolle?: string;
  strecken_partner?: { ek?: StreckenPartner; vk?: StreckenPartner };
}

// ========================== SIDEBAR SECTIONS ==========================
const sidebarSections = [
  { id: 'kopf', label: 'Kopfdaten', icon: FileText },
  { id: 'partner', label: 'Vertragspartner', icon: Building2 },
  { id: 'laeger', label: 'Läger', icon: Truck },
  { id: 'konditionen', label: 'Konditionen', icon: DollarSign },
  { id: 'fixierung', label: 'Fixierung', icon: TrendingUp },
  { id: 'positionen', label: 'Positionen', icon: Package },
  { id: 'formulartexte', label: 'Formulartexte', icon: FileCheck },
  { id: 'bemerkungen', label: 'Bemerkungen', icon: MessageSquare },
  { id: 'protokoll', label: 'Protokoll', icon: History },
];

const statusColors: Record<string, { bg: string; text: string; icon: any }> = {
  OFFEN: { bg: 'bg-blue-100', text: 'text-blue-700', icon: Clock },
  AKTIV: { bg: 'bg-green-100', text: 'text-green-700', icon: CheckCircle },
  TEILERFUELLT: { bg: 'bg-yellow-100', text: 'text-yellow-700', icon: AlertTriangle },
  ERFUELLT: { bg: 'bg-emerald-100', text: 'text-emerald-700', icon: CheckCircle },
  STORNO: { bg: 'bg-red-100', text: 'text-red-700', icon: Ban },
};

// ========================== BENUTZER-AUSWAHL ==========================
function BenutzerSelect({ 
  value, 
  onChange, 
  disabled, 
  placeholder = "Benutzer auswählen...",
  benutzerListe,
  showDetails = true
}: { 
  value?: string; 
  onChange: (id: string, benutzer?: Benutzer) => void; 
  disabled?: boolean; 
  placeholder?: string;
  benutzerListe: Benutzer[];
  showDetails?: boolean;
}) {
  const selected = benutzerListe.find(b => b.id === value);

  return (
    <Select 
      value={value || ''} 
      onValueChange={(v) => {
        const benutzer = benutzerListe.find(b => b.id === v);
        onChange(v, benutzer);
      }}
      disabled={disabled}
    >
      <SelectTrigger className={cn("bg-white", !value && "text-muted-foreground")}>
        <SelectValue placeholder={placeholder}>
          {selected && (
            <span className="flex items-center gap-2">
              <User className="h-3.5 w-3.5 text-gray-400" />
              {selected.name}
            </span>
          )}
        </SelectValue>
      </SelectTrigger>
      <SelectContent>
        {benutzerListe.map((b) => (
          <SelectItem key={b.id} value={b.id}>
            <span className="flex items-center gap-2">
              <span className="font-medium">{b.name}</span>
              {showDetails && b.rolle && <Badge variant="outline" className="text-[10px] px-1">{b.rolle}</Badge>}
            </span>
          </SelectItem>
        ))}
      </SelectContent>
    </Select>
  );
}

// ========================== ADRESSEN-AUSWAHL ==========================
function AdressenSelect({ 
  value, 
  onChange, 
  disabled 
}: { 
  value?: string; 
  onChange: (id: string, adresse?: AdresseOption) => void; 
  disabled?: boolean; 
}) {
  const [open, setOpen] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');

  const { data: adressenData } = useQuery({
    queryKey: ['adressen-lookup', searchTerm],
    queryFn: async () => {
      const response = await api.get('/kontrakte/lookup/adressen', { params: { suche: searchTerm, limit: 50 } });
      return response.data.data as AdresseOption[];
    },
  });

  const selected = adressenData?.find(a => a.id === value);

  return (
    <Popover open={open} onOpenChange={setOpen}>
      <PopoverTrigger asChild>
        <Button variant="outline" role="combobox" disabled={disabled}
          className={cn("w-full justify-between font-normal text-left h-auto min-h-[40px] py-2", !value && "text-muted-foreground")}>
          {selected ? (
            <div className="flex flex-col items-start">
              <span className="font-medium">{selected.name1}</span>
              <span className="text-xs text-gray-500">{selected.plz} {selected.ort}</span>
            </div>
          ) : (
            <span className="flex items-center gap-2">
              <Search className="h-4 w-4" />
              Adresse suchen...
            </span>
          )}
          <ChevronDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
        </Button>
      </PopoverTrigger>
      <PopoverContent className="w-[400px] p-0">
        <Command shouldFilter={false}>
          <CommandInput placeholder="Firma, Kundennr. oder Ort suchen..." 
            value={searchTerm} onValueChange={setSearchTerm} />
          <CommandList>
            <CommandEmpty>Keine Adressen gefunden.</CommandEmpty>
            <CommandGroup>
              {adressenData?.map((a) => (
                <CommandItem key={a.id} value={a.id}
                  onSelect={() => { onChange(a.id, a); setOpen(false); }}>
                  <div className="flex flex-col py-1">
                    <div className="flex items-center gap-2">
                      <Building2 className="h-4 w-4 text-gray-400" />
                      <span className="font-medium">{a.name1}</span>
                      {a.kundennummer && (
                        <Badge variant="outline" className="text-xs">{a.kundennummer}</Badge>
                      )}
                    </div>
                    <span className="text-xs text-gray-500 ml-6">
                      {a.strasse} {a.hausnummer}, {a.plz} {a.ort}
                    </span>
                  </div>
                </CommandItem>
              ))}
            </CommandGroup>
          </CommandList>
        </Command>
      </PopoverContent>
    </Popover>
  );
}

// ========================== ADRESSE-SELECT FÜR STRECKEN-DIALOG ==========================
function AdresseSelect({ 
  value, 
  onChange,
  placeholder = "Adresse suchen..." 
}: { 
  value: AdresseOption | null; 
  onChange: (adresse: AdresseOption | null) => void; 
  placeholder?: string;
}) {
  const [open, setOpen] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');

  const { data: adressenData } = useQuery({
    queryKey: ['adressen-lookup', searchTerm],
    queryFn: async () => {
      const response = await api.get('/kontrakte/lookup/adressen', { params: { suche: searchTerm, limit: 50 } });
      return response.data.data as AdresseOption[];
    },
  });

  return (
    <Popover open={open} onOpenChange={setOpen}>
      <PopoverTrigger asChild>
        <Button variant="outline" role="combobox"
          className={cn("w-full justify-between font-normal text-left h-auto min-h-[40px] py-2", !value && "text-muted-foreground")}>
          {value ? (
            <div className="flex flex-col items-start">
              <span className="font-medium">{value.name1}</span>
              <span className="text-xs text-gray-500">{value.plz} {value.ort}</span>
            </div>
          ) : (
            <span className="flex items-center gap-2">
              <Search className="h-4 w-4" />
              {placeholder}
            </span>
          )}
          <ChevronDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
        </Button>
      </PopoverTrigger>
      <PopoverContent className="w-[400px] p-0">
        <Command shouldFilter={false}>
          <CommandInput placeholder="Firma, Kundennr. oder Ort suchen..." 
            value={searchTerm} onValueChange={setSearchTerm} />
          <CommandList>
            <CommandEmpty>Keine Adressen gefunden.</CommandEmpty>
            <CommandGroup>
              {adressenData?.map((a) => (
                <CommandItem key={a.id} value={a.id}
                  onSelect={() => { onChange(a); setOpen(false); }}>
                  <div className="flex flex-col py-1">
                    <div className="flex items-center gap-2">
                      <Building2 className="h-4 w-4 text-gray-400" />
                      <span className="font-medium">{a.name1}</span>
                      {a.kundennummer && (
                        <Badge variant="outline" className="text-xs">{a.kundennummer}</Badge>
                      )}
                    </div>
                    <span className="text-xs text-gray-500 ml-6">
                      {a.strasse} {a.hausnummer}, {a.plz} {a.ort}
                    </span>
                  </div>
                </CommandItem>
              ))}
            </CommandGroup>
          </CommandList>
        </Command>
      </PopoverContent>
    </Popover>
  );
}

// ========================== ARTIKEL-AUSWAHL KOMPONENTE ==========================
interface ArtikelOption {
  id: string;
  anr1?: string;
  artbez1?: string;
  artbez2?: string;
  einheit?: string;
  einheit_preis?: string;
  artikelgruppe?: string;
  gefahrgut?: boolean;
  ist_produkt?: boolean;
  dienstleistung?: boolean;
}

function ArtikelSelect({ 
  value, 
  onChange, 
  disabled 
}: { 
  value?: string; 
  onChange: (id: string, artikel?: ArtikelOption) => void; 
  disabled?: boolean;
}) {
  const [open, setOpen] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');
  const [filterGruppe, setFilterGruppe] = useState<string>('');
  const [filterTyp, setFilterTyp] = useState<string>('');

  const { data: artikelData } = useQuery({
    queryKey: ['artikel-lookup', searchTerm, filterGruppe, filterTyp],
    queryFn: async () => {
      const params: any = { suche: searchTerm, limit: 50 };
      if (filterGruppe) params.artikelgruppe = filterGruppe;
      if (filterTyp === 'gefahrgut') params.gefahrgut = true;
      if (filterTyp === 'produkt') params.ist_produkt = true;
      if (filterTyp === 'dienstleistung') params.dienstleistung = true;
      const response = await api.get('/artikel/lookup', { params });
      return response.data.data as ArtikelOption[];
    },
  });

  const { data: gruppenData } = useQuery({
    queryKey: ['artikelgruppen'],
    queryFn: async () => {
      const response = await api.get('/artikel/gruppen');
      return response.data.data as string[];
    },
  });

  const selected = artikelData?.find(a => a.id === value);

  return (
    <Popover open={open} onOpenChange={setOpen}>
      <PopoverTrigger asChild>
        <Button variant="outline" role="combobox" disabled={disabled}
          className={cn("w-full justify-between font-normal text-left h-auto min-h-[40px] py-2", !value && "text-muted-foreground")}>
          {selected ? (
            <div className="flex items-center gap-2">
              <Package className="h-4 w-4 text-emerald-600" />
              <span className="font-mono text-sm">{selected.anr1}</span>
              <span className="font-medium">{selected.artbez1}</span>
            </div>
          ) : (
            <span className="flex items-center gap-2">
              <Search className="h-4 w-4" />
              Artikel auswählen...
            </span>
          )}
          <ChevronDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
        </Button>
      </PopoverTrigger>
      <PopoverContent className="w-[500px] p-0" align="start">
        <div className="p-3 border-b space-y-2">
          <Input
            placeholder="Artikelnummer oder Bezeichnung suchen..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="h-9"
          />
          <div className="flex gap-2">
            <Select value={filterGruppe || '__all__'} onValueChange={(v) => setFilterGruppe(v === '__all__' ? '' : v)}>
              <SelectTrigger className="h-8 text-xs flex-1">
                <SelectValue placeholder="Alle Gruppen" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="__all__">Alle Gruppen</SelectItem>
                {gruppenData?.map(g => (
                  <SelectItem key={g} value={g}>{g}</SelectItem>
                ))}
              </SelectContent>
            </Select>
            <Select value={filterTyp || '__all__'} onValueChange={(v) => setFilterTyp(v === '__all__' ? '' : v)}>
              <SelectTrigger className="h-8 text-xs w-[140px]">
                <SelectValue placeholder="Alle Typen" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="__all__">Alle Typen</SelectItem>
                <SelectItem value="gefahrgut">⚠️ Gefahrgut</SelectItem>
                <SelectItem value="produkt">📦 Produkt</SelectItem>
                <SelectItem value="dienstleistung">🔧 Dienstleistung</SelectItem>
              </SelectContent>
            </Select>
          </div>
        </div>
        <ScrollArea className="h-[300px]">
          {artikelData && artikelData.length > 0 ? (
            <div className="p-1">
              {artikelData.map((a) => (
                <div
                  key={a.id}
                  onClick={() => { onChange(a.id, a); setOpen(false); }}
                  className={cn(
                    "flex items-center gap-3 p-2 rounded cursor-pointer hover:bg-gray-100",
                    a.id === value && "bg-emerald-50"
                  )}
                >
                  <div className="flex-shrink-0">
                    <span className="font-mono text-sm text-gray-600 bg-gray-100 px-2 py-1 rounded">{a.anr1 || '-'}</span>
                  </div>
                  <div className="flex-1 min-w-0">
                    <div className="font-medium truncate">{a.artbez1}</div>
                    <div className="flex items-center gap-2 text-xs text-gray-500">
                      {a.artikelgruppe && <Badge variant="outline" className="text-[10px]">{a.artikelgruppe}</Badge>}
                      {a.gefahrgut && <span className="text-orange-600">⚠️</span>}
                      {a.ist_produkt && <span>📦</span>}
                    </div>
                  </div>
                  <div className="text-xs text-gray-400">{a.einheit_preis || 't'}</div>
                </div>
              ))}
            </div>
          ) : (
            <div className="p-8 text-center text-gray-500">
              <Package className="h-8 w-8 mx-auto mb-2 text-gray-300" />
              <p className="text-sm">Keine Artikel gefunden</p>
            </div>
          )}
        </ScrollArea>
      </PopoverContent>
    </Popover>
  );
}

// ========================== POSITIONS-DIALOG NEU ==========================
interface PositionDialogProps {
  open: boolean;
  onClose: () => void;
  onSave: (pos: Partial<Position>) => void;
  position?: Position | null;
  waehrung: string;
  readOnly?: boolean;
}

function PositionDialog({ open, onClose, onSave, position, waehrung, readOnly = false }: PositionDialogProps) {
  const [formData, setFormData] = useState<Partial<Position>>(
    position || { position_typ: 'ARTIKEL', einheitkurz: 't', waehrungskurs: 1, mengen_toleranz_prozent: 10, ueberliefer_ok: true, position_abgeschlossen: false }
  );
  const [activeTab, setActiveTab] = useState<'artikel' | 'preise' | 'optionen'>('artikel');
  const selectedWaehrung = getWaehrung(formData.waehrung_fremd_kurz || waehrung);

  // Reset formData wenn position sich ändert
  useMemo(() => {
    if (position) {
      setFormData(position);
    } else {
      setFormData({ position_typ: 'ARTIKEL', einheitkurz: 't', waehrungskurs: 1, mengen_toleranz_prozent: 10, ueberliefer_ok: true, position_abgeschlossen: false });
    }
  }, [position]);

  const gesamtpreis = formData.anzahl && formData.einzelpreis 
    ? Math.round(formData.anzahl * formData.einzelpreis * 100) / 100 
    : 0;

  const handleSave = () => {
    if (readOnly) { onClose(); return; }
    formData.gesamtpreis = gesamtpreis;
    onSave(formData);
    onClose();
  };

  const handleArtikelSelect = (id: string, artikel?: ArtikelOption) => {
    if (artikel && !readOnly) {
      setFormData({ 
        ...formData, 
        id_artikel: id, 
        anr1: artikel.anr1 || '', 
        artbez1: artikel.artbez1 || '',
        einheitkurz: artikel.einheit_preis || 't'
      });
    }
  };

  return (
    <Dialog open={open} onOpenChange={onClose}>
      <DialogContent className="max-w-2xl" data-testid="position-dialog">
        <DialogHeader className="pb-0">
          <DialogTitle className="flex items-center gap-2">
            <div className={cn("h-8 w-8 rounded-lg flex items-center justify-center", readOnly ? "bg-blue-100" : "bg-emerald-100")}>
              {readOnly ? <Eye className="h-4 w-4 text-blue-600" /> : <Package className="h-4 w-4 text-emerald-600" />}
            </div>
            <div>
              <span>{readOnly ? 'Positionsdetails' : (position ? 'Position bearbeiten' : 'Neue Position')}</span>
              {position?.positionsnummer && <Badge variant="outline" className="ml-2">Pos. {position.positionsnummer}</Badge>}
            </div>
          </DialogTitle>
        </DialogHeader>
        
        {/* Smart Tabs */}
        <div className="flex border-b mt-2">
          {[
            { id: 'artikel', label: 'Artikel', icon: Package },
            { id: 'preise', label: 'Mengen & Preise', icon: DollarSign },
            { id: 'optionen', label: 'Optionen', icon: Scale },
          ].map((tab) => (
            <button
              key={tab.id}
              type="button"
              onClick={() => setActiveTab(tab.id as any)}
              className={cn(
                "flex items-center gap-1.5 px-4 py-2 text-sm font-medium border-b-2 transition-colors -mb-px",
                activeTab === tab.id 
                  ? "border-emerald-600 text-emerald-700" 
                  : "border-transparent text-gray-500 hover:text-gray-700"
              )}
            >
              <tab.icon className="h-3.5 w-3.5" />
              {tab.label}
            </button>
          ))}
        </div>
        
        <div className="py-3 min-h-[200px]">
          {/* TAB: Artikel */}
          {activeTab === 'artikel' && (
            <div className="space-y-4">
              <div className="space-y-2">
                <Label className="font-medium">Artikel auswählen</Label>
                <ArtikelSelect 
                  value={formData.id_artikel} 
                  onChange={handleArtikelSelect}
                  disabled={readOnly}
                />
              </div>
              
              {/* Gewählter Artikel - Detailansicht */}
              {formData.artbez1 && (
                <div className="p-4 bg-gradient-to-br from-emerald-50 to-white rounded-xl border border-emerald-200 shadow-sm">
                  <div className="flex items-start justify-between">
                    <div className="flex items-center gap-3">
                      <div className="h-12 w-12 rounded-lg bg-emerald-100 flex items-center justify-center">
                        <Package className="h-6 w-6 text-emerald-600" />
                      </div>
                      <div>
                        <div className="flex items-center gap-2">
                          <span className="font-mono text-sm bg-white px-2 py-0.5 rounded border">{formData.anr1 || '-'}</span>
                          <span className="font-semibold text-gray-900">{formData.artbez1}</span>
                        </div>
                        <div className="text-sm text-gray-500 mt-0.5">Einheit: {formData.einheitkurz || 't'}</div>
                      </div>
                    </div>
                    {!readOnly && formData.id_artikel && (
                      <Button variant="ghost" size="sm" className="text-gray-400 hover:text-red-500"
                        onClick={() => setFormData({ ...formData, id_artikel: undefined, anr1: '', artbez1: '' })}>
                        <X className="h-4 w-4" />
                      </Button>
                    )}
                  </div>
                </div>
              )}
              
              {/* Manuelle Eingabe falls kein Artikel */}
              {!formData.id_artikel && !readOnly && (
                <div className="p-3 bg-gray-50 rounded-lg border border-dashed space-y-3">
                  <p className="text-xs text-gray-500 flex items-center gap-1"><AlertTriangle className="h-3 w-3" />Oder manuell eingeben:</p>
                  <div className="grid grid-cols-2 gap-3">
                    <div className="space-y-1">
                      <Label className="text-xs">Artikel-Nr.</Label>
                      <Input value={formData.anr1 || ''} onChange={(e) => setFormData({ ...formData, anr1: e.target.value })} className="h-8" />
                    </div>
                    <div className="space-y-1">
                      <Label className="text-xs">Bezeichnung</Label>
                      <Input value={formData.artbez1 || ''} onChange={(e) => setFormData({ ...formData, artbez1: e.target.value })} className="h-8" />
                    </div>
                  </div>
                </div>
              )}
            </div>
          )}

          {/* TAB: Mengen & Preise */}
          {activeTab === 'preise' && (
            <div className="space-y-4">
              {/* Hauptwerte - große Karten */}
              <div className="grid grid-cols-3 gap-3">
                <div className="p-4 rounded-xl border bg-white shadow-sm">
                  <Label className="text-xs text-gray-500 uppercase tracking-wide">Menge</Label>
                  <div className="flex items-end gap-2 mt-1">
                    <Input 
                      type="number" 
                      step="0.01" 
                      value={formData.anzahl || ''} 
                      onChange={(e) => setFormData({ ...formData, anzahl: parseFloat(e.target.value) || undefined })}
                      className="text-2xl font-bold h-12 border-0 p-0 focus-visible:ring-0"
                      disabled={readOnly}
                      data-testid="position-menge-input"
                    />
                    <Select value={formData.einheitkurz || 't'} onValueChange={(v) => setFormData({ ...formData, einheitkurz: v })} disabled={readOnly}>
                      <SelectTrigger className="w-20 h-8"><SelectValue /></SelectTrigger>
                      <SelectContent>
                        <SelectItem value="t">t</SelectItem>
                        <SelectItem value="kg">kg</SelectItem>
                        <SelectItem value="Stk">Stk</SelectItem>
                        <SelectItem value="m³">m³</SelectItem>
                      </SelectContent>
                    </Select>
                  </div>
                </div>
                
                <div className="p-4 rounded-xl border bg-white shadow-sm">
                  <Label className="text-xs text-gray-500 uppercase tracking-wide">Einzelpreis</Label>
                  <div className="flex items-end gap-1 mt-1">
                    <Input 
                      type="number" 
                      step="0.01" 
                      value={formData.einzelpreis || ''} 
                      onChange={(e) => setFormData({ ...formData, einzelpreis: parseFloat(e.target.value) || undefined })}
                      className="text-2xl font-bold h-12 border-0 p-0 focus-visible:ring-0"
                      disabled={readOnly}
                      data-testid="position-preis-input"
                    />
                    <span className="text-gray-400 text-lg pb-2">{selectedWaehrung.symbol}/{formData.einheitkurz || 't'}</span>
                  </div>
                </div>
                
                <div className="p-4 rounded-xl bg-gradient-to-br from-emerald-500 to-emerald-600 text-white shadow-lg">
                  <Label className="text-xs text-emerald-100 uppercase tracking-wide">Gesamtpreis</Label>
                  <div className="text-2xl font-bold mt-1">
                    {gesamtpreis.toLocaleString('de-DE', { minimumFractionDigits: 2 })}
                    <span className="text-lg ml-1 text-emerald-200">{selectedWaehrung.symbol}</span>
                  </div>
                </div>
              </div>

              {/* Gültigkeit */}
              <div className="p-3 bg-gray-50 rounded-lg border">
                <Label className="text-xs text-gray-600 flex items-center gap-1 mb-2"><Calendar className="h-3 w-3" />Gültigkeit</Label>
                <div className="grid grid-cols-2 gap-3">
                  <div className="space-y-1">
                    <Label className="text-xs text-gray-500">Von</Label>
                    <Input type="date" value={formData.gueltig_von || ''} onChange={(e) => setFormData({ ...formData, gueltig_von: e.target.value })} disabled={readOnly} className="h-8" />
                  </div>
                  <div className="space-y-1">
                    <Label className="text-xs text-gray-500">Bis</Label>
                    <Input type="date" value={formData.gueltig_bis || ''} onChange={(e) => setFormData({ ...formData, gueltig_bis: e.target.value })} disabled={readOnly} className="h-8" />
                  </div>
                </div>
              </div>
            </div>
          )}

          {/* TAB: Optionen */}
          {activeTab === 'optionen' && (
            <div className="space-y-4">
              {/* Toleranz & Überlieferung */}
              <div className="grid grid-cols-2 gap-4">
                <div className="p-4 rounded-xl border bg-white">
                  <Label className="text-sm font-medium flex items-center gap-2">
                    <Percent className="h-4 w-4 text-blue-600" />
                    Mengentoleranz
                  </Label>
                  <div className="flex items-center gap-2 mt-2">
                    <Input 
                      type="number" 
                      min="0" 
                      max="100" 
                      value={formData.mengen_toleranz_prozent || 10} 
                      onChange={(e) => setFormData({ ...formData, mengen_toleranz_prozent: parseFloat(e.target.value) })}
                      className="w-20 h-10 text-center text-lg font-semibold"
                      disabled={readOnly}
                    />
                    <span className="text-gray-500">%</span>
                  </div>
                  <p className="text-xs text-gray-500 mt-2">Erlaubte Abweichung bei der Liefermenge</p>
                </div>
                
                <div className="p-4 rounded-xl border bg-white">
                  <Label className="text-sm font-medium flex items-center gap-2">
                    <TrendingUp className="h-4 w-4 text-orange-600" />
                    Überlieferung
                  </Label>
                  <div className="flex items-center gap-3 mt-3">
                    <Switch 
                      checked={formData.ueberliefer_ok !== false} 
                      onCheckedChange={(v) => setFormData({ ...formData, ueberliefer_ok: v })}
                      disabled={readOnly}
                    />
                    <span className={cn("text-sm", formData.ueberliefer_ok ? "text-green-700" : "text-red-600")}>
                      {formData.ueberliefer_ok ? 'Erlaubt' : 'Nicht erlaubt'}
                    </span>
                  </div>
                  <p className="text-xs text-gray-500 mt-2">Darf mehr als bestellt geliefert werden?</p>
                </div>
              </div>

              {/* Status */}
              <div className="p-4 rounded-xl border bg-white">
                <Label className="text-sm font-medium flex items-center gap-2">
                  <Lock className="h-4 w-4 text-gray-600" />
                  Positionsstatus
                </Label>
                <div className="flex items-center gap-3 mt-3">
                  <Switch 
                    checked={formData.position_abgeschlossen || false} 
                    onCheckedChange={(v) => setFormData({ ...formData, position_abgeschlossen: v })}
                    disabled={readOnly}
                  />
                  <span className={cn("text-sm font-medium", formData.position_abgeschlossen ? "text-gray-700" : "text-green-600")}>
                    {formData.position_abgeschlossen ? 'Abgeschlossen' : 'Offen'}
                  </span>
                </div>
              </div>

              {/* Bemerkung */}
              <div className="space-y-1">
                <Label className="text-sm flex items-center gap-2"><MessageSquare className="h-4 w-4 text-gray-400" />Bemerkung</Label>
                <Textarea 
                  value={formData.bemerkung || ''} 
                  onChange={(e) => setFormData({ ...formData, bemerkung: e.target.value })}
                  disabled={readOnly}
                  rows={3}
                  placeholder="Optionale Bemerkung zur Position..."
                  className="resize-none"
                />
              </div>
            </div>
          )}
        </div>

        <DialogFooter className="border-t pt-4">
          <div className="flex items-center justify-between w-full">
            {/* Zusammenfassung links */}
            <div className="text-sm text-gray-500">
              {formData.artbez1 && (
                <span className="flex items-center gap-2">
                  <Package className="h-4 w-4" />
                  {formData.anzahl?.toLocaleString('de-DE')} {formData.einheitkurz} × {formData.einzelpreis?.toFixed(2)} = <strong className="text-emerald-700">{gesamtpreis.toLocaleString('de-DE', { minimumFractionDigits: 2 })} {selectedWaehrung.symbol}</strong>
                </span>
              )}
            </div>
            <div className="flex gap-2">
              <Button type="button" variant="outline" onClick={onClose}>
                {readOnly ? 'Schließen' : 'Abbrechen'}
              </Button>
              {!readOnly && (
                <Button type="button" onClick={handleSave} className="bg-emerald-600 hover:bg-emerald-700" data-testid="position-save-btn">
                  <Save className="h-4 w-4 mr-1" />
                  {position ? 'Speichern' : 'Hinzufügen'}
                </Button>
              )}
            </div>
          </div>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  );
}

// ========================== POSITIONS-LISTE (NEU) ==========================
interface PositionenListeProps {
  positionen: Position[];
  waehrung: string;
  isEditing: boolean;
  onEdit: (pos: Position) => void;
  onDelete: (id: string) => void;
  onCopy: (pos: Position) => void;
  onView: (pos: Position) => void;
}

function PositionenListe({ positionen, waehrung, isEditing, onEdit, onDelete, onCopy, onView }: PositionenListeProps) {
  const selectedWaehrung = getWaehrung(waehrung);
  
  // Summen berechnen
  const summen = useMemo(() => {
    return positionen.reduce((acc, pos) => ({
      menge: acc.menge + (pos.anzahl || 0),
      wert: acc.wert + (pos.gesamtpreis || 0)
    }), { menge: 0, wert: 0 });
  }, [positionen]);

  if (positionen.length === 0) {
    return (
      <div className="text-center py-12 bg-gradient-to-br from-gray-50 to-white rounded-xl border-2 border-dashed" data-testid="positionen-empty">
        <Package className="h-16 w-16 mx-auto mb-3 text-gray-300" />
        <p className="text-gray-500 font-medium">Keine Positionen vorhanden</p>
        <p className="text-sm text-gray-400 mt-1">Fügen Sie Artikel zum Kontrakt hinzu</p>
      </div>
    );
  }

  return (
    <div className="space-y-3" data-testid="positionen-liste">
      {/* Übersichts-Header */}
      <div className="flex items-center justify-between p-3 bg-gray-50 rounded-lg border">
        <div className="flex items-center gap-4">
          <Badge variant="secondary" className="text-sm">{positionen.length} Position{positionen.length !== 1 ? 'en' : ''}</Badge>
          <span className="text-sm text-gray-600">Σ {summen.menge.toLocaleString('de-DE')} t</span>
        </div>
        <div className="text-right">
          <span className="text-xs text-gray-500">Gesamtwert</span>
          <div className="text-lg font-bold text-emerald-700">{summen.wert.toLocaleString('de-DE', { minimumFractionDigits: 2 })} {selectedWaehrung.symbol}</div>
        </div>
      </div>

      {/* Positions-Karten */}
      {positionen.map((pos, index) => (
        <div 
          key={pos.id} 
          className={cn(
            "group relative rounded-xl border bg-white shadow-sm hover:shadow-md transition-all cursor-pointer",
            pos.position_abgeschlossen && "bg-gray-50 opacity-80"
          )}
          onDoubleClick={() => onView(pos)}
          data-testid={`position-item-${index}`}
        >
          {/* Positions-Nummer Badge */}
          <div className="absolute -left-2 -top-2 z-10">
            <div className={cn(
              "h-8 w-8 rounded-full flex items-center justify-center text-sm font-bold shadow-md",
              pos.position_abgeschlossen ? "bg-gray-400 text-white" : "bg-emerald-500 text-white"
            )}>
              {pos.positionsnummer}
            </div>
          </div>

          <div className="p-4 pl-8">
            <div className="flex items-start justify-between">
              {/* Linke Seite: Artikel-Info */}
              <div className="flex-1 min-w-0">
                <div className="flex items-center gap-2 flex-wrap">
                  <span className="font-mono text-xs bg-gray-100 px-2 py-0.5 rounded">{pos.anr1 || '-'}</span>
                  <span className="font-semibold text-gray-900 truncate">{pos.artbez1}</span>
                  {pos.position_abgeschlossen && (
                    <Badge variant="secondary" className="text-xs"><Lock className="h-3 w-3 mr-1" />Abgeschlossen</Badge>
                  )}
                </div>
                
                {/* Menge und Preis - Prominent */}
                <div className="flex items-center gap-6 mt-2">
                  <div className="flex items-center gap-1">
                    <span className="text-2xl font-bold text-gray-900">{pos.anzahl?.toLocaleString('de-DE')}</span>
                    <span className="text-sm text-gray-500">{pos.einheitkurz}</span>
                  </div>
                  <div className="text-gray-400">×</div>
                  <div className="flex items-center gap-1">
                    <span className="text-lg font-medium text-gray-700">{pos.einzelpreis?.toFixed(2)}</span>
                    <span className="text-sm text-gray-500">{selectedWaehrung.symbol}/{pos.einheitkurz}</span>
                  </div>
                </div>

                {/* Status-Tags */}
                <div className="flex items-center gap-2 mt-2">
                  {!pos.ueberliefer_ok && (
                    <Badge variant="outline" className="text-xs text-orange-600 border-orange-300">
                      <AlertTriangle className="h-3 w-3 mr-1" />Keine Überlieferung
                    </Badge>
                  )}
                  {pos.mengen_toleranz_prozent !== 10 && (
                    <Badge variant="outline" className="text-xs">Toleranz: {pos.mengen_toleranz_prozent}%</Badge>
                  )}
                  {pos.lieferort_name && (
                    <Badge variant="outline" className="text-xs text-gray-500">
                      <Truck className="h-3 w-3 mr-1" />{pos.lieferort_name}
                    </Badge>
                  )}
                </div>
              </div>

              {/* Rechte Seite: Gesamtpreis & Aktionen */}
              <div className="flex flex-col items-end gap-2">
                <div className="text-right">
                  <div className="text-xs text-gray-500">Gesamt</div>
                  <div className="text-xl font-bold text-emerald-700">
                    {pos.gesamtpreis?.toLocaleString('de-DE', { minimumFractionDigits: 2 })}
                    <span className="text-sm ml-1 font-normal text-gray-500">{selectedWaehrung.symbol}</span>
                  </div>
                </div>
                
                {/* Action Buttons */}
                <div className={cn("flex gap-1", !isEditing && "opacity-0 group-hover:opacity-100 transition-opacity")}>
                  <Button variant="ghost" size="icon" className="h-7 w-7" onClick={(e) => { e.stopPropagation(); onView(pos); }} title="Details anzeigen">
                    <Eye className="h-3.5 w-3.5 text-gray-500" />
                  </Button>
                  {isEditing && (
                    <>
                      <Button variant="ghost" size="icon" className="h-7 w-7" onClick={(e) => { e.stopPropagation(); onCopy(pos); }} title="Position kopieren" data-testid={`copy-position-${index}`}>
                        <Plus className="h-3.5 w-3.5 text-blue-600" />
                      </Button>
                      <Button variant="ghost" size="icon" className="h-7 w-7" onClick={(e) => { e.stopPropagation(); onEdit(pos); }} title="Bearbeiten">
                        <Pencil className="h-3.5 w-3.5 text-gray-600" />
                      </Button>
                      <Button variant="ghost" size="icon" className="h-7 w-7 text-red-600 hover:bg-red-50" onClick={(e) => { e.stopPropagation(); onDelete(pos.id); }} title="Löschen">
                        <Trash2 className="h-3.5 w-3.5" />
                      </Button>
                    </>
                  )}
                </div>
              </div>
            </div>
          </div>
          
          {/* Doppelklick-Hinweis */}
          <div className="absolute bottom-1 right-2 text-[10px] text-gray-400 opacity-0 group-hover:opacity-100 transition-opacity">
            Doppelklick für Details
          </div>
        </div>
      ))}
    </div>
  );
}


// ========================== PROTOKOLL-TAB KOMPONENTE ==========================

const AKTION_ICONS: Record<string, React.ElementType> = {
  'plus-circle': Plus,
  'pencil': Pencil,
  'refresh-cw': RefreshCw,
  'package-plus': Package,
  'package': Package,
  'package-x': Package,
  'printer': Printer,
  'download': Download,
  'eye': Eye,
  'building': Building2,
  'warehouse': Warehouse,
  'copy': Copy,
  'x-circle': XCircle,
  'check-circle': CheckCircle,
  'lock': Lock,
  'activity': Activity,
};

const AKTION_FARBEN: Record<string, string> = {
  'emerald': 'bg-emerald-100 text-emerald-700 border-emerald-200',
  'blue': 'bg-blue-100 text-blue-700 border-blue-200',
  'amber': 'bg-amber-100 text-amber-700 border-amber-200',
  'green': 'bg-green-100 text-green-700 border-green-200',
  'red': 'bg-red-100 text-red-700 border-red-200',
  'gray': 'bg-gray-100 text-gray-700 border-gray-200',
  'violet': 'bg-violet-100 text-violet-700 border-violet-200',
  'slate': 'bg-slate-100 text-slate-700 border-slate-200',
  'orange': 'bg-orange-100 text-orange-700 border-orange-200',
  'cyan': 'bg-cyan-100 text-cyan-700 border-cyan-200',
  'indigo': 'bg-indigo-100 text-indigo-700 border-indigo-200',
};

interface ProtokollTabProps {
  kontraktId: string;
}

function ProtokollTab({ kontraktId }: ProtokollTabProps) {
  const [expandedId, setExpandedId] = useState<string | null>(null);
  const [filterAktion, setFilterAktion] = useState<string>('__all__');
  
  const { data: auditData, isLoading } = useQuery({
    queryKey: ['kontrakt-audit-log', kontraktId],
    queryFn: async () => {
      const params = new URLSearchParams();
      if (filterAktion && filterAktion !== '__all__') params.append('aktion_filter', filterAktion);
      const response = await api.get(`/kontrakte/${kontraktId}/audit-log?${params.toString()}`);
      return response.data;
    },
    enabled: !!kontraktId,
  });

  const logs: AuditLogEintrag[] = auditData?.data || [];
  const aktionenTypen = auditData?.aktionen_typen || {};

  // Gruppiere nach Datum (in lokaler Zeitzone)
  const gruppierteLogs = useMemo(() => {
    const gruppen: Record<string, AuditLogEintrag[]> = {};
    logs.forEach(log => {
      const datum = formatDateLong(log.zeitstempel);
      if (!gruppen[datum]) gruppen[datum] = [];
      gruppen[datum].push(log);
    });
    return gruppen;
  }, [logs]);

  if (isLoading) {
    return (
      <div className="flex items-center justify-center py-12">
        <div className="animate-spin rounded-full h-8 w-8 border-b-2 border-emerald-600"></div>
      </div>
    );
  }

  return (
    <div className="space-y-4" data-testid="protokoll-tab">
      {/* Header mit Filter */}
      <div className="flex items-center justify-between">
        <h3 className="font-semibold text-gray-900 flex items-center gap-2">
          <History className="h-5 w-5 text-violet-600" />
          Aktivitätsprotokoll
          <Badge variant="secondary">{logs.length} Einträge</Badge>
        </h3>
        
        <Select value={filterAktion} onValueChange={setFilterAktion}>
          <SelectTrigger className="w-[180px] h-8">
            <Filter className="h-3.5 w-3.5 mr-2 text-gray-400" />
            <SelectValue placeholder="Alle Aktionen" />
          </SelectTrigger>
          <SelectContent>
            <SelectItem value="__all__">Alle Aktionen</SelectItem>
            {Object.entries(aktionenTypen).map(([key, meta]: [string, any]) => (
              <SelectItem key={key} value={key}>{meta.label}</SelectItem>
            ))}
          </SelectContent>
        </Select>
      </div>

      {logs.length === 0 ? (
        <div className="text-center py-12 bg-gradient-to-br from-gray-50 to-white rounded-xl border-2 border-dashed">
          <History className="h-16 w-16 mx-auto mb-3 text-gray-300" />
          <p className="text-gray-500 font-medium">Noch keine Aktivitäten</p>
          <p className="text-sm text-gray-400 mt-1">Alle Änderungen werden hier protokolliert</p>
        </div>
      ) : (
        <div className="space-y-6">
          {Object.entries(gruppierteLogs).map(([datum, tagesLogs]) => (
            <div key={datum}>
              {/* Datum-Header */}
              <div className="sticky top-0 bg-gray-50/95 backdrop-blur-sm z-10 py-2 px-3 rounded-lg mb-3">
                <span className="text-sm font-medium text-gray-600">{datum}</span>
              </div>
              
              {/* Timeline */}
              <div className="relative">
                {/* Vertikale Linie */}
                <div className="absolute left-5 top-0 bottom-0 w-0.5 bg-gradient-to-b from-gray-200 via-gray-200 to-transparent"></div>
                
                <div className="space-y-3">
                  {tagesLogs.map((log, index) => {
                    const IconComponent = AKTION_ICONS[log.aktion_meta?.icon] || Activity;
                    const farbe = AKTION_FARBEN[log.aktion_meta?.farbe] || AKTION_FARBEN['gray'];
                    const isExpanded = expandedId === log.id;
                    const hasDetails = (log.aenderungen && log.aenderungen.length > 0) || Object.keys(log.details || {}).length > 0;
                    
                    return (
                      <div 
                        key={log.id} 
                        className={cn(
                          "relative pl-12 group",
                          hasDetails && "cursor-pointer"
                        )}
                        onClick={() => hasDetails && setExpandedId(isExpanded ? null : log.id)}
                      >
                        {/* Icon auf der Timeline */}
                        <div className={cn(
                          "absolute left-2 w-7 h-7 rounded-full flex items-center justify-center border-2 shadow-sm transition-transform",
                          farbe,
                          "group-hover:scale-110"
                        )}>
                          <IconComponent className="h-3.5 w-3.5" />
                        </div>
                        
                        {/* Inhalt */}
                        <div className={cn(
                          "bg-white rounded-xl border shadow-sm p-4 transition-all",
                          hasDetails && "hover:shadow-md hover:border-gray-300",
                          isExpanded && "ring-2 ring-violet-200"
                        )}>
                          <div className="flex items-start justify-between gap-3">
                            <div className="flex-1 min-w-0">
                              {/* Aktion */}
                              <div className="flex items-center gap-2 flex-wrap">
                                <Badge className={cn("text-xs", farbe)}>
                                  {log.aktion_meta?.label || log.aktion}
                                </Badge>
                                {hasDetails && (
                                  <ChevronRight className={cn(
                                    "h-4 w-4 text-gray-400 transition-transform",
                                    isExpanded && "rotate-90"
                                  )} />
                                )}
                              </div>
                              
                              {/* Beschreibung */}
                              <p className="text-sm text-gray-700 mt-1">
                                {log.aktion === 'ERSTELLT' && `Kontrakt ${log.details?.kontraktnummer || ''} erstellt`}
                                {log.aktion === 'BEARBEITET' && `${log.aenderungen?.length || 0} Feld${log.aenderungen?.length !== 1 ? 'er' : ''} geändert`}
                                {log.aktion === 'STATUS_GEAENDERT' && `Status: ${log.details?.alter_status} → ${log.details?.neuer_status}`}
                                {log.aktion === 'POSITION_HINZUGEFUEGT' && `Position ${log.details?.positionsnummer}: ${log.details?.artikel || 'Artikel'} (${log.details?.menge} ${log.details?.einheit || 't'})`}
                                {log.aktion === 'POSITION_BEARBEITET' && `Position ${log.details?.positionsnummer} bearbeitet`}
                                {log.aktion === 'POSITION_GELOESCHT' && `Position ${log.details?.positionsnummer}: ${log.details?.artikel || 'Artikel'} entfernt`}
                                {log.aktion === 'GEDRUCKT' && 'Kontrakt gedruckt'}
                                {log.aktion === 'EXPORTIERT' && 'Kontrakt exportiert'}
                                {log.aktion === 'GEOEFFNET' && 'Kontrakt angesehen'}
                                {log.aktion === 'PARTNER_GEAENDERT' && `Partner: ${log.details?.alter_partner} → ${log.details?.neuer_partner}`}
                                {log.aktion === 'LAGER_GEAENDERT' && 'Lager-Zuordnung geändert'}
                                {log.aktion === 'STORNIERT' && `Storniert${log.details?.storno_grund ? `: ${log.details.storno_grund}` : ''}`}
                                {log.aktion === 'ABGESCHLOSSEN' && 'Kontrakt abgeschlossen'}
                              </p>
                            </div>
                            
                            {/* Zeit & Benutzer */}
                            <div className="text-right shrink-0">
                              <div className="text-xs text-gray-500">{formatRelativeTime(log.zeitstempel)}</div>
                              <div className="flex items-center justify-end gap-1.5 mt-1">
                                <div className="h-5 w-5 rounded-full bg-gradient-to-br from-violet-500 to-purple-600 flex items-center justify-center text-[10px] font-bold text-white">
                                  {log.benutzer_kuerzel?.substring(0, 2) || '??'}
                                </div>
                                <span className="text-xs text-gray-600">{log.benutzer_name}</span>
                              </div>
                            </div>
                          </div>
                          
                          {/* Expandierte Details */}
                          {isExpanded && hasDetails && (
                            <div className="mt-4 pt-4 border-t space-y-3">
                              {/* Änderungen */}
                              {log.aenderungen && log.aenderungen.length > 0 && (
                                <div className="space-y-2">
                                  <div className="text-xs font-medium text-gray-500 uppercase tracking-wide">Änderungen</div>
                                  <div className="grid gap-2">
                                    {log.aenderungen.map((aenderung, i) => (
                                      <div key={i} className="flex items-start gap-3 p-2 bg-gray-50 rounded-lg text-sm">
                                        <div className="font-medium text-gray-600 min-w-[120px]">{aenderung.feld_label || aenderung.feld}</div>
                                        <div className="flex items-center gap-2 flex-1 min-w-0">
                                          <span className="text-red-600 line-through truncate max-w-[200px]" title={String(aenderung.alt || '-')}>
                                            {aenderung.alt !== null && aenderung.alt !== undefined && aenderung.alt !== '' ? String(aenderung.alt) : '-'}
                                          </span>
                                          <ChevronRight className="h-3 w-3 text-gray-400 shrink-0" />
                                          <span className="text-green-600 font-medium truncate max-w-[200px]" title={String(aenderung.neu || '-')}>
                                            {aenderung.neu !== null && aenderung.neu !== undefined && aenderung.neu !== '' ? String(aenderung.neu) : '-'}
                                          </span>
                                        </div>
                                      </div>
                                    ))}
                                  </div>
                                </div>
                              )}
                              
                              {/* Details */}
                              {Object.keys(log.details || {}).length > 0 && (
                                <div className="space-y-2">
                                  <div className="text-xs font-medium text-gray-500 uppercase tracking-wide">Details</div>
                                  <div className="grid grid-cols-2 gap-2 text-sm">
                                    {Object.entries(log.details).filter(([k]) => !['alter_status', 'neuer_status', 'alter_partner', 'neuer_partner', 'storno_grund'].includes(k)).map(([key, value]) => (
                                      <div key={key} className="flex items-center gap-2">
                                        <span className="text-gray-500">{key.replace(/_/g, ' ')}:</span>
                                        <span className="font-medium">{String(value)}</span>
                                      </div>
                                    ))}
                                  </div>
                                </div>
                              )}
                              
                              {/* Zeitstempel */}
                              <div className="text-xs text-gray-400 pt-2">
                                {formatDateTime(log.zeitstempel)}
                              </div>
                            </div>
                          )}
                        </div>
                      </div>
                    );
                  })}
                </div>
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}


// ========================== STRECKEN-KARTE KOMPONENTE (MINIMALISTISCH) ==========================
interface StreckenGruppe {
  strecken_id: string;
  ek_kontrakt: Kontrakt | null;
  vk_kontrakt: Kontrakt | null;
}

function StreckenKarte({ 
  strecke, 
  onOpenKontrakt, 
  onAufloesen 
}: { 
  strecke: StreckenGruppe; 
  onOpenKontrakt: (kontrakt: Kontrakt) => void;
  onAufloesen: (streckenId: string) => void;
}) {
  const [isExpanded, setIsExpanded] = useState(false);
  const { ek_kontrakt, vk_kontrakt, strecken_id } = strecke;
  
  const ekStatus = ek_kontrakt?.status || 'FEHLT';
  const vkStatus = vk_kontrakt?.status || 'FEHLT';
  const totalMenge = (ek_kontrakt?.summen?.summe_menge || 0);
  
  return (
    <div className="border border-gray-200 rounded-lg bg-white overflow-hidden">
      {/* Kompakte Hauptzeile */}
      <div 
        className="flex items-center gap-3 px-3 py-2 hover:bg-gray-50 cursor-pointer"
        onClick={() => setIsExpanded(!isExpanded)}
      >
        <ChevronRight className={cn("h-4 w-4 text-gray-400 transition-transform", isExpanded && "rotate-90")} />
        <ArrowRightLeft className="h-4 w-4 text-orange-500" />
        
        {/* EK Info */}
        <div className="flex items-center gap-1.5 min-w-0">
          <ArrowDownToLine className="h-3.5 w-3.5 text-green-600 shrink-0" />
          <span className="font-mono text-sm text-green-700 truncate">{ek_kontrakt?.kontraktnummer || '-'}</span>
        </div>
        
        <ArrowRight className="h-3 w-3 text-gray-300 shrink-0" />
        
        {/* VK Info */}
        <div className="flex items-center gap-1.5 min-w-0">
          <ArrowUpFromLine className="h-3.5 w-3.5 text-blue-600 shrink-0" />
          <span className="font-mono text-sm text-blue-700 truncate">{vk_kontrakt?.kontraktnummer || '-'}</span>
        </div>
        
        <span className="text-gray-300">|</span>
        
        {/* Route */}
        <span className="text-sm text-gray-600 truncate flex-1">
          {ek_kontrakt?.name1 || '?'} → {vk_kontrakt?.name1 || '?'}
        </span>
        
        {/* Menge */}
        <span className="text-sm font-medium text-gray-700 shrink-0">{totalMenge.toLocaleString('de-DE')} t</span>
        
        {/* Status-Dots */}
        <div className="flex items-center gap-1 shrink-0">
          <div className={cn("h-2 w-2 rounded-full", ekStatus === 'OFFEN' ? 'bg-slate-400' : ekStatus === 'AKTIV' ? 'bg-green-500' : ekStatus === 'ERFUELLT' ? 'bg-emerald-500' : 'bg-gray-300')} title={`EK: ${ekStatus}`} />
          <div className={cn("h-2 w-2 rounded-full", vkStatus === 'OFFEN' ? 'bg-slate-400' : vkStatus === 'AKTIV' ? 'bg-green-500' : vkStatus === 'ERFUELLT' ? 'bg-emerald-500' : 'bg-gray-300')} title={`VK: ${vkStatus}`} />
        </div>
        
        {/* Aktionen */}
        <DropdownMenu>
          <DropdownMenuTrigger asChild onClick={(e) => e.stopPropagation()}>
            <Button variant="ghost" size="icon" className="h-7 w-7 shrink-0">
              <MoreHorizontal className="h-4 w-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            {ek_kontrakt && <DropdownMenuItem onClick={() => onOpenKontrakt(ek_kontrakt)}><ArrowDownToLine className="h-4 w-4 mr-2 text-green-600" />EK öffnen</DropdownMenuItem>}
            {vk_kontrakt && <DropdownMenuItem onClick={() => onOpenKontrakt(vk_kontrakt)}><ArrowUpFromLine className="h-4 w-4 mr-2 text-blue-600" />VK öffnen</DropdownMenuItem>}
            <DropdownMenuSeparator />
            <DropdownMenuItem className="text-orange-600" onClick={() => { if (confirm('Strecke auflösen?')) onAufloesen(strecken_id); }}>
              <Unlink className="h-4 w-4 mr-2" />Auflösen
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      </div>
      
      {/* Expandierter Bereich */}
      <AnimatePresence>
        {isExpanded && (
          <motion.div
            initial={{ height: 0, opacity: 0 }}
            animate={{ height: 'auto', opacity: 1 }}
            exit={{ height: 0, opacity: 0 }}
            transition={{ duration: 0.15 }}
            className="overflow-hidden border-t border-gray-100"
          >
            <div className="grid grid-cols-2 gap-2 p-3 bg-gray-50/50">
              {/* EK Details */}
              <div 
                className="p-2.5 rounded border border-green-200 bg-white hover:bg-green-50 cursor-pointer transition-colors"
                onClick={() => ek_kontrakt && onOpenKontrakt(ek_kontrakt)}
              >
                <div className="flex items-center gap-2 mb-1.5">
                  <ArrowDownToLine className="h-4 w-4 text-green-600" />
                  <span className="font-mono text-sm font-semibold text-green-700">{ek_kontrakt?.kontraktnummer}</span>
                  <Badge variant="outline" className="text-xs ml-auto">{ekStatus}</Badge>
                </div>
                <div className="text-sm font-medium truncate">{ek_kontrakt?.name1}</div>
                <div className="text-xs text-gray-500 truncate">{ek_kontrakt?.ort}</div>
                <div className="flex items-center gap-2 mt-1.5 text-xs">
                  <span>{(ek_kontrakt?.summen?.summe_menge || 0).toLocaleString('de-DE')} t</span>
                  <span className="text-gray-400">•</span>
                  <span>{(ek_kontrakt?.summen?.summe_wert || 0).toLocaleString('de-DE', { style: 'currency', currency: 'EUR' })}</span>
                </div>
              </div>
              
              {/* VK Details */}
              <div 
                className="p-2.5 rounded border border-blue-200 bg-white hover:bg-blue-50 cursor-pointer transition-colors"
                onClick={() => vk_kontrakt && onOpenKontrakt(vk_kontrakt)}
              >
                <div className="flex items-center gap-2 mb-1.5">
                  <ArrowUpFromLine className="h-4 w-4 text-blue-600" />
                  <span className="font-mono text-sm font-semibold text-blue-700">{vk_kontrakt?.kontraktnummer}</span>
                  <Badge variant="outline" className="text-xs ml-auto">{vkStatus}</Badge>
                </div>
                <div className="text-sm font-medium truncate">{vk_kontrakt?.name1}</div>
                <div className="text-xs text-gray-500 truncate">{vk_kontrakt?.ort}</div>
                <div className="flex items-center gap-2 mt-1.5 text-xs">
                  <span>{(vk_kontrakt?.summen?.summe_menge || 0).toLocaleString('de-DE')} t</span>
                  <span className="text-gray-400">•</span>
                  <span>{(vk_kontrakt?.summen?.summe_wert || 0).toLocaleString('de-DE', { style: 'currency', currency: 'EUR' })}</span>
                </div>
              </div>
            </div>
          </motion.div>
        )}
      </AnimatePresence>
    </div>
  );
}


// ========================== MAIN COMPONENT ==========================
interface KontraktePageProps {
  defaultFilter?: 'EK' | 'VK' | 'STRECKE' | '';
  pageTitle?: string;
}

export function KontraktePage({ defaultFilter = '', pageTitle }: KontraktePageProps) {
  const queryClient = useQueryClient();
  const [selectedKontrakt, setSelectedKontrakt] = useState<Kontrakt | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [isNewRecord, setIsNewRecord] = useState(false);
  const [activeSection, setActiveSection] = useState('kopf');
  const [showPositionDialog, setShowPositionDialog] = useState(false);
  const [editingPosition, setEditingPosition] = useState<Position | null>(null);
  const [selectedAdresse, setSelectedAdresse] = useState<AdresseOption | null>(null);
  const [ansprechpartnerListe, setAnsprechpartnerListe] = useState<Ansprechpartner[]>([]);
  // Streckengeschäft-States
  const [showStreckeDialog, setShowStreckeDialog] = useState(false);
  const [showVerknuepfenDialog, setShowVerknuepfenDialog] = useState(false);
  const [streckenLieferant, setStreckenLieferant] = useState<AdresseOption | null>(null);
  const [streckenAbnehmer, setStreckenAbnehmer] = useState<AdresseOption | null>(null);
  
  // Search & Filter States
  const [searchQuery, setSearchQuery] = useState('');
  const [dateRange, setDateRange] = useState<{ from: Date | undefined; to: Date | undefined }>({ from: undefined, to: undefined });
  
  // Fester Filter-Modus basierend auf defaultFilter
  const isFixedFilter = defaultFilter !== '';
  
  const { isDragging, containerRef, startDragging, leftPanelStyle, rightPanelStyle } = useResizablePanel();

  const { register, handleSubmit, reset, setValue, watch, formState: { errors } } = useForm<KontraktForm>({
    resolver: zodResolver(kontraktSchema),
    defaultValues: { 
      vorgang_typ: defaultFilter === 'VK' ? 'VK' : 'EK', 
      waehrung_kurz: 'EUR', 
      waehrungskurs: 1, 
      status: 'OFFEN', 
      aktiv: true, 
      ist_fixierung: false, 
      ist_strecke: defaultFilter === 'STRECKE' 
    }
  });

  const watchFields = watch();

  // Queries
  const { data: kontrakteData, isLoading } = useQuery({
    queryKey: ['kontrakte', defaultFilter],
    queryFn: async () => { const response = await api.get('/kontrakte'); return response.data; }
  });

  const { data: benutzerData } = useQuery({
    queryKey: ['benutzer-lookup'],
    queryFn: async () => { const response = await api.get('/kontrakte/lookup/benutzer'); return response.data.data as Benutzer[]; }
  });

  const { data: mandantData } = useQuery({
    queryKey: ['mandant-lookup'],
    queryFn: async () => { const response = await api.get('/kontrakte/lookup/mandant'); return response.data.data as MandantData | null; }
  });

  // Wildcard-Suche Funktion
  const matchesSearch = (kontrakt: Kontrakt, query: string): boolean => {
    if (!query.trim()) return true;
    const q = query.toLowerCase();
    const searchFields = [
      kontrakt.kontraktnummer,
      kontrakt.name1,
      kontrakt.name2,
      kontrakt.ort,
      kontrakt.plz,
      kontrakt.sachbearbeiter_name,
      kontrakt.status,
      // Positionen durchsuchen
      ...(kontrakt.positionen || []).flatMap(p => [p.artbez1]),
    ].filter(Boolean).map(s => s?.toLowerCase());
    
    // Wildcard-Support: * oder % als Platzhalter
    if (q.includes('*') || q.includes('%')) {
      const regex = new RegExp(q.replace(/\*/g, '.*').replace(/%/g, '.*'), 'i');
      return searchFields.some(f => f && regex.test(f));
    }
    return searchFields.some(f => f && f.includes(q));
  };

  // Datums-Filter Funktion
  const matchesDateRange = (kontrakt: Kontrakt): boolean => {
    if (!dateRange.from && !dateRange.to) return true;
    const kontraktDatum = kontrakt.erstellungsdatum ? new Date(kontrakt.erstellungsdatum) : null;
    if (!kontraktDatum) return false;
    if (dateRange.from && kontraktDatum < dateRange.from) return false;
    if (dateRange.to && kontraktDatum > dateRange.to) return false;
    return true;
  };

  // Aktive Filter zählen
  const activeFilterCount = [
    searchQuery.trim() ? 1 : 0,
    dateRange.from || dateRange.to ? 1 : 0,
  ].reduce((a, b) => a + b, 0);

  // Filter zurücksetzen
  const clearAllFilters = () => {
    setSearchQuery('');
    setDateRange({ from: undefined, to: undefined });
  };

  // Gruppierte Streckengeschäfte und normale Kontrakte
  const { streckenGruppen, normaleKontrakte } = useMemo(() => {
    if (!kontrakteData?.data) return { streckenGruppen: [], normaleKontrakte: [] };
    
    const streckenMap = new Map<string, StreckenGruppe>();
    const normale: Kontrakt[] = [];
    
    // Alle Kontrakte durchgehen
    (kontrakteData.data as Kontrakt[]).forEach(k => {
      // Zuerst: Search und Datums-Filter anwenden
      if (!matchesSearch(k, searchQuery)) return;
      if (!matchesDateRange(k)) return;
      
      // Bei EK/VK-Filter: NUR Einzelkontrakte (keine Strecken)
      if (defaultFilter === 'EK') {
        if (k.vorgang_typ === 'EK' && !k.ist_strecke) {
          normale.push(k);
        }
        return;
      }
      if (defaultFilter === 'VK') {
        if (k.vorgang_typ === 'VK' && !k.ist_strecke) {
          normale.push(k);
        }
        return;
      }
      
      // Bei STRECKE-Filter: Nur Strecken gruppieren
      if (defaultFilter === 'STRECKE') {
        if (k.ist_strecke && k.strecken_id) {
          if (!streckenMap.has(k.strecken_id)) {
            streckenMap.set(k.strecken_id, { strecken_id: k.strecken_id, ek_kontrakt: null, vk_kontrakt: null });
          }
          const gruppe = streckenMap.get(k.strecken_id)!;
          if (k.vorgang_typ === 'EK') gruppe.ek_kontrakt = k;
          else if (k.vorgang_typ === 'VK') gruppe.vk_kontrakt = k;
        }
        return;
      }
      
      // Kein Filter: Alles anzeigen (Strecken gruppiert, Rest als Einzelkontrakte)
      if (k.ist_strecke && k.strecken_id) {
        if (!streckenMap.has(k.strecken_id)) {
          streckenMap.set(k.strecken_id, { strecken_id: k.strecken_id, ek_kontrakt: null, vk_kontrakt: null });
        }
        const gruppe = streckenMap.get(k.strecken_id)!;
        if (k.vorgang_typ === 'EK') gruppe.ek_kontrakt = k;
        else if (k.vorgang_typ === 'VK') gruppe.vk_kontrakt = k;
      } else {
        normale.push(k);
      }
    });
    
    return { 
      streckenGruppen: Array.from(streckenMap.values()), 
      normaleKontrakte: normale 
    };
  }, [kontrakteData, defaultFilter, searchQuery, dateRange, matchesSearch, matchesDateRange]);

  // Mutations
  const createMutation = useMutation({
    mutationFn: (data: any) => api.post('/kontrakte', data),
    onSuccess: (response) => {
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
      toast.success('Kontrakt erfolgreich erstellt');
      if (response.data?.data) {
        setSelectedKontrakt(response.data.data);
        // Auch Audit-Log für den neuen Kontrakt invalidieren
        queryClient.invalidateQueries({ queryKey: ['kontrakt-audit-log', response.data.data.id] });
        Object.entries(response.data.data).forEach(([key, value]) => {
          if (key in kontraktSchema.shape) setValue(key as keyof KontraktForm, value as any);
        });
      }
      setIsNewRecord(false);
      setIsEditing(false);
    },
    onError: (error: any) => {
      const detail = error.response?.data?.detail;
      if (detail?.errors) detail.errors.forEach((e: string) => toast.error(e));
      else toast.error(typeof detail === 'string' ? detail : 'Fehler beim Erstellen');
    }
  });

  const updateMutation = useMutation({
    mutationFn: ({ id, data }: { id: string; data: any }) => api.put(`/kontrakte/${id}`, data),
    onSuccess: (_, variables) => {
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
      // Auch Audit-Log invalidieren für sofortige Aktualisierung
      queryClient.invalidateQueries({ queryKey: ['kontrakt-audit-log', variables.id] });
      toast.success('Kontrakt erfolgreich aktualisiert');
      setIsEditing(false);
    },
    onError: () => toast.error('Fehler beim Aktualisieren')
  });

  const deleteMutation = useMutation({
    mutationFn: (id: string) => api.delete(`/kontrakte/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
      toast.success('Kontrakt erfolgreich gelöscht');
      setSelectedKontrakt(null);
    }
  });

  // Streckengeschäft Mutations
  const createStreckeMutation = useMutation({
    mutationFn: (data: any) => api.post('/kontrakte/strecken', data),
    onSuccess: (response) => {
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
      toast.success('Streckengeschäft erfolgreich erstellt');
      setShowStreckeDialog(false);
      setStreckenLieferant(null);
      setStreckenAbnehmer(null);
      // EK-Kontrakt öffnen
      if (response.data?.data?.ek_kontrakt) {
        openDetail(response.data.data.ek_kontrakt);
      }
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Erstellen des Streckengeschäfts');
    }
  });

  const verknuepfenMutation = useMutation({
    mutationFn: ({ kontraktId, partnerKontraktId }: { kontraktId: string; partnerKontraktId?: string }) => 
      api.post(`/kontrakte/${kontraktId}/strecke/verknuepfen`, { kontrakt_id: kontraktId, partner_kontrakt_id: partnerKontraktId }),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
      toast.success('Kontrakt erfolgreich zu Streckengeschäft verknüpft');
      setShowVerknuepfenDialog(false);
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Verknüpfen');
    }
  });

  const streckeAufloesenMutation = useMutation({
    mutationFn: (streckenId: string) => api.delete(`/kontrakte/strecken/${streckenId}/aufloesen`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
      toast.success('Streckengeschäft aufgelöst');
    }
  });

  // Handlers
  const onSubmit = async (data: KontraktForm) => {
    const submitData = { ...data, positionen: selectedKontrakt?.positionen || [] };
    if (selectedKontrakt && !isNewRecord) updateMutation.mutate({ id: selectedKontrakt.id, data: submitData });
    else createMutation.mutate(submitData);
  };

  const handleSave = () => {
    handleSubmit(onSubmit, (validationErrors) => {
      Object.values(validationErrors).forEach((err: any) => { if (err?.message) toast.error(err.message); });
    })();
  };

  const handleNewKontrakt = () => {
    const today = new Date().toISOString().split('T')[0];
    const vorgangTyp = defaultFilter === 'VK' ? 'VK' : 'EK';
    const emptyKontrakt: Kontrakt = { 
      id: 'NEU', 
      vorgang_typ: vorgangTyp, 
      name1: '', 
      waehrung_kurz: 'EUR', 
      waehrungskurs: 1, 
      status: 'OFFEN', 
      aktiv: true, 
      erstellungsdatum: today, 
      ist_fixierung: false, 
      ist_strecke: false, 
      positionen: [], 
      abgeschlossen: false, 
      kopie_bemerkung_auf_pos: false 
    };
    setSelectedKontrakt(emptyKontrakt);
    setSelectedAdresse(null);
    reset(emptyKontrakt);
    setIsNewRecord(true);
    setIsEditing(true);
    setActiveSection('kopf');
  };

  const handleNewStrecke = () => {
    setStreckenLieferant(null);
    setStreckenAbnehmer(null);
    setShowStreckeDialog(true);
  };

  const handleCreateStrecke = () => {
    if (!streckenLieferant || !streckenAbnehmer) {
      toast.error('Bitte wählen Sie Lieferant und Abnehmer aus');
      return;
    }
    const today = new Date().toISOString().split('T')[0];
    createStreckeMutation.mutate({
      lieferant_id_adresse: streckenLieferant.id,
      lieferant_name1: streckenLieferant.name1,
      lieferant_name2: streckenLieferant.name2,
      lieferant_strasse: streckenLieferant.strasse,
      lieferant_hausnummer: streckenLieferant.hausnummer,
      lieferant_plz: streckenLieferant.plz,
      lieferant_ort: streckenLieferant.ort,
      lieferant_land: streckenLieferant.land,
      lieferant_land_code: streckenLieferant.land_code,
      abnehmer_id_adresse: streckenAbnehmer.id,
      abnehmer_name1: streckenAbnehmer.name1,
      abnehmer_name2: streckenAbnehmer.name2,
      abnehmer_strasse: streckenAbnehmer.strasse,
      abnehmer_hausnummer: streckenAbnehmer.hausnummer,
      abnehmer_plz: streckenAbnehmer.plz,
      abnehmer_ort: streckenAbnehmer.ort,
      abnehmer_land: streckenAbnehmer.land,
      abnehmer_land_code: streckenAbnehmer.land_code,
      erstellungsdatum: today,
      waehrung_kurz: 'EUR',
      waehrungskurs: 1,
    });
  };

  const handleClose = () => { setSelectedKontrakt(null); setIsEditing(false); setIsNewRecord(false); setSelectedAdresse(null); reset(); };

  const handleCancel = () => {
    if (isNewRecord) handleClose();
    else {
      setIsEditing(false);
      if (selectedKontrakt) Object.entries(selectedKontrakt).forEach(([key, value]) => { if (key in kontraktSchema.shape) setValue(key as keyof KontraktForm, value as any); });
    }
  };

  const openDetail = async (kontrakt: Kontrakt) => {
    setSelectedKontrakt(kontrakt);
    setIsNewRecord(false);
    setIsEditing(false);
    setActiveSection('kopf');
    Object.entries(kontrakt).forEach(([key, value]) => { if (key in kontraktSchema.shape) setValue(key as keyof KontraktForm, value as any); });
    
    // Wenn eine Adresse verknüpft ist, lade die Adressdaten für USt-ID/Bankverbindung-Dropdowns
    if (kontrakt.id_adresse) {
      try {
        // Lade die Adresse direkt über die API
        const response = await api.get('/kontrakte/lookup/adressen', { params: { suche: '', limit: 100 } });
        const adressen = response.data.data as AdresseOption[];
        const adresse = adressen.find(a => a.id === kontrakt.id_adresse);
        if (adresse) {
          setSelectedAdresse(adresse);
          setAnsprechpartnerListe(adresse.ansprechpartner || []);
        } else {
          setSelectedAdresse(null);
          setAnsprechpartnerListe([]);
        }
      } catch (error) {
        console.error('Fehler beim Laden der Adresse:', error);
        setSelectedAdresse(null);
        setAnsprechpartnerListe([]);
      }
    } else {
      setSelectedAdresse(null);
      setAnsprechpartnerListe([]);
    }
  };

  // Adresse auswählen Handler
  const handleAdresseSelect = (adresseId: string, adresse?: AdresseOption) => {
    setValue('id_adresse', adresseId);
    if (adresse) {
      setSelectedAdresse(adresse);
      setValue('name1', adresse.name1 || '');
      setValue('name2', adresse.name2 || '');
      setValue('strasse', adresse.strasse || '');
      setValue('hausnummer', adresse.hausnummer || '');
      setValue('plz', adresse.plz || '');
      setValue('ort', adresse.ort || '');
      setValue('land', adresse.land || '');
      setValue('telefon', adresse.telefon || '');
      setValue('telefax', adresse.telefax || '');
      setValue('email', adresse.email || '');
      
      // Haupt-USt-ID setzen
      const hauptUstId = adresse.ust_ids?.find(u => u.ist_hauptid) || adresse.ust_ids?.[0];
      setValue('ust_id', hauptUstId?.ust_id || adresse.ust_id || '');
      
      // Haupt-Bankverbindung setzen
      const hauptBank = adresse.bankverbindungen?.find(b => b.ist_hauptkonto) || adresse.bankverbindungen?.[0];
      if (hauptBank) {
        setValue('id_bankverbindung', hauptBank.id);
        setValue('bank_iban', hauptBank.iban || '');
        setValue('bank_bic', hauptBank.bic || '');
        setValue('bank_name', hauptBank.bank_name || '');
        setValue('bank_waehrung', hauptBank.waehrung || 'EUR');
      } else {
        setValue('id_bankverbindung', null);
        setValue('bank_iban', '');
        setValue('bank_bic', '');
        setValue('bank_name', '');
        setValue('bank_waehrung', '');
      }
      
      // Ansprechpartner laden
      setAnsprechpartnerListe(adresse.ansprechpartner || []);
      
      // Reset Ansprechpartner Auswahl
      setValue('id_ansprechpartner', '');
      setValue('ansprechpartner_name', '');
      setValue('ansprechpartner_telefon', '');
      setValue('ansprechpartner_email', '');
    }
  };

  // Sachbearbeiter auswählen
  const handleSachbearbeiterSelect = (id: string, benutzer?: Benutzer) => {
    setValue('id_sachbearbeiter', id);
    if (benutzer) {
      setValue('sachbearbeiter_name', benutzer.name);
      setValue('sachbearbeiter_telefon', benutzer.telefon || '');
      setValue('sachbearbeiter_email', benutzer.email || '');
    }
  };

  // Händler auswählen
  const handleHaendlerSelect = (id: string, benutzer?: Benutzer) => {
    setValue('id_haendler', id);
    if (benutzer) setValue('haendler_name', benutzer.name);
  };

  // Ansprechpartner auswählen
  const handleAnsprechpartnerSelect = (ap: Ansprechpartner) => {
    setValue('id_ansprechpartner', ap.id);
    setValue('ansprechpartner_name', `${ap.vorname || ''} ${ap.nachname || ''}`.trim());
    setValue('ansprechpartner_telefon', ap.telefon || '');
    setValue('ansprechpartner_email', ap.email || '');
  };

  // Position handlers
  const [viewPosition, setViewPosition] = useState<Position | null>(null);
  const [showViewDialog, setShowViewDialog] = useState(false);
  
  const handleAddPosition = () => { setEditingPosition(null); setShowPositionDialog(true); };
  const handleEditPosition = (pos: Position) => { setEditingPosition(pos); setShowPositionDialog(true); };
  const handleViewPosition = (pos: Position) => { setViewPosition(pos); setShowViewDialog(true); };
  const handleCopyPosition = (pos: Position) => {
    if (!selectedKontrakt) return;
    const newPositionen = [...(selectedKontrakt.positionen || [])];
    const maxNr = Math.max(...newPositionen.map(p => p.positionsnummer || 0), 0);
    const copiedPos: Position = {
      ...pos,
      id: `temp-${Date.now()}`,
      positionsnummer: maxNr + 1,
      position_abgeschlossen: false,
      bemerkung: pos.bemerkung ? `(Kopie) ${pos.bemerkung}` : '(Kopie)'
    };
    newPositionen.push(copiedPos);
    setSelectedKontrakt({ ...selectedKontrakt, positionen: newPositionen });
    toast.success('Position kopiert');
  };
  const handleSavePosition = (posData: Partial<Position>) => {
    if (!selectedKontrakt) return;
    let newPositionen = [...(selectedKontrakt.positionen || [])];
    if (editingPosition) newPositionen = newPositionen.map(p => p.id === editingPosition.id ? { ...p, ...posData } : p);
    else {
      const maxNr = Math.max(...newPositionen.map(p => p.positionsnummer || 0), 0);
      newPositionen.push({ id: `temp-${Date.now()}`, positionsnummer: maxNr + 1, position_typ: 'ARTIKEL', einheitkurz: 't', waehrungskurs: 1, mengen_toleranz_prozent: 10, ueberliefer_ok: true, position_abgeschlossen: false, ...posData } as Position);
    }
    setSelectedKontrakt({ ...selectedKontrakt, positionen: newPositionen });
  };
  const handleDeletePosition = (posId: string) => {
    if (!selectedKontrakt || !confirm('Position wirklich löschen?')) return;
    setSelectedKontrakt({ ...selectedKontrakt, positionen: (selectedKontrakt.positionen || []).filter(p => p.id !== posId) });
  };

  // Table columns
  const columns: ColumnDef<Kontrakt>[] = useMemo(() => [
    { accessorKey: 'kontraktnummer', header: 'Kontrakt-Nr.', cell: ({ row }) => (
      <div className="flex items-center gap-2">
        <span className="font-mono text-sm font-semibold">{row.original.kontraktnummer || '-'}</span>
        {row.original.ist_fixierung && <Badge variant="outline" className="text-xs bg-purple-50 text-purple-700 border-purple-200"><TrendingUp className="h-3 w-3 mr-1" />FIX</Badge>}
        {row.original.ist_strecke && <Badge variant="outline" className="text-xs bg-orange-50 text-orange-700 border-orange-200"><ArrowRightLeft className="h-3 w-3 mr-1" />STRECKE</Badge>}
      </div>
    )},
    { accessorKey: 'vorgang_typ', header: 'Typ', cell: ({ row }) => (
      <div className="flex items-center gap-1">
        {row.original.vorgang_typ === 'EK' ? (<><ArrowDownToLine className="h-4 w-4 text-green-600" /><span className="text-sm font-medium text-green-700">EK</span></>) : (<><ArrowUpFromLine className="h-4 w-4 text-blue-600" /><span className="text-sm font-medium text-blue-700">VK</span></>)}
      </div>
    )},
    { accessorKey: 'name1', header: 'Vertragspartner', cell: ({ row }) => (
      <div>
        <div className="font-medium">{row.original.name1}</div>
        <div className="text-xs text-gray-500">{[row.original.plz, row.original.ort].filter(Boolean).join(' ')}</div>
        {/* Strecken-Partner anzeigen */}
        {row.original.ist_strecke && row.original.strecken_partner && (
          <div className="flex items-center gap-1 mt-1">
            <ArrowRight className="h-3 w-3 text-gray-400" />
            <span className="text-xs text-orange-600">
              {row.original.vorgang_typ === 'EK' 
                ? row.original.strecken_partner.vk?.name1 
                : row.original.strecken_partner.ek?.name1}
            </span>
          </div>
        )}
      </div>
    )},
    { accessorKey: 'sachbearbeiter_name', header: 'Sachbearbeiter', cell: ({ row }) => <span className="text-sm">{row.original.sachbearbeiter_name || '-'}</span> },
    { accessorKey: 'gueltig_bis', header: 'Gültig bis', cell: ({ row }) => <span className="text-sm">{row.original.gueltig_bis ? new Date(row.original.gueltig_bis).toLocaleDateString('de-DE') : '-'}</span> },
    { accessorKey: 'waehrung_kurz', header: 'Währung', cell: ({ row }) => { const w = getWaehrung(row.original.waehrung_kurz); return <Badge variant="secondary" className="font-mono">{w.symbol} {w.code}</Badge>; }},
    { accessorKey: 'status', header: 'Status', cell: ({ row }) => { const status = row.original.status || 'OFFEN'; const colors = statusColors[status] || statusColors.OFFEN; const Icon = colors.icon; return <Badge className={cn(colors.bg, colors.text, "gap-1")}><Icon className="h-3 w-3" />{status}</Badge>; }},
    { id: 'actions', cell: ({ row }) => (
      <DropdownMenu><DropdownMenuTrigger asChild><Button variant="ghost" size="icon" className="h-8 w-8"><MoreHorizontal className="h-4 w-4" /></Button></DropdownMenuTrigger>
        <DropdownMenuContent align="end">
          <DropdownMenuItem onClick={() => openDetail(row.original)}><Eye className="h-4 w-4 mr-2" />Anzeigen</DropdownMenuItem>
          <DropdownMenuItem onClick={() => { openDetail(row.original); setIsEditing(true); }}><Pencil className="h-4 w-4 mr-2" />Bearbeiten</DropdownMenuItem>
          <DropdownMenuSeparator />
          {/* Streckengeschäft-Optionen */}
          {!row.original.ist_strecke && (
            <DropdownMenuItem onClick={() => { setSelectedKontrakt(row.original); setShowVerknuepfenDialog(true); }}>
              <Link2 className="h-4 w-4 mr-2" />Zu Strecke verknüpfen
            </DropdownMenuItem>
          )}
          {row.original.ist_strecke && row.original.strecken_id && (
            <>
              {/* Partner-Kontrakt öffnen */}
              {row.original.strecken_partner && (
                <DropdownMenuItem onClick={() => {
                  const partnerId = row.original.vorgang_typ === 'EK' 
                    ? row.original.strecken_partner?.vk?.id 
                    : row.original.strecken_partner?.ek?.id;
                  if (partnerId) {
                    const partner = kontrakteData?.data?.find((k: Kontrakt) => k.id === partnerId);
                    if (partner) openDetail(partner);
                  }
                }}>
                  <ArrowRightLeft className="h-4 w-4 mr-2" />
                  {row.original.vorgang_typ === 'EK' ? 'VK-Kontrakt öffnen' : 'EK-Kontrakt öffnen'}
                </DropdownMenuItem>
              )}
              <DropdownMenuItem 
                className="text-orange-600"
                onClick={() => { 
                  if (confirm('Streckengeschäft wirklich auflösen? Die Kontrakte bleiben als einzelne EK/VK bestehen.')) {
                    streckeAufloesenMutation.mutate(row.original.strecken_id!);
                  }
                }}
              >
                <Unlink className="h-4 w-4 mr-2" />Strecke auflösen
              </DropdownMenuItem>
            </>
          )}
          <DropdownMenuSeparator />
          <DropdownMenuItem className="text-red-600" onClick={() => { if (confirm('Kontrakt wirklich löschen?')) deleteMutation.mutate(row.original.id); }}><Trash2 className="h-4 w-4 mr-2" />Löschen</DropdownMenuItem>
        </DropdownMenuContent>
      </DropdownMenu>
    )}
  ], [deleteMutation, streckeAufloesenMutation, kontrakteData, openDetail]);

  const selectedWaehrung = getWaehrung(watchFields.waehrung_kurz);
  
  // Titel und Beschreibung basierend auf Filter
  const getTitleAndDescription = () => {
    if (pageTitle) {
      if (defaultFilter === 'EK') return { title: pageTitle, desc: 'Einkaufskontrakte verwalten' };
      if (defaultFilter === 'VK') return { title: pageTitle, desc: 'Verkaufskontrakte verwalten' };
      if (defaultFilter === 'STRECKE') return { title: pageTitle, desc: 'Streckengeschäfte verwalten' };
    }
    return { title: 'Kontrakte', desc: 'Einkaufs-, Verkaufs- und Streckenkontrakte verwalten' };
  };
  const { title, desc } = getTitleAndDescription();

  // Ergebnis-Count
  const totalResults = defaultFilter === 'STRECKE' ? streckenGruppen.length : normaleKontrakte.length + streckenGruppen.length;

  return (
    <div className="h-full flex flex-col">
      {/* Header */}
      <div className="bg-white border-b border-gray-200 px-6 py-4">
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-3">
            {defaultFilter === 'EK' && <div className="h-10 w-10 rounded-lg bg-green-100 flex items-center justify-center"><ArrowDownToLine className="h-5 w-5 text-green-600" /></div>}
            {defaultFilter === 'VK' && <div className="h-10 w-10 rounded-lg bg-blue-100 flex items-center justify-center"><ArrowUpFromLine className="h-5 w-5 text-blue-600" /></div>}
            {defaultFilter === 'STRECKE' && <div className="h-10 w-10 rounded-lg bg-orange-100 flex items-center justify-center"><ArrowRightLeft className="h-5 w-5 text-orange-600" /></div>}
            <div>
              <h1 className="text-xl font-semibold text-gray-900">{title}</h1>
              <p className="text-sm text-gray-500 mt-0.5">{desc}</p>
            </div>
          </div>
          <div className="flex items-center gap-3">
            {/* Neu-Button anpassen basierend auf defaultFilter */}
            {defaultFilter === 'STRECKE' ? (
              <Button onClick={handleNewStrecke} className="bg-orange-600 hover:bg-orange-700" data-testid="new-strecke-btn">
                <Plus className="h-4 w-4 mr-2" />Neues Streckengeschäft
              </Button>
            ) : defaultFilter ? (
              <Button onClick={handleNewKontrakt} className={defaultFilter === 'EK' ? "bg-green-600 hover:bg-green-700" : "bg-blue-600 hover:bg-blue-700"} data-testid="new-kontrakt-btn">
                <Plus className="h-4 w-4 mr-2" />Neuer {defaultFilter === 'EK' ? 'EK' : 'VK'}-Kontrakt
              </Button>
            ) : (
              <DropdownMenu>
              <DropdownMenuTrigger asChild>
                <Button className="bg-emerald-600 hover:bg-emerald-700" data-testid="new-kontrakt-btn">
                  <Plus className="h-4 w-4 mr-2" />Neu
                  <ChevronDown className="h-4 w-4 ml-2" />
                </Button>
              </DropdownMenuTrigger>
              <DropdownMenuContent align="end" className="w-48">
                <DropdownMenuItem onClick={handleNewKontrakt}>
                  <FileText className="h-4 w-4 mr-2" />Einzelkontrakt
                </DropdownMenuItem>
                <DropdownMenuItem onClick={handleNewStrecke}>
                  <ArrowRightLeft className="h-4 w-4 mr-2 text-orange-600" />
                  <span className="text-orange-700 font-medium">Streckengeschäft</span>
                </DropdownMenuItem>
              </DropdownMenuContent>
            </DropdownMenu>
            )}
          </div>
        </div>
      </div>

      {/* Filter- und Searchbar */}
      <div className="bg-gray-50/80 backdrop-blur-sm border-b border-gray-200 px-6 py-3">
        {/* Filterbar */}
        <div className="flex items-center gap-3 mb-3">
          <div className="flex items-center gap-2">
            <SlidersHorizontal className="h-4 w-4 text-gray-500" />
            <span className="text-sm font-medium text-gray-600">Filter</span>
            {activeFilterCount > 0 && (
              <Badge className="bg-blue-100 text-blue-700 text-xs">{activeFilterCount} aktiv</Badge>
            )}
          </div>
          
          {/* Datums-Range Filter */}
          <Popover>
            <PopoverTrigger asChild>
              <Button 
                variant="outline" 
                size="sm"
                className={cn(
                  "h-8 gap-2 text-sm font-normal",
                  (dateRange.from || dateRange.to) && "bg-blue-50 border-blue-200 text-blue-700"
                )}
              >
                <CalendarRange className="h-4 w-4" />
                {dateRange.from ? (
                  dateRange.to ? (
                    <>
                      {format(dateRange.from, "dd.MM.yy", { locale: de })} - {format(dateRange.to, "dd.MM.yy", { locale: de })}
                    </>
                  ) : (
                    <>ab {format(dateRange.from, "dd.MM.yyyy", { locale: de })}</>
                  )
                ) : (
                  "Kontraktdatum"
                )}
                {(dateRange.from || dateRange.to) && (
                  <X 
                    className="h-3 w-3 ml-1 hover:text-red-500" 
                    onClick={(e) => { e.stopPropagation(); setDateRange({ from: undefined, to: undefined }); }}
                  />
                )}
              </Button>
            </PopoverTrigger>
            <PopoverContent className="w-auto p-0" align="start">
              <div className="p-3 border-b">
                <div className="text-sm font-medium">Kontraktdatum wählen</div>
                <div className="text-xs text-gray-500">Wählen Sie einen Datumsbereich</div>
              </div>
              <div className="flex">
                <div className="border-r">
                  <div className="px-3 py-2 text-xs font-medium text-gray-500 bg-gray-50">Von</div>
                  <Calendar
                    mode="single"
                    selected={dateRange.from}
                    onSelect={(date) => setDateRange(prev => ({ ...prev, from: date }))}
                    initialFocus
                  />
                </div>
                <div>
                  <div className="px-3 py-2 text-xs font-medium text-gray-500 bg-gray-50">Bis</div>
                  <Calendar
                    mode="single"
                    selected={dateRange.to}
                    onSelect={(date) => setDateRange(prev => ({ ...prev, to: date }))}
                    disabled={(date) => dateRange.from ? date < dateRange.from : false}
                  />
                </div>
              </div>
              <div className="p-2 border-t flex justify-between">
                <Button 
                  variant="ghost" 
                  size="sm"
                  onClick={() => setDateRange({ from: undefined, to: undefined })}
                >
                  Zurücksetzen
                </Button>
                <div className="flex gap-1">
                  <Button 
                    variant="outline" 
                    size="sm"
                    onClick={() => {
                      const today = new Date();
                      const thirtyDaysAgo = new Date(today.getTime() - 30 * 24 * 60 * 60 * 1000);
                      setDateRange({ from: thirtyDaysAgo, to: today });
                    }}
                  >
                    30 Tage
                  </Button>
                  <Button 
                    variant="outline" 
                    size="sm"
                    onClick={() => {
                      const today = new Date();
                      const startOfYear = new Date(today.getFullYear(), 0, 1);
                      setDateRange({ from: startOfYear, to: today });
                    }}
                  >
                    Dieses Jahr
                  </Button>
                </div>
              </div>
            </PopoverContent>
          </Popover>

          {/* Alle Filter löschen */}
          {activeFilterCount > 0 && (
            <Button variant="ghost" size="sm" onClick={clearAllFilters} className="h-8 text-gray-500 hover:text-red-600">
              <XCircle className="h-4 w-4 mr-1" />
              Filter löschen
            </Button>
          )}
          
          <div className="flex-1" />
          
          {/* Ergebniszähler */}
          <span className="text-sm text-gray-500">
            {totalResults} {totalResults === 1 ? 'Ergebnis' : 'Ergebnisse'}
          </span>
        </div>

        {/* Searchbar */}
        <div className="relative">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-5 w-5 text-gray-400" />
          <Input
            value={searchQuery}
            onChange={(e) => setSearchQuery(e.target.value)}
            placeholder={
              defaultFilter === 'STRECKE' 
                ? "Suche nach Kontraktnummer, Partner, Artikel... (Wildcard: * oder %)"
                : `Suche nach Kontraktnummer, Vertragspartner, Artikel, Ort... (Wildcard: * oder %)`
            }
            className="pl-10 pr-10 h-11 text-base bg-white border-gray-300 focus:border-blue-500 focus:ring-blue-500 shadow-sm"
            data-testid="search-input"
          />
          {searchQuery && (
            <Button
              variant="ghost"
              size="icon"
              className="absolute right-2 top-1/2 -translate-y-1/2 h-7 w-7 text-gray-400 hover:text-gray-600"
              onClick={() => setSearchQuery('')}
            >
              <X className="h-4 w-4" />
            </Button>
          )}
        </div>
      </div>

      {/* Content */}
      <div ref={containerRef} className="flex-1 flex overflow-hidden">
        <div className="p-6 overflow-auto transition-none" style={selectedKontrakt ? leftPanelStyle : { width: '100%' }}>
          {/* Streckengeschäfte - nur bei STRECKE-Filter oder ohne Filter */}
          {streckenGruppen.length > 0 && (defaultFilter === 'STRECKE' || !isFixedFilter) && (
            <div className={defaultFilter === 'STRECKE' ? "" : "mb-4"}>
              {defaultFilter !== 'STRECKE' && (
                <div className="flex items-center gap-2 mb-2">
                  <ArrowRightLeft className="h-4 w-4 text-orange-500" />
                  <span className="text-sm font-medium text-gray-700">Streckengeschäfte</span>
                  <Badge variant="outline" className="text-xs bg-orange-50 text-orange-600 border-orange-200">{streckenGruppen.length}</Badge>
                </div>
              )}
              <div className="space-y-1">
                {streckenGruppen.map((strecke) => (
                  <StreckenKarte 
                    key={strecke.strecken_id} 
                    strecke={strecke} 
                    onOpenKontrakt={openDetail}
                    onAufloesen={(id) => streckeAufloesenMutation.mutate(id)}
                  />
                ))}
              </div>
            </div>
          )}
          
          {/* Normale Kontrakte als Tabelle - bei EK/VK-Filter oder ohne Filter */}
          {normaleKontrakte.length > 0 && defaultFilter !== 'STRECKE' && (
            <div>
              {streckenGruppen.length > 0 && !isFixedFilter && (
                <div className="flex items-center gap-2 mb-2">
                  <FileText className="h-4 w-4 text-gray-500" />
                  <span className="text-sm font-medium text-gray-700">Einzelkontrakte</span>
                  <Badge variant="outline" className="text-xs">{normaleKontrakte.length}</Badge>
                </div>
              )}
              <div className="bg-white rounded-lg shadow-sm border">
                <DataTable columns={columns} data={normaleKontrakte} searchKey="name1" searchPlaceholder="Vertragspartner suchen..." onRowDoubleClick={openDetail} />
              </div>
            </div>
          )}
          
          {/* Keine Daten */}
          {streckenGruppen.length === 0 && normaleKontrakte.length === 0 && !isLoading && (
            <div className="bg-white rounded-lg shadow-sm border p-12 text-center">
              <div className={cn(
                "h-16 w-16 rounded-full flex items-center justify-center mx-auto mb-4",
                filterTyp === 'EK' ? "bg-green-100" : filterTyp === 'VK' ? "bg-blue-100" : filterTyp === 'STRECKE' ? "bg-orange-100" : "bg-gray-100"
              )}>
                {filterTyp === 'EK' ? <ArrowDownToLine className="h-8 w-8 text-green-500" /> :
                 filterTyp === 'VK' ? <ArrowUpFromLine className="h-8 w-8 text-blue-500" /> :
                 filterTyp === 'STRECKE' ? <ArrowRightLeft className="h-8 w-8 text-orange-500" /> :
                 <FileText className="h-8 w-8 text-gray-400" />}
              </div>
              <h3 className="font-medium text-gray-900 mb-1">
                {filterTyp === 'EK' ? 'Keine Einkaufskontrakte' : 
                 filterTyp === 'VK' ? 'Keine Verkaufskontrakte' : 
                 filterTyp === 'STRECKE' ? 'Keine Streckengeschäfte' : 
                 'Keine Kontrakte gefunden'}
              </h3>
              <p className="text-sm text-gray-500">
                {filterTyp === 'STRECKE' 
                  ? 'Erstellen Sie ein neues Streckengeschäft.'
                  : `Erstellen Sie einen neuen ${filterTyp || 'Kontrakt'}.`}
              </p>
            </div>
          )}
        </div>

        {selectedKontrakt && <ResizeHandle isDragging={isDragging} onMouseDown={startDragging} />}

        <AnimatePresence>
          {selectedKontrakt && (
            <motion.div initial={{ x: '100%', opacity: 0 }} animate={{ x: 0, opacity: 1 }} exit={{ x: '100%', opacity: 0 }} transition={{ type: 'spring', damping: 25, stiffness: 200 }} className="border-l border-gray-200 bg-white flex flex-col overflow-hidden" style={rightPanelStyle}>
              {/* Panel Header */}
              <div className="flex items-center justify-between p-4 border-b bg-gray-50">
                <div className="flex items-center gap-3">
                  <div className={cn(
                    "h-10 w-10 rounded-lg flex items-center justify-center", 
                    selectedKontrakt.ist_strecke ? "bg-orange-100" : (watchFields.vorgang_typ === 'EK' ? "bg-green-100" : "bg-blue-100")
                  )}>
                    {selectedKontrakt.ist_strecke ? (
                      <ArrowRightLeft className="h-5 w-5 text-orange-600" />
                    ) : watchFields.vorgang_typ === 'EK' ? (
                      <ArrowDownToLine className="h-5 w-5 text-green-600" />
                    ) : (
                      <ArrowUpFromLine className="h-5 w-5 text-blue-600" />
                    )}
                  </div>
                  <div>
                    <div className="flex items-center gap-2">
                      <h2 className="font-bold text-lg">{isNewRecord ? 'Neuer Kontrakt' : (selectedKontrakt.kontraktnummer || 'Kontrakt')}</h2>
                      {selectedKontrakt.ist_strecke && <Badge className="bg-orange-100 text-orange-700"><ArrowRightLeft className="h-3 w-3 mr-1" />Strecke</Badge>}
                      {watchFields.ist_fixierung && <Badge className="bg-purple-100 text-purple-700"><TrendingUp className="h-3 w-3 mr-1" />Fixierung</Badge>}
                    </div>
                    <div className="flex items-center gap-2">
                      <span className="text-sm text-gray-500">{watchFields.vorgang_typ === 'EK' ? 'Einkaufskontrakt' : 'Verkaufskontrakt'}</span>
                      <Badge className={cn(statusColors[watchFields.status || 'OFFEN']?.bg, statusColors[watchFields.status || 'OFFEN']?.text)}>{watchFields.status || 'OFFEN'}</Badge>
                      {/* Strecken-Partner anzeigen */}
                      {selectedKontrakt.ist_strecke && selectedKontrakt.strecken_partner && (
                        <span className="text-xs text-orange-600 flex items-center gap-1">
                          <ArrowRight className="h-3 w-3" />
                          {watchFields.vorgang_typ === 'EK' 
                            ? selectedKontrakt.strecken_partner.vk?.kontraktnummer
                            : selectedKontrakt.strecken_partner.ek?.kontraktnummer}
                        </span>
                      )}
                    </div>
                  </div>
                </div>
                <div className="flex items-center gap-2">
                  {isEditing ? (<><Button variant="outline" size="sm" onClick={handleCancel}>Abbrechen</Button><Button size="sm" onClick={handleSave} className="bg-emerald-600 hover:bg-emerald-700"><Save className="h-4 w-4 mr-1" />Speichern</Button></>) : (<Button variant="outline" size="sm" onClick={() => setIsEditing(true)} disabled={selectedKontrakt.abgeschlossen}><Pencil className="h-4 w-4 mr-1" />Bearbeiten</Button>)}
                  <Button variant="ghost" size="icon" onClick={handleClose}><X className="h-5 w-5" /></Button>
                </div>
              </div>

              {/* Sidebar + Content */}
              <div className="flex flex-1 overflow-hidden">
                <div className="w-44 bg-gray-50 border-r p-2 space-y-1">
                  {sidebarSections.map((section) => (
                    <button key={section.id} onClick={() => setActiveSection(section.id)}
                      className={cn("w-full flex items-center gap-2 px-3 py-2 rounded-lg text-sm transition-colors", activeSection === section.id ? "bg-emerald-100 text-emerald-700 font-medium" : "text-gray-600 hover:bg-gray-100", section.id === 'fixierung' && !watchFields.ist_fixierung && "opacity-50")}>
                      <section.icon className="h-4 w-4" />{section.label}
                    </button>
                  ))}
                </div>

                <ScrollArea className="flex-1 p-4">
                  <form onSubmit={handleSubmit(onSubmit)}>
                    
                    {/* === KOPFDATEN === */}
                    {activeSection === 'kopf' && (
                      <div className="space-y-4">
                        <div className="grid grid-cols-2 gap-4">
                          <div className="space-y-1.5"><Label>Kontrakttyp</Label><Select value={watchFields.vorgang_typ || 'EK'} onValueChange={(v) => setValue('vorgang_typ', v)} disabled={!isEditing}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="EK">Einkaufskontrakt</SelectItem><SelectItem value="VK">Verkaufskontrakt</SelectItem></SelectContent></Select></div>
                          <div className="space-y-1.5"><Label>Kontraktnummer</Label><Input {...register('kontraktnummer')} disabled={!isEditing || !isNewRecord} className="font-mono" placeholder="Wird automatisch vergeben" /></div>
                        </div>
                        <div className="grid grid-cols-2 gap-4">
                          <div className="space-y-1.5"><Label>Status</Label><Select value={watchFields.status || 'OFFEN'} onValueChange={(v) => setValue('status', v)} disabled={!isEditing}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="OFFEN">Offen</SelectItem><SelectItem value="AKTIV">Aktiv</SelectItem><SelectItem value="TEILERFUELLT">Teilerfüllt</SelectItem><SelectItem value="ERFUELLT">Erfüllt</SelectItem><SelectItem value="STORNO">Storniert</SelectItem></SelectContent></Select></div>
                          <div className="flex items-end pb-2 gap-4"><div className="flex items-center gap-2"><Switch checked={watchFields.ist_fixierung || false} onCheckedChange={(v) => setValue('ist_fixierung', v)} disabled={!isEditing} /><Label className="text-sm">Fixierungskontrakt</Label></div></div>
                        </div>
                        
                        {/* Termine */}
                        <div className="pt-4 border-t">
                          <h4 className="font-semibold text-gray-900 flex items-center gap-2 mb-3">
                            <Calendar className="h-4 w-4 text-blue-600" />
                            Termine
                          </h4>
                          <div className="grid grid-cols-3 gap-4">
                            <div className="space-y-1.5">
                              <Label>Erstellungsdatum</Label>
                              <Input type="date" {...register('erstellungsdatum')} disabled={!isEditing} />
                            </div>
                            <div className="space-y-1.5">
                              <Label>Gültig von</Label>
                              <Input type="date" {...register('gueltig_von')} disabled={!isEditing} />
                            </div>
                            <div className="space-y-1.5">
                              <Label>Gültig bis</Label>
                              <Input type="date" {...register('gueltig_bis')} disabled={!isEditing} />
                            </div>
                          </div>
                        </div>
                      </div>
                    )}

                    {/* === VERTRAGSPARTNER === */}
                    {activeSection === 'partner' && (
                      <div className="space-y-4">
                        {/* VERTRAGSPARTNER CARD */}
                        <div className="rounded-lg border bg-white shadow-sm">
                          <div className="p-3 border-b bg-gray-50/50">
                            <h4 className="font-semibold text-gray-900 flex items-center gap-2">
                              <Building2 className="h-4 w-4 text-blue-600" />
                              Vertragspartner
                            </h4>
                          </div>
                          <div className="p-3 space-y-3">
                            {/* Adresse auswählen */}
                            <AdressenSelect 
                              value={watchFields.id_adresse || undefined} 
                              onChange={handleAdresseSelect} 
                              disabled={!isEditing} 
                            />
                            
                            {/* Gewählte Adresse anzeigen */}
                            {watchFields.id_adresse && watchFields.name1 && (
                              <div className="p-3 bg-blue-50/50 rounded-lg border border-blue-100">
                                <div className="flex items-start justify-between">
                                  <div>
                                    <div className="font-semibold text-gray-900">{watchFields.name1}</div>
                                    {watchFields.name2 && <div className="text-sm text-gray-600">{watchFields.name2}</div>}
                                    <div className="text-sm text-gray-500 mt-1">
                                      {watchFields.strasse} {watchFields.hausnummer}, {watchFields.plz} {watchFields.ort}
                                    </div>
                                  </div>
                                  {isEditing && (
                                    <Button variant="ghost" size="sm" className="text-gray-400 hover:text-red-500 -mt-1 -mr-1"
                                      onClick={() => {
                                        setValue('id_adresse', null);
                                        setValue('name1', '');
                                        setValue('name2', '');
                                        setValue('strasse', '');
                                        setValue('hausnummer', '');
                                        setValue('plz', '');
                                        setValue('ort', '');
                                        setValue('land', '');
                                        setValue('ust_id', '');
                                        setValue('id_bankverbindung', null);
                                        setValue('bank_iban', '');
                                        setValue('id_ansprechpartner', null);
                                        setValue('ansprechpartner_name', '');
                                        setAnsprechpartnerListe([]);
                                        setSelectedAdresse(null);
                                      }}>
                                      <X className="h-4 w-4" />
                                    </Button>
                                  )}
                                </div>
                                
                                {/* USt-ID & Bankverbindung - Anzeige immer wenn Daten vorhanden */}
                                <div className="grid grid-cols-2 gap-3 mt-3 pt-3 border-t border-blue-200/50">
                                  {/* USt-ID Auswahl/Anzeige */}
                                  <div className="space-y-1">
                                    <Label className="text-xs text-gray-600">USt-ID</Label>
                                    {(() => {
                                      const validUstIds = selectedAdresse?.ust_ids?.filter(u => u.ust_id && u.ust_id.trim() !== '') || [];
                                      if (isEditing && validUstIds.length > 1) {
                                        return (
                                          <Select value={watchFields.ust_id || ''} onValueChange={(v) => setValue('ust_id', v)} disabled={!isEditing}>
                                            <SelectTrigger className="h-8 text-sm bg-white">
                                              <SelectValue placeholder="USt-ID wählen" />
                                            </SelectTrigger>
                                            <SelectContent>
                                              {validUstIds.map((u) => (
                                                <SelectItem key={u.id} value={u.ust_id}>
                                                  {u.ust_id} {u.ist_hauptid && <span className="text-xs text-blue-600">(Haupt)</span>}
                                                </SelectItem>
                                              ))}
                                            </SelectContent>
                                          </Select>
                                        );
                                      }
                                      return <div className="text-sm font-medium text-gray-900 py-1">{watchFields.ust_id || '-'}</div>;
                                    })()}
                                  </div>
                                  
                                  {/* Bankverbindung Auswahl/Anzeige */}
                                  <div className="space-y-1">
                                    <Label className="text-xs text-gray-600 flex items-center gap-1">
                                      <CreditCard className="h-3 w-3" />Bankverbindung
                                    </Label>
                                    {(() => {
                                      const validBanks = selectedAdresse?.bankverbindungen?.filter(b => b.id && b.id.toString().trim() !== '') || [];
                                      if (isEditing && validBanks.length > 0) {
                                        return (
                                          <Select 
                                            value={watchFields.id_bankverbindung || ''} 
                                            onValueChange={(v) => {
                                              const bank = validBanks.find(b => b.id === v);
                                              if (bank) {
                                                setValue('id_bankverbindung', v);
                                                setValue('bank_iban', bank.iban);
                                                setValue('bank_bic', bank.bic || '');
                                                setValue('bank_name', bank.bank_name || '');
                                                setValue('bank_waehrung', bank.waehrung || 'EUR');
                                              }
                                            }} 
                                            disabled={!isEditing}
                                          >
                                            <SelectTrigger className="h-8 text-sm bg-white">
                                              <SelectValue placeholder="Konto wählen" />
                                            </SelectTrigger>
                                            <SelectContent>
                                              {validBanks.map((b) => (
                                                <SelectItem key={b.id} value={b.id}>
                                                  <span className="flex items-center gap-2">
                                                    <span className="font-mono text-xs">{b.iban.slice(-8)}</span>
                                                    <Badge variant="outline" className="text-[10px] px-1">{b.waehrung}</Badge>
                                                    {b.ist_hauptkonto && <span className="text-xs text-blue-600">(Haupt)</span>}
                                                  </span>
                                                </SelectItem>
                                              ))}
                                            </SelectContent>
                                          </Select>
                                        );
                                      }
                                      // Lesemodus: Zeige gespeicherte Bankverbindung
                                      if (watchFields.bank_iban) {
                                        return (
                                          <div className="text-sm font-medium text-gray-900 py-1">
                                            <span className="font-mono">{watchFields.bank_iban.slice(-8)}</span>
                                            {watchFields.bank_waehrung && <Badge variant="outline" className="text-[10px] px-1 ml-2">{watchFields.bank_waehrung}</Badge>}
                                          </div>
                                        );
                                      }
                                      return <div className="text-sm text-gray-500 py-1">Keine Bankverbindung</div>;
                                    })()}
                                  </div>
                                </div>
                              </div>
                            )}
                            
                            {/* Manueller Eintrag falls keine Adresse gewählt */}
                            {!watchFields.id_adresse && (
                              <div className="space-y-1.5">
                                <Label className="text-xs">Firma/Name *</Label>
                                <Input {...register('name1')} disabled={!isEditing} placeholder="Vertragspartner eingeben..." />
                                {errors.name1 && <p className="text-xs text-red-500">{errors.name1.message}</p>}
                              </div>
                            )}
                          </div>
                        </div>

                        {/* ANSPRECHPARTNER CARD */}
                        <div className="rounded-lg border bg-white shadow-sm">
                          <div className="p-3 border-b bg-gray-50/50">
                            <h4 className="font-semibold text-gray-900 flex items-center gap-2">
                              <Users className="h-4 w-4 text-emerald-600" />
                              Ansprechpartner
                            </h4>
                          </div>
                          <div className="p-3">
                            {/* Dropdown nur anzeigen wenn Liste vorhanden ODER im Bearbeitungsmodus */}
                            {ansprechpartnerListe.length > 0 ? (
                              <Select 
                                value={watchFields.id_ansprechpartner || ''} 
                                onValueChange={(v) => {
                                  const ap = ansprechpartnerListe.find(a => a.id === v);
                                  if (ap) handleAnsprechpartnerSelect(ap);
                                }}
                                disabled={!isEditing}
                              >
                                <SelectTrigger className="bg-white">
                                  <SelectValue placeholder="Ansprechpartner wählen..." />
                                </SelectTrigger>
                                <SelectContent>
                                  {ansprechpartnerListe.map((ap) => (
                                    <SelectItem key={ap.id} value={ap.id}>
                                      <span className="flex items-center gap-2">
                                        <span className="font-medium">{ap.vorname} {ap.nachname}</span>
                                        {ap.funktion && <span className="text-gray-500">({ap.funktion})</span>}
                                      </span>
                                    </SelectItem>
                                  ))}
                                </SelectContent>
                              </Select>
                            ) : watchFields.ansprechpartner_name ? (
                              // Ansprechpartner ist gesetzt aber Liste nicht geladen - zeige die gespeicherten Daten
                              <div className="p-2 bg-emerald-50/50 rounded text-sm">
                                <span className="font-medium">{watchFields.ansprechpartner_name}</span>
                                {watchFields.ansprechpartner_telefon && (
                                  <span className="text-gray-500 ml-3">📞 {watchFields.ansprechpartner_telefon}</span>
                                )}
                              </div>
                            ) : (
                              <div className="text-sm text-gray-500 bg-gray-50 p-3 rounded-lg text-center">
                                {watchFields.id_adresse ? 'Keine Ansprechpartner hinterlegt' : 'Bitte zuerst Vertragspartner wählen'}
                              </div>
                            )}
                            
                            {/* Gewählter Ansprechpartner Info - nur wenn Liste vorhanden UND ausgewählt */}
                            {ansprechpartnerListe.length > 0 && watchFields.ansprechpartner_name && (
                              <div className="mt-2 p-2 bg-emerald-50/50 rounded text-sm flex items-center gap-3">
                                <span className="font-medium">{watchFields.ansprechpartner_name}</span>
                                {watchFields.ansprechpartner_telefon && (
                                  <span className="text-gray-500">📞 {watchFields.ansprechpartner_telefon}</span>
                                )}
                              </div>
                            )}
                          </div>
                        </div>

                        {/* INTERNE ZUSTÄNDIGKEIT CARD */}
                        <div className="rounded-lg border bg-white shadow-sm">
                          <div className="p-3 border-b bg-gray-50/50">
                            <h4 className="font-semibold text-gray-900 flex items-center gap-2">
                              <User className="h-4 w-4 text-violet-600" />
                              Interne Zuständigkeit
                            </h4>
                          </div>
                          <div className="p-3">
                            <div className="grid grid-cols-2 gap-3">
                              <div className="space-y-1">
                                <Label className="text-xs text-gray-600">Sachbearbeiter</Label>
                                <BenutzerSelect 
                                  value={watchFields.id_sachbearbeiter || undefined} 
                                  onChange={handleSachbearbeiterSelect} 
                                  disabled={!isEditing} 
                                  placeholder="Wählen..." 
                                  benutzerListe={benutzerData || []} 
                                  showDetails={false}
                                />
                              </div>
                              <div className="space-y-1">
                                <Label className="text-xs text-gray-600">Händler</Label>
                                <BenutzerSelect 
                                  value={watchFields.id_haendler || undefined} 
                                  onChange={handleHaendlerSelect} 
                                  disabled={!isEditing} 
                                  placeholder="Wählen..." 
                                  benutzerListe={benutzerData || []} 
                                  showDetails={false}
                                />
                              </div>
                            </div>
                          </div>
                        </div>
                      </div>
                    )}

                    {/* === LÄGER === */}
                    {activeSection === 'laeger' && (
                      <div className="space-y-4">
                        {/* Info-Hinweis */}
                        <div className="p-3 bg-blue-50 rounded-lg border border-blue-200 text-sm text-blue-700">
                          <Truck className="h-4 w-4 inline mr-2" />
                          {watchFields.vorgang_typ === 'EK' 
                            ? 'Bei Einkaufskontrakten: Abhollager = Lieferant, Ziellager = Eigenes Lager'
                            : 'Bei Verkaufskontrakten: Abhollager = Eigenes Lager, Ziellager = Kunde'
                          }
                        </div>

                        {/* ABHOLLAGER CARD */}
                        <div className="rounded-lg border bg-white shadow-sm">
                          <div className="p-3 border-b bg-gray-50/50">
                            <h4 className="font-semibold text-gray-900 flex items-center gap-2">
                              <ArrowUpFromLine className="h-4 w-4 text-orange-600" />
                              Abhollager
                              <Badge variant="outline" className="ml-auto text-xs">
                                {watchFields.vorgang_typ === 'EK' ? 'Lieferant' : 'Eigenes Lager'}
                              </Badge>
                            </h4>
                          </div>
                          <div className="p-3">
                            {(() => {
                              // EK: Vertragspartner-Adressen, VK: Mandant-Adresse
                              const lagerOptionen: LagerOption[] = [];
                              if (watchFields.vorgang_typ === 'EK' && selectedAdresse) {
                                // Hauptadresse des Vertragspartners
                                lagerOptionen.push({
                                  id: `haupt_${selectedAdresse.id}`,
                                  typ: 'haupt',
                                  bezeichnung: 'Hauptadresse',
                                  name1: selectedAdresse.name1,
                                  strasse: selectedAdresse.strasse,
                                  hausnummer: selectedAdresse.hausnummer,
                                  plz: selectedAdresse.plz,
                                  ort: selectedAdresse.ort,
                                  land: selectedAdresse.land
                                });
                                // Lieferadressen
                                selectedAdresse.lieferadressen?.forEach(la => {
                                  lagerOptionen.push({
                                    id: `liefer_${la.id}`,
                                    typ: 'liefer',
                                    bezeichnung: la.bezeichnung || la.name1 || 'Lieferadresse',
                                    name1: la.name1,
                                    strasse: la.strasse,
                                    hausnummer: la.hausnummer,
                                    plz: la.plz,
                                    ort: la.ort,
                                    land: la.land
                                  });
                                });
                              } else if (watchFields.vorgang_typ === 'VK' && mandantData) {
                                // Hauptadresse des Mandanten
                                lagerOptionen.push({
                                  id: `mandant_${mandantData.id}`,
                                  typ: 'mandant',
                                  bezeichnung: 'Eigenes Lager (Hauptstandort)',
                                  name1: mandantData.name1,
                                  strasse: mandantData.strasse,
                                  hausnummer: mandantData.hausnummer,
                                  plz: mandantData.plz,
                                  ort: mandantData.ort,
                                  land: mandantData.land
                                });
                                // Lieferadressen/Standorte des Mandanten
                                mandantData.lieferadressen?.forEach((la) => {
                                  lagerOptionen.push({
                                    id: `mandant_liefer_${la.id || la.bezeichnung}`,
                                    typ: 'liefer',
                                    bezeichnung: la.bezeichnung || la.name1 || 'Standort',
                                    name1: la.name1,
                                    strasse: la.strasse,
                                    hausnummer: la.hausnummer,
                                    plz: la.plz,
                                    ort: la.ort,
                                    land: la.land
                                  });
                                });
                              }

                              if (lagerOptionen.length > 0) {
                                return (
                                  <>
                                    <Select 
                                      value={watchFields.id_abhollager || ''} 
                                      onValueChange={(v) => {
                                        const lager = lagerOptionen.find(l => l.id === v);
                                        if (lager) {
                                          setValue('id_abhollager', v);
                                          setValue('abhollager_typ', lager.typ);
                                          setValue('abhollager_name', lager.bezeichnung || lager.name1 || '');
                                          setValue('abhollager_strasse', `${lager.strasse || ''} ${lager.hausnummer || ''}`.trim());
                                          setValue('abhollager_plz', lager.plz || '');
                                          setValue('abhollager_ort', lager.ort || '');
                                          setValue('abhollager_land', lager.land || '');
                                        }
                                      }}
                                      disabled={!isEditing}
                                    >
                                      <SelectTrigger className="bg-white">
                                        <SelectValue placeholder="Abhollager wählen..." />
                                      </SelectTrigger>
                                      <SelectContent>
                                        {lagerOptionen.map((l) => (
                                          <SelectItem key={l.id} value={l.id}>
                                            <div className="flex flex-col">
                                              <span className="font-medium">{l.bezeichnung}</span>
                                              <span className="text-xs text-gray-500">{l.strasse} {l.hausnummer}, {l.plz} {l.ort}</span>
                                            </div>
                                          </SelectItem>
                                        ))}
                                      </SelectContent>
                                    </Select>
                                    {watchFields.abhollager_name && (
                                      <div className="mt-2 p-3 bg-orange-50/50 rounded-lg border border-orange-100">
                                        <div className="font-medium text-gray-900">{watchFields.abhollager_name}</div>
                                        <div className="text-sm text-gray-500">{watchFields.abhollager_strasse}, {watchFields.abhollager_plz} {watchFields.abhollager_ort}</div>
                                      </div>
                                    )}
                                  </>
                                );
                              }
                              return (
                                <div className="text-sm text-gray-500 bg-gray-50 p-3 rounded-lg text-center">
                                  {watchFields.vorgang_typ === 'EK' 
                                    ? 'Bitte zuerst Vertragspartner wählen' 
                                    : 'Mandant-Adresse nicht konfiguriert'
                                  }
                                </div>
                              );
                            })()}
                          </div>
                        </div>

                        {/* ZIELLAGER CARD */}
                        <div className="rounded-lg border bg-white shadow-sm">
                          <div className="p-3 border-b bg-gray-50/50">
                            <h4 className="font-semibold text-gray-900 flex items-center gap-2">
                              <ArrowDownToLine className="h-4 w-4 text-green-600" />
                              Ziellager
                              <Badge variant="outline" className="ml-auto text-xs">
                                {watchFields.vorgang_typ === 'EK' ? 'Eigenes Lager' : 'Kunde'}
                              </Badge>
                            </h4>
                          </div>
                          <div className="p-3">
                            {(() => {
                              // EK: Mandant-Adresse, VK: Vertragspartner-Adressen
                              const lagerOptionen: LagerOption[] = [];
                              if (watchFields.vorgang_typ === 'VK' && selectedAdresse) {
                                // Hauptadresse des Vertragspartners (Kunde)
                                lagerOptionen.push({
                                  id: `haupt_${selectedAdresse.id}`,
                                  typ: 'haupt',
                                  bezeichnung: 'Hauptadresse',
                                  name1: selectedAdresse.name1,
                                  strasse: selectedAdresse.strasse,
                                  hausnummer: selectedAdresse.hausnummer,
                                  plz: selectedAdresse.plz,
                                  ort: selectedAdresse.ort,
                                  land: selectedAdresse.land
                                });
                                // Lieferadressen des Kunden
                                selectedAdresse.lieferadressen?.forEach(la => {
                                  lagerOptionen.push({
                                    id: `liefer_${la.id}`,
                                    typ: 'liefer',
                                    bezeichnung: la.bezeichnung || la.name1 || 'Lieferadresse',
                                    name1: la.name1,
                                    strasse: la.strasse,
                                    hausnummer: la.hausnummer,
                                    plz: la.plz,
                                    ort: la.ort,
                                    land: la.land
                                  });
                                });
                              } else if (watchFields.vorgang_typ === 'EK' && mandantData) {
                                // Hauptadresse des Mandanten
                                lagerOptionen.push({
                                  id: `mandant_${mandantData.id}`,
                                  typ: 'mandant',
                                  bezeichnung: 'Eigenes Lager (Hauptstandort)',
                                  name1: mandantData.name1,
                                  strasse: mandantData.strasse,
                                  hausnummer: mandantData.hausnummer,
                                  plz: mandantData.plz,
                                  ort: mandantData.ort,
                                  land: mandantData.land
                                });
                                // Lieferadressen/Standorte des Mandanten
                                mandantData.lieferadressen?.forEach((la) => {
                                  lagerOptionen.push({
                                    id: `mandant_liefer_${la.id || la.bezeichnung}`,
                                    typ: 'liefer',
                                    bezeichnung: la.bezeichnung || la.name1 || 'Standort',
                                    name1: la.name1,
                                    strasse: la.strasse,
                                    hausnummer: la.hausnummer,
                                    plz: la.plz,
                                    ort: la.ort,
                                    land: la.land
                                  });
                                });
                              }

                              if (lagerOptionen.length > 0) {
                                return (
                                  <>
                                    <Select 
                                      value={watchFields.id_ziellager || ''} 
                                      onValueChange={(v) => {
                                        const lager = lagerOptionen.find(l => l.id === v);
                                        if (lager) {
                                          setValue('id_ziellager', v);
                                          setValue('ziellager_typ', lager.typ);
                                          setValue('ziellager_name', lager.bezeichnung || lager.name1 || '');
                                          setValue('ziellager_strasse', `${lager.strasse || ''} ${lager.hausnummer || ''}`.trim());
                                          setValue('ziellager_plz', lager.plz || '');
                                          setValue('ziellager_ort', lager.ort || '');
                                          setValue('ziellager_land', lager.land || '');
                                        }
                                      }}
                                      disabled={!isEditing}
                                    >
                                      <SelectTrigger className="bg-white">
                                        <SelectValue placeholder="Ziellager wählen..." />
                                      </SelectTrigger>
                                      <SelectContent>
                                        {lagerOptionen.map((l) => (
                                          <SelectItem key={l.id} value={l.id}>
                                            <div className="flex flex-col">
                                              <span className="font-medium">{l.bezeichnung}</span>
                                              <span className="text-xs text-gray-500">{l.strasse} {l.hausnummer}, {l.plz} {l.ort}</span>
                                            </div>
                                          </SelectItem>
                                        ))}
                                      </SelectContent>
                                    </Select>
                                    {watchFields.ziellager_name && (
                                      <div className="mt-2 p-3 bg-green-50/50 rounded-lg border border-green-100">
                                        <div className="font-medium text-gray-900">{watchFields.ziellager_name}</div>
                                        <div className="text-sm text-gray-500">{watchFields.ziellager_strasse}, {watchFields.ziellager_plz} {watchFields.ziellager_ort}</div>
                                      </div>
                                    )}
                                  </>
                                );
                              }
                              return (
                                <div className="text-sm text-gray-500 bg-gray-50 p-3 rounded-lg text-center">
                                  {watchFields.vorgang_typ === 'VK' 
                                    ? 'Bitte zuerst Vertragspartner wählen' 
                                    : 'Mandant-Adresse nicht konfiguriert'
                                  }
                                </div>
                              );
                            })()}
                          </div>
                        </div>
                      </div>
                    )}

                    {/* === KONDITIONEN === */}
                    {activeSection === 'konditionen' && (
                      <div className="space-y-4">
                        <div className="grid grid-cols-2 gap-4">
                          <div className="space-y-1.5"><Label>Währung</Label><Select value={watchFields.waehrung_kurz || 'EUR'} onValueChange={(v) => setValue('waehrung_kurz', v)} disabled={!isEditing}><SelectTrigger><SelectValue><span className="flex items-center gap-2"><span className="font-mono">{selectedWaehrung.symbol}</span>{selectedWaehrung.code}</span></SelectValue></SelectTrigger><SelectContent>{WAEHRUNGEN.map((w) => (<SelectItem key={w.code} value={w.code}><span className="flex items-center gap-2"><span className="font-mono font-semibold w-8">{w.symbol}</span>{w.code} - {w.name}</span></SelectItem>))}</SelectContent></Select></div>
                          <div className="space-y-1.5"><Label>Währungskurs</Label><Input type="number" step="0.0001" {...register('waehrungskurs', { valueAsNumber: true })} disabled={!isEditing} /></div>
                        </div>
                        <div className="space-y-1.5"><Label>Zahlungsbedingung</Label><SmartInput module="kontrakte" fieldName="zahlungsbedingung" value={watch('zahlungsbedingung')} onChange={(val) => setValue('zahlungsbedingung', val || '')} disabled={!isEditing} placeholder="z.B. 30 Tage netto" /></div>
                        <div className="space-y-1.5"><Label>Lieferbedingung</Label><SmartInput module="kontrakte" fieldName="lieferbedingung" value={watch('lieferbedingung')} onChange={(val) => setValue('lieferbedingung', val || '')} disabled={!isEditing} placeholder="z.B. FCA, DAP, CIF" /></div>
                      </div>
                    )}

                    {/* === FIXIERUNG === */}
                    {activeSection === 'fixierung' && (
                      <div className="space-y-4">
                        {!watchFields.ist_fixierung ? (
                          <div className="text-center py-8 text-gray-500 bg-gray-50 rounded-lg border-2 border-dashed"><TrendingUp className="h-12 w-12 mx-auto mb-2 text-gray-300" /><p>Kein Fixierungskontrakt</p><p className="text-sm mt-1">Aktivieren Sie "Fixierungskontrakt" in den Kopfdaten</p></div>
                        ) : (
                          <>
                            <div className="p-3 bg-purple-50 rounded-lg border border-purple-200"><p className="text-sm text-purple-700"><TrendingUp className="h-4 w-4 inline mr-1" />Fixierungskontrakte ermöglichen die Festlegung von Börsenpreisen für einen bestimmten Zeitraum.</p></div>
                            <div className="grid grid-cols-2 gap-4">
                              <div className="space-y-1.5"><Label>Fixierung von</Label><Input {...register('fix_von')} type="date" disabled={!isEditing} /></div>
                              <div className="space-y-1.5"><Label>Fixierung bis</Label><Input {...register('fix_bis')} type="date" disabled={!isEditing} /></div>
                            </div>
                            <div className="grid grid-cols-2 gap-4">
                              <div className="space-y-1.5"><Label>Fixierungsmenge (gesamt)</Label><Input type="number" step="0.01" {...register('fix_menge_gesamt', { valueAsNumber: true })} disabled={!isEditing} /></div>
                              <div className="space-y-1.5"><Label>Einheit</Label><SmartInput module="kontrakte" fieldName="fix_einheit" value={watch('fix_einheit')} onChange={(val) => setValue('fix_einheit', val || '')} disabled={!isEditing} placeholder="t" /></div>
                            </div>
                            <div className="space-y-1.5"><Label>Hauptartikel für Fixierung</Label><SmartInput module="kontrakte" fieldName="fix_id_artikel" value={watch('fix_id_artikel')} onChange={(val) => setValue('fix_id_artikel', val || '')} disabled={!isEditing} placeholder="Artikel auswählen..." /></div>
                            <div className="grid grid-cols-2 gap-4">
                              <div className="space-y-1.5"><Label>Börsendifferenz absolut ({selectedWaehrung.symbol}/t)</Label><Input type="number" step="0.01" {...register('boerse_diff_abs', { valueAsNumber: true })} disabled={!isEditing} /></div>
                              <div className="space-y-1.5"><Label>Börsendifferenz prozentual (%)</Label><Input type="number" step="0.01" {...register('boerse_diff_proz', { valueAsNumber: true })} disabled={!isEditing} /></div>
                            </div>
                            <div className="space-y-1.5"><Label>Fixierungs-Bemerkung</Label><Textarea {...register('bemerkung_fix1')} disabled={!isEditing} rows={3} /></div>
                          </>
                        )}
                      </div>
                    )}

                    {/* === POSITIONEN === */}
                    {activeSection === 'positionen' && (
                      <div className="space-y-4" data-testid="positionen-section">
                        <div className="flex items-center justify-between">
                          <h3 className="font-semibold text-gray-900 flex items-center gap-2">
                            <Package className="h-5 w-5 text-emerald-600" />
                            Kontraktpositionen
                          </h3>
                          {isEditing && (
                            <Button type="button" size="sm" onClick={handleAddPosition} className="bg-emerald-600 hover:bg-emerald-700" data-testid="add-position-btn">
                              <Plus className="h-4 w-4 mr-1" />Neue Position
                            </Button>
                          )}
                        </div>
                        
                        <PositionenListe
                          positionen={selectedKontrakt.positionen || []}
                          waehrung={watchFields.waehrung_kurz || 'EUR'}
                          isEditing={isEditing}
                          onEdit={handleEditPosition}
                          onDelete={handleDeletePosition}
                          onCopy={handleCopyPosition}
                          onView={handleViewPosition}
                        />
                        
                        {/* Hinweis für Lesemodus */}
                        {!isEditing && selectedKontrakt.positionen && selectedKontrakt.positionen.length > 0 && (
                          <p className="text-xs text-center text-gray-400 pt-2">
                            Doppelklick auf eine Position für Detailansicht
                          </p>
                        )}
                      </div>
                    )}

                    {/* === FORMULARTEXTE === */}
                    {activeSection === 'formulartexte' && (
                      <div className="space-y-4">
                        <div className="space-y-1.5"><Label>Text am Anfang des Formulars</Label><Textarea {...register('formulartext_anfang')} disabled={!isEditing} rows={5} placeholder="Dieser Text erscheint vor den Positionen..." /></div>
                        <div className="space-y-1.5"><Label>Text am Ende des Formulars</Label><Textarea {...register('formulartext_ende')} disabled={!isEditing} rows={5} placeholder="Dieser Text erscheint nach den Positionen..." /></div>
                        <div className="flex items-center gap-2 pt-2"><Switch checked={watchFields.kopie_bemerkung_auf_pos || false} onCheckedChange={(v) => setValue('kopie_bemerkung_auf_pos', v)} disabled={!isEditing} /><Label className="text-sm">Bemerkung auf Positionen kopieren</Label></div>
                      </div>
                    )}

                    {/* === BEMERKUNGEN === */}
                    {activeSection === 'bemerkungen' && (
                      <div className="space-y-4">
                        <div className="space-y-1.5"><Label>Externe Bemerkungen (für Partner sichtbar)</Label><Textarea {...register('bemerkung_extern')} disabled={!isEditing} rows={5} /></div>
                        <div className="space-y-1.5"><Label>Interne Bemerkungen</Label><Textarea {...register('bemerkung_intern')} disabled={!isEditing} rows={5} /></div>
                      </div>
                    )}

                    {/* === PROTOKOLL (nur im Lesemodus) === */}
                    {activeSection === 'protokoll' && selectedKontrakt?.id && (
                      <div className="space-y-4">
                        {isEditing ? (
                          <div className="text-center py-12 bg-gradient-to-br from-amber-50 to-white rounded-xl border-2 border-dashed border-amber-200">
                            <Lock className="h-12 w-12 mx-auto mb-3 text-amber-400" />
                            <p className="text-amber-700 font-medium">Protokoll im Bearbeitungsmodus nicht verfügbar</p>
                            <p className="text-sm text-amber-600 mt-1">Speichern Sie Ihre Änderungen, um das Protokoll einzusehen</p>
                          </div>
                        ) : (
                          <ProtokollTab kontraktId={selectedKontrakt.id} />
                        )}
                      </div>
                    )}

                  </form>
                </ScrollArea>
              </div>
            </motion.div>
          )}
        </AnimatePresence>
      </div>

      {/* Position Dialog - Bearbeiten/Hinzufügen */}
      <PositionDialog 
        open={showPositionDialog} 
        onClose={() => { setShowPositionDialog(false); setEditingPosition(null); }} 
        onSave={handleSavePosition} 
        position={editingPosition} 
        waehrung={watchFields.waehrung_kurz || 'EUR'} 
      />
      
      {/* Position Dialog - Nur Ansicht (Doppelklick) */}
      <PositionDialog 
        open={showViewDialog} 
        onClose={() => { setShowViewDialog(false); setViewPosition(null); }} 
        onSave={() => {}} 
        position={viewPosition} 
        waehrung={watchFields.waehrung_kurz || 'EUR'}
        readOnly={true}
      />

      {/* Streckengeschäft erstellen Dialog */}
      <Dialog open={showStreckeDialog} onOpenChange={setShowStreckeDialog}>
        <DialogContent className="max-w-3xl">
          <DialogHeader>
            <DialogTitle className="flex items-center gap-2">
              <ArrowRightLeft className="h-5 w-5 text-orange-600" />
              Neues Streckengeschäft erstellen
            </DialogTitle>
            <DialogDescription>
              Ein Streckengeschäft verknüpft automatisch einen Einkaufs- und Verkaufskontrakt. 
              Die Ware wird direkt vom Lieferanten zum Abnehmer geliefert.
            </DialogDescription>
          </DialogHeader>
          
          <div className="grid grid-cols-2 gap-6 py-4">
            {/* Lieferant (EK-Partner) */}
            <div className="space-y-4">
              <div className="flex items-center gap-2 pb-2 border-b">
                <div className="h-8 w-8 rounded-lg bg-green-100 flex items-center justify-center">
                  <ArrowDownToLine className="h-4 w-4 text-green-600" />
                </div>
                <div>
                  <h4 className="font-semibold text-gray-900">Lieferant (EK)</h4>
                  <p className="text-xs text-gray-500">Wo kaufen wir ein?</p>
                </div>
              </div>
              <AdresseSelect
                value={streckenLieferant}
                onChange={setStreckenLieferant}
                placeholder="Lieferant auswählen..."
              />
              {streckenLieferant && (
                <div className="p-3 bg-green-50 rounded-lg border border-green-100">
                  <div className="font-medium">{streckenLieferant.name1}</div>
                  <div className="text-sm text-gray-500">
                    {streckenLieferant.strasse} {streckenLieferant.hausnummer}, {streckenLieferant.plz} {streckenLieferant.ort}
                  </div>
                </div>
              )}
            </div>

            {/* Abnehmer (VK-Partner) */}
            <div className="space-y-4">
              <div className="flex items-center gap-2 pb-2 border-b">
                <div className="h-8 w-8 rounded-lg bg-blue-100 flex items-center justify-center">
                  <ArrowUpFromLine className="h-4 w-4 text-blue-600" />
                </div>
                <div>
                  <h4 className="font-semibold text-gray-900">Abnehmer (VK)</h4>
                  <p className="text-xs text-gray-500">An wen verkaufen wir?</p>
                </div>
              </div>
              <AdresseSelect
                value={streckenAbnehmer}
                onChange={setStreckenAbnehmer}
                placeholder="Abnehmer auswählen..."
              />
              {streckenAbnehmer && (
                <div className="p-3 bg-blue-50 rounded-lg border border-blue-100">
                  <div className="font-medium">{streckenAbnehmer.name1}</div>
                  <div className="text-sm text-gray-500">
                    {streckenAbnehmer.strasse} {streckenAbnehmer.hausnummer}, {streckenAbnehmer.plz} {streckenAbnehmer.ort}
                  </div>
                </div>
              )}
            </div>
          </div>

          {/* Lieferroute Visualisierung */}
          {streckenLieferant && streckenAbnehmer && (
            <div className="py-4 border-t">
              <div className="flex items-center justify-center gap-4 p-4 bg-gradient-to-r from-green-50 via-orange-50 to-blue-50 rounded-lg border">
                <div className="text-center">
                  <Building2 className="h-6 w-6 text-green-600 mx-auto" />
                  <div className="text-sm font-medium mt-1">{streckenLieferant.name1}</div>
                  <div className="text-xs text-gray-500">{streckenLieferant.ort}</div>
                </div>
                <div className="flex-1 flex items-center justify-center">
                  <div className="flex items-center gap-2 px-4 py-1 bg-orange-100 rounded-full">
                    <Truck className="h-4 w-4 text-orange-600" />
                    <ArrowRight className="h-4 w-4 text-orange-600" />
                    <span className="text-xs font-medium text-orange-700">Direktlieferung</span>
                  </div>
                </div>
                <div className="text-center">
                  <Building2 className="h-6 w-6 text-blue-600 mx-auto" />
                  <div className="text-sm font-medium mt-1">{streckenAbnehmer.name1}</div>
                  <div className="text-xs text-gray-500">{streckenAbnehmer.ort}</div>
                </div>
              </div>
            </div>
          )}

          <DialogFooter>
            <Button variant="outline" onClick={() => setShowStreckeDialog(false)}>Abbrechen</Button>
            <Button 
              onClick={handleCreateStrecke}
              disabled={!streckenLieferant || !streckenAbnehmer || createStreckeMutation.isPending}
              className="bg-orange-600 hover:bg-orange-700"
            >
              {createStreckeMutation.isPending ? 'Wird erstellt...' : 'Streckengeschäft erstellen'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>

      {/* Verknüpfen Dialog */}
      <Dialog open={showVerknuepfenDialog} onOpenChange={setShowVerknuepfenDialog}>
        <DialogContent className="max-w-lg">
          <DialogHeader>
            <DialogTitle className="flex items-center gap-2">
              <Link2 className="h-5 w-5 text-orange-600" />
              Kontrakt zu Streckengeschäft verknüpfen
            </DialogTitle>
            <DialogDescription>
              Verknüpfen Sie diesen {selectedKontrakt?.vorgang_typ}-Kontrakt mit einem bestehenden {selectedKontrakt?.vorgang_typ === 'EK' ? 'VK' : 'EK'}-Kontrakt zu einem Streckengeschäft.
            </DialogDescription>
          </DialogHeader>
          
          <div className="py-4 space-y-4">
            {/* Aktueller Kontrakt */}
            <div className="p-3 bg-gray-50 rounded-lg border">
              <div className="text-xs text-gray-500 mb-1">Aktueller Kontrakt</div>
              <div className="flex items-center gap-2">
                {selectedKontrakt?.vorgang_typ === 'EK' ? (
                  <ArrowDownToLine className="h-4 w-4 text-green-600" />
                ) : (
                  <ArrowUpFromLine className="h-4 w-4 text-blue-600" />
                )}
                <span className="font-mono font-medium">{selectedKontrakt?.kontraktnummer}</span>
                <span className="text-gray-500">-</span>
                <span>{selectedKontrakt?.name1}</span>
              </div>
            </div>

            {/* Partner-Kontrakt auswählen */}
            <div className="space-y-2">
              <Label>Partner-Kontrakt (optional)</Label>
              <Select onValueChange={(v) => {
                if (v && selectedKontrakt) {
                  verknuepfenMutation.mutate({ 
                    kontraktId: selectedKontrakt.id, 
                    partnerKontraktId: v === '__none__' ? undefined : v 
                  });
                }
              }}>
                <SelectTrigger>
                  <SelectValue placeholder="Optional: Partner-Kontrakt wählen..." />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="__none__">Ohne Partner (später verknüpfen)</SelectItem>
                  {kontrakteData?.data
                    ?.filter((k: Kontrakt) => 
                      k.vorgang_typ !== selectedKontrakt?.vorgang_typ && 
                      !k.ist_strecke &&
                      k.id !== selectedKontrakt?.id
                    )
                    .map((k: Kontrakt) => (
                      <SelectItem key={k.id} value={k.id}>
                        <div className="flex items-center gap-2">
                          {k.vorgang_typ === 'EK' ? (
                            <ArrowDownToLine className="h-4 w-4 text-green-600" />
                          ) : (
                            <ArrowUpFromLine className="h-4 w-4 text-blue-600" />
                          )}
                          <span className="font-mono">{k.kontraktnummer}</span>
                          <span className="text-gray-500">-</span>
                          <span>{k.name1}</span>
                        </div>
                      </SelectItem>
                    ))
                  }
                </SelectContent>
              </Select>
              <p className="text-xs text-gray-500">
                Ohne Partner wird ein neues Streckengeschäft erstellt, dem später ein zweiter Kontrakt hinzugefügt werden kann.
              </p>
            </div>
          </div>

          <DialogFooter>
            <Button variant="outline" onClick={() => setShowVerknuepfenDialog(false)}>Abbrechen</Button>
            <Button 
              onClick={() => {
                if (selectedKontrakt) {
                  verknuepfenMutation.mutate({ kontraktId: selectedKontrakt.id });
                }
              }}
              disabled={verknuepfenMutation.isPending}
              className="bg-orange-600 hover:bg-orange-700"
            >
              {verknuepfenMutation.isPending ? 'Wird verknüpft...' : 'Ohne Partner verknüpfen'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
