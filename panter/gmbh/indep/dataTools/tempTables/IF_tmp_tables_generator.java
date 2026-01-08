package panter.gmbh.indep.dataTools.tempTables;

import panter.gmbh.indep.exceptions.myException;

public interface IF_tmp_tables_generator {
	
	public boolean 					check_if_present() throws myException;
	public TT_GENERATE_RESULT_VALUES 	build_table(boolean b_commit) throws myException;
	
}
