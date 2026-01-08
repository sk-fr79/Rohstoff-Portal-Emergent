package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK.REM_CONST.TRANSLATOR;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM.MODUL;
import panter.gmbh.basics4project.Project_BasicModuleContainer;
import panter.gmbh.basics4project.DB_ENUMS.REMINDER_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;

public class REM_LIST_BasicModuleContainer extends Project_BasicModuleContainer implements REM__IF_getTableAndID {
	
	public static final String NAME_OF_CHECKBOX_IN_LIST = REM_CONST.REM_NAMES.CHECKBOX_LISTE.db_val();
	public static final String NAME_OF_LISTMARKER_IN_LIST = REM_CONST.REM_NAMES.MARKER_LISTE.db_val();

	/**
	 * werden table und id gesetzt, dann wird das modul als popup verwendet
	 */
	private _TAB 		table = null;
	private MODUL		modul = null;
	private String 		id = null;

	
	/**
	 * Konstruktor ohne Ziel-Modul
	 * @author manfred
	 * @date 12.04.2016
	 *
	 * @param table
	 * @param id
	 * @throws myException
	 */
	public REM_LIST_BasicModuleContainer(_TAB table, String id) throws myException {
		this(table,null,id);
	}
	
	
	/**
	 * Baut einen Reminder mit einem Zielmodul auf.
	 * @author manfred
	 * @date 12.04.2016
	 *
	 * @param table
	 * @param calling_module
	 * @param id
	 * @throws myException
	 */
	public REM_LIST_BasicModuleContainer(_TAB table, MODUL calling_module,String id) throws myException {
		super(TRANSLATOR.LIST.get_modul().get_callKey());
		
		
		this.set_cADDON_TO_CLASSNAME(table.baseTableName());  	//fuer die popup-variante einen nameszusatz, damit jedes fenster der einzelnen tabellen separat gespeichert werden kann
		this.set_bVisible_Row_For_Messages(true);

		this.modul = calling_module;
		this.table = table;
		this.id = id;
		String sWhere = "";
		
		if (table != null){
			sWhere += new And(new vgl(REMINDER_DEF.table_name,this.table.baseTableName())).s();
		}
		
		if (id != null){
			if (!bibALL.isEmpty(sWhere)) { 
				sWhere += new And().get_separator();
			}
			sWhere +=  new And(new vgl(REMINDER_DEF.id_table, this.id)).s();
		}
		
		this.__init(sWhere);
//		this.CREATE_AND_SHOW_POPUPWINDOW(new Extent(1500), new Extent(800), new MyE2_String("Erinnerungsmeldungen zu den ausgewählten Datensätzen"));

	}
	


	/**
	 * variante fuers menue, tab und id bleiben null
	 * @throws myException
	 */
	public REM_LIST_BasicModuleContainer() throws myException {
		super(TRANSLATOR.LIST.get_modul().get_callKey());
		this.set_bVisible_Row_For_Messages(true);
		
		this.table = _TAB.user;
		this.id = bibALL.get_ID_USER();
		
		this.__init(null);
	}

	
	private void __init(String c_static_whereblock) throws myException {
		E2_NavigationList oNaviList = new E2_NavigationList();
		REM_LIST_ComponentMap map = new REM_LIST_ComponentMap();
		SQLFieldMAP  oSQLFM = map.get_oSQLFieldMAP();
		if (S.isFull(c_static_whereblock)) {
			oSQLFM.add_BEDINGUNG_STATIC(c_static_whereblock);
		}
		
		oNaviList.INIT_WITH_ComponentMAP(map, E2_NavigationList.STYLE__4_2_4_2, this.get_MODUL_IDENTIFIER());
		REM_LIST_BedienPanel oPanel = new REM_LIST_BedienPanel(this,oNaviList);
		this.add(oPanel);
		this.add(oNaviList, E2_INSETS.I_2_2_2_2);

		// falls es keine ID und Tabelle gibt, kann man keinen neuen Reminder anlegen. 
		if (bibALL.isEmpty(get_id()) || get_table() == null ){
			oPanel.getoButtonNew().set_bEnabled_For_Edit(false);
		}
		
		oPanel.get_list_Selector().get_oSelVector().doActionPassiv();
	}
	
	@Override
	public _TAB get_table() {
		return table;
	}

	@Override
	public String get_id() {
		return id;
	}

	 
	@Override
	public MODUL get_modul() throws myException {
		return this.modul;
	}
	
}
