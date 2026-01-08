/**
 * panter.gmbh.Echo2.RB.VALID
 * @author martin
 * @date 15.02.2019
 * 
 */
package panter.gmbh.Echo2.RB.VALID;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 15.02.2019
 *
 */
public interface IF_Simple_Mask_Set_And_Valid {
	
	public MyE2_MessageVector executeSetterValidator(RB_ComponentMap rbMASK, VALID_TYPE ActionType) throws myException ;
	
	public default RB_Mask_Set_And_Valid get() {
		return new RB_Mask_Set_And_Valid() {
			
			@Override
			public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType,	ExecINFO oExecInfo) throws myException {
				return executeSetterValidator(rbMASK,ActionType);
			}
		};
	}
	
}
