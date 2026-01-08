 
package rohstoff.Echo2BusinessLogic.WORKFLOW.LAUFZETTEL_SIMPLE;
   
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V21;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;
   
public class WF_SIMPLE_MASK_DataObject_LAUFZETTEL extends RB_Dataobject_V21{
 
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
 
 
    public WF_SIMPLE_MASK_DataObject_LAUFZETTEL(Rec21 recORD, MASK_STATUS status, RB_TransportHashMap  p_tpHashMap)     throws myException {
        super(recORD, status);
        
        this.m_tpHashMap = p_tpHashMap;     
        
    }
 
    public WF_SIMPLE_MASK_DataObject_LAUFZETTEL(RB_TransportHashMap  p_params) throws myException {
        super(_TAB.laufzettel, MASK_STATUS.NEW);
         this.m_tpHashMap = p_params;     
    }
    
    
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
 
}
 
 
