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
  Search,
  Bell,
  Moon,
  Sun,
} from 'lucide-react';
import { useAuthStore } from '@/store/authStore';
import { Button } from '@/components/ui/button';
import { Input } from '@/components/ui/input';
import { cn } from '@/lib/utils';

// Navigation Items
const navItems = [
  { path: '/dashboard', label: 'Dashboard', icon: LayoutDashboard },
  { path: '/adressen', label: 'Adressen', icon: Users },
  { path: '/artikel', label: 'Artikel', icon: Package },
  { path: '/kontrakte', label: 'Kontrakte', icon: FileText },
  { path: '/fuhren', label: 'Fuhren', icon: Truck },
  { path: '/rechnungen', label: 'Rechnungen', icon: Receipt },
];

export function MainLayout() {
  const [sidebarOpen, setSidebarOpen] = useState(true);
  const [sidebarCollapsed, setSidebarCollapsed] = useState(false);
  const [darkMode, setDarkMode] = useState(true);
  const { user, logout } = useAuthStore();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const toggleTheme = () => {
    setDarkMode(!darkMode);
    document.documentElement.classList.toggle('light');
  };

  return (
    <div className="flex h-screen overflow-hidden bg-background">
      {/* Sidebar */}
      <AnimatePresence mode="wait">
        {sidebarOpen && (
          <motion.aside
            initial={{ x: -280 }}
            animate={{ x: 0 }}
            exit={{ x: -280 }}
            transition={{ type: 'spring', stiffness: 300, damping: 30 }}
            className={cn(
              'fixed inset-y-0 left-0 z-50 flex flex-col glass lg:relative',
              sidebarCollapsed ? 'w-20' : 'w-64'
            )}
          >
            {/* Logo */}
            <div className="flex h-16 items-center justify-between px-4 border-b border-border/50">
              {!sidebarCollapsed && (
                <motion.div
                  initial={{ opacity: 0 }}
                  animate={{ opacity: 1 }}
                  className="flex items-center gap-2"
                >
                  <div className="h-8 w-8 rounded-lg bg-primary flex items-center justify-center">
                    <Package className="h-5 w-5 text-primary-foreground" />
                  </div>
                  <span className="font-bold text-lg">Rohstoff</span>
                </motion.div>
              )}
              <Button
                variant="ghost"
                size="icon"
                onClick={() => setSidebarCollapsed(!sidebarCollapsed)}
                className="hidden lg:flex"
              >
                <ChevronLeft className={cn('h-5 w-5 transition-transform', sidebarCollapsed && 'rotate-180')} />
              </Button>
              <Button
                variant="ghost"
                size="icon"
                onClick={() => setSidebarOpen(false)}
                className="lg:hidden"
              >
                <X className="h-5 w-5" />
              </Button>
            </div>

            {/* Navigation */}
            <nav className="flex-1 overflow-y-auto py-4">
              <ul className="space-y-1 px-2">
                {navItems.map((item) => (
                  <li key={item.path}>
                    <NavLink
                      to={item.path}
                      className={({ isActive }) =>
                        cn(
                          'flex items-center gap-3 px-3 py-2.5 rounded-lg transition-all duration-200',
                          'hover:bg-accent hover:text-accent-foreground',
                          isActive && 'bg-primary text-primary-foreground hover:bg-primary/90',
                          sidebarCollapsed && 'justify-center'
                        )
                      }
                    >
                      <item.icon className="h-5 w-5 flex-shrink-0" />
                      {!sidebarCollapsed && (
                        <span className="font-medium">{item.label}</span>
                      )}
                    </NavLink>
                  </li>
                ))}
              </ul>
            </nav>

            {/* User Section */}
            <div className="border-t border-border/50 p-4">
              <div className={cn('flex items-center gap-3', sidebarCollapsed && 'justify-center')}>
                <div className="h-10 w-10 rounded-full bg-primary/20 flex items-center justify-center">
                  <span className="text-sm font-semibold text-primary">
                    {user?.kuerzel || user?.benutzername?.charAt(0).toUpperCase()}
                  </span>
                </div>
                {!sidebarCollapsed && (
                  <div className="flex-1 min-w-0">
                    <p className="text-sm font-medium truncate">
                      {user?.vorname} {user?.nachname}
                    </p>
                    <p className="text-xs text-muted-foreground truncate">
                      {user?.email}
                    </p>
                  </div>
                )}
              </div>
              {!sidebarCollapsed && (
                <div className="mt-4 flex gap-2">
                  <Button variant="ghost" size="sm" className="flex-1">
                    <Settings className="h-4 w-4 mr-2" />
                    Einstellungen
                  </Button>
                  <Button variant="ghost" size="icon" onClick={handleLogout}>
                    <LogOut className="h-4 w-4" />
                  </Button>
                </div>
              )}
            </div>
          </motion.aside>
        )}
      </AnimatePresence>

      {/* Main Content */}
      <div className="flex-1 flex flex-col overflow-hidden">
        {/* Header */}
        <header className="h-16 border-b border-border/50 glass flex items-center justify-between px-4 lg:px-6">
          <div className="flex items-center gap-4">
            <Button
              variant="ghost"
              size="icon"
              onClick={() => setSidebarOpen(!sidebarOpen)}
              className="lg:hidden"
            >
              <Menu className="h-5 w-5" />
            </Button>
            
            {/* Search */}
            <div className="hidden sm:flex items-center gap-2 bg-muted/50 rounded-lg px-3 py-1.5">
              <Search className="h-4 w-4 text-muted-foreground" />
              <Input
                placeholder="Suchen... (Strg+K)"
                className="border-0 bg-transparent h-8 w-64 focus-visible:ring-0"
              />
            </div>
          </div>

          <div className="flex items-center gap-2">
            {/* Theme Toggle */}
            <Button variant="ghost" size="icon" onClick={toggleTheme}>
              {darkMode ? <Sun className="h-5 w-5" /> : <Moon className="h-5 w-5" />}
            </Button>
            
            {/* Notifications */}
            <Button variant="ghost" size="icon" className="relative">
              <Bell className="h-5 w-5" />
              <span className="absolute -top-1 -right-1 h-4 w-4 rounded-full bg-destructive text-[10px] font-bold flex items-center justify-center text-white">
                3
              </span>
            </Button>
          </div>
        </header>

        {/* Page Content */}
        <main className="flex-1 overflow-y-auto p-4 lg:p-6">
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
