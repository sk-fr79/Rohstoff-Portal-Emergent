/**
 * panter.gmbh.indep.dataTools.RECORD2
 * @author martin
 * @date 15.10.2020
 * 
 */
package panter.gmbh.indep.dataTools.RECORD2;

import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 15.10.2020
 *
 */
public class FieldNotInRecException extends myException {

	/**
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param ex
	 */
	public FieldNotInRecException(myException ex) {
		super(ex);
	}

	/**
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param errorMessage
	 */
	public FieldNotInRecException(String errorMessage) {
		super(errorMessage);
	}

}
