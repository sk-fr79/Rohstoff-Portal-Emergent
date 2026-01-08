package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class WA_DO_Vektor extends RB_Dataobject_V2 {

	public WA_DO_Vektor(Rec20 rec, MASK_STATUS status) throws myException {
		super(rec, status);
	}

	public WA_DO_Vektor() throws myException {
		super(_TAB.bewegung_vektor);
	}

}
