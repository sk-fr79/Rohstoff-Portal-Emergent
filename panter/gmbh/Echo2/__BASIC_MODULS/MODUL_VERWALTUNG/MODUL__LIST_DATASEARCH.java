package panter.gmbh.Echo2.__BASIC_MODULS.MODUL_VERWALTUNG;

import panter.gmbh.Echo2.E2_CONSTANTS_AND_NAMES;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;


public class MODUL__LIST_DATASEARCH extends E2_DataSearch
{

	public MODUL__LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JD_SERVLETS","ID_SERVLETS",E2_CONSTANTS_AND_NAMES.NAME_MODUL_MODUL_MANAGER_LISTE);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_SERVLETS","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("BESCHREIBUNG","Beschreibung",false);
		this.addSearchDef("ID_SERVLETS","ID",false);
		this.addSearchDef("MENUEEINTRAG","Menüeintrag",false);
		this.addSearchDef("SERVLETAUFRUF","Servletaufruf",false);
		this.addSearchDef("TABTEXT","Tab-Text",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JD_SERVLETS.ID_SERVLETS FROM "+bibE2.cTO()+".JD_SERVLETS WHERE TO_CHAR(JD_SERVLETS."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JD_SERVLETS.ID_SERVLETS FROM "+bibE2.cTO()+".JD_SERVLETS WHERE UPPER(JD_SERVLETS."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

//	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
//	{
//		String cSearch = "";
//		if (bNumber)
//			cSearch = "SELECT JD_SERVLETS.ID_SERVLETS FROM "+bibE2.cTO()+".JD_SERVLETS,"+bibE2.cTO()+"."+cRefTableName+" WHERE JD_SERVLETS.ID_SERVLETS="+cRefTableName+".ID_SERVLETS AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
//		else
//			cSearch = "SELECT JD_SERVLETS.ID_SERVLETS FROM "+bibE2.cTO()+".JD_SERVLETS,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JD_SERVLETS.ID_SERVLETS="+cRefTableName+".ID_SERVLETS AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
//		
//		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
//	}
//

		
}
