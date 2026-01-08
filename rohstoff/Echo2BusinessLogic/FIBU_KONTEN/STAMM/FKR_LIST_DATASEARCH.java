package rohstoff.Echo2BusinessLogic.FIBU_KONTEN.STAMM;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class FKR_LIST_DATASEARCH extends E2_DataSearch
{

	public FKR_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException 	{
		
		super("JT_FIBU_KONTENREGEL","ID_FIBU_KONTENREGEL",E2_MODULNAMES.NAME_LIST_FIBU_KONTEN_REGELN);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_FIBU_KONTENREGEL","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("DEF_EK_VK","DEF_EK_VK",false);
		this.addSearchDef("ID_ADRESSE","ID_ADRESSE",false);
		this.addSearchDef("ID_ARTIKEL","ID_ARTIKEL",false);
		this.addSearchDef("ID_ARTIKEL_GRUPPE_FIBU","ID_ARTIKEL_GRUPPE_FIBU",false);
		this.addSearchDef("ID_FIBU_KONTENREGEL","ID_FIBU_KONTENREGEL",false);
		this.addSearchDef("ID_FIBU_KONTO","ID_FIBU_KONTO",false);
		this.addSearchDef("ID_TAX","ID_TAX",false);

		/*
		this.addSearchDef("AUFGABEKURZ","Aufgabe (kurz)",false);
		this.addSearchDef("AUFGABENTEXT","Aufgabe (Details)",false);
		this.addSearchDef("ANTWORTKURZ","Ergebnis (kurz)",false);
		this.addSearchDef("ANTWORTTEXT","Ergebnis (Details)",false);
		*/		

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_FIBU_KONTENREGEL.ID_FIBU_KONTENREGEL FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL WHERE TO_CHAR(JT_FIBU_KONTENREGEL."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_FIBU_KONTENREGEL.ID_FIBU_KONTENREGEL FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL WHERE UPPER(JT_FIBU_KONTENREGEL."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_FIBU_KONTENREGEL.ID_FIBU_KONTENREGEL FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_FIBU_KONTENREGEL.ID_FIBU_KONTENREGEL="+cRefTableName+".ID_FIBU_KONTENREGEL AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_FIBU_KONTENREGEL.ID_FIBU_KONTENREGEL FROM "+bibE2.cTO()+".JT_FIBU_KONTENREGEL,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_FIBU_KONTENREGEL.ID_FIBU_KONTENREGEL="+cRefTableName+".ID_FIBU_KONTENREGEL AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
