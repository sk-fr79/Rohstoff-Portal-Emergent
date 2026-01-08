/**
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class E2_NaviListStatusSaverBtRestoreSettings extends E2_Button {

	private E2_NavigationObject naviList = null;
	/**
	 * 
	 */
	public E2_NaviListStatusSaverBtRestoreSettings(E2_NavigationObject p_naviList) {
		super();
		this.naviList = p_naviList;
		
		this._image(E2_ResourceIcon.get_RI("clear.png"), true)._setHidden()._aaa(new ownAction());
		this._t("Liste wiederherstellen")._b()
					._ttt(S.ms("Die Liste enthält nur eine Auswahl der ursprünglichen Zeilen. Mit diesem Button wird die alte Sicht wiederhergestellt"));
		this._bord_black();
	}
	
	
	private class ownAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			if (E2_NaviListStatusSaverBtRestoreSettings.this.naviList instanceof E2_NavigationList) {
			
				E2_NaviListStatusSaverBtRestoreSettings oThis = E2_NaviListStatusSaverBtRestoreSettings.this;
				E2_NavigationList navilist = (E2_NavigationList) E2_NaviListStatusSaverBtRestoreSettings.this.naviList;
				
				if (navilist.getStatusSaver() == null) {
					throw new myException(this, "No Status was saved !!");
				} else {
					navilist.get_vectorSegmentation().clear();
					navilist.get_vectorSegmentation().addAll(navilist.getStatusSaver().getCopyOfSegmentationIds());
					oThis._setHidden();
					navilist.goToPage(null, navilist.getStatusSaver().getActualSiteNumber());
					navilist.set_CheckBox_To_AllIdsInVector(navilist.getStatusSaver().getSelectedLines());
					//am schluss den statusSaver loeschen
					navilist.setStatusSaver(null);
					
					//2019-02-18-ebenfalls auf farbmarkierungen achten
					try {
						for (String s: navilist.get_vSelectedIDs_Unformated()) {
							E2_ComponentMAP map = navilist.getMap(s);
							if (map!=null) {
								map.set_ZeileMarkOrUnmark(navilist.get_oColorForeMarkSelectedRows(), navilist.get_oFontForeMarkSelectedRows());
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

					
				}

			} else {
				throw new myException(this,"Error !! False Type");
			}
			
			
		}
		
	}
	
}
