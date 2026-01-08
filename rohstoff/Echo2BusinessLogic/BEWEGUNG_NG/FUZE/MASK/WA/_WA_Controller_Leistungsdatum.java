package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._CONTROLLER.FZ_Leistungsdatum_ControllerMap;

public class _WA_Controller_Leistungsdatum extends FZ_Leistungsdatum_ControllerMap {

	public _WA_Controller_Leistungsdatum(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
	}

	@Override
	public RB_KM set_key(RB_ComponentMapCollector p_componentMapCollector) {
		return ((WA_CM__Collector)p_componentMapCollector).get_master_key();
	}

	@Override
	public KEY_ATOM get_quelle_atom() {
		return ((WA_CM__Collector)get_ComponentMapCollector()).get_master_key().k_atom_left();
	}

	@Override
	public KEY_ATOM get_ziel_atom() {
		return ((WA_CM__Collector)get_ComponentMapCollector()).get_master_key().k_atom_right();
	}

	@Override
	public KEY_VEKT get_vektor() {
		return ((WA_CM__Collector)get_ComponentMapCollector()).get_master_key().k_vektor();
	}

}
