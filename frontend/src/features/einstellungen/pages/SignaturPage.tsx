import { useState } from 'react';
import { toast } from 'sonner';
import { Mail, Save, Loader2, Bold, Italic, Link, List } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Textarea } from '@/components/ui/textarea';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { Switch } from '@/components/ui/switch';

export function SignaturPage() {
  const [isLoading, setIsLoading] = useState(false);
  const [signatur, setSignatur] = useState(`Mit freundlichen Grüßen

Max Mustermann
Sachbearbeiter Einkauf

MV Rohstoff Portal GmbH
Musterstraße 123
12345 Musterstadt
Tel: +49 123 456789
E-Mail: m.mustermann@mv-rohstoff.de`);
  const [useHtml, setUseHtml] = useState(false);

  const handleSave = async () => {
    setIsLoading(true);
    await new Promise(resolve => setTimeout(resolve, 1000));
    setIsLoading(false);
    toast.success('E-Mail Signatur gespeichert');
  };

  return (
    <div className="p-6 max-w-3xl mx-auto" data-testid="signatur-page">
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-gray-900">E-Mail Signatur</h1>
        <p className="text-gray-500">Erstellen Sie Ihre persönliche E-Mail-Signatur</p>
      </div>

      <div className="grid gap-6">
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <Mail className="h-5 w-5" />
              Signatur bearbeiten
            </CardTitle>
            <CardDescription>
              Diese Signatur wird automatisch an Ihre ausgehenden E-Mails angehängt
            </CardDescription>
          </CardHeader>
          <CardContent className="space-y-4">
            <div className="flex items-center justify-between">
              <Label>HTML-Format verwenden</Label>
              <Switch checked={useHtml} onCheckedChange={setUseHtml} />
            </div>

            {useHtml && (
              <div className="flex gap-1 p-2 bg-gray-100 rounded-lg">
                <Button variant="ghost" size="icon" className="h-8 w-8">
                  <Bold className="h-4 w-4" />
                </Button>
                <Button variant="ghost" size="icon" className="h-8 w-8">
                  <Italic className="h-4 w-4" />
                </Button>
                <Button variant="ghost" size="icon" className="h-8 w-8">
                  <Link className="h-4 w-4" />
                </Button>
                <Button variant="ghost" size="icon" className="h-8 w-8">
                  <List className="h-4 w-4" />
                </Button>
              </div>
            )}

            <Textarea
              value={signatur}
              onChange={(e) => setSignatur(e.target.value)}
              rows={10}
              className="font-mono text-sm"
              placeholder="Geben Sie hier Ihre Signatur ein..."
            />

            <div className="flex justify-end">
              <Button onClick={handleSave} disabled={isLoading}>
                {isLoading ? (
                  <Loader2 className="h-4 w-4 mr-2 animate-spin" />
                ) : (
                  <Save className="h-4 w-4 mr-2" />
                )}
                Speichern
              </Button>
            </div>
          </CardContent>
        </Card>

        {/* Vorschau */}
        <Card>
          <CardHeader>
            <CardTitle>Vorschau</CardTitle>
          </CardHeader>
          <CardContent>
            <div className="p-4 bg-white border rounded-lg">
              <pre className="whitespace-pre-wrap text-sm text-gray-700 font-sans">
                {signatur}
              </pre>
            </div>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
