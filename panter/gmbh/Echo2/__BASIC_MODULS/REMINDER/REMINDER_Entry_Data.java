/**
 * panter.gmbh.Echo2.__BASIC_MODULS.REMINDER
 * @author manfred
 * @date 31.03.2016
 * 
 */
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER;

import java.util.Hashtable;
import java.util.Vector;

import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.MESSAGE_Entry_Target;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.RECLIST_REMINDER_DEF_Ext.ENUM_REMINDER_WIEDERHOLUNG;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_USER;

/**
 * Allegemeine Daten-Klasse für reminders die neu angelegt werden,
 * oder aber aus der TAbelle REMINDER_DEF gelesen werden.
 * Bei letzterem ist _rec_Reminder_def != null
 * 
 * @author manfred
 * @date 31.03.2016
 *
 */
public class REMINDER_Entry_Data {

	
	// Allgemein
	String 							_Titel = null;
	String 							_Nachricht = null; 
	String							_BaseTable = null;
	String 							_ID_Table = null;

	String							_ID_UserAngelegt = null;
	
	MODUL							_modul_ziel = null;
	Long							_intervall = null;
	
	Boolean							_SendEmail = true; 
	Boolean							_SendMessageOnCreation = false;
	String 							_DtAnzeigeAb; 
	
	String 							_Kategorie; // Die Kategorie in Textform. Ersetzt durch eine ID wird sie beim speichern im Handler
	Long 							_ID_Kategorie; // Alternativ die ID der Kategorie  
	String							_Reminder_kennung; 
	
	// Erweiterung der neuen Reminder-Intervalle
	String							_intervall_typ;
	Long							_intervall_param;
	
	
	Vector<MESSAGE_Entry_Target> 	_vTargets = new Vector<>();
//	private Vector<REMINDER_USER_Entry_Data>  _vUserAdditional = new Vector<>();
	private Hashtable<String, REMINDER_USER_Entry_Data>  _htUserAdditional = new Hashtable<>();
	
	
	// Ausschliesslicht für Reminder, die aus der DB gelesen wurden
	RECORD_REMINDER_DEF				_rec_Reminder_def = null;
	String 							_KeyReminder;
	Vector<RECORD_REMINDER_USER>	_vReminderUser = new Vector<RECORD_REMINDER_USER>();
	
	/**
	 * Default Konstruktor
	 * @author manfred
	 * @date 01.04.2016
	 *
	 */
	public REMINDER_Entry_Data() {
	}

	
	
	public String get_Titel() {
		return _Titel;
	}
	public REMINDER_Entry_Data set_Titel(String _Titel) {
		this._Titel = _Titel;
		return this;
	}
	
	public String get_Nachricht() {
		return _Nachricht;
	}
	public REMINDER_Entry_Data set_Nachricht(String _Nachricht) {
		this._Nachricht = _Nachricht;
		return this;
	}
	
	public RECORD_REMINDER_DEF get_rec_Reminder_def() {
		return _rec_Reminder_def;
	}
	public REMINDER_Entry_Data set_rec_Reminder_def(RECORD_REMINDER_DEF _rec_Reminder_def) {
		this._rec_Reminder_def = _rec_Reminder_def;
		return this;
	}
	
	public Vector<RECORD_REMINDER_USER> get_vReminderUser() {
		return _vReminderUser;
	}
	public REMINDER_Entry_Data set_vReminderUser(Vector<RECORD_REMINDER_USER> _vReminderUser) {
		this._vReminderUser = _vReminderUser;
		return this;
	}
	
	public Boolean get_SendEmail() {
		return _SendEmail;
	}
	public REMINDER_Entry_Data set_SendEmail(Boolean _SendEmail) {
		this._SendEmail = _SendEmail;
		return this;
	}
	
	public String get_DtAnzeigeAb() {
		return _DtAnzeigeAb;
	}
	public REMINDER_Entry_Data set_DtAnzeigeAb(String _DtAnzeigeAb) {
		this._DtAnzeigeAb = _DtAnzeigeAb;
		return this;
	}
	
	public String get_KeyReminder() {
		return _KeyReminder;
	}
	public REMINDER_Entry_Data set_KeyReminder(String _KeyReminder) {
		this._KeyReminder = _KeyReminder;
		return this;
	}
	
	public Vector<MESSAGE_Entry_Target> get_vTargets() {
		return _vTargets;
	}
	public REMINDER_Entry_Data set_vTargets(Vector<MESSAGE_Entry_Target> _vTargets) {
		this._vTargets = _vTargets;
		return this;
	}
	
	public String get_Kategorie() {
		return _Kategorie;
	}
	public REMINDER_Entry_Data set_Kategorie(String _Kategorie) {
		this._Kategorie = _Kategorie;
		return this;
	}
	
	public Long get_ID_Kategorie() {
		return _ID_Kategorie;
	}
	public REMINDER_Entry_Data set_ID_Kategorie(Long _ID_Kategorie) {
		this._ID_Kategorie = _ID_Kategorie;
		return this;
	}
	
	public Boolean get_SendMessageOnCreation() {
		return _SendMessageOnCreation;
	}
	public REMINDER_Entry_Data set_SendMessageOnCreation(Boolean _SendMessageOnCreation) {
		this._SendMessageOnCreation = _SendMessageOnCreation;
		return this;
	}

	

	public String get_BaseTable() {
		return _BaseTable;
	}
	public REMINDER_Entry_Data set_BaseTable(String _BaseTable) {
		this._BaseTable = _BaseTable;
		return this;
	}

	public String get_ID_Table() {
		return _ID_Table;
	}
	public REMINDER_Entry_Data set_ID_Table(String _ID_Table) {
		this._ID_Table = _ID_Table;
		return this;
	}

	public String get_ID_UserAngelegt() {
		return _ID_UserAngelegt;
	}
	public REMINDER_Entry_Data set_ID_UserAngelegt(String _ID_UserAngelegt) {
		this._ID_UserAngelegt = _ID_UserAngelegt;
		return this;
	}

	public MODUL get_modul_ziel() {
		return _modul_ziel;
	}
	public REMINDER_Entry_Data set_modul_ziel(MODUL _modul_ziel) {
		this._modul_ziel = _modul_ziel;
		return this;
	}

	public Long get_intervall() {
		return _intervall;
	}
	public REMINDER_Entry_Data set_intervall(Long _intervall) {
		this._intervall = _intervall;
		return this;
	}
	
	public String get_intervall_typ() {
		return _intervall_typ;
	}
	public REMINDER_Entry_Data set_intervall_typ(String _intervall_typ) {
		this._intervall_typ = _intervall_typ;
		return this;
	}
	public REMINDER_Entry_Data set_intervall_typ(ENUM_REMINDER_WIEDERHOLUNG _enum_reminder_wdh) {
		this._intervall_typ = _enum_reminder_wdh.DB_Entry();
		return this;
	}
	
	
	

	public Long get_intervall_param() {
		return _intervall_param;
	}
	public REMINDER_Entry_Data set_intervall_param(Long _intervall_param) {
		this._intervall_param = _intervall_param;
		return this;
	}
	
	
	
	
	
	public Hashtable<String,REMINDER_USER_Entry_Data> get_htUserAdditional() {
		return _htUserAdditional;
	}
	/**
	 * fügt einen User dazu, der den Reminder schliessen kann, aber nicht ändern.
	 * @author manfred
	 * @date 01.04.2016
	 *
	 * @param idUser
	 * @return
	 */
	public REMINDER_Entry_Data add_UserAdditional(String idUser){
		REMINDER_USER_Entry_Data o = 
				new REMINDER_USER_Entry_Data()
				.set_id_user(idUser)
				.set_allow_close(true)
				.set_allow_edit(false);
		_htUserAdditional.put(idUser,o);
		return this;
	}
	
	public REMINDER_Entry_Data add_UserAdditional(String idUser, boolean bAllowEdit, boolean bAllowClose,boolean bSendEmail, boolean bSofortanzeige){
		REMINDER_USER_Entry_Data o = 
				new REMINDER_USER_Entry_Data()
				.set_id_user(idUser)
				.set_allow_close(bAllowClose)
				.set_allow_edit(bAllowEdit)
				.set_send_mail(bSendEmail)
				.set_sofortanzeige(bSofortanzeige)
				;
		_htUserAdditional.put(idUser,o);
		return this;
	}
	
	
	public REMINDER_Entry_Data add_UserAdditional(REMINDER_USER_Entry_Data user){
		_htUserAdditional.put(user.get_id_user(),user);
		return this;
	}
	
	/**
	 * fügt die Userliste zur bestehenden Userliste dazu
	 * @author manfred
	 * @date 05.04.2016
	 *
	 * @param _vUserAdditional
	 * @return
	 */
	public REMINDER_Entry_Data add_UserAdditional(Vector<REMINDER_USER_Entry_Data> _vUserAdditional) {
		for (REMINDER_USER_Entry_Data o : _vUserAdditional){
			add_UserAdditional(o);
		}
		return this;
	}
	
	/**
	 * Ersetzt die bestehende Userliste 
	 * @author manfred
	 * @date 05.04.2016
	 *
	 * @param _vUserAdditional
	 * @return
	 */
	public REMINDER_Entry_Data set_UserAdditional(Vector<REMINDER_USER_Entry_Data> _vUserAdditional) {
		_htUserAdditional.clear();
		for (REMINDER_USER_Entry_Data o : _vUserAdditional){
			add_UserAdditional(o);
		}
		return this;
	}

	
	public String get_Reminder_kennung() {
		return _Reminder_kennung;
	}

	public REMINDER_Entry_Data set_Reminder_kennung(String _Reminder_kennung) {
		this._Reminder_kennung = _Reminder_kennung;
		return this;
	}





	
}
