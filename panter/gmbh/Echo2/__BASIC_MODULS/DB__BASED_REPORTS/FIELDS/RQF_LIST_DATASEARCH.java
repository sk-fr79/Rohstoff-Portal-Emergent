 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS.RQF_CONST.TRANSLATOR;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_FIELD;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class RQF_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public RQF_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.reporting_query_field.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(REPORTING_QUERY_FIELD.aktiv.fn(),RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.aktiv),	true);
        this.addSearchDef(REPORTING_QUERY_FIELD.alignment.fn(),RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.alignment),	true);
        this.addSearchDef(REPORTING_QUERY_FIELD.breite_liste_px.fn(),RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.breite_liste_px),	true);
        this.addSearchDef(REPORTING_QUERY_FIELD.fieldname.fn(),RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.fieldname),	true);
        this.addSearchDef(REPORTING_QUERY_FIELD.fieldname_4_user.fn(),RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.fieldname_4_user),	true);
        this.addSearchDef(REPORTING_QUERY_FIELD.id_reporting_query.fn(),RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.id_reporting_query),	true);
        this.addSearchDef(REPORTING_QUERY_FIELD.id_reporting_query_field.fn(),RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.id_reporting_query_field),	true);
        this.addSearchDef(REPORTING_QUERY_FIELD.is_searchfield.fn(),RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.is_searchfield),	true);
        this.addSearchDef(REPORTING_QUERY_FIELD.is_selectorfield.fn(),RQF_READABLE_FIELD_NAME.getReadable(REPORTING_QUERY_FIELD.is_selectorfield),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_REPORTING_QUERY_FIELD  FROM "+bibE2.cTO()+"."+_TAB.reporting_query_field.n()+" WHERE UPPER(TO_CHAR("+_TAB.reporting_query_field.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_REPORTING_QUERY_FIELD  FROM "+bibE2.cTO()+"."+_TAB.reporting_query_field.n()+" WHERE UPPER(TO_CHAR("+_TAB.reporting_query_field.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
