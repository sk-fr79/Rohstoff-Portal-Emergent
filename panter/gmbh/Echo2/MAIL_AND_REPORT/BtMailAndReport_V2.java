package panter.gmbh.Echo2.MAIL_AND_REPORT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.E2_MassMailer_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.V_JasperHASH;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public abstract class BtMailAndReport_V2 extends E2_Button {
	
	private E2_MassMailer  		massMailer = 		null;
	
	/**
	 * 
	 * @param NameOfIcon
	 * @param fensterTitel
	 * @param valid_tag_print
	 * @param valid_tag_mail
	 * @param actionAfterPrintOrMail
	 */
	public BtMailAndReport_V2() {
		super();
		this._image("printer.png",true);

		
	}


	/**
	 * quick and dirty
	 * @param baseKenner
	 * @return
	 * @throws myException 
	 */
	public BtMailAndReport_V2 _init(String baseKenner) throws myException {
		this.massMailer = new E2_MassMailer_STD(baseKenner,baseKenner,baseKenner);
		this.add_oActionAgent(new ownActionAgent(S.ms("").ut(baseKenner), baseKenner,	baseKenner, null));
		return this;
	}
	

	
	/**
	 * 
	 * @param title4popup
	 * @param validTagForPrint
	 * @param validTagForMail
	 * @param actionAfterPrintOrMail
	 * @param klemmbrettKennerBetreff
	 * @param klemmbrettKennerMail
	 * @param archivKenner
	 * @return
	 * @throws myException
	 */
	public BtMailAndReport_V2 _init(	MyE2_String 	title4popup, 
										String 			validTagForPrint, 
										String 			validTagForMail, 
										XX_ActionAgent 	actionAfterPrintOrMail, 
										String 			klemmbrettKennerBetreff, 
										String 			klemmbrettKennerMail,
										String          archivKenner) throws myException {
		this.massMailer = new E2_MassMailer_STD(klemmbrettKennerBetreff,klemmbrettKennerMail,archivKenner);
		this.add_oActionAgent(new ownActionAgent(title4popup, validTagForPrint,	validTagForMail, actionAfterPrintOrMail));
		return this;
	}
	
	
	


	public abstract V_JasperHASH 		get_VJasperHashMAP(ExecINFO execInfo) throws myException;


	
	
	private class ownActionAgent extends ACTIONAGENT_MAIL_AND_REPORT 	{

		public ownActionAgent(MyE2_String fensterTitel, String valid_tag_print,	String valid_tag_mail, XX_ActionAgent actionAfterPrintOrMail) 		{
			super(fensterTitel, valid_tag_print, valid_tag_mail, actionAfterPrintOrMail,false);
		}

		@Override
		public E2_MassMailer get_MassMailer() throws myException 	{
			return massMailer;
		}

		@Override
		public V_JasperHASH get_VJasperHashMAP(ExecINFO execInfo) throws myException 		{
			return BtMailAndReport_V2.this.get_VJasperHashMAP(execInfo) ;
		}
	}




	
		
}
