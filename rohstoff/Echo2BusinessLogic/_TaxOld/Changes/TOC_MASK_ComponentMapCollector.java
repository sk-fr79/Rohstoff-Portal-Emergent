 
package rohstoff.Echo2BusinessLogic._TaxOld.Changes;
   
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
  
public class TOC_MASK_ComponentMapCollector extends RB_ComponentMapCollector {
	
    //zentrale hashmap fuer transport von infos
 
	private RB_TransportHashMap   m_tpHashMap = null;
    
	public TOC_MASK_ComponentMapCollector(RB_TransportHashMap  p_tpHashMap) throws myException {
        super();
        
        this.m_tpHashMap = p_tpHashMap;     
	    this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MASK_COMPONENT_MAP_COLLECTOR,this);
        
        this.registerComponent(TOC_CONST.getLeadingMaskKey(), new TOC_MASK_ComponentMap(this.m_tpHashMap));
    }
 	
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}
 
}
 
 
