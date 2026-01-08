package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import nextapp.echo2.app.Extent;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextAnzeige;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.HIGHLEVEL.RB_HighLevel_SelectFieldUser;
import panter.gmbh.basics4project.ENUM_USER_TYP;
import panter.gmbh.basics4project.myCONST_ENUM.VORGANGSART;
import panter.gmbh.basics4project.DB_ENUMS.USER;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_USER;
import panter.gmbh.indep.MyLong;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.SQLFieldMAP;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX_K_M_ComponentMap extends RB_ComponentMap {

	private boolean isFixiert = false;
	private boolean isEk = false;

	public KFIX_K_M_ComponentMap(boolean b_fixierungsKontrakt, VORGANGSART belegtyp) throws myException {

		super();

		SQLFieldMAP  oFM = new KFIX_K_L__SQLFieldMAP(belegtyp);

		this.set_oSQLFieldMAP(oFM);

		if(belegtyp==VORGANGSART.EK_KONTRAKT){
			isEk = true;
		}

		this.isFixiert = b_fixierungsKontrakt;

		this.registerComponent(VKOPF_KON.id_vkopf_kon,    		new RB_TextField(120));
		this.registerComponent(VKOPF_KON.abgeschlossen,    		new RB_CheckBox(VKOPF_KON.abgeschlossen));
		this.registerComponent(VKOPF_KON.druckdatum,    			new RB_date_selektor(100,true));
		this.registerComponent(VKOPF_KON.buchungsnummer,    		new RB_TextField(120));
		this.registerComponent(VKOPF_KON.erstellungsdatum,    	new RB_date_selektor(100,true));
		this.registerComponent(VKOPF_KON.id_adresse,    			new KFIX_K_M_Adresse_SearchField(belegtyp));
		this.registerComponent(VKOPF_KON.kdnr,    				new RB_TextField(120));
		this.registerComponent(VKOPF_KON.vorname,    				new RB_TextField(560));
		this.registerComponent(VKOPF_KON.name1,    				new RB_TextField(560));
		this.registerComponent(VKOPF_KON.name2,    				new RB_TextField(560));
		this.registerComponent(VKOPF_KON.name3,    				new RB_TextField(560));
		this.registerComponent(VKOPF_KON.strasse,    				new RB_TextField(398));
		this.registerComponent(VKOPF_KON.hausnummer,    			new RB_TextField(60));
		this.registerComponent(VKOPF_KON.laendercode,    			new KFIX_K_M_SF_LaenderCode(VKOPF_KON.laendercode, 120));
		this.registerComponent(VKOPF_KON.ort,    					new RB_TextField(348));
		this.registerComponent(VKOPF_KON.plz,    					new RB_TextField(70));
		this.registerComponent(VKOPF_KON.ortzusatz,    			new RB_TextField(560));
		this.registerComponent(VKOPF_KON.plz_pob,    				new RB_TextField(120));
		this.registerComponent(VKOPF_KON.pob,    					new RB_TextField(302));
		this.registerComponent(VKOPF_KON.is_pob_active,    		new RB_CheckBox(VKOPF_KON.is_pob_active));
		this.registerComponent(VKOPF_KON.bemerkungen_intern,    	new RB_TextArea(500,8));
		this.registerComponent(VKOPF_KON.bezug,    				new RB_TextField(400));
		this.registerComponent(VKOPF_KON.deleted,    				new RB_CheckBox(VKOPF_KON.deleted));
		this.registerComponent(VKOPF_KON.del_date,    			new RB_TextField(100));
		this.registerComponent(VKOPF_KON.del_grund,    			new RB_TextField(400));
		this.registerComponent(VKOPF_KON.del_kuerzel,    			new RB_TextField(100));
		this.registerComponent(VKOPF_KON.druckzaehler,    		new RB_TextField(100));
		this.registerComponent(VKOPF_KON.email_auf_formular,    	new RB_TextField(400));
		this.registerComponent(VKOPF_KON.fax_ansprech_intern,    	new RB_TextField(150));
		this.registerComponent(VKOPF_KON.fax_bearbeiter_intern,   new RB_TextField(150));
		this.registerComponent(VKOPF_KON.fax_sachbearb_intern,    new RB_TextField(150));
		this.registerComponent(VKOPF_KON.fixmonat,    			new RB_TextField(30)._fsa(-2)._ttt("Fixmonat"));
		this.registerComponent(VKOPF_KON.fixtag,    				new RB_TextField(30)._fsa(-2)._ttt("Fixtage"));
		this.registerComponent(VKOPF_KON.formulartext_anfang,    	new KFIX_K_M_Formular_TextArea(isEk, 500, 8));
		this.registerComponent(VKOPF_KON.formulartext_ende,    	new KFIX_K_M_Formular_TextArea(isEk, 500, 8));
		this.registerComponent(VKOPF_KON.gueltig_bis,    			new RB_TextField(100));
		this.registerComponent(VKOPF_KON.gueltig_von,    			new RB_TextField(100));
		this.registerComponent(VKOPF_KON.id_user,    				new RB_HighLevel_SelectFieldUser(VKOPF_KON.id_user, true,new Extent(240), ENUM_USER_TYP.AKTIV)
																._aaa(new actionLoadMitarbeiterDaten(VKOPF_KON.id_user,VKOPF_KON.name_bearbeiter_intern,VKOPF_KON.tel_bearbeiter_intern,VKOPF_KON.fax_bearbeiter_intern)));
		this.registerComponent(VKOPF_KON.id_user_ansprech_intern,  new RB_HighLevel_SelectFieldUser(VKOPF_KON.id_user_ansprech_intern, true,new Extent(240), ENUM_USER_TYP.AKTIV)
																._aaa(new actionLoadMitarbeiterDaten(VKOPF_KON.id_user_ansprech_intern,VKOPF_KON.name_ansprech_intern,VKOPF_KON.tel_ansprech_intern,VKOPF_KON.fax_ansprech_intern)));
		this.registerComponent(VKOPF_KON.id_user_sachbearb_intern,new RB_HighLevel_SelectFieldUser(VKOPF_KON.id_user_sachbearb_intern,  true,new Extent(240), ENUM_USER_TYP.AKTIV)
																._aaa(new actionLoadMitarbeiterDaten(VKOPF_KON.id_user_sachbearb_intern,VKOPF_KON.name_sachbearb_intern,VKOPF_KON.tel_sachbearb_intern,VKOPF_KON.fax_sachbearb_intern)));
		this.registerComponent(VKOPF_KON.id_waehrung_fremd,    	new KFIX__SelectField_Waehrung(VKOPF_KON.id_waehrung_fremd, 120));
		this.registerComponent(VKOPF_KON.id_zahlungsbedingungen,  new KFIX_K_M_SF_Zahlungsbedingung(VKOPF_KON.id_zahlungsbedingungen, 240));
		this.registerComponent(VKOPF_KON.ist_fixierung,    		new KFIX_K_M_cb_ist_fixierung(VKOPF_KON.ist_fixierung));
		this.registerComponent(VKOPF_KON.letzter_druck,    		new RB_TextField(100));
		this.registerComponent(VKOPF_KON.lieferadresse_aktiv,    	new RB_CheckBox(VKOPF_KON.lieferadresse_aktiv));
		this.registerComponent(VKOPF_KON.lieferbedingungen,    	new KFIX_K_M_ComboboxErsatz_Lieferbedingungen(VKOPF_KON.lieferbedingungen));
		this.registerComponent(VKOPF_KON.l_hausnummer,    		new RB_TextField(100));
		this.registerComponent(VKOPF_KON.l_laendercode,    		new RB_TextField(40));
		this.registerComponent(VKOPF_KON.l_name1,    				new RB_TextField(400));
		this.registerComponent(VKOPF_KON.l_name2,    				new RB_TextField(400));
		this.registerComponent(VKOPF_KON.l_name3,    				new RB_TextField(400));
		this.registerComponent(VKOPF_KON.l_ort,    				new RB_TextField(400));
		this.registerComponent(VKOPF_KON.l_ortzusatz,    			new RB_TextField(400));
		this.registerComponent(VKOPF_KON.l_plz,    				new RB_TextField(100));
		this.registerComponent(VKOPF_KON.l_strasse,    			new RB_TextField(400));
		this.registerComponent(VKOPF_KON.l_vorname,    			new RB_TextField(400));
		this.registerComponent(VKOPF_KON.name_ansprechpartner,    new RB_TextField(400));
		this.registerComponent(VKOPF_KON.name_ansprech_intern,    new KFIX_K_M_Combobox_user(VKOPF_KON.name_ansprech_intern));
		this.registerComponent(VKOPF_KON.name_bearbeiter_intern,  new KFIX_K_M_Combobox_user(VKOPF_KON.name_bearbeiter_intern));
		this.registerComponent(VKOPF_KON.name_sachbearb_intern,   new KFIX_K_M_Combobox_user(VKOPF_KON.name_sachbearb_intern));
		this.registerComponent(VKOPF_KON.oeffnungszeiten,    		new RB_TextField(400));
		this.registerComponent(VKOPF_KON.skonto_prozent,    		new RB_TextField(30)._fsa(-2)._ttt("Skonto (in %)"));
		this.registerComponent(VKOPF_KON.sprache,    				new RB_TextField(100));
		this.registerComponent(VKOPF_KON.teilzahlung_prozent,    	new RB_TextField());
		this.registerComponent(VKOPF_KON.telefax_auf_formular,    new RB_TextField(400));
		this.registerComponent(VKOPF_KON.telefon_auf_formular,    new RB_TextField(400));
		this.registerComponent(VKOPF_KON.tel_ansprech_intern,    	new RB_TextField(150));
		this.registerComponent(VKOPF_KON.tel_bearbeiter_intern,   new RB_TextField(150));
		this.registerComponent(VKOPF_KON.tel_sachbearb_intern,    new RB_TextField(150));
		this.registerComponent(VKOPF_KON.vorgangsgruppe,    		new RB_TextField());
		this.registerComponent(VKOPF_KON.vorgang_nr,    			new RB_TextField(400));
		this.registerComponent(VKOPF_KON.vorgang_typ,    			new RB_TextField(400));
		this.registerComponent(VKOPF_KON.zaehler_entsperrung,    	new RB_TextField(100));
		this.registerComponent(VKOPF_KON.zahltage,    			new RB_TextField(30)._fsa(-2)._ttt("Tage Zahlungziel"));
		this.registerComponent(VKOPF_KON.zahlungsbedingungen,    	new RB_TextField(300)._fsa(-2));
		this.registerComponent(VKOPF_KON.fix_bis,    				new RB_date_selektor(120,true));
		this.registerComponent(VKOPF_KON.fix_id_artbez,    		new KFIX_K_M_Artikel_SearchField(this, isEk));
		this.registerComponent(VKOPF_KON.fix_id_artikel,    		new RB_TextField(80));
		this.registerComponent(VKOPF_KON.fix_menge_gesamt,    	new RB_TextField(120));
		this.registerComponent(VKOPF_KON.fix_von,    				new RB_date_selektor(120,true));
		this.registerComponent(VKOPF_KON.bemerkung_fix1,    		new RB_TextArea(560,8));
		this.registerComponent(VKOPF_KON.kopie_bemerkung_auf_pos, new RB_CheckBox(VKOPF_KON.kopie_bemerkung_auf_pos));
		this.registerComponent(VKOPF_KON.typ_25_to,			 	new RB_CheckBox(VKOPF_KON.typ_25_to));
		this.registerComponent(VKOPF_KON.typ_ladung,			 	new RB_CheckBox(VKOPF_KON.typ_ladung));
		this.registerComponent(VKOPF_KON.boerse_diff_proz,		new RB_TextField(120));
		this.registerComponent(VKOPF_KON.boerse_diff_abs,			new RB_TextField(120));
		this.registerComponent(VKOPF_KON.fixnummer_eigen, 		new RB_TextField(120));
		this.registerComponent(VKOPF_KON.fixnummer_fremd, 		new RB_TextField(120));
		
		this.registerComponent( KFIX___CONST.ADDITIONNAL_COMP_KOPF.MASK_ANSPRECHPARTNER_BT.key() , 			new KFIX_K_M_BT_Select_Fremd_Ansprechpartner());

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_ANR1.key(),								new RB_TextField(50));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_ANR2.key(),								new RB_TextField(50));

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.DAUGHTERTABLE_PRINTPROTOKOLL.key(),			new KFIX_K_M_masklist_printprotokoll());

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT.key(), 						new RB_TextAnzeige(120));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT_RESTMENGE.key(), 				new RB_TextAnzeige(120));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT.key(), 							new RB_TextAnzeige(120));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT_RESTMENGE.key(), 				new RB_TextAnzeige(120));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.CALC_RESTMENGE.key(), 						new KFIX_K_M_BT_RestMenge_Rechnung());

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.HASH_KEY_BT_SET_ACTUAL_MONTH.key(), 		new KFIX__BT_Set_Datum_Range(0, VKOPF_KON.fix_von, VKOPF_KON.fix_bis));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.HASH_KEY_BT_SET_ACTUAL_MONTH_PLUS_ONE.key(),new KFIX__BT_Set_Datum_Range(1, VKOPF_KON.fix_von, VKOPF_KON.fix_bis));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.HASH_KEY_BT_SET_ACTUAL_MONTH_PLUS_TWO.key(),new KFIX__BT_Set_Datum_Range(2, VKOPF_KON.fix_von, VKOPF_KON.fix_bis));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.HASH_KEY_LAB_EINHEIT.key(),					new KFIX_K_M_LAB_einheit());

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_KOPF.DAUGHTERTABLE_POSITION.key(), 				new KFIX_K_M_masklist_position(this));
		
	
		
		this.registerSetterValidators(new RB_KF(VKOPF_KON.fix_menge_gesamt), 	new KFIX_P_M_SetAndValid_fixierungsmenge());
		this.registerSetterValidators(new RB_KF(VKOPF_KON.fix_id_artikel), 	new KFIX_K_M_SetAndValid_einheit());
	}

	public void setFixierungKontrakte(boolean isFixiert){
		this.isFixiert = isFixiert;
	}

	public boolean isFixiert() {
		return isFixiert;
	}

	public boolean is_ek_kontrakt() {
		return isEk;
	}

	@Override
	public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
		MyE2_MessageVector omv = new MyE2_MessageVector();



		return omv;
	}

	@Override
	public MyE2_MessageVector maskSettings_do_After_Load() throws myException {

		MyE2_MessageVector omv = new MyE2_MessageVector();

		String id = bibALL.convertID2UnformattedID(((RB_TextField)this.getComp(VKOPF_KON.id_vkopf_kon)).rb_readValue_4_dataobject());//.getText());

		if(S.isFull(id)){

			Rec20_VKOPF_KON recVkopf = new Rec20_VKOPF_KON(_TAB.vkopf_kon)._fill_id(id);

			boolean isFix = recVkopf.is_fixierungsKontrakte();

			if(isFix){
				((RB_TextField)this.get__Comp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_ANR1.name())).setText(recVkopf.get_anr1());
				((RB_TextField)this.get__Comp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_ANR2.name())).setText(recVkopf.get_anr2());

				((RB_TextAnzeige)this.get__Comp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT.name())).rb_set_db_value_manual(recVkopf.get_aktuelle_fixiert_menge().get_FormatedRoundedNumber(3));

				((RB_TextAnzeige)this.get__Comp(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT.name())).rb_set_db_value_manual(recVkopf.get_gesamt_fuhre_menge(isEk).get_FormatedRoundedNumber(3));
			}
		}

		((RB_CheckBox)this.getComp(VKOPF_KON.typ_25_to))._aaa(new own_single_checkbox_active(VKOPF_KON.typ_25_to, VKOPF_KON.typ_ladung));
		((RB_CheckBox)this.getComp(VKOPF_KON.typ_ladung))._aaa(new own_single_checkbox_active(VKOPF_KON.typ_ladung, VKOPF_KON.typ_25_to));

		new KFIX_K___calculate_rests(this,false);

		return omv;
	}

	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
	}

	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,
			MASK_STATUS status) throws myException {

		//blockiert feld
	
		preSettingsContainer.rb_get(VKOPF_KON.buchungsnummer).set_Enabled(false);
		preSettingsContainer.rb_get(VKOPF_KON.abgeschlossen).set_Enabled(false);
		preSettingsContainer.rb_get(VKOPF_KON.zaehler_entsperrung).set_Enabled(false);
		preSettingsContainer.rb_get(VKOPF_KON.fix_id_artikel).set_Enabled(false);
		preSettingsContainer.rb_get(VKOPF_KON.ist_fixierung).set_Enabled(false);
		preSettingsContainer.rb_get(VKOPF_KON.skonto_prozent).set_Enabled(false);
		preSettingsContainer.rb_get(VKOPF_KON.fixtag).set_Enabled(false);
		preSettingsContainer.rb_get(VKOPF_KON.fixmonat).set_Enabled(false);
		preSettingsContainer.rb_get(VKOPF_KON.zahltage).set_Enabled(false);
		preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_ANR1.key()).set_Enabled(false);
		preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_ANR2.key()).set_Enabled(false);
		preSettingsContainer.rb_set_defaultMaskValue(VKOPF_KON.ist_fixierung, isFixiert?"Y":"N");

		preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT.key()).set_Enabled(false);
		preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT.key()).set_Enabled(false);
		preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_GELIEFERT_RESTMENGE.key()).set_Enabled(false);
		preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_KOPF.TA_FIXIERT_RESTMENGE.key()).set_Enabled(false);

		preSettingsContainer.rb_get(VKOPF_KON.typ_25_to).set_MustField(false);
		preSettingsContainer.rb_get(VKOPF_KON.typ_ladung).set_MustField(false);

		if(status == MASK_STATUS.NEW){
			preSettingsContainer.rb_get(VKOPF_KON.id_waehrung_fremd).set_Enabled(true);
			preSettingsContainer.rb_get(VKOPF_KON.id_waehrung_fremd).set_MustField(true);
			preSettingsContainer.rb_get(VKOPF_KON.id_adresse).set_MustField(true);
			preSettingsContainer.rb_get(VKOPF_KON.name1).set_MustField(true);
			preSettingsContainer.rb_get(VKOPF_KON.plz).set_MustField(true);
			preSettingsContainer.rb_get(VKOPF_KON.ort).set_MustField(true);
			preSettingsContainer.rb_get(VKOPF_KON.zahlungsbedingungen).set_MustField(true);	

			//default value
			preSettingsContainer.rb_get(VKOPF_KON.erstellungsdatum).set_rb_Default(bibALL.get_cDateNOW());
			preSettingsContainer.rb_get(VKOPF_KON.id_waehrung_fremd).set_rb_Default("");
			preSettingsContainer.rb_get(VKOPF_KON.id_user).set_rb_Default(bibALL.convertID2FormattedID(bibALL.get_ID_USER()));
			preSettingsContainer.rb_get(VKOPF_KON.name_bearbeiter_intern).set_rb_Default(bibALL.get_RECORD_USER().get___KETTE(USER.vorname, USER.name1));


			if(isFixiert){
				preSettingsContainer.rb_get(VKOPF_KON.fix_menge_gesamt).set_MustField(true);
				preSettingsContainer.rb_get(VKOPF_KON.fix_von).set_MustField(true);
				preSettingsContainer.rb_get(VKOPF_KON.fix_bis).set_MustField(true);
				preSettingsContainer.rb_get(VKOPF_KON.fix_id_artikel).set_MustField(true);
				preSettingsContainer.rb_get(VKOPF_KON.fix_id_artbez).set_MustField(true);
				preSettingsContainer.rb_get(VKOPF_KON.fix_id_artbez).set_Enabled(true);
				preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_KOPF.CALC_RESTMENGE.key()).set_Enabled(false);
				preSettingsContainer.rb_get(VKOPF_KON.typ_25_to).set_MustField(false);
				preSettingsContainer.rb_get(VKOPF_KON.typ_ladung).set_MustField(false);

			}
		}

		if (status == MASK_STATUS.EDIT){
			
			Rec20_VKOPF_KON recExt = new Rec20_VKOPF_KON(_TAB.vkopf_kon)._fill_id(((RB_TextField)this.getComp(VKOPF_KON.id_vkopf_kon)).rb_readValue_4_dataobject());
			
			if (recExt.hasPositionUndeleted()) {
				preSettingsContainer.rb_get(VKOPF_KON.id_waehrung_fremd).set_Enabled(false);
			}
			preSettingsContainer.rb_get(VKOPF_KON.id_adresse).set_MustField(true);
			preSettingsContainer.rb_get(VKOPF_KON.name1).set_MustField(true);
			preSettingsContainer.rb_get(VKOPF_KON.plz).set_MustField(true);
			preSettingsContainer.rb_get(VKOPF_KON.ort).set_MustField(true);
//			preSettingsContainer.rb_get(VKOPF_KON.zahlungsbedingungen).set_MustField(true);	
			
			
			if(isFixiert){
				preSettingsContainer.rb_get(VKOPF_KON.fix_von).set_MustField(true);
				preSettingsContainer.rb_get(VKOPF_KON.fix_bis).set_MustField(true);
				preSettingsContainer.rb_get(VKOPF_KON.fix_id_artikel).set_MustField(true);
				preSettingsContainer.rb_get(VKOPF_KON.fix_id_artbez).set_MustField(true);
				preSettingsContainer.rb_get(VKOPF_KON.fix_id_artbez).set_Enabled(true);

				preSettingsContainer.rb_get(VKOPF_KON.typ_25_to).set_MustField(false);
				preSettingsContainer.rb_get(VKOPF_KON.typ_ladung).set_MustField(false);
				preSettingsContainer.rb_get(VKOPF_KON.fix_menge_gesamt).set_MustField(true);
			}

			if(! recExt.hasPosition()){
				preSettingsContainer.rb_get(VKOPF_KON.fix_id_artbez).set_Enabled(true);
			}else{
				preSettingsContainer.rb_get(VKOPF_KON.fix_id_artbez).set_Enabled(false);
			}


		}

		if (status == MASK_STATUS.EDIT || status == MASK_STATUS.VIEW){
			Rec20_VKOPF_KON recExt = new Rec20_VKOPF_KON(_TAB.vkopf_kon)._fill_id(((RB_TextField)this.getComp(VKOPF_KON.id_vkopf_kon)).rb_readValue_4_dataobject());

			preSettingsContainer.rb_set_defaultMaskValue(VKOPF_KON.druckzaehler, Integer.toString(recExt.getDruckZaehler()));
		}

		return null;
	}

	private class own_single_checkbox_active extends XX_ActionAgent{
		private IF_Field field1, field2;
		public own_single_checkbox_active(IF_Field field1, IF_Field field2) {
			super();
			this.field1 = field1;
			this.field2 = field2;
		}

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			RB_CheckBox cb = (RB_CheckBox)oExecInfo.get_MyActionEvent().getSource();
			if(cb.rb_KF().get_HASHKEY().equals(this.field1.fieldName()) && cb.isSelected()){	
				((RB_CheckBox)KFIX_K_M_ComponentMap.this.getComp(this.field2)).rb_set_db_value_manual(cb.isSelected()?"N":"Y");
			}
		}
	}
	
	
	
	private  class actionLoadMitarbeiterDaten extends XX_ActionAgent {
		private IF_Field id_field = null;
		private IF_Field FELD_USERNAME =  null;
		private IF_Field FELD_TELEFON =  null;
		private IF_Field FELD_TELEFAX =  null;
		
		public actionLoadMitarbeiterDaten(IF_Field p_id_field, IF_Field fELD_USERNAME,	IF_Field fELD_TELEFON, IF_Field fELD_TELEFAX)	{	
			super();
			this.id_field = p_id_field;
			this.FELD_USERNAME = fELD_USERNAME;
			this.FELD_TELEFON = fELD_TELEFON;
			this.FELD_TELEFAX = fELD_TELEFAX;
		}


		public void executeAgentCode(ExecINFO oExecInfo) {
			try 	{
				
				KFIX_K_M_Controller controller = new KFIX_K_M_Controller(KFIX_K_M_ComponentMap.this.rb_get_belongs_to());
				
				MyLong l_id_user = controller.get_MyLong_liveVal(_TAB.vkopf_kon.rb_km(), this.id_field);
				
				if (l_id_user!=null && l_id_user.isOK()) {
					RECORD_USER oUser = new RECORD_USER(l_id_user.get_lValue());
					controller.set_maskVal(_TAB.vkopf_kon.rb_km(), this.FELD_USERNAME, oUser.get___KETTE(bibALL.get_Vector("VORNAME", "NAME1"), "", "", "", " "), bibMSG.MV());
					controller.set_maskVal(_TAB.vkopf_kon.rb_km(), this.FELD_TELEFON, oUser.get_TELEFON_cUF_NN(""), bibMSG.MV());
					controller.set_maskVal(_TAB.vkopf_kon.rb_km(), this.FELD_TELEFAX, oUser.get_TELEFAX_cUF_NN(""), bibMSG.MV());
				} else {
					controller.set_maskVal(_TAB.vkopf_kon.rb_km(), this.FELD_USERNAME, "", bibMSG.MV());
					controller.set_maskVal(_TAB.vkopf_kon.rb_km(), this.FELD_TELEFON, "", bibMSG.MV());
					controller.set_maskVal(_TAB.vkopf_kon.rb_km(), this.FELD_TELEFAX, "", bibMSG.MV());
				}
			} catch (myException ex) {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Laden des Benutzernamens !! "));
				bibMSG.add_MESSAGE(ex.get_ErrorMessage());
			}
		} 
	}

	
}

