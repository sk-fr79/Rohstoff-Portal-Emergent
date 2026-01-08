package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message_OT;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MANDANTEN_VERWALTUNG.MANDANT_CONST.MAILPROFILE;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.NACHRICHT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_NACHRICHT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_SCANNER_GROUP;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT_KATEGORIE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.dataTools.TERM.SELECT.LeftOuterJoin;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.is_null;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.MyMailHelper;
import rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG.WF_MessageHelper;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

/**
 * Klasse zum prüfen, welche Messages müssen als Email geschickt werden.
 * Die Prüfung findet für alle Messages und User des Mandanten statt.
 * Deshalb muss eine Absicherung gegen doppelte Ausführung durch 
 * DB-seitiges Prüfung eingebaut werden.
 *   
 * @author manfred
 */
public class MESSAGE_Email_Handler {

	
	
	boolean m_send_message_emails = false;

	public MESSAGE_Email_Handler() {
		m_send_message_emails = bib_Settigs_Mandant.get_Nachricht_Allow_Send_Additional_Email();
	}
	
	
	/**
	 * ermittelt alle Nachrichten, die ein SEND_EMAIL="Y" haben und noch nicht verschickt sind EMAIL_SENT = null
	 * Mit den ermittelten Nachrichten-IDS wird {@link MESSAGE_Email_Handler#sendMails(Vector)} aufgerufen.
	 * 
	 */
	public void sendAllOpenMails(){
		try {
			
			SEL sel =  new SEL()
					   .ADDFIELD(NACHRICHT.id_nachricht)
					   .FROM ( NACHRICHT.T() )
					   .WHERE ( new vgl(NACHRICHT.send_email,"Y") )
					   .AND (new is_null(NACHRICHT.email_sent) )
					   ;
			// todo: wie kann man das abbilden??
			String s = sel.s() + " and trunc(AKTIV_AB,'DD') <= trunc(sysdate,'DD') " ;
			
			RECLIST_NACHRICHT rlNachricht = new RECLIST_NACHRICHT(s);
			
			
			// jetzt versuchen die Mails zu verschicken...
			sendMails(rlNachricht.get_vKeyValues());
			
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * verschickt alle Mails, die in der Liste der vIDNachricht vorhanden sind.
	 * Die einzelnen Nachrichten werden mit FOR UPDATE NOWAIT gesperrt. Falls die
	 * Methode auf einen gesperrte Row trifft, wird diese ignoriert und die Mail nicht verschickt.
	 * Falls der globale Schalte NACHRICHT_ALLOW_SEND_ADDITIONAL_EMAIL auf "N" steht, wird die Email nicht verschickt und
	 * das Versendedatum auf den 1.1.2000 gesetzt, damit die Mail als bearbeitet gilt und nicht wieder angefasst wird.
	 * @param vIDNachricht
	 */
	public void sendMails(Vector<String> vIDNachricht){
		boolean bRet = false;
		
		RECORD_NACHRICHT rec = null;
		// alle records einzeln lesen und versuchen zu sperren mit select..for update;
		for (String sID : vIDNachricht){
			String sSql = "SELECT * FROM JT_NACHRICHT WHERE ID_NACHRICHT = "+ sID + " FOR UPDATE NOWAIT"; 
			try {
			
				rec = new RECORD_NACHRICHT(sSql);

				if (rec.is_SEND_EMAIL_NO() || rec.get_EMAIL_SENT_cUF() != null ){
					// freigeben der row
					bibDB.Rollback();
					continue;
				}
				
				// verschicke die Mail
				sendMail(rec);
				
			} catch (myException e) {
				bibDB.Rollback();
				continue;
			}
		}
	}

	
	
	
	/**
	 * Verschickt die NACHRICHT als Email
	 * @param rec
	 */
	public void sendMail(RECORD_NACHRICHT rec){
		
		RECORD_NACHRICHT_KATEGORIE  recCat = null;
		
		String 						sEmailSender= "";
		String						sEmailReceiver = "";
		
		boolean						bOk = true;
		String 						sStatus = "";

		String 						sKategorie = "";

		MyMailHelper				oMail = null;
		RECORD_USER 				recUserReceiver = null;
		RECORD_USER 				recUserSender 	= null;
		
		Vector<FileWithSendName> 	vAttachments = new Vector<>();
		String						sAttachmentStatus = "";  // enthält Meldungen, falls der Anhang nicht vorhanden ist.
		
		// äusserer TC-Block zur sicheren Release des locks
		try {
			
			if (!m_send_message_emails){
				bOk = false;
				sStatus = "'email disabled'";
			}
				
			try {
				if (bOk){
					recUserReceiver = rec.get_UP_RECORD_USER_id_user();
					if (recUserReceiver != null && !bibALL.isEmpty(recUserReceiver.get_EMAIL_cUF()) ){
						sEmailReceiver = recUserReceiver.get_EMAIL_cUF();
					} else {
						bOk = false;
						sStatus = "'no receiver'";
					}
				}
			
				if (bOk){
					recUserSender 	= new RECORD_USER(rec.get_ID_USER_SENDER_cUF_NN("-1"));
					if (recUserSender != null && !bibALL.isEmpty(recUserSender.get_EMAIL_cUF()) ){
						sEmailSender = recUserSender.get_EMAIL_cUF();
					} else {
						// standard-Sende-Email
						sEmailSender = bibALL.get_RECORD_MANDANT().get_EmailAddressOfType(MAILPROFILE.SYSTEMNACHRICHT); 
					}
	
					// falls es keine Sender-Email gibt, abbrechen
					if (bibALL.isEmpty(sEmailSender)){
						bOk 	= false;
						sStatus = "'no sender'";
					}
				}
			} catch (myException ei1) {
				bOk = false;
				sStatus = "'unknown'";
			}
			
			
			if (bOk){
				try {
					if (!bibALL.isEmpty(rec.get_ID_NACHRICHT_KATEGORIE_cUF()) ){
						recCat = new RECORD_NACHRICHT_KATEGORIE(rec.get_ID_NACHRICHT_KATEGORIE_lValue(-1l));
						sKategorie = recCat.get_KATEGORIE_cUF();
					}
				} catch (myException e1) { /* keine Kategorie, kein grosses Problem */	}			
			}
			
			// Liste der Anhänge ermitteln:
			// Alle Archivmedien suchen, die an dieser Nachricht hängen 
			//
			RECLIST_ARCHIVMEDIEN_ext rlArchivmedienNachricht = new RECLIST_ARCHIVMEDIEN_ext(_DB.NACHRICHT, rec.get_ID_NACHRICHT_cUF(), null,null);
			for(RECORD_ARCHIVMEDIEN recArchiv :rlArchivmedienNachricht.values()){
				
				String sDateiname = recArchiv.get_DATEINAME_VALUE_FOR_SQLSTATEMENT();
				String sPfad      = recArchiv.get_PFAD_VALUE_FOR_SQLSTATEMENT();
				String sDateinameOrig = "";
				try {
					RECORD_ARCHIVMEDIEN_ext recExt = new RECORD_ARCHIVMEDIEN_ext(recArchiv);
					sDateinameOrig = recExt.get_DATEINAME_Orig();
					vAttachments.addElement(new FileWithSendName(recExt.get__cCompletePathAndFileName(), sDateinameOrig, JasperFileDef.findFileDef(sDateinameOrig)));
				} catch (Exception e) {
					// archivmedium vermutlich nicht vorhanden..
					sAttachmentStatus += "<Anhang-Datei " +  sDateinameOrig + " konnte nicht gefunden werden.>" + System.lineSeparator();
				}
			}

			
			
			//
			// Email erzeugen und versenden
			//
			if (bOk){
				try {
					// nachdem wir alle Daten gelesen haben, noch die Email erstellen...
					String sText = ""; 
					String sHeader = "";
					
					sHeader += "Rohstoffportal: " +  rec.get_TITEL_cUF_NN("-");
					sText  	+= (!bibALL.isEmpty(sKategorie) ? "Kategorie: " + sKategorie + "\n\n": "");
					sText   += rec.get_NACHRICHT_cUF_NN("./.");
					if (!bibALL.isEmpty(sAttachmentStatus)) {
						sText   += 	System.lineSeparator()+
									System.lineSeparator()+
									System.lineSeparator();
						sText   += sAttachmentStatus;
					}
					
					// Mailobjekt 
					oMail = new MyMailHelper(		sEmailSender,
							sEmailReceiver,
							sHeader,
							sText,
							null);
					
					oMail.addAttachementlist(vAttachments);
					
					bOk = oMail.doSendWith_REAL_Adress();
					if (!bOk){
						sStatus = "'error sending'";
					}
				} catch (myException e) {
					bOk = false;
					sStatus = "'error mailhelper'";
					e.printStackTrace();
				}
			}

			
			if (bOk)
			{
				sStatus = "to_char(sysdate,'YYYY-MM-DD HH24:MI:SS')";
			} 
			
			// den Record updaten
			set_SendDateAndCommit(rec, sStatus);
			
		} catch (myException eo) {
			eo.printStackTrace();
		} finally {
			// ganz sicher nochmal den Lock von der DB nehmen
			bibDB.Rollback();
		}
		
		
		

	}
	
	
	
	/**
	 * Als Datum kann man
	 * sysdate oder
	 * to_date('2000-01-01','YYYY-MM-DD') 
	 * eingeben.
	 * @param rec
	 * @param sqlValue
	 * @throws myException 
	 */
	private void set_SendDateAndCommit(RECORD_NACHRICHT rec, String sqlValue) throws myException {
			rec.get_hm_Field_Value_pairs_from_outside().put(rec.FIELD__EMAIL_SENT, sqlValue);
			rec.UPDATE(true);
	}
	
	
}
