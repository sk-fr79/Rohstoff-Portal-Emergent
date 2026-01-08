 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.FIELDS;
   
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class RQF_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public RQF_LIST_FORMATING_Agent(RB_TransportHashMap  p_tpHashMap) throws myException {
       this.m_tpHashMap = p_tpHashMap;
    }
    
    
    public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
    }
    
    public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException  {
    }
}
 
 
