package rohstoff.Echo2BusinessLogic.MAIL_INBOX;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Vector;

import javax.mail.MessagingException;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_INBOX;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_EMAIL_INBOX_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_INBOX;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EMAIL_INBOX_DEF;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.archive.Archiver;
import panter.gmbh.indep.archive.Archiver.ENUM_ARCHIV_AUFTEILUNG;
import panter.gmbh.indep.archive.Archiver_CONST;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.mail.IMAP.IMAP_Mail_Handler;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

/**
 * Klasse die die definierten Mail-Inboxen durchsucht und neu gefundene Mails abholt und in das System überträgt.
 * Nutzt die Klasse 
 * 			IMAP_Mail_Handler
 * 
 * 
 * @author manfred
 * @date   25.02.2013
 */
public class MAIL_INBOX_Handler {

	
	private HashSet<String> hsMedienTypen = new HashSet<String>();
	private long            nCount = 0;
	
	/**
	 * Standard-Konstruktur
	 * @author manfred
	 * @date   28.02.2013
	 */
	public MAIL_INBOX_Handler() {

		// die Medientypen, die hochgeladen werden dürfen cachen...
		initMedienTypen();
	}
	
	
	
	/**
	 * Importieren aller neuen mails, die in der Inbox vorhanden sind und 
	 * noch nicht in das System importiert wurden
	 * 
	 * @author manfred
	 * @throws myException 
	 * @date   25.02.2013
	 */
	public void importMailsFromInboxes() throws myException{
		
		// die zu lesenden inboxen
		RECLIST_EMAIL_INBOX_DEF inboxes = getInboxes();
		
		if (inboxes != null){
			for (RECORD_EMAIL_INBOX_DEF inbox: inboxes.values()){
				nCount = 0;
				
				importMails(inbox,true);
				
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Vom Server " + inbox.get_MAIL_HOST_cUF() + " wurden " + Long.toString(nCount) + " neue eMails heruntergeladen."));
			}
		}
	}
	
	
	
	/**
	 * Importiert alle neuen mails der Inbox
	 * @author manfred
	 * @date   25.02.2013
	 * @param inbox
	 * @param bCheckAll
	 * @throws myException 
	 */
	private void importMails(RECORD_EMAIL_INBOX_DEF inbox, boolean bCheckAll) throws myException {
		String account = inbox.get_MAIL_ACCOUNT_cUF();
		String pwd     = inbox.get_MAIL_ACCOUNT_PW_cUF();
		String host    = inbox.get_MAIL_HOST_cUF();
		String protocol= inbox.get_MAIL_PROTOCOL_cUF();
		String folder  = inbox.get_MAIL_FOLDER_cUF();
		String port    = inbox.get_MAIL_PORT_cUF();
		 
		IMAP_Mail_Handler oIMAP = new IMAP_Mail_Handler(account,pwd,host,protocol,folder,port);
		Vector<String> vSql = new Vector<String>();
		HashSet<Long>  hsUIDs = null;
		
		try {
			oIMAP.connect();
			
			// ermitteln, welches die letzte UID des Postfachs/Folders importiert wurde
			Long lastUID = getLastInboxUID(inbox);
			
			if (lastUID == null || lastUID < 1 || bCheckAll){
				lastUID = 0L;
				hsUIDs = getInboxUIDs(inbox);
			} 
			
			
			// jetzt den Folder oeffnen mit der nächsten UID nach der aktuell größten 
			if (oIMAP.OpenFolder(lastUID + 1)){
				
				while (oIMAP.fetchNextMessge()){
					// prüfen, ob die Meldung schon einmal gelesen wurde...
					// Sonderfall: es wird, auch wenn die startuid > als die letzte UID ist immer die letzte Mail nochmal übermittelt:
					if (bCheckAll){
						if(hsUIDs.contains(oIMAP.getMailUID()) ){
							continue;
						}
					} else 	if (lastUID.equals(oIMAP.getMailUID() ) ) {
						// die UID wurde schon mal gelesen
						continue;
					}
					
					vSql.clear();
					
					// den Betreff merken
					String sSubject = oIMAP.getMailSubject();
					
					// Eintrag in die Tabelle für die EMAIL-Inbox vorbereiten
					String sSql = getSQLForInsertEmailEntry(oIMAP, host, account, folder);
					vSql.add(sSql);
					
					// einfügen der Email 
					bibDB.ExecMultiSQLVector(vSql, false);
					
					
					// ermitteln der neuen ID_EMAIL_INBOX
					sSql = "Select SEQ_EMAIL_INBOX.currval FROM " + bibE2.cTO() + ".JT_EMAIL_INBOX"; 
					String sID = bibDB.EinzelAbfrage(sSql);
					
					
					if (bibDB.Commit() ){
						// die Email wurde in die Tabelle geschrieben und die aktuelle ID des Eintrags wurde ermittelt.
						// jetzt können die Anhänge geschrieben werden
						
						String sPathAndFilename = "";
						String sFilename = "";
						String sEndung = "";
						
						Integer  nCount = 1;
						Archiver oArchiver = null;;
						
						// Ablegen der Mail selbst als Datei..
						Date dtReceived = oIMAP.getMailReceiveDate();
						
						oArchiver = new Archiver("INBOX",dtReceived,ENUM_ARCHIV_AUFTEILUNG.YEAR_MONTH_DAY);
						
						sFilename = sID + "_" + nCount.toString() + "_" +"message.eml";
						sPathAndFilename = oArchiver.get_cCompleteArchivePath() + File.separator + sFilename;
						sEndung = "EML";
						
						if (oIMAP.saveMail(sPathAndFilename) ) {
							nCount++;

							insertRecordInArchivemedien(oArchiver, 
									oArchiver.get_cSUB_DIR_IN_Archiv(), 
									sFilename, 
									sEndung, 
									"Original-Mail zu ID " + sID + System.getProperty("line.separator") + sSubject , 
									"EMAIL_INBOX", 
									sID, 
									Archiver_CONST.MEDIENKENNER.INBOX_ORIG.get_DB_WERT());
						}
						

						
						//
						// Ablegen des Mailtextes als Textfile, falls der zu lang ist
						//
						String sText = oIMAP.getMailBodyText();
						if (sText != null && sText.length() > 2000){
							// der Text der Mail ist zu lang für die Spalte, also wird der Text als Anhang extra gespeichert...
							sFilename = sID + "_" + nCount.toString() + "_" +"MESSAGE.txt";
							sPathAndFilename = oArchiver.get_cCompleteArchivePath() + File.separator + sFilename;
							sEndung = "TXT";
							
							File oFileOut = new File(sPathAndFilename);
							FileUtils.writeStringToFile(oFileOut, sText,"utf8");
							nCount++;
							
							insertRecordInArchivemedien(oArchiver, 
									oArchiver.get_cSUB_DIR_IN_Archiv(), 
									sFilename, 
									sEndung, 
									"Text-Body zur Mail " + sID + System.getProperty("line.separator") + sSubject, 
									"EMAIL_INBOX", 
									sID, 
									Archiver_CONST.MEDIENKENNER.INBOX_ORIG.get_DB_WERT());
						}
						
						
						
						// Ablegen der Anhänge 
						int nAnhaenge = oIMAP.getMailAttachmentCount();
						
						for (int n=1; n<= nAnhaenge; n++){
							sFilename = sID + "_" + nCount.toString() + "_" + oIMAP.getMailAttachmentFilename(n);
							sPathAndFilename = oArchiver.get_cCompleteArchivePath() + File.separator + sFilename;
							
							try {
								sEndung = sFilename.substring(sFilename.lastIndexOf(".") + 1);
							} catch (Exception e) {
								sEndung = "";
							} 
							
							nCount++;
					        
							// wenn die Dateiendung nicht bekannt ist, wird der Anhang nicht gespeichert!!
					        if ( !hsMedienTypen.contains(sEndung.toUpperCase()) ){
					        	bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Datei " + sFilename + " ist kein gültiges Medium zur Archivierung! " ));
					        	continue;
					        }
							
							if (oIMAP.saveMailAttachment(n, sPathAndFilename) ) {
								
								insertRecordInArchivemedien(oArchiver, 
															oArchiver.get_cSUB_DIR_IN_Archiv(), 
															sFilename, 
															sEndung, 
															"Anhang zur Email " + sID + System.getProperty("line.separator") + sSubject, 
															"EMAIL_INBOX", 
															sID, 
															Archiver_CONST.MEDIENKENNER.INBOX_ANHANG.get_DB_WERT());
								
								
								// Eintrag in die archivtabelle
								System.out.println(sPathAndFilename + "    GESPEICHERT!");
							}
							
						}
					}
					
					// jetzt alle anhänge an die adresse packen wenns geht...
					connectAddressToEmailEntryAuto(sID);
					
					nCount ++;
				}
			}
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			oIMAP.disconnect();
		}
	}


	/**
	 * Prüft den Medientyp und trägt den Anhang in die JT_ARCHIVMEDIEN ein, falls gültig.
	 * Sonst wird eine Alarmmessage geschrieben und "false" zurückgeliefert.
	 *  
	 * @author manfred
	 * @date   28.02.2013
	 * @param oArchiver
	 * @param Path
	 * @param Filename
	 * @param sEndung
	 * @param sDescription
	 * @param sTable
	 * @param sObjectID
	 * @param sMedienkenner
	 * @return
	 */
	private boolean insertRecordInArchivemedien(Archiver oArchiver,
												String Path,
												String Filename, 
												String sEndung,
												String sDescription,
												String sTable,
												String sObjectID,
												String sMedienkenner){
		boolean bRet = false;
		
		// wenn die Dateiendung nicht bekannt ist, wird der Anhang nicht gespeichert!!
        if ( hsMedienTypen.contains(sEndung.toUpperCase()) ){
        	
        	try {
        		oArchiver.WriteFileInfoToArchiveTable(Path,
        				Filename,
        				Filename,
        				sDescription ,
        				null,
        				null,
        				sTable, 
        				sObjectID,
        				sEndung,
        				sMedienkenner,
        				null,
        				null,
        				null,
        				null);
        		bRet = true;
        		
        	} catch (myException e) {
        		e.printStackTrace();
        		bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Die Datei " + Filename + " konnte nicht archiviert werden! " ));
        	}
        	
        } else {
        	bibMSG.add_MESSAGE(new MyE2_Info_Message("Die Datei " + Filename + " ist kein gültiges Medium zur Archivierung! " ));
        }
        
		return bRet;
	}
	
	
	/**
	 * Erzeugt den SQL-String fuer den eMail-Eintrag
	 * @author manfred
	 * @date   26.02.2013
	 * @param oIMAP
	 * @param host
	 * @param account
	 * @param folder
	 * @throws myException 
	 */
	private String getSQLForInsertEmailEntry(IMAP_Mail_Handler oIMAP, String host, String account, String folder) throws myException{
			MySqlStatementBuilder  oSql = new MySqlStatementBuilder();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String sOracleFormat = "'yyyy-mm-dd hh24:MI:SS'";
			String sText = "";

			
			Date dfSend = oIMAP.getMailSendDate();
			String sSend = " to_date( '" + df.format(dfSend) + "'," + sOracleFormat +")" ;
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__DATE_SEND,sSend , false);
			
			Date dfRec  = oIMAP.getMailReceiveDate();
			String sRec = " to_date( '" + df.format(dfRec) + "'," + sOracleFormat +")" ;
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__DATE_RECEIVED, sRec , false);
			
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__ID_EMAIL_INBOX, "SEQ_EMAIL_INBOX.NEXTVAL", false);
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__MESSAGE_ID, oIMAP.getMailMessageID(), true );
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__MAIL_UID, oIMAP.getMailUID().toString(), false);
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__MAIL_FROM,oIMAP.getMailFrom() , true);
			
			sText = oIMAP.getMailTo();
			if (sText != null && sText.length() > 250){
				sText = sText.substring(0, 245) + "...";
			}
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__MAIL_TO, sText, true);

			
			sText = oIMAP.getMailCC();
			if (sText != null && sText.length() > 250){
				sText = sText.substring(0, 245) + "...";
			}
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__MAIL_CC, sText, true);
			
			
			sText = oIMAP.getMailSubject();
			if (sText != null && sText.length() > 250){
				sText = sText.substring(0, 245) + "...";
			}
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__SUBJECT, sText, true);
			
			
			sText = oIMAP.getMailBodyText();
			if (sText != null && sText.length() > 2000){
				sText = sText.substring(0, 1900) + ".... (Text wurde Abgeschnitten. Vollständiger Mailtext in der Anlage MAILTEXT.TXT) " ;
			}
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__MESSAGE_TEXT,sText , true);
			
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__MAIL_HOST, host, true);
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__MAIL_ACCOUNT,account , true);
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__MAIL_FOLDER,folder , true);
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__ID_USER_GELESEN,null , true);

			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__ERZEUGT_VON, bibALL.get_KUERZEL(), true);
			oSql.addSQL_Paar(RECORD_EMAIL_INBOX.FIELD__ERZEUGT_AM, "SYSDATE", false);
			
			String sSql = oSql.get_CompleteInsertString(RECORD_EMAIL_INBOX.TABLENAME, bibE2.cTO());

			return sSql;
			
	}
	
	
	
	
	
	/**
	 * versucht Anhand der email-Entry-ID die zugehörige Adress-ID zu finden und diese 
	 * 1. in den Inbox-Datensatz zu schreiben
	 * 2. die Anhänge an die Adresse dran zu hängen
	 * @author manfred
	 * @date   08.03.2013
	 * @param sIDEmailEntry
	 */
	public void connectAddressToEmailEntryAuto(String sIDEmailEntry){
		String sIDAdresse = null;
		
		RECORD_EMAIL_INBOX recEmail;
		try {
			// den INboxeintrag lesen
			recEmail = new RECORD_EMAIL_INBOX(sIDEmailEntry);
			String sMailsender = recEmail.get_MAIL_FROM_cUF();
			if (sMailsender != null){
				// ermitteln der Adresse zur Email
				sIDAdresse =getAddressID_For_Sender(sMailsender);

				// Anhängen der Adresse an die Email
				if (sIDAdresse != null){
					doConnectEmailAndAdresse(recEmail, sIDAdresse);
				} else {
					bibMSG.add_MESSAGE(new MyE2_Info_Message("Es konnte keine zum Absender der Mail passende Adresse gefunden werden. (InboxID: " + recEmail.get_ID_EMAIL_INBOX_cUF_NN("?") + " eMail: " +sMailsender +" )"));
				}
			}
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message("Beim zuordnen der Email an eine Adresse ist ein Fehler aufgetreten"));
			e.printStackTrace();
		}
	}
	

	
	
	/**
	 * Ordnet eine beliebige email-Adresse einem Maileingan zu
	 * und hängt die anhänge der Email an die Adresse 
	 * @author manfred
	 * @date   13.03.2013
	 * @param sIDEmailEntry
	 * @param sIDAdresse
	 */
	public void connectAddressToEmailEntry(String sIDEmailEntry, String sIDAdresse){
		
		if (bibALL.isEmpty(sIDEmailEntry) ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Adresszuordnung: Die EmailID ist nicht vorhanden."));
		}
		
		if (bibALL.isEmpty(sIDAdresse) ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler bei der Adresszuordnung: Die AdressID ist nicht vorhanden."));
		}
		
		if (bibMSG.get_bHasAlarms()){
			return;
		}
		
		
		RECORD_EMAIL_INBOX recEmail;
		try {
			// den INboxeintrag lesen
			recEmail = new RECORD_EMAIL_INBOX(sIDEmailEntry);
			
			if (sIDAdresse != null){
				doConnectEmailAndAdresse(recEmail, sIDAdresse);
			} 
			

		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Beim zuordnen der Email an eine Adresse ist ein Fehler aufgetreten"));
			e.printStackTrace();
		}
		
	}
	
		
	
	/**
	 * Trennt die Verbindung zwischen Email und Adresse.
	 * Löscht auch die Anhänge die aus der Email kommen aus der Adresse 
	 *
	 * @author manfred
	 * @date   08.03.2013
	 * @param sIDEmailEntry
	 */
	public void disconnectAddressFromEmailEntry(String sIDEmailEntry){
		String sSql = "";
		String sIDAdresse = null;
		Vector<String> vSQL = new Vector<String>();
		
		if (bibALL.isEmpty(sIDEmailEntry) ){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Beim Lösen der Verknüpfung von Email und Adresse ist ein Fehler aufgetreten."));
			return;
		}
				
		
		RECORD_EMAIL_INBOX recEmail;
		MyE2_MessageVector vMsg = new MyE2_MessageVector();

		try {
			// den INboxeintrag lesen
			recEmail = new RECORD_EMAIL_INBOX(sIDEmailEntry);
			
			// die zugeordnete Adresse lesen
			sIDAdresse = recEmail.get_ID_ADRESSE_ZUGEORDNET_cUF();
					
			if (sIDAdresse != null){
				
				//
				// Alle Archivmedien suchen, die an dieser Email hängen von der angehängten Adresse löschen
				//
				RECLIST_ARCHIVMEDIEN rlArchivmedien = new RECLIST_ARCHIVMEDIEN ("TABLENAME = 'EMAIL_INBOX' AND ID_TABLE = " + sIDEmailEntry ,"");
				for(RECORD_ARCHIVMEDIEN rec :rlArchivmedien.values()){
					
					String sDateiname = rec.get_DATEINAME_VALUE_FOR_SQLSTATEMENT();
					String sPfad      = rec.get_PFAD_VALUE_FOR_SQLSTATEMENT();
					String sWhere     = "TABLENAME = 'ADRESSE' AND ID_TABLE = " + sIDAdresse + " AND DATEINAME = " + sDateiname + " AND PFAD = " + sPfad;
					RECLIST_ARCHIVMEDIEN rlArchivmedienAdresse = new RECLIST_ARCHIVMEDIEN (sWhere ,"");
					
					// alle zuordnungen löschen
					for (RECORD_ARCHIVMEDIEN rec_ADRESSE : rlArchivmedienAdresse.values()){
						sSql = "DELETE FROM " + bibE2.cTO() + "." + rec_ADRESSE.get_TABLE_NAME() + " WHERE " + rec_ADRESSE.FIELD__ID_ARCHIVMEDIEN + " = " + rec_ADRESSE.get_ID_ARCHIVMEDIEN_cUF_NN("-1");
						vSQL.add(sSql);
					}
				}
				
				// jetzt noch den Email-Eintrag ändern
				recEmail.set_NEW_VALUE_ID_ADRESSE_ZUGEORDNET(null);
				vSQL.add(recEmail.get_SQL_UPDATE_STATEMENT(null, true) );
				
				// die Einträge in der DB eintragen
				bibMSG.add_MESSAGE( bibDB.ExecMultiSQLVector(vSQL, true));
			}
		} catch (myException e) {
			bibMSG.add_MESSAGE(vMsg);
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Beim Lösen der Verknüpfung von Email und Adresse ist ein Fehler aufgetreten."));
			e.printStackTrace();
		}
		
	}
	

	
	
	
	/**
	 * geht alle noch nicht verarbeiteten Emails durch und hängt sie zu den zugehörigen Adressen wenn sie gefunden wurden
	 * - keine zugeordnete id_adresse
	 * - nicht gelöscht
	 * - nicht bestätigt
	 * 
	 * @author manfred
	 * @throws myException 
	 * @date   14.03.2013
	 */
	public void autoConnectAllUnconnectedEmailsToAddresses() throws myException{
		RECLIST_EMAIL_INBOX rlEmail = new RECLIST_EMAIL_INBOX("nvl(" + RECORD_EMAIL_INBOX.FIELD__ID_ADRESSE_ZUGEORDNET + ",-1) = -1 AND " + RECORD_EMAIL_INBOX.FIELD__ID_USER_GELESEN + " IS NULL AND nvl(" + RECORD_EMAIL_INBOX.FIELD__DELETED + ",'N') = 'N' ", "");
		
		for (RECORD_EMAIL_INBOX rec : rlEmail.values()){
			String sIDInbox = rec.get_ID_EMAIL_INBOX_cUF();
			connectAddressToEmailEntryAuto(sIDInbox);
		}
	}
	
	

	/**
	 * Erzeugt die SQL-Statements zur Anbindung der Adresse an die Email und
	 * hängt auch alle Anhänge der EMail an die Adresse 
	 * 
	 * @author manfred
	 * @date   13.03.2013
	 * @param recEmail
	 * @param sIDAdresse
	 * @throws myException
	 */
	private void doConnectEmailAndAdresse(RECORD_EMAIL_INBOX recEmail, String sIDAdresse) throws myException{
		
		Vector<String> vSQL = new Vector<String>();
		String sIDEmailEntry = recEmail.get_ID_EMAIL_INBOX_cUF_NN("-1");
		// ermitteln des Updatestatements für die INBOX
		recEmail.set_NEW_VALUE_ID_ADRESSE_ZUGEORDNET(sIDAdresse);
		String sSql = recEmail.get_SQL_UPDATE_STATEMENT(null, true);
		vSQL.add(sSql);
		
		
		// jeden Anhang den wir zu diesem Eintrag finden können auch an die Adresse hängen
		RECLIST_ARCHIVMEDIEN rlArchivmedien = new RECLIST_ARCHIVMEDIEN ("TABLENAME = 'EMAIL_INBOX' AND ID_TABLE = " + sIDEmailEntry ,"");
		for(RECORD_ARCHIVMEDIEN rec :rlArchivmedien.values()){
			
			RECORD_ARCHIVMEDIEN_ext recExt =  new RECORD_ARCHIVMEDIEN_ext(rec);
			
			vSQL.add(recExt.connectToAdditionalEntry("ADRESSE", sIDAdresse));

		}
		
		// die Einträge in der DB eintragen
		bibMSG.add_MESSAGE( bibDB.ExecMultiSQLVector(vSQL, true) );

	}
	
	

	

	/**
	 * ermittelt alle aktuellen Mailboxen die abgerufen werden sollen
	 * @author manfred
	 * @date   25.02.2013
	 * @return
	 */
	private RECLIST_EMAIL_INBOX_DEF getInboxes(){
		RECLIST_EMAIL_INBOX_DEF inboxes = null;
		try {
			inboxes = new RECLIST_EMAIL_INBOX_DEF(RECORD_EMAIL_INBOX_DEF.FIELD__AKTIV + " = 'Y' ", "");
		} catch (myException e) {
			e.printStackTrace();
		}
		
		return inboxes;
	}
	
	
	
	/**
	 * Ermittelt fuer die definierte INBOX die zuletzt gelesene (groeßte) UID, die in der Datenbank
	 * abgelegt wurde. 
	 * (Alle Mails danach muessen dann gelesen werden.) 
	 * @author manfred
	 * @date   26.02.2013
	 * @param inbox
	 * @return
	 */
	private long getLastInboxUID(RECORD_EMAIL_INBOX_DEF inbox){
		long lStart = 0L;
		try {
			String sSql = "SELECT max(MAIL_UID) " +
					" FROM " + bibE2.cTO()+ ".JT_EMAIL_INBOX " +
					" where ID_MANDANT = " + inbox.get_ID_MANDANT_cUF_NN("-1") + 
					" and MAIL_HOST = '" + inbox.get_MAIL_HOST_cUF_NN("-") + "' " +
					" and MAIL_ACCOUNT =  '" + inbox.get_MAIL_ACCOUNT_cUF_NN("-") + "' " +
					" and MAIL_FOLDER =  '" + inbox.get_MAIL_FOLDER_cUF_NN("-") + "' " ;
			
			String sStart = bibDB.EinzelAbfrage(sSql);
			
			if (sStart != null){
				lStart = Long.parseLong(sStart);
			}
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e2){
			lStart = 0;
		}
		
		return lStart;
	}
	
	
	
	/**
	 * Liest alle MAIL-UIDs die in der Tabelle zu dem Postfach abgelegt sind
	 * @author manfred
	 * @date   27.02.2013
	 * @param inbox
	 * @return
	 */
	private HashSet<Long> getInboxUIDs(RECORD_EMAIL_INBOX_DEF inbox){
		HashSet<Long> lUIDs = new HashSet<Long>();
		
		try {
			String sSql = "SELECT MAIL_UID " +
					" FROM " + bibE2.cTO()+ ".JT_EMAIL_INBOX " +
					" where ID_MANDANT = " + inbox.get_ID_MANDANT_cUF_NN("-1") + 
					" and MAIL_HOST = '" + inbox.get_MAIL_HOST_cUF_NN("-") + "' " +
					" and MAIL_ACCOUNT =  '" + inbox.get_MAIL_ACCOUNT_cUF_NN("-") + "' " +
					" and MAIL_FOLDER =  '" + inbox.get_MAIL_FOLDER_cUF_NN("-") + "' " +
					" ORDER BY MAIL_UID" ;
			
			String sStart[][] = bibDB.EinzelAbfrageInArray(sSql);
			
			
			if (sStart != null){
				for (int i = 0; i < sStart.length; i++){
					lUIDs.add(Long.parseLong(sStart[i][0]));
				}
			}
			
		} catch (myException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e2){
//			lStart = 0;
			e2.printStackTrace();
		}
		
		return lUIDs;
	}
	
	
	
	/**
	 * Initialisiert eine Liste von Medientypen, die man als Anhang automatisch hochladen darf.
	 * @author manfred
	 * @date   28.02.2013
	 */
	private void initMedienTypen (){
		hsMedienTypen.clear();
        
		String[][] sIDMedienTypen = bibDB.EinzelAbfrageInArray("SELECT upper(DATEIENDUNG) FROM "+ bibE2.cTO() + ".JT_MEDIENTYP " );

        for (int i=0; i<sIDMedienTypen.length; i++){
        	   hsMedienTypen.add(sIDMedienTypen[i][0]);
        }
	}
	
	
	
	
	/**
	 * Ermittelt die Adress-ID zu der die email-Adresse gehört
	 * @author manfred
	 * @date   06.03.2013
	 * @param email_address
	 * @return
	 */
	private String getAddressID_For_Sender(String email_address){
		String sIDAdresse = null;
		String sSql = "SELECT ID_ADRESSE FROM " + bibE2.cTO() + ".JT_EMAIL WHERE upper(email) = upper('"+ email_address.toLowerCase().trim()  +"')";
		
		String[][] sResult = bibDB.EinzelAbfrageInArray(sSql);
		
		if (sResult.length == 1){
			sIDAdresse = sResult[0][0];
		}
		
		return sIDAdresse;
	}
	
	
}
