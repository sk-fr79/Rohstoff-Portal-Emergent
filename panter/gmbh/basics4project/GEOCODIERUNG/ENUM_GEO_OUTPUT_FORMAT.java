package panter.gmbh.basics4project.GEOCODIERUNG;

import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.enumtools.bibENUM;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_GEO_OUTPUT_FORMAT implements IF_enum_4_db{
	HTML("html"),
	XML("xml"),
	JSON("json"),
	JSON_2("jsonv2");

	private String value;
	
	private ENUM_GEO_OUTPUT_FORMAT(String p_value) {
		value = p_value;
	}
	
	@Override
	public String db_val() {
		return value;
	}

	@Override
	public String user_text() {
		return value;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return bibENUM.dd_array(ENUM_GEO_OUTPUT_FORMAT.values(),false);
	}
}
