package panter.gmbh.basics4project.validation;

import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_VALID_SUBTHEME  implements IF_enum_4_db{

	KOPFSATZ("Kopfsatz")
	,POSITIONSSATZ("Positionssatz")
	;

	private String beschreibung = null;

	/**
	 * @param p_beschreibung
	 */
	private ENUM_VALID_SUBTHEME(String p_beschreibung) {
		this.beschreibung = p_beschreibung;
	}
	

	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return beschreibung;
	}

	@Override
	public String user_text_lang() {
		return beschreibung;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_VALID_SUBTHEME.values(), emptyPairInFront);
	}


}
