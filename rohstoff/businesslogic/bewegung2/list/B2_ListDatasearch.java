 
package rohstoff.businesslogic.bewegung2.list;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.BG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class B2_ListDatasearch extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public B2_ListDatasearch(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.bg_vektor.n(),_TAB.scanner_settings.keyFieldName(),E2_MODULNAME_ENUM.MODUL.B_TRANSPORT_LIST.get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(BG_VEKTOR.en_transport_typ.fn(),		"Transport-Typ",	true);
        this.addSearchDef(BG_VEKTOR.id_adresse_logi_start.fn(), "Logistik: Startadresse",	true);
        this.addSearchDef(BG_VEKTOR.id_adresse_logi_ziel.fn(),	"Logistik: Zieladresse",	true);
        this.addSearchDef(BG_VEKTOR.id_bg_vektor.fn(),			"ID",	true);
        this.addSearchDef(BG_VEKTOR.id_handelsdef.fn(),				"ID-Handelsdefinition",	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_bg_vektor  FROM "+bibE2.cTO()+"."+_TAB.bg_vektor.n()+" WHERE UPPER(TO_CHAR("+_TAB.bg_vektor.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_bg_vektor  FROM "+bibE2.cTO()+"."+_TAB.bg_vektor.n()+" WHERE UPPER(TO_CHAR("+_TAB.bg_vektor.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
