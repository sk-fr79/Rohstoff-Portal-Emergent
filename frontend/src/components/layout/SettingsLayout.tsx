import { useState } from 'react';
import { Outlet, NavLink, useNavigate } from 'react-router-dom';
import { motion, AnimatePresence } from 'framer-motion';
import {
  Settings,
  LogOut,
  Menu,
  X,
  ChevronLeft,
  ChevronRight,
  User,
  Lock,
  Mail,
  Image,
  PenTool,
  Building2,
  Shield,
  Database,
  Users,
  Cog,
  ArrowLeft,
} from 'lucide-react';
import { useAuthStore } from '@/store/authStore';
import { usePermissionsStore } from '@/store/permissionsStore';
import { Button } from '@/components/ui/button';
import { cn } from '@/lib/utils';
import { Tooltip, TooltipContent, TooltipProvider, TooltipTrigger } from '@/components/ui/tooltip';

// Navigation Items für Einstellungen
const userSettingsItems = [
  { path: '/einstellungen/profil', label: 'Mein Profil', icon: User },
  { path: '/einstellungen/kontakt', label: 'Kontaktdaten', icon: Mail },
  { path: '/einstellungen/adresse', label: 'Adresse', icon: Building2 },
  { path: '/einstellungen/passwort', label: 'Passwort ändern', icon: Lock },
  { path: '/einstellungen/profilbild', label: 'Profilbild', icon: Image },
  { path: '/einstellungen/signatur', label: 'E-Mail Signatur', icon: Mail },
  { path: '/einstellungen/unterschrift', label: 'Unterschrift', icon: PenTool },
];

const adminSettingsItems = [
  { path: '/einstellungen/benutzer', label: 'Benutzerverwaltung', icon: Users },
  { path: '/einstellungen/rollen', label: 'Benutzerrollen', icon: Shield },
  { path: '/einstellungen/abteilungen', label: 'Abteilungen', icon: Building2 },
  { path: '/einstellungen/berechtigungen', label: 'Zugriffssteuerung', icon: Shield },
  { path: '/einstellungen/system', label: 'Systemeinstellungen', icon: Cog },
  { path: '/einstellungen/datenbank', label: 'Datenbank', icon: Database },
];

export function SettingsLayout() {
  const [sidebarCollapsed, setSidebarCollapsed] = useState(false);
  const [mobileMenuOpen, setMobileMenuOpen] = useState(false);
  const { user, logout } = useAuthStore();
  const { istAdmin } = usePermissionsStore();
  const navigate = useNavigate();

  // Prüfen ob Benutzer Admin-Zugriff hat (aus Auth oder Permissions Store)
  const hasAdminAccess = user?.istAdmin || istAdmin;

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  const handleBackToERP = () => {
    navigate('/dashboard');
  };

  const toggleSidebar = () => {
    setSidebarCollapsed(!sidebarCollapsed);
  };

  // Sidebar content component
  const SidebarContent = ({ collapsed = false, isMobile = false }: { collapsed?: boolean; isMobile?: boolean }) => (
    <>
      {/* Logo / Header */}
      <div className={cn(
        "flex h-16 items-center border-b border-white/10",
        collapsed ? "justify-center px-3" : "justify-between px-4"
      )}>
        <div className={cn("flex items-center", collapsed ? "justify-center" : "gap-3")}>
          {collapsed ? (
            <div className="h-10 w-10 rounded-lg bg-gradient-to-br from-slate-500 to-slate-700 flex items-center justify-center">
              <Settings className="h-5 w-5 text-white" />
            </div>
          ) : (
            <>
              <div className="h-9 w-9 rounded-lg bg-gradient-to-br from-slate-500 to-slate-700 flex items-center justify-center">
                <Settings className="h-5 w-5 text-white" />
              </div>
              <span className="font-bold text-lg text-white whitespace-nowrap">Einstellungen</span>
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
          {/* Benutzer-Einstellungen */}
          <div className="mb-4">
            {!collapsed && (
              <p className="px-3 mb-2 text-xs font-semibold text-gray-500 uppercase tracking-wider">
                Meine Einstellungen
              </p>
            )}
            <div className="space-y-1">
              {userSettingsItems.map((item) => (
                collapsed ? (
                  <Tooltip key={item.path}>
                    <TooltipTrigger asChild>
                      <NavLink
                        to={item.path}
                        onClick={() => isMobile && setMobileMenuOpen(false)}
                        className={({ isActive }) =>
                          cn(
                            'w-full h-10 flex items-center justify-center rounded-lg transition-all duration-200',
                            'text-gray-400 hover:bg-white/10 hover:text-white',
                            isActive && 'bg-blue-500 text-white hover:bg-blue-600'
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
                        'flex items-center gap-3 px-3 py-2 rounded-lg transition-all duration-200',
                        'text-gray-400 hover:bg-white/10 hover:text-white',
                        isActive && 'bg-blue-500 text-white hover:bg-blue-600'
                      )
                    }
                  >
                    <item.icon className="h-4 w-4 flex-shrink-0" />
                    <span className="text-sm">{item.label}</span>
                  </NavLink>
                )
              ))}
            </div>
          </div>

          {/* Admin-Einstellungen (nur für Administratoren) */}
          {hasAdminAccess && (
            <div className="mb-4 pt-4 border-t border-white/10">
              {!collapsed && (
                <p className="px-3 mb-2 text-xs font-semibold text-gray-500 uppercase tracking-wider">
                  Administration
                </p>
              )}
              <div className="space-y-1">
                {adminSettingsItems.map((item) => (
                  collapsed ? (
                    <Tooltip key={item.path}>
                      <TooltipTrigger asChild>
                        <NavLink
                          to={item.path}
                          onClick={() => isMobile && setMobileMenuOpen(false)}
                          className={({ isActive }) =>
                            cn(
                              'w-full h-10 flex items-center justify-center rounded-lg transition-all duration-200',
                              'text-gray-400 hover:bg-white/10 hover:text-white',
                              isActive && 'bg-amber-500 text-white hover:bg-amber-600'
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
                          'flex items-center gap-3 px-3 py-2 rounded-lg transition-all duration-200',
                          'text-gray-400 hover:bg-white/10 hover:text-white',
                          isActive && 'bg-amber-500 text-white hover:bg-amber-600'
                        )
                      }
                    >
                      <item.icon className="h-4 w-4 flex-shrink-0" />
                      <span className="text-sm">{item.label}</span>
                    </NavLink>
                  )
                ))}
              </div>
            </div>
          )}
        </TooltipProvider>
      </nav>

      {/* User Section */}
      <div className={cn("border-t border-white/10", collapsed ? "p-3" : "p-4")}>
        {/* Zurück ins ERP-Portal Button */}
        {collapsed ? (
          <TooltipProvider delayDuration={0}>
            <Tooltip>
              <TooltipTrigger asChild>
                <Button 
                  variant="ghost" 
                  size="icon" 
                  onClick={handleBackToERP}
                  className="w-full h-10 mb-3 text-emerald-400 hover:text-white hover:bg-emerald-500/20"
                  data-testid="sidebar-back-to-erp-btn"
                >
                  <ArrowLeft className="h-5 w-5" />
                </Button>
              </TooltipTrigger>
              <TooltipContent side="right" className="bg-slate-800 text-white border-slate-700">
                <p>Zurück ins ERP-Portal</p>
              </TooltipContent>
            </Tooltip>
          </TooltipProvider>
        ) : (
          <Button 
            variant="ghost" 
            size="sm" 
            onClick={handleBackToERP}
            className="w-full mb-3 text-emerald-400 hover:text-white hover:bg-emerald-500/20 justify-start"
            data-testid="sidebar-back-to-erp-btn"
          >
            <ArrowLeft className="h-4 w-4 mr-2" />
            Zurück ins ERP-Portal
          </Button>
        )}
        
        {collapsed ? (
          <TooltipProvider delayDuration={0}>
            <div className="flex flex-col items-center gap-2">
              <Tooltip>
                <TooltipTrigger asChild>
                  {user?.profilbild ? (
                    <img 
                      src={user.profilbild} 
                      alt="Profilbild" 
                      className="h-10 w-10 rounded-full object-cover cursor-pointer"
                    />
                  ) : (
                    <div className="h-10 w-10 rounded-full bg-blue-500/20 flex items-center justify-center cursor-pointer">
                      <span className="text-sm font-semibold text-blue-400">
                        {user?.kuerzel || user?.benutzername?.charAt(0).toUpperCase() || 'A'}
                      </span>
                    </div>
                  )}
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
          <>
            <div className="flex items-center gap-3">
              {user?.profilbild ? (
                <img 
                  src={user.profilbild} 
                  alt="Profilbild" 
                  className="h-10 w-10 rounded-full object-cover flex-shrink-0"
                />
              ) : (
                <div className="h-10 w-10 rounded-full bg-blue-500/20 flex items-center justify-center flex-shrink-0">
                  <span className="text-sm font-semibold text-blue-400">
                    {user?.kuerzel || user?.benutzername?.charAt(0).toUpperCase() || 'A'}
                  </span>
                </div>
              )}
              <div className="flex-1 min-w-0">
                <p className="text-sm font-medium text-white truncate">
                  {user?.vorname || 'System'} {user?.nachname || 'Administrator'}
                </p>
                <p className="text-xs text-gray-400 truncate">
                  {user?.email || 'admin@demo.local'}
                </p>
              </div>
            </div>
            <div className="mt-4">
              <Button 
                variant="ghost" 
                size="sm" 
                onClick={handleLogout}
                className="w-full text-gray-400 hover:text-white hover:bg-white/10 justify-start"
              >
                <LogOut className="h-4 w-4 mr-2" />
                Abmelden
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
        className="hidden lg:flex flex-col flex-shrink-0"
        style={{ background: 'hsl(220 20% 18%)' }}
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
              style={{ background: 'hsl(220 20% 18%)' }}
            >
              <SidebarContent isMobile />
            </motion.aside>
          </>
        )}
      </AnimatePresence>

      {/* Main Content */}
      <div className="flex-1 flex flex-col overflow-hidden">
        {/* Header */}
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

          {/* Zurück zum ERP Button */}
          <div className="flex items-center gap-3">
            <Button 
              variant="outline" 
              size="sm"
              onClick={handleBackToERP}
              className="flex items-center gap-2 text-gray-700 border-gray-300 hover:bg-gray-50"
              data-testid="back-to-erp-btn"
            >
              <ArrowLeft className="h-4 w-4" />
              <span className="hidden sm:inline">Zurück ins ERP-Portal</span>
            </Button>

            {/* User Badge with Profile Picture */}
            <div className="hidden sm:flex items-center gap-2 pl-3 border-l border-gray-200">
              {user?.profilbild ? (
                <img 
                  src={user.profilbild} 
                  alt="Profilbild" 
                  className="h-8 w-8 rounded-full object-cover"
                  data-testid="header-profile-image"
                />
              ) : (
                <div className="h-8 w-8 rounded-full bg-blue-100 flex items-center justify-center">
                  <span className="text-sm font-medium text-blue-600" data-testid="header-profile-initials">
                    {user?.kuerzel || 'ADM'}
                  </span>
                </div>
              )}
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
