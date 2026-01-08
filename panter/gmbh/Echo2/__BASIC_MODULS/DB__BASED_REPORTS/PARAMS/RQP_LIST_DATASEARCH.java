 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.PARAMS;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.PARAMS.RQP_CONST.TRANSLATOR;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_PARAM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class RQP_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public RQP_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.reporting_query_param.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(REPORTING_QUERY_PARAM.id_reporting_query.fn(),RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.id_reporting_query),	true);
        this.addSearchDef(REPORTING_QUERY_PARAM.id_reporting_query_param.fn(),RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.id_reporting_query_param),	true);
        this.addSearchDef(REPORTING_QUERY_PARAM.paramdefault.fn(),RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.paramdefault),	true);
        this.addSearchDef(REPORTING_QUERY_PARAM.paramkey.fn(),RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.paramkey),	true);
        this.addSearchDef(REPORTING_QUERY_PARAM.paramname_4_user.fn(),RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.paramname_4_user),	true);
        this.addSearchDef(REPORTING_QUERY_PARAM.typ.fn(),RQP_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_PARAM.typ),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_REPORTING_QUERY_PARAM  FROM "+bibE2.cTO()+"."+_TAB.reporting_query_param.n()+" WHERE UPPER(TO_CHAR("+_TAB.reporting_query_param.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_REPORTING_QUERY_PARAM  FROM "+bibE2.cTO()+"."+_TAB.reporting_query_param.n()+" WHERE UPPER(TO_CHAR("+_TAB.reporting_query_param.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
