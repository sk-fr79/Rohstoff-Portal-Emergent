/**
 * 
 */
package rohstoff.businesslogic.bewegung2.convert_from_fuhre;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enumForDb;

/**
 * @author martin
 * definiert die Art des atoms
 */
public enum EN_ATOM_TYP implements IF_enumForDb<EN_ATOM_TYP> {
    
	HAUPTATOM("Haupt-Atom einer einfachen Warenbewegung")
	,VERTEILUNG_VERDECKT("Atom aus einer verdeckten Aufteilung")
	,VERTEILUNG_OFFEN("Atom aus einer offenen Aufteilung")
	,ABZUG_MENGE("Atom aus einem Abzug")
	;

	private String userText = null;
	
	
	/**
	 * @param userText
	 */
	private EN_ATOM_TYP(String p_userText) {
		this.userText = p_userText;
	}

	@Override
	public String userText() {
		return S.isFull(this.userText)?this.userText:IF_enumForDb.super.userText();
	}

	@Override
	public EN_ATOM_TYP[] getValues() {
		return EN_ATOM_TYP.values();
	}



}
