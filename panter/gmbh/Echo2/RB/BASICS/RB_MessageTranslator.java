/**
 * 
 */
package panter.gmbh.Echo2.RB.BASICS;

import panter.gmbh.indep.myVectors.VEK;

/**
 * @author martin
 *
 */
public class RB_MessageTranslator {
	

	private VEK<String> vMatchingKeys = null;
	private String      cLesbar4User = null;
	
	/**
	 * @param p_vMatchingKeys
	 * @param p_cLesbar4User
	 */
	public RB_MessageTranslator(VEK<String> p_vMatchingKeys, String p_cLesbar4User) {
		this.vMatchingKeys = p_vMatchingKeys;
		this.cLesbar4User = p_cLesbar4User;
	}

	public VEK<String> getvMatchingKeys() {
		return vMatchingKeys;
	}

	public String getcLesbar4User() {
		return cLesbar4User;
	}
	
	
}
