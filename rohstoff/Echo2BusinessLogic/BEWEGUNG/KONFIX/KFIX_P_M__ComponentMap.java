
package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Message;
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
import panter.gmbh.Echo2.RB.COMP.RB_cb;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.components.MyE2_CheckBox;
import panter.gmbh.Echo2.components.E2_calendar.E2_TF_4_Date;
import panter.gmbh.basics4project.myCONST;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON;
import panter.gmbh.basics4project.DB_ENUMS.WAEHRUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.MyBigDecimal;
import panter.gmbh.indep.MyNumberFormater;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.BS_PreisCalculator;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX_P_M__ComponentMap extends RB_ComponentMap {

	private Rec20_VKOPF_KON rec_vkopf;

	private String belegTyp;

	public KFIX_P_M__ComponentMap(String belegTyp, Rec20 rec20_vkopf) throws myException {

		super();

		this.rec_vkopf = new Rec20_VKOPF_KON(rec20_vkopf);
		this.belegTyp = belegTyp;

		MyE2_CheckBox kopie_bemerkung_auf_pos_chkBox = new MyE2_CheckBox("Bemerkung auf Position übernehmen");
		kopie_bemerkung_auf_pos_chkBox.setStyle(MyE2_CheckBox.STYLE_NORMAL_NO_BORDER());

		KFIX__SelectField_Waehrung waehrungSelectField = new KFIX__SelectField_Waehrung(VPOS_KON.id_waehrung_fremd,100);

		this.registerComponent(VPOS_KON.anr1,    						new RB_TextField(80));
		this.registerComponent(VPOS_KON.anr2,    						new RB_TextField(50));
		this.registerComponent(VPOS_KON.anzahl,    					new RB_TextField(100));
		this.registerComponent(VPOS_KON.anzahl_abzug,    				new RB_TextField());
		this.registerComponent(VPOS_KON.artbez1,    					new RB_TextField(400));
		this.registerComponent(VPOS_KON.artbez2,    					new RB_TextArea(400,7));
		this.registerComponent(VPOS_KON.ausfuehrungsdatum,    		new RB_TextField(100));
		this.registerComponent(VPOS_KON.bemerkung_intern,    			new RB_TextArea(400,6));
		this.registerComponent(VPOS_KON.bestellnummer,    			new RB_TextField(250));
		this.registerComponent(VPOS_KON.deleted,    					new RB_CheckBox(VPOS_KON.deleted));
		this.registerComponent(VPOS_KON.del_date,    					new RB_TextField(100));
		this.registerComponent(VPOS_KON.del_grund,    				new RB_TextField(400));
		this.registerComponent(VPOS_KON.del_kuerzel,    				new RB_TextField(100));
		this.registerComponent(VPOS_KON.einheitkurz,    				new RB_TextField(40));
		this.registerComponent(VPOS_KON.einheit_preis_kurz,    		new RB_TextField(40));
		this.registerComponent(VPOS_KON.einzelpreis,    				new RB_TextField(100));
		this.registerComponent(VPOS_KON.einzelpreis_abzug,    		new RB_TextField());
		this.registerComponent(VPOS_KON.einzelpreis_abzug_fw,    		new RB_TextField());
		this.registerComponent(VPOS_KON.einzelpreis_fw,    			new RB_TextField(100));
		this.registerComponent(VPOS_KON.einzelpreis_result,    		new RB_TextField());
		this.registerComponent(VPOS_KON.einzelpreis_result_fw,    	new RB_TextField());
		this.registerComponent(VPOS_KON.eucode,    					new RB_TextField(400));
		this.registerComponent(VPOS_KON.eunotiz,    					new RB_TextArea(400,5));
		this.registerComponent(VPOS_KON.fixmonat,    					new RB_TextField(30)._fsa(-2)._ttt("Fixmonat"));
		this.registerComponent(VPOS_KON.fixtag,   					new RB_TextField(30)._fsa(-2)._ttt("Fixtage"));
		this.registerComponent(VPOS_KON.gebucht,    					new RB_CheckBox(VPOS_KON.gebucht));
		this.registerComponent(VPOS_KON.gesamtpreis,    				new RB_TextField(120));
		this.registerComponent(VPOS_KON.gesamtpreis_abzug,    		new RB_TextField());
		this.registerComponent(VPOS_KON.gesamtpreis_abzug_fw,    		new RB_TextField());
		this.registerComponent(VPOS_KON.gesamtpreis_fw,    			new RB_TextField(120));
		this.registerComponent(VPOS_KON.id_adresse,    				new RB_TextField());
		this.registerComponent(VPOS_KON.id_adresse_lager,    			new RB_TextField());
		this.registerComponent(VPOS_KON.id_adresse_lager_start,   	new RB_TextField());
		this.registerComponent(VPOS_KON.id_adresse_lager_ziel,    	new RB_TextField());
		this.registerComponent(VPOS_KON.id_artikel,    				new RB_TextField(80));
		this.registerComponent(VPOS_KON.id_artikel_bez,    			new KFIX_K_M_Artikel_SearchField(this, belegTyp.equals(myCONST.VORGANGSART_EK_KONTRAKT)?true:false));
		this.registerComponent(VPOS_KON.id_strecken_def,    			new RB_TextField());
		this.registerComponent(VPOS_KON.id_vkopf_kon,    				new RB_TextField(80));
		this.registerComponent(VPOS_KON.id_vpos_kon,    				new RB_TextField(80));
		this.registerComponent(VPOS_KON.id_vpos_kon_zugeord,    		new RB_TextField());
		this.registerComponent(VPOS_KON.id_vpos_tpa_fuhre_zugeord,    new RB_TextField());
		this.registerComponent(VPOS_KON.id_waehrung_fremd,    		waehrungSelectField);
		this.registerComponent(VPOS_KON.id_zahlungsbedingungen,    	new KFIX_P_M_Zahlungsbedingung_SelectField(VPOS_KON.id_zahlungsbedingungen, 250));
		this.registerComponent(VPOS_KON.lager_vorzeichen,    			new RB_TextField());
		this.registerComponent(VPOS_KON.lieferbedingungen,    		new KFIX_K_M_ComboboxErsatz_Lieferbedingungen(VPOS_KON.lieferbedingungen,400,3));
		this.registerComponent(VPOS_KON.mengendivisor,    			new RB_TextField(40));
		this.registerComponent(VPOS_KON.mengen_toleranz_prozent,    	new RB_TextField(30));
		this.registerComponent(VPOS_KON.positionsklasse,    			new RB_TextField(400));
		this.registerComponent(VPOS_KON.positionsnummer,    			new RB_TextField(50));
		this.registerComponent(VPOS_KON.position_active,    			new RB_CheckBox(VPOS_KON.position_active));
		this.registerComponent(VPOS_KON.position_typ,    				new KFIX_P_M_SelectField_PositionTyp(VPOS_KON.position_typ));
		this.registerComponent(VPOS_KON.skonto_prozent,    			new RB_TextField(30)._fsa(-2)._ttt("Skonto (in %)"));
		this.registerComponent(VPOS_KON.steuersatz,    				new KFIX_P_M_Combobox_Steuersatz(VPOS_KON.steuersatz));
		this.registerComponent(VPOS_KON.vorgang_typ,    				new RB_TextField());
		this.registerComponent(VPOS_KON.waehrungskurs,    			new RB_TextField(80));
		this.registerComponent(VPOS_KON.wiegekartenkenner,    		new RB_TextField(100));
		this.registerComponent(VPOS_KON.zahltage,    					new RB_TextField(30)._fsa(-2)._ttt("Tage zahlungsziel"));
		this.registerComponent(VPOS_KON.zahlungsbedingungen,   		new RB_TextField(300)._fsa(-2));
		this.registerComponent(VPOS_KON.zahlungsbed_calc_datum,    	new RB_TextField(100));
		this.registerComponent(VPOS_KON.zolltarifnr,    				new RB_TextField(400));
		this.registerComponent(VPOS_KON.typ_25_to,			 		new RB_CheckBox(VPOS_KON.typ_25_to, new MyE2_String("25 Tonnen"), null));
		this.registerComponent(VPOS_KON.typ_ladung,			 		new RB_CheckBox(VPOS_KON.typ_ladung, new MyE2_String("Lademenge"), null));

		this.registerComponent(VPOS_KON.fixierungstag, 				new RB_date_selektor(120,true));
		this.registerComponent(VPOS_KON.preis_manuell, 				new RB_cb()._aaa(new own_manuell_preis_chkbox_aa()));
		this.registerComponent(VPOS_KON.wechselkurs, 					new RB_TextField(120));
		this.registerComponent(VPOS_KON.boersenkurs, 					new RB_TextField(120));
		this.registerComponent(VPOS_KON.boerse_diff_proz, 			new RB_TextField(120));
		this.registerComponent(VPOS_KON.boerse_diff_abs, 				new RB_TextField(120));


		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_BUCHUNGNR.key(), 		new RB_lab()._ttt("Buchungsnummer"));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_BUCHUNGPOSNR.key(), 	new RB_lab()._ttt("Position"));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_FIRMA.key(), 			new RB_lab()._ttt("Firma")._line_wrap(true));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_MENGE.key(), 			new RB_lab()._ttt("Menge"));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_PREIS.key(), 			new RB_lab()._ttt("Preis"));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_SORTE.key(), 			new RB_lab()._ttt("Sorte"));

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_BT_RECHNEN.key(), 			new KFIX_P_M_BT_Rechner(this));

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_BT_BERECHNUNG.key(),  		
				this.rec_vkopf.is_fixierungsKontrakte()? new KFIX_P_M_BT_Neuberechnung_fur_fixierung(this): new KFIX_P_M_BT_Neuberechnung(this)
				);

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_BT_KURS_HOLEN.key(),  		new KFIX_P_M_BT_Kurs_holen(this));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_GRID_INFO.key(), 			new KFIX_P_M_Grid_PosInformation());

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_LIEFERUNG_LIST.key(), 		new KFIX_P_M_masklist_lieferung_zum_lager());

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_LIEFERTERMINE.key(), 		new KFIX_P_M_masklist_liefertermine());

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_AENDERUNG.key(), 			new KFIX_P_M_masklist_aenderung());

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_KOPF_FIX_VON.key(), 			new E2_TF_4_Date("", 80, true));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_KOPF_FIX_BIS.key(), 			new E2_TF_4_Date("", 80, true));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_KOPF_FIX_GESAMTMENGE.key(), 	new RB_TextAnzeige(80));

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.CALC_RESTMENGE.key(),				new KFIX_P_M_BT_RestMenge_Rechnung());
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.TA_GELIEFERT.key(),					new RB_TextAnzeige(80));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.TA_GELIEFERT_RESTMENGE.key(),		new RB_TextAnzeige(80));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.TA_FIXIERT.key(),					new RB_TextAnzeige(80));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.TA_FIXIERT_RESTMENGE.key(),			new RB_TextAnzeige(80));

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_AUTOFILL_ANZAHL_BT.key(), 	new KFIX_P_M_BT_autofill_anzahl(true));

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.LBL_EINHEIT.key(), 					new KFIX__LBL_einheit());
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.LBL_EINHEIT_2.key(), 				new KFIX__LBL_einheit());

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.LBL_WAEHRUNG.key(), 					new KFIX__LBL_waehrung());
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.LBL_WAEHRUNG_2.key(), 				new KFIX__LBL_waehrung());

		this.registerSetterValidators(new RB_KF(VPOS_KON.anzahl), 							new KFIX_P_M_SetAndValid_Anzahl());
		this.registerSetterValidators(new RB_KF(VPOS_KON.anzahl),							new KFIX_P_M__SetAndValid_Menge_Pruefung());
		this.registerSetterValidators(new RB_KF(VPOS_KON.typ_ladung), 						new KFIX_P_M_SetAndValid_ladung_schalter());

		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_AVV_CODE_LIST.key(), 		new KFIX_P_M_masklist_AVV_code_list());
	}

	@Override
	public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
		return null;
	}

	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
	}

	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer, MASK_STATUS status) throws myException {

		preSettingsContainer.rb_get(VPOS_KON.id_vkopf_kon)													.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.id_vpos_kon)													.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.position_typ)													.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.anr1)															.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.anr2)															.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.id_artikel)													.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.id_waehrung_fremd)												.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.gesamtpreis)													.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.gesamtpreis_fw)												.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.einzelpreis_fw)												.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.einheitkurz)													.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.mengendivisor)													.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.einheit_preis_kurz)											.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.fixtag)														.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.fixmonat)														.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.skonto_prozent)												.set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON.zahltage)														.set_Enabled(false);

		preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_KOPF_FIX_GESAMTMENGE.key())	.set_Enabled(false);
		preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_POS.TA_GELIEFERT_RESTMENGE.key())			.set_Enabled(false);
		preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_POS.TA_FIXIERT_RESTMENGE.key())			.set_Enabled(false);
		preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_POS.TA_GELIEFERT.key())					.set_Enabled(false);
		preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_POS.TA_FIXIERT.key())						.set_Enabled(false);

		preSettingsContainer.rb_get(VPOS_KON.id_artikel)													.set_Enabled(false);

		if(rec_vkopf.is_fixierungsKontrakte()){
//			((KFIX_P_M_Grid_PosInformation)this.rb_Comp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_GRID_INFO_FIX.key())).setValues(rec_vkopf);

//			preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_GRID_INFO.key())		.set_Enabled(true);
			preSettingsContainer.rb_get(VPOS_KON.id_artikel_bez)											.set_Enabled(false);

		}else{
			preSettingsContainer.rb_get(VPOS_KON.id_artikel_bez)											.set_Enabled(true);
//			preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_GRID_INFO.key())		.set_Enabled(false);
			preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_AUTOFILL_ANZAHL_BT.key())	.set_Enabled(false);
		}

		if(status == MASK_STATUS.NEW){

			int maxPosNr = rec_vkopf.getMaxPositionNummer();

			preSettingsContainer.rb_get(VPOS_KON.positionsnummer)			.set_rb_Default("" + maxPosNr);
			preSettingsContainer.rb_get(VPOS_KON.waehrungskurs)				.set_rb_Default("1");
			preSettingsContainer.rb_get(VPOS_KON.position_typ)				.set_rb_Default(myCONST.VG_POSITION_TYP_ARTIKEL);
			preSettingsContainer.rb_get(VPOS_KON.id_vkopf_kon)				.set_rb_Default(this.rec_vkopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon));
			preSettingsContainer.rb_get(VPOS_KON.id_waehrung_fremd)			.set_rb_Default(this.rec_vkopf.get_fs_dbVal(VKOPF_KON.id_waehrung_fremd,""));
			preSettingsContainer.rb_get(VPOS_KON.lieferbedingungen)			.set_rb_Default(this.rec_vkopf.get_fs_dbVal(VKOPF_KON.lieferbedingungen,""));
			preSettingsContainer.rb_get(VPOS_KON.id_zahlungsbedingungen)	.set_rb_Default(this.rec_vkopf.get_fs_dbVal(VKOPF_KON.id_zahlungsbedingungen,""));
			preSettingsContainer.rb_get(VPOS_KON.zahlungsbedingungen)		.set_rb_Default(this.rec_vkopf.get_fs_dbVal(VKOPF_KON.zahlungsbedingungen,""));
			preSettingsContainer.rb_get(VPOS_KON.zahltage)					.set_rb_Default(this.rec_vkopf.get_fs_dbVal(VKOPF_KON.zahltage,""));
			preSettingsContainer.rb_get(VPOS_KON.fixtag)					.set_rb_Default(this.rec_vkopf.get_fs_dbVal(VKOPF_KON.fixtag,""));
			preSettingsContainer.rb_get(VPOS_KON.fixmonat)					.set_rb_Default(this.rec_vkopf.get_fs_dbVal(VKOPF_KON.fixmonat,""));
			preSettingsContainer.rb_get(VPOS_KON.skonto_prozent)			.set_rb_Default(this.rec_vkopf.get_fs_dbVal(VKOPF_KON.skonto_prozent,""));
		//	preSettingsContainer.rb_get(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_GRID_INFO.key()).set_rb_Default(rec_vkopf.get_ufs_dbVal(VKOPF_KON.id_vkopf_kon));

			if(rec_vkopf.is_fixierungsKontrakte()){

				preSettingsContainer.rb_get(VPOS_KON.id_artikel)			.set_rb_Default(this.rec_vkopf.get_fs_dbVal(VKOPF_KON.fix_id_artikel));
				preSettingsContainer.rb_get(VPOS_KON.id_artikel_bez)		.set_rb_Default(this.rec_vkopf.get_fs_dbVal(VKOPF_KON.fix_id_artbez));
				preSettingsContainer.rb_get(VPOS_KON.anr1)					.set_rb_Default(this.rec_vkopf.get_anr1());
				preSettingsContainer.rb_get(VPOS_KON.anr2)					.set_rb_Default(this.rec_vkopf.get_anr2());
				preSettingsContainer.rb_get(VPOS_KON.artbez1)				.set_rb_Default(this.rec_vkopf.get_artbez1());
				preSettingsContainer.rb_get(VPOS_KON.artbez2)				.set_rb_Default(this.rec_vkopf.get_artbez2());
				preSettingsContainer.rb_get(VPOS_KON.mengendivisor)			.set_rb_Default(this.rec_vkopf.getMengeDivisor().get_FormatedRoundedNumber(0));
				preSettingsContainer.rb_get(VPOS_KON.einheit_preis_kurz)	.set_rb_Default(this.rec_vkopf.get_preis_einheit());
				preSettingsContainer.rb_get(VPOS_KON.einheitkurz)			.set_rb_Default(this.rec_vkopf.get_einheit());
				preSettingsContainer.rb_get(VPOS_KON.boerse_diff_abs)		.set_rb_Default(this.rec_vkopf.get_fs_dbVal(VKOPF_KON.boerse_diff_abs));
				preSettingsContainer.rb_get(VPOS_KON.boerse_diff_proz)		.set_rb_Default(this.rec_vkopf.get_fs_dbVal(VKOPF_KON.boerse_diff_proz));

				preSettingsContainer.rb_get(VPOS_KON.fixierungstag)			.set_rb_Default(bibALL.get_cDateNOW());

				preSettingsContainer.rb_get(VPOS_KON.boersenkurs)			.set_MustField(false);
				preSettingsContainer.rb_get(VPOS_KON.fixierungstag)			.set_MustField(false);
				preSettingsContainer.rb_get(VPOS_KON.wechselkurs)			.set_MustField(false);

				preSettingsContainer.rb_get(VPOS_KON.preis_manuell)			.set_rb_Default("N");
				preSettingsContainer.rb_get(VPOS_KON.einzelpreis)			.set_Enabled(false);

			}

			String efd = (belegTyp.equals(myCONST.VORGANGSART_EK_KONTRAKT))?"-1":"1";

			preSettingsContainer.rb_get(VPOS_KON.lager_vorzeichen).set_rb_Default(efd);
		}

		if(status == MASK_STATUS.EDIT){
			if(rec_vkopf.isAbgeschlossen()){

				preSettingsContainer.rb_get(VPOS_KON.artbez1)				.set_Enabled(false);
				preSettingsContainer.rb_get(VPOS_KON.artbez2)				.set_Enabled(false);
				preSettingsContainer.rb_get(VPOS_KON.lieferbedingungen)		.set_Enabled(false);
				preSettingsContainer.rb_get(VPOS_KON.steuersatz)			.set_Enabled(false);
				preSettingsContainer.rb_get(VPOS_KON.positionsnummer)		.set_Enabled(false);
				preSettingsContainer.rb_get(VPOS_KON.id_zahlungsbedingungen).set_Enabled(false);
				preSettingsContainer.rb_get(VPOS_KON.zahlungsbedingungen)	.set_Enabled(false);
				preSettingsContainer.rb_get(VPOS_KON.waehrungskurs)			.set_Enabled(false);

				preSettingsContainer.rb_get(VPOS_KON.waehrungskurs)			.set_MustField(false);
				preSettingsContainer.rb_get(VPOS_KON.id_waehrung_fremd)		.set_MustField(false);
				preSettingsContainer.rb_get(VPOS_KON.positionsnummer)		.set_MustField(false);
				preSettingsContainer.rb_get(VPOS_KON.mengendivisor)			.set_MustField(false);
				preSettingsContainer.rb_get(VPOS_KON.id_zahlungsbedingungen).set_MustField(false);
				preSettingsContainer.rb_get(VPOS_KON.artbez1)				.set_MustField(false);

			}
			if(rec_vkopf.is_fixierungsKontrakte()){
				boolean is_preis_manuell = _find_component_in_neighborhood(VPOS_KON.preis_manuell).get__actual_maskstring_in_view().equals("Y")?true:false;

				preSettingsContainer.rb_get(VPOS_KON.anzahl)				.set_Enabled(true);

				if(is_preis_manuell){
					preSettingsContainer.rb_get(VPOS_KON.fixierungstag)		.set_Enabled(false);
					preSettingsContainer.rb_get(VPOS_KON.boersenkurs)		.set_Enabled(false);
					preSettingsContainer.rb_get(VPOS_KON.wechselkurs)		.set_Enabled(false);
					preSettingsContainer.rb_get(VPOS_KON.boerse_diff_proz)	.set_Enabled(false);
					preSettingsContainer.rb_get(VPOS_KON.boerse_diff_abs)	.set_Enabled(false);

					preSettingsContainer.rb_get(VPOS_KON.fixierungstag)		.set_MustField(false);
					preSettingsContainer.rb_get(VPOS_KON.boersenkurs)		.set_MustField(false);
					preSettingsContainer.rb_get(VPOS_KON.wechselkurs)		.set_MustField(false);
				}else{
					preSettingsContainer.rb_get(VPOS_KON.fixierungstag)		.set_MustField(false);
					preSettingsContainer.rb_get(VPOS_KON.boersenkurs)		.set_MustField(false);
					preSettingsContainer.rb_get(VPOS_KON.wechselkurs)		.set_MustField(false);
				}
			}
		}

		if(status == MASK_STATUS.VIEW){
			preSettingsContainer.rb_get(VPOS_KON.id_artikel_bez)			.set_Enabled(false);

			preSettingsContainer.rb_get(VPOS_KON.artbez1)					.set_MustField(false);
			preSettingsContainer.rb_get(VPOS_KON.id_waehrung_fremd)			.set_MustField(false);
			preSettingsContainer.rb_get(VPOS_KON.positionsnummer)			.set_MustField(false);
			preSettingsContainer.rb_get(VPOS_KON.id_zahlungsbedingungen)	.set_MustField(false);
			preSettingsContainer.rb_get(VPOS_KON.mengendivisor)				.set_MustField(false);

		}

		if(status == MASK_STATUS.NEW || status == MASK_STATUS.EDIT){
			preSettingsContainer.rb_get(VPOS_KON.positionsnummer)			.set_MustField(true);
			preSettingsContainer.rb_get(VPOS_KON.waehrungskurs)				.set_MustField(true);
			preSettingsContainer.rb_get(VPOS_KON.id_zahlungsbedingungen)	.set_MustField(true);
			preSettingsContainer.rb_get(VPOS_KON.artbez1)					.set_MustField(true);
		} 

		return null;
	}

	@Override
	public MyE2_MessageVector maskSettings_do_After_Load() throws myException {

		boolean isEK = belegTyp.equals(myCONST.VORGANGSART_EK_KONTRAKT)?true:false;

		MASK_STATUS actual_status = this.getRbDataObjectActual().rb_MASK_STATUS();

		String anzahl =				this.getRbComponentSavable(VPOS_KON.anzahl).rb_readValue_4_dataobject();
		String einzelpreis =		this.getRbComponentSavable(VPOS_KON.einzelpreis).rb_readValue_4_dataobject();

		boolean is_einzelpreis_full = !einzelpreis.isEmpty();
		boolean is_anzahl_full 		= !anzahl.isEmpty();
		String waehrung				= "";
		String einheit 				= "";
		String anr12 				= this.getRbComponentSavable(VPOS_KON.artbez1).rb_readValue_4_dataobject() + 
					" (" + this.getRbComponentSavable(VPOS_KON.anr1).rb_readValue_4_dataobject() + "/" +this.getRbComponentSavable(VPOS_KON.anr2).rb_readValue_4_dataobject()+")";

		this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_FIRMA.key(),	this.rec_vkopf.get_adresse_for_info());
		this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_SORTE.key(), 	anr12);
		this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_BUCHUNGNR.key(),this.rec_vkopf.get_fs_dbVal(VKOPF_KON.buchungsnummer,"<?>"));

		if(rec_vkopf.is_fixierungsKontrakte()){

			boolean is_boerse_diff_abs_empty 	= 	this.getRbComponentSavable(VPOS_KON.boerse_diff_abs).get__actual_maskstring_in_view().isEmpty();
			boolean is_boerse_diff_proz_empty	= 	this.getRbComponentSavable(VPOS_KON.boerse_diff_proz).get__actual_maskstring_in_view().isEmpty();

			einheit	= 	this.rec_vkopf.get_einheit();
			waehrung =  this.rec_vkopf.get_fremd_waehrung();
			
			if(is_boerse_diff_abs_empty){
				this._setValue(VPOS_KON.boerse_diff_abs, rec_vkopf.get_boerse_diff_abs());
			}

			if(is_boerse_diff_proz_empty){
				this._setValue(VPOS_KON.boerse_diff_proz,rec_vkopf.get_boerse_diff_proz());
			}

			((E2_TF_4_Date)this.get__Comp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_KOPF_FIX_VON.name())).get_oTextField().setText(this.rec_vkopf.get_fix_datum_von());
			((E2_TF_4_Date)this.get__Comp(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_KOPF_FIX_BIS.name())).get_oTextField().setText(this.rec_vkopf.get_fix_datum_bis());

			this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_KOPF_FIX_GESAMTMENGE.key()	,this.rec_vkopf.get_fixierung_gesamt_menge().get_FormatedRoundedNumber(3));
			this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.TA_GELIEFERT.key()					,this.rec_vkopf.get_gesamt_fuhre_menge(isEK).get_FormatedRoundedNumber(3));
			this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.TA_FIXIERT.key()						,this.rec_vkopf.get_aktuelle_fixiert_menge().get_FormatedRoundedNumber(3));

			new KFIX_P___calculate_rests(this,false);


		}else{

			this.getRbComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_SORTE.key()).rb_set_db_value_manual(anr12);

			einheit = this.getRbComponentSavable(VPOS_KON.einheitkurz).rb_readValue_4_dataobject();
		}

		if(is_einzelpreis_full){
			this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_PREIS.key(),new MyBigDecimal(this.getRbComponentSavable(VPOS_KON.einzelpreis).rb_readValue_4_dataobject()).get_FormatedRoundedNumber(2) + " "+ waehrung);
		}

		if(is_anzahl_full){
			this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_MENGE.key(),new MyBigDecimal(this.getRbComponentSavable(VPOS_KON.anzahl).rb_readValue_4_dataobject()).get_FormatedRoundedNumber(1) + " "+ einheit);
		}
		
		
		if(actual_status == MASK_STATUS.NEW){
			if(rec_vkopf.is_fixierungsKontrakte()){
				this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.LBL_EINHEIT.key(),		rec_vkopf.get_preis_einheit());
				this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.LBL_EINHEIT_2.key(),		rec_vkopf.get_preis_einheit());

				this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.LBL_WAEHRUNG.key(),		rec_vkopf.get_fremd_waehrung());
				this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.LBL_WAEHRUNG_2.key(),	rec_vkopf.get_fremd_waehrung());
			}
		}


		if(actual_status==MASK_STATUS.EDIT){

			String mengendivisor =	this.getRbComponentSavable(VPOS_KON.mengendivisor).rb_readValue_4_dataobject();
			String waehrungskurs =	this.getRbComponentSavable(VPOS_KON.waehrungskurs).rb_readValue_4_dataobject();
			waehrung = new Rec20(_TAB.waehrung)._fill_id(bibALL.convertID2UnformattedID(this.getRbComponentSavable(VPOS_KON.id_waehrung_fremd).rb_readValue_4_dataobject())).get_ufs_dbVal(WAEHRUNG.kurzbezeichnung);

			if(rec_vkopf.is_fixierungsKontrakte()){
				boolean is_manuell_preis = this.getRbComponentSavable(VPOS_KON.preis_manuell).rb_readValue_4_dataobject().equals("Y")?true:false;
				if(is_manuell_preis){
					this.getRbComponent(VPOS_KON.einzelpreis)			.set_bEnabled_For_Edit(true);

					this.getRbComponent(VPOS_KON.fixierungstag)		.set_bEnabled_For_Edit(false);
					this.getRbComponent(VPOS_KON.boersenkurs)			.set_bEnabled_For_Edit(false);
					this.getRbComponent(VPOS_KON.wechselkurs)			.set_bEnabled_For_Edit(false);
					this.getRbComponent(VPOS_KON.boerse_diff_proz)	.set_bEnabled_For_Edit(false);
					this.getRbComponent(VPOS_KON.boerse_diff_abs)		.set_bEnabled_For_Edit(false);

					new KFIX_P___calculate_fixierungs_preis(this);
				}else{
					this.getRbComponent(VPOS_KON.einzelpreis)			.set_bEnabled_For_Edit(false);

					this.getRbComponent(VPOS_KON.fixierungstag)		.set_bEnabled_For_Edit(true);
					this.getRbComponent(VPOS_KON.boersenkurs)			.set_bEnabled_For_Edit(true);
					this.getRbComponent(VPOS_KON.wechselkurs)			.set_bEnabled_For_Edit(true);		
					this.getRbComponent(VPOS_KON.boerse_diff_proz)	.set_bEnabled_For_Edit(true);
					this.getRbComponent(VPOS_KON.boerse_diff_abs)		.set_bEnabled_For_Edit(true);

				}
				

			}

			this._setValue(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_INFO_PREIS.key(),new MyBigDecimal(this.getRbComponentSavable(VPOS_KON.einzelpreis).rb_readValue_4_dataobject()).get_FormatedRoundedNumber(2) + " "+ waehrung);

			if(S.isAllFull(anzahl, einzelpreis, mengendivisor, waehrungskurs)){
				BS_PreisCalculator preisCalc = new BS_PreisCalculator(
						this.getRbComponentSavable(VPOS_KON.anzahl).rb_readValue_4_dataobject(),
						this.getRbComponentSavable(VPOS_KON.einzelpreis).rb_readValue_4_dataobject(),
						this.getRbComponentSavable(VPOS_KON.mengendivisor).rb_readValue_4_dataobject(),
						this.getRbComponentSavable(VPOS_KON.waehrungskurs).rb_readValue_4_dataobject(),
						true);

				this._setValue(VPOS_KON.gesamtpreis, 		MyNumberFormater.formatDez(bibALL.Round(preisCalc.get_dGesamtPreis().doubleValue(),2),2,true));
				this._setValue(VPOS_KON.einzelpreis_fw, 	MyNumberFormater.formatDez(bibALL.Round(preisCalc.get_dEinzelPreis_FW().doubleValue(),2),2,true));
				this._setValue(VPOS_KON.gesamtpreis_fw, 	MyNumberFormater.formatDez(bibALL.Round(preisCalc.get_dGesamtPreis_FW().doubleValue(),2),2,true));
			}

		}	

		if(actual_status==MASK_STATUS.VIEW){
			waehrung = new Rec20(_TAB.waehrung)._fill_id(bibALL.convertID2UnformattedID(this.getRbComponentSavable(VPOS_KON.id_waehrung_fremd).rb_readValue_4_dataobject())).get_ufs_dbVal(WAEHRUNG.kurzbezeichnung);
			if(rec_vkopf.is_fixierungsKontrakte() && is_anzahl_full){
				einheit = this.rec_vkopf.get_einheit();
			}else{
				if(is_anzahl_full){
					einheit = this.getRbComponentSavable(VPOS_KON.einheitkurz).rb_readValue_4_dataobject();
				}
				if(is_einzelpreis_full){
					einheit = "";
				}
			}
		}

	

		return null;
	}


	private class own_manuell_preis_chkbox_aa extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			boolean is_cb_selected = ((RB_cb)oExecInfo.get_MyActionEvent().getSource()).isSelected();

			KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.einzelpreis).rb_set_db_value_manual("");

			if(is_cb_selected){
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.einzelpreis)		.mark_Disabled();
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.einzelpreis)		.set_bEnabled_For_Edit(true);

				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.fixierungstag)	.mark_Neutral();
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boersenkurs)		.mark_Neutral();
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.wechselkurs)		.mark_Neutral();

				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.fixierungstag)	.set_bEnabled_For_Edit(false);
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boersenkurs)		.set_bEnabled_For_Edit(false);
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.wechselkurs)		.set_bEnabled_For_Edit(false);		
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boerse_diff_proz)	.set_bEnabled_For_Edit(false);
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boerse_diff_abs)	.set_bEnabled_For_Edit(false);

				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.fixierungstag)	.mark_Disabled();
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boersenkurs)		.mark_Disabled();
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.wechselkurs)		.mark_Disabled();
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boerse_diff_proz)	.mark_Disabled();
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boerse_diff_abs)	.mark_Disabled();	

				KFIX_P_M__ComponentMap.this.getPreSettingsContainer().rb_get(VPOS_KON.fixierungstag)	.set_MustField(false);
				KFIX_P_M__ComponentMap.this.getPreSettingsContainer().rb_get(VPOS_KON.boersenkurs)		.set_MustField(false);
				KFIX_P_M__ComponentMap.this.getPreSettingsContainer().rb_get(VPOS_KON.wechselkurs)		.set_MustField(false);
			}else{

				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.einzelpreis)		.mark_Disabled();
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.einzelpreis)		.set_bEnabled_For_Edit(false);

				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.fixierungstag)	.mark_Neutral();
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boersenkurs)		.mark_Neutral();
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.wechselkurs)		.mark_Neutral();
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boerse_diff_proz)	.mark_Neutral();
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boerse_diff_abs)	.mark_Neutral();

				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.fixierungstag)	.set_bEnabled_For_Edit(true);
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boersenkurs)		.set_bEnabled_For_Edit(true);
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.wechselkurs)		.set_bEnabled_For_Edit(true);		
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boerse_diff_proz)	.set_bEnabled_For_Edit(true);
				KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boerse_diff_abs)	.set_bEnabled_For_Edit(true);

				KFIX_P_M__ComponentMap.this.getPreSettingsContainer().rb_get(VPOS_KON.fixierungstag)	.set_MustField(true);
				KFIX_P_M__ComponentMap.this.getPreSettingsContainer().rb_get(VPOS_KON.boersenkurs)		.set_MustField(true);
				KFIX_P_M__ComponentMap.this.getPreSettingsContainer().rb_get(VPOS_KON.wechselkurs)		.set_MustField(true);

				boolean is_boersenkurs_empty = 		KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.boersenkurs).get__actual_maskstring_in_view().isEmpty();

				boolean is_waehrungskurs_empty =	KFIX_P_M__ComponentMap.this.getRbComponent(VPOS_KON.waehrungskurs).get__actual_maskstring_in_view().isEmpty();

				if(is_boersenkurs_empty || is_waehrungskurs_empty){
					bibMSG.add_MESSAGE(new MyE2_Message(new MyString("Der Einzelpreis kann nicht berechnet sein.")	, MyE2_Message.TYP_WARNING,true));
				}else{
					bibMSG.add_MESSAGE(new MyE2_Message(new MyString("Ein neuer Einzelpreis ist berechnet.")		, MyE2_Message.TYP_INFO,true));

					new KFIX_P___calculate_fixierungs_preis(KFIX_P_M__ComponentMap.this);
				}


			}
		}
	}


	//----- GETTER -------
	public Rec20_VKOPF_KON getRec_vkopf() {
		return rec_vkopf;
	}

	public boolean is_einkauf_position(){
		return belegTyp.equals(myCONST.VORGANGSART_EK_KONTRAKT)?true:false;
	}

}
