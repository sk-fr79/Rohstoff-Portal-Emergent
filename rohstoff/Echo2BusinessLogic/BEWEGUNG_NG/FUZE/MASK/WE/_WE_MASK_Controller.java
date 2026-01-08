package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_STATION;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.EAK_CODE;
import panter.gmbh.basics4project.DB_ENUMS.EINHEIT;
import panter.gmbh.basics4project.DB_ENUMS.FIRMENINFO;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ADRESSE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_EAK_CODE;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_FIRMENINFO;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LIEFERADRESSE;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class _WE_MASK_Controller extends RB_MaskControllerMap {

	private __WE_MASTER_KEY masterKey = null;
	
	public _WE_MASK_Controller(RB_ComponentMapCollector componentMapCollector) throws myException {
		super(componentMapCollector);
		this.masterKey=((WE_CM__Collector)componentMapCollector).get_master_key();
	}
	
	
	/**
	 * @param p_component
	 * @throws myException
	 */
	public _WE_MASK_Controller(IF_RB_Component p_component) throws myException {
		super(p_component);
		this.masterKey=((WE_CM__Collector)this.get_ComponentMapCollector()).get_master_key();

	}


	public _WE_MASK_Controller _refresh() throws myException {
		super._refresh();
		return this;
	}
	
	
	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall) throws myException {
		MyE2_MessageVector mv = new MyE2_MessageVector();
		return mv;
	}

	
	public RB_KM atom_right() {
		return this.masterKey.k_atom_right();
	}

	
	public RB_KM atom_left() {
		return this.masterKey.k_atom_left();
	}

	
//	//felder der ladestation gegebenenfalls fuellen, sorten und kontrakte loeschen
//	public void fill_lieferant_followers(MyE2_MessageVector mv) throws myException {
//		
//		MyLong id_lieferant = this.get_MyLong_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.id_adresse);
//		
//		if (id_lieferant!=null && id_lieferant.isOK()) {
//			RECORD_ADRESSE_extend  recFound = new RECORD_ADRESSE_extend(id_lieferant.get_lValue());
//			RECORD_ADRESSE_extend  recMain = recFound.get_main_Adress();
//			RECORD_LIEFERADRESSE  liefer = null;
//			RECORD_FIRMENINFO     rec_fi = null;
//			if (recFound.is_station_adress()) {
//				liefer = recFound.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0);
//			} else {
//				rec_fi = recFound.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
//			}
//			
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.laendercode, 		recFound.get_UP_RECORD_LAND_id_land()!=null?recFound.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""):"", mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.laendercode, 		recFound.get_UP_RECORD_LAND_id_land()!=null?recFound.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""):"",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.id_adresse_basis, 	recMain.get_ID_ADRESSE_cUF(),mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(),BEWEGUNG_STATION.id_adresse_besitzer,recMain.get_ID_ADRESSE_cUF() ,mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.name1, recFound.fs(ADRESSE.name1,""),mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.name2, recFound.fs(ADRESSE.name2,""),mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.name3, recFound.fs(ADRESSE.name3,""),mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.strasse, recFound.fs(ADRESSE.strasse,""),mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.hausnummer, recFound.fs(ADRESSE.hausnummer,""),mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.plz, recFound.fs(ADRESSE.plz,""),mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.ort, recFound.fs(ADRESSE.ort,""),mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.ortzusatz, recFound.fs(ADRESSE.ortzusatz,""),mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.oeffnungszeiten, liefer==null?rec_fi.fs(FIRMENINFO.oeffnungszeiten,""):liefer.fs(LIEFERADRESSE.oeffnungszeiten,""),mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.plz, recFound.fs(ADRESSE.plz,""),mv);
//			
//			this.set_maskVal(this.masterKey.k_atom_right__station_start(), BEWEGUNG_STATION.id_adresse_besitzer,	recMain.ufs(ADRESSE.id_adresse,""),	mv);
//			
//			String c_tel_std = recFound.get_StandardTelefonNumber();
//			String c_fax_std = recFound.get_StandardFaxNumber();
//			
//			if (S.isEmpty(c_tel_std)) {
//				c_tel_std = recMain.get_StandardTelefonNumber();
//			}
//			
//			if (S.isEmpty(c_fax_std)) {
//				c_fax_std = recMain.get_StandardFaxNumber();
//			}
//			
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.telefon, S.NN(c_tel_std),mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.fax, S.NN(c_fax_std),mv);
//
//		} else {
//			
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.laendercode, 				"",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.id_adresse_basis, 			"",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.id_adresse_besitzer,			"",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.name1, 						"",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.name2, "",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.name3, "",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.strasse, "",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.hausnummer, "",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.plz, "",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.ort, "",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.ortzusatz, "",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.oeffnungszeiten, "",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.plz, "",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.telefon, "",mv);
//			this.set_maskVal(this.masterKey.k_atom_right__station_ziel(), BEWEGUNG_STATION.fax, "",mv);
//		}
//	}
	
	
	//felder des eigenen lagers gegebenenfalls fuellen
	public void fill_ownStation_followers(MyE2_MessageVector mv) throws myException {
		
		RB_KM mKey = this.masterKey.k_atom_right__station_ziel();
		MyLong id_adresse = this.get_MyLong_maskVal(mKey, BEWEGUNG_STATION.id_adresse);
		
		if (id_adresse!=null && id_adresse.get_bOK()) {
			
			RECORD_ADRESSE_extend  recFound = new RECORD_ADRESSE_extend(id_adresse.get_lValue());
			RECORD_ADRESSE_extend  recMain = recFound.get_main_Adress();
			RECORD_LIEFERADRESSE  liefer = null;
			RECORD_FIRMENINFO     rec_fi = null;
			
			if (recFound.is_station_adress()) {
				liefer = recFound.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0);
			} else {
				rec_fi = recFound.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
			}
				
			this.set_maskVal(mKey, BEWEGUNG_STATION.laendercode, 				recFound.get_UP_RECORD_LAND_id_land()!=null?recFound.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""):"",mv);
//			this.set_maskVal(mKey, BEWEGUNG_STATION.id_adresse_basis, 			recMain.get_ID_ADRESSE_cUF(),mv);

			//das feld besitzer fuellen
			this.set_maskVal(mKey, BEWEGUNG_STATION.id_adresse_besitzer, 		recMain.ufs(ADRESSE.id_adresse),mv);
			
			this.set_maskVal(mKey, BEWEGUNG_STATION.name1, recFound.fs(ADRESSE.name1,""),mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.name2, recFound.fs(ADRESSE.name2,""),mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.name3, recFound.fs(ADRESSE.name3,""),mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.strasse, recFound.fs(ADRESSE.strasse,""),mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.hausnummer, recFound.fs(ADRESSE.hausnummer,""),mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.plz, recFound.fs(ADRESSE.plz,""),mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.ort, recFound.fs(ADRESSE.ort,""),mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.ortzusatz, recFound.fs(ADRESSE.ortzusatz,""),mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.oeffnungszeiten, liefer==null?rec_fi.fs(FIRMENINFO.oeffnungszeiten,""):liefer.fs(LIEFERADRESSE.oeffnungszeiten,""),mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.plz, recFound.fs(ADRESSE.plz,""),mv);
			
			this.set_maskVal(this.masterKey.k_atom_right__station_start(), BEWEGUNG_STATION.id_adresse_besitzer,	recMain.ufs(ADRESSE.id_adresse,""),	mv);
			
			String c_tel_std = recFound.get_StandardTelefonNumber();
			String c_fax_std = recFound.get_StandardFaxNumber();
			
			if (S.isEmpty(c_tel_std)) {
				c_tel_std = recMain.get_StandardTelefonNumber();
			}
			
			if (S.isEmpty(c_fax_std)) {
				c_fax_std = recMain.get_StandardFaxNumber();
			}
			
			this.set_maskVal(mKey, BEWEGUNG_STATION.telefon.fk(), S.NN(c_tel_std),mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.fax.fk(), S.NN(c_fax_std),mv);

		} else {
			this.set_maskVal(mKey, BEWEGUNG_STATION.laendercode, 				"",mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.id_adresse_basis, 			"",mv);
			//das feld besitzer fuellen
//			((WE_CO_search_besitzer)this.get_comp(mKey, BEWEGUNG_STATION.id_adresse_besitzer, mv)).rb_set_db_value_manual("",true,false);
			this.set_maskVal(mKey, BEWEGUNG_STATION.id_adresse_besitzer,"", mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.name1, "",mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.name2, "",mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.name3, "",mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.strasse, "",mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.hausnummer, "",mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.plz, "",mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.ort, "",mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.ortzusatz, "",mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.oeffnungszeiten, "",mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.plz, "",mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.telefon, "",mv);
			this.set_maskVal(mKey, BEWEGUNG_STATION.fax, "",mv);
		}
	}
	
	
	//felder des eigenen lagers gegebenenfalls fuellen
	public void fill_sorteFollowers(MyE2_MessageVector mv) throws myException {
		RB_KM mKey = this.masterKey.k_atom_left();
		MyLong id_artikel_bez = this.get_MyLong_maskVal(mKey, BEWEGUNG_ATOM.id_artikel_bez);
		
		if (id_artikel_bez!=null && id_artikel_bez.get_bOK()) {
				
			RECORD_ARTIKEL_BEZ  rab = new RECORD_ARTIKEL_BEZ(id_artikel_bez.get_cUF_LongString());
			
			this.set_maskVal(mKey, BEWEGUNG_ATOM.id_artikel, 	rab.fs(ARTIKEL_BEZ.id_artikel),mv);
			this.set_maskVal(mKey, BEWEGUNG_ATOM.artbez1, 		rab.fs(ARTIKEL_BEZ.artbez1),mv);
			this.set_maskVal(mKey, BEWEGUNG_ATOM.artbez2, 		rab.fs(ARTIKEL_BEZ.artbez1),mv);
			this.set_maskVal(mKey, FZ__CONST.f_keys.EINHEIT.k(), rab.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit_preis().fs(EINHEIT.einheitkurz),mv);
			
			MyLong id_adresse_start = this.get_MyLong_dbVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.id_adresse);
			if (id_adresse_start!=null && id_adresse_start.isOK()) {
				RECORD_ADRESSE_extend 	startAdresse = new RECORD_ADRESSE_extend(id_adresse_start.get_lValue());
				RECORD_ADRESSE_extend   mainAdresse = new RECORD_ADRESSE_extend(startAdresse.get_main_Adress());
				RECORD_EAK_CODE  		recAVV = mainAdresse.get_AVV_Code(rab);
				if (recAVV!=null) {
					this.set_maskVal(this.masterKey.k_vektor(), BEWEGUNG_VEKTOR.id_eak_code, recAVV.fs(EAK_CODE.id_eak_code),mv);
				}
			}
		} else {
			this.set_maskVal(mKey, BEWEGUNG_ATOM.id_artikel, 	"",mv);
			this.set_maskVal(mKey, BEWEGUNG_ATOM.artbez1, 	"",mv);
			this.set_maskVal(mKey, BEWEGUNG_ATOM.artbez2, 	"",mv);
			this.set_maskVal(mKey, FZ__CONST.f_keys.EINHEIT.k(), 	"",mv);
			this.set_maskVal(this.masterKey.k_vektor(),BEWEGUNG_VEKTOR.id_eak_code,"",mv);
		}
	}
	
	
//	public void fill_kontrakt_kombi_followers( MyE2_MessageVector mv) throws myException {
//		
//		RB_KM kAtom = this.masterKey.k_atom_left();
//		RB_KF kKombi = FZ__CONST.f_keys.KOMBI_ANG_KON_LEFT.k();
//		
//		
//		WE_CO_search_kontrakt_angebot search_kombi = (WE_CO_search_kontrakt_angebot)this.get_comp(kAtom, kKombi, mv);
//		
//		//hier ist entweder ein leer-eintrag oder ein wert mit TAG im Feld (KON@1222212)
//				
//		//sonderfall, hier kommt ein wert wie z.B. KON@1222212 in das textFeld
//		String actual_mask_val = ((WE_CO_search_kontrakt_angebot)this.get_comp(kAtom, kKombi, mv)).get_tf_search_input().getText();
//		
//		if (S.isEmpty(actual_mask_val) || actual_mask_val.startsWith("ANG@") || actual_mask_val.startsWith("KON@")) {
//		
//			if (S.isEmpty(actual_mask_val)) {
//				search_kombi.get_tf_search_input().setText("");
//			} else {
//				search_kombi.get_tf_search_input().setText(actual_mask_val.substring(4));
//			}
//			
//			
//			RB_SearchFieldSaveable  	sf_artbez = 		(RB_SearchFieldSaveable)this.get_comp(this.masterKey.k_atom_left(),BEWEGUNG_ATOM.id_artikel_bez,mv);
//			
//			MyLong  old_artbez = new MyLong("0");
//			MyLong  old_artikel = new MyLong("0");
//			if (sf_artbez.is_search_done_correct() && S.isFull(sf_artbez.rb_readValue_4_dataobject())) {
//				old_artbez = new MyLong(sf_artbez.rb_readValue_4_dataobject());
//				if (old_artbez.get_bOK()) {
//					RECORD_ARTIKEL rab = new RECORD_ARTIKEL_BEZ(old_artbez.get_lValue()).get_UP_RECORD_ARTIKEL_id_artikel();
//					old_artikel  = new MyLong(rab.l(ARTIKEL.id_artikel).longValue());
//				}
//			}
//	
//			//jetzt den string auseinandernehmen
//			String s_value_id_vpos_kon = "";
//			String s_value_id_vpos_std  = "";
//			
//			if(S.isFull(actual_mask_val)) {
//				if (actual_mask_val.startsWith("KON@")) {
//					s_value_id_vpos_kon = actual_mask_val.substring(4);
//					RECORD_VPOS_KON  kon = new RECORD_VPOS_KON(s_value_id_vpos_kon);
//					
//					if (kon.l(VPOS_KON.id_artikel, -1L).longValue()!=old_artikel.get_lValue(-2L)) {
//						sf_artbez.rb_set_db_value_manual(kon.ufs(VPOS_KON.id_artikel_bez), false,false);
//						fill_sorteFollowers(mv);
//					}
//					((IF_RB_Component)this.get_comp(this.masterKey.k_atom_left(),BEWEGUNG_ATOM.e_preis,mv)).rb_set_db_value_manual(kon.fs(VPOS_KON.einzelpreis,""));
//					((MyE2IF__Component)this.get_comp(this.masterKey.k_atom_left(),BEWEGUNG_ATOM.e_preis,mv)).set_bEnabled_For_Edit(false);
//				} else if (actual_mask_val.startsWith("ANG@")) {
//					s_value_id_vpos_std = actual_mask_val.substring(4);
//					RECORD_VPOS_STD  std = new RECORD_VPOS_STD(s_value_id_vpos_std);
//				
//					if (std.l(VPOS_STD.id_artikel, -1L).longValue()!=old_artikel.get_lValue(-2L)) {
//						sf_artbez.rb_set_db_value_manual(std.ufs(VPOS_STD.id_artikel_bez), false,false);
//						fill_sorteFollowers(mv);
//					}
//					((IF_RB_Component)this.get_comp(this.masterKey.k_atom_left(),BEWEGUNG_ATOM.e_preis,mv)).rb_set_db_value_manual(std.fs(VPOS_STD.einzelpreis,""));
//					((MyE2IF__Component)this.get_comp(this.masterKey.k_atom_left(),BEWEGUNG_ATOM.e_preis,mv)).set_bEnabled_For_Edit(false);
//				}
//			} else {
//				((MyE2IF__Component)this.get_comp(this.masterKey.k_atom_left(),BEWEGUNG_ATOM.e_preis,mv)).set_bEnabled_For_Edit(true);
//				this.set_maskVal(this.masterKey.k_atom_left(),BEWEGUNG_ATOM.e_preis,"",mv);
//				this.set_maskVal(this.masterKey.k_atom_right(),BEWEGUNG_ATOM.e_preis,"",mv);
//			}
//	
//			search_kombi.get_grid4Label_kontrakt_oder_angebot().removeAll();
//			search_kombi.get_grid4Label_kontrakt_oder_angebot().add(search_kombi.get_actual_label(), new RB_gld()._ins(E2_INSETS.I(0,0,0,0)));
//		} else {
//			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("WE___MASK_Controller.fill_kontrakt_kombi_followers() only allowd witt empty value or value starts with on of (ANG@ / KON@)")));
//		}
//		
//		
////		DEBUG.System_println("WERTE: Kontrakt:<"+sf_kontrakt.rb_readValue_4_dataobject()+">        Angebot:<"+sf_angebot.rb_readValue_4_dataobject()+">");
//		
//	}

	
	

	public __WE_MASTER_KEY get_masterKey() {
		return masterKey;
	}
	
	
	
	public _WE_MASK_Controller fill_lieferant_adress(RECORD_ADRESSE rec_adresse, MyE2_MessageVector mv) throws myException {
		if (rec_adresse != null) {
			RECORD_ADRESSE_extend  recFound = new RECORD_ADRESSE_extend(rec_adresse);
			RECORD_ADRESSE_extend  recMain = new RECORD_ADRESSE_extend(rec_adresse).get_main_Adress();
			RECORD_LIEFERADRESSE  liefer = null;
			RECORD_FIRMENINFO     rec_fi = null;
			
			if (recFound.is_station_adress()) {
				liefer = recFound.get_DOWN_RECORD_LIST_LIEFERADRESSE_id_adresse_liefer().get(0);
			} else {
				rec_fi = recFound.get_DOWN_RECORD_LIST_FIRMENINFO_id_adresse().get(0);
			}
			
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.laendercode, 		recFound.get_UP_RECORD_LAND_id_land()!=null?recFound.get_UP_RECORD_LAND_id_land().get_LAENDERCODE_cUF_NN(""):"",mv);
//			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.id_adresse_basis, 	recMain.get_ID_ADRESSE_cUF(),mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.id_adresse_besitzer,	recMain.ufs(ADRESSE.id_adresse,""),	mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.name1, recFound.fs(ADRESSE.name1,""),mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.name2, recFound.fs(ADRESSE.name2,""),mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.name3, recFound.fs(ADRESSE.name3,""),mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.strasse, recFound.fs(ADRESSE.strasse,""),mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.hausnummer, recFound.fs(ADRESSE.hausnummer,""),mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.plz, recFound.fs(ADRESSE.plz,""),mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.ort, recFound.fs(ADRESSE.ort,""),mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.ortzusatz, recFound.fs(ADRESSE.ortzusatz,""),mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.oeffnungszeiten, liefer==null?rec_fi.fs(FIRMENINFO.oeffnungszeiten,""):liefer.fs(LIEFERADRESSE.oeffnungszeiten,""),mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.plz, recFound.fs(ADRESSE.plz,""),mv);
			
			String c_tel_std = recFound.get_StandardTelefonNumber();
			String c_fax_std = recFound.get_StandardFaxNumber();
			
			if (S.isEmpty(c_tel_std)) {
				c_tel_std = recMain.get_StandardTelefonNumber();
			}
			
			if (S.isEmpty(c_fax_std)) {
				c_fax_std = recMain.get_StandardFaxNumber();
			}
			
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.telefon, S.NN(c_tel_std),mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.fax, S.NN(c_fax_std),mv);


			
		} else {
			
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.laendercode, 				"",mv);
//			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.id_adresse_basis, 			"",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.id_adresse_besitzer,      	"",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.name1, "",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.name2, "",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.name3, "",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.strasse, "",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.hausnummer, "",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.plz, "",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.ort, "",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.ortzusatz, "",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.oeffnungszeiten, "",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.plz, "",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.telefon, "",mv);
			this.set_maskVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.fax, "",mv);
		}

		return this;
	}
	
	
	public _WE_MASK_Controller fill_sorten_bez( MyE2_MessageVector mv) throws myException {
		
		RB_KM mKey = this.masterKey.k_atom_left();

		MyLong id_artikel_bez = this.get_MyLong_maskVal(mKey, BEWEGUNG_ATOM.id_artikel_bez);
		
		if (id_artikel_bez!=null && id_artikel_bez.get_bOK()) {
				
			RECORD_ARTIKEL_BEZ  rab = new RECORD_ARTIKEL_BEZ(id_artikel_bez.get_cUF_LongString());
			this.set_maskVal(mKey, BEWEGUNG_ATOM.id_artikel_bez, 	rab.fs(ARTIKEL_BEZ.id_artikel_bez),mv);
			this.set_maskVal(mKey, BEWEGUNG_ATOM.id_artikel, 	rab.fs(ARTIKEL_BEZ.id_artikel),mv);
			this.set_maskVal(mKey, BEWEGUNG_ATOM.artbez1, 		rab.fs(ARTIKEL_BEZ.artbez1),mv);
			this.set_maskVal(mKey, BEWEGUNG_ATOM.artbez2, 		rab.fs(ARTIKEL_BEZ.artbez1),mv);
			this.set_maskVal(mKey, FZ__CONST.f_keys.EINHEIT.k(), rab.get_UP_RECORD_ARTIKEL_id_artikel().get_UP_RECORD_EINHEIT_id_einheit_preis().fs(EINHEIT.einheitkurz),mv);
			
			MyLong id_adresse_start = this.get_MyLong_dbVal(this.masterKey.k_atom_left__station_start(), BEWEGUNG_STATION.id_adresse);
			if (id_adresse_start!=null && id_adresse_start.isOK()) {
				RECORD_ADRESSE_extend 	startAdresse = new RECORD_ADRESSE_extend(id_adresse_start.get_lValue());
				RECORD_ADRESSE_extend   mainAdresse = new RECORD_ADRESSE_extend(startAdresse.get_main_Adress());
				RECORD_EAK_CODE  		recAVV = mainAdresse.get_AVV_Code(rab);
				if (recAVV!=null) {
					this.set_maskVal(this.masterKey.k_vektor(), BEWEGUNG_VEKTOR.id_eak_code, recAVV.fs(EAK_CODE.id_eak_code),mv);
				}
			}
		} else {
			this.set_maskVal(mKey, BEWEGUNG_ATOM.id_artikel_bez, 	"",mv);
			this.set_maskVal(mKey, BEWEGUNG_ATOM.id_artikel, 	"",mv);
			this.set_maskVal(mKey, BEWEGUNG_ATOM.artbez1, 	"",mv);
			this.set_maskVal(mKey, BEWEGUNG_ATOM.artbez2, 	"",mv);
			this.set_maskVal(mKey, FZ__CONST.f_keys.EINHEIT.k(), 	"",mv);
			this.set_maskVal(this.masterKey.k_vektor(),BEWEGUNG_VEKTOR.id_eak_code,"",mv);
		}

		
		
		return this;
	}
	
	public void _fill_lade_datum(MyE2_MessageVector mv) throws myException {
		KEY_ATOM atom_quelle_key 	= this.masterKey.k_atom_left();

		KEY_ATOM atom_ziel_key 		= this.masterKey.k_atom_right();

		KEY_VEKT vekt_key 			= this.masterKey.k_vektor();

		String leistung_datum_start = this.get_maskVal(atom_quelle_key, BEWEGUNG_ATOM.leistungsdatum);

		this.set_maskVal(vekt_key, BEWEGUNG_VEKTOR.l_datum_von, leistung_datum_start, mv);

		this.set_maskVal(vekt_key, BEWEGUNG_VEKTOR.l_datum_bis, leistung_datum_start, mv);

		if(this.get_maskVal(atom_ziel_key, BEWEGUNG_ATOM.leistungsdatum).equals(leistung_datum_start)){
			this.set_maskVal(atom_ziel_key, BEWEGUNG_ATOM.leistungsdatum, leistung_datum_start, mv);
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
		KEY_ATOM atom_ziel_key 		= this.masterKey.k_atom_right();

		String leistung_datum_ziel = this.get_maskVal(atom_ziel_key, BEWEGUNG_ATOM.leistungsdatum);

		this.set_maskVal(vekt_key, BEWEGUNG_VEKTOR.a_datum_von, leistung_datum_ziel, mv);
		this.set_maskVal(vekt_key, BEWEGUNG_VEKTOR.a_datum_bis, leistung_datum_ziel, mv);
	}
	
}
