package rohstoff.Echo2BusinessLogic.LAGERSTATUS;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class LAG_STAT_LIST_DATASEARCH extends E2_DataSearch
{

	public LAG_STAT_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_STATUS_LAGER","ID_STATUS_LAGER", E2_MODULNAMES.NAME_MODUL_LAGERSTATUS_LISTE);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_STATUS_LAGER","ID",true);

		//hier kommen die Felder	
//		this.addSearchDef("AVG_WERT_GESAMT","AVG_WERT_GESAMT",false);
//		this.addSearchDef("AVG_WERT_MIT_NULL","AVG_WERT_MIT_NULL",false);
//		this.addSearchDef("AVG_WERT_NICHT_NULL","AVG_WERT_NICHT_NULL",false);
//		this.addSearchDef("BUCHUNGSDATUM","BUCHUNGSDATUM",false);
//		this.addSearchDef("MENGE_GESAMT","MENGE_GESAMT",false);
//		this.addSearchDef("MENGE_PREISE_LEER","MENGE_PREISE_LEER",false);
//		this.addSearchDef("MENGE_PREISE_NICHT_NULL","MENGE_PREISE_NICHT_NULL",false);
//		this.addSearchDef("MENGE_PREISE_NULL","MENGE_PREISE_NULL",false);
//		this.addSearchDef("SUM_RESTWERT","SUM_RESTWERT",false);

		this.addSearchDef("ID_ADRESSE","IDAdresse",false);
		this.addSearchDefLookupTable("JT_ADRESSE", "NAME1", "ID_ADRESSE", "ID_ADRESSE", "Name1", false);
		this.addSearchDefLookupTable("JT_ADRESSE", "NAME2", "ID_ADRESSE", "ID_ADRESSE", "Name2", false);
		this.addSearchDefLookupTable("JT_ADRESSE", "PLZ", "ID_ADRESSE", "ID_ADRESSE", "Plz", false);
		this.addSearchDefLookupTable("JT_ADRESSE", "ORT", "ID_ADRESSE", "ID_ADRESSE", "Ort", false);
		
		this.addSearchDef("ID_SORTE","IDSorte",false);
		
		this.addSearchDefLookupTable("JT_ARTIKEL", "ARTBEZ1", "ID_ARTIKEL", "ID_SORTE", "Sorte", false);
		this.addSearchDefLookupTable("JT_ARTIKEL", "ANR1", "ID_ARTIKEL", "ID_SORTE", "ANR1", false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_STATUS_LAGER.ID_STATUS_LAGER FROM "+bibE2.cTO()+".JT_STATUS_LAGER WHERE TO_CHAR(JT_STATUS_LAGER."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_STATUS_LAGER.ID_STATUS_LAGER FROM "+bibE2.cTO()+".JT_STATUS_LAGER WHERE UPPER(JT_STATUS_LAGER."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_STATUS_LAGER.ID_STATUS_LAGER FROM "+bibE2.cTO()+".JT_STATUS_LAGER,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_STATUS_LAGER.ID_STATUS_LAGER="+cRefTableName+".ID_STATUS_LAGER AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_STATUS_LAGER.ID_STATUS_LAGER FROM "+bibE2.cTO()+".JT_STATUS_LAGER,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_STATUS_LAGER.ID_STATUS_LAGER="+cRefTableName+".ID_STATUS_LAGER AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefLookupTable(String cLookupTableName, String cFieldName, String cLookupId, String cFieldId, String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_STATUS_LAGER.ID_STATUS_LAGER FROM "+bibE2.cTO()+".JT_STATUS_LAGER,"
			+bibE2.cTO()+"."+cLookupTableName + 
			" WHERE JT_STATUS_LAGER." + cFieldId +" = " + cLookupTableName + "." + cLookupId + 
			" AND TO_CHAR(" + cLookupTableName+"."+cFieldName+") = '#WERT#'";
		else
			cSearch = "SELECT JT_STATUS_LAGER.ID_STATUS_LAGER FROM "+bibE2.cTO()+".JT_STATUS_LAGER,"
			+bibE2.cTO()+"."+cLookupTableName + 
			" WHERE JT_STATUS_LAGER." + cFieldId +" = " + cLookupTableName + "." + cLookupId + 
			" AND UPPER(" + cLookupTableName+"."+cFieldName+") like Upper('%#WERT#%')";
	
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}



		
}
