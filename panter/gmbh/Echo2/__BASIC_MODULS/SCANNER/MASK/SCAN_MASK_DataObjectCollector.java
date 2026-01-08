package panter.gmbh.Echo2.__BASIC_MODULS.SCANNER.MASK;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.Echo2.__BASIC_MODULS.SCANNER._SCAN_KEY;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_MASK_DataObjectCollector extends RB_DataobjectsCollector_V2 {


	public SCAN_MASK_DataObjectCollector() throws myException {
		super();
		this.registerComponent(new _SCAN_KEY(), new SCAN_MASK_DataObject());
	}
	
	
	public SCAN_MASK_DataObjectCollector(String id_scannersetting, MASK_STATUS status) throws myException {
		super();
		this.registerComponent(new _SCAN_KEY(), new SCAN_MASK_DataObject(new Rec20(_TAB.scanner_settings)._fill_id(id_scannersetting), status));
	}

	@Override
	public void database_to_dataobject(Object id_scannersetting) throws myException {

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
