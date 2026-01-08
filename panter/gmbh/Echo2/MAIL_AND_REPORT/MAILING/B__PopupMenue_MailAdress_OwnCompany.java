package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.indep.exceptions.myException;

public class B__PopupMenue_MailAdress_OwnCompany extends MyE2_PopUpMenue
{
	private MailBlock oMailBlock = null;

	public B__PopupMenue_MailAdress_OwnCompany(MailBlock mailBlock)   throws myException
	{
		super(E2_ResourceIcon.get_RI("person_popup.png"), E2_ResourceIcon.get_RI("person_popup.png"), true);
		this.oMailBlock = mailBlock;

		this.get_oContainerEx().setWidth(new Extent(300));
		this.get_oContainerEx().setHeight(new Extent(150));
		
		//jetzt die mitarbeiter-email-adressen einlesen
		Vector<String> vMailAdressen = bibE2.get_activMitarbeiterEmails();
		
		for (int i=0;i<vMailAdressen.size();i++)
		{
			MyE2_Button oButton = new MyE2_Button(new MyE2_String(vMailAdressen.get(i),false));
			oButton.EXT().set_C_MERKMAL(vMailAdressen.get(i));
			oButton.add_oActionAgent(new ownActionAgent());
			this.addButton(oButton, true);
		}
	
	}

	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			B__PopupMenue_MailAdress_OwnCompany oThis = B__PopupMenue_MailAdress_OwnCompany.this;
			MyE2_Button   oButton = (MyE2_Button)oExecInfo.get_MyActionEvent().getSource();
			
			try
			{

				oThis.oMailBlock.ADD_MailAdress4MailBlock4Added_MitarbeiterMail(oButton.EXT().get_C_MERKMAL());
				oThis.oMailBlock.build_MailSendGrid();
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Am Ende der Liste folgende Mitarbeiter-eMail angefügt: ",true,oButton.EXT().get_C_MERKMAL(),false)));
			
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Button_ADD_NEW_MailAdress_Field:ownActionAgent:Error starting action",false)));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage(), false);
			}
			
		}
		
	}

	
}
