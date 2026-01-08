package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS;

import java.util.Vector;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossingChecker;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossingInfo;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossingInfoCollector;
import rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.BORDERCONTROL.TOOLS.BorderCrossing_Record;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_BORDERCROSSING_EXT;


/**
 * 
 * @author martin
 * decision-agent der biem Fuhrendruck prüft, ob eine meldung erzeugt werden muss betreffs grenzueberschreitung
 */

public class DA_Decision_BorderCrossing extends DS_ActionAgent {
	
	private BorderCrossingInfoCollector 	f_borderCrossingInfoCollector = null;
	private Vector<BorderCrossingInfo>   	v_critical_transactions = new Vector<>();

	public DA_Decision_BorderCrossing(DS_IF_components4decision p_actionComponent) {
		super(p_actionComponent);
	}
	


	@Override
	public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
		
		container.RESET_Content();
		
		int[] i_breite = {100,100,300};

		RB_gld g1 = new RB_gld()._ins(E2_INSETS.I(1,1,1,1));
		RB_gld g2 = new RB_gld()._ins(E2_INSETS.I(1,1,1,1))._col(new E2_ColorDark());
		
		container.get_bt_OK().set_Text(new MyE2_String("Ja - Erzeuge Erinnerung"));
		container.get_bt_NO().set_Text(new MyE2_String("Nein - Abbruch"));
		
		E2_Grid4MaskSimple  gm = new E2_Grid4MaskSimple()
				.def_(3,1).def_(MyE2_Grid.STYLE_GRID_DDARK_BORDER_NO_INSETS())
				.def_(E2_INSETS.I(2,5,5,5))
				.def_(new Alignment(Alignment.LEFT, Alignment.CENTER))
				.def_(new E2_ColorDark())
				.add_(new MyE2_Label(new MyE2_String("In der selektierten Auswahl befinden sich meldepflichtige Grenzübertritte!")
												.t(" Wenn Sie fortfahren, werden Erinnerungen erzeugt! Fortfahren ?"),true))
				.def_(1,1)
				._add(new MyE2_Label(new MyE2_String("Land Quelle")), g1)
				._add(new MyE2_Label(new MyE2_String("Land Ziel")), g1)
				._add(new MyE2_Label(new MyE2_String("Meldungstext")), g1)
				.def_(new E2_ColorBase());

				
		for (BorderCrossingInfo wb: this.v_critical_transactions) {
			E2_Grid4MaskSimple gms_Texte= new E2_Grid4MaskSimple()
						._add(new MyE2_Label(wb.f_record_bordercross.getTitle(""),true),g2)
						._add(new MyE2_Label(wb.f_record_bordercross.getMessage(""),true),g1)._setSize(300);
			gm._add(wb.get_label_start(), g1)._add(wb.get_label_ziel(),g1)._add(gms_Texte, g1);
		}
		gm.setSize_(i_breite);
		
		container.add(gm,E2_INSETS.I(5,10,2,2));
		container.add(
				new E2_Grid4MaskSimple()._setSize(400).def_(E2_INSETS.I(2,2,5,2)).add_(	container.get_bt_OK())
				.add_(	container.get_bt_NO()),E2_INSETS.I(5,15,2,2));
		
	}

	
	@Override
	public Boolean make_decision_when_true_then_popup() throws myException {

		this.v_critical_transactions.clear();
		
		if (this.f_borderCrossingInfoCollector==null){
			throw new myException(this,"no collector_warenbewegung is definined !!");
		}
		
		
		this.v_critical_transactions.addAll(new BorderCrossingChecker(this.f_borderCrossingInfoCollector.get_al_BorderCrossingInfo()).get_v_critical_transactions());
		
		
//		RECLIST_BORDERCROSSING  rl_bo = new RECLIST_BORDERCROSSING(new SEL(_TAB.bordercrossing).FROM(_TAB.bordercrossing).s());
//		
//		if (rl_bo.size()>0) {
//			
//			ArrayList<BorderCrossingInfo> borderCrossingInfos = this.f_borderCrossingInfoCollector.get_al_BorderCrossingInfo();
//			
//			for (BorderCrossingInfo bdc_info: borderCrossingInfos) {
//				for (RECORD_BORDERCROSSING  rb: rl_bo) {
//					RECORD_BORDERCROSSING_EXT  rbe = new  RECORD_BORDERCROSSING_EXT(rb);
//					BorderCrossingInfo wb = bdc_info.get_Copy();     //kopie erzeugen, falls es mehrere meldungen fuer eine warenbewegung gibt
//					if (rbe.must_be_warned(wb)) {
//						//jetzt nachsehen, ob es den reminder nicht bereits gibt
//						if (!rbe.reminder_even_exists(wb)) { 
//							wb.set_bordercrossing_that_causes_warning(rbe);
//							this.v_critical_transactions.add(wb);
//						}
//					}
//				}
//			}
//		}
		return this.v_critical_transactions.size()>0;
	}

	
	
	
	@Override
	public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent) 	throws myException {
		return new ownPopupContainer(this.get_actionComponent());
	}

	
	
	private class ownPopupContainer extends DS_PopupContainer4Decision {

		public ownPopupContainer(DS_IF_components4decision p_motherComponent) throws myException {
			super(p_motherComponent);
		}

		@Override
		public int get_width_in_pixel() {
			return 800;
		}

		@Override
		public int get_height_in_pixel() {
			return 300;
		}

		@Override
		public MyE2_String get_titleText4PopUp() {
			return new MyE2_String("Meldungsrelevante Grenzübertritte sind anhängig. Bitte beachten!");
		}
		
	}
	
	
//	/*
//	 * gibt fuer jede fuhre eine arraylist mit n datumswerten, deren zeitdifferenz geprueft wird.
//	 */
//	public abstract class collector_warenbewegung {
//		public abstract ArrayList<DA_Decision_BorderCrossing_WARENBEWEGUNG> get_warenbewegung() throws myException;
//	}
//


	
	public void set_BorderCrossingInfoCollector(BorderCrossingInfoCollector p_collector_warenbewegung) {
		this.f_borderCrossingInfoCollector = p_collector_warenbewegung;
	}
	



	@Override
	public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
		
		MyE2_MessageVector  mv = new MyE2_MessageVector();
		
		Vector<BorderCrossingInfo>   v_criticalTransactions = DA_Decision_BorderCrossing.this.v_critical_transactions;
		
		for (BorderCrossingInfo wb: v_criticalTransactions) {
//			RECORD_BORDERCROSSING_EXT rb_ext = wb.f_record_bordercross;
//			mv.add_MESSAGE(rb_ext.write_reminder_def(wb));
			BorderCrossing_Record rb = wb.f_record_bordercross;
			mv.add_MESSAGE(rb.write_reminder_def(wb));
		}
		
		if (mv.get_bIsOK()) {
			mv.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Es wurden Erinnerungen aktiviert!")));
		}
		
		return mv;
	}
	
	
	
	
}
