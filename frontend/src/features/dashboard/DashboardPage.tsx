import { motion } from 'framer-motion';
import { useEffect, useState } from 'react';
import {
  Users,
  Package,
  FileText,
  Truck,
  TrendingUp,
  TrendingDown,
  Euro,
  Activity,
} from 'lucide-react';
import { Card, CardContent, CardDescription, CardHeader, CardTitle } from '@/components/ui/card';
import { useAuthStore } from '@/store/authStore';
import { api } from '@/services/api/client';

// Dashboard Karten mit Statistiken
const stats = [
  {
    title: 'Aktive Adressen',
    value: '1.247',
    change: '+12%',
    trend: 'up',
    icon: Users,
    color: 'text-blue-500',
    bgColor: 'bg-blue-500/10',
  },
  {
    title: 'Artikel',
    value: '856',
    change: '+5%',
    trend: 'up',
    icon: Package,
    color: 'text-green-500',
    bgColor: 'bg-green-500/10',
  },
  {
    title: 'Offene Kontrakte',
    value: '38',
    change: '-3%',
    trend: 'down',
    icon: FileText,
    color: 'text-orange-500',
    bgColor: 'bg-orange-500/10',
  },
  {
    title: 'Fuhren diese Woche',
    value: '124',
    change: '+18%',
    trend: 'up',
    icon: Truck,
    color: 'text-purple-500',
    bgColor: 'bg-purple-500/10',
  },
];

// Animation Variants
const containerVariants = {
  hidden: { opacity: 0 },
  visible: {
    opacity: 1,
    transition: {
      staggerChildren: 0.1,
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
  const [stats, setStats] = useState([
    {
      title: 'Aktive Adressen',
      value: '...',
      change: '+0%',
      trend: 'up',
      icon: Users,
      color: 'text-blue-500',
      bgColor: 'bg-blue-500/10',
    },
    {
      title: 'Artikel',
      value: '...',
      change: '+0%',
      trend: 'up',
      icon: Package,
      color: 'text-green-500',
      bgColor: 'bg-green-500/10',
    },
    {
      title: 'Offene Kontrakte',
      value: '...',
      change: '+0%',
      trend: 'up',
      icon: FileText,
      color: 'text-orange-500',
      bgColor: 'bg-orange-500/10',
    },
    {
      title: 'Fuhren diese Woche',
      value: '...',
      change: '+0%',
      trend: 'up',
      icon: Truck,
      color: 'text-purple-500',
      bgColor: 'bg-purple-500/10',
    },
  ]);

  useEffect(() => {
    const loadStats = async () => {
      try {
        const response = await api.get('/dashboard/stats');
        if (response.data.success) {
          const data = response.data.data;
          setStats([
            {
              title: 'Aktive Adressen',
              value: data.adressen.toString(),
              change: '+12%',
              trend: 'up',
              icon: Users,
              color: 'text-blue-500',
              bgColor: 'bg-blue-500/10',
            },
            {
              title: 'Artikel',
              value: data.artikel.toString(),
              change: '+5%',
              trend: 'up',
              icon: Package,
              color: 'text-green-500',
              bgColor: 'bg-green-500/10',
            },
            {
              title: 'Offene Kontrakte',
              value: data.kontrakte_offen.toString(),
              change: '-3%',
              trend: 'down',
              icon: FileText,
              color: 'text-orange-500',
              bgColor: 'bg-orange-500/10',
            },
            {
              title: 'Fuhren diese Woche',
              value: '124',
              change: '+18%',
              trend: 'up',
              icon: Truck,
              color: 'text-purple-500',
              bgColor: 'bg-purple-500/10',
            },
          ]);
        }
      } catch (error) {
        console.error('Failed to load dashboard stats:', error);
      }
    };

    loadStats();
  }, []);

  return (
    <motion.div
      variants={containerVariants}
      initial="hidden"
      animate="visible"
      className="space-y-6"
    >
      {/* Willkommen */}
      <motion.div variants={itemVariants}>
        <h1 className="text-3xl font-bold tracking-tight">
          Willkommen zurück, {user?.vorname || user?.benutzername}!
        </h1>
        <p className="text-muted-foreground mt-1">
          Hier ist Ihr Überblick für heute.
        </p>
      </motion.div>

      {/* Statistik-Karten */}
      <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-4">
        {stats.map((stat) => (
          <motion.div key={stat.title} variants={itemVariants}>
            <Card className="hover:shadow-lg transition-shadow duration-300">
              <CardHeader className="flex flex-row items-center justify-between pb-2">
                <CardTitle className="text-sm font-medium text-muted-foreground">
                  {stat.title}
                </CardTitle>
                <div className={`p-2 rounded-lg ${stat.bgColor}`}>
                  <stat.icon className={`h-4 w-4 ${stat.color}`} />
                </div>
              </CardHeader>
              <CardContent>
                <div className="text-2xl font-bold">{stat.value}</div>
                <div className="flex items-center text-xs text-muted-foreground mt-1">
                  {stat.trend === 'up' ? (
                    <TrendingUp className="h-3 w-3 text-green-500 mr-1" />
                  ) : (
                    <TrendingDown className="h-3 w-3 text-red-500 mr-1" />
                  )}
                  <span className={stat.trend === 'up' ? 'text-green-500' : 'text-red-500'}>
                    {stat.change}
                  </span>
                  <span className="ml-1">vs. letzter Monat</span>
                </div>
              </CardContent>
            </Card>
          </motion.div>
        ))}
      </div>

      {/* Weitere Dashboard-Inhalte */}
      <div className="grid gap-4 md:grid-cols-2 lg:grid-cols-7">
        {/* Umsatz Chart */}
        <motion.div variants={itemVariants} className="col-span-4">
          <Card className="h-[400px]">
            <CardHeader>
              <CardTitle className="flex items-center gap-2">
                <Euro className="h-5 w-5" />
                Umsatzübersicht
              </CardTitle>
              <CardDescription>Monatlicher Umsatz der letzten 12 Monate</CardDescription>
            </CardHeader>
            <CardContent className="flex items-center justify-center h-[280px]">
              <div className="text-muted-foreground text-center">
                <Activity className="h-12 w-12 mx-auto mb-4 opacity-50" />
                <p>Chart wird hier angezeigt</p>
                <p className="text-sm">(Recharts Integration)</p>
              </div>
            </CardContent>
          </Card>
        </motion.div>

        {/* Letzte Aktivitäten */}
        <motion.div variants={itemVariants} className="col-span-3">
          <Card className="h-[400px]">
            <CardHeader>
              <CardTitle>Letzte Aktivitäten</CardTitle>
              <CardDescription>Ihre letzten Aktionen im System</CardDescription>
            </CardHeader>
            <CardContent>
              <div className="space-y-4">
                {[
                  { action: 'Kontrakt erstellt', target: 'VKK-2408-00012', time: 'vor 5 Min.' },
                  { action: 'Fuhre abgeschlossen', target: 'TPA-2408-00089', time: 'vor 23 Min.' },
                  { action: 'Adresse bearbeitet', target: 'Müller GmbH', time: 'vor 1 Std.' },
                  { action: 'Rechnung gedruckt', target: 'RE-2408-00156', time: 'vor 2 Std.' },
                  { action: 'Artikel angelegt', target: 'Kupferschrott II', time: 'vor 3 Std.' },
                ].map((activity, index) => (
                  <div key={index} className="flex items-center gap-4">
                    <div className="h-2 w-2 rounded-full bg-primary" />
                    <div className="flex-1 min-w-0">
                      <p className="text-sm font-medium truncate">{activity.action}</p>
                      <p className="text-xs text-muted-foreground truncate">{activity.target}</p>
                    </div>
                    <span className="text-xs text-muted-foreground whitespace-nowrap">
                      {activity.time}
                    </span>
                  </div>
                ))}
              </div>
            </CardContent>
          </Card>
        </motion.div>
      </div>
    </motion.div>
  );
}
