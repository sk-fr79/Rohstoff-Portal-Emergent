 
package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WE;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.ALIGN_HORIZONTAL;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_ATOM;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_TextField_resizable;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_label_anr1_2;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.RB_label_einheit;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK.WA._WA_SAV_Manuell_EPreis;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST.SEARCH_EK_OR_VK;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTORPOS_TYP;

public class WE_CM_Atom extends RB_ComponentMap {
	
	private ENUM_VEKTORPOS_TYP pos_typ = null;
	
    public WE_CM_Atom(ENUM_VEKTORPOS_TYP typ) throws myException {
        super();
         
        this.rb_INIT_4_DB(_TAB.bewegung_atom);
        
        this.pos_typ = typ;
        
    	this.registerComponent(FZ__CONST.f_keys.EINHEIT.k(),  				new RB_label_einheit()._fsa(2));
        
        this.registerComponent(BEWEGUNG_ATOM.id_artikel_bez, 					new WE_CO_search_artbez(pos_typ,90));

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
		
        this.registerComponent(FZ__CONST.f_keys.KOMBI_ANG_KON_LEFT.k(),  		new WE_CO_search_kontrakt_angebot(SEARCH_EK_OR_VK.EK, 
														        				(RB_TextField)this.getComp(BEWEGUNG_ATOM.id_vpos_kon), 
														        				(RB_TextField)this.getComp(BEWEGUNG_ATOM.id_vpos_std), 
														        						FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        
        
        
        this.registerSetterValidators(BEWEGUNG_ATOM.manuell_preis.fk(), new _WE_SAV_Manuell_EPreis());

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
    	
    	SurfaceSettingsContainer.rb_get(FZ__CONST.f_keys.KOMBI_ANG_KON_LEFT.k()).set_AlignHorizontal(ALIGN_HORIZONTAL.RIGHT);
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.id_artikel).set_Enabled(false);
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.gesamtpreis).set_Enabled(false);
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.id_vpos_kon).set_Enabled(false);
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.id_vpos_std).set_Enabled(false);
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.e_preis).set_AlignHorizontal(ALIGN_HORIZONTAL.RIGHT);
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.menge).set_AlignHorizontal(ALIGN_HORIZONTAL.RIGHT);
    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.gesamtpreis).set_AlignHorizontal(ALIGN_HORIZONTAL.RIGHT);
    	
    	

		if(status == MASK_STATUS.NEW || status== MASK_STATUS.EDIT){
			
			SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.id_artikel_bez)	.set_MustField(true);
//	    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.leistungsdatum)	.set_MustField(true);
//	    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.menge)			.set_MustField(true);
//	    	
//	    	SurfaceSettingsContainer.rb_get(BEWEGUNG_ATOM.menge)			.set_rb_Default("0");
		}

    	
        return new MyE2_MessageVector();
    }
    
    
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
    
    
    /**
     * validierer check, ob die internen veteilermengen groesser sind als die lagermengen
     * @author martin
     *
     */
//    private class ownMapSetAndValid_VerteilSummeKleinerAlsLagermenge extends RB_Mask_Set_And_Valid {
//
//		@Override
//		public MyE2_MessageVector make_Interactive_Set_and_Valid(RB_ComponentMap rbMASK, VALID_TYPE ActionType, ExecINFO oExecInfo) throws myException {
//			MyE2_MessageVector mv = new MyE2_MessageVector();
//			if (ActionType==VALID_TYPE.USE_IN_MASK_VALID_ACTION_BEFORE_FIELDVALIDATION) {
//				WE_CM__Collector collector =(WE_CM__Collector)WE_CM_Atom.this.rb_get_belongs_to();
//				
//				if (collector.get_master_key().get_mask_status().isStatusEdit()) {
//				
//					_WE_MASK_Controller controller = new _WE_MASK_Controller(collector);
//					
//					MyBigDecimal bd_ek_menge = controller.get_MyBigDecimal_maskVal(controller.get_masterKey().k_atom_left(), BEWEGUNG_ATOM.menge);
//
//					WE_CO_lager_menge  lagermenge = (WE_CO_lager_menge)WE_CM_Atom.this.rb_Comp(f_keys.LAGER_DIFF_MENGE_WE.k());
//					MyBigDecimal bd_lager_menge = new MyBigDecimal(lagermenge.getText());
//					
//					MyBigDecimal bd_relevant = (bd_lager_menge!=null&&bd_lager_menge.isOK())?bd_lager_menge:bd_ek_menge;
//					
//					if (bd_relevant!=null && bd_relevant.isOK()) {
//						
//						//jetzt die inneren sortenwechsel suchen
//						Rec20 rec_atom = WE_CM_Atom.this.rb_Mask_Dataobject_actual().rec20();
//						
//						Rec20 rec_vektor = rec_atom.get_up_Rec20(BEWEGUNG_VEKTOR.id_bewegung_vektor);
//						RecList20 rl_vektor_pos_hidden_sep = rec_vektor.get_down_reclist20(BEWEGUNG_VEKTOR_POS.id_bewegung_vektor, null, null)
//								.get_filtered_list(new IF_RecListFilter() {
//														@Override
//														public boolean isInFilter(Rec20 rec) throws myException {
//															return rec.get_fs_dbVal(BEWEGUNG_VEKTOR_POS.pos_typ,"").equals(VEKTOR_POS_TYP.WE_HIDDEN_SEP.db_val());
//														}
//													});
//						
//
//						//jetzt in den vektorpos die startatome der hidden-sep werte addieren, das ist die verteilte summe
//						MyBigDecimal  bd_verteil_summe = new MyBigDecimal(0);
//						
//						for (Rec20 rec_vektor_pos: rl_vektor_pos_hidden_sep) {
//							RecList20 rl_atom = rec_vektor_pos.get_down_reclist20(BEWEGUNG_ATOM.id_bewegung_vektor_pos, null, null);
//							for (Rec20 rec: rl_atom) {
//								Rec20_atom atom_umbuchung = new Rec20_atom(rec);
//								if (atom_umbuchung.__station_start().is_reale_adresse()) {
//									bd_verteil_summe.add_to_me(atom_umbuchung.get_myBigDecimal_dbVal(BEWEGUNG_ATOM.menge));
//								}
//							}
//						}
//						
//						
//						if (bd_relevant.get_bdWert().compareTo(bd_verteil_summe.get_bdWert())<0) {
//							mv.add_MESSAGE(new MyE2_Alarm_Message(S. mt("Die intern verteilte Menge ist: ")
//																	.ut(""+bd_verteil_summe.get_FormatedRoundedNumber(3))
//																	.t(" und damit größer als die Lagermenge: ")
//																	.ut(""+bd_relevant.get_FormatedRoundedNumber(3))));
//						}
//						
//					}
//					
//				}
//				
//			}
//			
//			return mv;
//		}
//    	
//    }
    
    
}
 
