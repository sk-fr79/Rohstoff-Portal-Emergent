package panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.IF_STATUS;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.UP_AND_DOWNLOAD.EMAIL.CHK_FRAMEWORK.CHK__CONST.STATUS_CHK_4;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_SEND_TARGETS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_ATTACH;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_SEND_TARGETS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MailAdressChecker;
import panter.gmbh.indep.myVectors.VectorSingle;

public class CHK_4_eMail_Status extends CHK_0_ABSTRACT {

	private  	RECORD_EMAIL_SEND  		record_eMail_Send = null;
	private  	RECORD_ARCHIVMEDIEN 	recordArchivmedien_original = null;
	
	/**
	 * 
	 * @param recSend
	 * @param record_Archivmedien ist das assoziierte orginalmedium (kann null sein) fuer allgemeine eMail-checks vor versand
	 * @throws myException 
	 */
	public CHK_4_eMail_Status(RECORD_EMAIL_SEND  recSend) throws myException {
		super();
		this.record_eMail_Send	=	recSend;
		if (recSend == null) {
			DEBUG.System_println("Das kann doch gar nicht sein !");
		}
	}

	@Override
	public IF_STATUS check_status(MyE2_MessageVector mv_sammler) throws myException {
		IF_STATUS status = null;
		
		//nachsehen, ob es ein orignal-medium gibt
		RECLIST_EMAIL_SEND_ATTACH  	rlAttach2 = this.record_eMail_Send.get_DOWN_RECORD_LIST_EMAIL_SEND_ATTACH_id_email_send();
		for (RECORD_EMAIL_SEND_ATTACH recAtt: rlAttach2) {
			if (recAtt.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().is_IST_ORIGINAL_YES()) {
				this.recordArchivmedien_original = recAtt.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien();
				//er nimmt das erste gefundene originalmedium (es darf sowieso nur eines geben) 
			}
		}
		
		
		MyE2_String ErrorMessageBase = new MyE2_String("Zur Email-ID: ");
		ErrorMessageBase.addString(new MyE2_String(this.record_eMail_Send.get_ID_EMAIL_SEND_cF_NN("<->"+" :"),false));
		
		if (this.recordArchivmedien_original!=null) {
			ErrorMessageBase = new MyE2_String("Zum Vorgang mit der ID: ");
			ErrorMessageBase.addString(new MyE2_String(this.recordArchivmedien_original.get_ID_TABLE_cUF_NN("<->")+" :",false));
		}
		
		
		boolean bMustBeSingleTarget = false;
		
		//pruefung, ob es nur die eine (und korrekte) originalanlage gibt
		VectorSingle dubletten_chk_orig = new VectorSingle();
		if (this.recordArchivmedien_original!=null) {
			bMustBeSingleTarget = true;                  //bei vorkommendem original MUSS die Zieladresse singulaer sein
		}
		
		//pruefung, ob alle anlage-dateien am selben table der gleichen id haengen
		VectorSingle dubletten_chk_sameTable = new VectorSingle();
		for (RECORD_EMAIL_SEND_ATTACH recAtt: rlAttach2) {
			if (recAtt.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().is_IST_ORIGINAL_YES()) {
				dubletten_chk_orig.add(recAtt.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().get_ID_ARCHIVMEDIEN_cUF());
			}
			dubletten_chk_sameTable.add(recAtt.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().get_TABLENAME_cUF_NN("")+"|"+
										recAtt.get_UP_RECORD_ARCHIVMEDIEN_id_archivmedien().get_ID_TABLE_cUF_NN(""));
		}

		if (dubletten_chk_orig.size()>1){
			status = STATUS_CHK_4.MAIL_CONTAINS_OTHER_ORIGINALS_ERR;
			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(S.ut(ErrorMessageBase.CTrans()),
																			S.t(" sind mehrere Originale angehängt!"))));
			return status;
		} 

		if (dubletten_chk_sameTable.size()!=1){
			status = STATUS_CHK_4.MAIL_CONTAINS_ATTACHMENTS_FROM_DIFF_RECORDS_ERR;
			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(	S.ut(ErrorMessageBase.CTrans()),
																			S.t(" sind Anlagen zu verschiedenen Originaldatensätzen angehängt!"))));
			return status;
		} 

		//jetzt die targets pruefen
		RECLIST_EMAIL_SEND_TARGETS  rlTargets = this.record_eMail_Send.get_DOWN_RECORD_LIST_EMAIL_SEND_TARGETS_id_email_send();

		
		if (rlTargets.get_vKeyValues().size()==0) {
			status = STATUS_CHK_4.MAIL_HAS_EMPTY_TARGET_LIST_ERR;
			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(	S.ut(ErrorMessageBase.CTrans()),
																			S.t(" exisitiert eine eMail-Sendung ohne Zieladressen!"))));
			return status;

		} else if (rlTargets.get_vKeyValues().size()>1 && bMustBeSingleTarget) {
			status = STATUS_CHK_4.MAIL_HAS_MORE_THAN_ONE_TARGETS_ERR;
			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(	S.ut(ErrorMessageBase.CTrans()),
																			S.t(" exisitiert eine Original-eMail-Sendung mit mehreren Zieladressen !"))));
			return status;
		} 

		boolean bOK = true;
		for (RECORD_EMAIL_SEND_TARGETS recTarget: rlTargets) {
			String cMailAdress = recTarget.get_TARGET_ADRESS_cUF_NN("");
			bOK = S.isFull(cMailAdress);
			if (bOK) {
				MailAdressChecker checkMail = new MailAdressChecker(cMailAdress);
				bOK = checkMail.isOK();
			}
			if (!bOK) {
				break;
			}
		}				
							
		if (!bOK) {
			status = STATUS_CHK_4.MAIL_HAS_FALSE_TARGET_ERR;
			mv_sammler.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(	S.ut(ErrorMessageBase.CTrans()),
																			S.t(" Die Versandliste enthält leere oder fehlerhafte Zieladresse !"))));
			return status;
		} else {
			boolean bEmailListIstKomplettVersandt = true;
			for (RECORD_EMAIL_SEND_TARGETS recTarget: rlTargets) {
				bEmailListIstKomplettVersandt = bEmailListIstKomplettVersandt&&recTarget.is_SEND_OK_YES();
			}
				
			//hier kann es nur noch geben: Versendet oder noch offen
			if (bEmailListIstKomplettVersandt) {
				status = STATUS_CHK_4.MAIL_IS_CORRECT_SENT;
				return status;
			} else {
				status = STATUS_CHK_4.MAIL_IS_CORRECT_UNSENT;
				return status;
			}
		}				
	}


	@Override
	public MyE2_String get_Description() {
		return new MyE2_String("Prüft die korrektheit einer eMail-Sendung !");
	}

}
