 
package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.KOSTEN_NG;
 
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_TPA_FUHRE_KOSTEN;
import panter.gmbh.indep.exceptions.myException;
 
public class FK_MASK_DataObject extends RB_Dataobject_V1 {
 
    public FK_MASK_DataObject(RECORD_VPOS_TPA_FUHRE_KOSTEN recORD, MASK_STATUS status)     throws myException {
        super(recORD, status);
    }
 
    public FK_MASK_DataObject(RECORDNEW_VPOS_TPA_FUHRE_KOSTEN recNEW) throws myException {
        super(recNEW);
    }
 
 
}
 
