import { useState, useRef } from 'react';
import { toast } from 'sonner';
import { PenTool, Upload, Trash2, Save, Loader2, Download } from 'lucide-react';
import { Button } from '@/components/ui/button';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';

export function UnterschriftPage() {
  const [isLoading, setIsLoading] = useState(false);
  const [signatureImage, setSignatureImage] = useState<string | null>(null);
  const fileInputRef = useRef<HTMLInputElement>(null);

  const handleFileUpload = (e: React.ChangeEvent<HTMLInputElement>) => {
    const file = e.target.files?.[0];
    if (file) {
      if (file.size > 500000) {
        toast.error('Datei ist zu groß (max. 500KB)');
        return;
      }
      const reader = new FileReader();
      reader.onload = (event) => {
        setSignatureImage(event.target?.result as string);
        toast.success('Unterschrift hochgeladen');
      };
      reader.readAsDataURL(file);
    }
  };

  const handleDelete = () => {
    setSignatureImage(null);
    toast.success('Unterschrift gelöscht');
  };

  const handleSave = async () => {
    setIsLoading(true);
    await new Promise(resolve => setTimeout(resolve, 1000));
    setIsLoading(false);
    toast.success('Unterschrift gespeichert');
  };

  return (
    <div className="p-6 max-w-3xl mx-auto" data-testid="unterschrift-page">
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-gray-900">Unterschrift</h1>
        <p className="text-gray-500">Laden Sie Ihre digitale Unterschrift hoch</p>
      </div>

      <div className="grid gap-6">
        <Card>
          <CardHeader>
            <CardTitle className="flex items-center gap-2">
              <PenTool className="h-5 w-5" />
              Digitale Unterschrift
            </CardTitle>
            <CardDescription>
              Ihre Unterschrift wird auf Dokumenten wie Rechnungen und Verträgen verwendet
            </CardDescription>
          </CardHeader>
          <CardContent className="space-y-4">
            {/* Upload-Bereich */}
            <div 
              className="border-2 border-dashed border-gray-200 rounded-lg p-8 text-center hover:border-gray-300 transition-colors cursor-pointer"
              onClick={() => fileInputRef.current?.click()}
            >
              {signatureImage ? (
                <div className="space-y-4">
                  <img 
                    src={signatureImage} 
                    alt="Unterschrift" 
                    className="max-h-32 mx-auto"
                  />
                  <p className="text-sm text-gray-500">Klicken zum Ersetzen</p>
                </div>
              ) : (
                <div className="space-y-2">
                  <Upload className="h-10 w-10 mx-auto text-gray-400" />
                  <p className="text-gray-600">Klicken zum Hochladen</p>
                  <p className="text-xs text-gray-400">PNG, JPG oder GIF (max. 500KB)</p>
                </div>
              )}
              <input
                ref={fileInputRef}
                type="file"
                accept="image/png,image/jpeg,image/gif"
                className="hidden"
                onChange={handleFileUpload}
              />
            </div>

            {/* Aktionen */}
            <div className="flex justify-between">
              <div className="space-x-2">
                {signatureImage && (
                  <>
                    <Button variant="outline" size="sm" onClick={handleDelete}>
                      <Trash2 className="h-4 w-4 mr-1" />
                      Löschen
                    </Button>
                    <Button variant="outline" size="sm">
                      <Download className="h-4 w-4 mr-1" />
                      Herunterladen
                    </Button>
                  </>
                )}
              </div>
              <Button onClick={handleSave} disabled={isLoading || !signatureImage}>
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

        {/* Hinweise */}
        <Card>
          <CardHeader>
            <CardTitle>Hinweise</CardTitle>
          </CardHeader>
          <CardContent>
            <ul className="space-y-2 text-sm text-gray-600">
              <li>• Verwenden Sie eine Unterschrift mit transparentem Hintergrund (PNG)</li>
              <li>• Die optimale Größe ist 300x100 Pixel</li>
              <li>• Die Unterschrift sollte gut lesbar und nicht zu klein sein</li>
              <li>• Sie können die Unterschrift jederzeit ändern oder löschen</li>
            </ul>
          </CardContent>
        </Card>
      </div>
    </div>
  );
}
