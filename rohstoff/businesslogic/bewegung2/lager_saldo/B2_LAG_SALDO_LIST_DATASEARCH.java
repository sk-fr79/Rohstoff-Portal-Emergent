package rohstoff.businesslogic.bewegung2.lager_saldo;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import rohstoff.businesslogic.bewegung2.lager_saldo.B2_LAG_SALDO_CONST.TRANSLATOR;
import panter.gmbh.basics4project.DB_ENUMS.BG_STATION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class B2_LAG_SALDO_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public B2_LAG_SALDO_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.bg_station.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(BG_STATION.fax.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.fax),	true);
        this.addSearchDef(BG_STATION.hausnummer.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.hausnummer),	true);
        this.addSearchDef(BG_STATION.id_adresse.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.id_adresse),	true);
        this.addSearchDef(BG_STATION.id_adresse_basis.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.id_adresse_basis),	true);
        this.addSearchDef(BG_STATION.id_adresse_besitz_ldg.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.id_adresse_besitz_ldg),	true);
        this.addSearchDef(BG_STATION.id_bg_del_info.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.id_bg_del_info),	true);
        this.addSearchDef(BG_STATION.id_bg_station.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.id_bg_station),	true);
        this.addSearchDef(BG_STATION.id_bg_storno_info.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.id_bg_storno_info),	true);
        this.addSearchDef(BG_STATION.id_bg_vektor.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.id_bg_vektor),	true);
        this.addSearchDef(BG_STATION.id_land.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.id_land),	true);
        this.addSearchDef(BG_STATION.id_wiegekarte.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.id_wiegekarte),	true);
        this.addSearchDef(BG_STATION.kontrollmenge.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.kontrollmenge),	true);
        this.addSearchDef(BG_STATION.name1.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.name1),	true);
        this.addSearchDef(BG_STATION.name2.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.name2),	true);
        this.addSearchDef(BG_STATION.name3.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.name3),	true);
        this.addSearchDef(BG_STATION.oeffnungszeiten.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.oeffnungszeiten),	true);
        this.addSearchDef(BG_STATION.ort.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.ort),	true);
        this.addSearchDef(BG_STATION.ortzusatz.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.ortzusatz),	true);
        this.addSearchDef(BG_STATION.plz.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.plz),	true);
        this.addSearchDef(BG_STATION.pos_in_mask.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.pos_in_mask),	true);
        this.addSearchDef(BG_STATION.strasse.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.strasse),	true);
        this.addSearchDef(BG_STATION.telefon.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.telefon),	true);
        this.addSearchDef(BG_STATION.timestamp_in_mask.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.timestamp_in_mask),	true);
        this.addSearchDef(BG_STATION.umsatzsteuerid.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.umsatzsteuerid),	true);
        this.addSearchDef(BG_STATION.umsatzsteuerlkz.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.umsatzsteuerlkz),	true);
        this.addSearchDef(BG_STATION.wiegekartenkenner.fn(),B2_LAG_SALDO_READABLE_FIELD_NAME.getReadable(BG_STATION.wiegekartenkenner),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_BG_STATION  FROM "+bibE2.cTO()+"."+_TAB.bg_station.n()+" WHERE UPPER(TO_CHAR("+_TAB.bg_station.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_BG_STATION  FROM "+bibE2.cTO()+"."+_TAB.bg_station.n()+" WHERE UPPER(TO_CHAR("+_TAB.bg_station.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
