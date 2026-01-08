package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportInfoUndPasswort;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportInfoUndPasswort.IP_CONST.TRANSLATOR;

public class IP_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public IP_MASK_MaskModulContainer() throws myException {
        super();
        IP_MASK_ComponentMapCollector compMapCollector = new IP_MASK_ComponentMapCollector() ; 
        this.registerComponent(new RB_KM(_TAB.hilfetext), compMapCollector );
        
        this.rb_INIT(TRANSLATOR.MASK.get_modul(), new IP_MASK_MaskGrid(compMapCollector), true);
        
    }
}
 
