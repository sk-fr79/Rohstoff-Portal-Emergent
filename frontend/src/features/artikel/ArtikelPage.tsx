import { useState, useMemo } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import { Plus, MoreHorizontal, Pencil, Trash2, Package, AlertTriangle, Check, X, Loader2, Zap, Recycle, FileText } from 'lucide-react';
import { artikelApi } from '@/services/api/client';
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

const artikelSchema = z.object({
  // Grunddaten
  anr1: z.string().max(10).optional(),
  artbez1: z.string().min(1, 'Artikelbezeichnung erforderlich').max(80),
  artbez2: z.string().max(1000).optional(),
  // Einheiten
  einheit: z.string().max(10).default('kg'),
  einheit_preis: z.string().max(10).default('t'),
  mengendivisor: z.number().min(1).default(1000),
  genauigkeit_mengen: z.number().min(0).max(6).default(3),
  // Klassifikation
  artikelgruppe: z.string().max(100).optional(),
  artikelgruppe_fibu: z.string().max(100).optional(),
  // Status
  aktiv: z.boolean().default(true),
  gefahrgut: z.boolean().default(false),
  ist_leergut: z.boolean().default(false),
  elektro_elektronik: z.boolean().default(false),
  ist_produkt: z.boolean().default(false),
  dienstleistung: z.boolean().default(false),
  end_of_waste: z.boolean().default(false),
  end_of_waste_lager: z.boolean().default(false),
  // AVV-Codes
  avv_code_eingang: z.string().max(50).optional(),
  avv_code_ausgang: z.string().max(50).optional(),
  eakcode: z.string().max(20).optional(),
  // Zoll
  zolltarifnr: z.string().max(50).optional(),
  zolltarifnotiz: z.string().max(500).optional(),
  // Basel
  basel_code: z.string().max(80).optional(),
  basel_notiz: z.string().max(500).optional(),
  // OECD
  oecd_code: z.string().max(50).optional(),
  oecd_notiz: z.string().max(500).optional(),
  // Anhang 7
  anhang7_3a_code: z.string().max(20).optional(),
  anhang7_3a_text: z.string().max(1000).optional(),
  anhang7_3b_code: z.string().max(20).optional(),
  anhang7_3b_text: z.string().max(1000).optional(),
  // Österreich
  oesterreichische_avv: z.string().max(50).optional(),
  // Bemerkung
  bemerkung_intern: z.string().max(1000).optional(),
});

type ArtikelForm = z.infer<typeof artikelSchema>;

interface Artikel {
  id: string;
  anr1?: string;
  artbez1: string;
  artbez2?: string;
  einheit: string;
  einheit_preis: string;
  mengendivisor: number;
  genauigkeit_mengen?: number;
  artikelgruppe?: string;
  aktiv: boolean;
  gefahrgut: boolean;
  ist_leergut?: boolean;
  elektro_elektronik?: boolean;
  ist_produkt?: boolean;
  dienstleistung?: boolean;
  end_of_waste?: boolean;
  avv_code_eingang?: string;
  avv_code_ausgang?: string;
  eakcode?: string;
  basel_code?: string;
  zolltarifnr?: string;
}

export function ArtikelPage() {
  const [searchTerm, setSearchTerm] = useState('');
  const [page, setPage] = useState(1);
  const [limit, setLimit] = useState(20);
  const [showCreateDialog, setShowCreateDialog] = useState(false);
  const [selectedArtikel, setSelectedArtikel] = useState<Artikel | null>(null);
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
    defaultValues: { einheit: 'kg', einheit_preis: 't', mengendivisor: 1000, genauigkeit_mengen: 3, aktiv: true, gefahrgut: false, ist_leergut: false, elektro_elektronik: false, ist_produkt: false, dienstleistung: false, end_of_waste: false, end_of_waste_lager: false },
  });

  const watchFields = watch(['aktiv', 'gefahrgut', 'ist_leergut', 'elektro_elektronik', 'ist_produkt', 'dienstleistung', 'end_of_waste', 'end_of_waste_lager']);

  const columns: ColumnDef<Artikel>[] = useMemo(() => [
    { accessorKey: 'anr1', header: 'ANR1', size: 100, cell: ({ row }) => <span className="font-mono text-primary">{row.getValue('anr1') || '-'}</span> },
    { accessorKey: 'artbez1', header: 'Artikelbezeichnung', cell: ({ row }) => (
      <div className="flex items-center gap-2">
        <div className={`h-8 w-8 rounded flex items-center justify-center flex-shrink-0 ${row.original.gefahrgut ? 'bg-orange-500/10' : row.original.elektro_elektronik ? 'bg-yellow-500/10' : 'bg-primary/10'}`}>
          {row.original.gefahrgut ? <AlertTriangle className="h-4 w-4 text-orange-500" /> : row.original.elektro_elektronik ? <Zap className="h-4 w-4 text-yellow-500" /> : <Package className="h-4 w-4 text-primary" />}
        </div>
        <div className="min-w-0">
          <span className="font-medium block truncate">{row.getValue('artbez1')}</span>
          {row.original.artbez2 && <p className="text-xs text-muted-foreground truncate">{row.original.artbez2}</p>}
        </div>
      </div>
    )},
    { accessorKey: 'artikelgruppe', header: 'Artikelgruppe', cell: ({ row }) => row.getValue('artikelgruppe') || '-' },
    { accessorKey: 'avv_code_eingang', header: 'AVV Eingang', size: 120, cell: ({ row }) => <span className="font-mono text-xs">{row.getValue('avv_code_eingang') || '-'}</span> },
    { accessorKey: 'avv_code_ausgang', header: 'AVV Ausgang', size: 120, cell: ({ row }) => <span className="font-mono text-xs">{row.getValue('avv_code_ausgang') || '-'}</span> },
    { accessorKey: 'einheit', header: 'Einheit', size: 70 },
    { accessorKey: 'einheit_preis', header: 'Preis/E', size: 70 },
    { id: 'flags', header: 'Eigenschaften', size: 180, cell: ({ row }) => (
      <div className="flex flex-wrap gap-1">
        {row.original.gefahrgut && <span className="inline-flex items-center px-1.5 py-0.5 rounded text-[10px] font-medium bg-orange-500/10 text-orange-500">Gefahrgut</span>}
        {row.original.ist_leergut && <span className="inline-flex items-center px-1.5 py-0.5 rounded text-[10px] font-medium bg-purple-500/10 text-purple-500">Leergut</span>}
        {row.original.elektro_elektronik && <span className="inline-flex items-center px-1.5 py-0.5 rounded text-[10px] font-medium bg-yellow-500/10 text-yellow-500">E-Schrott</span>}
        {row.original.ist_produkt && <span className="inline-flex items-center px-1.5 py-0.5 rounded text-[10px] font-medium bg-blue-500/10 text-blue-500">Produkt</span>}
        {row.original.dienstleistung && <span className="inline-flex items-center px-1.5 py-0.5 rounded text-[10px] font-medium bg-cyan-500/10 text-cyan-500">DL</span>}
        {row.original.end_of_waste && <span className="inline-flex items-center px-1.5 py-0.5 rounded text-[10px] font-medium bg-green-500/10 text-green-500">EoW</span>}
      </div>
    )},
    { accessorKey: 'aktiv', header: 'Status', size: 80, cell: ({ row }) => row.getValue('aktiv') ? (
      <span className="inline-flex items-center gap-1 px-2 py-0.5 rounded-full text-xs font-medium bg-green-500/10 text-green-500"><Check className="h-3 w-3" />Aktiv</span>
    ) : <span className="inline-flex items-center gap-1 px-2 py-0.5 rounded-full text-xs font-medium bg-red-500/10 text-red-500"><X className="h-3 w-3" />Inaktiv</span> },
    { id: 'actions', size: 60, cell: ({ row }) => (
      <DropdownMenu>
        <DropdownMenuTrigger asChild><Button variant="ghost" size="icon" className="h-8 w-8"><MoreHorizontal className="h-4 w-4" /></Button></DropdownMenuTrigger>
        <DropdownMenuContent align="end">
          <DropdownMenuItem onClick={() => setSelectedArtikel(row.original)}><FileText className="h-4 w-4 mr-2" />Details</DropdownMenuItem>
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
          <h1 className="text-3xl font-bold tracking-tight">Artikelstamm</h1>
          <p className="text-muted-foreground">{data?.pagination?.total || 0} Artikel verwalten</p>
        </div>
        <Button onClick={() => setShowCreateDialog(true)} data-testid="create-article-btn"><Plus className="h-4 w-4 mr-2" />Neuer Artikel</Button>
      </div>

      <DataTable columns={columns} data={data?.data || []} isLoading={isLoading} searchPlaceholder="Suchen nach Artikelbezeichnung, ANR1, AVV-Code..."
        onSearchChange={(s) => { setSearchTerm(s); setPage(1); }}
        pagination={data?.pagination ? { page: data.pagination.page, limit: data.pagination.limit, total: data.pagination.total, totalPages: data.pagination.total_pages } : undefined}
        onPageChange={setPage} onLimitChange={(l) => { setLimit(l); setPage(1); }} />

      {/* Create/Edit Dialog */}
      <Dialog open={showCreateDialog} onOpenChange={setShowCreateDialog}>
        <DialogContent className="sm:max-w-[700px] max-h-[90vh] overflow-y-auto">
          <DialogHeader>
            <DialogTitle>Neuen Artikel erstellen</DialogTitle>
            <DialogDescription>Erfassen Sie die Stammdaten für einen neuen Artikel.</DialogDescription>
          </DialogHeader>
          <form onSubmit={handleSubmit((d) => createMutation.mutate(d))} className="space-y-4">
            <Tabs defaultValue="artikel" className="w-full">
              <TabsList className="grid w-full grid-cols-3">
                <TabsTrigger value="artikel">Artikel-Angaben</TabsTrigger>
                <TabsTrigger value="codes">Nummern-Codes</TabsTrigger>
                <TabsTrigger value="bemerkung">Bemerkungen</TabsTrigger>
              </TabsList>

              {/* Tab 1: Artikel-Angaben */}
              <TabsContent value="artikel" className="space-y-4 mt-4">
                {/* Status Checkboxes */}
                <div className="grid grid-cols-4 gap-3 p-3 bg-muted/50 rounded-lg">
                  <div className="flex items-center space-x-2"><Switch checked={watchFields[0]} onCheckedChange={(c) => setValue('aktiv', c)} /><Label className="text-sm">Aktiv</Label></div>
                  <div className="flex items-center space-x-2"><Switch checked={watchFields[1]} onCheckedChange={(c) => setValue('gefahrgut', c)} /><Label className="text-sm text-orange-500">Gefahrgut</Label></div>
                  <div className="flex items-center space-x-2"><Switch checked={watchFields[2]} onCheckedChange={(c) => setValue('ist_leergut', c)} /><Label className="text-sm">Leergut</Label></div>
                  <div className="flex items-center space-x-2"><Switch checked={watchFields[3]} onCheckedChange={(c) => setValue('elektro_elektronik', c)} /><Label className="text-sm">Elektro/Elektronik</Label></div>
                  <div className="flex items-center space-x-2"><Switch checked={watchFields[4]} onCheckedChange={(c) => setValue('ist_produkt', c)} /><Label className="text-sm">Produkt</Label></div>
                  <div className="flex items-center space-x-2"><Switch checked={watchFields[5]} onCheckedChange={(c) => setValue('dienstleistung', c)} /><Label className="text-sm">Dienstleistung</Label></div>
                  <div className="flex items-center space-x-2"><Switch checked={watchFields[6]} onCheckedChange={(c) => setValue('end_of_waste', c)} /><Label className="text-sm text-green-500">End of Waste</Label></div>
                  <div className="flex items-center space-x-2"><Switch checked={watchFields[7]} onCheckedChange={(c) => setValue('end_of_waste_lager', c)} /><Label className="text-sm">EoW (Lager)</Label></div>
                </div>

                {/* Artikelgruppen */}
                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-2"><Label>Artikelgruppe</Label><Input {...register('artikelgruppe')} placeholder="z.B. Blechabfälle, Gießerei" /></div>
                  <div className="space-y-2"><Label>Artikelgruppe (für FiBu)</Label><Input {...register('artikelgruppe_fibu')} placeholder="Finanzbuchhaltung" /></div>
                </div>

                {/* Artikelnummern */}
                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-2"><Label>ANR1 (Artikelnummer)</Label><Input {...register('anr1')} placeholder="z.B. 01-1000" /></div>
                  <div className="space-y-2"><Label>EAK-Code (ID-Artikel)</Label><Input {...register('eakcode')} placeholder="z.B. 1.000" /></div>
                </div>

                {/* Bezeichnungen */}
                <div className="space-y-2"><Label>Artikelbez. 1 (intern) *</Label><Input {...register('artbez1')} placeholder="z.B. Blechabfälle lose, kurz" data-testid="article-name-input" />{errors.artbez1 && <p className="text-sm text-destructive">{errors.artbez1.message}</p>}</div>
                <div className="space-y-2"><Label>Artikelbez. 2 (intern)</Label><Input {...register('artbez2')} placeholder="Zusätzliche Beschreibung" /></div>

                {/* Einheiten */}
                <div className="grid grid-cols-4 gap-4">
                  <div className="space-y-2"><Label>Einheit</Label><Select defaultValue="kg" onValueChange={(v) => setValue('einheit', v)}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="kg">kg</SelectItem><SelectItem value="t">t</SelectItem><SelectItem value="stk">stk</SelectItem><SelectItem value="l">l</SelectItem><SelectItem value="m3">m³</SelectItem></SelectContent></Select></div>
                  <div className="space-y-2"><Label>Faktor</Label><Input type="number" {...register('mengendivisor', { valueAsNumber: true })} /></div>
                  <div className="space-y-2"><Label>Preiseinheit</Label><Select defaultValue="t" onValueChange={(v) => setValue('einheit_preis', v)}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="kg">kg</SelectItem><SelectItem value="t">t</SelectItem><SelectItem value="stk">stk</SelectItem></SelectContent></Select></div>
                  <div className="space-y-2"><Label>Nachkomma</Label><Select defaultValue="3" onValueChange={(v) => setValue('genauigkeit_mengen', Number(v))}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="0">0</SelectItem><SelectItem value="1">1</SelectItem><SelectItem value="2">2</SelectItem><SelectItem value="3">3</SelectItem><SelectItem value="4">4</SelectItem></SelectContent></Select></div>
                </div>

                {/* AVV-Codes */}
                <div className="grid grid-cols-2 gap-4">
                  <div className="space-y-2"><Label>AVV-Code Bar-Anlieferer</Label><Input {...register('avv_code_eingang')} placeholder="z.B. 12 01 02 Eisenstaub" /></div>
                  <div className="space-y-2"><Label>AVV-Code Ausgang mvg</Label><Input {...register('avv_code_ausgang')} placeholder="z.B. 19 12 02 Eisenmetalle" /></div>
                </div>
              </TabsContent>

              {/* Tab 2: Nummern-Codes */}
              <TabsContent value="codes" className="space-y-4 mt-4">
                {/* Basel */}
                <div className="space-y-3 p-3 border rounded-lg">
                  <h4 className="font-medium text-sm">Basel-Code</h4>
                  <div className="grid grid-cols-3 gap-4">
                    <div className="space-y-2"><Label>Basel-Code</Label><Input {...register('basel_code')} placeholder="z.B. B1010" /></div>
                    <div className="col-span-2 space-y-2"><Label>Basel-Notiz</Label><Input {...register('basel_notiz')} placeholder="z.B. Abfälle aus Metallen..." /></div>
                  </div>
                </div>

                {/* OECD */}
                <div className="space-y-3 p-3 border rounded-lg">
                  <h4 className="font-medium text-sm">OECD-Code</h4>
                  <div className="grid grid-cols-3 gap-4">
                    <div className="space-y-2"><Label>OECD-Code</Label><Input {...register('oecd_code')} /></div>
                    <div className="col-span-2 space-y-2"><Label>OECD-Notiz</Label><Input {...register('oecd_notiz')} /></div>
                  </div>
                </div>

                {/* Zolltarif */}
                <div className="space-y-3 p-3 border rounded-lg">
                  <h4 className="font-medium text-sm">Zolltarif</h4>
                  <div className="space-y-2"><Label>Zolltarif-Nummer</Label><Input {...register('zolltarifnr')} placeholder="z.B. 72044199" /></div>
                  <div className="space-y-2"><Label>Zolltarif-Text</Label><Textarea {...register('zolltarifnotiz')} placeholder="z.B. Stanzabfälle oder Schneidabfälle..." rows={2} /></div>
                </div>

                {/* Anhang 7 */}
                <div className="space-y-3 p-3 border rounded-lg">
                  <h4 className="font-medium text-sm">Anhang 7</h4>
                  <div className="grid grid-cols-2 gap-4">
                    <div className="space-y-2"><Label>Anhang 7 (IIIA) Nummer</Label><Input {...register('anhang7_3a_code')} /></div>
                    <div className="space-y-2"><Label>Anhang 7 (IIIA) Text</Label><Input {...register('anhang7_3a_text')} /></div>
                    <div className="space-y-2"><Label>Anhang 7 (IIIB) Nummer</Label><Input {...register('anhang7_3b_code')} /></div>
                    <div className="space-y-2"><Label>Anhang 7 (IIIB) Text</Label><Input {...register('anhang7_3b_text')} /></div>
                  </div>
                </div>

                {/* Österreich */}
                <div className="space-y-2"><Label>Österreichische AVV</Label><Input {...register('oesterreichische_avv')} /></div>
              </TabsContent>

              {/* Tab 3: Bemerkungen */}
              <TabsContent value="bemerkung" className="space-y-4 mt-4">
                <div className="space-y-2">
                  <Label>Bemerkung (intern)</Label>
                  <Textarea {...register('bemerkung_intern')} placeholder="Interne Notizen zum Artikel, z.B. Sortenanforderungen, Qualitätsvorgaben, Schüttgewicht..." rows={10} className="font-mono text-sm" />
                </div>
              </TabsContent>
            </Tabs>

            <DialogFooter className="mt-6">
              <Button type="button" variant="outline" onClick={() => setShowCreateDialog(false)}>Abbrechen</Button>
              <Button type="submit" disabled={createMutation.isPending} data-testid="article-submit-btn">
                {createMutation.isPending ? <><Loader2 className="mr-2 h-4 w-4 animate-spin" />Speichern...</> : 'Artikel erstellen'}
              </Button>
            </DialogFooter>
          </form>
        </DialogContent>
      </Dialog>

      {/* Detail Dialog */}
      <Dialog open={!!selectedArtikel} onOpenChange={() => setSelectedArtikel(null)}>
        <DialogContent className="sm:max-w-[600px]">
          <DialogHeader>
            <DialogTitle className="flex items-center gap-2">
              <Package className="h-5 w-5" />
              {selectedArtikel?.artbez1}
            </DialogTitle>
            <DialogDescription>ANR1: {selectedArtikel?.anr1 || '-'}</DialogDescription>
          </DialogHeader>
          {selectedArtikel && (
            <div className="space-y-4">
              <div className="flex flex-wrap gap-2">
                {selectedArtikel.aktiv && <span className="px-2 py-1 rounded text-xs bg-green-500/10 text-green-500">Aktiv</span>}
                {selectedArtikel.gefahrgut && <span className="px-2 py-1 rounded text-xs bg-orange-500/10 text-orange-500">Gefahrgut</span>}
                {selectedArtikel.ist_leergut && <span className="px-2 py-1 rounded text-xs bg-purple-500/10 text-purple-500">Leergut</span>}
                {selectedArtikel.elektro_elektronik && <span className="px-2 py-1 rounded text-xs bg-yellow-500/10 text-yellow-500">Elektro/Elektronik</span>}
                {selectedArtikel.end_of_waste && <span className="px-2 py-1 rounded text-xs bg-green-500/10 text-green-500">End of Waste</span>}
              </div>
              <div className="grid grid-cols-2 gap-4 text-sm">
                <div><p className="text-muted-foreground">Artikelgruppe</p><p className="font-medium">{selectedArtikel.artikelgruppe || '-'}</p></div>
                <div><p className="text-muted-foreground">Einheit</p><p className="font-medium">{selectedArtikel.einheit} / Preis pro {selectedArtikel.einheit_preis}</p></div>
                <div><p className="text-muted-foreground">AVV-Code Eingang</p><p className="font-mono">{selectedArtikel.avv_code_eingang || '-'}</p></div>
                <div><p className="text-muted-foreground">AVV-Code Ausgang</p><p className="font-mono">{selectedArtikel.avv_code_ausgang || '-'}</p></div>
                <div><p className="text-muted-foreground">Basel-Code</p><p className="font-mono">{selectedArtikel.basel_code || '-'}</p></div>
                <div><p className="text-muted-foreground">Zolltarifnummer</p><p className="font-mono">{selectedArtikel.zolltarifnr || '-'}</p></div>
              </div>
            </div>
          )}
        </DialogContent>
      </Dialog>
    </motion.div>
  );
}
