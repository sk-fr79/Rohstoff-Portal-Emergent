/**
 * Adresse-Router
 * Routen für Adressenverwaltung
 */

import { Router } from 'express';
import * as controller from './controller.js';
import { authenticate } from '../../middleware/auth.js';

const router = Router();

// Alle Routen erfordern Authentifizierung
router.use(authenticate);

router.get('/', controller.search);
router.get('/:id', controller.getById);
router.post('/', controller.create);
router.put('/:id', controller.update);
router.delete('/:id', controller.remove);

export default router;
