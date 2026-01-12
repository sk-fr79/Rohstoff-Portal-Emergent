import { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { Plus, Pencil, Trash2, CreditCard, Star, Loader2, Building2, Check, Coins } from 'lucide-react';
import { api } from '@/services/api/client';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Switch } from '@/components/ui/switch';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from '@/components/ui/dialog';
import { Badge } from '@/components/ui/badge';
import { SmartInput } from '@/components/ui/smart-input';
import { cn } from '@/lib/utils';

// OECD Währungen mit Symbolen - Referenzierbare Daten
export const WAEHRUNGEN = [
  { code: 'EUR', symbol: '€', name: 'Euro', land: 'Eurozone' },
  { code: 'USD', symbol: '$', name: 'US-Dollar', land: 'USA' },
  { code: 'CHF', symbol: 'CHF', name: 'Schweizer Franken', land: 'Schweiz' },
  { code: 'GBP', symbol: '£', name: 'Britisches Pfund', land: 'Großbritannien' },
  { code: 'JPY', symbol: '¥', name: 'Japanischer Yen', land: 'Japan' },
  { code: 'CAD', symbol: 'C$', name: 'Kanadischer Dollar', land: 'Kanada' },
  { code: 'AUD', symbol: 'A$', name: 'Australischer Dollar', land: 'Australien' },
  { code: 'NZD', symbol: 'NZ$', name: 'Neuseeland-Dollar', land: 'Neuseeland' },
  { code: 'SEK', symbol: 'kr', name: 'Schwedische Krone', land: 'Schweden' },
  { code: 'NOK', symbol: 'kr', name: 'Norwegische Krone', land: 'Norwegen' },
  { code: 'DKK', symbol: 'kr', name: 'Dänische Krone', land: 'Dänemark' },
  { code: 'PLN', symbol: 'zł', name: 'Polnischer Złoty', land: 'Polen' },
  { code: 'CZK', symbol: 'Kč', name: 'Tschechische Krone', land: 'Tschechien' },
  { code: 'HUF', symbol: 'Ft', name: 'Ungarischer Forint', land: 'Ungarn' },
  { code: 'TRY', symbol: '₺', name: 'Türkische Lira', land: 'Türkei' },
  { code: 'MXN', symbol: 'Mex$', name: 'Mexikanischer Peso', land: 'Mexiko' },
  { code: 'KRW', symbol: '₩', name: 'Südkoreanischer Won', land: 'Südkorea' },
  { code: 'ILS', symbol: '₪', name: 'Israelischer Schekel', land: 'Israel' },
] as const;

// Export für Referenzierung aus anderen Modulen
export type WaehrungCode = typeof WAEHRUNGEN[number]['code'];

export const getWaehrung = (code: string) => {
  return WAEHRUNGEN.find(w => w.code === code) || WAEHRUNGEN[0];
};

export const formatWaehrung = (code: string) => {
  const w = getWaehrung(code);
  return `${w.symbol} ${w.code}`;
};

interface Bankverbindung {
  id: string;
  iban: string;
  bic?: string;
  bank_name?: string;
  kontonummer?: string;
  bankleitzahl?: string;
  kontoinhaber?: string;
  waehrung?: string; // NEU: Währungscode
  verwendungszweck?: string;
  ist_hauptkonto: boolean;
  aktiv: boolean;
  bemerkungen?: string;
}

interface BankverbindungenTabProps {
  adresseId: string;
  isEditing: boolean;
}

const formatIBAN = (iban: string): string => {
  return iban.replace(/(.{4})/g, '$1 ').trim();
};

export function BankverbindungenTab({ adresseId, isEditing }: BankverbindungenTabProps) {
  const queryClient = useQueryClient();
  const [showDialog, setShowDialog] = useState(false);
  const [editingBank, setEditingBank] = useState<Bankverbindung | null>(null);
  const [formData, setFormData] = useState<Partial<Bankverbindung>>({
    iban: '',
    bic: '',
    bank_name: '',
    kontoinhaber: '',
    waehrung: 'EUR', // Default: Euro
    verwendungszweck: 'Beides',
    ist_hauptkonto: false,
    aktiv: true,
  });

  const { data: bankverbindungen, isLoading } = useQuery({
    queryKey: ['bankverbindungen', adresseId],
    queryFn: async () => {
      const response = await api.get(`/adressen/${adresseId}/bankverbindungen`);
      return response.data.data || [];
    },
    enabled: !!adresseId,
  });

  const addMutation = useMutation({
    mutationFn: async (data: Partial<Bankverbindung>) => {
      return api.post(`/adressen/${adresseId}/bankverbindungen`, data);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['bankverbindungen', adresseId] });
      toast.success('Bankverbindung hinzugefügt');
      setShowDialog(false);
      resetForm();
    },
    onError: () => {
      toast.error('Fehler beim Hinzufügen');
    },
  });

  const updateMutation = useMutation({
    mutationFn: async ({ id, data }: { id: string; data: Partial<Bankverbindung> }) => {
      return api.put(`/adressen/${adresseId}/bankverbindungen/${id}`, data);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['bankverbindungen', adresseId] });
      toast.success('Bankverbindung aktualisiert');
      setShowDialog(false);
      resetForm();
    },
    onError: () => {
      toast.error('Fehler beim Aktualisieren');
    },
  });

  const deleteMutation = useMutation({
    mutationFn: async (id: string) => {
      return api.delete(`/adressen/${adresseId}/bankverbindungen/${id}`);
    },
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['bankverbindungen', adresseId] });
      toast.success('Bankverbindung gelöscht');
    },
    onError: () => {
      toast.error('Fehler beim Löschen');
    },
  });

  const resetForm = () => {
    setFormData({
      iban: '',
      bic: '',
      bank_name: '',
      kontoinhaber: '',
      waehrung: 'EUR',
      verwendungszweck: 'Beides',
      ist_hauptkonto: false,
      aktiv: true,
    });
    setEditingBank(null);
  };

  const handleEdit = (bank: Bankverbindung) => {
    setEditingBank(bank);
    setFormData({
      ...bank,
      waehrung: bank.waehrung || 'EUR', // Fallback für bestehende Daten
    });
    setShowDialog(true);
  };

  const handleSubmit = () => {
    if (!formData.iban) {
      toast.error('IBAN ist ein Pflichtfeld');
      return;
    }

    if (editingBank) {
      updateMutation.mutate({ id: editingBank.id, data: formData });
    } else {
      addMutation.mutate(formData);
    }
  };

  if (isLoading) {
    return (
      <div className="flex items-center justify-center py-8">
        <Loader2 className="w-6 h-6 animate-spin text-gray-400" />
      </div>
    );
  }

  return (
    <div className="space-y-4">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div className="flex items-center gap-2">
          <CreditCard className="w-5 h-5 text-blue-600" />
          <h3 className="font-medium text-gray-900">Bankverbindungen</h3>
          <Badge variant="secondary" className="ml-2">
            {bankverbindungen?.length || 0}
          </Badge>
        </div>
        {isEditing && (
          <Button 
            size="sm" 
            onClick={() => { resetForm(); setShowDialog(true); }}
            className="bg-blue-600 hover:bg-blue-700"
          >
            <Plus className="w-4 h-4 mr-1" />
            Neu
          </Button>
        )}
      </div>

      {/* Liste */}
      {bankverbindungen?.length === 0 ? (
        <div className="text-center py-8 text-gray-500 bg-gray-50 rounded-lg border-2 border-dashed">
          <CreditCard className="w-10 h-10 mx-auto mb-2 text-gray-300" />
          <p>Keine Bankverbindungen vorhanden</p>
          {isEditing && (
            <Button 
              variant="link" 
              onClick={() => { resetForm(); setShowDialog(true); }}
              className="mt-2"
            >
              Erste Bankverbindung hinzufügen
            </Button>
          )}
        </div>
      ) : (
        <div className="space-y-3">
          {bankverbindungen?.map((bank: Bankverbindung) => {
            const waehrung = getWaehrung(bank.waehrung || 'EUR');
            return (
              <div 
                key={bank.id}
                className={cn(
                  "p-4 rounded-lg border bg-white hover:shadow-sm transition-shadow",
                  bank.ist_hauptkonto && "border-blue-200 bg-blue-50/50",
                  !bank.aktiv && "opacity-60"
                )}
              >
                <div className="flex items-start justify-between">
                  <div className="flex-1 space-y-1">
                    {/* Badges */}
                    <div className="flex items-center gap-2 mb-2">
                      {bank.ist_hauptkonto && (
                        <Badge className="bg-blue-600 text-xs">
                          <Star className="w-3 h-3 mr-1" />
                          Hauptkonto
                        </Badge>
                      )}
                      {bank.verwendungszweck && (
                        <Badge variant="outline" className="text-xs">
                          {bank.verwendungszweck}
                        </Badge>
                      )}
                      {/* Währungs-Badge */}
                      <Badge variant="secondary" className="text-xs font-mono">
                        {waehrung.symbol} {waehrung.code}
                      </Badge>
                      {!bank.aktiv && (
                        <Badge variant="secondary" className="text-xs">Inaktiv</Badge>
                      )}
                    </div>
                    
                    {/* Bank Name - Prominent */}
                    {bank.bank_name && (
                      <p className="text-base font-medium text-gray-900 flex items-center gap-1.5">
                        <Building2 className="w-4 h-4 text-gray-400" />
                        {bank.bank_name}
                      </p>
                    )}
                    
                    {/* BIC */}
                    {bank.bic && (
                      <p className="text-sm text-gray-500 font-mono">BIC: {bank.bic}</p>
                    )}
                    
                    {/* IBAN - Monospace */}
                    <p className="font-mono text-sm text-gray-700">
                      IBAN: {formatIBAN(bank.iban)}
                    </p>
                    
                    {/* Kontoinhaber */}
                    {bank.kontoinhaber && (
                      <p className="text-sm text-gray-500">Inhaber: {bank.kontoinhaber}</p>
                    )}
                  </div>
                  
                  {isEditing && (
                    <div className="flex gap-1">
                      <Button
                        variant="ghost"
                        size="icon"
                        onClick={() => handleEdit(bank)}
                        className="h-8 w-8"
                      >
                        <Pencil className="w-4 h-4" />
                      </Button>
                      <Button
                        variant="ghost"
                        size="icon"
                        onClick={() => deleteMutation.mutate(bank.id)}
                        className="h-8 w-8 text-red-600 hover:text-red-700 hover:bg-red-50"
                      >
                        <Trash2 className="w-4 h-4" />
                      </Button>
                    </div>
                  )}
                </div>
              </div>
            );
          })}
        </div>
      )}

      {/* Dialog - Neues Layout */}
      <Dialog open={showDialog} onOpenChange={setShowDialog}>
        <DialogContent className="sm:max-w-lg">
          <DialogHeader>
            <DialogTitle>
              {editingBank ? 'Bankverbindung bearbeiten' : 'Neue Bankverbindung'}
            </DialogTitle>
          </DialogHeader>

          <div className="space-y-4 py-4">
            {/* 1. Bank Name - Ganz oben */}
            <div>
              <Label className="text-sm font-medium">Bank</Label>
              <SmartInput
                module="adressen"
                fieldName="bankverbindungen.bank_name"
                value={formData.bank_name || ''}
                onChange={(val) => setFormData({ ...formData, bank_name: val || '' })}
                placeholder="z.B. Commerzbank, Sparkasse..."
              />
            </div>

            {/* 2. BIC */}
            <div>
              <Label className="text-sm font-medium">BIC/SWIFT</Label>
              <SmartInput
                module="adressen"
                fieldName="bankverbindungen.bic"
                value={formData.bic || ''}
                onChange={(val) => setFormData({ ...formData, bic: val?.toUpperCase() || '' })}
                placeholder="COBADEFFXXX"
                className="font-mono"
              />
            </div>

            {/* 3. IBAN */}
            <div>
              <Label className="text-sm font-medium">IBAN *</Label>
              <Input
                value={formData.iban || ''}
                onChange={(e) => setFormData({ ...formData, iban: e.target.value.toUpperCase().replace(/\s/g, '') })}
                placeholder="DE89370400440532013000"
                className="font-mono"
              />
            </div>

            {/* 4. Währung & Verwendung - Nebeneinander */}
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label className="text-sm font-medium flex items-center gap-1.5">
                  <Coins className="w-3.5 h-3.5" />
                  Währung
                </Label>
                <Select
                  value={formData.waehrung || 'EUR'}
                  onValueChange={(v) => setFormData({ ...formData, waehrung: v })}
                >
                  <SelectTrigger>
                    <SelectValue>
                      {(() => {
                        const w = getWaehrung(formData.waehrung || 'EUR');
                        return (
                          <span className="flex items-center gap-2">
                            <span className="font-mono font-medium">{w.symbol}</span>
                            <span>{w.code}</span>
                          </span>
                        );
                      })()}
                    </SelectValue>
                  </SelectTrigger>
                  <SelectContent className="max-h-[280px]">
                    {WAEHRUNGEN.map((w) => (
                      <SelectItem key={w.code} value={w.code}>
                        <div className="flex items-center gap-3">
                          <span className="font-mono font-semibold w-8 text-right">{w.symbol}</span>
                          <span className="font-medium">{w.code}</span>
                          <span className="text-gray-500 text-sm">{w.name}</span>
                        </div>
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              
              <div>
                <Label className="text-sm font-medium">Verwendung</Label>
                <Select
                  value={formData.verwendungszweck || 'Beides'}
                  onValueChange={(v) => setFormData({ ...formData, verwendungszweck: v })}
                >
                  <SelectTrigger>
                    <SelectValue />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="EK">Nur Einkauf</SelectItem>
                    <SelectItem value="VK">Nur Verkauf</SelectItem>
                    <SelectItem value="Beides">Beides</SelectItem>
                  </SelectContent>
                </Select>
              </div>
            </div>

            {/* 5. Kontoinhaber */}
            <div>
              <Label className="text-sm font-medium">Kontoinhaber</Label>
              <Input
                value={formData.kontoinhaber || ''}
                onChange={(e) => setFormData({ ...formData, kontoinhaber: e.target.value })}
                placeholder="Max Mustermann GmbH"
              />
            </div>

            {/* Switches */}
            <div className="space-y-3 pt-2 border-t">
              <div className="flex items-center justify-between py-1">
                <div className="flex items-center gap-2">
                  <Star className="w-4 h-4 text-amber-500" />
                  <Label className="text-sm">Hauptkonto</Label>
                </div>
                <Switch
                  checked={formData.ist_hauptkonto || false}
                  onCheckedChange={(v) => setFormData({ ...formData, ist_hauptkonto: v })}
                />
              </div>

              <div className="flex items-center justify-between py-1">
                <div className="flex items-center gap-2">
                  <Check className="w-4 h-4 text-green-500" />
                  <Label className="text-sm">Aktiv</Label>
                </div>
                <Switch
                  checked={formData.aktiv !== false}
                  onCheckedChange={(v) => setFormData({ ...formData, aktiv: v })}
                />
              </div>
            </div>
          </div>

          <DialogFooter>
            <Button variant="outline" onClick={() => setShowDialog(false)}>
              Abbrechen
            </Button>
            <Button 
              onClick={handleSubmit}
              disabled={addMutation.isPending || updateMutation.isPending}
            >
              {(addMutation.isPending || updateMutation.isPending) && (
                <Loader2 className="w-4 h-4 mr-2 animate-spin" />
              )}
              Speichern
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
