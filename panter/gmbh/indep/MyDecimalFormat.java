/**
 * panter.gmbh.indep
 * @author martin
 * @date 09.07.2019
 * 
 */
package panter.gmbh.indep;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

/**
 * @author martin
 * @date 09.07.2019
 *
 */
public class MyDecimalFormat extends DecimalFormat {

	public MyDecimalFormat() {
		super();
		
	    DecimalFormatSymbols sym = new DecimalFormatSymbols();
	    sym.setGroupingSeparator('.');
	    sym.setDecimalSeparator(',');
		withPattern("#,##0.00");
	}
	
	
	public MyDecimalFormat withPattern(String pattern) {
		this.applyPattern(pattern);
		return this;
	}
	
	
	public BigDecimal getParsedBigDecimal(String wert) {

		BigDecimal bd = null;
		
		try {
			MyBigDecimal m_bd = new MyBigDecimal(wert);
			if (m_bd.isOK()) {
				bd = m_bd.get_bdWert();
			} else {
				bd = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			bd=null;
		}
		
		return bd;
		
	}
	
}
