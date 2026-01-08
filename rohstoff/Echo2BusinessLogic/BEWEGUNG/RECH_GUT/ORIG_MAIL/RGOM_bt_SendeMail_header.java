package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.ORIG_MAIL;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_AUTH_VALIDATOR;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_MODULES;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK_RECORD_VKOPF_RG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CycleBeforeSendOriginalMail;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VKOPF_RG;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EMAIL_SEND_ext;

public class RGOM_bt_SendeMail_header extends MyE2_ButtonInLIST {

	private E2_NavigationList   naviList = null;
	
	public RGOM_bt_SendeMail_header(E2_NavigationList navi_List) {
		super(RGOM__CONST.ICON__ORIG_EMAIL_PREPARED);
		this.naviList = navi_List;
		this.setToolTipText(new MyE2_String("Alle Original-Belege auf dieser Listenseite versenden (bei Markierung nur die ausgewählten) ...").CTrans());
		
//		this.add_GlobalAUTHValidator_PROGRAMM_WIDE(ES_CONST.get_eMailSendValidationString4EmailButtons(_DB.VKOPF_RG.substring(3)));
		this.add_GlobalValidator(new VALID_ENUM_AUTH_VALIDATOR(VALID_ENUM_MODULES.RANGE_KEY.ATTACHMENT_VKOPF_RG , VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.SENDMAIL));
		
		this.add_oActionAgent(new ownActionAgent_send_mails_from_list());
		
	}
	
	private class ownActionAgent_send_mails_from_list extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {

			MyE2_MessageVector mv = new MyE2_MessageVector();
			
			Vector<String>  vIDs = new Vector<String>();
			Vector<String>  vSelects = RGOM_bt_SendeMail_header.this.naviList.get_vSelectedIDs_Unformated();
			if (vSelects.size()>0) {
				vIDs.addAll(vSelects);
			} else {
				vIDs.addAll(RGOM_bt_SendeMail_header.this.naviList.get_vActualID_Segment());
			}
			
			//jetzt durchsuchen, ob es unverschickte originale gibt
			Vector<cont> vZuSenden = new Vector<cont>();
			
			for (String id_rg: vIDs) {
				CHK_RECORD_VKOPF_RG recRG = new CHK_RECORD_VKOPF_RG(new RECORD_VKOPF_RG(id_rg));
				
				CHK__CycleBeforeSendOriginalMail checkCycle = new CHK__CycleBeforeSendOriginalMail(recRG);
				
				RECORD_EMAIL_SEND_ext recSend = checkCycle.do_checkCycle(mv, true);
				if (recSend!=null) {
					vZuSenden.add(new cont(recRG,recSend));
				}
			}
			
			
			if (mv.get_bIsOK()) {
				if (vZuSenden.size()>0) {
					for (cont sendBlock: vZuSenden) {
						mv.add_MESSAGE(sendBlock.RecEmail.Send_Email(
								new MyE2_String(S.t("Rechnungsoriginal ID: "),
												S.ut(sendBlock.RecRG.get_ID_VKOPF_RG_cF()+"  "), 
												S.ut(sendBlock.RecRG.get___KETTE(bibVECTOR.get_Vector(_DB.VKOPF_RG$NAME1,_DB.VKOPF_RG$NAME2))+" ")),
								sendBlock.RecEmail.get_MUST_BE_ATTACHMENT_LIST(mv)));
						
						if (mv.get_bHasAlarms()) {
							break;
						}
					}
				
				} else {
					mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Nichts zu versenden gefunden !")));
				}
			}
			RGOM_bt_SendeMail_header.this.naviList._REBUILD_ACTUAL_SITE("");
			bibMSG.add_MESSAGE(mv);
		}
		
	}
	
	private class cont {
		public RECORD_VKOPF_RG 			RecRG = null;
		public RECORD_EMAIL_SEND_ext 	RecEmail = null;
		public cont(RECORD_VKOPF_RG recRG, RECORD_EMAIL_SEND_ext recEmail)
		{
			super();
			RecRG = recRG;
			RecEmail = recEmail;
		}
		
		
	}
	
}
