/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE
 * @author martin
 * @date 14.09.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;

import panter.gmbh.BasicInterfaces.Service.PdServiceLagerhaltungAusbuchung;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_MessageBox;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec21_LagerPalette;

/**
 * @author martin
 * @date 14.09.2020
 *
 */
public class LH_P_LIST_btAusbuchungRueckgaengig extends E2_Button {


	
    private RB_TransportHashMap   tpHashMap = null;
	
	
	public LH_P_LIST_btAusbuchungRueckgaengig(RB_TransportHashMap  tpHashMap) {
		super();
		
		this.tpHashMap = tpHashMap;
		try {
			this._image(E2_ResourceIcon.get_RI("lagerabgang-storno.png"), true)
				._ttt("Eine bestehende Ausbuchung rückgängig machen ")	;
		} catch (Exception e) {
			e.printStackTrace();
		}

		this._aaa(new ownAction());
	}

	
	private class ownAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			try {
				VEK<String> ids = new VEK<String>()._a(tpHashMap.getNavigationList().get_vSelectedIDs_Unformated());
				
				if (ids.size()!=1) {
					bibMSG.MV()._addAlarm(S.ms("Bitte genau eine Palette auswählen !"));
				} else {
					Rec21_LagerPalette palette = new Rec21_LagerPalette()._fill_id(ids.get(0));
					
					if (palette.isAusgebucht()) {
						new E2_MessageBox()	._setTitleOfPopup(S.ms("Bitte wählen "))
											._addInfo(S.ms("Soll die Ausbuchung der Palette"))
											._addInfo(S.ms("rückgängig gemacht werden ?"))
											._setYesText("Ja: stornieren")
											._setNoText("Nein: Ausbuchung bleibt")
											._addActionInFrontYesBt(()-> {
												PdServiceLagerhaltungAusbuchung service = new PdServiceLagerhaltungAusbuchung();
												MyE2_MessageVector mv = bibMSG.getNewMV();
												String id = tpHashMap.getNavigationList().get_vSelectedIDs_Unformated().get(0);
												service._storniereAusbuchnung(Long.parseLong(id), mv);
												tpHashMap.getNavigationList()._REBUILD_ACTUAL_SITE(null);
												bibMSG.MV()._add(mv);
											})
											._show(360, 170);
											
													
						
					} else {
						bibMSG.MV()._addAlarm(S.ms("Die Ausbuchung kann nur bei einer ausgebuchten Palette storniert werden !"));
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				
				bibMSG.MV()._add(e);
				
			}
			
			
			
		}
		
	}
	

	
}
