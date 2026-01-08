/**
 * 
 */
package rohstoff.businesslogic.StammDaten.FuhrenKostenTypen;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enumForDb;

/**
 * @author martin
 *
 */
public enum EN_KOSTENTYP_VORSCHLAEGE implements IF_enumForDb<EN_KOSTENTYP_VORSCHLAEGE> {
	
	ROUTE(null,"Kosten aus Routenberechnung")
	,SPED(null,"Speditionskosten")
	,E_UST("E-UST","Einfuhrumsatzsteuer")
	,ZA(null,"Kosten Zollagentur")
	;
	
	
	private String dbVal = null;
	private String userText = null;

	
	/**
	 * @param p_dbVal
	 * @param p_userText
	 */
	private EN_KOSTENTYP_VORSCHLAEGE(String p_dbVal, String p_userText) {
		this.dbVal = 	S.isFull(p_dbVal)?p_dbVal:this.name();
		this.userText = p_userText;
	}

	
	@Override
	public EN_KOSTENTYP_VORSCHLAEGE[] getValues() {
		return EN_KOSTENTYP_VORSCHLAEGE.values();
	}

	@Override
	public String userText() {
		return this.userText;
	}

	@Override
	public String dbVal() {
		return this.dbVal;
	}

}
