package panter.gmbh.Echo2.__BASIC_MODULS.LAND;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.basics4project.DB_RECORDS._DB;
import panter.gmbh.indep.exceptions.myException;


public class LAND__LIST_DATASEARCH extends E2_DataSearch
{

	public LAND__LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JD_LAND","ID_LAND",E2_MODULNAMES.NAME_MODUL_LAENDER_LIST);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_LAND","ID",true);

		//hier kommen die Felder	
		this.addSearchDef(_DB.LAND$ID_LAND,				"ID-Land",false);
		this.addSearchDef(_DB.LAND$LAENDERNAME,			"Name",false);
		this.addSearchDef(_DB.LAND$LAENDERCODE,			"Ländercode",false);
		this.addSearchDef(_DB.LAND$LAENDERVORWAHL,		"Ländervorwahl",false);
		this.addSearchDef(_DB.LAND$BESCHREIBUNG,		"Beschreibung",false);
		this.addSearchDef(_DB.LAND$POST_CODE,			"Postcode",false);
		this.addSearchDef(_DB.LAND$UST_PRAEFIX,			"UST-Präfix",false);
		this.addSearchDef(_DB.LAND$ANZEIGEREIHENFOLGE,	"Anzeigereihenfolge",false);

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
			cSearch = "SELECT JD_LAND.ID_LAND FROM "+bibE2.cTO()+".JD_LAND WHERE TO_CHAR(JD_LAND."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JD_LAND.ID_LAND FROM "+bibE2.cTO()+".JD_LAND WHERE UPPER(JD_LAND."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JD_LAND.ID_LAND FROM "+bibE2.cTO()+".JD_LAND,"+bibE2.cTO()+"."+cRefTableName+" WHERE JD_LAND.ID_LAND="+cRefTableName+".ID_LAND AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JD_LAND.ID_LAND FROM "+bibE2.cTO()+".JD_LAND,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JD_LAND.ID_LAND="+cRefTableName+".ID_LAND AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
