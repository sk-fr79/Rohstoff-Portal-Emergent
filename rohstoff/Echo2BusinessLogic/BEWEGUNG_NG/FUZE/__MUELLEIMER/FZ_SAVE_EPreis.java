package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.__MUELLEIMER;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_FinalSave;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_PREISZUORDNUNG;

public class FZ_SAVE_EPreis implements IF_FinalSave {

	private RB_DataobjectsCollector_V2 	do_collector 	= null;
	private IF_MasterKey 				key 			= null;
	private MASK_STATUS 				status 			= null;

	private KEY_ATOM 					atom_left		= null;
	private KEY_ATOM					atom_right		= null;

	private ENUM_PREISZUORDNUNG			preis_zuordnung	= null;

	public FZ_SAVE_EPreis() {
	}

	@Override
	public FZ_SAVE_EPreis _set_collector(RB_DataobjectsCollector_V2 p_do_collector) {
		this.do_collector = p_do_collector;
		return this;
	}

	@Override
	public FZ_SAVE_EPreis _set_master_key(IF_MasterKey p_key) {
		this.key = p_key;
		return this;
	}

	@Override
	public FZ_SAVE_EPreis _set_mask_status(MASK_STATUS p_status) {
		this.status = p_status;
		return this;
	}

	@Override
	public FZ_SAVE_EPreis _set_atoms(KEY_ATOM p_atom_left, KEY_ATOM p_atom_right) {
		this.atom_left = p_atom_left;
		this.atom_right = p_atom_right;
		return this;
	}

	public FZ_SAVE_EPreis _set_rule(ENUM_PREISZUORDNUNG preis_ordnung){
		this.preis_zuordnung = preis_ordnung;
		return this;
	}

	@Override
	public void _SAVE(MyE2_MessageVector mv) throws myException {

		if(this.do_collector == null){
			mv._addAlarm("FZ_SAVE_EPreis: Dataobjectcollector ist null, bitte setzen Sie ein gultig dataobjectcollector");

		}else if(this.key == null){
			mv._addAlarm("FZ_SAVE_EPreis: Master key ist null, bitte setzen Sie ein gultig master key");

		}else if(this.status == null){
			mv._addAlarm("FZ_SAVE_EPreis: Status ist null, bitte setzen Sie ein gultig status");

		}else if(this.atom_left == null){
			mv._addAlarm("FZ_SAVE_EPreis: link atom ist null, bitte setzen Sie ein gultig link atom");

		}else if(this.atom_right == null){
			mv._addAlarm("FZ_SAVE_EPreis: recht atom ist null, bitte setzen Sie ein gultig recht atom");

		}else if(preis_zuordnung == null){
			mv._addAlarm("FZ_SAVE_EPreis: preis zuordnung ist nicht definiert");

		}

		if(mv.get_bIsOK()){
			KEY_ATOM atom_start = null;
			KEY_ATOM atom_ziel = null;

			switch(this.preis_zuordnung){
			case ATOM_LEFT_2_RIGHT:
				atom_start = this.atom_left;
				atom_ziel = this.atom_right;
				break;
			case ATOM_RIGHT_2_LEFT:
				atom_start = this.atom_right;
				atom_ziel = this.atom_left;
				break;
			case NOTHING:
				atom_start=null;
				atom_ziel=null;
				break;
			default:
				break;

			}
			
			if(atom_start != null && atom_ziel != null){
				String ePreis = this.do_collector.rb_ComponentMapCollector_ThisBelongsTo().get(atom_start).getRbComponentSavable(BEWEGUNG_ATOM.e_preis).rb_readValue_4_dataobject();
				
				String id_atom_ziel ="";
				
				if(this.status== MASK_STATUS.NEW){
					id_atom_ziel = this.do_collector.get(atom_ziel).rec20().get_rec_after_save_new().get_ufs_dbVal(BEWEGUNG_ATOM.id_bewegung_atom);
				}else{
					id_atom_ziel = this.do_collector.get(atom_ziel).rec20().get_ufs_dbVal(BEWEGUNG_ATOM.id_bewegung_atom);
				}
				
				Rec20 updatedrec = new Rec20(_TAB.bewegung_atom)._fill_id(id_atom_ziel);
				updatedrec._nv(BEWEGUNG_ATOM.e_preis,ePreis, mv);
				updatedrec._SAVE(false, mv);
			}
		}

	}
}
