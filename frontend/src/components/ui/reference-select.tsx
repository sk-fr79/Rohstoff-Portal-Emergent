import { useState, useEffect, useCallback, useMemo } from 'react';
import { useAuthStore } from '@/store/authStore';
import { Check, ChevronsUpDown, Loader2, Search, X, Link2, Globe, Database, AlertCircle } from 'lucide-react';
import { cn } from '@/lib/utils';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import {
  Command,
  CommandEmpty,
  CommandGroup,
  CommandItem,
  CommandList,
} from '@/components/ui/command';
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover';
import { Badge } from '@/components/ui/badge';
import { ScrollArea } from '@/components/ui/scroll-area';

const API_URL = import.meta.env.VITE_API_URL || '/api';

interface ReferenceOption {
  value: string;
  label: string;
  display: string;
  data: Record<string, any>;
}

interface ReferenceSelectProps {
  module: string;
  fieldName: string;
  value?: string | null;
  onChange: (value: string | null) => void;
  placeholder?: string;
  disabled?: boolean;
  className?: string;
  showBadge?: boolean;
}

interface BindingInfo {
  source_type: 'reference_table' | 'api_query';
  display_field: string;
  value_field: string;
  is_required: boolean;
  min_search_chars: number;
  api_name?: string;
  reference_table_name?: string;
}

export function ReferenceSelect({
  module,
  fieldName,
  value,
  onChange,
  placeholder = 'Auswählen...',
  disabled = false,
  className,
  showBadge = true,
}: ReferenceSelectProps) {
  const { accessToken: token } = useAuthStore();
  const [open, setOpen] = useState(false);
  const [searchValue, setSearchValue] = useState('');
  const [options, setOptions] = useState<ReferenceOption[]>([]);
  const [binding, setBinding] = useState<BindingInfo | null>(null);
  const [isLoading, setIsLoading] = useState(false);
  const [hasBinding, setHasBinding] = useState<boolean | null>(null);
  const [message, setMessage] = useState<string | null>(null);
  const [isLive, setIsLive] = useState(false);
  const [isFallback, setIsFallback] = useState(false);

  // Prüfe ob Verknüpfung existiert beim Mount
  useEffect(() => {
    const checkBinding = async () => {
      try {
        const res = await fetch(
          `${API_URL}/system/field-binding/lookup?module=${module}&field_name=${fieldName}`,
          { headers: { 'Authorization': `Bearer ${token}` } }
        );
        const data = await res.json();
        
        if (data.success && data.data) {
          setHasBinding(true);
          setBinding({
            source_type: data.data.source_type || 'reference_table',
            display_field: data.data.display_field,
            value_field: data.data.value_field,
            is_required: data.data.is_required,
            min_search_chars: data.data.min_search_chars || 3,
            api_name: data.data.api_name,
            reference_table_name: data.data.reference_table_name,
          });
        } else {
          setHasBinding(false);
        }
      } catch (error) {
        console.error('Error checking binding:', error);
        setHasBinding(false);
      }
    };

    checkBinding();
  }, [module, fieldName, token]);

  // Lade Optionen
  const loadOptions = useCallback(async (search: string = '') => {
    if (!hasBinding || !binding) return;
    
    // Bei API-Query: Mindest-Suchzeichen prüfen
    if (binding.source_type === 'api_query') {
      if (search.length < binding.min_search_chars) {
        setOptions([]);
        setMessage(`Mindestens ${binding.min_search_chars} Zeichen eingeben`);
        return;
      }
    }
    
    setIsLoading(true);
    setMessage(null);
    
    try {
      const params = new URLSearchParams();
      if (search) params.append('search', search);
      params.append('limit', '100');

      const res = await fetch(
        `${API_URL}/system/reference-select/${module}/${fieldName}?${params}`,
        { headers: { 'Authorization': `Bearer ${token}` } }
      );
      const data = await res.json();
      
      if (data.success) {
        setOptions(data.data.options || []);
        setIsLive(data.data.live === true);
        setIsFallback(data.data.binding?.fallback_active === true);
        
        if (data.data.message) {
          setMessage(data.data.message);
        }
        if (data.data.error) {
          setMessage(data.data.error);
        }
      }
    } catch (error) {
      console.error('Error loading options:', error);
      setMessage('Fehler beim Laden der Optionen');
    } finally {
      setIsLoading(false);
    }
  }, [module, fieldName, token, hasBinding, binding]);

  // Lade Optionen wenn Popover öffnet (nur für Referenztabellen)
  useEffect(() => {
    if (open && hasBinding && binding) {
      // Bei Referenztabelle: Sofort laden
      if (binding.source_type === 'reference_table') {
        loadOptions(searchValue);
      } else {
        // Bei API-Query: Nur laden wenn genug Zeichen
        if (searchValue.length >= binding.min_search_chars) {
          loadOptions(searchValue);
        } else {
          setMessage(`Mindestens ${binding.min_search_chars} Zeichen eingeben`);
          setOptions([]);
        }
      }
    }
  }, [open, hasBinding, binding?.source_type]);

  // Debounced Search
  useEffect(() => {
    if (!open || !hasBinding || !binding) return;
    
    const timer = setTimeout(() => {
      loadOptions(searchValue);
    }, binding.source_type === 'api_query' ? 400 : 300);
    
    return () => clearTimeout(timer);
  }, [searchValue]);

  // Finde das ausgewählte Option-Label
  const selectedOption = useMemo(() => {
    if (!value) return null;
    return options.find(opt => opt.value === value);
  }, [value, options]);

  // Wenn keine Verknüpfung existiert, zeige normales Input
  if (hasBinding === false) {
    return (
      <Input
        value={value || ''}
        onChange={(e) => onChange(e.target.value || null)}
        placeholder={placeholder}
        disabled={disabled}
        className={className}
      />
    );
  }

  // Lade-Status während Binding geprüft wird
  if (hasBinding === null) {
    return (
      <div className={cn("flex items-center gap-2 h-10 px-3 border rounded-md bg-gray-50", className)}>
        <Loader2 className="h-4 w-4 animate-spin text-muted-foreground" />
        <span className="text-sm text-muted-foreground">Lade...</span>
      </div>
    );
  }

  // Icon basierend auf source_type
  const SourceIcon = binding?.source_type === 'api_query' ? Globe : Database;
  const sourceColor = binding?.source_type === 'api_query' ? 'text-blue-500' : 'text-emerald-500';

  return (
    <Popover open={open} onOpenChange={setOpen}>
      <PopoverTrigger asChild>
        <Button
          variant="outline"
          role="combobox"
          aria-expanded={open}
          disabled={disabled}
          className={cn(
            "w-full justify-between font-normal",
            !value && "text-muted-foreground",
            className
          )}
        >
          <div className="flex items-center gap-2 truncate">
            {showBadge && (
              <SourceIcon className={cn("h-3.5 w-3.5 flex-shrink-0", sourceColor)} />
            )}
            <span className="truncate">
              {selectedOption ? selectedOption.label : (value || placeholder)}
            </span>
          </div>
          <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
        </Button>
      </PopoverTrigger>
      <PopoverContent className="w-[400px] p-0" align="start">
        <Command shouldFilter={false}>
          <div className="flex items-center border-b px-3">
            <Search className="mr-2 h-4 w-4 shrink-0 opacity-50" />
            <input
              placeholder={binding?.source_type === 'api_query' 
                ? `Mind. ${binding.min_search_chars} Zeichen...` 
                : "Suchen..."}
              value={searchValue}
              onChange={(e) => setSearchValue(e.target.value)}
              className="flex h-10 w-full bg-transparent py-3 text-sm outline-none placeholder:text-muted-foreground"
            />
            {searchValue && (
              <Button
                variant="ghost"
                size="icon"
                className="h-6 w-6"
                onClick={() => setSearchValue('')}
              >
                <X className="h-3 w-3" />
              </Button>
            )}
          </div>
          
          {/* Status-Badge */}
          {binding?.source_type === 'api_query' && (
            <div className="px-3 py-1.5 border-b bg-gray-50 flex items-center justify-between">
              <div className="flex items-center gap-1.5 text-xs text-muted-foreground">
                <Globe className="h-3 w-3" />
                <span>Live API: {binding.api_name || 'Unbekannt'}</span>
              </div>
              {isLive && (
                <Badge variant="outline" className="text-[10px] h-5 bg-blue-50 text-blue-600 border-blue-200">
                  Live
                </Badge>
              )}
              {isFallback && (
                <Badge variant="outline" className="text-[10px] h-5 bg-amber-50 text-amber-600 border-amber-200">
                  Fallback
                </Badge>
              )}
            </div>
          )}
          
          <CommandList>
            {isLoading ? (
              <div className="flex items-center justify-center py-6 gap-2">
                <Loader2 className="h-5 w-5 animate-spin text-muted-foreground" />
                <span className="text-sm text-muted-foreground">
                  {binding?.source_type === 'api_query' ? 'API wird abgefragt...' : 'Lade Daten...'}
                </span>
              </div>
            ) : message && options.length === 0 ? (
              <div className="py-6 text-center">
                <AlertCircle className="h-5 w-5 mx-auto mb-2 text-muted-foreground" />
                <p className="text-sm text-muted-foreground">{message}</p>
              </div>
            ) : options.length === 0 ? (
              <CommandEmpty>
                <div className="py-6 text-center text-sm text-muted-foreground">
                  {searchValue ? 'Keine Ergebnisse gefunden' : 'Keine Daten verfügbar'}
                </div>
              </CommandEmpty>
            ) : (
              <ScrollArea className="h-[300px]">
                <CommandGroup>
                  {/* Leer-Option */}
                  {!binding?.is_required && (
                    <CommandItem
                      value=""
                      onSelect={() => {
                        onChange(null);
                        setOpen(false);
                      }}
                      className="text-muted-foreground"
                    >
                      <Check
                        className={cn(
                          "mr-2 h-4 w-4",
                          !value ? "opacity-100" : "opacity-0"
                        )}
                      />
                      <span className="italic">Keine Auswahl</span>
                    </CommandItem>
                  )}
                  
                  {options.map((option) => (
                    <CommandItem
                      key={option.value}
                      value={option.value}
                      onSelect={(currentValue) => {
                        onChange(currentValue === value ? null : currentValue);
                        setOpen(false);
                      }}
                    >
                      <Check
                        className={cn(
                          "mr-2 h-4 w-4",
                          value === option.value ? "opacity-100" : "opacity-0"
                        )}
                      />
                      <div className="flex flex-col">
                        <span className="font-medium">{option.label}</span>
                        {option.display && option.display !== option.label && (
                          <span className="text-xs text-muted-foreground truncate max-w-[300px]">
                            {option.display}
                          </span>
                        )}
                      </div>
                    </CommandItem>
                  ))}
                </CommandGroup>
              </ScrollArea>
            )}
          </CommandList>
        </Command>
      </PopoverContent>
    </Popover>
  );
}

// Hook für einfache Verwendung
export function useReferenceBinding(module: string, fieldName: string) {
  const { accessToken: token } = useAuthStore();
  const [hasBinding, setHasBinding] = useState<boolean | null>(null);
  const [bindingInfo, setBindingInfo] = useState<BindingInfo | null>(null);

  useEffect(() => {
    const checkBinding = async () => {
      try {
        const res = await fetch(
          `${API_URL}/system/field-binding/lookup?module=${module}&field_name=${fieldName}`,
          { headers: { 'Authorization': `Bearer ${token}` } }
        );
        const data = await res.json();
        
        if (data.success && data.data) {
          setHasBinding(true);
          setBindingInfo({
            source_type: data.data.source_type || 'reference_table',
            display_field: data.data.display_field,
            value_field: data.data.value_field,
            is_required: data.data.is_required,
            min_search_chars: data.data.min_search_chars || 3,
          });
        } else {
          setHasBinding(false);
        }
      } catch (error) {
        setHasBinding(false);
      }
    };

    checkBinding();
  }, [module, fieldName, token]);

  return { hasBinding, bindingInfo };
}
