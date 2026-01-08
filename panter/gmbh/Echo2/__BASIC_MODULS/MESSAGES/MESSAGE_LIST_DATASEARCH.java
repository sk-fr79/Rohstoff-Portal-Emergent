package panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class MESSAGE_LIST_DATASEARCH extends E2_DataSearch
{

	public MESSAGE_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_NACHRICHT","ID_NACHRICHT",E2_MODULNAMES.NAME_MODUL_NACHRICHTEN_LISTE);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_NACHRICHT","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("BESTAETIGT","Bestätigte Nachrichten",false);
		this.addSearchDef("ID_NACHRICHT","NachrichtenID",false);
		this.addSearchDef("ID_NACHRICHT_PARENT","Verbunden NachrichtenID",false);
		this.addSearchDef("ID_USER","UserID",false);
		this.addSearchDef("ID_USER_SENDER","SenderID",false);
		this.addSearchDef("NACHRICHT","Nachrichtentext",false);
		this.addSearchDef("TITEL","Titel",false);

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
			cSearch = "SELECT JT_NACHRICHT.ID_NACHRICHT FROM "+bibE2.cTO()+".JT_NACHRICHT WHERE TO_CHAR(JT_NACHRICHT."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_NACHRICHT.ID_NACHRICHT FROM "+bibE2.cTO()+".JT_NACHRICHT WHERE UPPER(JT_NACHRICHT."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_NACHRICHT.ID_NACHRICHT FROM "+bibE2.cTO()+".JT_NACHRICHT,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_NACHRICHT.ID_NACHRICHT="+cRefTableName+".ID_NACHRICHT AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_NACHRICHT.ID_NACHRICHT FROM "+bibE2.cTO()+".JT_NACHRICHT,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_NACHRICHT.ID_NACHRICHT="+cRefTableName+".ID_NACHRICHT AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
