/**
 * 
 */
package rohstoff.businesslogic.bewegung2.global;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enumForDb;

/**
 * @author martin
 * definiert die Art des atoms
 */
public enum EN_VEKTOR_QUELLE implements IF_enumForDb<EN_VEKTOR_QUELLE> {
    
	FUHRE("Alte Warenbewegung")
	,NATIV("Neue Warenbewegung")
	,LAGER("Alte Handbuchung")
	;

	private String userText = null;
	
	
	/**
	 * @param userText
	 */
	private EN_VEKTOR_QUELLE(String p_userText) {
		this.userText = p_userText;
	}

	@Override
	public String userText() {
		return S.isFull(this.userText)?this.userText:IF_enumForDb.super.userText();
	}

	@Override
	public EN_VEKTOR_QUELLE[] getValues() {
		return EN_VEKTOR_QUELLE.values();
	}


	 
	public static EN_VEKTOR_QUELLE findQuelle(String dbVal) {
		for (EN_VEKTOR_QUELLE quelle: EN_VEKTOR_QUELLE.values()) {
			if (quelle.dbVal().equals(dbVal)) {
				return quelle;
			}
		}
		return null;
	}



}
