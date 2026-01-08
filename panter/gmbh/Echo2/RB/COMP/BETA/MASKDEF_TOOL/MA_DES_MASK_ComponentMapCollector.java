package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public MA_DES_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(new MA_DES_KEY(), new MA_DES_MASK_ComponentMap());
    }
}
 
