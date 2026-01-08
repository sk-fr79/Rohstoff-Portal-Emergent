 
package panter.gmbh.Echo2.basic_tools.emailv2;
  
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearch;
import panter.gmbh.Echo2.ListAndMask.List.Search.E2_DataSearchAgentList;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class EM2_LIST_DATASEARCH extends E2_DataSearch {
    
  	
	//zentrale hashmap fuer transport von infos
	private RB_TransportHashMap   m_tpHashMap = null;
    
    
    public EM2_LIST_DATASEARCH(RB_TransportHashMap  p_tpHashMap) throws myException    {
        super(_TAB.email_send.n(),_TAB.email_send.keyFieldName(),E2_MODULNAME_ENUM.MODUL.EMAIL_SEND_V2_LIST.get_callKey());
        
        this.m_tpHashMap = p_tpHashMap;        
        
        
        E2_DataSearchAgentList oSearchAgent = new E2_DataSearchAgentList(this.m_tpHashMap.getNavigationList());
        this.set_oSearchAgent(oSearchAgent);
        
        this.addSearchDef(EMAIL_SEND.betreff.fn(),EM2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND.betreff),	true);
        this.addSearchDef(EMAIL_SEND.betreff_2_send.fn(),EM2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND.betreff_2_send),	true);
        this.addSearchDef(EMAIL_SEND.id_email_send.fn(),EM2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND.id_email_send),	true);
        this.addSearchDef(EMAIL_SEND.id_table.fn(),EM2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND.id_table),	true);
        this.addSearchDef(EMAIL_SEND.sender_adress.fn(),EM2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND.sender_adress),	true);
        this.addSearchDef(EMAIL_SEND.send_type.fn(),EM2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND.send_type),	true);
        this.addSearchDef(EMAIL_SEND.table_base_name.fn(),EM2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND.table_base_name),	true);
        this.addSearchDef(EMAIL_SEND.text.fn(),EM2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND.text),	true);
        this.addSearchDef(EMAIL_SEND.text_2_send.fn(),EM2_READABLE_FIELD_NAME.getReadableForMask(EMAIL_SEND.text_2_send),	true);
  
        //20180523: datenbank gestuetzte suche zufuegen
        this.initAfterConstruction();
    }
    
    
    private void addSearchDef(String cFieldName, String cInfoText, boolean searchWithLike) throws myException   {
    
        String cSearch = null;
        if (searchWithLike) {
           cSearch = "SELECT id_EMAIL_SEND  FROM "+bibE2.cTO()+"."+_TAB.email_send.n()+" WHERE UPPER(TO_CHAR("+_TAB.email_send.n()+"."+cFieldName+")) like UPPER('%#WERT#%')";
        } else {
           cSearch = "SELECT id_EMAIL_SEND  FROM "+bibE2.cTO()+"."+_TAB.email_send.n()+" WHERE UPPER(TO_CHAR("+_TAB.email_send.n()+"."+cFieldName+"))=UPPER('#WERT#')";
        }           
        
        this.add_SearchElement(cSearch,new MyE2_String(cInfoText));
    }
    
        
}
 
 
