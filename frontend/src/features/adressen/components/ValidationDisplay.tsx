import { AlertTriangle, XCircle, AlertCircle, CheckCircle, Info } from 'lucide-react';
import { cn } from '@/lib/utils';
import { motion, AnimatePresence } from 'framer-motion';

interface ValidationFehler {
  meldung: string;
  schweregrad: 'fehler' | 'warnung' | 'info';
  betroffene_felder?: string[];
}

interface ValidationResult {
  ist_gueltig: boolean;
  fehler: ValidationFehler[];
  warnungen: ValidationFehler[];
  steuer_status?: string;
}

interface ValidationDisplayProps {
  validierung?: ValidationResult | null;
  className?: string;
  compact?: boolean;
}

const FELD_LABEL_MAP: Record<string, string> = {
  name1: 'Firmenname/Name1',
  strasse: 'Straße',
  plz: 'PLZ',
  ort: 'Ort',
  land: 'Land',
  umsatzsteuer_id: 'UST-ID',
  umsatzsteuer_lkz: 'UST-Länderkürzel',
  steuernummer: 'Steuernummer',
  ausweis_nummer: 'Ausweisnummer',
  ausweis_ablauf: 'Ausweis-Ablaufdatum',
  firma_ohne_ustid: 'Sonderschalter "Firma ohne UST-ID"',
  privat_mit_ustid: 'Sonderschalter "Privat mit UST-ID"',
  ist_firma: 'Firmen-/Privatstatus',
};

export function ValidationDisplay({ validierung, className, compact = false }: ValidationDisplayProps) {
  if (!validierung) return null;

  const allMessages = [
    ...validierung.fehler.map(f => ({ ...f, type: 'error' as const })),
    ...validierung.warnungen.map(w => ({ ...w, type: 'warning' as const })),
  ];

  if (allMessages.length === 0 && validierung.ist_gueltig) {
    return null;
  }

  return (
    <AnimatePresence>
      <motion.div
        initial={{ opacity: 0, y: -10 }}
        animate={{ opacity: 1, y: 0 }}
        exit={{ opacity: 0, y: -10 }}
        className={cn("space-y-2", className)}
      >
        {/* Zusammenfassung */}
        {!compact && (
          <div className={cn(
            "p-3 rounded-lg flex items-center gap-3",
            validierung.ist_gueltig 
              ? "bg-green-50 border border-green-200" 
              : "bg-red-50 border border-red-200"
          )}>
            {validierung.ist_gueltig ? (
              <>
                <CheckCircle className="w-5 h-5 text-green-600 flex-shrink-0" />
                <div>
                  <p className="font-medium text-green-800">Validierung erfolgreich</p>
                  {validierung.steuer_status && (
                    <p className="text-sm text-green-600">Status: {validierung.steuer_status.replace(/_/g, ' ')}</p>
                  )}
                </div>
              </>
            ) : (
              <>
                <XCircle className="w-5 h-5 text-red-600 flex-shrink-0" />
                <div>
                  <p className="font-medium text-red-800">
                    {validierung.fehler.length} Fehler gefunden
                  </p>
                  <p className="text-sm text-red-600">
                    Bitte korrigieren Sie die markierten Felder
                  </p>
                </div>
              </>
            )}
          </div>
        )}

        {/* Fehler-Liste */}
        {validierung.fehler.length > 0 && (
          <div className="space-y-1.5">
            {validierung.fehler.map((fehler, idx) => (
              <motion.div
                key={`error-${idx}`}
                initial={{ opacity: 0, x: -10 }}
                animate={{ opacity: 1, x: 0 }}
                transition={{ delay: idx * 0.05 }}
                className={cn(
                  "p-3 rounded-lg bg-red-50 border border-red-200",
                  compact && "p-2"
                )}
              >
                <div className="flex items-start gap-2">
                  <XCircle className={cn(
                    "text-red-600 flex-shrink-0 mt-0.5",
                    compact ? "w-4 h-4" : "w-5 h-5"
                  )} />
                  <div className="flex-1 min-w-0">
                    <p className={cn(
                      "text-red-800",
                      compact ? "text-sm" : "font-medium"
                    )}>
                      {fehler.meldung}
                    </p>
                    {fehler.betroffene_felder && fehler.betroffene_felder.length > 0 && !compact && (
                      <p className="text-xs text-red-600 mt-1">
                        Betroffene Felder: {fehler.betroffene_felder.map(f => FELD_LABEL_MAP[f] || f).join(', ')}
                      </p>
                    )}
                  </div>
                </div>
              </motion.div>
            ))}
          </div>
        )}

        {/* Warnungen-Liste */}
        {validierung.warnungen.length > 0 && (
          <div className="space-y-1.5">
            {validierung.warnungen.map((warnung, idx) => (
              <motion.div
                key={`warning-${idx}`}
                initial={{ opacity: 0, x: -10 }}
                animate={{ opacity: 1, x: 0 }}
                transition={{ delay: (validierung.fehler.length + idx) * 0.05 }}
                className={cn(
                  "p-3 rounded-lg bg-amber-50 border border-amber-200",
                  compact && "p-2"
                )}
              >
                <div className="flex items-start gap-2">
                  <AlertTriangle className={cn(
                    "text-amber-600 flex-shrink-0 mt-0.5",
                    compact ? "w-4 h-4" : "w-5 h-5"
                  )} />
                  <div className="flex-1 min-w-0">
                    <p className={cn(
                      "text-amber-800",
                      compact ? "text-sm" : "font-medium"
                    )}>
                      {warnung.meldung}
                    </p>
                    {warnung.betroffene_felder && warnung.betroffene_felder.length > 0 && !compact && (
                      <p className="text-xs text-amber-600 mt-1">
                        Betroffene Felder: {warnung.betroffene_felder.map(f => FELD_LABEL_MAP[f] || f).join(', ')}
                      </p>
                    )}
                  </div>
                </div>
              </motion.div>
            ))}
          </div>
        )}
      </motion.div>
    </AnimatePresence>
  );
}

// Kompakte Inline-Anzeige für einzelne Felder
interface FieldValidationProps {
  feldName: string;
  validierung?: ValidationResult | null;
}

export function FieldValidation({ feldName, validierung }: FieldValidationProps) {
  if (!validierung) return null;

  const fehler = validierung.fehler.find(f => 
    f.betroffene_felder?.includes(feldName)
  );
  
  const warnung = validierung.warnungen.find(w => 
    w.betroffene_felder?.includes(feldName)
  );

  if (!fehler && !warnung) return null;

  return (
    <motion.div
      initial={{ opacity: 0, height: 0 }}
      animate={{ opacity: 1, height: 'auto' }}
      exit={{ opacity: 0, height: 0 }}
      className="mt-1"
    >
      {fehler && (
        <p className="text-xs text-red-600 flex items-center gap-1">
          <XCircle className="w-3 h-3" />
          {fehler.meldung}
        </p>
      )}
      {warnung && !fehler && (
        <p className="text-xs text-amber-600 flex items-center gap-1">
          <AlertTriangle className="w-3 h-3" />
          {warnung.meldung}
        </p>
      )}
    </motion.div>
  );
}

// Steuer-Status Badge
interface SteuerStatusBadgeProps {
  status?: string | null;
  className?: string;
}

export function SteuerStatusBadge({ status, className }: SteuerStatusBadgeProps) {
  if (!status) return null;

  const config = {
    FIRMA_INLAND: { label: 'Firma (Inland)', color: 'bg-blue-100 text-blue-800 border-blue-200' },
    FIRMA_EU: { label: 'Firma (EU)', color: 'bg-purple-100 text-purple-800 border-purple-200' },
    FIRMA_DRITTLAND: { label: 'Firma (Drittland)', color: 'bg-indigo-100 text-indigo-800 border-indigo-200' },
    PRIVAT_INLAND: { label: 'Privat (Inland)', color: 'bg-green-100 text-green-800 border-green-200' },
    PRIVAT_AUSLAND: { label: 'Privat (Ausland)', color: 'bg-teal-100 text-teal-800 border-teal-200' },
  }[status] || { label: status, color: 'bg-gray-100 text-gray-800 border-gray-200' };

  return (
    <span className={cn(
      "inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium border",
      config.color,
      className
    )}>
      <Info className="w-3 h-3 mr-1" />
      {config.label}
    </span>
  );
}
