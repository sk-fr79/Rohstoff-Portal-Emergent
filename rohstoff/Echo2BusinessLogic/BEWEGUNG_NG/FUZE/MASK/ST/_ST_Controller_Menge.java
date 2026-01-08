package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._CONTROLLER.FZ_Menge_Controller;

public class _ST_Controller_Menge extends FZ_Menge_Controller {

	public _ST_Controller_Menge(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
	}
	
	@Override
	public void fill_menge(MyE2_MessageVector mv) throws myException {

		__ST_MASTER_KEY mKey = ((ST_CM__Collector)get_ComponentMapCollector()).get_master_key();
		
		String quelle_menge = this.get_maskVal(mKey.k_vektor_pos_left__atom_left(), BEWEGUNG_ATOM.menge);
		
		String ziel_menge = this.get_maskVal(mKey.k_vektor_pos_right__atom_right(), BEWEGUNG_ATOM.menge);
		
		if(S.isEmpty(ziel_menge) || ziel_menge.equals("0") || ziel_menge.equals("0,000")){
			this.set_maskVal(mKey.k_vektor_pos_right__atom_right(), BEWEGUNG_ATOM.menge, quelle_menge, mv);
		}
		
		String hidden_quelle_menge = this.get_maskVal(mKey.k_vektor_pos_left__atom_right(), BEWEGUNG_ATOM.menge);
		
		String hidden_ziel_menge = this.get_maskVal(mKey.k_vektor_pos_right__atom_left(), BEWEGUNG_ATOM.menge);
		
		ziel_menge = this.get_maskVal(mKey.k_vektor_pos_right__atom_right(), BEWEGUNG_ATOM.menge);

		if(S.isEmpty(hidden_quelle_menge) || hidden_quelle_menge.equals("0") || hidden_quelle_menge.equals("0,000")){
			this.set_maskVal(mKey.k_vektor_pos_left__atom_right(), BEWEGUNG_ATOM.menge, quelle_menge, mv);
		}

		this.set_maskVal(mKey.k_vektor_pos_right__atom_left(), BEWEGUNG_ATOM.menge, ziel_menge, mv);
		
	}
	
	@Override
	public KEY_ATOM get_quelle_atom() {
		return null;
	}

	@Override
	public KEY_ATOM get_ziel_atom() {
		return null;
	}

}
