/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components
 * @author manfred
 * @date 19.05.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author manfred
 * @date 26.05.2020
 *
 */
public class WK_RB_cb_SorteHand extends RB_cb implements IF_HasChangeListeners<WK_RB_cb_SorteHand> {
		
	private VEK<IF_ExecuterOnComponentV2<WK_RB_cb_SorteHand>>    changeListeners = new   VEK<>();
	
	public WK_RB_cb_SorteHand() {
		super();
	}

	public WK_RB_cb_SorteHand(MyE2_String text) {
		this(text.CTrans());
	}
	
	public WK_RB_cb_SorteHand(String sText) {
		super();
		this.setText(sText);
		this.setSelected(false);
		this._aaa(new ActionOnChangeListeners());
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_clearChangeListener()
	 */
	@Override
	public WK_RB_cb_SorteHand _clearChangeListener() {
		this.changeListeners._clear();
		return this;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_addChangeListener(panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2)
	 */
	@Override
	public WK_RB_cb_SorteHand _addChangeListener(IF_ExecuterOnComponentV2<WK_RB_cb_SorteHand> changeListener) {
		this.changeListeners._a(changeListener);
		return this;
	}
	
	
	/**
	 * Weiterleitung der Actions
	 * @author manfred
	 * @date 19.05.2020
	 *
	 */
	private class ActionOnChangeListeners extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			MyE2_MessageVector mv = new MyE2_MessageVector();
			for (IF_ExecuterOnComponentV2<WK_RB_cb_SorteHand> executer: changeListeners) {
				mv._add(executer.execute(WK_RB_cb_SorteHand.this));
			}
			bibMSG.MV()._add(mv);
		}
		
	}

}
