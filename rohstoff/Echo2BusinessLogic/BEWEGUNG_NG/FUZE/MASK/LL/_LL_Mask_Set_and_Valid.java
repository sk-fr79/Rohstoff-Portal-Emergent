package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyDouble;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.CM_Bewegung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_SONDERLAGER_ENUM;


public class _LL_Mask_Set_and_Valid extends RB_Mask_Set_And_Valid {

	private __LL_MASTER_KEY masterKey;
	
	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
		//zuerst den LL_CM__Collector beschaffen
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		CM_Bewegung  cm_bewegung = (CM_Bewegung) rbMASK;
		
		LL_CM__Collector cm_collector = (LL_CM__Collector)cm_bewegung.rb_get_belongs_to();
		
		this.masterKey = cm_collector.get_master_key();

		
		if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {
			//ablademenge fuellen, wenn noch leer
			String input_startmenge = cm_collector.get_start_atom().getRbComponentSavable(BEWEGUNG_ATOM.menge).rb_readValue_4_dataobject();
			MyDouble input_zielmenge = new MyDouble(cm_collector.get_ziel_atom().getRbComponentSavable(BEWEGUNG_ATOM.menge).rb_readValue_4_dataobject());
			if (!input_zielmenge.get_bOK()) {
				cm_collector.get_ziel_atom().getRbComponentSavable(BEWEGUNG_ATOM.menge).rb_set_db_value_manual(input_startmenge);
				mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Zielmenge wurde automatisch gesetzt")));
			}
			
			
			//die id_artikel reinschreiben
			//links
			MyLong l_artikel_bez_start  = new MyLong(cm_collector.get_start_atom().getRbComponentSavable(BEWEGUNG_ATOM.id_artikel_bez).rb_readValue_4_dataobject());
			if (l_artikel_bez_start.get_bOK()) {
				RECORD_ARTIKEL_BEZ  rab = new RECORD_ARTIKEL_BEZ(l_artikel_bez_start.get_lValue());
				cm_collector.get_start_atom().getRbComponentSavable(BEWEGUNG_ATOM.id_artikel).rb_set_db_value_manual(rab.ufs(ARTIKEL_BEZ.id_artikel));
			}
			
			//links
			MyLong l_artikel_bez_ziel  = new MyLong(cm_collector.get_ziel_atom().getRbComponentSavable(BEWEGUNG_ATOM.id_artikel_bez).rb_readValue_4_dataobject());
			if (l_artikel_bez_ziel.get_bOK()) {
				RECORD_ARTIKEL_BEZ  rab = new RECORD_ARTIKEL_BEZ(l_artikel_bez_ziel.get_lValue());
				cm_collector.get_ziel_atom().getRbComponentSavable(BEWEGUNG_ATOM.id_artikel).rb_set_db_value_manual(rab.ufs(ARTIKEL_BEZ.id_artikel));
			}
			
			new _LL_Controller_Leistungsdatum(cm_collector)	.fill_datum(mv);
			
//			new _LL_Controller_Artikel(cm_collector)		.fill_artikel(mv);
			
			new _LL_Controller_Menge(cm_collector)			.fill_menge(mv);
			
		}
		
		if(ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION){
			if(this.masterKey.get_mask_status()==MASK_STATUS.NEW){
				
				RB_ComponentMap key_start_atom = cm_collector
				.get(this.masterKey.k_atom_left__lager_start());
				
				key_start_atom._find_component_in_neighborhood(this.masterKey.k_atom_left__lager_start(), BEWEGUNG_STATION.id_adresse_besitzer.fk()).rb_set_db_value_manual(bibALL.get_ID_ADRESS_MANDANT());
				
				key_start_atom._find_component_in_neighborhood(this.masterKey.k_atom_right__lager_ziel(), BEWEGUNG_STATION.id_adresse_besitzer.fk()).rb_set_db_value_manual(bibALL.get_ID_ADRESS_MANDANT());

				key_start_atom._find_component_in_neighborhood(this.masterKey.k_atom_left__lager_start(), BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(bibALL.get_ID_ADRESS_MANDANT());

			}
			
		}
		
		if(ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION){

			String phys_id_adresse_start= 	rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_left__lager_start(), 	BEWEGUNG_STATION.id_adresse.fk()).get__actual_maskstring_in_view();
			String phys_id_adresse_ziel	= 	rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_right__lager_ziel(), 	BEWEGUNG_STATION.id_adresse.fk()).get__actual_maskstring_in_view();
			
			String id_adresse_besitzer 	= 	bibALL.get_ID_ADRESS_MANDANT();
			
			//sichereit: IDs unformattierung 
			String virtual_lager_id 	= 	FZ_SONDERLAGER_ENUM.LL_ZWISCHENLAGER.get_zwischenlager_id();
			phys_id_adresse_start		= 	bibALL.convertID2UnformattedID(phys_id_adresse_start);
			phys_id_adresse_ziel		= 	bibALL.convertID2UnformattedID(phys_id_adresse_ziel);
		
			
			//atom links
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_left(), 					BEWEGUNG_ATOM.id_adresse_wb_start.fk()			).rb_set_db_value_manual(phys_id_adresse_start);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_left(), 					BEWEGUNG_ATOM.id_adresse_wb_ziel.fk()			).rb_set_db_value_manual(phys_id_adresse_ziel);


			//atom recht
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_right(),					BEWEGUNG_ATOM.id_adresse_wb_start.fk()			).rb_set_db_value_manual(phys_id_adresse_start);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_right(),					BEWEGUNG_ATOM.id_adresse_wb_ziel.fk()			).rb_set_db_value_manual(phys_id_adresse_ziel);


			//stations links (start station ... virtuel lager)
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_left__lager_ziel(),		BEWEGUNG_STATION.id_adresse_basis.fk()			).rb_set_db_value_manual(virtual_lager_id);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_left__lager_ziel(),		BEWEGUNG_STATION.id_adresse.fk()				).rb_set_db_value_manual(virtual_lager_id);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_left__lager_start(), 		BEWEGUNG_STATION.id_adresse_besitzer.fk()		).rb_set_db_value_manual(id_adresse_besitzer);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_left__lager_ziel(),		BEWEGUNG_STATION.id_adresse_besitzer.fk()		).rb_set_db_value_manual(id_adresse_besitzer);
		
			//station recht(virtuel lager ... ziel lager)
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_right__lager_start(),		BEWEGUNG_STATION.id_adresse_basis.fk()			).rb_set_db_value_manual(virtual_lager_id);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_right__lager_start(),		BEWEGUNG_STATION.id_adresse.fk()				).rb_set_db_value_manual(virtual_lager_id);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_right__lager_start(), 		BEWEGUNG_STATION.id_adresse_besitzer.fk()		).rb_set_db_value_manual(id_adresse_besitzer);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_right__lager_ziel(),		BEWEGUNG_STATION.id_adresse_besitzer.fk()		).rb_set_db_value_manual(id_adresse_besitzer);
		}
		
		return mv;	}

}
