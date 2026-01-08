/**
 * rohstoff.Echo2BusinessLogic.DienstleistungsProfile
 * @author martin
 * @date 22.08.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

/**
 * @author martin
 * @date 22.08.2019
 *
 */
public enum DL_ENUM_TYP_MENGEN_CALC implements IF_enum_4_db_specified<DL_ENUM_TYP_MENGEN_CALC> {
	PRO_LADUNG("Pro Ladung")
	, PRO_MENGE("Basierend auf der transportierten Menge")
	;


	private String m_userText = null;
	private DL_ENUM_TYP_MENGEN_CALC(String userText) {
		this.m_userText =S.NN(userText,"<-->");
	}
	
	
	@Override
	public String db_val() {
		return name();
	}

	@Override
	public String user_text() {
		return m_userText;
	}

	@Override
	public DL_ENUM_TYP_MENGEN_CALC[] get_Values() {
		return DL_ENUM_TYP_MENGEN_CALC.values();
	}

	
}
