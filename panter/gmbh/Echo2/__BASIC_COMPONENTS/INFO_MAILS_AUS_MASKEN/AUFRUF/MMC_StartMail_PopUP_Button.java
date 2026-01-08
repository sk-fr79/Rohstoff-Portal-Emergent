package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF;

import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MAIL_AUS_MASK;
import panter.gmbh.indep.exceptions.myException;

public class MMC_StartMail_PopUP_Button extends MyE2_Button {
	
	private RECORD_MAIL_AUS_MASK  			RecMailAusMask=null;
	
	 
	public MMC_StartMail_PopUP_Button(RECORD_MAIL_AUS_MASK  recMailAusMask, MMC_ERSETZUNGS_HASH oErsetzungsHash) throws myException {
		super(recMailAusMask.get_BUTTONBESCHRIFTUNG_cUF());
		
		this.RecMailAusMask = recMailAusMask;
		
		this.add_GlobalAUTHValidator_AUTO("INFOMAIL:"+recMailAusMask.get_BUTTONKENNER_cUF());
		this.add_oActionAgent(new MMC_MailAction(recMailAusMask, oErsetzungsHash));
	}


	public RECORD_MAIL_AUS_MASK get_RecMailAusMask() {
		return RecMailAusMask;
	}


	public void set_RecMailAusMask(RECORD_MAIL_AUS_MASK recMailAusMask) {
		RecMailAusMask = recMailAusMask;
	}


	

}
