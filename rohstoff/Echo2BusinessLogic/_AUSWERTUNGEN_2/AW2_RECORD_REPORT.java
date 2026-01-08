package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_M2N;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.indep.exceptions.myException;

public class AW2_RECORD_REPORT extends RECORD_REPORT {

	private RECORD_M2N rec_M2N = null;
	
	public AW2_RECORD_REPORT(String c_ID, RECORD_M2N p_rec_M2N) 	throws myException {
		super(c_ID);
		this.rec_M2N = p_rec_M2N;
	}

	public RECORD_M2N get_m2n() {
		return rec_M2N;
	}

}
