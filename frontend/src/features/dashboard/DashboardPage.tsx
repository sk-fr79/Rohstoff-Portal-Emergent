import { motion } from 'framer-motion';
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import {
  Users,
  Package,
  FileText,
  Truck,
  TrendingUp,
  TrendingDown,
  Euro,
  ArrowRight,
  Plus,
  Receipt,
  Settings,
  Clock,
  CheckCircle2,
  AlertCircle,
} from 'lucide-react';
import { useAuthStore } from '@/store/authStore';
import { api } from '@/services/api/client';
import {
  AreaChart,
  Area,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  ResponsiveContainer,
} from 'recharts';

// Umsatzdaten für das Chart
const umsatzData = [
  { monat: 'Jan', umsatz: 125000 },
  { monat: 'Feb', umsatz: 148000 },
  { monat: 'Mär', umsatz: 162000 },
  { monat: 'Apr', umsatz: 185000 },
  { monat: 'Mai', umsatz: 178000 },
  { monat: 'Jun', umsatz: 195000 },
  { monat: 'Jul', umsatz: 210000 },
  { monat: 'Aug', umsatz: 198000 },
  { monat: 'Sep', umsatz: 225000 },
  { monat: 'Okt', umsatz: 242000 },
  { monat: 'Nov', umsatz: 238000 },
  { monat: 'Dez', umsatz: 265000 },
];

// Animation Variants
const containerVariants = {
  hidden: { opacity: 0 },
  visible: {
    opacity: 1,
    transition: {
      staggerChildren: 0.08,
    },
  },
};

const itemVariants = {
  hidden: { opacity: 0, y: 20 },
  visible: {
    opacity: 1,
    y: 0,
    transition: {
      type: 'spring',
      stiffness: 100,
    },
  },
};

export function DashboardPage() {
  const { user } = useAuthStore();
  const navigate = useNavigate();
  const [stats, setStats] = useState({
    adressen: 0,
    artikel: 0,
    kontrakte_offen: 0,
    fuhren: 124,
  });

  useEffect(() => {
    const loadStats = async () => {
      try {
        const response = await api.get('/dashboard/stats');
        if (response.data.success) {
          const data = response.data.data;
          setStats({
            adressen: data.adressen,
            artikel: data.artikel,
            kontrakte_offen: data.kontrakte_offen,
            fuhren: 124,
          });
        }
      } catch (error) {
        console.error('Failed to load dashboard stats:', error);
      }
    };

    loadStats();
  }, []);

  const statCards = [
    {
      title: 'Adressen',
      value: stats.adressen.toString(),
      change: '+12.5%',
      trend: 'up' as const,
      icon: Users,
      color: 'bg-blue-500',
    },
    {
      title: 'Artikel',
      value: stats.artikel.toString(),
      change: '+5.2%',
      trend: 'up' as const,
      icon: Package,
      color: 'bg-emerald-500',
    },
    {
      title: 'Offene Kontrakte',
      value: stats.kontrakte_offen.toString(),
      change: '-3.1%',
      trend: 'down' as const,
      icon: FileText,
      color: 'bg-amber-500',
    },
    {
      title: 'Fuhren (Woche)',
      value: stats.fuhren.toString(),
      change: '+18.3%',
      trend: 'up' as const,
      icon: Truck,
      color: 'bg-purple-500',
    },
  ];

  const quickActions = [
    { label: 'Neue Adresse', icon: Users, color: 'bg-blue-50 text-blue-600', path: '/adressen' },
    { label: 'Neuer Kontrakt', icon: FileText, color: 'bg-amber-50 text-amber-600', path: '/kontrakte' },
    { label: 'Neue Fuhre', icon: Truck, color: 'bg-purple-50 text-purple-600', path: '/fuhren' },
    { label: 'Neue Rechnung', icon: Receipt, color: 'bg-emerald-50 text-emerald-600', path: '/rechnungen' },
  ];

  const recentActivities = [
    { action: 'Kontrakt erstellt', target: 'VKK-2408-00012', time: 'vor 5 Min.', icon: CheckCircle2, iconColor: 'text-emerald-500' },
    { action: 'Fuhre abgeschlossen', target: 'TPA-2408-00089', time: 'vor 23 Min.', icon: Truck, iconColor: 'text-blue-500' },
    { action: 'Adresse bearbeitet', target: 'Müller GmbH', time: 'vor 1 Std.', icon: Users, iconColor: 'text-purple-500' },
    { action: 'Rechnung gedruckt', target: 'RE-2408-00156', time: 'vor 2 Std.', icon: Receipt, iconColor: 'text-amber-500' },
    { action: 'Warnung: Kontingent', target: 'VKK-2408-00008', time: 'vor 3 Std.', icon: AlertCircle, iconColor: 'text-red-500' },
  ];

  return (
    <motion.div
      variants={containerVariants}
      initial="hidden"
      animate="visible"
      className="p-6 space-y-6"
    >
      {/* Header */}
      <motion.div variants={itemVariants} className="flex flex-col sm:flex-row sm:items-center sm:justify-between gap-4">
        <div>
          <h1 className="text-2xl font-bold text-gray-900">
            Willkommen zurück, {user?.vorname || user?.benutzername || 'Admin'}!
          </h1>
          <p className="text-gray-500 mt-1">
            Hier ist Ihr Überblick für heute, {new Date().toLocaleDateString('de-DE', { weekday: 'long', day: 'numeric', month: 'long' })}.
          </p>
        </div>
        <div className="flex gap-3">
          <button 
            onClick={() => navigate('/kontrakte')}
            className="flex items-center gap-2 px-4 py-2 bg-emerald-500 text-white rounded-lg hover:bg-emerald-600 transition-colors"
          >
            <Plus className="h-4 w-4" />
            Neuer Kontrakt
          </button>
        </div>
      </motion.div>

      {/* Stats Cards */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-4 gap-4">
        {statCards.map((stat, index) => (
          <motion.div
            key={stat.title}
            variants={itemVariants}
            className="bg-white rounded-xl shadow-sm border border-gray-100 p-5 hover:shadow-md transition-shadow"
          >
            <div className="flex items-center justify-between">
              <div>
                <p className="text-sm font-medium text-gray-500">{stat.title}</p>
                <p className="text-3xl font-bold text-gray-900 mt-1">{stat.value}</p>
              </div>
              <div className={`h-12 w-12 rounded-xl ${stat.color} flex items-center justify-center`}>
                <stat.icon className="h-6 w-6 text-white" />
              </div>
            </div>
            <div className="flex items-center mt-3 text-sm">
              {stat.trend === 'up' ? (
                <TrendingUp className="h-4 w-4 text-emerald-500 mr-1" />
              ) : (
                <TrendingDown className="h-4 w-4 text-red-500 mr-1" />
              )}
              <span className={stat.trend === 'up' ? 'text-emerald-600 font-medium' : 'text-red-600 font-medium'}>
                {stat.change}
              </span>
              <span className="text-gray-500 ml-1">vs. letzter Monat</span>
            </div>
          </motion.div>
        ))}
      </div>

      {/* Main Content Grid */}
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-6">
        {/* Umsatz Chart */}
        <motion.div variants={itemVariants} className="lg:col-span-2">
          <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-6">
            <div className="flex items-center justify-between mb-6">
              <div>
                <h2 className="text-lg font-semibold text-gray-900 flex items-center gap-2">
                  <Euro className="h-5 w-5 text-emerald-500" />
                  Umsatzübersicht
                </h2>
                <p className="text-sm text-gray-500">Monatlicher Umsatz 2024</p>
              </div>
              <select className="text-sm border border-gray-200 rounded-lg px-3 py-1.5 text-gray-600 bg-gray-50">
                <option>Letzten 12 Monate</option>
                <option>Dieses Quartal</option>
                <option>Dieses Jahr</option>
              </select>
            </div>
            <div className="h-[280px]">
              <ResponsiveContainer width="100%" height="100%">
                <AreaChart data={umsatzData} margin={{ top: 10, right: 10, left: 0, bottom: 0 }}>
                  <defs>
                    <linearGradient id="colorUmsatz" x1="0" y1="0" x2="0" y2="1">
                      <stop offset="5%" stopColor="#10b981" stopOpacity={0.2} />
                      <stop offset="95%" stopColor="#10b981" stopOpacity={0} />
                    </linearGradient>
                  </defs>
                  <CartesianGrid strokeDasharray="3 3" stroke="#e5e7eb" vertical={false} />
                  <XAxis 
                    dataKey="monat" 
                    stroke="#9ca3af" 
                    fontSize={12}
                    tickLine={false}
                    axisLine={false}
                  />
                  <YAxis 
                    stroke="#9ca3af" 
                    fontSize={12}
                    tickLine={false}
                    axisLine={false}
                    tickFormatter={(value) => `${(value / 1000).toFixed(0)}k €`}
                  />
                  <Tooltip 
                    contentStyle={{ 
                      backgroundColor: '#ffffff', 
                      border: '1px solid #e5e7eb',
                      borderRadius: '8px',
                      boxShadow: '0 4px 6px -1px rgb(0 0 0 / 0.1)',
                    }}
                    formatter={(value: number) => [`€ ${value.toLocaleString('de-DE')}`, 'Umsatz']}
                    labelStyle={{ color: '#374151', fontWeight: 600 }}
                  />
                  <Area
                    type="monotone"
                    dataKey="umsatz"
                    stroke="#10b981"
                    strokeWidth={2}
                    fillOpacity={1}
                    fill="url(#colorUmsatz)"
                  />
                </AreaChart>
              </ResponsiveContainer>
            </div>
          </div>
        </motion.div>

        {/* Quick Actions & Activity */}
        <motion.div variants={itemVariants} className="space-y-6">
          {/* Quick Actions */}
          <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-6">
            <h2 className="text-lg font-semibold text-gray-900 mb-4">Schnellzugriff</h2>
            <div className="grid grid-cols-2 gap-3">
              {quickActions.map((action) => (
                <button
                  key={action.label}
                  onClick={() => navigate(action.path)}
                  className="flex flex-col items-center gap-2 p-4 rounded-xl border border-gray-100 hover:border-emerald-200 hover:shadow-md transition-all group"
                >
                  <div className={`h-10 w-10 rounded-lg ${action.color} flex items-center justify-center group-hover:scale-110 transition-transform`}>
                    <action.icon className="h-5 w-5" />
                  </div>
                  <span className="text-sm font-medium text-gray-700">{action.label}</span>
                </button>
              ))}
            </div>
          </div>

          {/* Recent Activity */}
          <div className="bg-white rounded-xl shadow-sm border border-gray-100 p-6">
            <div className="flex items-center justify-between mb-4">
              <h2 className="text-lg font-semibold text-gray-900">Letzte Aktivitäten</h2>
              <button className="text-sm text-emerald-600 hover:text-emerald-700 font-medium flex items-center gap-1">
                Alle <ArrowRight className="h-4 w-4" />
              </button>
            </div>
            <div className="space-y-3">
              {recentActivities.map((activity, index) => (
                <div key={index} className="flex items-start gap-3 p-2 rounded-lg hover:bg-gray-50 transition-colors cursor-pointer">
                  <div className={`h-8 w-8 rounded-full bg-gray-100 flex items-center justify-center flex-shrink-0`}>
                    <activity.icon className={`h-4 w-4 ${activity.iconColor}`} />
                  </div>
                  <div className="flex-1 min-w-0">
                    <p className="text-sm font-medium text-gray-900 truncate">{activity.action}</p>
                    <p className="text-xs text-gray-500 truncate">{activity.target}</p>
                  </div>
                  <span className="text-xs text-gray-400 whitespace-nowrap flex items-center gap-1">
                    <Clock className="h-3 w-3" />
                    {activity.time}
                  </span>
                </div>
              ))}
            </div>
          </div>
        </motion.div>
      </div>
    </motion.div>
  );
}
