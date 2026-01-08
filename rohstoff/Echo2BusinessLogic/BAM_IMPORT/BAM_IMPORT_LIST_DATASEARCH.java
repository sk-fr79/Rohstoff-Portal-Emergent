package rohstoff.Echo2BusinessLogic.BAM_IMPORT;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class BAM_IMPORT_LIST_DATASEARCH extends E2_DataSearch
{

	public BAM_IMPORT_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_BAM_IMPORT","ID_BAM_IMPORT", E2_MODULNAMES.BAM_IMPORT_LIST);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_BAM_IMPORT","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("BELEGNUMMER","Belegnummer",false);
		this.addSearchDef("ID_BAM_IMPORT","ID BAM-Import",false);
		this.addSearchDef("ID_WIEGEKARTE", "ID-Wiegekarte", false);
		this.addSearchDef("ID_VPOS_TPA_FUHRE", "ID-Fuhre", false);
				

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_BAM_IMPORT.ID_BAM_IMPORT FROM "+bibE2.cTO()+".JT_BAM_IMPORT WHERE TO_CHAR(JT_BAM_IMPORT."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_BAM_IMPORT.ID_BAM_IMPORT FROM "+bibE2.cTO()+".JT_BAM_IMPORT WHERE UPPER(JT_BAM_IMPORT."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_BAM_IMPORT.ID_BAM_IMPORT FROM "+bibE2.cTO()+".JT_BAM_IMPORT,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_BAM_IMPORT.ID_BAM_IMPORT="+cRefTableName+".ID_BAM_IMPORT AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_BAM_IMPORT.ID_BAM_IMPORT FROM "+bibE2.cTO()+".JT_BAM_IMPORT,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_BAM_IMPORT.ID_BAM_IMPORT="+cRefTableName+".ID_BAM_IMPORT AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
