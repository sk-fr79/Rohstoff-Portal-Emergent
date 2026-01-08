package rohstoff.Echo2BusinessLogic.PROJEKTVERWALTUNG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;

public class PROJEKT_LIST_DATASEARCH extends E2_DataSearch
{

	public PROJEKT_LIST_DATASEARCH(E2_NavigationList oNaviList, String MODULE_KENNER) throws myException
	{
		super("JT_PROJEKT","ID_PROJEKT", MODULE_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_PROJEKT","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("ID_PROJEKT","ID",true);
		this.addSearchDef("PROJEKTBESCHREIBUNG","Projektbeschreibung",false);
		this.addSearchDef("PROJEKTNAME","Projektname",false);


		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_PROJEKT.ID_PROJEKT FROM "+bibE2.cTO()+".JT_PROJEKT WHERE TO_CHAR(JT_PROJEKT."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_PROJEKT.ID_PROJEKT FROM "+bibE2.cTO()+".JT_PROJEKT WHERE UPPER(JT_PROJEKT."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}

		
}
