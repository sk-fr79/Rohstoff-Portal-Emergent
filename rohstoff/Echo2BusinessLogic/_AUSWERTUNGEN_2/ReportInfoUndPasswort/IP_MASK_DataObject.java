 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportInfoUndPasswort;
 
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_REPORT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT;
import panter.gmbh.indep.exceptions.myException;
 
public class IP_MASK_DataObject extends RB_Dataobject_V1 {
 
    public IP_MASK_DataObject(RECORD_REPORT recORD, MASK_STATUS status)     throws myException {
        super(recORD, status);
    }
 
    public IP_MASK_DataObject(RECORDNEW_REPORT recNEW) throws myException {
        super(recNEW);
    }
 
 
}
 
