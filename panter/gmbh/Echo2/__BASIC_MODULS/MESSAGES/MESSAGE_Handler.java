package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MODUL_LINK.MODUL_LINK_SQL_Factory;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.NACHRICHT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MODUL_CONNECT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_NACHRICHT_KAT_DEFAULT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT_KAT_DEFAULT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

public class MESSAGE_Handler {

	
	//2011-11-17
	private String cNACHRICHT_TYP = MESSAGE_CONST.MESSAGE_TYP_USER;
	
	
	
	/**
	 * zum erzeugen einer standard-benutzer-nachricht (2011-11-17)
	 */
	public MESSAGE_Handler()
	{
		super();
	}

	/**
	 * zum erzeugen einer nachricht beliebigen typs  (2011-11-17)
	 * @param cNachrichtTyp
	 */
	public MESSAGE_Handler(String cNachrichtTyp) throws myException
	{
		super();
		
		if (cNachrichtTyp.equals( MESSAGE_CONST.MESSAGE_TYP_USER) || cNachrichtTyp.equals( MESSAGE_CONST.MESSAGE_TYP_SYSTEM))
		{
			this.cNACHRICHT_TYP=cNachrichtTyp;
		}
		else
		{
			throw new myException(this,"TYP_NACHRICHT:"+cNachrichtTyp+" is no allowed type !!!");
		}
		
		
	}

	

			
	
		
	
	/**
	 * Speichert eine Meldung die als MESSAGE_Entry übergeben wurde.
	 * Alle Informationen der Meldung werden in der MESSAGE_Entry definiert.
	 * 
	 * 
	 * @author manfred
	 * @date   08.07.2015
	 *
	 * @param oMessage
	 * @return
	 * @throws myException
	 */
	public boolean saveMessage(MESSAGE_Entry oMessage) throws myException { 
		
		boolean bRet=false;
		bRet = this.saveMessage(oMessage.get_Titel(), 
				oMessage.get_Nachricht(), 
				oMessage.get_vIdEmpfaenger(), 
				oMessage.get_rec_Nachricht_ori() , 
				oMessage.get_Sofortanzeige(), 
				oMessage.get_DtAnzeigeAb(), 
				oMessage.get_KeyReminder(), 
				oMessage.get_vTargets(), 
				oMessage.get_Kategorie(),
				oMessage.get_ID_Kategorie(),
				oMessage.get_SendEmail(),
				oMessage.get_IDUserSender(),
				oMessage.get_vArchivmedien());
		return bRet;
	}
	
	
	

	
	
	
	/**
	 * MAIN SAVE METHOD
	 * @param sTitel
	 * @param sNachricht
	 * @param vIdEmpfaenger
	 * @param rec_ori
	 * @param bSofortanzeige
	 * @param sDtAnzeigeAb
	 * @param sKeyReminder  Kennung, die von der automatischen Wiedervorlagen-Estellung geschrieben wird, damit diese nicht doppelt eingetragen wird
	 * @param cTextAufButton  ButtonText fuer modulsprung (falls leer, wird der standard benutzt)
	 * @return
	 * @throws myException
	 */
	private boolean saveMessage(String sTitel, 
								String sNachricht, 
								Vector<String> vIdEmpfaenger, 
								RECORD_NACHRICHT rec_ori, 
								Boolean bSofortanzeige, 
								String sDtAnzeigeAb, 
								String sKeyReminder,
								Vector<MESSAGE_Entry_Target> vTargets,
								String sNachrichtKategorie,
								Long   lIDNachrichtKategorie,
								Boolean bSendEmail,
								String sIDUserSender,
								Vector<RECORD_ARCHIVMEDIEN> vArchivmedien
								) throws myException{
		
		boolean bRet = false;
		
		Vector<String> vsSql = new Vector<String>();
		String sIdUser = null;
		MySqlStatementBuilder  oSql = new MySqlStatementBuilder();

		// wenn keine Empfänger da sind, auch nichts schicken
		if (vIdEmpfaenger.size() == 0){
			return bRet;
		}
		if (sTitel == null || sTitel.equals("") || sNachricht == null || sNachricht.equals("")){
			return bRet;
		}

		
		// Wenn ein Zielmodul und eine ZielID angegeben ist, dann auch die Verlinkung definieren
		MODUL_LINK_SQL_Factory oLinkFactory = null;
		if (vTargets != null && vTargets.size()>0){
			oLinkFactory = new MODUL_LINK_SQL_Factory();
		}
		
		String sIDKategorie = null;
		if (lIDNachrichtKategorie != null ) {
			sIDKategorie = lIDNachrichtKategorie.toString();
		}  else {
			sIDKategorie = getID_from_Kategorie(sNachrichtKategorie);
		}
		
		// initiales setzen des SQL-Statements, alle variablen Werte werden ersetzt
		oSql.clear();
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__ID_NACHRICHT			, "SEQ_NACHRICHT.NEXTVAL", false);
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__ID_USER				, sIdUser,false);
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__TITEL					, sTitel, true);
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__NACHRICHT				, sNachricht, true);
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__BESTAETIGT				, "N" , true);
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__ID_USER_SENDER			, (sIDUserSender != null ? sIDUserSender : bibALL.get_ID_USER() ), false);
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__ID_GRUPPE				, "SEQ_NACHRICHT.CURRVAL", false);
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__ERZEUGT_VON			, bibALL.get_KUERZEL(), true);
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__ERZEUGT_AM				, "SYSDATE", false);
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__SOFORTANZEIGE			, (bSofortanzeige ? "Y" : "N"),true);
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__AKTIV_AB				, sDtAnzeigeAb ,true);
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__WV_KENNUNG				, sKeyReminder,true);
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__ID_NACHRICHT_KATEGORIE	, sIDKategorie ,false);
		//2011-11-17
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__TYP_NACHRICHT			, cNACHRICHT_TYP,true);
		// 2011-12-1
		oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__SEND_EMAIL				, (bSendEmail ? "Y" : "N"),true );
		
		if (rec_ori == null){
			
			// Neue Nachricht 1
			sIdUser = vIdEmpfaenger.get(0);
			sIdUser = sIdUser.replace(".", "");
			oSql.addSQL_Paar(RECORD_NACHRICHT.FIELD__ID_USER				, sIdUser,false);
			
			
			// 1. Statement ausführen
			bRet = bibDB.ExecSQL(oSql.get_CompleteInsertString("JT_NACHRICHT", bibE2.cTO()), false);
			if (!bRet){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Schreiben der Nachricht! Möglicherweise ist die Nachricht zu lang."));
				return false;
			}
			
			
			
			// für diesen eintrag noch alle Links aufbauen
			if (oLinkFactory != null){
				String sIDCurr = bibDB.EinzelAbfrage("SELECT SEQ_NACHRICHT.CURRVAL FROM DUAL");
				for (MESSAGE_Entry_Target target : vTargets){
					String cButtonText = target.get_Target_Button_Text();
					if (S.isEmpty(cButtonText))	{
						cButtonText = "Springe zu " + sTitel;
					}
					
					// anhand des Modulnamens wird in der aufgerufenen Funktion die Verbindung auf die Liste oder Maske generiert.
					vsSql.addAll(oLinkFactory.get_SQL_Statement_For_Insert_Message_Button( 
							sIDCurr, 
							target.get_IDTarget(), 
							target.get_ModulTarget(), 
							target.get_ModulTargetZusatz(), 
							cButtonText)) ;				
					}
			}
			
			
			// falls Archivmedien angehängt werden sollen müssen diese noch geschrieben werden
			if (vArchivmedien != null && vArchivmedien.size() > 0){
				for (RECORD_ARCHIVMEDIEN recArch: vArchivmedien){
					RECORD_ARCHIVMEDIEN_ext recExt = new RECORD_ARCHIVMEDIEN_ext(recArch);
					vsSql.add( recExt.connectToAdditionalEntry(NACHRICHT.baseTabName(), "SEQ_NACHRICHT.CURRVAL") );
				}
			}
			

			
			if (bRet){
				// alle folgenden Nachrichten
				if (vIdEmpfaenger.size() > 1){
					// finden der aktuellen Sequenz
					String sIDCurr = bibDB.EinzelAbfrage("SELECT SEQ_NACHRICHT.CURRVAL FROM DUAL");
					for (int i = 1; i < vIdEmpfaenger.size(); i++){
						sIdUser = vIdEmpfaenger.get(i);
						sIdUser = sIdUser.replace(".", "");

						oSql.addSQL_Paar("ID_USER", sIdUser,false);
						oSql.addSQL_Paar("ID_GRUPPE", sIDCurr, false);

						vsSql.add(oSql.get_CompleteInsertString("JT_NACHRICHT", bibE2.cTO()));
						
						
						// restliche Statements
						// für diesen eintrag noch den Link aufbauen
						if (oLinkFactory != null){
							for (MESSAGE_Entry_Target target : vTargets){
								String cButtonText = target.get_Target_Button_Text();
								if (S.isEmpty(cButtonText)){
									cButtonText = "Springe zu " + sTitel;
								}
								
								// anhand des Modulnamens wird in der aufgerufenen Funktion die Verbindung auf die Liste oder Maske generiert.
								vsSql.addAll(oLinkFactory.get_SQL_Statement_For_Insert_Message_Button( 
										"SEQ_NACHRICHT.CURRVAL", 
										target.get_IDTarget(), 
										target.get_ModulTarget(), 
										target.get_ModulTargetZusatz(), 
										cButtonText)) ;				
								}
						}
					}
					
					// falls Archivmedien angehängt werden sollen müssen diese noch geschrieben werden
					if (vArchivmedien != null && vArchivmedien.size() > 0){
						for (RECORD_ARCHIVMEDIEN recArch: vArchivmedien){
							RECORD_ARCHIVMEDIEN_ext recExt = new RECORD_ARCHIVMEDIEN_ext(recArch);
							vsSql.add( recExt.connectToAdditionalEntry(NACHRICHT.baseTabName(), "SEQ_NACHRICHT.CURRVAL") );
						}
					}
					
				}
			}
			
		} else {
			// Antwort
			
			rec_ori.set_NEW_VALUE_BESTAETIGT("Y");
			vsSql.add(rec_ori.get_SQL_UPDATE_STATEMENT(null, true));
				
			// Neue Nachricht 1
			sIdUser = vIdEmpfaenger.get(0);
			sIdUser = sIdUser.replace(".", "");
			
			oSql.addSQL_Paar("ID_USER", sIdUser,false);
			oSql.addSQL_Paar("ID_NACHRICHT_PARENT", rec_ori.get_ID_NACHRICHT_cUF(),false);

			
			// jetzt noch mal schauen, ob es ein reminder gibt, wenn ja, dann diesen auch kopieren
			RECLIST_MODUL_CONNECT list = new RECLIST_MODUL_CONNECT(	RECORD_MODUL_CONNECT.FIELD__ID_QUELLE + " = " + rec_ori.get_ID_NACHRICHT_cUF() + 
																	" AND " + RECORD_MODUL_CONNECT.FIELD__QUELLE + " = '" + E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_LISTE + "'", "");
			
			
			// 1. Statement
			bRet = bibDB.ExecSQL(oSql.get_CompleteInsertString("JT_NACHRICHT", bibE2.cTO()), false);
			if (!bRet ){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Schreiben der Nachricht!"));
				return false;
			}
			
			
			// Referenzen weiter geben an die antworten
			if (list.size() > 0){
				for(int i=0; i< list.size(); i++){
					RECORD_MODUL_CONNECT rec = list.get(i);
					MySqlStatementBuilder build = rec.get_StatementBuilderFilledWithActualValues();
					build.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_QUELLE, "SEQ_NACHRICHT.CURRVAL", false);
					build.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_MODUL_CONNECT, "SEQ_MODUL_CONNECT.NEXTVAL", false);
					vsSql.add(build.get_CompleteInsertString("JT_MODUL_CONNECT", bibE2.cTO() ));
				}
			}
			

			if (bRet){
				// alle folgenden Nachrichten
				if (vIdEmpfaenger.size() > 1){
					// finden der aktuellen Sequenz
					String sIDCurr = bibDB.EinzelAbfrage("SELECT SEQ_NACHRICHT.CURRVAL FROM DUAL");
					
					for (int i = 1; i < vIdEmpfaenger.size(); i++){
						sIdUser = vIdEmpfaenger.get(i);
						sIdUser = sIdUser.replace(".", "");
						oSql.addSQL_Paar("ID_USER", sIdUser);
						oSql.addSQL_Paar("ID_GRUPPE", sIDCurr, false);
						oSql.addSQL_Paar("ID_NACHRICHT_PARENT", rec_ori.get_ID_NACHRICHT_cUF(),false);
						oSql.addSQL_Paar("WV_KENNUNG", "null",false);
						
						
						// restliche Statements
						vsSql.add(oSql.get_CompleteInsertString("JT_NACHRICHT", bibE2.cTO()));
						
						if (list.size() > 0){
							for(int j=0; j< list.size(); j++){
								RECORD_MODUL_CONNECT rec = list.get(j);
								MySqlStatementBuilder build = rec.get_StatementBuilderFilledWithActualValues();
								build.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_QUELLE, "SEQ_NACHRICHT.CURRVAL", false);
								build.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_MODUL_CONNECT, "SEQ_MODUL_CONNECT.NEXTVAL", false);
								
								vsSql.add(build.get_CompleteInsertString("JT_MODUL_CONNECT", bibE2.cTO() ));
							}
						}
					}
				}
			}
		}
		
		
		// Werte eintragen
		MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(vsSql, true);
		bRet &= mv.get_bIsOK();
		
		if (bRet){
			bibDB.Commit();
			
		} else {
			bibDB.Rollback();
			bibMSG.add_MESSAGE(mv);
		}
		
		// nachdem die Message geschrieben ist, 
		// alle offenen Emails senden
		new MESSAGE_Email_Handler().sendAllOpenMails(); 
		
		
		return bRet;
	}
	
	
	
	
	
	/**
	 * Erzeugt aus einer Nachricht eine Wiedervorlage mit dem gleichen Text 
	 * d.h. verschiebt die Nachricht auf ein neues Datum als Kopie
	 * die ursprüngliche Nachricht wird als gelesen markiert
	 * 
	 * @param rec_ori
	 * @param sDtAnzeigeAb
	 * @return
	 * @throws myException 
	 */
	public boolean copyReminder(RECORD_NACHRICHT rec_ori, String sDtAnzeigeAb) throws myException{
		boolean bRet = true;
		
		Vector<String> vsSql = new Vector<String>();
		rec_ori.set_NEW_VALUE_BESTAETIGT("Y");
		vsSql.add(rec_ori.get_SQL_UPDATE_STATEMENT(null, true));
		
		// jetzt die Kopie anfertigen und das neue Datum reinschreiben
		MySqlStatementBuilder oSql = rec_ori.get_StatementBuilderFilledWithActualValues();
		oSql.addSQL_Paar("ID_NACHRICHT", "SEQ_NACHRICHT.NEXTVAL", false);
		oSql.addSQL_Paar("ID_GRUPPE", "SEQ_NACHRICHT.CURRVAL", false);
		oSql.addSQL_Paar("BESTAETIGT", "N" , true);
		oSql.addSQL_Paar("AKTIV_AB", sDtAnzeigeAb ,true);
		oSql.addSQL_Paar("WV_KENNUNG", "null",false);
		oSql.addSQL_Paar(NACHRICHT.email_sent.fieldName(), "null",false);
		oSql.addSQL_Paar(NACHRICHT.msg_convert.fieldName(),"null",false);
		
		
		vsSql.add(oSql.get_CompleteInsertString("JT_NACHRICHT", bibE2.cTO()));
		
		
		// jetzt noch mal schauen, ob es eine Modulverknüfung gibt, wenn ja, dann diese auch kopieren
		RECLIST_MODUL_CONNECT list = new RECLIST_MODUL_CONNECT(	RECORD_MODUL_CONNECT.FIELD__ID_QUELLE + " = " + rec_ori.get_ID_NACHRICHT_cUF() + 
																" AND " + RECORD_MODUL_CONNECT.FIELD__QUELLE + " = '" + E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_LISTE + "'", "");
		
		if (list.size() > 0){
			for(int j=0; j< list.size(); j++){
				RECORD_MODUL_CONNECT rec = list.get(j);
				MySqlStatementBuilder build = rec.get_StatementBuilderFilledWithActualValues();
				build.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_QUELLE, "SEQ_NACHRICHT.CURRVAL", false);
				build.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_MODUL_CONNECT, "SEQ_MODUL_CONNECT.NEXTVAL", false);
				
				vsSql.add(build.get_CompleteInsertString("JT_MODUL_CONNECT", bibE2.cTO() ));
			}
		}
		
		// schauen, ob es noch Anhänge gibt, wenn ja diese verknüpfen...
		// Liste der Anhänge ermitteln:
		// Alle Archivmedien suchen, die an dieser Nachricht hängen 
		//
		RECLIST_ARCHIVMEDIEN_ext rlArchivmedienNachricht = new RECLIST_ARCHIVMEDIEN_ext(_DB.NACHRICHT, rec_ori.get_ID_NACHRICHT_cUF(), null,null);
		for(RECORD_ARCHIVMEDIEN recArchiv :rlArchivmedienNachricht.values()){
			RECORD_ARCHIVMEDIEN_ext recExt = new RECORD_ARCHIVMEDIEN_ext(recArchiv);
			vsSql.add( recExt.connectToAdditionalEntry(NACHRICHT.baseTabName(), "SEQ_NACHRICHT.CURRVAL") ); 
		}

		
		
		// Werte eintragen
		MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(vsSql, true);
		bRet &= mv.get_bIsOK();
		
		if (bRet){
			bibDB.Commit();
			
		} else {
			bibDB.Rollback();
		}
		
		// alle offenen Emails verschicken
		new MESSAGE_Email_Handler().sendAllOpenMails();
		
		return bRet;

		
	}
	
	
	/**
	 * Sucht die Merkmal-ID aus der Tabelle der Default-Merkmale
	 * 
	 * @author manfred
	 * @date   25.06.2015
	 *
	 * @param sMerkmal
	 * @return
	 */
	public String getID_from_Kategorie(String sMerkmal){
		String sRet = null;
		RECLIST_NACHRICHT_KAT_DEFAULT rl = null;
		if (sMerkmal != null){
			try {
				rl = new RECLIST_NACHRICHT_KAT_DEFAULT("UPPER( " + RECORD_NACHRICHT_KAT_DEFAULT.FIELD__MODULNAME + ") = UPPER('" + sMerkmal.trim() + "') ", "");
				if (rl.size() > 0){
					sRet = rl.get(0).get_ID_NACHRICHT_KATEGORIE_cUF();
				}
			} catch (myException e) {
				// fehler beim ermitteln der ID
				e.printStackTrace();
			}
		}
		
		return sRet;
	}
	
	
	/**
	 * Setzt die Nachricht in den Status gelesen
	 * @author manfred
	 * @date 16.07.2018
	 *
	 * @param rec
	 */
	public void setMessageAsRead(RECORD_NACHRICHT rec){
		String id = "?";
		try {
			Rec21 r = new Rec21(_TAB.nachricht)._fill(rec);
			id = r.get_ufs_dbVal(NACHRICHT.id_nachricht, "?");
			r.set_NewValueForDatabase(NACHRICHT.bestaetigt.fn(), "Y");
			r.UPDATE(true);
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString(String.format("Meldung %s konnte nicht bestätigt werden",id ))));
		}
	}
	
	
	
	/**
	 * Setzt eine Sofort-Nachricht in den Status normal
	 * @author manfred
	 * @date 16.07.2018
	 *
	 * @param rec
	 */
	public void setMessageAsNormal(RECORD_NACHRICHT rec){
		String id = "?";
		try {
			Rec21 r = new Rec21(_TAB.nachricht)._fill(rec);
			id = r.get_ufs_dbVal(NACHRICHT.id_nachricht, "?");

			if (r.getUfs(NACHRICHT.sofortanzeige,"N").equalsIgnoreCase("Y")){
				r._setNewValueInDatabaseTerminus(NACHRICHT.msg_convert, "sysdate");
				r.set_NewValueForDatabase(NACHRICHT.sofortanzeige.fn(), "N");
				r.UPDATE(true);
			}

		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString(String.format("Sofortnachrichten-Status der Meldung %s konnte nicht geändert werden",id ))));
		}
		
		
		
	}

}
