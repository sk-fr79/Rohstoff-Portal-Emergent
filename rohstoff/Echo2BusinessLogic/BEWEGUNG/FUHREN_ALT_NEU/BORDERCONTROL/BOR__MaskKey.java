package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;


public class BOR__MaskKey extends RB_KM {

	public BOR__MaskKey() throws myException {
		super(_TAB.bordercrossing);
	}

}
