/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7.RELATION;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.AH7__ENUM_STATUSRELATION;

/**
 * @author martin
 *
 */
public class AH7_Mask_selFieldStatus extends RB_selField {

	/**
	 * @throws myException 
	 * 
	 */
	public AH7_Mask_selFieldStatus() throws myException {
		super();
		
		this._populate(AH7__ENUM_STATUSRELATION.get_ddArray(true));
	}

}
