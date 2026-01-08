/**
 * 
 */
package jasper;

import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class MyExceptionJasperValueNotFound extends myException {

	/**
	 * @param errorMessage
	 */
	public MyExceptionJasperValueNotFound(String keyCode) {
		super("Value for JASPER-REPORT-KeyCode was not found: "+keyCode);
	}
  
}
