import { cn } from '@/lib/utils';
import { GripVertical } from 'lucide-react';

interface ResizeHandleProps {
  isDragging: boolean;
  onMouseDown: (e: React.MouseEvent) => void;
  className?: string;
}

export function ResizeHandle({ isDragging, onMouseDown, className }: ResizeHandleProps) {
  return (
    <div
      className={cn(
        "relative flex w-1.5 items-center justify-center bg-gray-100 transition-colors cursor-col-resize select-none",
        "hover:bg-emerald-200 active:bg-emerald-300",
        isDragging && "bg-emerald-400",
        className
      )}
      onMouseDown={onMouseDown}
      data-testid="resize-handle"
    >
      {/* Visual grip indicator */}
      <div className={cn(
        "absolute flex h-10 w-5 items-center justify-center rounded-sm",
        "bg-gray-200/80 backdrop-blur-sm opacity-0 transition-opacity",
        "hover:opacity-100",
        isDragging && "opacity-100 bg-emerald-400"
      )}>
        <GripVertical className="h-4 w-4 text-gray-500" />
      </div>
      {/* Wider hit area for easier grabbing */}
      <div className="absolute inset-y-0 -left-2 -right-2" />
    </div>
  );
}
