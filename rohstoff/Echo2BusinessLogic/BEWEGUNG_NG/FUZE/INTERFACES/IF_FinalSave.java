package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;

public interface IF_FinalSave {
	
	public IF_FinalSave _set_collector(RB_DataobjectsCollector_V2 p_do_collector);
	
	public IF_FinalSave _set_master_key(IF_MasterKey p_key);
	
	public IF_FinalSave _set_mask_status(MASK_STATUS p_status);
	
	public IF_FinalSave _set_atoms(KEY_ATOM p_atom_left, KEY_ATOM p_atom_right);
	
	public void _SAVE(MyE2_MessageVector mv) throws myException;
}
