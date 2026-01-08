package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.GROOVYS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;


public class GROOVY_LIST_DATASEARCH extends E2_DataSearch
{

	public GROOVY_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super(_DB.GROOVYSCRIPT,_DB.GROOVYSCRIPT$ID_GROOVYSCRIPT,E2_MODULNAMES.MODUL_GROOVYDEF_LIST);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		//hier kommen die Felder	
		this.addSearchDef(_DB.GROOVYSCRIPT$ID_GROOVYSCRIPT,"ID",false);
		this.addSearchDef(_DB.GROOVYSCRIPT$NAME_RETURN_VAR,"Variable für Rückgabe",false);
		this.addSearchDef(_DB.GROOVYSCRIPT$USER_TEXT,"Benutzertext",false);
		this.addSearchDef(_DB.GROOVYSCRIPT$SCRIPT,"Scriptblock",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT "+_DB.GROOVYSCRIPT+"."+_DB.GROOVYSCRIPT$ID_GROOVYSCRIPT+" FROM "+bibE2.cTO()+"."+_DB.GROOVYSCRIPT+" WHERE TO_CHAR("+_DB.GROOVYSCRIPT+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT "+_DB.GROOVYSCRIPT+"."+_DB.GROOVYSCRIPT$ID_GROOVYSCRIPT+" FROM "+bibE2.cTO()+"."+_DB.GROOVYSCRIPT+" WHERE UPPER("+_DB.GROOVYSCRIPT+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	


		
}
