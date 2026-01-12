/**
 * AnsprechpartnerSection - Moderne Ansprechpartner-Verwaltung
 * 
 * Features:
 * - Kompakte Tabellen/Grid-Ansicht mit vollständigen Namen
 * - Wildcard-Suche
 * - Doppelklick öffnet Detail-Dialog (auch ohne Bearbeiten-Modus)
 * - Modernes, aufgeräumtes Design
 * - Adressfelder und Korrespondenzsprache
 */
import { useState, useMemo } from 'react';
import { 
  Search, Plus, Pencil, Trash2, UserCircle, Phone, Mail, 
  Building2, Star, Eye, X, MapPin, Globe, Smartphone, Copy
} from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Badge } from '@/components/ui/badge';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from '@/components/ui/dialog';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Switch } from '@/components/ui/switch';
import { Textarea } from '@/components/ui/textarea';
import { SmartInput } from '@/components/ui/smart-input';
import { cn } from '@/lib/utils';
import { toast } from 'sonner';
import { api } from '@/services/api/client';
import * as Flags from 'country-flag-icons/react/3x2';

// OECD Länder mit Korrespondenzsprachen
const KORRESPONDENZSPRACHEN = [
  { code: 'DE', sprache: 'Deutsch', land: 'Deutschland' },
  { code: 'GB', sprache: 'Englisch', land: 'Vereinigtes Königreich' },
  { code: 'FR', sprache: 'Französisch', land: 'Frankreich' },
  { code: 'ES', sprache: 'Spanisch', land: 'Spanien' },
  { code: 'IT', sprache: 'Italienisch', land: 'Italien' },
  { code: 'NL', sprache: 'Niederländisch', land: 'Niederlande' },
  { code: 'PL', sprache: 'Polnisch', land: 'Polen' },
  { code: 'PT', sprache: 'Portugiesisch', land: 'Portugal' },
  { code: 'CZ', sprache: 'Tschechisch', land: 'Tschechien' },
  { code: 'HU', sprache: 'Ungarisch', land: 'Ungarn' },
  { code: 'SK', sprache: 'Slowakisch', land: 'Slowakei' },
  { code: 'AT', sprache: 'Deutsch (Österreich)', land: 'Österreich' },
  { code: 'CH', sprache: 'Deutsch (Schweiz)', land: 'Schweiz' },
  { code: 'BE', sprache: 'Französisch/Niederländisch', land: 'Belgien' },
  { code: 'DK', sprache: 'Dänisch', land: 'Dänemark' },
  { code: 'SE', sprache: 'Schwedisch', land: 'Schweden' },
  { code: 'NO', sprache: 'Norwegisch', land: 'Norwegen' },
  { code: 'FI', sprache: 'Finnisch', land: 'Finnland' },
  { code: 'JP', sprache: 'Japanisch', land: 'Japan' },
  { code: 'KR', sprache: 'Koreanisch', land: 'Südkorea' },
  { code: 'US', sprache: 'Englisch (US)', land: 'USA' },
  { code: 'CA', sprache: 'Englisch/Französisch', land: 'Kanada' },
  { code: 'AU', sprache: 'Englisch', land: 'Australien' },
  { code: 'NZ', sprache: 'Englisch', land: 'Neuseeland' },
  { code: 'MX', sprache: 'Spanisch', land: 'Mexiko' },
  { code: 'TR', sprache: 'Türkisch', land: 'Türkei' },
  { code: 'IL', sprache: 'Hebräisch', land: 'Israel' },
  { code: 'GR', sprache: 'Griechisch', land: 'Griechenland' },
];

// Flaggen-Komponente
const LanguageFlag = ({ code, className = "w-5 h-3.5" }: { code: string; className?: string }) => {
  const FlagComponent = (Flags as Record<string, React.FC<{ className?: string }>>)[code];
  if (!FlagComponent) {
    return <span className="inline-block w-5 h-3.5 bg-gray-200 rounded text-[8px] text-center">{code}</span>;
  }
  return <FlagComponent className={cn("inline-block rounded shadow-sm", className)} />;
};

export interface Ansprechpartner {
  id: string;
  anrede?: string;
  vorname?: string;
  nachname?: string;
  funktion?: string;
  abteilung?: string;
  telefon?: string;
  mobil?: string;
  fax?: string;
  email?: string;
  strasse?: string;
  plz?: string;
  ort?: string;
  korrespondenzsprache?: string;
  notizen?: string;
  profilbild?: string;
  ist_hauptkontakt?: boolean;
  aktiv?: boolean;
}

interface AnsprechpartnerSectionProps {
  adresseId: string;
  ansprechpartner: Ansprechpartner[];
  isEditing: boolean;
  onUpdate: (list: Ansprechpartner[]) => void;
}

const emptyAnsprechpartner: Omit<Ansprechpartner, 'id'> = {
  anrede: '',
  vorname: '',
  nachname: '',
  funktion: '',
  abteilung: '',
  telefon: '',
  mobil: '',
  fax: '',
  email: '',
  strasse: '',
  plz: '',
  ort: '',
  korrespondenzsprache: 'Deutsch',
  notizen: '',
  ist_hauptkontakt: false,
  aktiv: true,
};

export function AnsprechpartnerSection({
  adresseId,
  ansprechpartner,
  isEditing,
  onUpdate,
}: AnsprechpartnerSectionProps) {
  const [searchTerm, setSearchTerm] = useState('');
  const [showDialog, setShowDialog] = useState(false);
  const [dialogMode, setDialogMode] = useState<'view' | 'edit' | 'create'>('view');
  const [selectedAp, setSelectedAp] = useState<Ansprechpartner | null>(null);
  const [formData, setFormData] = useState<Partial<Ansprechpartner>>(emptyAnsprechpartner);
  const [isSaving, setIsSaving] = useState(false);

  const filteredList = useMemo(() => {
    if (!searchTerm.trim()) return ansprechpartner;
    const pattern = searchTerm.toLowerCase().replace(/\*/g, '.*').replace(/\?/g, '.');
    const regex = new RegExp(pattern, 'i');
    return ansprechpartner.filter(ap => {
      const searchString = [ap.vorname, ap.nachname, ap.funktion, ap.abteilung, ap.email, ap.telefon, ap.mobil]
        .filter(Boolean).join(' ').toLowerCase();
      return regex.test(searchString);
    });
  }, [ansprechpartner, searchTerm]);

  const handleDoubleClick = (ap: Ansprechpartner) => {
    setSelectedAp(ap);
    setFormData(ap);
    setDialogMode('view');
    setShowDialog(true);
  };

  const handleEdit = (ap: Ansprechpartner) => {
    setSelectedAp(ap);
    setFormData(ap);
    setDialogMode('edit');
    setShowDialog(true);
  };

  const handleCreate = () => {
    setSelectedAp(null);
    setFormData(emptyAnsprechpartner);
    setDialogMode('create');
    setShowDialog(true);
  };

  const handleSave = async () => {
    if (!formData.nachname?.trim()) {
      toast.error('Nachname ist erforderlich');
      return;
    }
    setIsSaving(true);
    try {
      if (dialogMode === 'create') {
        const response = await api.post(`/adressen/${adresseId}/ansprechpartner`, formData);
        onUpdate([...ansprechpartner, response.data.data]);
        toast.success('Ansprechpartner erstellt');
      } else {
        await api.put(`/adressen/${adresseId}/ansprechpartner/${selectedAp?.id}`, formData);
        onUpdate(ansprechpartner.map(a => a.id === selectedAp?.id ? { ...a, ...formData } : a));
        toast.success('Ansprechpartner aktualisiert');
      }
      setShowDialog(false);
    } catch (error) {
      toast.error('Fehler beim Speichern');
    } finally {
      setIsSaving(false);
    }
  };

  const handleDelete = async (apId: string) => {
    if (!confirm('Ansprechpartner wirklich löschen?')) return;
    try {
      await api.delete(`/adressen/${adresseId}/ansprechpartner/${apId}`);
      onUpdate(ansprechpartner.filter(a => a.id !== apId));
      toast.success('Ansprechpartner gelöscht');
    } catch (error) {
      toast.error('Fehler beim Löschen');
    }
  };

  const setAsMainContact = async (apId: string) => {
    try {
      const updated = ansprechpartner.map(a => ({ ...a, ist_hauptkontakt: a.id === apId }));
      await api.put(`/adressen/${adresseId}/ansprechpartner/${apId}`, {
        ...ansprechpartner.find(a => a.id === apId),
        ist_hauptkontakt: true,
      });
      onUpdate(updated);
      toast.success('Hauptkontakt festgelegt');
    } catch (error) {
      toast.error('Fehler beim Setzen des Hauptkontakts');
    }
  };

  const getSpracheFlagCode = (sprache?: string) => {
    const entry = KORRESPONDENZSPRACHEN.find(s => s.sprache === sprache);
    return entry?.code || 'DE';
  };

  // Copy to clipboard helper
  const copyToClipboard = (text: string, label: string) => {
    navigator.clipboard.writeText(text);
    toast.success(`${label} kopiert`);
  };

  return (
    <div className="space-y-4">
      {/* Header */}
      <div className="flex items-center gap-3">
        <div className="relative flex-1">
          <Search className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-gray-400" />
          <Input
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            placeholder="Suchen... (Wildcard: * = beliebig)"
            className="pl-9 h-9 text-sm"
          />
          {searchTerm && (
            <Button variant="ghost" size="icon" className="absolute right-1 top-1/2 -translate-y-1/2 h-6 w-6"
              onClick={() => setSearchTerm('')}>
              <X className="h-3 w-3" />
            </Button>
          )}
        </div>
        {isEditing && (
          <Button size="sm" onClick={handleCreate}>
            <Plus className="h-4 w-4 mr-1" />Neu
          </Button>
        )}
      </div>

      <div className="flex items-center justify-between text-xs text-gray-500">
        <span>{filteredList.length} von {ansprechpartner.length} Ansprechpartner</span>
        <span className="text-gray-400">Doppelklick für Details</span>
      </div>

      {/* Table */}
      {filteredList.length === 0 ? (
        <div className="text-center py-8 bg-gray-50 rounded-lg border-2 border-dashed border-gray-200">
          <UserCircle className="h-10 w-10 text-gray-300 mx-auto mb-2" />
          <p className="text-sm text-gray-500">
            {searchTerm ? 'Keine Treffer gefunden' : 'Keine Ansprechpartner vorhanden'}
          </p>
          {isEditing && !searchTerm && (
            <Button variant="link" size="sm" className="mt-2" onClick={handleCreate}>
              Ersten Ansprechpartner hinzufügen
            </Button>
          )}
        </div>
      ) : (
        <div className="border rounded-lg overflow-hidden bg-white">
          <div className="grid grid-cols-[40px_minmax(180px,2fr)_minmax(120px,1fr)_minmax(100px,1fr)_minmax(100px,1fr)_80px] gap-3 px-3 py-2 bg-gray-50 border-b text-xs font-medium text-gray-500 uppercase tracking-wider">
            <div></div>
            <div>Name / Funktion</div>
            <div>E-Mail</div>
            <div>Telefon</div>
            <div>Abteilung</div>
            <div className="text-right">Aktionen</div>
          </div>
          <div className="divide-y">
            {filteredList.map((ap) => (
              <div key={ap.id} data-testid={`ansprechpartner-row-${ap.id}`}
                className={cn(
                  "grid grid-cols-[40px_minmax(180px,2fr)_minmax(120px,1fr)_minmax(100px,1fr)_minmax(100px,1fr)_80px] gap-3 px-3 py-3 items-center",
                  "hover:bg-emerald-50/50 cursor-pointer transition-colors group",
                  ap.ist_hauptkontakt && "bg-amber-50/50"
                )}
                onDoubleClick={() => handleDoubleClick(ap)}>
                <div>
                  {ap.profilbild ? (
                    <img src={ap.profilbild} alt="" className="h-9 w-9 rounded-full object-cover border border-gray-200" />
                  ) : (
                    <div className="h-9 w-9 rounded-full bg-gradient-to-br from-emerald-400 to-emerald-600 flex items-center justify-center text-white text-xs font-medium">
                      {ap.vorname?.[0]}{ap.nachname?.[0]}
                    </div>
                  )}
                </div>
                <div className="min-w-0">
                  <div className="flex items-center gap-2 flex-wrap">
                    <span className="font-medium text-gray-900">
                      {ap.anrede && <span className="text-gray-500 font-normal">{ap.anrede} </span>}
                      {ap.vorname} {ap.nachname}
                    </span>
                    {ap.ist_hauptkontakt && <Star className="h-3.5 w-3.5 text-amber-500 fill-amber-500 flex-shrink-0" />}
                  </div>
                  {ap.funktion && <p className="text-xs text-gray-500 mt-0.5">{ap.funktion}</p>}
                </div>
                <div className="min-w-0">
                  {ap.email ? (
                    <a href={`mailto:${ap.email}`} className="text-emerald-600 hover:underline text-sm truncate block"
                      onClick={(e) => e.stopPropagation()} title={ap.email}>{ap.email}</a>
                  ) : <span className="text-gray-400 text-sm">-</span>}
                </div>
                <div className="min-w-0">
                  {ap.telefon ? (
                    <a href={`tel:${ap.telefon}`} className="text-gray-700 hover:text-emerald-600 text-sm truncate block"
                      onClick={(e) => e.stopPropagation()} title={ap.telefon}>{ap.telefon}</a>
                  ) : ap.mobil ? (
                    <a href={`tel:${ap.mobil}`} className="text-gray-700 hover:text-emerald-600 text-sm truncate block"
                      onClick={(e) => e.stopPropagation()} title={ap.mobil}>{ap.mobil}</a>
                  ) : <span className="text-gray-400 text-sm">-</span>}
                </div>
                <div className="min-w-0">
                  {ap.abteilung ? (
                    <Badge variant="outline" className="text-xs font-normal truncate">{ap.abteilung}</Badge>
                  ) : <span className="text-gray-400 text-sm">-</span>}
                </div>
                <div className="flex justify-end gap-0.5 opacity-0 group-hover:opacity-100 transition-opacity">
                  <Button variant="ghost" size="icon" className="h-7 w-7"
                    onClick={(e) => { e.stopPropagation(); handleDoubleClick(ap); }} title="Details anzeigen">
                    <Eye className="h-3.5 w-3.5" />
                  </Button>
                  {isEditing && (
                    <>
                      <Button variant="ghost" size="icon" className="h-7 w-7"
                        onClick={(e) => { e.stopPropagation(); handleEdit(ap); }} title="Bearbeiten">
                        <Pencil className="h-3.5 w-3.5" />
                      </Button>
                      {!ap.ist_hauptkontakt && (
                        <Button variant="ghost" size="icon" className="h-7 w-7 text-amber-500 hover:text-amber-600"
                          onClick={(e) => { e.stopPropagation(); setAsMainContact(ap.id); }} title="Als Hauptkontakt setzen">
                          <Star className="h-3.5 w-3.5" />
                        </Button>
                      )}
                      <Button variant="ghost" size="icon" className="h-7 w-7 text-red-500 hover:text-red-600"
                        onClick={(e) => { e.stopPropagation(); handleDelete(ap.id); }} title="Löschen">
                        <Trash2 className="h-3.5 w-3.5" />
                      </Button>
                    </>
                  )}
                </div>
              </div>
            ))}
          </div>
        </div>
      )}

      {/* CLEAN Detail Dialog - Modern Card Layout */}
      <Dialog open={showDialog} onOpenChange={setShowDialog}>
        <DialogContent className={cn(
          "p-0 gap-0 overflow-hidden",
          dialogMode === 'view' ? "max-w-md" : "max-w-2xl max-h-[90vh] overflow-y-auto"
        )}>
          {dialogMode === 'view' ? (
            /* ==================== CLEAN VIEW MODE ==================== */
            <div className="flex flex-col">
              {/* Header Card - Gradient Background */}
              <div className="bg-gradient-to-br from-emerald-500 to-emerald-600 px-6 py-5 text-white">
                <div className="flex items-start gap-4">
                  {/* Avatar */}
                  {formData.profilbild ? (
                    <img src={formData.profilbild} alt=""
                      className="h-16 w-16 rounded-full object-cover border-2 border-white/30 shadow-lg" />
                  ) : (
                    <div className="h-16 w-16 rounded-full bg-white/20 backdrop-blur-sm flex items-center justify-center text-white text-xl font-semibold border-2 border-white/30">
                      {formData.vorname?.[0]}{formData.nachname?.[0]}
                    </div>
                  )}
                  {/* Name & Title */}
                  <div className="flex-1 min-w-0">
                    <h2 className="text-xl font-semibold truncate">
                      {formData.anrede} {formData.vorname} {formData.nachname}
                    </h2>
                    {formData.funktion && (
                      <p className="text-emerald-100 text-sm mt-0.5">{formData.funktion}</p>
                    )}
                    {formData.abteilung && (
                      <p className="text-emerald-200/80 text-xs mt-1">{formData.abteilung}</p>
                    )}
                    {/* Badges */}
                    <div className="flex items-center gap-2 mt-3">
                      {formData.ist_hauptkontakt && (
                        <span className="inline-flex items-center gap-1 px-2 py-0.5 rounded-full bg-amber-400 text-amber-900 text-xs font-medium">
                          <Star className="h-3 w-3 fill-current" />
                          Hauptkontakt
                        </span>
                      )}
                      {formData.korrespondenzsprache && (
                        <span className="inline-flex items-center gap-1.5 px-2 py-0.5 rounded-full bg-white/20 text-white text-xs">
                          <LanguageFlag code={getSpracheFlagCode(formData.korrespondenzsprache)} className="w-4 h-3" />
                          {formData.korrespondenzsprache}
                        </span>
                      )}
                    </div>
                  </div>
                </div>
              </div>

              {/* Content - Clean List */}
              <div className="px-6 py-4 space-y-1">
                {/* Contact Info - Clean clickable rows */}
                {formData.email && (
                  <a href={`mailto:${formData.email}`}
                    className="flex items-center gap-3 py-2.5 px-3 -mx-3 rounded-lg hover:bg-gray-50 transition-colors group">
                    <div className="h-9 w-9 rounded-full bg-emerald-50 flex items-center justify-center flex-shrink-0">
                      <Mail className="h-4 w-4 text-emerald-600" />
                    </div>
                    <div className="flex-1 min-w-0">
                      <p className="text-sm font-medium text-gray-900 truncate">{formData.email}</p>
                      <p className="text-xs text-gray-500">E-Mail</p>
                    </div>
                    <Copy className="h-4 w-4 text-gray-400 opacity-0 group-hover:opacity-100 transition-opacity cursor-pointer"
                      onClick={(e) => { e.preventDefault(); e.stopPropagation(); copyToClipboard(formData.email!, 'E-Mail'); }} />
                  </a>
                )}

                {formData.telefon && (
                  <a href={`tel:${formData.telefon}`}
                    className="flex items-center gap-3 py-2.5 px-3 -mx-3 rounded-lg hover:bg-gray-50 transition-colors group">
                    <div className="h-9 w-9 rounded-full bg-blue-50 flex items-center justify-center flex-shrink-0">
                      <Phone className="h-4 w-4 text-blue-600" />
                    </div>
                    <div className="flex-1 min-w-0">
                      <p className="text-sm font-medium text-gray-900">{formData.telefon}</p>
                      <p className="text-xs text-gray-500">Telefon</p>
                    </div>
                    <Copy className="h-4 w-4 text-gray-400 opacity-0 group-hover:opacity-100 transition-opacity cursor-pointer"
                      onClick={(e) => { e.preventDefault(); e.stopPropagation(); copyToClipboard(formData.telefon!, 'Telefon'); }} />
                  </a>
                )}

                {formData.mobil && (
                  <a href={`tel:${formData.mobil}`}
                    className="flex items-center gap-3 py-2.5 px-3 -mx-3 rounded-lg hover:bg-gray-50 transition-colors group">
                    <div className="h-9 w-9 rounded-full bg-purple-50 flex items-center justify-center flex-shrink-0">
                      <Smartphone className="h-4 w-4 text-purple-600" />
                    </div>
                    <div className="flex-1 min-w-0">
                      <p className="text-sm font-medium text-gray-900">{formData.mobil}</p>
                      <p className="text-xs text-gray-500">Mobil</p>
                    </div>
                    <Copy className="h-4 w-4 text-gray-400 opacity-0 group-hover:opacity-100 transition-opacity cursor-pointer"
                      onClick={(e) => { e.preventDefault(); e.stopPropagation(); copyToClipboard(formData.mobil!, 'Mobil'); }} />
                  </a>
                )}

                {/* Address - Only show if exists */}
                {(formData.strasse || formData.plz || formData.ort) && (
                  <div className="flex items-start gap-3 py-2.5 px-3 -mx-3 rounded-lg">
                    <div className="h-9 w-9 rounded-full bg-orange-50 flex items-center justify-center flex-shrink-0 mt-0.5">
                      <MapPin className="h-4 w-4 text-orange-600" />
                    </div>
                    <div className="flex-1">
                      <p className="text-sm font-medium text-gray-900">
                        {formData.strasse}
                      </p>
                      <p className="text-sm text-gray-700">
                        {formData.plz} {formData.ort}
                      </p>
                      <p className="text-xs text-gray-500 mt-0.5">Adresse (abweichend)</p>
                    </div>
                  </div>
                )}

                {/* Notes - Only show if exists */}
                {formData.notizen && (
                  <div className="pt-3 mt-2 border-t">
                    <p className="text-xs text-gray-500 mb-1">Notizen</p>
                    <p className="text-sm text-gray-700 bg-gray-50 rounded-lg p-3">{formData.notizen}</p>
                  </div>
                )}
              </div>

              {/* Footer */}
              <div className="px-6 py-4 bg-gray-50 border-t flex justify-end gap-2">
                <Button variant="outline" onClick={() => setShowDialog(false)}>
                  Schließen
                </Button>
                {isEditing && (
                  <Button onClick={() => setDialogMode('edit')}>
                    <Pencil className="h-4 w-4 mr-1" />
                    Bearbeiten
                  </Button>
                )}
              </div>
            </div>
          ) : (
            /* ==================== EDIT/CREATE MODE ==================== */
            <>
              <DialogHeader className="px-6 pt-6 pb-4">
                <DialogTitle className="flex items-center gap-2">
                  {dialogMode === 'edit' && <Pencil className="h-5 w-5 text-blue-500" />}
                  {dialogMode === 'create' && <Plus className="h-5 w-5 text-emerald-500" />}
                  {dialogMode === 'edit' && 'Ansprechpartner bearbeiten'}
                  {dialogMode === 'create' && 'Neuer Ansprechpartner'}
                </DialogTitle>
              </DialogHeader>

              <div className="px-6 pb-6 space-y-5">
                {/* Personal Data */}
                <div className="space-y-3">
                  <h4 className="text-sm font-semibold text-gray-700 flex items-center gap-2">
                    <UserCircle className="h-4 w-4 text-emerald-500" />
                    Persönliche Daten
                  </h4>
                  <div className="grid grid-cols-4 gap-3">
                    <div>
                      <Label className="text-xs text-gray-500">Anrede</Label>
                      <SmartInput module="adressen" fieldName="ansprechpartner.anrede"
                        value={formData.anrede || ''} onChange={(val) => setFormData({ ...formData, anrede: val || '' })}
                        placeholder="Herr/Frau" />
                    </div>
                    <div>
                      <Label className="text-xs text-gray-500">Vorname</Label>
                      <Input value={formData.vorname || ''} onChange={(e) => setFormData({ ...formData, vorname: e.target.value })} />
                    </div>
                    <div>
                      <Label className="text-xs text-gray-500">Nachname *</Label>
                      <Input value={formData.nachname || ''} onChange={(e) => setFormData({ ...formData, nachname: e.target.value })} required />
                    </div>
                    <div>
                      <Label className="text-xs text-gray-500 flex items-center gap-1">
                        <Globe className="h-3 w-3" />Sprache
                      </Label>
                      <Select value={formData.korrespondenzsprache || 'Deutsch'}
                        onValueChange={(val) => setFormData({ ...formData, korrespondenzsprache: val })}>
                        <SelectTrigger>
                          <SelectValue>
                            <div className="flex items-center gap-2">
                              <LanguageFlag code={getSpracheFlagCode(formData.korrespondenzsprache)} />
                              <span>{formData.korrespondenzsprache || 'Deutsch'}</span>
                            </div>
                          </SelectValue>
                        </SelectTrigger>
                        <SelectContent className="max-h-[300px]">
                          {KORRESPONDENZSPRACHEN.map((s) => (
                            <SelectItem key={s.code} value={s.sprache}>
                              <div className="flex items-center gap-2">
                                <LanguageFlag code={s.code} />
                                <span>{s.sprache}</span>
                                <span className="text-gray-400 text-xs">({s.land})</span>
                              </div>
                            </SelectItem>
                          ))}
                        </SelectContent>
                      </Select>
                    </div>
                  </div>
                </div>

                {/* Function & Department */}
                <div className="space-y-3">
                  <h4 className="text-sm font-semibold text-gray-700 flex items-center gap-2">
                    <Building2 className="h-4 w-4 text-emerald-500" />
                    Funktion & Abteilung
                  </h4>
                  <div className="grid grid-cols-2 gap-3">
                    <div>
                      <Label className="text-xs text-gray-500">Funktion</Label>
                      <SmartInput module="adressen" fieldName="ansprechpartner.funktion"
                        value={formData.funktion || ''} onChange={(val) => setFormData({ ...formData, funktion: val || '' })}
                        placeholder="z.B. Geschäftsführer" />
                    </div>
                    <div>
                      <Label className="text-xs text-gray-500">Abteilung</Label>
                      <SmartInput module="adressen" fieldName="ansprechpartner.abteilung"
                        value={formData.abteilung || ''} onChange={(val) => setFormData({ ...formData, abteilung: val || '' })}
                        placeholder="z.B. Einkauf" />
                    </div>
                  </div>
                </div>

                {/* Contact */}
                <div className="space-y-3">
                  <h4 className="text-sm font-semibold text-gray-700 flex items-center gap-2">
                    <Phone className="h-4 w-4 text-emerald-500" />
                    Kontaktdaten
                  </h4>
                  <div className="grid grid-cols-3 gap-3">
                    <div>
                      <Label className="text-xs text-gray-500">Telefon</Label>
                      <Input value={formData.telefon || ''} onChange={(e) => setFormData({ ...formData, telefon: e.target.value })} type="tel" />
                    </div>
                    <div>
                      <Label className="text-xs text-gray-500">Mobil</Label>
                      <Input value={formData.mobil || ''} onChange={(e) => setFormData({ ...formData, mobil: e.target.value })} type="tel" />
                    </div>
                    <div>
                      <Label className="text-xs text-gray-500">Fax</Label>
                      <Input value={formData.fax || ''} onChange={(e) => setFormData({ ...formData, fax: e.target.value })} type="tel" />
                    </div>
                  </div>
                  <div>
                    <Label className="text-xs text-gray-500">E-Mail</Label>
                    <Input value={formData.email || ''} onChange={(e) => setFormData({ ...formData, email: e.target.value })} type="email" />
                  </div>
                </div>

                {/* Address */}
                <div className="space-y-3">
                  <h4 className="text-sm font-semibold text-gray-700 flex items-center gap-2">
                    <MapPin className="h-4 w-4 text-emerald-500" />
                    Adresse <span className="text-xs font-normal text-gray-400">(falls abweichend)</span>
                  </h4>
                  <div className="grid grid-cols-6 gap-3">
                    <div className="col-span-4">
                      <Label className="text-xs text-gray-500">Straße</Label>
                      <Input value={formData.strasse || ''} onChange={(e) => setFormData({ ...formData, strasse: e.target.value })} placeholder="Straße und Hausnummer" />
                    </div>
                    <div>
                      <Label className="text-xs text-gray-500">PLZ</Label>
                      <Input value={formData.plz || ''} onChange={(e) => setFormData({ ...formData, plz: e.target.value })} placeholder="PLZ" />
                    </div>
                    <div>
                      <Label className="text-xs text-gray-500">Ort</Label>
                      <Input value={formData.ort || ''} onChange={(e) => setFormData({ ...formData, ort: e.target.value })} placeholder="Ort" />
                    </div>
                  </div>
                </div>

                {/* Notes */}
                <div className="space-y-3">
                  <h4 className="text-sm font-semibold text-gray-700">Notizen</h4>
                  <Textarea value={formData.notizen || ''} onChange={(e) => setFormData({ ...formData, notizen: e.target.value })}
                    rows={2} placeholder="Interne Notizen zu diesem Ansprechpartner..." />
                </div>

                {/* Switches */}
                <div className="flex items-center gap-6 pt-2 border-t">
                  <div className="flex items-center gap-2">
                    <Switch checked={formData.ist_hauptkontakt || false}
                      onCheckedChange={(v) => setFormData({ ...formData, ist_hauptkontakt: v })} />
                    <Label className="text-sm">Hauptkontakt</Label>
                  </div>
                  <div className="flex items-center gap-2">
                    <Switch checked={formData.aktiv !== false}
                      onCheckedChange={(v) => setFormData({ ...formData, aktiv: v })} />
                    <Label className="text-sm">Aktiv</Label>
                  </div>
                </div>
              </div>

              <DialogFooter className="px-6 py-4 bg-gray-50 border-t">
                <Button variant="outline" onClick={() => setShowDialog(false)}>Abbrechen</Button>
                <Button onClick={handleSave} disabled={isSaving}>
                  {isSaving ? 'Speichert...' : 'Speichern'}
                </Button>
              </DialogFooter>
            </>
          )}
        </DialogContent>
      </Dialog>
    </div>
  );
}
