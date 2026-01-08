/**
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.GPS;

import panter.gmbh.BasicInterfaces.ServiceBean.PdServiceShowAddressLocationsOnMapBean;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class FS_MaskBtShowAdresseOSM extends E2_Button {

	
	/**
	 * @throws myException
	 */
	public FS_MaskBtShowAdresseOSM() throws myException {
		super();
		this._image(E2_ResourceIcon.get_RI("gps_klein.png"), true);
		this._ttt(S.ms("Karte zu den GPS-Koordinaten der Adressen aufrufen ..."));
		
		this._aaa(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				FS_MaskBtShowAdresseOSM oThis = FS_MaskBtShowAdresseOSM.this;
				String id = oThis.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
				PdServiceShowAddressLocationsOnMapBean oService = new PdServiceShowAddressLocationsOnMapBean();
				oService.showSingleAddressLocationsOnMap(id);
			}
		});
	}

}
