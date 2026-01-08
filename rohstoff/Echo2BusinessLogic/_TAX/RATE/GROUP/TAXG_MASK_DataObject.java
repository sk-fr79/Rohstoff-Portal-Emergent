 
package rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP;
  
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic._TAX.RATE.GROUP.TAXG_CONST.PARAMHASH;
   
public class TAXG_MASK_DataObject extends RB_Dataobject_V2 {
 
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
 
 
    public TAXG_MASK_DataObject(Rec20 recORD, MASK_STATUS status, PARAMHASH  p_params)     throws myException {
        super(recORD, status);
        
        this.params = p_params;     
        
    }
 
    public TAXG_MASK_DataObject(PARAMHASH  p_params) throws myException {
        super(_TAB.tax_group);
        
         this.params = p_params;     
        
    }
}
 
