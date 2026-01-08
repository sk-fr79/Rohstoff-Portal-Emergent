package panter.gmbh.Echo2.components.E2_calendar;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;

import nextapp.echo2.app.Font;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.dataToView;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public class E2_calendar_grid_headpanel extends MyE2_Grid {

	private E2_calendar_grid  				calendar = null;
	private LinkedHashMap<String, String> 		hm_years = new LinkedHashMap<>();
	private select_field_years 				sel_years = null;
	private select_field_months 			sel_months = null;
	private GridLayoutData  				gl_4_headpanel = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(0,1,0,1));
	private Font 							font_4_dropdowns = new E2_FontPlain(-2);
	
	private MyE2_Button  					bt_now = new ButtonToday();
	private MyE2_Button  					bt_month_left = new ButtonMonthMinus();
	private MyE2_Button  					bt_month_right = new ButtonMonthPlus();
	private MyE2_Button  					bt_year_left = new ButtonYearMinus();
	private MyE2_Button  					bt_year_right = new ButtonYearPlus();
	
	
	public E2_calendar_grid_headpanel(E2_calendar_grid  p_calendar) throws myException {
		super(7, MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());
		
		this.calendar = p_calendar;
		this.sel_months = new select_field_months();
		
		//jahres bereich festlegen
		int highest_year = new GregorianCalendar().get(Calendar.YEAR)+10;

		//2016-04-25: reihenfolge umdrehen
//		for (int i=highest_year-60;i<highest_year;i++) {
//			this.hm_years.put(""+i, ""+i);
//		}
//		
		
		for (int i=highest_year;i>highest_year-60;i--) {
			this.hm_years.put(""+i, ""+i);
		}

		
		this.sel_years = new select_field_years();
		this.re_build();
		
	}
	
	
	public void re_build() throws myException {
		this.removeAll();
		this.sel_months.set_ActiveValue_OR_FirstValue(""+this.calendar.get_month());
		this.sel_years.set_ActiveValue_OR_FirstValue(""+this.calendar.get_year());
		this.add(this.bt_now,this.gl_4_headpanel);
		this.add(this.bt_month_left,this.gl_4_headpanel);
		this.add(this.sel_months,this.gl_4_headpanel);
		this.add(this.bt_month_right,this.gl_4_headpanel);
		this.add(this.bt_year_left,this.gl_4_headpanel);
		this.add(this.sel_years,this.gl_4_headpanel);
		this.add(this.bt_year_right,this.gl_4_headpanel);
		
	}
	
	/*
	 * sucht sich die richtige seite und baut die komponente neu auf
	 */
	public void SPRINGE_AUF_DATUM(String cDateFormated) throws myException 	{
		MyDate  oDate = new MyDate(cDateFormated);
		
		//bei falsch oder leer auf das aktuelle datum
		if (!oDate.get_bOK()) {
			oDate = new MyDate(myDateHelper.get_actual_date_formated());
		}

		
		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK)) {
			MyInteger intMonat = oDate.get_INT_MONAT();
			MyInteger intJahr = oDate.get_INT_JAHR();
			String c_jahr = intJahr.get_cUF_IntegerString();
			
			if (!this.sel_years.get_DataToView().get_bHasData(c_jahr)) {
				this.sel_years.check_and_add_year(c_jahr);
			}
			
			this.sel_months.set_ActiveValue_OR_FirstValue(intMonat.get_cUF_IntegerString());
			this.sel_years.set_ActiveValue_OR_FirstValue(c_jahr);
			
			this.re_build();
		}
	}

	
	
	/*
	 * sucht sich die richtige seite und baut die komponente neu auf
	 */
	public void SPRINGE_AUF_MONAT(int i_monat, int i_jahr) throws myException 	{

		if (i_monat<1 || i_monat>12) {
			throw new myException(this, "Month with number: "+i_monat+" is not existing !");
		}
		if (i_jahr<1) {
			throw new myException(this, "Year must be > 0!");
		}
		
		String c_jahr = ""+i_jahr;
		String c_monat = ""+i_monat;
		if (c_monat.length()<2) { c_monat=("0"+c_monat);}
		
		if (!this.sel_years.get_DataToView().get_bHasData(c_jahr)) {
			this.sel_years.check_and_add_year(c_jahr);
			this.sel_months.set_ActiveValue_OR_FirstValue(c_monat);
			this.sel_years.set_ActiveValue_OR_FirstValue(c_jahr);
			this.re_build();
		}
	}

	
	
	private class ButtonMonthMinus extends MyE2_Button {
		public ButtonMonthMinus() {
			super(E2_ResourceIcon.get_RI("flat_left.png"), true);
			this.setToolTipText(new MyE2_String("1 Monat zurück").CTrans());
			this.add_oActionAgent(new ownAction());
		}

		private class ownAction extends XX_ActionAgent  {
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				E2_calendar_grid_headpanel.this.calendar.decrease_month(1);
				E2_calendar_grid_headpanel.this.re_build();
			}
		}
	}

	private class ButtonMonthPlus extends MyE2_Button
	{
		public ButtonMonthPlus() 
		{
			super(E2_ResourceIcon.get_RI("flat_right.png"), true);
			this.setToolTipText(new MyE2_String("1 Monat vor").CTrans());

			this.add_oActionAgent(new ownAction());
		}

		private class ownAction extends XX_ActionAgent  {
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				E2_calendar_grid_headpanel.this.calendar.increase_month(1);
				E2_calendar_grid_headpanel.this.re_build();
			}
		}
	}

	
	private class ButtonToday extends MyE2_Button 	{
		public ButtonToday() 	{
			super(E2_ResourceIcon.get_RI("flat_right_left.png"), true);
			this.setToolTipText(new MyE2_String("Springe zum aktuellen Monat").CTrans());
			this.add_oActionAgent(new ownAction());
		}

		private class ownAction extends XX_ActionAgent		{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException	{
				E2_calendar_grid_headpanel oThis = E2_calendar_grid_headpanel.this;
				
				myDateHelper oHelper= null;
				oHelper = new myDateHelper(new Date());
				oThis.calendar.set_month(oHelper.get_IMonth());
				oThis.calendar.set_year(oHelper.get_IYear());
				oThis.calendar.re_build();
				E2_calendar_grid_headpanel.this.re_build();
			}
		}
	}

	
	
	private class select_field_months extends MyE2_SelectField{
		public select_field_months() throws myException {
			super();
			this.setFont(E2_calendar_grid_headpanel.this.get_font_4_dropdowns());
			this.set_ListenInhalt(MONAT.monate_kurz_4_dropdown(),false)  ;
			this.set_ActiveValue_OR_FirstValue( Integer.toString(E2_calendar_grid_headpanel.this.calendar.get_month()) );
			this.add_oActionAgent( new ownAction() ) ;
		}
		
		private class ownAction extends XX_ActionAgent{
			public ownAction() {
				super();
			}
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				// Monat übernehmen 
				E2_calendar_grid_headpanel.this.calendar.set_month(Integer.parseInt( select_field_months.this.get_ActualWert()));
				E2_calendar_grid_headpanel.this.calendar.re_build();
			}
		}
	}
	
	
	
	/**
	 * SelectField der Jahre als DropDown
	 * 
	 */
	private class select_field_years extends MyE2_SelectField{
		
		public select_field_years() throws myException {
			super();
			this.setFont(E2_calendar_grid_headpanel.this.get_font_4_dropdowns());
			dataToView dw = new dataToView(E2_calendar_grid_headpanel.this.hm_years, false);

			this.set_oDataToView(dw)  ;
			this.set_ActiveInhalt_or_FirstInhalt(Integer.toString(E2_calendar_grid_headpanel.this.calendar.get_year()));
			
			this.add_oActionAgent( new ownAction() ) ;
		}

		/**
		 * pruefen, ob der anwender ueber die grenzen geblaettert hat
		 * @param c_year
		 * @throws myException
		 */
		public void check_and_add_year(String c_year) throws myException {
			if (!E2_calendar_grid_headpanel.this.hm_years.containsKey(c_year)) {
				E2_calendar_grid_headpanel.this.hm_years.put(c_year, c_year);
				this.set_oDataToView(new dataToView(E2_calendar_grid_headpanel.this.hm_years, false));
				this.set_ActiveInhalt_or_FirstInhalt(c_year);
			}
		}
		
		/**
		 * Eventhandler
		 * @author manfred
		 * @date   23.01.2013
		 */
		private class ownAction extends XX_ActionAgent{
			
			public ownAction() {
				super();
			}
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				E2_calendar_grid_headpanel.this.calendar.set_year(Integer.parseInt(select_field_years.this.get_ActualWert()));
				E2_calendar_grid_headpanel.this.calendar.re_build();
			}
		}
	}
	
	

	private class ButtonYearMinus extends MyE2_Button
	{
		public ButtonYearMinus(){
			super(E2_ResourceIcon.get_RI("flat_left.png"), true);
			this.setToolTipText(new MyE2_String("1 Jahr zurück").CTrans());
			this.add_oActionAgent(new ownAction());
		}
		
		private class ownAction extends XX_ActionAgent 	{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				int i_year_new = E2_calendar_grid_headpanel.this.calendar.get_year()-1;
				E2_calendar_grid_headpanel.this.sel_years.check_and_add_year(""+i_year_new);
				E2_calendar_grid_headpanel.this.calendar.set_year(i_year_new);
				E2_calendar_grid_headpanel.this.calendar.re_build();
				E2_calendar_grid_headpanel.this.re_build();
			}
		}
	}


	private class ButtonYearPlus extends MyE2_Button
	{
		public ButtonYearPlus(){
			super(E2_ResourceIcon.get_RI("flat_right.png"), true);
			this.setToolTipText(new MyE2_String("1 Jahr vor").CTrans());

			this.add_oActionAgent(new ownAction());
		}
		
		private class ownAction extends XX_ActionAgent 	{
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				int i_year_new = E2_calendar_grid_headpanel.this.calendar.get_year()+1;
				E2_calendar_grid_headpanel.this.sel_years.check_and_add_year(""+i_year_new);
				E2_calendar_grid_headpanel.this.calendar.set_year(i_year_new);
				E2_calendar_grid_headpanel.this.calendar.re_build();
				E2_calendar_grid_headpanel.this.re_build();
			}
		}
	}


	public GridLayoutData get_layoutdata_4_headpanel() {
		return gl_4_headpanel;
	}


	public void set_layoutdata_4_headpanel(GridLayoutData p_gl_4_headpanel) {
		this.gl_4_headpanel = p_gl_4_headpanel;
	}


	public Font get_font_4_dropdowns() {
		return font_4_dropdowns;
	}


	public void set_font_4_dropdowns(Font p_font_4_dropdowns) {
		this.font_4_dropdowns = p_font_4_dropdowns;
	}


	public select_field_years get_sel_years() {
		return sel_years;
	}


	public select_field_months get_sel_months() {
		return sel_months;
	}


	public MyE2_Button get_bt_now() {
		return bt_now;
	}


	public MyE2_Button get_bt_month_left() {
		return bt_month_left;
	}


	public MyE2_Button get_bt_month_right() {
		return bt_month_right;
	}


	public MyE2_Button get_bt_year_left() {
		return bt_year_left;
	}


	public MyE2_Button get_bt_year_right() {
		return bt_year_right;
	}

	
	
}
