/**
 * rohstoff.businesslogic.bewegung2.mask.Components
 * @author martin
 * @date 14.02.2019
 * 
 */
package rohstoff.businesslogic.bewegung2.mask.Components;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.IF_RbComponentWithOwnKey;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskController;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 14.02.2019
 *
 */
public class B2_btPruefeFuhre extends E2_Button implements IF_RbComponentWithOwnKey{

	public B2_btPruefeFuhre() {
		super();
		
		this._setShapeBigHighLightText()._t(S.ms("Status-Check"))._fsa(3)._b()._lw();
		
		this._ttt(S.ms("Die Fuhre auf potentielle Fehler untersuchen und Inhalte abgleichen ...."));
		
		this._aaa(new Action());
		
		
	}
	
	
	private class Action extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_MaskController c = new RB_MaskController(B2_btPruefeFuhre.this);
			bibMSG.MV()._add(c.get_ComponentMapCollector().rb_SIMULATE_SAVE_VALIDATION_CYCLE());
		}
		
	}


	/* (non-Javadoc)
	 * @see panter.gmbh.Echo2.RB.BASICS.IF_RbComponentWithOwnKey#getFieldKey()
	 */
	@Override
	public RB_KF getFieldKey() {
		return new RB_KF()._setHASHKEY(this.getClass().getName()+"7d621cb8-7fae-40bc-bcb2-4c1a2f9a6062")._setREALNAME(this.getClass().getName());
	}
	
}
