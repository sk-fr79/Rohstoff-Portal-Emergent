 
package rohstoff.businesslogic.bewegung.lager.list_bewegung;
  
import java.util.Vector;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.BASICS.RB_ComponentMap;
import panter.gmbh.Echo2.RB.BASICS.RB_KF;
import panter.gmbh.Echo2.RB.BASICS.RB_PreSettingsContainer;
import panter.gmbh.Echo2.RB.BASICS.RB_SQLField;
import panter.gmbh.Echo2.RB.BASICS.RB__CONST.MASK_STATUS;
import panter.gmbh.Echo2.RB.COMP.RB_CheckBox;
import panter.gmbh.Echo2.RB.COMP.RB_Label;
import panter.gmbh.Echo2.RB.COMP.RB_TextArea;
import panter.gmbh.Echo2.RB.COMP.RB_TextField;
//import panter.gmbh.basics4project.DB_ENUMS.BG_LADUNG;
import panter.gmbh.indep.exceptions.myException;
@Deprecated
public class BG_Lager_Bewegung_MASK_ComponentMap extends RB_ComponentMap {
    public BG_Lager_Bewegung_MASK_ComponentMap() throws myException {
        super();
        
//        this.registerComponent(new RB_KF(BG_LADUNG.abzug_menge),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.anr1),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.anr2),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.artbez1),    new RB_TextField()._width(400));
//        this.registerComponent(new RB_KF(BG_LADUNG.artbez2),    new RB_TextArea()._width(400)._rows(5));
//        this.registerComponent(new RB_KF(BG_LADUNG.bemerkung_extern),    new RB_TextArea()._width(400)._rows(5));
//        this.registerComponent(new RB_KF(BG_LADUNG.bemerkung_intern),    new RB_TextArea()._width(400)._rows(5));
//        this.registerComponent(new RB_KF(BG_LADUNG.eu_steuer_vermerk),    new RB_TextArea()._width(400)._rows(5));
//        this.registerComponent(new RB_KF(BG_LADUNG.e_preis_result_brutto_mge),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.e_preis_result_netto_mge),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.gesamtpreis),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.gpreis_abz_auf_nettomge),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.gpreis_abz_mge),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.gpreis_abz_vorauszahlung),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_adresse_besitzer),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_artikel),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_artikel_bez),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_bg_atom),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_bg_del_info),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_bg_ladung),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_bg_pruefprot_abschluss),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_bg_pruefprot_menge),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_bg_pruefprot_preis),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_bg_station),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_bg_storno_info),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_bg_vektor),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_lager_konto),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_tax),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_vpos_kon),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.id_vpos_std),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.kontraktzwang),    new RB_CheckBox(BG_LADUNG.kontraktzwang));
//        this.registerComponent(new RB_KF(BG_LADUNG.kosten_produkt),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.leistungsdatum),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.menge),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.mengenvorzeichen),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.menge_netto),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.postennummer),    new RB_TextField()._width(400));
//        this.registerComponent(new RB_KF(BG_LADUNG.steuersatz),    new RB_TextField()._width(100));
//        this.registerComponent(new RB_KF(BG_LADUNG.timestamp_in_mask),    new RB_TextField()._width(100));
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
    public void user_setting_change_EXCLUDE_HASHKEYS(Vector<String> v_standardExcludeHashKeys) {
    }
	@Override
	public MyE2_MessageVector maskSettings_define_own_pre_settings(RB_PreSettingsContainer preSettingsContainer,MASK_STATUS status) throws myException {
		return null;
	}
}
 
