package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_LabelFilledWithResult;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class WE_CO_label_show_main_adress extends RB_LabelFilledWithResult {

	public WE_CO_label_show_main_adress() throws myException {
		super("...");
		this.setFont(new E2_FontPlain(-2));
		this.setLineWrap(true);
	}

	@Override
	public String transferDbValueToVisibleText(String c_db_val) throws myException {
		if (S.isEmpty(c_db_val))  {
			return "...";
		}
		MyLong  l = new MyLong(c_db_val);
		
		if (l.get_bOK()) {
			RECORD_ADRESSE_extend  ra = new RECORD_ADRESSE_extend(l.get_cUF_LongString());
			return ra.get__Name1_and_Name2();
		}
		
		throw new myException(this,"DBValue is not correct: "+c_db_val);
		
	}

}
