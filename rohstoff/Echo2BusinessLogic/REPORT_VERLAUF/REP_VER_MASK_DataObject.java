package rohstoff.Echo2BusinessLogic.REPORT_VERLAUF;
  
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
   
public class REP_VER_MASK_DataObject extends RB_Dataobject_V2 {
 
	private RB_TransportHashMap   m_tpHashMap = null; 
 
    public REP_VER_MASK_DataObject(Rec20 recORD, MASK_STATUS status, RB_TransportHashMap  p_tpHashMap)     throws myException {
        super(recORD, status);
        
        this.m_tpHashMap = p_tpHashMap;             
    }
 
    public REP_VER_MASK_DataObject(RB_TransportHashMap  p_tpHashMap) throws myException {
        super(_TAB.report_log);
        
        this.m_tpHashMap = p_tpHashMap;             
    }
    
    public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
}
 
