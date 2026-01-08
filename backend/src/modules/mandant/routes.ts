/**
 * Mandant-Router
 * Routen für Mandantenverwaltung
 */

import { Router } from 'express';
import * as controller from './controller.js';
import { authenticate, requireAdmin } from '../../middleware/auth.js';

const router = Router();

// Alle Routen erfordern Authentifizierung und Admin-Rechte
router.use(authenticate);
router.use(requireAdmin);

router.get('/', controller.getAll);
router.get('/:id', controller.getById);
router.post('/', controller.create);
router.put('/:id', controller.update);
router.delete('/:id', controller.remove);

export default router;
