package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.LIST;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.SCANNER_SETTINGS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;


public class SCAN_LIST_DATASEARCH extends E2_DataSearch
{

	public SCAN_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super(_TAB.scanner_settings.n(),_TAB.scanner_settings.keyFieldName(),E2_MODULNAME_ENUM.MODUL.SCANNER_LIST.get_callKey());
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef(SCANNER_SETTINGS.id_scanner_settings.fn(),"ID",true);
		this.addSearchDef(SCANNER_SETTINGS.filetype.fn(),			"Dateityp",false);
		this.addSearchDef(SCANNER_SETTINGS.module_kenner.fn(),		"Modul",false);
		this.addSearchDef(SCANNER_SETTINGS.programm_kenner.fn(),	"Programmkenner",false);
		this.addSearchDef(SCANNER_SETTINGS.scanner_aufruf1.fn(),	"Aufruf 1",false);
		this.addSearchDef(SCANNER_SETTINGS.scanner_aufruf2.fn(),	"Aufruf 2",false);
		this.addSearchDef(SCANNER_SETTINGS.scanner_aufruf3.fn(),	"Aufruf 3",false);
		this.addSearchDef(SCANNER_SETTINGS.scanner_aufruf4.fn(),	"Aufruf 4",false);
		this.addSearchDef(SCANNER_SETTINGS.scanner_name.fn(),		"Scanner-Name",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber) {
			cSearch = "SELECT "+SCANNER_SETTINGS.id_scanner_settings.tnfn()+ " FROM "+bibE2.cTO()+"."+_TAB.scanner_settings.n()+" WHERE TO_CHAR("+_TAB.scanner_settings.n()+"."+cFieldName+")='#WERT#'";
		} else {
			cSearch = "SELECT "+SCANNER_SETTINGS.id_scanner_settings.tnfn()+ " FROM "+bibE2.cTO()+"."+_TAB.scanner_settings.n()+" WHERE UPPER("+_TAB.scanner_settings.n()+"."+cFieldName+") like upper('%#WERT#%')";
		}
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

		
}
