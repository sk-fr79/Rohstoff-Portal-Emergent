package panter.gmbh.indep.dataTools.TERM.SELECT;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.exceptions.myException;

public class TableTerm implements Term {

	private String table = null;
	private String alias = null;
	private Term   tabTerm = null;
	
	public TableTerm(String p_table) {
		super();
		this.table = p_table;
		this.alias = p_table;
	}

	public TableTerm(_TAB tab) {
		super();
		this.table = tab.fullTableName();
		this.alias = tab.fullTableName();
		
	}
	
	/**
	 * @author martin
	 * @date 31.10.2019
	 *
	 * @param tab
	 * @param alias
	 */
	public TableTerm(_TAB tab, String alias) {
		super();
		this.table = tab.fullTableName();
		this.alias = S.NN(alias,tab.fullTableName());
		
	}

	public TableTerm(String p_table, String p_alias) {
		super();
		this.table = p_table;
		this.alias = p_alias;
	}
	
	public TableTerm(Term p_tabTerm) {
		this.tabTerm = p_tabTerm;
	}
	

	@Override
	public String s() throws myException {
		if (this.tabTerm==null) {
			return this.table+" "+this.alias;
		} else {
			return this.tabTerm.s();
		}
	}

	
	/**
	 * 
	 * @param field
	 * @return fieldTerm with as : "alias.field"
	 */
	public FieldTerm  ft(IF_Field field) throws myException {
		if (this.tabTerm == null) {
			if (field.tn().equals(this.table)) {
				return new FieldTerm(this.alias, field);
			} else {
				throw new myException(this,"Error, ft() can only be used when field is from the table !");
			}
		} else {
			throw new myException(this,"Error, ft() can only be used when table and alias is set !");
		}
	}
	
	
	public String alias() throws myException {
		if (this.tabTerm == null) {
			return this.alias;
		} else {
			throw new myException(this,"Error, alias() can only be used when table and alias is set !");
		}
	}
}
