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
public class WK_RB_Popup_Kennzeichen extends E2_PopUp_For_LookupValue {

	public static RB_KF key = new RB_KF()._setHASHKEY("26cfa6c0-f03d-48c0-96e7-22955bb6f938")._setREALNAME(S.ms("LKW_KENNZEICHEN_PopUp").CTrans());

	
	/**
	 * @author manfred
	 * @date 21.07.2020
	 *
	 * @throws myException
	 */
	public WK_RB_Popup_Kennzeichen() throws myException {
		super();

		String sqlLKWs = " SELECT M.ID_MASCHINEN, UPPER( REPLACE(M.KFZKENNZEICHEN,'-',' ') ) "
				+ " FROM  "
				+ bibE2.cTO()
				+ ".JT_MASCHINENTYP  T INNER JOIN "
				+ bibE2.cTO()
				+ ".JT_MASCHINEN M  ON   ( T.ID_MASCHINENTYP = M.ID_MASCHINENTYP    ) "
				+ " WHERE     NVL(T.IST_LKW ,'N') = 'Y' AND NVL(M.AKTIV,'N') = 'Y' ORDER BY 2  "
				+ " ";
		this.setSQL(sqlLKWs);
		this._set_container_width(new Extent(150));
		this._use_container_ex(true);
	}
	
	
}
