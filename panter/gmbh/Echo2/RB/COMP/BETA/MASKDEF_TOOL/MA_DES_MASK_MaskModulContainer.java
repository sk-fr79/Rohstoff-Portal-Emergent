package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL;

import panter.gmbh.Echo2.RB.COMP.BETA.MASKDEF_TOOL.MA_DES_CONST.TRANSLATOR;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.indep.exceptions.myException;

public class MA_DES_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public MA_DES_MASK_MaskModulContainer() throws myException {
        super();
        MA_DES_MASK_ComponentMapCollector compMapCollector = new MA_DES_MASK_ComponentMapCollector() ; 
        this.registerComponent(new  MA_DES_KEY(), compMapCollector );
        
        this.rb_INIT(TRANSLATOR.MASK.get_modul(), new MA_DES_MASK_MaskGrid(compMapCollector), true);
        
    }
}
 
