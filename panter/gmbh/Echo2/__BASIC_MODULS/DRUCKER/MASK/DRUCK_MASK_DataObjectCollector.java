package panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER.MASK;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.__BASIC_MODULS.DRUCKER._DRUCK_KEY;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class DRUCK_MASK_DataObjectCollector extends RB_DataobjectsCollector_V2 {
    
	public DRUCK_MASK_DataObjectCollector() throws myException {
        super();
        this.registerComponent(new _DRUCK_KEY(), new DRUCK_MASK_DataObject());
    }
    
    
    public DRUCK_MASK_DataObjectCollector(String id_drucker, MASK_STATUS status) throws myException {
        super();
        this.registerComponent(new _DRUCK_KEY(), new DRUCK_MASK_DataObject(new Rec20(_TAB.drucker)._fill_id(id_drucker), status));
    }
    
    @Override
    public void database_to_dataobject(Object id_drucker) throws myException {
       //wird bei verbundenen dataObjects benoetigt, wo bei der basis-id gestartet, alle aufgebaut wird
    }


	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv)
			throws myException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector,
			MyE2_MessageVector mv) throws myException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException {
		// TODO Auto-generated method stub
		return null;
	}
}
 
