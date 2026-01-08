package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;


import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR_POS;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.RecList20;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.dataTools.TERM.SELECT.And;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.FZ_dataObjectsCollector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_atom;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_bewegung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._RECS.Rec20_vektor;



public class ST_DO__Collector extends FZ_dataObjectsCollector {

	private __ST_MASTER_KEY key = null;

	// neuerfassung
	public ST_DO__Collector(__ST_MASTER_KEY p_key) throws myException {
		super();
		this.key = p_key;

		this.registerComponent(this.key, 													new ST_DO_Bewegung());

		this.registerComponent(this.key.k_vektor(), 										new ST_DO_Vektor());

		this.registerComponent(this.key.k_vektor_pos_left(),								new ST_DO_VektorPos());

		this.registerComponent(this.key.k_vektor_pos_left__atom_left(), 					new ST_DO_Atom());
		this.registerComponent(this.key.k_vektor_pos_left__atom_left__station_start(), 	new ST_DO_Station());
		this.registerComponent(this.key.k_vektor_pos_left__atom_left__station_ziel(), 	new ST_DO_Station());

		this.registerComponent(this.key.k_vektor_pos_left__atom_right(), 					new ST_DO_Atom());
		this.registerComponent(this.key.k_vektor_pos_left__atom_right__station_start(), 	new ST_DO_Station());
		this.registerComponent(this.key.k_vektor_pos_left__atom_right__station_ziel(), 	new ST_DO_Station());


		this.registerComponent(this.key.k_vektor_pos_right(), new ST_DO_VektorPos());

		this.registerComponent(this.key.k_vektor_pos_right__atom_left(), 					new ST_DO_Atom());
		this.registerComponent(this.key.k_vektor_pos_right__atom_left__station_start(), 	new ST_DO_Station());
		this.registerComponent(this.key.k_vektor_pos_right__atom_left__station_ziel(), 	new ST_DO_Station());

		this.registerComponent(this.key.k_vektor_pos_right__atom_right(), 				new ST_DO_Atom());
		this.registerComponent(this.key.k_vektor_pos_right__atom_right__station_start(), 	new ST_DO_Station());
		this.registerComponent(this.key.k_vektor_pos_right__atom_right__station_ziel(), 	new ST_DO_Station());
	}

	// bereits vorhandene daten oeffnen (edit oder view)
	public ST_DO__Collector(String id_bewegung_vektor, IF_MasterKey p_key) throws myException {
		super();

		this.key = (__ST_MASTER_KEY) p_key;

		// vorbereitung
		this.get_keys().clear();

		Rec20_vektor	r2_vektor 			= new Rec20_vektor()._fill_id(id_bewegung_vektor);

		Rec20_bewegung	r2_bewegung 		= r2_vektor.get_up_bewegung();

		RecList20 		r2_vektor_pos 		= r2_vektor.get_down_reclist20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, null, BEWEGUNG_VEKTOR_POS.posnr.fn());

		//links und rechts unterscheiden
		RecList20 		r2_vektor_pos_left 	= r2_vektor_pos.get_filtered_list(rec->rec.get_ufs_dbVal(BEWEGUNG_VEKTOR_POS.pos_typ,"").equals(ENUM_VEKTORPOS_TYP.ST_MAIN_LEFT.db_val()));

		RecList20		rl2_pl_atom			= r2_vektor_pos_left.get(0).get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, new And(new vgl_YN(BEWEGUNG_ATOM.deleted,false)).s(),BEWEGUNG_ATOM.id_bewegung_atom.fieldName());
		Rec20_atom		r2_pl_atom_left		= new Rec20_atom(rl2_pl_atom.get(0));
		Rec20_atom		r2_pl_atom_right	= new Rec20_atom(rl2_pl_atom.get(1));

		RecList20  		r2_vektor_pos_right = r2_vektor_pos.get_filtered_list(rec->rec.get_ufs_dbVal(BEWEGUNG_VEKTOR_POS.pos_typ,"").equals(ENUM_VEKTORPOS_TYP.ST_MAIN_RIGHT.db_val()));

		RecList20		rl2_pr_atom 		= r2_vektor_pos_right.get(0).get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, new And(new vgl_YN(BEWEGUNG_ATOM.deleted,false)).s(),BEWEGUNG_ATOM.id_bewegung_atom.fieldName());
		Rec20_atom		r2_pr_atom_left		= new Rec20_atom(rl2_pr_atom.get(0));
		Rec20_atom		r2_pr_atom_right	= new Rec20_atom(rl2_pr_atom.get(1));

		// pruefung:
		if (!(r2_vektor_pos_left.size()==1 && r2_vektor_pos_right.size()==1)) {
			throw new myException("ST_DO__Collector: Uncorrect structure at opening ST-Vektor");
		}

		this.registerComponent(this.key, 													new ST_DO_Bewegung(r2_bewegung, this.key.get_mask_status()));

		this.registerComponent(this.key.k_vektor(), 										new ST_DO_Vektor(r2_vektor, 	this.key.get_mask_status()));


		this.registerComponent(this.key.k_vektor_pos_left(),								new ST_DO_VektorPos(r2_vektor_pos_left.get(0), 			this.key.get_mask_status()));

		this.registerComponent(this.key.k_vektor_pos_left__atom_left(), 					new ST_DO_Atom(		r2_pl_atom_left, 					this.key.get_mask_status()));
		this.registerComponent(this.key.k_vektor_pos_left__atom_left__station_start(), 	new ST_DO_Station(	r2_pl_atom_left.__station_start(), 	this.key.get_mask_status()));
		this.registerComponent(this.key.k_vektor_pos_left__atom_left__station_ziel(), 	new ST_DO_Station(	r2_pl_atom_left.__station_ziel(), 	this.key.get_mask_status()));

		this.registerComponent(this.key.k_vektor_pos_left__atom_right(), 					new ST_DO_Atom(		r2_pl_atom_right,						this.key.get_mask_status()));
		this.registerComponent(this.key.k_vektor_pos_left__atom_right__station_start(), 	new ST_DO_Station(	r2_pl_atom_right.__station_start(),	this.key.get_mask_status()));
		this.registerComponent(this.key.k_vektor_pos_left__atom_right__station_ziel(), 	new ST_DO_Station(	r2_pl_atom_right.__station_ziel(), 	this.key.get_mask_status()));


		this.registerComponent(this.key.k_vektor_pos_right(), 							new ST_DO_VektorPos(r2_vektor_pos_right.get(0), 		this.key.get_mask_status()));

		this.registerComponent(this.key.k_vektor_pos_right__atom_left(), 					new ST_DO_Atom(r2_pr_atom_left, 						this.key.get_mask_status()));
		this.registerComponent(this.key.k_vektor_pos_right__atom_left__station_start(), 	new ST_DO_Station(r2_pr_atom_left.__station_start(), 	this.key.get_mask_status()));
		this.registerComponent(this.key.k_vektor_pos_right__atom_left__station_ziel(), 	new ST_DO_Station(r2_pr_atom_left.__station_ziel(), 	this.key.get_mask_status()));

		this.registerComponent(this.key.k_vektor_pos_right__atom_right(), 				new ST_DO_Atom(r2_pr_atom_right,						this.key.get_mask_status()));
		this.registerComponent(this.key.k_vektor_pos_right__atom_right__station_start(), 	new ST_DO_Station(r2_pr_atom_right.__station_start(), 	this.key.get_mask_status()));
		this.registerComponent(this.key.k_vektor_pos_right__atom_right__station_ziel(), 	new ST_DO_Station(r2_pr_atom_right.__station_ziel(), 	this.key.get_mask_status()));

	}

//	@Override
//	public void database_to_dataobject(Object id_bewegung_atom) throws myException {
//	}



	// zusaetzliche getter um einfacheren zugriff auf die komponenten zu haben
	public __ST_MASTER_KEY k_bewegung() {
		return this.key;
	}


	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException { 
		ST_DO__Collector v_neu = new ST_DO__Collector(this.key);
		v_neu.rb_hm_DataObjects().putAll(this.rb_hm_DataObjects());
		return v_neu;
	}

	@Override
	public void manipulate_filled_records_before_save_individual(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException {
		
	}



	@Override
	public IF_MasterKey get_master_key() {
		return this.key;
	}

	@Override
	public void execute_final_statements_in_open_transaction_individual(RB_DataobjectsCollector_V2 do_collector, 	MyE2_MessageVector mv) throws myException {
		
	}
}
