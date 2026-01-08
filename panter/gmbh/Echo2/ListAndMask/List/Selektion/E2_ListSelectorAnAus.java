/**
 * panter.gmbh.Echo2.ListAndMask.List.Selektion
 * @author martin
 * @date 06.11.2020
 * 
 */
package panter.gmbh.Echo2.ListAndMask.List.Selektion;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 06.11.2020
 *
 */
public class E2_ListSelectorAnAus extends E2_ListSelektorMultiselektionV2 {



	/**
	 * 
	 * @author martin
	 * @date 06.11.2020
	 *
	 * @param field
	 * @param beschriftung
	 * @param textAn
	 * @param textAus
	 * @param toolTipsAn
	 * @param toolTipsAus
	 * @param widthText
	 * @param widthAn
	 * @param widthAus
	 * @throws myException
	 */
	public E2_ListSelectorAnAus(IF_Field field,	MyE2_String beschriftung, MyE2_String textAn, MyE2_String textAus, MyE2_String toolTipsAn, MyE2_String toolTipsAus, Integer widthText, Integer widthAn, Integer widthAus) throws myException {
		
		super();
		
		Term trueTerm = 	new vgl_YN(field, true);
		Term falseTerm = 	new vgl_YN(field, false);
		
		this._addLabel(beschriftung, widthText)
			._addCheck(field, true, trueTerm.s(), textAn, toolTipsAn, widthAn)
			._addCheck(field, true, falseTerm.s(), textAus, toolTipsAus, widthAus)
			;
		
		this._setConditionWhenAllIsSelected("");		
	}




}
