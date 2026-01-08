package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.RB.COMP.RB_LabelFilledWithResult;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class RB_label_filled_with_result extends RB_LabelFilledWithResult {

	public RB_label_filled_with_result() throws myException {
		super("...");
		this.setFont(new E2_FontPlain(-2));
		this.setLineWrap(true);
	}

	@Override
	public String transferDbValueToVisibleText(String c_db_val) throws myException {
		if (S.isEmpty(c_db_val))  {
			return "...";
		}
		return c_db_val;		
	}

}
