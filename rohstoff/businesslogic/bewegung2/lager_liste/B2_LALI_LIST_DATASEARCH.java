package rohstoff.businesslogic.bewegung2.lager_liste;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.DB_ENUMS.BG_AUSWERT;
import panter.gmbh.basics4project.SANKTION_FREIGABE.ADR_FREIGABE_CONST.TRANSLATOR;
import panter.gmbh.indep.exceptions.myException;


public class B2_LALI_LIST_DATASEARCH extends E2_DataSearch
{

	private String sSearchBlockNavilist = "";

	public B2_LALI_LIST_DATASEARCH(E2_NavigationList oNaviList) throws myException
	{
		super("JT_BG_AUSWERT","ID_BG_AUSWERT",TRANSLATOR.LIST.get_modul().get_callKey());
	

		// SQL-JOINS von Navilist übernehmen
		sSearchBlockNavilist =  
				"SELECT " + BG_AUSWERT.id_bg_auswert.tnfn() + " FROM " + 
				oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cSQL_FROM_BLOCK()
				;
		
		String sStatic = oNaviList.get_oComponentMAP__REF().get_oSQLFieldMAP().get_cSQL_WHERE_BLOCK_STATIC();
		sSearchBlockNavilist += " WHERE " + sStatic;
	
		
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_BG_AUSWERT","ID",true);

		//hier kommen die Felder der Station
		
		// BG_AUSWERT
		this.addSearchDef("ID_ADRESSE","AdressID des Kundenlagers",true);
		this.addSearchDef("ID_ADRESSE_BESITZ_LDG","AdressID des Besitzers",false);
		this.addSearchDef("ID_BG_VEKTOR","ID Vektor",true);
		this.addSearchDef("ID_BG_ATOM","ID Atom",true);
		this.addSearchDef("ID_ARTIKEL","ID Artikel",true);
		this.addSearchDef("ID_ARTIKEL_BEZ","ID Artikelbezeichnung",true);
	

		
		// JT_ADRESSE / LAGERADRESSE
		this.addSearchDefJoinBasic("ADR_LAGER.NAME1||ADR_LAGER.NAME2||ADR_LAGER.NAME3||ADR_LAGER.STRASSE||ADR_LAGER.HAUSNUMMER||ADR_LAGER.PLZ||ADR_LAGER.ORT|| TO_CHAR(ADR_LAGER.ID_ADRESSE) ",  "Lager", false);

		// Kundenlager
		this.addSearchDefJoinBasic("ADR_KUNDE.NAME1||ADR_KUNDE.NAME2||ADR_KUNDE.NAME3||ADR_KUNDE.ORT||ADR_KUNDE.PLZ || TO_CHAR(ADR_KUNDE.ID_ADRESSE)||"+
				"ADR_KUNDE_BASIS.NAME1||ADR_KUNDE_BASIS.NAME2||ADR_KUNDE_BASIS.NAME3||ADR_KUNDE_BASIS.ORT||ADR_KUNDE_BASIS.PLZ || TO_CHAR(ADR_KUNDE_BASIS.ID_ADRESSE) "
				+ "","Kunden (Name/PLZ/ORT)",false);

		
//		// BG_ATOM_KONVERT
		this.addSearchDefJoinBasic("JT_BG_VEKTOR_KONVERT.ID_VPOS_TPA_FUHRE ", "ID Fuhre", true);
		this.addSearchDefJoinBasic("JT_BG_VEKTOR_KONVERT.ID_VPOS_TPA_FUHRE_ORT", "ID Fuhrenort", true);
		this.addSearchDefJoinBasic("JT_BG_VEKTOR_KONVERT.ID_LAGER_KONTO", "ID Lagerkonto-ALT", true);
		

//		// BG_ATOM
		this.addSearchDefJoinBasic("JT_BG_ATOM.BESTELLNUMMER","Bestellnummer",false);
		this.addSearchDefJoinBasic("JT_BG_ATOM.POSTENNUMMER","Postennummer",false);
		this.addSearchDefJoinBasic("JT_BG_ATOM.NATIONALER_ABFALL_CODE","Nationaler Abfallcode",false);
		this.addSearchDefJoinBasic("JT_BG_ATOM.NOTIFIKATION_NR","NotifikationNr",false);

		
//		// BG_VEKTOR
		this.addSearchDefJoinBasic("JT_BG_VEKTOR.ORDNUNGSNUMMER_CMR","Ordnungsnummer CMR",false);
		
		
//		// BG_STATION
		this.addSearchDefJoinBasic("S1.ID_WIEGEKARTE","ID Wiegekarten",true);
		this.addSearchDefJoinBasic("S1.WIEGEKARTENKENNER","Wiegekartenkenner",false);


		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();
		
	}

	
	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = sSearchBlockNavilist + " AND TO_CHAR(JT_BG_AUSWERT."+cFieldName+")='#WERT#'" ;
		else
			cSearch = sSearchBlockNavilist + " AND UPPER(JT_BG_AUSWERT."+cFieldName+") like upper('%#WERT#%') ";

		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	
	private void addSearchDefJoinBasic (String cFieldName,  String cInfoText, boolean bNumber) throws myException {
		String cSearch = "";
		if (bNumber)
			cSearch = sSearchBlockNavilist + " AND TO_CHAR("+ cFieldName+")='#WERT#'" ;
		else
			cSearch = sSearchBlockNavilist + " AND UPPER("+ cFieldName+") like upper('%#WERT#%') ";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
		
	}	
	

	private void addSearchDefJoin (String cFieldName, String cJoinTable, String cInfoText, boolean bNumber) throws myException {
		String cSearch = "";
		if (bNumber)
			cSearch = sSearchBlockNavilist + " AND TO_CHAR("+ cJoinTable +"."+cFieldName+")='#WERT#'" ;
		else
			cSearch = sSearchBlockNavilist + " AND UPPER("+ cJoinTable +"."+cFieldName+") like upper('%#WERT#%') ";

		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
		
	}
	
	

		
}
