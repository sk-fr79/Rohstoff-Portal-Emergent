package panter.gmbh.Echo2.__BASIC_COMPONENTS.INFO_MAILS_AUS_MASKEN.AUFRUF;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.components.MyE2_PopUpMenue;
import panter.gmbh.indep.exceptions.myException;

public  class MMC_StartMail_PopUP extends MyE2_PopUpMenue {
	
	public MMC_StartMail_PopUP(MMC_OWN_RECLIST_MAIL_AUS_MASK  mmcRECORDS, MMC_ERSETZUNGS_HASH oErsetzungsHash) throws myException {
		
		super(E2_ResourceIcon.get_RI("emailwhite_popup.png"), E2_ResourceIcon.get_RI("emailwhite_popup__.png"),false,new Extent(200),
				new Extent(20+20*mmcRECORDS.get_vKeyValues().size()),-5,20);
		
		for (int i=0;i<mmcRECORDS.get_vKeyValues().size();i++) {
			this.addButton(new MMC_StartMail_PopUP_Button(mmcRECORDS.get(i), oErsetzungsHash), true);
		}
		
	}
	
	

}
