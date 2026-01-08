/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 22.05.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import panter.gmbh.Echo2.RB.COMP.RB_date_selektor2;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 22.05.2019
 *
 */
public class B2_DateField extends RB_date_selektor2 {

	/**
	 * @author martin
	 * @date 22.05.2019
	 *
	 * @throws myException
	 */
	public B2_DateField() throws myException {
		super(100,true);
	}

}
