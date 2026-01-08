import { useState, useMemo } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import { Plus, MoreHorizontal, Pencil, Trash2, Eye, Building2, User, MapPin, Save } from 'lucide-react';
import { adressenApi } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { DataTable } from '@/components/ui/data-table';
import { Dialog, DialogContent, DialogDescription, DialogFooter, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuSeparator, DropdownMenuTrigger } from '@/components/ui/dropdown-menu';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Textarea } from '@/components/ui/textarea';

const adresseSchema = z.object({
  name1: z.string().min(1, 'Firma/Name erforderlich').max(40),
  name2: z.string().max(40).optional(),
  name3: z.string().max(40).optional(),
  strasse: z.string().max(45).optional(),
  hausnummer: z.string().max(10).optional(),
  plz: z.string().max(10).optional(),
  ort: z.string().max(30).optional(),
  land: z.string().max(30).optional(),
  telefon: z.string().max(30).optional(),
  email: z.string().email().optional().or(z.literal('')),
  adresstyp: z.number().min(1).max(5).default(1),
  betreuer: z.string().max(20).optional(),
  bemerkungen: z.string().max(700).optional(),
});

type AdresseForm = z.infer<typeof adresseSchema>;

interface Adresse {
  id: string;
  kdnr?: string;
  name1: string;
  name2?: string;
  name3?: string;
  strasse?: string;
  hausnummer?: string;
  plz?: string;
  ort?: string;
  land?: string;
  telefon?: string;
  email?: string;
  adresstyp?: number;
  betreuer?: string;
  aktiv: boolean;
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

  const { register, handleSubmit, reset, setValue, formState: { errors } } = useForm<AdresseForm>({
    resolver: zodResolver(adresseSchema),
    defaultValues: { adresstyp: 1 },
  });

  const handleRowDoubleClick = (adresse: Adresse) => {
    setSelectedAdresse(adresse);
    setIsEditMode(false);
  };

  const handleCloseDetail = () => {
    setSelectedAdresse(null);
    setIsEditMode(false);
  };

  const columns: ColumnDef<Adresse>[] = useMemo(() => [
    { accessorKey: 'kdnr', header: 'KDNR', size: 100, cell: ({ row }) => <span className="font-mono text-primary font-medium">{row.getValue('kdnr') || '-'}</span> },
    { accessorKey: 'name1', header: 'Firma', cell: ({ row }) => <div className="flex items-center gap-2"><Building2 className="h-4 w-4 text-muted-foreground" /><span className="font-medium">{row.getValue('name1')}</span></div> },
    { accessorKey: 'name2', header: 'Name', cell: ({ row }) => row.getValue('name2') || '-' },
    { accessorKey: 'name3', header: 'Vorname', cell: ({ row }) => row.getValue('name3') || '-' },
    { id: 'adresse', header: 'Straße', cell: ({ row }) => row.original.strasse ? `${row.original.strasse} ${row.original.hausnummer || ''}`.trim() : '-' },
    { id: 'ortDisplay', header: 'Ort', cell: ({ row }) => row.original.plz || row.original.ort ? `${row.original.plz || ''} ${row.original.ort || ''}`.trim() : '-' },
    { accessorKey: 'land', header: 'Land', cell: ({ row }) => row.getValue('land') || 'DE' },
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
          <DropdownMenuItem onClick={() => setSelectedAdresse(row.original)}><Eye className="h-4 w-4 mr-2" />Details</DropdownMenuItem>
          <DropdownMenuItem><Pencil className="h-4 w-4 mr-2" />Bearbeiten</DropdownMenuItem>
          <DropdownMenuSeparator />
          <DropdownMenuItem className="text-destructive" onClick={() => deleteMutation.mutate(row.original.id)}><Trash2 className="h-4 w-4 mr-2" />Deaktivieren</DropdownMenuItem>
        </DropdownMenuContent>
      </DropdownMenu>
    )},
  ], [deleteMutation]);

  return (
    <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="space-y-6">
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold tracking-tight">Adressen</h1>
          <p className="text-muted-foreground">{data?.pagination?.total || 0} Adressen verwalten</p>
        </div>
        <Button onClick={() => setShowCreateDialog(true)} data-testid="create-address-btn"><Plus className="h-4 w-4 mr-2" />Neue Adresse</Button>
      </div>

      <DataTable columns={columns} data={data?.data || []} isLoading={isLoading} searchPlaceholder="Suchen nach KDNR, Firma, Ort..."
        onSearchChange={(s) => { setSearchTerm(s); setPage(1); }}
        pagination={data?.pagination ? { page: data.pagination.page, limit: data.pagination.limit, total: data.pagination.total, totalPages: data.pagination.total_pages } : undefined}
        onPageChange={setPage} onLimitChange={(l) => { setLimit(l); setPage(1); }} />

      <Dialog open={showCreateDialog} onOpenChange={setShowCreateDialog}>
        <DialogContent className="sm:max-w-[600px]">
          <DialogHeader><DialogTitle>Neue Adresse erstellen</DialogTitle><DialogDescription>Erfassen Sie die Stammdaten.</DialogDescription></DialogHeader>
          <form onSubmit={handleSubmit((d) => createMutation.mutate(d))} className="space-y-4">
            <div className="grid grid-cols-2 gap-4">
              <div className="col-span-2 space-y-2"><Label>Firma / Name 1 *</Label><Input {...register('name1')} placeholder="z.B. Müller GmbH" />{errors.name1 && <p className="text-sm text-destructive">{errors.name1.message}</p>}</div>
              <div className="space-y-2"><Label>Name</Label><Input {...register('name2')} placeholder="Nachname" /></div>
              <div className="space-y-2"><Label>Vorname</Label><Input {...register('name3')} placeholder="Vorname" /></div>
              <div className="space-y-2"><Label>Straße</Label><Input {...register('strasse')} /></div>
              <div className="space-y-2"><Label>Hausnummer</Label><Input {...register('hausnummer')} /></div>
              <div className="space-y-2"><Label>PLZ</Label><Input {...register('plz')} /></div>
              <div className="space-y-2"><Label>Ort</Label><Input {...register('ort')} /></div>
              <div className="space-y-2"><Label>Land</Label><Input {...register('land')} defaultValue="Deutschland" /></div>
              <div className="space-y-2"><Label>Betreuer</Label><Input {...register('betreuer')} /></div>
              <div className="space-y-2"><Label>Telefon</Label><Input {...register('telefon')} /></div>
              <div className="space-y-2"><Label>E-Mail</Label><Input type="email" {...register('email')} /></div>
              <div className="space-y-2"><Label>Adresstyp</Label><Select defaultValue="1" onValueChange={(v) => setValue('adresstyp', Number(v))}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="1">Kunde</SelectItem><SelectItem value="2">Lieferant</SelectItem><SelectItem value="3">Spedition</SelectItem><SelectItem value="4">Interessent</SelectItem><SelectItem value="5">Sonstige</SelectItem></SelectContent></Select></div>
              <div className="col-span-2 space-y-2"><Label>Bemerkungen</Label><Textarea {...register('bemerkungen')} rows={2} /></div>
            </div>
            <DialogFooter><Button type="button" variant="outline" onClick={() => setShowCreateDialog(false)}>Abbrechen</Button><Button type="submit" disabled={createMutation.isPending}>{createMutation.isPending ? 'Speichern...' : 'Erstellen'}</Button></DialogFooter>
          </form>
        </DialogContent>
      </Dialog>

      <Dialog open={!!selectedAdresse} onOpenChange={() => setSelectedAdresse(null)}>
        <DialogContent><DialogHeader><DialogTitle className="flex items-center gap-2"><Building2 className="h-5 w-5" />{selectedAdresse?.name1}</DialogTitle><DialogDescription>KDNR: {selectedAdresse?.kdnr || '-'}</DialogDescription></DialogHeader>
          {selectedAdresse && <div className="grid grid-cols-2 gap-4 text-sm">
            <div><p className="text-muted-foreground">Name</p><p className="font-medium">{selectedAdresse.name2 || '-'}</p></div>
            <div><p className="text-muted-foreground">Vorname</p><p className="font-medium">{selectedAdresse.name3 || '-'}</p></div>
            <div className="col-span-2"><p className="text-muted-foreground">Adresse</p><p className="font-medium flex items-center gap-1"><MapPin className="h-4 w-4" />{selectedAdresse.strasse} {selectedAdresse.hausnummer}, {selectedAdresse.plz} {selectedAdresse.ort}</p></div>
            <div><p className="text-muted-foreground">Telefon</p><p className="font-medium">{selectedAdresse.telefon || '-'}</p></div>
            <div><p className="text-muted-foreground">E-Mail</p><p className="font-medium">{selectedAdresse.email || '-'}</p></div>
            <div><p className="text-muted-foreground">Betreuer</p><p className="font-medium">{selectedAdresse.betreuer || '-'}</p></div>
            <div><p className="text-muted-foreground">Typ</p><p className="font-medium">{adresstypLabels[selectedAdresse.adresstyp || 5]}</p></div>
          </div>}
        </DialogContent>
      </Dialog>
    </motion.div>
  );
}
