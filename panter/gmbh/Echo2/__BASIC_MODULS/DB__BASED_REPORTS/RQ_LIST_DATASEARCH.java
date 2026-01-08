 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.RQ_CONST.TRANSLATOR;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class RQ_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public RQ_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.reporting_query.n(),_TAB.reporting_query.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(REPORTING_QUERY.aktiv.fn(),RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.aktiv),	true);
        this.addSearchDef(REPORTING_QUERY.id_reporting_query.fn(),RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.id_reporting_query),	true);
        this.addSearchDef(REPORTING_QUERY.query1.fn(),RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.query1),	true);
        this.addSearchDef(REPORTING_QUERY.query2.fn(),RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.query2),	true);
        this.addSearchDef(REPORTING_QUERY.query3.fn(),RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.query3),	true);
        this.addSearchDef(REPORTING_QUERY.query4.fn(),RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.query4),	true);
        this.addSearchDef(REPORTING_QUERY.realname_temptable.fn(),RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.realname_temptable),	true);
        this.addSearchDef(REPORTING_QUERY.table_basename.fn(),RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.table_basename),	true);
        this.addSearchDef(REPORTING_QUERY.titel_4_user.fn(),RQ_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY.titel_4_user),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_REPORTING_QUERY  FROM "+bibE2.cTO()+"."+_TAB.reporting_query.n()+" WHERE UPPER(TO_CHAR("+_TAB.reporting_query.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_REPORTING_QUERY  FROM "+bibE2.cTO()+"."+_TAB.reporting_query.n()+" WHERE UPPER(TO_CHAR("+_TAB.reporting_query.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
