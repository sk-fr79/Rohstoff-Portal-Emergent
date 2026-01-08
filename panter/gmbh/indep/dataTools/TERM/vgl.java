package panter.gmbh.indep.dataTools.TERM;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF.BASETYPE;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF.DBTYPE;
import panter.gmbh.indep.dataTools.TERM._TermCONST.COMP;
import panter.gmbh.indep.exceptions.myException;

public class vgl implements Term  {
	
	private String s_term = null;
	
	
	
	/**
	 * 
	 * @param tabAlias (table-alias)
	 * @param field
	 * @param comp
	 * @param value
	 * @throws myException
	 */
	public vgl(String tabAlias, IF_Field field,String c_comp, String value) throws myException {
		super();
		
		this.s_term = null;
		String tableName = field.fullTableName();
		String fieldname = field.fieldName();
		String tableAlias = S.NN(tabAlias, tableName);
		
		
		if (value == null) {
			this.s_term= tableAlias+"."+fieldname+" IS NULL";
		} else if (	field.fieldTextLength().intValue()==1 && 
					DBTYPE.get_DBTYPE(field.fieldType())!=null && 
					DBTYPE.get_DBTYPE(field.fieldType()).baseType()==BASETYPE.CHAR &&
					"YN".contains(S.NN(value,"").toUpperCase())) {
			
			_TAB tab = _TAB.find_Table(tableName);
			MyMetaFieldDEF  mf = DB_STATICS.get_MetaDef(tab, field);
			String db_wert = mf.get_cStringForDataBase(value, false, false);
			this.s_term = "NVL("+tableAlias+"."+fieldname+",'N')"+c_comp+db_wert;
	
		} else {
			_TAB tab = _TAB.find_Table(tableName);
			MyMetaFieldDEF  mf = DB_STATICS.get_MetaDef(tab, field);
			MyMetaFieldDEF.DBTYPE typ = MyMetaFieldDEF.DBTYPE.get_DBTYPE(mf.get_FieldType());
			String db_wert = mf.get_cStringForDataBase(value, false, false);

			if (typ!=null && typ.baseType()==MyMetaFieldDEF.BASETYPE.DATE) {
				this.s_term = "TO_CHAR("+tableAlias+"."+fieldname+",'YYYY-MM-DD')"+c_comp+db_wert;				
			} else {
				this.s_term = tableAlias+"."+fieldname+c_comp+db_wert;
			}
		}
	}

	
	/**
	 * 
	 * @param tabAlias (table-alias)
	 * @param field
	 * @param comp
	 * @param value
	 * @throws myException
	 */
	public vgl(String tabAlias, IF_Field field, COMP comp, String value) throws myException {
		this(tabAlias,field,comp.s(),value);
	}


	
	/**
	 * 
	 * @param tabAlias (table-alias)
	 * @param field
	 * @param value
	 * @throws myException
	 */
	public vgl(String tabAlias, IF_Field field, String value) throws myException {
		this(tabAlias,field,COMP.EQ.s(),value);
	}

	
	
	
	
	/**
	 * 
	 * @param field
	 * @param comp
	 * @param value
	 * @throws myException
	 */
	public vgl(IF_Field field, COMP comp, String value) throws myException {
		this(null,field,comp,value);
	}

	/**
	 * 
	 * @param field
	 * @param value
	 * @throws myException
	 */
	public vgl(IF_Field field, String value) throws myException {
		this(null,field,COMP.EQ,value);
	}

	
	public vgl(IF_Field field_l,  IF_Field field_r) {
		this(field_l,COMP.EQ,field_r);
	}
	
	public vgl(IF_Field field_l, COMP comp, IF_Field field_r) {
		this.s_term = null;
		String tableName_l = field_l.fullTableName();
		String fieldname_l = field_l.fieldName();
		
		String tableName_r = field_r.fullTableName();
		String fieldname_r = field_r.fieldName();
		
		this.s_term = tableName_l+"."+fieldname_l+" "+comp.s()+tableName_r+"."+fieldname_r;
	}
	
	public vgl(IF_Field field_l, COMP comp, Term right) throws myException {
		this.s_term = null;
		String tableName_l = field_l.fullTableName();
		String fieldname_l = field_l.fieldName();
		
		this.s_term = tableName_l+"."+fieldname_l+" "+comp.s()+right.s();
	}
	

	
	public vgl(Term termLeft, COMP comp, Term termRight) throws myException {
		this.s_term = termLeft.s()+comp.s()+termRight.s();
	}
	
	public vgl(Term termLeft, Term termRight) throws myException {
		this(termLeft,COMP.EQ,termRight);
	}
	
	
	//2019-01-04: weiterer konstruktor
	public vgl(String aliasField_l,  IF_Field field_l, COMP comp, String aliasField_r,IF_Field field_r) {
		this.s_term = null;
		String fieldname_l = field_l.fieldName();
		
		String fieldname_r = field_r.fieldName();
		
		this.s_term = aliasField_l+"."+fieldname_l+" "+comp.s()+aliasField_r+"."+fieldname_r;
	}

	
	//2019-01-04: weiterer konstruktor
	public vgl(String aliasField_l,  IF_Field field_l,  String aliasField_r,IF_Field field_r) {
		this.s_term = null;
		String fieldname_l = field_l.fieldName();
		
		String fieldname_r = field_r.fieldName();
		
		this.s_term = aliasField_l+"."+fieldname_l+" "+COMP.EQ.s()+aliasField_r+"."+fieldname_r;
	}



	@Override
	public String s() throws myException {
		return this.s_term;
	}

}
