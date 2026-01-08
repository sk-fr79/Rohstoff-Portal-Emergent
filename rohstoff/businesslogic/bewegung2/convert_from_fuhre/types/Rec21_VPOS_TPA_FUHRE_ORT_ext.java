package rohstoff.businesslogic.bewegung2.convert_from_fuhre.types;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;


public class Rec21_VPOS_TPA_FUHRE_ORT_ext extends Rec21 {


	public Rec21_VPOS_TPA_FUHRE_ORT_ext() throws myException{
		super (_TAB.vpos_tpa_fuhre_ort);
		this._setPrepared(true);
	}
	
	public Rec21_VPOS_TPA_FUHRE_ORT_ext(Rec21 baseRec) throws myException {
		super(baseRec);
		this._setPrepared();
	}



	
}
