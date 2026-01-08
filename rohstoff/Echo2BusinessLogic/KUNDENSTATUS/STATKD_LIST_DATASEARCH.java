package rohstoff.Echo2BusinessLogic.KUNDENSTATUS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class STATKD_LIST_DATASEARCH extends E2_DataSearch
{

	public STATKD_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_STATUS_KUNDE","ID_STATUS_KUNDE",E2_MODULNAMES.NAME_MODUL_KUNDENSTATUS_FORDERUNGEN_LISTE);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_STATUS_KUNDE","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("BUCHUNGSDATUM","BUCHUNGSDATUM",false);
		this.addSearchDef("ID_ADRESSE","ID_ADRESSE",false);
		this.addSearchDef("ID_STATUS_KUNDE","ID_STATUS_KUNDE",false);


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
			cSearch = "SELECT JT_STATUS_KUNDE.ID_STATUS_KUNDE FROM "+bibE2.cTO()+".JT_STATUS_KUNDE WHERE TO_CHAR(JT_STATUS_KUNDE."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_STATUS_KUNDE.ID_STATUS_KUNDE FROM "+bibE2.cTO()+".JT_STATUS_KUNDE WHERE UPPER(JT_STATUS_KUNDE."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_STATUS_KUNDE.ID_STATUS_KUNDE FROM "+bibE2.cTO()+".JT_STATUS_KUNDE,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_STATUS_KUNDE.ID_STATUS_KUNDE="+cRefTableName+".ID_STATUS_KUNDE AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_STATUS_KUNDE.ID_STATUS_KUNDE FROM "+bibE2.cTO()+".JT_STATUS_KUNDE,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_STATUS_KUNDE.ID_STATUS_KUNDE="+cRefTableName+".ID_STATUS_KUNDE AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


		
}
