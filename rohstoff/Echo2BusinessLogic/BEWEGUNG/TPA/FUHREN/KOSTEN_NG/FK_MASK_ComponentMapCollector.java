
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN_NG;

import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;


public class FK_MASK_ComponentMapCollector extends RB_ComponentMapCollector {

	public FK_MASK_ComponentMapCollector() throws myException {
		super();

		this.registerComponent(new RB_KM(_TAB.vpos_tpa_fuhre_kosten), new FK_MASK_ComponentMap());
	}
}
