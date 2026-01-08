 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL;
  
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.PARAMHASH;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.DAUGHTER_ARTIKEL.BOR_ART_CONST.BOR_ART_PARAMS;
   
public class BOR_ART_MASK_DataObject extends RB_Dataobject_V2 {
 
    //zentrale hashmap fuer transport von infos
    private PARAMHASH   params = null;
 
 
    public BOR_ART_MASK_DataObject(Rec20 recORD, MASK_STATUS status, PARAMHASH  p_params)     throws myException {
        super(recORD, status);
        
        this.params = p_params;     
        
    }
 
    public BOR_ART_MASK_DataObject(PARAMHASH  p_params) throws myException {
        super(_TAB.bordercrossing_artikel);
        
         this.params = p_params;     
        
    }
}
 
