package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD.ZOL_CONST.TRANSLATOR;


public class ZOL_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public ZOL_MASK_MaskModulContainer() throws myException {
        super();
        
        ZOL_MASK_ComponentMapCollector compMapCollector = new ZOL_MASK_ComponentMapCollector() ; 
        this.registerComponent(new RB_KM(_TAB.zolltarifnummer), compMapCollector );
        
        this.rb_INIT(TRANSLATOR.MASK.get_modul(), new ZOL_MASK_MaskGrid(compMapCollector), true);
        
    }
}
 
