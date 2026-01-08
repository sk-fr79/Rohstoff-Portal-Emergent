/**
 * rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde
 * @author manfred
 * @date 24.06.2021
 * 
 */
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.E2_Button;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.WK_RB_VALIDATORS;

/**
 * @author manfred
 * @date 24.06.2021
 *
 */
public class WK_RB_CHILD_ABZUG_GEB_MASK_btSave extends E2_Button {

	// Key for compmap
	public static RB_KF key = new RB_KF()._setHASHKEY("f4eaf049-11e5-4238-8d30-9d26d74150a1")._setREALNAME(S.ms("Speichern des Gebindes").CTrans());

	private RB_TransportHashMap _tpHashMap = null;
	
	/**
	 * @author manfred
	 * @date 24.06.2021
	 *
	 */
	public WK_RB_CHILD_ABZUG_GEB_MASK_btSave(RB_TransportHashMap  p_tpHashMap) {
		super();
		_tpHashMap = p_tpHashMap;
		
        this.setText(new MyString("Speichern").CTrans()) ; 
        this._setShapeStandardTextButton();
        this._image(E2_ResourceIcon.get_RI("save.png") , true);
		
		this.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				// Speichern des Vorgangs...
				((E2_Button)_tpHashMap.getButtonMaskSave()).doActionPassiv();
			}
		});
	}

}
