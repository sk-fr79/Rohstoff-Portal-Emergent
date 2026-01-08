/**
 * Express App Setup
 * Haupteinstiegspunkt für das Backend
 */

import express, { Express } from 'express';
import cors from 'cors';
import helmet from 'helmet';
import compression from 'compression';
import cookieParser from 'cookie-parser';

import { env } from './config/env.js';
import { testDatabaseConnection, disconnectDatabase } from './config/database.js';
import { logger } from './utils/logger.js';
import { errorHandler, notFoundHandler } from './middleware/errorHandler.js';
import { standardLimiter } from './middleware/rateLimiter.js';

// Modul-Router importieren
import authRoutes from './modules/auth/routes.js';
import mandantRoutes from './modules/mandant/routes.js';
import adresseRoutes from './modules/adresse/routes.js';
import artikelRoutes from './modules/artikel/routes.js';
import kontraktRoutes from './modules/kontrakt/routes.js';

// Express App erstellen
const app: Express = express();

// =====================================================
// SICHERHEITS-MIDDLEWARE
// =====================================================

// Helmet für Sicherheits-Header
app.use(helmet({
  contentSecurityPolicy: {
    directives: {
      defaultSrc: ["'self'"],
      styleSrc: ["'self'", "'unsafe-inline'"],
      scriptSrc: ["'self'"],
      imgSrc: ["'self'", 'data:', 'https:'],
    },
  },
  crossOriginEmbedderPolicy: false,
}));

// CORS-Konfiguration
app.use(cors({
  origin: env.CORS_ORIGIN.split(','),
  credentials: true,
  methods: ['GET', 'POST', 'PUT', 'PATCH', 'DELETE', 'OPTIONS'],
  allowedHeaders: ['Content-Type', 'Authorization', 'X-Requested-With'],
}));

// Rate Limiting
app.use('/api', standardLimiter);

// =====================================================
// STANDARD-MIDDLEWARE
// =====================================================

// Body-Parser
app.use(express.json({ limit: '10mb' }));
app.use(express.urlencoded({ extended: true, limit: '10mb' }));

// Cookie-Parser
app.use(cookieParser());

// Kompression
app.use(compression());

// Request-Logging
app.use((req, res, next) => {
  const start = Date.now();
  
  res.on('finish', () => {
    const duration = Date.now() - start;
    logger.info(`${req.method} ${req.originalUrl}`, {
      status: res.statusCode,
      duration: `${duration}ms`,
      ip: req.ip,
    });
  });
  
  next();
});

// =====================================================
// HEALTH-CHECK
// =====================================================

app.get('/api/health', (req, res) => {
  res.json({
    status: 'ok',
    timestamp: new Date().toISOString(),
    uptime: process.uptime(),
    environment: env.NODE_ENV,
  });
});

// =====================================================
// API-ROUTEN
// =====================================================

// Auth-Modul
app.use('/api/auth', authRoutes);

// Weitere Module werden hier hinzugefügt:
// app.use('/api/mandanten', mandantRoutes);
// app.use('/api/adressen', adresseRoutes);
// app.use('/api/artikel', artikelRoutes);
// app.use('/api/kontrakte', kontraktRoutes);
// app.use('/api/fuhren', fuhreRoutes);
// app.use('/api/rechnungen', rechnungRoutes);

// =====================================================
// FEHLERBEHANDLUNG
// =====================================================

// 404-Handler
app.use(notFoundHandler);

// Globaler Fehlerhandler
app.use(errorHandler);

// =====================================================
// SERVER STARTEN
// =====================================================

async function startServer(): Promise<void> {
  try {
    // Datenbankverbindung testen
    const dbConnected = await testDatabaseConnection();
    if (!dbConnected) {
      throw new Error('Datenbankverbindung konnte nicht hergestellt werden');
    }
    
    // Server starten
    const server = app.listen(env.PORT, () => {
      logger.info(`
========================================
  Rohstoff Portal Backend gestartet
========================================
  Umgebung:  ${env.NODE_ENV}
  Port:      ${env.PORT}
  Zeit:      ${new Date().toISOString()}
========================================
      `);
    });
    
    // Graceful Shutdown
    const shutdown = async (signal: string) => {
      logger.info(`${signal} Signal empfangen. Fahre Server herunter...`);
      
      server.close(async () => {
        await disconnectDatabase();
        logger.info('Server erfolgreich heruntergefahren');
        process.exit(0);
      });
      
      // Force Shutdown nach 10 Sekunden
      setTimeout(() => {
        logger.error('Erzwungener Shutdown nach Timeout');
        process.exit(1);
      }, 10000);
    };
    
    process.on('SIGTERM', () => shutdown('SIGTERM'));
    process.on('SIGINT', () => shutdown('SIGINT'));
    
  } catch (error) {
    logger.error('Fehler beim Starten des Servers:', error);
    process.exit(1);
  }
}

// Server starten
startServer();

export default app;
