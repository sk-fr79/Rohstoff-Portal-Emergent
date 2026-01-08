/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 14.04.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_ENUM_DRUCKTYP;



/**
 * @author manfred
 * @date 14.04.2020
 *
 */
public class WK_RB_SelField_Drucktyp extends RB_selField {

	/**
	 * @author manfred
	 * @throws myException 
	 * @date 17.04.2020
	 *
	 */
	public WK_RB_SelField_Drucktyp() throws myException {
		super();
		this._fo_s_plus(1)._fo_bold()._width(250);
		this._populate(WK_RB_ENUM_DRUCKTYP.get_ddArray(false));
		this.setSelectedIndex(0);
	}

	
	
	public WK_RB_ENUM_DRUCKTYP _getCurrentSelectedTyp() {
		WK_RB_ENUM_DRUCKTYP typ  = null;
	
		try {
			typ = WK_RB_ENUM_DRUCKTYP.getTyp(this.getVCompleteVisibleDbVals().get(this.getSelectedIndex()));
		} catch (myException e) {
			//e.printStackTrace();
		}
		return typ;
	}
	

	/**
	 * Setzt den Typ der Wiegekarte
	 * @author manfred
	 * @date 21.09.2020
	 *
	 * @param typ
	 * @return
	 * @throws myException 
	*/
	public WK_RB_SelField_Drucktyp _setCurrentSelectedTyp(WK_RB_ENUM_DRUCKTYP typ) throws myException {
		rb_set_db_value_manual(typ.db_val());
		return this;
	}
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.RB_selField#rb_Datacontent_to_Component(panter.gmbh.Echo2.RB.DATA.RB_Dataobject)
	 */
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	
	}
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.RB_selField#rb_readValue_4_dataobject()
	 */
	@Override
	public String rb_readValue_4_dataobject() {
		return null;
	}
	
}
