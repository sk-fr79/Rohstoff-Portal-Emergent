 
package rohstoff.Echo2BusinessLogic._TaxOld.Changes;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.MWSTSCHLUESSEL_AENDERUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class TOC_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public TOC_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.mwstschluessel_aenderungen.n(),_TAB.scanner_settings.keyFieldName(),E2_MODULNAME_ENUM.MODUL.MWSTSCHLUESSEL_OLD_AENDERUNGEN_LIST.get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(MWSTSCHLUESSEL_AENDERUNGEN.beschreibung.fn(),TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.beschreibung),	true);
        this.addSearchDef(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis.fn(),TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_bis),	true);
        this.addSearchDef(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von.fn(),TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.gueltig_von),	true);
        this.addSearchDef(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel.fn(),TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel),	true);
        this.addSearchDef(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen.fn(),TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.id_mwstschluessel_aenderungen),	true);
        this.addSearchDef(MWSTSCHLUESSEL_AENDERUNGEN.steuersatz.fn(),TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.steuersatz),	true);
        this.addSearchDef(MWSTSCHLUESSEL_AENDERUNGEN.sys_trigger_timestamp.fn(),TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.sys_trigger_timestamp),	true);
        this.addSearchDef(MWSTSCHLUESSEL_AENDERUNGEN.sys_trigger_uuid.fn(),TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.sys_trigger_uuid),	true);
        this.addSearchDef(MWSTSCHLUESSEL_AENDERUNGEN.sys_trigger_version.fn(),TOC_READABLE_FIELD_NAME.getReadable(MWSTSCHLUESSEL_AENDERUNGEN.sys_trigger_version),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_MWSTSCHLUESSEL_AENDERUNGEN  FROM "+bibE2.cTO()+"."+_TAB.mwstschluessel_aenderungen.n()+" WHERE UPPER(TO_CHAR("+_TAB.mwstschluessel_aenderungen.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_MWSTSCHLUESSEL_AENDERUNGEN  FROM "+bibE2.cTO()+"."+_TAB.mwstschluessel_aenderungen.n()+" WHERE UPPER(TO_CHAR("+_TAB.mwstschluessel_aenderungen.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
