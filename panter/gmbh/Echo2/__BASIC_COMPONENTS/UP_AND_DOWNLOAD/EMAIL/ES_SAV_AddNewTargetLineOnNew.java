package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class ES_SAV_AddNewTargetLineOnNew extends RB_Mask_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
		MyE2_MessageVector mv_rueck = new MyE2_MessageVector();
		
		if (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
			
			if (rbMASK.getRbDataObjectActual().rb_MASK_STATUS().isStatusNew()) {
			
				ES_mask_complex_EMAIL_SEND_TARGETS targets = (ES_mask_complex_EMAIL_SEND_TARGETS) rbMASK.getRbComponent(
										new RB_KF(_DB.EMAIL_SEND$ID_EMAIL_SEND,ES_CONST.HASHKEY_MASK_DAUGHTER_TARGETS));

				targets.get_oNavigationList().add_Row_MAP_FOR_NEW_INPUT(true,false, true);
				targets.get_oNavigationList().FILL_GRID_From_InternalComponentMAPs(true, true);

				
			}
		}
		return mv_rueck;
	}

}
