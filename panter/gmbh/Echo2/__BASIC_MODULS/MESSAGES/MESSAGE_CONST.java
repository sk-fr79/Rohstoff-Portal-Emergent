package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public class MESSAGE_CONST {
	
	/**
	 * 201771128: ersatz der konstanten messagetyp durch enum
	 */
	public enum ENUM_MESSAGE_TYP implements IF_enum_4_db {

		USER("Benutzermeldung")
		,SYSTEM("Systemmeldung")
		;
		

		/**
		 * @param p_userText
		 */
		private ENUM_MESSAGE_TYP(String p_userText) {
			this.userText = p_userText;
		}

		private String userText = null;
		@Override
		public String db_val() {
			return this.name();
		}

		@Override
		public String user_text() {
			return S.NN(this.userText, this.name());
		}

		@Override
		public String user_text_lang() {
			return this.userText;
		}

		@Override
		public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
			return bibENUM.dd_array(ENUM_MESSAGE_TYP.values(), emptyPairInFront);
		}
		
	}

	
	
	
	/*
	 * feldwerte fuer JT_NACHRICHTEN.TYP_NACHRICHT
	 */
//	public static String  MESSAGE_TYP_USER = 	"USER";
//	public static String  MESSAGE_TYP_SYSTEM = 	"SYSTEM";
	public static String  MESSAGE_TYP_USER = 	ENUM_MESSAGE_TYP.USER.db_val();
	public static String  MESSAGE_TYP_SYSTEM = 	ENUM_MESSAGE_TYP.SYSTEM.db_val();
	
	
	
	public static String  MESSAGE_LIST_BUTTON_CONNECTOR = "MESSAGE_LIST_BUTTON_CONNECTOR";
	public static String  MESSAGE_LIST_ROW_BUTTONS 		= "MESSAGE_LIST_ROW_BUTTONS";
	
	/**
	 * Message-Kennungen, die auch der automatischen Zuordnungen in Meldungs-Kategorien dienen
	 * @author manfred
	 * @date 03.07.2015
	 *
	 */
	public enum REMINDER_Kennung {
		MESSAGE_FIRMENSTAMM_INFO,
		MESSAGE_KREDITVERSICHERUNG_ABLAUF,
		MESSAGE_KREDITVERSICHERUNG_UEBERZOGEN,
		MESSAGE_HANDELSDEF_INFO,
		MESSAGE_WARENBEWEGUNG_GRENZUEBERSCHREITEND,
		MESSAGE_FIRMENSTAMM_SANKTION
	}
	
	
	
}
