package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.IF_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.STATUS_CHK_3;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class CHK_3_OriginalBeleg_Connected_to_EmailSend extends CHK_0_ABSTRACT {

	private  RECORD_ARCHIVMEDIEN  	        recArchivmedien = null;
	private  RECORD_EMAIL_SEND 				recEmailSend = null;
	
	
	public CHK_3_OriginalBeleg_Connected_to_EmailSend(RECORD_ARCHIVMEDIEN  recordArchivmedien) throws myException {
		super();
		this.recArchivmedien = recordArchivmedien;
		if (this.recArchivmedien.is_IST_ORIGINAL_NO()) {
			throw new myException(this, "Check is senseless on not original-Files !");
		}
	}

	@Override
	public IF_STATUS check_status(MyE2_MessageVector mv_sammler) throws myException {
		IF_STATUS status = null;
		

		RECLIST_EMAIL_SEND_ATTACH  	rlAttach = recArchivmedien.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_archivmedien();
			
		if (rlAttach.get_vKeyValues().size()==0) {
			status = STATUS_CHK_3.ORIGINAL_IS_ATTATCHED_TO_NONE;
			return status;
		} else if (rlAttach.get_vKeyValues().size()==1){	
			status = STATUS_CHK_3.ORIGINAL_IS_ATTATCHED_TO_SINGLE_MAIL;
			this.recEmailSend = rlAttach.get(0).get_UP_RECORD_EMAIL_SEND_id_email_send();
			return status;
		} else {
			status = STATUS_CHK_3.ORIGINAL_IS_ATTATCHED_TO_MULTIPLE_MAIL_ERR;
			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(	S.t("Zur Vorgangs-ID:"),
																			S.ut(recArchivmedien.get_ID_TABLE_cF_NN("<->")),
																			S.t(" exisitiert eine Original-Archiv-Datei, diese ist aber mehreren eMail-Versendungen zugewiesen!"))));
			return status;
		}
	}

	@Override
	public MyE2_String get_Description() {
		return new MyE2_String("Prüft, ob ein vorhandener Originalbeleg zu eine eindeutigen eMail-Sendung zugeordnet ist");
	}

	public RECORD_EMAIL_SEND get_RecordEmailSend() {
		return this.recEmailSend;
	}

}
