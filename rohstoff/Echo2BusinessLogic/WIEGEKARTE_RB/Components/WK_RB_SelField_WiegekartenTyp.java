/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_MASK_RB.Components
 * @author manfred
 * @date 14.04.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_selField;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_ENUM_WKTYP;



/**
 * @author manfred
 * @date 14.04.2020
 *
 */
public class WK_RB_SelField_WiegekartenTyp extends RB_selField implements IF_HasChangeListeners<WK_RB_SelField_WiegekartenTyp>{

	/**
	 * @author manfred
	 * @throws myException 
	 * @date 17.04.2020
	 *
	 */
	public WK_RB_SelField_WiegekartenTyp() throws myException {
		super();
		this._fo_s_plus(3)._fo_bold();
		this._populate(WK_RB_ENUM_WKTYP.get_ddArray(false,false));
		this._populateShadow(WK_RB_ENUM_WKTYP.get_ddArray(false,true));
		this._aaa(new ActionOnChangeListeners());
	}

	
	
	public WK_RB_ENUM_WKTYP _getCurrentSelectedTyp() {
		WK_RB_ENUM_WKTYP typ  = WK_RB_ENUM_WKTYP.WKTYP_NONE;
	
		try {
			typ = WK_RB_ENUM_WKTYP.getTyp(this.getActualDbVal());
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
	public WK_RB_SelField_WiegekartenTyp _setCurrentSelectedTyp(WK_RB_ENUM_WKTYP typ) throws myException {
		rb_set_db_value_manual(typ.db_val());
		return this;
	}



	/**
	 *  ChangeListeners
	 */
	private VEK<IF_ExecuterOnComponentV2<WK_RB_SelField_WiegekartenTyp>>    changeListeners = new   VEK<>();

	@Override
	public WK_RB_SelField_WiegekartenTyp _clearChangeListener() {
		changeListeners._clear();
		return this;
	}


	@Override
	public WK_RB_SelField_WiegekartenTyp _addChangeListener(
			IF_ExecuterOnComponentV2<WK_RB_SelField_WiegekartenTyp> changeListener) {
		changeListeners._a(changeListener);
		return this;
	}
	
	
	private class ActionOnChangeListeners extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			for (IF_ExecuterOnComponentV2<WK_RB_SelField_WiegekartenTyp> executer: changeListeners) {
				mv._add(executer.execute(WK_RB_SelField_WiegekartenTyp.this));
			}
			bibMSG.MV()._add(mv);
		}
	}
	
	
	
	
	
	
}
