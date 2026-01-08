package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._CONTROLLER.FZ_Menge_Controller;

public class _LL_Controller_Menge extends FZ_Menge_Controller {

	public _LL_Controller_Menge(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
	}

	@Override
	public KEY_ATOM get_quelle_atom() {
		return ((LL_CM__Collector)get_ComponentMapCollector()).get_master_key().k_atom_left();
	}

	@Override
	public KEY_ATOM get_ziel_atom() {
		return ((LL_CM__Collector)get_ComponentMapCollector()).get_master_key().k_atom_right();
	}

}
