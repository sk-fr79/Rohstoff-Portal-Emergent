import { useState, useEffect } from 'react';
import { useAuthStore } from '@/store/authStore';
import { 
  Clock, Loader2, CheckCircle, XCircle, AlertTriangle, RefreshCw,
  ChevronDown, ChevronUp
} from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Badge } from '@/components/ui/badge';
import { 
  Table, TableBody, TableCell, TableHead, TableHeader, TableRow 
} from '@/components/ui/table';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Card } from '@/components/ui/card';
import { cn } from '@/lib/utils';

const API_URL = import.meta.env.VITE_API_URL || '/api';

interface SyncLog {
  id: string;
  api_config_id: string;
  started_at: string;
  completed_at?: string;
  status: 'running' | 'success' | 'error';
  records_fetched?: number;
  records_created?: number;
  records_updated?: number;
  error_message?: string;
  triggered_by?: string;
}

interface ApiConfig {
  id: string;
  name: string;
}

interface Props {
  apis: ApiConfig[];
}

export function SyncLogsTab({ apis }: Props) {
  const { accessToken: token } = useAuthStore();
  const [selectedApi, setSelectedApi] = useState<string>('');
  const [logs, setLogs] = useState<SyncLog[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [expandedLog, setExpandedLog] = useState<string | null>(null);

  // Logs laden wenn API gewählt
  const fetchLogs = async () => {
    if (!selectedApi) {
      setLogs([]);
      return;
    }
    
    setIsLoading(true);
    try {
      const res = await fetch(`${API_URL}/system/apis/${selectedApi}/logs?limit=50`, {
        headers: { 'Authorization': `Bearer ${token}` }
      });
      const data = await res.json();
      if (data.success) {
        setLogs(data.data);
      }
    } catch (error) {
      console.error('Fehler beim Laden der Logs:', error);
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchLogs();
  }, [selectedApi]);

  const formatDate = (dateStr?: string) => {
    if (!dateStr) return '-';
    return new Date(dateStr).toLocaleString('de-DE', {
      day: '2-digit',
      month: '2-digit',
      year: 'numeric',
      hour: '2-digit',
      minute: '2-digit',
      second: '2-digit'
    });
  };

  const formatDuration = (start: string, end?: string) => {
    if (!end) return 'Läuft...';
    const ms = new Date(end).getTime() - new Date(start).getTime();
    if (ms < 1000) return `${ms}ms`;
    if (ms < 60000) return `${(ms / 1000).toFixed(1)}s`;
    return `${Math.floor(ms / 60000)}m ${Math.floor((ms % 60000) / 1000)}s`;
  };

  const StatusIcon = ({ status }: { status: string }) => {
    switch (status) {
      case 'success':
        return <CheckCircle className="h-4 w-4 text-emerald-500" />;
      case 'error':
        return <XCircle className="h-4 w-4 text-red-500" />;
      case 'running':
        return <Loader2 className="h-4 w-4 text-blue-500 animate-spin" />;
      default:
        return <AlertTriangle className="h-4 w-4 text-amber-500" />;
    }
  };

  const StatusBadge = ({ status }: { status: string }) => {
    const config = {
      success: { label: 'Erfolgreich', className: 'bg-emerald-100 text-emerald-700' },
      error: { label: 'Fehler', className: 'bg-red-100 text-red-700' },
      running: { label: 'Läuft', className: 'bg-blue-100 text-blue-700' },
    }[status] || { label: status, className: 'bg-gray-100 text-gray-700' };

    return (
      <Badge variant="outline" className={cn('text-xs', config.className)}>
        {config.label}
      </Badge>
    );
  };

  return (
    <div className="space-y-6">
      {/* Filter */}
      <div className="flex items-center gap-4">
        <div className="w-64">
          <Select value={selectedApi} onValueChange={setSelectedApi}>
            <SelectTrigger>
              <SelectValue placeholder="API auswählen..." />
            </SelectTrigger>
            <SelectContent>
              {apis.map(api => (
                <SelectItem key={api.id} value={api.id}>
                  {api.name}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>
        
        {selectedApi && (
          <Button variant="outline" onClick={fetchLogs} disabled={isLoading}>
            <RefreshCw className={cn("h-4 w-4 mr-2", isLoading && "animate-spin")} />
            Aktualisieren
          </Button>
        )}
      </div>

      {/* Logs Table */}
      {!selectedApi ? (
        <div className="text-center py-12 text-muted-foreground">
          <Clock className="h-12 w-12 mx-auto mb-4 opacity-50" />
          <p>Wählen Sie eine API aus, um die Sync-Historie anzuzeigen</p>
        </div>
      ) : isLoading ? (
        <div className="flex items-center justify-center py-12">
          <Loader2 className="h-8 w-8 animate-spin text-emerald-600" />
        </div>
      ) : logs.length === 0 ? (
        <div className="text-center py-12 text-muted-foreground">
          <Clock className="h-12 w-12 mx-auto mb-4 opacity-50" />
          <p>Noch keine Synchronisierungen durchgeführt</p>
        </div>
      ) : (
        <Card>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead className="w-[40px]"></TableHead>
                <TableHead>Zeitpunkt</TableHead>
                <TableHead>Status</TableHead>
                <TableHead className="text-right">Abgerufen</TableHead>
                <TableHead className="text-right">Erstellt</TableHead>
                <TableHead className="text-right">Aktualisiert</TableHead>
                <TableHead>Dauer</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {logs.map((log) => (
                <>
                  <TableRow 
                    key={log.id}
                    className="cursor-pointer hover:bg-gray-50"
                    onClick={() => setExpandedLog(expandedLog === log.id ? null : log.id)}
                  >
                    <TableCell>
                      {expandedLog === log.id ? (
                        <ChevronUp className="h-4 w-4 text-muted-foreground" />
                      ) : (
                        <ChevronDown className="h-4 w-4 text-muted-foreground" />
                      )}
                    </TableCell>
                    <TableCell>
                      <div className="flex items-center gap-2">
                        <StatusIcon status={log.status} />
                        <span className="text-sm">{formatDate(log.started_at)}</span>
                      </div>
                    </TableCell>
                    <TableCell>
                      <StatusBadge status={log.status} />
                    </TableCell>
                    <TableCell className="text-right font-mono text-sm">
                      {log.records_fetched?.toLocaleString('de-DE') || '-'}
                    </TableCell>
                    <TableCell className="text-right font-mono text-sm text-emerald-600">
                      {log.records_created ? `+${log.records_created.toLocaleString('de-DE')}` : '-'}
                    </TableCell>
                    <TableCell className="text-right font-mono text-sm text-blue-600">
                      {log.records_updated ? `~${log.records_updated.toLocaleString('de-DE')}` : '-'}
                    </TableCell>
                    <TableCell className="text-sm text-muted-foreground">
                      {formatDuration(log.started_at, log.completed_at)}
                    </TableCell>
                  </TableRow>
                  
                  {/* Expanded Details */}
                  {expandedLog === log.id && (
                    <TableRow>
                      <TableCell colSpan={7} className="bg-gray-50 p-4">
                        <div className="space-y-2 text-sm">
                          <div className="grid grid-cols-2 gap-4">
                            <div>
                              <span className="text-muted-foreground">Gestartet:</span>{' '}
                              {formatDate(log.started_at)}
                            </div>
                            <div>
                              <span className="text-muted-foreground">Beendet:</span>{' '}
                              {formatDate(log.completed_at)}
                            </div>
                          </div>
                          
                          {log.error_message && (
                            <div className="p-3 bg-red-50 border border-red-200 rounded-lg">
                              <p className="text-red-700 font-medium">Fehlermeldung:</p>
                              <p className="text-red-600 mt-1 font-mono text-xs whitespace-pre-wrap">
                                {log.error_message}
                              </p>
                            </div>
                          )}
                        </div>
                      </TableCell>
                    </TableRow>
                  )}
                </>
              ))}
            </TableBody>
          </Table>
        </Card>
      )}
    </div>
  );
}
