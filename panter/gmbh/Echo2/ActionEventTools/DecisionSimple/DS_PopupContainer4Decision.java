package panter.gmbh.Echo2.ActionEventTools.DecisionSimple;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.IF_Handles_StealthMode;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.MyActionEvent;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_WindowPane;
import panter.gmbh.indep.exceptions.myException;

/**
 * abstract zwingt zum manuellen erzeugen einer klasse und damit separates groesse-speichern
 * @author martin
 *
 */
public abstract class DS_PopupContainer4Decision extends E2_BasicModuleContainer {

	public enum DECISION {
		OK
		,CANCEL
	}
	
	private DECISION 					my_descision = 				null;   //bleibts bei null ist es gleich cancel
	private DS_IF_components4decision 	component_with_decision = 	null;
	
	private MyE2_Button                 buttonOK = new ownButtonYes();
	private MyE2_Button                 buttonNO = new ownButtonNo();
	
	private MyActionEvent               actionAgentOfLastActionEvent = null;
	
	/*
	 * der DS_ActionAgent der den popup-container aufruft
	 */
	private DS_ActionAgent  			myDecisionActionAgent = null;
	
	public DS_PopupContainer4Decision(DS_IF_components4decision p_motherComponent) {
		super();
		this.component_with_decision = p_motherComponent;
		this.actionAgentOfLastActionEvent=bibE2.get_LAST_ACTIONEVENT();
		this.set_oStyleForWindowPane(MyE2_WindowPane.STYLE_WINDOW_SMALL_TITLE_NO_CLOSE());
	}

	public DECISION get_my_descision() {
		return my_descision;
	}

	public void set_my_descision(DECISION p_descision) {
		this.my_descision = p_descision;
	}

	
	public abstract int get_width_in_pixel();
	public abstract int get_height_in_pixel();
	public abstract MyE2_String get_titleText4PopUp();
	
	public boolean is_decision_ok() {
		if (this.my_descision!=null && this.my_descision==DECISION.OK) {
			return true;
		}
		return false;
	}
	
	
	private class ownButtonYes extends MyE2_Button implements IF_Handles_StealthMode{
		public ownButtonYes() {
			super(new MyE2_String("OK"));
			this.add_oActionAgent(new ownActionOk());
		}

		@Override
		public MyActionEvent get_action_event_of_calling_klick() throws myException {
			return DS_PopupContainer4Decision.this.actionAgentOfLastActionEvent;
		}


	}
	
	private class ownButtonNo extends MyE2_Button {
		public ownButtonNo() {
			super(new MyE2_String("Abbruch"));
			this.add_oActionAgent(new ownActionAbgelehnt());
		}
	}
	
	private class ownActionAbgelehnt extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			DS_PopupContainer4Decision oThis = DS_PopupContainer4Decision.this;
			
			oThis.my_descision=DECISION.CANCEL;
			
			//dann alle popup-container schliessen, damit gehts nicht weiter
			for (DS_PopupContainer4Decision pop: oThis.component_with_decision.get_hm_descision_containers().values()) {
				if (pop.IS_Popup()) {
					pop.CLOSE_AND_DESTROY_POPUPWINDOW(false);
				}
			}
		}
	}
	
	
	private class ownActionOk extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			DS_PopupContainer4Decision oThis = DS_PopupContainer4Decision.this;
			
			//2016-04-07: hier koennen aktionen ausgeloest werden, die vor dem OK durchgefuehrt werden muessen
			MyE2_MessageVector mv = oThis.myDecisionActionAgent.is_something_to_do_before_ok_is_possible();
			bibMSG.add_MESSAGE(mv);
			
			if (mv==null ||mv.get_bIsOK()) {
			
				oThis.my_descision=DECISION.OK;
				oThis.CLOSE_AND_DESTROY_POPUPWINDOW(true);
				
				boolean bAllPopupsAreClosed = true;
				//dann alle popup-container schliessen, damit gehts nicht weiter
				for (DS_PopupContainer4Decision pop: oThis.component_with_decision.get_hm_descision_containers().values()) {
					if (pop.IS_Popup()) {
						bAllPopupsAreClosed = false;
					}
				}
				
				
				if (bAllPopupsAreClosed) {
					//wenn es der letzte war, dann die standard-actions auf der komponente durchlaufen lassen und wieder restaurieren
					oThis.component_with_decision.get_vActionAgents().removeAllElements();
					oThis.component_with_decision.get_vActionAgents().addAll(oThis.component_with_decision.get_storage_vector_4_standard_agents());
					oThis.component_with_decision.doActionPassiv();
					oThis.component_with_decision.get_storage_vector_4_decision_agents().get(0).reset_component_status2();
				}
			}
		}
	}


	public MyE2_Button get_bt_OK() {
		return buttonOK;
	}

	public MyE2_Button get_bt_NO() {
		return buttonNO;
	}

	public MyActionEvent get_actionAgentOfLastActionEvent() {
		return actionAgentOfLastActionEvent;
	}

	/**
	 * 2016-05-07
	 * 
	 * @param DecisionActionAgent
	 */
	public void set_myDecisionActionAgent(DS_ActionAgent DecisionActionAgent) {
		this.myDecisionActionAgent = DecisionActionAgent;
	}
	

	
}
