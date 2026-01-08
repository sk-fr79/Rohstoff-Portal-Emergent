 
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;
 
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_Dataobject_V1;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_DEF;
import panter.gmbh.indep.exceptions.myException;
 
public class REM_MASK_DataObject extends RB_Dataobject_V1 {
 
    public REM_MASK_DataObject(RECORD_REMINDER_DEF recORD, MASK_STATUS status)     throws myException {
        super(recORD, status);
    }
 
    public REM_MASK_DataObject(RECORDNEW_REMINDER_DEF recNEW) throws myException {
        super(recNEW);
    }
 
 
}
 
