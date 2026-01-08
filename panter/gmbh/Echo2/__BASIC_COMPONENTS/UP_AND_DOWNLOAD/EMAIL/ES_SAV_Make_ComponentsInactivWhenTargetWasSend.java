package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.indep.exceptions.myException;

/**
 * wird eine einzige zieladresse als versendet erkannt, dann werden alle felder disabled
 * @author martin
 *
 */
public class ES_SAV_Make_ComponentsInactivWhenTargetWasSend extends RB_Mask_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
		MyE2_MessageVector mv_rueck = new MyE2_MessageVector();
		
		if (ActionType==VALID_TYPE.USE_IN_MASK_LOAD_ACTION) {
			ES_mask_complex_EMAIL_SEND_TARGETS targets = (ES_mask_complex_EMAIL_SEND_TARGETS) rbMASK.getRbComponent(
									new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_DAUGHTER_TARGETS));
			if (targets.get_bEvenOneTargetWasSend()) {
				rbMASK.getPreSettingsContainer().rb_get(new RB_KF(EMAIL_SEND.id_email_send)).set_Enabled(false);
				rbMASK.getPreSettingsContainer().rb_get(new RB_KF(EMAIL_SEND.betreff)).set_Enabled(false);
				rbMASK.getPreSettingsContainer().rb_get(new RB_KF(EMAIL_SEND.sender_adress)).set_Enabled(false);
				rbMASK.getPreSettingsContainer().rb_get(new RB_KF(EMAIL_SEND.text)).set_Enabled(false);
				rbMASK.getPreSettingsContainer().rb_get(new RB_KF(EMAIL_SEND.send_type)).set_Enabled(false);
				
				rbMASK.getPreSettingsContainer().rb_get(new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_DAUGHTER_TARGETS)).set_Enabled(false);
				rbMASK.getPreSettingsContainer().rb_get(new RB_KF(EMAIL_SEND.id_email_send,ES_CONST.HASHKEY_MASK_LIST_ARCHIVMEDIEN)).set_Enabled(false);
				
				
				rbMASK.setSurfaceSettingsActive();
			}
			
			
		}
		return mv_rueck;
	}

}
