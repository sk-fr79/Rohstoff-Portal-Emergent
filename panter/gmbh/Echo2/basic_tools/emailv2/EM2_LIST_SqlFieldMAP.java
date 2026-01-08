 
package panter.gmbh.Echo2.basic_tools.emailv2;
  
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.DB_ENUMS.EMAIL_SEND;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.SQLFieldForRestrictTableRange;
import panter.gmbh.indep.exceptions.myException;
  
public class EM2_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
	
    //zentrale hashmap fuer transport von infos
    private EM2_TransportHashMap   m_tpHashMap = null;
    
    public EM2_LIST_SqlFieldMAP(EM2_TransportHashMap  p_tpHashMap) throws myException   {
    	
        super(_TAB.email_send.n(), "", false);
        
        this.m_tpHashMap = p_tpHashMap;        
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);
        
        _TAB  table = m_tpHashMap.getTable();
        
        if (table!=null) {
        	this.add_SQLField(new SQLFieldForRestrictTableRange(_TAB.email_send.fullTableName()
        														, EMAIL_SEND.table_base_name.fn()
        														, EMAIL_SEND.table_base_name.fn()
        														, S.ms("Tabelle")
        														,"'"+m_tpHashMap.getTable().baseTableName()+"'"
        														,bibE2.get_CurrSession()),true);
        	
        	this.add_SQLField(new SQLFieldForRestrictTableRange(_TAB.email_send.fullTableName()
																, EMAIL_SEND.id_table.fn()
																, EMAIL_SEND.id_table.fn()
																, S.ms("Ref-Tabellen-ID")
																,m_tpHashMap.getTableId().toString()
																,bibE2.get_CurrSession()),true);
        	
        }
        
        
        this.initFields();
    }
    
}
 
 
