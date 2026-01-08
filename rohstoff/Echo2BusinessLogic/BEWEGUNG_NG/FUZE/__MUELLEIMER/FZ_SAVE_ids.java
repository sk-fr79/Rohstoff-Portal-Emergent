package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.__MUELLEIMER;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_STATION;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT_POS;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_vektor;

public class FZ_SAVE_ids {

	private RB_DataobjectsCollector_V2 	do_collector 	= null;
	private IF_MasterKey 				key 			= null;
	private MASK_STATUS 				status 			= null;

	private KEY_ATOM 					atom_left		= null;
	private KEY_ATOM					atom_right		= null;

	public FZ_SAVE_ids() {
	}

	public FZ_SAVE_ids _set_collector(RB_DataobjectsCollector_V2 p_do_collector){
		this.do_collector = p_do_collector;
		return this;
	}

	public FZ_SAVE_ids _set_master_key(IF_MasterKey p_key){
		this.key = p_key;
		return this;
	}

	public FZ_SAVE_ids _set_mask_status(MASK_STATUS p_status){
		this.status = p_status;
		return this;
	}

	public FZ_SAVE_ids _set_atoms(KEY_ATOM p_atom_left, KEY_ATOM p_atom_right){
		this.atom_left = 	p_atom_left;
		this.atom_right = 	p_atom_right;
		return this;
	}



	public FZ_SAVE_ids _SAVE(MyE2_MessageVector mv) throws myException{
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

		if(mv.get_bIsOK()){
			Rec20_vektor r_vektor = new Rec20_vektor(do_collector.get(this.key.k_vektor()).rec20());

			do_collector.EXT_DO_Collector().get_v_statements_in_front().addAll(new Rec20_vektor(r_vektor).generate_sql_2_delete_all_automatic_datasets());

			KEY_VEKT_POS vektor_pos=(KEY_VEKT_POS) this.key.k_vektor().get_direct_child_keys().get(0);

			KEY_ATOM atom1 		= 	atom_left;
			KEY_STATION	at1_st1 = 	(KEY_STATION) atom1.get_direct_child_keys().get(0);
			KEY_STATION	at1_st2 = 	(KEY_STATION) atom1.get_direct_child_keys().get(1);	

			KEY_ATOM atom2 = 		atom_right;
			KEY_STATION	at2_st1 = 	(KEY_STATION) atom2.get_direct_child_keys().get(0);
			KEY_STATION	at2_st2 = 	(KEY_STATION) atom2.get_direct_child_keys().get(1);	

			if(this.status== MASK_STATUS.NEW){

				do_collector.get(this.key.k_vektor()).rec20()._put_raw_value(BEWEGUNG_VEKTOR.id_bewegung, 	_TAB.bewegung.seq_currval(), 			false);
				do_collector.get(vektor_pos).rec20()._put_raw_value(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, _TAB.bewegung_vektor.seq_currval(), 	false);

				do_collector.get(atom1).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegung, 					_TAB.bewegung.seq_currval(), 			false);
				do_collector.get(atom1).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor, 			_TAB.bewegung_vektor.seq_currval(), 	false);
				do_collector.get(atom1).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor_pos,		_TAB.bewegung_vektor_pos.seq_currval(), false);
				do_collector.get(atom1).rec20()._put_raw_value(BEWEGUNG_ATOM.posnr, 						"1", 									false);

				do_collector.get(atom2).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegung, 					_TAB.bewegung.seq_currval(), 			false);
				do_collector.get(atom2).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor, 			_TAB.bewegung_vektor.seq_currval(), 	false);
				do_collector.get(atom2).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor_pos,		_TAB.bewegung_vektor_pos.seq_currval(), false);
				do_collector.get(atom2).rec20()._put_raw_value(BEWEGUNG_ATOM.posnr, 						"2", 									false);

				do_collector.get(at1_st1).rec20()._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, 		_TAB.bewegung_atom.seq_currval(), 		false);
				do_collector.get(at1_st2).rec20()._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, 		_TAB.bewegung_atom.seq_currval(), 		false);

				do_collector.get(at2_st1).rec20()._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, 		_TAB.bewegung_atom.seq_currval(), 		false);
				do_collector.get(at2_st2).rec20()._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, 		_TAB.bewegung_atom.seq_currval(), 		false);
			}


			if(this.status== MASK_STATUS.EDIT){

				String id_station_start = do_collector.get(at1_st1).rec20().get_key_value();
				String id_station_ziel 	= do_collector.get(at2_st2).rec20().get_key_value();

				do_collector.get(atom1).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegungstation_logi_start, 	id_station_start, 	false);
				do_collector.get(atom1).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegungstation_logi_ziel, 	id_station_ziel, 	false);

				do_collector.get(atom2).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegungstation_logi_start, 	id_station_start, 	false);
				do_collector.get(atom2).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegungstation_logi_ziel, 	id_station_ziel, 	false);

			}
		}
		return this;
	}
}
