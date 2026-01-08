package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._SET_AN_VALID.FZ_SaV_Manuell_EPreis;

public class _ST_SAV_Manuell_Epreis_Quelle extends FZ_SaV_Manuell_EPreis {

	@Override
	public KEY_ATOM get_atom(RB_ComponentMap rbMASK) throws myException {
		return ((ST_CM__Collector)rbMASK.rb_get_belongs_to()).get_master_key().k_vektor_pos_left__atom_left();
	}

	@Override
	public MASK_STATUS get_status(RB_ComponentMap rbMASK) throws myException {
		return ((ST_CM__Collector)rbMASK.rb_get_belongs_to()).get_master_key().get_mask_status();
	}

}
