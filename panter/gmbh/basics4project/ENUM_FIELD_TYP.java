package panter.gmbh.basics4project;

import panter.gmbh.indep.enumtools.IF_enum_4_db;
import panter.gmbh.indep.exceptions.myException;

public enum ENUM_FIELD_TYP implements IF_enum_4_db {
	STRING("STRING"),
	NUMERIC("NUMERIC"),
	DATE("DATE");

	private String value;

	private ENUM_FIELD_TYP(String p_value) {
		this.value = p_value;
	}

	@Override
	public String db_val() {
		
		return this.value;
	}

	@Override
	public String user_text() {
		return this.value;
	}

	@Override
	public String[][] get_dd_Array(boolean emptyPairInFront) throws myException {
		return null;
	}
}
