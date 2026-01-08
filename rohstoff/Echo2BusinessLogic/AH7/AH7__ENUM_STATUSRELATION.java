package rohstoff.Echo2BusinessLogic.AH7;

import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum AH7__ENUM_STATUSRELATION  implements IF_enum_4_db {

	UNDEF("Undefiniert, Relation hat noch keine geprüften AH7-Adressen / gar keine Adressen hinterlegt")
	,INACTIVE("Definiert, Relation hat bereits AH7-Adressen, muss noch bearbeitet und aktiviert werden")
	,ACTIVE("Aktiviert und bereit zur Nutzung")
	;

	private String userText = null;
	
	/**
	 * @param p_userText
	 */
	private AH7__ENUM_STATUSRELATION(String p_userText) {
		this.userText = p_userText;
	}

	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return userText;
	}

	@Override
	public String user_text_lang() {
		return userText;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(AH7__ENUM_STATUSRELATION.values(), emptyPairInFront);
	}

	
	public static String[][] get_ddArray(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(AH7__ENUM_STATUSRELATION.values(), emptyPairInFront);
	}

	public static AH7__ENUM_STATUSRELATION getStatus(String dbVal) throws myException {
		for (AH7__ENUM_STATUSRELATION t: AH7__ENUM_STATUSRELATION.values()) {
			if (t.db_val().equals(dbVal)) {
				return t;
			}
		}
		throw new myException("Cannot correlate <"+dbVal+"> to member of AH7__ENUM_STATUSRELATION!");
	}

}
