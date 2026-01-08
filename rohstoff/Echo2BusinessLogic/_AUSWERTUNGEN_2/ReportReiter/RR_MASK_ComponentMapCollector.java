 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class RR_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
    public RR_MASK_ComponentMapCollector() throws myException {
        super();
        
        this.registerComponent(new RB_KM(_TAB.report_reiter), new RR_MASK_ComponentMap());
    }
}
 
