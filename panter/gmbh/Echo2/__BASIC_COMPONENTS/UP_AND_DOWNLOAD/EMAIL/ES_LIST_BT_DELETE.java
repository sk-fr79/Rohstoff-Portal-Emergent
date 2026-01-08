package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;


import java.util.Vector;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.ActionAgents.ButtonActionAgentMULTIDELETE;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.__VALIDATOR_4_ATTACHMENT_POPUP;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.GENERATE.GenTERM;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EMAIL_SEND_ext;

public class ES_LIST_BT_DELETE extends MyE2_Button
{

	public ES_LIST_BT_DELETE(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("delete.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList,this));
//		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("LOESCHE_ES"));
		this.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.DELETE_MAIL));
		this.add_GlobalValidator(new ownValidator(onavigationList));
	}
	

	private class ownActionAgent extends ButtonActionAgentMULTIDELETE
	{
		public ownActionAgent(E2_NavigationList onavigationList,  MyE2_Button oownButton)
		{
			super(new MyE2_String("Loeschen von -Eintraegen"), onavigationList);
		}
		
		/*
		 * zuerst muss der eintrag in der tabelle jt_lieferadresse/jt_mitarbeiter geloescht werden 
		 */
		public Vector<String> get_vSQL_Before_DELETE(String cID_toDeleteUnformated)  			{
			
			Vector<String> vSQL_Rueck = new Vector<String>();
			
			try {
				GenTERM term1 = new GenTERM();
				term1.AppendTerm(_DB.EMAIL_SEND_ATTACH$ID_EMAIL_SEND, "=",cID_toDeleteUnformated );
				vSQL_Rueck.add("DELETE FROM "+bibE2.cTO()+"."+_DB.EMAIL_SEND_ATTACH+" WHERE "+term1.get_TERMS());

				GenTERM term2 = new GenTERM();
				term2.AppendTerm(_DB.EMAIL_SEND_TARGETS$ID_EMAIL_SEND, "=",cID_toDeleteUnformated );
				vSQL_Rueck.add("DELETE FROM "+bibE2.cTO()+"."+_DB.EMAIL_SEND_TARGETS+" WHERE "+term1.get_TERMS());
				
			} catch (myException e) {
				e.printStackTrace();
			}
			
			return  vSQL_Rueck;
			
		}
		public Vector<String> get_vSQL_After_DELETE(String cID_toDeleteUnformated) 			{return  new Vector<String>();}
		public MyE2_MessageVector CheckIdToDelete(Vector<String> vID_UnformatedToDelete) throws myException 	{return  new MyE2_MessageVector();}
		public void Execute_After_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}
		public void Execute_Before_DELETE(Vector<String> vIDs_toDeleteUnformated) throws myException {}

	}
	


	
	private class ownValidator extends XX_ActionValidator {

		private E2_NavigationList  navi_list = null;
		
		public ownValidator(E2_NavigationList  navilist) {
			super();
			this.navi_list=navilist;
		}

		@Override
		public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			Vector<String> vIDs = this.navi_list.get_vSelectedIDs_Unformated();
			for (String cID: vIDs) {
				RECORD_EMAIL_SEND_ext recSend = new RECORD_EMAIL_SEND_ext(new RECORD_EMAIL_SEND(cID));
				if (!recSend.get_bAllowedToDelete()){
					mv.add(new MyE2_Alarm_Message(new MyE2_String("Die Mail ",true,
																	""+recSend.get_ID_EMAIL_SEND_cUF(),false,
																	" kann nicht gelöscht werden ! ",true,
																	" (Der Grund kann sein: Es ist ein Original oder es wurden einige oder alle bereits versandt)",true)));
				}
			}
			return mv;
		}

		@Override
		protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	
}
