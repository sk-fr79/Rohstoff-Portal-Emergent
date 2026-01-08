/**
 * rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN
 * @author martin
 * @date 11.07.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN;

import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

/**
 * @author martin
 * @date 11.07.2019
 *
 */
public enum EN_CO2_RESULT_STATUS implements IF_enum_4_db_specified<EN_CO2_RESULT_STATUS>{
	OK("OK")
	,NO_COORDINATES("Adresse ohne korrekte Geokodierung involviert")
	,INHOMOGEN_FUHRENORT("Fuhre ist inhomogen (mehrere Lade- oder Abladeorte)")
	,UNKNOWN_ERROR("Unbekannter Fehler")
	;

	
	
	private EN_CO2_RESULT_STATUS(String p_userText) {
		this.userText = p_userText;
	}

	private String userText = null;
	
	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return userText;
	}

	@Override
	public EN_CO2_RESULT_STATUS[] get_Values() {
		return EN_CO2_RESULT_STATUS.values();
	}

}
