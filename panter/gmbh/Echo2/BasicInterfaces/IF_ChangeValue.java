/**
 * panter.gmbh.Echo2.BasicInterfaces
 * @author martin
 * @date 19.08.2019
 * 
 */
package panter.gmbh.Echo2.BasicInterfaces;

import panter.gmbh.indep.dataTools.RECORD2.Rec21;

/**
 * @author martin
 * @date 19.08.2019
 *
 */
public interface IF_ChangeValue<S, T> {

	public T change(S source, Rec21 recRow);
	
}
