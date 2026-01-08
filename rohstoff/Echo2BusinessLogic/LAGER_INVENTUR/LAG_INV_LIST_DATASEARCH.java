package rohstoff.Echo2BusinessLogic.LAGER_INVENTUR;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.E2_MODULNAMES;
import panter.gmbh.indep.exceptions.myException;


public class LAG_INV_LIST_DATASEARCH extends E2_DataSearch
{

	public LAG_INV_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_LAGER_INVENTUR","ID_LAGER_INVENTUR",E2_MODULNAMES.NAME_MODUL_INTRASTAT_LISTE);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_LAGER_INVENTUR","Inventur ID",true);
		
		this.addSearchDef("ID_ADRESSE_LAGER","ID Lagerort",false);
		this.addSearchDefLookupTable("JT_ADRESSE", "NAME1", "ID_ADRESSE", "ID_ADRESSE_LAGER", "Lager Name1", false);
		this.addSearchDefLookupTable("JT_ADRESSE", "NAME2", "ID_ADRESSE", "ID_ADRESSE_LAGER", "Lager Name2", false);
		this.addSearchDefLookupTable("JT_ADRESSE", "PLZ", "ID_ADRESSE", "ID_ADRESSE_LAGER", "Lager Plz", false);
		this.addSearchDefLookupTable("JT_ADRESSE", "ORT", "ID_ADRESSE", "ID_ADRESSE_LAGER", "Lager Ort", false);
		
		this.addSearchDef("ID_ARTIKEL_SORTE","ID Sorte",false);
		this.addSearchDefLookupTable("JT_ARTIKEL", "ANR1", "ID_ARTIKEL", "ID_ARTIKEL_SORTE", "ANR1", false);
		this.addSearchDefLookupTable("JT_ARTIKEL", "ARTBEZ1", "ID_ARTIKEL", "ID_ARTIKEL_SORTE", "Sortenbezeichnung", false);
		
		this.addSearchDef("BUCHUNGSDATUM","Buchungsdatum",false);
		this.addSearchDef("BUCHUNGSZEIT","Buchungszeit",false);
		this.addSearchDef("MENGE","Menge",false);
		this.addSearchDef("PREIS","Preis",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
	}

	
	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_LAGER_INVENTUR.ID_LAGER_INVENTUR FROM "+bibE2.cTO()+".JT_LAGER_INVENTUR WHERE TO_CHAR(JT_LAGER_INVENTUR."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_LAGER_INVENTUR.ID_LAGER_INVENTUR FROM "+bibE2.cTO()+".JT_LAGER_INVENTUR WHERE UPPER(JT_LAGER_INVENTUR."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_LAGER_INVENTUR.ID_LAGER_INVENTUR FROM "+bibE2.cTO()+".JT_LAGER_INVENTUR,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_LAGER_INVENTUR.ID_LAGER_INVENTUR="+cRefTableName+".ID_LAGER_INVENTUR AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_LAGER_INVENTUR.ID_LAGER_INVENTUR FROM "+bibE2.cTO()+".JT_LAGER_INVENTUR,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_LAGER_INVENTUR.ID_LAGER_INVENTUR="+cRefTableName+".ID_LAGER_INVENTUR AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


	
	private void addSearchDefLookupTable(String cLookupTableName, String cFieldName, String cLookupId, String cFieldId, String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		String cWhere = "";
		
		cSearch = "SELECT JT_LAGER_INVENTUR.ID_LAGER_INVENTUR FROM "+bibE2.cTO()+".JT_LAGER_INVENTUR,"
		+bibE2.cTO()+"."+cLookupTableName + 
		" WHERE JT_LAGER_INVENTUR." + cFieldId +" = " + cLookupTableName + "." + cLookupId;

		if (bNumber)
			cWhere = " AND TO_CHAR(" + cLookupTableName+"."+cFieldName+") = '#WERT#'";
		else
			cWhere = " AND UPPER(" + cLookupTableName+"."+cFieldName+") like Upper('%#WERT#%')";
			
		this.add_SearchElement(cSearch + cWhere,new MyE2_String(cInfoText));
	}

		
		

		
}
