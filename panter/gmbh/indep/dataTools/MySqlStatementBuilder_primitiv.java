package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.exceptions.myException;

public abstract class MySqlStatementBuilder_primitiv extends MySqlStatementBuilder {

	public abstract String get_SQL_Single() throws myException;
	
}
