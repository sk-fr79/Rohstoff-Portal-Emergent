package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._CONTROLLER.FZ_Kontrakte_Angebot_ControllerMap;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST.SEARCH_EK_OR_VK;

public class _WA_Controller_kontrakt extends FZ_Kontrakte_Angebot_ControllerMap {

	public _WA_Controller_kontrakt(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
	}

	@Override
	public KEY_ATOM get_left_atom() {
		return ((WA_CM__Collector)get_ComponentMapCollector()).get_master_key().k_atom_left();
	}

	@Override
	public RB_KF get_angebot_kontrakt_field() throws myException {
		return FZ__CONST.f_keys.KOMBI_ANG_KON_RIGHT.k();
	}

	@Override
	public KEY_ATOM get_right_atom() throws myException {
		return ((WA_CM__Collector)get_ComponentMapCollector()).get_master_key().k_atom_right();
	}

	@Override
	public KEY_VEKT get_vektor() throws myException {
		return ((WA_CM__Collector)get_ComponentMapCollector()).get_master_key().k_vektor();
	}

	@Override
	public RB_KF get_einheit_field() throws myException {
		return FZ__CONST.f_keys.EINHEIT.k();
	}

	@Override
	public SEARCH_EK_OR_VK get_ek() throws myException {
		return SEARCH_EK_OR_VK.VK;
	}

	
}
