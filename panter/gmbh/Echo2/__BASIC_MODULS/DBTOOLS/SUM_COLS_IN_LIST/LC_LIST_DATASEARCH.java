package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class LC_LIST_DATASEARCH extends E2_DataSearch
{

	public LC_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_COLUMNS_TO_CALC","ID_COLUMNS_TO_CALC",E2_MODULNAMES.NAME_COLS_TO_CALC_DEF_LIST);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_COLUMNS_TO_CALC","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("COLUMN_LABEL","COLUMN_LABEL",false);
		this.addSearchDef("ID_COLUMNS_TO_CALC","ID_COLUMNS_TO_CALC",false);
		this.addSearchDef("MODULNAME_LISTE","MODULNAME_LISTE",false);
		this.addSearchDef("NUMBER_DECIMALS","NUMBER_DECIMALS",false);
		this.addSearchDef("SUMMATION_VIA_QUERY","SUMMATION_VIA_QUERY",false);
		this.addSearchDef("TEXT4SUMMATION","TEXT4SUMMATION",false);
		this.addSearchDef("TEXT4TITLE_IN_WINDOW","TEXT4TITLE_IN_WINDOW",false);
		this.addSearchDef("TEXT4WINDOWTITLE","TEXT4WINDOWTITLE",false);
		this.addSearchDef("TOOLTIPS","TOOLTIPS",false);
		this.addSearchDef("VALIDATION_TAG","VALIDATION_TAG",false);

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
			cSearch = "SELECT JT_COLUMNS_TO_CALC.ID_COLUMNS_TO_CALC FROM "+bibE2.cTO()+".JT_COLUMNS_TO_CALC WHERE TO_CHAR(JT_COLUMNS_TO_CALC."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_COLUMNS_TO_CALC.ID_COLUMNS_TO_CALC FROM "+bibE2.cTO()+".JT_COLUMNS_TO_CALC WHERE UPPER(JT_COLUMNS_TO_CALC."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_COLUMNS_TO_CALC.ID_COLUMNS_TO_CALC FROM "+bibE2.cTO()+".JT_COLUMNS_TO_CALC,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_COLUMNS_TO_CALC.ID_COLUMNS_TO_CALC="+cRefTableName+".ID_COLUMNS_TO_CALC AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_COLUMNS_TO_CALC.ID_COLUMNS_TO_CALC FROM "+bibE2.cTO()+".JT_COLUMNS_TO_CALC,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_COLUMNS_TO_CALC.ID_COLUMNS_TO_CALC="+cRefTableName+".ID_COLUMNS_TO_CALC AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
