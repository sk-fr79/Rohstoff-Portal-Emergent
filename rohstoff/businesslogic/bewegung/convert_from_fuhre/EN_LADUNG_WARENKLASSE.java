package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enumForDb;

/**
 * definition der Warenklasse einer ladung
 * @author martin
 *
 */
public enum EN_LADUNG_WARENKLASSE implements IF_enumForDb<EN_LADUNG_WARENKLASSE> {
	EIGENWARE("Eigen","")
	,FREMDWARE("Fremd","")
	,LEERGUT("Leergut","Leegutstellung/-rückholung")
	
	;
	private String  		userText = null;
	private String   		userTextLang = null;
	
	private EN_LADUNG_WARENKLASSE(String p_user_text, String p_user_text_lang) {
		this.userText = p_user_text;
		this.userTextLang = p_user_text_lang;
	}

	
	@Override
	public String userText() {
		return S.isFull(this.userText)?this.userText:IF_enumForDb.super.userText();
	}

	@Override
	public String userTextLang() {
		return S.isFull(this.userTextLang)?this.userTextLang:IF_enumForDb.super.userTextLang();
	}

	@Override
	public EN_LADUNG_WARENKLASSE[] getValues() {
		return EN_LADUNG_WARENKLASSE.values();
	}

}