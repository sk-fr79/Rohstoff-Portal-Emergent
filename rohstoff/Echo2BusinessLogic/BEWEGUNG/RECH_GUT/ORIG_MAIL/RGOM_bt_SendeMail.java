package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL;

import java.util.ArrayList;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_AUTH_VALIDATOR;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK_RECORD_VKOPF_RG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CycleBeforeSendOriginalMail;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EMAIL_SEND_ext;

public class RGOM_bt_SendeMail extends MyE2_ButtonInLIST {

	private RGOM_K_LIST_IconToShowSendStatusOrigMail containingDBComponent = null;
	
	public RGOM_bt_SendeMail(RGOM_K_LIST_IconToShowSendStatusOrigMail containing_DBComponent) {
		super(RGOM__CONST.ICON__ORIG_EMAIL_PREPARED);
		this.containingDBComponent = containing_DBComponent;
		this.setToolTipText(new MyE2_String("Der Beleg ist zum eMail-Versand vorbereitet (noch nicht verschickt).").CTrans());
		
		//this.add_GlobalAUTHValidator_PROGRAMM_WIDE(ES_CONST.get_eMailSendValidationString4EmailButtons(_DB.VKOPF_RG.substring(3)));
		
		
		this.add_oActionAgent(new ownActionAgent_send_single_mail_from_list());
		
		this.add_GlobalValidator(new VALID_ENUM_AUTH_VALIDATOR(VALID_ENUM_MODULES.RANGE_KEY.ATTACHMENT_VKOPF_RG, VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.SENDMAIL));
		
	}
	
	private class ownActionAgent_send_single_mail_from_list extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			RECORD_VKOPF_RG recRG = new RECORD_VKOPF_RG(RGOM_bt_SendeMail.this.containingDBComponent.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID());
			
			
			CHK__CycleBeforeSendOriginalMail checkCycle = new CHK__CycleBeforeSendOriginalMail(new CHK_RECORD_VKOPF_RG(recRG));
			
			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			RECORD_EMAIL_SEND_ext recSend = checkCycle.do_checkCycle(mv,false);
			
			if (mv.get_bIsOK()) {
				if (recSend != null) {
					mv.add_MESSAGE(recSend.Send_Email(
							new MyE2_String(S.t("Rechnungsoriginal ID: "),
											S.ut(recRG.get_ID_VKOPF_RG_cF()+"  "), 
											S.ut(recRG.get___KETTE(bibVECTOR.get_Vector(_DB.VKOPF_RG$NAME1,_DB.VKOPF_RG$NAME2))+" ")),
							recSend.get_MUST_BE_ATTACHMENT_LIST(mv)));
				} else {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("RGOM_bt_SendeMail: Unknown Error!")));
				}
			}

			RGOM_bt_SendeMail.this.containingDBComponent.EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
			
			bibMSG.add_MESSAGE(mv);
		}
		
	}
	
	
}
