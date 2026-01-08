package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KORR;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;



public class FUK_Bt_StartKorr extends MyE2_Button {

	private E2_NavigationList   oNaviList = null;

	public FUK_Bt_StartKorr(E2_NavigationList   NaviList) {
		super(new MyE2_String("Alternative Lieferbedingung neu setzen"));
		
		this.oNaviList = NaviList;
		
		this.add_GlobalAUTHValidator_AUTO("KORRIGIERE_LIEFERBEDINGUNGEN");
		
		this.add_oActionAgent(new ownActionAgent());
	}
	
	
	private class ownActionAgent extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new FUK_Popup_SelectRange(FUK_Bt_StartKorr.this.oNaviList);
		}
		
	}
	
	
	
	
}
