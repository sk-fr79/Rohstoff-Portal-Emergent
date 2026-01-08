import { useState } from 'react';
import { useQuery } from '@tanstack/react-query';
import { motion } from 'framer-motion';
import {
  Plus,
  Search,
  Filter,
  MoreHorizontal,
  Building2,
  Phone,
  Mail,
  MapPin,
} from 'lucide-react';
import { adressenApi } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';

export function AdressenPage() {
  const [searchTerm, setSearchTerm] = useState('');
  const [page, setPage] = useState(1);

  const { data, isLoading } = useQuery({
    queryKey: ['adressen', { suche: searchTerm, page }],
    queryFn: () => adressenApi.search({ suche: searchTerm, page, limit: 20 }),
    select: (res) => res.data,
  });

  return (
    <motion.div
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      className="space-y-6"
    >
      {/* Header */}
      <div className="flex flex-col sm:flex-row justify-between items-start sm:items-center gap-4">
        <div>
          <h1 className="text-3xl font-bold tracking-tight">Adressen</h1>
          <p className="text-muted-foreground">Verwalten Sie Ihre Kunden und Lieferanten</p>
        </div>
        <Button data-testid="create-address-btn">
          <Plus className="h-4 w-4 mr-2" />
          Neue Adresse
        </Button>
      </div>

      {/* Filter und Suche */}
      <Card>
        <CardContent className="p-4">
          <div className="flex flex-col sm:flex-row gap-4">
            <div className="relative flex-1">
              <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
              <Input
                placeholder="Suchen nach Name, KDNR, Ort..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="pl-10"
                data-testid="address-search"
              />
            </div>
            <Button variant="outline">
              <Filter className="h-4 w-4 mr-2" />
              Filter
            </Button>
          </div>
        </CardContent>
      </Card>

      {/* Adressliste */}
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
          {data.data.map((adresse: {
            id: string;
            kdnr?: string;
            name1: string;
            name2?: string;
            strasse?: string;
            hausnummer?: string;
            plz?: string;
            ort?: string;
            telefon?: string;
            email?: string;
            adresstyp?: number;
            aktiv: boolean;
          }) => (
            <motion.div
              key={adresse.id}
              initial={{ opacity: 0, y: 20 }}
              animate={{ opacity: 1, y: 0 }}
              whileHover={{ scale: 1.02 }}
              transition={{ duration: 0.2 }}
            >
              <Card className="cursor-pointer hover:shadow-lg transition-shadow">
                <CardHeader className="pb-2">
                  <div className="flex items-start justify-between">
                    <div className="flex items-center gap-3">
                      <div className="h-10 w-10 rounded-lg bg-primary/10 flex items-center justify-center">
                        <Building2 className="h-5 w-5 text-primary" />
                      </div>
                      <div>
                        <CardTitle className="text-base">{adresse.name1}</CardTitle>
                        {adresse.kdnr && (
                          <p className="text-xs text-muted-foreground">KDNR: {adresse.kdnr}</p>
                        )}
                      </div>
                    </div>
                    <Button variant="ghost" size="icon">
                      <MoreHorizontal className="h-4 w-4" />
                    </Button>
                  </div>
                </CardHeader>
                <CardContent className="space-y-2">
                  {adresse.name2 && (
                    <p className="text-sm text-muted-foreground">{adresse.name2}</p>
                  )}
                  {(adresse.strasse || adresse.ort) && (
                    <div className="flex items-center gap-2 text-sm text-muted-foreground">
                      <MapPin className="h-4 w-4 flex-shrink-0" />
                      <span>
                        {adresse.strasse} {adresse.hausnummer}, {adresse.plz} {adresse.ort}
                      </span>
                    </div>
                  )}
                  {adresse.telefon && (
                    <div className="flex items-center gap-2 text-sm text-muted-foreground">
                      <Phone className="h-4 w-4 flex-shrink-0" />
                      <span>{adresse.telefon}</span>
                    </div>
                  )}
                  {adresse.email && (
                    <div className="flex items-center gap-2 text-sm text-muted-foreground">
                      <Mail className="h-4 w-4 flex-shrink-0" />
                      <span className="truncate">{adresse.email}</span>
                    </div>
                  )}
                  <div className="pt-2 flex items-center gap-2">
                    <span className={`inline-flex items-center px-2 py-0.5 rounded-full text-xs font-medium ${
                      adresse.adresstyp === 1 ? 'bg-blue-500/10 text-blue-500' :
                      adresse.adresstyp === 2 ? 'bg-green-500/10 text-green-500' :
                      'bg-gray-500/10 text-gray-500'
                    }`}>
                      {adresse.adresstyp === 1 ? 'Kunde' :
                       adresse.adresstyp === 2 ? 'Lieferant' : 'Sonstige'}
                    </span>
                    {!adresse.aktiv && (
                      <span className="inline-flex items-center px-2 py-0.5 rounded-full text-xs font-medium bg-red-500/10 text-red-500">
                        Inaktiv
                      </span>
                    )}
                  </div>
                </CardContent>
              </Card>
            </motion.div>
          ))}
        </div>
      ) : (
        <Card>
          <CardContent className="flex flex-col items-center justify-center py-12">
            <Building2 className="h-12 w-12 text-muted-foreground/50 mb-4" />
            <h3 className="text-lg font-medium">Keine Adressen gefunden</h3>
            <p className="text-muted-foreground text-sm mt-1">
              {searchTerm ? 'Versuchen Sie andere Suchbegriffe' : 'Erstellen Sie Ihre erste Adresse'}
            </p>
          </CardContent>
        </Card>
      )}

      {/* Pagination */}
      {data?.pagination && data.pagination.totalPages > 1 && (
        <div className="flex justify-center gap-2">
          <Button
            variant="outline"
            onClick={() => setPage(p => Math.max(1, p - 1))}
            disabled={page === 1}
          >
            Zurück
          </Button>
          <span className="flex items-center px-4 text-sm text-muted-foreground">
            Seite {page} von {data.pagination.totalPages}
          </span>
          <Button
            variant="outline"
            onClick={() => setPage(p => p + 1)}
            disabled={page >= data.pagination.totalPages}
          >
            Weiter
          </Button>
        </div>
      )}
    </motion.div>
  );
}
