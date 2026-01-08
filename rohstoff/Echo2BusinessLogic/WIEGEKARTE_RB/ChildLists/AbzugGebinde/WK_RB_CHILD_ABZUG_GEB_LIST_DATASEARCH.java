 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_ABZUG_GEB;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class WK_RB_CHILD_ABZUG_GEB_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public WK_RB_CHILD_ABZUG_GEB_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.wiegekarte_abzug_geb.n(),_TAB.scanner_settings.keyFieldName(),WK_RB_CHILD_ABZUG_GEB_CONST.MODUL_Identifier_LIST);
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(WIEGEKARTE_ABZUG_GEB.gebinde.fn(),WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.gebinde),	true);
        this.addSearchDef(WIEGEKARTE_ABZUG_GEB.gewicht_einzel.fn(),WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.gewicht_einzel),	true);
        this.addSearchDef(WIEGEKARTE_ABZUG_GEB.id_wiegekarte.fn(),WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.id_wiegekarte),	true);
        this.addSearchDef(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_abzug_geb.fn(),WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_abzug_geb),	true);
        this.addSearchDef(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_gebinde.fn(),WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.id_wiegekarte_gebinde),	true);
        this.addSearchDef(WIEGEKARTE_ABZUG_GEB.menge.fn(),WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.menge),	true);
        this.addSearchDef(WIEGEKARTE_ABZUG_GEB.sys_trigger_timestamp.fn(),WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.sys_trigger_timestamp),	true);
        this.addSearchDef(WIEGEKARTE_ABZUG_GEB.sys_trigger_uuid.fn(),WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.sys_trigger_uuid),	true);
        this.addSearchDef(WIEGEKARTE_ABZUG_GEB.sys_trigger_version.fn(),WK_RB_CHILD_ABZUG_GEB_READABLE_FIELD_NAME.getReadable(WIEGEKARTE_ABZUG_GEB.sys_trigger_version),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_WIEGEKARTE_ABZUG_GEB  FROM "+bibE2.cTO()+"."+_TAB.wiegekarte_abzug_geb.n()+" WHERE UPPER(TO_CHAR("+_TAB.wiegekarte_abzug_geb.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_WIEGEKARTE_ABZUG_GEB  FROM "+bibE2.cTO()+"."+_TAB.wiegekarte_abzug_geb.n()+" WHERE UPPER(TO_CHAR("+_TAB.wiegekarte_abzug_geb.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
