package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS;

import java.util.Vector;

import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibARR;
import panter.gmbh.indep.bib_Settigs_Mandant;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.KUNDENSTATUS.STATKD_StatusErmittlung_Kreditversicherung;
import rohstoff.Echo2BusinessLogic.KUNDENSTATUS.STATKD_StatusErmittlung_Kreditversicherung.fehlerinfo;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_ADRESSE_extend;

public abstract class DA_Decision_CheckKreditVersicherung  extends DS_ActionAgent{

	public static String BUTTON_KENNER_ERLAUBE_UEBERSCHREITEN_KREDITLIMIT = "ERLAUBE_UEBERSCHREITEN_KREDITLIMIT";
	
	private Vector<STATKD_StatusErmittlung_Kreditversicherung> 		v_meldungen_kreditstatus = new Vector<>();
	
	public abstract Vector<String>  get_v_ids_fuhren_2_check_kredit() throws myException;
	
	public DA_Decision_CheckKreditVersicherung(DS_IF_components4decision p_actionComponent) {
		super(p_actionComponent);
	}

	@Override
	public Boolean make_decision_when_true_then_popup() throws myException {
		if (bib_Settigs_Mandant.get_SUPPRESS_KREDITSTATE_CHECK_BEFORE_PRINT_PAPERS()) {
			return false;
		}
		
		this.v_meldungen_kreditstatus.clear();
		Vector<String>  v_id_fuhren = new Vector<>();
		v_id_fuhren.addAll(this.get_v_ids_fuhren_2_check_kredit());
		
		boolean b_rueck = false;
		
		if (v_id_fuhren.size()>0) {
			for(String sIDFuhre: v_id_fuhren){
				STATKD_StatusErmittlung_Kreditversicherung oStatus = new STATKD_StatusErmittlung_Kreditversicherung();
				boolean bRet = oStatus.pruefeFuhre(sIDFuhre);
				if (!bRet) {
					this.v_meldungen_kreditstatus.add(oStatus);
					b_rueck = true;   // es gibt einen fehler und muss gemeldet werden
				}
			}
		} 
		
		return b_rueck;
	}

	@Override
	public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent)	throws myException {
		own_PopupContainer pop = new own_PopupContainer(activeComponent);
		pop.get_bt_OK().add_GlobalAUTHValidator_AUTO(DA_Decision_CheckKreditVersicherung.BUTTON_KENNER_ERLAUBE_UEBERSCHREITEN_KREDITLIMIT);
		return pop;
	}


	@Override
	public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
		MyE2_Grid grid_warnings = new MyE2_Grid(bibARR.ia(60,700), MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS());
		
		//titel
		grid_warnings._add(new MyE2_Label(new MyE2_String("Die Prüfung des Kreditstatus der beteiligten Firmen hat Fehler ergeben ..."),MyE2_Label.STYLE_TITEL_BIG()), 
																				new RB_gld()._span(5)._ins(E2_INSETS.I(2,5,2,10))._col(Color.RED))
							._add(new MyE2_Label(new MyE2_String("ID-Fuhre")),	new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._col(new E2_ColorDark()))
							._add(new MyE2_Label(new MyE2_String("Firmen in der Fuhre / korrelierter Fehler"),true),new RB_gld()._ins(E2_INSETS.I(2,2,2,2))._col(new E2_ColorDark()))
							;
		
		
		//pruefvektor, verhindert die doppelte listung von adressen
		for (STATKD_StatusErmittlung_Kreditversicherung kred_status: DA_Decision_CheckKreditVersicherung.this.v_meldungen_kreditstatus) {
			Vector<fehlerinfo> v_messages = kred_status.get_v_fehler();
			if (v_messages.size()>0) {
				MyE2_Label label_id_fuhre = new MyE2_Label(kred_status.get_REC_FUHRE().get_ID_VPOS_TPA_FUHRE_cF());
				
				E2_Grid4MaskSimple grid_fehler = new E2_Grid4MaskSimple().def_(E2_INSETS.I(1,1,1,3));
				for (fehlerinfo fehler: kred_status.get_v_fehler()) {
					grid_fehler.add_(new MyE2_Label(new RECORD_ADRESSE_extend(fehler.id_adresse).get_Signatur_String_kurz(),true));
					grid_fehler.add_(new MyE2_Label(fehler.fehlertext,true));
				}
				grid_fehler.setSize_(bibARR.ia(300,300));
				
				grid_warnings._add(label_id_fuhre,new RB_gld()._ins(E2_INSETS.I(2,2,2,2)))
							._add(grid_fehler,new RB_gld()._ins(E2_INSETS.I(2,2,2,2)))
							;
				
			}
		}
		
		container.add(grid_warnings,E2_INSETS.I(3,3,3,3));
		
		container.get_bt_OK().setText(new MyE2_String("OK, trotzdem weitermachen").CTrans());
		container.get_bt_NO().setText(new MyE2_String("Vorgang abbrechen").CTrans());
		
		//2016-04-18: bug
		//container.get_bt_OK().set_bEnabled_For_Edit(!bib_Settigs_Mandant.get_VERBIETE_UEBERSTIMMEN_FEHLENDEN_HANDELSVERTRAG());	
		
		container.add(new E2_Grid4MaskSimple(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS()).def_(E2_INSETS.I(0,0,20,0)).add_(container.get_bt_OK()).add_(container.get_bt_NO()),E2_INSETS.I(3,10,3,2));
	}
	
	
	private class own_PopupContainer  extends DS_PopupContainer4Decision {

		public own_PopupContainer(DS_IF_components4decision p_motherComponent) throws myException {
			super(p_motherComponent);
		}
		
		
		@Override
		public int get_width_in_pixel() {
			return 680;
		}

		@Override
		public int get_height_in_pixel() {
			return 450;
		}

		@Override
		public MyE2_String get_titleText4PopUp() {
			return new MyE2_String("Prüfung auf Kreditstatus ...");
		}
		
	}

	
}
