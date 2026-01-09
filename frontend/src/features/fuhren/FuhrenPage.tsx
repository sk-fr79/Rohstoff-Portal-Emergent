import { useState, useEffect, useCallback } from 'react';
import { fuhrenApi, adressenApi, artikelApi, wiegekartenApi } from '../../services/api/client';
import { Button } from '../../components/ui/button';
import { Input } from '../../components/ui/input';
import { Label } from '../../components/ui/label';
import { Badge } from '../../components/ui/badge';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '../../components/ui/select';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from '../../components/ui/dialog';
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from '../../components/ui/table';
import { Textarea } from '../../components/ui/textarea';
import { toast } from 'sonner';
import { 
  Search, Plus, X, Truck, MapPin, Package, Calendar, 
  User, FileText, Scale, ChevronRight, Save, Edit2
} from 'lucide-react';

interface Fuhre {
  id: string;
  fuhren_nr: string;
  datum_abholung: string;
  datum_anlieferung: string;
  name_lieferant?: string;
  name_abnehmer?: string;
  artbez1_ek?: string;
  menge_vorgabe?: number;
  menge_aufladen?: number;
  menge_abladen?: number;
  einheit?: string;
  transportmittel?: string;
  transportkennzeichen?: string;
  status: string;
  [key: string]: unknown;
}

const STATUS_CONFIG: Record<string, { label: string; color: string }> = {
  OFFEN: { label: 'Offen', color: 'bg-blue-100 text-blue-800' },
  IN_TRANSPORT: { label: 'Im Transport', color: 'bg-yellow-100 text-yellow-800' },
  GELIEFERT: { label: 'Geliefert', color: 'bg-emerald-100 text-emerald-800' },
  ABGERECHNET: { label: 'Abgerechnet', color: 'bg-green-100 text-green-800' },
  STORNIERT: { label: 'Storniert', color: 'bg-red-100 text-red-800' },
};

const SIDEBAR_SECTIONS = [
  { id: 'stammdaten', label: 'Stammdaten', icon: FileText },
  { id: 'lieferant', label: 'Lieferant', icon: MapPin },
  { id: 'abnehmer', label: 'Abnehmer', icon: MapPin },
  { id: 'artikel', label: 'Artikel & Mengen', icon: Package },
  { id: 'transport', label: 'Transport', icon: Truck },
  { id: 'wiegekarten', label: 'Wiegekarten', icon: Scale },
  { id: 'bemerkungen', label: 'Bemerkungen', icon: FileText },
];

export default function FuhrenPage() {
  const [fuhren, setFuhren] = useState<Fuhre[]>([]);
  const [loading, setLoading] = useState(true);
  const [searchTerm, setSearchTerm] = useState('');
  const [filterStatus, setFilterStatus] = useState('');
  const [selectedFuhre, setSelectedFuhre] = useState<Fuhre | null>(null);
  const [isEditing, setIsEditing] = useState(false);
  const [editData, setEditData] = useState<Partial<Fuhre>>({});
  const [activeSection, setActiveSection] = useState('stammdaten');
  const [showCreateDialog, setShowCreateDialog] = useState(false);
  
  // Lookup data
  const [adressen, setAdressen] = useState<Array<{id: string; name1: string; ort?: string}>>([]);
  const [artikel, setArtikel] = useState<Array<{id: string; artbez1: string}>>([]);
  const [wiegekarten, setWiegekarten] = useState<Array<{id: string; wiegekarten_nr: string}>>([]);
  
  const [newFuhre, setNewFuhre] = useState({
    id_adresse_start: '',
    id_adresse_ziel: '',
    id_artikel: '',
    datum_abholung: new Date().toISOString().split('T')[0],
    datum_anlieferung: new Date().toISOString().split('T')[0],
    transportmittel: 'LKW',
    menge_vorgabe: 0,
    einheit: 'kg',
  });

  const loadFuhren = useCallback(async () => {
    try {
      setLoading(true);
      const params: Record<string, unknown> = {};
      if (searchTerm) params.suche = searchTerm;
      if (filterStatus && filterStatus !== 'ALL') params.status = filterStatus;
      
      const response = await fuhrenApi.list(params);
      setFuhren(response.data.data || []);
    } catch (error) {
      toast.error('Fehler beim Laden der Fuhren');
    } finally {
      setLoading(false);
    }
  }, [searchTerm, filterStatus]);

  const loadLookupData = useCallback(async () => {
    try {
      const [adressenRes, artikelRes, wiegekartenRes] = await Promise.all([
        adressenApi.list({ limit: 500 }),
        artikelApi.list({ limit: 500 }),
        wiegekartenApi.list({ limit: 500 }),
      ]);
      setAdressen(adressenRes.data.data || []);
      setArtikel(artikelRes.data.data || []);
      setWiegekarten(wiegekartenRes.data.data || []);
    } catch (error) {
      console.error('Fehler beim Laden der Lookup-Daten:', error);
    }
  }, []);

  useEffect(() => {
    loadFuhren();
    loadLookupData();
  }, [loadFuhren, loadLookupData]);

  const handleCreate = async () => {
    try {
      if (!newFuhre.id_adresse_start || !newFuhre.id_adresse_ziel || !newFuhre.id_artikel) {
        toast.error('Bitte alle Pflichtfelder ausfüllen');
        return;
      }
      await fuhrenApi.create(newFuhre);
      toast.success('Fuhre erstellt');
      setShowCreateDialog(false);
      setNewFuhre({
        id_adresse_start: '',
        id_adresse_ziel: '',
        id_artikel: '',
        datum_abholung: new Date().toISOString().split('T')[0],
        datum_anlieferung: new Date().toISOString().split('T')[0],
        transportmittel: 'LKW',
        menge_vorgabe: 0,
        einheit: 'kg',
      });
      loadFuhren();
    } catch (error) {
      toast.error('Fehler beim Erstellen');
    }
  };

  const handleSave = async () => {
    if (!selectedFuhre) return;
    try {
      await fuhrenApi.update(selectedFuhre.id, editData);
      toast.success('Änderungen gespeichert');
      setIsEditing(false);
      loadFuhren();
      // Reload selected
      const res = await fuhrenApi.getById(selectedFuhre.id);
      setSelectedFuhre(res.data.data);
    } catch (error) {
      toast.error('Fehler beim Speichern');
    }
  };

  const handleRowDoubleClick = async (fuhre: Fuhre) => {
    try {
      const res = await fuhrenApi.getById(fuhre.id);
      setSelectedFuhre(res.data.data);
      setEditData(res.data.data);
      setActiveSection('stammdaten');
    } catch (error) {
      toast.error('Fehler beim Laden der Fuhre');
    }
  };

  const formatDate = (dateStr?: string) => {
    if (!dateStr) return '-';
    return new Date(dateStr).toLocaleDateString('de-DE');
  };

  const formatMenge = (value?: number, einheit?: string) => {
    if (value === undefined || value === null) return '-';
    return `${value.toLocaleString('de-DE')} ${einheit || 'kg'}`;
  };

  return (
    <div className="h-full flex flex-col" data-testid="fuhren-page">
      {/* Header */}
      <div className="flex items-center justify-between mb-6">
        <div>
          <h1 className="text-2xl font-bold text-slate-800">Fuhren</h1>
          <p className="text-slate-500">Transportverwaltung</p>
        </div>
        <div className="flex items-center gap-3">
          <div className="relative">
            <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-slate-400" />
            <Input
              placeholder="Suchen..."
              className="pl-10 w-[250px]"
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
              data-testid="fuhren-search"
            />
          </div>
          <Select value={filterStatus || "ALL"} onValueChange={(v) => setFilterStatus(v === "ALL" ? "" : v)}>
            <SelectTrigger className="w-[150px]">
              <SelectValue placeholder="Alle Status" />
            </SelectTrigger>
            <SelectContent>
              <SelectItem value="ALL">Alle Status</SelectItem>
              {Object.entries(STATUS_CONFIG).map(([key, { label }]) => (
                <SelectItem key={key} value={key}>{label}</SelectItem>
              ))}
            </SelectContent>
          </Select>
          <Button onClick={() => setShowCreateDialog(true)} className="bg-emerald-600 hover:bg-emerald-700" data-testid="new-fuhre-btn">
            <Plus className="h-4 w-4 mr-2" />
            Neue Fuhre
          </Button>
        </div>
      </div>

      {/* Table */}
      <div className="bg-white rounded-xl shadow-sm border flex-1 overflow-hidden">
        <Table>
          <TableHeader>
            <TableRow className="bg-slate-50">
              <TableHead className="w-[100px]">Nr.</TableHead>
              <TableHead>Abholdatum</TableHead>
              <TableHead>Lieferant</TableHead>
              <TableHead>Abnehmer</TableHead>
              <TableHead>Artikel</TableHead>
              <TableHead className="text-right">Menge</TableHead>
              <TableHead>Kennzeichen</TableHead>
              <TableHead>Status</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {loading ? (
              <TableRow><TableCell colSpan={8} className="text-center py-8">Laden...</TableCell></TableRow>
            ) : fuhren.length === 0 ? (
              <TableRow><TableCell colSpan={8} className="text-center py-8 text-slate-500">Keine Fuhren gefunden</TableCell></TableRow>
            ) : (
              fuhren.map((fuhre) => (
                <TableRow 
                  key={fuhre.id} 
                  className="cursor-pointer hover:bg-slate-50"
                  onDoubleClick={() => handleRowDoubleClick(fuhre)}
                  data-testid={`fuhre-row-${fuhre.id}`}
                >
                  <TableCell className="font-medium">{fuhre.fuhren_nr}</TableCell>
                  <TableCell>{formatDate(fuhre.datum_abholung)}</TableCell>
                  <TableCell>{fuhre.name_lieferant || '-'}</TableCell>
                  <TableCell>{fuhre.name_abnehmer || '-'}</TableCell>
                  <TableCell>{fuhre.artbez1_ek || '-'}</TableCell>
                  <TableCell className="text-right">{formatMenge(fuhre.menge_abladen || fuhre.menge_aufladen, fuhre.einheit)}</TableCell>
                  <TableCell>{fuhre.transportkennzeichen || '-'}</TableCell>
                  <TableCell>
                    <Badge className={STATUS_CONFIG[fuhre.status]?.color || 'bg-slate-100'}>
                      {STATUS_CONFIG[fuhre.status]?.label || fuhre.status}
                    </Badge>
                  </TableCell>
                </TableRow>
              ))
            )}
          </TableBody>
        </Table>
      </div>

      {/* Detail Slide-In Panel */}
      {selectedFuhre && (
        <div className="fixed inset-y-0 right-0 w-[600px] bg-white shadow-2xl border-l z-50 flex flex-col" data-testid="fuhre-detail-panel">
          {/* Header */}
          <div className="flex items-center justify-between p-4 border-b bg-slate-50">
            <div>
              <h2 className="text-lg font-semibold">{selectedFuhre.fuhren_nr}</h2>
              <Badge className={STATUS_CONFIG[selectedFuhre.status]?.color}>
                {STATUS_CONFIG[selectedFuhre.status]?.label}
              </Badge>
            </div>
            <div className="flex items-center gap-2">
              {isEditing ? (
                <>
                  <Button variant="outline" size="sm" onClick={() => { setIsEditing(false); setEditData(selectedFuhre); }}>Abbrechen</Button>
                  <Button size="sm" onClick={handleSave} className="bg-emerald-600"><Save className="h-4 w-4 mr-1" />Speichern</Button>
                </>
              ) : (
                <Button variant="outline" size="sm" onClick={() => setIsEditing(true)}><Edit2 className="h-4 w-4 mr-1" />Bearbeiten</Button>
              )}
              <Button variant="ghost" size="icon" onClick={() => setSelectedFuhre(null)}><X className="h-5 w-5" /></Button>
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
              {activeSection === 'stammdaten' && (
                <div className="space-y-4">
                  <h3 className="font-medium text-slate-800 mb-4">Stammdaten</h3>
                  <div className="grid grid-cols-2 gap-4">
                    <div>
                      <Label>Fuhren-Nr.</Label>
                      <Input value={selectedFuhre.fuhren_nr} disabled />
                    </div>
                    <div>
                      <Label>Status</Label>
                      <Select 
                        disabled={!isEditing} 
                        value={editData.status || selectedFuhre.status}
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
                      <Label>Abholdatum</Label>
                      <Input 
                        type="date" 
                        disabled={!isEditing}
                        value={editData.datum_abholung || selectedFuhre.datum_abholung || ''}
                        onChange={(e) => setEditData({...editData, datum_abholung: e.target.value})}
                      />
                    </div>
                    <div>
                      <Label>Lieferdatum</Label>
                      <Input 
                        type="date" 
                        disabled={!isEditing}
                        value={editData.datum_anlieferung || selectedFuhre.datum_anlieferung || ''}
                        onChange={(e) => setEditData({...editData, datum_anlieferung: e.target.value})}
                      />
                    </div>
                  </div>
                </div>
              )}
              
              {activeSection === 'lieferant' && (
                <div className="space-y-4">
                  <h3 className="font-medium text-slate-800 mb-4">Lieferant (Start)</h3>
                  <div>
                    <Label>Lieferant</Label>
                    <Select 
                      disabled={!isEditing}
                      value={editData.id_adresse_start as string || selectedFuhre.id_adresse_start as string || ''}
                      onValueChange={(v) => setEditData({...editData, id_adresse_start: v})}
                    >
                      <SelectTrigger><SelectValue placeholder="Lieferant auswählen" /></SelectTrigger>
                      <SelectContent>
                        {adressen.map((a) => (
                          <SelectItem key={a.id} value={a.id}>{a.name1} {a.ort ? `(${a.ort})` : ''}</SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>
                  <div className="p-4 bg-slate-50 rounded-lg">
                    <p className="font-medium">{selectedFuhre.name_lieferant || '-'}</p>
                  </div>
                </div>
              )}
              
              {activeSection === 'abnehmer' && (
                <div className="space-y-4">
                  <h3 className="font-medium text-slate-800 mb-4">Abnehmer (Ziel)</h3>
                  <div>
                    <Label>Abnehmer</Label>
                    <Select 
                      disabled={!isEditing}
                      value={editData.id_adresse_ziel as string || selectedFuhre.id_adresse_ziel as string || ''}
                      onValueChange={(v) => setEditData({...editData, id_adresse_ziel: v})}
                    >
                      <SelectTrigger><SelectValue placeholder="Abnehmer auswählen" /></SelectTrigger>
                      <SelectContent>
                        {adressen.map((a) => (
                          <SelectItem key={a.id} value={a.id}>{a.name1} {a.ort ? `(${a.ort})` : ''}</SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>
                  <div className="p-4 bg-slate-50 rounded-lg">
                    <p className="font-medium">{selectedFuhre.name_abnehmer || '-'}</p>
                  </div>
                </div>
              )}
              
              {activeSection === 'artikel' && (
                <div className="space-y-4">
                  <h3 className="font-medium text-slate-800 mb-4">Artikel & Mengen</h3>
                  <div>
                    <Label>Artikel/Sorte</Label>
                    <Select 
                      disabled={!isEditing}
                      value={editData.id_artikel as string || selectedFuhre.id_artikel as string || ''}
                      onValueChange={(v) => setEditData({...editData, id_artikel: v})}
                    >
                      <SelectTrigger><SelectValue placeholder="Artikel auswählen" /></SelectTrigger>
                      <SelectContent>
                        {artikel.map((a) => (
                          <SelectItem key={a.id} value={a.id}>{a.artbez1}</SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                  </div>
                  <div className="grid grid-cols-2 gap-4">
                    <div>
                      <Label>Planmenge</Label>
                      <Input 
                        type="number" 
                        disabled={!isEditing}
                        value={editData.menge_vorgabe ?? selectedFuhre.menge_vorgabe ?? ''}
                        onChange={(e) => setEditData({...editData, menge_vorgabe: parseFloat(e.target.value)})}
                      />
                    </div>
                    <div>
                      <Label>Einheit</Label>
                      <Input 
                        disabled={!isEditing}
                        value={editData.einheit || selectedFuhre.einheit || 'kg'}
                        onChange={(e) => setEditData({...editData, einheit: e.target.value})}
                      />
                    </div>
                    <div>
                      <Label>Lademenge (Lieferant)</Label>
                      <Input 
                        type="number" 
                        disabled={!isEditing}
                        value={editData.menge_aufladen ?? selectedFuhre.menge_aufladen ?? ''}
                        onChange={(e) => setEditData({...editData, menge_aufladen: parseFloat(e.target.value)})}
                      />
                    </div>
                    <div>
                      <Label>Ablademenge (Abnehmer)</Label>
                      <Input 
                        type="number" 
                        disabled={!isEditing}
                        value={editData.menge_abladen ?? selectedFuhre.menge_abladen ?? ''}
                        onChange={(e) => setEditData({...editData, menge_abladen: parseFloat(e.target.value)})}
                      />
                    </div>
                  </div>
                </div>
              )}
              
              {activeSection === 'transport' && (
                <div className="space-y-4">
                  <h3 className="font-medium text-slate-800 mb-4">Transport</h3>
                  <div className="grid grid-cols-2 gap-4">
                    <div>
                      <Label>Transportmittel</Label>
                      <Select 
                        disabled={!isEditing}
                        value={editData.transportmittel || selectedFuhre.transportmittel || 'LKW'}
                        onValueChange={(v) => setEditData({...editData, transportmittel: v})}
                      >
                        <SelectTrigger><SelectValue /></SelectTrigger>
                        <SelectContent>
                          <SelectItem value="LKW">LKW</SelectItem>
                          <SelectItem value="PKW">PKW</SelectItem>
                          <SelectItem value="BAHN">Bahn</SelectItem>
                          <SelectItem value="SCHIFF">Schiff</SelectItem>
                          <SelectItem value="CONTAINER">Container</SelectItem>
                        </SelectContent>
                      </Select>
                    </div>
                    <div>
                      <Label>Kennzeichen</Label>
                      <Input 
                        disabled={!isEditing}
                        value={editData.transportkennzeichen || selectedFuhre.transportkennzeichen || ''}
                        onChange={(e) => setEditData({...editData, transportkennzeichen: e.target.value})}
                      />
                    </div>
                    <div className="col-span-2">
                      <Label>Fahrer</Label>
                      <Input 
                        disabled={!isEditing}
                        value={editData.fahrer_name || selectedFuhre.fahrer_name || ''}
                        onChange={(e) => setEditData({...editData, fahrer_name: e.target.value})}
                      />
                    </div>
                  </div>
                </div>
              )}
              
              {activeSection === 'wiegekarten' && (
                <div className="space-y-4">
                  <h3 className="font-medium text-slate-800 mb-4">Wiegekarten</h3>
                  <div className="grid grid-cols-2 gap-4">
                    <div>
                      <Label>Wiegekarte Start</Label>
                      <Select 
                        disabled={!isEditing}
                        value={editData.id_wiegekarte_start as string || selectedFuhre.id_wiegekarte_start as string || ''}
                        onValueChange={(v) => setEditData({...editData, id_wiegekarte_start: v})}
                      >
                        <SelectTrigger><SelectValue placeholder="Auswählen" /></SelectTrigger>
                        <SelectContent>
                          {wiegekarten.map((w) => (
                            <SelectItem key={w.id} value={w.id}>{w.wiegekarten_nr}</SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                    </div>
                    <div>
                      <Label>Wiegekarte Ziel</Label>
                      <Select 
                        disabled={!isEditing}
                        value={editData.id_wiegekarte_ziel as string || selectedFuhre.id_wiegekarte_ziel as string || ''}
                        onValueChange={(v) => setEditData({...editData, id_wiegekarte_ziel: v})}
                      >
                        <SelectTrigger><SelectValue placeholder="Auswählen" /></SelectTrigger>
                        <SelectContent>
                          {wiegekarten.map((w) => (
                            <SelectItem key={w.id} value={w.id}>{w.wiegekarten_nr}</SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                    </div>
                  </div>
                </div>
              )}
              
              {activeSection === 'bemerkungen' && (
                <div className="space-y-4">
                  <h3 className="font-medium text-slate-800 mb-4">Bemerkungen</h3>
                  <div>
                    <Label>Bemerkung</Label>
                    <Textarea 
                      rows={6}
                      disabled={!isEditing}
                      value={editData.bemerkung || selectedFuhre.bemerkung || ''}
                      onChange={(e) => setEditData({...editData, bemerkung: e.target.value})}
                    />
                  </div>
                </div>
              )}
            </div>
          </div>
        </div>
      )}

      {/* Create Dialog */}
      <Dialog open={showCreateDialog} onOpenChange={setShowCreateDialog}>
        <DialogContent className="max-w-lg">
          <DialogHeader>
            <DialogTitle>Neue Fuhre erstellen</DialogTitle>
          </DialogHeader>
          <div className="space-y-4">
            <div>
              <Label>Lieferant *</Label>
              <Select value={newFuhre.id_adresse_start} onValueChange={(v) => setNewFuhre({...newFuhre, id_adresse_start: v})}>
                <SelectTrigger><SelectValue placeholder="Lieferant auswählen" /></SelectTrigger>
                <SelectContent>
                  {adressen.map((a) => (
                    <SelectItem key={a.id} value={a.id}>{a.name1} {a.ort ? `(${a.ort})` : ''}</SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>
            <div>
              <Label>Abnehmer *</Label>
              <Select value={newFuhre.id_adresse_ziel} onValueChange={(v) => setNewFuhre({...newFuhre, id_adresse_ziel: v})}>
                <SelectTrigger><SelectValue placeholder="Abnehmer auswählen" /></SelectTrigger>
                <SelectContent>
                  {adressen.map((a) => (
                    <SelectItem key={a.id} value={a.id}>{a.name1} {a.ort ? `(${a.ort})` : ''}</SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>
            <div>
              <Label>Artikel *</Label>
              <Select value={newFuhre.id_artikel} onValueChange={(v) => setNewFuhre({...newFuhre, id_artikel: v})}>
                <SelectTrigger><SelectValue placeholder="Artikel auswählen" /></SelectTrigger>
                <SelectContent>
                  {artikel.map((a) => (
                    <SelectItem key={a.id} value={a.id}>{a.artbez1}</SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label>Abholdatum</Label>
                <Input type="date" value={newFuhre.datum_abholung} onChange={(e) => setNewFuhre({...newFuhre, datum_abholung: e.target.value})} />
              </div>
              <div>
                <Label>Lieferdatum</Label>
                <Input type="date" value={newFuhre.datum_anlieferung} onChange={(e) => setNewFuhre({...newFuhre, datum_anlieferung: e.target.value})} />
              </div>
            </div>
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label>Planmenge</Label>
                <Input type="number" value={newFuhre.menge_vorgabe} onChange={(e) => setNewFuhre({...newFuhre, menge_vorgabe: parseFloat(e.target.value)})} />
              </div>
              <div>
                <Label>Transportmittel</Label>
                <Select value={newFuhre.transportmittel} onValueChange={(v) => setNewFuhre({...newFuhre, transportmittel: v})}>
                  <SelectTrigger><SelectValue /></SelectTrigger>
                  <SelectContent>
                    <SelectItem value="LKW">LKW</SelectItem>
                    <SelectItem value="PKW">PKW</SelectItem>
                    <SelectItem value="BAHN">Bahn</SelectItem>
                    <SelectItem value="SCHIFF">Schiff</SelectItem>
                  </SelectContent>
                </Select>
              </div>
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setShowCreateDialog(false)}>Abbrechen</Button>
            <Button onClick={handleCreate} className="bg-emerald-600 hover:bg-emerald-700">Erstellen</Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
