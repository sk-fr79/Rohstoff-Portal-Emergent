/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7._services;

import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.AH7.PROFIL.AH7P_MASK_DataObjectCollector;
import rohstoff.Echo2BusinessLogic.AH7.PROFIL.AH7P_MASK_MaskModulContainer;

/**
 * @author martin
 *
 */
public class _PdServiceShowProfileMask {
	
	public void show(String id_ah7_profile_uf) throws myException {
		AH7P_MASK_MaskModulContainer  moduleContainer = new AH7P_MASK_MaskModulContainer();
		moduleContainer.rb_FirstAndOnlyComponentMapCollector().rb_COMPLETE_FILL_CYCLE(new AH7P_MASK_DataObjectCollector(id_ah7_profile_uf,MASK_STATUS.VIEW));
		moduleContainer.CREATE_AND_SHOW_POPUPWINDOW(S.ms("Anzeigen des aktiven AH7-Profils ..."));
	}
	
}
