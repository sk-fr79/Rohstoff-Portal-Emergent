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

public class A__Button_ADD_NEW_MailBlock extends MyE2_Button 
{

	private E2_MassMailer oMassMailer = null;
	
	public A__Button_ADD_NEW_MailBlock(E2_MassMailer MassMailer) 
	{
		super(E2_ResourceIcon.get_RI("new.png"));
		this.oMassMailer = MassMailer;
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Neue, leere Mailadresse anfügen !").CTrans());
		
	}

	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			A__Button_ADD_NEW_MailBlock oThis = A__Button_ADD_NEW_MailBlock.this;
			
			try
			{
				
				//als securitypolicy fuer neu-eingaben ist nur aendern sinnvoll
				MailBlock oMailBlock = oThis.oMassMailer.build_MailBlock4Added_EmptyMails();
				oMailBlock.ADD_NewTargetAdress("");
				
				oThis.oMassMailer.get_MailBlockVector().add(oMailBlock);
				oThis.oMassMailer.paint_MailMask();
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Am Ende der Liste wurde eine neue Mailadresse angefuegt !")));
			
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Button_ADD_NEW_MailAdress_Field:ownActionAgent:Error starting action",false)));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
		}
		
	}
	
	
	
	
	
	
}

