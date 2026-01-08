package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import java.util.Vector;

import nextapp.echo2.app.Component;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;


public class PROJEKT_LIST_BT_DELETE extends MyE2_Button
{

	public PROJEKT_LIST_BT_DELETE(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_PROJEKT_LISTE,"LOESCHE_PROJEKT"));
		this.add_IDValidator(new onlyOwnProjectsToDelete());
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
		public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated)  		
		{
			Vector<String>  vRueck = new Vector<String>();
			vRueck.add("DELETE FROM "+bibE2.cTO()+".JT_PROJEKTINFO WHERE ID_PROJEKT="+cID_toDeleteUnformated);
			vRueck.add("DELETE FROM "+bibE2.cTO()+".JT_PRO_MITARB WHERE ID_PROJEKT="+cID_toDeleteUnformated);
			vRueck.add("DELETE FROM "+bibE2.cTO()+".JT_PRO_ADRESSEN WHERE ID_PROJEKT="+cID_toDeleteUnformated);
			
			
			return vRueck;
		}
		public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) 					{return  new Vector<String>();}
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException 	{return  new MyE2_MessageVector();}
		public void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
		public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}

	}
	

	
	private class onlyOwnProjectsToDelete extends XX_ActionValidator
	{

		public MyE2_MessageVector isValid(Component oCompWhichIsValidated)
		{
			return new MyE2_MessageVector();
		}
		
		public MyE2_MessageVector isValid(String cID_Unformated)
		{
			
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			if (bibALL.get_bIST_SUPERVISOR())
				return oMV;
				
			String cQueryIsOne = bibDB.EinzelAbfrage("SELECT ID_USER FROM "+bibE2.cTO()+".JT_PROJEKT WHERE ID_PROJEKT="+cID_Unformated);
			if (!cQueryIsOne.equals(bibALL.get_ID_USER()))
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nur der Besitzer oder Administrator darf ein Projekt löschen !!")));
			
			return oMV;
		}
		
	}

	
	
}
