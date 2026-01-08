/**
 * rohstoff.Echo2BusinessLogic.EAK.BRANCHE
 * @author martin
 * @date 08.07.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.EAK.BRANCHE;

import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NaviListActionOnSelection;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.Echo2.components.E2_Grid;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.EAK_BRANCHE;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.basics4project.DB_ENUMS.EAK_GRUPPE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 08.07.2019
 *
 */
public class TestListenSchleife extends E2_Button {
	
	private E2_NavigationList  naviList = null;

	public TestListenSchleife(E2_NavigationList naviList) throws myException {
		super();
		this.naviList = naviList;
		
		this._t("Testschleife")._aaa(new ActionSchleife());
	}
	
	
	private class ActionSchleife extends E2_NaviListActionOnSelection {

		MyE2_MessageVector mv = bibMSG.getNewMV();
		
		public ActionSchleife() throws myException {
			super(naviList);
			this._setTitelInPopup(S.ms("Test der AVV-Codes"))
						._setGrenzeBisFortschrittsanzeige(19)
						._setAutoCloseProgressWindowAfterDone(true)
						._setLoopRefreshTimeInMilliSeconds(1000)
						._setClosePopupAfterDone(true)
						;
		}

		@Override
		public void setShapeOfSelectPopup(E2_BasicModuleContainer popup) {
		}

		@Override
		public void doSomethingWithId(String idUnformatedRowId) throws myException {
			Rec21 r = new Rec21(_TAB.eak_branche)._fill_id(idUnformatedRowId);
			RecList21  rlGruppen = r.get_down_reclist21(EAK_GRUPPE.id_eak_branche);
			int iCountCodes = 0;
			for (Rec21 rg: rlGruppen) {
				RecList21  rlCodes = rg.get_down_reclist21(EAK_CODE.id_eak_gruppe);
				iCountCodes += rlCodes.size();
			}
			mv._addInfo(r.getFs(EAK_BRANCHE.branche)+" Gruppen: "+rlGruppen.size()+" Codes: "+iCountCodes);
			
			if (idUnformatedRowId.equals("1007")) {
				throw new myException("Test-Fehler");
			}
			
		}

		@Override
		public void refreshFortschrittsAnzeige(E2_Grid g, int iCount, int iGesamt) {
		     g._clear()._setSize(200,20,200)._a(new RB_lab()._t("Adresse "+iCount))._a(new RB_lab()._t("von"), new RB_gld()._center_mid())._a(new RB_lab()._t(""+iCount), new RB_gld()._center_mid());
		}

		@Override
		public void doSomethingBeforeLoop() throws myException {
			mv.clear();
		}

		@Override
		public void doSomethingAfterLoop() throws myException {
			//bibMSG.MV()._add(mv);
			DEBUG.System_print(mv);
		}

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.E2_NaviListActionOnSelection#doFinaleTasks(panter.gmbh.Echo2.ListAndMask.List.E2_NaviListActionOnSelection)
		 */
		@Override
		public void doFinaleTasks() {
			mv.clear();
		}

		/* (non-Javadoc)
		 * @see panter.gmbh.Echo2.ListAndMask.List.E2_NaviListActionOnSelection#setProgressWindowPaneLookAndFeel(panter.gmbh.Echo2.components.MyE2_WindowPane)
		 */
		@Override
		public void setProgressWindowPaneLookAndFeel(MyE2_WindowPane pane) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
}
