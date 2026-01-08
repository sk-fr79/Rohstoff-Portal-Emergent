package rohstoff.Echo2BusinessLogic.ZOLLTARIFNUMMER_OLD;
 
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZOLLTARIFNUMMER;
import panter.gmbh.indep.exceptions.myException;
 
public class ZOL_MASK_DataObject extends RB_Dataobject_V1 {
 
    public ZOL_MASK_DataObject(RECORD_ZOLLTARIFNUMMER recORD, MASK_STATUS status)     throws myException {
        super(recORD, status);
    }
 
    public ZOL_MASK_DataObject(RECORDNEW_ZOLLTARIFNUMMER recNEW) throws myException {
        super(recNEW);
    }
 
 
}
 
