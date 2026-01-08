package panter.gmbh.Echo2.ActionEventTools.DecisionSimple;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.indep.exceptions.myException;

public class TestButton extends MyE2_Button implements DS_IF_components4decision {

	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();
	
	
	public TestButton() {
		super(new MyE2_String("Test für Sicherheitsabfrage"), new E2_FontBold(6));
		
		this.add_oActionAgent(new ownDecisionAction1(this));
		this.add_oActionAgent(new ownDecisionAction2(this));
		this.add_oActionAgent(new ownDecisionAction3(this));
		this.add_oActionAgent(new ownAction());
		this.add_oActionAgent(new ownAction2());
	}

	private class ownDecisionAction1 extends DS_ActionAgent {

		public ownDecisionAction1(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		public Boolean make_decision_when_true_then_popup() throws myException {
			return true;
		}

		@Override
		public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent) 	throws myException {
			return new ownPopUp();
		}
		
		private class ownPopUp extends DS_PopupContainer4Decision {
			
			public ownPopUp() {
				super(TestButton.this);
				
				E2_Grid4MaskSimple gs=new E2_Grid4MaskSimple().add_(this.get_bt_OK()).add_(this.get_bt_NO());
				this.add(gs);
			}

			@Override
			public int get_width_in_pixel() {	return 400;}

			@Override
			public int get_height_in_pixel() {	return 200; }

			@Override
			public MyE2_String get_titleText4PopUp() { return new MyE2_String("Abfrage 1"); }
			
		}

		@Override
		public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
			return null;
		}
		
	}

	
	private class ownDecisionAction2 extends DS_ActionAgent {

		public ownDecisionAction2(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		public Boolean make_decision_when_true_then_popup() throws myException {
			return true;
		}

		@Override
		public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent) 	throws myException {
			return new ownPopUp();
		}
		
		private class ownPopUp extends DS_PopupContainer4Decision {
			
			public ownPopUp() {
				super(TestButton.this);
				E2_Grid4MaskSimple gs=new E2_Grid4MaskSimple().add_(this.get_bt_OK()).add_(this.get_bt_NO());
				this.add(gs);
			}

			@Override
			public int get_width_in_pixel() {	return 600;}

			@Override
			public int get_height_in_pixel() {	return 100; }

			@Override
			public MyE2_String get_titleText4PopUp() { return new MyE2_String("Abfrage 3"); }
			
		}
		@Override
		public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {

			// TODO Auto-generated method stub
			return null;
		}

	}

	
	private class ownDecisionAction3 extends DS_ActionAgent {

		public ownDecisionAction3(DS_IF_components4decision p_actionComponent) {
			super(p_actionComponent);
		}

		@Override
		public Boolean make_decision_when_true_then_popup() throws myException {
			return true;
		}

		@Override
		public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent) 	throws myException {
			return new ownPopUp();
		}
		
		private class ownPopUp extends DS_PopupContainer4Decision {
			
			public ownPopUp() throws myException {
				super(TestButton.this);
				E2_Grid4MaskSimple gs=new E2_Grid4MaskSimple().add_(this.get_bt_OK()).add_(this.get_bt_NO());
				this.add(gs);
				
				//this.getButtonOK().set_bEnabled_For_Edit(false);
			}

			@Override
			public int get_width_in_pixel() {	return 200;}

			@Override
			public int get_height_in_pixel() {	return 100; }

			@Override
			public MyE2_String get_titleText4PopUp() { return new MyE2_String("Abfrage 3"); }
			
		}
		@Override
		public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
		}

		@Override
		public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {

			// TODO Auto-generated method stub
			return null;
		}

	}

	
	private class ownAction extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			DEBUG.System_println("Hallo Welt ....");
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Test-message"));
			
			
			RECLIST_ADRESSE  ra = new RECLIST_ADRESSE("SELECT JT_ADRESSE.*, NAME1||NAME2 AS NAMEX FROM JT_ADRESSE WHERE ID_ADRESSE<3020");
			
			for (RECORD_ADRESSE  a: ra) {
				DEBUG.System_println(a.get_UnFormatedValue("NAMEX"));
			}
			

		}
	}
	
	
	private class ownAction2 extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			DEBUG.System_println("Quelle :"+((MyE2_Button)oExecInfo.get_MyActionEvent().getSource()).getText());
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Test-message, die zweite..."));

		}
	}
	
	
	
	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
		return this.storage_vector_4_all_agents;
	}

	@Override
	public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
		return this.storage_vector_4_standard_agents;
	}

	@Override
	public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
		return this.storage_vector_4_decision_agents;
	}

	@Override
	public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
		return this.hm_descision_containers;
	}
		
	
	
}
