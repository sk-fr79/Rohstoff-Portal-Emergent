package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL;

import panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL.MC_DES_CONST.TRANSLATOR;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class MC_DES_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {
    public MC_DES_MASK_MaskModulContainer(Rec20 record_maskdef) throws myException {
        super();
        MC_DES_MASK_ComponentMapCollector compMapCollector = new MC_DES_MASK_ComponentMapCollector(record_maskdef) ; 
        this.registerComponent(new  MC_DES_KEY(), compMapCollector );
        
        this.rb_INIT(TRANSLATOR.MASK.get_modul(), new MC_DES_MASK_MaskGrid(compMapCollector), true);
        
    }
}
 
