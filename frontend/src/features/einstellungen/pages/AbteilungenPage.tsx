import { useState, useMemo } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { toast } from 'sonner';
import { 
  Building2, Plus, Pencil, Trash2, Save, Loader2, Users, Shield
} from 'lucide-react';
import { api } from '@/services/api/client';
import { useAuthStore } from '@/store/authStore';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';
import { Badge } from '@/components/ui/badge';
import { DataTable } from '@/components/ui/data-table';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from '@/components/ui/dialog';

interface Abteilung {
  id: string;
  name: string;
  kuerzel?: string;
  beschreibung?: string;
  benutzer_count?: number;
}

export function AbteilungenPage() {
  const { user } = useAuthStore();
  const queryClient = useQueryClient();
  const [showDialog, setShowDialog] = useState(false);
  const [editingAbteilung, setEditingAbteilung] = useState<Abteilung | null>(null);

  const [formData, setFormData] = useState({
    name: '',
    kuerzel: '',
    beschreibung: '',
  });

  // Nur Admins
  if (!user?.istAdmin) {
    return (
      <div className="p-6 flex items-center justify-center min-h-[400px]">
        <div className="text-center">
          <Shield className="h-16 w-16 mx-auto text-gray-300 mb-4" />
          <h2 className="text-xl font-semibold text-gray-700">Kein Zugriff</h2>
        </div>
      </div>
    );
  }

  // Queries
  const { data: abteilungen, isLoading } = useQuery({
    queryKey: ['admin-abteilungen'],
    queryFn: async () => {
      const response = await api.get('/admin/abteilungen');
      return response.data.data as Abteilung[];
    },
  });

  // Mutations
  const createMutation = useMutation({
    mutationFn: (data: typeof formData) => api.post('/admin/abteilungen', data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['admin-abteilungen'] });
      toast.success('Abteilung erstellt');
      setShowDialog(false);
      resetForm();
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Erstellen');
    },
  });

  const updateMutation = useMutation({
    mutationFn: ({ id, data }: { id: string; data: any }) => 
      api.put(`/admin/abteilungen/${id}`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['admin-abteilungen'] });
      toast.success('Abteilung aktualisiert');
      setShowDialog(false);
      resetForm();
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Aktualisieren');
    },
  });

  const deleteMutation = useMutation({
    mutationFn: (id: string) => api.delete(`/admin/abteilungen/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['admin-abteilungen'] });
      toast.success('Abteilung gelöscht');
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Löschen');
    },
  });

  // Init System
  const initMutation = useMutation({
    mutationFn: () => api.post('/admin/init-system'),
    onSuccess: (response) => {
      queryClient.invalidateQueries({ queryKey: ['admin-rollen'] });
      queryClient.invalidateQueries({ queryKey: ['admin-abteilungen'] });
      const { rollen, abteilungen } = response.data.created;
      toast.success(`Initialisiert: ${rollen} Rollen, ${abteilungen} Abteilungen`);
    },
  });

  const resetForm = () => {
    setFormData({ name: '', kuerzel: '', beschreibung: '' });
    setEditingAbteilung(null);
  };

  const handleEdit = (abteilung: Abteilung) => {
    setEditingAbteilung(abteilung);
    setFormData({
      name: abteilung.name,
      kuerzel: abteilung.kuerzel || '',
      beschreibung: abteilung.beschreibung || '',
    });
    setShowDialog(true);
  };

  const handleSave = () => {
    if (editingAbteilung) {
      updateMutation.mutate({ id: editingAbteilung.id, data: formData });
    } else {
      createMutation.mutate(formData);
    }
  };

  // Table columns
  const columns: ColumnDef<Abteilung>[] = useMemo(() => [
    {
      accessorKey: 'name',
      header: 'Abteilung',
      cell: ({ row }) => (
        <div className="flex items-center gap-3">
          <div className="w-10 h-10 rounded-lg bg-green-100 flex items-center justify-center">
            <Building2 className="w-5 h-5 text-green-600" />
          </div>
          <div>
            <p className="font-medium">{row.original.name}</p>
            {row.original.kuerzel && (
              <p className="text-xs text-gray-500">{row.original.kuerzel}</p>
            )}
          </div>
        </div>
      ),
    },
    {
      accessorKey: 'beschreibung',
      header: 'Beschreibung',
      cell: ({ row }) => (
        <span className="text-gray-600">{row.original.beschreibung || '-'}</span>
      ),
    },
    {
      accessorKey: 'benutzer_count',
      header: 'Mitarbeiter',
      cell: ({ row }) => (
        <Badge variant="secondary">
          <Users className="w-3 h-3 mr-1" />
          {row.original.benutzer_count || 0}
        </Badge>
      ),
    },
    {
      id: 'actions',
      header: '',
      cell: ({ row }) => (
        <div className="flex gap-2">
          <Button variant="ghost" size="icon" onClick={() => handleEdit(row.original)}>
            <Pencil className="w-4 h-4" />
          </Button>
          <Button 
            variant="ghost" 
            size="icon"
            className="text-red-600 hover:text-red-700"
            onClick={() => {
              if (confirm('Abteilung wirklich löschen?')) {
                deleteMutation.mutate(row.original.id);
              }
            }}
          >
            <Trash2 className="w-4 h-4" />
          </Button>
        </div>
      ),
    },
  ], []);

  return (
    <div className="p-6 max-w-4xl mx-auto" data-testid="abteilungen-page">
      <div className="flex items-center justify-between mb-6">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">Abteilungen</h1>
          <p className="text-gray-500">Verwalten Sie die Unternehmensabteilungen</p>
        </div>
        <div className="flex gap-2">
          {(!abteilungen || abteilungen.length === 0) && (
            <Button 
              variant="outline"
              onClick={() => initMutation.mutate()}
              disabled={initMutation.isPending}
            >
              {initMutation.isPending && <Loader2 className="w-4 h-4 mr-2 animate-spin" />}
              Standard-Daten anlegen
            </Button>
          )}
          <Button onClick={() => { resetForm(); setShowDialog(true); }}>
            <Plus className="w-4 h-4 mr-2" />
            Neue Abteilung
          </Button>
        </div>
      </div>

      {isLoading ? (
        <div className="flex items-center justify-center py-12">
          <Loader2 className="h-8 w-8 animate-spin text-gray-400" />
        </div>
      ) : (
        <DataTable
          columns={columns}
          data={abteilungen || []}
          searchPlaceholder="Abteilung suchen..."
        />
      )}

      {/* Dialog */}
      <Dialog open={showDialog} onOpenChange={setShowDialog}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>
              {editingAbteilung ? 'Abteilung bearbeiten' : 'Neue Abteilung'}
            </DialogTitle>
          </DialogHeader>
          <div className="grid gap-4 py-4">
            <div className="grid grid-cols-3 gap-4">
              <div className="col-span-2">
                <Label>Name *</Label>
                <Input
                  value={formData.name}
                  onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                />
              </div>
              <div>
                <Label>Kürzel</Label>
                <Input
                  value={formData.kuerzel}
                  onChange={(e) => setFormData({ ...formData, kuerzel: e.target.value.toUpperCase() })}
                  maxLength={10}
                />
              </div>
            </div>
            <div>
              <Label>Beschreibung</Label>
              <Textarea
                value={formData.beschreibung}
                onChange={(e) => setFormData({ ...formData, beschreibung: e.target.value })}
                rows={2}
              />
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setShowDialog(false)}>
              Abbrechen
            </Button>
            <Button 
              onClick={handleSave}
              disabled={!formData.name || createMutation.isPending || updateMutation.isPending}
            >
              {(createMutation.isPending || updateMutation.isPending) && (
                <Loader2 className="w-4 h-4 mr-2 animate-spin" />
              )}
              <Save className="w-4 h-4 mr-2" />
              Speichern
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
