package panter.gmbh.indep.dataTools;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class MySqlStatementBuilder_Simple extends MySqlStatementBuilder_primitiv {

	
	public static enum SQL_TYPES {
		INSERT,
		UPDATE,
		DELETE,
		SIMPLESQL
	}
	
	
	private String id_toDel = null;
	private String id_FieldName = null;

	
	private String c_sqlstring = null;

//	public MySqlStatementBuilder_Simple(String TableName, String cID_FieldName, String cID) {
//		super(MySqlStatementBuilder_TypeDefined.SQL_TYPES.SIMPLESQL,TableName);
//		
//		this.id_toDel = cID;
//		this.id_FieldName = cID_FieldName;
//		
//	}

	
	public MySqlStatementBuilder_Simple(String sql_string) throws myException {
		super();
		if (S.isFull(sql_string)) {
			this.c_sqlstring = sql_string;
		} else {
			throw new myException(this,"Empty sql-string in constructor not allowed !!");
		}
	}
	
	
	
	@Override
	public String get_SQL_Single() throws myException {
		
		if (S.isEmpty(this.c_sqlstring)) {
			if (S.isEmpty(this.id_toDel) || S.isEmpty(this.id_FieldName) || S.isEmpty(this.get_cTableName())) {
				throw new myException(this,"Something is empty !!");
			}
			return "DELETE FROM "+bibE2.cTO()+"."+this.get_cTableName()+" WHERE "+this.id_FieldName+"="+this.id_toDel;
		} else {
			return this.c_sqlstring;
		}
	}
	
}
