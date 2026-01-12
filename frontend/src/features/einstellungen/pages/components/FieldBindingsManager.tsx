import { useState, useEffect } from 'react';
import { useAuthStore } from '@/store/authStore';
import { toast } from 'sonner';
import { 
  Link2, Plus, Trash2, Loader2, Database, Globe,
  Package, MapPin, FileText, Truck, Settings2, Check,
  Zap, Clock, RefreshCw
} from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Card, CardContent } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { Label } from '@/components/ui/label';
import { Input } from '@/components/ui/input';
import {
  Dialog, DialogContent, DialogDescription, DialogFooter,
  DialogHeader, DialogTitle,
} from '@/components/ui/dialog';
import {
  Select, SelectContent, SelectItem, SelectTrigger, SelectValue
} from '@/components/ui/select';
import {
  Table, TableBody, TableCell, TableHead, TableHeader, TableRow
} from '@/components/ui/table';
import { Switch } from '@/components/ui/switch';
import {
  Tabs, TabsContent, TabsList, TabsTrigger
} from '@/components/ui/tabs';
import { cn } from '@/lib/utils';

const API_URL = import.meta.env.VITE_API_URL || '/api';

interface FieldBinding {
  id: string;
  source_type: 'reference_table' | 'api_query';
  reference_table_id?: string;
  reference_table_name?: string;
  reference_table_display_name?: string;
  api_config_id?: string;
  api_name?: string;
  module: string;
  module_label: string;
  field_name: string;
  field_label: string;
  display_field: string;
  value_field: string;
  additional_display_fields: string[];
  is_required: boolean;
  allow_search: boolean;
  min_search_chars: number;
  cache_ttl_seconds: number;
  fallback_to_reference: boolean;
  created_at: string;
}

interface ReferenceTable {
  id: string;
  table_name: string;
  display_name: string;
  record_count: number;
  columns_config?: Array<{ target_field: string; source_path: string }>;
}

interface ApiConfig {
  id: string;
  name: string;
  base_url: string;
  is_active: boolean;
  response_mapping?: {
    field_mapping?: Array<{ source_path: string; target_field: string }>;
  };
}

interface ModuleInfo {
  label: string;
  fields: Array<{ 
    name: string; 
    label: string; 
    category?: string;
    is_subfield?: boolean;
  }>;
}

// Kategorie-Icons und Labels
const CATEGORY_CONFIG: Record<string, { label: string; icon: any }> = {
  stamm: { label: 'Stammdaten', icon: Package },
  adresse: { label: 'Adresse', icon: MapPin },
  kontakt: { label: 'Kontakt', icon: Settings2 },
  steuer: { label: 'Steuer & UST', icon: FileText },
  bank: { label: 'Bankverbindungen', icon: Settings2 },
  zahlung: { label: 'Zahlungsbedingungen', icon: Settings2 },
  zoll: { label: 'Zoll & Export', icon: Globe },
  transport: { label: 'Transport', icon: Truck },
  betreuer: { label: 'Betreuer', icon: Settings2 },
  position: { label: 'Positionen', icon: Settings2 },
  sonstiges: { label: 'Sonstige', icon: Settings2 },
};

const MODULE_ICONS: Record<string, any> = {
  artikel: Package,
  adressen: MapPin,
  kontrakte: FileText,
  fuhren: Truck,
};

interface FieldBindingsManagerProps {
  referenceTableId?: string;
  referenceTableName?: string;
  onBindingCreated?: () => void;
}

export function FieldBindingsManager({ 
  referenceTableId, 
  referenceTableName,
  onBindingCreated 
}: FieldBindingsManagerProps) {
  const { accessToken: token } = useAuthStore();
  const [bindings, setBindings] = useState<FieldBinding[]>([]);
  const [modules, setModules] = useState<Record<string, ModuleInfo>>({});
  const [referenceTables, setReferenceTables] = useState<ReferenceTable[]>([]);
  const [apiConfigs, setApiConfigs] = useState<ApiConfig[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  
  // Dialog State
  const [showCreateDialog, setShowCreateDialog] = useState(false);
  const [creating, setCreating] = useState(false);
  const [deleting, setDeleting] = useState<string | null>(null);
  
  // Form State
  const [sourceType, setSourceType] = useState<'reference_table' | 'api_query'>('reference_table');
  const [selectedRefTable, setSelectedRefTable] = useState<string>(referenceTableId || '');
  const [selectedApiConfig, setSelectedApiConfig] = useState<string>('');
  const [selectedModule, setSelectedModule] = useState<string>('');
  const [selectedField, setSelectedField] = useState<string>('');
  const [displayField, setDisplayField] = useState<string>('bezeichnung');
  const [valueField, setValueField] = useState<string>('code');
  const [isRequired, setIsRequired] = useState(false);
  const [minSearchChars, setMinSearchChars] = useState(3);
  const [cacheTtl, setCacheTtl] = useState(300);
  const [fallbackToReference, setFallbackToReference] = useState(true);

  // Daten laden
  const fetchData = async () => {
    setIsLoading(true);
    try {
      const headers = { 'Authorization': `Bearer ${token}` };
      
      // Parallel laden
      const [bindingsRes, modulesRes, tablesRes, apisRes] = await Promise.all([
        fetch(`${API_URL}/system/field-bindings${referenceTableId ? `?reference_table_id=${referenceTableId}` : ''}`, { headers }),
        fetch(`${API_URL}/system/modules`, { headers }),
        fetch(`${API_URL}/system/reference-tables`, { headers }),
        fetch(`${API_URL}/system/apis`, { headers }),
      ]);
      
      const [bindingsData, modulesData, tablesData, apisData] = await Promise.all([
        bindingsRes.json(),
        modulesRes.json(),
        tablesRes.json(),
        apisRes.json(),
      ]);
      
      if (bindingsData.success) setBindings(bindingsData.data);
      if (modulesData.success) setModules(modulesData.data);
      if (tablesData.success) setReferenceTables(tablesData.data);
      if (apisData.success) setApiConfigs(apisData.data.filter((a: ApiConfig) => a.is_active));
      
    } catch (error) {
      console.error('Error loading data:', error);
      toast.error('Fehler beim Laden der Daten');
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    fetchData();
  }, [referenceTableId]);

  // Verknüpfung erstellen
  const handleCreate = async () => {
    if (!selectedModule || !selectedField) {
      toast.error('Bitte Modul und Feld auswählen');
      return;
    }

    if (sourceType === 'reference_table' && !selectedRefTable) {
      toast.error('Bitte Referenztabelle auswählen');
      return;
    }

    if (sourceType === 'api_query' && !selectedApiConfig) {
      toast.error('Bitte API auswählen');
      return;
    }

    setCreating(true);
    try {
      const payload: any = {
        source_type: sourceType,
        module: selectedModule,
        field_name: selectedField,
        display_field: displayField,
        value_field: valueField,
        additional_display_fields: [],
        is_required: isRequired,
        allow_search: true,
        min_search_chars: minSearchChars,
        cache_ttl_seconds: cacheTtl,
        fallback_to_reference: fallbackToReference,
      };

      if (sourceType === 'reference_table') {
        payload.reference_table_id = selectedRefTable;
      } else {
        payload.api_config_id = selectedApiConfig;
        // Optional: Referenztabelle für Fallback
        if (fallbackToReference && selectedRefTable) {
          payload.reference_table_id = selectedRefTable;
        }
      }

      const res = await fetch(`${API_URL}/system/field-bindings`, {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(payload)
      });

      const data = await res.json();

      if (data.success) {
        toast.success('Verknüpfung erstellt');
        setShowCreateDialog(false);
        resetForm();
        fetchData();
        onBindingCreated?.();
      } else {
        toast.error(data.detail || 'Fehler beim Erstellen');
      }
    } catch (error) {
      toast.error('Fehler beim Erstellen');
    } finally {
      setCreating(false);
    }
  };

  // Verknüpfung löschen
  const handleDelete = async (bindingId: string) => {
    setDeleting(bindingId);
    try {
      const res = await fetch(`${API_URL}/system/field-bindings/${bindingId}`, {
        method: 'DELETE',
        headers: { 'Authorization': `Bearer ${token}` }
      });

      const data = await res.json();

      if (data.success) {
        toast.success('Verknüpfung gelöscht');
        fetchData();
      } else {
        toast.error(data.detail || 'Fehler beim Löschen');
      }
    } catch (error) {
      toast.error('Fehler beim Löschen');
    } finally {
      setDeleting(null);
    }
  };

  const resetForm = () => {
    setSourceType('reference_table');
    if (!referenceTableId) setSelectedRefTable('');
    setSelectedApiConfig('');
    setSelectedModule('');
    setSelectedField('');
    setDisplayField('bezeichnung');
    setValueField('code');
    setIsRequired(false);
    setMinSearchChars(3);
    setCacheTtl(300);
    setFallbackToReference(true);
  };

  // Verfügbare Felder für ausgewähltes Modul (nach Kategorie sortiert)
  const availableFields = selectedModule 
    ? (modules[selectedModule]?.fields || [])
        .filter(f => !bindings.some(b => b.module === selectedModule && b.field_name === f.name))
        .sort((a, b) => {
          // Sortiere nach Kategorie, dann nach Label
          const catA = a.category || 'sonstiges';
          const catB = b.category || 'sonstiges';
          if (catA !== catB) return catA.localeCompare(catB);
          return a.label.localeCompare(b.label);
        })
    : [];

  // Spalten der ausgewählten Referenztabelle oder API
  const selectedSource = sourceType === 'reference_table'
    ? referenceTables.find(t => t.id === selectedRefTable)
    : apiConfigs.find(a => a.id === selectedApiConfig);

  const availableColumns = sourceType === 'reference_table'
    ? (selectedSource as ReferenceTable)?.columns_config || []
    : (selectedSource as ApiConfig)?.response_mapping?.field_mapping || [];

  if (isLoading) {
    return (
      <div className="flex items-center justify-center py-8">
        <Loader2 className="h-6 w-6 animate-spin text-emerald-600" />
      </div>
    );
  }

  return (
    <div className="space-y-4">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h3 className="text-lg font-semibold flex items-center gap-2">
            <Link2 className="h-5 w-5 text-emerald-600" />
            Feld-Verknüpfungen
          </h3>
          <p className="text-sm text-muted-foreground">
            Verknüpfen Sie Referenztabellen oder APIs mit Modulfeldern
          </p>
        </div>
        <Button onClick={() => setShowCreateDialog(true)} className="gap-2">
          <Plus className="h-4 w-4" />
          Neue Verknüpfung
        </Button>
      </div>

      {/* Bindings Liste */}
      {bindings.length === 0 ? (
        <Card className="border-dashed">
          <CardContent className="py-8 text-center">
            <Link2 className="h-10 w-10 mx-auto mb-3 text-muted-foreground opacity-50" />
            <p className="text-muted-foreground">Keine Verknüpfungen vorhanden</p>
            <p className="text-sm text-muted-foreground mt-1">
              Erstellen Sie eine Verknüpfung, um Referenzdaten oder Live-API-Daten in Modulen anzuzeigen
            </p>
          </CardContent>
        </Card>
      ) : (
        <Card>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead>Modul / Feld</TableHead>
                <TableHead>Datenquelle</TableHead>
                <TableHead>Mapping</TableHead>
                <TableHead>Optionen</TableHead>
                <TableHead className="w-[80px]"></TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {bindings.map((binding) => {
                const ModuleIcon = MODULE_ICONS[binding.module] || Settings2;
                const isApiQuery = binding.source_type === 'api_query';
                
                return (
                  <TableRow key={binding.id}>
                    <TableCell>
                      <div className="flex items-center gap-3">
                        <div className="h-8 w-8 rounded-lg bg-blue-100 flex items-center justify-center">
                          <ModuleIcon className="h-4 w-4 text-blue-600" />
                        </div>
                        <div>
                          <p className="font-medium">{binding.module_label}</p>
                          <p className="text-sm text-muted-foreground">{binding.field_label}</p>
                        </div>
                      </div>
                    </TableCell>
                    <TableCell>
                      <div className="flex items-center gap-2">
                        {isApiQuery ? (
                          <>
                            <Globe className="h-4 w-4 text-blue-600" />
                            <div>
                              <span className="font-medium">{binding.api_name || 'Unbekannt'}</span>
                              <Badge variant="outline" className="ml-2 text-[10px] bg-blue-50 text-blue-600">
                                Live
                              </Badge>
                            </div>
                          </>
                        ) : (
                          <>
                            <Database className="h-4 w-4 text-emerald-600" />
                            <span>{binding.reference_table_display_name || 'Unbekannt'}</span>
                          </>
                        )}
                      </div>
                      {isApiQuery && binding.fallback_to_reference && binding.reference_table_display_name && (
                        <p className="text-xs text-muted-foreground mt-1">
                          Fallback: {binding.reference_table_display_name}
                        </p>
                      )}
                    </TableCell>
                    <TableCell>
                      <div className="text-sm">
                        <span className="text-muted-foreground">Wert:</span> {binding.value_field}
                        <br />
                        <span className="text-muted-foreground">Anzeige:</span> {binding.display_field}
                      </div>
                    </TableCell>
                    <TableCell>
                      <div className="flex flex-wrap gap-1">
                        {binding.is_required && (
                          <Badge variant="outline" className="text-xs bg-red-50 text-red-700 border-red-200">
                            Pflicht
                          </Badge>
                        )}
                        {isApiQuery && (
                          <>
                            <Badge variant="outline" className="text-xs">
                              {binding.min_search_chars}+ Zeichen
                            </Badge>
                            <Badge variant="outline" className="text-xs">
                              <Clock className="h-3 w-3 mr-1" />
                              {Math.floor(binding.cache_ttl_seconds / 60)} Min Cache
                            </Badge>
                          </>
                        )}
                      </div>
                    </TableCell>
                    <TableCell>
                      <Button
                        variant="ghost"
                        size="icon"
                        onClick={() => handleDelete(binding.id)}
                        disabled={deleting === binding.id}
                        className="text-red-600 hover:text-red-700 hover:bg-red-50"
                      >
                        {deleting === binding.id ? (
                          <Loader2 className="h-4 w-4 animate-spin" />
                        ) : (
                          <Trash2 className="h-4 w-4" />
                        )}
                      </Button>
                    </TableCell>
                  </TableRow>
                );
              })}
            </TableBody>
          </Table>
        </Card>
      )}

      {/* Create Dialog */}
      <Dialog open={showCreateDialog} onOpenChange={setShowCreateDialog}>
        <DialogContent className="max-w-lg">
          <DialogHeader>
            <DialogTitle className="flex items-center gap-2">
              <Link2 className="h-5 w-5 text-emerald-600" />
              Neue Feld-Verknüpfung
            </DialogTitle>
            <DialogDescription>
              Verknüpfen Sie eine Datenquelle mit einem Modul-Feld
            </DialogDescription>
          </DialogHeader>

          <div className="space-y-4 py-4">
            {/* Datenquelle Tabs */}
            <Tabs value={sourceType} onValueChange={(v) => setSourceType(v as any)}>
              <TabsList className="grid w-full grid-cols-2">
                <TabsTrigger value="reference_table" className="gap-2">
                  <Database className="h-4 w-4" />
                  Referenztabelle
                </TabsTrigger>
                <TabsTrigger value="api_query" className="gap-2">
                  <Globe className="h-4 w-4" />
                  Live API
                </TabsTrigger>
              </TabsList>

              <TabsContent value="reference_table" className="mt-4">
                <div>
                  <Label>Referenztabelle</Label>
                  <Select value={selectedRefTable} onValueChange={setSelectedRefTable}>
                    <SelectTrigger className="mt-1.5">
                      <SelectValue placeholder="Referenztabelle wählen..." />
                    </SelectTrigger>
                    <SelectContent>
                      {referenceTables.map((table) => (
                        <SelectItem key={table.id} value={table.id}>
                          <div className="flex items-center gap-2">
                            <Database className="h-4 w-4 text-emerald-600" />
                            <span>{table.display_name}</span>
                            <Badge variant="outline" className="text-xs ml-2">
                              {table.record_count} Einträge
                            </Badge>
                          </div>
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>
              </TabsContent>

              <TabsContent value="api_query" className="mt-4 space-y-4">
                <div>
                  <Label>API-Konfiguration</Label>
                  <Select value={selectedApiConfig} onValueChange={setSelectedApiConfig}>
                    <SelectTrigger className="mt-1.5">
                      <SelectValue placeholder="API wählen..." />
                    </SelectTrigger>
                    <SelectContent>
                      {apiConfigs.map((api) => (
                        <SelectItem key={api.id} value={api.id}>
                          <div className="flex items-center gap-2">
                            <Globe className="h-4 w-4 text-blue-600" />
                            <span>{api.name}</span>
                            <Badge variant="outline" className="text-xs ml-2 bg-blue-50 text-blue-600">
                              Live
                            </Badge>
                          </div>
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>

                {/* API-spezifische Optionen */}
                <div className="grid grid-cols-2 gap-4 p-3 bg-blue-50 rounded-lg">
                  <div>
                    <Label className="text-xs">Mindest-Suchzeichen</Label>
                    <Input
                      type="number"
                      min={1}
                      max={10}
                      value={minSearchChars}
                      onChange={(e) => setMinSearchChars(parseInt(e.target.value) || 3)}
                      className="mt-1 h-8"
                    />
                  </div>
                  <div>
                    <Label className="text-xs">Cache (Sekunden)</Label>
                    <Input
                      type="number"
                      min={0}
                      max={3600}
                      value={cacheTtl}
                      onChange={(e) => setCacheTtl(parseInt(e.target.value) || 300)}
                      className="mt-1 h-8"
                    />
                  </div>
                </div>

                {/* Fallback Option */}
                <div className="flex items-center justify-between p-3 bg-amber-50 rounded-lg">
                  <div>
                    <Label>Fallback auf Referenztabelle</Label>
                    <p className="text-xs text-muted-foreground">
                      Bei API-Fehler lokale Daten nutzen
                    </p>
                  </div>
                  <Switch 
                    checked={fallbackToReference} 
                    onCheckedChange={setFallbackToReference} 
                  />
                </div>

                {fallbackToReference && (
                  <div>
                    <Label className="text-xs">Fallback-Referenztabelle</Label>
                    <Select value={selectedRefTable} onValueChange={setSelectedRefTable}>
                      <SelectTrigger className="mt-1.5">
                        <SelectValue placeholder="Optional: Fallback wählen..." />
                      </SelectTrigger>
                      <SelectContent>
                        {referenceTables.map((table) => (
                          <SelectItem key={table.id} value={table.id}>
                            {table.display_name}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>
                )}
              </TabsContent>
            </Tabs>

            {/* Modul */}
            <div>
              <Label>Modul</Label>
              <Select value={selectedModule} onValueChange={(v) => {
                setSelectedModule(v);
                setSelectedField('');
              }}>
                <SelectTrigger className="mt-1.5">
                  <SelectValue placeholder="Modul wählen..." />
                </SelectTrigger>
                <SelectContent>
                  {Object.entries(modules).map(([key, info]) => {
                    const Icon = MODULE_ICONS[key] || Settings2;
                    return (
                      <SelectItem key={key} value={key}>
                        <div className="flex items-center gap-2">
                          <Icon className="h-4 w-4" />
                          <span>{info.label}</span>
                        </div>
                      </SelectItem>
                    );
                  })}
                </SelectContent>
              </Select>
            </div>

            {/* Feld */}
            {selectedModule && (
              <div>
                <Label>Feld</Label>
                <Select value={selectedField} onValueChange={setSelectedField}>
                  <SelectTrigger className="mt-1.5">
                    <SelectValue placeholder="Feld wählen..." />
                  </SelectTrigger>
                  <SelectContent className="max-h-[300px]">
                    {availableFields.length === 0 ? (
                      <div className="px-2 py-3 text-sm text-muted-foreground text-center">
                        Alle Felder bereits verknüpft
                      </div>
                    ) : (
                      // Felder nach Kategorien gruppieren
                      (() => {
                        const grouped = availableFields.reduce((acc, field) => {
                          const cat = field.category || 'sonstiges';
                          if (!acc[cat]) acc[cat] = [];
                          acc[cat].push(field);
                          return acc;
                        }, {} as Record<string, typeof availableFields>);
                        
                        return Object.entries(grouped).map(([category, fields]) => (
                          <div key={category}>
                            <div className="px-2 py-1.5 text-xs font-semibold text-gray-500 bg-gray-50 sticky top-0">
                              {CATEGORY_CONFIG[category]?.label || category}
                            </div>
                            {fields.map((field) => (
                              <SelectItem key={field.name} value={field.name}>
                                <div className="flex items-center gap-2">
                                  <span>{field.label}</span>
                                  {field.is_subfield && (
                                    <Badge variant="outline" className="text-[10px] h-4 px-1 bg-purple-50 text-purple-600">
                                      Sub
                                    </Badge>
                                  )}
                                </div>
                              </SelectItem>
                            ))}
                          </div>
                        ));
                      })()
                    )}
                  </SelectContent>
                </Select>
              </div>
            )}

            {/* Mapping */}
            {(selectedRefTable || selectedApiConfig) && (
              <div className="grid grid-cols-2 gap-4 pt-2 border-t">
                <div>
                  <Label>Wert-Feld (wird gespeichert)</Label>
                  <Select value={valueField} onValueChange={setValueField}>
                    <SelectTrigger className="mt-1.5">
                      <SelectValue />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="code">code</SelectItem>
                      <SelectItem value="id">id</SelectItem>
                      <SelectItem value="external_id">external_id</SelectItem>
                      {availableColumns.map((col: any) => (
                        <SelectItem key={col.target_field} value={col.target_field}>
                          {col.target_field}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>
                <div>
                  <Label>Anzeige-Feld (wird angezeigt)</Label>
                  <Select value={displayField} onValueChange={setDisplayField}>
                    <SelectTrigger className="mt-1.5">
                      <SelectValue />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="bezeichnung">bezeichnung</SelectItem>
                      <SelectItem value="name">name</SelectItem>
                      <SelectItem value="description">description</SelectItem>
                      {availableColumns.map((col: any) => (
                        <SelectItem key={col.target_field} value={col.target_field}>
                          {col.target_field}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>
              </div>
            )}

            {/* Pflichtfeld */}
            <div className="flex items-center justify-between pt-2 border-t">
              <div>
                <Label>Pflichtfeld</Label>
                <p className="text-xs text-muted-foreground">
                  Nur Werte aus der Datenquelle erlaubt
                </p>
              </div>
              <Switch checked={isRequired} onCheckedChange={setIsRequired} />
            </div>
          </div>

          <DialogFooter>
            <Button variant="outline" onClick={() => setShowCreateDialog(false)}>
              Abbrechen
            </Button>
            <Button 
              onClick={handleCreate} 
              disabled={creating || !selectedModule || !selectedField || (sourceType === 'reference_table' ? !selectedRefTable : !selectedApiConfig)}
              className="gap-2"
            >
              {creating ? (
                <Loader2 className="h-4 w-4 animate-spin" />
              ) : (
                <Check className="h-4 w-4" />
              )}
              Verknüpfung erstellen
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
