 
package rohstoff.businesslogic.bewegung.lager.list_bewegung;
  
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ALIGN;
import panter.gmbh.Echo2.RB.BASICS.RB_KM;
import panter.gmbh.Echo2.RB.COMP.RB_lab;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4Mask;
//import panter.gmbh.basics4project.DB_ENUMS.BG_LADUNG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.exceptions.myException;
@Deprecated
public class BG_Lager_Bewegung_MASK_MaskGrid extends E2_Grid4Mask {
    public BG_Lager_Bewegung_MASK_MaskGrid(BG_Lager_Bewegung_MASK_ComponentMapCollector  mapColl) throws myException {
        super();
        this._setSize(160,600)._bo_no();
        
//        BG_Lager_Bewegung_MASK_ComponentMap  map1 = (BG_Lager_Bewegung_MASK_ComponentMap) mapColl.get(_TAB.bg_ladung.rb_km());
        
//        this._add(new RB_lab("abzug_menge") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.abzug_menge), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("anr1") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.anr1), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("anr2") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.anr2), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("artbez1") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.artbez1), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("artbez2") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.artbez2), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("bemerkung_extern") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.bemerkung_extern), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("bemerkung_intern") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.bemerkung_intern), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("eu_steuer_vermerk") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.eu_steuer_vermerk), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("e_preis_result_brutto_mge") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.e_preis_result_brutto_mge), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("e_preis_result_netto_mge") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.e_preis_result_netto_mge), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("gesamtpreis") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.gesamtpreis), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("gpreis_abz_auf_nettomge") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.gpreis_abz_auf_nettomge), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("gpreis_abz_mge") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.gpreis_abz_mge), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("gpreis_abz_vorauszahlung") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.gpreis_abz_vorauszahlung), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_adresse_besitzer") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_adresse_besitzer), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_artikel") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_artikel), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_artikel_bez") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_artikel_bez), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_bg_atom") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_bg_atom), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_bg_del_info") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_bg_del_info), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_bg_ladung") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_bg_ladung), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_bg_pruefprot_abschluss") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_bg_pruefprot_abschluss), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_bg_pruefprot_menge") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_bg_pruefprot_menge), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_bg_pruefprot_preis") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_bg_pruefprot_preis), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_bg_station") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_bg_station), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_bg_storno_info") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_bg_storno_info), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_bg_vektor") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_bg_vektor), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_lager_konto") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_lager_konto), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_tax") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_tax), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_vpos_kon") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_vpos_kon), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("id_vpos_std") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.id_vpos_std), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("kontraktzwang") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.kontraktzwang), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("kosten_produkt") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.kosten_produkt), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("leistungsdatum") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.leistungsdatum), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("menge") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.menge), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("mengenvorzeichen") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.mengenvorzeichen), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("menge_netto") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.menge_netto), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("postennummer") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.postennummer), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("steuersatz") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.steuersatz), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(new RB_lab("timestamp_in_mask") ,new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//        this._add(map1.getComp(BG_LADUNG.timestamp_in_mask), new RB_gld()._ins(2)._al(E2_ALIGN.LEFT_TOP));
//    
    }
  
    
}
 
