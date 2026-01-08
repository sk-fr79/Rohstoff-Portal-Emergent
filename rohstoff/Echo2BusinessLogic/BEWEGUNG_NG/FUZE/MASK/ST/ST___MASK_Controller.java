package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.ST;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerField;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerFiller;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldSaveable;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_CODE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_KeyChain;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_STATION;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL._LL_CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_STATION_TYP;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_adresse;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class ST___MASK_Controller extends RB_MaskControllerMap {

	private __ST_MASTER_KEY masterKey = null;

	public ST___MASK_Controller(RB_ComponentMapCollector componentMapCollector) throws myException {
		super(componentMapCollector);
		this.masterKey=((ST_CM__Collector)componentMapCollector).get_master_key();

	}

	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();

		try {
			RB_MaskControllerField  callingField = this.get_MaskControllerField(compCalling);

			if (callingField != null) {
				if (callingField.get_maskKey() instanceof IF_KeyChain) {
					this.masterKey = (__ST_MASTER_KEY)((IF_KeyChain)callingField.get_maskKey()).get_root_key();
				}
			}

			if (this.masterKey==null) {
				mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Maskencontroller! Es konnte kein Masterkey gefunden werden !!")));
				return mv;
			}

			//hier beginnt der eigenliche code
			//feststellen, welcher mask-key und welcher field-key an das rufende objekt gebunden ist
			RB_KM mKey=callingField.get_maskKey();
			RB_KF fKey=callingField.get_fieldKey();


			if (mv.get_bIsOK()) {

				//hier die entscheider
				if (mKey.equals(this.masterKey.k_vektor_pos_left__atom_left()) && fKey.equals(BEWEGUNG_ATOM.leistungsdatum.fk())) {
					this.fill_after_ladedatum(mv, true);
				}

				if (mKey.equals(this.masterKey.k_vektor_pos_right__atom_right()) && fKey.equals(BEWEGUNG_ATOM.leistungsdatum.fk())) {
					this.fill_after_ladedatum(mv, false);
				}


				//hier die Startlager, schreibt den standard-startbesitzer
				if (mKey.equals(this.masterKey.k_vektor_pos_left__atom_left__station_start()) && fKey.equals(BEWEGUNG_STATION.id_adresse.fk())) {
					this.fill_adresse(mv,mKey);
				}
				if (mKey.equals(this.masterKey.k_vektor_pos_right__atom_right__station_ziel()) && fKey.equals(BEWEGUNG_STATION.id_adresse.fk())) {
					this.fill_adresse(mv,mKey);
				}

				if (fKey.equals(BEWEGUNG_ATOM.id_artikel_bez.fk())) {
					this.fill_sorte(mv, mKey);
				}




			}
		} catch (Exception e) {
			e.printStackTrace();
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("Unknown Error:"+e.getMessage())));
		}

		return mv;
	}



	//felder der ladestation gegebenenfalls fuellen, sorten und kontrakte loeschen
	private void fill_after_ladedatum(MyE2_MessageVector mv, boolean true_if_left) throws myException {
		if(true_if_left){
			String quelle_leistungdatum = this.get_maskVal(this.masterKey.k_vektor_pos_left__atom_left(), BEWEGUNG_ATOM.leistungsdatum);
			this.set_maskVal(this.masterKey.k_vektor_pos_left__atom_right(), BEWEGUNG_ATOM.leistungsdatum, quelle_leistungdatum, mv);
		}else{
			String ziel_leistungdatum 	= this.get_maskVal(this.masterKey.k_vektor_pos_right__atom_right(), BEWEGUNG_ATOM.leistungsdatum);
			this.set_maskVal(this.masterKey.k_vektor_pos_right__atom_left(), BEWEGUNG_ATOM.leistungsdatum, ziel_leistungdatum, mv);
		}
	}




	//felder des eigenen lagers gegebenenfalls fuellen
	private void fill_adresse(MyE2_MessageVector mv, RB_KM keyStation) throws myException {

		MyLong id_station_start = this.get_MyLong_maskVal(keyStation, BEWEGUNG_STATION.id_adresse);

		if (id_station_start!=null && id_station_start.isOK()) {
			Rec20 ra = new Rec20(_TAB.adresse)._fill_id(id_station_start.get_lValue());

			RECORD_ADRESSE_extend rec_a = new RECORD_ADRESSE_extend(id_station_start.get_lValue());
			String laendercode = (rec_a.get_UP_RECORD_LAND_id_land()!=null?rec_a.get_UP_RECORD_LAND_id_land().fs(LAND.laendercode,""):"");
			RECORD_ADRESSE_extend ra_main = rec_a.get_main_Adress();
			this.set_maskVal(keyStation, BEWEGUNG_STATION.id_adresse_basis, 	ra_main.fs(ADRESSE.id_adresse), mv);
			this.set_maskVal(keyStation, BEWEGUNG_STATION.id_adresse_besitzer, 	ra_main.fs(ADRESSE.id_adresse), mv);
			this.set_maskVal(keyStation, BEWEGUNG_STATION.telefon, 				ra_main.get_StandardTelefonNumber(), mv);
			this.set_maskVal(keyStation, BEWEGUNG_STATION.fax, 					ra_main.get_StandardTelefonNumber(), mv);
			this.set_maskVal(keyStation, BEWEGUNG_STATION.oeffnungszeiten, 		ra_main.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0).fs(FIRMENINFO.oeffnungszeiten), mv);
			this.set_maskVal(keyStation, BEWEGUNG_STATION.laendercode, 			laendercode, mv);
		}else{

			this.set_maskVal(keyStation, BEWEGUNG_STATION.id_adresse_basis, 	"", mv);
			this.set_maskVal(keyStation, BEWEGUNG_STATION.id_adresse_besitzer, 	"", mv);
			this.set_maskVal(keyStation, BEWEGUNG_STATION.telefon, 				"", mv);
			this.set_maskVal(keyStation, BEWEGUNG_STATION.fax, 					"", mv);
			this.set_maskVal(keyStation, BEWEGUNG_STATION.oeffnungszeiten, 		"", mv);
			this.set_maskVal(keyStation, BEWEGUNG_STATION.laendercode,			"", mv);
		}
	}



	//felder des eigenen lagers gegebenenfalls fuellen
	private void fill_sorte(MyE2_MessageVector mv, RB_KM keyAtom) throws myException {
		MyLong id_artikel_bez = this.get_MyLong_maskVal(keyAtom, BEWEGUNG_ATOM.id_artikel_bez);
		MyLong id_adresse = this.get_MyLong_maskVal(masterKey.k_vektor_pos_left__atom_left__station_start(), BEWEGUNG_STATION.id_adresse);

		//wenn es die linke seite ist, dann noch nach einem AVV-code schauen
		if (id_artikel_bez!=null && id_artikel_bez.isOK() && keyAtom.equals(this.masterKey.k_vektor_pos_left__atom_left())) {
			Rec20 rab = new Rec20(_TAB.artikel_bez)._fill_id(id_artikel_bez.get_lValue());
			new RB_MaskControllerFiller(keyAtom, this, rab).fill_fields(BEWEGUNG_ATOM.artbez1, BEWEGUNG_ATOM.artbez2);

			if (id_adresse!=null && id_adresse.isOK()) {
				RECORD_ADRESSE_extend rae = new RECORD_ADRESSE_extend(id_adresse.get_lValue());
				RECORD_ARTIKEL_BEZ    recab = new RECORD_ARTIKEL_BEZ(rab.gen_record(true));

				RECORD_EAK_CODE r_eak = rae.get_AVV_Code(recab);

				if (r_eak!=null) {
					this.set_maskVal(masterKey.k_vektor(), BEWEGUNG_VEKTOR.id_eak_code, r_eak.ufs(EAK_CODE.id_eak_code), mv);
				}
			}



			this.set_maskVal(keyAtom, BEWEGUNG_ATOM.id_artikel_bez, id_artikel_bez.get_cF_LongString(), mv);
		}


		//		MyLong id_artikel_bez = this.get_MyLong_maskVal(this.masterKey.k_atom_left(), BEWEGUNG_ATOM.id_artikel_bez);
		//		
	}

	public __ST_MASTER_KEY get_masterKey() {
		return this.masterKey;
	}

	/*public void _fill_artikel_feldern(VEKTOR_POS_TYP pos_typ, MyE2_MessageVector mv) throws myException {
		KEY_ATOM atom_left 	= null;
		KEY_ATOM atom_right = null;
		KEY_ATOM atom_with_id_artbez = null;

		RB_KF einheit_key = null;

		if(pos_typ == VEKTOR_POS_TYP.ST_MAIN_LEFT){
			atom_with_id_artbez = this.masterKey.k_vektor_pos_left__atom_left();

			atom_left = this.masterKey.k_vektor_pos_left__atom_left();
			atom_right = this.masterKey.k_vektor_pos_left__atom_right();
			einheit_key = FZ__CONST.f_keys.EINHEIT_LEFT.k();

		}else{
			atom_with_id_artbez =  this.masterKey.k_vektor_pos_right__atom_right();

			atom_left = this.masterKey.k_vektor_pos_right__atom_left();
			atom_right = this.masterKey.k_vektor_pos_right__atom_right();
			einheit_key = FZ__CONST.f_keys.EINHEIT_RIGHT.k();
		}

		MyLong l_id_artikel_bez = this.get_MyLong_maskVal(atom_with_id_artbez, BEWEGUNG_ATOM.id_artikel_bez);

		if(l_id_artikel_bez!=null && l_id_artikel_bez.get_bOK()){
			Rec20_artikel recArtikel = new Rec20_artikel(new Rec20(_TAB.artikel_bez)._fill_id(l_id_artikel_bez.get_cUF_LongString()));

			if(pos_typ == VEKTOR_POS_TYP.ST_MAIN_LEFT){
				this.set_maskVal(atom_right, BEWEGUNG_ATOM.id_artikel_bez,	recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez), mv);
				this.set_maskVal(atom_left, einheit_key,					l_id_artikel_bez.get_cUF_LongString()				, mv);
			}else{
				this.set_maskVal(atom_left, BEWEGUNG_ATOM.id_artikel_bez,	recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez), mv);
				this.set_maskVal(atom_right,einheit_key,					l_id_artikel_bez.get_cUF_LongString()				, mv);

			}

			this.set_maskVal(atom_left, BEWEGUNG_ATOM.id_artikel,		recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel)	, mv);
			this.set_maskVal(atom_left, BEWEGUNG_ATOM.artbez1, 			recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.artbez1)		, mv);
			this.set_maskVal(atom_left, BEWEGUNG_ATOM.artbez2, 			recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.artbez2)		, mv);

			this.set_maskVal(atom_right, BEWEGUNG_ATOM.id_artikel,		recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel)	, mv);
			this.set_maskVal(atom_right, BEWEGUNG_ATOM.artbez1, 		recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.artbez1)		, mv);
			this.set_maskVal(atom_right, BEWEGUNG_ATOM.artbez2, 		recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.artbez2)		, mv);

		}
	}*/

	public void _fill_kontrakte_abhaengig_feldern(MyE2_MessageVector mv, ST_CO_search_kontrakt_angebot parent, String unformated_MaskValue) throws myException{

		RB_KM kAtom = (KEY_ATOM)parent.rb_ComponentMap_this_belongsTo().getOwnMaskKey();

		RB_KF kKombi = FZ__CONST.f_keys.KOMBI_ANG_KON_LEFT.k();


		ST_CO_search_kontrakt_angebot search_kombi = (ST_CO_search_kontrakt_angebot)this.get_comp(kAtom, kKombi, mv);

		//hier ist entweder ein leer-eintrag oder ein wert mit TAG im Feld (KON@1222212)

		//sonderfall, hier kommt ein wert wie z.B. KON@1222212 in das textFeld
		String actual_mask_val = unformated_MaskValue;

		if (S.isEmpty(actual_mask_val) || actual_mask_val.startsWith("ANG@") || actual_mask_val.startsWith("KON@")) {

			if (S.isEmpty(actual_mask_val)) {
				search_kombi.get_tf_search_input().setText("");
			} else {
				search_kombi.get_tf_search_input().setText(actual_mask_val.substring(4));
			}


			RB_SearchFieldSaveable  	sf_artbez = 		(RB_SearchFieldSaveable)this.get_comp(kAtom,BEWEGUNG_ATOM.id_artikel_bez,mv);

			MyLong  old_artbez = new MyLong("0");
			MyLong  old_artikel = new MyLong("0");
			if (sf_artbez.is_search_done_correct() && S.isFull(sf_artbez.rb_readValue_4_dataobject())) {
				old_artbez = new MyLong(sf_artbez.rb_readValue_4_dataobject());
				if (old_artbez.get_bOK()) {
					RECORD_ARTIKEL rab = new RECORD_ARTIKEL_BEZ(old_artbez.get_lValue()).get_UP_RECORD_ARTIKEL_id_artikel();
					old_artikel  = new MyLong(rab.l(ARTIKEL.id_artikel).longValue());
				}
			}

			//jetzt den string auseinandernehmen
			String s_value_id_vpos_kon = "";
			String s_value_id_vpos_std  = "";

			if(S.isFull(actual_mask_val)) {
				if (actual_mask_val.startsWith("KON@")) {
					s_value_id_vpos_kon = actual_mask_val.substring(4);
					RECORD_VPOS_KON  kon = new RECORD_VPOS_KON(s_value_id_vpos_kon);

					if (kon.l(VPOS_KON.id_artikel, -1L).longValue()!=old_artikel.get_lValue(-2L)) {
						sf_artbez.rb_set_db_value_manual(kon.ufs(VPOS_KON.id_artikel_bez), false,false);
					}
					((IF_RB_Component)	this.get_comp(kAtom,BEWEGUNG_ATOM.e_preis,mv)).rb_set_db_value_manual(kon.fs(VPOS_KON.einzelpreis,""));
					((MyE2IF__Component)this.get_comp(kAtom,BEWEGUNG_ATOM.e_preis,mv)).set_bEnabled_For_Edit(false);
					((MyE2IF__Component)this.get_comp(kAtom,BEWEGUNG_ATOM.manuell_preis,mv)).set_bEnabled_For_Edit(true);
				} else if (actual_mask_val.startsWith("ANG@")) {
					s_value_id_vpos_std = actual_mask_val.substring(4);
					RECORD_VPOS_STD  std = new RECORD_VPOS_STD(s_value_id_vpos_std);

					if (std.l(VPOS_STD.id_artikel, -1L).longValue()!=old_artikel.get_lValue(-2L)) {
						sf_artbez.rb_set_db_value_manual(std.ufs(VPOS_STD.id_artikel_bez), false,false);
					}
					((IF_RB_Component)	this.get_comp(kAtom,BEWEGUNG_ATOM.e_preis,mv)).rb_set_db_value_manual(std.fs(VPOS_STD.einzelpreis,""));
					((MyE2IF__Component)this.get_comp(kAtom,BEWEGUNG_ATOM.e_preis,mv)).set_bEnabled_For_Edit(false);
					((MyE2IF__Component)this.get_comp(kAtom,BEWEGUNG_ATOM.manuell_preis,mv)).set_bEnabled_For_Edit(true);
				}
			} else {
				((MyE2IF__Component)this.get_comp(kAtom,BEWEGUNG_ATOM.e_preis,mv)).set_bEnabled_For_Edit(true);
				((MyE2IF__Component)this.get_comp(kAtom,BEWEGUNG_ATOM.manuell_preis,mv)).set_bEnabled_For_Edit(false);
			}

			search_kombi.get_grid4Label_kontrakt_oder_angebot().removeAll();
			search_kombi.get_grid4Label_kontrakt_oder_angebot().add(search_kombi.get_actual_label(), new RB_gld()._ins(E2_INSETS.I(0,0,0,0)));
		} else {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("WE___MASK_Controller.fill_kontrakt_kombi_followers() only allowd witt empty value or value starts with on of (ANG@ / KON@)")));
		}
	}

	public void _fill_lade_datum(MyE2_MessageVector mv) throws myException {
		KEY_ATOM atom_quelle_key 	= this.masterKey.k_vektor_pos_left__atom_left();

		KEY_VEKT vekt_key 			= this.masterKey.k_vektor();

		String leistung_datum_start = this.get_maskVal(atom_quelle_key, BEWEGUNG_ATOM.leistungsdatum);

		this.set_maskVal(this.masterKey.k_vektor_pos_left__atom_right(), BEWEGUNG_ATOM.leistungsdatum, leistung_datum_start, mv);
		this.set_maskVal(vekt_key, BEWEGUNG_VEKTOR.l_datum_von, leistung_datum_start, mv);
		this.set_maskVal(vekt_key, BEWEGUNG_VEKTOR.l_datum_bis, leistung_datum_start, mv);


		if(this.get_maskVal(this.masterKey.k_vektor_pos_right__atom_left(), BEWEGUNG_ATOM.leistungsdatum).equals(leistung_datum_start)){
			this.set_maskVal(this.masterKey.k_vektor_pos_right__atom_left(), BEWEGUNG_ATOM.leistungsdatum, leistung_datum_start, mv);
		}
		if(this.get_maskVal(this.masterKey.k_vektor_pos_right__atom_right(), BEWEGUNG_ATOM.leistungsdatum).equals(leistung_datum_start)){
			this.set_maskVal(this.masterKey.k_vektor_pos_right__atom_right(), BEWEGUNG_ATOM.leistungsdatum, leistung_datum_start, mv);
		}
		if(this.get_maskVal(vekt_key, BEWEGUNG_VEKTOR.a_datum_von).equals(leistung_datum_start)){
			this.set_maskVal(vekt_key, BEWEGUNG_VEKTOR.a_datum_von, leistung_datum_start, mv);
		}
		if(this.get_maskVal(vekt_key, BEWEGUNG_VEKTOR.a_datum_bis).equals(leistung_datum_start)){
			this.set_maskVal(vekt_key, BEWEGUNG_VEKTOR.a_datum_bis, leistung_datum_start, mv);
		}
	}

	public void _fill_ablade_datum(MyE2_MessageVector mv) throws myException {
		KEY_VEKT vekt_key 			= this.masterKey.k_vektor();

		KEY_ATOM atom_ziel_key 		= this.masterKey.k_vektor_pos_right__atom_right();

		String leistung_datum_ziel = this.get_maskVal(atom_ziel_key, BEWEGUNG_ATOM.leistungsdatum);

		this.set_maskVal(this.masterKey.k_vektor_pos_right__atom_left(), BEWEGUNG_ATOM.leistungsdatum, leistung_datum_ziel, mv);

		this.set_maskVal(vekt_key, BEWEGUNG_VEKTOR.a_datum_von, leistung_datum_ziel, mv);
		this.set_maskVal(vekt_key, BEWEGUNG_VEKTOR.a_datum_bis, leistung_datum_ziel, mv);
	}

	public void fill_adresse_feldern(ENUM_STATION_TYP typ_station, MyE2_MessageVector mv) throws myException{

		MyLong l_id_adresse 	= null;


		KEY_ATOM key_atom_left 	= null;
		KEY_ATOM key_atom_right = null;
		
		KEY_STATION key_station = null;
		
		RB_KF kontrakt_feld = null;
		
		if(typ_station == ENUM_STATION_TYP.START_STATION){	
			l_id_adresse = this.get_MyLong_liveVal(this.masterKey.k_vektor_pos_left__atom_left__station_start(), BEWEGUNG_STATION.id_adresse);
			key_station 	= this.masterKey.k_vektor_pos_left__atom_left__station_start();
			key_atom_left 	= this.masterKey.k_vektor_pos_left__atom_left();
			key_atom_right 	= this.masterKey.k_vektor_pos_left__atom_right();
			kontrakt_feld 	= FZ__CONST.f_keys.KOMBI_ANG_KON_LEFT.k();
			
		}else{
			l_id_adresse = this.get_MyLong_liveVal(this.masterKey.k_vektor_pos_right__atom_right__station_ziel(), BEWEGUNG_STATION.id_adresse);
			key_station 	= this.masterKey.k_vektor_pos_right__atom_right__station_ziel();
			key_atom_left 	= this.masterKey.k_vektor_pos_right__atom_left();
			key_atom_right 	= this.masterKey.k_vektor_pos_right__atom_right();
			kontrakt_feld 	= FZ__CONST.f_keys.KOMBI_ANG_KON_RIGHT.k();
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
			
			this.set_maskVal(key_station, BEWEGUNG_STATION.id_adresse_besitzer, 	id_adresse_basis, 							mv);
			this.set_maskVal(key_station, BEWEGUNG_STATION.id_adresse_basis, 		id_adresse_basis, 							mv);
			this.set_maskVal(key_station, BEWEGUNG_STATION.name1, 					rec_addr.get_ufs_dbVal(ADRESSE.name1), 		mv);
			this.set_maskVal(key_station, BEWEGUNG_STATION.name2, 					rec_addr.get_ufs_dbVal(ADRESSE.name2), 		mv);
			this.set_maskVal(key_station, BEWEGUNG_STATION.name3, 					rec_addr.get_ufs_dbVal(ADRESSE.name3), 		mv);
			this.set_maskVal(key_station, BEWEGUNG_STATION.strasse, 				rec_addr.get_ufs_dbVal(ADRESSE.strasse), 	mv);
			this.set_maskVal(key_station, BEWEGUNG_STATION.hausnummer, 				rec_addr.get_ufs_dbVal(ADRESSE.hausnummer), mv);
			this.set_maskVal(key_station, BEWEGUNG_STATION.laendercode, 			land_kurz, 									mv);
			this.set_maskVal(key_station, BEWEGUNG_STATION.ort, 					rec_addr.get_ufs_dbVal(ADRESSE.ort), 		mv);
			this.set_maskVal(key_station, BEWEGUNG_STATION.plz, 					rec_addr.get_ufs_dbVal(ADRESSE.plz), 		mv);
			this.set_maskVal(key_station, BEWEGUNG_STATION.oeffnungszeiten, 		oeffnungZ, 									mv);
			this.set_maskVal(key_station, BEWEGUNG_STATION.telefon, 				rec_addr.get_StandardTelefonNumber(), 		mv);
			this.set_maskVal(key_station, BEWEGUNG_STATION.fax, 					rec_addr.get_StandardFaxNumber(), 			mv);

		}else{
			this.set_maskVal(key_station, 		BEWEGUNG_STATION.id_adresse_besitzer, 	"", mv);
			this.set_maskVal(key_station, 		BEWEGUNG_STATION.id_adresse_basis, 		"", mv);
			this.set_maskVal(key_station,	 	BEWEGUNG_STATION.name1, 				"", mv);
			this.set_maskVal(key_station, 		BEWEGUNG_STATION.name2, 				"", mv);
			this.set_maskVal(key_station, 		BEWEGUNG_STATION.name3, 				"", mv);
			this.set_maskVal(key_station, 		BEWEGUNG_STATION.strasse, 				"", mv);
			this.set_maskVal(key_station, 		BEWEGUNG_STATION.hausnummer, 			"", mv);
			this.set_maskVal(key_station, 		BEWEGUNG_STATION.laendercode, 			"", mv);
			this.set_maskVal(key_station, 		BEWEGUNG_STATION.ort, 					"", mv);
			this.set_maskVal(key_station, 		BEWEGUNG_STATION.plz, 					"", mv);
			this.set_maskVal(key_station, 		BEWEGUNG_STATION.oeffnungszeiten, 		"", mv);
			this.set_maskVal(key_station, 		BEWEGUNG_STATION.telefon, 				"", mv);
			this.set_maskVal(key_station, 		BEWEGUNG_STATION.fax, 					"", mv);
			
			this.set_maskVal(key_atom_left, 	BEWEGUNG_ATOM.id_artikel_bez,			"", mv);
			this.set_maskVal(key_atom_left, 	BEWEGUNG_ATOM.id_artikel,				"", mv);
			this.set_maskVal(key_atom_left, 	BEWEGUNG_ATOM.artbez1,					"", mv);
			this.set_maskVal(key_atom_left, 	BEWEGUNG_ATOM.artbez2,					"", mv);
			this.set_maskVal(key_atom_left, 	BEWEGUNG_ATOM.id_artikel_bez,			"", mv);
				
			this.set_maskVal(key_atom_right, 	BEWEGUNG_ATOM.id_artikel_bez,			"", mv);
			this.set_maskVal(key_atom_right, 	BEWEGUNG_ATOM.id_artikel,				"", mv);
			this.set_maskVal(key_atom_right, 	BEWEGUNG_ATOM.artbez1,					"", mv);
			this.set_maskVal(key_atom_right, 	BEWEGUNG_ATOM.artbez2,					"", mv);
			this.set_maskVal(key_atom_right, 	BEWEGUNG_ATOM.id_artikel_bez,			"", mv);
			
			this.set_maskVal(key_atom_left, 	kontrakt_feld, 							"", mv);
			this.set_maskVal(key_atom_right, 	kontrakt_feld, 							"", mv);
			
			this.set_maskVal(key_atom_right, 	_LL_CONST._LL_KEYS.ANR12.k(), 			"", mv);
			this.set_maskVal(key_atom_right, 	_LL_CONST._LL_KEYS.ANR12.k(), 			"", mv);
			
			this.set_maskVal(key_atom_left, 	BEWEGUNG_ATOM.e_preis, 					"", mv);
			this.set_maskVal(key_atom_right, 	BEWEGUNG_ATOM.e_preis, 					"", mv);
			
			this.set_maskVal(key_atom_left, 	BEWEGUNG_ATOM.gesamtpreis, 				"", mv);
			this.set_maskVal(key_atom_right, 	BEWEGUNG_ATOM.gesamtpreis, 				"", mv);
			
			this.set_maskVal(key_atom_left, 	BEWEGUNG_ATOM.menge, 					"", mv);
			this.set_maskVal(key_atom_right, 	BEWEGUNG_ATOM.menge, 					"", mv);
			
			this.set_maskVal(key_atom_left, 	BEWEGUNG_ATOM.id_vpos_kon, 				"", mv);
			this.set_maskVal(key_atom_left, 	BEWEGUNG_ATOM.id_vpos_kon, 				"", mv);
			this.set_maskVal(key_atom_right, 	BEWEGUNG_ATOM.id_vpos_kon, 				"", mv);
			this.set_maskVal(key_atom_right, 	BEWEGUNG_ATOM.id_vpos_kon, 				"", mv);
			
			this.set_maskVal(masterKey.k_vektor(), BEWEGUNG_VEKTOR.id_eak_code, 		"", mv);
		}
	}
}
