package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._CONTROLLER;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;

public abstract class FZ_Menge_Controller extends RB_MaskControllerMap{

	public FZ_Menge_Controller(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
	}

	public abstract KEY_ATOM get_quelle_atom();
	
	public abstract KEY_ATOM get_ziel_atom();
	
	public void fill_menge(MyE2_MessageVector mv)throws myException{
		KEY_ATOM start_atom = get_quelle_atom();
	
		KEY_ATOM ziel_atom = get_ziel_atom();
		
		String quelle_menge = this.get_maskVal(start_atom, BEWEGUNG_ATOM.menge);
		
		String ziel_menge = this.get_maskVal(ziel_atom, BEWEGUNG_ATOM.menge);
		
		if(S.isEmpty(ziel_menge) || ziel_menge.equals("0") || ziel_menge.equals("0,000")){
			this.set_maskVal(ziel_atom, BEWEGUNG_ATOM.menge, quelle_menge, mv);
		}
	}
	
	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)
			throws myException {
		return null;
	}

}
