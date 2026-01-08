package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;


public class AT_MASK_DataObjectCollector extends RB_DataobjectsCollector_V2 {
	
    public AT_MASK_DataObjectCollector() throws myException {
        super();
        this.registerComponent(new RB_KM(_TAB.trigger_action_def), new AT_MASK_DataObject());
    }
    
    
    public AT_MASK_DataObjectCollector(String id_trigger_action_def, MASK_STATUS status) throws myException {
        super();
        this.registerComponent(new RB_KM(_TAB.trigger_action_def), new AT_MASK_DataObject(new Rec20(_TAB.trigger_action_def)._fill_id(id_trigger_action_def),status));
    }
    
    @Override
    public void database_to_dataobject(Object id_trigger_action_def) throws myException {
    }


	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException {
	}


	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector,MyE2_MessageVector mv) throws myException {
	}


	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException {
		return null;
	}
    

    
    
    
}
 
