 
package rohstoff.Echo2BusinessLogic.LAGERHALTUNG;
  
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap_ENUM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.db.Project_SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class LH_LIST_SqlFieldMAP extends Project_SQLFieldMAP  {
	
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public LH_LIST_SqlFieldMAP(RB_TransportHashMap  p_tpHashMap) throws myException   {
    	
        super(_TAB.lager_box.n(),"", false);
        
        this.m_tpHashMap = p_tpHashMap;        
        this.m_tpHashMap.put(RB_TransportHashMap_ENUM.MODULCONTAINER_LIST_SQL_FIELDMAP,this);

//        this.add_JOIN_Table(
//        		_TAB.lager_palette.fullTableName(), 
//        		_TAB.lager_palette.fullTableName(), 
//        		RIGHT_OUTER, 
//        		":"+LAGER_PALETTE.buchungsnr_hand.fn()+":",
//        		true, 
//        		LAGER_PALETTE.id_lager_box.tnfn()+"="+LAGER_BOX.id_lager_box.tnfn(), 
//        		"", 
//        		null);
        
        this.initFields();
    }
    
}
 
 
