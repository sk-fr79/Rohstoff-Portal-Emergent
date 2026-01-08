 
package rohstoff.Echo2BusinessLogic._TaxOld;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class TO_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public TO_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.mwstschluessel.n(),_TAB.scanner_settings.keyFieldName(),E2_MODULNAME_ENUM.MODUL.MWSTSCHLUESSEL_OLD_LIST.get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(MWSTSCHLUESSEL.id_mwstschluessel.fn(),	TO_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL.id_mwstschluessel),	true);
        this.addSearchDef(MWSTSCHLUESSEL.bezeichnung.fn(),			TO_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL.bezeichnung),	true);
        this.addSearchDef(MWSTSCHLUESSEL.kurzbezeichnung.fn(),		TO_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL.kurzbezeichnung),	true);
        this.addSearchDef(MWSTSCHLUESSEL.steuersatz.fn(),			TO_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL.steuersatz),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_MWSTSCHLUESSEL  FROM "+bibE2.cTO()+"."+_TAB.mwstschluessel.n()+" WHERE UPPER(TO_CHAR("+_TAB.mwstschluessel.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_MWSTSCHLUESSEL  FROM "+bibE2.cTO()+"."+_TAB.mwstschluessel.n()+" WHERE UPPER(TO_CHAR("+_TAB.mwstschluessel.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
