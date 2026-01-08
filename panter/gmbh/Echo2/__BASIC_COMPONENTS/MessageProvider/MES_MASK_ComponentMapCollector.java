package panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.indep.exceptions.myException;


public class MES_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public MES_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(new MES_KEY(), new MES_MASK_ComponentMap());
    }
}
 
