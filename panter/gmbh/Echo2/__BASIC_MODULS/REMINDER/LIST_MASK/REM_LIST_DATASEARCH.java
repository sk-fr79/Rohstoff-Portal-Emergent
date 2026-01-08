package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_CONST.TRANSLATOR;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;


public class REM_LIST_DATASEARCH extends E2_DataSearch {
	
	public REM_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException {
		super(_TAB.reminder_def.n(), _TAB.scanner_settings.keyFieldName(), TRANSLATOR.LIST.get_modul().get_callKey());

		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);

		this.addSearchDef(REMINDER_DEF.abgeschlossen_am.fn(), "Abgeschlossen am", true);
		this.addSearchDef(REMINDER_DEF.erinnerung_ab.fn(), "Erinnerung ab", false);
		this.addSearchDef(REMINDER_DEF.erinnerung_bei_anlage.fn(), "Erinnerung bei Anlage", false);
		this.addSearchDef(REMINDER_DEF.id_reminder_def.fn(), "ID ReminderDef", true);
		this.addSearchDef(REMINDER_DEF.table_name.fn(), "Tabellenname", false);
		this.addSearchDef(REMINDER_DEF.id_table.fn(), "Tabellen-ID", true);
		this.addSearchDef(REMINDER_DEF.id_user_abgeschlossen.fn(), "ID Benutzer abgeschlossen", true);
		this.addSearchDef(REMINDER_DEF.id_user_angelegt.fn(), "ID Benutzer angelegt", true);
		this.addSearchDef(REMINDER_DEF.intervall.fn(), "Intervall", true);
		this.addSearchDef(REMINDER_DEF.reminder_heading.fn(), "Erinnerungsmeldung Titel", false);
		this.addSearchDef(REMINDER_DEF.reminder_text.fn(), "Erinnerungsmeldung Text", false);
		this.addSearchDef(REMINDER_DEF.modul_connect_ziel.fn(), "Verknüpftes Modul", false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName, String cInfoText, boolean bNumber) throws myException {
		String cSearch = "";
		if (bNumber) {
			cSearch = "SELECT id_reminder_def  FROM " + bibE2.cTO() + "." + _TAB.reminder_def.n() + " WHERE TO_CHAR(" + _TAB.reminder_def.n() + "." + cFieldName + ")='#WERT#'";
		}
		else {
			cSearch = "SELECT id_reminder_def  FROM " + bibE2.cTO() + "." + _TAB.reminder_def.n() + " WHERE UPPER(" + _TAB.reminder_def.n() + "." + cFieldName + ") like upper('%#WERT#%')";
		}

		this.add_SearchElement(cSearch, new MyE2_String(cInfoText));
	}

}
