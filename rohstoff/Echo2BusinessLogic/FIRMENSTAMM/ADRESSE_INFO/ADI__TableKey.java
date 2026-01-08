package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class ADI__TableKey extends RB_KM {

	public ADI__TableKey() throws myException {
		super(_TAB.adresse_info);
		
	}

}
