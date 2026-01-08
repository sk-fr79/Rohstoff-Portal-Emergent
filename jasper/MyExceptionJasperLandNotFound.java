/**
 * 
 */
package jasper;

import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class MyExceptionJasperLandNotFound extends myException {

	/**
	 * @param errorMessage
	 */
	public MyExceptionJasperLandNotFound(String laendercode) {
		super("Value for JASPER-REPORT-Laendercode was not found: "+laendercode);
	}
  
}
