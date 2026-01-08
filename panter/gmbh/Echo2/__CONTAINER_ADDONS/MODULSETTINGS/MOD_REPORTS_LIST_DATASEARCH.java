package panter.gmbh.Echo2.__CONTAINER_ADDONS.MODULSETTINGS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;

public class MOD_REPORTS_LIST_DATASEARCH extends E2_DataSearch
{

	private String cWhereAddON = null;
	
	public MOD_REPORTS_LIST_DATASEARCH(E2_NavigationList oNaviList, String cCallingModulContainerKennung) throws myException
	{
		super(_DB.REPORT,_DB.REPORT$ID_REPORT, null);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.cWhereAddON=_DB.REPORT$MODULE_KENNER+"='"+cCallingModulContainerKennung+"'";
		
		this.addSearchDefREPORT(_DB.REPORT$BESCHREIBUNG,"Beschreibung",false);
		this.addSearchDefREPORT(_DB.REPORT$BUTTONTEXT,"Text auf Button",false);
		this.addSearchDefREPORT(_DB.REPORT$NAME_OF_REPORTFILE,"Report-Datei",false);
		this.addSearchDefREPORT(_DB.REPORT$BUTTON_AUTH_KENNER,"Autentifizierungs-Code",false);
		this.addSearchDefREPORT(_DB.REPORT$ID_REPORT,"ID-Report",true);
		

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
	
	}

	private void addSearchDefREPORT(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT "+_DB.REPORT+"."+_DB.REPORT$ID_REPORT+" FROM "+bibE2.cTO()+"."+_DB.REPORT+" WHERE TO_CHAR("+_DB.REPORT+"."+cFieldName+")='#WERT#' AND "+this.cWhereAddON;
		else
			cSearch = "SELECT "+_DB.REPORT+"."+_DB.REPORT$ID_REPORT+" FROM "+bibE2.cTO()+"."+_DB.REPORT+" WHERE UPPER("+_DB.REPORT+"."+cFieldName+") like upper('%#WERT#%') AND "+this.cWhereAddON;
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	
	
}
