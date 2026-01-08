package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.__MUELLEIMER;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_STATION;

public class FZ_SAVE_logi_start_and_ziel {

	private RB_DataobjectsCollector_V2 	do_collector 	= null;
	private IF_MasterKey 				key 			= null;
	private MASK_STATUS 				status 			= null;

	private KEY_STATION					start_station	= null;
	private KEY_STATION					ziel_station	= null;
	private KEY_ATOM					atom_right		= null;
	private KEY_ATOM					atom_left		= null;
	
	public FZ_SAVE_logi_start_and_ziel() {}

	public FZ_SAVE_logi_start_and_ziel _set_collector(RB_DataobjectsCollector_V2 p_do_collector){
		this.do_collector = p_do_collector;
		return this;
	}

	public FZ_SAVE_logi_start_and_ziel _set_master_key(IF_MasterKey p_key){
		this.key = p_key;
		return this;
	}

	public FZ_SAVE_logi_start_and_ziel _set_atoms(KEY_ATOM p_left_atom, KEY_ATOM p_right_atom){
		this.atom_left 	= p_left_atom;
		this.atom_right = p_right_atom;
		return this;
	}
	
	public FZ_SAVE_logi_start_and_ziel _set_stations(KEY_STATION p_start_station, KEY_STATION p_ziel_station){
		this.start_station 	= p_start_station;
		this.ziel_station 	= p_ziel_station;
		return this;
	}
	
	public FZ_SAVE_logi_start_and_ziel _set_mask_status(MASK_STATUS p_status){
		this.status = p_status;
		return this;
	}

	public FZ_SAVE_logi_start_and_ziel _SAVE(MyE2_MessageVector mv) throws myException{
		if(this.do_collector == null){
			mv._addAlarm("FZ_SAVE_logi_start_and_ziel: Dataobjectcollector is null, please set a valid dataobjectcollector");
		
		}else if(this.key == null){
			mv._addAlarm("FZ_SAVE_logi_start_and_ziel: Master key is null, please set a valid master key");
		
		}else if(this.status == null){
			mv._addAlarm("FZ_SAVE_logi_start_and_ziel: Status is null, please set a valid status");
		
		}else if(this.atom_left == null){
			mv._addAlarm("FZ_SAVE_logi_start_and_ziel: atom_left is null, please set a valid atom_left");
		
		}else if(this.atom_right == null){
			mv._addAlarm("FZ_SAVE_logi_start_and_ziel: atom_right is null, please set a valid atom_right");
		
		}else if(this.start_station == null){
			mv._addAlarm("FZ_SAVE_logi_start_and_ziel: start_station is null, please set a valid start_station");
		
		}else if(this.ziel_station == null){
			mv._addAlarm("FZ_SAVE_logi_start_and_ziel: ziel_station is null, please set a ziel_station");
		}

		if(mv.get_bIsOK()){
			String id_bewegung_atom_links = null;
			String id_bewegung_atom_rechts = null;

			String id_bewegung_station_start_links = null;
			String id_bewegung_station_ziel_rechts = null;

			if(this.status== MASK_STATUS.NEW){
				id_bewegung_station_start_links = 	this.do_collector.get(this.start_station).rec20().get_rec_after_save_new().get_ufs_dbVal(BEWEGUNG_STATION.id_bewegung_station);
				id_bewegung_station_ziel_rechts= 	this.do_collector.get(this.ziel_station).rec20().get_rec_after_save_new().get_ufs_dbVal(BEWEGUNG_STATION.id_bewegung_station);

				id_bewegung_atom_links = 			this.do_collector.get(this.atom_left).rec20().get_rec_after_save_new().get_ufs_dbVal(BEWEGUNG_ATOM.id_bewegung_atom);
				id_bewegung_atom_rechts = 			this.do_collector.get(this.atom_right).rec20().get_rec_after_save_new().get_ufs_dbVal(BEWEGUNG_ATOM.id_bewegung_atom);

			} else {		
				//hier edit
				id_bewegung_station_start_links = 	this.do_collector.get(this.start_station).rec20().get_ufs_dbVal(BEWEGUNG_STATION.id_bewegung_station);
				id_bewegung_station_ziel_rechts= 	this.do_collector.get(this.ziel_station).rec20().get_ufs_dbVal(BEWEGUNG_STATION.id_bewegung_station);

				id_bewegung_atom_links = 			this.do_collector.get(this.atom_left).rec20().get_ufs_dbVal(BEWEGUNG_ATOM.id_bewegung_atom);
				id_bewegung_atom_rechts = 			this.do_collector.get(this.atom_right).rec20().get_ufs_dbVal(BEWEGUNG_ATOM.id_bewegung_atom);
			}

			if (S.isAllFull(id_bewegung_atom_links,id_bewegung_atom_rechts,id_bewegung_station_start_links,id_bewegung_station_ziel_rechts)) {

				Rec20 rec_atom_links = new Rec20(_TAB.bewegung_atom)._fill_id(id_bewegung_atom_links)
						._nv(BEWEGUNG_ATOM.id_bewegungstation_logi_start, id_bewegung_station_start_links, mv)
						._nv(BEWEGUNG_ATOM.id_bewegungstation_logi_ziel, id_bewegung_station_ziel_rechts, mv);

				Rec20 rec_atom_rechts = new Rec20(_TAB.bewegung_atom)._fill_id(id_bewegung_atom_rechts)
						._nv(BEWEGUNG_ATOM.id_bewegungstation_logi_start, id_bewegung_station_start_links, mv)
						._nv(BEWEGUNG_ATOM.id_bewegungstation_logi_ziel, id_bewegung_station_ziel_rechts, mv);

				rec_atom_links._SAVE(false, mv);
				rec_atom_rechts._SAVE(false, mv);

			} else {
				mv.add_MESSAGE(new MyE2_Alarm_Message(S.mt("Achtung! Systemfehler beim Speichern !!")));
			}
		}else{
			
		}
		return this;
	}
}
