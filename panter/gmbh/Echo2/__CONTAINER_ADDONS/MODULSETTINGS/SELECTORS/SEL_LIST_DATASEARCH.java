package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS.SELECTORS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;


public class SEL_LIST_DATASEARCH extends E2_DataSearch
{

	public SEL_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_SELECTOR","ID_SELECTOR",E2_MODULNAMES.MODUL_SELEKTORDEF_LIST);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_SELECTOR","ID",true);

		//hier kommen die Felder	
		this.addSearchDef(_DB.SELECTOR$ID_SELECTOR,"ID",false);
		this.addSearchDef(_DB.SELECTOR$USER_TEXT,"Anzeige im DropDown",false);
		this.addSearchDef(_DB.SELECTOR$WHEREBLOCK,"Where-Statement",false);


		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_SELECTOR.ID_SELECTOR FROM "+bibE2.cTO()+".JT_SELECTOR WHERE TO_CHAR(JT_SELECTOR."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_SELECTOR.ID_SELECTOR FROM "+bibE2.cTO()+".JT_SELECTOR WHERE UPPER(JT_SELECTOR."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	


		
}
