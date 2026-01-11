import { useState, useCallback, useEffect, useRef, RefObject } from 'react';

interface UseResizablePanelOptions {
  defaultWidth?: number; // Standard-Breite der linken Seite in %
  minWidth?: number;     // Minimale Breite in %
  maxWidth?: number;     // Maximale Breite in %
}

interface UseResizablePanelReturn {
  panelWidth: number;
  isDragging: boolean;
  containerRef: RefObject<HTMLDivElement>;
  startDragging: (e: React.MouseEvent) => void;
  leftPanelStyle: { width: string };
  rightPanelStyle: { width: string };
}

export function useResizablePanel({
  defaultWidth = 50,
  minWidth = 30,
  maxWidth = 70,
}: UseResizablePanelOptions = {}): UseResizablePanelReturn {
  const [panelWidth, setPanelWidth] = useState(defaultWidth);
  const [isDragging, setIsDragging] = useState(false);
  const containerRef = useRef<HTMLDivElement>(null);

  const handleMouseMove = useCallback(
    (e: MouseEvent) => {
      if (!isDragging || !containerRef.current) return;

      const container = containerRef.current;
      const rect = container.getBoundingClientRect();
      const x = e.clientX - rect.left;
      const percentage = (x / rect.width) * 100;

      // Clamp zwischen minWidth und maxWidth
      const clampedPercentage = Math.min(Math.max(percentage, minWidth), maxWidth);
      setPanelWidth(clampedPercentage);
    },
    [isDragging, minWidth, maxWidth]
  );

  const handleMouseUp = useCallback(() => {
    setIsDragging(false);
  }, []);

  useEffect(() => {
    if (isDragging) {
      document.addEventListener('mousemove', handleMouseMove);
      document.addEventListener('mouseup', handleMouseUp);
      document.body.style.userSelect = 'none';
      document.body.style.cursor = 'col-resize';
    } else {
      document.body.style.userSelect = '';
      document.body.style.cursor = '';
    }

    return () => {
      document.removeEventListener('mousemove', handleMouseMove);
      document.removeEventListener('mouseup', handleMouseUp);
      document.body.style.userSelect = '';
      document.body.style.cursor = '';
    };
  }, [isDragging, handleMouseMove, handleMouseUp]);

  const startDragging = useCallback((e: React.MouseEvent) => {
    e.preventDefault();
    setIsDragging(true);
  }, []);

  return {
    panelWidth,
    isDragging,
    containerRef: containerRef as RefObject<HTMLDivElement>,
    startDragging,
    leftPanelStyle: { width: `${panelWidth}%` },
    rightPanelStyle: { width: `${100 - panelWidth}%` },
  };
}
