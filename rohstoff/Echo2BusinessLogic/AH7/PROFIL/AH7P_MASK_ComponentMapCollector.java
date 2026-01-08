package rohstoff.Echo2BusinessLogic.AH7.PROFIL;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.indep.exceptions.myException;

public class AH7P_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public AH7P_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(new AH7P_KEY(), new AH7P_MASK_ComponentMap());
    }
}
 
