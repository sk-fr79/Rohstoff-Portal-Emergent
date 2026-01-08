package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;
import panter.gmbh.Echo2.Container.E2_ConfirmBasicModuleContainer;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_USER;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDataRecordHashMap;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MyMailHelper;


public class TODO_LIST_BT_MAIL_TODO extends MyE2_Button
{

	private E2_NavigationList	oNaviListToDos = null;
	
	public TODO_LIST_BT_MAIL_TODO(E2_NavigationList 	onavigationList)
	{
		super(E2_ResourceIcon.get_RI("emailwhite.png"), true);
		
		this.oNaviListToDos = onavigationList;
		this.add_oActionAgent(new ownActionAgent());
		this.add_GlobalValidator(new E2_ButtonAUTHValidator(E2_CONSTANTS_AND_NAMES.NAME_MODUL_TODO_LIST,"SENDE_MAIL"));

		
	}


	private class ownActionAgent extends XX_ActionAgent
	{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			E2_NavigationList oNaviList = TODO_LIST_BT_MAIL_TODO.this.oNaviListToDos;
			
			try
			{
				Vector<String> vIDs = oNaviList.get_vSelectedIDs_Unformated();
				if (vIDs.size()==0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Bitte mindestens eine TODO selektieren !!"), false);
					return;
				}
				
				String cSenderEmail = bibALL.get_RECORD_USER().get_EMAIL_cUF_NN("");
				
				if (bibALL.isEmpty(cSenderEmail))
					throw new myException(this,": Error sending mail: User "+bibALL.get_USERNAME()+" has no Sending-Adress !");
				
				
				Vector<MyMailHelper> vMailHelpers = new Vector<MyMailHelper>();
				
				
				for (int i=0;i<vIDs.size();i++)
				{
					MyDataRecordHashMap oHMToDO = new MyDataRecordHashMap("JT_TODO",(String)vIDs.get(i));
					
					String cQuery = "SELECT DISTINCT JD_USER.EMAIL FROM "+bibE2.cTO()+".JT_TODO_TEILNEHMER,  "+bibE2.cTO()+".JD_USER " +
											" WHERE JT_TODO_TEILNEHMER.ID_USER = JD_USER.ID_USER AND JT_TODO_TEILNEHMER.ID_TODO="+(String)vIDs.get(i)+" AND " +
											" NVL(JD_USER.EMAIL,'--')<>'--' "+
									" UNION "+
									" SELECT JD_USER.EMAIL FROM "+bibE2.cTO()+".JD_USER WHERE ID_USER="+bibALL.get_ID_USER()+" AND NVL(JD_USER.EMAIL,'--')<>'--' ";
					
					String[][] cZielAdressen = bibDB.EinzelAbfrageInArray(cQuery);
					
					if (cZielAdressen==null)
						throw new myException(this,"Error Quering e-Mail-Adresses ...");
					
					BASIC_RECORD_USER oUser = new BASIC_RECORD_USER(oHMToDO.get_UnFormatedValue("ID_USER"));
					
					
					
					for (int k=0;k<cZielAdressen.length;k++)
					{
						String cText = "Folgendes ist zu erledigen: \n---------------------------------------------------------------\n"+
										oHMToDO.get_FormatedValue_LeerWennNull("AUFGABEKURZ");
						cText += "\n\n"+ oHMToDO.get_FormatedValue_LeerWennNull("AUFGABEKURZ");
						cText += "\n"+ oHMToDO.get_FormatedValue_LeerWennNull("AUFGABENTEXT");
						cText += "\n\n"+ oHMToDO.get_FormatedValue_LeerWennNull("ANTWORTKURZ");
						cText += "\n"+ oHMToDO.get_FormatedValue_LeerWennNull("ANTWORTTEXT");
						cText += "\n---------------------------------------------------------------\n";
						cText += "\n\nErstellt von: "+ oUser.get_VORNAME_cF_NN("")+" "+oUser.get_NAME1_cUF_NN("");
						cText += "\n\nErstellt am: "+ oHMToDO.get_FormatedValue_LeerWennNull("GENERIERUNGSDATUM");
						cText += "\nFrist Ablauf am: "+ oHMToDO.get_FormatedValue_LeerWennNull("ABLAUFDATUM");
						cText += "\n\n\n"+ bibALL.get_EMAIL_SIGNATUR();
						
						MyMailHelper oMail = new MyMailHelper(
											cSenderEmail,
											cZielAdressen[k][0],
											new MyE2_String("Es gibt eine TODO-Information fuer Sie ...").CTrans(),
											cText,
											null);
						
						vMailHelpers.add(oMail);
					}
				}
				
				
				/*
				 * jetzt senden
				 */
				int iOK = 0;
				int iError = 0;
				Vector<MyString> vSendedMails   = new Vector<MyString>();
				
				for (int i=0;i< vMailHelpers.size();i++)
				{
					if (((MyMailHelper)vMailHelpers.get(i)).doSendWith_REAL_Adress())
					{
						iOK++;
						vSendedMails.add(new MyE2_String("** OK ***        "+((MyMailHelper)vMailHelpers.get(i)).get_REAL_ReceiverEmail(),false));
					}
					else
					{
						iError++;
						vSendedMails.add(new MyE2_String("** ERROR ***  "+((MyMailHelper)vMailHelpers.get(i)).get_REAL_ReceiverEmail(),false));
					}
				}
				if (iError>0)
				{
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT(new MyE2_String("Verschickte Mails: ").CTrans()+iOK+"   --  "+new MyE2_String("Fehlerhafte Mails: ").CTrans()+iError));
				}
				else
				{
					bibMSG.add_MESSAGE(new MyE2_Info_Message_OT(new MyE2_String("Verschickte Mails: ").CTrans()+iOK+"   --  "+new MyE2_String("Fehlerhafte Mails: ").CTrans()+iError));
				}

				E2_ConfirmBasicModuleContainer oMessageBox = new E2_ConfirmBasicModuleContainer(
						new MyE2_String("Mail-Status"),
						new MyE2_String("Liste der Adressen"),
						vSendedMails,
						new MyE2_String("OK"), 
						new MyE2_String("Abbruch"),
						new Extent(500),
						new Extent(500));
		
				oMessageBox.show_POPUP_BOX();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("BAM_ActionAgents:ActionAgentSendMailToUsers:Error : "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
			catch (Exception ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT("BAM_ActionAgents:ActionAgentSendMailToUsers:Unknown Error: "+ex.getLocalizedMessage()));
			}
			
		}
		
	}
	
	
	
	
}
