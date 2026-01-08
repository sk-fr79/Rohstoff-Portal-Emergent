package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.RB.COMP.RB_LabelFilledWithResult_Saveable;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class FZ_MaskShowAdresse extends RB_LabelFilledWithResult_Saveable {

	public FZ_MaskShowAdresse() throws myException {
		super();
	}

	@Override
	public String transferDbValueToVisibleText(String c_db_val) throws myException {
		String c_ret = "@@ERROR";
		
		if (S.isEmpty(c_db_val)) {
			c_ret="";
		} else {
		
			MyLong l = new MyLong(c_db_val);
			if (l.isOK()) {
				RECORD_ADRESSE_extend  ra = new RECORD_ADRESSE_extend(l.get_lValue());
				c_ret = ra.get__FullNameAndAdress_mit_id();
			}
		}
		return c_ret;
	}

}
