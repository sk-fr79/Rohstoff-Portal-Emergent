package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public interface REM__IF_getTableAndID {
	public _TAB get_table() throws myException;
	public String get_id() throws myException;
	public MODUL get_modul() throws myException;
}
