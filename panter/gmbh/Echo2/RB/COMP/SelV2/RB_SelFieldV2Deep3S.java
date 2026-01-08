/**
 * panter.gmbh.Echo2.RB.COMP.SelV2
 * @author martin
 * @date 23.05.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SelV2;

import panter.gmbh.Echo2.RB.IF.IF_RB_Component_Savable;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 23.05.2019
 *
 */
public class RB_SelFieldV2Deep3S extends RB_SelFieldV2Deep3 implements IF_RB_Component_Savable {

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return this.getActualValue();
	}

}
