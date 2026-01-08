 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter.RR_CONST.TRANSLATOR;


public class RR_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public RR_MASK_MaskModulContainer() throws myException {
        super();
        RR_MASK_ComponentMapCollector compMapCollector = new RR_MASK_ComponentMapCollector() ; 
        this.registerComponent(new RB_KM(_TAB.hilfetext), compMapCollector );
        
        this.rb_INIT(TRANSLATOR.MASK.get_modul(), new RR_MASK_MaskGrid(compMapCollector), true);
        
    }
}
 
