package panter.gmbh.indep.dataTools.TERM;

import java.util.Vector;

import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;

public class In implements Term  {
	
	private String s_term = null;
	
	
	/**
	 * 
	 * @param tabAlias (table-alias)
	 * @param field
	 * @param values
	 * @throws myException
	 */
	public In(String tabAlias, IF_Field field, Vector<String> values) throws myException {
		super();
		
		this.s_term = null;
		String tableName = field.fullTableName();
		String fieldname = field.fieldName();
		String tableAlias = S.NN(tabAlias, tableName);
		
		
		if (values == null || values.size()==0) {
			this.s_term= tableAlias+"."+fieldname+" IN (NULL) ";
		} else {
			_TAB tab = _TAB.find_Table(tableName);
			MyMetaFieldDEF  mf = DB_STATICS.get_MetaDef(tab, field);
			MyMetaFieldDEF.DBTYPE typ = MyMetaFieldDEF.DBTYPE.get_DBTYPE(mf.get_FieldType());
			
			Vector<String> vFormatedVals = new Vector<String>();
			for (String value: values) {
				String db_wert = mf.get_cStringForDataBase(value, false, false);
				vFormatedVals.add(db_wert);
				
			}
			if (typ!=null && typ.baseType()==MyMetaFieldDEF.BASETYPE.DATE) {
				this.s_term = "TO_CHAR("+tableAlias+"."+fieldname+",'YYYY-MM-DD') IN ("+bibALL.Concatenate(vFormatedVals, ",", "NULL")+")";				
			} else {
				this.s_term = tableAlias+"."+fieldname+" IN ("+bibALL.Concatenate(vFormatedVals, ",", "NULL")+")";	
			}	
		}
		
		this.s_term="("+this.s_term+")";
		
	}


	
	/**
	 * 
	 * @param field
	 * @param value
	 * @throws myException
	 */
	public In(IF_Field field, Vector<String> values) throws myException {
		this(field._t().fullTableName(),field,values);
	}

	
	public In(IF_Field field, String... values) throws myException {
		this(field, new VEK<String>()._a(values));
	}
	
	
	
	@Override
	public String s() throws myException {
		return this.s_term;
	}

}
