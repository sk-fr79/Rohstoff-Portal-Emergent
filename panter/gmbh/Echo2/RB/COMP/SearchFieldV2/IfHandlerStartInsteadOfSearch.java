/**
 * panter.gmbh.Echo2.RB.COMP.SearchFieldV2
 * @author martin
 * @date 20.09.2019
 * 
 */
package panter.gmbh.Echo2.RB.COMP.SearchFieldV2;

import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 20.09.2019
 *
 */
public interface IfHandlerStartInsteadOfSearch {
	public void executeInsteadOfSearch(RB_SearchFieldV2 searchfield, And andStatementCollectorHandlerStartInsteadOfSearch) throws myException;
	public boolean checkIfNeeded (RB_SearchFieldV2 searchfield) throws myException;
}
