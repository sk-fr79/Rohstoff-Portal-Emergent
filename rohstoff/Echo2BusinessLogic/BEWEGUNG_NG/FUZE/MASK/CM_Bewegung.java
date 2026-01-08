 
package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.MASK;
import java.util.Vector;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_date_selektor;
import panter.gmbh.basics4project.DB_ENUMS.BEWEGUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._BASICS.FZ__CONST;

public class CM_Bewegung extends RB_ComponentMap {
	
    public CM_Bewegung() throws myException {
        super();
         
        this.rb_INIT_4_DB(_TAB.bewegung);
        
        this.registerComponent(BEWEGUNG.id_bewegung, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.anzahl_container_fp, 		new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.id_containertyp_fp, 		new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.bewegung_typ, 			new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.erfasser_fp, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.id_maschinen_anh_fp, 		new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.fahrplangruppe_fp, 		new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.taetigkeit_fp, 			new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.sortierung_fp, 			new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.del_kuerzel, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.ean_code_fp, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.id_maschinen_lkw_fp, 		new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.anrufer_fp, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.fahrer_fp, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.fahrt_anfang_fp, 			new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.id_vpos_tpa_fuhre, 		new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.fahrt_ende_fp, 			new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.id_vpos_tpa_ng, 			new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));
		this.registerComponent(BEWEGUNG.zeitstempel, 				new RB_TextField_resizable(FZ__CONST.FIELD_WIDTH.MENGEN.get_pixel()));

		this.registerComponent(BEWEGUNG.dat_fahrplan_fp, 			new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG.dat_vorgemerkt_ende_fp, 	new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG.del_date, 				new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG.dat_vorgemerkt_fp, 		new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG.lade_datum_von, 			new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG.ablade_datum_bis, 		new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG.ablade_datum_von, 		new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG.anrufdatum_fp, 			new RB_date_selektor(80,true));
		this.registerComponent(BEWEGUNG.lade_datum_bis, 			new RB_date_selektor(80,true));
		
		this.registerComponent(BEWEGUNG.bemerkung, 				new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG.bemerkung_sachbearbeiter, new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG.del_grund, 				new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG.bemerkung_start_fp, 		new RB_TextArea(400,5));
		this.registerComponent(BEWEGUNG.bemerkung_ziel_fp, 		new RB_TextArea(400,5));

		this.registerComponent(BEWEGUNG.deleted, 					new RB_CheckBox(BEWEGUNG.deleted));
		this.registerComponent(BEWEGUNG.ist_geplant_fp, 			new RB_CheckBox(BEWEGUNG.ist_geplant_fp));
		this.registerComponent(BEWEGUNG.ist_lagerbuchung_alt, 	new RB_CheckBox(BEWEGUNG.ist_lagerbuchung_alt));
		this.registerComponent(BEWEGUNG.fahrplan_satz, 			new RB_CheckBox(BEWEGUNG.fahrplan_satz));
       
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
 
