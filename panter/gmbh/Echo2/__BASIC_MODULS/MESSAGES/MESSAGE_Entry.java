package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARCHIVMEDIEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_NACHRICHT;

public class MESSAGE_Entry {

	
	String 							_Titel = null;
	String 							_Nachricht = null; 
	String 							_IDUserSender = null;



	Vector<String> 					_vIdEmpfaenger = new Vector<String>(); 
	Boolean 						_Sofortanzeige; 
	String 							_DtAnzeigeAb; 
	String 							_KeyReminder;
	Vector<MESSAGE_Entry_Target> 	_vTargets = new Vector<MESSAGE_Entry_Target>();
	String 							_Kategorie; // Die Kategorie in Textform. Ersetzt durch eine ID wird sie beim speichern im Handler
	Long 							_ID_Kategorie; // Alternativ die ID der Kategorie  
	RECORD_NACHRICHT 				_rec_Nachricht_ori = null; // für Antworten der Record der originalen Nachricht
	Boolean							_SendEmail = false; // anpassungen für die neue Email- und Nachrichten-Logik
	GregorianCalendar				_EmailSent = null;
	
	// 2016-06-13 : archivmedien an die Message anhängen, damit diese auch verschickt werden können
	Vector<RECORD_ARCHIVMEDIEN>     _vArchivmedien = new Vector<>();
	
	
	public MESSAGE_Entry() {
		// TODO Auto-generated constructor stub
	}
	
	
	public MESSAGE_Entry(String Titel, String Nachricht,
			Vector<String> vIdEmpfaenger, Boolean Sofortanzeige,
			String DtAnzeigeAb, String KeyReminder,
			Vector<MESSAGE_Entry_Target> vTargets,
			String Kategorie,
			Long IDKategorie,
			RECORD_NACHRICHT rec_Ori) {
		super();
	
		this._Titel 		= Titel;
		this._Nachricht 	= Nachricht;
		this._vIdEmpfaenger = vIdEmpfaenger;
		this._Sofortanzeige = Sofortanzeige;
		this._DtAnzeigeAb 	= DtAnzeigeAb;
		this._KeyReminder 	= KeyReminder;
		this._vTargets 		= vTargets;
		this._Kategorie 	= Kategorie;
		this._ID_Kategorie  = IDKategorie;
		this._rec_Nachricht_ori = rec_Ori;
	}

	

	/**
	 * @return the _Titel
	 */
	public String get_Titel() {
		return _Titel;
	}

	/**
	 * @param _Titel the _Titel to set
	 */
	public MESSAGE_Entry set_Titel(String _Titel) {
		this._Titel = _Titel;
		return this;
	}

	/**
	 * @return the _Nachricht
	 */
	public String get_Nachricht() {
		return _Nachricht;
	}

	/**
	 * @param _Nachricht the _Nachricht to set
	 */
	public MESSAGE_Entry set_Nachricht(String _Nachricht) {
		this._Nachricht = _Nachricht;
		return this;
	}

	/**
	 * @return the _vIdEmpfaenger
	 */
	public Vector<String> get_vIdEmpfaenger() {
		return _vIdEmpfaenger;
	}

	/**
	 * @param _vIdEmpfaenger the _vIdEmpfaenger to set
	 */
	public MESSAGE_Entry set_vIdEmpfaenger(Vector<String> _vIdEmpfaenger) {
		this._vIdEmpfaenger = _vIdEmpfaenger;
		return this;
	}

	public MESSAGE_Entry add_idEmpfaenger(String idEmpfaenger){
		this._vIdEmpfaenger.add(idEmpfaenger);
		return this;
	}
	
	/**
	 * @return the _Sofortanzeige
	 */
	public Boolean get_Sofortanzeige() {
		
		return (_Sofortanzeige == null ? false : _Sofortanzeige) ;
	}

	/**
	 * @param _Sofortanzeige the _Sofortanzeige to set
	 */
	public MESSAGE_Entry set_Sofortanzeige(Boolean _Sofortanzeige) {
		this._Sofortanzeige = _Sofortanzeige;
		return this;
	}

	/**
	 * @return the _DtAnzeigeAb
	 */
	public String get_DtAnzeigeAb() {
		return _DtAnzeigeAb;
	}

	/**
	 * @param _DtAnzeigeAb the _DtAnzeigeAb to set
	 */
	public MESSAGE_Entry set_DtAnzeigeAb(String _DtAnzeigeAb) {
		this._DtAnzeigeAb = _DtAnzeigeAb;
		return this;
	}

	/**
	 * @return the _KeyReminder
	 */
	public String get_KeyReminder() {
		return _KeyReminder;
	}

	/**
	 * @param _KeyReminder the _KeyReminder to set
	 */
	public MESSAGE_Entry set_KeyReminder(String _KeyReminder) {
		this._KeyReminder = _KeyReminder;
		return this;
	}



	public Vector<MESSAGE_Entry_Target> get_vTargets() {
		return _vTargets;
	}

	public MESSAGE_Entry set_vTargets(Vector<MESSAGE_Entry_Target> _vTargets) {
		this._vTargets = _vTargets;
		return this;
	}

	public MESSAGE_Entry add_Target(MESSAGE_Entry_Target Target) {
		this._vTargets.add(Target);
		return this;
	}


	public String get_Kategorie() {
		return _Kategorie;
	}



	public MESSAGE_Entry set_Kategorie(String Kategorie) {
		this._Kategorie = Kategorie;
		return this;
	}



	public Long get_ID_Kategorie() {
		return _ID_Kategorie;
	}



	public MESSAGE_Entry set_ID_Kategorie(Long _ID_Kategorie) {
		this._ID_Kategorie = _ID_Kategorie;
		return this;
	}



	public RECORD_NACHRICHT get_rec_Nachricht_ori() {
		return _rec_Nachricht_ori;
	}



	public MESSAGE_Entry set_rec_Nachricht_ori(RECORD_NACHRICHT _rec_Nachricht_ori) {
		this._rec_Nachricht_ori = _rec_Nachricht_ori;
		return this;
	}


	public Boolean get_SendEmail() {
		return _SendEmail;
	}

	public MESSAGE_Entry set_SendEmail(Boolean _SendEmail) {
		this._SendEmail = _SendEmail;
		return this;
	}


	public GregorianCalendar get_EmailSent() {
		return _EmailSent;
	}


	public MESSAGE_Entry set_EmailSent(GregorianCalendar _EmailSent) {
		this._EmailSent = _EmailSent;
		return this;
	}
	
	public String get_IDUserSender() {
		return _IDUserSender;
	}

	public MESSAGE_Entry set_IDUserSender(String _IDUserSender) {
		this._IDUserSender = _IDUserSender;
		return this;
	}
	
	public Vector<RECORD_ARCHIVMEDIEN> get_vArchivmedien(){
		return _vArchivmedien;
	}
	
	public MESSAGE_Entry set_vArchivmedien(Vector<RECORD_ARCHIVMEDIEN> vArchivmedien ){
		this._vArchivmedien = vArchivmedien;
		return this;
	}
	
	public MESSAGE_Entry add_Archivmedien(RECORD_ARCHIVMEDIEN archiv ){
		this._vArchivmedien.addElement(archiv);;
		return this;
	}
	
	
	
}
