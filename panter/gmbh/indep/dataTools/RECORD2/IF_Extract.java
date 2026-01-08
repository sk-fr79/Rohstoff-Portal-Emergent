/**
 * 
 */
package panter.gmbh.indep.dataTools.RECORD2;

import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public interface IF_Extract<T> {
	public T getValue(Rec20 r) throws myException; 
}
