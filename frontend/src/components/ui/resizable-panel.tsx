import * as React from "react";
import { cn } from "@/lib/utils";
import { GripVertical } from "lucide-react";

interface ResizablePanelGroupProps {
  children: React.ReactNode;
  className?: string;
  /** Default split ratio for right panel (0-100). Default is 50 */
  defaultRightPanelSize?: number;
  /** Minimum size for panels in percentage */
  minSize?: number;
  /** Maximum size for panels in percentage */
  maxSize?: number;
}

interface ResizablePanelProps {
  children: React.ReactNode;
  className?: string;
}

interface ResizableHandleProps {
  className?: string;
}

interface ResizablePanelContextValue {
  leftWidth: number;
  setLeftWidth: (width: number) => void;
  isDragging: boolean;
  setIsDragging: (dragging: boolean) => void;
  minSize: number;
  maxSize: number;
}

const ResizablePanelContext = React.createContext<ResizablePanelContextValue | null>(null);

const useResizablePanel = () => {
  const context = React.useContext(ResizablePanelContext);
  if (!context) {
    throw new Error("useResizablePanel must be used within a ResizablePanelGroup");
  }
  return context;
};

export function ResizablePanelGroup({
  children,
  className,
  defaultRightPanelSize = 50,
  minSize = 30,
  maxSize = 70,
}: ResizablePanelGroupProps) {
  const [leftWidth, setLeftWidth] = React.useState(100 - defaultRightPanelSize);
  const [isDragging, setIsDragging] = React.useState(false);
  const containerRef = React.useRef<HTMLDivElement>(null);

  const handleMouseMove = React.useCallback(
    (e: MouseEvent) => {
      if (!isDragging || !containerRef.current) return;

      const container = containerRef.current;
      const rect = container.getBoundingClientRect();
      const x = e.clientX - rect.left;
      const percentage = (x / rect.width) * 100;

      // Clamp between minSize and maxSize
      const clampedPercentage = Math.min(Math.max(percentage, minSize), maxSize);
      setLeftWidth(clampedPercentage);
    },
    [isDragging, minSize, maxSize]
  );

  const handleMouseUp = React.useCallback(() => {
    setIsDragging(false);
  }, []);

  React.useEffect(() => {
    if (isDragging) {
      document.addEventListener("mousemove", handleMouseMove);
      document.addEventListener("mouseup", handleMouseUp);
      // Prevent text selection while dragging
      document.body.style.userSelect = "none";
      document.body.style.cursor = "col-resize";
    } else {
      document.body.style.userSelect = "";
      document.body.style.cursor = "";
    }

    return () => {
      document.removeEventListener("mousemove", handleMouseMove);
      document.removeEventListener("mouseup", handleMouseUp);
      document.body.style.userSelect = "";
      document.body.style.cursor = "";
    };
  }, [isDragging, handleMouseMove, handleMouseUp]);

  return (
    <ResizablePanelContext.Provider
      value={{ leftWidth, setLeftWidth, isDragging, setIsDragging, minSize, maxSize }}
    >
      <div
        ref={containerRef}
        className={cn("flex h-full w-full overflow-hidden", className)}
        data-testid="resizable-panel-group"
      >
        {children}
      </div>
    </ResizablePanelContext.Provider>
  );
}

export function ResizablePanel({ children, className }: ResizablePanelProps) {
  return (
    <div className={cn("overflow-auto", className)} data-testid="resizable-panel">
      {children}
    </div>
  );
}

export function ResizableLeftPanel({ children, className }: ResizablePanelProps) {
  const { leftWidth } = useResizablePanel();

  return (
    <div
      className={cn("overflow-auto transition-none", className)}
      style={{ width: `${leftWidth}%` }}
      data-testid="resizable-left-panel"
    >
      {children}
    </div>
  );
}

export function ResizableRightPanel({ children, className }: ResizablePanelProps) {
  const { leftWidth } = useResizablePanel();

  return (
    <div
      className={cn("overflow-auto transition-none", className)}
      style={{ width: `${100 - leftWidth}%` }}
      data-testid="resizable-right-panel"
    >
      {children}
    </div>
  );
}

export function ResizableHandle({ className }: ResizableHandleProps) {
  const { isDragging, setIsDragging } = useResizablePanel();

  const handleMouseDown = (e: React.MouseEvent) => {
    e.preventDefault();
    setIsDragging(true);
  };

  return (
    <div
      className={cn(
        "relative flex w-1.5 items-center justify-center bg-gray-100 transition-colors",
        "cursor-col-resize select-none",
        "hover:bg-emerald-200 active:bg-emerald-300",
        isDragging && "bg-emerald-400",
        className
      )}
      onMouseDown={handleMouseDown}
      data-testid="resizable-handle"
    >
      {/* Visual indicator */}
      <div
        className={cn(
          "absolute inset-y-0 -left-1 -right-1 z-10",
          "flex items-center justify-center"
        )}
      >
        <div
          className={cn(
            "flex h-8 w-4 items-center justify-center rounded-sm",
            "bg-gray-200 opacity-0 transition-opacity",
            "hover:opacity-100",
            isDragging && "opacity-100 bg-emerald-400"
          )}
        >
          <GripVertical className="h-4 w-4 text-gray-500" />
        </div>
      </div>
      
      {/* Wider hit area for easier grabbing */}
      <div className="absolute inset-y-0 -left-2 -right-2" />
    </div>
  );
}
