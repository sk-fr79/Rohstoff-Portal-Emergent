package panter.gmbh.indep.dataTools.TERM;

import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.exceptions.myException;

public class is_not_null implements Term {

	private IF_Field  	field = null;
	private String   	tableAlias = null;
	
	public is_not_null(IF_Field p_field) throws myException {
		super();
		this.field = p_field;
	}

	
	public is_not_null(IF_Field p_field, String p_tableAlias) throws myException {
		super();
		this.field = p_field;
		this.tableAlias = p_tableAlias;
	}

	@Override
	public String s() throws myException {
		if (S.isEmpty(this.tableAlias)) {
			return this.field.fn()+" IS NOT NULL";
		} else {
			return this.field.fn(this.tableAlias)+" IS NOT NULL";
		}
	}

}
