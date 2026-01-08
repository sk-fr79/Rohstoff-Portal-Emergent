package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class B__Button_ADD_NEW_MailAdress extends MyE2_Button 
{

	private MailBlock oMailBlock = null;
	
	public B__Button_ADD_NEW_MailAdress(MailBlock mailBlock) 
	{
		super(E2_ResourceIcon.get_RI("new.png"));
		this.oMailBlock = mailBlock;
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Neue, leere Mailadresse anfügen !").CTrans());
		
	}
 
	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			B__Button_ADD_NEW_MailAdress oThis = B__Button_ADD_NEW_MailAdress.this;
			
			try
			{
				oThis.oMailBlock.ADD_NewTargetAdress_interactivEmptyAdress();
				oThis.oMailBlock.build_MailSendGrid();
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Am Ende des Blocks wurde eine neue Mailadresse angefuegt !")));
			
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("_Button_ADD_NEW_MailBlock:ownActionAgent:Error starting action",false)));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
		}
		
	}
	
	
	
	
	
	
}

