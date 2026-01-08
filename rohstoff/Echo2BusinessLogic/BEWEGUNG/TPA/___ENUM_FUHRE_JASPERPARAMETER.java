/**
 * 
 */
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA;

import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

/**
 * @author martin
 *
 */
public enum ___ENUM_FUHRE_JASPERPARAMETER implements IF_enum_4_db_specified<___ENUM_FUHRE_JASPERPARAMETER>{
	
	//ab 20180501: ab hier eingesetzt reportparameter bei fuhrendrucken
	USE_AH7_STEUERTABELLE("use_ah7_steuertabelle")
	;
	
	
	private String parameterName = null;

	
	private ___ENUM_FUHRE_JASPERPARAMETER(String parametername) {
		this.parameterName = parametername;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db#db_val()
	 */
	@Override
	public String db_val() {
		return parameterName;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db#user_text()
	 */
	@Override
	public String user_text() {
		return parameterName;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db_specified#get_Values()
	 */
	@Override
	public ___ENUM_FUHRE_JASPERPARAMETER[] get_Values() {
		return ___ENUM_FUHRE_JASPERPARAMETER.values();
	}
	
}
