import { useState, useMemo, useEffect } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { motion } from 'framer-motion';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { toast } from 'sonner';
import { Plus, MoreHorizontal, Pencil, Trash2, Package, AlertTriangle, Check, X, Loader2, Zap, FileText, Save } from 'lucide-react';
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
  anr1: z.string().max(10).optional(),
  artbez1: z.string().min(1, 'Artikelbezeichnung erforderlich').max(80),
  artbez2: z.string().max(1000).optional(),
  einheit: z.string().max(10).default('kg'),
  einheit_preis: z.string().max(10).default('t'),
  mengendivisor: z.number().min(1).default(1000),
  genauigkeit_mengen: z.number().min(0).max(6).default(3),
  artikelgruppe: z.string().max(100).optional(),
  artikelgruppe_fibu: z.string().max(100).optional(),
  aktiv: z.boolean().default(true),
  gefahrgut: z.boolean().default(false),
  ist_leergut: z.boolean().default(false),
  elektro_elektronik: z.boolean().default(false),
  ist_produkt: z.boolean().default(false),
  dienstleistung: z.boolean().default(false),
  end_of_waste: z.boolean().default(false),
  end_of_waste_lager: z.boolean().default(false),
  avv_code_eingang: z.string().max(50).optional(),
  avv_code_ausgang: z.string().max(50).optional(),
  eakcode: z.string().max(20).optional(),
  zolltarifnr: z.string().max(50).optional(),
  zolltarifnotiz: z.string().max(500).optional(),
  basel_code: z.string().max(80).optional(),
  basel_notiz: z.string().max(500).optional(),
  oecd_code: z.string().max(50).optional(),
  oecd_notiz: z.string().max(500).optional(),
  anhang7_3a_code: z.string().max(20).optional(),
  anhang7_3a_text: z.string().max(1000).optional(),
  anhang7_3b_code: z.string().max(20).optional(),
  anhang7_3b_text: z.string().max(1000).optional(),
  oesterreichische_avv: z.string().max(50).optional(),
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
  artikelgruppe_fibu?: string;
  aktiv: boolean;
  gefahrgut: boolean;
  ist_leergut?: boolean;
  elektro_elektronik?: boolean;
  ist_produkt?: boolean;
  dienstleistung?: boolean;
  end_of_waste?: boolean;
  end_of_waste_lager?: boolean;
  avv_code_eingang?: string;
  avv_code_ausgang?: string;
  eakcode?: string;
  zolltarifnr?: string;
  zolltarifnotiz?: string;
  basel_code?: string;
  basel_notiz?: string;
  oecd_code?: string;
  oecd_notiz?: string;
  anhang7_3a_code?: string;
  anhang7_3a_text?: string;
  anhang7_3b_code?: string;
  anhang7_3b_text?: string;
  oesterreichische_avv?: string;
  bemerkung_intern?: string;
}

export function ArtikelPage() {
  const [searchTerm, setSearchTerm] = useState('');
  const [page, setPage] = useState(1);
  const [limit, setLimit] = useState(20);
  const [showCreateDialog, setShowCreateDialog] = useState(false);
  const [selectedArtikel, setSelectedArtikel] = useState<Artikel | null>(null);
  const [isEditMode, setIsEditMode] = useState(false);
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
    defaultValues: { einheit: 'kg', einheit_preis: 't', mengendivisor: 1000, genauigkeit_mengen: 3, aktiv: true },
  });

  const watchFields = watch(['aktiv', 'gefahrgut', 'ist_leergut', 'elektro_elektronik', 'ist_produkt', 'dienstleistung', 'end_of_waste', 'end_of_waste_lager']);

  // Load selected artikel into form when editing
  useEffect(() => {
    if (selectedArtikel && isEditMode) {
      Object.entries(selectedArtikel).forEach(([key, value]) => {
        if (key !== 'id' && value !== undefined) {
          setValue(key as keyof ArtikelForm, value);
        }
      });
    }
  }, [selectedArtikel, isEditMode, setValue]);

  const handleRowDoubleClick = (artikel: Artikel) => {
    setSelectedArtikel(artikel);
    setIsEditMode(false);
  };

  const handleCloseDetail = () => {
    setSelectedArtikel(null);
    setIsEditMode(false);
    reset();
  };

  const handleEnableEdit = () => {
    setIsEditMode(true);
  };

  const handleSaveEdit = (data: ArtikelForm) => {
    // TODO: Implement update API
    toast.success('Änderungen gespeichert (Demo)');
    setIsEditMode(false);
    queryClient.invalidateQueries({ queryKey: ['artikel'] });
  };

  const columns: ColumnDef<Artikel>[] = useMemo(() => [
    { accessorKey: 'anr1', header: 'Sorten Nr.', size: 100, cell: ({ row }) => <span className="font-mono text-primary">{row.getValue('anr1') || '-'}</span> },
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
          <DropdownMenuItem onClick={() => handleRowDoubleClick(row.original)}><FileText className="h-4 w-4 mr-2" />Details</DropdownMenuItem>
          <DropdownMenuItem onClick={() => { setSelectedArtikel(row.original); setIsEditMode(true); }}><Pencil className="h-4 w-4 mr-2" />Bearbeiten</DropdownMenuItem>
          <DropdownMenuSeparator />
          <DropdownMenuItem className="text-destructive"><Trash2 className="h-4 w-4 mr-2" />Deaktivieren</DropdownMenuItem>
        </DropdownMenuContent>
      </DropdownMenu>
    )},
  ], []);

  const renderFormContent = (readOnly: boolean = false) => (
    <Tabs defaultValue="artikel" className="w-full">
      <TabsList className="grid w-full grid-cols-3">
        <TabsTrigger value="artikel">Artikel-Angaben</TabsTrigger>
        <TabsTrigger value="codes">Nummern-Codes</TabsTrigger>
        <TabsTrigger value="bemerkung">Bemerkungen</TabsTrigger>
      </TabsList>

      <TabsContent value="artikel" className="space-y-4 mt-4">
        <div className="grid grid-cols-4 gap-3 p-3 bg-muted/50 rounded-lg">
          <div className="flex items-center space-x-2"><Switch checked={watchFields[0]} onCheckedChange={(c) => setValue('aktiv', c)} disabled={readOnly} /><Label className="text-sm">Aktiv</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[1]} onCheckedChange={(c) => setValue('gefahrgut', c)} disabled={readOnly} /><Label className="text-sm text-orange-500">Gefahrgut</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[2]} onCheckedChange={(c) => setValue('ist_leergut', c)} disabled={readOnly} /><Label className="text-sm">Leergut</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[3]} onCheckedChange={(c) => setValue('elektro_elektronik', c)} disabled={readOnly} /><Label className="text-sm">Elektro/Elektronik</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[4]} onCheckedChange={(c) => setValue('ist_produkt', c)} disabled={readOnly} /><Label className="text-sm">Produkt</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[5]} onCheckedChange={(c) => setValue('dienstleistung', c)} disabled={readOnly} /><Label className="text-sm">Dienstleistung</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[6]} onCheckedChange={(c) => setValue('end_of_waste', c)} disabled={readOnly} /><Label className="text-sm text-green-500">End of Waste</Label></div>
          <div className="flex items-center space-x-2"><Switch checked={watchFields[7]} onCheckedChange={(c) => setValue('end_of_waste_lager', c)} disabled={readOnly} /><Label className="text-sm">EoW (Lager)</Label></div>
        </div>

        <div className="grid grid-cols-2 gap-4">
          <div className="space-y-2"><Label>Artikelgruppe</Label><Input {...register('artikelgruppe')} disabled={readOnly} placeholder="z.B. Blechabfälle, Gießerei" /></div>
          <div className="space-y-2"><Label>Artikelgruppe (für FiBu)</Label><Input {...register('artikelgruppe_fibu')} disabled={readOnly} placeholder="Finanzbuchhaltung" /></div>
        </div>

        <div className="grid grid-cols-2 gap-4">
          <div className="space-y-2"><Label>Sorten Nr. (ANR1)</Label><Input {...register('anr1')} disabled={readOnly} placeholder="z.B. 01-1000" /></div>
          <div className="space-y-2"><Label>EAK-Code (ID-Artikel)</Label><Input {...register('eakcode')} disabled={readOnly} placeholder="z.B. 1.000" /></div>
        </div>

        <div className="space-y-2"><Label>Artikelbez. 1 (intern) *</Label><Input {...register('artbez1')} disabled={readOnly} placeholder="z.B. Blechabfälle lose, kurz" />{errors.artbez1 && <p className="text-sm text-destructive">{errors.artbez1.message}</p>}</div>
        <div className="space-y-2"><Label>Artikelbez. 2 (intern)</Label><Input {...register('artbez2')} disabled={readOnly} placeholder="Zusätzliche Beschreibung" /></div>

        <div className="grid grid-cols-4 gap-4">
          <div className="space-y-2"><Label>Einheit</Label><Select defaultValue={selectedArtikel?.einheit || "kg"} onValueChange={(v) => setValue('einheit', v)} disabled={readOnly}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="kg">kg</SelectItem><SelectItem value="t">t</SelectItem><SelectItem value="stk">stk</SelectItem><SelectItem value="l">l</SelectItem><SelectItem value="m3">m³</SelectItem></SelectContent></Select></div>
          <div className="space-y-2"><Label>Faktor</Label><Input type="number" {...register('mengendivisor', { valueAsNumber: true })} disabled={readOnly} /></div>
          <div className="space-y-2"><Label>Preiseinheit</Label><Select defaultValue={selectedArtikel?.einheit_preis || "t"} onValueChange={(v) => setValue('einheit_preis', v)} disabled={readOnly}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="kg">kg</SelectItem><SelectItem value="t">t</SelectItem><SelectItem value="stk">stk</SelectItem></SelectContent></Select></div>
          <div className="space-y-2"><Label>Nachkomma</Label><Select defaultValue={String(selectedArtikel?.genauigkeit_mengen || 3)} onValueChange={(v) => setValue('genauigkeit_mengen', Number(v))} disabled={readOnly}><SelectTrigger><SelectValue /></SelectTrigger><SelectContent><SelectItem value="0">0</SelectItem><SelectItem value="1">1</SelectItem><SelectItem value="2">2</SelectItem><SelectItem value="3">3</SelectItem><SelectItem value="4">4</SelectItem></SelectContent></Select></div>
        </div>

        <div className="grid grid-cols-2 gap-4">
          <div className="space-y-2"><Label>AVV-Code Bar-Anlieferer</Label><Input {...register('avv_code_eingang')} disabled={readOnly} placeholder="z.B. 12 01 02 Eisenstaub" /></div>
          <div className="space-y-2"><Label>AVV-Code Ausgang mvg</Label><Input {...register('avv_code_ausgang')} disabled={readOnly} placeholder="z.B. 19 12 02 Eisenmetalle" /></div>
        </div>
      </TabsContent>

      <TabsContent value="codes" className="space-y-4 mt-4">
        <div className="space-y-3 p-3 border rounded-lg">
          <h4 className="font-medium text-sm">Basel-Code</h4>
          <div className="grid grid-cols-3 gap-4">
            <div className="space-y-2"><Label>Basel-Code</Label><Input {...register('basel_code')} disabled={readOnly} placeholder="z.B. B1010" /></div>
            <div className="col-span-2 space-y-2"><Label>Basel-Notiz</Label><Input {...register('basel_notiz')} disabled={readOnly} placeholder="z.B. Abfälle aus Metallen..." /></div>
          </div>
        </div>

        <div className="space-y-3 p-3 border rounded-lg">
          <h4 className="font-medium text-sm">OECD-Code</h4>
          <div className="grid grid-cols-3 gap-4">
            <div className="space-y-2"><Label>OECD-Code</Label><Input {...register('oecd_code')} disabled={readOnly} /></div>
            <div className="col-span-2 space-y-2"><Label>OECD-Notiz</Label><Input {...register('oecd_notiz')} disabled={readOnly} /></div>
          </div>
        </div>

        <div className="space-y-3 p-3 border rounded-lg">
          <h4 className="font-medium text-sm">Zolltarif</h4>
          <div className="space-y-2"><Label>Zolltarif-Nummer</Label><Input {...register('zolltarifnr')} disabled={readOnly} placeholder="z.B. 72044199" /></div>
          <div className="space-y-2"><Label>Zolltarif-Text</Label><Textarea {...register('zolltarifnotiz')} disabled={readOnly} placeholder="z.B. Stanzabfälle oder Schneidabfälle..." rows={2} /></div>
        </div>

        <div className="space-y-3 p-3 border rounded-lg">
          <h4 className="font-medium text-sm">Anhang 7</h4>
          <div className="grid grid-cols-2 gap-4">
            <div className="space-y-2"><Label>Anhang 7 (IIIA) Nummer</Label><Input {...register('anhang7_3a_code')} disabled={readOnly} /></div>
            <div className="space-y-2"><Label>Anhang 7 (IIIA) Text</Label><Input {...register('anhang7_3a_text')} disabled={readOnly} /></div>
            <div className="space-y-2"><Label>Anhang 7 (IIIB) Nummer</Label><Input {...register('anhang7_3b_code')} disabled={readOnly} /></div>
            <div className="space-y-2"><Label>Anhang 7 (IIIB) Text</Label><Input {...register('anhang7_3b_text')} disabled={readOnly} /></div>
          </div>
        </div>

        <div className="space-y-2"><Label>Österreichische AVV</Label><Input {...register('oesterreichische_avv')} disabled={readOnly} /></div>
      </TabsContent>

      <TabsContent value="bemerkung" className="space-y-4 mt-4">
        <div className="space-y-2">
          <Label>Bemerkung (intern)</Label>
          <Textarea {...register('bemerkung_intern')} disabled={readOnly} placeholder="Interne Notizen zum Artikel..." rows={10} className="font-mono text-sm" />
        </div>
      </TabsContent>
    </Tabs>
  );

  return (
    <motion.div initial={{ opacity: 0 }} animate={{ opacity: 1 }} className="space-y-6">
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold tracking-tight">Artikelstamm</h1>
          <p className="text-muted-foreground">{data?.pagination?.total || 0} Artikel verwalten · Doppelklick für Details</p>
        </div>
        <Button onClick={() => setShowCreateDialog(true)} data-testid="create-article-btn"><Plus className="h-4 w-4 mr-2" />Neuer Artikel</Button>
      </div>

      <DataTable columns={columns} data={data?.data || []} isLoading={isLoading} searchPlaceholder="Suchen nach Artikelbezeichnung, Sorten Nr., AVV-Code..."
        onSearchChange={(s) => { setSearchTerm(s); setPage(1); }}
        onRowDoubleClick={handleRowDoubleClick}
        pagination={data?.pagination ? { page: data.pagination.page, limit: data.pagination.limit, total: data.pagination.total, totalPages: data.pagination.total_pages } : undefined}
        onPageChange={setPage} onLimitChange={(l) => { setLimit(l); setPage(1); }} />

      {/* Create Dialog */}
      <Dialog open={showCreateDialog} onOpenChange={setShowCreateDialog}>
        <DialogContent className="sm:max-w-[700px] max-h-[90vh] overflow-y-auto">
          <DialogHeader>
            <DialogTitle>Neuen Artikel erstellen</DialogTitle>
            <DialogDescription>Erfassen Sie die Stammdaten für einen neuen Artikel.</DialogDescription>
          </DialogHeader>
          <form onSubmit={handleSubmit((d) => createMutation.mutate(d))} className="space-y-4">
            {renderFormContent(false)}
            <DialogFooter className="mt-6">
              <Button type="button" variant="outline" onClick={() => setShowCreateDialog(false)}>Abbrechen</Button>
              <Button type="submit" disabled={createMutation.isPending} data-testid="article-submit-btn">
                {createMutation.isPending ? <><Loader2 className="mr-2 h-4 w-4 animate-spin" />Speichern...</> : 'Artikel erstellen'}
              </Button>
            </DialogFooter>
          </form>
        </DialogContent>
      </Dialog>

      {/* Detail/Edit Dialog */}
      <Dialog open={!!selectedArtikel} onOpenChange={handleCloseDetail}>
        <DialogContent className="sm:max-w-[700px] max-h-[90vh] overflow-y-auto">
          <DialogHeader>
            <DialogTitle className="flex items-center gap-2">
              <Package className="h-5 w-5" />
              {selectedArtikel?.artbez1}
              {isEditMode && <span className="text-sm font-normal text-muted-foreground ml-2">(Bearbeitungsmodus)</span>}
            </DialogTitle>
            <DialogDescription>Sorten Nr.: {selectedArtikel?.anr1 || '-'}</DialogDescription>
          </DialogHeader>
          
          {selectedArtikel && (
            <form onSubmit={handleSubmit(handleSaveEdit)} className="space-y-4">
              {renderFormContent(!isEditMode)}
              
              <DialogFooter className="mt-6">
                {!isEditMode ? (
                  <>
                    <Button type="button" variant="outline" onClick={handleCloseDetail}>Schließen</Button>
                    <Button type="button" onClick={handleEnableEdit}>
                      <Pencil className="h-4 w-4 mr-2" />Bearbeiten
                    </Button>
                  </>
                ) : (
                  <>
                    <Button type="button" variant="outline" onClick={() => setIsEditMode(false)}>Abbrechen</Button>
                    <Button type="submit">
                      <Save className="h-4 w-4 mr-2" />Änderungen speichern
                    </Button>
                  </>
                )}
              </DialogFooter>
            </form>
          )}
        </DialogContent>
      </Dialog>
    </motion.div>
  );
}
