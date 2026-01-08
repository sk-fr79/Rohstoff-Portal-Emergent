
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import panter.gmbh.basics4project.Project_RB_ModuleContainerMASK;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.BOR_CONST.TRANSLATOR;


public class BOR_MASK_MaskModulContainer extends Project_RB_ModuleContainerMASK {

	public BOR_MASK_MaskModulContainer() throws myException {
		super();
		BOR_MASK_ComponentMapCollector compMapCollector = new BOR_MASK_ComponentMapCollector();
		this.registerComponent(new BOR__MaskKey(), compMapCollector);

		this.rb_INIT(TRANSLATOR.MASK.get_modul(), new BOR_MASK_MaskGrid(compMapCollector), true);

	}
}
