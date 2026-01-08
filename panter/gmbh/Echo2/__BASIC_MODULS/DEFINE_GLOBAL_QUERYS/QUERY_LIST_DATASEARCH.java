package panter.gmbh.Echo2.__BASIC_MODULS.DEFINE_GLOBAL_QUERYS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;

public class QUERY_LIST_DATASEARCH extends E2_DataSearch
{

	public QUERY_LIST_DATASEARCH(E2_NavigationList oNaviList, String MODULE_KENNER) throws myException
	{
		super("JT_QUERY","ID_QUERY",MODULE_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_QUERY","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("NAME","NAME",false);
		this.addSearchDef("BESCHREIB1","BESCHREIB1",false);
		this.addSearchDef("BESCHREIB2","BESCHREIB2",false);
		this.addSearchDef("BESCHREIB3","BESCHREIB3",false);
		this.addSearchDef("BESCHREIB4","BESCHREIB4",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_QUERY.ID_QUERY FROM "+bibE2.cTO()+".JT_QUERY WHERE TO_CHAR(JT_QUERY."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_QUERY.ID_QUERY FROM "+bibE2.cTO()+".JT_QUERY WHERE UPPER(JT_QUERY."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

		
}
