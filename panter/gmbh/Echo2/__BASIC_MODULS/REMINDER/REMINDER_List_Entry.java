/**
 * panter.gmbh.Echo2.__BASIC_MODULS.REMINDER
 * @author manfred
 * @date 21.03.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.FileWithSendName;
import panter.gmbh.Echo2.MAIL_AND_REPORT.REPORTING.JasperFileDef;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Handler;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.NACHRICHT;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_LOG;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_REMINDER_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_REMINDER_LOG;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_USER;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.LOGIC.CONVERT_FROM_FUHRE.bibDate;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECLIST_ARCHIVMEDIEN_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ARCHIVMEDIEN_ext;

/**
 * @author manfred
 * @date 21.03.2016
 *
 */
public class REMINDER_List_Entry {
	
	private REMINDER_Entry_Data     _data = new REMINDER_Entry_Data();
	

	/**
	 * @author manfred
	 * @date 22.03.2016
	 *
	 */
	public REMINDER_List_Entry() {
	}
		
	
	/**
	 * Initialisiert den Entry mit den Werten aus dem Objekt von Reminder_def für die Anlage der Logs
	 * @author manfred
	 * @date 22.03.2016
	 *
	 * @param _rec_Reminder_def
	 * @throws myException 
	 */
	public REMINDER_List_Entry(RECORD_REMINDER_DEF _rec_Reminder_def) throws myException {
		this();
		
		_data._rec_Reminder_def = _rec_Reminder_def;
		_data.set_Titel(_rec_Reminder_def.get_REMINDER_HEADING_cUF());
		_data.set_Nachricht(_rec_Reminder_def.get_REMINDER_TEXT_cUF());
		_data.set_DtAnzeigeAb(_rec_Reminder_def.get_ERINNERUNG_AB_cUF());
		_data.set_SendMessageOnCreation(_rec_Reminder_def.get_ERINNERUNG_BEI_ANLAGE_cUF_NN("N").equals("Y"));
		_data.set_ID_UserAngelegt(_rec_Reminder_def.get_ID_USER_ANGELEGT_cUF());
		
		String sKey = _rec_Reminder_def.get_UnFormatedValue(RECLIST_REMINDER_DEF_Ext.colName_KeyReminder);
		_data.set_KeyReminder(sKey);
		
		
		// ziele des Reminders angeben, falls der Reminder von einer Tabelle kommt.
		if (_rec_Reminder_def.get_MODUL_CONNECT_ZIEL_cUF() != null){
			
			String sModul = _rec_Reminder_def.get_MODUL_CONNECT_ZIEL_cUF();
			MODUL  oModul = E2_MODULNAME_ENUM.find_Corresponding_Enum(sModul);
			String sButtonText = new MyString("Springe zum zugehörigen Modul").CTrans();
			if (oModul != null){
				sButtonText = new MyString("Springe zu Modul ").CTrans() +  oModul.get_userInfo();
			}  
			
			MESSAGE_Entry_Target t = new MESSAGE_Entry_Target()
					.set_ModulTarget(_rec_Reminder_def.get_MODUL_CONNECT_ZIEL_cUF())
					.set_IDTarget(_rec_Reminder_def.get_ID_TABLE_cUF())
					.set_Target_Button_Text(sButtonText)
					;
			
			_data.get_vTargets().add(t);
		}
		
		
		// User anhängen
		try {
			RECLIST_REMINDER_USER m_reminder_users = _rec_Reminder_def.get_DOWN_RECORD_LIST_REMINDER_USER_id_reminder_def();
			_data.set_vReminderUser( new Vector<RECORD_REMINDER_USER>(m_reminder_users.values())) ;
		} catch (myException e) {
			e.printStackTrace();
		}
		
		
	}

	
	
	/**
	 * Führt den Reminder aus:
	 * -> Meldung(en) generieren
	 * -> Logeinträge schreiben
	 * 
	 * @author manfred
	 * @date 31.03.2016
	 *
	 * @throws myException
	 */
	public void doRemind() throws myException{
		boolean bRet = false;
		String sSQLLog = "";
		

		MESSAGE_Handler  oHandler = new MESSAGE_Handler();
		MESSAGE_Entry msgEntry = new MESSAGE_Entry();
		
		// default sprung von der Nachricht zum Reminder
		MESSAGE_Entry_Target target = new MESSAGE_Entry_Target()
		    		.set_IDTarget(_data.get_rec_Reminder_def().get_ID_REMINDER_DEF_cUF())
		    		.set_ModulTarget(E2_MODULNAME_ENUM.MODUL.REMINDER_DEF_LIST.toString())
		    		.set_Target_Button_Text(new MyString("Springe zur Erinnerungsmeldung").CTrans())
		    		;
		
		_data.get_vTargets().add(target);
		
		// Titel zusammensetzen
		String sMessageTitel = "Erinnerung: " + _data.get_Titel();
		
		
		// Text zusammensetzen
		String sMessageText = "Erinnerungsmeldung ID: " + _data.get_rec_Reminder_def().get_ID_REMINDER_DEF_cUF() + "\n";

		Date dtBegin = bibDate.String2Date(_data.get_DtAnzeigeAb());
		GregorianCalendar  cal = new GregorianCalendar();
		cal.setTime(dtBegin);
		GregorianCalendar  calNow = new GregorianCalendar();

		int diff = (int)myDateHelper.get_DayDifference_Date2_MINUS_Date1(cal , calNow);
		if (diff > 0){
			sMessageText  += "Seit " + Integer.toString(diff) + " Tagen fällig!"  + "\n";
		} else if (diff < 0) {
			sMessageText  += "In " + Integer.toString(Math.abs(diff)) + " Tagen fällig!"  + "\n";
		} else {
			sMessageText  += "Heute fällig!"  + "\n";
		}
		sMessageText +=  "------------------------------------------------------------------ \n\n" ;
		sMessageText += _data.get_Nachricht() == null ? "- Kein Meldungstext -" : _data.get_Nachricht();
		
		if (sMessageText.length() >  NACHRICHT.nachricht.fieldTextLength() ){
			sMessageText = sMessageText.substring(0, NACHRICHT.nachricht.fieldTextLength()-1);
		}
		if (sMessageTitel.length() >  NACHRICHT.titel.fieldTextLength() ){
			sMessageTitel = sMessageTitel.substring(0, NACHRICHT.titel.fieldTextLength()-1);
		}
		
		
		NACHRICHT.titel.fieldTextLength();
		// Meldungsrumpf vorbereiten
		msgEntry.set_Titel(sMessageTitel)
					.set_Nachricht(sMessageText)
					.set_KeyReminder(_data.get_KeyReminder())
					.set_vTargets(_data.get_vTargets())
					.set_DtAnzeigeAb(bibALL.get_cDateNOW_ISO())
					.set_Kategorie(MODUL.REMINDER_DEF_LIST.get_callKey())
					.set_IDUserSender(_data.get_ID_UserAngelegt())
					;
			
		// 
		// Alle Archivmedien suchen, die an diesem Reminder hängen 
		//
		RECLIST_ARCHIVMEDIEN_ext rlArchivmedienNachricht = new RECLIST_ARCHIVMEDIEN_ext(_DB.REMINDER_DEF, _data._rec_Reminder_def.get_ID_REMINDER_DEF_cUF(), null,null);
		msgEntry.get_vArchivmedien().addAll(rlArchivmedienNachricht.values());
		
		
		// Meldung für jeden einzelnen User verschicken, da unterschiedliche Zustellungen möglich sind
		try {
			for(RECORD_REMINDER_USER user : _data.get_vReminderUser()){
				String sIDUser = user.get_ID_USER_cUF();
					
				if (sIDUser != null){
					sSQLLog = generateSQLforLog(	_data.get_rec_Reminder_def().get_ID_REMINDER_DEF_cUF(),
														sIDUser, 
														msgEntry.get_KeyReminder());
					bRet = bibDB.ExecSQL(sSQLLog, false);
					
					if (bRet){
						// alte Empfänger löschen
						msgEntry.get_vIdEmpfaenger().clear();
						
						msgEntry.add_idEmpfaenger(sIDUser);
						msgEntry.set_SendEmail(user.is_SEND_MAIL_YES());
						msgEntry.set_Sofortanzeige(user.is_SOFORTANZEIGE_YES());

						oHandler.saveMessage(msgEntry);
					}
				}
			}
		} catch (myException e) {
			bibMSG.add_MESSAGE(e);
		}
		
	}

	
	/**
	 * Generiert das SQL-Statement für das REMINDER_LOG
	 * @author manfred
	 * @date 22.03.2016
	 *
	 * @param recUser
	 * @param sKeyReminder
	 * @throws myException
	 */
	private String generateSQLforLog(String idReminderDef, String idReminderUser, String sKeyReminder) throws myException{
		String sRet = null;
		RECORDNEW_REMINDER_LOG oRecNew = new RECORDNEW_REMINDER_LOG();

		oRecNew.add_raw_val(REMINDER_LOG.date_sent, "sysdate");
		oRecNew.set_NEW_VALUE_ID_REMINDER_DEF(idReminderDef);
		oRecNew.set_NEW_VALUE_ID_USER(idReminderUser);
		oRecNew.set_NEW_VALUE_REMINDER_KEY(sKeyReminder);
				
		sRet = oRecNew.get_InsertSQLStatementWith_Id_Field(true, true);
		return sRet;
	}



	
	
}
