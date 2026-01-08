package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;


public class AT_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public AT_MASK_ComponentMapCollector() throws myException {
        super();
        this.registerComponent(new RB_KM(_TAB.trigger_action_def), new AT_MASK_ComponentMap());
    }
}
 
