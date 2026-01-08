 
package panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.PARAMS;
   
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.Echo2.RB.BASICS.RB_TransportHashMap;
import panter.gmbh.Echo2.__BASIC_MODULS.DB__BASED_REPORTS.RQ__PARAM_TYP;
import panter.gmbh.Echo2.components.DB.MyE2IF__DB_Component;
import panter.gmbh.basics4project.DB_ENUMS.REPORTING_QUERY_PARAM;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
  
public class RQP_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
    //zentrale hashmap fuer transport von infos
    private RB_TransportHashMap   m_tpHashMap = null;
    
    public RQP_LIST_FORMATING_Agent(RB_TransportHashMap  p_tpHashMap) throws myException {
       this.m_tpHashMap = p_tpHashMap;
    }
    
    
    public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
    }
    
    public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException  {
    	
    	String valueOfType = oUsedResultMAP.get_UnFormatedValue(REPORTING_QUERY_PARAM.typ.fn());
    	String valueReadable = valueOfType;
    	
    	RQ__PARAM_TYP readable = RQ__PARAM_TYP.DATE.getEnum(valueOfType);
    	
    	if (readable!=null) {
    		valueReadable=readable.user_text();
    	}
    	
    	((MyE2IF__DB_Component)oMAP.get__Comp(REPORTING_QUERY_PARAM.typ)).set_cActualMaskValue(valueReadable);
    	
    }
}
 
 
