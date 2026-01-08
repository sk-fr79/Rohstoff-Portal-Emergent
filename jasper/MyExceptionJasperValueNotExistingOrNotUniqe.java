/**
 * 
 */
package jasper;

import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class MyExceptionJasperValueNotExistingOrNotUniqe extends myException {

	/**
	 * @param errorMessage
	 */
	public MyExceptionJasperValueNotExistingOrNotUniqe(String key, String laendercode) {
		super("Value for JASPER-REPORT-value was not found or is multiple : Key:"+key+" / Countrycode: "+laendercode);
	}
  
}
