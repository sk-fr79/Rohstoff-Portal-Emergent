import { useState, useMemo } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import { Plus, MoreHorizontal, Eye, ArrowDownCircle, ArrowUpCircle, CheckCircle, Clock, ChevronDown, Loader2, Building2, Pencil } from 'lucide-react';
import { kontrakteApi, adressenApi } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { DataTable } from '@/components/ui/data-table';
import { Textarea } from '@/components/ui/textarea';
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuSeparator, DropdownMenuTrigger } from '@/components/ui/dropdown-menu';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Tabs, TabsList, TabsTrigger } from '@/components/ui/tabs';
import { Popover, PopoverContent, PopoverTrigger } from '@/components/ui/popover';
import { Command, CommandEmpty, CommandGroup, CommandInput, CommandItem, CommandList } from '@/components/ui/command';

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
  plz?: string;
  ort?: string;
  ist_einkauf: boolean;
  gueltig_von?: string;
  gueltig_bis?: string;
  abgeschlossen: boolean;
  positionen?: { id: string }[];
  erstellt_am: string;
}

interface Adresse { id: string; name1: string; kdnr?: string; ort?: string; }

export function KontraktePage() {
  const [searchTerm, setSearchTerm] = useState('');
  const [page, setPage] = useState(1);
  const [limit, setLimit] = useState(20);
  const [filterType, setFilterType] = useState<'all' | 'einkauf' | 'verkauf'>('all');
  const [filterStatus, setFilterStatus] = useState<'all' | 'offen' | 'abgeschlossen'>('all');
  const [showCreateDialog, setShowCreateDialog] = useState(false);
  const [adresseOpen, setAdresseOpen] = useState(false);
  const [selectedAdresse, setSelectedAdresse] = useState<Adresse | null>(null);
  const [selectedKontrakt, setSelectedKontrakt] = useState<Kontrakt | null>(null);
  const [isEditMode, setIsEditMode] = useState(false);
  const queryClient = useQueryClient();

  const { data, isLoading } = useQuery({
    queryKey: ['kontrakte', { suche: searchTerm, page, limit, filterType, filterStatus }],
    queryFn: () => kontrakteApi.search({
      suche: searchTerm, page, limit,
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
    onError: () => toast.error('Fehler beim Erstellen des Kontrakts'),
  });

  const abschliessenMutation = useMutation({
    mutationFn: (id: string) => kontrakteApi.abschliessen(id),
    onSuccess: () => {
      toast.success('Kontrakt abgeschlossen');
      queryClient.invalidateQueries({ queryKey: ['kontrakte'] });
    },
    onError: () => toast.error('Fehler beim Abschließen'),
  });

  const { register, handleSubmit, reset, setValue, watch, formState: { errors } } = useForm<KontraktForm>({
    resolver: zodResolver(kontraktSchema),
    defaultValues: { ist_einkauf: true },
  });

  const istEinkauf = watch('ist_einkauf');

  const formatDate = (d?: string) => d ? new Date(d).toLocaleDateString('de-DE') : '-';

  const handleRowDoubleClick = (kontrakt: Kontrakt) => {
    setSelectedKontrakt(kontrakt);
    setIsEditMode(false);
  };

  const handleCloseDetail = () => {
    setSelectedKontrakt(null);
    setIsEditMode(false);
  };

  const columns: ColumnDef<Kontrakt>[] = useMemo(() => [
    { accessorKey: 'buchungsnummer', header: 'Buchungsnummer', cell: ({ row }) => (
      <div className="flex items-center gap-2">
        <div className={`h-8 w-8 rounded flex items-center justify-center ${row.original.ist_einkauf ? 'bg-green-500/10' : 'bg-blue-500/10'}`}>
          {row.original.ist_einkauf ? <ArrowDownCircle className="h-4 w-4 text-green-500" /> : <ArrowUpCircle className="h-4 w-4 text-blue-500" />}
        </div>
        <div>
          <span className="font-mono font-medium">{row.getValue('buchungsnummer')}</span>
          <p className="text-xs text-muted-foreground">{row.original.ist_einkauf ? 'Einkauf' : 'Verkauf'}</p>
        </div>
      </div>
    )},
    { accessorKey: 'kdnr', header: 'KDNR', size: 100, cell: ({ row }) => <span className="font-mono">{row.getValue('kdnr') || '-'}</span> },
    { accessorKey: 'name1', header: 'Partner', cell: ({ row }) => (
      <div className="flex items-center gap-2"><Building2 className="h-4 w-4 text-muted-foreground" /><span>{row.getValue('name1')}</span></div>
    )},
    { id: 'ortDisplay', header: 'Ort', cell: ({ row }) => row.original.plz || row.original.ort ? `${row.original.plz || ''} ${row.original.ort || ''}`.trim() : '-' },
    { id: 'gueltig', header: 'Gültigkeit', cell: ({ row }) => `${formatDate(row.original.gueltig_von)} - ${formatDate(row.original.gueltig_bis)}` },
    { id: 'positionen', header: 'Pos.', size: 60, cell: ({ row }) => row.original.positionen?.length || 0 },
    { accessorKey: 'abgeschlossen', header: 'Status', size: 120, cell: ({ row }) => row.getValue('abgeschlossen') ? (
      <span className="inline-flex items-center gap-1 px-2 py-0.5 rounded-full text-xs font-medium bg-gray-500/10 text-gray-500"><CheckCircle className="h-3 w-3" />Abgeschlossen</span>
    ) : <span className="inline-flex items-center gap-1 px-2 py-0.5 rounded-full text-xs font-medium bg-green-500/10 text-green-500"><Clock className="h-3 w-3" />Offen</span> },
    { id: 'actions', size: 60, cell: ({ row }) => (
      <DropdownMenu>
        <DropdownMenuTrigger asChild><Button variant="ghost" size="icon" className="h-8 w-8"><MoreHorizontal className="h-4 w-4" /></Button></DropdownMenuTrigger>
        <DropdownMenuContent align="end">
          <DropdownMenuItem onClick={() => handleRowDoubleClick(row.original)}><Eye className="h-4 w-4 mr-2" />Details</DropdownMenuItem>
          <DropdownMenuItem onClick={() => { setSelectedKontrakt(row.original); setIsEditMode(true); }}><Pencil className="h-4 w-4 mr-2" />Bearbeiten</DropdownMenuItem>
          <DropdownMenuItem>Position hinzufügen</DropdownMenuItem>
          {!row.original.abgeschlossen && <><DropdownMenuSeparator /><DropdownMenuItem onClick={() => abschliessenMutation.mutate(row.original.id)}><CheckCircle className="h-4 w-4 mr-2" />Abschließen</DropdownMenuItem></>}
        </DropdownMenuContent>
      </DropdownMenu>
    )},
  ], [abschliessenMutation]);

  return (
    <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="space-y-6">
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold tracking-tight">Kontrakte</h1>
          <p className="text-muted-foreground">{data?.pagination?.total || 0} Kontrakte verwalten · Doppelklick für Details</p>
        </div>
        <Button onClick={() => setShowCreateDialog(true)} data-testid="create-contract-btn"><Plus className="h-4 w-4 mr-2" />Neuer Kontrakt</Button>
      </div>

      <div className="flex flex-col sm:flex-row gap-4 items-start sm:items-center justify-between">
        <Tabs value={filterType} onValueChange={(v) => { setFilterType(v as 'all' | 'einkauf' | 'verkauf'); setPage(1); }}>
          <TabsList>
            <TabsTrigger value="all">Alle</TabsTrigger>
            <TabsTrigger value="einkauf"><ArrowDownCircle className="h-4 w-4 mr-2 text-green-500" />Einkauf</TabsTrigger>
            <TabsTrigger value="verkauf"><ArrowUpCircle className="h-4 w-4 mr-2 text-blue-500" />Verkauf</TabsTrigger>
          </TabsList>
        </Tabs>
        <Select value={filterStatus} onValueChange={(v) => { setFilterStatus(v as 'all' | 'offen' | 'abgeschlossen'); setPage(1); }}>
          <SelectTrigger className="w-[150px]"><SelectValue /></SelectTrigger>
          <SelectContent><SelectItem value="all">Alle Status</SelectItem><SelectItem value="offen">Offen</SelectItem><SelectItem value="abgeschlossen">Abgeschlossen</SelectItem></SelectContent>
        </Select>
      </div>

      <DataTable columns={columns} data={data?.data || []} isLoading={isLoading} searchPlaceholder="Suchen nach Buchungsnummer, Name..."
        onSearchChange={(s) => { setSearchTerm(s); setPage(1); }}
        onRowDoubleClick={handleRowDoubleClick}
        pagination={data?.pagination ? { page: data.pagination.page, limit: data.pagination.limit, total: data.pagination.total, totalPages: data.pagination.total_pages } : undefined}
        onPageChange={setPage} onLimitChange={(l) => { setLimit(l); setPage(1); }} />

      <Dialog open={showCreateDialog} onOpenChange={setShowCreateDialog}>
        <DialogContent className="sm:max-w-[500px]">
          <DialogHeader><DialogTitle>Neuen Kontrakt erstellen</DialogTitle><DialogDescription>Erfassen Sie die Grunddaten für einen neuen Kontrakt.</DialogDescription></DialogHeader>
          <form onSubmit={handleSubmit((d) => createMutation.mutate(d))} className="space-y-4">
            <div className="space-y-2"><Label>Kontrakttyp</Label>
              <div className="grid grid-cols-2 gap-2">
                <Button type="button" variant={istEinkauf ? 'default' : 'outline'} className={istEinkauf ? 'bg-green-600 hover:bg-green-700' : ''} onClick={() => setValue('ist_einkauf', true)}><ArrowDownCircle className="h-4 w-4 mr-2" />Einkauf</Button>
                <Button type="button" variant={!istEinkauf ? 'default' : 'outline'} className={!istEinkauf ? 'bg-blue-600 hover:bg-blue-700' : ''} onClick={() => setValue('ist_einkauf', false)}><ArrowUpCircle className="h-4 w-4 mr-2" />Verkauf</Button>
              </div>
            </div>
            <div className="space-y-2"><Label>Adresse *</Label>
              <Popover open={adresseOpen} onOpenChange={setAdresseOpen}>
                <PopoverTrigger asChild>
                  <Button variant="outline" role="combobox" className="w-full justify-between" data-testid="select-address-btn">
                    {selectedAdresse ? <span>{selectedAdresse.name1} ({selectedAdresse.kdnr})</span> : <span className="text-muted-foreground">Adresse auswählen...</span>}
                    <ChevronDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
                  </Button>
                </PopoverTrigger>
                <PopoverContent className="w-[400px] p-0">
                  <Command><CommandInput placeholder="Suchen..." /><CommandList><CommandEmpty>Keine Adresse gefunden.</CommandEmpty><CommandGroup>
                    {adressenData?.map((a: Adresse) => (
                      <CommandItem key={a.id} value={a.name1} onSelect={() => { setSelectedAdresse(a); setValue('adresse_id', a.id); setAdresseOpen(false); }}>
                        <div className="flex flex-col"><span>{a.name1}</span><span className="text-xs text-muted-foreground">{a.kdnr} - {a.ort}</span></div>
                      </CommandItem>
                    ))}
                  </CommandGroup></CommandList></Command>
                </PopoverContent>
              </Popover>
              {errors.adresse_id && <p className="text-sm text-destructive">{errors.adresse_id.message}</p>}
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2"><Label>Gültig von</Label><Input type="date" {...register('gueltig_von')} /></div>
              <div className="space-y-2"><Label>Gültig bis</Label><Input type="date" {...register('gueltig_bis')} /></div>
            </div>
            <div className="space-y-2"><Label>Interne Bemerkungen</Label><Textarea {...register('bemerkungen_intern')} placeholder="Optionale Notizen..." rows={3} /></div>
            <DialogFooter><Button type="button" variant="outline" onClick={() => setShowCreateDialog(false)}>Abbrechen</Button><Button type="submit" disabled={createMutation.isPending} data-testid="contract-submit-btn">{createMutation.isPending ? <><Loader2 className="mr-2 h-4 w-4 animate-spin" />Speichern...</> : 'Kontrakt erstellen'}</Button></DialogFooter>
          </form>
        </DialogContent>
      </Dialog>
    </motion.div>
  );
}
