import { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { 
  Shield, Plus, Pencil, Trash2, Calendar, Euro, Building2, 
  AlertTriangle, CheckCircle, Clock, Loader2, ChevronDown, ChevronUp,
  ExternalLink, Users
} from 'lucide-react';
import { api } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Badge } from '@/components/ui/badge';
import { cn } from '@/lib/utils';
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from '@/components/ui/tooltip';

interface KreditlimitInfo {
  betrag: number;
  betrag_anfrage?: number;
  gueltig_ab?: string;
  gueltig_bis?: string;
  beschreibung?: string;
  kv_id?: string;
  versicherungsnummer?: string;
  ist_intern: boolean;
  ist_aktiv: boolean;
}

interface KreditlimitsData {
  adresse_id: string;
  gesamtlimit: number;
  limits: KreditlimitInfo[];
}

interface KreditversicherungTabProps {
  adresseId: string;
  isEditing: boolean;
}

const formatCurrency = (value: number): string => {
  return new Intl.NumberFormat('de-DE', {
    style: 'currency',
    currency: 'EUR'
  }).format(value);
};

const formatDate = (dateStr?: string): string => {
  if (!dateStr) return '-';
  try {
    return new Date(dateStr).toLocaleDateString('de-DE');
  } catch {
    return dateStr;
  }
};

export function KreditversicherungTab({ adresseId, isEditing }: KreditversicherungTabProps) {
  const [expanded, setExpanded] = useState<string | null>(null);

  const { data, isLoading, error } = useQuery({
    queryKey: ['kreditlimits', adresseId],
    queryFn: async () => {
      const response = await api.get(`/adressen/${adresseId}/kreditlimits`);
      return response.data.data as KreditlimitsData;
    },
    enabled: !!adresseId,
  });

  const { data: statusData } = useQuery({
    queryKey: ['kreditstatus', adresseId],
    queryFn: async () => {
      const response = await api.get(`/adressen/${adresseId}/kreditstatus`);
      return response.data.data;
    },
    enabled: !!adresseId,
  });

  if (isLoading) {
    return (
      <div className="flex items-center justify-center py-8">
        <Loader2 className="w-6 h-6 animate-spin text-gray-400" />
      </div>
    );
  }

  if (error) {
    return (
      <div className="text-center py-8 text-red-500">
        <AlertTriangle className="w-10 h-10 mx-auto mb-2" />
        <p>Fehler beim Laden der Kreditlimits</p>
      </div>
    );
  }

  const limits = data?.limits || [];
  const gesamtlimit = data?.gesamtlimit || 0;
  const aktiveLimits = limits.filter(l => l.ist_aktiv);
  const inaktiveLimits = limits.filter(l => !l.ist_aktiv);

  // Status-Badge
  const getStatusBadge = () => {
    if (!statusData) return null;
    
    const { status, verfuegbar, aktuelle_forderungen, gesamtlimit } = statusData;
    
    const config = {
      ok: { 
        color: 'bg-green-100 text-green-800 border-green-200', 
        icon: CheckCircle,
        label: 'OK' 
      },
      warnung: { 
        color: 'bg-amber-100 text-amber-800 border-amber-200', 
        icon: AlertTriangle,
        label: 'Warnung (>80%)' 
      },
      ueberschritten: { 
        color: 'bg-red-100 text-red-800 border-red-200', 
        icon: AlertTriangle,
        label: 'Überschritten' 
      },
      kein_limit: { 
        color: 'bg-gray-100 text-gray-600 border-gray-200', 
        icon: Shield,
        label: 'Kein Limit' 
      },
    }[status] || { color: 'bg-gray-100', icon: Shield, label: status };

    const Icon = config.icon;

    return (
      <TooltipProvider>
        <Tooltip>
          <TooltipTrigger asChild>
            <Badge className={cn("gap-1 cursor-help", config.color)}>
              <Icon className="w-3 h-3" />
              {config.label}
            </Badge>
          </TooltipTrigger>
          <TooltipContent className="max-w-xs">
            <div className="text-sm space-y-1">
              <p><strong>Gesamtlimit:</strong> {formatCurrency(gesamtlimit)}</p>
              <p><strong>Forderungen:</strong> {formatCurrency(aktuelle_forderungen)}</p>
              <p><strong>Verfügbar:</strong> {formatCurrency(verfuegbar)}</p>
            </div>
          </TooltipContent>
        </Tooltip>
      </TooltipProvider>
    );
  };

  return (
    <div className="space-y-4">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div className="flex items-center gap-2">
          <Shield className="w-5 h-5 text-purple-600" />
          <h3 className="font-medium text-gray-900">Kreditversicherung</h3>
          {getStatusBadge()}
        </div>
        {isEditing && (
          <Button 
            size="sm" 
            variant="outline"
            onClick={() => window.open('/kreditversicherungen', '_blank')}
            className="text-purple-600 border-purple-200 hover:bg-purple-50"
          >
            <ExternalLink className="w-4 h-4 mr-1" />
            Verwalten
          </Button>
        )}
      </div>

      {/* Status-Übersicht */}
      {statusData && statusData.status !== 'kein_limit' && (
        <div className={cn(
          "p-4 rounded-lg border",
          statusData.status === 'ok' && "bg-green-50 border-green-200",
          statusData.status === 'warnung' && "bg-amber-50 border-amber-200",
          statusData.status === 'ueberschritten' && "bg-red-50 border-red-200",
        )}>
          <div className="grid grid-cols-3 gap-4 text-center">
            <div>
              <p className="text-xs text-gray-500 uppercase tracking-wider">Limit</p>
              <p className="text-lg font-semibold">{formatCurrency(statusData.gesamtlimit)}</p>
            </div>
            <div>
              <p className="text-xs text-gray-500 uppercase tracking-wider">Forderungen</p>
              <p className="text-lg font-semibold">{formatCurrency(statusData.aktuelle_forderungen)}</p>
            </div>
            <div>
              <p className="text-xs text-gray-500 uppercase tracking-wider">Verfügbar</p>
              <p className={cn(
                "text-lg font-semibold",
                statusData.verfuegbar < 0 && "text-red-600"
              )}>
                {formatCurrency(statusData.verfuegbar)}
              </p>
            </div>
          </div>
        </div>
      )}

      {/* Keine Limits */}
      {limits.length === 0 ? (
        <div className="text-center py-8 text-gray-500 bg-gray-50 rounded-lg border-2 border-dashed">
          <Shield className="w-10 h-10 mx-auto mb-2 text-gray-300" />
          <p>Keine Kreditversicherungen vorhanden</p>
          <p className="text-sm text-gray-400 mt-1">
            Diese Adresse ist bei keiner Kreditversicherung hinterlegt
          </p>
          {isEditing && (
            <Button 
              variant="link" 
              onClick={() => window.open('/kreditversicherungen', '_blank')}
              className="mt-2"
            >
              Kreditversicherung anlegen
            </Button>
          )}
        </div>
      ) : (
        <div className="space-y-3">
          {/* Aktive Limits */}
          {aktiveLimits.length > 0 && (
            <div className="space-y-2">
              <p className="text-xs text-gray-500 uppercase tracking-wider font-medium">
                Aktive Limits ({aktiveLimits.length})
              </p>
              {aktiveLimits.map((limit, idx) => (
                <KreditlimitCard 
                  key={`${limit.kv_id}-${idx}`}
                  limit={limit}
                  expanded={expanded === `${limit.kv_id}-${idx}`}
                  onToggle={() => setExpanded(
                    expanded === `${limit.kv_id}-${idx}` ? null : `${limit.kv_id}-${idx}`
                  )}
                />
              ))}
            </div>
          )}

          {/* Inaktive/Abgelaufene Limits */}
          {inaktiveLimits.length > 0 && (
            <div className="space-y-2 opacity-60">
              <p className="text-xs text-gray-500 uppercase tracking-wider font-medium">
                Inaktiv/Abgelaufen ({inaktiveLimits.length})
              </p>
              {inaktiveLimits.map((limit, idx) => (
                <KreditlimitCard 
                  key={`inactive-${limit.kv_id}-${idx}`}
                  limit={limit}
                  expanded={expanded === `inactive-${limit.kv_id}-${idx}`}
                  onToggle={() => setExpanded(
                    expanded === `inactive-${limit.kv_id}-${idx}` ? null : `inactive-${limit.kv_id}-${idx}`
                  )}
                />
              ))}
            </div>
          )}
        </div>
      )}
    </div>
  );
}

interface KreditlimitCardProps {
  limit: KreditlimitInfo;
  expanded: boolean;
  onToggle: () => void;
}

function KreditlimitCard({ limit, expanded, onToggle }: KreditlimitCardProps) {
  return (
    <div className={cn(
      "p-3 rounded-lg border bg-white transition-shadow",
      limit.ist_aktiv ? "hover:shadow-sm" : "bg-gray-50",
      limit.ist_intern && "border-blue-200 bg-blue-50/30"
    )}>
      <div 
        className="flex items-center justify-between cursor-pointer"
        onClick={onToggle}
      >
        <div className="flex items-center gap-3">
          <div className={cn(
            "w-10 h-10 rounded-full flex items-center justify-center",
            limit.ist_intern ? "bg-blue-100" : "bg-purple-100"
          )}>
            {limit.ist_intern ? (
              <Building2 className="w-5 h-5 text-blue-600" />
            ) : (
              <Shield className="w-5 h-5 text-purple-600" />
            )}
          </div>
          <div>
            <p className="font-medium text-gray-900">
              {formatCurrency(limit.betrag)}
            </p>
            <p className="text-sm text-gray-500">
              {limit.beschreibung || (limit.ist_intern ? 'Interner Kredit' : 'Kreditversicherung')}
            </p>
          </div>
        </div>
        <div className="flex items-center gap-2">
          {!limit.ist_aktiv && (
            <Badge variant="secondary" className="text-xs">Inaktiv</Badge>
          )}
          {expanded ? (
            <ChevronUp className="w-4 h-4 text-gray-400" />
          ) : (
            <ChevronDown className="w-4 h-4 text-gray-400" />
          )}
        </div>
      </div>

      {expanded && (
        <div className="mt-3 pt-3 border-t border-gray-100 space-y-2 text-sm">
          {limit.versicherungsnummer && (
            <div className="flex justify-between">
              <span className="text-gray-500">Versicherungsnummer:</span>
              <span className="font-mono">{limit.versicherungsnummer}</span>
            </div>
          )}
          {limit.betrag_anfrage && (
            <div className="flex justify-between">
              <span className="text-gray-500">Angefragter Betrag:</span>
              <span>{formatCurrency(limit.betrag_anfrage)}</span>
            </div>
          )}
          <div className="flex justify-between">
            <span className="text-gray-500">Gültig ab:</span>
            <span>{formatDate(limit.gueltig_ab)}</span>
          </div>
          <div className="flex justify-between">
            <span className="text-gray-500">Gültig bis:</span>
            <span>{formatDate(limit.gueltig_bis)}</span>
          </div>
          {limit.kv_id && (
            <div className="pt-2">
              <Button 
                variant="outline" 
                size="sm" 
                className="w-full"
                onClick={(e) => {
                  e.stopPropagation();
                  window.open(`/kreditversicherungen/${limit.kv_id}`, '_blank');
                }}
              >
                <ExternalLink className="w-3 h-3 mr-1" />
                Details anzeigen
              </Button>
            </div>
          )}
        </div>
      )}
    </div>
  );
}

// Export für Kreditstatus-Badge (zur Verwendung in Fuhren)
export function KreditstatusBadge({ adresseId }: { adresseId: string }) {
  const { data } = useQuery({
    queryKey: ['kreditstatus', adresseId],
    queryFn: async () => {
      const response = await api.get(`/adressen/${adresseId}/kreditstatus`);
      return response.data.data;
    },
    enabled: !!adresseId,
  });

  if (!data || data.status === 'kein_limit' || data.status === 'ok') {
    return null;
  }

  return (
    <TooltipProvider>
      <Tooltip>
        <TooltipTrigger asChild>
          <Badge 
            className={cn(
              "gap-1 cursor-help",
              data.status === 'warnung' && "bg-amber-100 text-amber-800",
              data.status === 'ueberschritten' && "bg-red-100 text-red-800"
            )}
          >
            <AlertTriangle className="w-3 h-3" />
            {data.status === 'warnung' ? 'Kreditlimit >80%' : 'Limit überschritten'}
          </Badge>
        </TooltipTrigger>
        <TooltipContent>
          <p>Verfügbar: {formatCurrency(data.verfuegbar)} von {formatCurrency(data.gesamtlimit)}</p>
        </TooltipContent>
      </Tooltip>
    </TooltipProvider>
  );
}
