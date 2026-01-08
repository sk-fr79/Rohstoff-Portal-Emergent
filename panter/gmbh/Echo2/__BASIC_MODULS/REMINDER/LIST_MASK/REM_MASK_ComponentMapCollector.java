package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class REM_MASK_ComponentMapCollector extends RB_ComponentMapCollector {

	public REM_MASK_ComponentMapCollector(REM__IF_getTableAndID list_container) throws myException {
        super();
        this.registerComponent(new RB_KM(_TAB.reminder_def), new REM_MASK_ComponentMap(list_container));
    }
}
 
