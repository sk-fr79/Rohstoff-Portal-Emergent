package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;



public class ZOL_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public ZOL_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(new RB_KM(_TAB.zolltarifnummer), new ZOL_MASK_ComponentMap());
    }
}
 
