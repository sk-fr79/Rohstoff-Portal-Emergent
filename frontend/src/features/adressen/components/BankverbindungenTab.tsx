import { useState } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { toast } from 'sonner';
import { Plus, Pencil, Trash2, CreditCard, Star, Loader2, Building2, Check } from 'lucide-react';
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

interface Bankverbindung {
  id: string;
  iban: string;
  bic?: string;
  bank_name?: string;
  kontonummer?: string;
  bankleitzahl?: string;
  kontoinhaber?: string;
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
      verwendungszweck: 'Beides',
      ist_hauptkonto: false,
      aktiv: true,
    });
    setEditingBank(null);
  };

  const handleEdit = (bank: Bankverbindung) => {
    setEditingBank(bank);
    setFormData(bank);
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
          {bankverbindungen?.map((bank: Bankverbindung) => (
            <div 
              key={bank.id}
              className={cn(
                "p-4 rounded-lg border bg-white hover:shadow-sm transition-shadow",
                bank.ist_hauptkonto && "border-blue-200 bg-blue-50/50",
                !bank.aktiv && "opacity-60"
              )}
            >
              <div className="flex items-start justify-between">
                <div className="flex-1">
                  <div className="flex items-center gap-2 mb-1">
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
                    {!bank.aktiv && (
                      <Badge variant="secondary" className="text-xs">Inaktiv</Badge>
                    )}
                  </div>
                  <p className="font-mono text-lg font-medium text-gray-900">
                    {formatIBAN(bank.iban)}
                  </p>
                  {bank.bic && (
                    <p className="text-sm text-gray-500 font-mono">BIC: {bank.bic}</p>
                  )}
                  {bank.bank_name && (
                    <p className="text-sm text-gray-600 mt-1 flex items-center gap-1">
                      <Building2 className="w-3 h-3" />
                      {bank.bank_name}
                    </p>
                  )}
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
          ))}
        </div>
      )}

      {/* Dialog */}
      <Dialog open={showDialog} onOpenChange={setShowDialog}>
        <DialogContent className="sm:max-w-lg">
          <DialogHeader>
            <DialogTitle>
              {editingBank ? 'Bankverbindung bearbeiten' : 'Neue Bankverbindung'}
            </DialogTitle>
          </DialogHeader>

          <div className="space-y-4 py-4">
            <div className="grid gap-4">
              <div>
                <Label className="text-sm font-medium">IBAN *</Label>
                <Input
                  value={formData.iban || ''}
                  onChange={(e) => setFormData({ ...formData, iban: e.target.value.toUpperCase().replace(/\s/g, '') })}
                  placeholder="DE89370400440532013000"
                  className="font-mono"
                />
              </div>

              <div className="grid grid-cols-2 gap-4">
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

              <div>
                <Label className="text-sm font-medium">Bank</Label>
                <SmartInput
                  module="adressen"
                  fieldName="bankverbindungen.bank_name"
                  value={formData.bank_name || ''}
                  onChange={(val) => setFormData({ ...formData, bank_name: val || '' })}
                  placeholder="Commerzbank"
                />
              </div>

              <div>
                <Label className="text-sm font-medium">Kontoinhaber</Label>
                <Input
                  value={formData.kontoinhaber || ''}
                  onChange={(e) => setFormData({ ...formData, kontoinhaber: e.target.value })}
                  placeholder="Max Mustermann GmbH"
                />
              </div>

              <div className="flex items-center justify-between py-2">
                <div className="flex items-center gap-2">
                  <Star className="w-4 h-4 text-amber-500" />
                  <Label className="text-sm">Hauptkonto</Label>
                </div>
                <Switch
                  checked={formData.ist_hauptkonto || false}
                  onCheckedChange={(v) => setFormData({ ...formData, ist_hauptkonto: v })}
                />
              </div>

              <div className="flex items-center justify-between py-2">
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
              {editingBank ? 'Speichern' : 'Hinzufügen'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
