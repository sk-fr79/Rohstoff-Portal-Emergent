package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.__MUELLEIMER;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;

public class FZ_SAVE_Gesamtpreis {

	private RB_DataobjectsCollector_V2 	do_collector 	= null;
	private IF_MasterKey 				key 			= null;
	private MASK_STATUS 				status 			= null;

	private KEY_ATOM 					atom_left		= null;
	private KEY_ATOM					atom_right		= null;
	
	public FZ_SAVE_Gesamtpreis() {
	}
	
	public FZ_SAVE_Gesamtpreis _set_collector(RB_DataobjectsCollector_V2 p_do_collector){
		this.do_collector = p_do_collector;
		return this;
	}

	public FZ_SAVE_Gesamtpreis _set_master_key(IF_MasterKey p_key){
		this.key = p_key;
		return this;
	}
	
	public FZ_SAVE_Gesamtpreis _set_atoms(KEY_ATOM p_atom_left, KEY_ATOM p_atom_right){
		this.atom_left = 	p_atom_left;
		this.atom_right = 	p_atom_right;
		return this;
	}
	
	public FZ_SAVE_Gesamtpreis _set_mask_status(MASK_STATUS p_status){
		this.status = p_status;
		return this;
	}
	
	public FZ_SAVE_Gesamtpreis _SAVE(MyE2_MessageVector mv) throws myException{
		if(this.do_collector == null){
			mv._addAlarm("FZ_SAVE_ids: Dataobjectcollector is null, please set a valid dataobjectcollector");

		}else if(this.key == null){
			mv._addAlarm("FZ_SAVE_ids: Master key is null, please set a valid master key");

		}else if(this.status == null){
			mv._addAlarm("FZ_SAVE_ids: Status is null, please set a valid status");

		}else if(this.atom_left == null){
			mv._addAlarm("FZ_SAVE_ids: left_atom is null, please set a left_atom");

		}else if(this.atom_right == null){
			mv._addAlarm("FZ_SAVE_ids: right_atom is null, please set a right_atom");
		}
		
		return this;
		
	}
}
