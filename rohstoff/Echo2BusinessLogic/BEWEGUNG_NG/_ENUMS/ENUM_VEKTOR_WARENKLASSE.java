package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS;

import java.util.LinkedHashMap;

import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

/**
 * definition des Status eines Vektors
 * @author martin
 *
 */
public enum ENUM_VEKTOR_WARENKLASSE implements IF_enum_4_db{
	EIGENWARE("Eigen","")
	,FREMDWARE("Fremd","")
	,LEERGUT("Leergut","Leegutstellung/-rückholung")
	
	;
	private String 			db_val = null;
	private String  		user_text = null;
	private String   		user_text_lang = null;
	
	private ENUM_VEKTOR_WARENKLASSE(String p_user_text, String p_user_text_lang) {
		this.db_val=null;
		this.user_text = p_user_text;
		this.user_text_lang = p_user_text_lang;
	}
	@Override
	public String db_val() {
		return db_val==null?this.name():db_val;
	}
	@Override
	public String user_text() {
		return user_text;
	}
	
	@Override
	public String user_text_lang() {
		return this.user_text_lang;
	}

	
	public static ENUM_VEKTOR_WARENKLASSE find_status(String dbVal) {
		for (ENUM_VEKTOR_WARENKLASSE status: ENUM_VEKTOR_WARENKLASSE.values()) {
			if (status.db_val().equals(dbVal)) {
				return status;
			}
		}
		return null;
	}
	
	public static LinkedHashMap<String, String> hm_key_userText() {
		LinkedHashMap<String, String> hmRueck = new LinkedHashMap<String, String>();
		for (ENUM_VEKTOR_WARENKLASSE en: ENUM_VEKTOR_WARENKLASSE.values()) {
			hmRueck.put(en.db_val(), en.user_text());
		}
		return hmRueck;
	}


	public static LinkedHashMap<String, String> hm_key_userTextLang() {
		LinkedHashMap<String, String> hmRueck = new LinkedHashMap<String, String>();
		for (ENUM_VEKTOR_WARENKLASSE en: ENUM_VEKTOR_WARENKLASSE.values()) {
			hmRueck.put(en.db_val(), en.user_text_lang());
		}
		return hmRueck;
	}
	
	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_VEKTOR_WARENKLASSE.values(), emptyPairInFront);
	}

}