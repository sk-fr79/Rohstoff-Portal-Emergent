package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchField;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_STATION;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_STATION_TYP;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_artikel;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class _LL_Mask_ControllerMap extends RB_MaskControllerMap {

	private __LL_MASTER_KEY masterKey = null;

	public _LL_Mask_ControllerMap(RB_ComponentMapCollector componentMapCollector) throws myException {
		super(componentMapCollector);
		this.masterKey=((LL_CM__Collector)componentMapCollector).get_master_key();
	}

	/**
	 * @param p_component
	 * @throws myException
	 */
	public _LL_Mask_ControllerMap(IF_RB_Component p_component) throws myException {
		super(p_component);
		this.masterKey=((LL_CM__Collector)p_component._find_componentMapCollector_i_belong_to()).get_master_key();
	}

	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		return mv;
	}


	public void fill_adresse_feldern(ENUM_STATION_TYP typ_station, MyE2_MessageVector mv) throws myException{

		MyLong l_id_adresse = null;

		KEY_STATION key = null;

		
		if(typ_station == ENUM_STATION_TYP.START_STATION){	
			l_id_adresse = this.get_MyLong_liveVal(this.masterKey.k_atom_left__lager_start(), BEWEGUNG_STATION.id_adresse);
			key = this.masterKey.k_atom_left__lager_start();
		}else{
			l_id_adresse = this.get_MyLong_liveVal(this.masterKey.k_atom_right__lager_ziel(), BEWEGUNG_STATION.id_adresse);
			key = this.masterKey.k_atom_right__lager_ziel();
		}

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

			this.set_maskVal(key, BEWEGUNG_STATION.id_adresse_basis, 		id_adresse_basis							, mv);
			this.set_maskVal(key, BEWEGUNG_STATION.name1, 					rec_addr.get_ufs_dbVal(ADRESSE.name1), 		mv);
			this.set_maskVal(key, BEWEGUNG_STATION.name2, 					rec_addr.get_ufs_dbVal(ADRESSE.name2), 		mv);
			this.set_maskVal(key, BEWEGUNG_STATION.name3, 					rec_addr.get_ufs_dbVal(ADRESSE.name3), 		mv);
			this.set_maskVal(key, BEWEGUNG_STATION.strasse, 				rec_addr.get_ufs_dbVal(ADRESSE.strasse), 	mv);
			this.set_maskVal(key, BEWEGUNG_STATION.hausnummer, 				rec_addr.get_ufs_dbVal(ADRESSE.hausnummer), mv);
			this.set_maskVal(key, BEWEGUNG_STATION.laendercode, 			land_kurz, 									mv);
			this.set_maskVal(key, BEWEGUNG_STATION.plz, 					rec_addr.get_ufs_dbVal(ADRESSE.plz), 		mv);
			this.set_maskVal(key, BEWEGUNG_STATION.ort, 					rec_addr.get_ufs_dbVal(ADRESSE.ort), 		mv);
			this.set_maskVal(key, BEWEGUNG_STATION.oeffnungszeiten, 		oeffnungZ, 									mv);
			this.set_maskVal(key, BEWEGUNG_STATION.telefon, 				rec_addr.get_StandardTelefonNumber(), 		mv);
			this.set_maskVal(key, BEWEGUNG_STATION.fax, 					rec_addr.get_StandardFaxNumber(), 			mv);

		}else{
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
		}
	}

	//felder des eigenen lagers gegebenenfalls fuellen
	public void fill_adresse_start_followers(MyE2_MessageVector mv) throws myException {

	}

	//felder des eigenen lagers gegebenenfalls fuellen
	public void fill_adresse_ziel_followers(MyE2_MessageVector mv) throws myException {
		MyLong id_adresse_ziel = this.get_MyLong_liveVal(this.masterKey.k_atom_right__lager_ziel(), BEWEGUNG_STATION.id_adresse);

		if (id_adresse_ziel!=null && id_adresse_ziel.isOK()) {
			RECORD_ADRESSE_extend adr = 		new RECORD_ADRESSE_extend(id_adresse_ziel.get_lValue());
			RECORD_ADRESSE_extend adr_base = 	adr.base_Adress();
			this.set_maskVal(this.masterKey.k_atom_right__lager_ziel(),BEWEGUNG_STATION.id_adresse_basis, adr_base.ufs(ADRESSE.id_adresse), mv);

			((RB_TextField)this.get_comp(this.masterKey.k_atom_right__lager_ziel(),BEWEGUNG_STATION.id_adresse_besitzer, mv)).rb_set_db_value_manual(adr_base.ufs(ADRESSE.id_adresse));
			mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Der Besitzer (Abladeseite) wurde besetzt:").ut(adr_base.get__FullNameAndAdress_Typ1())));
		} else {
			KEY_STATION key = this.masterKey.k_atom_right__lager_ziel();
			
			this.set_maskVal(key, BEWEGUNG_STATION.id_adresse_basis, 		"", mv);
			this.set_maskVal(key, BEWEGUNG_STATION.name1, 					"", mv);
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

	public void _fill_artikel_feldern(MyE2_MessageVector mv) throws myException{
		MyLong l_id_artBez = this.get_MyLong_liveVal(this.masterKey.k_atom_left(), BEWEGUNG_ATOM.id_artikel_bez);

		if(l_id_artBez.get_bOK()){
			Rec20_artikel recArtikel = new Rec20_artikel(new Rec20(_TAB.artikel_bez)._fill_id(l_id_artBez.get_lValue()));

			this.set_maskVal(this.masterKey.k_atom_left(), BEWEGUNG_ATOM.id_artikel,		recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel), mv);
			this.set_maskVal(this.masterKey.k_atom_left(), BEWEGUNG_ATOM.artbez1, 			recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.artbez1)	, mv);
			this.set_maskVal(this.masterKey.k_atom_left(), BEWEGUNG_ATOM.artbez2, 			recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.artbez2)	, mv);

			this.set_maskVal(this.masterKey.k_atom_right(), BEWEGUNG_ATOM.id_artikel_bez,	recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez)	, mv);
			this.set_maskVal(this.masterKey.k_atom_right(), BEWEGUNG_ATOM.id_artikel,		recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel), mv);
			this.set_maskVal(this.masterKey.k_atom_right(), BEWEGUNG_ATOM.artbez1, 			recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.artbez1)	, mv);
			this.set_maskVal(this.masterKey.k_atom_right(), BEWEGUNG_ATOM.artbez2, 			recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.artbez2)	, mv);

			this.set_maskVal(this.masterKey.k_atom_left(), FZ__CONST.f_keys.EINHEIT.k(), 	recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez),mv);

		}

	}

	public __LL_MASTER_KEY get_masterKey() {
		return masterKey;
	}
}
