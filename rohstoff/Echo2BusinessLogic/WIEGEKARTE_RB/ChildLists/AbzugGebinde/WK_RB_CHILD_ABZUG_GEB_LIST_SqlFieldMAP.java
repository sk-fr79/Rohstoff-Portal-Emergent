 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde;
  
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class WK_RB_CHILD_ABZUG_GEB_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public WK_RB_CHILD_ABZUG_GEB_LIST_SqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {
    	
        super(_TAB.wiegekarte_abzug_geb.n(), "", false);
        
        this.m_tpHashMap = p_tpHashMap;        
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);
        
        this.initFields();
    }
    
}
 
 
