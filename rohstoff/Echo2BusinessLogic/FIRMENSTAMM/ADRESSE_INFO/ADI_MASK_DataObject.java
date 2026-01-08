 
package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.ADRESSE_INFO;
 
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ADRESSE_INFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE_INFO;
import panter.gmbh.indep.exceptions.myException;
 
public class ADI_MASK_DataObject extends RB_Dataobject_V1 {
 
    public ADI_MASK_DataObject(RECORD_ADRESSE_INFO recORD, MASK_STATUS status)     throws myException {
        super(recORD, status);
    }
 
    public ADI_MASK_DataObject(RECORDNEW_ADRESSE_INFO recNEW) throws myException {
        super(recNEW);
    }
 
 
}
 
