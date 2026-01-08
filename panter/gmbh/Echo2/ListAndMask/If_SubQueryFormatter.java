/**
 * panter.gmbh.Echo2.ListAndMask
 * @author martin
 * @date 11.02.2019
 * 
 */
package panter.gmbh.Echo2.ListAndMask;

import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 11.02.2019
 *  functional interface fuer lambdas
 */
public interface If_SubQueryFormatter {

	public void doFormating(E2_ComponentMAP oMAP,SQLResultMAP oUsedResultMAP);
	
	
	public default XX_ComponentMAP_SubqueryAGENT genSubQueryAgent() {
		return new XX_ComponentMAP_SubqueryAGENT() {

			public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP) 	throws myException {};      //hier nicht benutzt
			
			public void fillComponents(E2_ComponentMAP oMAP,SQLResultMAP oUsedResultMAP) throws myException {
				doFormating(oMAP, oUsedResultMAP);
			}
		};
	}
	
	
}
