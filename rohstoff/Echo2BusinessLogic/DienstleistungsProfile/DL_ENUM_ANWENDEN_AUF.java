/**
 * rohstoff.Echo2BusinessLogic.DienstleistungsProfile
 * @author martin
 * @date 16.09.2019
 * 
 */
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;

import panter.gmbh.indep.S;
import panter.gmbh.indep.enumtools.IF_enum_4_db_specified;
import panter.gmbh.indep.myVectors.HMAP;

/**
 * @author martin
 * @date 16.09.2019
 *
 */
public enum DL_ENUM_ANWENDEN_AUF implements IF_enum_4_db_specified<DL_ENUM_ANWENDEN_AUF> {
	 MIT_ABRRECH("Abrechnungsfuhren")
	,OHNE_ABRECH("Fuhren ohne Abrechnung")
	,ALLE("Alle Fuhren")
	
	
    ;
	private String m_userText = null;
	private DL_ENUM_ANWENDEN_AUF(String userText) {
		this.m_userText = S.NN(userText,"<-->");
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
	public DL_ENUM_ANWENDEN_AUF[] get_Values() {
		return DL_ENUM_ANWENDEN_AUF.values();
	}

	
	public HMAP<String, String> getSelectorHMAP() {
		 HMAP<String, String> map = new HMAP<>();
		 
		 map.put("-", "");
		 
		 for (DL_ENUM_ANWENDEN_AUF en: DL_ENUM_ANWENDEN_AUF.values()) {
			 map.put(en.user_text(), en.db_val());
		 }
		 
		 return map;
	}

}
