/**
 * Auth-Router
 * Routen für Authentifizierung
 */

import { Router } from 'express';
import * as controller from './controller.js';
import { authenticate } from '../../middleware/auth.js';
import { authLimiter, passwordResetLimiter } from '../../middleware/rateLimiter.js';

const router = Router();

// Öffentliche Routen
router.post('/login', authLimiter, controller.login);
router.post('/register', authLimiter, controller.register);
router.post('/refresh', controller.refresh);

// Geschützte Routen
router.post('/logout', authenticate, controller.logout);
router.post('/change-password', authenticate, passwordResetLimiter, controller.changePassword);
router.get('/me', authenticate, controller.me);

export default router;
