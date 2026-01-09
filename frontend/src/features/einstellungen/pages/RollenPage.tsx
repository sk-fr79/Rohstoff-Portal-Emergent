import { useState, useMemo } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { toast } from 'sonner';
import { 
  Shield, Plus, Pencil, Trash2, Save, Loader2, Users, Lock
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
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';

interface Rolle {
  id: string;
  name: string;
  beschreibung?: string;
  farbe?: string;
  ist_system?: boolean;
  benutzer_count?: number;
}

const FARBEN = [
  { value: '#DC2626', label: 'Rot' },
  { value: '#D97706', label: 'Orange' },
  { value: '#059669', label: 'Grün' },
  { value: '#2563EB', label: 'Blau' },
  { value: '#7C3AED', label: 'Violett' },
  { value: '#DB2777', label: 'Pink' },
  { value: '#0891B2', label: 'Cyan' },
  { value: '#6B7280', label: 'Grau' },
];

export function RollenPage() {
  const { user } = useAuthStore();
  const queryClient = useQueryClient();
  const [showDialog, setShowDialog] = useState(false);
  const [editingRolle, setEditingRolle] = useState<Rolle | null>(null);

  const [formData, setFormData] = useState({
    name: '',
    beschreibung: '',
    farbe: '#2563EB',
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
  const { data: rollen, isLoading } = useQuery({
    queryKey: ['admin-rollen'],
    queryFn: async () => {
      const response = await api.get('/admin/rollen');
      return response.data.data as Rolle[];
    },
  });

  // Mutations
  const createMutation = useMutation({
    mutationFn: (data: typeof formData) => api.post('/admin/rollen', data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['admin-rollen'] });
      toast.success('Rolle erstellt');
      setShowDialog(false);
      resetForm();
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Erstellen');
    },
  });

  const updateMutation = useMutation({
    mutationFn: ({ id, data }: { id: string; data: any }) => 
      api.put(`/admin/rollen/${id}`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['admin-rollen'] });
      toast.success('Rolle aktualisiert');
      setShowDialog(false);
      resetForm();
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Aktualisieren');
    },
  });

  const deleteMutation = useMutation({
    mutationFn: (id: string) => api.delete(`/admin/rollen/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['admin-rollen'] });
      toast.success('Rolle gelöscht');
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
    setFormData({ name: '', beschreibung: '', farbe: '#2563EB' });
    setEditingRolle(null);
  };

  const handleEdit = (rolle: Rolle) => {
    setEditingRolle(rolle);
    setFormData({
      name: rolle.name,
      beschreibung: rolle.beschreibung || '',
      farbe: rolle.farbe || '#2563EB',
    });
    setShowDialog(true);
  };

  const handleSave = () => {
    if (editingRolle) {
      updateMutation.mutate({ id: editingRolle.id, data: formData });
    } else {
      createMutation.mutate(formData);
    }
  };

  // Table columns
  const columns: ColumnDef<Rolle>[] = useMemo(() => [
    {
      accessorKey: 'name',
      header: 'Rolle',
      cell: ({ row }) => (
        <div className="flex items-center gap-3">
          <div 
            className="w-4 h-4 rounded-full" 
            style={{ backgroundColor: row.original.farbe || '#6B7280' }}
          />
          <span className="font-medium">{row.original.name}</span>
          {row.original.ist_system && (
            <Lock className="w-3 h-3 text-gray-400" />
          )}
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
      header: 'Benutzer',
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
          {!row.original.ist_system && (
            <Button 
              variant="ghost" 
              size="icon"
              className="text-red-600 hover:text-red-700"
              onClick={() => {
                if (confirm('Rolle wirklich löschen?')) {
                  deleteMutation.mutate(row.original.id);
                }
              }}
            >
              <Trash2 className="w-4 h-4" />
            </Button>
          )}
        </div>
      ),
    },
  ], []);

  return (
    <div className="p-6 max-w-4xl mx-auto" data-testid="rollen-page">
      <div className="flex items-center justify-between mb-6">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">Benutzerrollen</h1>
          <p className="text-gray-500">Definieren Sie Rollen für die Rechteverwaltung</p>
        </div>
        <div className="flex gap-2">
          {(!rollen || rollen.length === 0) && (
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
            Neue Rolle
          </Button>
        </div>
      </div>

      {/* Info Card */}
      <Card className="mb-6 bg-amber-50 border-amber-200">
        <CardContent className="pt-4">
          <div className="flex items-start gap-3">
            <Lock className="w-5 h-5 text-amber-600 mt-0.5" />
            <div>
              <p className="font-medium text-amber-800">System-Rollen</p>
              <p className="text-sm text-amber-700">
                Die Rolle "Administrator" ist eine System-Rolle und kann nicht gelöscht werden.
                Administratoren haben automatisch vollen Zugriff auf alle Module.
              </p>
            </div>
          </div>
        </CardContent>
      </Card>

      {isLoading ? (
        <div className="flex items-center justify-center py-12">
          <Loader2 className="h-8 w-8 animate-spin text-gray-400" />
        </div>
      ) : (
        <DataTable
          columns={columns}
          data={rollen || []}
          searchPlaceholder="Rolle suchen..."
        />
      )}

      {/* Dialog */}
      <Dialog open={showDialog} onOpenChange={setShowDialog}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>
              {editingRolle ? 'Rolle bearbeiten' : 'Neue Rolle'}
            </DialogTitle>
          </DialogHeader>
          <div className="grid gap-4 py-4">
            <div>
              <Label>Name *</Label>
              <Input
                value={formData.name}
                onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                disabled={editingRolle?.ist_system}
              />
            </div>
            <div>
              <Label>Beschreibung</Label>
              <Textarea
                value={formData.beschreibung}
                onChange={(e) => setFormData({ ...formData, beschreibung: e.target.value })}
                rows={2}
              />
            </div>
            <div>
              <Label>Farbe</Label>
              <div className="flex gap-2 mt-2">
                {FARBEN.map((farbe) => (
                  <button
                    key={farbe.value}
                    className={`w-8 h-8 rounded-full border-2 transition-transform ${
                      formData.farbe === farbe.value ? 'border-gray-900 scale-110' : 'border-transparent'
                    }`}
                    style={{ backgroundColor: farbe.value }}
                    onClick={() => setFormData({ ...formData, farbe: farbe.value })}
                    title={farbe.label}
                  />
                ))}
              </div>
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
