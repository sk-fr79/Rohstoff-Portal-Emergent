 
package rohstoff.Echo2BusinessLogic.CONTAINER;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.indep.exceptions.myException;



public class CON_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public CON_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(new CON_KEY(), new CON_MASK_ComponentMap());
    }
}
 
