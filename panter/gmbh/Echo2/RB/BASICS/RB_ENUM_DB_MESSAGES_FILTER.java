/**
 * 
 */
package panter.gmbh.Echo2.RB.BASICS;

import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public enum RB_ENUM_DB_MESSAGES_FILTER {
	
	//sammlung von Datenbank-meldungen, die in einem dataobjects-collector (V2) in fehlermeldungen umgeschrieben werden
	UNIQUE_CONSTRAINT(new VEK<String>(),"Es wurde bei der Prüfung auf doppelte Datensätze eine Dublette festgestellt !")
	
	
	;
	
	private VEK<String> vMatchingKeys = null;
	private String      cLesbar4User = null;
	
	
	/**
	 * @param p_vMatchingKeys
	 * @param p_cLesbar4User
	 */
	private RB_ENUM_DB_MESSAGES_FILTER(VEK<String> p_vMatchingKeys, String p_cLesbar4User) {
		this.vMatchingKeys = p_vMatchingKeys;
		this.cLesbar4User = p_cLesbar4User;
	}
	
	
	
}
