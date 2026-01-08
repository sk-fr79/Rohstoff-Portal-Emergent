package panter.gmbh.Echo2.__CONTAINER_ADDONS.TODOS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;

public class TODO_LIST_DATASEARCH extends E2_DataSearch
{

	public TODO_LIST_DATASEARCH(E2_NavigationList oNaviList, String MODULE_KENNER) throws myException
	{
		super("JT_TODO","ID_TODO",MODULE_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_TODO","ID",true);
		this.addSearchDef("AUFGABEKURZ","Aufgabe (kurz)",false);
		this.addSearchDef("AUFGABENTEXT","Aufgabe (Details)",false);
		this.addSearchDef("ANTWORTKURZ","Ergebnis (kurz)",false);
		this.addSearchDef("ANTWORTTEXT","Ergebnis (Details)",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_TODO.ID_TODO FROM "+bibE2.cTO()+".JT_TODO WHERE TO_CHAR(JT_TODO."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_TODO.ID_TODO FROM "+bibE2.cTO()+".JT_TODO WHERE UPPER(JT_TODO."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

		
}
