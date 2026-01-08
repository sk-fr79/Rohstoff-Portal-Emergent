/**
 * rohstoff.Echo2BusinessLogic.CONTAINERTYP
 * @author manfred
 * @date 07.12.2017
 * 
 */
package rohstoff.Echo2BusinessLogic.CONTAINERTYP;

import panter.gmbh.basics4project.DB_ENUMS.CONTAINERTYP;
import panter.gmbh.indep.S;

/**
 * @author manfred
 * @date 07.12.2017
 *
 */
public class CONTYP_Selector_Abroll extends CONTYP_Selector_YN {

	/**
	 * @author manfred
	 * @date 07.12.2017
	 *
	 * @param sBeschriftung
	 */
	public CONTYP_Selector_Abroll() {
		super("Abroll");
	}

	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.CONTAINERTYP.CONTYP_Selector_YN#addStatusToSelector()
	 */
	@Override
	protected void addStatusToSelector() {
		ADD_STATUS_TO_Selector(true, CONTAINERTYP.abroll.tnfn() + "=" + "'Y'", S.ms("Ja"), S.ms("Abroller"));
		ADD_STATUS_TO_Selector(true, CONTAINERTYP.abroll.tnfn() + "=" + "'N'", S.ms("Nein"), S.ms("Kein Abroller"));
		
	}

}
