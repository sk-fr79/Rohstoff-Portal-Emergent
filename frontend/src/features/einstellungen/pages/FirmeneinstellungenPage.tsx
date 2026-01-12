import { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { useAuthStore } from '@/store/authStore';
import { toast } from 'sonner';
import { 
  Building2, Save, Loader2, Search, Star, MapPin, Phone, Mail,
  CreditCard, FileText, Globe, X, Check, ChevronDown, RefreshCw, Clock, Zap
} from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { ScrollArea } from '@/components/ui/scroll-area';
import {
  Command,
  CommandEmpty,
  CommandGroup,
  CommandInput,
  CommandItem,
  CommandList,
} from '@/components/ui/command';
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover';
import { cn } from '@/lib/utils';
import { api } from '@/services/api/client';

interface Lieferadresse {
  id: string;
  bezeichnung?: string;
  name1?: string;
  strasse?: string;
  hausnummer?: string;
  plz?: string;
  ort?: string;
  land?: string;
}

interface Bankverbindung {
  id: string;
  iban: string;
  bic?: string;
  bank_name?: string;
  kontoinhaber?: string;
  waehrung: string;
  ist_hauptkonto?: boolean;
}

interface UstId {
  id?: string;
  ust_id?: string;
  ustid?: string;
  lkz?: string;
  land?: string;
}

interface Adresse {
  id: string;
  name1: string;
  name2?: string;
  strasse?: string;
  hausnummer?: string;
  plz?: string;
  ort?: string;
  land?: string;
  telefon?: string;
  email?: string;
  ist_firmenadresse?: boolean;
}

interface FirmaDaten {
  id?: string;
  id_adresse?: string;
  name1?: string;
  name2?: string;
  strasse?: string;
  hausnummer?: string;
  plz?: string;
  ort?: string;
  land?: string;
  land_code?: string;
  telefon?: string;
  telefax?: string;
  email?: string;
  website?: string;
  ust_id?: string;
  steuernummer?: string;
  handelsregister?: string;
  handelsregister_gericht?: string;
  debitoren_nummer?: string;
  kreditoren_nummer?: string;
  weitere_ustids?: UstId[];
  lieferadressen?: Lieferadresse[];
  bankverbindungen?: Bankverbindung[];
  // Sync-Metadaten
  letzter_sync?: string;
  sync_quelle?: 'auto' | 'manuell';
  sync_von?: string;
}

// Helper für relative Zeitanzeige
function formatRelativeTime(isoString?: string): string {
  if (!isoString) return 'Nie';
  const date = new Date(isoString);
  const now = new Date();
  const diffMs = now.getTime() - date.getTime();
  const diffMins = Math.floor(diffMs / 60000);
  const diffHours = Math.floor(diffMs / 3600000);
  const diffDays = Math.floor(diffMs / 86400000);
  
  if (diffMins < 1) return 'Gerade eben';
  if (diffMins < 60) return `vor ${diffMins} Min.`;
  if (diffHours < 24) return `vor ${diffHours} Std.`;
  if (diffDays < 7) return `vor ${diffDays} Tagen`;
  return date.toLocaleDateString('de-DE');
}

export function FirmeneinstellungenPage() {
  const { accessToken: token } = useAuthStore();
  const queryClient = useQueryClient();
  const [adressePopoverOpen, setAdressePopoverOpen] = useState(false);
  const [searchTerm, setSearchTerm] = useState('');

  // Firmendaten laden
  const { data: firmaData, isLoading: isLoadingFirma } = useQuery({
    queryKey: ['firma'],
    queryFn: async () => {
      const response = await api.get('/system/firma');
      return response.data.data as FirmaDaten | null;
    }
  });

  // Adressen für Auswahl laden
  const { data: adressenData } = useQuery({
    queryKey: ['adressen-lookup', searchTerm],
    queryFn: async () => {
      const response = await api.get('/kontrakte/lookup/adressen', {
        params: { suche: searchTerm, limit: 50 }
      });
      return response.data.data as Adresse[];
    }
  });

  // Firma aus Adresse übernehmen
  const uebernehmeMutation = useMutation({
    mutationFn: async (adresseId: string) => {
      const response = await api.post(`/system/firma/aus-adresse/${adresseId}`);
      return response.data;
    },
    onSuccess: (data) => {
      toast.success(data.message || 'Firmendaten übernommen');
      queryClient.invalidateQueries({ queryKey: ['firma'] });
      queryClient.invalidateQueries({ queryKey: ['adressen-lookup'] });
      setAdressePopoverOpen(false);
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Übernehmen');
    }
  });

  // Firma löschen
  const loeschenMutation = useMutation({
    mutationFn: async () => {
      const response = await api.delete('/system/firma');
      return response.data;
    },
    onSuccess: () => {
      toast.success('Firmendaten gelöscht');
      queryClient.invalidateQueries({ queryKey: ['firma'] });
      queryClient.invalidateQueries({ queryKey: ['adressen-lookup'] });
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Löschen');
    }
  });

  // Firma manuell synchronisieren
  const syncMutation = useMutation({
    mutationFn: async () => {
      const response = await api.post('/system/firma/sync');
      return response.data;
    },
    onSuccess: (data) => {
      toast.success(data.message || 'Firmendaten synchronisiert');
      queryClient.invalidateQueries({ queryKey: ['firma'] });
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Synchronisieren');
    }
  });

  if (isLoadingFirma) {
    return (
      <div className="flex items-center justify-center h-64">
        <Loader2 className="h-8 w-8 animate-spin text-blue-600" />
      </div>
    );
  }

  return (
    <div className="space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-2xl font-bold text-gray-900 flex items-center gap-2">
            <Building2 className="h-6 w-6 text-blue-600" />
            Firmeneinstellungen
          </h1>
          <p className="text-gray-500 mt-1">
            Definieren Sie eine Adresse aus den Stammdaten als Firmenmandant
          </p>
        </div>
      </div>

      {/* Adresse auswählen */}
      <Card>
        <CardHeader>
          <CardTitle className="flex items-center gap-2">
            <Star className="h-5 w-5 text-yellow-500" />
            Firmenadresse auswählen
          </CardTitle>
          <CardDescription>
            Wählen Sie eine Adresse aus den Stammdaten, die als Firmenmandant verwendet werden soll.
            Diese Adresse wird mit einem ⭐ in der Adressliste markiert.
          </CardDescription>
        </CardHeader>
        <CardContent>
          <div className="flex items-center gap-4">
            <Popover open={adressePopoverOpen} onOpenChange={setAdressePopoverOpen}>
              <PopoverTrigger asChild>
                <Button variant="outline" className="w-[400px] justify-between">
                  {firmaData?.name1 ? (
                    <span className="flex items-center gap-2">
                      <Star className="h-4 w-4 text-yellow-500 fill-yellow-500" />
                      {firmaData.name1}
                    </span>
                  ) : (
                    <span className="text-muted-foreground">Adresse auswählen...</span>
                  )}
                  <ChevronDown className="h-4 w-4 opacity-50" />
                </Button>
              </PopoverTrigger>
              <PopoverContent className="w-[400px] p-0" align="start">
                <Command>
                  <CommandInput 
                    placeholder="Firma suchen..." 
                    value={searchTerm}
                    onValueChange={setSearchTerm}
                  />
                  <CommandList>
                    <CommandEmpty>Keine Adresse gefunden.</CommandEmpty>
                    <CommandGroup>
                      {adressenData?.map((adresse) => (
                        <CommandItem
                          key={adresse.id}
                          value={adresse.name1}
                          onSelect={() => uebernehmeMutation.mutate(adresse.id)}
                          className="cursor-pointer"
                        >
                          <div className="flex items-center gap-2 flex-1">
                            {adresse.ist_firmenadresse && (
                              <Star className="h-4 w-4 text-yellow-500 fill-yellow-500" />
                            )}
                            <div className="flex flex-col">
                              <span className="font-medium">{adresse.name1}</span>
                              <span className="text-xs text-gray-500">
                                {adresse.strasse} {adresse.hausnummer}, {adresse.plz} {adresse.ort}
                              </span>
                            </div>
                          </div>
                          {adresse.id === firmaData?.id_adresse && (
                            <Check className="h-4 w-4 text-green-600" />
                          )}
                        </CommandItem>
                      ))}
                    </CommandGroup>
                  </CommandList>
                </Command>
              </PopoverContent>
            </Popover>

            {firmaData && (
              <Button 
                variant="outline" 
                className="text-red-600 hover:text-red-700"
                onClick={() => {
                  if (confirm('Firmendaten wirklich löschen?')) {
                    loeschenMutation.mutate();
                  }
                }}
              >
                <X className="h-4 w-4 mr-2" />
                Zurücksetzen
              </Button>
            )}
          </div>
        </CardContent>
      </Card>

      {/* Firmendaten anzeigen */}
      {firmaData && (
        <>
          {/* Stammdaten */}
          <Card>
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <Building2 className="h-5 w-5 text-blue-600" />
                Stammdaten
              </CardTitle>
            </CardHeader>
            <CardContent className="grid grid-cols-2 gap-6">
              <div className="space-y-4">
                <div className="space-y-1.5">
                  <Label className="text-xs text-gray-500">Firmenname</Label>
                  <div className="font-semibold text-lg">{firmaData.name1}</div>
                  {firmaData.name2 && <div className="text-gray-600">{firmaData.name2}</div>}
                </div>
                <div className="space-y-1.5">
                  <Label className="text-xs text-gray-500 flex items-center gap-1">
                    <MapPin className="h-3 w-3" /> Adresse
                  </Label>
                  <div className="text-gray-900">
                    {firmaData.strasse} {firmaData.hausnummer}<br />
                    {firmaData.plz} {firmaData.ort}<br />
                    {firmaData.land}
                  </div>
                </div>
              </div>
              <div className="space-y-4">
                <div className="space-y-1.5">
                  <Label className="text-xs text-gray-500 flex items-center gap-1">
                    <Phone className="h-3 w-3" /> Kontakt
                  </Label>
                  <div className="text-gray-900">
                    {firmaData.telefon && <div>Tel: {firmaData.telefon}</div>}
                    {firmaData.telefax && <div>Fax: {firmaData.telefax}</div>}
                    {firmaData.email && <div>E-Mail: {firmaData.email}</div>}
                    {firmaData.website && <div>Web: {firmaData.website}</div>}
                  </div>
                </div>
              </div>
            </CardContent>
          </Card>

          {/* Steuer & Handelsregister */}
          <Card>
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <FileText className="h-5 w-5 text-green-600" />
                Steuer & Handelsregister
              </CardTitle>
            </CardHeader>
            <CardContent className="grid grid-cols-2 gap-6">
              <div className="space-y-4">
                <div className="space-y-1.5">
                  <Label className="text-xs text-gray-500">USt-ID (Hauptnummer)</Label>
                  <div className="font-mono text-gray-900">{firmaData.ust_id || '-'}</div>
                </div>
                {firmaData.weitere_ustids && firmaData.weitere_ustids.length > 0 && (
                  <div className="space-y-1.5">
                    <Label className="text-xs text-gray-500">Weitere USt-IDs</Label>
                    <div className="flex flex-wrap gap-2">
                      {firmaData.weitere_ustids.map((u, idx) => (
                        <Badge key={idx} variant="outline" className="font-mono">
                          {u.lkz}{u.ustid || u.ust_id}
                        </Badge>
                      ))}
                    </div>
                  </div>
                )}
                <div className="space-y-1.5">
                  <Label className="text-xs text-gray-500">Steuernummer</Label>
                  <div className="font-mono text-gray-900">{firmaData.steuernummer || '-'}</div>
                </div>
              </div>
              <div className="space-y-4">
                <div className="space-y-1.5">
                  <Label className="text-xs text-gray-500">Handelsregister</Label>
                  <div className="text-gray-900">{firmaData.handelsregister || '-'}</div>
                </div>
                <div className="space-y-1.5">
                  <Label className="text-xs text-gray-500">Registergericht</Label>
                  <div className="text-gray-900">{firmaData.handelsregister_gericht || '-'}</div>
                </div>
                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-1.5">
                    <Label className="text-xs text-gray-500">Debitoren-Nr.</Label>
                    <div className="font-mono text-gray-900">{firmaData.debitoren_nummer || '-'}</div>
                  </div>
                  <div className="space-y-1.5">
                    <Label className="text-xs text-gray-500">Kreditoren-Nr.</Label>
                    <div className="font-mono text-gray-900">{firmaData.kreditoren_nummer || '-'}</div>
                  </div>
                </div>
              </div>
            </CardContent>
          </Card>

          {/* Bankverbindungen */}
          {firmaData.bankverbindungen && firmaData.bankverbindungen.length > 0 && (
            <Card>
              <CardHeader>
                <CardTitle className="flex items-center gap-2">
                  <CreditCard className="h-5 w-5 text-violet-600" />
                  Bankverbindungen
                </CardTitle>
              </CardHeader>
              <CardContent>
                <div className="grid grid-cols-2 gap-4">
                  {firmaData.bankverbindungen.map((bank, idx) => (
                    <div 
                      key={idx} 
                      className={cn(
                        "p-4 rounded-lg border",
                        bank.ist_hauptkonto ? "border-violet-300 bg-violet-50" : "border-gray-200 bg-gray-50"
                      )}
                    >
                      <div className="flex items-center justify-between mb-2">
                        <span className="font-semibold">{bank.bank_name || 'Bank'}</span>
                        <div className="flex items-center gap-2">
                          <Badge variant="outline">{bank.waehrung}</Badge>
                          {bank.ist_hauptkonto && (
                            <Badge className="bg-violet-600">Hauptkonto</Badge>
                          )}
                        </div>
                      </div>
                      <div className="space-y-1 text-sm">
                        <div><span className="text-gray-500">IBAN:</span> <span className="font-mono">{bank.iban}</span></div>
                        {bank.bic && <div><span className="text-gray-500">BIC:</span> <span className="font-mono">{bank.bic}</span></div>}
                        {bank.kontoinhaber && <div><span className="text-gray-500">Inhaber:</span> {bank.kontoinhaber}</div>}
                      </div>
                    </div>
                  ))}
                </div>
              </CardContent>
            </Card>
          )}

          {/* Lieferadressen */}
          {firmaData.lieferadressen && firmaData.lieferadressen.length > 0 && (
            <Card>
              <CardHeader>
                <CardTitle className="flex items-center gap-2">
                  <Globe className="h-5 w-5 text-emerald-600" />
                  Lieferadressen / Standorte
                </CardTitle>
              </CardHeader>
              <CardContent>
                <div className="grid grid-cols-3 gap-4">
                  {firmaData.lieferadressen.map((la, idx) => (
                    <div key={idx} className="p-4 rounded-lg border border-gray-200 bg-gray-50">
                      <div className="font-semibold mb-2">{la.bezeichnung || la.name1 || `Standort ${idx + 1}`}</div>
                      <div className="text-sm text-gray-600">
                        {la.strasse} {la.hausnummer}<br />
                        {la.plz} {la.ort}<br />
                        {la.land}
                      </div>
                    </div>
                  ))}
                </div>
              </CardContent>
            </Card>
          )}
        </>
      )}

      {/* Keine Firma definiert */}
      {!firmaData && (
        <Card className="border-dashed">
          <CardContent className="flex flex-col items-center justify-center py-12">
            <Building2 className="h-16 w-16 text-gray-300 mb-4" />
            <h3 className="text-lg font-semibold text-gray-900 mb-2">Keine Firmenadresse definiert</h3>
            <p className="text-gray-500 text-center max-w-md mb-4">
              Wählen Sie eine Adresse aus den Stammdaten aus, um sie als Firmenmandant zu definieren.
              Diese Adresse wird dann für Module wie Kontrakte (Eigenes Lager) verwendet.
            </p>
          </CardContent>
        </Card>
      )}
    </div>
  );
}
