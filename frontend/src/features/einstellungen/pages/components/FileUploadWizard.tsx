import { useState, useCallback, useRef } from 'react';
import { useAuthStore } from '@/store/authStore';
import { toast } from 'sonner';
import { 
  Upload, FileSpreadsheet, FileText, Check, ArrowRight, ArrowLeft,
  Loader2, CheckCircle, Settings2, Database,
  Sparkles, Key, Hash, Calendar, ToggleLeft, Type,
  Eye, ChevronDown, ChevronUp
} from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Card, CardContent, CardHeader, CardTitle } from '@/components/ui/card';
import { Badge } from '@/components/ui/badge';
import { ScrollArea } from '@/components/ui/scroll-area';
import { 
  Select, SelectContent, SelectItem, SelectTrigger 
} from '@/components/ui/select';
import { Switch } from '@/components/ui/switch';
import {
  Dialog, DialogContent, DialogHeader, DialogTitle,
} from '@/components/ui/dialog';
import {
  Table, TableBody, TableCell, TableHead, TableHeader, TableRow
} from '@/components/ui/table';
import { Progress } from '@/components/ui/progress';
import { cn } from '@/lib/utils';

const API_URL = import.meta.env.VITE_API_URL || '/api';

interface ColumnInfo {
  name: string;
  detected_type: string;
  sample_values: any[];
  null_count: number;
  unique_count: number;
}

interface ColumnMapping {
  source_column: string;
  target_field: string;
  data_type: string;
  is_primary_key: boolean;
  include: boolean;
}

interface FileAnalysis {
  filename: string;
  file_type: string;
  row_count: number;
  columns: ColumnInfo[];
  preview_rows: Record<string, any>[];
  file_data: string;
}

const DATA_TYPE_ICONS: Record<string, any> = {
  string: Type,
  number: Hash,
  date: Calendar,
  boolean: ToggleLeft,
};

const DATA_TYPE_COLORS: Record<string, string> = {
  string: 'bg-blue-100 text-blue-700 border-blue-200',
  number: 'bg-emerald-100 text-emerald-700 border-emerald-200',
  date: 'bg-purple-100 text-purple-700 border-purple-200',
  boolean: 'bg-amber-100 text-amber-700 border-amber-200',
};

interface FileUploadWizardProps {
  open: boolean;
  onClose: () => void;
  onSuccess: () => void;
}

export function FileUploadWizard({ open, onClose, onSuccess }: FileUploadWizardProps) {
  const { accessToken: token } = useAuthStore();
  const fileInputRef = useRef<HTMLInputElement>(null);
  
  // Step State
  const [currentStep, setCurrentStep] = useState(0);
  
  // Upload State
  const [isDragging, setIsDragging] = useState(false);
  const [isAnalyzing, setIsAnalyzing] = useState(false);
  const [analysisProgress, setAnalysisProgress] = useState(0);
  
  // Analysis Result
  const [analysis, setAnalysis] = useState<FileAnalysis | null>(null);
  
  // Mapping State
  const [mappings, setMappings] = useState<ColumnMapping[]>([]);
  const [tableName, setTableName] = useState('');
  const [displayName, setDisplayName] = useState('');
  const [updateStrategy, setUpdateStrategy] = useState<'replace' | 'append' | 'merge'>('merge');
  
  // Import State
  const [isImporting, setIsImporting] = useState(false);
  const [importResult, setImportResult] = useState<any>(null);
  
  // Preview State
  const [showPreview, setShowPreview] = useState(false);

  const STEPS = [
    { id: 'upload', label: 'Datei hochladen', icon: Upload },
    { id: 'mapping', label: 'Spalten zuordnen', icon: Settings2 },
    { id: 'config', label: 'Konfiguration', icon: Database },
    { id: 'import', label: 'Import', icon: CheckCircle },
  ];

  // Reset state
  const resetWizard = () => {
    setCurrentStep(0);
    setAnalysis(null);
    setMappings([]);
    setTableName('');
    setDisplayName('');
    setUpdateStrategy('merge');
    setImportResult(null);
    setIsAnalyzing(false);
    setIsImporting(false);
  };

  const handleClose = () => {
    resetWizard();
    onClose();
  };

  // File Drop Handler
  const handleDragOver = useCallback((e: React.DragEvent) => {
    e.preventDefault();
    setIsDragging(true);
  }, []);

  const handleDragLeave = useCallback((e: React.DragEvent) => {
    e.preventDefault();
    setIsDragging(false);
  }, []);

  const handleDrop = useCallback((e: React.DragEvent) => {
    e.preventDefault();
    setIsDragging(false);
    
    const files = e.dataTransfer.files;
    if (files.length > 0) {
      handleFileSelect(files[0]);
    }
  }, []);

  const handleFileSelect = async (file: File) => {
    // Validate file type
    const validTypes = ['.csv', '.xlsx', '.xls'];
    const extension = file.name.toLowerCase().slice(file.name.lastIndexOf('.'));
    
    if (!validTypes.includes(extension)) {
      toast.error('Nur CSV, XLS und XLSX Dateien werden unterstützt');
      return;
    }

    if (file.size > 50 * 1024 * 1024) {
      toast.error('Datei ist zu groß (max. 50 MB)');
      return;
    }

    setIsAnalyzing(true);
    setAnalysisProgress(10);

    const formData = new FormData();
    formData.append('file', file);

    try {
      // Simulate progress
      const progressInterval = setInterval(() => {
        setAnalysisProgress(prev => Math.min(prev + 15, 85));
      }, 300);

      const res = await fetch(`${API_URL}/system/reference-upload/analyze`, {
        method: 'POST',
        headers: { 'Authorization': `Bearer ${token}` },
        body: formData
      });

      clearInterval(progressInterval);
      setAnalysisProgress(100);

      const data = await res.json();
      
      if (data.success) {
        setAnalysis(data.data);
        
        // Initialize mappings
        const initialMappings: ColumnMapping[] = data.data.columns.map((col: ColumnInfo) => ({
          source_column: col.name,
          target_field: col.name.toLowerCase().replace(/[^a-z0-9]+/g, '_'),
          data_type: col.detected_type,
          is_primary_key: false,
          include: true,
        }));
        
        // Auto-detect primary key (id, code, nr, nummer)
        const pkCandidates = ['id', 'code', 'nr', 'nummer', 'key', 'schluessel'];
        const pkIndex = initialMappings.findIndex(m => 
          pkCandidates.some(pk => m.source_column.toLowerCase().includes(pk))
        );
        if (pkIndex >= 0) {
          initialMappings[pkIndex].is_primary_key = true;
        }
        
        setMappings(initialMappings);
        
        // Generate default names
        const baseName = file.name.replace(/\.[^.]+$/, '').toLowerCase().replace(/[^a-z0-9]+/g, '_');
        setTableName(`ref_${baseName}`);
        setDisplayName(file.name.replace(/\.[^.]+$/, ''));
        
        toast.success(`Datei analysiert: ${data.data.row_count.toLocaleString('de-DE')} Zeilen, ${data.data.columns.length} Spalten`);
        setCurrentStep(1);
      } else {
        toast.error(data.detail || 'Analyse fehlgeschlagen');
      }
    } catch (error) {
      console.error('Analysis error:', error);
      toast.error('Fehler bei der Dateianalyse');
    } finally {
      setIsAnalyzing(false);
      setAnalysisProgress(0);
    }
  };

  // Update mapping
  const updateMapping = (index: number, updates: Partial<ColumnMapping>) => {
    setMappings(prev => prev.map((m, i) => {
      if (i === index) {
        // If setting as primary key, unset others
        if (updates.is_primary_key) {
          return { ...m, ...updates };
        }
        return { ...m, ...updates };
      }
      // Unset other primary keys if this one is being set
      if (updates.is_primary_key) {
        return { ...m, is_primary_key: false };
      }
      return m;
    }));
  };

  // Import handler
  const handleImport = async () => {
    const includedMappings = mappings.filter(m => m.include);
    
    if (includedMappings.length === 0) {
      toast.error('Mindestens eine Spalte muss ausgewählt sein');
      return;
    }

    if (!tableName.trim()) {
      toast.error('Tabellenname ist erforderlich');
      return;
    }

    if (!displayName.trim()) {
      toast.error('Anzeigename ist erforderlich');
      return;
    }

    setIsImporting(true);

    try {
      const res = await fetch(`${API_URL}/system/reference-upload/import`, {
        method: 'POST',
        headers: {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          table_name: tableName,
          display_name: displayName,
          mappings: includedMappings.map(m => ({
            source_column: m.source_column,
            target_field: m.target_field,
            data_type: m.data_type,
            is_primary_key: m.is_primary_key
          })),
          update_strategy: updateStrategy,
          file_data: analysis!.file_data,
          file_type: analysis!.file_type
        })
      });

      const data = await res.json();

      if (data.success) {
        setImportResult(data.data);
        setCurrentStep(3);
        toast.success('Import erfolgreich!');
      } else {
        toast.error(data.detail || 'Import fehlgeschlagen');
      }
    } catch (error) {
      console.error('Import error:', error);
      toast.error('Fehler beim Import');
    } finally {
      setIsImporting(false);
    }
  };

  // Render Step Content
  const renderStep = () => {
    switch (currentStep) {
      case 0: // Upload
        return (
          <div className="space-y-6">
            {/* Drop Zone */}
            <div
              className={cn(
                "relative border-2 border-dashed rounded-xl p-12 transition-all duration-300 cursor-pointer",
                "flex flex-col items-center justify-center text-center",
                isDragging 
                  ? "border-emerald-500 bg-emerald-50 scale-[1.02]" 
                  : "border-gray-300 hover:border-emerald-400 hover:bg-gray-50",
                isAnalyzing && "pointer-events-none opacity-70"
              )}
              onDragOver={handleDragOver}
              onDragLeave={handleDragLeave}
              onDrop={handleDrop}
              onClick={() => !isAnalyzing && fileInputRef.current?.click()}
            >
              <input
                ref={fileInputRef}
                type="file"
                accept=".csv,.xlsx,.xls"
                className="hidden"
                onChange={(e) => e.target.files?.[0] && handleFileSelect(e.target.files[0])}
              />
              
              {isAnalyzing ? (
                <div className="space-y-4 w-full max-w-xs">
                  <div className="h-16 w-16 mx-auto rounded-full bg-emerald-100 flex items-center justify-center">
                    <Sparkles className="h-8 w-8 text-emerald-600 animate-pulse" />
                  </div>
                  <p className="font-medium text-emerald-700">Analysiere Datei...</p>
                  <Progress value={analysisProgress} className="h-2" />
                  <p className="text-sm text-muted-foreground">Spalten und Datentypen werden erkannt</p>
                </div>
              ) : (
                <>
                  <div className={cn(
                    "h-20 w-20 rounded-2xl flex items-center justify-center mb-4 transition-colors",
                    isDragging ? "bg-emerald-200" : "bg-gray-100"
                  )}>
                    <Upload className={cn(
                      "h-10 w-10 transition-colors",
                      isDragging ? "text-emerald-600" : "text-gray-400"
                    )} />
                  </div>
                  <p className="text-lg font-medium mb-2">
                    {isDragging ? 'Datei hier ablegen' : 'Datei per Drag & Drop hochladen'}
                  </p>
                  <p className="text-sm text-muted-foreground mb-4">
                    oder klicken Sie zum Auswählen
                  </p>
                  <div className="flex items-center gap-3">
                    <Badge variant="outline" className="gap-1.5">
                      <FileSpreadsheet className="h-3.5 w-3.5" />
                      .xlsx
                    </Badge>
                    <Badge variant="outline" className="gap-1.5">
                      <FileSpreadsheet className="h-3.5 w-3.5" />
                      .xls
                    </Badge>
                    <Badge variant="outline" className="gap-1.5">
                      <FileText className="h-3.5 w-3.5" />
                      .csv
                    </Badge>
                  </div>
                </>
              )}
            </div>

            {/* Features */}
            <div className="grid grid-cols-3 gap-4">
              {[
                { icon: Sparkles, label: 'Intelligente Erkennung', desc: 'Spalten & Typen automatisch' },
                { icon: Settings2, label: 'Flexibles Mapping', desc: 'Spalten umbenennen & filtern' },
                { icon: Database, label: 'Sofort verfügbar', desc: 'Als Referenztabelle nutzen' },
              ].map((feature, i) => (
                <div key={i} className="p-4 rounded-lg bg-gray-50 text-center">
                  <feature.icon className="h-6 w-6 mx-auto mb-2 text-emerald-600" />
                  <p className="text-sm font-medium">{feature.label}</p>
                  <p className="text-xs text-muted-foreground">{feature.desc}</p>
                </div>
              ))}
            </div>
          </div>
        );

      case 1: // Mapping
        return (
          <div className="space-y-4">
            {/* File Info */}
            <div className="flex items-center justify-between p-4 bg-gray-50 rounded-lg">
              <div className="flex items-center gap-3">
                <div className="h-10 w-10 rounded-lg bg-emerald-100 flex items-center justify-center">
                  {analysis?.file_type === 'csv' 
                    ? <FileText className="h-5 w-5 text-emerald-600" />
                    : <FileSpreadsheet className="h-5 w-5 text-emerald-600" />
                  }
                </div>
                <div>
                  <p className="font-medium">{analysis?.filename}</p>
                  <p className="text-sm text-muted-foreground">
                    {analysis?.row_count.toLocaleString('de-DE')} Zeilen • {analysis?.columns.length} Spalten
                  </p>
                </div>
              </div>
              <Button 
                variant="outline" 
                size="sm" 
                onClick={() => setShowPreview(!showPreview)}
                className="gap-2"
              >
                <Eye className="h-4 w-4" />
                Vorschau
                {showPreview ? <ChevronUp className="h-4 w-4" /> : <ChevronDown className="h-4 w-4" />}
              </Button>
            </div>

            {/* Preview Table */}
            {showPreview && (
              <Card>
                <ScrollArea className="h-[200px]">
                  <Table>
                    <TableHeader>
                      <TableRow>
                        {analysis?.columns.map(col => (
                          <TableHead key={col.name} className="whitespace-nowrap text-xs">
                            {col.name}
                          </TableHead>
                        ))}
                      </TableRow>
                    </TableHeader>
                    <TableBody>
                      {analysis?.preview_rows.slice(0, 5).map((row, idx) => (
                        <TableRow key={idx}>
                          {analysis.columns.map(col => (
                            <TableCell key={col.name} className="text-xs max-w-[150px] truncate">
                              {String(row[col.name] ?? '')}
                            </TableCell>
                          ))}
                        </TableRow>
                      ))}
                    </TableBody>
                  </Table>
                </ScrollArea>
              </Card>
            )}

            {/* Column Mappings */}
            <div className="space-y-2">
              <div className="flex items-center justify-between mb-3">
                <Label className="text-base font-medium">Spalten-Zuordnung</Label>
                <Badge variant="outline">
                  {mappings.filter(m => m.include).length} von {mappings.length} ausgewählt
                </Badge>
              </div>
              
              <ScrollArea className="h-[300px] pr-4">
                <div className="space-y-2">
                  {mappings.map((mapping, index) => {
                    const colInfo = analysis?.columns.find(c => c.name === mapping.source_column);
                    const TypeIcon = DATA_TYPE_ICONS[mapping.data_type] || Type;
                    
                    return (
                      <div 
                        key={mapping.source_column}
                        className={cn(
                          "p-3 border rounded-lg transition-all",
                          mapping.include 
                            ? "bg-white border-gray-200" 
                            : "bg-gray-50 border-gray-100 opacity-60"
                        )}
                      >
                        <div className="flex items-center gap-3">
                          {/* Include Switch */}
                          <Switch
                            checked={mapping.include}
                            onCheckedChange={(v) => updateMapping(index, { include: v })}
                          />
                          
                          {/* Source Column */}
                          <div className="flex-1 min-w-0">
                            <p className="font-medium text-sm truncate">{mapping.source_column}</p>
                            <p className="text-xs text-muted-foreground">
                              {colInfo?.unique_count} eindeutige • {colInfo?.null_count} leer
                            </p>
                          </div>

                          {/* Arrow */}
                          <ArrowRight className="h-4 w-4 text-gray-400 flex-shrink-0" />

                          {/* Target Field */}
                          <Input
                            value={mapping.target_field}
                            onChange={(e) => updateMapping(index, { target_field: e.target.value })}
                            className="w-40 h-8 text-sm"
                            disabled={!mapping.include}
                          />

                          {/* Data Type */}
                          <Select
                            value={mapping.data_type}
                            onValueChange={(v) => updateMapping(index, { data_type: v })}
                            disabled={!mapping.include}
                          >
                            <SelectTrigger className="w-28 h-8">
                              <Badge 
                                variant="outline" 
                                className={cn("text-xs gap-1", DATA_TYPE_COLORS[mapping.data_type])}
                              >
                                <TypeIcon className="h-3 w-3" />
                                {mapping.data_type}
                              </Badge>
                            </SelectTrigger>
                            <SelectContent>
                              {Object.entries(DATA_TYPE_ICONS).map(([type, Icon]) => (
                                <SelectItem key={type} value={type}>
                                  <span className="flex items-center gap-2">
                                    <Icon className="h-3.5 w-3.5" />
                                    {type}
                                  </span>
                                </SelectItem>
                              ))}
                            </SelectContent>
                          </Select>

                          {/* Primary Key */}
                          <Button
                            variant={mapping.is_primary_key ? "default" : "outline"}
                            size="icon"
                            className={cn("h-8 w-8", mapping.is_primary_key && "bg-amber-500 hover:bg-amber-600")}
                            onClick={() => updateMapping(index, { is_primary_key: !mapping.is_primary_key })}
                            disabled={!mapping.include}
                            title="Als Primärschlüssel"
                          >
                            <Key className="h-3.5 w-3.5" />
                          </Button>
                        </div>
                      </div>
                    );
                  })}
                </div>
              </ScrollArea>
            </div>
          </div>
        );

      case 2: // Config
        return (
          <div className="space-y-6">
            {/* Table Name */}
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label>Technischer Name</Label>
                <Input
                  value={tableName}
                  onChange={(e) => setTableName(e.target.value)}
                  placeholder="ref_meine_daten"
                  className="mt-1.5 font-mono"
                />
                <p className="text-xs text-muted-foreground mt-1">
                  Wird automatisch mit "ref_" prefixiert
                </p>
              </div>
              <div>
                <Label>Anzeigename</Label>
                <Input
                  value={displayName}
                  onChange={(e) => setDisplayName(e.target.value)}
                  placeholder="Meine Daten"
                  className="mt-1.5"
                />
              </div>
            </div>

            {/* Update Strategy */}
            <div>
              <Label className="mb-3 block">Aktualisierungsstrategie</Label>
              <div className="grid grid-cols-3 gap-3">
                {[
                  { 
                    value: 'merge', 
                    label: 'Merge', 
                    desc: 'Bestehende aktualisieren, neue hinzufügen',
                    icon: '🔄'
                  },
                  { 
                    value: 'replace', 
                    label: 'Ersetzen', 
                    desc: 'Alle bestehenden Daten löschen',
                    icon: '🔁'
                  },
                  { 
                    value: 'append', 
                    label: 'Anhängen', 
                    desc: 'Nur neue Datensätze hinzufügen',
                    icon: '➕'
                  },
                ].map((option) => (
                  <button
                    key={option.value}
                    onClick={() => setUpdateStrategy(option.value as any)}
                    className={cn(
                      "p-4 rounded-lg border-2 text-left transition-all",
                      updateStrategy === option.value
                        ? "border-emerald-500 bg-emerald-50"
                        : "border-gray-200 hover:border-gray-300"
                    )}
                  >
                    <span className="text-2xl mb-2 block">{option.icon}</span>
                    <p className="font-medium">{option.label}</p>
                    <p className="text-xs text-muted-foreground">{option.desc}</p>
                  </button>
                ))}
              </div>
            </div>

            {/* Summary */}
            <Card className="bg-gray-50">
              <CardHeader className="pb-3">
                <CardTitle className="text-base">Zusammenfassung</CardTitle>
              </CardHeader>
              <CardContent className="space-y-2 text-sm">
                <div className="flex justify-between">
                  <span className="text-muted-foreground">Datei:</span>
                  <span className="font-medium">{analysis?.filename}</span>
                </div>
                <div className="flex justify-between">
                  <span className="text-muted-foreground">Zeilen:</span>
                  <span className="font-medium">{analysis?.row_count.toLocaleString('de-DE')}</span>
                </div>
                <div className="flex justify-between">
                  <span className="text-muted-foreground">Ausgewählte Spalten:</span>
                  <span className="font-medium">{mappings.filter(m => m.include).length}</span>
                </div>
                <div className="flex justify-between">
                  <span className="text-muted-foreground">Primärschlüssel:</span>
                  <span className="font-medium">
                    {mappings.find(m => m.is_primary_key)?.target_field || 'Keiner'}
                  </span>
                </div>
              </CardContent>
            </Card>
          </div>
        );

      case 3: // Success
        return (
          <div className="text-center py-8 space-y-6">
            <div className="h-20 w-20 mx-auto rounded-full bg-emerald-100 flex items-center justify-center">
              <CheckCircle className="h-10 w-10 text-emerald-600" />
            </div>
            
            <div>
              <h3 className="text-xl font-semibold text-emerald-700">Import erfolgreich!</h3>
              <p className="text-muted-foreground mt-1">
                Die Referenztabelle wurde erfolgreich erstellt
              </p>
            </div>

            <Card className="max-w-md mx-auto">
              <CardContent className="pt-6 space-y-3">
                <div className="flex justify-between text-sm">
                  <span className="text-muted-foreground">Tabelle:</span>
                  <span className="font-mono font-medium">{importResult?.table_name}</span>
                </div>
                <div className="flex justify-between text-sm">
                  <span className="text-muted-foreground">Erstellt:</span>
                  <Badge variant="outline" className="bg-emerald-50 text-emerald-700">
                    {importResult?.records_created.toLocaleString('de-DE')} Datensätze
                  </Badge>
                </div>
                {importResult?.records_updated > 0 && (
                  <div className="flex justify-between text-sm">
                    <span className="text-muted-foreground">Aktualisiert:</span>
                    <Badge variant="outline" className="bg-blue-50 text-blue-700">
                      {importResult?.records_updated.toLocaleString('de-DE')} Datensätze
                    </Badge>
                  </div>
                )}
                <div className="flex justify-between text-sm pt-2 border-t">
                  <span className="text-muted-foreground">Gesamt:</span>
                  <span className="font-semibold">
                    {importResult?.total_records.toLocaleString('de-DE')} Datensätze
                  </span>
                </div>
              </CardContent>
            </Card>

            <Button onClick={() => { onSuccess(); handleClose(); }} className="mt-4">
              <Database className="h-4 w-4 mr-2" />
              Zur Referenztabelle
            </Button>
          </div>
        );

      default:
        return null;
    }
  };

  return (
    <Dialog open={open} onOpenChange={(v) => !v && handleClose()}>
      <DialogContent className="max-w-3xl max-h-[90vh] flex flex-col">
        <DialogHeader>
          <DialogTitle className="flex items-center gap-2">
            <Upload className="h-5 w-5 text-emerald-600" />
            Referenztabelle aus Datei erstellen
          </DialogTitle>
        </DialogHeader>

        {/* Step Indicators */}
        {currentStep < 3 && (
          <div className="flex items-center justify-between py-4 border-b">
            {STEPS.map((step, index) => {
              const Icon = step.icon;
              const isActive = index === currentStep;
              const isComplete = index < currentStep;
              
              return (
                <div
                  key={step.id}
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
                </div>
              );
            })}
          </div>
        )}

        {/* Step Content */}
        <ScrollArea className="flex-1 pr-4">
          <div className="py-4">
            {renderStep()}
          </div>
        </ScrollArea>

        {/* Footer */}
        {currentStep < 3 && (
          <div className="flex items-center justify-between pt-4 border-t">
            <Button
              variant="outline"
              onClick={() => currentStep > 0 ? setCurrentStep(currentStep - 1) : handleClose()}
              disabled={isImporting}
            >
              <ArrowLeft className="h-4 w-4 mr-2" />
              {currentStep === 0 ? 'Abbrechen' : 'Zurück'}
            </Button>

            <div className="flex gap-2">
              {currentStep === 2 && (
                <Button 
                  onClick={handleImport} 
                  disabled={isImporting} 
                  className="bg-emerald-600 hover:bg-emerald-700"
                >
                  {isImporting ? (
                    <>
                      <Loader2 className="h-4 w-4 mr-2 animate-spin" />
                      Importiere...
                    </>
                  ) : (
                    <>
                      <Check className="h-4 w-4 mr-2" />
                      Import starten
                    </>
                  )}
                </Button>
              )}
              {currentStep < 2 && currentStep > 0 && (
                <Button 
                  onClick={() => setCurrentStep(currentStep + 1)}
                  disabled={mappings.filter(m => m.include).length === 0}
                >
                  Weiter
                  <ArrowRight className="h-4 w-4 ml-2" />
                </Button>
              )}
            </div>
          </div>
        )}
      </DialogContent>
    </Dialog>
  );
}
