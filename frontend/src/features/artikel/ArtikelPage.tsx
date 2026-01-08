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
  Package,
  Scale,
  AlertTriangle,
  Check,
  X,
  Loader2,
} from 'lucide-react';
import { artikelApi } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Switch } from '@/components/ui/switch';
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
  DropdownMenuTrigger,
} from '@/components/ui/dropdown-menu';
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from '@/components/ui/select';

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
  const [showCreateDialog, setShowCreateDialog] = useState(false);
  const [filterAktiv, setFilterAktiv] = useState<boolean | undefined>(undefined);
  const queryClient = useQueryClient();

  const { data, isLoading } = useQuery({
    queryKey: ['artikel', { suche: searchTerm, page, aktiv: filterAktiv }],
    queryFn: () => artikelApi.search({ suche: searchTerm, page, limit: 20, aktiv: filterAktiv }),
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
    onError: () => {
      toast.error('Fehler beim Erstellen des Artikels');
    },
  });

  const {
    register,
    handleSubmit,
    reset,
    setValue,
    watch,
    formState: { errors },
  } = useForm<ArtikelForm>({
    resolver: zodResolver(artikelSchema),
    defaultValues: {
      einheit: 'kg',
      einheit_preis: 't',
      mengendivisor: 1000,
      aktiv: true,
      gefahrgut: false,
    },
  });

  const onSubmit = (data: ArtikelForm) => {
    createMutation.mutate(data);
  };

  const gefahrgut = watch('gefahrgut');
  const aktiv = watch('aktiv');

  return (
    <motion.div
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      className="space-y-6"
    >
      {/* Header */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold tracking-tight">Artikel</h1>
          <p className="text-muted-foreground">Verwalten Sie Ihren Artikelstamm</p>
        </div>
        <Button onClick={() => setShowCreateDialog(true)} data-testid="create-article-btn">
          <Plus className="h-4 w-4 mr-2" />
          Neuer Artikel
        </Button>
      </div>

      {/* Filter und Suche */}
      <Card>
        <CardContent className="p-4">
          <div className="flex flex-col sm:flex-row gap-4">
            <div className="relative flex-1">
              <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
              <Input
                placeholder="Suchen nach Artikelbezeichnung..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="pl-10"
                data-testid="article-search"
              />
            </div>
            <DropdownMenu>
              <DropdownMenuTrigger asChild>
                <Button variant="outline">
                  <Filter className="h-4 w-4 mr-2" />
                  Filter
                </Button>
              </DropdownMenuTrigger>
              <DropdownMenuContent align="end">
                <DropdownMenuItem onClick={() => setFilterAktiv(undefined)}>
                  Alle anzeigen
                </DropdownMenuItem>
                <DropdownMenuItem onClick={() => setFilterAktiv(true)}>
                  Nur aktive
                </DropdownMenuItem>
                <DropdownMenuItem onClick={() => setFilterAktiv(false)}>
                  Nur inaktive
                </DropdownMenuItem>
              </DropdownMenuContent>
            </DropdownMenu>
          </div>
        </CardContent>
      </Card>

      {/* Artikelliste */}
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
          {data.data.map((artikel: Artikel) => (
            <motion.div
              key={artikel.id}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              whileHover={{ scale: 1.02 }}
              transition={{ duration: 0.2 }}
            >
              <Card className="cursor-pointer hover:shadow-lg transition-shadow" data-testid={`article-card-${artikel.id}`}>
                <CardHeader className="pb-2">
                  <div className="flex items-start justify-between">
                    <div className="flex items-center gap-3">
                      <div className={`h-10 w-10 rounded-lg flex items-center justify-center ${
                        artikel.gefahrgut ? 'bg-orange-500/10' : 'bg-primary/10'
                      }`}>
                        {artikel.gefahrgut ? (
                          <AlertTriangle className="h-5 w-5 text-orange-500" />
                        ) : (
                          <Package className="h-5 w-5 text-primary" />
                        )}
                      </div>
                      <div>
                        <CardTitle className="text-base">{artikel.artbez1}</CardTitle>
                        {artikel.eakcode && (
                          <p className="text-xs text-muted-foreground">EAK: {artikel.eakcode}</p>
                        )}
                      </div>
                    </div>
                    <DropdownMenu>
                      <DropdownMenuTrigger asChild>
                        <Button variant="ghost" size="icon">
                          <MoreHorizontal className="h-4 w-4" />
                        </Button>
                      </DropdownMenuTrigger>
                      <DropdownMenuContent align="end">
                        <DropdownMenuItem>Bearbeiten</DropdownMenuItem>
                        <DropdownMenuItem className="text-destructive">Deaktivieren</DropdownMenuItem>
                      </DropdownMenuContent>
                    </DropdownMenu>
                  </div>
                </CardHeader>
                <CardContent className="space-y-2">
                  {artikel.artbez2 && (
                    <p className="text-sm text-muted-foreground line-clamp-2">{artikel.artbez2}</p>
                  )}
                  <div className="flex items-center gap-2 text-sm text-muted-foreground">
                    <Scale className="h-4 w-4 flex-shrink-0" />
                    <span>Einheit: {artikel.einheit} | Preis pro: {artikel.einheit_preis}</span>
                  </div>
                  <div className="pt-2 flex items-center gap-2 flex-wrap">
                    {artikel.gefahrgut && (
                      <span className="inline-flex items-center px-2 py-0.5 rounded-full text-xs font-medium bg-orange-500/10 text-orange-500">
                        <AlertTriangle className="h-3 w-3 mr-1" />
                        Gefahrgut
                      </span>
                    )}
                    <span className={`inline-flex items-center px-2 py-0.5 rounded-full text-xs font-medium ${
                      artikel.aktiv ? 'bg-green-500/10 text-green-500' : 'bg-red-500/10 text-red-500'
                    }`}>
                      {artikel.aktiv ? (
                        <><Check className="h-3 w-3 mr-1" /> Aktiv</>
                      ) : (
                        <><X className="h-3 w-3 mr-1" /> Inaktiv</>
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
            <Package className="h-12 w-12 text-muted-foreground/50 mb-4" />
            <h3 className="text-lg font-medium">Keine Artikel gefunden</h3>
            <p className="text-muted-foreground text-sm mt-1">
              {searchTerm ? 'Versuchen Sie andere Suchbegriffe' : 'Erstellen Sie Ihren ersten Artikel'}
            </p>
          </CardContent>
        </Card>
      )}

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
            <DialogTitle>Neuen Artikel erstellen</DialogTitle>
            <DialogDescription>
              Erfassen Sie die Stammdaten für einen neuen Artikel.
            </DialogDescription>
          </DialogHeader>
          <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
            <div className="space-y-2">
              <Label htmlFor="artbez1">Artikelbezeichnung *</Label>
              <Input
                id="artbez1"
                {...register('artbez1')}
                placeholder="z.B. Kupferschrott I"
                data-testid="article-name-input"
              />
              {errors.artbez1 && (
                <p className="text-sm text-destructive">{errors.artbez1.message}</p>
              )}
            </div>

            <div className="space-y-2">
              <Label htmlFor="artbez2">Zusätzliche Beschreibung</Label>
              <Input
                id="artbez2"
                {...register('artbez2')}
                placeholder="Detaillierte Beschreibung..."
              />
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label htmlFor="einheit">Einheit</Label>
                <Select
                  defaultValue="kg"
                  onValueChange={(value) => setValue('einheit', value)}
                >
                  <SelectTrigger>
                    <SelectValue placeholder="Einheit wählen" />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="kg">Kilogramm (kg)</SelectItem>
                    <SelectItem value="t">Tonne (t)</SelectItem>
                    <SelectItem value="stk">Stück (stk)</SelectItem>
                    <SelectItem value="l">Liter (l)</SelectItem>
                  </SelectContent>
                </Select>
              </div>

              <div className="space-y-2">
                <Label htmlFor="einheit_preis">Preis pro</Label>
                <Select
                  defaultValue="t"
                  onValueChange={(value) => setValue('einheit_preis', value)}
                >
                  <SelectTrigger>
                    <SelectValue placeholder="Preiseinheit" />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="kg">pro kg</SelectItem>
                    <SelectItem value="t">pro Tonne</SelectItem>
                    <SelectItem value="stk">pro Stück</SelectItem>
                  </SelectContent>
                </Select>
              </div>
            </div>

            <div className="space-y-2">
              <Label htmlFor="eakcode">EAK-Code</Label>
              <Input
                id="eakcode"
                {...register('eakcode')}
                placeholder="z.B. 17 04 01"
              />
            </div>

            <div className="flex items-center justify-between">
              <div className="flex items-center space-x-2">
                <Switch
                  id="gefahrgut"
                  checked={gefahrgut}
                  onCheckedChange={(checked) => setValue('gefahrgut', checked)}
                />
                <Label htmlFor="gefahrgut" className="flex items-center gap-2">
                  <AlertTriangle className="h-4 w-4 text-orange-500" />
                  Gefahrgut
                </Label>
              </div>
              <div className="flex items-center space-x-2">
                <Switch
                  id="aktiv"
                  checked={aktiv}
                  onCheckedChange={(checked) => setValue('aktiv', checked)}
                />
                <Label htmlFor="aktiv">Aktiv</Label>
              </div>
            </div>

            <DialogFooter>
              <Button type="button" variant="outline" onClick={() => setShowCreateDialog(false)}>
                Abbrechen
              </Button>
              <Button type="submit" disabled={createMutation.isPending} data-testid="article-submit-btn">
                {createMutation.isPending ? (
                  <>
                    <Loader2 className="mr-2 h-4 w-4 animate-spin" />
                    Speichern...
                  </>
                ) : (
                  'Artikel erstellen'
                )}
              </Button>
            </DialogFooter>
          </form>
        </DialogContent>
      </Dialog>
    </motion.div>
  );
}
