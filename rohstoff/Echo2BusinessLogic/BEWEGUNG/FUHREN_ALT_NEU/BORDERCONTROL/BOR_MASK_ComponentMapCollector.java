
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;


public class BOR_MASK_ComponentMapCollector extends RB_ComponentMapCollector {

	public BOR_MASK_ComponentMapCollector() throws myException {
		super();

		this.registerComponent(new RB_KM(_TAB.bordercrossing), new BOR_MASK_ComponentMap());
	}
}
