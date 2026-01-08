package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.ENUM_BASED.VALID_ENUM_BUTTONS;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.__VALIDATOR_4_ATTACHMENT_POPUP;
import panter.gmbh.Echo2.components.MyE2_ButtonInLIST;
import panter.gmbh.Echo2.components.DB.MyE2_DB_PlaceHolder;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EMAIL_SEND_ext;

public class ES_LIST_BT_SendEMail extends MyE2_DB_PlaceHolder {

	private String baseTableName = null;
	
	public ES_LIST_BT_SendEMail(SQLField osqlField, String cBaseTableName) throws myException {
		super(osqlField);
		this.baseTableName = cBaseTableName;
	}

	@Override
	public void set_cActual_Formated_DBContent_To_Mask(String cText, String cMASK_STATUS, SQLResultMAP oResultMAP) throws myException {
		this.removeAll();
		this.setSize(1);
		
		//pruefen, ob bereits verschickt, wenn ja, dann gruener button sonst weiﬂ
		RECORD_EMAIL_SEND_ext recRow = new RECORD_EMAIL_SEND_ext((RECORD_EMAIL_SEND)this.EXT().get_oComponentMAP().get_Record4MainTable());
		boolean eMailIsDone = false;
		if (recRow.get_SendStatus()== ES_CONST.SEND_STATUS.SEND_ALL) {
			eMailIsDone = true;
		}
		
		MyE2_ButtonInLIST bt_send = new MyE2_ButtonInLIST(E2_ResourceIcon.get_RI(eMailIsDone?"email_done.png":"email.png"),true);
		bt_send.add_oActionAgent(new ownActionSend());
		
		this.add(bt_send, E2_INSETS.I(1,1,1,1));
		//bt_send.add_GlobalAUTHValidator_PROGRAMM_WIDE(ES_CONST.get_eMailSendValidationString4EmailButtons(this.baseTableName));
		bt_send.add_GlobalValidator(new __VALIDATOR_4_ATTACHMENT_POPUP(VALID_ENUM_BUTTONS.ATTACHMENT_BUTTONS.SENDMAIL));
			
	}

	private class ownActionSend extends XX_ActionAgent {

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			
			RECORD_EMAIL_SEND_ext  recSend= new RECORD_EMAIL_SEND_ext(
					new RECORD_EMAIL_SEND(
					ES_LIST_BT_SendEMail.this.EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID()
					));
			MyE2_MessageVector mv = new MyE2_MessageVector();
			recSend.check_and_send_email_if_possible(mv, recSend.get_MUST_BE_ATTACHMENT_LIST(mv));
			
			bibMSG.add_MESSAGE(mv);
			
			ES_LIST_BT_SendEMail.this.EXT().get_oComponentMAP().get_oNavigationList_This_Belongs_to()._REBUILD_ACTUAL_SITE("");
			
		}
		
	}
	
	public Object get_Copy(Object objHelp) throws myExceptionCopy {
		try {
			return new ES_LIST_BT_SendEMail(this.EXT_DB().get_oSQLField(),this.baseTableName);
		} catch (myException e) {
			e.printStackTrace();
			throw new myExceptionCopy(e.ErrorMessage);
		}
	}

}
