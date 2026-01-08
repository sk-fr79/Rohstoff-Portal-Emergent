package panter.gmbh.Echo2.__BASIC_MODULS.ADRESSKLASSE;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class ADK_M_Key extends RB_KM {

	public ADK_M_Key() throws myException {
		super(_TAB.adressklasse_def);
	}

}
