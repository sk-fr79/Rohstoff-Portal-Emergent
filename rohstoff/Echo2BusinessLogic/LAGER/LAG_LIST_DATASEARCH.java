package rohstoff.Echo2BusinessLogic.LAGER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;


public class LAG_LIST_DATASEARCH extends E2_DataSearch
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -489897881872384318L;



	public LAG_LIST_DATASEARCH(E2_NavigationList oNaviList,String MODUL_KENNER) throws myException
	{
		super("JT_LAGER_KONTO","ID_LAGER_KONTO",MODUL_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_LAGER_KONTO","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("ID_ADRESSE_LAGER","Lagerort",false);
		this.addSearchDefLookupTable("JT_ARTIKEL", "ARTBEZ1", "ID_ARTIKEL", "ID_ARTIKEL_SORTE", "Sorte", false);
		this.addSearchDefLookupTable("JT_ARTIKEL", "ANR1", "ID_ARTIKEL", "ID_ARTIKEL_SORTE", "ArtikelNr", false);
		
		this.addSearchDefLookupTable("JT_ADRESSE", "NAME1", "ID_ADRESSE", "ID_ADRESSE_LAGER", "Name1", false);
		this.addSearchDefLookupTable("JT_ADRESSE", "NAME2", "ID_ADRESSE", "ID_ADRESSE_LAGER", "Name2", false);
		this.addSearchDefLookupTable("JT_ADRESSE", "PLZ", "ID_ADRESSE", "ID_ADRESSE_LAGER", "Plz", false);
		this.addSearchDefLookupTable("JT_ADRESSE", "ORT", "ID_ADRESSE", "ID_ADRESSE_LAGER", "Ort", false);
		
		
		this.addSearchDef("ID_LAGER_KONTO","LagerId",false);		
		//this.addSearchDef("SALDO","Saldo",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE TO_CHAR(JT_LAGER_KONTO."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO FROM "+bibE2.cTO()+".JT_LAGER_KONTO WHERE UPPER(JT_LAGER_KONTO."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	@SuppressWarnings("unused")
	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO FROM "+bibE2.cTO()+".JT_LAGER_KONTO,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_LAGER_KONTO.ID_LAGER_KONTO="+cRefTableName+".ID_LAGER_KONTO AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO FROM "+bibE2.cTO()+".JT_LAGER_KONTO,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_LAGER_KONTO.ID_LAGER_KONTO="+cRefTableName+".ID_LAGER_KONTO AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}

	

	private void addSearchDefLookupTable(String cLookupTableName, String cFieldName, String cLookupId, String cFieldId, String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO FROM "+bibE2.cTO()+".JT_LAGER_KONTO,"
			+bibE2.cTO()+"."+cLookupTableName + 
			" WHERE JT_LAGER_KONTO." + cFieldId +" = " + cLookupTableName + "." + cLookupId + 
			" AND TO_CHAR(" + cLookupTableName+"."+cFieldName+") = '#WERT#'";
		else
			cSearch = "SELECT JT_LAGER_KONTO.ID_LAGER_KONTO FROM "+bibE2.cTO()+".JT_LAGER_KONTO,"
			+bibE2.cTO()+"."+cLookupTableName + 
			" WHERE JT_LAGER_KONTO." + cFieldId +" = " + cLookupTableName + "." + cLookupId + 
			" AND UPPER(" + cLookupTableName+"."+cFieldName+") like Upper('%#WERT#%')";
	
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}

		
}
