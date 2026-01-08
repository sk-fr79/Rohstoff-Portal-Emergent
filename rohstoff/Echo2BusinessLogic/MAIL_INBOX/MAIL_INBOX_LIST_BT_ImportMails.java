package rohstoff.Echo2BusinessLogic.MAIL_INBOX;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class MAIL_INBOX_LIST_BT_ImportMails extends MyE2_Button
{

	public MAIL_INBOX_LIST_BT_ImportMails(E2_NavigationList onavigationList)
	{
		super(E2_ResourceIcon.get_RI("download_emails.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent(onavigationList));
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("DOWNLOAD_EMAILS_MAIL_INBOX"));
		this.setToolTipText(new MyE2_String("Importieren von neuen eMails").CTrans());
	}
	
	private class ownActionAgent extends XX_ActionAgent
	{
		E2_NavigationList m_navList = null;
		public ownActionAgent(E2_NavigationList onavigationList)
		{
			super();
			m_navList = onavigationList;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			MAIL_INBOX_Handler oHandler = new MAIL_INBOX_Handler();
			oHandler.importMailsFromInboxes();
			
			m_navList.RefreshList();
		}
	}
	
}