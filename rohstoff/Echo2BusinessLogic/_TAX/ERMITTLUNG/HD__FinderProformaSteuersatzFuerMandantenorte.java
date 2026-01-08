package rohstoff.Echo2BusinessLogic._TAX.ERMITTLUNG;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_TAX;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.TAX_CONST;

public class HD__FinderProformaSteuersatzFuerMandantenorte {

	private RECORD_TAX  recTaxProforma = null;
	
	public RECORD_TAX get_RECORD_TAX_proforma() {
		return recTaxProforma;
	}

	public HD__FinderProformaSteuersatzFuerMandantenorte() throws myException {
		super();
		
		// dann wird die tax-ermittlung durchgefuehrt, als waere es ein normaler ort, es wird aber der proforma-steuersatz fuer 
		// lager gesetzt 
		RECLIST_TAX  rlTaxLager = new RECLIST_TAX("SELECT * FROM "+bibE2.cTO()+"."+_DB.TAX+" WHERE NVL("+_DB.TAX$LEERVERMERK+",'N')='Y' " +
													" AND NVL("+_DB.TAX$TAXTYP+",'-')="+bibALL.MakeSql(TAX_CONST.TAXTYP_NULL_LAGERSEITE));
		if (rlTaxLager.get_vKeyValues().size()>0) {
			this.recTaxProforma = rlTaxLager.get(0);
		}

	}

}
