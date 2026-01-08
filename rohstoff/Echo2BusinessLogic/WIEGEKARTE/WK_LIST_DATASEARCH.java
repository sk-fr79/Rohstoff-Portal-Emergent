package rohstoff.Echo2BusinessLogic.WIEGEKARTE;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.E2_NavigationList;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.indep.exceptions.myException;


public class WK_LIST_DATASEARCH extends E2_DataSearch
{

	public WK_LIST_DATASEARCH(E2_NavigationList oNaviList,String MODUL_KENNER) throws myException
	{
		super("JT_WIEGEKARTE","ID_WIEGEKARTE",MODUL_KENNER);
		
		E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(oNaviList);
		this.set_oSearchAgent(oSearchAgent);
		
		this.addSearchDef("ID_WIEGEKARTE","ID",true);

		//hier kommen die Felder	
		this.addSearchDef("ID_ADRESSE_LAGER","ID Lager",false);
		this.addSearchDef("ID_ADRESSE_LIEFERANT","ID Kunde",false);
		this.addSearchDef("ID_ADRESSE_SPEDITION","ID Spedition", false);
		this.addSearchDef("ID_ARTIKEL_BEZ","ID Sorte",false);
		this.addSearchDef("ID_VPOS_TPA_FUHRE","ID Fuhre",false);
		this.addSearchDef("ID_VPOS_TPA_FUHRE_ORT","ID Fuhrenort",false);
		this.addSearchDef("IST_LIEFERANT","Wareneingang",false);
		this.addSearchDef("KENNZEICHEN","Kennzeichen",false);
		this.addSearchDef("LFD_NR","WiegekartenNr",false);
		this.addSearchDef("ES_NR", "EingangsscheinNr", false);
		this.addSearchDef(WIEGEKARTE.container_nr.fieldName(), "Container Nr.", false);
		this.addSearchDef(WIEGEKARTE.siegel_nr.fieldName(), "Siegel Nr.", false);
		

		this.addSearchDefLookupTable("JT_VPOS_TPA_FUHRE", "JT_VPOS_TPA_FUHRE.BUCHUNGSNR_FUHRE", "ID_VPOS_TPA_FUHRE", "ID_VPOS_TPA_FUHRE", "Buchungsnummer", true);
		
//		this.addSearchDefLookupTable("JT_ARTIKEL_BEZ", "JT_ARTIKEL_BEZ.ARTBEZ1||JT_ARTIKEL_BEZ.ARTBEZ2", "ID_ARTIKEL_BEZ", "ID_ARTIKEL_BEZ", "Sorte", false);
		
		this.addSearchDefArtikel("Sorte");
		
		
		this.addSearchDefLookupTable("JT_ADRESSE", "JT_ADRESSE.NAME1||JT_ADRESSE.NAME2||JT_ADRESSE.PLZ||JT_ADRESSE.ORT", "ID_ADRESSE", "ID_ADRESSE_LIEFERANT", "Kunde", false);
		this.addSearchDefLookupTable("JT_ADRESSE", "JT_ADRESSE.NAME1||JT_ADRESSE.NAME2||JT_ADRESSE.PLZ||JT_ADRESSE.ORT", "ID_ADRESSE", "ID_ADRESSE_SPEDITION", "Spedition", false);
		this.addSearchDefLookupTable("JT_ADRESSE", "JT_ADRESSE.NAME1||JT_ADRESSE.NAME2||JT_ADRESSE.PLZ||JT_ADRESSE.ORT", "ID_ADRESSE", "ID_ADRESSE_ABN_STRECKE", "Abnehmer Strecke", false);

		
		
		this.addSearchDef("BEMERKUNG1","Bemerkung WK",false);
		this.addSearchDef("BEMERKUNG_INTERN","Bemerkung ES",false);
		this.addSearchDef("BEFUND","Befund",false);
		

		
//		this.addSearchDef("NETTOGEWICHT","Nettogewicht",false);
		
//		this.addSearchDefRefTable("JT_VPOS_TPA_FUHRE_ORT", "ID_WIEGEKARTE", "ID FUHRENORT", true);
		
		// Fuhre ist ein wenig komplizierter, da 2 mögliche Felder
//		this.add_SearchElement("SELECT JT_WIEGEKARTE.ID_WIEGEKARTE FROM "+bibE2.cTO()+".JT_WIEGEKARTE,"+bibE2.cTO()+".JT_VPOS_TPA_FUHRE " +
//				" WHERE ( JT_WIEGEKARTE.ID_WIEGEKARTE = JT_VPOS_TPA_FUHRE.ID_WIEGEKARTE_ABN " +
//				" AND TO_CHAR(JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)='#WERT#')" +
//				" OR ( JT_WIEGEKARTE.ID_WIEGEKARTE = JT_VPOS_TPA_FUHRE.ID_WIEGEKARTE_LIEF " +
//				"	AND TO_CHAR(JT_VPOS_TPA_FUHRE.ID_VPOS_TPA_FUHRE)='#WERT#')" ,new MyE2_String("ID FUHRE"));
		

		/*
		this.addSearchDef("AUFGABEKURZ","Aufgabe (kurz)",false);
		this.addSearchDef("AUFGABENTEXT","Aufgabe (Details)",false);
		this.addSearchDef("ANTWORTKURZ","Ergebnis (kurz)",false);
		this.addSearchDef("ANTWORTTEXT","Ergebnis (Details)",false);
		*/		

		//20180523: datenbank gestützte suche zufuegen
		this.initAfterConstruction();

	}

	
	// Sondersuche nach Artikelbezeichnung...
	private void addSearchDefArtikel (String cInfoText) throws myException{
		String cSearch = "";
		cSearch = " SELECT W.ID_WIEGEKARTE from "+bibE2.cTO()+".JT_WIEGEKARTE W "
				+ " INNER JOIN JT_ARTIKEL_BEZ B ON W.id_artikel_bez = B.id_artikel_bez " 
				+ " INNER JOIN JT_ARTIKEL A ON B.id_artikel = A.id_artikel "
				+ " WHERE UPPER(replace(NVL(A.ANR1,'') || ' - ' ||NVL(B.ANR2,'') || ' ' || NVL(B.ARTBEZ1,'') || NVL2(B.ARTBEZ2,' ' || B.ARTBEZ2,''),' ','')) LIKE upper(replace('%#WERT#%',' ','')) ";
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}

	
	private void addSearchDef(String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_WIEGEKARTE.ID_WIEGEKARTE FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE TO_CHAR(JT_WIEGEKARTE."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_WIEGEKARTE.ID_WIEGEKARTE FROM "+bibE2.cTO()+".JT_WIEGEKARTE WHERE UPPER(JT_WIEGEKARTE."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	

	private void addSearchDefRefTable(String cRefTableName, String cFieldName,String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_WIEGEKARTE.ID_WIEGEKARTE FROM "+bibE2.cTO()+".JT_WIEGEKARTE,"+bibE2.cTO()+"."+cRefTableName+" WHERE JT_WIEGEKARTE.ID_WIEGEKARTE="+cRefTableName+".ID_WIEGEKARTE AND TO_CHAR("+cRefTableName+"."+cFieldName+")='#WERT#'";
		else
			cSearch = "SELECT JT_WIEGEKARTE.ID_WIEGEKARTE FROM "+bibE2.cTO()+".JT_WIEGEKARTE,"+bibE2.cTO()+"."+cRefTableName+" WHERE  JT_WIEGEKARTE.ID_WIEGEKARTE="+cRefTableName+".ID_WIEGEKARTE AND UPPER("+cRefTableName+"."+cFieldName+") like upper('%#WERT#%')";
		
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}


	private void addSearchDefLookupTable(String cLookupTableName, String cFieldName, String cLookupId, String cFieldId, String cInfoText, boolean bNumber) throws myException
	{
		String cSearch = "";
		if (bNumber)
			cSearch = "SELECT JT_WIEGEKARTE.ID_WIEGEKARTE FROM "+bibE2.cTO()+".JT_WIEGEKARTE,"
			+bibE2.cTO()+"."+cLookupTableName + 
			" WHERE JT_WIEGEKARTE." + cFieldId +" = " + cLookupTableName + "." + cLookupId + 
			" AND TO_CHAR(" + cFieldName+") = '#WERT#'";
		else
			cSearch = "SELECT JT_WIEGEKARTE.ID_WIEGEKARTE FROM "+bibE2.cTO()+".JT_WIEGEKARTE,"
			+bibE2.cTO()+"."+cLookupTableName + 
			" WHERE JT_WIEGEKARTE." + cFieldId +" = " + cLookupTableName + "." + cLookupId + 
			" AND UPPER(" + cFieldName + ") like Upper('%#WERT#%')";
	
		this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
	}
	
}
