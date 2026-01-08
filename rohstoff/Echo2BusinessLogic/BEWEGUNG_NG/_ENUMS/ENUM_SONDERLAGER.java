package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS;

import java.util.LinkedHashMap;

import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

/**
 * KH - Korrektur Hand
 * UH - Umbuchung Hand
 * LL - LagerLager
 * MI - Mixed
 * MK - Mengenkorrektur
 * STRECKE - Strecke
 * SW - Sortenwechsel
 * ZWE - ZwischenLager - Wareneingang
 * ZWA - Zwischenlager - Warenausgang
 * ZWE_UMB - Zwischenlager - Umbuchungen Wareneingang
 * ZWA_UMB - Zwischenlager - Umbuchungen Warenausgang
 * 
 * @author manfred
 * @date 26.10.2017
 *
 */
public enum ENUM_SONDERLAGER implements IF_enum_4_db {
	 KH("Handkorrektur-Lager","Handkorrektur-Lager")
	,UH("Handumbuchung-Lager","Handumbuchung-Lager")
	
	,LL("Lagerumfuhren-Zwischenlager","Lagerumfuhren-Zwischenlager")
	,MI("Mixed-Lager","Mixed-Lager")
	,MK("Mengenkorrektur-Lager","Mengenkorrektur-Lager")
	,STRECKE("Strecken-Lager","Strecken-Lager")
	,SW("Sortenwechsel-Lager","Sortenwechsel-Lager")
	
	,ZWE("Zwischenlager Wareneingang","Zwischenlager Wareneingang")
	,ZWA("Zwischenlager Warenausgang","Zwischenlager Warenausgang")
	
	,ZWE_UMB("Zubuchung Zwischenlager","Zubuchung Zwischenlager")
	,ZWA_UMB("Ausbuchung Zwischenlager","Ausbuchung Zwischenlager")
	
	,OFFLG_EIN("Pseudo-Lager Einbuchungsquelle","Pseudo-Lager Einbuchungsquelle")
	,OFFLG_AUS("Pseudo-Lager Ausbuchungssenke","Pseudo-Lager Ausbuchungssenke")
	
	,ZW_UMB("Umbuchung Zwischenlager","Umbuchung Zwischenlager")
	;
	
	
	private String  	user_text = null; 
	private String   	user_text_lang = null;
	
	private ENUM_SONDERLAGER(String p_userText, String p_user_text_lang) {
		this.user_text = 	p_userText;
		this.user_text_lang = p_user_text_lang;
	}
	
	@Override
	public String user_text() {
		return this.user_text;
	}
	
	@Override
	public String db_val() {
		return this.name();
	}
	
	/**
	 * gibt die Mandanten-Abhängige ID des Sonderlagers zurück
	 * @author manfred
	 * @date 23.05.2017
	 *
	 * @return
	 */
	public String get_ID(){
		return  bibSES.getID_AdresseLAGER(this.db_val());
	}
	
	public Long get_ID_longValue(){
		return Long.parseLong(bibSES.getID_AdresseLAGER(this.db_val()));
	}
	
	
	
	
	
	@Override
	public String user_text_lang() {
		return this.user_text_lang;
	}
	
	public static ENUM_SONDERLAGER find_SonderlagerFromDBValue(String dbVal) {
		for (ENUM_SONDERLAGER sl: ENUM_SONDERLAGER.values()) {
			if (sl.db_val().equals(dbVal)) {
				return sl; 
			}
		}
		return null;
	}
	public static LinkedHashMap<String, String> hm_key_userText() {
		LinkedHashMap<String, String> hmRueck = new LinkedHashMap<String, String>();
		for (ENUM_SONDERLAGER en: ENUM_SONDERLAGER.values()) {
			hmRueck.put(en.db_val(), en.user_text());
		}
		return hmRueck;
	}


	public static LinkedHashMap<String, String> hm_key_userTextLang() {
		LinkedHashMap<String, String> hmRueck = new LinkedHashMap<String, String>();
		for (ENUM_SONDERLAGER en: ENUM_SONDERLAGER.values()) {
			hmRueck.put(en.db_val(), en.user_text_lang());
		}
		return hmRueck;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_SONDERLAGER.values(), emptyPairInFront);
	}

	
}