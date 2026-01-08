 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportInfoUndPasswort;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class IP_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public IP_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(new RB_KM(_TAB.report), new IP_MASK_ComponentMap());
    }
}
 
