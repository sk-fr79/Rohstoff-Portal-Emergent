package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.CM_Bewegung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_SONDERLAGER_ENUM;


public class ST_CM_Bewegung_MASK_SET_AND_VALID extends RB_Mask_Set_And_Valid {

	private __ST_MASTER_KEY masterKey = null;

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
		//zuerst den ST_CM__Collector beschaffen

		MyE2_MessageVector mv = new MyE2_MessageVector();

		CM_Bewegung  cm_bewegung = (CM_Bewegung) rbMASK;

		ST_CM__Collector cm_collector = (ST_CM__Collector)cm_bewegung.rb_get_belongs_to();

		this.masterKey = cm_collector.get_master_key();

		if (ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {

			//die id_artikel reinschreiben
			//links
			MyLong l_artikel_bez_start  = new MyLong(cm_collector.get_startAtom().getRbComponentSavable(BEWEGUNG_ATOM.id_artikel_bez).rb_readValue_4_dataobject());
			if (l_artikel_bez_start.get_bOK()) {
				RECORD_ARTIKEL_BEZ  rab = new RECORD_ARTIKEL_BEZ(l_artikel_bez_start.get_lValue());
				cm_collector.get_startAtom().getRbComponentSavable(BEWEGUNG_ATOM.id_artikel).rb_set_db_value_manual(rab.ufs(ARTIKEL_BEZ.id_artikel));
			}

			//links
			MyLong l_artikel_bez_ziel  = new MyLong(cm_collector.get_zielAtom().getRbComponentSavable(BEWEGUNG_ATOM.id_artikel_bez).rb_readValue_4_dataobject());
			if (l_artikel_bez_ziel.get_bOK()) {
				RECORD_ARTIKEL_BEZ  rab = new RECORD_ARTIKEL_BEZ(l_artikel_bez_ziel.get_lValue());
				cm_collector.get_zielAtom().getRbComponentSavable(BEWEGUNG_ATOM.id_artikel).rb_set_db_value_manual(rab.ufs(ARTIKEL_BEZ.id_artikel));
			}

			new _ST_Controller_Menge(cm_collector).fill_menge(mv);

		}

		if(ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION){
			if(this.masterKey.get_mask_status()==MASK_STATUS.NEW){

				String virtual_strecke_id_we 	= 	FZ_SONDERLAGER_ENUM.ST_ZWE_ZWISCHENLAGER.get_zwischenlager_id();

				String virtual_strecke_id_wa 	= 	FZ_SONDERLAGER_ENUM.ST_ZWA_ZWISCHENLAGER.get_zwischenlager_id();

				String sonderlager_id = 		FZ_SONDERLAGER_ENUM.ST_SONDERLAGER.get_zwischenlager_id();

				rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left__station_ziel(), 	BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(virtual_strecke_id_we);

				rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_right__station_start(), 	BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(virtual_strecke_id_we);

				rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_right__station_ziel(), 	BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(sonderlager_id);

				rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_left__station_start(), 	BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(sonderlager_id);

				rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_left__station_ziel(), 	BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(virtual_strecke_id_wa);

				rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_right__station_start(), 	BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(virtual_strecke_id_wa);
				
				
				rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left__station_ziel(), 	BEWEGUNG_STATION.id_adresse.fk()).rb_set_db_value_manual(virtual_strecke_id_we);

				rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_right__station_start(), 	BEWEGUNG_STATION.id_adresse.fk()).rb_set_db_value_manual(virtual_strecke_id_we);

				rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_right__station_ziel(), 	BEWEGUNG_STATION.id_adresse.fk()).rb_set_db_value_manual(sonderlager_id);

				rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_left__station_start(), 	BEWEGUNG_STATION.id_adresse.fk()).rb_set_db_value_manual(sonderlager_id);

				rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_left__station_ziel(), 	BEWEGUNG_STATION.id_adresse.fk()).rb_set_db_value_manual(virtual_strecke_id_wa);

				rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_right__station_start(), 	BEWEGUNG_STATION.id_adresse.fk()).rb_set_db_value_manual(virtual_strecke_id_wa);
				
				/*
				RB_ComponentMap key_start_atom = cm_collector.get(this.masterKey.k_vektor_pos_left__atom_left__station_start());

				key_start_atom._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left__station_start(), BEWEGUNG_STATION.id_adresse_besitzer.fk()).rb_set_db_value_manual(bibALL.get_ID_ADRESS_MANDANT());

				key_start_atom._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left__station_start(), BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(bibALL.get_ID_ADRESS_MANDANT());
				 */
			}
		}

		if(ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION){

			//			String menge_left 			= new MyBigDecimal(rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left(),	BEWEGUNG_ATOM.menge.fk()).get__actual_maskstring_in_view()).get_UnFormatedRoundedNumber(0);
			//			String menge_right			= new MyBigDecimal(rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_right(),	BEWEGUNG_ATOM.menge.fk()).get__actual_maskstring_in_view()).get_UnFormatedRoundedNumber(0);

			String id_artikel_left		= rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left(), 	BEWEGUNG_ATOM.id_artikel.fk()).get__actual_maskstring_in_view();
			String id_artikel_bez_left	= rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left(),		BEWEGUNG_ATOM.id_artikel_bez.fk()).get__actual_maskstring_in_view();

			String id_artikel_right		= rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_right(),  	BEWEGUNG_ATOM.id_artikel.fk()).get__actual_maskstring_in_view();
			String id_artikel_bez_right	= rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_right(),	BEWEGUNG_ATOM.id_artikel_bez.fk()).get__actual_maskstring_in_view();


			String phys_id_adresse_start= 	rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left__station_start(), 	BEWEGUNG_STATION.id_adresse.fk()).get__actual_maskstring_in_view();
			String phys_id_adresse_ziel	= 	rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_right__station_ziel(), 	BEWEGUNG_STATION.id_adresse.fk()).get__actual_maskstring_in_view();

			id_artikel_left 			= 	bibALL.convertID2UnformattedID(id_artikel_left);
			id_artikel_bez_left 		= 	bibALL.convertID2UnformattedID(id_artikel_bez_left);
			id_artikel_right 			= 	bibALL.convertID2UnformattedID(id_artikel_right);
			id_artikel_bez_right 		= 	bibALL.convertID2UnformattedID(id_artikel_bez_right);
			phys_id_adresse_start		= 	bibALL.convertID2UnformattedID(phys_id_adresse_start);
			phys_id_adresse_ziel		= 	bibALL.convertID2UnformattedID(phys_id_adresse_ziel);


			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left(), 	BEWEGUNG_ATOM.id_artikel.fk()			).rb_set_db_value_manual(id_artikel_left);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left(), 	BEWEGUNG_ATOM.id_artikel_bez.fk()		).rb_set_db_value_manual(id_artikel_bez_left);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left(), 	BEWEGUNG_ATOM.id_adresse_wb_start.fk()	).rb_set_db_value_manual(phys_id_adresse_start);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left(), 	BEWEGUNG_ATOM.id_adresse_wb_ziel.fk()	).rb_set_db_value_manual(phys_id_adresse_ziel);

			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_right(), 	BEWEGUNG_ATOM.id_artikel.fk()			).rb_set_db_value_manual(id_artikel_left);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_right(), 	BEWEGUNG_ATOM.id_artikel_bez.fk()		).rb_set_db_value_manual(id_artikel_bez_left);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_right(), 	BEWEGUNG_ATOM.id_adresse_wb_start.fk()	).rb_set_db_value_manual(phys_id_adresse_start);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_right(), 	BEWEGUNG_ATOM.id_adresse_wb_ziel.fk()	).rb_set_db_value_manual(phys_id_adresse_ziel);

			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_left(), 	BEWEGUNG_ATOM.id_artikel.fk()			).rb_set_db_value_manual(id_artikel_right);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_left(), 	BEWEGUNG_ATOM.id_artikel_bez.fk()		).rb_set_db_value_manual(id_artikel_bez_right);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_left(), 	BEWEGUNG_ATOM.id_adresse_wb_start.fk()	).rb_set_db_value_manual(phys_id_adresse_start);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_left(), 	BEWEGUNG_ATOM.id_adresse_wb_ziel.fk()	).rb_set_db_value_manual(phys_id_adresse_ziel);

			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_right(), BEWEGUNG_ATOM.id_artikel.fk()			).rb_set_db_value_manual(id_artikel_right);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_right(), BEWEGUNG_ATOM.id_artikel_bez.fk()		).rb_set_db_value_manual(id_artikel_bez_right);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_right(), BEWEGUNG_ATOM.id_adresse_wb_start.fk()	).rb_set_db_value_manual(phys_id_adresse_start);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_right(), BEWEGUNG_ATOM.id_adresse_wb_ziel.fk()	).rb_set_db_value_manual(phys_id_adresse_ziel);


			String mandant_id = 			bibALL.get_ID_ADRESS_MANDANT();

			String virtual_strecke_id_we 	= 	FZ_SONDERLAGER_ENUM.ST_ZWE_ZWISCHENLAGER.get_zwischenlager_id();
			String virtual_strecke_id_wa 	= 	FZ_SONDERLAGER_ENUM.ST_ZWA_ZWISCHENLAGER.get_zwischenlager_id();
			String sonderlager_id 			= 	FZ_SONDERLAGER_ENUM.ST_SONDERLAGER.get_zwischenlager_id();

			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left__station_ziel(), 	BEWEGUNG_STATION.id_adresse.fk()).rb_set_db_value_manual(virtual_strecke_id_we);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_right__station_start(), 	BEWEGUNG_STATION.id_adresse.fk()).rb_set_db_value_manual(virtual_strecke_id_we);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_right__station_ziel(), 	BEWEGUNG_STATION.id_adresse.fk()).rb_set_db_value_manual(sonderlager_id);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_left__station_start(), 	BEWEGUNG_STATION.id_adresse.fk()).rb_set_db_value_manual(sonderlager_id);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_left__station_ziel(), 	BEWEGUNG_STATION.id_adresse.fk()).rb_set_db_value_manual(virtual_strecke_id_wa);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_right__station_start(), 	BEWEGUNG_STATION.id_adresse.fk()).rb_set_db_value_manual(virtual_strecke_id_wa);


			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_left__station_ziel(), 	BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(virtual_strecke_id_we);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_right__station_start(), 	BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(virtual_strecke_id_we);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_left__atom_right__station_ziel(), 	BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(sonderlager_id);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_left__station_start(), 	BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(sonderlager_id);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_left__station_ziel(), 	BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(virtual_strecke_id_wa);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_vektor_pos_right__atom_right__station_start(), 	BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(virtual_strecke_id_wa);

		}
		return mv;
	}
}
