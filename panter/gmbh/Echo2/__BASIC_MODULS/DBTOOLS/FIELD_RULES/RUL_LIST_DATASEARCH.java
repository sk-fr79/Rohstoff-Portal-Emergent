package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.FIELD_RULES;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class RUL_LIST_DATASEARCH extends E2_DataSearch
{

	public RUL_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_FIELD_RULE","ID_FIELD_RULE",E2_MODULNAMES.NAME_MODUL_FIELDRULE_LIST);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_FIELD_RULE","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("FIELD_NAME","FIELD_NAME",false);
		this.addSearchDef("ID_FIELD_RULE","ID_FIELD_RULE",false);
		this.addSearchDef("ID_USER","ID_USER",false);
		this.addSearchDef("MODUL_KENNER","MODUL_KENNER",false);
		this.addSearchDef("RULE","RULE",false);
		this.addSearchDef("RULE_INFO","RULE_INFO",false);
		this.addSearchDef("RULETYPE","RULETYPE",false);
		this.addSearchDef("TABLE_NAME","TABLE_NAME",false);

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
			cSearch = "SELECT JT_FIELD_RULE.ID_FIELD_RULE FROM "+bibE2.cTO()+".JT_FIELD_RULE WHERE TO_CHAR(JT_FIELD_RULE."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_FIELD_RULE.ID_FIELD_RULE FROM "+bibE2.cTO()+".JT_FIELD_RULE WHERE UPPER(JT_FIELD_RULE."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_FIELD_RULE.ID_FIELD_RULE FROM "+bibE2.cTO()+".JT_FIELD_RULE,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_FIELD_RULE.ID_FIELD_RULE="+cRefTableName+".ID_FIELD_RULE AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_FIELD_RULE.ID_FIELD_RULE FROM "+bibE2.cTO()+".JT_FIELD_RULE,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_FIELD_RULE.ID_FIELD_RULE="+cRefTableName+".ID_FIELD_RULE AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
