/**
 * AnsprechpartnerSection - Moderne Ansprechpartner-Verwaltung
 * 
 * Features:
 * - Kompakte Tabellen/Grid-Ansicht
 * - Wildcard-Suche
 * - Doppelklick öffnet Detail-Dialog (auch ohne Bearbeiten-Modus)
 * - Modernes Design mit Hover-Effekten
 */
import { useState, useMemo } from 'react';
import { 
  Search, Plus, Pencil, Trash2, UserCircle, Phone, Mail, 
  Building2, Star, Eye, X, ChevronRight, Filter
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

// Leerer Ansprechpartner für neue Einträge
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

  // Wildcard-Suche (unterstützt * als Wildcard)
  const filteredList = useMemo(() => {
    if (!searchTerm.trim()) return ansprechpartner;
    
    const pattern = searchTerm
      .toLowerCase()
      .replace(/\*/g, '.*')
      .replace(/\?/g, '.');
    
    const regex = new RegExp(pattern, 'i');
    
    return ansprechpartner.filter(ap => {
      const searchString = [
        ap.vorname,
        ap.nachname,
        ap.funktion,
        ap.abteilung,
        ap.email,
        ap.telefon,
        ap.mobil,
      ].filter(Boolean).join(' ').toLowerCase();
      
      return regex.test(searchString);
    });
  }, [ansprechpartner, searchTerm]);

  // Doppelklick Handler - öffnet Detail-Ansicht
  const handleDoubleClick = (ap: Ansprechpartner) => {
    setSelectedAp(ap);
    setFormData(ap);
    setDialogMode('view');
    setShowDialog(true);
  };

  // Bearbeiten Handler
  const handleEdit = (ap: Ansprechpartner) => {
    setSelectedAp(ap);
    setFormData(ap);
    setDialogMode('edit');
    setShowDialog(true);
  };

  // Neu erstellen Handler
  const handleCreate = () => {
    setSelectedAp(null);
    setFormData(emptyAnsprechpartner);
    setDialogMode('create');
    setShowDialog(true);
  };

  // Speichern
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

  // Löschen
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

  // Hauptkontakt setzen
  const setAsMainContact = async (apId: string) => {
    try {
      const updated = ansprechpartner.map(a => ({
        ...a,
        ist_hauptkontakt: a.id === apId,
      }));
      
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

  return (
    <div className="space-y-4">
      {/* Header mit Suche und Hinzufügen */}
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
            <Button
              variant="ghost"
              size="icon"
              className="absolute right-1 top-1/2 -translate-y-1/2 h-6 w-6"
              onClick={() => setSearchTerm('')}
            >
              <X className="h-3 w-3" />
            </Button>
          )}
        </div>
        {isEditing && (
          <Button size="sm" onClick={handleCreate}>
            <Plus className="h-4 w-4 mr-1" />
            Neu
          </Button>
        )}
      </div>

      {/* Kompakte Info-Zeile */}
      <div className="flex items-center justify-between text-xs text-gray-500">
        <span>{filteredList.length} von {ansprechpartner.length} Ansprechpartner</span>
        <span className="text-gray-400">Doppelklick für Details</span>
      </div>

      {/* Tabellen-Grid Ansicht */}
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
          {/* Table Header */}
          <div className="grid grid-cols-[auto_1fr_1fr_1fr_auto] gap-2 px-3 py-2 bg-gray-50 border-b text-xs font-medium text-gray-500 uppercase tracking-wider">
            <div className="w-8"></div>
            <div>Name / Funktion</div>
            <div>Kontakt</div>
            <div>Abteilung</div>
            <div className="w-20 text-right">Aktionen</div>
          </div>
          
          {/* Table Body */}
          <div className="divide-y">
            {filteredList.map((ap) => (
              <div
                key={ap.id}
                className={cn(
                  "grid grid-cols-[auto_1fr_1fr_1fr_auto] gap-2 px-3 py-2.5 items-center",
                  "hover:bg-emerald-50/50 cursor-pointer transition-colors group",
                  ap.ist_hauptkontakt && "bg-amber-50/50"
                )}
                onDoubleClick={() => handleDoubleClick(ap)}
              >
                {/* Avatar */}
                <div className="w-8">
                  {ap.profilbild ? (
                    <img 
                      src={ap.profilbild} 
                      alt=""
                      className="h-8 w-8 rounded-full object-cover border border-gray-200"
                    />
                  ) : (
                    <div className="h-8 w-8 rounded-full bg-gradient-to-br from-emerald-400 to-emerald-600 flex items-center justify-center text-white text-xs font-medium">
                      {ap.vorname?.[0]}{ap.nachname?.[0]}
                    </div>
                  )}
                </div>

                {/* Name & Funktion */}
                <div className="min-w-0">
                  <div className="flex items-center gap-1.5">
                    <span className="font-medium text-gray-900 truncate">
                      {ap.anrede && <span className="text-gray-500 font-normal">{ap.anrede} </span>}
                      {ap.vorname} {ap.nachname}
                    </span>
                    {ap.ist_hauptkontakt && (
                      <Star className="h-3.5 w-3.5 text-amber-500 fill-amber-500 flex-shrink-0" />
                    )}
                  </div>
                  {ap.funktion && (
                    <p className="text-xs text-gray-500 truncate">{ap.funktion}</p>
                  )}
                </div>

                {/* Kontakt */}
                <div className="min-w-0 text-sm">
                  {ap.email && (
                    <a 
                      href={`mailto:${ap.email}`}
                      className="text-emerald-600 hover:underline truncate block text-xs"
                      onClick={(e) => e.stopPropagation()}
                    >
                      {ap.email}
                    </a>
                  )}
                  {ap.telefon && (
                    <span className="text-gray-500 text-xs truncate block">{ap.telefon}</span>
                  )}
                </div>

                {/* Abteilung */}
                <div className="min-w-0">
                  {ap.abteilung && (
                    <Badge variant="outline" className="text-xs font-normal">
                      {ap.abteilung}
                    </Badge>
                  )}
                </div>

                {/* Aktionen */}
                <div className="w-20 flex justify-end gap-0.5 opacity-0 group-hover:opacity-100 transition-opacity">
                  <Button
                    variant="ghost"
                    size="icon"
                    className="h-7 w-7"
                    onClick={(e) => { e.stopPropagation(); handleDoubleClick(ap); }}
                    title="Details anzeigen"
                  >
                    <Eye className="h-3.5 w-3.5" />
                  </Button>
                  {isEditing && (
                    <>
                      <Button
                        variant="ghost"
                        size="icon"
                        className="h-7 w-7"
                        onClick={(e) => { e.stopPropagation(); handleEdit(ap); }}
                        title="Bearbeiten"
                      >
                        <Pencil className="h-3.5 w-3.5" />
                      </Button>
                      {!ap.ist_hauptkontakt && (
                        <Button
                          variant="ghost"
                          size="icon"
                          className="h-7 w-7 text-amber-500 hover:text-amber-600"
                          onClick={(e) => { e.stopPropagation(); setAsMainContact(ap.id); }}
                          title="Als Hauptkontakt setzen"
                        >
                          <Star className="h-3.5 w-3.5" />
                        </Button>
                      )}
                      <Button
                        variant="ghost"
                        size="icon"
                        className="h-7 w-7 text-red-500 hover:text-red-600"
                        onClick={(e) => { e.stopPropagation(); handleDelete(ap.id); }}
                        title="Löschen"
                      >
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

      {/* Detail/Edit Dialog */}
      <Dialog open={showDialog} onOpenChange={setShowDialog}>
        <DialogContent className="max-w-lg">
          <DialogHeader>
            <DialogTitle className="flex items-center gap-2">
              {dialogMode === 'view' && <Eye className="h-5 w-5 text-emerald-500" />}
              {dialogMode === 'edit' && <Pencil className="h-5 w-5 text-blue-500" />}
              {dialogMode === 'create' && <Plus className="h-5 w-5 text-emerald-500" />}
              {dialogMode === 'view' && 'Ansprechpartner Details'}
              {dialogMode === 'edit' && 'Ansprechpartner bearbeiten'}
              {dialogMode === 'create' && 'Neuer Ansprechpartner'}
            </DialogTitle>
          </DialogHeader>

          <div className="space-y-4 py-4">
            {/* Avatar und Hauptkontakt Badge */}
            {dialogMode === 'view' && selectedAp && (
              <div className="flex items-center gap-4 pb-4 border-b">
                {selectedAp.profilbild ? (
                  <img 
                    src={selectedAp.profilbild} 
                    alt=""
                    className="h-16 w-16 rounded-full object-cover border-2 border-emerald-200"
                  />
                ) : (
                  <div className="h-16 w-16 rounded-full bg-gradient-to-br from-emerald-400 to-emerald-600 flex items-center justify-center text-white text-xl font-medium">
                    {selectedAp.vorname?.[0]}{selectedAp.nachname?.[0]}
                  </div>
                )}
                <div>
                  <h3 className="text-lg font-semibold">
                    {selectedAp.anrede} {selectedAp.vorname} {selectedAp.nachname}
                  </h3>
                  {selectedAp.funktion && (
                    <p className="text-gray-500">{selectedAp.funktion}</p>
                  )}
                  {selectedAp.ist_hauptkontakt && (
                    <Badge className="mt-1 bg-amber-100 text-amber-700">
                      <Star className="h-3 w-3 mr-1 fill-amber-500" />
                      Hauptkontakt
                    </Badge>
                  )}
                </div>
              </div>
            )}

            {/* Formular */}
            <div className="grid grid-cols-3 gap-4">
              <div>
                <Label className="text-xs text-gray-500">Anrede</Label>
                {dialogMode === 'view' ? (
                  <p className="font-medium">{formData.anrede || '-'}</p>
                ) : (
                  <SmartInput
                    module="adressen"
                    fieldName="ansprechpartner.anrede"
                    value={formData.anrede || ''}
                    onChange={(val) => setFormData({ ...formData, anrede: val || '' })}
                    placeholder="Herr/Frau"
                  />
                )}
              </div>
              <div>
                <Label className="text-xs text-gray-500">Vorname</Label>
                {dialogMode === 'view' ? (
                  <p className="font-medium">{formData.vorname || '-'}</p>
                ) : (
                  <Input
                    value={formData.vorname || ''}
                    onChange={(e) => setFormData({ ...formData, vorname: e.target.value })}
                  />
                )}
              </div>
              <div>
                <Label className="text-xs text-gray-500">Nachname *</Label>
                {dialogMode === 'view' ? (
                  <p className="font-medium">{formData.nachname || '-'}</p>
                ) : (
                  <Input
                    value={formData.nachname || ''}
                    onChange={(e) => setFormData({ ...formData, nachname: e.target.value })}
                    required
                  />
                )}
              </div>
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label className="text-xs text-gray-500">Funktion</Label>
                {dialogMode === 'view' ? (
                  <p className="font-medium">{formData.funktion || '-'}</p>
                ) : (
                  <SmartInput
                    module="adressen"
                    fieldName="ansprechpartner.funktion"
                    value={formData.funktion || ''}
                    onChange={(val) => setFormData({ ...formData, funktion: val || '' })}
                    placeholder="z.B. Geschäftsführer"
                  />
                )}
              </div>
              <div>
                <Label className="text-xs text-gray-500">Abteilung</Label>
                {dialogMode === 'view' ? (
                  <p className="font-medium">{formData.abteilung || '-'}</p>
                ) : (
                  <SmartInput
                    module="adressen"
                    fieldName="ansprechpartner.abteilung"
                    value={formData.abteilung || ''}
                    onChange={(val) => setFormData({ ...formData, abteilung: val || '' })}
                    placeholder="z.B. Einkauf"
                  />
                )}
              </div>
            </div>

            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label className="text-xs text-gray-500">
                  <Phone className="h-3 w-3 inline mr-1" />
                  Telefon
                </Label>
                {dialogMode === 'view' ? (
                  <p className="font-medium">
                    {formData.telefon ? (
                      <a href={`tel:${formData.telefon}`} className="text-emerald-600 hover:underline">
                        {formData.telefon}
                      </a>
                    ) : '-'}
                  </p>
                ) : (
                  <Input
                    value={formData.telefon || ''}
                    onChange={(e) => setFormData({ ...formData, telefon: e.target.value })}
                    type="tel"
                  />
                )}
              </div>
              <div>
                <Label className="text-xs text-gray-500">Mobil</Label>
                {dialogMode === 'view' ? (
                  <p className="font-medium">
                    {formData.mobil ? (
                      <a href={`tel:${formData.mobil}`} className="text-emerald-600 hover:underline">
                        {formData.mobil}
                      </a>
                    ) : '-'}
                  </p>
                ) : (
                  <Input
                    value={formData.mobil || ''}
                    onChange={(e) => setFormData({ ...formData, mobil: e.target.value })}
                    type="tel"
                  />
                )}
              </div>
            </div>

            <div>
              <Label className="text-xs text-gray-500">
                <Mail className="h-3 w-3 inline mr-1" />
                E-Mail
              </Label>
              {dialogMode === 'view' ? (
                <p className="font-medium">
                  {formData.email ? (
                    <a href={`mailto:${formData.email}`} className="text-emerald-600 hover:underline">
                      {formData.email}
                    </a>
                  ) : '-'}
                </p>
              ) : (
                <Input
                  value={formData.email || ''}
                  onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                  type="email"
                />
              )}
            </div>

            {(dialogMode !== 'view' || formData.notizen) && (
              <div>
                <Label className="text-xs text-gray-500">Notizen</Label>
                {dialogMode === 'view' ? (
                  <p className="text-sm bg-gray-50 p-2 rounded">{formData.notizen || '-'}</p>
                ) : (
                  <Textarea
                    value={formData.notizen || ''}
                    onChange={(e) => setFormData({ ...formData, notizen: e.target.value })}
                    rows={2}
                  />
                )}
              </div>
            )}

            {dialogMode !== 'view' && (
              <div className="flex items-center gap-4 pt-2">
                <div className="flex items-center gap-2">
                  <Switch
                    checked={formData.ist_hauptkontakt || false}
                    onCheckedChange={(v) => setFormData({ ...formData, ist_hauptkontakt: v })}
                  />
                  <Label className="text-sm">Hauptkontakt</Label>
                </div>
                <div className="flex items-center gap-2">
                  <Switch
                    checked={formData.aktiv !== false}
                    onCheckedChange={(v) => setFormData({ ...formData, aktiv: v })}
                  />
                  <Label className="text-sm">Aktiv</Label>
                </div>
              </div>
            )}
          </div>

          <DialogFooter>
            {dialogMode === 'view' ? (
              <>
                <Button variant="outline" onClick={() => setShowDialog(false)}>
                  Schließen
                </Button>
                {isEditing && (
                  <Button onClick={() => setDialogMode('edit')}>
                    <Pencil className="h-4 w-4 mr-1" />
                    Bearbeiten
                  </Button>
                )}
              </>
            ) : (
              <>
                <Button variant="outline" onClick={() => setShowDialog(false)}>
                  Abbrechen
                </Button>
                <Button onClick={handleSave} disabled={isSaving}>
                  {isSaving ? 'Speichert...' : 'Speichern'}
                </Button>
              </>
            )}
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
