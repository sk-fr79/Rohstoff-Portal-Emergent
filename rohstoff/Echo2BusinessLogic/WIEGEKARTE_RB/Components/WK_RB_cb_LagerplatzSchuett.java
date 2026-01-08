/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components
 * @author manfred
 * @date 19.05.2020
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.Components;

import panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2;
import panter.gmbh.BasicInterfaces.IF_HasChangeListeners;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

/**
 * @author manfred
 * @date 19.05.2020
 *
 */
public class WK_RB_cb_LagerplatzSchuett extends RB_cb implements IF_HasChangeListeners<WK_RB_cb_LagerplatzSchuett> {
	public static RB_KF key = new RB_KF()._setHASHKEY("045d7c4f-32a7-4c5f-8322-cc90a66479a4")._setREALNAME(S.ms("Lagerplatz Schuette").CTrans());
	
	
	private VEK<IF_ExecuterOnComponentV2<WK_RB_cb_LagerplatzSchuett>>    changeListeners = new   VEK<>();
	
	/**
	 * @author manfred
	 * @date 27.03.2019
	 *
	 */
	public WK_RB_cb_LagerplatzSchuett(String sText) {
		super();
		this.setText(sText);
		this.setSelected(false);
		this._aaa(new ActionOnChangeListeners());
	}
	
	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.RB_cb#rb_Datacontent_to_Component(panter.gmbh.Echo2.RB.DATA.RB_Dataobject)
	 */
	@Override
	public void rb_Datacontent_to_Component(RB_Dataobject dataObject) throws myException {
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.COMP.RB_cb#rb_readValue_4_dataobject()
	 */
	@Override
	public String rb_readValue_4_dataobject() throws myException {
		return "N";
	}
	
	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_clearChangeListener()
	 */
	@Override
	public WK_RB_cb_LagerplatzSchuett _clearChangeListener() {
		this.changeListeners._clear();
		return this;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.BasicInterfaces.IF_HasChangeListeners#_addChangeListener(panter.gmbh.BasicInterfaces.IF_ExecuterOnComponentV2)
	 */
	@Override
	public WK_RB_cb_LagerplatzSchuett _addChangeListener(IF_ExecuterOnComponentV2<WK_RB_cb_LagerplatzSchuett> changeListener) {
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
			for (IF_ExecuterOnComponentV2<WK_RB_cb_LagerplatzSchuett> executer: changeListeners) {
				mv._add(executer.execute(WK_RB_cb_LagerplatzSchuett.this));
			}
			bibMSG.MV()._add(mv);
		}
		
	}

}
