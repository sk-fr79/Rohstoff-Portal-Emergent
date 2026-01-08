package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class ES_SAV_Even_ONE_Target extends RB_Mask_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
		MyE2_MessageVector mv_rueck = new MyE2_MessageVector();
		if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION) {

			ES_mask_complex_EMAIL_SEND_TARGETS targets = (ES_mask_complex_EMAIL_SEND_TARGETS) rbMASK.getRbComponent(
					new RB_KF(_DB.EMAIL_SEND$ID_EMAIL_SEND,ES_CONST.HASHKEY_MASK_DAUGHTER_TARGETS));
	
			if (targets.get_NumberTargetAdresses()==0) {
				mv_rueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Bitte mindestens eine Zieladresse hinterlegen !")));
			}
		}
		return mv_rueck;
	}

}
