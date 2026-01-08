package panter.gmbh.Echo2.components.E2_calendar;

import java.util.Date;
import java.util.Vector;

import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_calendar extends MyE2_Grid {

	private E2_calendar_grid_headpanel  	head_panel = null;
	private ownCalendarGrid			  		calendar_body = null;
	
	
	public abstract void  			do_day_button_action(ExecINFO execInfo)	throws myException;
	public abstract Vector<Date> 	get_DatesToHighlight() 	throws myException;
	
	public E2_calendar(String c_actual_day_formated) throws myException {
		super(1, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.calendar_body = new ownCalendarGrid(c_actual_day_formated);
		this.head_panel = new E2_calendar_grid_headpanel(this.calendar_body);
		this.calendar_body.set_layout_4_titel_month_year(MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,5,1,5),null,7,null));
		this.add(this.head_panel,E2_INSETS.I(1,1,1,1));
		this.add(this.calendar_body,E2_INSETS.I(1,1,1,1));
	}
	
	
	/*
	 * sucht sich die richtige seite und baut die komponente neu auf
	 */
	public void SPRINGE_AUF_DATUM(String cDateFormated) throws myException 	{
		this.calendar_body.SPRINGE_AUF_DATUM(cDateFormated);
		this.get_calendar_head_panel().SPRINGE_AUF_DATUM(cDateFormated);
	}

	/*
	 * sucht sich die richtige seite und baut die komponente neu auf
	 */
	public void SPRINGE_AUF_MONAT(int i_monat, int i_jahr) throws myException 	{
		this.calendar_body.SPRINGE_AUF_MONAT(i_monat, i_jahr);
		this.get_calendar_head_panel().SPRINGE_AUF_MONAT(i_monat, i_jahr);
	}

	private class ownCalendarGrid extends E2_calendar_grid {
		public ownCalendarGrid(String c_date_actual_formated) throws myException {
			super(c_date_actual_formated);
			this.set_style_4_titel_month_year(MyE2_Label.STYLE_NORMAL_BOLD());
			this.re_build();
		}

		@Override
		public void doDayButtonAction(ExecINFO execInfo) throws myException {
			E2_calendar.this.do_day_button_action(execInfo);
		}

		@Override
		public Vector<Date> get_DatesToHighlight() throws myException {
			return E2_calendar.this.get_DatesToHighlight();
		}
	}



	public E2_calendar_grid_headpanel get_calendar_head_panel() {
		return head_panel;
	}
	
	public E2_calendar_grid get_calendar_body() {
		return calendar_body;
	}
	
}
