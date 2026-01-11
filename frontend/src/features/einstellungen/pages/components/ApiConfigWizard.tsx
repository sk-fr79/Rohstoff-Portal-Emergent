import { useState, useEffect } from 'react';
import { useAuthStore } from '@/store/authStore';
import { toast } from 'sonner';
import { 
  Globe, ArrowRight, ArrowLeft, Check, Loader2, Key, Link2,
  FileJson, Database, Play, AlertTriangle, CheckCircle,
  Copy, Eye, EyeOff, Plus, Trash2, X
} from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';
import { Switch } from '@/components/ui/switch';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Dialog, DialogContent, DialogHeader, DialogTitle } from '@/components/ui/dialog';
import { ScrollArea } from '@/components/ui/scroll-area';
import { Badge } from '@/components/ui/badge';
import { cn } from '@/lib/utils';

const API_URL = import.meta.env.VITE_API_URL || '/api';

interface FieldMapping {
  source_path: string;
  target_field: string;
  is_primary_key: boolean;
  data_type: 'string' | 'number' | 'boolean' | 'date';
}

interface ApiConfig {
  id?: string;
  name: string;
  description?: string;
  api_type: 'REST' | 'GraphQL' | 'SOAP';
  base_url: string;
  auth_type: 'none' | 'api_key' | 'bearer' | 'basic' | 'oauth2';
  auth_config?: {
    api_key?: string;
    api_key_name?: string;
    api_key_location?: 'header' | 'query';
    bearer_token?: string;
    basic_username?: string;
    basic_password?: string;
  };
  request_config?: {
    method: 'GET' | 'POST' | 'PUT' | 'DELETE';
    headers: Record<string, string>;
    query_params: Record<string, string>;
    body_template?: string;
  };
  response_mapping?: {
    data_path: string;
    field_mappings: FieldMapping[];
  };
  reference_table?: {
    enabled: boolean;
    table_name: string;
    display_name: string;
    update_strategy: 'replace' | 'append' | 'merge';
    track_history: boolean;
  };
  is_active: boolean;
}

const STEPS = [
  { id: 'basics', label: 'Grundlagen', icon: Globe },
  { id: 'auth', label: 'Authentifizierung', icon: Key },
  { id: 'request', label: 'Request', icon: Link2 },
  { id: 'mapping', label: 'Response-Mapping', icon: FileJson },
  { id: 'reference', label: 'Referenztabelle', icon: Database },
  { id: 'test', label: 'Testen', icon: Play },
];

const DEFAULT_CONFIG: ApiConfig = {
  name: '',
  description: '',
  api_type: 'REST',
  base_url: '',
  auth_type: 'none',
  auth_config: {},
  request_config: {
    method: 'GET',
    headers: {},
    query_params: {},
  },
  response_mapping: {
    data_path: '',
    field_mappings: [],
  },
  reference_table: {
    enabled: false,
    table_name: '',
    display_name: '',
    update_strategy: 'merge',
    track_history: false,
  },
  is_active: true,
};

// Zolltarifnummern.de Preset (V2 API mit semantischer Suche)
const ZOLLTARIF_PRESET: Partial<ApiConfig> = {
  name: 'Zolltarifnummern.de',
  description: 'Deutsche Zolltarifnummern-Datenbank V2 - Semantische Suche mit AI Embeddings',
  api_type: 'REST',
  base_url: 'https://www.zolltarifnummern.de/api/v2/cnSuggest',
  auth_type: 'none', // Keine Auth nötig, nur Rate-Limit (5 req/min)
  auth_config: {},
  request_config: {
    method: 'GET',
    headers: {
      'Accept': 'application/json',
    },
    query_params: { 
      term: 'Fleisch', // Standard-Suchbegriff für Test
      year: '2026',
      lang: 'de',
    },
  },
  response_mapping: {
    data_path: 'suggestions',
    field_mappings: [
      { source_path: 'code', target_field: 'code', is_primary_key: true, data_type: 'string' },
      { source_path: 'value', target_field: 'bezeichnung', is_primary_key: false, data_type: 'string' },
      { source_path: 'score', target_field: 'relevanz', is_primary_key: false, data_type: 'number' },
      { source_path: 'data', target_field: 'detail_url', is_primary_key: false, data_type: 'string' },
    ],
  },
  reference_table: {
    enabled: true,
    table_name: 'ref_zolltarifnummern',
    display_name: 'Zolltarifnummern',
    update_strategy: 'merge',
    track_history: false,
  },
};

interface Props {
  open: boolean;
  onClose: () => void;
  onSuccess: () => void;
  editingApi?: ApiConfig | null;
}

export function ApiConfigWizard({ open, onClose, onSuccess, editingApi }: Props) {
  const { accessToken: token } = useAuthStore();
  const [currentStep, setCurrentStep] = useState(0);
  const [config, setConfig] = useState<ApiConfig>(DEFAULT_CONFIG);
  const [saving, setSaving] = useState(false);
  const [testing, setTesting] = useState(false);
  const [testResult, setTestResult] = useState<any>(null);
  const [showApiKey, setShowApiKey] = useState(false);
  
  // Headers/Query Params Editing
  const [newHeaderKey, setNewHeaderKey] = useState('');
  const [newHeaderValue, setNewHeaderValue] = useState('');
  const [newParamKey, setNewParamKey] = useState('');
  const [newParamValue, setNewParamValue] = useState('');

  useEffect(() => {
    if (open) {
      if (editingApi) {
        setConfig({ ...DEFAULT_CONFIG, ...editingApi });
      } else {
        setConfig(DEFAULT_CONFIG);
      }
      setCurrentStep(0);
      setTestResult(null);
    }
  }, [open, editingApi]);

  // Auto-generate table name from API name
  useEffect(() => {
    if (!editingApi && config.name && config.reference_table) {
      const tableName = 'ref_' + config.name.toLowerCase()
        .replace(/[^a-z0-9]+/g, '_')
        .replace(/^_+|_+$/g, '');
      setConfig(prev => ({
        ...prev,
        reference_table: {
          ...prev.reference_table!,
          table_name: tableName,
          display_name: prev.reference_table?.display_name || config.name,
        }
      }));
    }
  }, [config.name, editingApi]);

  const updateConfig = (updates: Partial<ApiConfig>) => {
    setConfig(prev => ({ ...prev, ...updates }));
  };

  const loadPreset = () => {
    setConfig({ ...DEFAULT_CONFIG, ...ZOLLTARIF_PRESET } as ApiConfig);
    toast.info('Zolltarifnummern.de Preset geladen');
  };

  const handleTest = async () => {
    setTesting(true);
    setTestResult(null);
    
    try {
      // Erst API speichern/updaten falls noch nicht geschehen
      let apiId = config.id;
      
      if (!apiId) {
        // Temporär speichern zum Testen
        const saveRes = await fetch(`${API_URL}/system/apis`, {
          method: 'POST',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
          body: JSON.stringify(config),
        });
        const saveData = await saveRes.json();
        if (saveData.success) {
          apiId = saveData.data.id;
          setConfig(prev => ({ ...prev, id: apiId }));
        } else {
          toast.error(saveData.detail || 'Fehler beim Speichern');
          setTesting(false);
          return;
        }
      }
      
      // Test ausführen
      const res = await fetch(`${API_URL}/system/apis/${apiId}/test`, {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ test_params: config.request_config?.query_params || {} }),
      });
      const data = await res.json();
      setTestResult(data);
      
      if (data.success) {
        toast.success('API-Test erfolgreich');
      } else {
        toast.error(data.error || 'Test fehlgeschlagen');
      }
    } catch (error) {
      toast.error('Fehler beim Testen');
      setTestResult({ success: false, error: 'Verbindungsfehler' });
    } finally {
      setTesting(false);
    }
  };

  const handleSave = async () => {
    setSaving(true);
    
    try {
      const url = config.id 
        ? `${API_URL}/system/apis/${config.id}`
        : `${API_URL}/system/apis`;
      
      const res = await fetch(url, {
        method: config.id ? 'PUT' : 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(config),
      });
      
      const data = await res.json();
      
      if (data.success) {
        toast.success(config.id ? 'API aktualisiert' : 'API erstellt');
        onSuccess();
      } else {
        toast.error(data.detail || 'Fehler beim Speichern');
      }
    } catch (error) {
      toast.error('Fehler beim Speichern');
    } finally {
      setSaving(false);
    }
  };

  const addHeader = () => {
    if (newHeaderKey && newHeaderValue) {
      setConfig(prev => ({
        ...prev,
        request_config: {
          ...prev.request_config!,
          headers: {
            ...prev.request_config?.headers,
            [newHeaderKey]: newHeaderValue,
          }
        }
      }));
      setNewHeaderKey('');
      setNewHeaderValue('');
    }
  };

  const removeHeader = (key: string) => {
    setConfig(prev => {
      const { [key]: _, ...rest } = prev.request_config?.headers || {};
      return {
        ...prev,
        request_config: { ...prev.request_config!, headers: rest }
      };
    });
  };

  const addQueryParam = () => {
    if (newParamKey) {
      setConfig(prev => ({
        ...prev,
        request_config: {
          ...prev.request_config!,
          query_params: {
            ...prev.request_config?.query_params,
            [newParamKey]: newParamValue,
          }
        }
      }));
      setNewParamKey('');
      setNewParamValue('');
    }
  };

  const removeQueryParam = (key: string) => {
    setConfig(prev => {
      const { [key]: _, ...rest } = prev.request_config?.query_params || {};
      return {
        ...prev,
        request_config: { ...prev.request_config!, query_params: rest }
      };
    });
  };

  const addFieldMapping = () => {
    setConfig(prev => ({
      ...prev,
      response_mapping: {
        ...prev.response_mapping!,
        field_mappings: [
          ...prev.response_mapping?.field_mappings || [],
          { source_path: '', target_field: '', is_primary_key: false, data_type: 'string' }
        ]
      }
    }));
  };

  const updateFieldMapping = (index: number, updates: Partial<FieldMapping>) => {
    setConfig(prev => ({
      ...prev,
      response_mapping: {
        ...prev.response_mapping!,
        field_mappings: prev.response_mapping?.field_mappings.map((fm, i) => 
          i === index ? { ...fm, ...updates } : fm
        ) || []
      }
    }));
  };

  const removeFieldMapping = (index: number) => {
    setConfig(prev => ({
      ...prev,
      response_mapping: {
        ...prev.response_mapping!,
        field_mappings: prev.response_mapping?.field_mappings.filter((_, i) => i !== index) || []
      }
    }));
  };

  const canProceed = () => {
    switch (currentStep) {
      case 0: return config.name && config.base_url;
      case 1: return true;
      case 2: return true;
      case 3: return true;
      case 4: return true;
      case 5: return true;
      default: return true;
    }
  };

  const renderStep = () => {
    switch (currentStep) {
      case 0: // Grundlagen
        return (
          <div className="space-y-6">
            {!editingApi && (
              <div className="p-4 bg-amber-50 border border-amber-200 rounded-lg">
                <div className="flex items-center justify-between">
                  <div>
                    <p className="text-sm font-medium text-amber-800">Vorlage verfügbar</p>
                    <p className="text-xs text-amber-600">Zolltarifnummern.de API schnell einrichten</p>
                  </div>
                  <Button size="sm" variant="outline" onClick={loadPreset}>
                    Vorlage laden
                  </Button>
                </div>
              </div>
            )}
            
            <div className="grid gap-4">
              <div>
                <Label htmlFor="name">Name *</Label>
                <Input
                  id="name"
                  placeholder="z.B. Zolltarif-API"
                  value={config.name}
                  onChange={(e) => updateConfig({ name: e.target.value })}
                />
              </div>
              
              <div>
                <Label htmlFor="description">Beschreibung</Label>
                <Textarea
                  id="description"
                  placeholder="Kurze Beschreibung der API..."
                  value={config.description || ''}
                  onChange={(e) => updateConfig({ description: e.target.value })}
                  rows={2}
                />
              </div>
              
              <div className="grid grid-cols-2 gap-4">
                <div>
                  <Label>API-Typ</Label>
                  <Select 
                    value={config.api_type} 
                    onValueChange={(v) => updateConfig({ api_type: v as any })}
                  >
                    <SelectTrigger>
                      <SelectValue />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="REST">REST</SelectItem>
                      <SelectItem value="GraphQL">GraphQL</SelectItem>
                      <SelectItem value="SOAP">SOAP</SelectItem>
                    </SelectContent>
                  </Select>
                </div>
                
                <div className="flex items-end gap-2">
                  <div className="flex items-center gap-2">
                    <Switch
                      checked={config.is_active}
                      onCheckedChange={(v) => updateConfig({ is_active: v })}
                    />
                    <Label>Aktiv</Label>
                  </div>
                </div>
              </div>
              
              <div>
                <Label htmlFor="base_url">Basis-URL *</Label>
                <Input
                  id="base_url"
                  placeholder="https://api.example.com/v1"
                  value={config.base_url}
                  onChange={(e) => updateConfig({ base_url: e.target.value })}
                />
                <p className="text-xs text-muted-foreground mt-1">
                  Vollständige URL zum API-Endpunkt
                </p>
              </div>
            </div>
          </div>
        );

      case 1: // Authentifizierung
        return (
          <div className="space-y-6">
            <div>
              <Label>Authentifizierungstyp</Label>
              <Select 
                value={config.auth_type} 
                onValueChange={(v) => updateConfig({ auth_type: v as any })}
              >
                <SelectTrigger>
                  <SelectValue />
                </SelectTrigger>
                <SelectContent>
                  <SelectItem value="none">Keine Authentifizierung</SelectItem>
                  <SelectItem value="api_key">API-Key</SelectItem>
                  <SelectItem value="bearer">Bearer Token</SelectItem>
                  <SelectItem value="basic">Basic Auth</SelectItem>
                  <SelectItem value="oauth2">OAuth2 (Client Credentials)</SelectItem>
                </SelectContent>
              </Select>
            </div>

            {config.auth_type === 'api_key' && (
              <div className="space-y-4 p-4 bg-gray-50 rounded-lg">
                <div>
                  <Label>API-Key</Label>
                  <div className="flex gap-2">
                    <Input
                      type={showApiKey ? 'text' : 'password'}
                      placeholder="Ihr API-Key"
                      value={config.auth_config?.api_key || ''}
                      onChange={(e) => updateConfig({
                        auth_config: { ...config.auth_config, api_key: e.target.value }
                      })}
                    />
                    <Button 
                      variant="outline" 
                      size="icon"
                      onClick={() => setShowApiKey(!showApiKey)}
                    >
                      {showApiKey ? <EyeOff className="h-4 w-4" /> : <Eye className="h-4 w-4" />}
                    </Button>
                  </div>
                </div>
                <div className="grid grid-cols-2 gap-4">
                  <div>
                    <Label>Header/Parameter Name</Label>
                    <Input
                      placeholder="z.B. X-API-Key"
                      value={config.auth_config?.api_key_name || ''}
                      onChange={(e) => updateConfig({
                        auth_config: { ...config.auth_config, api_key_name: e.target.value }
                      })}
                    />
                  </div>
                  <div>
                    <Label>Übertragung</Label>
                    <Select 
                      value={config.auth_config?.api_key_location || 'header'} 
                      onValueChange={(v) => updateConfig({
                        auth_config: { ...config.auth_config, api_key_location: v as any }
                      })}
                    >
                      <SelectTrigger>
                        <SelectValue />
                      </SelectTrigger>
                      <SelectContent>
                        <SelectItem value="header">HTTP Header</SelectItem>
                        <SelectItem value="query">Query Parameter</SelectItem>
                      </SelectContent>
                    </Select>
                  </div>
                </div>
              </div>
            )}

            {config.auth_type === 'bearer' && (
              <div className="p-4 bg-gray-50 rounded-lg">
                <Label>Bearer Token</Label>
                <div className="flex gap-2">
                  <Input
                    type={showApiKey ? 'text' : 'password'}
                    placeholder="Token"
                    value={config.auth_config?.bearer_token || ''}
                    onChange={(e) => updateConfig({
                      auth_config: { ...config.auth_config, bearer_token: e.target.value }
                    })}
                  />
                  <Button 
                    variant="outline" 
                    size="icon"
                    onClick={() => setShowApiKey(!showApiKey)}
                  >
                    {showApiKey ? <EyeOff className="h-4 w-4" /> : <Eye className="h-4 w-4" />}
                  </Button>
                </div>
              </div>
            )}

            {config.auth_type === 'basic' && (
              <div className="space-y-4 p-4 bg-gray-50 rounded-lg">
                <div>
                  <Label>Benutzername</Label>
                  <Input
                    placeholder="Username"
                    value={config.auth_config?.basic_username || ''}
                    onChange={(e) => updateConfig({
                      auth_config: { ...config.auth_config, basic_username: e.target.value }
                    })}
                  />
                </div>
                <div>
                  <Label>Passwort</Label>
                  <Input
                    type="password"
                    placeholder="Password"
                    value={config.auth_config?.basic_password || ''}
                    onChange={(e) => updateConfig({
                      auth_config: { ...config.auth_config, basic_password: e.target.value }
                    })}
                  />
                </div>
              </div>
            )}

            <div className="p-3 bg-blue-50 border border-blue-200 rounded-lg">
              <p className="text-sm text-blue-700">
                🔒 Credentials werden AES-256 verschlüsselt gespeichert
              </p>
            </div>
          </div>
        );

      case 2: // Request
        return (
          <div className="space-y-6">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label>HTTP-Methode</Label>
                <Select 
                  value={config.request_config?.method || 'GET'} 
                  onValueChange={(v) => updateConfig({
                    request_config: { ...config.request_config!, method: v as any }
                  })}
                >
                  <SelectTrigger>
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="GET">GET</SelectItem>
                    <SelectItem value="POST">POST</SelectItem>
                    <SelectItem value="PUT">PUT</SelectItem>
                    <SelectItem value="DELETE">DELETE</SelectItem>
                  </SelectContent>
                </Select>
              </div>
            </div>

            {/* Headers */}
            <div>
              <Label className="mb-2 block">Custom Headers</Label>
              <div className="space-y-2">
                {Object.entries(config.request_config?.headers || {}).map(([key, value]) => (
                  <div key={key} className="flex items-center gap-2 p-2 bg-gray-50 rounded">
                    <code className="text-sm flex-1">{key}: {value}</code>
                    <Button variant="ghost" size="icon" onClick={() => removeHeader(key)}>
                      <X className="h-4 w-4" />
                    </Button>
                  </div>
                ))}
                <div className="flex gap-2">
                  <Input
                    placeholder="Header Name"
                    value={newHeaderKey}
                    onChange={(e) => setNewHeaderKey(e.target.value)}
                    className="flex-1"
                  />
                  <Input
                    placeholder="Wert"
                    value={newHeaderValue}
                    onChange={(e) => setNewHeaderValue(e.target.value)}
                    className="flex-1"
                  />
                  <Button variant="outline" onClick={addHeader}>
                    <Plus className="h-4 w-4" />
                  </Button>
                </div>
              </div>
            </div>

            {/* Query Parameters */}
            <div>
              <Label className="mb-2 block">Query Parameter</Label>
              <div className="space-y-2">
                {Object.entries(config.request_config?.query_params || {}).map(([key, value]) => (
                  <div key={key} className="flex items-center gap-2 p-2 bg-gray-50 rounded">
                    <code className="text-sm flex-1">{key}={value || '(leer)'}</code>
                    <Button variant="ghost" size="icon" onClick={() => removeQueryParam(key)}>
                      <X className="h-4 w-4" />
                    </Button>
                  </div>
                ))}
                <div className="flex gap-2">
                  <Input
                    placeholder="Parameter"
                    value={newParamKey}
                    onChange={(e) => setNewParamKey(e.target.value)}
                    className="flex-1"
                  />
                  <Input
                    placeholder="Standard-Wert (optional)"
                    value={newParamValue}
                    onChange={(e) => setNewParamValue(e.target.value)}
                    className="flex-1"
                  />
                  <Button variant="outline" onClick={addQueryParam}>
                    <Plus className="h-4 w-4" />
                  </Button>
                </div>
              </div>
            </div>

            {config.request_config?.method !== 'GET' && (
              <div>
                <Label>Request Body (JSON Template)</Label>
                <Textarea
                  placeholder='{"query": "${search_term}"}'
                  value={config.request_config?.body_template || ''}
                  onChange={(e) => updateConfig({
                    request_config: { ...config.request_config!, body_template: e.target.value }
                  })}
                  rows={4}
                  className="font-mono text-sm"
                />
              </div>
            )}
          </div>
        );

      case 3: // Response Mapping
        return (
          <div className="space-y-6">
            <div>
              <Label>Datenpfad im Response</Label>
              <Input
                placeholder="z.B. data.items oder results"
                value={config.response_mapping?.data_path || ''}
                onChange={(e) => updateConfig({
                  response_mapping: { ...config.response_mapping!, data_path: e.target.value }
                })}
              />
              <p className="text-xs text-muted-foreground mt-1">
                JSON-Pfad zum Array mit den Daten (z.B. "suggestions" für Zolltarif-API)
              </p>
            </div>

            <div>
              <div className="flex items-center justify-between mb-2">
                <Label>Feld-Mapping</Label>
                <Button variant="outline" size="sm" onClick={addFieldMapping}>
                  <Plus className="h-4 w-4 mr-1" />
                  Feld hinzufügen
                </Button>
              </div>
              
              <div className="space-y-3">
                {config.response_mapping?.field_mappings?.map((fm, index) => (
                  <div key={index} className="flex items-center gap-2 p-3 bg-gray-50 rounded-lg">
                    <Input
                      placeholder="API-Feld"
                      value={fm.source_path}
                      onChange={(e) => updateFieldMapping(index, { source_path: e.target.value })}
                      className="flex-1"
                    />
                    <ArrowRight className="h-4 w-4 text-muted-foreground" />
                    <Input
                      placeholder="Internes Feld"
                      value={fm.target_field}
                      onChange={(e) => updateFieldMapping(index, { target_field: e.target.value })}
                      className="flex-1"
                    />
                    <Select 
                      value={fm.data_type} 
                      onValueChange={(v) => updateFieldMapping(index, { data_type: v as any })}
                    >
                      <SelectTrigger className="w-28">
                        <SelectValue />
                      </SelectTrigger>
                      <SelectContent>
                        <SelectItem value="string">Text</SelectItem>
                        <SelectItem value="number">Zahl</SelectItem>
                        <SelectItem value="boolean">Boolean</SelectItem>
                        <SelectItem value="date">Datum</SelectItem>
                      </SelectContent>
                    </Select>
                    <div className="flex items-center gap-1">
                      <Switch
                        checked={fm.is_primary_key}
                        onCheckedChange={(v) => updateFieldMapping(index, { is_primary_key: v })}
                      />
                      <span className="text-xs text-muted-foreground">PK</span>
                    </div>
                    <Button variant="ghost" size="icon" onClick={() => removeFieldMapping(index)}>
                      <Trash2 className="h-4 w-4" />
                    </Button>
                  </div>
                ))}
              </div>
              
              {(config.response_mapping?.field_mappings?.length || 0) === 0 && (
                <div className="text-center py-8 text-muted-foreground bg-gray-50 rounded-lg">
                  <FileJson className="h-8 w-8 mx-auto mb-2 opacity-50" />
                  <p className="text-sm">Noch keine Felder definiert</p>
                  <p className="text-xs">Fügen Sie Felder hinzu, die aus dem Response extrahiert werden sollen</p>
                </div>
              )}
            </div>
          </div>
        );

      case 4: // Referenztabelle
        return (
          <div className="space-y-6">
            <div className="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
              <div>
                <p className="font-medium">Als Referenztabelle speichern</p>
                <p className="text-sm text-muted-foreground">
                  Daten lokal speichern für schnellen Zugriff in anderen Modulen
                </p>
              </div>
              <Switch
                checked={config.reference_table?.enabled || false}
                onCheckedChange={(v) => updateConfig({
                  reference_table: { ...config.reference_table!, enabled: v }
                })}
              />
            </div>

            {config.reference_table?.enabled && (
              <div className="space-y-4">
                <div className="grid grid-cols-2 gap-4">
                  <div>
                    <Label>Technischer Name</Label>
                    <Input
                      placeholder="ref_zolltarifnummern"
                      value={config.reference_table.table_name}
                      onChange={(e) => updateConfig({
                        reference_table: { ...config.reference_table!, table_name: e.target.value }
                      })}
                    />
                    <p className="text-xs text-muted-foreground mt-1">
                      Ohne Leerzeichen, Kleinbuchstaben
                    </p>
                  </div>
                  <div>
                    <Label>Anzeigename</Label>
                    <Input
                      placeholder="Zolltarifnummern"
                      value={config.reference_table.display_name}
                      onChange={(e) => updateConfig({
                        reference_table: { ...config.reference_table!, display_name: e.target.value }
                      })}
                    />
                  </div>
                </div>

                <div>
                  <Label>Aktualisierungsstrategie</Label>
                  <Select 
                    value={config.reference_table.update_strategy} 
                    onValueChange={(v) => updateConfig({
                      reference_table: { ...config.reference_table!, update_strategy: v as any }
                    })}
                  >
                    <SelectTrigger>
                      <SelectValue />
                    </SelectTrigger>
                    <SelectContent>
                      <SelectItem value="replace">Vollständig ersetzen</SelectItem>
                      <SelectItem value="append">Nur neue hinzufügen</SelectItem>
                      <SelectItem value="merge">Merge (empfohlen)</SelectItem>
                    </SelectContent>
                  </Select>
                  <p className="text-xs text-muted-foreground mt-1">
                    Merge: Bestehende aktualisieren, neue hinzufügen
                  </p>
                </div>

                <div className="flex items-center gap-2">
                  <Switch
                    checked={config.reference_table.track_history}
                    onCheckedChange={(v) => updateConfig({
                      reference_table: { ...config.reference_table!, track_history: v }
                    })}
                  />
                  <Label>Änderungshistorie führen</Label>
                </div>
              </div>
            )}
          </div>
        );

      case 5: // Testen
        return (
          <div className="space-y-6">
            <div className="p-4 bg-gray-50 rounded-lg">
              <h3 className="font-medium mb-2">Zusammenfassung</h3>
              <dl className="grid grid-cols-2 gap-2 text-sm">
                <dt className="text-muted-foreground">Name:</dt>
                <dd>{config.name}</dd>
                <dt className="text-muted-foreground">URL:</dt>
                <dd className="truncate">{config.base_url}</dd>
                <dt className="text-muted-foreground">Auth:</dt>
                <dd className="capitalize">{config.auth_type.replace('_', ' ')}</dd>
                <dt className="text-muted-foreground">Referenztabelle:</dt>
                <dd>{config.reference_table?.enabled ? config.reference_table.table_name : 'Deaktiviert'}</dd>
              </dl>
            </div>

            <Button 
              onClick={handleTest} 
              disabled={testing}
              className="w-full"
              variant="outline"
            >
              {testing ? (
                <>
                  <Loader2 className="h-4 w-4 mr-2 animate-spin" />
                  Teste Verbindung...
                </>
              ) : (
                <>
                  <Play className="h-4 w-4 mr-2" />
                  API testen
                </>
              )}
            </Button>

            {testResult && (
              <div className={cn(
                "p-4 rounded-lg",
                testResult.success ? "bg-emerald-50 border border-emerald-200" : "bg-red-50 border border-red-200"
              )}>
                <div className="flex items-center gap-2 mb-2">
                  {testResult.success ? (
                    <CheckCircle className="h-5 w-5 text-emerald-600" />
                  ) : (
                    <AlertTriangle className="h-5 w-5 text-red-600" />
                  )}
                  <span className={cn(
                    "font-medium",
                    testResult.success ? "text-emerald-700" : "text-red-700"
                  )}>
                    {testResult.success ? 'Verbindung erfolgreich' : 'Fehler'}
                  </span>
                </div>

                {testResult.success && testResult.data && (
                  <div className="space-y-2 text-sm">
                    <p>Status: {testResult.data.status_code}</p>
                    {testResult.data.mapped_preview && (
                      <div>
                        <p className="font-medium">Vorschau ({testResult.data.mapped_preview.total_records} Datensätze):</p>
                        <pre className="mt-1 p-2 bg-white rounded text-xs overflow-auto max-h-40">
                          {JSON.stringify(testResult.data.mapped_preview.sample_mapped, null, 2)}
                        </pre>
                      </div>
                    )}
                  </div>
                )}

                {!testResult.success && testResult.error && (
                  <p className="text-sm text-red-600">{testResult.error}</p>
                )}
              </div>
            )}
          </div>
        );

      default:
        return null;
    }
  };

  return (
    <Dialog open={open} onOpenChange={(v) => !v && onClose()}>
      <DialogContent className="max-w-3xl max-h-[90vh] flex flex-col">
        <DialogHeader>
          <DialogTitle>
            {editingApi ? 'API bearbeiten' : 'Neue API konfigurieren'}
          </DialogTitle>
        </DialogHeader>

        {/* Step Indicators */}
        <div className="flex items-center justify-between py-4 border-b">
          {STEPS.map((step, index) => {
            const Icon = step.icon;
            const isActive = index === currentStep;
            const isComplete = index < currentStep;
            
            return (
              <button
                key={step.id}
                onClick={() => setCurrentStep(index)}
                className={cn(
                  "flex flex-col items-center gap-1 transition-colors",
                  isActive && "text-emerald-600",
                  isComplete && "text-emerald-500",
                  !isActive && !isComplete && "text-muted-foreground"
                )}
              >
                <div className={cn(
                  "h-8 w-8 rounded-full flex items-center justify-center border-2",
                  isActive && "border-emerald-600 bg-emerald-50",
                  isComplete && "border-emerald-500 bg-emerald-500",
                  !isActive && !isComplete && "border-gray-200"
                )}>
                  {isComplete ? (
                    <Check className="h-4 w-4 text-white" />
                  ) : (
                    <Icon className="h-4 w-4" />
                  )}
                </div>
                <span className="text-xs hidden sm:block">{step.label}</span>
              </button>
            );
          })}
        </div>

        {/* Step Content */}
        <ScrollArea className="flex-1 pr-4">
          <div className="py-4">
            {renderStep()}
          </div>
        </ScrollArea>

        {/* Footer */}
        <div className="flex items-center justify-between pt-4 border-t">
          <Button
            variant="outline"
            onClick={() => currentStep > 0 ? setCurrentStep(currentStep - 1) : onClose()}
          >
            <ArrowLeft className="h-4 w-4 mr-2" />
            {currentStep === 0 ? 'Abbrechen' : 'Zurück'}
          </Button>

          <div className="flex gap-2">
            {currentStep === STEPS.length - 1 ? (
              <Button onClick={handleSave} disabled={saving} className="bg-emerald-600 hover:bg-emerald-700">
                {saving ? (
                  <Loader2 className="h-4 w-4 mr-2 animate-spin" />
                ) : (
                  <Check className="h-4 w-4 mr-2" />
                )}
                Speichern
              </Button>
            ) : (
              <Button 
                onClick={() => setCurrentStep(currentStep + 1)}
                disabled={!canProceed()}
              >
                Weiter
                <ArrowRight className="h-4 w-4 ml-2" />
              </Button>
            )}
          </div>
        </div>
      </DialogContent>
    </Dialog>
  );
}
