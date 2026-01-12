/**
 * SmartInput - Intelligenter Input mit automatischer Feld-Verknüpfung
 * 
 * Ersetzt <Input> und wird automatisch zum Dropdown, wenn eine 
 * Feld-Verknüpfung für das Feld existiert.
 * 
 * Verwendung:
 * <SmartInput
 *   module="adressen"
 *   fieldName="bankverbindungen.bic"
 *   value={value}
 *   onChange={onChange}
 *   placeholder="BIC eingeben..."
 * />
 * 
 * Oder ohne Verknüpfung (verhält sich wie normales Input):
 * <SmartInput
 *   value={value}
 *   onChange={(val) => setValue(val)}
 *   placeholder="Text eingeben..."
 * />
 */
import { forwardRef } from 'react';
import { useFieldBindingsStore } from '@/store/fieldBindingsStore';
import { ReferenceSelect } from '@/components/ui/reference-select';
import { Input } from '@/components/ui/input';
import { Link2 } from 'lucide-react';
import { cn } from '@/lib/utils';

export interface SmartInputProps {
  // Für Feld-Verknüpfung (optional)
  module?: string;
  fieldName?: string;
  
  // Standard Input Props
  value?: string | null;
  onChange?: (value: string | null) => void;
  onSelectOption?: (option: any) => void;
  placeholder?: string;
  disabled?: boolean;
  className?: string;
  maxLength?: number;
  type?: string;
  
  // Für react-hook-form
  name?: string;
  onBlur?: () => void;
  ref?: React.Ref<HTMLInputElement>;
  
  // Styling
  showBindingIndicator?: boolean;
}

export const SmartInput = forwardRef<HTMLInputElement, SmartInputProps>(({
  module,
  fieldName,
  value,
  onChange,
  onSelectOption,
  placeholder,
  disabled = false,
  className,
  maxLength,
  type = 'text',
  showBindingIndicator = false,
  ...rest
}, ref) => {
  // Prüfe ob eine Verknüpfung existiert (aus globalem Cache)
  const getBinding = useFieldBindingsStore((state) => state.getBinding);
  const isLoaded = useFieldBindingsStore((state) => state.isLoaded);
  
  const binding = module && fieldName ? getBinding(module, fieldName) : null;
  const hasBinding = binding !== null;

  // Wenn Verknüpfung existiert und geladen, ReferenceSelect anzeigen
  if (hasBinding && isLoaded && module && fieldName) {
    return (
      <div className="relative">
        <ReferenceSelect
          module={module}
          fieldName={fieldName}
          value={value}
          onChange={onChange || (() => {})}
          onSelectOption={onSelectOption}
          placeholder={placeholder}
          disabled={disabled}
          className={cn("bg-white", className)}
        />
        {showBindingIndicator && (
          <Link2 className="absolute right-8 top-1/2 -translate-y-1/2 h-3 w-3 text-emerald-500" />
        )}
      </div>
    );
  }

  // Normales Input anzeigen
  return (
    <Input
      ref={ref}
      type={type}
      value={value || ''}
      onChange={(e) => onChange?.(e.target.value || null)}
      placeholder={placeholder}
      disabled={disabled}
      maxLength={maxLength}
      className={cn("bg-white", className)}
      {...rest}
    />
  );
});

SmartInput.displayName = 'SmartInput';

/**
 * SmartTextarea - Intelligente Textarea mit automatischer Feld-Verknüpfung
 * (Für mehrzeilige Felder - zeigt Dropdown wenn Verknüpfung existiert)
 */
import { Textarea } from '@/components/ui/textarea';

export interface SmartTextareaProps {
  module?: string;
  fieldName?: string;
  value?: string | null;
  onChange?: (value: string | null) => void;
  onSelectOption?: (option: any) => void;
  placeholder?: string;
  disabled?: boolean;
  className?: string;
  rows?: number;
  maxLength?: number;
}

export function SmartTextarea({
  module,
  fieldName,
  value,
  onChange,
  onSelectOption,
  placeholder,
  disabled = false,
  className,
  rows = 3,
  maxLength,
}: SmartTextareaProps) {
  const getBinding = useFieldBindingsStore((state) => state.getBinding);
  const isLoaded = useFieldBindingsStore((state) => state.isLoaded);
  
  const binding = module && fieldName ? getBinding(module, fieldName) : null;
  const hasBinding = binding !== null;

  // Wenn Verknüpfung existiert, ReferenceSelect anzeigen
  if (hasBinding && isLoaded && module && fieldName) {
    return (
      <ReferenceSelect
        module={module}
        fieldName={fieldName}
        value={value}
        onChange={onChange || (() => {})}
        onSelectOption={onSelectOption}
        placeholder={placeholder}
        disabled={disabled}
        className={cn("bg-white", className)}
      />
    );
  }

  // Normale Textarea anzeigen
  return (
    <Textarea
      value={value || ''}
      onChange={(e) => onChange?.(e.target.value || null)}
      placeholder={placeholder}
      disabled={disabled}
      rows={rows}
      maxLength={maxLength}
      className={cn("bg-white", className)}
    />
  );
}
