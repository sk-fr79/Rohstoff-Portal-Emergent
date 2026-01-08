package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_EINTRAG;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map.Entry;

import oracle.net.aso.s;

import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.MyE2_Warning_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Handler;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.REMINDERS.REMINDER_Handler_Laufzettel_Eintrag;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_ENUMS.LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_PRIO;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MODUL_CONNECT;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_NACHRICHT;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_EINTRAG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAUFZETTEL_STATUS;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MODUL_CONNECT;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

/**
 * Klasse zum erzeugen von Laufzettel-Einträgen z.B. für die Wiedervorlage nach
 * KADENZ
 * 
 * @author manfred
 *
 */

public class WF_Handler {

	// private void createNewWorkflowEntry(String IDWorkflow,String IDBesitzer,
	// String IDUser, String Aufgabe, Date Termin ){
	//
	// }
	// Wird gesetzt, wenn ein neuer Workflow-Eintrag generiert wird.
	private String m_idWorkflowCreated = null;

	/**
	 * Erzeugt einen neuen WF-Eintrag aus einem Alten wenn: im alten WF eine
	 * KADENZ angegeben wurde und der Alte Eintrag abgeschlossen ist, und der
	 * Alte Eintrag noch nicht zur Wiedervorlage/Wiederbearbeitung generiert
	 * wurde.
	 * 
	 * @param IDWorkflow
	 * @param IDWorkflowEntry
	 * @throws myException
	 * @throws ParseException
	 */
	public String createNew_Workflow_And_Entry(String sWorkflowTitle, String sWorkflowEntry, String sKadenz_In_Tagen) throws myException {

		String sRet = null;
		m_idWorkflowCreated = null;

		// Default-Status
		RECLIST_LAUFZETTEL_STATUS reclistStatusDefault = new RECLIST_LAUFZETTEL_STATUS("NVL(ISDEFAULT,'N') = 'Y'", "");
		if (reclistStatusDefault.size() <= 0) {
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Es ist kein Standard-Status für den Laufzettel angegeben. Es kann kein Eintrag generiert werden!"));
			return null;
		}
		String idLaufzettelStatus = reclistStatusDefault.get(0).get_ID_LAUFZETTEL_STATUS_cUF();

		// Default-Prio
		RECLIST_LAUFZETTEL_PRIO reclistPrioDefault = new RECLIST_LAUFZETTEL_PRIO("NVL(ISDEFAULT,'N') = 'Y'", "");
		if (reclistPrioDefault.size() <= 0) {
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Es ist keine Standard-Priorität für den Laufzettel angegeben. Es kann kein Eintrag generiert werden!"));
			return null;
		}
		String idLaufzettelPrio = reclistPrioDefault.get(0).get_ID_LAUFZETTEL_PRIO_cUF();

		Vector<String> vSQL = new Vector<String>();

		MySqlStatementBuilder oSql = new MySqlStatementBuilder();

		// der Laufzettel
		// Neue Nachricht 1

		oSql.clear();
		oSql.addSQL_Paar(RECORD_LAUFZETTEL.FIELD__ID_LAUFZETTEL, "SEQ_LAUFZETTEL.NEXTVAL", false);

		oSql.addSQL_Paar(RECORD_LAUFZETTEL.FIELD__ANGELEGT_AM, "SYSDATE", false);
		oSql.addSQL_Paar(RECORD_LAUFZETTEL.FIELD__ID_LAUFZETTEL_STATUS, idLaufzettelStatus, false);
		oSql.addSQL_Paar(RECORD_LAUFZETTEL.FIELD__ID_USER_BESITZER, bibALL.get_ID_USER(), false);
		oSql.addSQL_Paar(RECORD_LAUFZETTEL.FIELD__TEXT, sWorkflowTitle, true);

		// 1. Statement für den Laufzettel
		vSQL.add(oSql.get_CompleteInsertString("JT_LAUFZETTEL", bibE2.cTO()));

		oSql.clear();
		oSql.addSQL_Paar(RECORD_LAUFZETTEL_EINTRAG.FIELD__ID_LAUFZETTEL_EINTRAG, "SEQ_LAUFZETTEL_EINTRAG.NEXTVAL", false);
		oSql.addSQL_Paar(RECORD_LAUFZETTEL_EINTRAG.FIELD__ID_LAUFZETTEL, "SEQ_LAUFZETTEL.CURRVAL", false);
		oSql.addSQL_Paar(RECORD_LAUFZETTEL_EINTRAG.FIELD__ANGELEGT_AM, "SYSDATE", false);
		oSql.addSQL_Paar(RECORD_LAUFZETTEL_EINTRAG.FIELD__ID_USER_BESITZER, bibALL.get_ID_USER(), false);
		oSql.addSQL_Paar(RECORD_LAUFZETTEL_EINTRAG.FIELD__ID_LAUFZETTEL_PRIO, idLaufzettelPrio, false);
		oSql.addSQL_Paar(RECORD_LAUFZETTEL_EINTRAG.FIELD__ID_LAUFZETTEL_STATUS, idLaufzettelStatus, false);
		oSql.addSQL_Paar(RECORD_LAUFZETTEL_EINTRAG.FIELD__KADENZ_NACH_ABSCHLUSS, sKadenz_In_Tagen, false);
		oSql.addSQL_Paar(RECORD_LAUFZETTEL_EINTRAG.FIELD__AUFGABE, sWorkflowEntry, true);

		// 2. Statement für den Laufzettel-Eintrag
		vSQL.add(oSql.get_CompleteInsertString("JT_LAUFZETTEL_EINTRAG", bibE2.cTO()));

		// starten der Transaktion
		MyE2_MessageVector vRet = bibDB.ExecMultiSQLVector(vSQL, false);

		if (vRet.get_bIsOK()) {
			// jetzt noch die Laufzettel-ID ermitteln:
			sRet = bibDB.EinzelAbfrage("SELECT SEQ_LAUFZETTEL_EINTRAG.CURRVAL FROM DUAL");

			// die ID des aktuellen Workflows lesen
			m_idWorkflowCreated = bibDB.EinzelAbfrage("SELECT SEQ_LAUFZETTEL.CURRVAL FROM DUAL");

			if (bibDB.Commit() == false) {
				bibDB.Rollback();
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler beim speichern des Laufzettels!")));
				m_idWorkflowCreated = null;
				sRet = null;
			}
		} else {
			bibMSG.add_MESSAGE(vRet);
		}

		// die neue LaufzettelEintrag-ID wird zurückgegeben
		// die Laufzettel-ID muss über die Methode getIDWorkflowOfCreatedEntry()
		// gelesen werden.
		return sRet;
	}

	/**
	 * gibt die ID des zuletzt generierten Workflows zurück
	 * 
	 * @return
	 */
	public String getIDWorkflowOfCreatedEntry() {
		return m_idWorkflowCreated;
	}

	/**
	 * Erzeugt einen neuen WF-Eintrag aus einem Alten wenn: im alten WF eine
	 * KADENZ angegeben wurde und der Alte Eintrag abgeschlossen ist, und der
	 * Alte Eintrag noch nicht zur Wiedervorlage/Wiederbearbeitung generiert
	 * wurde.
	 * 
	 * @param IDWorkflow
	 * @param IDWorkflowEntry
	 * @throws myException
	 * @throws ParseException
	 */
	public boolean createNew_WFEntry_ForReminder(String IDWorkflow, String IDWorkflowEntry, boolean bCommit, boolean bSendReminderToUser) throws myException {

		RECORD_LAUFZETTEL_EINTRAG oRecEintrag_alt = new RECORD_LAUFZETTEL_EINTRAG(IDWorkflowEntry);
		RECORD_LAUFZETTEL oRecLaufzettel_alt = oRecEintrag_alt.get_UP_RECORD_LAUFZETTEL_id_laufzettel();

		RECORDNEW_LAUFZETTEL_EINTRAG oRecNew = null;

		m_idWorkflowCreated = null;
		boolean bRet = false;

		Vector<String> vSql = new Vector<String>();

		// alle Stati, die einen Abschluss triggern
		RECLIST_LAUFZETTEL_STATUS reclistStatus = new RECLIST_LAUFZETTEL_STATUS("SELECT * FROM " + bibE2.cTO() + ".JT_LAUFZETTEL_STATUS WHERE NVL(TRIGGER_ABSCHLUSS,'N') = 'Y'");

		// Standard-Status, den man braucht um einen neuen Eintrag zu
		// generieren, sonst Fehler
		RECLIST_LAUFZETTEL_STATUS reclistStatusDefault = new RECLIST_LAUFZETTEL_STATUS("SELECT * FROM " + bibE2.cTO() + ".JT_LAUFZETTEL_STATUS WHERE NVL(ISDEFAULT,'N') = 'Y'");
		if (reclistStatusDefault.size() <= 0) {
			bibMSG.add_MESSAGE(new MyE2_Warning_Message("Es ist kein Standard-Status für den Laufzettel angegeben. Es kann kein Eintrag generiert werden!"));
			return true;
		}

		// Liste von aktiven Einträgen, die als Kadenz-Einträge auf diesen
		// Eintrag generiert wurden
		RECLIST_LAUFZETTEL_EINTRAG oRecListEintraege_KADENZ = new RECLIST_LAUFZETTEL_EINTRAG("ID_PARENT_KADENZ = " + IDWorkflowEntry + " AND NVL(GELOESCHT,'N') = 'N' ", "");

		// wenn eine Kadenz angegeben wurde, und der Status einer Meldung ist
		// so, dass er Meldepflichtig ist (in der Tabelle JT_LAUFZETTEL_STATUS
		// als trigger gesetzt)
		if (oRecEintrag_alt.get_KADENZ_NACH_ABSCHLUSS_cUF() == null || !reclistStatus.containsKey(oRecEintrag_alt.get_ID_LAUFZETTEL_STATUS_cUF())) {

			bRet = true;

			// möglicherweise gab es schon mal alte Einträge durch eine Kadenz
			// und den Statuswechsel, welcher wieder rückgängig gemacht wurde.
			// dann müssen auch die dadurch erzeugten Einträge neutralisiert
			// werden! (auf gelöscht setzen)
			if (oRecListEintraege_KADENZ.size() > 0) {
				for (RECORD_LAUFZETTEL_EINTRAG o : oRecListEintraege_KADENZ.values()) {
					o.set_NEW_VALUE_GELOESCHT("Y");
					String sBericht = o.get_BERICHT_cUF_NN("");
					o.set_NEW_VALUE_BERICHT(sBericht + System.getProperty("line.separator") + new MyString(" * automatisch gelöscht wegen Änderung des Aufgaben-Status oder der Kadenz * ").CTrans());
					vSql.add(o.get_SQL_UPDATE_STATEMENT(null, true));
				}

				// Speichern und beenden
				MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(vSql, true);
				bRet = mv.get_bIsOK();
			}

			return bRet;
		}

		// ermitteln des neuen Datums
		// an dem Die Aufgabe die Fälligkeit hat
		boolean bKadenzNachFaelligkeit =oRecEintrag_alt.get_KADENZ_NACH_FAELLIGKEIT_cUF_NN("N").equals("Y");
		String sDate = null;
		if (bKadenzNachFaelligkeit){
			sDate = oRecEintrag_alt.get_FAELLIG_AM_cUF();
		} else {
			sDate = oRecEintrag_alt.get_ABGESCHLOSSEN_AM_cUF();
		}
		
		GregorianCalendar calnew = new GregorianCalendar();
		if (sDate != null) {
			Date dt;
			try {
				dt = new SimpleDateFormat("yyyy-MM-dd").parse(sDate);
				calnew.setTime(dt);
			} catch (ParseException e) {
				// kein Datum geparst...
			}
		}
		calnew.add(Calendar.DAY_OF_YEAR, oRecEintrag_alt.get_KADENZ_NACH_ABSCHLUSS_bdValue(BigDecimal.ZERO).intValue());

		// dann schauen, ob der Kadenz-Eintrag schon gemacht war,
		// wenn ja, prüfen, ob die Kadenz und das Datum korrekt ausgefüllt
		// waren,
		// sonst korrigieren...
		if (oRecListEintraege_KADENZ.size() > 0) {
			boolean bOK = true;
			for (RECORD_LAUFZETTEL_EINTRAG o : oRecListEintraege_KADENZ.values()) {

				if (!o.get_FAELLIG_AM_cUF().equalsIgnoreCase(myDateHelper.FormatDateNormal(calnew.getTime()))
						|| !o.get_KADENZ_NACH_ABSCHLUSS_cUF().equalsIgnoreCase(oRecEintrag_alt.get_KADENZ_NACH_ABSCHLUSS_cUF())) {
					o.set_NEW_VALUE_GELOESCHT("Y");
					String sBericht = o.get_BERICHT_cUF_NN("");
					o.set_NEW_VALUE_BERICHT(sBericht + System.getProperty("line.separator") + new MyString(" * automatisch gelöscht wegen Änderung an Kadenz oder Fälligkeit * ").CTrans());
					vSql.add(o.get_SQL_UPDATE_STATEMENT(null, true));
					bOK = false;
				}
			}
			// wenn Fälligkeit und Kadenz noch gleich waren, ist bOK == true,
			// dann kann man hier abbrechen
			if (bOK) {
				return true;
			}
		}

		
		oRecNew = new RECORDNEW_LAUFZETTEL_EINTRAG();
		oRecNew.set_NEW_VALUE_ANGELEGT_AM(new GregorianCalendar());

		oRecNew.set_NEW_VALUE_AUFGABE(oRecEintrag_alt.get_AUFGABE_cUF());
		oRecNew.set_NEW_VALUE_ID_EINTRAG_PARENT(oRecEintrag_alt.get_ID_EINTRAG_PARENT_cUF());
		oRecNew.set_NEW_VALUE_ID_LAUFZETTEL(oRecEintrag_alt.get_ID_LAUFZETTEL_cUF());
		oRecNew.set_NEW_VALUE_ID_LAUFZETTEL_PRIO(oRecEintrag_alt.get_ID_LAUFZETTEL_PRIO_cUF());
		oRecNew.set_NEW_VALUE_ID_USER_BEARBEITER(oRecEintrag_alt.get_ID_USER_BEARBEITER_cUF());
		oRecNew.set_NEW_VALUE_ID_USER_BESITZER(oRecEintrag_alt.get_ID_USER_BESITZER_cUF());
		oRecNew.set_NEW_VALUE_KADENZ_NACH_ABSCHLUSS(oRecEintrag_alt.get_KADENZ_NACH_ABSCHLUSS_bdValue(null));
		oRecNew.set_NEW_VALUE_KADENZ_NACH_FAELLIGKEIT(oRecEintrag_alt.get_KADENZ_NACH_FAELLIGKEIT_cUF());
		oRecNew.set_NEW_VALUE_PRIVAT(oRecEintrag_alt.get_PRIVAT_cUF());
		oRecNew.set_NEW_VALUE_ID_PARENT_KADENZ(oRecEintrag_alt.get_ID_LAUFZETTEL_EINTRAG_cUF());
		oRecNew.set_NEW_VALUE_FAELLIG_AM(calnew);
		oRecNew.set_NEW_VALUE_SEND_NACHRICHT("Y");

		oRecNew.set_NEW_VALUE_ID_LAUFZETTEL_STATUS(reclistStatusDefault.get(0).get_ID_LAUFZETTEL_STATUS_cUF());

		vSql.add(oRecNew.get_InsertSQLStatementWith_Id_Field(false, true));

		// jetzt noch mal schauen, ob es eine Verbindung zwischen Laufzettel und
		// dem anderen Objekt gibt, wenn ja, dann diesen auch kopieren
		RECLIST_MODUL_CONNECT list = new RECLIST_MODUL_CONNECT(RECORD_MODUL_CONNECT.FIELD__ID_QUELLE + " = " + oRecEintrag_alt.get_ID_LAUFZETTEL_EINTRAG_cUF() + " AND "
				+ RECORD_MODUL_CONNECT.FIELD__QUELLE + " = '" + E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_EINTRAG_LISTE + "'", "");
		// Referenzen weiter geben an die antworten
		if (list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				RECORD_MODUL_CONNECT rec = list.get(i);
				MySqlStatementBuilder build = rec.get_StatementBuilderFilledWithActualValues();
				build.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_QUELLE, "SEQ_LAUFZETTEL_EINTRAG.CURRVAL", false);
				build.addSQL_Paar(RECORD_MODUL_CONNECT.FIELD__ID_MODUL_CONNECT, "SEQ_MODUL_CONNECT.NEXTVAL", false);
				vSql.add(build.get_CompleteInsertString("JT_MODUL_CONNECT", bibE2.cTO()));
			}
		}

		//
		// Anhänge weitergeben falls welche an der Aufgabe hängen
		//
		RECLIST_ARCHIVMEDIEN_ext rlArchivmedienNachricht = new RECLIST_ARCHIVMEDIEN_ext(_DB.LAUFZETTEL_EINTRAG, oRecEintrag_alt.get_ID_LAUFZETTEL_EINTRAG_cUF(), null,null);
		for(RECORD_ARCHIVMEDIEN recArchiv :rlArchivmedienNachricht.values()){
			RECORD_ARCHIVMEDIEN_ext oArchiv = new RECORD_ARCHIVMEDIEN_ext(recArchiv);
			String sSql = oArchiv.connectToAdditionalEntry(_DB.LAUFZETTEL_EINTRAG, "SEQ_" + LAUFZETTEL_EINTRAG.baseTabName()+".CURRVAL");
			vSql.add(sSql);
		}
		
		
		// starten der Transaktion
		MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(vSql, true);
		bRet = mv.get_bIsOK();
		
		
		
		// Aktuell anliegende Meldungen verschicken
		REMINDER_Handler_Laufzettel_Eintrag reminders = new REMINDER_Handler_Laufzettel_Eintrag();
		reminders.updateReminders();

		return bRet;

	}

	/**
	 * schickt eine Erledigt-Message an den Besitzer des Laufzettels, des
	 * Laufzetteleintrags und den Supervisor des Laufzettels
	 * 
	 * @param IDWorkflow
	 * @param IDWorkflowEntry
	 * @throws myException
	 */
	public void sendMessagesIfStatusChanged(String IDWorkflow, String IDWorkflowEntry) throws myException {
		HashSet<String> hsKeys = new HashSet<String>();
		m_idWorkflowCreated = null;

		RECORD_LAUFZETTEL_EINTRAG oRecLaufzettelEintrag = new RECORD_LAUFZETTEL_EINTRAG(IDWorkflowEntry);

		// Status prüfen:
		// es muss eine Meldung gewünscht sein...
		RECORD_LAUFZETTEL_STATUS recStatus = new RECORD_LAUFZETTEL_STATUS(oRecLaufzettelEintrag.get_ID_LAUFZETTEL_STATUS_cUF());
		if (!recStatus.get_TRIGGER_MELDUNG_cUF_NN("N").equals("Y")) {
			return;
		}

		// den Laufzettel
		RECORD_LAUFZETTEL oRecLaufzettel = new RECORD_LAUFZETTEL(IDWorkflow);

		// Schnittmenge aller User
		if (oRecLaufzettelEintrag.get_ID_USER_BESITZER_cUF() != null) {
			hsKeys.add(oRecLaufzettelEintrag.get_ID_USER_BESITZER_cUF());
		}
		if (oRecLaufzettel.get_ID_USER_BESITZER_cUF() != null) {
			hsKeys.add(oRecLaufzettel.get_ID_USER_BESITZER_cUF());
		}
		if (oRecLaufzettel.get_ID_USER_SUPERVISOR_cUF() != null) {
			hsKeys.add(oRecLaufzettel.get_ID_USER_SUPERVISOR_cUF());
		}
		
		// 2015-11-13 Laut RL soll keine Meldung an denjenigen Verschickt werden, der den Nachrichtenstatus ändert!
		if (hsKeys.contains(bibALL.get_ID_USER())){
			hsKeys.remove(bibALL.get_ID_USER());
		}
		// 
		
		
		Vector<String> vUser = new Vector<String>();
		vUser.addAll(hsKeys);

		// prüfen, ob schon mal für den Zustand und das Datum schon eine
		// Nachricht verschickt wurde.
		String sKeyReminder =  "LAUFZETTEL_EINTRAG#" + oRecLaufzettelEintrag.get_ID_LAUFZETTEL_EINTRAG_cUF() + "#" + recStatus.get_ID_LAUFZETTEL_STATUS_cUF() + "#" + oRecLaufzettelEintrag.get_ABGESCHLOSSEN_AM_cUF_NN("-");
		RECLIST_NACHRICHT oNachrichten = new RECLIST_NACHRICHT("SELECT * from " + bibE2.cTO() + ".JT_NACHRICHT WHERE WV_KENNUNG = '" + sKeyReminder + "'");
		if (oNachrichten.size() > 0) {
			return;
		}

		String sMessageBodyForStatusMsg = "";

		WF_MessageHelper oHelp = new WF_MessageHelper(oRecLaufzettel, oRecLaufzettelEintrag);
		sMessageBodyForStatusMsg = oHelp.getMessageText(true);

		// generieren der Meldung
		MESSAGE_Entry msgEntry = new MESSAGE_Entry()
									.set_Titel(new MyString("Laufzettel").CTrans())
									.set_Nachricht(	sMessageBodyForStatusMsg + "\n\n" + new MyString("Die Aufgabe wurde in den Status ").CTrans() + recStatus.get_STATUS_cUF() + new MyString(" versetzt!").CTrans())
									.set_vIdEmpfaenger(	vUser)
									.set_Sofortanzeige(false)
									.set_DtAnzeigeAb(new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
									.set_KeyReminder(sKeyReminder)
									.add_Target(new MESSAGE_Entry_Target(oRecLaufzettel.get_ID_LAUFZETTEL_cUF(), E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE, null, null))
									.set_Kategorie(E2_MODULNAMES.NAME_WORKFLOW_LAUFZETTEL_LISTE)
									.set_SendEmail(bib_Settigs_Mandant.get_Workflow_Send_Mail_on_Status_Change())
									;

		new MESSAGE_Handler().saveMessage(msgEntry);

		
		
	}

	
	/*
	 * erzeugt den Text für die Nachricht
	 */
	private String getMessageBody(RECORD_LAUFZETTEL_EINTRAG oRecLaufzettelEintrag, RECORD_LAUFZETTEL oRecLaufzettel) throws myException {
		String sRet = "";

		WF_MessageHelper oHelp = new WF_MessageHelper(oRecLaufzettel, oRecLaufzettelEintrag);
		sRet = oHelp.getMessageText(true);

		return sRet;
	}
	
	
	
	/**
	 * 
	 * @author manfred
	 * @date 17.10.2017
	 *
	 * @param idWorkflow
	 * @param newDeadlineISO / Datum der neuen Terminierung im Format "YYYY-MM-DD"
	 * @return
	 */
	public MyE2_MessageVector copyWorkflow(String idLaufzettel, String newDeadlineISO, String sNewDescription, boolean copyAnhang) throws myException{
		MyE2_MessageVector mv = new MyE2_MessageVector();
	
		RecList20 rlStatus = new RecList20(_TAB.laufzettel_status)._fill(LAUFZETTEL_STATUS.isdefault.fieldName() +  " = 'Y'", "");
		if (rlStatus.size()== 0){
			throw new myException("copyWorkflow::Kein Standard-Laufzettelstatus ermittelbar.");
		}

		Vector<String> vSqlAnhaenge = new Vector<>();
		

		Rec20 recStatus = rlStatus.get(0);
		String statusNew = recStatus.get_key_value();
		
		
		// den zu kopierenden Laufzettel ermitteln
		Rec20 rOld = new Rec20(_TAB.laufzettel)._fill_id(idLaufzettel);

		// Copy anlegen und Werte korrigieren
		Rec20 rNew = rOld.getRec20CopyAsNew(bibMSG.MV(), rOld.getAutoFieldsStd());
		
		// beschreibung überschreiben, falls vorhanden, sonst wird die alte genommen
		if (S.isFull(sNewDescription)){
			rNew.set_NewValueForDatabase(LAUFZETTEL.text.fieldName(), sNewDescription);
		}
		rNew.set_NewValueForDatabase(LAUFZETTEL.id_laufzettel_status.fieldName(), statusNew );
		rNew.set_NewValueForDatabase(LAUFZETTEL.abgeschlossen_am.fieldName(), (String)null);
		rNew.set_NewValueForDatabase(LAUFZETTEL.id_user_abgeschlossen_von.fieldName(), (String)null);
		rNew._put_raw_value(LAUFZETTEL.angelegt_am, "sysdate", false);
		rNew._put_raw_value(LAUFZETTEL.id_laufzettel, LAUFZETTEL._tab().seq_nextval(), false);
		
		// Laufzettel abspeichen
		rNew._SAVE(false, mv);
		String idLaufzettelNew = rNew.get_rec_after_save_new().get_key_value();
		
		
		// prüfen, ob es Anhänge am Eintrag gibt, wenn ja, evtl. kopieren
		if (copyAnhang){
			// jeden Anhang den wir zu diesem Eintrag finden können auch an den neuen Eintrag hängen
			RecList20 rlArchiv = new RecList20(_TAB.archivmedien)._fill("TABLENAME = '" + LAUFZETTEL.baseTabName() + "' AND ID_TABLE = " + idLaufzettel, "");
			if (rlArchiv.size() > 0){
				for (Rec20 recArchivmedien: rlArchiv){
					RECORD_ARCHIVMEDIEN_ext re = new RECORD_ARCHIVMEDIEN_ext(recArchivmedien.get_key_value());
					vSqlAnhaenge.add( re.connectToAdditionalEntry(LAUFZETTEL.baseTabName(), idLaufzettelNew) ) ;
				}
			}
			
		}

		
		
		// Liste der Laufzettel-Einträge ermitteln
		RecList20 rlEintrag = rOld.get_down_reclist20(LAUFZETTEL_EINTRAG.id_laufzettel, "NVL(" + LAUFZETTEL_EINTRAG.geloescht.fieldName() + ",'N')='N'", "");
		
		// Kopieren der Laufzettel-Einträge und merken der alten und neuen ID
		HashMap<String, String> hmOldNewKeys = new HashMap<>();  // alte / neue laufzettel_eintrag_id's der jeweiligen kopierten Sätze
		HashMap<String, String> hmParentKeys = new HashMap<>();  // oldid / oldparent-ID Einträge der alten Sätze
		
		// Hashmap für die neu gespeicherten Rec20 Objekte
 		HashMap<String, Rec20> hmCopies = new HashMap<>();

 		
 		
		for (Rec20 recEintragOld: rlEintrag){
				
				String idOld = recEintragOld.get_key_value();
				String idParentOld = recEintragOld.get_ufs_dbVal(LAUFZETTEL_EINTRAG.id_eintrag_parent);
				
				Rec20 rEnew = recEintragOld.getRec20CopyAsNew(bibMSG.MV(), recEintragOld.getAutoFieldsStd());
				
				rEnew._put_raw_value(LAUFZETTEL_EINTRAG.id_laufzettel, LAUFZETTEL._tab().seq_currval(), false);
				rEnew.set_NewValueForDatabase(LAUFZETTEL_EINTRAG.id_user_abgeschlossen_von.fieldName(), (String)null);
				rEnew.set_NewValueForDatabase(LAUFZETTEL_EINTRAG.abgeschlossen_am.fieldName(), (String)null);
				rEnew.set_NewValueForDatabase(LAUFZETTEL_EINTRAG.id_parent_kadenz.fieldName(), (String)null);
				rEnew.set_NewValueForDatabase(LAUFZETTEL_EINTRAG.bericht.fieldName(),(String)null);
				rEnew.set_NewValueForDatabase(LAUFZETTEL_EINTRAG.nachricht_sent.fieldName(), (String)null);
				rEnew._put_raw_value(LAUFZETTEL_EINTRAG.angelegt_am, "sysdate", false);

				if (S.isFull(newDeadlineISO)){
					rEnew.set_NewValueForDatabase(LAUFZETTEL_EINTRAG.faellig_am.fieldName(), new MyDate( newDeadlineISO, MyDate.DATE_FORMAT.YYYY_MM_DD_DASH).get_cDateStandardFormat() ) ;
				} else {
					rEnew.set_NewValueForDatabase(LAUFZETTEL_EINTRAG.faellig_am.fieldName(), (String)null ) ;
					rEnew.set_NewValueForDatabase(LAUFZETTEL_EINTRAG.send_nachricht.fieldName(), (String)null ) ;
				}
				
				
				rEnew.set_NewValueForDatabase(LAUFZETTEL_EINTRAG.id_laufzettel_status.fieldName(), statusNew);
				// parent erst mal auf null setzen
				rEnew.set_NewValueForDatabase(LAUFZETTEL_EINTRAG.id_eintrag_parent.fieldName(), (String)null);

				// neuen Eintrag sichern
				rEnew._SAVE(false, mv);

				// keys merken
				String idNew = rEnew.get_rec_after_save_new().get_key_value();
				
				
				// prüfen, ob es Anhänge am Eintrag gibt, wenn ja, evtl. kopieren
				if (copyAnhang){
					// jeden Anhang den wir zu diesem Eintrag finden können auch an den neuen Eintrag hängen
					RecList20 rlArchiv = new RecList20(_TAB.archivmedien)._fill("TABLENAME = '" + LAUFZETTEL_EINTRAG.baseTabName() + "' AND ID_TABLE = " + idOld, "");
					if (rlArchiv.size() > 0){
						for (Rec20 recArchivmedien: rlArchiv){
							RECORD_ARCHIVMEDIEN_ext re = new RECORD_ARCHIVMEDIEN_ext(recArchivmedien.get_key_value());
							vSqlAnhaenge.add (re.connectToAdditionalEntry(LAUFZETTEL_EINTRAG.baseTabName(), idNew) );
						}
					}
					
				}
				
				// interne Hashmaps füllen
				hmCopies.put(idNew,rEnew.get_rec_after_save_new());
				hmOldNewKeys.put(idOld, idNew);
				if (idParentOld != null){
					hmParentKeys.put(idOld, idParentOld);
				}
		}

		
		// Hierarchie setzen, falls es eine gibt
		if (!hmParentKeys.isEmpty()){
			// lesen der records
			for(Entry<String, String> set :  hmParentKeys.entrySet()){
				// den Satz holen, der die Basis ist
				Rec20 rec = hmCopies.get(hmOldNewKeys.get(set.getKey()));
				// die Parentid setzen
				rec.set_NewValueForDatabase(LAUFZETTEL_EINTRAG.id_eintrag_parent.fieldName(), hmOldNewKeys.get(set.getValue()));
				rec._SAVE(false, mv);
			}
		}
		
		
		// Sql für die Anhänge ausführen
		if (vSqlAnhaenge.size() > 0){
			mv._add( bibDB.ExecMultiSQLVector(vSqlAnhaenge, false) );
		}
		
		if (mv.get_bIsOK()){
			bibDB.Commit();
		} else {
			bibDB.Rollback();
		}
		
		
		return mv;
	}
	
	

	
	
	
	
}