 
package rohstoff.Echo2BusinessLogic.DienstleistungsProfile;
   
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.indep.exceptions.myException;
  
public class DL_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
	
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
    
	public DL_MASK_ComponentMapCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.m_tpHashMap = p_tpHashMap;     
	    this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_COMPONENT_MAP_COLLECTOR,this);
        
        this.registerComponent(DL_CONST.getLeadingMaskKey(), new DL_MASK_ComponentMap(this.m_tpHashMap));
    }
 	
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
 
}
 
 
