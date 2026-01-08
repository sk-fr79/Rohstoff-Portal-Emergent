 
package rohstoff.Echo2BusinessLogic.CONTAINER;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.CONTAINER.CON_CONST.TRANSLATOR;


public class CON_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
	
	
    public CON_MASK_MaskModulContainer() throws myException {
        super();
        CON_MASK_ComponentMapCollector compMapCollector = new CON_MASK_ComponentMapCollector() ; 
        this.registerComponent(new  CON_KEY(), compMapCollector );
        
        this.rb_INIT(TRANSLATOR.MASK.get_modul(), new CON_MASK_MaskGrid(compMapCollector), true);
        
    }
}
 
