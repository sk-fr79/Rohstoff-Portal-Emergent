package panter.gmbh.indep.dataTools;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;


public class MySqlStatementBuilderDEL extends MySqlStatementBuilder_primitiv {

	private _TAB 		table = null;
	private String   	id_to_del = null;
	
	public MySqlStatementBuilderDEL(_TAB p_table, String p_id_to_del) {
		super();
		this.table = p_table;
		this.id_to_del = p_id_to_del;
	}

	@Override
	public String get_SQL_Single() throws myException {
		if (this.table==null || S.isEmpty(this.id_to_del)) {
			throw new myException(this,"you MUST set TABLE and ID  to delete !!!");
		}
		
		return "DELETE FROM "+bibE2.cTO()+"."+this.table.n()+" WHERE "+table.keyFieldName()+"="+this.id_to_del;
	}

}
