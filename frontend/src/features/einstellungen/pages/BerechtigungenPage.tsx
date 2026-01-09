import { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { 
  Shield, Loader2, Check, X, Eye, Edit, Ban, Minus,
  Users, Building2, User, Info
} from 'lucide-react';
import { api } from '@/services/api/client';
import { useAuthStore } from '@/store/authStore';
import { Button } from '@/components/ui/button';
import { Badge } from '@/components/ui/badge';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Tabs, TabsContent, TabsList, TabsTrigger } from '@/components/ui/tabs';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { cn } from '@/lib/utils';
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from '@/components/ui/tooltip';

interface Modul {
  key: string;
  name: string;
  gruppe: string;
}

interface Rolle {
  id: string;
  name: string;
  farbe?: string;
}

interface Abteilung {
  id: string;
  name: string;
}

interface Berechtigung {
  id: string;
  modul: string;
  ziel_typ: string;
  ziel_id: string;
  level: string;
}

interface MatrixData {
  module: Modul[];
  rollen: Rolle[];
  abteilungen: Abteilung[];
  berechtigungen: Berechtigung[];
  levels: string[];
}

const LEVEL_CONFIG: Record<string, { icon: typeof Check; label: string; color: string; bgColor: string }> = {
  full: { icon: Check, label: 'Vollzugriff', color: 'text-green-600', bgColor: 'bg-green-100' },
  write: { icon: Edit, label: 'Lesen & Schreiben', color: 'text-blue-600', bgColor: 'bg-blue-100' },
  read: { icon: Eye, label: 'Nur Lesen', color: 'text-amber-600', bgColor: 'bg-amber-100' },
  denied: { icon: Ban, label: 'Kein Zugriff', color: 'text-red-600', bgColor: 'bg-red-100' },
};

export function BerechtigungenPage() {
  const { user } = useAuthStore();
  const queryClient = useQueryClient();
  const [activeTab, setActiveTab] = useState('rollen');

  // Nur Admins
  if (!user?.istAdmin) {
    return (
      <div className="p-6 flex items-center justify-center min-h-[400px]">
        <div className="text-center">
          <Shield className="h-16 w-16 mx-auto text-gray-300 mb-4" />
          <h2 className="text-xl font-semibold text-gray-700">Kein Zugriff</h2>
        </div>
      </div>
    );
  }

  // Query
  const { data: matrix, isLoading } = useQuery({
    queryKey: ['admin-berechtigungen-matrix'],
    queryFn: async () => {
      const response = await api.get('/admin/berechtigungen/matrix');
      return response.data.data as MatrixData;
    },
  });

  // Mutation
  const setPermissionMutation = useMutation({
    mutationFn: (data: { modul: string; ziel_typ: string; ziel_id: string; level: string }) =>
      api.post('/admin/berechtigungen', data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['admin-berechtigungen-matrix'] });
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Speichern');
    },
  });

  const getPermissionLevel = (modul: string, zielTyp: string, zielId: string): string | null => {
    if (!matrix) return null;
    const perm = matrix.berechtigungen.find(
      b => b.modul === modul && b.ziel_typ === zielTyp && b.ziel_id === zielId
    );
    return perm?.level || null;
  };

  const handlePermissionChange = (modul: string, zielTyp: string, zielId: string, level: string) => {
    setPermissionMutation.mutate({ modul, ziel_typ: zielTyp, ziel_id: zielId, level });
  };

  // Gruppiere Module nach Gruppe
  const moduleByGroup = matrix?.module.reduce((acc, m) => {
    if (!acc[m.gruppe]) acc[m.gruppe] = [];
    acc[m.gruppe].push(m);
    return acc;
  }, {} as Record<string, Modul[]>) || {};

  if (isLoading) {
    return (
      <div className="p-6 flex items-center justify-center min-h-[400px]">
        <Loader2 className="h-8 w-8 animate-spin text-gray-400" />
      </div>
    );
  }

  return (
    <div className="p-6 max-w-7xl mx-auto" data-testid="berechtigungen-page">
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-gray-900">Zugriffssteuerung</h1>
        <p className="text-gray-500">Konfigurieren Sie Berechtigungen für Module nach Rollen und Abteilungen</p>
      </div>

      {/* Legende */}
      <Card className="mb-6">
        <CardHeader className="pb-3">
          <CardTitle className="text-sm flex items-center gap-2">
            <Info className="w-4 h-4" />
            Berechtigungsstufen
          </CardTitle>
        </CardHeader>
        <CardContent>
          <div className="flex flex-wrap gap-4">
            {Object.entries(LEVEL_CONFIG).map(([key, config]) => (
              <div key={key} className="flex items-center gap-2">
                <div className={cn("w-8 h-8 rounded flex items-center justify-center", config.bgColor)}>
                  <config.icon className={cn("w-4 h-4", config.color)} />
                </div>
                <span className="text-sm">{config.label}</span>
              </div>
            ))}
            <div className="flex items-center gap-2">
              <div className="w-8 h-8 rounded bg-gray-100 flex items-center justify-center">
                <Minus className="w-4 h-4 text-gray-400" />
              </div>
              <span className="text-sm text-gray-500">Nicht definiert (erbt von Rolle/Abteilung)</span>
            </div>
          </div>
        </CardContent>
      </Card>

      {/* Info */}
      <Card className="mb-6 bg-blue-50 border-blue-200">
        <CardContent className="pt-4">
          <div className="flex items-start gap-3">
            <Info className="w-5 h-5 text-blue-600 mt-0.5" />
            <div className="text-sm text-blue-800">
              <p className="font-medium mb-1">Berechtigungsvererbung</p>
              <ul className="list-disc list-inside space-y-1 text-blue-700">
                <li><strong>Priorität:</strong> Benutzer &gt; Rolle &gt; Abteilung</li>
                <li>Bei mehreren Abteilungen gilt das höchste Recht (außer "denied")</li>
                <li>Administratoren haben automatisch Vollzugriff auf alle Module</li>
              </ul>
            </div>
          </div>
        </CardContent>
      </Card>

      <Tabs value={activeTab} onValueChange={setActiveTab}>
        <TabsList className="mb-6">
          <TabsTrigger value="rollen" className="flex items-center gap-2">
            <Shield className="w-4 h-4" />
            Nach Rollen
          </TabsTrigger>
          <TabsTrigger value="abteilungen" className="flex items-center gap-2">
            <Building2 className="w-4 h-4" />
            Nach Abteilungen
          </TabsTrigger>
        </TabsList>

        {/* Rollen-Matrix */}
        <TabsContent value="rollen">
          <Card>
            <CardContent className="pt-6">
              <div className="overflow-x-auto">
                <table className="w-full border-collapse">
                  <thead>
                    <tr>
                      <th className="text-left p-3 bg-gray-50 border-b font-medium text-gray-700 min-w-[200px]">
                        Modul
                      </th>
                      {matrix?.rollen.map(rolle => (
                        <th key={rolle.id} className="p-3 bg-gray-50 border-b text-center min-w-[120px]">
                          <Badge style={{ backgroundColor: rolle.farbe || '#6B7280' }} className="text-white">
                            {rolle.name}
                          </Badge>
                        </th>
                      ))}
                    </tr>
                  </thead>
                  <tbody>
                    {Object.entries(moduleByGroup).map(([gruppe, module]) => (
                      <>
                        <tr key={`group-${gruppe}`}>
                          <td 
                            colSpan={(matrix?.rollen.length || 0) + 1} 
                            className="p-2 bg-gray-100 text-xs font-semibold text-gray-600 uppercase tracking-wider"
                          >
                            {gruppe}
                          </td>
                        </tr>
                        {module.map(modul => (
                          <tr key={modul.key} className="border-b hover:bg-gray-50">
                            <td className="p-3 font-medium">{modul.name}</td>
                            {matrix?.rollen.map(rolle => {
                              const level = getPermissionLevel(modul.key, 'rolle', rolle.id);
                              return (
                                <td key={rolle.id} className="p-2 text-center">
                                  <PermissionSelector
                                    currentLevel={level}
                                    onChange={(newLevel) => 
                                      handlePermissionChange(modul.key, 'rolle', rolle.id, newLevel)
                                    }
                                    disabled={rolle.name === 'Administrator'}
                                    isAdmin={rolle.name === 'Administrator'}
                                  />
                                </td>
                              );
                            })}
                          </tr>
                        ))}
                      </>
                    ))}
                  </tbody>
                </table>
              </div>
            </CardContent>
          </Card>
        </TabsContent>

        {/* Abteilungen-Matrix */}
        <TabsContent value="abteilungen">
          <Card>
            <CardContent className="pt-6">
              <div className="overflow-x-auto">
                <table className="w-full border-collapse">
                  <thead>
                    <tr>
                      <th className="text-left p-3 bg-gray-50 border-b font-medium text-gray-700 min-w-[200px]">
                        Modul
                      </th>
                      {matrix?.abteilungen.map(abt => (
                        <th key={abt.id} className="p-3 bg-gray-50 border-b text-center min-w-[120px]">
                          <Badge variant="outline">{abt.name}</Badge>
                        </th>
                      ))}
                    </tr>
                  </thead>
                  <tbody>
                    {Object.entries(moduleByGroup).map(([gruppe, module]) => (
                      <>
                        <tr key={`group-${gruppe}`}>
                          <td 
                            colSpan={(matrix?.abteilungen.length || 0) + 1} 
                            className="p-2 bg-gray-100 text-xs font-semibold text-gray-600 uppercase tracking-wider"
                          >
                            {gruppe}
                          </td>
                        </tr>
                        {module.map(modul => (
                          <tr key={modul.key} className="border-b hover:bg-gray-50">
                            <td className="p-3 font-medium">{modul.name}</td>
                            {matrix?.abteilungen.map(abt => {
                              const level = getPermissionLevel(modul.key, 'abteilung', abt.id);
                              return (
                                <td key={abt.id} className="p-2 text-center">
                                  <PermissionSelector
                                    currentLevel={level}
                                    onChange={(newLevel) => 
                                      handlePermissionChange(modul.key, 'abteilung', abt.id, newLevel)
                                    }
                                  />
                                </td>
                              );
                            })}
                          </tr>
                        ))}
                      </>
                    ))}
                  </tbody>
                </table>
              </div>
            </CardContent>
          </Card>
        </TabsContent>
      </Tabs>
    </div>
  );
}

interface PermissionSelectorProps {
  currentLevel: string | null;
  onChange: (level: string) => void;
  disabled?: boolean;
  isAdmin?: boolean;
}

function PermissionSelector({ currentLevel, onChange, disabled, isAdmin }: PermissionSelectorProps) {
  if (isAdmin) {
    return (
      <TooltipProvider>
        <Tooltip>
          <TooltipTrigger asChild>
            <div className="w-10 h-10 mx-auto rounded bg-green-100 flex items-center justify-center cursor-not-allowed">
              <Check className="w-5 h-5 text-green-600" />
            </div>
          </TooltipTrigger>
          <TooltipContent>
            <p>Administratoren haben automatisch Vollzugriff</p>
          </TooltipContent>
        </Tooltip>
      </TooltipProvider>
    );
  }

  const config = currentLevel ? LEVEL_CONFIG[currentLevel] : null;
  const Icon = config?.icon || Minus;

  return (
    <Select value={currentLevel || 'none'} onValueChange={onChange} disabled={disabled}>
      <SelectTrigger className="w-10 h-10 p-0 border-0 bg-transparent mx-auto">
        <div className={cn(
          "w-10 h-10 rounded flex items-center justify-center transition-colors",
          config?.bgColor || "bg-gray-100 hover:bg-gray-200"
        )}>
          <Icon className={cn("w-5 h-5", config?.color || "text-gray-400")} />
        </div>
      </SelectTrigger>
      <SelectContent>
        <SelectItem value="full">
          <div className="flex items-center gap-2">
            <Check className="w-4 h-4 text-green-600" />
            Vollzugriff
          </div>
        </SelectItem>
        <SelectItem value="write">
          <div className="flex items-center gap-2">
            <Edit className="w-4 h-4 text-blue-600" />
            Lesen & Schreiben
          </div>
        </SelectItem>
        <SelectItem value="read">
          <div className="flex items-center gap-2">
            <Eye className="w-4 h-4 text-amber-600" />
            Nur Lesen
          </div>
        </SelectItem>
        <SelectItem value="denied">
          <div className="flex items-center gap-2">
            <Ban className="w-4 h-4 text-red-600" />
            Kein Zugriff (Ausblenden)
          </div>
        </SelectItem>
      </SelectContent>
    </Select>
  );
}
