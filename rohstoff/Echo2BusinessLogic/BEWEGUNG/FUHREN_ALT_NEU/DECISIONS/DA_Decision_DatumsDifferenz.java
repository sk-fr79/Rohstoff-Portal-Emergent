package rohstoff.Echo2BusinessLogic.BEWEGUNG.FUHREN_ALT_NEU.DECISIONS;

import java.util.ArrayList;
import java.util.Comparator;

import nextapp.echo2.app.Alignment;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_ActionAgent;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_IF_components4decision;
import panter.gmbh.Echo2.ActionEventTools.DecisionSimple.DS_PopupContainer4Decision;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.components.E2_Grid4MaskSimple;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;

public class DA_Decision_DatumsDifferenz extends DS_ActionAgent {
	
	private int                				allowedDifferenceInDays = 6;
	
	private collector_Date_values           date_collector = null;
	
	public DA_Decision_DatumsDifferenz(DS_IF_components4decision p_actionComponent) {
		super(p_actionComponent);
		
		try {
			this.allowedDifferenceInDays = (int)bibALL.get_RECORD_MANDANT().get_ALLOWED_DATE_DIFF_lValue(-6l).longValue();
		} catch (myException e) {
			e.printStackTrace();
			this.allowedDifferenceInDays = 3;
		}
		
	}
	

	public collector_Date_values get_date_collector() {
		return date_collector;
	}


	public void set_date_collector(collector_Date_values p_date_collector) {
		this.date_collector = p_date_collector;
	}

	@Override
	public void fill_popupContainer(DS_PopupContainer4Decision container) throws myException {
	}

	
	@Override
	public Boolean make_decision_when_true_then_popup() throws myException {
		
		if (this.date_collector==null){
			throw new myException(this,"no Datecollector definined !!");
		}
		
		ArrayList<ArrayList<MyDate>> dates_of_all_fuhren = this.date_collector.get_dateSammlungen();
		boolean b_rueck = false;
		
		for (ArrayList<MyDate> listDates: dates_of_all_fuhren) {
			//zuerst die dateValuesInProcess sortieren
			listDates.sort(new Comparator<MyDate>() {
				@Override
				public int compare(MyDate o1, MyDate o2) {
					if (o1!=null && o2!=null && o1.get_bOK() && o2.get_bOK()) {
						return o1.get_cDBFormatErgebnis_4_SQLString().compareTo(o2.get_cDBFormatErgebnis_4_SQLString());
					}
					return 0;
				}
			});
			
			//jetzt die differenz zwischen erstem und letztem datum rechnen
			if (listDates.size()<2) {
				continue;
			}
			
			MyDate date1 = listDates.get(0);
			MyDate date2 = listDates.get(listDates.size()-1);
			
			//DEBUG.System_println("Vergleiche: "+date1.get_cDBFormatErgebnis_4_SQLString()+" ---- "+date2.get_cDBFormatErgebnis_4_SQLString());
			
			Long diff = MyDate.get_DayDifference_Date2_MINUS_Date1(date1, date2);
			
			if (diff==null) {
				continue;
			} else {
				diff = Math.abs(diff);
				if (diff.longValue()>this.allowedDifferenceInDays) {
					b_rueck = true;  //mindestens eine fuhre falsch
				}
			}
		}
		return b_rueck;
	}

	@Override
	public DS_PopupContainer4Decision generate_popupContainer(DS_IF_components4decision activeComponent) 	throws myException {
		return new ownPopupContainer(this.get_actionComponent());
	}

	
	
	private class ownPopupContainer extends DS_PopupContainer4Decision {

		public ownPopupContainer(DS_IF_components4decision p_motherComponent) throws myException {
			super(p_motherComponent);
			
			int[] i_breite = {180,180};
			
			this.get_bt_OK().set_Text(new MyE2_String("Ja - Weiter"));
			this.get_bt_NO().set_Text(new MyE2_String("Nein - Abbruch"));
			
			DA_Decision_DatumsDifferenz oThis = DA_Decision_DatumsDifferenz.this;
			E2_Grid4MaskSimple  gm = new E2_Grid4MaskSimple()
					.def_(2,1)
					.def_(E2_INSETS.I(2,10,10,2))
					.def_(new Alignment(Alignment.LEFT, Alignment.CENTER))
					.def_(new E2_ColorDDark())
					.add_(new MyE2_Label(new MyE2_String("Es existieren Datumsdifferenzen größer als die erlaubten ")
													.ut(" "+oThis.allowedDifferenceInDays)
													.t(" Tage zwischen Abholung und Anlieferung! ")
													.t(" Wollen Sie trotzdem fortfahren ?"),true))
					.def_(1,1)
					.def_(E2_INSETS.I(2,10,10,2))
					.def_(new E2_ColorBase())
					.add_(this.get_bt_OK())
					.add_(this.get_bt_NO())
					.setSize_(i_breite);
			
			this.add(gm,E2_INSETS.I(5,10,5,10));
			
		}

		@Override
		public int get_width_in_pixel() {
			return 400;
		}

		@Override
		public int get_height_in_pixel() {
			return 200;
		}

		@Override
		public MyE2_String get_titleText4PopUp() {
			return new MyE2_String("Bitte entscheiden Sie ...");
		}
		
	}
	
	
	/*
	 * gibt fuer jede fuhre eine arraylist mit n datumswerten, deren zeitdifferenz geprueft wird.
	 */
	public abstract class collector_Date_values {
		public abstract ArrayList<ArrayList<MyDate>> get_dateSammlungen() throws myException;
	}


	@Override
	public MyE2_MessageVector is_something_to_do_before_ok_is_possible() throws myException {
		return null;
	}


}
