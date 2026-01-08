package rohstoff.Echo2BusinessLogic.SPIELWIESE.list;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class SPIELWIESE_LIST_DATASEARCH extends E2_DataSearch
{

	public SPIELWIESE_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_FIBU_KONTENREGEL_NEU","ID_FIBU_KONTENREGEL_NEU",E2_MODULNAMES.SPIELWIESE_LIST);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_FIBU_KONTENREGEL_NEU","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("AKTIV","AKTIV",false);
		this.addSearchDef("ID_FIBU_KONTENREGEL_NEU","ID_FIBU_KONTENREGEL_NEU",false);
		this.addSearchDef("ID_FIBU_KONTO","ID_FIBU_KONTO",false);
		this.addSearchDef("ID_FILTER","ID_FILTER",false);
		this.addSearchDef("KOMMENTAR","KOMMENTAR",false);

		/*
		this.addSearchDef("AUFGABEKURZ","Aufgabe (kurz)",false);
		this.addSearchDef("AUFGABENTEXT","Aufgabe (Details)",false);
		this.addSearchDef("ANTWORTKURZ","Ergebnis (kurz)",false);
		this.addSearchDef("ANTWORTTEXT","Ergebnis (Details)",false);
		*/		

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_FIBU_KONTENREGEL_NEU.ID_FIBU_KONTENREGEL_NEU FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL_NEU WHERE TO_CHAR(JT_FIBU_KONTENREGEL_NEU."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_FIBU_KONTENREGEL_NEU.ID_FIBU_KONTENREGEL_NEU FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL_NEU WHERE UPPER(JT_FIBU_KONTENREGEL_NEU."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_FIBU_KONTENREGEL_NEU.ID_FIBU_KONTENREGEL_NEU FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL_NEU,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_FIBU_KONTENREGEL_NEU.ID_FIBU_KONTENREGEL_NEU="+cRefTableName+".ID_FIBU_KONTENREGEL_NEU AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_FIBU_KONTENREGEL_NEU.ID_FIBU_KONTENREGEL_NEU FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL_NEU,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_FIBU_KONTENREGEL_NEU.ID_FIBU_KONTENREGEL_NEU="+cRefTableName+".ID_FIBU_KONTENREGEL_NEU AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
