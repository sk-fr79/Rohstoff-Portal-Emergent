package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.VALID_TYPE;
import panter.gmbh.Echo2.RB.VALID.RB_Mask_Set_And_Valid;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.CM_Bewegung;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_SONDERLAGER_ENUM;

public class _WE_Mask_Set_and_Valid extends RB_Mask_Set_And_Valid {

	private __WE_MASTER_KEY masterKey;

	@Override
	public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
		
		MyE2_MessageVector mv = new MyE2_MessageVector();
		
		CM_Bewegung  cm_bewegung = (CM_Bewegung) rbMASK;
		
		WE_CM__Collector cm_collector = (WE_CM__Collector)cm_bewegung.rb_get_belongs_to();
		
		this.masterKey = cm_collector.get_master_key();
		
		if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {
			
			//die id_artikel reinschreiben
			MyLong l_artikel_bez_start  = new MyLong(cm_collector.get_left_atom().getRbComponentSavable(BEWEGUNG_ATOM.id_artikel_bez).rb_readValue_4_dataobject());
			if (l_artikel_bez_start.get_bOK()) {
				RECORD_ARTIKEL_BEZ  rab = new RECORD_ARTIKEL_BEZ(l_artikel_bez_start.get_lValue());
				cm_collector.get_left_atom().getRbComponentSavable(BEWEGUNG_ATOM.id_artikel).rb_set_db_value_manual(rab.ufs(ARTIKEL_BEZ.id_artikel));
			}
			
			MyLong l_ziel_lager_start = new MyLong(cm_collector.get_zielStation().getRbComponentSavable(BEWEGUNG_STATION.id_adresse).rb_readValue_4_dataobject());
			if(l_ziel_lager_start.get_bOK()){
				RECORD_ADRESSE ra = new RECORD_ADRESSE(l_ziel_lager_start.get_lValue());
				cm_collector.get_zielStation().getRbComponentSavable(BEWEGUNG_STATION.id_adresse).rb_set_db_value_manual(ra.ufs(ADRESSE.id_adresse));
			}
			
//			new _WE_Controller_Leistungsdatum(cm_collector)	.fill_datum(mv);
			
//			new _WE_Controller_Artikel(cm_collector)		.fill_artikel(mv);
			
			new _WE_Controller_Menge(cm_collector)			.fill_menge(mv);
		}
		
		if(ActionType == VALID_TYPE.USE_IN_MASK_LOAD_ACTION){
			if(this.masterKey.get_mask_status()==MASK_STATUS.NEW){
				
				RB_ComponentMap ziel_station = cm_collector
				.get(this.masterKey.k_atom_right__station_ziel());
				
				ziel_station._find_component_in_neighborhood(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.id_adresse_besitzer.fk()).rb_set_db_value_manual(bibALL.get_ID_ADRESS_MANDANT());
				
				ziel_station._find_component_in_neighborhood(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.id_adresse_basis.fk()).rb_set_db_value_manual(bibALL.get_ID_ADRESS_MANDANT());

			}
			
		}
		
		if(ActionType == VALID_TYPE.USE_IN_MASK_VALID_ACTION){

			
			String id_artikel			= 	rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_left(), 					BEWEGUNG_ATOM.id_artikel.fk()).get__actual_maskstring_in_view();
			
			if(S.isEmpty(id_artikel)){
				mv._addAlarm("Bitte führen Sie eine korrekte Sortensuche durch !");
			}

			String id_mandant = bibALL.get_ID_ADRESS_MANDANT();
			

			//sichereit: IDs unformattierung 
			String virtual_lager_id 	= 	FZ_SONDERLAGER_ENUM.WE_ZWISCHENLAGER.get_zwischenlager_id();

			//stations links (start station ... virtuel lager)
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_left__station_ziel(),		BEWEGUNG_STATION.id_adresse.fk()				).rb_set_db_value_manual(virtual_lager_id);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_left__station_ziel(),		BEWEGUNG_STATION.id_adresse_basis.fk()			).rb_set_db_value_manual(virtual_lager_id);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_left__station_ziel(),		BEWEGUNG_STATION.id_adresse_besitzer.fk()		).rb_set_db_value_manual(id_mandant);
		

			
			//stations recht (virtuel lager ... ziel station)
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_right__station_start(),	BEWEGUNG_STATION.id_adresse.fk()				).rb_set_db_value_manual(virtual_lager_id);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_right__station_start(),	BEWEGUNG_STATION.id_adresse_basis.fk()			).rb_set_db_value_manual(virtual_lager_id);
			rbMASK._find_component_in_neighborhood(this.masterKey.k_atom_right__station_start(), 	BEWEGUNG_STATION.id_adresse_besitzer.fk()		).rb_set_db_value_manual(id_mandant);
		}
		
		return mv;
	}
}
