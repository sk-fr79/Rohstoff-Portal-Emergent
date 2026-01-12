/**
 * DynamicField - Intelligente Feld-Komponente
 * 
 * Prüft automatisch, ob für ein Feld eine Verknüpfung existiert und
 * rendert entsprechend ein Dropdown (ReferenceSelect) oder ein normales Input.
 * 
 * Verwendung:
 * <DynamicField
 *   module="adressen"
 *   fieldName="bankverbindungen.bic"
 *   value={value}
 *   onChange={onChange}
 *   label="BIC/SWIFT"
 *   disabled={!isEditing}
 * />
 */
import { useFieldBinding } from '@/hooks/useFieldBinding';
import { ReferenceSelect } from '@/components/ui/reference-select';
import { Input } from '@/components/ui/input';
import { Label } from '@/components/ui/label';
import { Loader2, Link2 } from 'lucide-react';
import { cn } from '@/lib/utils';

interface DynamicFieldProps {
  module: string;
  fieldName: string;
  value: string | null | undefined;
  onChange: (value: string | null) => void;
  onSelectOption?: (option: any) => void;
  label?: string;
  placeholder?: string;
  disabled?: boolean;
  className?: string;
  inputClassName?: string;
  maxLength?: number;
  showLabel?: boolean;
  required?: boolean;
}

export function DynamicField({
  module,
  fieldName,
  value,
  onChange,
  onSelectOption,
  label,
  placeholder,
  disabled = false,
  className,
  inputClassName,
  maxLength,
  showLabel = true,
  required = false,
}: DynamicFieldProps) {
  const { hasBinding, binding, isLoading } = useFieldBinding(module, fieldName);

  // Während des Ladens einen Skeleton/Loader zeigen
  if (isLoading) {
    return (
      <div className={cn("space-y-1.5", className)}>
        {showLabel && label && (
          <Label className="text-sm font-medium">{label}</Label>
        )}
        <div className="h-10 bg-gray-100 rounded-md animate-pulse flex items-center justify-center">
          <Loader2 className="h-4 w-4 animate-spin text-gray-400" />
        </div>
      </div>
    );
  }

  // Wenn eine Verknüpfung existiert, ReferenceSelect anzeigen
  if (hasBinding && binding) {
    return (
      <div className={cn("space-y-1.5", className)}>
        {showLabel && label && (
          <Label className="text-sm font-medium flex items-center gap-1.5">
            {label}
            <Link2 className="h-3 w-3 text-emerald-500" title="Verknüpft mit Referenzdaten" />
            {required && <span className="text-red-500">*</span>}
          </Label>
        )}
        <ReferenceSelect
          module={module}
          fieldName={fieldName}
          value={value}
          onChange={onChange}
          onSelectOption={onSelectOption}
          placeholder={placeholder || `${label} auswählen...`}
          disabled={disabled}
          className={cn("bg-white", inputClassName)}
        />
      </div>
    );
  }

  // Kein Binding - normales Input anzeigen
  return (
    <div className={cn("space-y-1.5", className)}>
      {showLabel && label && (
        <Label className="text-sm font-medium">
          {label}
          {required && <span className="text-red-500 ml-1">*</span>}
        </Label>
      )}
      <Input
        value={value || ''}
        onChange={(e) => onChange(e.target.value || null)}
        placeholder={placeholder}
        disabled={disabled}
        maxLength={maxLength}
        className={cn("bg-white", inputClassName)}
      />
    </div>
  );
}

/**
 * Einfachere Version ohne Label-Management
 * Für Inline-Nutzung in Formularen mit externem Label
 */
export function DynamicInput({
  module,
  fieldName,
  value,
  onChange,
  onSelectOption,
  placeholder,
  disabled = false,
  className,
  maxLength,
}: Omit<DynamicFieldProps, 'label' | 'showLabel' | 'required'>) {
  const { hasBinding, binding, isLoading } = useFieldBinding(module, fieldName);

  console.log(`[DynamicInput] ${module}.${fieldName}: isLoading=${isLoading}, hasBinding=${hasBinding}`, binding);

  if (isLoading) {
    return (
      <div className={cn("h-10 bg-gray-100 rounded-md animate-pulse flex items-center justify-center", className)}>
        <Loader2 className="h-4 w-4 animate-spin text-gray-400" />
      </div>
    );
  }

  if (hasBinding && binding) {
    console.log(`[DynamicInput] Rendering ReferenceSelect for ${module}.${fieldName}`);
    return (
      <ReferenceSelect
        module={module}
        fieldName={fieldName}
        value={value}
        onChange={onChange}
        onSelectOption={onSelectOption}
        placeholder={placeholder}
        disabled={disabled}
        className={cn("bg-white", className)}
      />
    );
  }

  return (
    <Input
      value={value || ''}
      onChange={(e) => onChange(e.target.value || null)}
      placeholder={placeholder}
      disabled={disabled}
      maxLength={maxLength}
      className={cn("bg-white", className)}
    />
  );
}
