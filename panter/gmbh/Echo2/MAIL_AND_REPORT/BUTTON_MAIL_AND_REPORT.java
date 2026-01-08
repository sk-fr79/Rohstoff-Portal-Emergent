package panter.gmbh.Echo2.MAIL_AND_REPORT;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public abstract class BUTTON_MAIL_AND_REPORT extends MyE2_Button
{
	
	private ACTIONAGENT_MAIL_AND_REPORT          ownACTIONAGENT_MAIL_AND_REPORT = null;
	
	/**
	 * 
	 * @param NameOfIcon
	 * @param fensterTitel
	 * @param valid_tag_print
	 * @param valid_tag_mail
	 * @param actionAfterPrintOrMail
	 */
	public BUTTON_MAIL_AND_REPORT(	String 			NameOfIcon,
										MyE2_String 	fensterTitel, 
										String 			valid_tag_print,
										String 			valid_tag_mail, 
										XX_ActionAgent 	actionAfterPrintOrMail) 
	{
		super(E2_ResourceIcon.get_RI(S.isEmpty(NameOfIcon)?"printer.png":NameOfIcon),true);
		this.add_oActionAgent(this.ownACTIONAGENT_MAIL_AND_REPORT=new ownActionAgent(fensterTitel, valid_tag_print,	valid_tag_mail, actionAfterPrintOrMail));
	}


	/**
	 * 
	 * @param NameOfIcon
	 * @param fensterTitel
	 * @param valid_tag_print
	 * @param valid_tag_mail
	 * @param actionAfterPrintOrMail
	 */
	public BUTTON_MAIL_AND_REPORT(	MyString 		cButtonText,
										MyE2_String 	fensterTitel, 
										String 			valid_tag_print,
										String 			valid_tag_mail, 
										XX_ActionAgent 	actionAfterPrintOrMail) 
	{
		super(cButtonText);
		this.add_oActionAgent(this.ownACTIONAGENT_MAIL_AND_REPORT=new ownActionAgent(fensterTitel, valid_tag_print,	valid_tag_mail, actionAfterPrintOrMail));
	}



	public abstract V_JasperHASH 		get_VJasperHashMAP(ExecINFO execInfo) throws myException;
	public abstract E2_MassMailer  		get_MassMailer() throws myException;


	
	
	private class ownActionAgent extends ACTIONAGENT_MAIL_AND_REPORT
	{

		public ownActionAgent(MyE2_String fensterTitel, String valid_tag_print,	String valid_tag_mail, XX_ActionAgent actionAfterPrintOrMail)
		{
			super(fensterTitel, valid_tag_print, valid_tag_mail, actionAfterPrintOrMail,false);
		}

		@Override
		public E2_MassMailer get_MassMailer() throws myException
		{
			return BUTTON_MAIL_AND_REPORT.this.get_MassMailer();
		}

		@Override
		public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException
		{
			return BUTTON_MAIL_AND_REPORT.this.get_VJasperHashMAP(execInfo) ;
		}
	}




	public ACTIONAGENT_MAIL_AND_REPORT get_ownACTIONAGENT_MAIL_AND_REPORT()
	{
		return ownACTIONAGENT_MAIL_AND_REPORT;
	}
	
	
		
}
