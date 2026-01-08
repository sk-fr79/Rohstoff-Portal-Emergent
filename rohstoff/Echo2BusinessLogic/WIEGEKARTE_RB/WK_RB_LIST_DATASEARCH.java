 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.CONTAINER;
import panter.gmbh.basics4project.DB_ENUMS.LAGERPLATZ;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class WK_RB_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public WK_RB_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.wiegekarte.n(),_TAB.scanner_settings.keyFieldName(),E2_MODULNAME_ENUM.MODUL.WK_RB_LIST.get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
    
    
    E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
    this.set_oSearchAgent(oSearchAgent);
	
	this.addSearchDef("ID_WIEGEKARTE","ID",true);

	//hier kommen die Felder	
	this.addSearchDef(WIEGEKARTE.id_adresse_lager.fn(),"ID Lager",false);
	this.addSearchDef(WIEGEKARTE.id_adresse_lieferant.fn(),"ID Kunde",false);
	this.addSearchDef(WIEGEKARTE.id_adresse_spedition.fn(),"ID Spedition", false);
	this.addSearchDef(WIEGEKARTE.id_artikel_bez.fn(),"ID Sorte",false);
	this.addSearchDef(WIEGEKARTE.id_vpos_tpa_fuhre.fn(),"ID Fuhre",false);
	this.addSearchDef(WIEGEKARTE.id_vpos_tpa_fuhre_ort.fn(),"ID Fuhrenort",false);
	this.addSearchDef(WIEGEKARTE.ist_lieferant.fn(),"Wareneingang",false);
	this.addSearchDef(WIEGEKARTE.kennzeichen.fn(),"Kennzeichen",false);
	this.addSearchDef(WIEGEKARTE.trailer.fn(),"Trailer",false);
	
	this.addSearchDef(WIEGEKARTE.lfd_nr.fn(),"WiegekartenNr",false);
	this.addSearchDef(WIEGEKARTE.es_nr.fn(), "EingangsscheinNr", false);
	this.addSearchDef(WIEGEKARTE.container_nr.fn(), "Container Nr.", false);
	this.addSearchDef(WIEGEKARTE.siegel_nr.fn(), "Siegel Nr.", false);
	
	
	

	this.addSearchDefLookupTable("JT_VPOS_TPA_FUHRE", "JT_VPOS_TPA_FUHRE.BUCHUNGSNR_FUHRE", "ID_VPOS_TPA_FUHRE", "ID_VPOS_TPA_FUHRE", "Buchungsnummer", true);
	
	this.addSearchDefArtikel("Sorte");
	
	this.addSearchDefLookupTable("JT_ADRESSE", "JT_ADRESSE.NAME1||JT_ADRESSE.NAME2||JT_ADRESSE.PLZ||JT_ADRESSE.ORT", "ID_ADRESSE", "ID_ADRESSE_LIEFERANT", "Kunde", false);
	this.addSearchDefLookupTable("JT_ADRESSE", "JT_ADRESSE.NAME1||JT_ADRESSE.NAME2||JT_ADRESSE.PLZ||JT_ADRESSE.ORT", "ID_ADRESSE", "ID_ADRESSE_SPEDITION", "Spedition", false);
	this.addSearchDefLookupTable("JT_ADRESSE", "JT_ADRESSE.NAME1||JT_ADRESSE.NAME2||JT_ADRESSE.PLZ||JT_ADRESSE.ORT", "ID_ADRESSE", "ID_ADRESSE_ABN_STRECKE", "Abnehmer Strecke", false);

	this.addSearchDefLookupTable(CONTAINER.fullTabName(), CONTAINER.container_nr.tnfn(), CONTAINER.id_container.fn(),WIEGEKARTE.id_container_eigen.fn(), "Container Eigen", false);
	
	this.addSearchDefLookupTable(LAGERPLATZ.fullTabName(), LAGERPLATZ.bezeichnung.tnfn(), LAGERPLATZ.id_lagerplatz.fn(),WIEGEKARTE.id_lagerplatz_absetz.fn(), "Absetz-Lagerplatz", false);
	this.addSearchDefLookupTable(LAGERPLATZ.fullTabName(), LAGERPLATZ.bezeichnung.tnfn(), LAGERPLATZ.id_lagerplatz.fn(),WIEGEKARTE.id_lagerplatz_schuett.fn(), "Schütt-Lagerplatz", false);
	

	
	this.addSearchDef("BEMERKUNG1","Bemerkung WK",false);
	this.addSearchDef("BEMERKUNG_INTERN","Bemerkung ES",false);
	this.addSearchDef("BEFUND","Befund",false);

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
 
 
