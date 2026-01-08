package panter.gmbh.Echo2.RB.BASICS;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class RB_MASK_DUMMY_KEY extends RB_KM {
	public static long counter = 1;

	public RB_MASK_DUMMY_KEY() throws myException {
		super(_TAB.mandant,""+(RB_MASK_DUMMY_KEY.counter++));
	}
	

}
