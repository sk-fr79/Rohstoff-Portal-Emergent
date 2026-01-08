package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.HashMap;

import org.hibernate.hql.classic.WhereParser;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMapCollector;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerField;
import panter.gmbh.Echo2.RB.BASICS.RB_MaskControllerMap;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HighLevel_SelectFieldUser;
import panter.gmbh.Echo2.RB.IF.IF_RB_Component;
import panter.gmbh.Echo2.__BASIC_MODULS.MESSAGES.__SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN;
import panter.gmbh.basics4project.E2_MODULNAME_ENUM;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.ADRESSE;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL;
import panter.gmbh.basics4project.DB_ENUMS.ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_ENUMS.LAND;
import panter.gmbh.basics4project.DB_ENUMS.LIEFERBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS.ZAHLUNGSBEDINGUNGEN;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.RecList21;
import panter.gmbh.indep.dataTools.TERM.vgl;
import panter.gmbh.indep.dataTools.TERM.vgl_YN;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public class KFIX_K_M_Controller extends RB_MaskControllerMap {



	private String vorgangTyp;


	public KFIX_K_M_Controller(RB_ComponentMapCollector componentMapCollector) throws myException {
		super(componentMapCollector);
	}

	@Override
	public MyE2_MessageVector do_mask_settings(IF_RB_Component compCalling, String fieldVal, boolean primaryCall)
			throws myException {

		MyE2_MessageVector omv = new  MyE2_MessageVector();

		RB_MaskControllerField  callingField = this.get_MaskControllerField(compCalling);

		String modul = compCalling.rb_ComponentMap_this_belongsTo().get_oModulContainerMASK_This_BelongsTo().get_MODUL_IDENTIFIER();
		
		vorgangTyp = modul.equals(E2_MODULNAME_ENUM.MODUL.NAME_MODUL_EK_KONTRAKT_MASK_NG.get_callKey())?myCONST.VORGANGSART_EK_KONTRAKT:myCONST.VORGANGSART_VK_KONTRAKT;
		
		if (callingField != null) {

			if(callingField.get_component() instanceof KFIX_K_M_Adresse_SearchField){
				fill_adresse_dateien(omv, fieldVal);
			}else

			if(callingField.get_component() instanceof KFIX_K_M_Artikel_SearchField){
				fill_artikel_dateien(omv, fieldVal);
			}else

			if(callingField.get_component() instanceof KFIX_K_M_SF_Zahlungsbedingung){
				fill_zahlungsbedingung_dateien(omv, fieldVal);
			}else

			if(callingField.get_component() instanceof KFIX_K_M_BT_Select_Fremd_Ansprechpartner){
				fill_ansprechpartner_dateien(omv, fieldVal);
			}

		}

		return omv;
	}


	private void fill_ansprechpartner_dateien(MyE2_MessageVector omv, String fieldVal) throws myException {
		RB_KM keymap = new RB_KM(_TAB.vkopf_kon);

		if(S.isFull(fieldVal)){
			RECORD_ADRESSE_extend oAdress = new RECORD_ADRESSE_extend(bibALL.convertID2UnformattedID(fieldVal));			

			HashMap<String, String> nameAnsprech = oAdress.get_hmFormularFelderAnsprechpartner(vorgangTyp);
			if(S.isFull(nameAnsprech.get("ANSPRECHPARTNER"))){
				this.set_maskVal(keymap, VKOPF_KON.name_ansprechpartner, nameAnsprech.get("ANSPRECHPARTNER"), omv);
			}
			if(S.isFull(nameAnsprech.get("TELEFON"))){
				this.set_maskVal(keymap, VKOPF_KON.telefon_auf_formular, nameAnsprech.get("TELEFON"), omv);
			}
			if(S.isFull(nameAnsprech.get("TELEFAX"))){
				this.set_maskVal(keymap, VKOPF_KON.telefax_auf_formular, nameAnsprech.get("TELEFAX"), omv);
			}
			if(S.isFull(nameAnsprech.get("EMAIL"))){
				this.set_maskVal(keymap, VKOPF_KON.email_auf_formular, 	 nameAnsprech.get("EMAIL"), omv);
			}
		}
	}

	private void fill_adresse_dateien(MyE2_MessageVector omv, String id_adresse) throws myException{

		RB_KM keymap = new RB_KM(_TAB.vkopf_kon);

		//hier pruefen, ob die Maske bereits gespeichert war und ob es bereits positionen gibt, wenn ja, dann nix veraendern
		boolean erlaubt = this.get_ComponentMapCollector().rb_Actual_DataobjectCollector().rb_hm_DataObjects().get(_TAB.vkopf_kon.rb_km()).isStatusNew();
		
		if (!erlaubt) {
			Long idVkpfKon = this.getLongDBVal(VKOPF_KON.id_vkopf_kon);
			if (idVkpfKon!=null) {
				Rec21 rk = new Rec21(_TAB.vkopf_kon)._fill_id(idVkpfKon);
				RecList21 rl = rk.get_down_reclist21(VPOS_KON.id_vkopf_kon, new vgl_YN(VPOS_KON.deleted, false).s(), null);
				if (rl.size()==0) {
					erlaubt=true;
				}
			}
		}
		
		if (erlaubt) {
			if(S.isFull(id_adresse)){
				Rec20 recAdresse = new Rec20(_TAB.adresse)._fill_id(id_adresse);
				Rec20 recHaendlerInfo = recAdresse.get_up_Rec20(USER.id_user);
				Rec20 recSachbearbeiter = recAdresse.get_up_Rec20(ADRESSE.id_user_sachbearbeiter, USER.id_user, true);
	
				Rec20 recLaender = recAdresse.get_up_Rec20(LAND.id_land);
				Rec20 recWaehrung = recAdresse.get_up_Rec20(WAEHRUNG.id_waehrung);
				
				Rec20 recZahlB = null;
				if(vorgangTyp.equals(myCONST.VORGANGSART_EK_KONTRAKT)){
					recZahlB = recAdresse.get_up_Rec20(ADRESSE.id_zahlungsbedingungen, 		ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen, false);
				}else{ 
					recZahlB = recAdresse.get_up_Rec20(ADRESSE.id_zahlungsbedingungen_vk, 	ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen, false);
				}
				Rec20 recLief = recAdresse.get_up_Rec20(ADRESSE.id_lieferbedingungen, LIEFERBEDINGUNGEN.id_lieferbedingungen, false);
				
				this.set_maskVal(		keymap, VKOPF_KON.id_adresse, recAdresse.get_fs_dbVal(ADRESSE.id_adresse), omv);
				this.set_maskVal(		keymap, VKOPF_KON.name1, recAdresse.get_fs_dbVal(ADRESSE.name1), omv);//get_NAME1_cF_NN(""), omv);
				this.set_maskVal(		keymap, VKOPF_KON.name2, recAdresse.get_fs_dbVal(ADRESSE.name2), omv);
				this.set_maskVal(		keymap, VKOPF_KON.name3, recAdresse.get_fs_dbVal(ADRESSE.name3), omv);
				this.set_maskVal(		keymap, VKOPF_KON.strasse, recAdresse.get_fs_dbVal(ADRESSE.strasse), omv);
				this.set_maskVal(		keymap, VKOPF_KON.hausnummer, recAdresse.get_fs_dbVal(ADRESSE.hausnummer), omv);
				this.set_maskVal(		keymap, VKOPF_KON.plz, recAdresse.get_fs_dbVal(ADRESSE.plz), omv);
				this.set_maskVal(		keymap, VKOPF_KON.ort, recAdresse.get_fs_dbVal(ADRESSE.ort), omv);
				if (recLaender!=null) {
					this.set_maskVal(	keymap, VKOPF_KON.laendercode, recLaender.get_fs_dbVal(LAND.laendercode), omv); 
				}
				if (recWaehrung!=null) {
					this.set_maskVal(	keymap, VKOPF_KON.id_waehrung_fremd, recWaehrung.get_fs_dbVal(WAEHRUNG.id_waehrung), omv); 
				}
				this.set_maskVal(		keymap, VKOPF_KON.id_user, bibALL.get_ID_USER_FORMATTED(), omv);
				if (recHaendlerInfo!=null) {
					this.set_maskVal(	keymap, VKOPF_KON.id_user_ansprech_intern, recHaendlerInfo.get_fs_dbVal(USER.id_user), omv); 
				}
				if (recSachbearbeiter!=null) {
					this.set_maskVal(	keymap, VKOPF_KON.id_user_sachbearb_intern, recSachbearbeiter.get_fs_dbVal(USER.id_user), omv); 
				}
				if (recLief != null) {
					this.set_maskVal(		keymap, VKOPF_KON.lieferbedingungen, recLief.get_fs_dbVal(LIEFERBEDINGUNGEN.bezeichnung,"-"),omv);
				}
				
				if(! (recZahlB == null)){
					this.set_maskVal(	keymap, VKOPF_KON.id_zahlungsbedingungen, recZahlB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.id_zahlungsbedingungen,"-"),omv);
					this.set_maskVal(	keymap, VKOPF_KON.zahlungsbedingungen, recZahlB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.bezeichnung), omv);
					this.set_maskVal(	keymap, VKOPF_KON.fixmonat, recZahlB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.fixmonat), omv);
					this.set_maskVal(	keymap, VKOPF_KON.fixtag, recZahlB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.fixtag), omv);
					this.set_maskVal(	keymap, VKOPF_KON.zahltage, recZahlB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.zahltage), omv);
					this.set_maskVal(	keymap, VKOPF_KON.skonto_prozent, recZahlB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.skonto_prozent), omv);
					this.set_maskVal(	keymap, VKOPF_KON.fixmonat, recZahlB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.fixmonat), omv);
				}
				this.get_comp(			keymap, VKOPF_KON.fix_id_artbez, omv).set_bEnabled_For_Edit(true);
				
				RECORD_ADRESSE_extend  rae = new RECORD_ADRESSE_extend(id_adresse);
				this.set_maskVal(	keymap, VKOPF_KON.telefon_auf_formular, S.NN(rae.get_StandardTelefonNumber()), omv);
				this.set_maskVal(	keymap, VKOPF_KON.telefax_auf_formular, S.NN(rae.get_StandardFaxNumber()), omv);
				
			}else{
				this.set_maskVal(keymap, VKOPF_KON.id_adresse, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.name1,"", omv);
				this.set_maskVal(keymap, VKOPF_KON.name2, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.name3, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.strasse, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.hausnummer, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.plz, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.ort, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.laendercode, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.id_waehrung_fremd, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.id_user, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.id_user_ansprech_intern, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.id_user_sachbearb_intern, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.lieferbedingungen, "",omv);
				this.set_maskVal(keymap, VKOPF_KON.fixmonat, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.fixtag, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.zahltage, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.skonto_prozent, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.fixmonat, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.zahlungsbedingungen, "", omv);
				this.set_maskVal(keymap, VKOPF_KON.id_zahlungsbedingungen, "",omv);
				
				this.set_maskVal(keymap, VKOPF_KON.fix_id_artbez, "",omv);
				this.get_comp(keymap, VKOPF_KON.fix_id_artbez, omv).set_bEnabled_For_Edit(false);
			}
			
			//jetzt die aktionen der dropdown-fields ausfuehren, damit die nachgelagerten felder gefuellt werden
			((RB_HighLevel_SelectFieldUser)this.get_comp(_TAB.vkopf_kon.rb_km(), VKOPF_KON.id_user, omv)).doActionPassivManual();   
			((RB_HighLevel_SelectFieldUser)this.get_comp(_TAB.vkopf_kon.rb_km(), VKOPF_KON.id_user_ansprech_intern, omv)).doActionPassivManual();   
			((RB_HighLevel_SelectFieldUser)this.get_comp(_TAB.vkopf_kon.rb_km(), VKOPF_KON.id_user_sachbearb_intern, omv)).doActionPassivManual();   
			
			
			//2011-11-17: meldungen einblenden
			MyLong lId_adresse = new MyLong(id_adresse);
			if (lId_adresse != null && lId_adresse.isOK()) {
				new __SYSTEM_MESSAGE_GENERATOR_BEWEGUNGSDATEN(lId_adresse.get_cUF_LongString(), this.vorgangTyp).ACTIVATE_MESSAGES();
			}
		}
		
	}


	private void fill_artikel_dateien(MyE2_MessageVector omv, String fieldVal) throws myException{
		String anr1="";
		String anr2="";
		String idArtikel = "";
	
		RB_KM keymap = new RB_KM(_TAB.vkopf_kon);
		
		if(S.isFull(fieldVal)){
			Rec20 recArtikelBez = new Rec20(_TAB.artikel_bez)._fill_id(fieldVal); 
			Rec20 recArtikel = recArtikelBez.get_up_Rec20(ARTIKEL.id_artikel);
			
			anr1 = 		recArtikel.get_fs_dbVal(ARTIKEL.anr1, "-");
			anr2 = 		recArtikelBez.get_fs_dbVal(ARTIKEL_BEZ.anr2, "-");
			idArtikel=  recArtikelBez.get_fs_dbVal(ARTIKEL_BEZ.id_artikel, "");
		}
		

		this.set_maskVal(keymap, KFIX___CONST.ADDITIONNAL_COMP_KOPF.HASH_KEY_LAB_EINHEIT.key(), 	idArtikel	, omv);
		this.set_maskVal(keymap, KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_ANR1.key(), 			anr1		, omv);
		this.set_maskVal(keymap, KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_ANR2.key(), 			anr2		, omv);
		this.set_maskVal(keymap, VKOPF_KON.fix_id_artikel, 								idArtikel	, omv);
	}


	private void fill_zahlungsbedingung_dateien(MyE2_MessageVector omv, String zahlungsbedingung) throws myException{
		Rec20 recZahlungsB = new Rec20(_TAB.zahlungsbedingungen)._fill_id(zahlungsbedingung);

		RB_KM keymap = new RB_KM(_TAB.vkopf_kon);
		this.set_maskVal(keymap, VKOPF_KON.zahlungsbedingungen, recZahlungsB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.bezeichnung), omv);
		this.set_maskVal(keymap, VKOPF_KON.fixmonat, recZahlungsB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.fixmonat), omv);
		this.set_maskVal(keymap, VKOPF_KON.fixtag, recZahlungsB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.fixtag), omv);
		this.set_maskVal(keymap, VKOPF_KON.zahltage, recZahlungsB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.zahltage), omv);
		this.set_maskVal(keymap, VKOPF_KON.skonto_prozent, recZahlungsB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.skonto_prozent), omv);
		this.set_maskVal(keymap, VKOPF_KON.fixmonat, recZahlungsB.get_fs_dbVal(ZAHLUNGSBEDINGUNGEN.fixmonat), omv);
	}
}
