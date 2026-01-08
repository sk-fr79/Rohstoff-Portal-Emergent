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
public class FieldFalseTypeException extends myException {

	/**
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param ex
	 */
	public FieldFalseTypeException(myException ex) {
		super(ex);
	}

	/**
	 * @author martin
	 * @date 15.10.2020
	 *
	 * @param errorMessage
	 */
	public FieldFalseTypeException(String errorMessage) {
		super(errorMessage);
	}

}
