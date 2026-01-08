package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL;


import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.FZ_dataObjectsCollector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_atom;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_vektor;



public class LL_DO__Collector extends FZ_dataObjectsCollector {

	private __LL_MASTER_KEY key = null;

	// neuerfassung
	public LL_DO__Collector(__LL_MASTER_KEY p_key) throws myException {
		super();
		this.key = p_key;
		this.registerComponent(this.key, 								new LL_DO_Bewegung());
		this.registerComponent(this.key.k_vektor(), 					new LL_DO_Vektor());

		this.registerComponent(this.key.k_vektor_pos(), 				new LL_DO_VektorPos());
		
		this.registerComponent(this.key.k_atom_left(), 				new LL_DO_Atom());
		this.registerComponent(this.key.k_atom_left__lager_start(), 	new LL_DO_Station());
		this.registerComponent(this.key.k_atom_left__lager_ziel(), 	new LL_DO_Station());

		this.registerComponent(this.key.k_atom_right(), 				new LL_DO_Atom());
		this.registerComponent(this.key.k_atom_right__lager_start(), 	new LL_DO_Station());
		this.registerComponent(this.key.k_atom_right__lager_ziel(), 	new LL_DO_Station());
	}

	// bereits vorhandene daten oeffnen (edit oder view)
	public LL_DO__Collector(String id_bewegung_vektor, IF_MasterKey p_key) throws myException {
		super();

		this.key = (__LL_MASTER_KEY)p_key;

		//vorbereitung 
		this.get_keys().clear();

		//fixpunkte
		Rec20   rv = new Rec20(_TAB.bewegung_vektor)._fill_id(id_bewegung_vektor); 
		Rec20	rb = rv.get_up_Rec20(BEWEGUNG.id_bewegung);

		RecList20  rlp = rv.get_down_reclist20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, new vgl(BEWEGUNG_VEKTOR_POS.pos_typ, ENUM_VEKTORPOS_TYP.LL_MAIN.db_val()).s(), null);

		RecList20  rla = rlp.get(0).get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, new And(new vgl_YN(BEWEGUNG_ATOM.deleted,false)).s(),BEWEGUNG_ATOM.id_bewegung_atom.fieldName());

		Rec20_atom  record_atom_START = new Rec20_atom(rla.get(0));
		Rec20_atom  record_atom_ZIEL = new Rec20_atom(rla.get(1));

		if (rlp.size()>1) {
			throw new myException(this, "!!Error! Multiple Vektor-POS of type LL_MAIN forbidden !!!");
		} else if (rlp.size()==0) {
			throw new myException(this, "!!Error! no Vektor-POS of type LL_MAIN found !!!");
		}

		this.registerComponent(this.key, 								new LL_DO_Bewegung(rb,			this.key.get_mask_status()));
		this.registerComponent(this.key.k_vektor(),  					new LL_DO_Vektor(rv, 			this.key.get_mask_status()));
		this.registerComponent(this.key.k_vektor_pos(),  				new LL_DO_VektorPos(rlp.get(0), this.key.get_mask_status()));
		
		this.registerComponent(this.key.k_atom_left(),  				new LL_DO_Atom(rla.get(0), 								this.key.get_mask_status()));
		this.registerComponent(this.key.k_atom_left__lager_start(), 	new LL_DO_Station(record_atom_START.__station_start(),	this.key.get_mask_status()));
		this.registerComponent(this.key.k_atom_left__lager_ziel(), 	new LL_DO_Station(record_atom_START.__station_ziel(),	this.key.get_mask_status()));

		this.registerComponent(this.key.k_atom_right(),  				new LL_DO_Atom(rla.get(1), 								this.key.get_mask_status()));
		this.registerComponent(this.key.k_atom_right__lager_start(), 	new LL_DO_Station(record_atom_ZIEL.__station_start(),	this.key.get_mask_status()));
		this.registerComponent(this.key.k_atom_right__lager_ziel(), 	new LL_DO_Station(record_atom_ZIEL.__station_ziel(),	this.key.get_mask_status()));

	}

//	@Override
//	public void database_to_dataobject(Object id_bewegung_atom) throws myException {
//	}

	
	
	// zusaetzliche getter um einfacheren zugriff auf die komponenten zu haben
	public __LL_MASTER_KEY k_bewegung() {
		return this.key;
	}

	
	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException { 
		LL_DO__Collector v_neu = new LL_DO__Collector(this.key);
		v_neu.rb_hm_DataObjects().putAll(this.rb_hm_DataObjects());
		return v_neu;
	}

	@Override
	public void manipulate_filled_records_before_save_individual(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException {

//		Rec20_vektor r_vektor = new Rec20_vektor(do_collector.get(this.key.k_vektor()).rec20());
	
//		//alle automatik-saetze loeschen
//		this.EXT_DO_Collector().get_v_statements_in_front().addAll(new Rec20_vektor(r_vektor).generate_sql_2_delete_all_automatic_datasets());
//		
//		if (this.key.get_mask_status() == MASK_STATUS.NEW) {
//			this.get(this.key.k_vektor()).rec20()._put_raw_value(BEWEGUNG_VEKTOR.id_bewegung, 							_TAB.bewegung.seq_currval(), 			false);
//			this.get(this.key.k_vektor_pos()).rec20()._put_raw_value(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, 			_TAB.bewegung_vektor.seq_currval(), 	false);
//
//			this.get(this.key.k_atom_left()).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegung, 							_TAB.bewegung.seq_currval(), 			false);
//			this.get(this.key.k_atom_left()).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor, 					_TAB.bewegung_vektor.seq_currval(), 	false);
//			this.get(this.key.k_atom_left()).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor_pos, 				_TAB.bewegung_vektor_pos.seq_currval(), false);
//			this.get(this.key.k_atom_left()).rec20()._put_raw_value(BEWEGUNG_ATOM.posnr, 								"1", 									false);
//
//			this.get(this.key.k_atom_right()).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegung, 						_TAB.bewegung.seq_currval(), 			false);
//			this.get(this.key.k_atom_right()).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor, 					_TAB.bewegung_vektor.seq_currval(), 	false);
//			this.get(this.key.k_atom_right()).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegung_vektor_pos, 				_TAB.bewegung_vektor_pos.seq_currval(), false);
//			this.get(this.key.k_atom_right()).rec20()._put_raw_value(BEWEGUNG_ATOM.posnr, 								"2", 									false);
//
//			this.get(this.key.k_atom_left__lager_start()).rec20()._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, 	_TAB.bewegung_atom.seq_currval(), 		false);
//			this.get(this.key.k_atom_left__lager_ziel()).rec20()._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, 		_TAB.bewegung_atom.seq_currval(), 		false);
//
//			this.get(this.key.k_atom_right__lager_start()).rec20()._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, 	_TAB.bewegung_atom.seq_currval(), 		false);
//			this.get(this.key.k_atom_right__lager_ziel()).rec20()._put_raw_value(BEWEGUNG_STATION.id_bewegung_atom, 	_TAB.bewegung_atom.seq_currval(), 		false);
//			
//		}
//		
//		if(this.key.get_mask_status()== MASK_STATUS.EDIT){
//
//			String id_station_start = do_collector.get(this.key.k_atom_left__lager_start()).rec20().get_key_value();
//			String id_station_ziel 	= do_collector.get(this.key.k_atom_right__lager_ziel()).rec20().get_key_value();
//
//			this.get(this.key.k_atom_left()).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegungstation_logi_start, 	id_station_start, 	false);
//			this.get(this.key.k_atom_left()).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegungstation_logi_ziel, 	id_station_ziel, 	false);
//
//			this.get(this.key.k_atom_right()).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegungstation_logi_start, 	id_station_start, 	false);
//			this.get(this.key.k_atom_right()).rec20()._put_raw_value(BEWEGUNG_ATOM.id_bewegungstation_logi_ziel, 	id_station_ziel, 	false);
//
//		}
	}

	@Override
	public IF_MasterKey get_master_key() {
		return this.key;
	}

	@Override
	public void execute_final_statements_in_open_transaction_individual(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException {
		
	}


}
