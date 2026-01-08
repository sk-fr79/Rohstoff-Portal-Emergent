package panter.gmbh.Echo2.RB.COMP.BETA;

import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_MASKNAME implements IF_enum_4_db {
	TEST			("TEST"				,	"TEST"			)
	,BEWEGUNG_INFO	("BEWEGUNG_INFO"	,	"BEWEGUNG_INFO"	)
	
	;
	
	private String name;
	private String beschreibung;
	
	private ENUM_MASKNAME(String p_name, String p_beschreibung ) {
		this.name= p_name;
		this.beschreibung = p_beschreibung;
	}
	
	@Override
	public String db_val() {
		return name;
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
		return bibENUM.dd_array(ENUM_MASKNAME.values(), emptyPairInFront);
	}

}
