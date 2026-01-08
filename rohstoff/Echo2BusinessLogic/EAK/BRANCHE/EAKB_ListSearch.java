package rohstoff.Echo2BusinessLogic.EAK.BRANCHE;

import java.util.HashMap;

import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.indep.exceptions.myException;


public class EAKB_ListSearch extends E2_DataSearch
{

	public EAKB_ListSearch(     E2_ComponentMAP		oComponentMAP,
								E2_NavigationList	oNavigationList) throws myException
	{
		super("JT_EAK_BRANCHE", "ID_EAK_BRANCHE",null);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNavigationList);
		this.set_oSearchAgent(oSearchAgent);

		HashMap<String, MyE2IF__Component> oHelpHash = oComponentMAP.get_REAL_ComponentHashMap();

		this.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("ID_EAK_BRANCHE"));
		this.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("KEY_BRANCHE"));
		this.add_SearchElement((MyE2IF__DB_Component)oHelpHash.get("BRANCHE"));

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
	}

}
