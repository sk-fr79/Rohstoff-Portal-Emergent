package panter.gmbh.Echo2.ListAndMask.List.calculation;

import java.math.BigDecimal;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.exceptions.myException;

public class CALC_Rule_AnzahlAuswahl extends CALC_Rule_ABSTRACT {

	
	
	public CALC_Rule_AnzahlAuswahl() {
		super();
		this.set_cINFOTEXT(new MyE2_String("Anzahl Zeilen"));
		this.set_iDECIMALNUMBERS(0);
	}

	@Override
	public BigDecimal get_bdVALUE_ERGEBNISS(Vector<String> vID_COL) throws myException {
		return new BigDecimal(vID_COL.size());
	}

}
