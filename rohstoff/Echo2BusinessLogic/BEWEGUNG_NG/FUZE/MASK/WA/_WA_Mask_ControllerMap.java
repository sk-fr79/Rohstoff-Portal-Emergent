package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA;

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
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_STATION;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL._LL_CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class _WA_Mask_ControllerMap extends RB_MaskControllerMap {

	private __WA_MASTER_KEY masterKey;

	public _WA_Mask_ControllerMap(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
		this.masterKey = ((WA_CM__Collector)p_componentMapCollector).get_master_key();
	}

	public _WA_Mask_ControllerMap(IF_RB_Component p_component) throws myException {
		super(p_component);
		this.masterKey=((WA_CM__Collector)p_component._find_componentMapCollector_i_belong_to()).get_master_key();	
	}

	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)
			throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		return mv;
	}


	public void _fill_lager_feldern(MyE2_MessageVector mv) throws myException{

		MyLong l_id_adresse = this.get_MyLong_liveVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.id_adresse);

		KEY_STATION key_left_start = 	this.masterKey.k_atom_left__station_start();
		KEY_STATION key_left_ziel = 	this.masterKey.k_atom_left__station_ziel();

		if (l_id_adresse!= null && l_id_adresse.get_bOK()) {

			RECORD_ADRESSE_extend  recFound = new RECORD_ADRESSE_extend(l_id_adresse.get_lValue());
			RECORD_ADRESSE_extend  recMain = recFound.get_main_Adress();
			RECORD_LIEFERADRESSE  liefer = null;
			RECORD_FIRMENINFO     rec_fi = null;

			if (recFound.is_station_adress()) {
				liefer = recFound.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0);
			} else {
				rec_fi = recFound.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
			}

			String laenderCode = (recFound.get_UP_RECORD_LAND_id_land()!=null)?recFound.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""):"";

			String oeffnungzeit = (liefer==null)?rec_fi.fs(FIRMENINFO.oeffnungszeiten,""):liefer.fs(LIEFERADRESSE.oeffnungszeiten,"");

			String c_tel_std = recFound.get_StandardTelefonNumber();
			if (S.isEmpty(c_tel_std)) {
				c_tel_std = recMain.get_StandardTelefonNumber();
			}

			String c_fax_std = recFound.get_StandardFaxNumber();
			if (S.isEmpty(c_fax_std)) {
				c_fax_std = recMain.get_StandardFaxNumber();
			}

			//			IF_generate_RB_KF K = (field) -> {return new RB_KF(field);};
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.id_adresse_besitzer,	recMain.ufs(ADRESSE.id_adresse,""),	mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.id_adresse, 			recFound.ufs(ADRESSE.id_adresse,""),mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.laendercode, 			laenderCode, 						mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.name1, 				recFound.fs(ADRESSE.name1,""), 		mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.name2, 				recFound.fs(ADRESSE.name2,""), 		mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.name3, 				recFound.fs(ADRESSE.name3,""), 		mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.strasse, 				recFound.fs(ADRESSE.strasse,""), 	mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.hausnummer, 			recFound.fs(ADRESSE.hausnummer,""), mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.plz, 					recFound.fs(ADRESSE.plz,""), 		mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.ort, 					recFound.fs(ADRESSE.ort,""), 		mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.ortzusatz, 			recFound.fs(ADRESSE.ortzusatz,""), 	mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.oeffnungszeiten,		oeffnungzeit, 						mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.plz, 					recFound.fs(ADRESSE.plz,""), 		mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.telefon, 				S.NN(c_tel_std), 					mv);
			this.set_maskVal(key_left_start, BEWEGUNG_STATION.fax, 					S.NN(c_fax_std), 					mv);

			this.set_maskVal(key_left_ziel, BEWEGUNG_STATION.id_adresse_besitzer,	recMain.ufs(ADRESSE.id_adresse,""),	mv);

		} else {
			this.set_maskVal(key_left_start, 			BEWEGUNG_STATION.id_adresse_basis, 	"", mv);
			this.set_maskVal(key_left_start,			BEWEGUNG_STATION.name1, 			"", mv);
			this.set_maskVal(key_left_start, 			BEWEGUNG_STATION.name2, 			"", mv);
			this.set_maskVal(key_left_start, 			BEWEGUNG_STATION.name3, 			"", mv);
			this.set_maskVal(key_left_start, 			BEWEGUNG_STATION.strasse, 			"", mv);
			this.set_maskVal(key_left_start, 			BEWEGUNG_STATION.hausnummer, 		"", mv);
			this.set_maskVal(key_left_start, 			BEWEGUNG_STATION.laendercode, 		"", mv);
			this.set_maskVal(key_left_start, 			BEWEGUNG_STATION.ort, 				"", mv);
			this.set_maskVal(key_left_start, 			BEWEGUNG_STATION.plz, 				"", mv);
			this.set_maskVal(key_left_start, 			BEWEGUNG_STATION.oeffnungszeiten,	"", mv);
			this.set_maskVal(key_left_start, 			BEWEGUNG_STATION.telefon, 			"", mv);
			this.set_maskVal(key_left_start, 			BEWEGUNG_STATION.fax, 				"", mv);
			
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
		}
	}

	public void _fill_adresse_feldern(MyE2_MessageVector mv) throws myException{

		MyLong l_id_adresse = null;

		l_id_adresse = this.get_MyLong_liveVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.id_adresse);

		if(l_id_adresse!= null && l_id_adresse.isOK()){

			Rec20_adresse rec_addr = new Rec20_adresse(new Rec20(_TAB.adresse)._fill_id(l_id_adresse.get_lValue()));

			String land_kurz = rec_addr.get_up_Rec20(ADRESSE.id_land,LAND.id_land, false).get_ufs_dbVal(LAND.laendercode);

			Rec20 liefer_rec = new Rec20(_TAB.lieferadresse);
			Rec20 firmenInfo = new Rec20(_TAB.firmeninfo);

			String id_adresse_basis ;

			if (rec_addr.__is_liefer_adresse()) {
				liefer_rec 			= rec_addr.get_down_reclist20(LIEFERADRESSE.id_adresse_liefer, "", "").get(0);
				id_adresse_basis 	= rec_addr.__get_main_adresse().get_fs_dbVal(ADRESSE.id_adresse);
			} else {
				firmenInfo 			= rec_addr.get_down_reclist20(FIRMENINFO.id_adresse,"", "").get(0);
				id_adresse_basis 	= firmenInfo.get_ufs_dbVal(FIRMENINFO.id_adresse);
			}

			String oeffnungZ = liefer_rec == null ? firmenInfo.get_fs_dbVal(FIRMENINFO.oeffnungszeiten,""):liefer_rec.get_fs_dbVal(LIEFERADRESSE.oeffnungszeiten,"");

			KEY_STATION key_right_ziel = this.masterKey.k_atom_right__station_ziel();

			this.set_maskVal(key_right_ziel, BEWEGUNG_STATION.id_adresse_besitzer, 	id_adresse_basis, 							mv);
			this.set_maskVal(key_right_ziel, BEWEGUNG_STATION.id_adresse_basis, 	id_adresse_basis, 							mv);
			this.set_maskVal(key_right_ziel, BEWEGUNG_STATION.name1, 				rec_addr.get_ufs_dbVal(ADRESSE.name1), 		mv);
			this.set_maskVal(key_right_ziel, BEWEGUNG_STATION.name2, 				rec_addr.get_ufs_dbVal(ADRESSE.name2), 		mv);
			this.set_maskVal(key_right_ziel, BEWEGUNG_STATION.name3, 				rec_addr.get_ufs_dbVal(ADRESSE.name3), 		mv);
			this.set_maskVal(key_right_ziel, BEWEGUNG_STATION.strasse, 				rec_addr.get_ufs_dbVal(ADRESSE.strasse), 	mv);
			this.set_maskVal(key_right_ziel, BEWEGUNG_STATION.hausnummer, 			rec_addr.get_ufs_dbVal(ADRESSE.hausnummer), mv);
			this.set_maskVal(key_right_ziel, BEWEGUNG_STATION.laendercode, 			land_kurz, 									mv);
			this.set_maskVal(key_right_ziel, BEWEGUNG_STATION.ort, 					rec_addr.get_ufs_dbVal(ADRESSE.ort), 		mv);
			this.set_maskVal(key_right_ziel, BEWEGUNG_STATION.oeffnungszeiten, 		oeffnungZ, 									mv);
			this.set_maskVal(key_right_ziel, BEWEGUNG_STATION.telefon, 				rec_addr.get_StandardTelefonNumber(), 		mv);
			this.set_maskVal(key_right_ziel, BEWEGUNG_STATION.fax, 					rec_addr.get_StandardFaxNumber(), 			mv);

			KEY_STATION key_right_start = this.masterKey.k_atom_right__station_start();

			this.set_maskVal(key_right_start, BEWEGUNG_STATION.id_adresse_besitzer, id_adresse_basis, 							mv);

		}else{
			KEY_STATION key_right_ziel = this.masterKey.k_atom_right__station_ziel();
			KEY_STATION key_right_start = this.masterKey.k_atom_right__station_start();
			
			this.set_maskVal(key_right_ziel, 			BEWEGUNG_STATION.id_adresse_basis, 	"", mv);
			this.set_maskVal(key_right_ziel,			BEWEGUNG_STATION.name1, 			"", mv);
			this.set_maskVal(key_right_ziel, 			BEWEGUNG_STATION.name2, 			"", mv);
			this.set_maskVal(key_right_ziel, 			BEWEGUNG_STATION.name3, 			"", mv);
			this.set_maskVal(key_right_ziel, 			BEWEGUNG_STATION.strasse, 			"", mv);
			this.set_maskVal(key_right_ziel, 			BEWEGUNG_STATION.hausnummer, 		"", mv);
			this.set_maskVal(key_right_ziel, 			BEWEGUNG_STATION.laendercode, 		"", mv);
			this.set_maskVal(key_right_ziel, 			BEWEGUNG_STATION.ort, 				"", mv);
			this.set_maskVal(key_right_ziel, 			BEWEGUNG_STATION.plz, 				"", mv);
			this.set_maskVal(key_right_ziel, 			BEWEGUNG_STATION.oeffnungszeiten,	"", mv);
			this.set_maskVal(key_right_ziel, 			BEWEGUNG_STATION.telefon, 			"", mv);
			this.set_maskVal(key_right_ziel, 			BEWEGUNG_STATION.fax, 				"", mv);
			
			this.set_maskVal(masterKey.k_atom_left(), 	BEWEGUNG_ATOM.id_artikel_bez,		"", mv);
			this.set_maskVal(masterKey.k_atom_left(), 	BEWEGUNG_ATOM.id_artikel,			"", mv);
			this.set_maskVal(masterKey.k_atom_left(), 	BEWEGUNG_ATOM.artbez1,				"", mv);
			this.set_maskVal(masterKey.k_atom_left(), 	BEWEGUNG_ATOM.artbez2,				"", mv);
			this.set_maskVal(masterKey.k_atom_left(), 	BEWEGUNG_ATOM.id_artikel_bez,		"", mv);

			this.set_maskVal(masterKey.k_atom_right(), FZ__CONST.f_keys.KOMBI_ANG_KON_RIGHT.k(), "", mv);
			this.set_maskVal(masterKey.k_atom_right(), BEWEGUNG_ATOM.id_vpos_kon, 			"", mv);
			this.set_maskVal(masterKey.k_atom_right(), BEWEGUNG_ATOM.id_vpos_std, 			"", mv);
			this.set_maskVal(masterKey.k_atom_right(), BEWEGUNG_ATOM.e_preis, 				"", mv);
			
			this.set_maskVal(masterKey.k_atom_right(), 	BEWEGUNG_ATOM.id_artikel_bez,		"", mv);
			this.set_maskVal(masterKey.k_atom_right(), 	BEWEGUNG_ATOM.id_artikel,			"", mv);
			this.set_maskVal(masterKey.k_atom_right(), 	BEWEGUNG_ATOM.artbez1,				"", mv);
			this.set_maskVal(masterKey.k_atom_right(), 	BEWEGUNG_ATOM.artbez2,				"", mv);
			this.set_maskVal(masterKey.k_atom_right(), 	BEWEGUNG_ATOM.id_artikel_bez,		"", mv);
			
			this.set_maskVal(masterKey.k_atom_left(), 	_LL_CONST._LL_KEYS.ANR12.k(), 		"", mv);
			this.set_maskVal(masterKey.k_atom_right(), 	_LL_CONST._LL_KEYS.ANR12.k(), 		"", mv);
			
			this.get_comp(masterKey.k_atom_left(),	 	BEWEGUNG_ATOM.e_preis, 					mv).set_bEnabled_For_Edit(true);
			
			this.set_maskVal(masterKey.k_vektor(), 		BEWEGUNG_VEKTOR.id_eak_code, 		"", mv);
			this.set_maskVal(key_right_start, BEWEGUNG_STATION.id_adresse_besitzer, "", 		mv);
		}
	}
}
