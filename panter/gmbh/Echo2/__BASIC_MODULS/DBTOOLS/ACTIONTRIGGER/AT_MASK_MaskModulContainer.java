package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.AT_CONST.TRANSLATOR;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

public class AT_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {

	public AT_MASK_MaskModulContainer() throws myException {
        super();
        AT_MASK_ComponentMapCollector compMapCollector = new AT_MASK_ComponentMapCollector() ; 
        this.registerComponent(new RB_KM(_TAB.trigger_action_def), compMapCollector );
        this.rb_INIT(TRANSLATOR.MASK.get_modul(), new AT_MASK_MaskGrid(compMapCollector), true);
    }
}
 
