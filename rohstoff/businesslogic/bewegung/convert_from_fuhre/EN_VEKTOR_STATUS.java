package rohstoff.businesslogic.bewegung.convert_from_fuhre;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enumForDb;

/**
 * definition des Status eines Vektors
 * @author martin
 *
 */
public enum EN_VEKTOR_STATUS implements IF_enumForDb<EN_VEKTOR_STATUS> {
	 AKTIV("Aktiv")
	,STORNIERT("Storniert")
	,GEPLANT("Geplant")
	,FINAL("Abgeschlossen")
	;
	
	 private String  		userText = null;

	
	private EN_VEKTOR_STATUS(String p_userText) {
		this.userText = p_userText;
	}

	
	@Override
	public String userText() {
		return S.isFull(this.userText)?this.userText:IF_enumForDb.super.userText();
	}


	@Override
	public EN_VEKTOR_STATUS[] getValues() {
		return EN_VEKTOR_STATUS.values();
	}

	

}