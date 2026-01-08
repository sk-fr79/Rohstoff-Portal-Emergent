package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.indep.exceptions.myException;

public class ES_SAV_BeiOriginalNur_EINE_Zieladresse extends RB_Mask_Set_And_Valid {

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid( 	RB_ComponentMap 	mask, 
																VALID_TYPE 	ActionType, 
																ExecINFO 	oExecInfo) throws myException {
		
		MyE2_MessageVector oMVRueck = new MyE2_MessageVector();
		
		//nur beim laden in eine Edit-maske
		if (ActionType==RB__CONST.VALID_TYPE.USE_IN_MASK_VALID_ACTION) {
			
			//jetzt pruefen, ob die eMail eine originaldatei betrifft und ob bereits verschickt
			if ( ((ES_mask_complex_EMAIL_SEND_TARGETS)mask.get(ES_CONST.HASHKEY_MASK_DAUGHTER_TARGETS)).get_NumberTargetAdresses()>1 &&
			     ((ES_mask_complex_LISTE_ANLAGEN)mask.get(ES_CONST.HASHKEY_MASK_LIST_ARCHIVMEDIEN)).get_bIsOriginalSelected4Save() ) {
				oMVRueck.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Ist ein Originalbeleg bei der Sendung, dann darf nur eine Zieladresse verwendet werden !")));
			}

		}
		return oMVRueck;
	}
	
}


	

