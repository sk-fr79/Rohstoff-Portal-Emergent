package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.indep.exceptions.myException;

public class AH7_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public AH7_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(new AH7_KEY(), new AH7_MASK_ComponentMap());
    }
}
 
