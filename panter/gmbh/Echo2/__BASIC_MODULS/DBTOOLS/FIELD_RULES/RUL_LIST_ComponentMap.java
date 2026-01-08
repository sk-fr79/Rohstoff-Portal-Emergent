package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;

public class RUL_LIST_ComponentMap extends E2_ComponentMAP 
{

	public RUL_LIST_ComponentMap() throws myException
	{
		super(new RUL_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
		this.add_Component(RUL_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,		new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(RUL_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		RUL_DB_CompUserDropDown  		oDDUser = 	new RUL_DB_CompUserDropDown(oFM.get_(_DB.FIELD_RULE$ID_USER));
		RUL_DB_RuleTypes 				oDD_Rules= 	new RUL_DB_RuleTypes(oFM.get_(_DB.FIELD_RULE$RULETYPE));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.FIELD_RULE$MODUL_KENNER)), 		new MyE2_String("Modul"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.FIELD_RULE$TABLE_NAME)), 		new MyE2_String("Tabelle"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.FIELD_RULE$FIELD_NAME)), 		new MyE2_String("Feldname"));
		this.add_Component(oDDUser, 															new MyE2_String("Benutzer"));
		this.add_Component(oDD_Rules, 															new MyE2_String("Regeltyp"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.FIELD_RULE$RULE)), 				new MyE2_String("Regel"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.FIELD_RULE$RULE_INFO)), 		new MyE2_String("Info zur Regel"));
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.FIELD_RULE$ID_FIELD_RULE)), 	new MyE2_String("ID"));
		

		this.set_oSubQueryAgent(new RUL_LIST_FORMATING_Agent());
	}

}
