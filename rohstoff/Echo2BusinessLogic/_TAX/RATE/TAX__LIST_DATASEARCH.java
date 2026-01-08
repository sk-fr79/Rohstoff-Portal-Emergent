package rohstoff.Echo2BusinessLogic._TAX.RATE;


import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.indep.exceptions.myException;


public class TAX__LIST_DATASEARCH extends E2_DataSearch
{

	public TAX__LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_TAX","ID_TAX",E2_MODULNAME_ENUM.MODUL.MODUL_TAX_LIST.get_callKey());
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		//hier kommen die Felder	
		this.addSearchDef("ID_TAX","ID des Eintrags",false);
		this.addSearchDef("STEUERSATZ","Steuersatz aktiv",false);
		this.addSearchDef("DROPDOWN_TEXT","Text auf Drowdown-Eintrag",false);
		this.addSearchDef("INFO_TEXT_USER","Infotext für den Benutzer",false);
		this.add_SearchElement("select JT_TAX.ID_TAX from "+bibE2.cTO()+".JT_TAX INNER JOIN "+bibE2.cTO()+".JD_LAND  ON (JT_TAX.ID_LAND=JD_LAND.ID_LAND) " +
									" where upper(JD_LAND.LAENDERNAME) like upper('%#WERT#%')",new MyE2_String("Land Gültigkeit"),false);
		
		this.addSearchDef("STEUERSATZ_NEU","Steuersatz neu",false);
		this.addSearchDef("STEUERVERMERK","Steuervermerk",false);
		this.addSearchDef("TAXTYP","Steuertyp",false);
		this.addSearchDef("WECHSELDATUM","Datum für Steuersatz-Wechsel",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_TAX.ID_TAX FROM "+bibE2.cTO()+".JT_TAX WHERE TO_CHAR(JT_TAX."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_TAX.ID_TAX FROM "+bibE2.cTO()+".JT_TAX WHERE UPPER(JT_TAX."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}

}
