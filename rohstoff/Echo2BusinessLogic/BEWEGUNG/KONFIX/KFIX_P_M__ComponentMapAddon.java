package rohstoff.Echo2BusinessLogic.BEWEGUNG.KONFIX;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.basics4project.DB_ENUMS.VKOPF_KON;
import panter.gmbh.basics4project.DB_ENUMS.VPOS_KON_TRAKT;
import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALREC20.Rec20_VKOPF_KON;

public class KFIX_P_M__ComponentMapAddon extends RB_ComponentMap {

	private Rec20_VKOPF_KON rec_vkopf = null;
	
	public KFIX_P_M__ComponentMapAddon(String oBelegTyp, Rec20 rec20_vkopf) throws myException {
		super();
		
		this.rec_vkopf = new Rec20_VKOPF_KON(rec20_vkopf);
		
		boolean is_fix = this.rec_vkopf.is_yes_db_val(VKOPF_KON.ist_fixierung);
		
		this.registerComponent(VPOS_KON_TRAKT.abgeschlossen, 			new RB_CheckBox(VPOS_KON_TRAKT.abgeschlossen));
		this.registerComponent(VPOS_KON_TRAKT.gueltig_von, 			new RB_date_selektor(95, true));
		this.registerComponent(VPOS_KON_TRAKT.gueltig_bis, 			new RB_date_selektor(95, true));
		this.registerComponent(VPOS_KON_TRAKT.ueberliefer_ok, 		new RB_CheckBox(VPOS_KON_TRAKT.ueberliefer_ok));
		this.registerComponent(VPOS_KON_TRAKT.lieferort, 				new RB_TextField(500));
		this.registerComponent(VPOS_KON_TRAKT.lieferzeit, 			new RB_TextField(500));
		this.registerComponent(VPOS_KON_TRAKT.transportmittel,		new KFIX_P_M_Combobox_Transportmittel(VPOS_KON_TRAKT.transportmittel,400));
		this.registerComponent(VPOS_KON_TRAKT.bemerkung_intern,		new RB_TextArea(500, 10));
		this.registerComponent(VPOS_KON_TRAKT.bemerkung_extern,		new KFIX_P_M_position_zusatzinfo_TextArea(oBelegTyp, 500,  10));
		this.registerComponent(VPOS_KON_TRAKT.fixierungsbedingungen,	new KFIX_P_M_position_zusatzinfo_TextArea(oBelegTyp, is_fix?400:500, is_fix?7:10));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_BT_LIEFERORT.key(), new KFIX_P_M_BT_lieferort());
		
		this.registerComponent(VPOS_KON_TRAKT.verlaengerte_fakt_frist, 		new RB_CheckBox(VPOS_KON_TRAKT.verlaengerte_fakt_frist)._ttt(S.ms("Falls beim Kunden eine Kreditversicherung mit verlängerter Fakturierungsfrist hinterlegt ist, kann die Nutzung dieser Frist hier eingeschaltet werden")));

		
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASH_KEY_BT_SET_ACTUAL_MONTH.key(), 			new KFIX__BT_Set_Datum_Range(0,VPOS_KON_TRAKT.gueltig_von,VPOS_KON_TRAKT.gueltig_bis));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASH_KEY_BT_SET_ACTUAL_MONTH_PLUS_ONE.key(), 	new KFIX__BT_Set_Datum_Range(1,VPOS_KON_TRAKT.gueltig_von,VPOS_KON_TRAKT.gueltig_bis));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASH_KEY_BT_SET_ACTUAL_MONTH_PLUS_TWO.key(), 	new KFIX__BT_Set_Datum_Range(2,VPOS_KON_TRAKT.gueltig_von,VPOS_KON_TRAKT.gueltig_bis));
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASHKEY_BT_LIEFERANT.key(), 					new KFIX_K_M_BT_Adresse_popup());
		this.registerComponent(KFIX___CONST.ADDITIONNAL_COMP_POS.HASH_KEY_BT_SET_FIXIERUNGS_ZEITRAUM.key(), 	new KFIX_P_M_BT_Set_Fixierungs_zeitraum(rec_vkopf));

		
		this.registerSetterValidators(new RB_KF(VPOS_KON_TRAKT.gueltig_von), new KFIX_P_M_SetAndValid_Gueltigkeit());
		this.registerSetterValidators(new RB_KF(VPOS_KON_TRAKT.gueltig_bis), new KFIX_P_M_SetAndValid_Gueltigkeit());
	}

	@Override
	public MyE2_MessageVector setUserdefinedMaskSettingBeforeLoad() throws myException {
		return null;
	}

	@Override
	public MyE2_MessageVector maskSettings_do_After_Load() throws myException {
		return null;
	}

	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,
			MASK_STATUS status) throws myException {
		
		preSettingsContainer.rb_get(VPOS_KON_TRAKT.abgeschlossen).set_Enabled(false);
		preSettingsContainer.rb_get(VPOS_KON_TRAKT.abgeschlossen).set_MustField(false);
		if(status == MASK_STATUS.NEW || status == MASK_STATUS.EDIT){
			preSettingsContainer.rb_get(VPOS_KON_TRAKT.gueltig_von).set_MustField(true);
			preSettingsContainer.rb_get(VPOS_KON_TRAKT.gueltig_bis).set_MustField(true);
		}
		if(status == MASK_STATUS.NEW){
			if(rec_vkopf.is_fixierungsKontrakte() && rec_vkopf.kopie_bemerkung_auf_position()){
				preSettingsContainer.rb_get(VPOS_KON_TRAKT.fixierungsbedingungen).set_rb_Default(rec_vkopf.get_fixierungs_bemerkung());
			}
		}
		
		return null;
	}

	public Rec20_VKOPF_KON getRecVkopf() {
		return rec_vkopf;
	}

	@Override
	public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
		// TODO Auto-generated method stub
		
	}

	public Rec20_VKOPF_KON getRec_vkopf() {
		return rec_vkopf;
	}

}
