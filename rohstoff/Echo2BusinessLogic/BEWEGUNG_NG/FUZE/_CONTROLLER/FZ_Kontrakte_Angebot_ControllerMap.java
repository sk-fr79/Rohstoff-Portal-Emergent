package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._CONTROLLER;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.COMP.SearchField.RB_SearchFieldSaveable;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES._SIMPLE_MESSAGE.kategorien;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_STD_ANGEBOT;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_KON;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_VPOS_STD;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.MyRECORD;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.SELECT.SEL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_ATOM;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.KEYS.KEY_VEKT;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.FZ_MASK_Search_Kontrakt_Angebot_in_ONE;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RECORD_VPOS_STD_ext;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST.SEARCH_EK_OR_VK;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VPOS_KON;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_artikel;

public abstract class FZ_Kontrakte_Angebot_ControllerMap extends RB_MaskControllerMap {

	public FZ_Kontrakte_Angebot_ControllerMap(RB_ComponentMapCollector p_componentMapCollector) throws myException {
		super(p_componentMapCollector);
	}

	public abstract KEY_ATOM get_left_atom() throws myException;

	public abstract KEY_ATOM get_right_atom() throws myException;

	public abstract KEY_VEKT get_vektor() throws myException;
	
	public abstract RB_KF get_angebot_kontrakt_field() throws myException;
	
	public abstract RB_KF get_einheit_field() throws myException;

	public abstract FZ__CONST.SEARCH_EK_OR_VK get_ek() throws myException;
	
	public void fill_angebot_kontrakt(MyE2_MessageVector mv, String unformated_ergebnis) throws myException{

		RB_KM kAtom = (get_ek() == SEARCH_EK_OR_VK.EK)?get_left_atom():get_right_atom();

		RB_KF kKombi = get_angebot_kontrakt_field();

		FZ_MASK_Search_Kontrakt_Angebot_in_ONE searc_comp = (FZ_MASK_Search_Kontrakt_Angebot_in_ONE) this.get_comp(kAtom, kKombi, mv);

		//hier ist entweder ein leer-eintrag oder ein wert mit TAG im Feld (KON@1222212)

		//sonderfall, hier kommt ein wert wie z.B. KON@1222212 in das textFeld
		String actual_mask_val = unformated_ergebnis;

		if (S.isEmpty(actual_mask_val) || actual_mask_val.startsWith("ANG@") || actual_mask_val.startsWith("KON@")) {

			if (S.isEmpty(actual_mask_val)) {
				searc_comp.get_tf_search_input().setText("");

				this.set_maskVal(get_left_atom(), get_angebot_kontrakt_field(),	"",	mv);
				this.set_maskVal(get_right_atom(), get_angebot_kontrakt_field(),"",	mv);
				
				this.set_maskVal(get_left_atom(), BEWEGUNG_ATOM.id_vpos_kon,	"",	mv);
				this.set_maskVal(get_left_atom(), BEWEGUNG_ATOM.id_vpos_std,	"", mv);
				this.set_maskVal(get_right_atom(),BEWEGUNG_ATOM.id_vpos_kon, 	"", mv);
				this.set_maskVal(get_right_atom(),BEWEGUNG_ATOM.id_vpos_std, 	"", mv);
			}

			//jetzt den string auseinandernehmen
			if(S.isFull(actual_mask_val)) {
				if (actual_mask_val.startsWith("KON@")) {

					if(get_ek()==SEARCH_EK_OR_VK.EK){
						this.set_maskVal(get_left_atom(), BEWEGUNG_ATOM.id_vpos_kon, actual_mask_val.substring(4), 	mv);
						this.set_maskVal(get_left_atom(), BEWEGUNG_ATOM.id_vpos_std, "",							mv);
					}else{
						this.set_maskVal(get_right_atom(),BEWEGUNG_ATOM.id_vpos_kon, actual_mask_val.substring(4), 	mv);		
						this.set_maskVal(get_right_atom(), BEWEGUNG_ATOM.id_vpos_std, "", 							mv);
					}
					RECORD_VPOS_KON  kon = new RECORD_VPOS_KON(actual_mask_val.substring(4));

					
					
					searc_comp.refresh_kontrakt_or_angebot_label();
					
					this
					.check_and_fill_leistungdatum(kon, mv,true)
					.check_and_fill_sorte(kon, mv, true)
					.check_and_fill_preis(kon, mv, true)
					;


				} else if (actual_mask_val.startsWith("ANG@")) {

					if(get_ek()==SEARCH_EK_OR_VK.EK){
						this.set_maskVal(get_left_atom(), BEWEGUNG_ATOM.id_vpos_std, actual_mask_val.substring(4), 	mv);
						this.set_maskVal(get_left_atom(), BEWEGUNG_ATOM.id_vpos_kon, "", 							mv);
					}else{
						this.set_maskVal(get_right_atom(),BEWEGUNG_ATOM.id_vpos_std, actual_mask_val.substring(4),	mv);		
						this.set_maskVal(get_right_atom(), BEWEGUNG_ATOM.id_vpos_kon, "", 							mv);
					}
					
					RECORD_VPOS_STD  std = new RECORD_VPOS_STD(actual_mask_val.substring(4));
					
					searc_comp.refresh_kontrakt_or_angebot_label();
					
					this
					.check_and_fill_leistungdatum(std, mv,false)
					.check_and_fill_sorte(std, mv, false)
					.check_and_fill_preis(std, mv, false)
					;
				}

				searc_comp.get_grid4Label_kontrakt_oder_angebot().removeAll();
				searc_comp.get_grid4Label_kontrakt_oder_angebot().add(searc_comp.get_actual_label(), new RB_gld()._ins(0));
				
				this.get_comp(get_left_atom(), 	BEWEGUNG_ATOM.e_preis, 					mv).set_bEnabled_For_Edit(false);
				this.get_comp(get_right_atom(), BEWEGUNG_ATOM.e_preis, 					mv).set_bEnabled_For_Edit(false);
				
				this.get_comp(get_left_atom(), 	BEWEGUNG_ATOM.manuell_preis, 			mv).set_bEnabled_For_Edit(true);
				this.get_comp(get_right_atom(),	BEWEGUNG_ATOM.manuell_preis, 			mv).set_bEnabled_For_Edit(true);
				
			} else {
				((MyE2IF__Component)this.get_comp(kAtom,BEWEGUNG_ATOM.e_preis,	mv)).set_bEnabled_For_Edit(true);
				this.get_comp(kAtom,	BEWEGUNG_ATOM.manuell_preis, 	mv).set_bEnabled_For_Edit(false);
				this.set_maskVal(kAtom, BEWEGUNG_ATOM.manuell_preis, "N", mv);
			}

		} else {
			mv.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("WE___MASK_Controller.fill_kontrakt_kombi_followers() only allowd witt empty value or value starts with on of (ANG@ / KON@)")));
		}
	}

	private FZ_Kontrakte_Angebot_ControllerMap check_and_fill_leistungdatum(MyRECORD kon, MyE2_MessageVector mv, boolean isKontrakt) throws myException{

		RB_KM k_atom_left = get_left_atom();
				
		Rec20_VPOS_KON record_vpos ;
		String datum_kon_von = "";
		String datum_kon_bis = "";
		if(isKontrakt){
			record_vpos 	= new Rec20_VPOS_KON(new Rec20(_TAB.vpos_kon)._fill_id(kon.get_UnFormatedValue(VPOS_KON.id_vpos_kon.fn())));
			datum_kon_von	= record_vpos.get_gueltig_von();
			datum_kon_bis 	= record_vpos.get_gueltig_bis();
		}else{
			String q = new SEL().FROM(_TAB.vpos_std_angebot).WHERE(new vgl(VPOS_STD_ANGEBOT.id_vpos_std, kon.get_UnFormatedValue(VPOS_STD.id_vpos_std.fn()))).s();
			Rec20 record_vpos_ang = new Rec20(_TAB.vpos_std_angebot)._fill_sql(q);
			datum_kon_von	= record_vpos_ang.get_myDate_dbVal(VPOS_STD_ANGEBOT.gueltig_von).get_cDateStandardFormat();
			datum_kon_bis	= record_vpos_ang.get_myDate_dbVal(VPOS_STD_ANGEBOT.gueltig_bis).get_cDateStandardFormat();
		}

		String leistungsdatum_q = this.get_maskVal(k_atom_left, BEWEGUNG_ATOM.leistungsdatum);

		if ( (S.isEmpty(datum_kon_von) || S.isEmpty(datum_kon_bis) ) ){
			mv._addWarn("Die kontrakt Datum sind nicht gefüllt!");
		}else{
			if(myDateHelper.get_Date1_Greater_Date2(leistungsdatum_q,datum_kon_bis) || myDateHelper.get_Date1_Less_Date2(datum_kon_bis, leistungsdatum_q)){

//				this.set_maskVal(k_atom_left, BEWEGUNG_ATOM.leistungsdatum, datum_kon_von, mv);
//				
//				this.set_maskVal(k_atom_right, 	BEWEGUNG_ATOM.leistungsdatum, datum_kon_bis, mv);
//				
//				this.set_maskVal(k_vektor,		BEWEGUNG_VEKTOR.l_datum_von, "", mv);
//				this.set_maskVal(k_vektor,		BEWEGUNG_VEKTOR.l_datum_bis, "", mv);
//				this.set_maskVal(k_vektor,		BEWEGUNG_VEKTOR.a_datum_von, "", mv);
//				this.set_maskVal(k_vektor,		BEWEGUNG_VEKTOR.a_datum_bis, "", mv);
				
				mv._addWarn("Das Datum ist ausserhalb der Kontraktgültigkeit.");
//				mv._addInfo("Das Datum ist ausserhalb der Kontraktgültigkeit. Der Kontrakt wird aus der Maske entfernt!");
			}
		}


		return this;
	}

	private FZ_Kontrakte_Angebot_ControllerMap check_and_fill_preis(MyRECORD kon, MyE2_MessageVector mv, boolean isKontrakt) throws myException{
		RB_KM k_atom_left = get_left_atom();
		RB_KM k_atom_right = get_right_atom();

		Rec20 record_vpos;
		String epreis = "";

		if(isKontrakt){
			record_vpos = new Rec20(_TAB.vpos_kon)._fill_id(kon.get_UnFormatedValue(VPOS_KON.id_vpos_kon.fn()));
			epreis = record_vpos.get_fs_dbVal(VPOS_KON.einzelpreis,"");
		}else{
			record_vpos = new Rec20(_TAB.vpos_std)._fill_id(kon.get_UnFormatedValue(VPOS_STD.id_vpos_std.fn()));
			epreis = record_vpos.get_fs_dbVal(VPOS_STD.einzelpreis,"");
		}

		((IF_RB_Component)	this.get_comp(k_atom_left,	BEWEGUNG_ATOM.e_preis,mv)).rb_set_db_value_manual(epreis);
		((MyE2IF__Component)this.get_comp(k_atom_left,	BEWEGUNG_ATOM.e_preis,mv)).set_bEnabled_For_Edit(false);

		((IF_RB_Component)	this.get_comp(k_atom_right,	BEWEGUNG_ATOM.e_preis,mv)).rb_set_db_value_manual(epreis);
		((MyE2IF__Component)this.get_comp(k_atom_right,	BEWEGUNG_ATOM.e_preis,mv)).set_bEnabled_For_Edit(false);

		return this;
	}



	private FZ_Kontrakte_Angebot_ControllerMap check_and_fill_sorte(MyRECORD std, MyE2_MessageVector mv, boolean isKontrakt) throws myException{

		RB_KM k_atom_left = get_left_atom();

		RB_KM k_atom_right = get_right_atom();


		Rec20_VPOS_KON record_vpos;
		MyLong l_id_artBez;
		if(isKontrakt){
			record_vpos = new Rec20_VPOS_KON(new Rec20(_TAB.vpos_kon)._fill_id(std.get_UnFormatedValue(VPOS_KON.id_vpos_kon.fn())));
			l_id_artBez = record_vpos.get_myLong_dbVal(VPOS_KON.id_artikel_bez);
		}else{
			record_vpos = new Rec20_VPOS_KON(new Rec20(_TAB.vpos_std)._fill_id(std.get_UnFormatedValue(VPOS_STD.id_vpos_std.fn())));
			l_id_artBez = record_vpos.get_myLong_dbVal(VPOS_STD.id_artikel_bez);
		}

		RB_SearchFieldSaveable  sf_artbez = (RB_SearchFieldSaveable)this.get_comp(k_atom_left,BEWEGUNG_ATOM.id_artikel_bez,mv);
		sf_artbez.rb_set_db_value_manual(l_id_artBez.get_cF_LongString());


		if(l_id_artBez.get_bOK()){
			Rec20_artikel recArtikel = new Rec20_artikel(new Rec20(_TAB.artikel_bez)._fill_id(l_id_artBez.get_lValue()));

			this.set_maskVal(k_atom_left, 	BEWEGUNG_ATOM.id_artikel,		recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel), mv);
			this.set_maskVal(k_atom_left, 	BEWEGUNG_ATOM.artbez1, 		recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.artbez1)	, mv);
			this.set_maskVal(k_atom_left,	BEWEGUNG_ATOM.artbez2, 		recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.artbez2)	, mv);

			this.set_maskVal(k_atom_right, 	BEWEGUNG_ATOM.id_artikel_bez,recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez)	, mv);
			this.set_maskVal(k_atom_right, 	BEWEGUNG_ATOM.id_artikel,	recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel), mv);
			this.set_maskVal(k_atom_right, 	BEWEGUNG_ATOM.artbez1, 		recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.artbez1)	, mv);
			this.set_maskVal(k_atom_right, 	BEWEGUNG_ATOM.artbez2, 		recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.artbez2)	, mv);

			this.set_maskVal(k_atom_left, 	get_einheit_field(), 			recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez),mv);
			this.set_maskVal(k_atom_right, 	get_einheit_field(),			recArtikel.get_ufs_dbVal(ARTIKEL_BEZ.id_artikel_bez),mv);
		}
		return this;
	}


	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)
			throws myException {
		return null;
	}

}
