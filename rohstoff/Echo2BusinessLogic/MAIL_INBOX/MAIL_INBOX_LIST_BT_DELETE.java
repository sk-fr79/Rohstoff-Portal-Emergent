package rohstoff.Echo2BusinessLogic.MAIL_INBOX;


import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgent_TOGGLE_Y_N;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class MAIL_INBOX_LIST_BT_DELETE extends MyE2_Button
{

	public MAIL_INBOX_LIST_BT_DELETE(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.setToolTipText(new MyE2_String("Markiert die gewählten Zeilen als gelöscht").CTrans() );
		
//		this.add_oActionAgent(new ownActionAgent(onavigationList,this));
		
		this.add_oActionAgent(new deleteActionAgent(new MyE2_String("Löschen von Emails aus der Inbox"),onavigationList,"DELETED","JT_EMAIL_INBOX","ID_EMAIL_INBOX"));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LOESCHE_MAIL_INBOX"));
	}
	
	
	
	private class deleteActionAgent extends ButtonActionAgent_TOGGLE_Y_N
	{
		
		public deleteActionAgent(MyE2_String actionName,
				E2_NavigationList onavigationList, String FieldNAME_YES_NO,
				String TABLENAME, String ID_TABLENAME) {
			super(actionName, onavigationList, FieldNAME_YES_NO, TABLENAME, ID_TABLENAME);
		}
		

		@Override
		public Vector<String> get_vSQL_Before_TOGGLE(
				String cID_toToggleUnformated, String cNewValue)
				throws myException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Vector<String> get_vSQL_After_TOGGLE(
				String cID_toToggleUnformated, String cNewValue)
				throws myException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void Execute_Before_TOGGLE(Vector<String> vIDs_toToggleUnformated)
				throws myException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void Execute_After_TOGGLE(Vector<String> vIDs_toToggleUnformated)
				throws myException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public MyE2_MessageVector CheckIdToToggle(
				Vector<String> vID_UnformatedToDelete) {
			// TODO Auto-generated method stub
//			return new MyE2_MessageVector(new MyE2_Alarm_Message("test"));
			return null;
		}



		
	
	}



	
	
}
