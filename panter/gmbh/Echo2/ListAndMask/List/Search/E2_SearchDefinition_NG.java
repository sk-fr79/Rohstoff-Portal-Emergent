package panter.gmbh.Echo2.ListAndMask.List.Search;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.DB_META;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;

public class E2_SearchDefinition_NG extends E2_SearchDefinition {

	
	public E2_SearchDefinition_NG(IF_Field p_field, MyE2_String listText) throws myException {
		super();
		this.init(true, this.generate_searchString(p_field), listText, null, null);
	}

	
	private String generate_searchString(IF_Field p_field) throws myException {

		_TAB table = _TAB.find_Table(p_field.fullTableName());
        
        if (table == null) {
        	throw new myException(this, "Table not found :"+p_field.fullTableName());
        }
		
       	String cWhereBlock = "";
        //String cFieldName = oEXT_DB.get_oSQLField().get_cTableName()+"."+oEXT_DB.get_oSQLField().get_cFieldName();
       	MyMetaFieldDEF  fdef = p_field.generate_MetaFieldDef();
		if (fdef.get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_TEXT))
		{
		    cWhereBlock =  "UPPER(RTRIM("+p_field.tnfn()+")) LIKE UPPER('%#WERT#%')";
		}
		else if (fdef.get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_NUMMER))
		{
		    cWhereBlock = "TO_CHAR("+p_field.tnfn()+") = '#WERT#'";
		}
		else if (fdef.get_cBASIC_Field_TYPE().equals(DB_META.BASIC_TYPE_DATUM))
		{
			String cWhereBlock1 = "TO_CHAR("+p_field.tnfn()+",'DD.MM.YYYY') LIKE '%#WERT#%' ";
			String cWhereBlock2 = "TO_CHAR("+p_field.tnfn()+",'DDMMYYYY') = '#WERT#' ";
			String cWhereBlock3 = "TO_CHAR("+p_field.tnfn()+",'DDMMYY') = '#WERT#' ";
		    cWhereBlock= "("+cWhereBlock1+" OR "+cWhereBlock2+" OR "+cWhereBlock3+")";
		}
		else
		{
		    throw new myException("mySearchDefinition:makeWhereBlock:  Field-Types "+fdef.get_cBASIC_Field_TYPE()+" is not allowed !");
		}
		
		String cQuery =  "SELECT " +table.fullTableName()+"."+table.keyFieldName()+
                " FROM "+bibALL.get_TABLEOWNER()+"."+table.fullTableName()+" WHERE "+ cWhereBlock ;
		
		return cQuery;
	}
}
