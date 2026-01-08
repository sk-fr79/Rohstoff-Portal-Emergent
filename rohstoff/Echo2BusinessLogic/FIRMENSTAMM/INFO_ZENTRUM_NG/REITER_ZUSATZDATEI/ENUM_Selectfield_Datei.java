/**
 * rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.REITER_MELDUNG
 * @author sebastien
 * @date 29.11.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG.REITER_ZUSATZDATEI;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.indep.S;

/**
 * @author sebastien
 * @date 29.11.2018
 *
 */
public enum ENUM_Selectfield_Datei {
	SF_FARBDIFF("Farbdiff.","Farbunterschied der Einrückungen definieren")
	,SF_INSETS("Abstände","Abstände der einzelnen Infoblöcke definieren")
	;
	
	private String tooltiptext = "";
	private String lbl_4_user = "";
	
	private ENUM_Selectfield_Datei(String p_lbl_4_user, String p_tooltiptext) {
		this.tooltiptext = p_tooltiptext;
		this.lbl_4_user = p_lbl_4_user;
	}

	public MyE2_String getTooltiptext() {
		return S.ms(tooltiptext);
	}

	public MyE2_String get_lbl_4_user() {
		return S.ms(lbl_4_user);
	}
	
	
}
