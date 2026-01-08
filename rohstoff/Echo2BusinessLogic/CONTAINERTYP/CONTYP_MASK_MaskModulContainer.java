 
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import rohstoff.Echo2BusinessLogic.CONTAINERTYP.CONTYP_CONST.TRANSLATOR;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
public class CONTYP_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public CONTYP_MASK_MaskModulContainer() throws myException {
        super();
        CONTYP_MASK_ComponentMapCollector compMapCollector = new CONTYP_MASK_ComponentMapCollector() ; 
        this.registerComponent(new  CONTYP_KEY(), compMapCollector );
        
        this.rb_INIT(TRANSLATOR.MASK.get_modul(), new CONTYP_MASK_MaskGrid(compMapCollector), true);
        
    }
}
 
