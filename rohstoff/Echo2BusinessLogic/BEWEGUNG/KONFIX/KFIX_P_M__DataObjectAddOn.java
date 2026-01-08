package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class KFIX_P_M__DataObjectAddOn extends RB_Dataobject_V2 {

	public KFIX_P_M__DataObjectAddOn(_TAB p_tab) throws myException {
		super(p_tab);
			
	}

	public KFIX_P_M__DataObjectAddOn(Rec20 recORD, MASK_STATUS status, String oBelegTyp) throws myException {
		super(recORD, status);
	}

}
