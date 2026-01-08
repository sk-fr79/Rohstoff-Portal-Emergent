/**
 * 
 */
package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enumForDb;

/**
 * @author martin
 * Definiert die Position eines Atoms innerhalb der Bewegung 
 */
public enum EN_ATOM_POS_IN_BG implements IF_enumForDb<EN_ATOM_POS_IN_BG> {
	
	LEFT("Linkes Seite")
	,RIGHT("Rechte Seite")
	,MID("Singuläre Bewegung")
	;

	private String userText = null;
	
	
	/**
	 * @param userText
	 */
	private EN_ATOM_POS_IN_BG(String p_userText) {
		this.userText = p_userText;
	}

	@Override
	public String userText() {
		return S.isFull(this.userText)?this.userText:IF_enumForDb.super.userText();
	}

	@Override
	public EN_ATOM_POS_IN_BG[] getValues() {
		return EN_ATOM_POS_IN_BG.values();
	}



}
