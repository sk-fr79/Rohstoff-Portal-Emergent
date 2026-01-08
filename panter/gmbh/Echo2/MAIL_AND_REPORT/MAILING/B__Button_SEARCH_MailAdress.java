package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class B__Button_SEARCH_MailAdress extends MyE2_Button 
{

	private MailBlock oMailBlock = null;
	
	
	public B__Button_SEARCH_MailAdress(MailBlock mailBlock) 
	{
		super(E2_ResourceIcon.get_RI("suche.png"));
		this.oMailBlock = mailBlock;
		
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Mailadressen im Adressstamm suchen ...").CTrans());
	}

	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			B__Button_SEARCH_MailAdress oThis = B__Button_SEARCH_MailAdress.this;
			Popup_EMailAdress_Searcher oPopUp = new Popup_EMailAdress_Searcher(null,oThis.oMailBlock);
			oPopUp.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(new Extent(500), new Extent(500), new MyE2_String("Suche nach Mail-Adressen"));
		}
	}
	
	
}

