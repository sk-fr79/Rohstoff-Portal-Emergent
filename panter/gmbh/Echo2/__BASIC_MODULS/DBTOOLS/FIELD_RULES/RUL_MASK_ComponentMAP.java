package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_COLUMN_DROPDOWN;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_MODULENAMES_DROPDOWN;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_TABLE_DROPDOWN;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES.INTERACTIVE_SETTER_VALIDATOR.SET_FIELDS_WITH_REGELTYPE;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;


public class RUL_MASK_ComponentMAP extends E2_ComponentMAP 
{

	private DB_Component_TABLE_DROPDOWN 	oTables = null;
	private DB_Component_COLUMN_DROPDOWN 	oColumns = null;

	public RUL_MASK_ComponentMAP() throws myException
	{
		super(new RUL_MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		

		oTables = new DB_Component_TABLE_DROPDOWN(oFM.get_(_DB.FIELD_RULE$TABLE_NAME),false,bibARR.ARR_EMPTY_DB_VALUE_IN_FRONT);
		oColumns = new DB_Component_COLUMN_DROPDOWN(oFM.get_(_DB.FIELD_RULE$FIELD_NAME),true);
		oColumns.set_cDefineAction_IF_ValueNotFound(MyE2_SelectFieldWithParameters.SHOW_EMPTY_STRING_IF_VALUE_NOT_FOUND);
		oColumns.RefreshComboboxFromSQL();

		oTables.add_oActionAgent(new  XX_ActionAgent() {
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				oColumns.set_ActiveInhalt_or_FirstInhalt("");
				oColumns.SetParameter("#TABLE_NAME#", oTables.get_ActualWert());
				oColumns.RefreshComboboxFromSQL();
			}
		});

		oTables.setWidth(new Extent(300));
		oColumns.setWidth(new Extent(300));
		
		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label(oFM.get_(_DB.FIELD_RULE$ID_FIELD_RULE)), new MyE2_String("ID"));
		this.add_Component(new DB_Component_MODULENAMES_DROPDOWN(oFM.get_(_DB.FIELD_RULE$MODUL_KENNER),new Extent(300),null), new MyE2_String("Modul-Schlüssel"));
		this.add_Component(this.oTables, new MyE2_String("Tabelle"));
		this.add_Component(this.oColumns, new MyE2_String("Feldname"));
		this.add_Component(new RUL_DB_CompUserDropDown(oFM.get_(_DB.FIELD_RULE$ID_USER)), new MyE2_String("Benutzer"));
		this.add_Component(new RUL_DB_RuleTypes(oFM.get_(_DB.FIELD_RULE$RULETYPE)), new MyE2_String("Regel-Typ"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.FIELD_RULE$RULE),true,500,100,false), new MyE2_String("Regel-Inhalt"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_(_DB.FIELD_RULE$RULE_INFO),true,500,100,false), new MyE2_String("Regel-Information"));
		

		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new RUL_MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new RUL_MASK_FORMATING_Agent());
		
		this.register_Interactiv_settings_validation(_DB.FIELD_RULE$RULETYPE, new SET_FIELDS_WITH_REGELTYPE());
		
	}
	
}
