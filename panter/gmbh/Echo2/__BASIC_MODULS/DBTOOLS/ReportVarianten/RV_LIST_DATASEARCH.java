 
package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ReportVarianten;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.REP_VARIANTEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class RV_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public RV_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.rep_varianten.n(),_TAB.scanner_settings.keyFieldName(),E2_MODULNAME_ENUM.MODUL.REP_VARIANTEN_MASKE_LIST.get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(REP_VARIANTEN.id_rep_varianten.fn(),RV_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN.id_rep_varianten),	true);
        this.addSearchDef(REP_VARIANTEN.rep_file_name.fn(),RV_READABLE_FIELD_NAME.getReadable(REP_VARIANTEN.rep_file_name),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_REP_VARIANTEN  FROM "+bibE2.cTO()+"."+_TAB.rep_varianten.n()+" WHERE UPPER(TO_CHAR("+_TAB.rep_varianten.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_REP_VARIANTEN  FROM "+bibE2.cTO()+"."+_TAB.rep_varianten.n()+" WHERE UPPER(TO_CHAR("+_TAB.rep_varianten.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
