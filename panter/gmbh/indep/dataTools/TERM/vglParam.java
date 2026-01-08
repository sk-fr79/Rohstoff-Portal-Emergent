package panter.gmbh.indep.dataTools.TERM;

import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.dataTools.TERM.SELECT.FieldTerm;
import panter.gmbh.indep.dataTools.TERM.SELECT.TableTerm;
import panter.gmbh.indep.exceptions.myException;

public class vglParam implements Term  {
	
	private String s_term = null;
	
	
	
	/**
	 * 
	 * @param tabAlias (table-alias)
	 * @param field
	 * @param comp
	 * @throws myException
	 */
	public vglParam(String tabAlias, IF_Field field,String c_comp) throws myException {
		super();
		
		this.s_term = null;
		String tableName = field.fullTableName();
		String fieldname = field.fieldName();
		String tableAlias = S.NN(tabAlias, tableName);
		
		this.s_term = tableAlias+"."+fieldname+c_comp+"?";
	}

	
	/**
	 * 
	 * @param tabAlias (table-alias)
	 * @param field
	 * @param comp
	 * @throws myException
	 */
	public vglParam(String tabAlias, IF_Field field, COMP comp) throws myException {
		this(tabAlias,field,comp.s());
	}

	
	/**
	 * 
	 * @param tabAlias (table-alias)
	 * @param field
	 * @param value
	 * @throws myException
	 */
	public vglParam(String tabAlias, IF_Field field) throws myException {
		this(tabAlias,field,COMP.EQ.s());
	}
	
	
	/**
	 * 
	 * @param field
	 * @param comp
	 * @param value
	 * @throws myException
	 */
	public vglParam(IF_Field field, COMP comp) throws myException {
		this(null,field,comp);
	}

	/**
	 * 
	 * @param field
	 * @param value
	 * @throws myException
	 */
	public vglParam(IF_Field field) throws myException {
		this(null,field,COMP.EQ);
	}

	
	/**
	 * 
	 * @author martin
	 * @date 07.11.2019
	 *
	 * @param table
	 * @param field
	 * @throws myException
	 */
	public vglParam(String tableAlias, String field) throws myException {
		this.s_term = tableAlias+"."+field+"=?";
	}
	
	
	
	@Override
	public String s() throws myException {
		return this.s_term;
	}

}
