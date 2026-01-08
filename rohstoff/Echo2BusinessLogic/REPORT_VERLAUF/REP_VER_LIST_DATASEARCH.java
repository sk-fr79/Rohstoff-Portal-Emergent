package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.DB_ENUMS.REPORT_LOG;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.REPORT_VERLAUF.REP_VER_CONST.TRANSLATOR;
  
public class REP_VER_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public REP_VER_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.report_log.n(),_TAB.scanner_settings.keyFieldName(),TRANSLATOR.LIST.get_modul().get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(REPORT_LOG.id_report_log.fn(),REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.id_report_log),			true);
        this.addSearchDef(REPORT_LOG.report_datei_name.fn(),REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_datei_name),	true);
        this.addSearchDef(REPORT_LOG.report_druck_am.fn(),REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_druck_am),		true);
        this.addSearchDef(REPORT_LOG.report_druck_von.fn(),REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_druck_von),	true);
        this.addSearchDef(REPORT_LOG.report_jasperfile.fn(),REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_jasperfile),	true);
        this.addSearchDef(REPORT_LOG.report_titel.fn(),REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_titel),			true);
        this.addSearchDef(REPORT_LOG.report_uuid.fn(),REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_uuid),				true);
        this.addSearchDef(REPORT_LOG.report_weg.fn(),REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_weg),				true);
        
//        this.add_SearchElement(
//        		new SEL(REPORT_LOG.id_report_log)
//        		.FROM(_TAB.report_log)
//        		.INNERJOIN(_TAB.user, USER.id_user, REPORT_LOG.report_druck_von)
//        		.WHERE("UPPER("+USER.name.tnfn()+")", "LIKE",  "UPPER('%#WERT#%')")
//        		.OR("UPPER("+USER.kuerzel.tnfn()+")", "LIKE",  "UPPER('%#WERT#%')").s(), 
//        		S.ms(REP_VER_READABLE_FIELD_NAME.getReadable(REPORT_LOG.report_druck_von))
//        		);
        
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_REPORT_LOG  FROM "+bibE2.cTO()+"."+_TAB.report_log.n()+" WHERE UPPER(TO_CHAR("+_TAB.report_log.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_REPORT_LOG  FROM "+bibE2.cTO()+"."+_TAB.report_log.n()+" WHERE UPPER(TO_CHAR("+_TAB.report_log.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
