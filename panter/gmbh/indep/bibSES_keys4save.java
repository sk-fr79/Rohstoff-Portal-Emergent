package panter.gmbh.indep;


/**
 * namen, unter denen session-werte gespeichert und geladen werden
 * @author martin
 *
 */
public enum bibSES_keys4save {
	 SES_KEY____ALLOWED_MENUES_4_USER("____ALLOWED_MENUES_4_USER")
	,SES_KEY____FIRMEN_MASKE_4_POPUP_BUTTONS_AUS_LISTEN("____FIRMEN_MASKE_4_POPUP_BUTTONS_AUS_LISTEN")
	,SES_KEY____BORDER_COLOR_4_UMA_BUTTON("____BORDER_COLOR_4_UMA_BUTTON")
	,SES_KEY____PRAEFIX_SONDERLAGER("___SONDERLAGER_")
	,SES_KEY____EIGENE_LKW_RECLIST("____EIGENE_LKW_RECLIST")
	,SES_KEY____SUMMATIONSSPALTEN("____SUMMATIONSSPALTEN")
	,SES_KEY____UUID_HASHMAP_4_FINGERPRINTS("____UUID_HASHMAP_4_FINGERPRINTS")
	,SES_KEY_HASHMAPS_METAFIELDDEFS("____KEY_HASHMAPS_METAFIELDDEFS")
	,SES_KEY_FIELD_RULES("____KEY_FIELD_RULES")
	,SES_KEY____MESSAGE_POPUP_TIMESTAMP("____MESSAGE_POPUP_TIMESTAMP")
	,SES_KEY____LAENDERCODE_MANDANT("____LAENDERCODE_MANDANT")
	,SES_KEY_____EIGENE_WAAGE_LAGER_ADRESSEN("____EIGENE_WAAGE_LAGER_ADRESSEN")
	,SES_KEY_____EIGENE_LIEFERADRESSEN_OHNE_SONDERLAGER("_____EIGENE_LIEFERADRESSEN_OHNE_SONDERLAGER")
	,RECORD_MANDANT_REAL()
	,RECORD_USER_REAL()
	,OBJECT_CACHE()
	,SAVE_ENUM_SINGULAR_SETTING_ADDONCODE()   						//addon-code fuer die ENUM_SINGULAR_SETTINGS
	,SAVEKEY_FOR_REGISTRY_VALS("d26bc416-a520-11e8-98d0-529269fb1459")   //fuer sessionhaltung der 
	;
	
	private String f_keyString = null;
	
	private bibSES_keys4save(String p_keyString) {
		this.f_keyString = p_keyString;
	}
	private bibSES_keys4save() {
		this.f_keyString = null;
	}

	
	public String s() {
		if (S.isFull(this.f_keyString)) {
			return this.f_keyString;
		} else {
			return "@@@__@@@#"+this.name();
		}
		
	}
	
	
}
