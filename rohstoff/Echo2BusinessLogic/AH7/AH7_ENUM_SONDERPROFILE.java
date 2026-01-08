/**
 * 
 */
package rohstoff.Echo2BusinessLogic.AH7;

import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;

/**
 * @author martin
 *
 */
public enum AH7_ENUM_SONDERPROFILE implements IF_enum_4_db_specified<AH7_ENUM_SONDERPROFILE>{

	NOPROFILE("<spezielles Profile, ausdefiniert>")
	,WE_PROFILE("Wareneingang (Lieferant liefert in UNSER Lager im Inland)")
	,WA_PROFILE("Warenausgang (Wir liefern von UNSEREM Lager im Inland zu Abnehmer)")
	,STRECKE_START_IM_INLAND("Strecke, Start im Inland")
	,STRECKE_START_IM_AUSLAND("Strecke, Start im Ausland")
	;

	private String  text = null;
	
	
	
	/**
	 * @param p_text
	 */
	private AH7_ENUM_SONDERPROFILE(String p_text) {
		this.text = p_text;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db#db_val()
	 */
	@Override
	public String db_val() {
		return this.name();
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db#user_text()
	 */
	@Override
	public String user_text() {
		return this.text;
	}

	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db#user_text_lang()
	 */
	@Override
	public String user_text_lang() {
		return this.text;
	}

	
	/* (non-Javadoc)
	 * @see panter.gmbh.indep.enumtools.IF_enum_4_db_specified#get_Values()
	 */
	@Override
	public AH7_ENUM_SONDERPROFILE[] get_Values() {
		return AH7_ENUM_SONDERPROFILE.values();
	}
	
	
	public static String[][] getDDArray() {
		String[][] c_ret = new String[AH7_ENUM_SONDERPROFILE.values().length][2];
		
		int i=0;
		for (AH7_ENUM_SONDERPROFILE s: AH7_ENUM_SONDERPROFILE.values()) {
			c_ret[i][0]=s.text;	c_ret[i][1]=s.db_val();
			i++;
		}
		return c_ret;
	}
}
