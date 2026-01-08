package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES;

import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class RUL_MASK_SQLFieldMAP extends Project_SQLFieldMAP 
{

	public RUL_MASK_SQLFieldMAP() throws myException 
	{
		super("JT_FIELD_RULE", "", false);
	
		/*
		 * beispiel fuer die definition von standard-werten
		 */
		this.get_("FIELD_NAME").set_cDefaultValueFormated("");
		this.get_("ID_FIELD_RULE").set_cDefaultValueFormated("");
		this.get_("ID_USER").set_cDefaultValueFormated("");
		this.get_("MODUL_KENNER").set_cDefaultValueFormated("");
		this.get_("RULE").set_cDefaultValueFormated("");
		this.get_("RULE_INFO").set_cDefaultValueFormated("");
		this.get_("RULETYPE").set_cDefaultValueFormated("");
		this.get_("TABLE_NAME").set_cDefaultValueFormated("");
//		this.get_("FIELD_NAME").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_FIELD_RULE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("ID_USER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("MODUL_KENNER").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("RULE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("RULE_INFO").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("RULETYPE").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
//		this.get_("TABLE_NAME").set_bIsNullableByUserDef_Before_INIT_SQLFieldMAP(false);
		
		this.initFields();
	}

}
