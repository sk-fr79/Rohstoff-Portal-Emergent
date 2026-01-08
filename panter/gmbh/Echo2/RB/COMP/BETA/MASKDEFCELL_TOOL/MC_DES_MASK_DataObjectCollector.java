package panter.gmbh.Echo2.RB.COMP.BETA.MASKDEFCELL_TOOL;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class MC_DES_MASK_DataObjectCollector extends RB_DataobjectsCollector_V2 {
	
	public MC_DES_MASK_DataObjectCollector() throws myException {
		super();
		this.registerComponent(new MC_DES_KEY(), new MC_DES_MASK_DataObject());
	}

	public MC_DES_MASK_DataObjectCollector(String id_mask_def_cell, MASK_STATUS status) throws myException {
		super();
		if(S.isFull(id_mask_def_cell)) {
			this.registerComponent(new  MC_DES_KEY(), new MC_DES_MASK_DataObject(new Rec20(_TAB.mask_def_cell)._fill_id(id_mask_def_cell),status));
		}
	}

	@Override
	public void database_to_dataobject(Object startPoint) throws myException {

	}

	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException {
		return null;
	}


	@Override
	public void manipulate_filled_records_before_save(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv)
			throws myException {

	}

	@Override
	public void execute_final_statements_in_open_transaction(RB_DataobjectsCollector_V2 do_collector,
			MyE2_MessageVector mv) throws myException {
	}
}

