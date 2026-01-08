package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_AND_DOCUMENTATION;

import java.util.HashMap;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public class HAD___CONST {

	public static String MAIL_KLEMMBRETT_BETREFF_HANDBUCHDRUCK="BETREFF_HANDBUCHDRUCK";
	public static String MAIL_KLEMMBRETT_MAILTEXT_HANDBUCHDRUCK="MAILTEXT_HANDBUCHDRUCK";
	
	
	public static int              WindowWIDTH = 850;
	public static int              WindowHEIGTH = 750;

	
	public enum parameter_handbuchdruck {
		 WHEREBLOCK
		,ORDERBLOCK 
		,HASH_STATUS
		,HASH_TYP
		,HASH_MODULE
	}
	
	
	public enum TICKET_STATUS implements IF_enum_4_db{
		 NEW(			"Neues Ticket")	
		,IN_PROGRESS(	"In Arbeit")
		,CLOSED(		"Abgeschlossen")
		;
		
		private String 	db_Val = null;
		private String 	userText = null;

		private TICKET_STATUS(String p_userText) {
			
			this.db_Val = this.name();
			this.userText = new MyE2_String(p_userText).CTrans();
		}

		@Override
		public String db_val() {
			return db_Val;
		}


		@Override
		public String user_text() {
			return this.userText;
		}

		@Override
		public String user_text_lang() {
			return this.userText;
		}
		
		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(TICKET_STATUS.values(), emptyPairInFront);
		}

	}

	
	

	public enum TICKET_TYP  implements IF_enum_4_db {
		DOKUMENT(			"Handbuch",true,false)
		,FEATURE(			"Neue Funktion",false,true)
		,BUGFIX(			"Fehlerkorrektur",false,true)
		;
		
		private String 		db_Val = null;
		private String 		userText = null;
		private boolean    	is_doku = false;
		private boolean    	is_develop = false;

		private TICKET_TYP(String p_userText, boolean p_doku, boolean p_develop) {
			this.db_Val = this.name();
			this.userText = new MyE2_String(p_userText).CTrans();
			this.is_develop = p_develop;
			this.is_doku = p_doku;
		}
		
		
		public String db_val() {
			return db_Val;
		}

		@Override
		public String user_text() {
			return this.userText;
		}
		
		@Override
		public String user_text_lang() {
			return this.userText;
		}

		public boolean is_doku() {
			return is_doku;
		}

		public boolean is_develop() {
			return is_develop;
		}


		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(TICKET_TYP.values(), emptyPairInFront);
		}
		
	}

	
	
	public static HashMap<String, String> get_hm_status() {
		HashMap<String, String> hm_rueck = new HashMap<String, String>();
		for (TICKET_STATUS s: TICKET_STATUS.values()) {
			hm_rueck.put(s.db_val(), s.user_text_lang());
		}
		return hm_rueck;
	}

	
	public static HashMap<String, String> get_hm_ticketTyp() {
		HashMap<String, String> hm_rueck = new HashMap<String, String>();
		for (TICKET_TYP s: TICKET_TYP.values()) {
			hm_rueck.put(s.db_val(), s.user_text_lang());
		}
		return hm_rueck;
	}
	
	
	
	
}
