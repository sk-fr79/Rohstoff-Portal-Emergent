package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.EXPORT;

import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.indep.exceptions.myException;


public class FIBU_EXPORT_LIST_Datasearch extends E2_DataSearch
{
	public FIBU_EXPORT_LIST_Datasearch(E2_NavigationList oNaviList) throws myException 	{
		
		super("JT_VPOS_EXPORT_RG", "JT_VPOS_EXPORT_RG", "Fibu-Exportpositionen");

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}
}
