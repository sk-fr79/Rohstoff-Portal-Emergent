package rohstoff.Echo2BusinessLogic.AH7;

import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum AH7__ENUM_TEILNEHMER   implements IF_enum_4_db {
	 
	ADRESSE_MANDANT("Mandantenadresse")
	,ADRESSE_START_GEO("Quelle (geografisch)")
	,ADRESSE_START_JUR("Quellfirma (juristisch)")
	,ADRESSE_START_GEO_DRITTBESITZ("Drittbesitzer Startlager ")
	,ADRESSE_ZIEL_GEO("Ziel (geografisch)")
	,ADRESSE_ZIEL_JUR("Zielfirma (juristisch))")
	,ADRESSE_ZIEL_GEO_DRITTBESITZ("Drittbesitzer Ziellager")
	;
	
	
	private String userText = null;
	
	/**
	 * @param p_userText
	 */
	private AH7__ENUM_TEILNEHMER(String p_userText) {
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
		if (this==ADRESSE_MANDANT) {
			try {
				return bibALL.get_RECORD_MANDANT().get___KETTE(MANDANT.name1,MANDANT.name2);
			} catch (myException e) {
				e.printStackTrace();
				return userText;
			}
		} else {
			return userText;
		}
	}


	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(AH7__ENUM_TEILNEHMER.values(), emptyPairInFront);
	}

	
	public static String[][] get_ddArray(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(AH7__ENUM_TEILNEHMER.values(), emptyPairInFront);
	}
	
	
	/**
	 * 
	 * @param dbVal
	 * @return zu dbVal korrelierende AH7__ENUM_TEILNEHMER oder bei S.isEmpty(dbVal) null, sonst exception
	 * @throws myException
	 */
	public static AH7__ENUM_TEILNEHMER getTeilnehmer(String dbVal) throws myException {
		for (AH7__ENUM_TEILNEHMER t: AH7__ENUM_TEILNEHMER.values()) {
			if (t.db_val().equals(dbVal)) {
				return t;
			}
		}
		if (S.isFull(dbVal)) {
			throw new myException("Cannot correlate <"+dbVal+"> to member of AH7__ENUM_TEILNEHMER!");
		} else {
			return null;
		}
	}
	
	
	
}
