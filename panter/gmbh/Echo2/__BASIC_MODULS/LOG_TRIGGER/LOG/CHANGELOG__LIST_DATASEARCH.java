package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.LOG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class CHANGELOG__LIST_DATASEARCH extends E2_DataSearch
{

	public CHANGELOG__LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_CHANGELOG","ID_CHANGELOG",E2_MODULNAMES.NAME_MODUL_LOG_TRIGGER_ENTRIES_LISTE);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_CHANGELOG","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("BESCHREIBUNG","BESCHREIBUNG",false);
		this.addSearchDef("COLNAME","COLNAME",false);
		this.addSearchDef("ERZEUGT_AM","ERZEUGT_AM",false);
		this.addSearchDef("ERZEUGT_VON","ERZEUGT_VON",false);
		this.addSearchDef("ID_CHANGELOG","ID_CHANGELOG",false);
		this.addSearchDef("ID_TABLE","ID_TABLE",false);
		this.addSearchDef("NEW_VALUE","NEW_VALUE",false);
		this.addSearchDef("OLD_VALUE","OLD_VALUE",false);
		this.addSearchDef("TABLENAME","TABLENAME",false);

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
			cSearch = "SELECT JT_CHANGELOG.ID_CHANGELOG FROM "+bibE2.cTO()+".JT_CHANGELOG WHERE TO_CHAR(JT_CHANGELOG."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_CHANGELOG.ID_CHANGELOG FROM "+bibE2.cTO()+".JT_CHANGELOG WHERE UPPER(JT_CHANGELOG."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_CHANGELOG.ID_CHANGELOG FROM "+bibE2.cTO()+".JT_CHANGELOG,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_CHANGELOG.ID_CHANGELOG="+cRefTableName+".ID_CHANGELOG AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_CHANGELOG.ID_CHANGELOG FROM "+bibE2.cTO()+".JT_CHANGELOG,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_CHANGELOG.ID_CHANGELOG="+cRefTableName+".ID_CHANGELOG AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
