import { useState, useMemo } from 'react';
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query';
import { ColumnDef } from '@tanstack/react-table';
import { toast } from 'sonner';
import { 
  Users, Plus, Pencil, Trash2, Key, Save, Loader2, X,
  Mail, User, Building2, Shield, MoreHorizontal, Search,
  CheckCircle, XCircle
} from 'lucide-react';
import { api } from '@/services/api/client';
import { useAuthStore } from '@/store/authStore';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Badge } from '@/components/ui/badge';
import { Switch } from '@/components/ui/switch';
import { DataTable } from '@/components/ui/data-table';
import { Dialog, DialogContent, DialogHeader, DialogTitle, DialogFooter } from '@/components/ui/dialog';
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from '@/components/ui/select';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { DropdownMenu, DropdownMenuContent, DropdownMenuItem, DropdownMenuTrigger } from '@/components/ui/dropdown-menu';
import { cn } from '@/lib/utils';

interface Benutzer {
  id: string;
  benutzername: string;
  email: string;
  vorname?: string;
  nachname?: string;
  kuerzel?: string;
  rolle_id?: string;
  rolle_name?: string;
  rolle_farbe?: string;
  abteilung_ids: string[];
  abteilung_names?: string[];
  aktiv: boolean;
  profilbild_url?: string;
}

interface Rolle {
  id: string;
  name: string;
  farbe?: string;
  ist_system?: boolean;
  benutzer_count?: number;
}

interface Abteilung {
  id: string;
  name: string;
  kuerzel?: string;
  benutzer_count?: number;
}

export function BenutzerPage() {
  const { user } = useAuthStore();
  const queryClient = useQueryClient();
  const [showDialog, setShowDialog] = useState(false);
  const [showPasswordDialog, setShowPasswordDialog] = useState(false);
  const [editingUser, setEditingUser] = useState<Benutzer | null>(null);
  const [selectedUserId, setSelectedUserId] = useState<string | null>(null);
  const [newPassword, setNewPassword] = useState('');

  const [formData, setFormData] = useState({
    benutzername: '',
    email: '',
    passwort: '',
    vorname: '',
    nachname: '',
    kuerzel: '',
    rolle_id: '',
    abteilung_ids: [] as string[],
    aktiv: true,
  });

  // Nur Admins
  if (!user?.istAdmin) {
    return (
      <div className="p-6 flex items-center justify-center min-h-[400px]">
        <div className="text-center">
          <Shield className="h-16 w-16 mx-auto text-gray-300 mb-4" />
          <h2 className="text-xl font-semibold text-gray-700">Kein Zugriff</h2>
          <p className="text-gray-500 mt-2">Sie benötigen Administrator-Rechte.</p>
        </div>
      </div>
    );
  }

  // Queries
  const { data: benutzer, isLoading } = useQuery({
    queryKey: ['admin-benutzer'],
    queryFn: async () => {
      const response = await api.get('/admin/benutzer');
      return response.data.data as Benutzer[];
    },
  });

  const { data: rollen } = useQuery({
    queryKey: ['admin-rollen'],
    queryFn: async () => {
      const response = await api.get('/admin/rollen');
      return response.data.data as Rolle[];
    },
  });

  const { data: abteilungen } = useQuery({
    queryKey: ['admin-abteilungen'],
    queryFn: async () => {
      const response = await api.get('/admin/abteilungen');
      return response.data.data as Abteilung[];
    },
  });

  // Mutations
  const createMutation = useMutation({
    mutationFn: (data: typeof formData) => api.post('/admin/benutzer', data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['admin-benutzer'] });
      toast.success('Benutzer erstellt');
      setShowDialog(false);
      resetForm();
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Erstellen');
    },
  });

  const updateMutation = useMutation({
    mutationFn: ({ id, data }: { id: string; data: any }) => 
      api.put(`/admin/benutzer/${id}`, data),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['admin-benutzer'] });
      toast.success('Benutzer aktualisiert');
      setShowDialog(false);
      resetForm();
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Aktualisieren');
    },
  });

  const deleteMutation = useMutation({
    mutationFn: (id: string) => api.delete(`/admin/benutzer/${id}`),
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['admin-benutzer'] });
      toast.success('Benutzer gelöscht');
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Löschen');
    },
  });

  const resetPasswordMutation = useMutation({
    mutationFn: ({ id, password }: { id: string; password: string }) =>
      api.post(`/admin/benutzer/${id}/passwort-reset`, { neues_passwort: password }),
    onSuccess: () => {
      toast.success('Passwort zurückgesetzt');
      setShowPasswordDialog(false);
      setNewPassword('');
      setSelectedUserId(null);
    },
    onError: (error: any) => {
      toast.error(error.response?.data?.detail || 'Fehler beim Zurücksetzen');
    },
  });

  const resetForm = () => {
    setFormData({
      benutzername: '',
      email: '',
      passwort: '',
      vorname: '',
      nachname: '',
      kuerzel: '',
      rolle_id: '',
      abteilung_ids: [],
      aktiv: true,
    });
    setEditingUser(null);
  };

  const handleEdit = (benutzer: Benutzer) => {
    setEditingUser(benutzer);
    setFormData({
      benutzername: benutzer.benutzername,
      email: benutzer.email,
      passwort: '',
      vorname: benutzer.vorname || '',
      nachname: benutzer.nachname || '',
      kuerzel: benutzer.kuerzel || '',
      rolle_id: benutzer.rolle_id || '',
      abteilung_ids: benutzer.abteilung_ids || [],
      aktiv: benutzer.aktiv,
    });
    setShowDialog(true);
  };

  const handleSave = () => {
    if (editingUser) {
      const { passwort, benutzername, ...updateData } = formData;
      updateMutation.mutate({ id: editingUser.id, data: updateData });
    } else {
      createMutation.mutate(formData);
    }
  };

  const toggleAbteilung = (abtId: string) => {
    const current = formData.abteilung_ids;
    if (current.includes(abtId)) {
      setFormData({ ...formData, abteilung_ids: current.filter(id => id !== abtId) });
    } else {
      setFormData({ ...formData, abteilung_ids: [...current, abtId] });
    }
  };

  // Table columns
  const columns: ColumnDef<Benutzer>[] = useMemo(() => [
    {
      accessorKey: 'benutzername',
      header: 'Benutzer',
      cell: ({ row }) => (
        <div className="flex items-center gap-3">
          <div className="h-10 w-10 rounded-full bg-blue-100 flex items-center justify-center flex-shrink-0">
            <span className="text-sm font-semibold text-blue-600">
              {row.original.kuerzel || row.original.benutzername.charAt(0).toUpperCase()}
            </span>
          </div>
          <div>
            <p className="font-medium">{row.original.benutzername}</p>
            <p className="text-sm text-gray-500">{row.original.email}</p>
          </div>
        </div>
      ),
    },
    {
      accessorKey: 'name',
      header: 'Name',
      cell: ({ row }) => (
        <span>
          {row.original.vorname || row.original.nachname
            ? `${row.original.vorname || ''} ${row.original.nachname || ''}`.trim()
            : '-'}
        </span>
      ),
    },
    {
      accessorKey: 'rolle_name',
      header: 'Rolle',
      cell: ({ row }) => (
        row.original.rolle_name ? (
          <Badge style={{ backgroundColor: row.original.rolle_farbe || '#6B7280' }} className="text-white">
            {row.original.rolle_name}
          </Badge>
        ) : (
          <span className="text-gray-400">Keine Rolle</span>
        )
      ),
    },
    {
      accessorKey: 'abteilung_names',
      header: 'Abteilungen',
      cell: ({ row }) => {
        const abteilungen = row.original.abteilung_names || [];
        return abteilungen.length > 0 ? (
          <div className="flex flex-wrap gap-1">
            {abteilungen.slice(0, 2).map((name, i) => (
              <Badge key={i} variant="outline" className="text-xs">
                {name}
              </Badge>
            ))}
            {abteilungen.length > 2 && (
              <Badge variant="outline" className="text-xs">
                +{abteilungen.length - 2}
              </Badge>
            )}
          </div>
        ) : (
          <span className="text-gray-400">-</span>
        );
      },
    },
    {
      accessorKey: 'aktiv',
      header: 'Status',
      cell: ({ row }) => (
        <Badge className={row.original.aktiv ? 'bg-green-100 text-green-800' : 'bg-gray-100 text-gray-600'}>
          {row.original.aktiv ? (
            <><CheckCircle className="w-3 h-3 mr-1" /> Aktiv</>
          ) : (
            <><XCircle className="w-3 h-3 mr-1" /> Inaktiv</>
          )}
        </Badge>
      ),
    },
    {
      id: 'actions',
      header: '',
      cell: ({ row }) => (
        <DropdownMenu>
          <DropdownMenuTrigger asChild>
            <Button variant="ghost" size="icon" className="h-8 w-8">
              <MoreHorizontal className="w-4 h-4" />
            </Button>
          </DropdownMenuTrigger>
          <DropdownMenuContent align="end">
            <DropdownMenuItem onClick={() => handleEdit(row.original)}>
              <Pencil className="w-4 h-4 mr-2" />
              Bearbeiten
            </DropdownMenuItem>
            <DropdownMenuItem onClick={() => {
              setSelectedUserId(row.original.id);
              setShowPasswordDialog(true);
            }}>
              <Key className="w-4 h-4 mr-2" />
              Passwort zurücksetzen
            </DropdownMenuItem>
            <DropdownMenuItem
              className="text-red-600"
              onClick={() => {
                if (confirm('Benutzer wirklich löschen?')) {
                  deleteMutation.mutate(row.original.id);
                }
              }}
            >
              <Trash2 className="w-4 h-4 mr-2" />
              Löschen
            </DropdownMenuItem>
          </DropdownMenuContent>
        </DropdownMenu>
      ),
    },
  ], []);

  return (
    <div className="p-6 max-w-6xl mx-auto" data-testid="benutzer-page">
      <div className="flex items-center justify-between mb-6">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">Benutzerverwaltung</h1>
          <p className="text-gray-500">Verwalten Sie Benutzer, Rollen und Abteilungen</p>
        </div>
        <Button onClick={() => { resetForm(); setShowDialog(true); }}>
          <Plus className="w-4 h-4 mr-2" />
          Neuer Benutzer
        </Button>
      </div>

      {/* Stats Cards */}
      <div className="grid grid-cols-3 gap-4 mb-6">
        <Card>
          <CardContent className="pt-6">
            <div className="flex items-center gap-4">
              <div className="p-3 bg-blue-100 rounded-lg">
                <Users className="h-6 w-6 text-blue-600" />
              </div>
              <div>
                <p className="text-2xl font-bold">{benutzer?.length || 0}</p>
                <p className="text-sm text-gray-500">Benutzer</p>
              </div>
            </div>
          </CardContent>
        </Card>
        <Card>
          <CardContent className="pt-6">
            <div className="flex items-center gap-4">
              <div className="p-3 bg-purple-100 rounded-lg">
                <Shield className="h-6 w-6 text-purple-600" />
              </div>
              <div>
                <p className="text-2xl font-bold">{rollen?.length || 0}</p>
                <p className="text-sm text-gray-500">Rollen</p>
              </div>
            </div>
          </CardContent>
        </Card>
        <Card>
          <CardContent className="pt-6">
            <div className="flex items-center gap-4">
              <div className="p-3 bg-green-100 rounded-lg">
                <Building2 className="h-6 w-6 text-green-600" />
              </div>
              <div>
                <p className="text-2xl font-bold">{abteilungen?.length || 0}</p>
                <p className="text-sm text-gray-500">Abteilungen</p>
              </div>
            </div>
          </CardContent>
        </Card>
      </div>

      {/* Benutzer-Tabelle */}
      {isLoading ? (
        <div className="flex items-center justify-center py-12">
          <Loader2 className="h-8 w-8 animate-spin text-gray-400" />
        </div>
      ) : (
        <DataTable
          columns={columns}
          data={benutzer || []}
          searchPlaceholder="Benutzer suchen..."
        />
      )}

      {/* Benutzer erstellen/bearbeiten Dialog */}
      <Dialog open={showDialog} onOpenChange={setShowDialog}>
        <DialogContent className="max-w-2xl">
          <DialogHeader>
            <DialogTitle>
              {editingUser ? 'Benutzer bearbeiten' : 'Neuer Benutzer'}
            </DialogTitle>
          </DialogHeader>
          <div className="grid gap-4 py-4">
            <div className="grid grid-cols-2 gap-4">
              <div>
                <Label>Benutzername *</Label>
                <Input
                  value={formData.benutzername}
                  onChange={(e) => setFormData({ ...formData, benutzername: e.target.value })}
                  disabled={!!editingUser}
                />
              </div>
              <div>
                <Label>E-Mail *</Label>
                <Input
                  type="email"
                  value={formData.email}
                  onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                />
              </div>
            </div>

            {!editingUser && (
              <div>
                <Label>Passwort *</Label>
                <Input
                  type="password"
                  value={formData.passwort}
                  onChange={(e) => setFormData({ ...formData, passwort: e.target.value })}
                />
              </div>
            )}

            <div className="grid grid-cols-3 gap-4">
              <div>
                <Label>Vorname</Label>
                <Input
                  value={formData.vorname}
                  onChange={(e) => setFormData({ ...formData, vorname: e.target.value })}
                />
              </div>
              <div>
                <Label>Nachname</Label>
                <Input
                  value={formData.nachname}
                  onChange={(e) => setFormData({ ...formData, nachname: e.target.value })}
                />
              </div>
              <div>
                <Label>Kürzel</Label>
                <Input
                  value={formData.kuerzel}
                  onChange={(e) => setFormData({ ...formData, kuerzel: e.target.value.toUpperCase() })}
                  maxLength={5}
                />
              </div>
            </div>

            <div>
              <Label>Rolle</Label>
              <Select
                value={formData.rolle_id}
                onValueChange={(v) => setFormData({ ...formData, rolle_id: v })}
              >
                <SelectTrigger>
                  <SelectValue placeholder="Rolle auswählen..." />
                </SelectTrigger>
                <SelectContent>
                  {rollen?.map((rolle) => (
                    <SelectItem key={rolle.id} value={rolle.id}>
                      <div className="flex items-center gap-2">
                        <div 
                          className="w-3 h-3 rounded-full" 
                          style={{ backgroundColor: rolle.farbe || '#6B7280' }}
                        />
                        {rolle.name}
                      </div>
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>

            <div>
              <Label>Abteilungen</Label>
              <div className="flex flex-wrap gap-2 mt-2 p-3 border rounded-lg bg-gray-50">
                {abteilungen?.map((abt) => (
                  <Badge
                    key={abt.id}
                    variant={formData.abteilung_ids.includes(abt.id) ? 'default' : 'outline'}
                    className={cn(
                      "cursor-pointer transition-colors",
                      formData.abteilung_ids.includes(abt.id) && "bg-blue-500"
                    )}
                    onClick={() => toggleAbteilung(abt.id)}
                  >
                    {abt.name}
                  </Badge>
                ))}
              </div>
              <p className="text-xs text-gray-500 mt-1">
                Klicken Sie auf eine Abteilung, um sie zuzuweisen
              </p>
            </div>

            <div className="flex items-center justify-between">
              <Label>Aktiv</Label>
              <Switch
                checked={formData.aktiv}
                onCheckedChange={(v) => setFormData({ ...formData, aktiv: v })}
              />
            </div>
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setShowDialog(false)}>
              Abbrechen
            </Button>
            <Button 
              onClick={handleSave}
              disabled={createMutation.isPending || updateMutation.isPending}
            >
              {(createMutation.isPending || updateMutation.isPending) && (
                <Loader2 className="w-4 h-4 mr-2 animate-spin" />
              )}
              <Save className="w-4 h-4 mr-2" />
              {editingUser ? 'Speichern' : 'Erstellen'}
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>

      {/* Passwort-Reset Dialog */}
      <Dialog open={showPasswordDialog} onOpenChange={setShowPasswordDialog}>
        <DialogContent>
          <DialogHeader>
            <DialogTitle>Passwort zurücksetzen</DialogTitle>
          </DialogHeader>
          <div className="py-4">
            <Label>Neues Passwort</Label>
            <Input
              type="password"
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
              placeholder="Mindestens 6 Zeichen"
            />
          </div>
          <DialogFooter>
            <Button variant="outline" onClick={() => setShowPasswordDialog(false)}>
              Abbrechen
            </Button>
            <Button
              onClick={() => {
                if (selectedUserId && newPassword.length >= 6) {
                  resetPasswordMutation.mutate({ id: selectedUserId, password: newPassword });
                }
              }}
              disabled={newPassword.length < 6 || resetPasswordMutation.isPending}
            >
              {resetPasswordMutation.isPending && (
                <Loader2 className="w-4 h-4 mr-2 animate-spin" />
              )}
              <Key className="w-4 h-4 mr-2" />
              Zurücksetzen
            </Button>
          </DialogFooter>
        </DialogContent>
      </Dialog>
    </div>
  );
}
