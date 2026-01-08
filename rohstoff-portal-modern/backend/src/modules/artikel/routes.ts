/**
 * Artikel-Router
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
router.delete('/:id', controller.remove);

// Artikelbezeichnungen (Sorten)
router.get('/:id/bezeichnungen', controller.getArtikelBezeichnungen);
router.post('/:id/bezeichnungen', controller.createArtikelBez);

export default router;
