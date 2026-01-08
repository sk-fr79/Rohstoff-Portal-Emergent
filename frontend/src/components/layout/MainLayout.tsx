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
  ChevronDown,
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
    items: [
      { path: '/berichte', label: 'Berichte', icon: BarChart3 },
    ]
  },
];

export function MainLayout() {
  const [sidebarOpen, setSidebarOpen] = useState(true);
  const [expandedGroups, setExpandedGroups] = useState<string[]>(['Stammdaten', 'Bewegungsdaten']);
  const { user, logout } = useAuthStore();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const toggleGroup = (label: string) => {
    setExpandedGroups(prev => 
      prev.includes(label) 
        ? prev.filter(g => g !== label)
        : [...prev, label]
    );
  };

  return (
    <div className="flex h-screen overflow-hidden bg-gray-50">
      {/* Sidebar - Dark Navy Style */}
      <AnimatePresence mode="wait">
        {sidebarOpen && (
          <motion.aside
            initial={{ x: -260 }}
            animate={{ x: 0 }}
            exit={{ x: -260 }}
            transition={{ type: 'spring', stiffness: 300, damping: 30 }}
            className="fixed inset-y-0 left-0 z-50 w-64 flex flex-col sidebar-enterprise lg:relative"
            style={{ background: 'hsl(220 26% 14%)' }}
          >
            {/* Logo */}
            <div className="flex h-16 items-center justify-between px-4 border-b border-white/10">
              <div className="flex items-center gap-3">
                <div className="h-9 w-9 rounded-lg bg-emerald-500 flex items-center justify-center">
                  <Building2 className="h-5 w-5 text-white" />
                </div>
                <span className="font-bold text-lg text-white">Rohstoff ERP</span>
              </div>
              <Button
                variant="ghost"
                size="icon"
                onClick={() => setSidebarOpen(false)}
                className="text-gray-400 hover:text-white hover:bg-white/10 lg:hidden"
              >
                <X className="h-5 w-5" />
              </Button>
            </div>

            {/* Navigation */}
            <nav className="flex-1 overflow-y-auto py-4 px-3">
              {navGroups.map((group, groupIndex) => (
                <div key={groupIndex} className="mb-2">
                  {group.label ? (
                    <>
                      {/* Group Header */}
                      <button
                        onClick={() => toggleGroup(group.label!)}
                        className="w-full flex items-center justify-between px-3 py-2.5 rounded-lg text-gray-400 hover:bg-white/5 transition-colors"
                      >
                        <div className="flex items-center gap-3">
                          {group.icon && <group.icon className="h-5 w-5" />}
                          <span className="font-medium text-sm">{group.label}</span>
                        </div>
                        <ChevronDown 
                          className={cn(
                            "h-4 w-4 transition-transform",
                            expandedGroups.includes(group.label!) && "rotate-180"
                          )} 
                        />
                      </button>
                      
                      {/* Group Items */}
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
                    </>
                  ) : (
                    /* Ungrouped Items */
                    group.items.map((item) => (
                      <NavLink
                        key={item.path}
                        to={item.path}
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
                    ))
                  )}
                </div>
              ))}
            </nav>

            {/* User Section */}
            <div className="border-t border-white/10 p-4">
              <div className="flex items-center gap-3">
                <div className="h-10 w-10 rounded-full bg-emerald-500/20 flex items-center justify-center">
                  <span className="text-sm font-semibold text-emerald-400">
                    {user?.kuerzel || user?.benutzername?.charAt(0).toUpperCase() || 'A'}
                  </span>
                </div>
                <div className="flex-1 min-w-0">
                  <p className="text-sm font-medium text-white truncate">
                    {user?.vorname || 'Admin'} {user?.nachname || ''}
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
                  className="flex-1 text-gray-400 hover:text-white hover:bg-white/10"
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
            </div>
          </motion.aside>
        )}
      </AnimatePresence>

      {/* Main Content */}
      <div className="flex-1 flex flex-col overflow-hidden">
        {/* Header - Light Style */}
        <header className="h-16 bg-white border-b border-gray-200 flex items-center justify-between px-4 lg:px-6 shadow-sm">
          <div className="flex items-center gap-4">
            <Button
              variant="ghost"
              size="icon"
              onClick={() => setSidebarOpen(!sidebarOpen)}
              className="lg:hidden text-gray-600 hover:text-gray-900"
            >
              <Menu className="h-5 w-5" />
            </Button>
            
            {/* Page Title - Could be dynamic */}
            <h1 className="text-lg font-semibold text-gray-900 hidden sm:block">
              Dashboard
            </h1>
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
            <Button variant="ghost" size="icon" className="text-gray-600 hover:text-gray-900">
              <Settings className="h-5 w-5" />
            </Button>

            {/* User Badge */}
            <div className="hidden sm:flex items-center gap-2 pl-3 border-l border-gray-200">
              <span className="text-sm font-medium text-gray-700">Admin</span>
            </div>
          </div>
        </header>

        {/* Page Content */}
        <main className="flex-1 overflow-y-auto bg-gray-50">
          <Outlet />
        </main>
      </div>

      {/* Mobile Overlay */}
      {sidebarOpen && (
        <div
          className="fixed inset-0 bg-black/50 z-40 lg:hidden"
          onClick={() => setSidebarOpen(false)}
        />
      )}
    </div>
  );
}
