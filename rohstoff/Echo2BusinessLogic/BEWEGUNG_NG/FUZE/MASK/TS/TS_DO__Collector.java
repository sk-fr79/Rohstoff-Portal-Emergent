package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.TS;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.DATA.RB_DataobjectsCollector_V2;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
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

public class TS_DO__Collector extends FZ_dataObjectsCollector {

	private __TS_MASTER_KEY key = null;

	public TS_DO__Collector(__TS_MASTER_KEY p_key) throws myException {
		super();
		this.key = p_key;

		this.registerComponent(this.key, 									new TS_DO_Bewegung());
		this.registerComponent(this.key.k_vektor(), 						new TS_DO_Vektor());

		this.registerComponent(this.key.k_vektor_pos(), 					new TS_DO_VektorPos());

		this.registerComponent(this.key.k_atom_left(), 					new TS_DO_Atom());
		this.registerComponent(this.key.k_atom_left__station_start(), 	new TS_DO_Station());
		this.registerComponent(this.key.k_atom_left__station_ziel(), 		new TS_DO_Station());

		this.registerComponent(this.key.k_atom_right(), 					new TS_DO_Atom());
		this.registerComponent(this.key.k_atom_right__station_start(), 	new TS_DO_Station());
		this.registerComponent(this.key.k_atom_right__station_ziel(), 	new TS_DO_Station());
	}

	public TS_DO__Collector(String id_bewegung_vektor, IF_MasterKey p_key) throws myException {
		super();

		this.key = (__TS_MASTER_KEY)p_key;

		//vorbereitung 
		this.get_keys().clear();

		//fixpunkte
		Rec20   rv = new Rec20(_TAB.bewegung_vektor)._fill_id(id_bewegung_vektor); 
		Rec20	rb = rv.get_up_Rec20(BEWEGUNG.id_bewegung);

		RecList20  rlp = rv.get_down_reclist20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, new vgl(BEWEGUNG_VEKTOR_POS.pos_typ, ENUM_VEKTORPOS_TYP.TS_MAIN.db_val()).s(), null);

		RecList20  rla = rlp.get(0).get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, new And(new vgl_YN(BEWEGUNG_ATOM.deleted,false)).s(),BEWEGUNG_ATOM.id_bewegung_atom.fieldName());

		Rec20_atom  record_atom_START = new Rec20_atom(rla.get(0));
		Rec20_atom  record_atom_ZIEL = new Rec20_atom(rla.get(1));

		if (rlp.size()>1) {
			throw new myException(this, "!!Error! Multiple Vektor-POS of type >TS_MAIN forbidden !!!");
		} else if (rlp.size()==0) {
			throw new myException(this, "!!Error! no Vektor-POS of type TS_MAIN found !!!");
		}

		this.registerComponent(this.key, 									new TS_DO_Bewegung(rb,			this.key.get_mask_status()));
		this.registerComponent(this.key.k_vektor(),  						new TS_DO_Vektor(rv, 			this.key.get_mask_status()));
		this.registerComponent(this.key.k_vektor_pos(),  					new TS_DO_VektorPos(rlp.get(0), this.key.get_mask_status()));

		this.registerComponent(this.key.k_atom_left(),  					new TS_DO_Atom(rla.get(0), 								this.key.get_mask_status()));
		this.registerComponent(this.key.k_atom_left__station_start(), 	new TS_DO_Station(record_atom_START.__station_start(),	this.key.get_mask_status()));
		this.registerComponent(this.key.k_atom_left__station_ziel(), 		new TS_DO_Station(record_atom_START.__station_ziel(),	this.key.get_mask_status()));

		this.registerComponent(this.key.k_atom_right(),  					new TS_DO_Atom(rla.get(1), 								this.key.get_mask_status()));
		this.registerComponent(this.key.k_atom_right__station_start(), 	new TS_DO_Station(record_atom_ZIEL.__station_start(),	this.key.get_mask_status()));
		this.registerComponent(this.key.k_atom_right__station_ziel(), 	new TS_DO_Station(record_atom_ZIEL.__station_ziel(),	this.key.get_mask_status()));
	}

	@Override
	public void manipulate_filled_records_before_save_individual(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv)  	throws myException {
	}

	@Override
	public void execute_final_statements_in_open_transaction_individual(RB_DataobjectsCollector_V2 do_collector, MyE2_MessageVector mv) throws myException {
	}

	
	@Override
	public RB_DataobjectsCollector_V2 get_copy() throws myException {return null;}


	@Override
	public IF_MasterKey get_master_key() {
		return this.key;
	}


}
