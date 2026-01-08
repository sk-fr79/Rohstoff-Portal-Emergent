
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_CONST.TRANSLATOR;
import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.indep.exceptions.myException;






public class REM_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {

	public REM_MASK_MaskModulContainer(REM__IF_getTableAndID  list_container) throws myException {
		super();

		REM_MASK_ComponentMapCollector compMapCollector = new REM_MASK_ComponentMapCollector(list_container);
		this.registerComponent(new REM__TableKey(), compMapCollector);

		this.rb_INIT(TRANSLATOR.MASK.get_modul(), new REM_MASK_MaskGrid(compMapCollector), true);

	}
}
