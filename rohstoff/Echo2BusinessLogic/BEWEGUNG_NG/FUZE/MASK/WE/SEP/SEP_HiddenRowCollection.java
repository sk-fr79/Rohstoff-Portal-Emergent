package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE.SEP;

import java.math.BigDecimal;
import java.util.ArrayList;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.exceptions.myException;


public class SEP_HiddenRowCollection extends ArrayList<SEP_HiddenRow> {
	

	private boolean status_calculation = false;
	
	
	public SEP_HiddenRowCollection() {
		super();
	}


	/**
	 * validierung der liste, komplett leere zeilen sind erlaubt
	 * @return
	 * @throws myException
	 */
	public boolean is_valid() {
		boolean b_rueck = true;
		for (SEP_HiddenRow r: this) {
			if (!r.is_empty()) {
				if (!r.is_valid()) {
					b_rueck=false;
					break;
				}
			}
		}
		return b_rueck;
	}
	
	
	public BigDecimal calculate_liste(MyE2_MessageVector  mv) {
		BigDecimal bd_sum = new BigDecimal(0);
		this.status_calculation = true;

		if (this.is_valid()) {
			for (SEP_HiddenRow r: this) {
				if (!r.is_empty()) {
					MyBigDecimal bd_row = new MyBigDecimal(r.get_tf_menge().getText());
					bd_sum = bd_sum.add(bd_row.get_bdWert());
				}
			}
		} else {
			this.status_calculation = false;
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Es wurde nicht alles korrekt ausgefüllt!")));
		}
		
		return bd_sum;
	}

	
	public boolean is_last_calculation_ok() {
		return status_calculation;
	}
	
	
	/**
	 * liefert komplett ausgefuellte lines
	 * @return
	 * @throws myException
	 */
	public SEP_HiddenRowCollection get_full_lines() throws myException {
		SEP_HiddenRowCollection ret = new SEP_HiddenRowCollection();
		if (this.is_valid()) {
			for (SEP_HiddenRow r: this) {
				if (!r.is_empty()) {
					ret.add(r);
				}
			}
		} else {
			throw new myException(this,"List is not valid !!");
		}
		
		return ret;
	}
	
	
}
