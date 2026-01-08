package panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class _DRUCK_KEY extends RB_KM {
	
	public _DRUCK_KEY() throws myException {
		super(_TAB.drucker);
	}
}
