 
package rohstoff.Echo2BusinessLogic.WIEGEKARTE_RB.ChildLists.AbzugFuhre;
   
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.components.DB.MyE2_DB_Label_INGRID;
import panter.gmbh.basics4project.DB_ENUMS.ABZUGSGRUND;
import panter.gmbh.basics4project.DB_ENUMS.WIEGEKARTE_MGE_ABZ;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.dataTools.RECORD2.Rec22;
import panter.gmbh.indep.exceptions.myException;
  
public class WK_RB_CHILD_MGE_ABZ_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public WK_RB_CHILD_MGE_ABZ_LIST_FORMATING_Agent(RB_TransportHashMap  p_tpHashMap) throws myException {
       this.m_tpHashMap = p_tpHashMap;
    }
    
    
    public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
    }
    
    public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException  {
    	MyE2_DB_Label_INGRID oAbzugsgrund = (MyE2_DB_Label_INGRID) oMAP.get__Comp(WIEGEKARTE_MGE_ABZ.id_abzugsgrund);
    	
    	Rec22 rec = new Rec22(_TAB.abzugsgrund)._fill_id(oUsedResultMAP.getUfs(WIEGEKARTE_MGE_ABZ.id_abzugsgrund));
    	if (rec != null) {
    		oAbzugsgrund.setText(rec.getUfs(ABZUGSGRUND.abzugsgrund));
    	}
    	
    }
}
 
 
