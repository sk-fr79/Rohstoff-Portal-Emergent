package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.IF_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.STATUS_CHK_2;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.STATUS_CHK_3;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.STATUS_CHK_4;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_EMAIL_SEND_ext;


/**
 * check-klasse fuer email-buttons, die innerhalb der RG-Module verschickt werden
 * @author martin
 *
 */
public class CHK__CycleBeforeSendOriginalMail {

	private CHK_IF_BELEG  vorgang_to_check = null;

	public CHK__CycleBeforeSendOriginalMail(CHK_IF_BELEG vorgang2check) {
		super();
		this.vorgang_to_check = vorgang2check;
	}
	
	
	/**
	 * 
	 * @param mv_sammler
	 * @param collectOnlyCheckingErrors: wird eine einzelne eMail verschickt, dann false, es werden dann auch fehler aufgrund von umgebungstatbestaenden gemeldet,
	 *                                   wenn true, dann gilt: senden kann erfolgen, wenn   recEmailWithOriginal!=null und mv_sammler hat keine fehler
	 * @return
	 * @throws myException
	 */
	public RECORD_EMAIL_SEND_ext do_checkCycle(MyE2_MessageVector mv_sammler, boolean collectOnlyCheckingErrors) throws myException {

		RECORD_EMAIL_SEND_ext recEmailWithOriginal = null;
		
		MyE2_String info = this.vorgang_to_check.get_BelegInfo();
			
		CHK_2_OriginalBeleg_exists_in_closed_Document chk2 = new CHK_2_OriginalBeleg_exists_in_closed_Document(this.vorgang_to_check);
		
		IF_STATUS status2 = chk2.check_status(mv_sammler);
		if (status2 == STATUS_CHK_2.HAS_ONE_ORIGINAL_ATTACHMENT) {
			
			CHK_3_OriginalBeleg_Connected_to_EmailSend chk3 = new CHK_3_OriginalBeleg_Connected_to_EmailSend(chk2.get_recAM().get(0));
			
			IF_STATUS status3 = chk3.check_status(mv_sammler);
			
			if (status3 == STATUS_CHK_3.ORIGINAL_IS_ATTATCHED_TO_SINGLE_MAIL) {
				
				CHK_4_eMail_Status chk4 = new CHK_4_eMail_Status(chk3.get_RecordEmailSend());
				
				IF_STATUS status4 = chk4.check_status(mv_sammler);
				
				if (status4 == STATUS_CHK_4.MAIL_IS_CORRECT_SENT) {
					if (!collectOnlyCheckingErrors) {
						info.addString(new MyE2_String(" Die eMail mit dem Originalbeleg wurde bereits verschickt !"));
						mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(info));
					}
				} else if (status4 == STATUS_CHK_4.MAIL_IS_CORRECT_UNSENT) {
					
					recEmailWithOriginal = new RECORD_EMAIL_SEND_ext(chk3.get_RecordEmailSend());
					//wer bis hierher kommt hat gewonnen, alle anderen haben mindestens eine error-message
					
				}
			} else if (status3 == STATUS_CHK_3.ORIGINAL_IS_ATTATCHED_TO_NONE) {
				if (!collectOnlyCheckingErrors) {
					info.addString(new MyE2_String(" Original wurde nicht zum Versenden vorgesehen !"));
					mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(info));
				}
			}
			
		} else if (status2 == STATUS_CHK_2.HAS_NO_ORIGINAL_ATTACHMENT) {
			if (!collectOnlyCheckingErrors) {
				info.addString(new MyE2_String(" besitzt keine Originalanlage !"));
				mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(info));
			}
		}
				
		return recEmailWithOriginal;
	}
}
