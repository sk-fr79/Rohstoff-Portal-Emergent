
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG.PALETTE;
   
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.indep.exceptions.myException;
  
public class LH_P_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
	
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
    
	public LH_P_MASK_ComponentMapCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.m_tpHashMap = p_tpHashMap;     
	    this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_COMPONENT_MAP_COLLECTOR,this);
        
	    //Palette
        this.registerComponent(LH_P_CONST.getLeadingMaskKey(), new LH_P_MASK_ComponentMap(this.m_tpHashMap));
        
    }
 	
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
 
}
 
 
