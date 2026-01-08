/**
 * panter.gmbh.Echo2.RB.BASICS
 * @author martin
 * @date 04.04.2019
 * 
 */
package panter.gmbh.basics4project;

import panter.gmbh.indep.enumtools.IF_enumForDb;

/**
 * @author martin
 * @date 04.04.2019
 * varianten der pruefung fuer diverse felder 
 */
public enum EnPruefungTyp implements IF_enumForDb<EnPruefungTyp> {
	
	BG_ATOM_MENGENKONTROLLE("Mengenkontrolle Warenbewegung")
	,BG_ATOM_PREISKONTROLLE("Preiskontrolle Warenbewegung")
	,BG_ATOM_ABSCHLUSS("Abschluss Warenbewegung")
	,BG_ATOM_GELANGENSBESTAETIGUNG("Gelangensbestätigung")
	
	
	;

	private String m_userText = null;
	
	private EnPruefungTyp(String userText) {
		this.m_userText = userText; 
	}
	
	
	@Override
	public EnPruefungTyp[] getValues() {
		return EnPruefungTyp.values();
	}


	@Override
	public String userText() {
		return this.m_userText;
	}


}
