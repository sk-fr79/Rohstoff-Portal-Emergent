import { useState } from 'react';
import { Outlet, NavLink, useNavigate } from 'react-router-dom';
import { motion, AnimatePresence } from 'framer-motion';
import {
  LayoutDashboard,
  Users,
  Package,
  FileText,
  Truck,
  Receipt,
  Settings,
  LogOut,
  Menu,
  X,
  ChevronLeft,
  ChevronRight,
  ChevronDown,
  ChevronUp,
  Bell,
  Database,
  Warehouse,
  Scale,
  BarChart3,
  Building2,
} from 'lucide-react';
import { useAuthStore } from '@/store/authStore';
import { Button } from '@/components/ui/button';
import { cn } from '@/lib/utils';
import { Tooltip, TooltipContent, TooltipProvider, TooltipTrigger } from '@/components/ui/tooltip';

// Navigation Items with grouping like the reference design
const navGroups = [
  {
    items: [
      { path: '/dashboard', label: 'Dashboard', icon: LayoutDashboard },
    ]
  },
  {
    label: 'Stammdaten',
    icon: Database,
    items: [
      { path: '/adressen', label: 'Adressen', icon: Users },
      { path: '/artikel', label: 'Artikel', icon: Package },
    ]
  },
  {
    label: 'Bewegungsdaten',
    icon: FileText,
    items: [
      { path: '/kontrakte', label: 'Kontrakte', icon: FileText },
    ]
  },
  {
    items: [
      { path: '/rechnungen', label: 'Rechnungen', icon: Receipt },
    ]
  },
  {
    label: 'Lager',
    icon: Warehouse,
    items: [
      { path: '/fuhren', label: 'Fuhren', icon: Truck },
    ]
  },
  {
    label: 'Waage',
    icon: Scale,
    items: [
      { path: '/wiegekarten', label: 'Wiegekarten', icon: Scale },
    ]
  },
  {
    items: [
      { path: '/berichte', label: 'Berichte', icon: BarChart3 },
    ]
  },
];

export function MainLayout() {
  const [sidebarCollapsed, setSidebarCollapsed] = useState(false);
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
  const [expandedGroups, setExpandedGroups] = useState<string[]>(['Stammdaten', 'Bewegungsdaten', 'Waage']);
  const [savedExpandedGroups, setSavedExpandedGroups] = useState<string[]>([]);
  const { user, logout } = useAuthStore();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  // Beim Ein-/Ausklappen der Sidebar die Gruppen-Zustände speichern/wiederherstellen
  const toggleSidebar = () => {
    if (sidebarCollapsed) {
      // Ausklappen: Gruppen wiederherstellen
      setSidebarCollapsed(false);
      setExpandedGroups(savedExpandedGroups);
    } else {
      // Einklappen: Gruppen speichern und alle schließen
      setSavedExpandedGroups(expandedGroups);
      setExpandedGroups([]);
      setSidebarCollapsed(true);
    }
  };

  const toggleGroup = (label: string) => {
    if (sidebarCollapsed) {
      // Wenn eingeklappt, zuerst ausklappen und nur diese Gruppe öffnen
      setSavedExpandedGroups(expandedGroups);
      setSidebarCollapsed(false);
      setExpandedGroups([label]);
    } else {
      setExpandedGroups(prev => 
        prev.includes(label) 
          ? prev.filter(g => g !== label)
          : [...prev, label]
      );
    }
  };

  // Sidebar content component to avoid duplication
  const SidebarContent = ({ collapsed = false, isMobile = false }: { collapsed?: boolean; isMobile?: boolean }) => (
    <>
      {/* Logo */}
      <div className={cn(
        "flex h-16 items-center border-b border-white/10",
        collapsed ? "justify-center px-3" : "justify-between px-4"
      )}>
        <div className={cn("flex items-center", collapsed ? "justify-center" : "gap-3")}>
          {collapsed ? (
            // Collapsed: Show only "MV" text as logo
            <div className="h-10 w-10 rounded-lg bg-gradient-to-br from-red-500 to-red-700 flex items-center justify-center">
              <span className="text-white font-bold text-sm">MV</span>
            </div>
          ) : (
            // Expanded: Show full logo
            <>
              <img 
                src="/mv_logo.png" 
                alt="MV Logo" 
                className="h-9 w-auto object-contain"
              />
              <span className="font-bold text-lg text-white whitespace-nowrap">Rohstoff Portal</span>
            </>
          )}
        </div>
        {isMobile && (
          <Button
            variant="ghost"
            size="icon"
            onClick={() => setMobileMenuOpen(false)}
            className="text-gray-400 hover:text-white hover:bg-white/10"
          >
            <X className="h-5 w-5" />
          </Button>
        )}
      </div>

      {/* Navigation */}
      <nav className={cn("flex-1 overflow-y-auto py-4", collapsed ? "px-2" : "px-3")}>
        <TooltipProvider delayDuration={0}>
          {navGroups.map((group, groupIndex) => (
            <div key={groupIndex} className="mb-2">
              {group.label ? (
                <>
                  {/* Group Header */}
                  {collapsed ? (
                    // Collapsed: Show only icon with tooltip
                    <Tooltip>
                      <TooltipTrigger asChild>
                        <button
                          onClick={() => toggleGroup(group.label!)}
                          className={cn(
                            "w-full flex items-center justify-center p-2.5 rounded-lg transition-colors",
                            "text-gray-400 hover:bg-white/10 hover:text-white",
                            expandedGroups.includes(group.label!) && "bg-white/5"
                          )}
                        >
                          {group.icon && <group.icon className="h-5 w-5" />}
                        </button>
                      </TooltipTrigger>
                      <TooltipContent side="right" className="bg-slate-800 text-white border-slate-700">
                        <p>{group.label}</p>
                      </TooltipContent>
                    </Tooltip>
                  ) : (
                    // Expanded: Show full group header
                    <button
                      onClick={() => toggleGroup(group.label!)}
                      className="w-full flex items-center justify-between px-3 py-2.5 rounded-lg text-gray-400 hover:bg-white/5 transition-colors"
                    >
                      <div className="flex items-center gap-3">
                        {group.icon && <group.icon className="h-5 w-5" />}
                        <span className="font-medium text-sm">{group.label}</span>
                      </div>
                      {expandedGroups.includes(group.label!) ? (
                        <ChevronUp className="h-4 w-4" />
                      ) : (
                        <ChevronDown className="h-4 w-4" />
                      )}
                    </button>
                  )}
                  
                  {/* Group Items - Only show when expanded AND sidebar not collapsed */}
                  {!collapsed && (
                    <AnimatePresence>
                      {expandedGroups.includes(group.label!) && (
                        <motion.div
                          initial={{ height: 0, opacity: 0 }}
                          animate={{ height: 'auto', opacity: 1 }}
                          exit={{ height: 0, opacity: 0 }}
                          transition={{ duration: 0.2 }}
                          className="overflow-hidden"
                        >
                          <div className="pl-4 mt-1 space-y-1">
                            {group.items.map((item) => (
                              <NavLink
                                key={item.path}
                                to={item.path}
                                onClick={() => isMobile && setMobileMenuOpen(false)}
                                className={({ isActive }) =>
                                  cn(
                                    'flex items-center gap-3 px-3 py-2 rounded-lg transition-all duration-200',
                                    'text-gray-400 hover:bg-white/10 hover:text-white',
                                    isActive && 'bg-emerald-500 text-white hover:bg-emerald-600'
                                  )
                                }
                              >
                                <item.icon className="h-4 w-4 flex-shrink-0" />
                                <span className="text-sm">{item.label}</span>
                              </NavLink>
                            ))}
                          </div>
                        </motion.div>
                      )}
                    </AnimatePresence>
                  )}
                </>
              ) : (
                /* Ungrouped Items */
                group.items.map((item) => (
                  collapsed ? (
                    <Tooltip key={item.path}>
                      <TooltipTrigger asChild>
                        <NavLink
                          to={item.path}
                          onClick={() => isMobile && setMobileMenuOpen(false)}
                          className={({ isActive }) =>
                            cn(
                              'flex items-center justify-center p-2.5 rounded-lg transition-all duration-200',
                              'text-gray-400 hover:bg-white/10 hover:text-white',
                              isActive && 'bg-emerald-500 text-white hover:bg-emerald-600'
                            )
                          }
                        >
                          <item.icon className="h-5 w-5 flex-shrink-0" />
                        </NavLink>
                      </TooltipTrigger>
                      <TooltipContent side="right" className="bg-slate-800 text-white border-slate-700">
                        <p>{item.label}</p>
                      </TooltipContent>
                    </Tooltip>
                  ) : (
                    <NavLink
                      key={item.path}
                      to={item.path}
                      onClick={() => isMobile && setMobileMenuOpen(false)}
                      className={({ isActive }) =>
                        cn(
                          'flex items-center gap-3 px-3 py-2.5 rounded-lg transition-all duration-200',
                          'text-gray-400 hover:bg-white/10 hover:text-white',
                          isActive && 'bg-emerald-500 text-white hover:bg-emerald-600'
                        )
                      }
                    >
                      <item.icon className="h-5 w-5 flex-shrink-0" />
                      <span className="font-medium text-sm">{item.label}</span>
                    </NavLink>
                  )
                ))
              )}
            </div>
          ))}
        </TooltipProvider>
      </nav>

      {/* User Section */}
      <div className={cn("border-t border-white/10", collapsed ? "p-3" : "p-4")}>
        {collapsed ? (
          // Collapsed: Show only avatar with tooltip
          <TooltipProvider delayDuration={0}>
            <div className="flex flex-col items-center gap-2">
              <Tooltip>
                <TooltipTrigger asChild>
                  <div className="h-10 w-10 rounded-full bg-emerald-500/20 flex items-center justify-center cursor-pointer">
                    <span className="text-sm font-semibold text-emerald-400">
                      {user?.kuerzel || user?.benutzername?.charAt(0).toUpperCase() || 'A'}
                    </span>
                  </div>
                </TooltipTrigger>
                <TooltipContent side="right" className="bg-slate-800 text-white border-slate-700">
                  <p>{user?.vorname || 'System Administrator'}</p>
                  <p className="text-xs text-gray-400">{user?.email || 'admin@demo.local'}</p>
                </TooltipContent>
              </Tooltip>
              
              <Tooltip>
                <TooltipTrigger asChild>
                  <Button 
                    variant="ghost" 
                    size="icon" 
                    className="text-gray-400 hover:text-white hover:bg-white/10"
                    onClick={() => navigate('/einstellungen')}
                  >
                    <Settings className="h-5 w-5" />
                  </Button>
                </TooltipTrigger>
                <TooltipContent side="right" className="bg-slate-800 text-white border-slate-700">
                  <p>Einstellungen</p>
                </TooltipContent>
              </Tooltip>
              
              <Tooltip>
                <TooltipTrigger asChild>
                  <Button 
                    variant="ghost" 
                    size="icon" 
                    onClick={handleLogout}
                    className="text-gray-400 hover:text-white hover:bg-white/10"
                  >
                    <LogOut className="h-5 w-5" />
                  </Button>
                </TooltipTrigger>
                <TooltipContent side="right" className="bg-slate-800 text-white border-slate-700">
                  <p>Abmelden</p>
                </TooltipContent>
              </Tooltip>
            </div>
          </TooltipProvider>
        ) : (
          // Expanded: Show full user info
          <>
            <div className="flex items-center gap-3">
              <div className="h-10 w-10 rounded-full bg-emerald-500/20 flex items-center justify-center flex-shrink-0">
                <span className="text-sm font-semibold text-emerald-400">
                  {user?.kuerzel || user?.benutzername?.charAt(0).toUpperCase() || 'A'}
                </span>
              </div>
              <div className="flex-1 min-w-0">
                <p className="text-sm font-medium text-white truncate">
                  {user?.vorname || 'System'} {user?.nachname || 'Administrator'}
                </p>
                <p className="text-xs text-gray-400 truncate">
                  {user?.email || 'admin@demo.local'}
                </p>
              </div>
            </div>
            <div className="mt-4 flex gap-2">
              <Button 
                variant="ghost" 
                size="sm" 
                className="flex-1 text-gray-400 hover:text-white hover:bg-white/10 justify-start"
                onClick={() => navigate('/einstellungen')}
              >
                <Settings className="h-4 w-4 mr-2" />
                Einstellungen
              </Button>
              <Button 
                variant="ghost" 
                size="icon" 
                onClick={handleLogout}
                className="text-gray-400 hover:text-white hover:bg-white/10"
              >
                <LogOut className="h-4 w-4" />
              </Button>
            </div>
          </>
        )}
      </div>
    </>
  );

  return (
    <div className="flex h-screen overflow-hidden bg-gray-50">
      {/* Desktop Sidebar */}
      <motion.aside
        initial={false}
        animate={{ width: sidebarCollapsed ? 72 : 260 }}
        transition={{ type: 'spring', stiffness: 300, damping: 30 }}
        className="hidden lg:flex flex-col sidebar-enterprise flex-shrink-0"
        style={{ background: 'hsl(220 26% 14%)' }}
      >
        <SidebarContent collapsed={sidebarCollapsed} />
      </motion.aside>

      {/* Mobile Sidebar */}
      <AnimatePresence>
        {mobileMenuOpen && (
          <>
            <motion.div
              initial={{ opacity: 0 }}
              animate={{ opacity: 1 }}
              exit={{ opacity: 0 }}
              className="fixed inset-0 bg-black/50 z-40 lg:hidden"
              onClick={() => setMobileMenuOpen(false)}
            />
            <motion.aside
              initial={{ x: -260 }}
              animate={{ x: 0 }}
              exit={{ x: -260 }}
              transition={{ type: 'spring', stiffness: 300, damping: 30 }}
              className="fixed inset-y-0 left-0 z-50 w-64 flex flex-col lg:hidden"
              style={{ background: 'hsl(220 26% 14%)' }}
            >
              <SidebarContent isMobile />
            </motion.aside>
          </>
        )}
      </AnimatePresence>

      {/* Main Content */}
      <div className="flex-1 flex flex-col overflow-hidden">
        {/* Header - Light Style */}
        <header className="h-16 bg-white border-b border-gray-200 flex items-center justify-between px-4 lg:px-6 shadow-sm flex-shrink-0">
          <div className="flex items-center gap-4">
            {/* Mobile menu button */}
            <Button
              variant="ghost"
              size="icon"
              onClick={() => setMobileMenuOpen(true)}
              className="lg:hidden text-gray-600 hover:text-gray-900"
            >
              <Menu className="h-5 w-5" />
            </Button>
            
            {/* Desktop collapse button */}
            <Button
              variant="ghost"
              size="icon"
              onClick={toggleSidebar}
              className="hidden lg:flex text-gray-600 hover:text-gray-900"
            >
              {sidebarCollapsed ? (
                <ChevronRight className="h-5 w-5" />
              ) : (
                <ChevronLeft className="h-5 w-5" />
              )}
            </Button>
          </div>

          <div className="flex items-center gap-3">
            {/* Notifications */}
            <Button variant="ghost" size="icon" className="relative text-gray-600 hover:text-gray-900">
              <Bell className="h-5 w-5" />
              <span className="absolute -top-1 -right-1 h-4 w-4 rounded-full bg-red-500 text-[10px] font-bold flex items-center justify-center text-white">
                3
              </span>
            </Button>
            
            {/* Settings */}
            <Button 
              variant="ghost" 
              size="icon" 
              className="text-gray-600 hover:text-gray-900"
              onClick={() => navigate('/einstellungen')}
            >
              <Settings className="h-5 w-5" />
            </Button>

            {/* User Badge */}
            <div className="hidden sm:flex items-center gap-2 pl-3 border-l border-gray-200">
              <span className="text-sm font-medium text-gray-700">
                {user?.kuerzel || 'ADM'}
              </span>
            </div>
          </div>
        </header>

        {/* Page Content */}
        <main className="flex-1 overflow-y-auto bg-gray-50">
          <Outlet />
        </main>
      </div>
    </div>
  );
}
