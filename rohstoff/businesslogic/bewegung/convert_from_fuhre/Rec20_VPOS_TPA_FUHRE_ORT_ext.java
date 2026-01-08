package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;


public class Rec20_VPOS_TPA_FUHRE_ORT_ext extends Rec21 {


	public Rec20_VPOS_TPA_FUHRE_ORT_ext() throws myException{
		super (_TAB.vpos_tpa_fuhre_ort);
		this._setPrepared(true);
	}
	
	public Rec20_VPOS_TPA_FUHRE_ORT_ext(Rec21 baseRec) throws myException {
		super(baseRec);
		this._setPrepared();
	}



	
}
