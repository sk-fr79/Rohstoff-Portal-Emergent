package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;

import java.sql.Timestamp;

import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public class REP_VER_MASK_DruckDatumUndZeit_Anzeige extends RB_TextAnzeige {
	public REP_VER_MASK_DruckDatumUndZeit_Anzeige() throws myException {
		super(300);

	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
		if(! (dataObject == null)) {
			Timestamp ts = dataObject.rec20().get_raw_resultValue_timeStamp(REPORT_LOG.report_druck_am);
			this.setText(myDateHelper.FormatDateTimeKurz(ts));
		}
	}
	
	
}
