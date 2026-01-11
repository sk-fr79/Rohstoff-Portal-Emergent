import { useState, useEffect } from 'react';
import { useAuthStore } from '@/store/authStore';
import { toast } from 'sonner';
import { 
  Database, Search, Plus, Loader2, MoreVertical, Eye, Trash2,
  ExternalLink, Download, Upload, RefreshCw, Edit2, ChevronLeft,
  ChevronRight, Globe, FileSpreadsheet
} from 'lucide-react';
import { FileUploadWizard } from './FileUploadWizard';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { 
  Table, TableBody, TableCell, TableHead, TableHeader, TableRow 
} from '@/components/ui/table';
import { 
  DropdownMenu, DropdownMenuContent, DropdownMenuItem, 
  DropdownMenuSeparator, DropdownMenuTrigger 
} from '@/components/ui/dropdown-menu';
import {
  Dialog, DialogContent, DialogDescription, DialogFooter,
  DialogHeader, DialogTitle,
} from '@/components/ui/dialog';
import { ScrollArea } from '@/components/ui/scroll-area';
import { cn } from '@/lib/utils';

const API_URL = import.meta.env.VITE_API_URL || '/api';

interface ReferenceTable {
  id: string;
  table_name: string;
  display_name: string;
  api_config_id?: string;
  api_name?: string;
  is_manual?: boolean;
  record_count: number;
  last_updated_at?: string;
  update_strategy: string;
  usage: string[];
}

interface ReferenceRecord {
  _id: string;
  _external_id: string;
  [key: string]: any;
}

export function ReferenceTablesTab() {
  const { accessToken: token } = useAuthStore();
  const [tables, setTables] = useState<ReferenceTable[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');
  
  // Detail View State
  const [selectedTable, setSelectedTable] = useState<ReferenceTable | null>(null);
  const [tableData, setTableData] = useState<ReferenceRecord[]>([]);
  const [pagination, setPagination] = useState({ page: 1, page_size: 50, total: 0, total_pages: 0 });
  const [dataLoading, setDataLoading] = useState(false);
  const [dataSearch, setDataSearch] = useState('');
  
  // Delete Dialog
  const [deleteDialog, setDeleteDialog] = useState<{ open: boolean; table?: ReferenceTable }>({ open: false });
  const [deleting, setDeleting] = useState(false);
  
  // Upload Wizard
  const [uploadWizardOpen, setUploadWizardOpen] = useState(false);

  // Tabellen laden
  const fetchTables = async () => {
    setIsLoading(true);
    try {
      const res = await fetch(`${API_URL}/system/reference-tables${searchTerm ? `?search=${searchTerm}` : ''}`, {
        headers: { 'Authorization': `Bearer ${token}` }
      });
      const data = await res.json();
      if (data.success) {
        setTables(data.data);
      }
    } catch (error) {
      console.error('Fehler beim Laden:', error);
      toast.error('Fehler beim Laden der Referenztabellen');
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchTables();
  }, [searchTerm]);

  // Tabellendaten laden
  const fetchTableData = async (tableId: string, page = 1) => {
    setDataLoading(true);
    try {
      const params = new URLSearchParams({
        page: page.toString(),
        page_size: pagination.page_size.toString(),
      });
      if (dataSearch) params.append('search', dataSearch);
      
      const res = await fetch(`${API_URL}/system/reference-tables/${tableId}?${params}`, {
        headers: { 'Authorization': `Bearer ${token}` }
      });
      const data = await res.json();
      if (data.success) {
        setTableData(data.data.records);
        setPagination(data.data.pagination);
      }
    } catch (error) {
      toast.error('Fehler beim Laden der Daten');
    } finally {
      setDataLoading(false);
    }
  };

  // Tabelle öffnen
  const openTable = async (table: ReferenceTable) => {
    setSelectedTable(table);
    setDataSearch('');
    await fetchTableData(table.id, 1);
  };

  // Tabelle löschen
  const handleDelete = async () => {
    if (!deleteDialog.table) return;
    
    setDeleting(true);
    try {
      const res = await fetch(`${API_URL}/system/reference-tables/${deleteDialog.table.id}`, {
        method: 'DELETE',
        headers: { 'Authorization': `Bearer ${token}` }
      });
      const data = await res.json();
      
      if (data.success) {
        toast.success('Referenztabelle gelöscht');
        fetchTables();
        if (selectedTable?.id === deleteDialog.table.id) {
          setSelectedTable(null);
        }
      } else {
        toast.error(data.detail || 'Fehler beim Löschen');
      }
    } catch (error) {
      toast.error('Fehler beim Löschen');
    } finally {
      setDeleting(false);
      setDeleteDialog({ open: false });
    }
  };

  // Export als JSON
  const handleExport = async (table: ReferenceTable) => {
    try {
      const res = await fetch(`${API_URL}/system/reference-tables/${table.id}?page_size=10000`, {
        headers: { 'Authorization': `Bearer ${token}` }
      });
      const data = await res.json();
      
      if (data.success) {
        const blob = new Blob([JSON.stringify(data.data.records, null, 2)], { type: 'application/json' });
        const url = URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = `${table.table_name}_export.json`;
        a.click();
        URL.revokeObjectURL(url);
        toast.success('Export erfolgreich');
      }
    } catch (error) {
      toast.error('Export fehlgeschlagen');
    }
  };

  const formatDate = (dateStr?: string) => {
    if (!dateStr) return '-';
    return new Date(dateStr).toLocaleString('de-DE', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  };

  // Gefilterte Tabellen
  const filteredTables = tables;

  // Detail View
  if (selectedTable) {
    const columns = tableData.length > 0 
      ? Object.keys(tableData[0]).filter(k => !k.startsWith('_'))
      : [];

    return (
      <div className="space-y-4">
        {/* Header */}
        <div className="flex items-center justify-between">
          <div className="flex items-center gap-3">
            <Button variant="outline" size="icon" onClick={() => setSelectedTable(null)}>
              <ChevronLeft className="h-4 w-4" />
            </Button>
            <div>
              <h2 className="text-lg font-semibold">{selectedTable.display_name}</h2>
              <p className="text-sm text-muted-foreground font-mono">{selectedTable.table_name}</p>
            </div>
          </div>
          <div className="flex items-center gap-2">
            <Button variant="outline" size="sm" onClick={() => handleExport(selectedTable)}>
              <Download className="h-4 w-4 mr-2" />
              Export
            </Button>
            <Button 
              variant="outline" 
              size="sm" 
              onClick={() => fetchTableData(selectedTable.id, pagination.page)}
            >
              <RefreshCw className="h-4 w-4 mr-2" />
              Aktualisieren
            </Button>
          </div>
        </div>

        {/* Search */}
        <div className="flex items-center gap-4">
          <div className="relative flex-1 max-w-md">
            <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
            <Input
              placeholder="In Daten suchen..."
              value={dataSearch}
              onChange={(e) => setDataSearch(e.target.value)}
              onKeyDown={(e) => e.key === 'Enter' && fetchTableData(selectedTable.id, 1)}
              className="pl-10"
            />
          </div>
          <Badge variant="outline">
            {pagination.total.toLocaleString('de-DE')} Einträge
          </Badge>
        </div>

        {/* Data Table */}
        <Card>
          <ScrollArea className="h-[500px]">
            <Table>
              <TableHeader>
                <TableRow>
                  {columns.map(col => (
                    <TableHead key={col} className="whitespace-nowrap">
                      {col}
                    </TableHead>
                  ))}
                </TableRow>
              </TableHeader>
              <TableBody>
                {dataLoading ? (
                  <TableRow>
                    <TableCell colSpan={columns.length || 1} className="text-center py-8">
                      <Loader2 className="h-6 w-6 animate-spin mx-auto" />
                    </TableCell>
                  </TableRow>
                ) : tableData.length === 0 ? (
                  <TableRow>
                    <TableCell colSpan={columns.length || 1} className="text-center py-8 text-muted-foreground">
                      Keine Daten vorhanden
                    </TableCell>
                  </TableRow>
                ) : (
                  tableData.map((row, idx) => (
                    <TableRow key={row._id || idx}>
                      {columns.map(col => (
                        <TableCell key={col} className="max-w-[200px] truncate">
                          {typeof row[col] === 'object' ? JSON.stringify(row[col]) : String(row[col] ?? '-')}
                        </TableCell>
                      ))}
                    </TableRow>
                  ))
                )}
              </TableBody>
            </Table>
          </ScrollArea>
        </Card>

        {/* Pagination */}
        {pagination.total_pages > 1 && (
          <div className="flex items-center justify-between">
            <p className="text-sm text-muted-foreground">
              Seite {pagination.page} von {pagination.total_pages}
            </p>
            <div className="flex gap-2">
              <Button
                variant="outline"
                size="sm"
                disabled={pagination.page <= 1}
                onClick={() => fetchTableData(selectedTable.id, pagination.page - 1)}
              >
                <ChevronLeft className="h-4 w-4" />
                Zurück
              </Button>
              <Button
                variant="outline"
                size="sm"
                disabled={pagination.page >= pagination.total_pages}
                onClick={() => fetchTableData(selectedTable.id, pagination.page + 1)}
              >
                Weiter
                <ChevronRight className="h-4 w-4" />
              </Button>
            </div>
          </div>
        )}
      </div>
    );
  }

  // Liste View
  return (
    <div className="space-y-6">
      {/* Search + Actions */}
      <div className="flex items-center gap-4">
        <div className="relative flex-1 max-w-md">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
          <Input
            placeholder="Referenztabellen durchsuchen..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="pl-10"
          />
        </div>
        <Button 
          onClick={() => setUploadWizardOpen(true)}
          className="bg-emerald-600 hover:bg-emerald-700 gap-2"
        >
          <FileSpreadsheet className="h-4 w-4" />
          CSV/Excel Import
        </Button>
        <Button variant="outline" onClick={fetchTables}>
          <RefreshCw className="h-4 w-4 mr-2" />
          Aktualisieren
        </Button>
      </div>

      {/* Tables Grid */}
      {isLoading ? (
        <div className="flex items-center justify-center py-12">
          <Loader2 className="h-8 w-8 animate-spin text-emerald-600" />
        </div>
      ) : filteredTables.length === 0 ? (
        <div className="text-center py-12 text-muted-foreground">
          <Database className="h-12 w-12 mx-auto mb-4 opacity-50" />
          <p>Keine Referenztabellen vorhanden</p>
          <p className="text-sm mt-1">Erstellen Sie eine API mit aktivierter Referenztabelle</p>
        </div>
      ) : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
          {filteredTables.map((table) => (
            <Card 
              key={table.id} 
              className="hover:shadow-md transition-shadow cursor-pointer"
              onClick={() => openTable(table)}
            >
              <CardHeader className="pb-3">
                <div className="flex items-start justify-between">
                  <div className="flex items-center gap-3">
                    <div className="h-10 w-10 rounded-lg bg-blue-100 flex items-center justify-center">
                      <Database className="h-5 w-5 text-blue-600" />
                    </div>
                    <div>
                      <CardTitle className="text-base">{table.display_name}</CardTitle>
                      <p className="text-xs text-muted-foreground font-mono">{table.table_name}</p>
                    </div>
                  </div>
                  <DropdownMenu>
                    <DropdownMenuTrigger asChild onClick={(e) => e.stopPropagation()}>
                      <Button variant="ghost" size="icon" className="h-8 w-8">
                        <MoreVertical className="h-4 w-4" />
                      </Button>
                    </DropdownMenuTrigger>
                    <DropdownMenuContent align="end">
                      <DropdownMenuItem onClick={(e) => { e.stopPropagation(); openTable(table); }}>
                        <Eye className="h-4 w-4 mr-2" />
                        Daten anzeigen
                      </DropdownMenuItem>
                      <DropdownMenuItem onClick={(e) => { e.stopPropagation(); handleExport(table); }}>
                        <Download className="h-4 w-4 mr-2" />
                        Export (JSON)
                      </DropdownMenuItem>
                      <DropdownMenuSeparator />
                      <DropdownMenuItem 
                        onClick={(e) => { e.stopPropagation(); setDeleteDialog({ open: true, table }); }}
                        className="text-red-600"
                      >
                        <Trash2 className="h-4 w-4 mr-2" />
                        Löschen
                      </DropdownMenuItem>
                    </DropdownMenuContent>
                  </DropdownMenu>
                </div>
              </CardHeader>
              <CardContent className="pt-0 space-y-3">
                <div className="flex items-center gap-2">
                  <Badge variant="outline" className="text-xs">
                    {table.record_count.toLocaleString('de-DE')} Einträge
                  </Badge>
                  <Badge variant="outline" className="text-xs capitalize">
                    {table.update_strategy}
                  </Badge>
                </div>

                {table.api_name && (
                  <div className="flex items-center gap-2 text-sm text-muted-foreground">
                    <Globe className="h-4 w-4" />
                    <span>{table.api_name}</span>
                  </div>
                )}

                {table.is_manual && (
                  <Badge variant="secondary" className="text-xs">
                    Manuell gepflegt
                  </Badge>
                )}

                <div className="text-xs text-muted-foreground pt-2 border-t">
                  Zuletzt aktualisiert: {formatDate(table.last_updated_at)}
                </div>
              </CardContent>
            </Card>
          ))}
        </div>
      )}

      {/* Delete Dialog */}
      <Dialog open={deleteDialog.open} onOpenChange={(open) => setDeleteDialog({ open })}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Referenztabelle löschen</DialogTitle>
            <DialogDescription>
              Möchten Sie die Tabelle "{deleteDialog.table?.display_name}" wirklich löschen?
              <span className="block mt-2 text-red-600">
                ⚠️ Alle {deleteDialog.table?.record_count.toLocaleString('de-DE')} Datensätze werden unwiderruflich gelöscht!
              </span>
            </DialogDescription>
          </DialogHeader>
          <DialogFooter>
            <Button variant="outline" onClick={() => setDeleteDialog({ open: false })}>
              Abbrechen
            </Button>
            <Button variant="destructive" onClick={handleDelete} disabled={deleting}>
              {deleting ? <Loader2 className="h-4 w-4 mr-2 animate-spin" /> : <Trash2 className="h-4 w-4 mr-2" />}
              Löschen
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
