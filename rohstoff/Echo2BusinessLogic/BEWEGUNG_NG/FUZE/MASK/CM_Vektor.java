package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;

import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG_VEKTOR;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.FZ_maskSetAndValidDoMaskMapping;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.INTERFACES.IF_MasterKey;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._SET_AN_VALID.FZ_SaV_CheckDatumKontraktGegenLeistungsDatum;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE._SET_AN_VALID.FZ_SaV_CheckLeistungsDatumGegenPlanungszeitraum;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;

public class CM_Vektor extends RB_ComponentMap {


    public CM_Vektor(IF_MasterKey masterkey) throws myException {
        super();
        
        
        this.rb_INIT_4_DB(_TAB.bewegung_vektor);
        
        this.registerComponent(BEWEGUNG_VEKTOR.abgeschlossen_von, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.anhaengerkennzeichen, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.del_kuerzel, 						new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.eu_blatt_menge, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.eu_blatt_volumen, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.id_adresse_fremdware, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.id_adresse_spedition, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.id_adresse_start_logistik, 		new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.id_bewegung, 						new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.id_bewegung_atom_trigstart, 		new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.id_bewegung_atom_trigziel, 		new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.id_bewegung_vektor, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.id_eak_code, 						new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.id_uma_kontrakt, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.id_vektor_gruppe, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.id_verpackungsart, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.kosten_produkt_wa, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.kosten_produkt_we, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.kosten_transport_wa, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.kosten_transport_we, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.laendercode_transit1, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.laendercode_transit2, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.laendercode_transit3, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.status, 							new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.storniert_von, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.transportkennzeichen, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.transportmittel, 					new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.variante, 							new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.warenklasse, 						new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.zahl_gutpos, 						new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.zahl_rechpos, 						new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
        this.registerComponent(BEWEGUNG_VEKTOR.zeitstempel, 						new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));


		this.registerComponent(BEWEGUNG_VEKTOR.abgeschlossen_am, 					new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG_VEKTOR.avv_ausstellung_datum, 			new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG_VEKTOR.storniert_am,			 			new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG_VEKTOR.a_datum_von, 						new RB_date_selektor(73,true));
		this.registerComponent(BEWEGUNG_VEKTOR.a_datum_bis, 						new RB_date_selektor(90,true));
		this.registerComponent(BEWEGUNG_VEKTOR.l_datum_von, 						new RB_date_selektor(73,true));
		this.registerComponent(BEWEGUNG_VEKTOR.l_datum_bis, 						new RB_date_selektor(90,true));
		this.registerComponent(BEWEGUNG_VEKTOR.del_date, 							new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG_VEKTOR.statistik_timestamp, 				new RB_date_selektor(80,true));

		this.registerComponent(BEWEGUNG_VEKTOR.storniert_grund, 					new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG_VEKTOR.bemerkung_fuer_kunde, 				new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG_VEKTOR.bemerkung, 						new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG_VEKTOR.bemerkung_sachbearbeiter, 			new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG_VEKTOR.del_grund, 						new RB_TextArea(400,5));
		
		this.registerComponent(BEWEGUNG_VEKTOR.ek_vk_zuord_zwang, 				new RB_CheckBox(BEWEGUNG_VEKTOR.ek_vk_zuord_zwang));
		this.registerComponent(BEWEGUNG_VEKTOR.gelangensbestaetigung_erhalten, 	new RB_CheckBox(BEWEGUNG_VEKTOR.gelangensbestaetigung_erhalten));
		this.registerComponent(BEWEGUNG_VEKTOR.print_eu_amtsblatt, 				new RB_CheckBox(BEWEGUNG_VEKTOR.print_eu_amtsblatt));
		this.registerComponent(BEWEGUNG_VEKTOR.deleted, 							new RB_CheckBox(BEWEGUNG_VEKTOR.deleted));
		this.registerComponent(BEWEGUNG_VEKTOR.storniert, 						new RB_CheckBox(BEWEGUNG_VEKTOR.storniert));
		this.registerComponent(BEWEGUNG_VEKTOR.abgeschlossen, 					new RB_CheckBox(BEWEGUNG_VEKTOR.abgeschlossen));
       
		
		this.registerSetterValidators(BEWEGUNG_VEKTOR.id_bewegung_vektor.fk(), 	new FZ_maskSetAndValidDoMaskMapping());
		this.registerSetterValidators(BEWEGUNG_VEKTOR.id_bewegung_vektor.fk(), 	new FZ_SaV_CheckLeistungsDatumGegenPlanungszeitraum(masterkey,this));
		this.registerSetterValidators(BEWEGUNG_VEKTOR.id_bewegung_vektor.fk(), 	new FZ_SaV_CheckDatumKontraktGegenLeistungsDatum(masterkey,this));

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
        return new MyE2_MessageVector();
    }
    
    @Override
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }


}
