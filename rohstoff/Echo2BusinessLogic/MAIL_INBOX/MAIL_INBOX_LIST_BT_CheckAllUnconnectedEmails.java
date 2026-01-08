package rohstoff.Echo2BusinessLogic.MAIL_INBOX;


import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class MAIL_INBOX_LIST_BT_CheckAllUnconnectedEmails extends MyE2_Button
{

	public MAIL_INBOX_LIST_BT_CheckAllUnconnectedEmails(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("verbinden.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.setToolTipText(new MyString("Ermitteln von Adressen zu noch nicht zugeordneten Emails.").CTrans());
		
		this.add_oActionAgent(new ownActionAgent(onavigationList,this));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("VERBINDE_MAILS_UND_ADRESSEN"));
	}
	

	private class ownActionAgent extends XX_ActionAgent
	{
		E2_NavigationList m_oNaviList = null;
		public ownActionAgent(E2_NavigationList onavigationList,  MyE2_Button oownButton)
		{
			super();
			m_oNaviList = onavigationList;
		}
		
	
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MAIL_INBOX_Handler oHandler = new MAIL_INBOX_Handler();
			
			oHandler.autoConnectAllUnconnectedEmailsToAddresses();
			m_oNaviList.RefreshList();
			
		}

	}
	


	
	
}
