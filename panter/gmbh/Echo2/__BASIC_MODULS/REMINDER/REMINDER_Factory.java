/**
 * panter.gmbh.Echo2.__BASIC_MODULS.REMINDER
 * @author manfred
 * @date 31.03.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER;

import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message_OT;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_REMINDER_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_DEF;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author manfred
 * @date 31.03.2016
 *
 */
public class REMINDER_Factory {

	REMINDER_Entry_Data         m_Reminder_data = new REMINDER_Entry_Data();
	
	
	public REMINDER_Factory(){
	}
	
	
	
	public REMINDER_Factory(E2_NavigationList oNaviList, MODUL modul, boolean bErinnerungBeiAnlage) {
		
		Vector<String> vIDs_Selected_Unformated = null;
		vIDs_Selected_Unformated = oNaviList.get_vSelectedIDs_Unformated();
		
		if (vIDs_Selected_Unformated.size() != 1)
		{
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message_OT(new MyE2_String("Es muss genau ein Datensatz ausgewählt sein um eine Erinnerung anzulegen!").CTrans()));
			return;
		}
		
		// todo: Edit-Dialog der Reminder-Def starten...und die Daten übergeben
		m_Reminder_data.set_BaseTable(oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cMAIN_TABLE());
		m_Reminder_data.set_ID_Table(vIDs_Selected_Unformated.get(0));
		m_Reminder_data.set_modul_ziel(modul);
		m_Reminder_data.set_ID_UserAngelegt(bibALL.get_ID_USER());
		m_Reminder_data.set_SendMessageOnCreation(bErinnerungBeiAnlage);
		m_Reminder_data.set_intervall(1L);
		m_Reminder_data.set_DtAnzeigeAb(bibALL.get_cDateNOW_ISO());
		
		// default-Reminder-User ist der aufrufende Bearbeiter.
		REMINDER_USER_Entry_Data o = new REMINDER_USER_Entry_Data()
					.set_allow_close(true)
					.set_allow_edit(true)
					.set_id_user(bibALL.get_ID_USER());
		m_Reminder_data.add_UserAdditional(o);
		
	}
	
	
	
	public REMINDER_Factory( 
			String 		table_name,
			String 		id_table,
			MODUL    	ModulZiel,
			String      Titel,
			String      Meldung,
			Long	    IntervallTage,
			String      Datum_Reminder_Beginn_ISO,
			boolean 	SendMail,
			boolean   	bSendeNachrichtBeiErstellung,
			String		ReminderKennung,
			Vector<REMINDER_USER_Entry_Data> vUserToRemind
		){

		m_Reminder_data.set_BaseTable(table_name)
			.set_ID_Table(id_table)
			.set_modul_ziel(ModulZiel)
			.set_ID_UserAngelegt(bibALL.get_ID_USER())
			.set_SendMessageOnCreation(bSendeNachrichtBeiErstellung)
			.set_intervall(IntervallTage)
			.set_DtAnzeigeAb(Datum_Reminder_Beginn_ISO)
			.set_Titel(Titel)
			.set_Nachricht(Meldung)
			.set_Reminder_kennung(ReminderKennung);
		
		if (vUserToRemind!=null){
			m_Reminder_data.add_UserAdditional(vUserToRemind);
		}
	}
	
	
	public REMINDER_Entry_Data get_ReminderData(){
		return m_Reminder_data;
	}
	
	

	/**
	 * fügt einen User zur Liste der Erinnerungen dazu, der abschliessen, aber nichtbearbeiten darf
	 * @author manfred
	 * @date 01.04.2016
	 *
	 * @param idUser
	 * @return
	 */
	public REMINDER_Factory add_AdditionalUserID(String idUser){
		m_Reminder_data.add_UserAdditional(idUser);
		return this;
	}
	
	
	/**
	 * fügt einen User zur Liste der Erinnerungen dazu
	 * @author manfred
	 * @date 01.04.2016
	 *
	 * @param idUser
	 * @return
	 */
	public REMINDER_Factory add_AdditionalUserID(String idUser, boolean bAllowEdit, boolean bAllowClose, boolean bSendEmail, boolean bSofortanzeige){
		m_Reminder_data.add_UserAdditional(idUser,bAllowEdit, bAllowClose, bSendEmail,bSofortanzeige);
		return this;
	}
	
	
	
	public void openReminderEditMask(){
		// todo: Implement?
	}
	
	
	
	/**
	 * fügt den Reminder ein und gibt den erzeugten Record zurück.
	 * 
	 * @author manfred
	 * @date 01.04.2016
	 *
	 * @return
	 * @throws myException
	 */
	public boolean saveReminderDef() throws myException{
		boolean bRet = false;
		
		RECORDNEW_REMINDER_DEF rec_reminder_def = new RECORDNEW_REMINDER_DEF();
		
		Vector<String> vSql = new Vector<>();
		
		// User der den Satz angelegt hat...
		String idUserAngelegt = m_Reminder_data.get_ID_UserAngelegt()!= null ?m_Reminder_data.get_ID_UserAngelegt() : bibALL.get_ID_USER();
		// falls kein Datum angegeben wurde, wird das erinnerungsdatum auf "now" gesetzt
		if (m_Reminder_data.get_DtAnzeigeAb() == null){
			m_Reminder_data.set_DtAnzeigeAb(bibALL.get_cDateNOW_ISO());
		}

		rec_reminder_def.set_NEW_VALUE_ID_USER_ANGELEGT(idUserAngelegt);
		rec_reminder_def.set_NEW_VALUE_TABLE_NAME(m_Reminder_data.get_BaseTable());
		rec_reminder_def.set_NEW_VALUE_ID_TABLE(m_Reminder_data.get_ID_Table());
		rec_reminder_def.set_NEW_VALUE_MODUL_CONNECT_ZIEL(m_Reminder_data.get_modul_ziel().get_callKey());
		rec_reminder_def.set_NEW_VALUE_MODUL_CONNECT_TYPE(m_Reminder_data.get_modul_ziel().is_LIST()? 1 : 2 );
		rec_reminder_def.set_NEW_VALUE_ERINNERUNG_BEI_ANLAGE(m_Reminder_data.get_SendMessageOnCreation() ? "Y" :"N");
		rec_reminder_def.set_NEW_VALUE_INTERVALL(m_Reminder_data.get_intervall());
		rec_reminder_def.set_NEW_VALUE_REMINDER_KENNUNG(m_Reminder_data.get_Reminder_kennung());
		rec_reminder_def.set_NEW_VALUE_REMINDER_HEADING(m_Reminder_data.get_Titel());
		rec_reminder_def.set_NEW_VALUE_REMINDER_TEXT(m_Reminder_data.get_Nachricht());
		rec_reminder_def.set_NEW_VALUE_SEND_NACHRICHT("Y");
		rec_reminder_def.add_raw_val(REMINDER_DEF.erinnerung_ab, "to_date('" + m_Reminder_data.get_DtAnzeigeAb() + "','yyyy-mm-dd')");
		
		
		// Statement holen und implizit die ID generieren 
		String sSQL = rec_reminder_def.get_InsertSQLStatementWith_Id_Field(false, false);
		vSql.add(sSQL);
		
		// die generierte ID holen
		String idReminderDef = rec_reminder_def.get_cLastSEQ_NUMBER();
		
		// der User der angelegt hat, soll immer den reminder bekommen.
		// jeder User wird nur einmal erfasst.
		if(m_Reminder_data.get_htUserAdditional().size() == 0){
			m_Reminder_data.add_UserAdditional(idUserAngelegt, true, true, true, false );
		}
		
		if (m_Reminder_data.get_htUserAdditional()!= null ){
			for (REMINDER_USER_Entry_Data user: m_Reminder_data.get_htUserAdditional().values()){
				RECORDNEW_REMINDER_USER rec_reminder_user = new RECORDNEW_REMINDER_USER();
				rec_reminder_user.set_NEW_VALUE_ID_USER(user.get_id_user());
				rec_reminder_user.set_NEW_VALUE_ALLOW_CHANGE(user.is_allow_edit() ? "Y" : "N");
				rec_reminder_user.set_NEW_VALUE_ALLOW_CLOSE(user.is_allow_close() ? "Y" : "N");
				rec_reminder_user.set_NEW_VALUE_SEND_MAIL(user.is_send_mail() ? "Y" : "N");
				rec_reminder_user.set_NEW_VALUE_SOFORTANZEIGE(user.is_sofortanzeige() ? "Y" : "N");
				
				rec_reminder_user.set_NEW_VALUE_ID_REMINDER_DEF(idReminderDef);
				vSql.add(rec_reminder_user.get_InsertSQLStatementWith_Id_Field(true, false));
			}
		}

		MyE2_MessageVector mv = bibDB.ExecMultiSQLVector(vSql, true);

		bRet = mv.get_bIsOK();
		if (bRet){
			// schauen, ob die Nachrict initial gesendet werden soll, 
			if (m_Reminder_data.get_SendMessageOnCreation()){
				
				RECLIST_REMINDER_DEF_Ext reclist = new RECLIST_REMINDER_DEF_Ext(idReminderDef);
				if (reclist != null && reclist.size() == 1){
				RECORD_REMINDER_DEF rec = reclist.get(0);
//				REMINDER_List_Entry entry = new REMINDER_List_Entry(rec);
//				entry.doRemind();
				doSendReminderNachricht(rec);
				}
			}
		}
		
		return bRet;
		
	}


	/**
	 * Sucht die ReminderDef anhand der Nummer 
	 * @author manfred
	 * @date 13.04.2016
	 *
	 * @param idReminderDef
	 */
	public void doCheckAndSendReminderOnCreation(String idReminderDef){
		RECLIST_REMINDER_DEF_Ext reclist;
		try {
			reclist = new RECLIST_REMINDER_DEF_Ext(idReminderDef);
			if (reclist != null && reclist.size() == 1){
				RECORD_REMINDER_DEF rec = reclist.get(0);
				if (rec.get_ERINNERUNG_BEI_ANLAGE_cUF_NN("N").equals("Y")){
					doSendReminderNachricht(rec);
				}
			}
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Erinnerungsmeldung konnte nicht ermittelt werden.")));
		}
	}

	

	/**
	 * Verschickt die Meldung zur Erinnerungsmeldungs-Definition
	 * @author manfred
	 * @date 13.04.2016
	 *
	 * @param recReminder
	 */
	private void doSendReminderNachricht(RECORD_REMINDER_DEF recReminder){
		try {
			REMINDER_List_Entry entry = new REMINDER_List_Entry(recReminder);
			entry.doRemind();
		} catch (myException e) {
			bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyString("Erinnerungsmeldung konnte nicht erzeugt werden.")));
		}
		
	}
	
	
	
	
}
