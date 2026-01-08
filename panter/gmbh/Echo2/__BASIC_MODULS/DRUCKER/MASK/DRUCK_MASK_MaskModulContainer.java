package panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.MASK;

import panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER._DRUCK_KEY;
import panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.DRUCK_CONST.TRANSLATOR;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.indep.exceptions.myException;

public class DRUCK_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
	
    public DRUCK_MASK_MaskModulContainer() throws myException {
        super();
        DRUCK_MASK_ComponentMapCollector compMapCollector = new DRUCK_MASK_ComponentMapCollector() ; 
        this.registerComponent(new _DRUCK_KEY(), compMapCollector );
        
        this.rb_INIT(TRANSLATOR.MASK.get_modul(), new DRUCK_MASK_MaskGrid(compMapCollector), true);
    
    }
    
    
   
}
 
