package panter.gmbh.basics4project.SANKTION;

import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_SANKTION_Ergebnis_typ implements IF_enum_4_db {
	TREFFER_NAME("Name")
	,TREFFER_ADRESSE("Anschrift")
	,TREFFER_AUSWEIS("Ausweis")
	,FEHLER_BEI_PRUEFUNG("Fehler bei Pruefung")
	,OK("OK")
	;

	private String typ = "";
	
	private ENUM_SANKTION_Ergebnis_typ(String oTyp) {
		typ = oTyp;
	}
	
	@Override
	public String db_val() {
		return this.name();
	}

	@Override
	public String user_text() {
		return typ;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_SANKTION_Ergebnis_typ.values(), false);
	}

}
