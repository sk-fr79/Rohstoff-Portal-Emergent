package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class _SIMPLE_MESSAGE {
	
	public enum kategorien {
		TRIGGER_ACTION("Überwachungstrigger")
		;
		
		private String lesbarer_text = null;
		
		private kategorien(String lesbar) {
			this.lesbarer_text = lesbar;
		}

		public String get_lesbarer_text() {
			return lesbarer_text;
		}
	}
	
	
	private String 		message_title = S.mt("Meldung").CTrans();
	private String 		message_block = "<>";
	private VEK<String> v_id_user = new VEK<String>()._a(bibALL.get_ID_USER());
	private boolean 	sofort_meldung = true;
	private MyDate  	anzeige_ab = new MyDate(bibALL.get_cDateNOW());
	private kategorien  kategorie = kategorien.TRIGGER_ACTION;
	private String    	message_typ = MESSAGE_CONST.MESSAGE_TYP_SYSTEM;
	
	
	/**
	 * 
	 */
	public _SIMPLE_MESSAGE() {
		super();
	}


	public _SIMPLE_MESSAGE _setMessage_title(String _message_title) {
		this.message_title = _message_title;
		return this;
	}


	public _SIMPLE_MESSAGE _setMessage_block(String _message_block) {
		this.message_block = _message_block;
		return this;
	}


	public _SIMPLE_MESSAGE _add_id_user(String _id_user) {
		this.v_id_user._a(_id_user);
		return this;
	}


	public _SIMPLE_MESSAGE _set_sofort_meldung_ein() {
		this.sofort_meldung = true;
		return this;
	}
	public _SIMPLE_MESSAGE _set_sofort_meldung_aus() {
		this.sofort_meldung = false;
		return this;
	}


	public _SIMPLE_MESSAGE _setAnzeige_ab(MyDate _anzeige_ab) {
		this.anzeige_ab = _anzeige_ab;
		return this;
	}


	public _SIMPLE_MESSAGE _setKategorie(kategorien _kategorie) {
		this.kategorie = _kategorie;
		return this;
	}


	public _SIMPLE_MESSAGE _set_TYP_SYSTEM() {
		this.message_typ = MESSAGE_CONST.MESSAGE_TYP_SYSTEM;
		return this;
	}

	public _SIMPLE_MESSAGE _set_TYP_USER() {
		this.message_typ = MESSAGE_CONST.MESSAGE_TYP_USER;
		return this;
	}

	
	

	public _SIMPLE_MESSAGE _START() throws myException {
		MESSAGE_Entry msgEntry = new MESSAGE_Entry()
		.set_Titel(this.message_title)
		.set_Nachricht(this.message_block)
		.set_Sofortanzeige(this.sofort_meldung)
		.set_DtAnzeigeAb(this.anzeige_ab.get_cDBFormatErgebnis())
		.set_Kategorie(this.kategorie.lesbarer_text);

		for (String id: this.v_id_user) {
			msgEntry.add_idEmpfaenger(id);
		}
		new MESSAGE_Handler(this.message_typ).saveMessage(msgEntry);
		return this;
	}
	
	


	
}
