package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.AW2_RECLIST_M2N;
import rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.AW2__BasicContainer_RohstoffAuswertungen;

public class RR__bt_callPopup extends MyE2_Button {

	private AW2__BasicContainer_RohstoffAuswertungen 	callingTabContainer = null;

	
	public RR__bt_callPopup(AW2__BasicContainer_RohstoffAuswertungen 	p_callingTabContainer) {
		super(new MyE2_String("Report-Reiter"), MyE2_Button.StyleImageButtonCenteredWithBlackBorder());
		this.callingTabContainer = p_callingTabContainer;
		
		this.setToolTipText(new MyE2_String("Die Einträge der Report-Reiter bearbeiten").CTrans());
		
		this.add_oActionAgent(new ownActionClearDoubles());
		this.add_oActionAgent(new ownAction());
	}
	
	private class ownAction extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new RR_LIST_BasicModuleContainer(RR__bt_callPopup.this.callingTabContainer).CREATE_AND_SHOW_POPUPWINDOW(new Extent(700), new Extent(600), new MyE2_String("Report-Gruppen-Reiter erfassen"));
		}
	}

	private class ownActionClearDoubles extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			new AW2_RECLIST_M2N().clear__doubleValues();
		}
		
	}
	
	
}
