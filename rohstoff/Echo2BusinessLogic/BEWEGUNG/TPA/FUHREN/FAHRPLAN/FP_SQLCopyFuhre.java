package rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN;

import java.util.HashMap;

import panter.gmbh.basics4project.DB_ENUMS.VPOS_TPA_FUHRE;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.myDataRecordCopySQLString;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.TPA.FUHREN.FAHRPLAN.FP__ALL.CopyTyp;

public class FP_SQLCopyFuhre extends myDataRecordCopySQLString {
	

	
	
	public FP_SQLCopyFuhre(String cid_FUHRE_TO_COPY, CopyTyp  p_copyTyp, String nextID) throws myException {
		super(	"JT_VPOS_TPA_FUHRE", 
				"ID_VPOS_TPA_FUHRE", 
				cid_FUHRE_TO_COPY,
				null, 
				null, 
				bibALL.get_Vector("EAN_CODE_FP", "BUCHUNGSNR_FUHRE"),
				null, 
				false);
		
		//this.add_ErsatzFields(FP_SQLCopyFuhre.get_hmErsatzfelderBeide());
		HashMap<String, String> hm_stdField = new HashMap<String, String>();
		hm_stdField.put(VPOS_TPA_FUHRE.id_mandant.fn(),bibALL.get_RECORD_MANDANT().get_ID_MANDANT_VALUE_FOR_SQLSTATEMENT());
		hm_stdField.put(VPOS_TPA_FUHRE.letzte_aenderung.fn(),"SYSDATE");
		hm_stdField.put(VPOS_TPA_FUHRE.geaendert_von.fn(),bibALL.get_RECORD_USER().get_KUERZEL_VALUE_FOR_SQLSTATEMENT());
		hm_stdField.put(VPOS_TPA_FUHRE.id_vpos_tpa_fuhre.fn(),nextID);
		this.add_ErsatzFields(hm_stdField);
		
		this.add_ErsatzFields(this.get_ausnahme_felder(p_copyTyp));
	}

	
	
	private HashMap<String, String> get_ausnahme_felder(CopyTyp  p_copyTyp) {
		HashMap<String, String>  hmTemp = new HashMap<String, String>(); 
		
		
		hmTemp.put(VPOS_TPA_FUHRE.id_vpos_tpa.fn(),"NULL");

		
		//zuerst die allgemeinen felder
		//2015-06-19: abschlussfelder muessen raus
		hmTemp.put(VPOS_TPA_FUHRE.pruefung_lademenge.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.pruefung_lademenge_von.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.pruefung_lademenge_am.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.pruefung_ablademenge.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.pruefung_ablademenge_von.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.pruefung_ablademenge_am.fn(),"NULL");
		
		hmTemp.put(VPOS_TPA_FUHRE.pruefung_ek_preis.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.pruefung_ek_preis_von.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.pruefung_ek_preis_am.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.pruefung_vk_preis.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.pruefung_vk_preis_von.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.pruefung_vk_preis_am.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.schliesse_fuhre.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.schliesse_fuhre_von.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.schliesse_fuhre_am.fn(),"NULL");

		hmTemp.put(VPOS_TPA_FUHRE.anteil_planmenge_lief.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.anteil_lademenge_lief.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.anteil_ablademenge_lief.fn(),"NULL");
		
		hmTemp.put(VPOS_TPA_FUHRE.anteil_planmenge_abn.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.anteil_lademenge_abn.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.anteil_ablademenge_abn.fn(),"NULL");
		
		hmTemp.put(VPOS_TPA_FUHRE.einzelpreis_ek.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.einzelpreis_vk.fn(),"NULL");

		hmTemp.put(VPOS_TPA_FUHRE.id_vpos_std_ek.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.id_vpos_std_vk.fn(),"NULL");
		
		hmTemp.put(VPOS_TPA_FUHRE.id_vpos_kon_ek.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.id_vpos_kon_vk.fn(),"NULL");
		
		
//		//2015-11-16: neue felder zum leermachen 
//		hmTemp.put(VPOS_TPA_FUHRE.datum_abholung.fn(),"SYSDATE");
//		hmTemp.put(VPOS_TPA_FUHRE.datum_abholung_ende.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.datum_aufladen.fn(),"NULL");
		
//		hmTemp.put(VPOS_TPA_FUHRE.datum_anlieferung.fn(),"SYSDATE");
//		hmTemp.put(VPOS_TPA_FUHRE.datum_anlieferung_ende.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.datum_abladen.fn(),"NULL");
		
		hmTemp.put(VPOS_TPA_FUHRE.eu_steuer_vermerk_ek.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.eu_steuer_vermerk_vk.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.id_tax_ek.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.id_tax_ek.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.steuersatz_ek.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.steuersatz_vk.fn(),"NULL");
		
		hmTemp.put(VPOS_TPA_FUHRE.abgeschlossen.fn(),"'N'");
		hmTemp.put(VPOS_TPA_FUHRE.notifikation_nr.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.notifikation_nr_ek.fn(),"NULL");
		
		hmTemp.put(VPOS_TPA_FUHRE.status_buchung.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.ist_storniert.fn(),"'N'");
		
		hmTemp.put(VPOS_TPA_FUHRE.storno_grund.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.storno_kuerzel.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.wiegekartenkenner_laden.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.wiegekartenkenner_abladen.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.fahrt_anfang_fp.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.fahrt_anfang_fp.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.anrufer_fp.fn(),"'<auto>'");
		hmTemp.put(VPOS_TPA_FUHRE.anrufdatum_fp.fn(),"SYSDATE");
		hmTemp.put(VPOS_TPA_FUHRE.kenner_alte_saetze_fp.fn(),"NULL");
		
		hmTemp.put(VPOS_TPA_FUHRE.fuhre_komplett.fn(),"'N'");
		hmTemp.put(VPOS_TPA_FUHRE.id_uma_kontrakt.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.id_handelsdef.fn(),"NULL");
		
		hmTemp.put(VPOS_TPA_FUHRE.erfasser_fp.fn(),bibALL.MakeSql(bibALL.get_KUERZEL()));

		hmTemp.put(VPOS_TPA_FUHRE.fahrt_anfang_fp.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.fahrt_ende_fp.fn(),"NULL");
		
		hmTemp.put(VPOS_TPA_FUHRE.manuell_preis_ek.fn(),"NULL");
		hmTemp.put(VPOS_TPA_FUHRE.manuell_preis_vk.fn(),"NULL");
		
		
		if (p_copyTyp == CopyTyp.FAHRPLAN_KOPIE) {
		
			hmTemp.put(VPOS_TPA_FUHRE.ist_geplant_fp.fn(),"'N'");

		} else if (p_copyTyp == CopyTyp.FAHRPLANPOOL_KOPIE) {
			
			hmTemp.put(VPOS_TPA_FUHRE.ist_geplant_fp.fn(),"'N'");
			
			hmTemp.put(VPOS_TPA_FUHRE.transportkennzeichen.fn(),"NULL");
			hmTemp.put(VPOS_TPA_FUHRE.anhaengerkennzeichen.fn(),"NULL");
			hmTemp.put(VPOS_TPA_FUHRE.id_maschinen_lkw_fp.fn(),"NULL");
			hmTemp.put(VPOS_TPA_FUHRE.id_maschinen_anh_fp.fn(),"NULL");
			hmTemp.put(VPOS_TPA_FUHRE.fahrer_fp.fn(),"NULL");

			hmTemp.put(VPOS_TPA_FUHRE.dat_fahrplan_fp.fn(),"NULL");
//			hmTemp.put(VPOS_TPA_FUHRE.dat_vorgemerkt_fp.fn(),"NULL");
//			hmTemp.put(VPOS_TPA_FUHRE.dat_vorgemerkt_ende_fp.fn(),"NULL");
		
		
		} else if (p_copyTyp == CopyTyp.FAHRPLANPOOL_PLANER) {
			
			hmTemp.put(VPOS_TPA_FUHRE.ist_geplant_fp.fn(),"'Y'");
			
			hmTemp.put(VPOS_TPA_FUHRE.fahrplangruppe_fp.fn(),"SEQ_"+bibALL.get_ID_MANDANT()+"_FAHRPLANGRUPPE.NEXTVAL");             //planung wird nicht als gruppe gehandelt
			
			hmTemp.put(VPOS_TPA_FUHRE.transportkennzeichen.fn(),"NULL");
			hmTemp.put(VPOS_TPA_FUHRE.anhaengerkennzeichen.fn(),"NULL");
			hmTemp.put(VPOS_TPA_FUHRE.id_maschinen_lkw_fp.fn(),"NULL");
			hmTemp.put(VPOS_TPA_FUHRE.id_maschinen_anh_fp.fn(),"NULL");
			hmTemp.put(VPOS_TPA_FUHRE.fahrer_fp.fn(),"NULL");

			hmTemp.put(VPOS_TPA_FUHRE.dat_fahrplan_fp.fn(),"NULL");

		}		
		
		//2021-02-26: gelangesbestaetigung erhalten muss raus
		hmTemp.put(VPOS_TPA_FUHRE.gelangensbestaetigung_erhalten.fn(),"NULL");

		
		
		
		return hmTemp;
	}
}
