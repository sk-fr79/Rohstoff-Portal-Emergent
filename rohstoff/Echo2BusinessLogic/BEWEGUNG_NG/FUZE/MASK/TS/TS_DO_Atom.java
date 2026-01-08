package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class TS_DO_Atom extends RB_Dataobject_V2 {

	public TS_DO_Atom(Rec20 rec, MASK_STATUS status) throws myException {
		super(rec, status);
	}

	public TS_DO_Atom() throws myException {
		super(_TAB.bewegung_atom);
	}

}
