import { motion } from 'framer-motion';
import { FileText, Plus } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';

export function KontraktePage() {
  return (
    <motion.div
      initial={{ opacity: 0 }}
      animate={{ opacity: 1 }}
      className="space-y-6"
    >
      <div className="flex justify-between items-center">
        <div>
          <h1 className="text-3xl font-bold tracking-tight">Kontrakte</h1>
          <p className="text-muted-foreground">Verwalten Sie Ihre Ein- und Verkaufskontrakte</p>
        </div>
        <Button>
          <Plus className="h-4 w-4 mr-2" />
          Neuer Kontrakt
        </Button>
      </div>

      <Card>
        <CardContent className="flex flex-col items-center justify-center py-12">
          <FileText className="h-12 w-12 text-muted-foreground/50 mb-4" />
          <h3 className="text-lg font-medium">Kontrakte-Modul</h3>
          <p className="text-muted-foreground text-sm mt-1">
            Kontraktverwaltung wird hier implementiert
          </p>
        </CardContent>
      </Card>
    </motion.div>
  );
}
