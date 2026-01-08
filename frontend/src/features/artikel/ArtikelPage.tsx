import { useState, useMemo } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import { Plus, MoreHorizontal, Pencil, Trash2, Package, AlertTriangle, Check, X, Loader2 } from 'lucide-react';
import { artikelApi } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { DataTable } from '@/components/ui/data-table';
import { Switch } from '@/components/ui/switch';
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuSeparator, DropdownMenuTrigger } from '@/components/ui/dropdown-menu';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';

const artikelSchema = z.object({
  artbez1: z.string().min(1, 'Artikelbezeichnung erforderlich').max(80),
  artbez2: z.string().max(1000).optional(),
  einheit: z.string().max(10).default('kg'),
  einheit_preis: z.string().max(10).default('t'),
  mengendivisor: z.number().min(1).default(1000),
  aktiv: z.boolean().default(true),
  gefahrgut: z.boolean().default(false),
  eakcode: z.string().max(20).optional(),
});

type ArtikelForm = z.infer<typeof artikelSchema>;

interface Artikel {
  id: string;
  artbez1: string;
  artbez2?: string;
  einheit: string;
  einheit_preis: string;
  mengendivisor: number;
  aktiv: boolean;
  gefahrgut: boolean;
  eakcode?: string;
}

export function ArtikelPage() {
  const [searchTerm, setSearchTerm] = useState('');
  const [page, setPage] = useState(1);
  const [limit, setLimit] = useState(20);
  const [showCreateDialog, setShowCreateDialog] = useState(false);
  const queryClient = useQueryClient();

  const { data, isLoading } = useQuery({
    queryKey: ['artikel', { suche: searchTerm, page, limit }],
    queryFn: () => artikelApi.search({ suche: searchTerm, page, limit }),
    select: (res) => res.data,
  });

  const createMutation = useMutation({
    mutationFn: (data: ArtikelForm) => artikelApi.create(data),
    onSuccess: () => {
      toast.success('Artikel erfolgreich erstellt');
      queryClient.invalidateQueries({ queryKey: ['artikel'] });
      setShowCreateDialog(false);
      reset();
    },
    onError: () => toast.error('Fehler beim Erstellen des Artikels'),
  });

  const { register, handleSubmit, reset, setValue, watch, formState: { errors } } = useForm<ArtikelForm>({
    resolver: zodResolver(artikelSchema),
    defaultValues: { einheit: 'kg', einheit_preis: 't', mengendivisor: 1000, aktiv: true, gefahrgut: false },
  });

  const gefahrgut = watch('gefahrgut');
  const aktiv = watch('aktiv');

  const columns: ColumnDef<Artikel>[] = useMemo(() => [
    { accessorKey: 'artbez1', header: 'Artikelbezeichnung', cell: ({ row }) => (
      <div className="flex items-center gap-2">
        <div className={`h-8 w-8 rounded flex items-center justify-center ${row.original.gefahrgut ? 'bg-orange-500/10' : 'bg-primary/10'}`}>
          {row.original.gefahrgut ? <AlertTriangle className="h-4 w-4 text-orange-500" /> : <Package className="h-4 w-4 text-primary" />}
        </div>
        <div>
          <span className="font-medium">{row.getValue('artbez1')}</span>
          {row.original.artbez2 && <p className="text-xs text-muted-foreground truncate max-w-[200px]">{row.original.artbez2}</p>}
        </div>
      </div>
    )},
    { accessorKey: 'eakcode', header: 'EAK-Code', size: 120, cell: ({ row }) => <span className="font-mono">{row.getValue('eakcode') || '-'}</span> },
    { accessorKey: 'einheit', header: 'Einheit', size: 80, cell: ({ row }) => row.getValue('einheit') },
    { accessorKey: 'einheit_preis', header: 'Preis pro', size: 100, cell: ({ row }) => row.getValue('einheit_preis') },
    { accessorKey: 'gefahrgut', header: 'Gefahrgut', size: 100, cell: ({ row }) => row.getValue('gefahrgut') ? (
      <span className="inline-flex items-center gap-1 px-2 py-0.5 rounded-full text-xs font-medium bg-orange-500/10 text-orange-500"><AlertTriangle className="h-3 w-3" />Ja</span>
    ) : <span className="text-muted-foreground">Nein</span> },
    { accessorKey: 'aktiv', header: 'Status', size: 100, cell: ({ row }) => row.getValue('aktiv') ? (
      <span className="inline-flex items-center gap-1 px-2 py-0.5 rounded-full text-xs font-medium bg-green-500/10 text-green-500"><Check className="h-3 w-3" />Aktiv</span>
    ) : <span className="inline-flex items-center gap-1 px-2 py-0.5 rounded-full text-xs font-medium bg-red-500/10 text-red-500"><X className="h-3 w-3" />Inaktiv</span> },
    { id: 'actions', size: 60, cell: ({ row }) => (
      <DropdownMenu>
        <DropdownMenuTrigger asChild><Button variant="ghost" size="icon" className="h-8 w-8"><MoreHorizontal className="h-4 w-4" /></Button></DropdownMenuTrigger>
        <DropdownMenuContent align="end">
          <DropdownMenuItem><Pencil className="h-4 w-4 mr-2" />Bearbeiten</DropdownMenuItem>
          <DropdownMenuSeparator />
          <DropdownMenuItem className="text-destructive"><Trash2 className="h-4 w-4 mr-2" />Deaktivieren</DropdownMenuItem>
        </DropdownMenuContent>
      </DropdownMenu>
    )},
  ], []);

  return (
    <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="space-y-6">
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold tracking-tight">Artikel</h1>
          <p className="text-muted-foreground">{data?.pagination?.total || 0} Artikel verwalten</p>
        </div>
        <Button onClick={() => setShowCreateDialog(true)} data-testid="create-article-btn"><Plus className="h-4 w-4 mr-2" />Neuer Artikel</Button>
      </div>

      <DataTable columns={columns} data={data?.data || []} isLoading={isLoading} searchPlaceholder="Suchen nach Artikelbezeichnung, EAK-Code..."
        onSearchChange={(s) => { setSearchTerm(s); setPage(1); }}
        pagination={data?.pagination ? { page: data.pagination.page, limit: data.pagination.limit, total: data.pagination.total, totalPages: data.pagination.total_pages } : undefined}
        onPageChange={setPage} onLimitChange={(l) => { setLimit(l); setPage(1); }} />

      <Dialog open={showCreateDialog} onOpenChange={setShowCreateDialog}>
        <DialogContent className="sm:max-w-[500px]">
          <DialogHeader><DialogTitle>Neuen Artikel erstellen</DialogTitle><DialogDescription>Erfassen Sie die Stammdaten für einen neuen Artikel.</DialogDescription></DialogHeader>
          <form onSubmit={handleSubmit((d) => createMutation.mutate(d))} className="space-y-4">
            <div className="space-y-2"><Label>Artikelbezeichnung *</Label><Input {...register('artbez1')} placeholder="z.B. Kupferschrott I" data-testid="article-name-input" />{errors.artbez1 && <p className="text-sm text-destructive">{errors.artbez1.message}</p>}</div>
            <div className="space-y-2"><Label>Zusätzliche Beschreibung</Label><Input {...register('artbez2')} placeholder="Detaillierte Beschreibung..." /></div>
            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2"><Label>Einheit</Label><Select defaultValue="kg" onValueChange={(v) => setValue('einheit', v)}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="kg">Kilogramm (kg)</SelectItem><SelectItem value="t">Tonne (t)</SelectItem><SelectItem value="stk">Stück (stk)</SelectItem><SelectItem value="l">Liter (l)</SelectItem></SelectContent></Select></div>
              <div className="space-y-2"><Label>Preis pro</Label><Select defaultValue="t" onValueChange={(v) => setValue('einheit_preis', v)}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="kg">pro kg</SelectItem><SelectItem value="t">pro Tonne</SelectItem><SelectItem value="stk">pro Stück</SelectItem></SelectContent></Select></div>
            </div>
            <div className="space-y-2"><Label>EAK-Code</Label><Input {...register('eakcode')} placeholder="z.B. 17 04 01" /></div>
            <div className="flex items-center justify-between">
              <div className="flex items-center space-x-2"><Switch id="gefahrgut" checked={gefahrgut} onCheckedChange={(c) => setValue('gefahrgut', c)} /><Label htmlFor="gefahrgut" className="flex items-center gap-2"><AlertTriangle className="h-4 w-4 text-orange-500" />Gefahrgut</Label></div>
              <div className="flex items-center space-x-2"><Switch id="aktiv" checked={aktiv} onCheckedChange={(c) => setValue('aktiv', c)} /><Label htmlFor="aktiv">Aktiv</Label></div>
            </div>
            <DialogFooter><Button type="button" variant="outline" onClick={() => setShowCreateDialog(false)}>Abbrechen</Button><Button type="submit" disabled={createMutation.isPending} data-testid="article-submit-btn">{createMutation.isPending ? <><Loader2 className="mr-2 h-4 w-4 animate-spin" />Speichern...</> : 'Artikel erstellen'}</Button></DialogFooter>
          </form>
        </DialogContent>
      </Dialog>
    </motion.div>
  );
}
