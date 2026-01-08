package rohstoff.Echo2BusinessLogic.WIEGEKARTE;


import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgent_TOGGLE_Y_N;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class WK_LIST_BT_DELETE extends MyE2_Button
{

	public WK_LIST_BT_DELETE(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("storno.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("STORNIERE_WK"));
	}
	

	private class ownActionAgent extends ButtonActionAgent_TOGGLE_Y_N
	{
		public ownActionAgent(E2_NavigationList onavigationList,  MyE2_Button oownButton)
		{
			super(new MyE2_String("Stornieren der Wiegekarte"), onavigationList, "STORNO", "JT_WIEGEKARTE", "ID_WIEGEKARTE");
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
			return null;
		}

	}
	


	
	
}
