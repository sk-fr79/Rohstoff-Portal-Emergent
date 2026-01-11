import { useState, useEffect } from 'react';
import { useAuthStore } from '@/store/authStore';
import { toast } from 'sonner';
import { 
  Globe, Plus, RefreshCw, Loader2, Search, MoreVertical,
  Pencil, Trash2, Play, CheckCircle, XCircle, Clock,
  Database, ExternalLink, Settings2, AlertTriangle, Link2,
  ChevronRight, Shield
} from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { 
  DropdownMenu, DropdownMenuContent, DropdownMenuItem, 
  DropdownMenuSeparator, DropdownMenuTrigger 
} from '@/components/ui/dropdown-menu';
import {
  Dialog, DialogContent, DialogDescription, DialogFooter,
  DialogHeader, DialogTitle,
} from '@/components/ui/dialog';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import { cn } from '@/lib/utils';
import { ApiConfigWizard } from './components/ApiConfigWizard';
import { ReferenceTablesTab } from './components/ReferenceTablesTab';
import { SyncLogsTab } from './components/SyncLogsTab';

const API_URL = import.meta.env.VITE_API_URL || '/api';

interface ApiConfig {
  id: string;
  name: string;
  slug: string;
  description?: string;
  api_type: string;
  base_url: string;
  auth_type: string;
  is_active: boolean;
  last_sync_at?: string;
  last_sync_status?: 'success' | 'error' | 'pending' | 'running';
  last_sync_message?: string;
  reference_table?: {
    enabled: boolean;
    table_name: string;
    display_name: string;
  };
  reference_table_info?: {
    id: string;
    record_count: number;
    last_updated_at?: string;
  };
  created_at: string;
}

// Status Badge Komponente
function StatusBadge({ status }: { status?: string }) {
  const config = {
    success: { label: 'Aktiv', className: 'bg-emerald-100 text-emerald-700 border-emerald-200' },
    error: { label: 'Fehler', className: 'bg-red-100 text-red-700 border-red-200' },
    running: { label: 'Sync läuft', className: 'bg-blue-100 text-blue-700 border-blue-200' },
    pending: { label: 'Ausstehend', className: 'bg-amber-100 text-amber-700 border-amber-200' },
  }[status || 'pending'] || { label: 'Inaktiv', className: 'bg-gray-100 text-gray-600 border-gray-200' };

  return (
    <Badge variant="outline" className={cn('text-xs', config.className)}>
      {status === 'running' && <Loader2 className="h-3 w-3 mr-1 animate-spin" />}
      {status === 'success' && <CheckCircle className="h-3 w-3 mr-1" />}
      {status === 'error' && <XCircle className="h-3 w-3 mr-1" />}
      {config.label}
    </Badge>
  );
}

// API Card Komponente
function ApiCard({ 
  api, 
  onEdit, 
  onDelete, 
  onSync,
  syncing 
}: { 
  api: ApiConfig;
  onEdit: () => void;
  onDelete: () => void;
  onSync: () => void;
  syncing: boolean;
}) {
  const formatDate = (dateStr?: string) => {
    if (!dateStr) return 'Nie';
    return new Date(dateStr).toLocaleString('de-DE', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  };

  return (
    <Card className={cn(
      "group relative overflow-hidden transition-all duration-200",
      "hover:shadow-md hover:border-emerald-200",
      !api.is_active && "opacity-60"
    )}>
      <CardHeader className="pb-3">
        <div className="flex items-start justify-between">
          <div className="flex items-center gap-3">
            <div className={cn(
              "h-10 w-10 rounded-lg flex items-center justify-center",
              api.last_sync_status === 'error' 
                ? "bg-red-100" 
                : "bg-emerald-100"
            )}>
              <Globe className={cn(
                "h-5 w-5",
                api.last_sync_status === 'error' ? "text-red-600" : "text-emerald-600"
              )} />
            </div>
            <div>
              <CardTitle className="text-base">{api.name}</CardTitle>
              <p className="text-xs text-muted-foreground font-mono truncate max-w-[200px]">
                {api.base_url}
              </p>
            </div>
          </div>
          <DropdownMenu>
            <DropdownMenuTrigger asChild>
              <Button variant="ghost" size="icon" className="h-8 w-8">
                <MoreVertical className="h-4 w-4" />
              </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent align="end">
              <DropdownMenuItem onClick={onEdit}>
                <Pencil className="h-4 w-4 mr-2" />
                Bearbeiten
              </DropdownMenuItem>
              <DropdownMenuItem onClick={onSync} disabled={syncing}>
                <RefreshCw className={cn("h-4 w-4 mr-2", syncing && "animate-spin")} />
                Jetzt synchronisieren
              </DropdownMenuItem>
              <DropdownMenuSeparator />
              <DropdownMenuItem onClick={onDelete} className="text-red-600">
                <Trash2 className="h-4 w-4 mr-2" />
                Löschen
              </DropdownMenuItem>
            </DropdownMenuContent>
          </DropdownMenu>
        </div>
      </CardHeader>
      <CardContent className="pt-0 space-y-3">
        <div className="flex items-center gap-2">
          <StatusBadge status={api.is_active ? api.last_sync_status : undefined} />
          <Badge variant="outline" className="text-xs">
            {api.api_type}
          </Badge>
          <Badge variant="outline" className="text-xs capitalize">
            {api.auth_type === 'none' ? 'Keine Auth' : api.auth_type.replace('_', ' ')}
          </Badge>
        </div>

        {api.reference_table?.enabled && (
          <div className="flex items-center gap-2 text-sm text-muted-foreground">
            <Database className="h-4 w-4" />
            <span className="font-mono text-xs">{api.reference_table.table_name}</span>
            {api.reference_table_info && (
              <span className="text-xs">
                ({api.reference_table_info.record_count.toLocaleString('de-DE')} Einträge)
              </span>
            )}
          </div>
        )}

        {api.last_sync_status === 'error' && api.last_sync_message && (
          <div className="flex items-start gap-2 p-2 bg-red-50 rounded-md text-xs text-red-700">
            <AlertTriangle className="h-4 w-4 flex-shrink-0 mt-0.5" />
            <span className="line-clamp-2">{api.last_sync_message}</span>
          </div>
        )}

        <div className="flex items-center justify-between pt-2 border-t text-xs text-muted-foreground">
          <span>Letzter Sync: {formatDate(api.last_sync_at)}</span>
          <Button 
            variant="ghost" 
            size="sm" 
            className="h-7 text-xs"
            onClick={onSync}
            disabled={syncing || !api.is_active}
          >
            {syncing ? (
              <Loader2 className="h-3 w-3 mr-1 animate-spin" />
            ) : (
              <Play className="h-3 w-3 mr-1" />
            )}
            Sync
          </Button>
        </div>
      </CardContent>
    </Card>
  );
}

export function ApisPage() {
  const { user, accessToken: token } = useAuthStore();
  const [apis, setApis] = useState<ApiConfig[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');
  const [activeTab, setActiveTab] = useState('overview');
  
  // Wizard State
  const [showWizard, setShowWizard] = useState(false);
  const [editingApi, setEditingApi] = useState<ApiConfig | null>(null);
  
  // Delete Dialog
  const [deleteDialog, setDeleteDialog] = useState<{ open: boolean; api?: ApiConfig }>({ open: false });
  const [deleting, setDeleting] = useState(false);
  
  // Syncing State
  const [syncingApis, setSyncingApis] = useState<Set<string>>(new Set());

  // APIs laden
  const fetchApis = async () => {
    setIsLoading(true);
    try {
      const res = await fetch(`${API_URL}/system/apis`, {
        headers: { 'Authorization': `Bearer ${token}` }
      });
      const data = await res.json();
      if (data.success) {
        setApis(data.data);
      }
    } catch (error) {
      console.error('Fehler beim Laden der APIs:', error);
      toast.error('Fehler beim Laden der APIs');
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    if (user?.istAdmin) {
      fetchApis();
    } else {
      setIsLoading(false);
    }
  }, [user?.istAdmin]);

  // Nur Admins
  if (!user?.istAdmin) {
    return (
      <div className="p-6 flex items-center justify-center min-h-[400px]">
        <div className="text-center">
          <Shield className="h-16 w-16 mx-auto text-gray-300 mb-4" />
          <h2 className="text-xl font-semibold text-gray-700">Kein Zugriff</h2>
          <p className="text-gray-500 mt-2">
            Sie benötigen Administrator-Rechte, um diese Seite anzuzeigen.
          </p>
        </div>
      </div>
    );
  }

  // API löschen
  const handleDelete = async () => {
    if (!deleteDialog.api) return;
    
    setDeleting(true);
    try {
      const res = await fetch(`${API_URL}/system/apis/${deleteDialog.api.id}`, {
        method: 'DELETE',
        headers: { 'Authorization': `Bearer ${token}` }
      });
      const data = await res.json();
      
      if (data.success) {
        toast.success('API-Konfiguration gelöscht');
        fetchApis();
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

  // API synchronisieren
  const handleSync = async (apiId: string) => {
    setSyncingApis(prev => new Set(prev).add(apiId));
    
    try {
      const res = await fetch(`${API_URL}/system/apis/${apiId}/sync`, {
        method: 'POST',
        headers: { 'Authorization': `Bearer ${token}` }
      });
      const data = await res.json();
      
      if (data.success) {
        toast.success(`Synchronisierung abgeschlossen: ${data.data.records_fetched} Datensätze`);
        fetchApis();
      } else {
        toast.error(data.detail || 'Synchronisierung fehlgeschlagen');
      }
    } catch (error) {
      toast.error('Synchronisierung fehlgeschlagen');
    } finally {
      setSyncingApis(prev => {
        const next = new Set(prev);
        next.delete(apiId);
        return next;
      });
    }
  };

  // Gefilterte APIs
  const filteredApis = apis.filter(api => 
    api.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    api.base_url.toLowerCase().includes(searchTerm.toLowerCase())
  );

  if (isLoading) {
    return (
      <div className="p-6 flex items-center justify-center min-h-[400px]">
        <Loader2 className="h-8 w-8 animate-spin text-emerald-600" />
      </div>
    );
  }

  return (
    <div className="p-6 max-w-7xl mx-auto" data-testid="apis-page">
      {/* Header */}
      <div className="mb-6">
        <div className="flex items-center gap-2 text-sm text-muted-foreground mb-2">
          <Settings2 className="h-4 w-4" />
          <span>Systemeinstellungen</span>
          <ChevronRight className="h-4 w-4" />
          <span className="text-foreground">APIs</span>
        </div>
        <div className="flex items-center justify-between">
          <div>
            <h1 className="text-2xl font-bold text-gray-900">API-Konfiguration</h1>
            <p className="text-gray-500">Externe Dienste anbinden & als Referenztabellen speichern</p>
          </div>
          <Button onClick={() => { setEditingApi(null); setShowWizard(true); }} className="bg-emerald-600 hover:bg-emerald-700">
            <Plus className="h-4 w-4 mr-2" />
            Neue API
          </Button>
        </div>
      </div>

      {/* Tabs */}
      <Tabs value={activeTab} onValueChange={setActiveTab} className="space-y-6">
        <TabsList className="bg-gray-100">
          <TabsTrigger value="overview" className="gap-2">
            <Globe className="h-4 w-4" />
            Übersicht
          </TabsTrigger>
          <TabsTrigger value="tables" className="gap-2">
            <Database className="h-4 w-4" />
            Referenztabellen
          </TabsTrigger>
          <TabsTrigger value="logs" className="gap-2">
            <Clock className="h-4 w-4" />
            Sync-Historie
          </TabsTrigger>
        </TabsList>

        {/* Übersicht Tab */}
        <TabsContent value="overview" className="space-y-6">
          {/* Search */}
          <div className="flex items-center gap-4">
            <div className="relative flex-1 max-w-md">
              <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-muted-foreground" />
              <Input
                placeholder="APIs durchsuchen..."
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                className="pl-10"
              />
            </div>
            <Button variant="outline" onClick={fetchApis}>
              <RefreshCw className="h-4 w-4 mr-2" />
              Aktualisieren
            </Button>
          </div>

          {/* API Cards Grid */}
          <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
            {filteredApis.map((api) => (
              <ApiCard
                key={api.id}
                api={api}
                onEdit={() => { setEditingApi(api); setShowWizard(true); }}
                onDelete={() => setDeleteDialog({ open: true, api })}
                onSync={() => handleSync(api.id)}
                syncing={syncingApis.has(api.id)}
              />
            ))}

            {/* Add New Card */}
            <Card 
              className="border-dashed border-2 hover:border-emerald-300 hover:bg-emerald-50/50 transition-colors cursor-pointer"
              onClick={() => { setEditingApi(null); setShowWizard(true); }}
            >
              <CardContent className="flex flex-col items-center justify-center h-full min-h-[200px] text-muted-foreground">
                <div className="h-12 w-12 rounded-full bg-emerald-100 flex items-center justify-center mb-3">
                  <Plus className="h-6 w-6 text-emerald-600" />
                </div>
                <span className="font-medium">Neue API hinzufügen</span>
              </CardContent>
            </Card>
          </div>

          {filteredApis.length === 0 && !isLoading && (
            <div className="text-center py-12 text-muted-foreground">
              <Globe className="h-12 w-12 mx-auto mb-4 opacity-50" />
              <p>Keine APIs konfiguriert</p>
              <Button 
                variant="link" 
                className="mt-2"
                onClick={() => { setEditingApi(null); setShowWizard(true); }}
              >
                Erste API hinzufügen
              </Button>
            </div>
          )}
        </TabsContent>

        {/* Referenztabellen Tab */}
        <TabsContent value="tables">
          <ReferenceTablesTab />
        </TabsContent>

        {/* Sync-Historie Tab */}
        <TabsContent value="logs">
          <SyncLogsTab apis={apis} />
        </TabsContent>
      </Tabs>

      {/* Wizard Dialog */}
      <ApiConfigWizard
        open={showWizard}
        onClose={() => { setShowWizard(false); setEditingApi(null); }}
        onSuccess={() => { setShowWizard(false); setEditingApi(null); fetchApis(); }}
        editingApi={editingApi}
      />

      {/* Delete Confirmation Dialog */}
      <Dialog open={deleteDialog.open} onOpenChange={(open) => setDeleteDialog({ open })}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>API löschen</DialogTitle>
            <DialogDescription>
              Möchten Sie die API "{deleteDialog.api?.name}" wirklich löschen? 
              {deleteDialog.api?.reference_table?.enabled && (
                <span className="block mt-2 text-red-600">
                  ⚠️ Die zugehörige Referenztabelle und alle Daten werden ebenfalls gelöscht!
                </span>
              )}
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
