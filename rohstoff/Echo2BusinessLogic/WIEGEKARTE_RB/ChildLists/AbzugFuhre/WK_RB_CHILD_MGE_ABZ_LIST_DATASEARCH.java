 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugFuhre;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class WK_RB_CHILD_MGE_ABZ_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public WK_RB_CHILD_MGE_ABZ_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.wiegekarte_mge_abz.n(),_TAB.scanner_settings.keyFieldName(),E2_MODULNAME_ENUM.MODUL.WK_RB_CHILD_LIST_MGE_ABZ.get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(WIEGEKARTE_MGE_ABZ.abzug_menge.fn(),WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.abzug_menge),	true);
        this.addSearchDef(WIEGEKARTE_MGE_ABZ.abzug_prozent.fn(),WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.abzug_prozent),	true);
        this.addSearchDef(WIEGEKARTE_MGE_ABZ.id_abzugsgrund.fn(),WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.id_abzugsgrund),	true);
        this.addSearchDef(WIEGEKARTE_MGE_ABZ.id_wiegekarte.fn(),WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.id_wiegekarte),	true);
        this.addSearchDef(WIEGEKARTE_MGE_ABZ.id_wiegekarte_mge_abz.fn(),WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.id_wiegekarte_mge_abz),	true);
        this.addSearchDef(WIEGEKARTE_MGE_ABZ.langtext.fn(),WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.langtext),	true);
        this.addSearchDef(WIEGEKARTE_MGE_ABZ.sys_trigger_timestamp.fn(),WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.sys_trigger_timestamp),	true);
        this.addSearchDef(WIEGEKARTE_MGE_ABZ.sys_trigger_uuid.fn(),WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.sys_trigger_uuid),	true);
        this.addSearchDef(WIEGEKARTE_MGE_ABZ.sys_trigger_version.fn(),WK_RB_CHILD_MGE_ABZ_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_MGE_ABZ.sys_trigger_version),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_WIEGEKARTE_MGE_ABZ  FROM "+bibE2.cTO()+"."+_TAB.wiegekarte_mge_abz.n()+" WHERE UPPER(TO_CHAR("+_TAB.wiegekarte_mge_abz.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_WIEGEKARTE_MGE_ABZ  FROM "+bibE2.cTO()+"."+_TAB.wiegekarte_mge_abz.n()+" WHERE UPPER(TO_CHAR("+_TAB.wiegekarte_mge_abz.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
