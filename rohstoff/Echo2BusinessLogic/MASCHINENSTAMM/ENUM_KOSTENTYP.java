/**
 * 
 */
package rohstoff.Echo2BusinessLogic.MASCHINENSTAMM;

import panter.gmbh.indep.enumtools.IF_enumForDb;

/**
 * @author martin
 *
 */
public enum ENUM_KOSTENTYP implements IF_enumForDb<ENUM_KOSTENTYP> {
	
	FAHRTKOSTEN_PAUSCHAL(ENUM_KOSTENBASIS.KM,"Pauschalkosten/km")
	,ANSCHAFFUNG(ENUM_KOSTENBASIS.SINGULAER,"Anschaffung")
	,
	
	
	;

	private ENUM_KOSTENBASIS basis = null;
	private String  			userText = null;
	
	
	
	/**
	 * @param p_basis
	 * @param p_userText
	 */
	private ENUM_KOSTENTYP(ENUM_KOSTENBASIS p_basis, String p_userText) {
		this.basis = p_basis;
		this.userText = p_userText;
	}



	@Override
	public ENUM_KOSTENTYP[] getValues() {
		return ENUM_KOSTENTYP.values();
	}



	@Override
	public String userText() {
		return this.userText;
	}



	public ENUM_KOSTENBASIS getBasis() {
		return basis;
	}
	
	
	
	
}
