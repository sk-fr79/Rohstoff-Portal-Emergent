 
package panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.__CONTAINER_ADDONS.HELP_V2.HELP2_CONST.TRANSLATOR;
import panter.gmbh.basics4project.DB_ENUMS.HILFETEXT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class HELP2_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public HELP2_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.hilfetext.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(HILFETEXT.abschlussdatum.fn(),HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.abschlussdatum),	true);
        this.addSearchDef(HILFETEXT.hilfetext.fn(),HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.hilfetext),	true);
        this.addSearchDef(HILFETEXT.id_hilfetext.fn(),HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.id_hilfetext),	true);
        this.addSearchDef(HILFETEXT.id_user_bearbeiter.fn(),HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.id_user_bearbeiter),	true);
        this.addSearchDef(HILFETEXT.id_user_ursprung.fn(),HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.id_user_ursprung),	true);
        this.addSearchDef(HILFETEXT.id_version.fn(),HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.id_version),	true);
        this.addSearchDef(HILFETEXT.info_developer.fn(),HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.info_developer),	true);
        this.addSearchDef(HILFETEXT.modulkenner.fn(),HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.modulkenner),	true);
        this.addSearchDef(HILFETEXT.status.fn(),HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.status),	true);
        this.addSearchDef(HILFETEXT.ticketnummer.fn(),HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.ticketnummer),	true);
        this.addSearchDef(HILFETEXT.titel.fn(),HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.titel),	true);
        this.addSearchDef(HILFETEXT.typ.fn(),HELP2_READABLE_FIELD_NAME.getReadable(HILFETEXT.typ),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_HILFETEXT  FROM "+bibE2.cTO()+"."+_TAB.hilfetext.n()+" WHERE UPPER(TO_CHAR("+_TAB.hilfetext.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_HILFETEXT  FROM "+bibE2.cTO()+"."+_TAB.hilfetext.n()+" WHERE UPPER(TO_CHAR("+_TAB.hilfetext.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
