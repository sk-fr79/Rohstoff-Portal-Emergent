package panter.gmbh.indep.dataTools.TERM;

import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class VglNotNull implements Term {

	private IF_Field  	field = null;
	private String   	tableAlias = null;
	
	public VglNotNull(IF_Field p_field) throws myException {
		super();
		this.field = p_field;
		this.tableAlias = p_field.fullTableName();
	}

	
	public VglNotNull(IF_Field p_field, String p_tableAlias) throws myException {
		super();
		this.field = p_field;
		this.tableAlias = p_tableAlias;
	}

	@Override
	public String s() throws myException {
		if (S.isEmpty(this.tableAlias)) {
			return this.field.tnfn()+" IS NOT NULL";
		} else {
			return this.field.fn(this.tableAlias)+" IS NOT NULL";
		}
	}

}
