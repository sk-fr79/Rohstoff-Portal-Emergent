package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;


public class REM__TableKey extends RB_KM {

	public REM__TableKey() throws myException {
		super(_TAB.reminder_def);
	}

}
