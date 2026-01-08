package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.exceptions.myException;

public class DB_META_RECORDLIST extends MyRECORD_LIST {

	private String c_TableName = null;
	
	public DB_META_RECORDLIST(String TABLENAME) throws myException {
		super(DB_META.get_FieldsQueryOracle(TABLENAME),"COLUMN_NAME");
		this.c_TableName = TABLENAME;
	}
	
	public String get_TABLENAME() {
		return this.c_TableName;
	}

	
	public String get_DATA_TYPE(String COLNAME) throws myException {
		return this.get(COLNAME).get_UnFormatedValue("DATA_TYPE");
	}

	public int get_CHAR_LENGTH(String COLNAME) throws myException {
		return this.get(COLNAME).get_longValue("CHAR_LENGTH",-1l).intValue();
	}

	/*
	 * der normalfall ist nullable = Y
	 */
	public boolean get_bNULLABLE(String COLNAME) throws myException {
		return (this.get(COLNAME).get_UnFormatedValue("NULLABLE","Y").equals("Y"));
	}

	public int get_DATA_PRECISION(String COLNAME) throws myException {
		return this.get(COLNAME).get_longValue("DATA_PRECISION",-1l).intValue();
	}
	
	public int get_DATA_SCALE(String COLNAME) throws myException {
		return this.get(COLNAME).get_longValue("DATA_SCALE",-1l).intValue();
	}
	
	public String get_DATA_DEFAULT(String COLNAME) throws myException {
		return this.get(COLNAME).get_UnFormatedValue("DATA_DEFAULT");
	}
	
	
}
