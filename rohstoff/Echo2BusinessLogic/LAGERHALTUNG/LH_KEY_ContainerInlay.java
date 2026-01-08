/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG
 * @author martin
 * @date 30.08.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.basics4project.DB_ENUMS.LAGER_BOX;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 30.08.2019
 *
 */
public class LH_KEY_ContainerInlay extends RB_KF {


	/**
	 * @author martin
	 * @date 30.08.2019
	 *
	 * @param fieldName
	 * @throws myException
	 */
	public LH_KEY_ContainerInlay() throws myException {
		super(LAGER_BOX.id_lager_box," 4cd748dc-cb23-11e9-a32f-2a2ae2dbcce4");
	}


}
