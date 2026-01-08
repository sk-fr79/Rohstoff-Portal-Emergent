/**
 * rohstoff.Echo2BusinessLogic.LAGERHALTUNG
 * @author sebastien
 * @date 05.12.2018
 * 
 */
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;

import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author sebastien
 * @date 05.12.2018
 *
 */
public class LH_MASK_Box_DataObject extends RB_Dataobject_V2 {

	private RB_TransportHashMap m_tpHashMap;

	public LH_MASK_Box_DataObject(Rec21 recORD, MASK_STATUS status, RB_TransportHashMap  p_tpHashMap)     throws myException {
        super(recORD, status);
        
        this.m_tpHashMap = p_tpHashMap;     
        
    }
 
    public LH_MASK_Box_DataObject(RB_TransportHashMap  p_params) throws myException {
        super(_TAB.lager_box);
        
         this.m_tpHashMap = p_params;     
    }
    
    
	public RB_TransportHashMap getParams() {
		return m_tpHashMap;
	}

}
