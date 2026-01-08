package panter.gmbh.indep.dataTools.tempTables;


public enum TT_GENERATE_RESULT_VALUES {

	CREATE_OK(true),
	TABLE_WAS_THERE(true),
	CREATE_FAILED(false)
	
	;
	
	private boolean b_ok = false; 
	
	private TT_GENERATE_RESULT_VALUES(boolean ok) {
		this.b_ok = ok;
	}
	
	public boolean is_ok() {
		return this.b_ok;
	}
	
}



