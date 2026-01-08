package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.MITARBEITER;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;


public class FSM_BT_LIST_DELETE_MITARBEITER extends MyE2_Button
{

	public FSM_BT_LIST_DELETE_MITARBEITER(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LOESCHE_MITARBEITER"));

	}
	
	private class ownActionAgent extends ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(new MyE2_String("Löschen eine Mitarbeiters"), onavigationList);
		}
		
		
		/*
		 * zuerst muss der eintrag in der tabelle jt_lieferadresse geloescht werden 
		 */
		public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add("DELETE FROM "+bibE2.cTO()+".JT_MITARBEITER WHERE ID_ADRESSE_MITARBEITER="+cID_toDeleteUnformated);
			return vRueck;
		}


		public MyE2_MessageVector CheckIdToDelete(String cID_UnformatedToDelete) {return null;}
		public void Execute_After_DELETE(String cID_toDeleteUnformated) throws myException {}
		public void Execute_Before_DELETE(String cID_toDeleteUnformated) throws myException {}
		public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) {return  new Vector<String>();}


		public MyE2_MessageVector CheckIdToDelete(Vector<String> unformatedToDelete) throws myException
		{
			return new MyE2_MessageVector();
		}


		public void Execute_After_DELETE(Vector<String> ds_toDeleteUnformated)	throws myException
		{
		}


		public void Execute_Before_DELETE(Vector<String> ds_toDeleteUnformated)	throws myException
		{
		}
	}
	
	
	
	
}
