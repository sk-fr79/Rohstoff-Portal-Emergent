package panter.gmbh.indep.dataTools.TERM.SELECT;

import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.TERM.Term;
import panter.gmbh.indep.dataTools.TERM._TermCONST.ATTRIBUTES;
import panter.gmbh.indep.exceptions.myException;

public class FieldTerm implements Term {
	
	
	
	private String 		tablePrefix = null;
	private IF_Field  	field = null;
	private String 		fieldAlias = null;
	private Term   		fieldTerm = null;

	private boolean 	bShowAlias = true;
	

	private ATTRIBUTES  attrib = null;
	
//	/**
//	 * Bsp: A.NAME  NAME = tableName
//	 * @param p_fieldName
//	 */
//	public FieldTerm(String p_fieldName) {
//		super();
//		this.tablePrefix = null;
//		this.fieldName = p_fieldName;
//	}

	
	public FieldTerm(IF_Field field) {
		this.tablePrefix=field.fullTableName();
		this.field = field;
	}

	
	public FieldTerm(ATTRIBUTES atr, IF_Field field) {
		this.tablePrefix=field.fullTableName();
		this.field = field;
		this.attrib = atr;
	}

	public FieldTerm(ATTRIBUTES atr,String tablePraefix, IF_Field field) {
		this.tablePrefix=tablePraefix;
		if (S.isEmpty(this.tablePrefix)) {
			this.tablePrefix=field.fullTableName();
		}
		this.field = field;
		this.attrib = atr;
	}

	
	/**
	 * Bsp: A.NAME   = tablePrefix = A,  p_fieldName = tableName
	 * @param p_tablePrefix
	 * @param p_fieldName
	 */
	public FieldTerm(String p_tablePrefix, IF_Field p_field) {
		super();
		this.tablePrefix = p_tablePrefix;
		this.field = p_field;
	}

	
	
	/**
	 * Bsp: A.NAME1 AS VORNAME   = tablePrefix = A,  NAME = field, VORNAME = alias 
	 * @param p_tablePrefix
	 */
	public FieldTerm(String p_tablePrefix, IF_Field field, String p_fieldAlias) {
		super();
		this.tablePrefix = p_tablePrefix;
		this.field = field;
		this.fieldAlias = p_fieldAlias;
	}


	
	
	public FieldTerm(Term p_fieldTerm) {
		this.fieldTerm = p_fieldTerm;
	}
	

	@Override
	public String s() throws myException {
		if (this.fieldTerm==null) {
			String s_rueck = "";
			if (S.isFull(this.tablePrefix)) {
				s_rueck = s_rueck+(this.tablePrefix+"."+this.field.fieldName());
			} else {
				s_rueck = s_rueck+this.field.fieldName();
			}
			
			//2015-12-01: neue variante fuer UPPER und TRIM
			if (this.attrib!=null) {
				s_rueck = this.attrib.embedd(s_rueck);
			}
			
			if (S.isFull(this.fieldAlias) && this.bShowAlias) {
				s_rueck = s_rueck+(" AS "+this.fieldAlias);
			}
			
			return s_rueck;
		} else {
			return this.fieldTerm.s();
		}
	}

	
	public FieldTerm _showAlias(boolean showAlias){
		this.bShowAlias = showAlias;
		return this;
	}
	
	
	public FieldTerm get_termWithout_As_Part() {
		this.bShowAlias = false;
		return this;
//		return new FieldTerm(this.tablePrefix,this.field);
	}

	/**
	 * 
	 * @author martin
	 * @date 18.11.2020
	 *
	 * @param field
	 * @param alias
	 */
	public FieldTerm(IF_Field field, String alias) {
		this.tablePrefix=field.fullTableName();
		this.field = field;
		this.fieldAlias=alias;
	}

	
	public IF_Field get_field() {
	
		return field;
	}
	
}
