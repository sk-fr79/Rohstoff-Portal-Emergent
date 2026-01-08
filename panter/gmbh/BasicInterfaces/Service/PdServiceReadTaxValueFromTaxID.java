/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 15.10.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import java.math.BigDecimal;
import java.util.Date;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_TAX;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_TAX_EXT;

/**
 * @author martin
 * @date 15.10.2020
 *
 */
public class PdServiceReadTaxValueFromTaxID {


	public static class TaxEvaluationException extends myException {
		public TaxEvaluationException(myException ex) {
			super(ex);
		}
		public TaxEvaluationException(String errorMessage, Exception oFatherException) {
			super(errorMessage, oFatherException);
		}
		public TaxEvaluationException(String errorMessage) {
			super(errorMessage);
		}
	}
	
	
	/**
	 * @author martin
	 * @date 15.10.2020
	 *
	 */
	public PdServiceReadTaxValueFromTaxID() {
		super();
	}

	
	/**
	 * 
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param idTax
	 * @param leistungsDatum
	 * @return s zum datum gueltiger steuersatz als BigDecimal 
	 * @throws TaxEvaluationException
	 */
	public BigDecimal getTaxValue(Long idTax, Date leistungsDatum) throws TaxEvaluationException {
		BigDecimal ret;
		try {
			ret = null;
			RECORD_TAX_EXT tax = new RECORD_TAX_EXT(new RECORD_TAX(idTax));
			
			tax.getBDSteuerSatzKorrigiert(new MyDate(leistungsDatum));
			
		} catch (myException e) {
			e.printStackTrace();
			throw new TaxEvaluationException(e);
		}
		
		return ret;
	}
	
	
	
	
}
