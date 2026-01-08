 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugGebinde;
   
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
   
public class WK_RB_CHILD_ABZUG_GEB_MASK_DataObject extends RB_Dataobject_V2 {
 
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
 
 
    public WK_RB_CHILD_ABZUG_GEB_MASK_DataObject(Rec22 recORD, MASK_STATUS status, RB_TransportHashMap  p_tpHashMap)     throws myException {
        super(recORD, status);
        
        this.m_tpHashMap = p_tpHashMap;     
        
    }
 
    public WK_RB_CHILD_ABZUG_GEB_MASK_DataObject(RB_TransportHashMap  p_params) throws myException {
        super(_TAB.wiegekarte_abzug_geb);
        
         this.m_tpHashMap = p_params;     
    }
    
    
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
 
	
}
 
 
