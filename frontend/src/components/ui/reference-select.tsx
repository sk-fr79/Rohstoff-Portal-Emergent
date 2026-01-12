import { useState, useEffect, useCallback, useMemo } from 'react';
import { useAuthStore } from '@/store/authStore';
import { Check, ChevronsUpDown, Loader2, Search, X, Globe, Database, AlertCircle, ExternalLink, Copy, CheckCheck } from 'lucide-react';
import { cn } from '@/lib/utils';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from '@/components/ui/popover';
import { Badge } from '@/components/ui/badge';
import { ScrollArea } from '@/components/ui/scroll-area';
import { toast } from 'sonner';

const API_URL = import.meta.env.VITE_API_URL || '/api';

export interface ReferenceOption {
  value: string;
  label: string;
  display: string;
  data: Record<string, any>;
}

interface ReferenceSelectProps {
  module: string;
  fieldName: string;
  value?: string | null;
  displayValue?: string | null;  // Gespeicherte Bezeichnung aus der DB für Anzeige
  onChange: (value: string | null) => void;
  onSelectOption?: (option: ReferenceOption | null) => void;  // Callback mit vollständigen Daten
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
  onSelectOption,
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
  const [copiedCode, setCopiedCode] = useState<string | null>(null);
  const [hoveredOption, setHoveredOption] = useState<string | null>(null);

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

  // Lade Optionen wenn Popover öffnet
  useEffect(() => {
    if (open && hasBinding && binding) {
      if (binding.source_type === 'reference_table') {
        loadOptions(searchValue);
      } else {
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

  // Copy Code to clipboard
  const handleCopyCode = (code: string, e: React.MouseEvent) => {
    e.stopPropagation();
    navigator.clipboard.writeText(code);
    setCopiedCode(code);
    toast.success('Code kopiert!');
    setTimeout(() => setCopiedCode(null), 2000);
  };

  // Strip HTML tags and decode entities
  const cleanText = (text: string) => {
    if (!text) return '';
    // Remove HTML tags
    let clean = text.replace(/<[^>]+>/g, '');
    // Decode HTML entities
    clean = clean.replace(/&hellip;/g, '...');
    clean = clean.replace(/&amp;/g, '&');
    clean = clean.replace(/&lt;/g, '<');
    clean = clean.replace(/&gt;/g, '>');
    clean = clean.replace(/&quot;/g, '"');
    return clean.trim();
  };

  // Extract code and description from label
  const parseOption = (option: ReferenceOption) => {
    const code = option.value || '';
    let description = '';
    
    // Try to extract description from label or display
    if (option.display) {
      description = cleanText(option.display);
    } else if (option.label) {
      // Label format: "CODE | DESCRIPTION"
      const parts = option.label.split(' | ');
      if (parts.length > 1) {
        description = cleanText(parts.slice(1).join(' | '));
      } else {
        description = cleanText(option.label);
      }
    }
    
    // Remove code from beginning of description if present
    if (description.startsWith(code)) {
      description = description.substring(code.length).trim();
    }
    
    return { code, description };
  };

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

  // Lade-Status
  if (hasBinding === null) {
    return (
      <div className={cn("flex items-center gap-2 h-10 px-3 border rounded-md bg-gray-50", className)}>
        <Loader2 className="h-4 w-4 animate-spin text-muted-foreground" />
        <span className="text-sm text-muted-foreground">Lade...</span>
      </div>
    );
  }

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
              {selectedOption ? cleanText(selectedOption.label) : (value || placeholder)}
            </span>
          </div>
          <ChevronsUpDown className="ml-2 h-4 w-4 shrink-0 opacity-50" />
        </Button>
      </PopoverTrigger>
      
      <PopoverContent 
        className="w-[600px] p-0" 
        align="start"
        side="bottom"
        sideOffset={4}
      >
        <div className="flex flex-col">
          {/* Search Header */}
          <div className="flex items-center gap-2 p-3 border-b bg-gray-50/80">
            <Search className="h-4 w-4 text-muted-foreground flex-shrink-0" />
            <input
              placeholder={binding?.source_type === 'api_query' 
                ? `Suche (mind. ${binding.min_search_chars} Zeichen)...` 
                : "Suchen..."}
              value={searchValue}
              onChange={(e) => setSearchValue(e.target.value)}
              className="flex-1 bg-transparent text-sm outline-none placeholder:text-muted-foreground"
              autoFocus
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
          
          {/* Source Info Bar */}
          <div className="px-3 py-2 border-b bg-gradient-to-r from-gray-50 to-white flex items-center justify-between">
            <div className="flex items-center gap-2 text-xs text-muted-foreground">
              <SourceIcon className={cn("h-3.5 w-3.5", sourceColor)} />
              <span>
                {binding?.source_type === 'api_query' 
                  ? `Live API: ${binding.api_name || 'Extern'}` 
                  : `Referenztabelle: ${binding?.reference_table_name || 'Lokal'}`}
              </span>
            </div>
            <div className="flex items-center gap-1">
              {isLive && (
                <Badge variant="outline" className="text-[10px] h-5 bg-blue-50 text-blue-600 border-blue-200 animate-pulse">
                  Live
                </Badge>
              )}
              {isFallback && (
                <Badge variant="outline" className="text-[10px] h-5 bg-amber-50 text-amber-600 border-amber-200">
                  Fallback
                </Badge>
              )}
              {options.length > 0 && (
                <Badge variant="outline" className="text-[10px] h-5">
                  {options.length} Treffer
                </Badge>
              )}
            </div>
          </div>

          {/* Options List */}
          <ScrollArea className="h-[400px]">
            {isLoading ? (
              <div className="flex flex-col items-center justify-center py-12 gap-3">
                <Loader2 className="h-8 w-8 animate-spin text-blue-500" />
                <span className="text-sm text-muted-foreground">
                  {binding?.source_type === 'api_query' ? 'API wird abgefragt...' : 'Lade Daten...'}
                </span>
              </div>
            ) : message && options.length === 0 ? (
              <div className="flex flex-col items-center justify-center py-12 gap-3">
                <div className="h-12 w-12 rounded-full bg-gray-100 flex items-center justify-center">
                  <AlertCircle className="h-6 w-6 text-muted-foreground" />
                </div>
                <p className="text-sm text-muted-foreground text-center max-w-[300px]">{message}</p>
              </div>
            ) : options.length === 0 ? (
              <div className="flex flex-col items-center justify-center py-12 gap-3">
                <div className="h-12 w-12 rounded-full bg-gray-100 flex items-center justify-center">
                  <Search className="h-6 w-6 text-muted-foreground" />
                </div>
                <p className="text-sm text-muted-foreground">
                  {searchValue ? 'Keine Ergebnisse gefunden' : 'Suchbegriff eingeben'}
                </p>
              </div>
            ) : (
              <div className="p-2">
                {/* Keine Auswahl Option */}
                {!binding?.is_required && (
                  <button
                    onClick={() => {
                      onChange(null);
                      onSelectOption?.(null);
                      setOpen(false);
                    }}
                    className={cn(
                      "w-full flex items-center gap-3 p-3 rounded-lg text-left transition-all",
                      "hover:bg-gray-100 group",
                      !value && "bg-gray-50"
                    )}
                  >
                    <div className={cn(
                      "h-5 w-5 rounded border-2 flex items-center justify-center flex-shrink-0",
                      !value ? "border-emerald-500 bg-emerald-500" : "border-gray-300"
                    )}>
                      {!value && <Check className="h-3 w-3 text-white" />}
                    </div>
                    <span className="text-sm text-muted-foreground italic">Keine Auswahl</span>
                  </button>
                )}
                
                {/* Options */}
                {options.map((option) => {
                  const { code, description } = parseOption(option);
                  const isSelected = value === option.value;
                  const isHovered = hoveredOption === option.value;
                  
                  return (
                    <button
                      key={option.value}
                      onClick={() => {
                        onChange(option.value);
                        onSelectOption?.(option);
                        setOpen(false);
                      }}
                      onMouseEnter={() => setHoveredOption(option.value)}
                      onMouseLeave={() => setHoveredOption(null)}
                      className={cn(
                        "w-full flex items-start gap-3 p-3 rounded-lg text-left transition-all mb-1",
                        "hover:bg-gradient-to-r hover:from-emerald-50 hover:to-white",
                        "border border-transparent",
                        isSelected && "bg-emerald-50 border-emerald-200",
                        isHovered && !isSelected && "border-gray-200"
                      )}
                    >
                      {/* Checkbox */}
                      <div className={cn(
                        "h-5 w-5 rounded border-2 flex items-center justify-center flex-shrink-0 mt-0.5",
                        isSelected ? "border-emerald-500 bg-emerald-500" : "border-gray-300"
                      )}>
                        {isSelected && <Check className="h-3 w-3 text-white" />}
                      </div>
                      
                      {/* Content */}
                      <div className="flex-1 min-w-0">
                        {/* Code Badge Row */}
                        <div className="flex items-center gap-2 mb-1">
                          <Badge 
                            variant="outline" 
                            className={cn(
                              "font-mono text-xs px-2 py-0.5",
                              isSelected 
                                ? "bg-emerald-100 text-emerald-700 border-emerald-300" 
                                : "bg-gray-100 text-gray-700"
                            )}
                          >
                            {code}
                          </Badge>
                          
                          {/* Copy Button */}
                          <button
                            onClick={(e) => handleCopyCode(code, e)}
                            className={cn(
                              "p-1 rounded hover:bg-gray-200 transition-colors",
                              "opacity-0 group-hover:opacity-100",
                              isHovered && "opacity-100"
                            )}
                            title="Code kopieren"
                          >
                            {copiedCode === code ? (
                              <CheckCheck className="h-3 w-3 text-emerald-500" />
                            ) : (
                              <Copy className="h-3 w-3 text-gray-400" />
                            )}
                          </button>
                          
                          {/* External Link (if detail_url exists) */}
                          {option.data?.detail_url && (
                            <a
                              href={option.data.detail_url}
                              target="_blank"
                              rel="noopener noreferrer"
                              onClick={(e) => e.stopPropagation()}
                              className={cn(
                                "p-1 rounded hover:bg-gray-200 transition-colors",
                                "opacity-0",
                                isHovered && "opacity-100"
                              )}
                              title="Details öffnen"
                            >
                              <ExternalLink className="h-3 w-3 text-gray-400" />
                            </a>
                          )}
                        </div>
                        
                        {/* Description */}
                        <p className={cn(
                          "text-sm leading-relaxed",
                          isSelected ? "text-gray-900" : "text-gray-600"
                        )}>
                          {description || code}
                        </p>
                      </div>
                    </button>
                  );
                })}
              </div>
            )}
          </ScrollArea>
          
          {/* Footer */}
          {options.length > 0 && (
            <div className="px-3 py-2 border-t bg-gray-50/50 text-xs text-muted-foreground flex items-center justify-between">
              <span>↑↓ Navigieren • Enter Auswählen • Esc Schließen</span>
              {binding?.source_type === 'api_query' && (
                <span className="text-blue-500">Echtzeit-Suche aktiv</span>
              )}
            </div>
          )}
        </div>
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
