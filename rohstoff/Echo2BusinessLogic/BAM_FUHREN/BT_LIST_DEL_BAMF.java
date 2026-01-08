/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BAM_FUHREN;

import java.util.Vector;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentSingleDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;


public class BT_LIST_DEL_BAMF extends MyE2_Button
{
	public BT_LIST_DEL_BAMF(	E2_NavigationList oList)
	{
		super(E2_ResourceIcon.get_RI("delete.png"), true);
		this.add_oActionAgent(new ownActionAgent(new MyE2_String("Löschen einer Transport-Positions-Beanstandung:"),oList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_MODULNAMES.NAME_MODUL_FBAM_LIST,"LOESCHE_FBAM"));
	}
	
	
	private class ownActionAgent extends ButtonActionAgentSingleDELETE
	{

		public ownActionAgent(MyE2_String actionName, E2_NavigationList onavigationList)
		{
			super(actionName, onavigationList);
		}
		
		public MyE2_MessageVector CheckIdToDelete(String cID_UnformatedToDelete)
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			try
			{
				MyDataRecordHashMap oFBAM = new MyDataRecordHashMap("JT_FBAM",cID_UnformatedToDelete);
				if (oFBAM.get_UnFormatedValue("ABGESCHLOSSEN_BEHEBUNG").equals("Y"))
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Beanstandungsmeldung darf nicht geloescht werden, Sie wurde bereits abgeschlossen !"), false);
					
				if (oFBAM.get_UnFormatedValue("WM_GESPERRT").equals("Y"))
					oMV.add_MESSAGE(new MyE2_Alarm_Message("Beanstandungsmeldung darf nicht geloescht werden, es wurde bereits eine Weigermeldung erzeugt !"), false);
				
			}
			catch (myException ex)
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Löschen: Die FBam konnte nicht geprüft werden !"), false);
			}
			
			return oMV;
		}

		public void Execute_After_DELETE(String cID_toDeleteUnformated) throws myException {}

		public void Execute_Before_DELETE(String cID_toDeleteUnformated) throws myException {}

		public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) { return new Vector<String>();}

		public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated) {return  new Vector<String>();}
		
	}
	
	

}