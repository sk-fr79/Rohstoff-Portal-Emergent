package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_MAP_Set_And_Valid;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_COLUMN_DROPDOWN;
import panter.gmbh.Echo2.__BASIC_COMPONENTS.DB_Component_TABLE_DROPDOWN;
import panter.gmbh.Echo2.components.MyE2_SelectFieldWithParameters;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox;
import panter.gmbh.Echo2.components.DB.MyE2_DB_TextField;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TRIGGER_DEF;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.SQLMaskInputMAP;
import panter.gmbh.indep.exceptions.myException;


public class LOGTRIG__MASK_ComponentMAP extends E2_ComponentMAP 
{

	DB_Component_TABLE_DROPDOWN 	oTables = null;
	DB_Component_COLUMN_DROPDOWN 	oColumns = null;
	MyE2_DB_TextField 				oTriggername = null;

	
	public LOGTRIG__MASK_ComponentMAP() throws myException
	{
		super(new LOGTRIG__MASK_SQLFieldMAP());
		
		SQLFieldMAP oFM = this.get_oSQLFieldMAP();
		
		oTables = new DB_Component_TABLE_DROPDOWN(oFM.get_("TABELLE"),null);
		oColumns = new DB_Component_COLUMN_DROPDOWN(oFM.get_("SPALTE"));
		oColumns.set_cDefineAction_IF_ValueNotFound(MyE2_SelectFieldWithParameters.SHOW_EMPTY_STRING_IF_VALUE_NOT_FOUND);
		oColumns.RefreshComboboxFromSQL();
		
		oTables.add_oActionAgent(new  XX_ActionAgent() {
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				oColumns.set_ActiveInhalt_or_FirstInhalt("");
				oColumns.SetParameter("#TABLE_NAME#", oTables.get_ActualWert());
				oColumns.RefreshComboboxFromSQL();
			}
		});
		
		oTriggername = new MyE2_DB_TextField(oFM.get_("TRIGG_NAME"),true,350,30,false);
	    oTriggername.EXT().set_bDisabledFromBasic(true);
		
		
		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_TextField(oFM.get_("ID_TRIGGER_DEF"),true,200,10,false), new MyE2_String("ID_TRIGGER_DEF"));
		this.add_Component(oTables, new MyE2_String("Tabelle"));
		this.add_Component(oColumns, new MyE2_String("Spalte"));
		this.add_Component(oTriggername, new MyE2_String("Trigger-Name"));
		this.add_Component(new MyE2_DB_TextField(oFM.get_("BESCHREIBUNG"),true,700,200,false), new MyE2_String("Eintrag"));
		this.add_Component(new MyE2_DB_CheckBox(oFM.get_(RECORD_TRIGGER_DEF.FIELD__VORDEFINIERT),true), new MyE2_String("Vordefiniert"));

		oColumns.add_oActionAgent(new XX_ActionAgent() {
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				
				String sTable = oTables.get_ActualWert();
				String sColumn = oColumns.get_ActualWert();
				
//				// Tabellenname abkürzen, falls nötig
//				if (sTable.length() > 20)
//				{
//					sTable = sTable.substring(0, 19);
//				}
				if (S.isFull(sTable) && S.isFull(sColumn))
				{
					((MyE2_DB_TextField)get__Comp("TRIGG_NAME")).setText(LOGTRIG__CONST.generateTriggerName(sTable, sColumn) );
				}
				else
				{
					((MyE2_DB_TextField)get__Comp("TRIGG_NAME")).setText("");
				}
			}
		});

		
		
		/*
 		 * definiert die maskeneinstellungen evtl. vor dem bearbeiten
		 */
		this.set_oMAPSettingAgent(new LOGTRIG__MASK_MapSettingAgent());
		
		/*
		 * ermoeglicht formatierungen von zusatzinfos in der maske
		 */
		this.set_oSubQueryAgent(new LOGTRIG__MASK_FORMATING_Agent());
		
		
		//2013-01-08: dafuer sorgen, dass vordefinierte Triggereintraege nicht bearbeitet werden koennen
		this.get_hmInteractiv_settings_validation().put_(RECORD_TRIGGER_DEF.FIELD__VORDEFINIERT, new ownMapSetAndValid());
		
	}
	
	
	/*
	 * maskenvalidierer, sorgt dafuer, dass vordefinierte logtrigger nicht bearbeitet werden koennen
	 */
	private class ownMapSetAndValid extends XX_MAP_Set_And_Valid
	{

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_COPY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_NEW_EMPTY(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_EDIT(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			if (oMAP.get_cActualDBValueFormated(RECORD_TRIGGER_DEF.FIELD__VORDEFINIERT).toUpperCase().equals("Y") && ActionType==XX_MAP_Set_And_Valid.USE_IN_MASK_LOAD_ACTION)
			{
				oMAP.set_All_DB_ComponentsAsDisableFromInteractive();
				oMAP.set_AllComponentsEnabled_For_Edit(false, E2_ComponentMAP.STATUS_EDIT);
			}
			return new MyE2_MessageVector();
		}

		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_VIEW(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return new MyE2_MessageVector();
		}
		
		@Override
		public MyE2_MessageVector make_InteractiveSettings_STATUS_UNDEFINED(E2_ComponentMAP oMAP, int ActionType, ExecINFO oExecInfo, SQLMaskInputMAP oInputMap) throws myException
		{
			return new MyE2_MessageVector();
		}

	}
	
}
