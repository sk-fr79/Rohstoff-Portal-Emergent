package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import java.util.Vector;

import nextapp.echo2.app.Component;

import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_USER;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.exceptions.myException;


public class TODO_LIST_BT_DELETE_TODO extends MyE2_Button
{

	public TODO_LIST_BT_DELETE_TODO(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_CONSTANTS_AND_NAMES.NAME_MODUL_TODO_LIST,"LOESCHE_TODO"));
		this.add_IDValidator(new ownValidator());
	}
	

	private class ownActionAgent extends ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super(new MyE2_String("Löschen von Todo-Einträgen"), onavigationList);
		}
		
		
		/*
		 * zuerst muss der eintrag in der tabelle jt_lieferadresse/jt_mitarbeiter geloescht werden 
		 */
		public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated) 				
		{
			return new Vector<String>();
		}
		
		public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) 					{return  new Vector<String>();}
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException 	{return  new MyE2_MessageVector();}
		public void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
		public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}

	}
	

	private class ownValidator extends XX_ActionValidator
	{

		public MyE2_MessageVector isValid(Component oCompWhichIsValidated) 
		{
			return new MyE2_MessageVector();
		}

		public MyE2_MessageVector isValid(String cID_Unformated) throws myException 
		{
			MyE2_MessageVector oMV = new MyE2_MessageVector();
			
			
			BASIC_RECORD_USER oUser = new BASIC_RECORD_USER(bibALL.get_ID_USER());
			
			MyDataRecordHashMap oMapToDo = new MyDataRecordHashMap("JT_TODO",cID_Unformated);
			
			//	wenn supervisor, dann ok
			
			if (oUser.is_IST_SUPERVISOR_YES() || oUser.get_TODO_SUPERVISOR_cUF().equals("2"))                  
				return oMV;
			
			//  eigene sind ok
			if (! oMapToDo.get_UnFormatedValue("ID_USER").equals(bibALL.get_ID_USER()))
			{
				oMV.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Löschen nicht erlaubt !!")));
			}
			
			return oMV;
		}
		
	}

	
	
}
