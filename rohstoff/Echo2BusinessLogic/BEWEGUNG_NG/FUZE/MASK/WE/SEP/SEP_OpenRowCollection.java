package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.SEP;

import java.math.BigDecimal;
import java.util.ArrayList;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.exceptions.myException;


public class SEP_OpenRowCollection extends ArrayList<SEP_OpenRow> {
	

	private boolean status_calculation = false;
	
	
	public SEP_OpenRowCollection() {
		super();
	}

	public BigDecimal calculate_liste(MyE2_MessageVector  mv) throws myException {
		BigDecimal bd_sum = new BigDecimal(0);
		
		this.status_calculation = true;
		
		for (SEP_OpenRow r: this) {
			if (!r.is_empty()) {
				if (r.is_valid()) {
					MyBigDecimal bd_row = new MyBigDecimal(r.get_tf_menge().getText());
					if (bd_row.get_bOK()) { 
						bd_sum = bd_sum.add(bd_row.get_bdWert());
					} else {
						this.status_calculation = false;
						bd_sum = null;
						mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte alles korrekt ausfüllen !")));
						break;
					}
				} else {
					this.status_calculation = false;
					bd_sum = null;
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte alles korrekt ausfüllen !")));
					break;
				}
			}
		}

		return bd_sum;
	}

	
	public boolean is_last_calculation_ok() {
		return status_calculation;
	}
	
	
	
}
