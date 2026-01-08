package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.SONDERDEF;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;

public class FU_SONDER_LIST_DATASEARCH extends E2_DataSearch
{

	public FU_SONDER_LIST_DATASEARCH(E2_NavigationList oNaviList, String MODULE_KENNER) throws myException
	{
		super("JT_VPOS_TPA_FUHRE_SONDER","ID_VPOS_TPA_FUHRE_SONDER", MODULE_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_VPOS_TPA_FUHRE_SONDER","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("AUSNAHME","Ausnahme",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_VPOS_TPA_FUHRE_SONDER.ID_VPOS_TPA_FUHRE_SONDER FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_SONDER WHERE TO_CHAR(JT_VPOS_TPA_FUHRE_SONDER."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_VPOS_TPA_FUHRE_SONDER.ID_VPOS_TPA_FUHRE_SONDER FROM "+bibE2.cTO()+".JT_VPOS_TPA_FUHRE_SONDER WHERE UPPER(JT_VPOS_TPA_FUHRE_SONDER."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	


		
}
