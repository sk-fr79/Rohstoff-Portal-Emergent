/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class AH7_MLabelStatusFieldKey extends RB_KF {

	/**
	 * @param table in which mask it is used
	 * @throws myException
	 */
	public AH7_MLabelStatusFieldKey(_TAB table) throws myException {
		super(table.key(),table.key().fn()+"@ADDONStatusLabel");
	}

}
