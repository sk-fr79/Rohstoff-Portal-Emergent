import { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { motion } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import {
  Plus,
  Search,
  Filter,
  MoreHorizontal,
  FileText,
  ArrowDownCircle,
  ArrowUpCircle,
  Calendar,
  Building2,
  CheckCircle,
  Clock,
  Loader2,
  ChevronDown,
} from 'lucide-react';
import { kontrakteApi, adressenApi } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Textarea } from '@/components/ui/textarea';
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from '@/components/ui/dialog';
import {
  DropdownMenu,
  DropdownMenuContent,
  DropdownMenuItem,
  DropdownMenuSeparator,
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';
import {
  Tabs,
  TabsContent,
  TabsList,
  TabsTrigger,
} from '@/components/ui/tabs';
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

const kontraktSchema = z.object({
  adresse_id: z.string().min(1, 'Adresse erforderlich'),
  ist_einkauf: z.boolean().default(true),
  gueltig_von: z.string().optional(),
  gueltig_bis: z.string().optional(),
  bemerkungen_intern: z.string().max(500).optional(),
});

type KontraktForm = z.infer<typeof kontraktSchema>;

interface Kontrakt {
  id: string;
  buchungsnummer: string;
  adresse_id: string;
  kdnr?: string;
  name1?: string;
  name2?: string;
  strasse?: string;
  plz?: string;
  ort?: string;
  ist_einkauf: boolean;
  gueltig_von?: string;
  gueltig_bis?: string;
  bemerkungen_intern?: string;
  abgeschlossen: boolean;
  positionen?: Array<{
    id: string;
    positionsnummer: number;
    artbez1?: string;
    menge?: number;
    einzelpreis?: number;
  }>;
  erstellt_am: string;
}

interface Adresse {
  id: string;
  name1: string;
  kdnr?: string;
  ort?: string;
}

export function KontraktePage() {
  const [searchTerm, setSearchTerm] = useState('');
  const [page, setPage] = useState(1);
  const [showCreateDialog, setShowCreateDialog] = useState(false);
  const [filterType, setFilterType] = useState<'all' | 'einkauf' | 'verkauf'>('all');
  const [filterStatus, setFilterStatus] = useState<'all' | 'offen' | 'abgeschlossen'>('all');
  const [adresseOpen, setAdresseOpen] = useState(false);
  const [selectedAdresse, setSelectedAdresse] = useState<Adresse | null>(null);
  const queryClient = useQueryClient();

  const { data, isLoading } = useQuery({
    queryKey: ['kontrakte', { suche: searchTerm, page, filterType, filterStatus }],
    queryFn: () => kontrakteApi.search({
      suche: searchTerm,
      page,
      limit: 20,
      ist_einkauf: filterType === 'all' ? undefined : filterType === 'einkauf',
      abgeschlossen: filterStatus === 'all' ? undefined : filterStatus === 'abgeschlossen',
    }),
    select: (res) => res.data,
  });

  const { data: adressenData } = useQuery({
    queryKey: ['adressen-select'],
    queryFn: () => adressenApi.search({ limit: 100, aktiv: true }),
    select: (res) => res.data?.data || [],
  });

  const createMutation = useMutation({
    mutationFn: (data: KontraktForm) => kontrakteApi.create(data),
    onSuccess: () => {
      toast.success('Kontrakt erfolgreich erstellt');
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
      setShowCreateDialog(false);
      reset();
      setSelectedAdresse(null);
    },
    onError: () => {
      toast.error('Fehler beim Erstellen des Kontrakts');
    },
  });

  const abschliessenMutation = useMutation({
    mutationFn: (id: string) => kontrakteApi.abschliessen(id),
    onSuccess: () => {
      toast.success('Kontrakt abgeschlossen');
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
    },
    onError: () => {
      toast.error('Fehler beim Abschließen des Kontrakts');
    },
  });

  const {
    register,
    handleSubmit,
    reset,
    setValue,
    watch,
    formState: { errors },
  } = useForm<KontraktForm>({
    resolver: zodResolver(kontraktSchema),
    defaultValues: {
      ist_einkauf: true,
    },
  });

  const istEinkauf = watch('ist_einkauf');

  const onSubmit = (data: KontraktForm) => {
    createMutation.mutate(data);
  };

  const formatDate = (dateString?: string) => {
    if (!dateString) return '-';
    return new Date(dateString).toLocaleDateString('de-DE');
  };

  return (
    <motion.div
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      className="space-y-6"
    >
      {/* Header */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold tracking-tight">Kontrakte</h1>
          <p className="text-muted-foreground">Verwalten Sie Ihre Ein- und Verkaufskontrakte</p>
        </div>
        <Button onClick={() => setShowCreateDialog(true)} data-testid="create-contract-btn">
          <Plus className="h-4 w-4 mr-2" />
          Neuer Kontrakt
        </Button>
      </div>

      {/* Tabs für Typ-Filter */}
      <Tabs value={filterType} onValueChange={(v) => setFilterType(v as 'all' | 'einkauf' | 'verkauf')}>
        <div className="flex flex-col sm:flex-row gap-4 items-start sm:items-center justify-between">
          <TabsList>
            <TabsTrigger value="all" data-testid="filter-all">Alle</TabsTrigger>
            <TabsTrigger value="einkauf" data-testid="filter-einkauf">
              <ArrowDownCircle className="h-4 w-4 mr-2 text-green-500" />
              Einkauf
            </TabsTrigger>
            <TabsTrigger value="verkauf" data-testid="filter-verkauf">
              <ArrowUpCircle className="h-4 w-4 mr-2 text-blue-500" />
              Verkauf
            </TabsTrigger>
          </TabsList>

          <div className="flex gap-2">
            <Select value={filterStatus} onValueChange={(v) => setFilterStatus(v as 'all' | 'offen' | 'abgeschlossen')}>
              <SelectTrigger className="w-[150px]">
                <SelectValue placeholder="Status" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="all">Alle Status</SelectItem>
                <SelectItem value="offen">Offen</SelectItem>
                <SelectItem value="abgeschlossen">Abgeschlossen</SelectItem>
              </SelectContent>
            </Select>
          </div>
        </div>

        {/* Suche */}
        <Card className="mt-4">
          <CardContent className="p-4">
            <div className="relative">
              <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
              <Input
                placeholder="Suchen nach Buchungsnummer, Name..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="pl-10"
                data-testid="contract-search"
              />
            </div>
          </CardContent>
        </Card>

        <TabsContent value={filterType} className="mt-4">
          {/* Kontraktliste */}
          {isLoading ? (
            <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
              {[...Array(6)].map((_, i) => (
                <Card key={i} className="animate-pulse">
                  <CardHeader className="pb-2">
                    <div className="h-5 bg-muted rounded w-3/4" />
                  </CardHeader>
                  <CardContent>
                    <div className="space-y-2">
                      <div className="h-4 bg-muted rounded w-1/2" />
                      <div className="h-4 bg-muted rounded w-2/3" />
                    </div>
                  </CardContent>
                </Card>
              ))}
            </div>
          ) : data?.data?.length > 0 ? (
            <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
              {data.data.map((kontrakt: Kontrakt) => (
                <motion.div
                  key={kontrakt.id}
                  initial={{ opacity: 0, y: 20 }}
                  animate={{ opacity: 1, y: 0 }}
                  whileHover={{ scale: 1.02 }}
                  transition={{ duration: 0.2 }}
                >
                  <Card className="cursor-pointer hover:shadow-lg transition-shadow" data-testid={`contract-card-${kontrakt.id}`}>
                    <CardHeader className="pb-2">
                      <div className="flex items-start justify-between">
                        <div className="flex items-center gap-3">
                          <div className={`h-10 w-10 rounded-lg flex items-center justify-center ${
                            kontrakt.ist_einkauf ? 'bg-green-500/10' : 'bg-blue-500/10'
                          }`}>
                            {kontrakt.ist_einkauf ? (
                              <ArrowDownCircle className="h-5 w-5 text-green-500" />
                            ) : (
                              <ArrowUpCircle className="h-5 w-5 text-blue-500" />
                            )}
                          </div>
                          <div>
                            <CardTitle className="text-base font-mono">
                              {kontrakt.buchungsnummer}
                            </CardTitle>
                            <p className="text-xs text-muted-foreground">
                              {kontrakt.ist_einkauf ? 'Einkaufskontrakt' : 'Verkaufskontrakt'}
                            </p>
                          </div>
                        </div>
                        <DropdownMenu>
                          <DropdownMenuTrigger asChild>
                            <Button variant="ghost" size="icon">
                              <MoreHorizontal className="h-4 w-4" />
                            </Button>
                          </DropdownMenuTrigger>
                          <DropdownMenuContent align="end">
                            <DropdownMenuItem>Details anzeigen</DropdownMenuItem>
                            <DropdownMenuItem>Position hinzufügen</DropdownMenuItem>
                            <DropdownMenuSeparator />
                            {!kontrakt.abgeschlossen && (
                              <DropdownMenuItem
                                onClick={() => abschliessenMutation.mutate(kontrakt.id)}
                              >
                                <CheckCircle className="h-4 w-4 mr-2" />
                                Abschließen
                              </DropdownMenuItem>
                            )}
                          </DropdownMenuContent>
                        </DropdownMenu>
                      </div>
                    </CardHeader>
                    <CardContent className="space-y-3">
                      <div className="flex items-center gap-2 text-sm">
                        <Building2 className="h-4 w-4 text-muted-foreground flex-shrink-0" />
                        <span className="font-medium">{kontrakt.name1}</span>
                        {kontrakt.kdnr && (
                          <span className="text-muted-foreground">({kontrakt.kdnr})</span>
                        )}
                      </div>
                      
                      {kontrakt.ort && (
                        <p className="text-sm text-muted-foreground">
                          {kontrakt.plz} {kontrakt.ort}
                        </p>
                      )}

                      <div className="flex items-center gap-2 text-sm text-muted-foreground">
                        <Calendar className="h-4 w-4 flex-shrink-0" />
                        <span>
                          {formatDate(kontrakt.gueltig_von)} - {formatDate(kontrakt.gueltig_bis)}
                        </span>
                      </div>

                      {kontrakt.positionen && kontrakt.positionen.length > 0 && (
                        <p className="text-sm text-muted-foreground">
                          {kontrakt.positionen.length} Position(en)
                        </p>
                      )}

                      <div className="pt-2 flex items-center gap-2">
                        <span className={`inline-flex items-center px-2 py-0.5 rounded-full text-xs font-medium ${
                          kontrakt.abgeschlossen 
                            ? 'bg-gray-500/10 text-gray-500' 
                            : 'bg-green-500/10 text-green-500'
                        }`}>
                          {kontrakt.abgeschlossen ? (
                            <><CheckCircle className="h-3 w-3 mr-1" /> Abgeschlossen</>
                          ) : (
                            <><Clock className="h-3 w-3 mr-1" /> Offen</>
                          )}
                        </span>
                      </div>
                    </CardContent>
                  </Card>
                </motion.div>
              ))}
            </div>
          ) : (
            <Card>
              <CardContent className="flex flex-col items-center justify-center py-12">
                <FileText className="h-12 w-12 text-muted-foreground/50 mb-4" />
                <h3 className="text-lg font-medium">Keine Kontrakte gefunden</h3>
                <p className="text-muted-foreground text-sm mt-1">
                  {searchTerm ? 'Versuchen Sie andere Suchbegriffe' : 'Erstellen Sie Ihren ersten Kontrakt'}
                </p>
              </CardContent>
            </Card>
          )}
        </TabsContent>
      </Tabs>

      {/* Pagination */}
      {data?.pagination && data.pagination.total_pages > 1 && (
        <div className="flex justify-center gap-2">
          <Button
            variant="outline"
            onClick={() => setPage(p => Math.max(1, p - 1))}
            disabled={page === 1}
          >
            Zurück
          </Button>
          <span className="flex items-center px-4 text-sm text-muted-foreground">
            Seite {page} von {data.pagination.total_pages}
          </span>
          <Button
            variant="outline"
            onClick={() => setPage(p => p + 1)}
            disabled={page >= data.pagination.total_pages}
          >
            Weiter
          </Button>
        </div>
      )}

      {/* Create Dialog */}
      <Dialog open={showCreateDialog} onOpenChange={setShowCreateDialog}>
        <DialogContent className="sm:max-w-[500px]">
          <DialogHeader>
            <DialogTitle>Neuen Kontrakt erstellen</DialogTitle>
            <DialogDescription>
              Erfassen Sie die Grunddaten für einen neuen Kontrakt.
            </DialogDescription>
          </DialogHeader>
          <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
            {/* Kontrakttyp */}
            <div className="space-y-2">
              <Label>Kontrakttyp</Label>
              <div className="grid grid-cols-2 gap-2">
                <Button
                  type="button"
                  variant={istEinkauf ? 'default' : 'outline'}
                  className={istEinkauf ? 'bg-green-600 hover:bg-green-700' : ''}
                  onClick={() => setValue('ist_einkauf', true)}
                >
                  <ArrowDownCircle className="h-4 w-4 mr-2" />
                  Einkauf
                </Button>
                <Button
                  type="button"
                  variant={!istEinkauf ? 'default' : 'outline'}
                  className={!istEinkauf ? 'bg-blue-600 hover:bg-blue-700' : ''}
                  onClick={() => setValue('ist_einkauf', false)}
                >
                  <ArrowUpCircle className="h-4 w-4 mr-2" />
                  Verkauf
                </Button>
              </div>
            </div>

            {/* Adresse auswählen */}
            <div className="space-y-2">
              <Label>Adresse *</Label>
              <Popover open={adresseOpen} onOpenChange={setAdresseOpen}>
                <PopoverTrigger asChild>
                  <Button
                    variant="outline"
                    role="combobox"
                    aria-expanded={adresseOpen}
                    className="w-full justify-between"
                    data-testid="select-address-btn"
                  >
                    {selectedAdresse ? (
                      <span>{selectedAdresse.name1} ({selectedAdresse.kdnr})</span>
                    ) : (
                      <span className="text-muted-foreground">Adresse auswählen...</span>
                    )}
                    <ChevronDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
                  </Button>
                </PopoverTrigger>
                <PopoverContent className="w-[400px] p-0">
                  <Command>
                    <CommandInput placeholder="Suchen..." />
                    <CommandList>
                      <CommandEmpty>Keine Adresse gefunden.</CommandEmpty>
                      <CommandGroup>
                        {adressenData?.map((adresse: Adresse) => (
                          <CommandItem
                            key={adresse.id}
                            value={adresse.name1}
                            onSelect={() => {
                              setSelectedAdresse(adresse);
                              setValue('adresse_id', adresse.id);
                              setAdresseOpen(false);
                            }}
                          >
                            <div className="flex flex-col">
                              <span>{adresse.name1}</span>
                              <span className="text-xs text-muted-foreground">
                                {adresse.kdnr} - {adresse.ort}
                              </span>
                            </div>
                          </CommandItem>
                        ))}
                      </CommandGroup>
                    </CommandList>
                  </Command>
                </PopoverContent>
              </Popover>
              {errors.adresse_id && (
                <p className="text-sm text-destructive">{errors.adresse_id.message}</p>
              )}
            </div>

            {/* Gültigkeit */}
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label htmlFor="gueltig_von">Gültig von</Label>
                <Input
                  id="gueltig_von"
                  type="date"
                  {...register('gueltig_von')}
                />
              </div>
              <div className="space-y-2">
                <Label htmlFor="gueltig_bis">Gültig bis</Label>
                <Input
                  id="gueltig_bis"
                  type="date"
                  {...register('gueltig_bis')}
                />
              </div>
            </div>

            {/* Bemerkungen */}
            <div className="space-y-2">
              <Label htmlFor="bemerkungen_intern">Interne Bemerkungen</Label>
              <Textarea
                id="bemerkungen_intern"
                {...register('bemerkungen_intern')}
                placeholder="Optionale Notizen zum Kontrakt..."
                rows={3}
              />
            </div>

            <DialogFooter>
              <Button type="button" variant="outline" onClick={() => setShowCreateDialog(false)}>
                Abbrechen
              </Button>
              <Button type="submit" disabled={createMutation.isPending} data-testid="contract-submit-btn">
                {createMutation.isPending ? (
                  <>
                    <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                    Speichern...
                  </>
                ) : (
                  'Kontrakt erstellen'
                )}
              </Button>
            </DialogFooter>
          </form>
        </DialogContent>
      </Dialog>
    </motion.div>
  );
}
