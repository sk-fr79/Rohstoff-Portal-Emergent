import { useState, useEffect, useCallback, useRef } from 'react';
import { rechnungenApi, adressenApi } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Badge } from '@/components/ui/badge';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '@/components/ui/table';
import { Textarea } from '@/components/ui/textarea';
import { toast } from 'sonner';
import { cn } from '@/lib/utils';
import { 
  Search, Plus, X, FileText, Building2, Calendar, 
  CreditCard, ChevronRight, Save, Edit2, 
  ArrowUpRight, ArrowDownLeft, Package, Loader2, GripVertical
} from 'lucide-react';

interface Rechnung {
  id: string;
  rechnungs_nr: string;
  buchungsnummer?: string;
  vorgang_typ: string;
  name1: string;
  name2?: string;
  strasse?: string;
  hausnummer?: string;
  plz?: string;
  ort?: string;
  land?: string;
  ustid?: string;
  erstellungsdatum?: string;
  leistungsdatum?: string;
  faelligkeitsdatum?: string;
  waehrung?: string;
  zahlungsbedingung?: string;
  status: string;
  summe_netto?: number;
  summe_steuer?: number;
  summe_brutto?: number;
  positionen?: Array<{id: string; artbez: string; menge: number; einzelpreis: number; gesamtpreis: number}>;
  bemerkung_extern?: string;
  bemerkung_intern?: string;
  [key: string]: unknown;
}

const STATUS_CONFIG: Record<string, { label: string; color: string }> = {
  ENTWURF: { label: 'Entwurf', color: 'bg-slate-100 text-slate-800' },
  OFFEN: { label: 'Offen', color: 'bg-blue-100 text-blue-800' },
  BEZAHLT: { label: 'Bezahlt', color: 'bg-emerald-100 text-emerald-800' },
  TEILBEZAHLT: { label: 'Teilbezahlt', color: 'bg-yellow-100 text-yellow-800' },
  MAHNUNG: { label: 'Mahnung', color: 'bg-orange-100 text-orange-800' },
  STORNIERT: { label: 'Storniert', color: 'bg-red-100 text-red-800' },
};

const VORGANG_TYP_CONFIG: Record<string, { label: string; icon: typeof ArrowUpRight; color: string }> = {
  RECHNUNG: { label: 'Rechnung', icon: ArrowUpRight, color: 'text-blue-600' },
  GUTSCHRIFT: { label: 'Gutschrift', icon: ArrowDownLeft, color: 'text-green-600' },
};

const SIDEBAR_SECTIONS = [
  { id: 'kopfdaten', label: 'Kopfdaten', icon: FileText },
  { id: 'adressat', label: 'Adressat', icon: Building2 },
  { id: 'termine', label: 'Termine', icon: Calendar },
  { id: 'positionen', label: 'Positionen', icon: Package },
  { id: 'zahlungen', label: 'Zahlungen', icon: CreditCard },
  { id: 'bemerkungen', label: 'Bemerkungen', icon: FileText },
];

export default function RechnungenPage() {
  const [rechnungen, setRechnungen] = useState<Rechnung[]>([]);
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');
  const [filterTyp, setFilterTyp] = useState('');
  const [filterStatus, setFilterStatus] = useState('');
  const [selectedRechnung, setSelectedRechnung] = useState<Rechnung | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [isNewRecord, setIsNewRecord] = useState(false);
  const [editData, setEditData] = useState<Partial<Rechnung>>({});
  const [activeSection, setActiveSection] = useState('kopfdaten');
  const [saving, setSaving] = useState(false);
  
  // State für resizable Sidebar
  const [panelWidth, setPanelWidth] = useState(50); // Standard 50%
  const [isDragging, setIsDragging] = useState(false);
  const containerRef = useRef<HTMLDivElement>(null);
  
  // Lookup data
  const [adressen, setAdressen] = useState<Array<{id: string; name1: string; ort?: string; plz?: string; strasse?: string}>>([]);
  
  // Resizable Panel Logik
  const handleMouseMove = useCallback((e: MouseEvent) => {
    if (!isDragging || !containerRef.current) return;
    
    const container = containerRef.current;
    const rect = container.getBoundingClientRect();
    const x = e.clientX - rect.left;
    const percentage = (x / rect.width) * 100;
    
    // Grenzen: Min 30%, Max 70%
    const clampedPercentage = Math.min(Math.max(percentage, 30), 70);
    setPanelWidth(clampedPercentage);
  }, [isDragging]);
  
  const handleMouseUp = useCallback(() => {
    setIsDragging(false);
  }, []);
  
  useEffect(() => {
    if (isDragging) {
      document.addEventListener('mousemove', handleMouseMove);
      document.addEventListener('mouseup', handleMouseUp);
      document.body.style.userSelect = 'none';
      document.body.style.cursor = 'col-resize';
    } else {
      document.body.style.userSelect = '';
      document.body.style.cursor = '';
    }
    
    return () => {
      document.removeEventListener('mousemove', handleMouseMove);
      document.removeEventListener('mouseup', handleMouseUp);
      document.body.style.userSelect = '';
      document.body.style.cursor = '';
    };
  }, [isDragging, handleMouseMove, handleMouseUp]);

  const loadRechnungen = useCallback(async () => {
    try {
      setLoading(true);
      const params: Record<string, unknown> = {};
      if (searchTerm) params.suche = searchTerm;
      if (filterTyp && filterTyp !== 'ALL') params.vorgang_typ = filterTyp;
      if (filterStatus && filterStatus !== 'ALL') params.status = filterStatus;
      
      const response = await rechnungenApi.list(params);
      setRechnungen(response.data.data || []);
    } catch (error) {
      toast.error('Fehler beim Laden der Rechnungen');
    } finally {
      setLoading(false);
    }
  }, [searchTerm, filterTyp, filterStatus]);

  const loadLookupData = useCallback(async () => {
    try {
      const adressenRes = await adressenApi.list({ limit: 500 });
      setAdressen(adressenRes.data.data || []);
    } catch (error) {
      console.error('Fehler beim Laden der Lookup-Daten:', error);
    }
  }, []);

  useEffect(() => {
    loadRechnungen();
    loadLookupData();
  }, [loadRechnungen, loadLookupData]);

  // Neue Rechnung anlegen - öffnet Sidebar mit leerem Datensatz
  const handleNewRechnung = () => {
    const today = new Date().toISOString().split('T')[0];
    const emptyRechnung: Rechnung = {
      id: 'NEU',
      rechnungs_nr: '(wird automatisch vergeben)',
      vorgang_typ: 'RECHNUNG',
      name1: '',
      status: 'ENTWURF',
      waehrung: 'EUR',
      erstellungsdatum: today,
    };
    setSelectedRechnung(emptyRechnung);
    setEditData(emptyRechnung);
    setIsNewRecord(true);
    setIsEditing(true);
    setActiveSection('kopfdaten');
  };

  // Adresse auswählen und Felder übernehmen
  const handleAdresseSelect = (adresseId: string) => {
    const adresse = adressen.find(a => a.id === adresseId);
    if (adresse) {
      setEditData({
        ...editData,
        id_adresse: adresseId,
        name1: adresse.name1,
        ort: adresse.ort,
        plz: adresse.plz,
        strasse: adresse.strasse,
      });
    }
  };

  // Speichern (Create oder Update)
  const handleSave = async () => {
    if (!selectedRechnung) return;
    
    setSaving(true);
    try {
      if (isNewRecord) {
        // Neue Rechnung erstellen
        const createData = {
          vorgang_typ: editData.vorgang_typ || 'RECHNUNG',
          id_adresse: editData.id_adresse,
          name1: editData.name1,
          name2: editData.name2,
          strasse: editData.strasse,
          hausnummer: editData.hausnummer,
          plz: editData.plz,
          ort: editData.ort,
          land: editData.land,
          ustid: editData.ustid,
          erstellungsdatum: editData.erstellungsdatum,
          leistungsdatum: editData.leistungsdatum,
          faelligkeitsdatum: editData.faelligkeitsdatum,
          waehrung: editData.waehrung || 'EUR',
          zahlungsbedingung: editData.zahlungsbedingung,
          status: editData.status || 'ENTWURF',
          bemerkung_extern: editData.bemerkung_extern,
          bemerkung_intern: editData.bemerkung_intern,
        };
        
        const response = await rechnungenApi.create(createData);
        if (response.data.success) {
          toast.success('Rechnung erstellt');
          setSelectedRechnung(response.data.data);
          setEditData(response.data.data);
          setIsNewRecord(false);
          setIsEditing(false);
          loadRechnungen();
        } else {
          toast.error(response.data.error || 'Fehler beim Erstellen');
        }
      } else {
        // Bestehende Rechnung aktualisieren
        const response = await rechnungenApi.update(selectedRechnung.id, editData);
        if (response.data.success) {
          toast.success('Änderungen gespeichert');
          setIsEditing(false);
          loadRechnungen();
          const res = await rechnungenApi.getById(selectedRechnung.id);
          setSelectedRechnung(res.data.data);
          setEditData(res.data.data);
        } else {
          toast.error(response.data.error || 'Fehler beim Speichern');
        }
      }
    } catch (error) {
      toast.error('Fehler beim Speichern');
    } finally {
      setSaving(false);
    }
  };

  // Abbrechen
  const handleCancel = () => {
    if (isNewRecord) {
      setSelectedRechnung(null);
      setIsNewRecord(false);
    }
    setIsEditing(false);
    if (selectedRechnung && !isNewRecord) {
      setEditData(selectedRechnung);
    }
  };

  // Sidebar schließen
  const handleClose = () => {
    setSelectedRechnung(null);
    setIsEditing(false);
    setIsNewRecord(false);
  };

  const handleRowDoubleClick = async (rechnung: Rechnung) => {
    try {
      const res = await rechnungenApi.getById(rechnung.id);
      setSelectedRechnung(res.data.data);
      setEditData(res.data.data);
      setIsNewRecord(false);
      setIsEditing(false);
      setActiveSection('kopfdaten');
    } catch (error) {
      toast.error('Fehler beim Laden der Rechnung');
    }
  };

  const formatDate = (dateStr?: string) => {
    if (!dateStr) return '-';
    return new Date(dateStr).toLocaleDateString('de-DE');
  };

  const formatCurrency = (value?: number) => {
    if (value === undefined || value === null) return '-';
    return new Intl.NumberFormat('de-DE', { style: 'currency', currency: 'EUR' }).format(value);
  };

  return (
    <div className="h-full flex flex-col" data-testid="rechnungen-page">
      {/* Header */}
      <div className="bg-white border-b border-gray-200 px-6 py-4">
        <div className="flex items-center justify-between">
          <div>
            <h1 className="text-xl font-semibold text-gray-900">Rechnungen & Gutschriften</h1>
            <p className="text-sm text-gray-500 mt-0.5">Fakturierung</p>
          </div>
          <div className="flex items-center gap-3">
            <div className="relative">
              <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
              <Input
                placeholder="Suchen..."
                className="pl-10 w-[250px]"
                value={searchTerm}
                onChange={(e) => setSearchTerm(e.target.value)}
                data-testid="rechnungen-search"
              />
            </div>
            <Select value={filterTyp || "ALL"} onValueChange={(v) => setFilterTyp(v === "ALL" ? "" : v)}>
              <SelectTrigger className="w-[140px]">
                <SelectValue placeholder="Alle Typen" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="ALL">Alle Typen</SelectItem>
                <SelectItem value="RECHNUNG">Rechnungen</SelectItem>
                <SelectItem value="GUTSCHRIFT">Gutschriften</SelectItem>
              </SelectContent>
            </Select>
            <Select value={filterStatus || "ALL"} onValueChange={(v) => setFilterStatus(v === "ALL" ? "" : v)}>
              <SelectTrigger className="w-[130px]">
                <SelectValue placeholder="Alle Status" />
              </SelectTrigger>
              <SelectContent>
                <SelectItem value="ALL">Alle Status</SelectItem>
                {Object.entries(STATUS_CONFIG).map(([key, { label }]) => (
                  <SelectItem key={key} value={key}>{label}</SelectItem>
                ))}
              </SelectContent>
            </Select>
            <Button onClick={handleNewRechnung} className="bg-emerald-600 hover:bg-emerald-700" data-testid="new-rechnung-btn">
              <Plus className="h-4 w-4 mr-2" />
              Neue Rechnung
            </Button>
          </div>
        </div>
      </div>

      {/* Content Area */}
      <div ref={containerRef} className="flex-1 flex overflow-hidden">
        {/* Table */}
        <div 
          className="p-6 overflow-auto transition-none"
          style={{ width: selectedRechnung ? `${panelWidth}%` : '100%' }}
        >
          <div className="bg-white rounded-xl shadow-sm border overflow-hidden">
            <Table>
              <TableHeader>
                <TableRow className="bg-slate-50">
                  <TableHead className="w-[100px]">Nr.</TableHead>
                  <TableHead className="w-[80px]">Typ</TableHead>
                  <TableHead>Adressat</TableHead>
                  <TableHead>Datum</TableHead>
                  <TableHead className="text-right">Netto</TableHead>
                  <TableHead className="text-right">Brutto</TableHead>
                  <TableHead>Status</TableHead>
                </TableRow>
              </TableHeader>
              <TableBody>
                {loading ? (
                  <TableRow><TableCell colSpan={7} className="text-center py-8">Laden...</TableCell></TableRow>
                ) : rechnungen.length === 0 ? (
                  <TableRow><TableCell colSpan={7} className="text-center py-8 text-slate-500">Keine Rechnungen gefunden</TableCell></TableRow>
                ) : (
                  rechnungen.map((rechnung) => {
                    const TypConfig = VORGANG_TYP_CONFIG[rechnung.vorgang_typ] || VORGANG_TYP_CONFIG.RECHNUNG;
                    const TypIcon = TypConfig.icon;
                    return (
                      <TableRow 
                        key={rechnung.id} 
                        className="cursor-pointer hover:bg-slate-50"
                        onDoubleClick={() => handleRowDoubleClick(rechnung)}
                        data-testid={`rechnung-row-${rechnung.id}`}
                      >
                        <TableCell className="font-medium">{rechnung.rechnungs_nr}</TableCell>
                        <TableCell>
                          <div className={`flex items-center gap-1 ${TypConfig.color}`}>
                            <TypIcon className="h-4 w-4" />
                            <span className="text-xs">{TypConfig.label.substring(0, 2)}</span>
                          </div>
                        </TableCell>
                        <TableCell>{rechnung.name1}</TableCell>
                        <TableCell>{formatDate(rechnung.erstellungsdatum)}</TableCell>
                        <TableCell className="text-right">{formatCurrency(rechnung.summe_netto)}</TableCell>
                        <TableCell className="text-right font-medium">{formatCurrency(rechnung.summe_brutto)}</TableCell>
                        <TableCell>
                          <Badge className={STATUS_CONFIG[rechnung.status]?.color || 'bg-slate-100'}>
                            {STATUS_CONFIG[rechnung.status]?.label || rechnung.status}
                          </Badge>
                        </TableCell>
                      </TableRow>
                    );
                  })
                )}
              </TableBody>
            </Table>
          </div>
        </div>

        {/* Resizable Handle */}
        {selectedRechnung && (
          <div
            className={cn(
              "relative flex w-1.5 items-center justify-center bg-gray-100 transition-colors cursor-col-resize select-none",
              "hover:bg-emerald-200 active:bg-emerald-300",
              isDragging && "bg-emerald-400"
            )}
            onMouseDown={(e) => {
              e.preventDefault();
              setIsDragging(true);
            }}
            data-testid="resize-handle"
          >
            <div className={cn(
              "absolute flex h-10 w-5 items-center justify-center rounded-sm",
              "bg-gray-200/80 backdrop-blur-sm opacity-0 transition-opacity",
              "hover:opacity-100",
              isDragging && "opacity-100 bg-emerald-400"
            )}>
              <GripVertical className="h-4 w-4 text-gray-500" />
            </div>
            <div className="absolute inset-y-0 -left-2 -right-2" />
          </div>
        )}

        {/* Detail Panel */}
        {selectedRechnung && (
          <div 
            className="bg-white border-l border-gray-200 flex flex-col overflow-hidden" 
            style={{ width: `${100 - panelWidth}%` }}
            data-testid="rechnung-detail-panel"
          >
            {/* Header */}
            <div className="flex items-center justify-between p-4 border-b bg-slate-50">
              <div>
                <div className="flex items-center gap-2">
                  <h2 className="text-lg font-semibold">
                    {isNewRecord ? 'Neue Rechnung' : selectedRechnung.rechnungs_nr}
                  </h2>
                  <Badge className={VORGANG_TYP_CONFIG[selectedRechnung.vorgang_typ]?.color}>
                    {VORGANG_TYP_CONFIG[selectedRechnung.vorgang_typ]?.label}
                  </Badge>
                </div>
                <Badge className={STATUS_CONFIG[selectedRechnung.status]?.color}>
                  {STATUS_CONFIG[selectedRechnung.status]?.label}
                </Badge>
              </div>
              <div className="flex items-center gap-2">
                {isEditing ? (
                  <>
                    <Button variant="outline" size="sm" onClick={handleCancel} disabled={saving}>
                      Abbrechen
                    </Button>
                    <Button size="sm" onClick={handleSave} className="bg-emerald-600" disabled={saving}>
                      {saving ? <Loader2 className="h-4 w-4 mr-1 animate-spin" /> : <Save className="h-4 w-4 mr-1" />}
                      Speichern
                    </Button>
                  </>
                ) : (
                  <Button variant="outline" size="sm" onClick={() => setIsEditing(true)}>
                    <Edit2 className="h-4 w-4 mr-1" />Bearbeiten
                  </Button>
                )}
                <Button variant="ghost" size="icon" onClick={handleClose}>
                  <X className="h-5 w-5" />
                </Button>
              </div>
            </div>
          
            {/* Content with Sidebar */}
            <div className="flex flex-1 overflow-hidden">
              {/* Sidebar */}
              <div className="w-48 border-r bg-slate-50 p-2 space-y-1">
                {SIDEBAR_SECTIONS.map((section) => (
                  <button
                  key={section.id}
                  onClick={() => setActiveSection(section.id)}
                  className={`w-full flex items-center gap-2 px-3 py-2 rounded-lg text-sm transition-colors ${
                    activeSection === section.id 
                      ? 'bg-emerald-100 text-emerald-800 font-medium' 
                      : 'text-slate-600 hover:bg-slate-100'
                  }`}
                >
                  <section.icon className="h-4 w-4" />
                  {section.label}
                  {activeSection === section.id && <ChevronRight className="h-4 w-4 ml-auto" />}
                </button>
              ))}
            </div>
            
            {/* Form Content */}
            <div className="flex-1 p-4 overflow-y-auto">
              {activeSection === 'kopfdaten' && (
                <div className="space-y-4">
                  <h3 className="font-medium text-slate-800 mb-4">Kopfdaten</h3>
                  <div className="grid grid-cols-2 gap-4">
                    <div>
                      <Label>Rechnungs-Nr.</Label>
                      <Input value={isNewRecord ? '(wird automatisch vergeben)' : selectedRechnung.rechnungs_nr} disabled />
                    </div>
                    <div>
                      <Label>Buchungsnummer</Label>
                      <Input value={selectedRechnung.buchungsnummer || ''} disabled />
                    </div>
                    <div>
                      <Label>Typ *</Label>
                      <Select 
                        disabled={!isEditing} 
                        value={editData.vorgang_typ || selectedRechnung.vorgang_typ}
                        onValueChange={(v) => setEditData({...editData, vorgang_typ: v})}
                      >
                        <SelectTrigger><SelectValue /></SelectTrigger>
                        <SelectContent>
                          <SelectItem value="RECHNUNG">Rechnung</SelectItem>
                          <SelectItem value="GUTSCHRIFT">Gutschrift</SelectItem>
                        </SelectContent>
                      </Select>
                    </div>
                    <div>
                      <Label>Status</Label>
                      <Select 
                        disabled={!isEditing || isNewRecord} 
                        value={editData.status || selectedRechnung.status}
                        onValueChange={(v) => setEditData({...editData, status: v})}
                      >
                        <SelectTrigger><SelectValue /></SelectTrigger>
                        <SelectContent>
                          {Object.entries(STATUS_CONFIG).map(([key, { label }]) => (
                            <SelectItem key={key} value={key}>{label}</SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                    </div>
                    <div>
                      <Label>Währung</Label>
                      <Select 
                        disabled={!isEditing}
                        value={editData.waehrung || selectedRechnung.waehrung || 'EUR'}
                        onValueChange={(v) => setEditData({...editData, waehrung: v})}
                      >
                        <SelectTrigger><SelectValue /></SelectTrigger>
                        <SelectContent>
                          <SelectItem value="EUR">EUR</SelectItem>
                          <SelectItem value="USD">USD</SelectItem>
                          <SelectItem value="CHF">CHF</SelectItem>
                        </SelectContent>
                      </Select>
                    </div>
                  </div>
                  
                  {/* Summen */}
                  {!isNewRecord && (
                    <div className="mt-6 p-4 bg-slate-50 rounded-lg">
                      <h4 className="font-medium mb-3">Summen</h4>
                      <div className="space-y-2">
                        <div className="flex justify-between">
                          <span>Netto:</span>
                          <span>{formatCurrency(selectedRechnung.summe_netto)}</span>
                        </div>
                        <div className="flex justify-between">
                          <span>MwSt:</span>
                          <span>{formatCurrency(selectedRechnung.summe_steuer)}</span>
                        </div>
                        <div className="flex justify-between font-bold text-lg border-t pt-2">
                          <span>Brutto:</span>
                          <span>{formatCurrency(selectedRechnung.summe_brutto)}</span>
                        </div>
                      </div>
                    </div>
                  )}
                </div>
              )}
              
              {activeSection === 'adressat' && (
                <div className="space-y-4">
                  <h3 className="font-medium text-slate-800 mb-4">Adressat</h3>
                  {isEditing && (
                    <div>
                      <Label>Adresse auswählen</Label>
                      <Select 
                        value={editData.id_adresse as string || selectedRechnung.id_adresse as string || ''}
                        onValueChange={handleAdresseSelect}
                      >
                        <SelectTrigger><SelectValue placeholder="Adresse auswählen" /></SelectTrigger>
                        <SelectContent>
                          {adressen.map((a) => (
                            <SelectItem key={a.id} value={a.id}>{a.name1} {a.ort ? `(${a.ort})` : ''}</SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                    </div>
                  )}
                  <div>
                    <Label>Name 1 *</Label>
                    <Input 
                      disabled={!isEditing}
                      value={editData.name1 ?? selectedRechnung.name1 ?? ''}
                      onChange={(e) => setEditData({...editData, name1: e.target.value})}
                    />
                  </div>
                  <div>
                    <Label>Name 2</Label>
                    <Input 
                      disabled={!isEditing}
                      value={editData.name2 ?? selectedRechnung.name2 ?? ''}
                      onChange={(e) => setEditData({...editData, name2: e.target.value})}
                    />
                  </div>
                  <div className="grid grid-cols-3 gap-4">
                    <div className="col-span-2">
                      <Label>Straße</Label>
                      <Input 
                        disabled={!isEditing}
                        value={editData.strasse ?? selectedRechnung.strasse ?? ''}
                        onChange={(e) => setEditData({...editData, strasse: e.target.value})}
                      />
                    </div>
                    <div>
                      <Label>Hausnr.</Label>
                      <Input 
                        disabled={!isEditing}
                        value={editData.hausnummer ?? selectedRechnung.hausnummer ?? ''}
                        onChange={(e) => setEditData({...editData, hausnummer: e.target.value})}
                      />
                    </div>
                  </div>
                  <div className="grid grid-cols-3 gap-4">
                    <div>
                      <Label>PLZ</Label>
                      <Input 
                        disabled={!isEditing}
                        value={editData.plz ?? selectedRechnung.plz ?? ''}
                        onChange={(e) => setEditData({...editData, plz: e.target.value})}
                      />
                    </div>
                    <div className="col-span-2">
                      <Label>Ort</Label>
                      <Input 
                        disabled={!isEditing}
                        value={editData.ort ?? selectedRechnung.ort ?? ''}
                        onChange={(e) => setEditData({...editData, ort: e.target.value})}
                      />
                    </div>
                  </div>
                  <div>
                    <Label>UST-ID</Label>
                    <Input 
                      disabled={!isEditing}
                      value={editData.ustid ?? selectedRechnung.ustid ?? ''}
                      onChange={(e) => setEditData({...editData, ustid: e.target.value})}
                    />
                  </div>
                </div>
              )}
              
              {activeSection === 'termine' && (
                <div className="space-y-4">
                  <h3 className="font-medium text-slate-800 mb-4">Termine</h3>
                  <div className="grid grid-cols-2 gap-4">
                    <div>
                      <Label>Rechnungsdatum</Label>
                      <Input 
                        type="date" 
                        disabled={!isEditing}
                        value={editData.erstellungsdatum ?? selectedRechnung.erstellungsdatum ?? ''}
                        onChange={(e) => setEditData({...editData, erstellungsdatum: e.target.value})}
                      />
                    </div>
                    <div>
                      <Label>Leistungsdatum</Label>
                      <Input 
                        type="date" 
                        disabled={!isEditing}
                        value={editData.leistungsdatum ?? selectedRechnung.leistungsdatum ?? ''}
                        onChange={(e) => setEditData({...editData, leistungsdatum: e.target.value})}
                      />
                    </div>
                    <div>
                      <Label>Fälligkeitsdatum</Label>
                      <Input 
                        type="date" 
                        disabled={!isEditing}
                        value={editData.faelligkeitsdatum ?? selectedRechnung.faelligkeitsdatum ?? ''}
                        onChange={(e) => setEditData({...editData, faelligkeitsdatum: e.target.value})}
                      />
                    </div>
                  </div>
                  <div>
                    <Label>Zahlungsbedingung</Label>
                    <Textarea 
                      disabled={!isEditing}
                      value={editData.zahlungsbedingung ?? selectedRechnung.zahlungsbedingung ?? ''}
                      onChange={(e) => setEditData({...editData, zahlungsbedingung: e.target.value})}
                    />
                  </div>
                </div>
              )}
              
              {activeSection === 'positionen' && (
                <div className="space-y-4">
                  <div className="flex items-center justify-between mb-4">
                    <h3 className="font-medium text-slate-800">Positionen</h3>
                    {isEditing && !isNewRecord && (
                      <Button size="sm" variant="outline">
                        <Plus className="h-4 w-4 mr-1" /> Position
                      </Button>
                    )}
                  </div>
                  {isNewRecord ? (
                    <div className="text-center py-8 text-slate-500 border rounded-lg">
                      Bitte zuerst speichern, um Positionen hinzuzufügen
                    </div>
                  ) : selectedRechnung.positionen && selectedRechnung.positionen.length > 0 ? (
                    <div className="border rounded-lg overflow-hidden">
                      <Table>
                        <TableHeader>
                          <TableRow className="bg-slate-50">
                            <TableHead>Artikel</TableHead>
                            <TableHead className="text-right">Menge</TableHead>
                            <TableHead className="text-right">Einzelpreis</TableHead>
                            <TableHead className="text-right">Gesamt</TableHead>
                          </TableRow>
                        </TableHeader>
                        <TableBody>
                          {selectedRechnung.positionen.map((pos, idx) => (
                            <TableRow key={pos.id || idx}>
                              <TableCell>{pos.artbez}</TableCell>
                              <TableCell className="text-right">{pos.menge}</TableCell>
                              <TableCell className="text-right">{formatCurrency(pos.einzelpreis)}</TableCell>
                              <TableCell className="text-right font-medium">{formatCurrency(pos.gesamtpreis)}</TableCell>
                            </TableRow>
                          ))}
                        </TableBody>
                      </Table>
                    </div>
                  ) : (
                    <div className="text-center py-8 text-slate-500 border rounded-lg">
                      Keine Positionen vorhanden
                    </div>
                  )}
                </div>
              )}
              
              {activeSection === 'zahlungen' && (
                <div className="space-y-4">
                  <h3 className="font-medium text-slate-800 mb-4">Zahlungen</h3>
                  <div className="text-center py-8 text-slate-500 border rounded-lg">
                    Keine Zahlungen erfasst
                  </div>
                </div>
              )}
              
              {activeSection === 'bemerkungen' && (
                <div className="space-y-4">
                  <h3 className="font-medium text-slate-800 mb-4">Bemerkungen</h3>
                  <div>
                    <Label>Externe Bemerkung (auf Beleg)</Label>
                    <Textarea 
                      rows={4}
                      disabled={!isEditing}
                      value={editData.bemerkung_extern ?? selectedRechnung.bemerkung_extern ?? ''}
                      onChange={(e) => setEditData({...editData, bemerkung_extern: e.target.value})}
                    />
                  </div>
                  <div>
                    <Label>Interne Bemerkung</Label>
                    <Textarea 
                      rows={4}
                      disabled={!isEditing}
                      value={editData.bemerkung_intern ?? selectedRechnung.bemerkung_intern ?? ''}
                      onChange={(e) => setEditData({...editData, bemerkung_intern: e.target.value})}
                    />
                  </div>
                </div>
              )}
            </div>
          </div>
        </div>
        )}
      </div>
    </div>
  );
}
