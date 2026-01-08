/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components
 * @author manfred
 * @date 21.07.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 21.07.2020
 *
 */
public class WK_RB_Popup_ContainerAbsetzGrund extends E2_PopUp_For_LookupValue {

	public static RB_KF key = new RB_KF()._setHASHKEY("be03897d-83d6-4a43-9b3a-b52eb34532c2")._setREALNAME(S.ms("CONTAINER_ABSETZ_GRUND_PopUp").CTrans());

	
	/**
	 * @author manfred
	 * @date 02.08.2021
	 *
	 * @throws myException
	 */
	public WK_RB_Popup_ContainerAbsetzGrund() throws myException {
		super();

		String sqlLookup = " SELECT G.KURZ, G.BEZEICHNUNG "
				+ " FROM  "
				+ bibE2.cTO()
				+ ".JT_CONTAINER_ABSETZ_GRUND G "
				+ " ORDER BY 1  "
				+ " ";
		this.setSQL(sqlLookup);
		this._set_container_width(new Extent(200));
		this._set_container_heigth(new Extent(100));
		this._use_container_ex(true);
	}
	
	
}
