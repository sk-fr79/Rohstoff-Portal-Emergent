package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBResultSet;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MailException;
import panter.gmbh.indep.mail.SendMail;


public class CAL_TerminButton_SEND_MAIL extends MyE2_Button 
{
	private CAL_TerminDataRecordHashMap   	hmTerminQuery = null;
	
	public CAL_TerminButton_SEND_MAIL(CAL_TerminDataRecordHashMap   drhmTerminQuery) 
	{
		super(E2_ResourceIcon.get_RI("sendmail.png"),true);
		this.hmTerminQuery = drhmTerminQuery;
		
		this.add_oActionAgent(new actionEditSendMail());
	}

	public CAL_TerminDataRecordHashMap get_hmTerminQuery() 
	{
		return hmTerminQuery;
	}

	
	
	 private class actionEditSendMail extends XX_ActionAgent
	 {
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			CAL_TerminButton_SEND_MAIL 	oThis = 		CAL_TerminButton_SEND_MAIL.this;
				
			String cID_Termin = oThis.hmTerminQuery.get_cID_TERMIN();
			
		    String cInfoOK = "";
		    String cInfoError = "";
		    
			String cSQL = "select EMAIL from "+bibE2.cTO()+".jd_user,"+bibE2.cTO()+".jt_termin_user where id_termin = "+cID_Termin+
							" and jd_user.id_user = jt_termin_user.id_user and email is not null";
			
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			MyDBResultSet oRS = oDB.OpenResultSet(cSQL);
			
			
			if (oRS.RS != null)
			{
				SendMail oSendMail;
				String cSQLSender = "select EMAIL,kurztext,langtext,datum,zeit_von,zeit_bis from "+
												bibE2.cTO()+".jd_user,"+
												bibE2.cTO()+".jt_termin, "+
												bibE2.cTO()+".jt_termin_user where" +
														" jt_termin_user.id_termin = "+cID_Termin+" and " +
														" jt_termin.id_termin = "+cID_Termin+" and " +
														" jd_user.id_user = jt_termin_user.id_user and " +
														" is_owner = 'Y'";
				
				String cSend[][] = oDB.EinzelAbfrageInArrayFormatiert(cSQLSender,"@@");
				String cSender = cSend[0][0];
				String cBetreff = "TERMIN-INFO: "+cSend[0][1];
				String cLangText = cSend[0][3]+'\n'+cSend[0][4]+" --- "+cSend[0][5]+'\n'+cSend[0][2];
				try
				{
					while(oRS.RS.next())
					{
						String cServer = bibALL.get_cMAILSERVER_FOR_SMTP_RELAYING();
						String cSMTPUser = bibALL.get_cMAILUSERNAME_FOR_SMTP_RELAYING();
						String cPWD	 = 	bibALL.get_cMAILPASSWORD_FOR_SMTP_RELAYING();
						String cReciver = oRS.RS.getString("email");
						
						oSendMail = new SendMail(cServer,cSMTPUser,cPWD,cSender,cReciver,null,null,cBetreff,cLangText,null);
						cInfoOK += "\n"+cReciver;
						
						oSendMail.sendViaStandardMail();								
					}
				}
				catch( MailException  error)
				{
					cInfoError += error.getErrorMessage();
				}
				catch(Exception error)
				{
				    cInfoError += error.getLocalizedMessage();
				}
			    if (!cInfoError.equals(""))
			    {
			        bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(new MyE2_String("Fehler beim Senden : ").CTrans()+" "+cInfoError,false)));
			        		
			    }
			    if (!cInfoOK.equals(""))
			    {
			        bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String(new MyE2_String("Gesendet an : ").CTrans()+cInfoOK)));
			    }
			    oRS.Close();
			}
			else
			{
			    bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler! Email kann nicht verschickt werden (Datenbankfehler) !"));
			}
				
		}
		 
	 }

	
	
	
	
}
