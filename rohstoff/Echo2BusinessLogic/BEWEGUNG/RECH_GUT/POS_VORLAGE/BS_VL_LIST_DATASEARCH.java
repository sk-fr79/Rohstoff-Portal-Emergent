package rohstoff.Echo2BusinessLogic.BEWEGUNG.RECH_GUT.POS_VORLAGE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;

public class BS_VL_LIST_DATASEARCH extends E2_DataSearch
{
	private String cWhereZusatz = null;


	public BS_VL_LIST_DATASEARCH(E2_NavigationList 	oNaviList, String MODULE_KENNER) throws myException
	{
		super( "JT_VPOS_RG_VL","ID_VPOS_RG_VL", MODULE_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);

		this.addSearchDef("ANR1",				"ANR1",				false);
		this.addSearchDef("ANR2",				"ANR2",				false);
		this.addSearchDef("ARTBEZ1",			"Artikelbez. 1",	false);
		this.addSearchDef("ARTBEZ2",			"Artikelbez. 2",	false);
		this.addSearchDef("ID_ARTIKEL",			"ID Sorte",			true);
		this.addSearchDef("EINHEITKURZ",		"Mengeneinheit",	false);
		this.addSearchDef("EINHEIT_PREIS_KURZ",	"Mengeneinheit",	false);
		this.addSearchDef("BESTELLNUMMER",		"Bestellnummer",		false);
		this.addSearchDef("ZAHLUNGSBEDINGUNGEN","Zahlungsbedingungen",false);
		this.addSearchDef("LIEFERBEDINGUNGEN",	"Lieferbedingungen",false);
		

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_VPOS_RG_VL.ID_VPOS_RG_VL FROM "+bibE2.cTO()+".JT_VPOS_RG_VL WHERE TO_CHAR(JT_VPOS_RG_VL."+cFieldName+") ='#WERT#' AND "+this.cWhereZusatz;
		else
			cSearch = "SELECT JT_VPOS_RG_VL.ID_VPOS_RG_VL FROM "+bibE2.cTO()+".JT_VPOS_RG_VL WHERE UPPER(JT_VPOS_RG_VL."+cFieldName+") like upper('%#WERT#%') AND "+this.cWhereZusatz;
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	
}
