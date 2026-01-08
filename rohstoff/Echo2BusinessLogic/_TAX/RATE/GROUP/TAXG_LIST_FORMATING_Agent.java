 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
  
import panter.gmbh.Echo2.ListAndMask.E2_ComponentMAP;
import panter.gmbh.Echo2.ListAndMask.XX_ComponentMAP_SubqueryAGENT;
import panter.gmbh.indep.dataTools.SQLResultMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.PARAMHASH;
  
public class TAXG_LIST_FORMATING_Agent extends XX_ComponentMAP_SubqueryAGENT  {
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
    public TAXG_LIST_FORMATING_Agent(PARAMHASH  p_params) throws myException {
       this.params = p_params;
    }
    public void PrepareComponents_For_NEW(E2_ComponentMAP oMAP)    throws myException   {
        this.params.putAll(this.params);        
    }
    public void fillComponents(E2_ComponentMAP oMAP, SQLResultMAP oUsedResultMAP) throws myException  {
        this.params.putAll(this.params);        
    }
}
 
