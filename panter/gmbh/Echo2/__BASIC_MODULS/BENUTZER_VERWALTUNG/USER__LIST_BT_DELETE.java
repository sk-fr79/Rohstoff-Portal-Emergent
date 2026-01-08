package panter.gmbh.Echo2.__BASIC_MODULS.BENUTZER_VERWALTUNG;


import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_ADMIN;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class USER__LIST_BT_DELETE extends MyE2_Button
{

	public USER__LIST_BT_DELETE(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		//this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LOESCHE_USER"));
		this.add_GlobalValidator(new E2_ButtonAUTH_ONLY_ADMIN());  //2014-02-17: neu: Benutzung fuer alle USER moeglich, aber nur fuer den eigenen Account

	}
	

	private class ownActionAgent extends ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(new MyE2_String("Loeschen von -Eintraegen"), onavigationList);
		}
		
		/*
		 * zuerst muss der eintrag in der tabelle jt_lieferadresse/jt_mitarbeiter geloescht werden 
		 */
		public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated)  			{return  new Vector<String>();}
		public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) 			{return  new Vector<String>();}
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException 	{return  new MyE2_MessageVector();}
		public void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
		public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}

	}
	


	
	
}
