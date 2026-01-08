package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS;

import java.util.ArrayList;
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ARTIKEL_BEZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_LAND;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

/**
 * Pruefvarianten:  
 *                 1. EU -> EU:             wenn gleiches Land, dann keine Pruefung, wenn unterschiedlich, dann bei allen Teilnehmern pruefen, die nicht im Land des mandanten (hier D) sind
 *                 2. NICHT EU -> EU:       dann bei allen Teilnehmern pruefen, die nicht im Land des mandanten (hier D) sind
 *                 3. NICHT-EU -> NICHT-EU  keine Prüfung
 *                 
 * @author martin
 *
 */

public class DA_DecisionHat_EU_VERTRAG extends DS_ActionAgent {

	private collector_Relations  									collector = null;
	private ArrayList<DA_DecisionHat_EU_VERTRAG_warenbewegung>		v_daten_zu_pruefen= new ArrayList<>();
	
	//die mandantensettings checken
	private boolean b_check_jur_ort = bib_Settigs_Mandant.get_EU_VERTRAG_PRUEFUNG_AN_JURISTISCHER_ADRESSE();
	private boolean b_check_geo_ort = bib_Settigs_Mandant.get_EU_VERTRAG_PRUEFUNG_AN_GEOGRAFISCHER_ADRESSE();

	
	public enum FEHLERSTATUS {
		UNBEWERTET(false, new MyE2_String("unbewertet"))
		,KEIN_FEHLER(false, new MyE2_String("OK"))
		,KEIN_VERTRAG(true, new MyE2_String("Kein EU-Vertrag vorhanden"))
		,VERTRAG_ABGELAUFEN(true, new MyE2_String("EU-Vertrag ist abgelaufen"))
		;
		private boolean relevant_4_warn =false;
		private FEHLERSTATUS(boolean relevant4warn, MyE2_String warnung) {
			this.relevant_4_warn=relevant4warn;
		}
		public boolean is_relevant_4_warn() {
			return relevant_4_warn;
		}
	}

	
	public DA_DecisionHat_EU_VERTRAG(DS_IF_components4decision p_actionComponent) {
		super(p_actionComponent);
	}

	
	@Override
	public Boolean make_decision_when_true_then_popup() throws myException {
		this.v_daten_zu_pruefen.clear();
		this.v_daten_zu_pruefen.addAll(this.collector.get_datasets());
		
		for (DA_DecisionHat_EU_VERTRAG_warenbewegung ds: this.v_daten_zu_pruefen) {
			if (this.has_relevant_station(ds) && ds.is_real_business() && (!ds.is_pruefung_unterdrueckt())) {
				if(ds.date_execution!=null && ds.date_execution.get_bOK()) {
					this.check_and_set_status_in_warenbewegung(ds);
				}
			}
		}
		boolean b_hat_fehler = false;
		for (DA_DecisionHat_EU_VERTRAG_warenbewegung ds: this.v_daten_zu_pruefen) {
			b_hat_fehler=b_hat_fehler||ds.has_fehlerstatus();
		}
		
		return b_hat_fehler;
	}

	
	
	@Override
	public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent) 	throws myException {
		return new ownPopupContainer(activeComponent);
	}

	@Override
	public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
		MyE2_Grid grid_warnings = new MyE2_Grid(bibARR.ia(60,200,200,200), MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		
		//titel
		grid_warnings._add(new MyE2_Label(new MyE2_String("Die Prüfung der EU-Verträge der beteiligten Firmen hat Fehler ergeben ..."),MyE2_Label.STYLE_TITEL_BIG_BOLD_RED()), new RB_gld()._span(5)._ins(E2_INSETS.I(2,5,2,10)))
							._add(new MyE2_Label(new MyE2_String("ID-Firma")),new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._col(new E2_ColorDark()))
							._add(new MyE2_Label(new MyE2_String("Firma"),true),new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._col(new E2_ColorDark()))
							._add(new MyE2_Label(new MyE2_String("Lieferadresse"),true),new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._col(new E2_ColorDark()))
							._add(new MyE2_Label(new MyE2_String("Vermerk"),true),new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._col(new E2_ColorDark()))
							;
		
		
		//pruefvektor, verhindert die doppelte listung von adressen
		Vector<String>  v_schon_in_list = new Vector<>();
		for (DA_DecisionHat_EU_VERTRAG_warenbewegung wd: DA_DecisionHat_EU_VERTRAG.this.v_daten_zu_pruefen) {
			if (wd.has_fehlerstatus()) {
				Vector<String[]> v_rueck = wd.get_fehlerliste();
				
				for (String[] ar: v_rueck) {
					String c_test = S.NN(ar[0])+S.NN(ar[1])+S.NN(ar[2])+S.NN(ar[3]);
					if (v_schon_in_list.contains(c_test)) {
						continue;
					}
					v_schon_in_list.add(c_test);
					grid_warnings._add(new MyE2_Label(ar[0]),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)))
									._add(new MyE2_Label(ar[1],true),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)))
									._add(new MyE2_Label(ar[2],true),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)))
									._add(new MyE2_Label(ar[3],true),new RB_gld()._ins(E2_INSETS.I(2,2,2,2)))
									;
				}
			}
			
		}
		
		container.add(grid_warnings,E2_INSETS.I(3,3,3,3));
		
		container.get_bt_OK().setText(new MyE2_String("OK, trotzdem weitermachen").CTrans());
		container.get_bt_NO().setText(new MyE2_String("Vorgang abbrechen").CTrans());
		
		container.get_bt_OK().set_bEnabled_For_Edit(!bib_Settigs_Mandant.get_VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG());	
		
		container.add(new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()).add_(container.get_bt_OK()).add_(container.get_bt_NO()),E2_INSETS.I(3,10,3,2));

	}

	
	/*
	 * gibt fuer jede fuhre eine arraylist mit n datumswerten, deren zeitdifferenz geprueft wird.
	 */
	public abstract class collector_Relations {
		public abstract ArrayList<DA_DecisionHat_EU_VERTRAG_warenbewegung> get_datasets() throws myException;
	}

	public collector_Relations get_collector_adress_pairs() {
		return collector;
	}

	public void set_collector_relations(collector_Relations p_collector) {
		this.collector = p_collector;
	}

	/*
	 * relevant ist eine Adress-kombination, wenn die Adressen in zwei verschiedenen EU-Laendern liegen
	 */
	private boolean has_relevant_station(DA_DecisionHat_EU_VERTRAG_warenbewegung wb) throws myException { 
		boolean b_rueck = 		false;
		
		if (wb.adress_start==null || wb.adress_ziel==null) {
			return b_rueck;
		}
		
		RECORD_LAND  rl_start = wb.adress_start.get_UP_RECORD_LAND_id_land();
		RECORD_LAND  rl_ziel = 	wb.adress_ziel.get_UP_RECORD_LAND_id_land();
		
		RECORD_LAND  rl_mandant = bibALL.get_RECORD_MANDANT().get_UP_RECORD_LAND_id_land();
		
		if (rl_mandant==null) {
			throw new myException(this, "Mandant has no ID_LAND -- please set this !!");
		}
		
		
		if (rl_start != null && rl_ziel != null) {
			if (rl_start.get_ID_LAND_cUF().equals(rl_ziel.get_ID_LAND_cUF())) {
				b_rueck = false;          //ware bleibt im land
			} else {
				if (rl_start.is_INTRASTAT_JN_YES() || rl_ziel.is_INTRASTAT_JN_YES()) {
					boolean b_must_check_left = (rl_start.is_INTRASTAT_JN_YES() && (!rl_start.get_ID_LAND_cUF().equals(rl_mandant.get_ID_LAND_cUF())));
					boolean b_must_check_right = (rl_ziel.is_INTRASTAT_JN_YES() && (!rl_ziel.get_ID_LAND_cUF().equals(rl_mandant.get_ID_LAND_cUF())));
					b_rueck=b_must_check_left||b_must_check_right;   //mindestens ein eu-land ausserhalb D
					wb.set_left_is_relevant(b_must_check_left);
					wb.set_right_is_relevant(b_must_check_right);
					
				}
			}
		} else {
			b_rueck = true;
		}
		return b_rueck;
	}
	


	
	private void check_and_set_status_in_warenbewegung(DA_DecisionHat_EU_VERTRAG_warenbewegung wb) throws myException {
		
		boolean b_private_auch_pruefen = bib_Settigs_Mandant.get_PRUEFE_EU_VERTRAEGE_BEI_PRIVATADRESSE();
				
		//zuerst linke seite
		RECORD_ADRESSE_extend  ra_e = new RECORD_ADRESSE_extend(wb.adress_start);
		
		if (b_private_auch_pruefen || (!ra_e.is_privat_adresse())) {
			if (wb.is_left_is_relevant() && this.get_bArtikelMustBeChecked(wb.sort_at_start)) {
				if (!ra_e.get_main_Adress().get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""))) {
					if (ra_e.is_main_adress()) {
						if (this.b_check_geo_ort||this.b_check_jur_ort) {
							String c_eu_vertrag = ra_e.get_EU_BEIBLATT_EK_VERTRAG_cF_NN("");
							if (S.isEmpty(c_eu_vertrag)) {
								wb.set_status_left(FEHLERSTATUS.KEIN_VERTRAG,  false);
							} else {
								if (myDateHelper.get_Date1_Less_Date2(c_eu_vertrag, wb.date_execution.get_cDateStandardFormat())) {
									wb.set_status_left(FEHLERSTATUS.VERTRAG_ABGELAUFEN,  false);
								}
							}
						}
					} else {		/*ist lieferadresse*/
						if (this.b_check_jur_ort) {
							String c_eu_vertrag = ra_e.get_main_Adress().get_EU_BEIBLATT_EK_VERTRAG_cF_NN("");
							if (S.isEmpty(c_eu_vertrag)) {
								wb.set_status_left(FEHLERSTATUS.KEIN_VERTRAG,  false);
							} else {
								if (myDateHelper.get_Date1_Less_Date2(c_eu_vertrag, wb.date_execution.get_cDateStandardFormat())) {
									wb.set_status_left(FEHLERSTATUS.VERTRAG_ABGELAUFEN,  false);
								}
							}
						}
						if (this.b_check_geo_ort) {
							String c_eu_vertrag = ra_e.get_EU_BEIBLATT_EK_VERTRAG_cF_NN("");
							if (S.isEmpty(c_eu_vertrag)) {
								wb.set_status_left(FEHLERSTATUS.KEIN_VERTRAG,  true);
							} else {
								if (myDateHelper.get_Date1_Less_Date2(c_eu_vertrag, wb.date_execution.get_cDateStandardFormat())) {
									wb.set_status_left(FEHLERSTATUS.VERTRAG_ABGELAUFEN,  true);
								}
							}
						}
					}
				}
			}
		}
		
		
		//dann rechte seite
		ra_e = new RECORD_ADRESSE_extend(wb.adress_ziel);
		
		if (b_private_auch_pruefen || (!ra_e.is_privat_adresse())) {

			if (wb.is_right_is_relevant() && this.get_bArtikelMustBeChecked(wb.sort_at_target)) {
				if (!ra_e.get_main_Adress().get_ID_ADRESSE_cUF().equals(bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF_NN(""))) {
					if (ra_e.is_main_adress()) {
						if (this.b_check_geo_ort||this.b_check_jur_ort) {
							String c_eu_vertrag = ra_e.get_EU_BEIBLATT_VK_VERTRAG_cF_NN("");
							if (S.isEmpty(c_eu_vertrag)) {
								wb.set_status_right(FEHLERSTATUS.KEIN_VERTRAG, false);
							} else {
								if (myDateHelper.get_Date1_Less_Date2(c_eu_vertrag, wb.date_execution.get_cDateStandardFormat())) {
									wb.set_status_right(FEHLERSTATUS.VERTRAG_ABGELAUFEN, false);
								}
							}
						}
					} else {
						if (this.b_check_jur_ort) {
							String c_eu_vertrag = ra_e.get_main_Adress().get_EU_BEIBLATT_VK_VERTRAG_cF_NN("");
							if (S.isEmpty(c_eu_vertrag)) {
								wb.set_status_right(FEHLERSTATUS.KEIN_VERTRAG, false);
							} else {
								if (myDateHelper.get_Date1_Less_Date2(c_eu_vertrag, wb.date_execution.get_cDateStandardFormat())) {
									wb.set_status_right(FEHLERSTATUS.VERTRAG_ABGELAUFEN,  false);
								}
							}
						}
						if (this.b_check_geo_ort) {
							String c_eu_vertrag = ra_e.get_EU_BEIBLATT_VK_VERTRAG_cF_NN("");
							if (S.isEmpty(c_eu_vertrag)) {
								wb.set_status_right(FEHLERSTATUS.KEIN_VERTRAG, true);
							} else {
								if (myDateHelper.get_Date1_Less_Date2(c_eu_vertrag, wb.date_execution.get_cDateStandardFormat())) {
									wb.set_status_right(FEHLERSTATUS.VERTRAG_ABGELAUFEN, true);
								}
							}
						}
					}
				}
			}
		}
	}

	
	
	/**
	 * nur rohstoffsorten muessen gecheckt werden
	 * @param recArtikelBez
	 * @return
	 * @throws myException
	 */
	private boolean get_bArtikelMustBeChecked(RECORD_ARTIKEL_BEZ recArtikelBez) throws myException {
		if (recArtikelBez!=null && 
			recArtikelBez.get_UP_RECORD_ARTIKEL_id_artikel()!=null &&
			recArtikelBez.get_UP_RECORD_ARTIKEL_id_artikel().is_IST_PRODUKT_NO() &&
			recArtikelBez.get_UP_RECORD_ARTIKEL_id_artikel().is_END_OF_WASTE_NO() &&
			recArtikelBez.get_UP_RECORD_ARTIKEL_id_artikel().is_DIENSTLEISTUNG_NO()) {
			return true;
		}
		return false;
	}
	

	
	
	private class ownPopupContainer  extends DS_PopupContainer4Decision {

		public ownPopupContainer(DS_IF_components4decision p_motherComponent) throws myException {
			super(p_motherComponent);
		}
		
		
		@Override
		public int get_width_in_pixel() {
			return 680;
		}

		@Override
		public int get_height_in_pixel() {
			return 280;
		}

		@Override
		public MyE2_String get_titleText4PopUp() {
			return new MyE2_String("Prüfung auf korrekten EU-Vertrag ...");
		}
		
	}

	@Override
	public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
		return null;
	}
	
}
