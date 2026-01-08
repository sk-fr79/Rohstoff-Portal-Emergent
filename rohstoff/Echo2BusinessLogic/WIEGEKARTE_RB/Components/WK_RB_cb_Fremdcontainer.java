/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components
 * @author manfred
 * @date 06.05.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author manfred
 * @date 06.05.2020
 *
 */
public class WK_RB_cb_Fremdcontainer extends RB_cb implements IF_HasChangeListeners<WK_RB_cb_Fremdcontainer> {

	/**
	 * @author manfred
	 * @date 06.05.2020
	 *
	 */
	public WK_RB_cb_Fremdcontainer(String sText) {
		super();
		this.setText(sText);
		this._aaa(new ActionOnChangeListeners());
//		this.setSelected(true);
	}

	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	}

	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return "N";
	}

	
	
	private VEK<IF_ExecuterOnComponentV2<WK_RB_cb_Fremdcontainer>>    changeListeners = new   VEK<>();
	
	@Override
	public WK_RB_cb_Fremdcontainer _clearChangeListener() {
		this.changeListeners._clear();
		return this;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_addChangeListener(panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2)
	 */
	@Override
	public WK_RB_cb_Fremdcontainer _addChangeListener(IF_ExecuterOnComponentV2<WK_RB_cb_Fremdcontainer> changeListener) {
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
			for (IF_ExecuterOnComponentV2<WK_RB_cb_Fremdcontainer> executer: changeListeners) {
				mv._add(executer.execute(WK_RB_cb_Fremdcontainer.this));
			}
			bibMSG.MV()._add(mv);
		}
		
	}
	
}
