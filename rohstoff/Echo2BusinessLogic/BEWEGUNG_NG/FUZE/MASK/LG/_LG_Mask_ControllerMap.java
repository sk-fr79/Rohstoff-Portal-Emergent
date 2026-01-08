package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LG;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.MANDANT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_STATION;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL._LL_CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_STATION_TYP;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;

public class _LG_Mask_ControllerMap extends RB_MaskControllerMap {

	private __LG_MASTER_KEY masterKey;

	public _LG_Mask_ControllerMap(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
		this.masterKey=((LG_CM__Collector)p_componentMapCollector).get_master_key();
	}

	public _LG_Mask_ControllerMap(IF_RB_Component p_component) throws myException {
		super(p_component);
		this.masterKey=((LG_CM__Collector)p_component._find_componentMapCollector_i_belong_to()).get_master_key();
	}

	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)
			throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		return mv;
	}

	public void _fill_adresse_feldern(ENUM_STATION_TYP typ_station, MyE2_MessageVector mv) throws myException{

		MyLong l_id_adresse = null;

		if(typ_station == ENUM_STATION_TYP.START_STATION){
			l_id_adresse = this.get_MyLong_liveVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.id_adresse);
		}else{
			l_id_adresse = this.get_MyLong_liveVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.id_adresse);
		}

		if(l_id_adresse!= null && l_id_adresse.isOK()){

			Rec20_adresse rec_addr = new Rec20_adresse(new Rec20(_TAB.adresse)._fill_id(l_id_adresse.get_lValue()));

			String land_kurz = rec_addr.get_up_Rec20(ADRESSE.id_land,LAND.id_land, false).get_ufs_dbVal(LAND.laendercode);

			Rec20 liefer_rec = new Rec20(_TAB.lieferadresse);
			Rec20 firmenInfo = new Rec20(_TAB.firmeninfo);

			String id_adresse_basis ;
			
			String id_adresse_besitzer = "";//this.get_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.id_adresse_besitzer);
					
			if (rec_addr.__is_liefer_adresse()) {
				liefer_rec 			= rec_addr.get_down_reclist20(LIEFERADRESSE.id_adresse_liefer, "", "").get(0);
				id_adresse_basis 	= rec_addr.__get_main_adresse().get_fs_dbVal(ADRESSE.id_adresse);
				
			} else {
				firmenInfo 			= rec_addr.get_down_reclist20(FIRMENINFO.id_adresse,"", "").get(0);
				id_adresse_basis 	= firmenInfo.get_ufs_dbVal(FIRMENINFO.id_adresse);
			}

			String oeffnungZ = liefer_rec == null ? firmenInfo.get_fs_dbVal(FIRMENINFO.oeffnungszeiten,""):liefer_rec.get_fs_dbVal(LIEFERADRESSE.oeffnungszeiten,"");

			KEY_STATION key = null;

			if(typ_station == ENUM_STATION_TYP.START_STATION){
				key = this.masterKey.k_atom_left__station_start();
			/*	this.set_maskVal(this.masterKey.k_atom_left__station_ziel(), BEWEGUNG_STATION.id_adresse_besitzer, 		id_adresse_besitzer, mv);
				this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.id_adresse_besitzer,		id_adresse_besitzer, mv);
				this.set_maskVal(this.masterKey.k_atom_right__station_start(), BEWEGUNG_STATION.id_adresse_besitzer, 	id_adresse_besitzer, mv);*/
			}else{
				key = this.masterKey.k_atom_right__station_ziel();
			}
			
			this.set_maskVal(key, BEWEGUNG_STATION.id_adresse_basis, 		id_adresse_basis, 							mv);
			this.set_maskVal(key, BEWEGUNG_STATION.name1, 					rec_addr.get_ufs_dbVal(ADRESSE.name1), 		mv);
			this.set_maskVal(key, BEWEGUNG_STATION.name2, 					rec_addr.get_ufs_dbVal(ADRESSE.name2), 		mv);
			this.set_maskVal(key, BEWEGUNG_STATION.name3, 					rec_addr.get_ufs_dbVal(ADRESSE.name3), 		mv);
			this.set_maskVal(key, BEWEGUNG_STATION.strasse, 				rec_addr.get_ufs_dbVal(ADRESSE.strasse), 	mv);
			this.set_maskVal(key, BEWEGUNG_STATION.hausnummer, 				rec_addr.get_ufs_dbVal(ADRESSE.hausnummer), mv);
			this.set_maskVal(key, BEWEGUNG_STATION.laendercode, 			land_kurz, 									mv);
			this.set_maskVal(key, BEWEGUNG_STATION.ort, 					rec_addr.get_ufs_dbVal(ADRESSE.ort), 		mv);
			this.set_maskVal(key, BEWEGUNG_STATION.oeffnungszeiten, 		oeffnungZ, 									mv);
			this.set_maskVal(key, BEWEGUNG_STATION.telefon, 				rec_addr.get_StandardTelefonNumber(), 		mv);
			this.set_maskVal(key, BEWEGUNG_STATION.fax, 					rec_addr.get_StandardFaxNumber(), 			mv);

		}else{
			KEY_STATION key = null;
			
			if(typ_station == ENUM_STATION_TYP.START_STATION){
				key = this.masterKey.k_atom_left__station_start();
			}else{
				key = this.masterKey.k_atom_right__station_ziel();	
			}
			
			this.set_maskVal(key, BEWEGUNG_STATION.id_adresse_besitzer, 	bibSES.RECORD_MANDANT().ufs(MANDANT.eigene_adress_id), mv);
			
			this.set_maskVal(key, 						BEWEGUNG_STATION.id_adresse_basis, 	"", mv);
			this.set_maskVal(key,					 	BEWEGUNG_STATION.name1, 			"", mv);
			this.set_maskVal(key, 						BEWEGUNG_STATION.name2, 			"", mv);
			this.set_maskVal(key, 						BEWEGUNG_STATION.name3, 			"", mv);
			this.set_maskVal(key, 						BEWEGUNG_STATION.strasse, 			"", mv);
			this.set_maskVal(key, 						BEWEGUNG_STATION.hausnummer, 		"", mv);
			this.set_maskVal(key, 						BEWEGUNG_STATION.laendercode, 		"", mv);
			this.set_maskVal(key, 						BEWEGUNG_STATION.ort, 				"", mv);
			this.set_maskVal(key, 						BEWEGUNG_STATION.plz, 				"", mv);
			this.set_maskVal(key, 						BEWEGUNG_STATION.oeffnungszeiten,	"", mv);
			this.set_maskVal(key, 						BEWEGUNG_STATION.telefon, 			"", mv);
			this.set_maskVal(key, 						BEWEGUNG_STATION.fax, 				"", mv);
			
			this.set_maskVal(masterKey.k_atom_left(), 	BEWEGUNG_ATOM.id_artikel_bez,		"", mv);
			this.set_maskVal(masterKey.k_atom_left(), 	BEWEGUNG_ATOM.id_artikel,			"", mv);
			this.set_maskVal(masterKey.k_atom_left(), 	BEWEGUNG_ATOM.artbez1,				"", mv);
			this.set_maskVal(masterKey.k_atom_left(), 	BEWEGUNG_ATOM.artbez2,				"", mv);
			this.set_maskVal(masterKey.k_atom_left(), 	BEWEGUNG_ATOM.id_artikel_bez,		"", mv);
			
			this.set_maskVal(masterKey.k_atom_right(), 	BEWEGUNG_ATOM.id_artikel_bez,		"", mv);
			this.set_maskVal(masterKey.k_atom_right(), 	BEWEGUNG_ATOM.id_artikel,			"", mv);
			this.set_maskVal(masterKey.k_atom_right(), 	BEWEGUNG_ATOM.artbez1,				"", mv);
			this.set_maskVal(masterKey.k_atom_right(), 	BEWEGUNG_ATOM.artbez2,				"", mv);
			this.set_maskVal(masterKey.k_atom_right(), 	BEWEGUNG_ATOM.id_artikel_bez,		"", mv);
			
			this.set_maskVal(masterKey.k_atom_left(), 	_LL_CONST._LL_KEYS.ANR12.k(), 		"", mv);
			this.set_maskVal(masterKey.k_atom_right(), 	_LL_CONST._LL_KEYS.ANR12.k(), 		"", mv);
			
			this.set_maskVal(masterKey.k_vektor(), 		BEWEGUNG_VEKTOR.id_eak_code, 		"", mv);
			this.set_maskVal(key, BEWEGUNG_STATION.id_adresse_basis, 		"", mv);
			this.set_maskVal(key, BEWEGUNG_STATION.name1, 					"",	mv);
			this.set_maskVal(key, BEWEGUNG_STATION.name2, 					"", mv);
			this.set_maskVal(key, BEWEGUNG_STATION.name3, 					"", mv);
			this.set_maskVal(key, BEWEGUNG_STATION.strasse, 				"", mv);
			this.set_maskVal(key, BEWEGUNG_STATION.hausnummer, 				"", mv);
			this.set_maskVal(key, BEWEGUNG_STATION.laendercode, 			"", mv);
			this.set_maskVal(key, BEWEGUNG_STATION.ort, 					"", mv);
			this.set_maskVal(key, BEWEGUNG_STATION.oeffnungszeiten, 		"", mv);
			this.set_maskVal(key, BEWEGUNG_STATION.telefon, 				"", mv);
			this.set_maskVal(key, BEWEGUNG_STATION.fax, 					"", mv);
		}
	}

	public void _fill_besitzer_feldern(MyE2_MessageVector mv) throws myException{
		MyLong l_id_adresse_besitzer = this.get_MyLong_liveVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.id_adresse_besitzer);

		if(l_id_adresse_besitzer!= null && l_id_adresse_besitzer.isOK()){
			Rec20_adresse rec_addr = new Rec20_adresse(new Rec20(_TAB.adresse)._fill_id(l_id_adresse_besitzer.get_lValue()));

			String id_adresse_besitzer__only_hauptadresse ="";
			if(rec_addr.__is_liefer_adresse()){
				id_adresse_besitzer__only_hauptadresse =rec_addr.__get_main_adresse().get_fs_dbVal(ADRESSE.id_adresse);
			}else{
				id_adresse_besitzer__only_hauptadresse = l_id_adresse_besitzer.get_cUF_LongString();
			}

			this.set_maskVal(this.masterKey.k_atom_left__station_start(),	BEWEGUNG_STATION.id_adresse_besitzer, 	id_adresse_besitzer__only_hauptadresse, mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_ziel(),	BEWEGUNG_STATION.id_adresse_besitzer, 	id_adresse_besitzer__only_hauptadresse, mv);
			this.set_maskVal(this.masterKey.k_atom_right__station_start(), 	BEWEGUNG_STATION.id_adresse_besitzer, 	id_adresse_besitzer__only_hauptadresse, mv);
			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), 	BEWEGUNG_STATION.id_adresse_besitzer, 	id_adresse_besitzer__only_hauptadresse, mv);

		}
	}
}
