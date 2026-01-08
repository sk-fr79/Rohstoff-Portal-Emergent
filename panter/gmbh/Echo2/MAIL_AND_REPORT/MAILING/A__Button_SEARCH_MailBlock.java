package panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.exceptions.myException;

public class A__Button_SEARCH_MailBlock extends MyE2_Button 
{

	private E2_MassMailer oMassMailer = null;
	
	
	public A__Button_SEARCH_MailBlock(E2_MassMailer 	MassMailer) 
	{
		super(E2_ResourceIcon.get_RI("suche.png"));
		this.oMassMailer = MassMailer;
		
		this.add_oActionAgent(new ownActionAgent());
		this.setToolTipText(new MyE2_String("Mailadressen im Adressstamm suchen ...").CTrans());
	}

	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			A__Button_SEARCH_MailBlock oThis = A__Button_SEARCH_MailBlock.this;
			Popup_EMailAdress_Searcher oPopUp = new Popup_EMailAdress_Searcher(oThis.oMassMailer,null);
			Extent extWidth = new Extent(oThis.oMassMailer.get_oExtWidth().getValue()-50);
			Extent extHeight = new Extent(oThis.oMassMailer.get_oExtHeight().getValue()-100);
			oPopUp.CREATE_AND_SHOW_POPUPWINDOW_SPLIT(extWidth, extHeight, new MyE2_String("Suche nach Mail-Adressen"));
		}
	}
	
	
}

