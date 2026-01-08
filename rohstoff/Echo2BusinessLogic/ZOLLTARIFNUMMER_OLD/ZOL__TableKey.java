package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;


public class ZOL__TableKey extends RB_KM {

	public ZOL__TableKey() throws myException {
		super(_TAB.zolltarifnummer);
	}

}
