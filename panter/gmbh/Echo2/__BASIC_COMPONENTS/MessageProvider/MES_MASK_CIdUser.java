/**
 * 
 */
package panter.gmbh.Echo2.__BASIC_COMPONENTS.MessageProvider;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_SelField_USER;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public class MES_MASK_CIdUser extends RB_SelField_USER {

	/**
	 * @param field
	 * @param emtpy_in_front
	 * @param e_width
	 * @param typ
	 * @throws myException
	 */
	public MES_MASK_CIdUser() throws myException {
		super(true,new Extent(200),ENUM_USER_TYP.BUERO, ENUM_USER_TYP.AKTIV);
	}

}
