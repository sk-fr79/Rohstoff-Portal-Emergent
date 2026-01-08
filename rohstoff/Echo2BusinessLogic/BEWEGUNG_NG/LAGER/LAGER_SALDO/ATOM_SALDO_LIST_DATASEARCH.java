package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.LAGER.LAGER_SALDO;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.indep.exceptions.myException;


public class ATOM_SALDO_LIST_DATASEARCH extends E2_DataSearch
{


	public ATOM_SALDO_LIST_DATASEARCH(E2_NavigationList oNaviList,String MODUL_KENNER) throws myException
	{
		super("JT_BEWEGUNG_STATION","JT_BEWEGUNG_STATION",MODUL_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
//		this.addSearchDef("ID_BEWEGUNG_STATION","ID",true);
		this.addSearchDef("ID_ADRESSE","ID Adresse",false);
//
		//hier kommen die Felder	
//		this.addSearchDefLookupTable("JT_ARTIKEL", "ARTBEZ1", "ID_ARTIKEL", "ID_ARTIKEL", "Sorte", false);
//		this.addSearchDefLookupTable("JT_ARTIKEL", "ANR1", "ID_ARTIKEL", "ID_ARTIKEL", "ArtikelNr", false);
//		
//		this.addSearchDefLookupTable("JT_ADRESSE", "NAME1", "ID_ADRESSE", "ID_ADRESSE", "Name1", false);
//		this.addSearchDefLookupTable("JT_ADRESSE", "NAME2", "ID_ADRESSE", "ID_ADRESSE", "Name2", false);
//		this.addSearchDefLookupTable("JT_ADRESSE", "PLZ", "ID_ADRESSE", "ID_ADRESSE", "Plz", false);
//		this.addSearchDefLookupTable("JT_ADRESSE", "ORT", "ID_ADRESSE", "ID_ADRESSE", "Ort", false);
//		
//		
//    	this.addSearchDef("ID_BEWEGUNG_ATOM","AtomId",false);		
		//this.addSearchDef("SALDO","Saldo",false);

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
	}

	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_BEWEGUNG_STATION.ID_BEWEGUNG_STATION FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION WHERE TO_CHAR(JT_BEWEGUNG_STATION."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_BEWEGUNG_STATION.ID_BEWEGUNG_STATION FROM "+bibE2.cTO()+".JT_BEWEGUNG_STATION WHERE UPPER(JT_BEWEGUNG_STATION."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	@SuppressWarnings("unused")
	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM="+cRefTableName+".ID_BEWEGUNG_ATOM AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM="+cRefTableName+".ID_BEWEGUNG_ATOM AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}

	

	private void addSearchDefLookupTable(String cLookupTableName, String cFieldName, String cLookupId, String cFieldId, String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM,"
			+bibE2.cTO()+"."+cLookupTableName + 
			" WHERE JT_BEWEGUNG_ATOM." + cFieldId +" = " + cLookupTableName + "." + cLookupId + 
			" AND TO_CHAR(" + cLookupTableName+"."+cFieldName+") = '#WERT#'";
		else
			cSearch = "SELECT JT_BEWEGUNG_ATOM.ID_BEWEGUNG_ATOM FROM "+bibE2.cTO()+".JT_BEWEGUNG_ATOM,"
			+bibE2.cTO()+"."+cLookupTableName + 
			" WHERE JT_BEWEGUNG_ATOM." + cFieldId +" = " + cLookupTableName + "." + cLookupId + 
			" AND UPPER(" + cLookupTableName+"."+cFieldName+") like Upper('%#WERT#%')";
	
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}

		
}
