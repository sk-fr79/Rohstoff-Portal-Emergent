/**
 * rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL
 * @author manfred
 * @date 05.07.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 05.07.2016
 *
 */
public class WF_SIMPLE_LIST_SelectField_Zeitraum extends MyE2_SelectField {


	/**
	 * @author manfred
	 * @date 05.07.2016
	 *
	 * @param aDefArray
	 * @param cdefaultValue
	 * @param btranslate
	 * @param oWidth
	 * @throws myException
	 */
	public WF_SIMPLE_LIST_SelectField_Zeitraum(String[][] aDefArray, String cdefaultValue, boolean btranslate) throws myException {
		super(aDefArray, cdefaultValue, btranslate);
		
	}

	

}
