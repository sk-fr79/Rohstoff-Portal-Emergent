/**
 * panter.gmbh.BasicInterfaces
 * @author martin
 * @date 24.09.2019
 * 
 */
package panter.gmbh.BasicInterfaces;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 24.09.2019
 *
 */
public interface IF_ExecuterReturnsMV<T> {
	public MyE2_MessageVector execute(T o) throws myException;
}
