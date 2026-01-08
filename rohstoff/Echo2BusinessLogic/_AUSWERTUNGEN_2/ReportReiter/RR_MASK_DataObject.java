 
package rohstoff.Echo2BusinessLogic._AUSWERTUNGEN_2.ReportReiter;
 
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_REPORT_REITER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REPORT_REITER;
import panter.gmbh.indep.exceptions.myException;
 
public class RR_MASK_DataObject extends RB_Dataobject_V1 {
 
    public RR_MASK_DataObject(RECORD_REPORT_REITER recORD, MASK_STATUS status)     throws myException {
        super(recORD, status);
    }
 
    public RR_MASK_DataObject(RECORDNEW_REPORT_REITER recNEW) throws myException {
        super(recNEW);
    }
 
 
}
 
