 
package panter.gmbh.Echo2.__BASIC_MODULS.REMINDER.LIST_MASK;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V1;
import panter.gmbh.Echo2.RB.DATA.RB_StatementBuilderCollector;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_REMINDER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_REMINDER_DEF;
import panter.gmbh.indep.exceptions.myException;


public class REM_MASK_DataObjectCollector extends RB_DataobjectsCollector_V1 {
	
    public REM_MASK_DataObjectCollector() throws myException {
        super();
        this.registerComponent(new RB_KM(_TAB.reminder_def), new REM_MASK_DataObject(new RECORDNEW_REMINDER_DEF()));
    }
    
    
    public REM_MASK_DataObjectCollector(String id_reminder_def, MASK_STATUS status) throws myException {
        super();
        this.registerComponent(new RB_KM(_TAB.reminder_def), new REM_MASK_DataObject(new RECORD_REMINDER_DEF(id_reminder_def),status));
    }
    
    @Override
    public void database_to_dataobject(Object id_reminder_def) throws myException {
    }

    @Override
    public void dataobject_to_database_connect_RB_MASK_DATA(RB_StatementBuilderCollector Statemenbuilder_Collector)    throws myException {
    }
    


}
 
