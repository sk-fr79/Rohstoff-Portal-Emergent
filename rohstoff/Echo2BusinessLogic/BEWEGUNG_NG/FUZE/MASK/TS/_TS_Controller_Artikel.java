package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._CONTROLLER.FZ_Artikel_ControllerMap;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST.SEARCH_EK_OR_VK;

public class _TS_Controller_Artikel extends FZ_Artikel_ControllerMap {

	public _TS_Controller_Artikel(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector, SEARCH_EK_OR_VK.EK);
	}

	@Override
	public KEY_ATOM get_quelle_atom() {
		return ((TS_CM__Collector)get_ComponentMapCollector()).get_master_key().k_atom_left();
	}

	@Override
	public KEY_ATOM get_ziel_atom() {
		return ((TS_CM__Collector)get_ComponentMapCollector()).get_master_key().k_atom_right();
	}

	@Override
	public KEY_VEKT get_vektor() {
		return ((TS_CM__Collector)get_ComponentMapCollector()).get_master_key().k_vektor();
	}

	@Override
	public RB_KF get_angebot_kontrakt_field() throws myException {
		return null;
	}
	
	@Override
	public RB_KF get_einheit_field() throws myException {
		return FZ__CONST.f_keys.EINHEIT.k();
	}
}
