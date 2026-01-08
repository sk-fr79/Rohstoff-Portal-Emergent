/**
 * 
 */
package panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.basics4project.ENUM_MESSAGE_PROVIDER;
import panter.gmbh.indep.PairS;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class MES_MASK_CMessageKey extends RB_selField {

	/**
	 * @param field
	 * @param emtpy_in_front
	 * @param e_width
	 * @param typ
	 * @throws myException
	 */
	public MES_MASK_CMessageKey() throws myException {
		super();
		
		this._addPair(new PairS("-",""));
		
		for (ENUM_MESSAGE_PROVIDER p: ENUM_MESSAGE_PROVIDER.values()) {
			this._addPair(new PairS(p.user_text(),p.db_val()));
		}
		
	}

}
