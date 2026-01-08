 
package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.LL;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_TextField_resizable;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_label_anr1_2;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_label_einheit;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_label_einheitpreis;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;

public class LL_CM_Atom extends RB_ComponentMap {
	
	private ENUM_VEKTORPOS_TYP pos_typ = null;

    public LL_CM_Atom(ENUM_VEKTORPOS_TYP typ) throws myException {
        super();
        
        this.pos_typ = typ;

        this.rb_INIT_4_DB(_TAB.bewegung_atom);
        
        this.registerComponent(BEWEGUNG_ATOM.id_artikel_bez, 					new LL_CO_search_artbez(this.pos_typ,FZ__CONST.FIELD_WIDTH.ARTIKEL_SUCHE.get_pixel()));
		this.registerComponent(FZ__CONST.f_keys.EINHEIT.k(),  				new RB_label_einheit()._fsa(2));
		this.registerComponent(FZ__CONST.f_keys.EINHEIT_PREIS.k(),			new RB_label_einheitpreis()._fsa(2));

		this.registerComponent(BEWEGUNG_ATOM.abgeschlossen_von,				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.abzug_menge, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.artbez1, 						new RB_TextField_resizable(402));
		this.registerComponent(BEWEGUNG_ATOM.buchungsnummer, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.del_kuerzel, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.e_preis, 						new RB_TextField_resizable(70));
		this.registerComponent(BEWEGUNG_ATOM.e_preis_result_brutto_mge, 		new RB_TextField_resizable(70));
		this.registerComponent(BEWEGUNG_ATOM.e_preis_result_netto_mge, 		new RB_TextField_resizable(70));
		this.registerComponent(BEWEGUNG_ATOM.gesamtpreis, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.gpreis_abz_auf_nettomge, 		new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.gpreis_abz_mge, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.gpreis_abz_vorauszahlung, 		new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.id_adresse_wb_start, 			new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.id_adresse_wb_ziel, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.id_artikel, 						new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.id_bewegung, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.id_bewegungstation_logi_start, 	new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.id_bewegungstation_logi_ziel, 	new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.id_bewegung_atom, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.id_lager_konto, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.id_lieferbedingungen, 			new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.id_vpos_kon, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.id_vpos_std, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.id_zahlungsbedingungen, 			new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.kontraktzwang_aus_von,			new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.lieferbedingungen, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.menge, 							new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.menge_netto, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.nationaler_abfall_code, 			new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.notifikation_nr, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.ordnungsnummer_cmr, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.planmenge, 						new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.postennummer, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.preisermittlung, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.pruefung_menge_von, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.pruefung_preis_von, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.steuersatz, 						new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.storniert_von, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG_ATOM.zeitstempel, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));

		this.registerComponent(BEWEGUNG_ATOM.kontraktzwang_aus_am, 			new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG_ATOM.abgeschlossen_am, 				new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG_ATOM.pruefung_preis_am, 				new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG_ATOM.leistungsdatum, 					new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG_ATOM.storniert_am, 					new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG_ATOM.pruefung_menge_am, 				new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG_ATOM.del_date, 						new RB_date_selektor(80,true));

		this.registerComponent(BEWEGUNG_ATOM.storniert_grund, 				new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG_ATOM.lieferinfo_tpa, 					new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG_ATOM.eu_steuer_vermerk, 				new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG_ATOM.bemerkung, 						new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG_ATOM.bemerkung_sachbearbeiter, 		new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG_ATOM.del_grund, 						new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG_ATOM.kontraktzwang_aus_grund, 		new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG_ATOM.artbez2, 						new RB_TextArea(400,5));
		
		this.registerComponent(BEWEGUNG_ATOM.pruefung_preis, 					new RB_CheckBox(BEWEGUNG_ATOM.pruefung_preis));
		this.registerComponent(BEWEGUNG_ATOM.pruefung_menge, 					new RB_CheckBox(BEWEGUNG_ATOM.pruefung_menge));
		this.registerComponent(BEWEGUNG_ATOM.deleted, 						new RB_CheckBox(BEWEGUNG_ATOM.deleted));
		this.registerComponent(BEWEGUNG_ATOM.storniert, 						new RB_CheckBox(BEWEGUNG_ATOM.storniert));
		this.registerComponent(BEWEGUNG_ATOM.manuell_preis, 					new RB_CheckBox(BEWEGUNG_ATOM.manuell_preis));
		this.registerComponent(BEWEGUNG_ATOM.kontraktzwang, 					new RB_CheckBox(BEWEGUNG_ATOM.kontraktzwang));
		this.registerComponent(BEWEGUNG_ATOM.setzkasten_komplett, 			new RB_CheckBox(BEWEGUNG_ATOM.setzkasten_komplett));
		this.registerComponent(BEWEGUNG_ATOM.abgerechnet,						new RB_CheckBox(BEWEGUNG_ATOM.abgerechnet));
		this.registerComponent(BEWEGUNG_ATOM.abrechenbar, 					new RB_CheckBox(BEWEGUNG_ATOM.abrechenbar));
		this.registerComponent(BEWEGUNG_ATOM.abgeschlossen, 					new RB_CheckBox(BEWEGUNG_ATOM.abgeschlossen));
		
		this.registerComponent(FZ__CONST.f_keys.ANR12.k(), 					new RB_label_anr1_2());
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
    public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer SurfaceSettingsContainer,MASK_STATUS status) throws myException {

    	//sortenbezeichung ist mandatory
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.id_artikel_bez).set_MustField(true);
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.leistungsdatum).set_MustField(true);
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.menge).set_MustField(true);
//    	SurfaceSettingsContainer.rb_get_forcedDB(BEWEGUNG_ATOM.posnr).rb_set_formated_value_forced_at_save(this.pos_typ==ENUM_VEKTORPOS_TYP.LL_MAIN_LEFT?"1":"6");    //maximal 6 posnummern moeglich
    	
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.id_vpos_kon).set_Enabled(false);
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.id_vpos_std).set_Enabled(false);
    	
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.id_artikel).set_Enabled(false);
        
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.gesamtpreis).set_Enabled(false);
    	
    	return new MyE2_MessageVector();
    }
    
    
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
}
 
