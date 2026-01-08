package rohstoff.Echo2BusinessLogic.FIBU.BELEG_VALIDIERER;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_FORMULAR;
import panter.gmbh.indep.exceptions.myException;


public class RECORD_FIBU_FORMULAR_val extends RECORD_FIBU_FORMULAR {

	public RECORD_FIBU_FORMULAR_val(RECORD_FIBU_FORMULAR recordOrig) {
		super(recordOrig);
	}
	
	public boolean is_allowd(BELEGTYP4VALID typ) throws myException {
		if (typ ==null) {
			throw new myException("Error validation FIBU-Type (4)");
		}
		
		boolean b_rueck = false;
		switch (typ) {
		case RECH_POSITIV: 
			return this.is_RECH_POSITIV_YES();
		
		case RECH_NEGATIV: 
			return this.is_RECH_NEGATIV_YES();
		
		case RECH_POSITIV_STORNO: 
			return this.is_RECH_POSITIV_STORNO_YES();

		case RECH_NEGATIV_STORNO: 
			return this.is_RECH_NEGATIV_STORNO_YES();

		case GUT_POSITIV: 
			return this.is_GUT_POSITIV_YES();
		
		case GUT_NEGATIV: 
			return this.is_GUT_NEGATIV_YES();
		
		case GUT_POSITIV_STORNO: 
			return this.is_GUT_POSITIV_STORNO_YES();

		case GUT_NEGATIV_STORNO: 
			return this.is_GUT_NEGATIV_STORNO_YES();
		
		case ZAHLUNGSAUSGANG: 
			return this.is_ZAHLUNGSAUSGANG_YES();
	
		case ZAHLUNGSEINGANG: 
			return this.is_ZAHLUNGSEINGANG_YES();
		
		case SCHECK: 
			return this.is_SCHECK_YES();
		}
		return b_rueck;
	}

}
