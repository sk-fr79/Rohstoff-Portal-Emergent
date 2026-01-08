/**
 * Kontrakt-Router
 */

import { Router } from 'express';
import * as controller from './controller.js';
import { authenticate } from '../../middleware/auth.js';

const router = Router();

router.use(authenticate);

router.get('/', controller.search);
router.get('/:id', controller.getById);
router.post('/', controller.create);
router.put('/:id', controller.update);
router.post('/:id/abschliessen', controller.abschliessen);
router.delete('/:id', controller.remove);

// Positionen
router.post('/:id/positionen', controller.addPosition);

export default router;
