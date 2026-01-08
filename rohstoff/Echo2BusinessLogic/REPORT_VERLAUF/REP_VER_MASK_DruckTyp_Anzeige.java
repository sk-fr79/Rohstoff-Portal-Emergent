package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;

import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class REP_VER_MASK_DruckTyp_Anzeige extends RB_TextAnzeige {
	
	public REP_VER_MASK_DruckTyp_Anzeige() {
		super(300);	
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if(!(dataObject == null)) {
			String weg = dataObject.rec20().get_fs_dbVal(REPORT_LOG.report_weg);
			if(S.isFull(weg)) {
				ENUM_REPORT_WEG eweg = ENUM_REPORT_WEG.valueOf(weg);
				if(! (weg==null)) {
					this.setText(eweg.user_text());
				}
			}
		}
	}
}
