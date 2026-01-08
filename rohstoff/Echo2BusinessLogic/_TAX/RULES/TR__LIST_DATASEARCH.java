package rohstoff.Echo2BusinessLogic._TAX.RULES;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.exceptions.myException;


public class TR__LIST_DATASEARCH extends E2_DataSearch
{

	public TR__LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_HANDELSDEF","ID_HANDELSDEF",E2_MODULNAME_ENUM.MODUL.MODUL_TAXRULES_LIST.get_callKey());
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_HANDELSDEF","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("ID_HANDELSDEF","ID_HANDELSDEF",false);
		
		this.add_LandSuche("ID_LAND_QUELLE_JUR", 	"Land Quelle juristisch");
		this.add_LandSuche("ID_LAND_QUELLE_GEO", 	"Land Quelle geografisch");
		this.add_LandSuche("ID_LAND_ZIEL_JUR", 		"Land Ziel juristisch");
		this.add_LandSuche("ID_LAND_ZIEL_GEO", 		"Land Ziel geografisch");
				
		this.add_SteuerSuche("ID_TAX_QUELLE", 		"Steuer Quelle");
		this.add_SteuerSuche("ID_TAX_ZIEL", 		"Steuer Ziel");
		
		this.addSearchDef("TP_VERANTWORTUNG","TP_VERANTWORTUNG",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_HANDELSDEF.ID_HANDELSDEF FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE TO_CHAR(JT_HANDELSDEF."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_HANDELSDEF.ID_HANDELSDEF FROM "+bibE2.cTO()+".JT_HANDELSDEF WHERE UPPER(JT_HANDELSDEF."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	


	private void add_LandSuche(String cFELD_ID_LAND, String cTextPopup) throws myException
	{
		this.add_SearchElement("SELECT HD.ID_HANDELSDEF FROM "+bibE2.cTO()+".JT_HANDELSDEF HD INNER JOIN JD_LAND LD ON" +
				" (HD."+cFELD_ID_LAND+"=LD.ID_LAND)" +
				" WHERE UPPER(LD.LAENDERCODE) LIKE UPPER('%#WERT#%') " +
				" OR    UPPER(LD.LAENDERNAME) LIKE UPPER('%#WERT#%')",new MyE2_String(cTextPopup));

	}
	
		
	private void add_SteuerSuche(String cFELD_ID_TAX, String cTextPopup) throws myException
	{
		this.add_SearchElement("SELECT HD.ID_HANDELSDEF FROM "+bibE2.cTO()+".JT_HANDELSDEF HD INNER JOIN JT_TAX TX ON" +
				" (HD."+cFELD_ID_TAX+"=TX.ID_TAX)" +
				" WHERE UPPER(TX.DROPDOWN_TEXT) LIKE UPPER('%#WERT#%') " +
				" OR    UPPER(TX.STEUERVERMERK) LIKE UPPER('%#WERT#%') " +
				" OR    TO_CHAR(TX.STEUERSATZ,'fm9g990d00','NLS_NUMERIC_CHARACTERS = '',.''') = '#WERT#' " +
				" OR    TO_CHAR(TX.STEUERSATZ,'fm9g990d0','NLS_NUMERIC_CHARACTERS = '',.''') = '#WERT#' " +
				" OR    TO_CHAR(TX.STEUERSATZ,'fm9g990','NLS_NUMERIC_CHARACTERS = '',.''') = '#WERT#' ",new MyE2_String(cTextPopup));

	}
	
}
