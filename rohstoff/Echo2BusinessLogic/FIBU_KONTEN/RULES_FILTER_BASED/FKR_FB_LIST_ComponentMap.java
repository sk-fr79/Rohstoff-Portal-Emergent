package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.RULES_FILTER_BASED;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_ButtonListMarker;
import panter.gmbh.Echo2.ListAndMask.List.E2_CheckBoxForList;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST.LC__LIST_CB_AktivAnAus;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST.actionResetSettingsAfterSave;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.DB.MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INROW;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FILTER_AND;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_FILTER_OR;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_COLUMNS_TO_CALC;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIBU_KONTENREGEL_NEU;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER_AND;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FILTER_OR;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.dataTools.SQLField;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.exceptions.myExceptionCopy;
import rohstoff.Echo2BusinessLogic.FIBU_KONTEN.REGELN.FIBU_KONTEN_REGELN__CONST;

public class FKR_FB_LIST_ComponentMap extends E2_ComponentMAP 
{

	public FKR_FB_LIST_ComponentMap() throws myException
	{
		super(new FKR_FB_LIST_SqlFieldMAP());
		
		SQLFieldMAP  oFM = this.get_oSQLFieldMAP();
		
//		E2_DropDownSettingsNew  oDDFibuKonto = new E2_DropDownSettingsNew("SELECT CONCAT(CONCAT(KONTO, ' '), BESCHREIBUNG), ID_FIBU_KONTO FROM JT_FIBU_KONTO ORDER BY KONTO", true, true);
////		E2_DropDownSettingsNew  oDDFilter = new E2_DropDownSettingsNew("SELECT NVL(BESCHREIBUNG,'<beschreibung>'), ID_FILTER FROM JT_FILTER ORDER BY BESCHREIBUNG", true, true);

//		MyE2_DB_SelectField sf_FIBU_KONTO = new MyE2_DB_SelectField(oFM.get_("FKR_"+DB_), new dataToView(oDDFibuKonto.getDD(), false, bibE2.get_CurrSession()));
//		MyE2_DB_SelectField sf_FILTER = 	new MyE2_DB_SelectField(oFM.get_(_DB.FIBU_KONTENREGEL_NEU$ID_FILTER),     new dataToView(oDDFilter.getDD(), false, bibE2.get_CurrSession()));


		this.add_Component(FKR_FB_LIST_BasicModuleContainer.NAME_OF_CHECKBOX_IN_LIST,	new E2_CheckBoxForList(),new MyE2_String("?"));
		this.add_Component(FKR_FB_LIST_BasicModuleContainer.NAME_OF_LISTMARKER_IN_LIST,	new E2_ButtonListMarker(),new MyE2_String("?"));

		//hier kommen die Felder	
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("FKR_"+_DB.FIBU_KONTENREGEL_NEU$ID_FIBU_KONTENREGEL_NEU)), new MyE2_String("Kontenregel-ID"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.FILTER$ID_FILTER)), new MyE2_String("Filter-ID"));
		//this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("FKR_"+_DB.FIBU_KONTENREGEL_NEU$AKTIV)), new MyE2_String("Aktiv?"));
		this.add_Component(new RuleAktivToggler(oFM.get_("FKR_"+_DB.FIBU_KONTENREGEL_NEU$AKTIV)), new MyE2_String("Aktiv?"));
		
		
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("FKR_"+_DB.FIBU_KONTENREGEL_NEU$KOMMENTAR)), new MyE2_String("Kommentar Kontenregel"));
//		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_(_DB.FILTER$BESCHREIBUNG)), new MyE2_String("Filterbeschreibung"));
		this.add_Component(FKR_FB__CONST.COMPONENT_NAME_STRINGED_CONDITION, new MyE2_Grid(1,MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11()), new MyE2_String("Regel"));
		this.add_Component(new MyE2_DB_Label_INROW(oFM.get_("FIBU_KONTO_GESAMT")), new MyE2_String("Zielkonto"));
		
		this.set_oSubQueryAgent(new FKR_FB_LIST_FORMATING_Agent());
	}
	
	private class RuleAktivToggler extends MyE2_DB_CheckBox_IN_LIST_WITH_ToggleAction {

		public RuleAktivToggler(SQLField osqlField) throws myException {
			super(osqlField);
			this.add_oActionAgent(this.get_ToggleAction());
			//this.add_oActionAgent(new actionResetSettingsAfterSave());
		}

		@Override
		public Object get_Copy(Object ob) throws myExceptionCopy
		{
			RuleAktivToggler copy = null;
			try {
				copy = new RuleAktivToggler(this.EXT_DB().get_oSQLField());
			} catch (myException e) {
				e.printStackTrace();
			}
			return copy;
		}

		@Override
		public XX_ActionAgent get_ToggleAction() throws myException
		{
			return new XX_ActionAgent()
			{
				@Override
				public void executeAgentCode(ExecINFO oExecInfo) throws myException
				{
					String idFilter = EXT().get_oComponentMAP().get_oInternalSQLResultMAP().get_cUNFormatedROW_ID();
					RECORD_FIBU_KONTENREGEL_NEU rfkn = new RECORD_FIBU_KONTENREGEL_NEU(
							"ID_FILTER = "+idFilter);

					boolean active = rfkn.get_AKTIV_cF().equals("Y");
					
					rfkn.set_NEW_VALUE_AKTIV(active?"N":"Y");
					
					MyE2_MessageVector oMV = rfkn.UPDATE(true);
					if (oMV.get_bHasAlarms()) {
						bibMSG.add_MESSAGE(oMV);
					} else {
						EXT().get_oComponentMAP()._DO_REFRESH_COMPONENTMAP(E2_ComponentMAP.STATUS_VIEW, true, false);
						bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Status wurde auf "+(active?"INAKTIV":"AKTIV")+" geändert!")));
					}
				}
			};
		}
	}
}
