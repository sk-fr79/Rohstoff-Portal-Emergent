import { useState, useEffect } from 'react';
import { useAuthStore } from '@/store/authStore';
import { toast } from 'sonner';
import { 
  Link2, Plus, Trash2, Loader2, Database, ArrowRight,
  Package, MapPin, FileText, Truck, Settings2, Check,
  ChevronDown
} from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardHeader, CardTitle, CardDescription } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { Label } from '@/components/ui/label';
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
import { cn } from '@/lib/utils';

const API_URL = import.meta.env.VITE_API_URL || '/api';

interface FieldBinding {
  id: string;
  reference_table_id: string;
  reference_table_name: string;
  reference_table_display_name: string;
  module: string;
  module_label: string;
  field_name: string;
  field_label: string;
  display_field: string;
  value_field: string;
  additional_display_fields: string[];
  is_required: boolean;
  allow_search: boolean;
  created_at: string;
}

interface ReferenceTable {
  id: string;
  table_name: string;
  display_name: string;
  record_count: number;
  columns_config?: Array<{ target_field: string; source_path: string }>;
}

interface ModuleInfo {
  label: string;
  fields: Array<{ name: string; label: string }>;
}

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
  const [isLoading, setIsLoading] = useState(true);
  
  // Dialog State
  const [showCreateDialog, setShowCreateDialog] = useState(false);
  const [creating, setCreating] = useState(false);
  const [deleting, setDeleting] = useState<string | null>(null);
  
  // Form State
  const [selectedRefTable, setSelectedRefTable] = useState<string>(referenceTableId || '');
  const [selectedModule, setSelectedModule] = useState<string>('');
  const [selectedField, setSelectedField] = useState<string>('');
  const [displayField, setDisplayField] = useState<string>('bezeichnung');
  const [valueField, setValueField] = useState<string>('code');
  const [isRequired, setIsRequired] = useState(false);

  // Daten laden
  const fetchData = async () => {
    setIsLoading(true);
    try {
      const headers = { 'Authorization': `Bearer ${token}` };
      
      // Parallel laden
      const [bindingsRes, modulesRes, tablesRes] = await Promise.all([
        fetch(`${API_URL}/system/field-bindings${referenceTableId ? `?reference_table_id=${referenceTableId}` : ''}`, { headers }),
        fetch(`${API_URL}/system/modules`, { headers }),
        fetch(`${API_URL}/system/reference-tables`, { headers }),
      ]);
      
      const [bindingsData, modulesData, tablesData] = await Promise.all([
        bindingsRes.json(),
        modulesRes.json(),
        tablesRes.json(),
      ]);
      
      if (bindingsData.success) setBindings(bindingsData.data);
      if (modulesData.success) setModules(modulesData.data);
      if (tablesData.success) setReferenceTables(tablesData.data);
      
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
    if (!selectedRefTable || !selectedModule || !selectedField) {
      toast.error('Bitte alle Felder ausfüllen');
      return;
    }

    setCreating(true);
    try {
      const res = await fetch(`${API_URL}/system/field-bindings`, {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          reference_table_id: selectedRefTable,
          module: selectedModule,
          field_name: selectedField,
          display_field: displayField,
          value_field: valueField,
          additional_display_fields: [],
          is_required: isRequired,
          allow_search: true,
        })
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
    if (!referenceTableId) setSelectedRefTable('');
    setSelectedModule('');
    setSelectedField('');
    setDisplayField('bezeichnung');
    setValueField('code');
    setIsRequired(false);
  };

  // Verfügbare Felder für ausgewähltes Modul
  const availableFields = selectedModule 
    ? modules[selectedModule]?.fields.filter(f => 
        !bindings.some(b => b.module === selectedModule && b.field_name === f.name)
      ) || []
    : [];

  // Spalten der ausgewählten Referenztabelle
  const selectedTableColumns = referenceTables.find(t => t.id === selectedRefTable)?.columns_config || [];

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
            Verknüpfen Sie Referenztabellen mit Modulfeldern für automatische Dropdown-Auswahl
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
              Erstellen Sie eine Verknüpfung, um Referenzdaten in Modulen als Dropdown anzuzeigen
            </p>
          </CardContent>
        </Card>
      ) : (
        <Card>
          <Table>
            <TableHeader>
              <TableRow>
                <TableHead>Modul / Feld</TableHead>
                <TableHead>Referenztabelle</TableHead>
                <TableHead>Anzeige</TableHead>
                <TableHead>Optionen</TableHead>
                <TableHead className="w-[80px]"></TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {bindings.map((binding) => {
                const ModuleIcon = MODULE_ICONS[binding.module] || Settings2;
                
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
                        <Database className="h-4 w-4 text-emerald-600" />
                        <span>{binding.reference_table_display_name}</span>
                      </div>
                      <p className="text-xs text-muted-foreground font-mono">
                        {binding.reference_table_name}
                      </p>
                    </TableCell>
                    <TableCell>
                      <div className="text-sm">
                        <span className="text-muted-foreground">Wert:</span> {binding.value_field}
                        <br />
                        <span className="text-muted-foreground">Anzeige:</span> {binding.display_field}
                      </div>
                    </TableCell>
                    <TableCell>
                      <div className="flex gap-1">
                        {binding.is_required && (
                          <Badge variant="outline" className="text-xs bg-red-50 text-red-700 border-red-200">
                            Pflicht
                          </Badge>
                        )}
                        {binding.allow_search && (
                          <Badge variant="outline" className="text-xs">
                            Durchsuchbar
                          </Badge>
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
              Verknüpfen Sie eine Referenztabelle mit einem Modul-Feld
            </DialogDescription>
          </DialogHeader>

          <div className="space-y-4 py-4">
            {/* Referenztabelle */}
            {!referenceTableId && (
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
            )}

            {referenceTableId && (
              <div className="p-3 bg-emerald-50 rounded-lg flex items-center gap-2">
                <Database className="h-5 w-5 text-emerald-600" />
                <span className="font-medium">{referenceTableName}</span>
              </div>
            )}

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
                  <SelectContent>
                    {availableFields.length === 0 ? (
                      <div className="px-2 py-3 text-sm text-muted-foreground text-center">
                        Alle Felder bereits verknüpft
                      </div>
                    ) : (
                      availableFields.map((field) => (
                        <SelectItem key={field.name} value={field.name}>
                          {field.label}
                        </SelectItem>
                      ))
                    )}
                  </SelectContent>
                </Select>
              </div>
            )}

            {/* Mapping */}
            {selectedRefTable && (
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
                      {selectedTableColumns.map((col) => (
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
                      {selectedTableColumns.map((col) => (
                        <SelectItem key={col.target_field} value={col.target_field}>
                          {col.target_field}
                        </SelectItem>
                      ))}
                    </SelectContent>
                  </Select>
                </div>
              </div>
            )}

            {/* Optionen */}
            <div className="flex items-center justify-between pt-2 border-t">
              <div>
                <Label>Pflichtfeld</Label>
                <p className="text-xs text-muted-foreground">
                  Nur Werte aus der Referenztabelle erlaubt
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
              disabled={creating || !selectedRefTable || !selectedModule || !selectedField}
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
