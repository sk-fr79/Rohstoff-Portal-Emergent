 
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class CONTYP_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public CONTYP_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(new CONTYP_KEY(), new CONTYP_MASK_ComponentMap());
    }
}
 
