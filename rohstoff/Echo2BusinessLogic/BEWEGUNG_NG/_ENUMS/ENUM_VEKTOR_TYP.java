package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS;

import java.util.LinkedHashMap;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

/**
 * Typen, die innerhalb der datenbank unterschieden werden
 */
public enum ENUM_VEKTOR_TYP implements IF_enum_4_db {

	 WE("WE","Wareneingang", 			true, 	E2_ResourceIcon.get_RI("vt_we.png"), 	"Wareneingang auf eigenes Lager")
	,WA("WA","Warenausgang", 			true, 	E2_ResourceIcon.get_RI("vt_wa.png"), 	"Warenausgang von eigenem Lager")
	,UMB("Hand-UB","Hand-Umbuchung", 	true, 	E2_ResourceIcon.get_RI("leer.png"),		"")
	,ST("Strecke","Strecke", 			true, 	E2_ResourceIcon.get_RI("vt_st.png"), 	"")
	,MI("Mixed","Mixed",				false, 	E2_ResourceIcon.get_RI("leer.png"), 	"")
	,LL("L->L","Lager-Lager", 			true, 	E2_ResourceIcon.get_RI("vt_ll.png"), 	"Lager-Lager-Fuhre")
	,KORR("Hand-Korr","Hand-Korrektur", true, 	E2_ResourceIcon.get_RI("leer.png"), 	"")
	,MK("Mge-Korr","Mengenkorrektur",	false,	E2_ResourceIcon.get_RI("leer.png"),		"")
	,SW("Sort.Wech.","Sortenwechsel",	false, 	E2_ResourceIcon.get_RI("leer.png"),		"")
	,LG("Leergut","Leergutfuhre",		true, 	E2_ResourceIcon.get_RI("vt_lg.png"), 	"Leergut-Fuhre")
	,TS("Probe", "Probefuhre", 			true, 	E2_ResourceIcon.get_RI("vt_p.png"), 	"Probe-Fuhre")
	,S("Strecke","Strecke", 			true, 	E2_ResourceIcon.get_RI("vt_s.png"), 	"Streckenfuhre")
	;
	
	private String    			db_val 			= null;
	private String		  		user_text 		= null; 
	private String   			user_text_lang 	= null;
	private boolean   			editable 		= false;  
	private E2_ResourceIcon  	icon 			= null;
	private String 				beschreibung	= null;

	private ENUM_VEKTOR_TYP(String p_user_text, String p_user_text_lang, boolean p_editable, E2_ResourceIcon p_icon, String p_beschreibung) {
		this.db_val			=	null;
		this.user_text		=	p_user_text;
		this.user_text_lang = 	p_user_text_lang;
		this.editable 		=   p_editable;
		this.icon 			= 	p_icon;
		this.beschreibung 	= 	p_beschreibung;
	}

	
	@Override
	public String db_val() {
		return this.db_val==null?this.name():this.db_val;
	}

	@Override
	public String user_text()	{
		return this.user_text;
	}
	
	@Override
	public String user_text_lang() {
		return this.user_text_lang;
	}

	public boolean isEditable()	{
		return this.editable;
	}

	public String get_beschreibung() {
		return beschreibung;
	}

	public RB_lab get_vektor_typ_label() throws myException {
		return new RB_lab(this.icon)._ttt(this.get_beschreibung());
	}

	public static ENUM_VEKTOR_TYP find_typ(String dbVal) {
		for (ENUM_VEKTOR_TYP typ: ENUM_VEKTOR_TYP.values()) {
			if (typ.db_val().equals(dbVal)) {
				return typ;
			}
		}
		return null;
	}


	public static LinkedHashMap<String, String> hm_key_userText() {
		LinkedHashMap<String, String> hmRueck = new LinkedHashMap<String, String>();
		for (ENUM_VEKTOR_TYP en: ENUM_VEKTOR_TYP.values()) {
			hmRueck.put(en.db_val(), en.user_text());
		}
		return hmRueck;
	}


	public static LinkedHashMap<String, String> hm_key_userTextLang() {
		LinkedHashMap<String, String> hmRueck = new LinkedHashMap<String, String>();
		for (ENUM_VEKTOR_TYP en: ENUM_VEKTOR_TYP.values()) {
			hmRueck.put(en.db_val(), en.user_text_lang());
		}
		return hmRueck;
	}
	
	
	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_VEKTOR_TYP.values(), emptyPairInFront);
	}

	
}