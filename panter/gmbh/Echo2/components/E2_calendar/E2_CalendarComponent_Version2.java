package panter.gmbh.Echo2.components.E2_calendar;

import java.util.Date;
import java.util.TreeMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RB.TOOLS.RB_gld;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_CalendarComponent_Version2 extends MyE2_Grid
{

	private 	Insets    	oInsetsForDayComponents = E2_INSETS.I_2_2_2_2;
	private  	boolean 	bshowNavigators = true;


	private     int     	iMonth = 0;
	private     int 		iYear = 0;

	public static String[] tagListe = {"Mo", "Di", "Mi", "Do", "Fr", "Sa", "So"};


	static TreeMap<Integer, String> mapJahre = null;

	private MyE2_Grid      		oGridForCalendar = null;

	private String         		cSelectedDayFormated = null;

	private boolean 			m_bPassiveAction = false;
	private boolean 			m_activeMonth = false;

	private MyE2_Button 				cSaveButton;

	private E2_TF_4_Date_Enum m_mode;

	private ownCalendarGrid monateMinusComponent;
	private ownCalendarGrid monateComponent;
	private ownCalendarGrid monatePlusComponent;

	//	baut ein grid mit buttons, fuer jeden tag einen, in der klassischen kalender-ansicht 
	public E2_CalendarComponent_Version2(	int 			Monat, 	
			int 			Jahr, 	
			Insets 			InsetsForButtons, 
			boolean 		showNavigators,
			MutableStyle    oStyleForGrid, 
			String 			SelectedDayFormated
			) throws myException
	{		

		super(MyE2_Grid.STYLE_GRID_NO_BORDER_NO_INSETS());

		this.iMonth = Monat;
		this.iYear  = Jahr;

		this.oInsetsForDayComponents = InsetsForButtons;
		this.bshowNavigators = showNavigators;

		this.oGridForCalendar = new MyE2_Grid(7,oStyleForGrid);

		this.cSelectedDayFormated = SelectedDayFormated;

		E2_CalendarComponent_Version2.mapJahre = new TreeMap<Integer, String>();

		// Standard-Highlight

		this.setSize(3);

		monateComponent = new ownCalendarGrid(iMonth,  iYear,bibALL.get_cDateNOW(),true);

	}



	/**
	 * Setzen der hervorzuhebenden DatumswertebuildComponentGrid
	 * Standardmäßig wird null zurückgegeben, 
	 * wenn Daten hervorgehoben werden sollen, dann
	 * muss man die Methode überschreiben
	 * @param vDates
	 */
	public abstract Vector<Date> get_DatesToHighlight() throws myException;

	public abstract void saveAction(ExecINFO oExecInfo) throws myException;

	public abstract Component buildDayComponent(E2_CalendarComponent_Version2 ownCalendarGrid, myDateHelper oDateHelper, boolean bActiveMonth)  throws myException;

	public void baue_calender() throws myException
	{	

		buildNavigationPanel();

		int monthMinus1, monthPlus1, yearMinus1,yearPlus1 = 0;

		if(iMonth == 1){
			monthMinus1 = 12;
			monthPlus1 = iMonth+1;
			yearMinus1 = iYear-1;
			yearPlus1 = iYear;
		} 
		else if(iMonth==12){
			monthMinus1 = 11;
			monthPlus1 = 1;
			yearMinus1 = iYear;
			yearPlus1 = iYear+1;
		}else{
			monthMinus1 = iMonth-1;
			monthPlus1 = iMonth+1;
			yearPlus1 = iYear;
			yearMinus1 = iYear;
		}

		this.removeAll();
		this.oGridForCalendar.removeAll();

		monateMinusComponent = new ownCalendarGrid(monthMinus1, yearMinus1,"", false);
		monateMinusComponent.setActive(false);
		monateComponent = new ownCalendarGrid(iMonth,  iYear,bibALL.get_cDateNOW(), true);
		monateComponent.setActive(true);
		monatePlusComponent = new ownCalendarGrid(monthPlus1, yearPlus1,"", false);
		monatePlusComponent.setActive(false);
		buildNavigationPanel();

		GridLayoutData oGLCalendar = new GridLayoutData();
		oGLCalendar.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		oGLCalendar.setInsets(this.oInsetsForDayComponents);

		GridLayoutData oGLTitle = new GridLayoutData();
		oGLTitle.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		oGLTitle.setInsets(this.oInsetsForDayComponents);

		this.add(monateMinusComponent, 		new RB_gld()._ins(E2_INSETS.I(8,2,8,2)));
		this.add(monateComponent, 			new RB_gld()._ins(E2_INSETS.I(8,2,8,2)));
		this.add(monatePlusComponent, 		new RB_gld()._ins(E2_INSETS.I(8,2,8,2)));
		if(m_mode==E2_TF_4_Date_Enum.OLD_SELECTION_MODE){
			this.buildSaveCancelRow();
		}	

		this.set_bPassivAction(m_bPassiveAction);
	}



	private void buildNavigationPanel() throws myException {
		if (this.bshowNavigators)
		{
			E2_calendar_grid_headpanel panel = new E2_calendar_grid_headpanel(monateComponent);

			MyE2_Button navButtonMonthLeft 	= panel.get_bt_month_left();
			MyE2_Button navButtonMonthRight = panel.get_bt_month_right();
			MyE2_Button navButtonYearLeft 	= panel.get_bt_year_left();
			MyE2_Button navButtonYearRight 	= panel.get_bt_year_right();

			MyE2_Button navButtonNow = panel.get_bt_now();

			MyE2_SelectField navSfMonth = panel.get_sel_months();
			MyE2_SelectField navSfYear 	= panel.get_sel_years();

			navButtonMonthLeft.remove_AllActionAgents();
			navButtonMonthLeft.add_oActionAgent(new plusMonthAction());

			navButtonMonthRight.remove_AllActionAgents();
			navButtonMonthRight.add_oActionAgent(new minusMonthAction());

			navButtonYearLeft.remove_AllActionAgents();
			navButtonYearLeft.add_oActionAgent(new YearMinusAction());

			navButtonYearRight.remove_AllActionAgents();
			navButtonYearRight.add_oActionAgent(new YearPlusAction());

			navButtonNow.remove_AllActionAgents();
			navButtonNow.add_oActionAgent(new nowAction());

			navSfMonth.remove_AllActionAgents();
			navSfMonth.add_oActionAgent(new changeMonthAction());

			navSfYear.remove_AllActionAgents();
			navSfYear.add_oActionAgent(new changeYearAction());

			this.add(panel, 3, 1, E2_INSETS.I(1,1,1,1), new Alignment(Alignment.CENTER, Alignment.CENTER));
		}
	}

	public boolean isActiveMonth() {
		return m_activeMonth;
	}


	public void setActiveMonth(boolean m_activeMonth) {
		this.m_activeMonth = m_activeMonth;
	}

	public void highlight_date_von_bis(myDateHelper dateVon, myDateHelper dateBis) throws myException {
		iterateAndUpdateCalendar(monateMinusComponent, dateVon, dateBis);
		iterateAndUpdateCalendar(monateComponent, dateVon, dateBis);
		iterateAndUpdateCalendar(monatePlusComponent, dateVon, dateBis);

		//		monateMinusComponent.getget_Basic_Mask_Container_Components();
	}

	public void resetCalendar() throws myException{
		iterateAndUpdateCalendar(monateMinusComponent,null,null);
		iterateAndUpdateCalendar(monateComponent,null,null);
		iterateAndUpdateCalendar(monatePlusComponent,null, null);		
	}

	private void iterateAndUpdateCalendar(ownCalendarGrid calendarToIterate, myDateHelper dateVon, myDateHelper dateBis) throws myException{
		for(Component cmp : calendarToIterate.getComponents()){
			if(cmp instanceof E2_TF_4_Date_Button){
				E2_TF_4_Date_Button cmp_ = (E2_TF_4_Date_Button)cmp;
				myDateHelper dateFromCmp_ =  (myDateHelper) cmp_.EXT().get_O_PLACE_FOR_EVERYTHING();
				if(dateVon==null || dateBis==null){
					cmp_.setForeground(new E2_ColorGray(128));
					cmp_.setFont(new E2_FontPlain());
					cmp.setBackground(new E2_ColorBase(-10));
					cmp_.setBorder(new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID));
				}else{
					boolean isGreaterAsDateVon = myDateHelper.get_Date1_Greater_Date2(dateFromCmp_.get_cDateFormatForMask(),dateVon.get_cDateFormatForMask());
					boolean isLessAsDateBis = myDateHelper.get_Date1_Less_Date2(dateFromCmp_.get_cDateFormatForMask(),dateBis.get_cDateFormatForMask());
					if((dateFromCmp_.equals(dateVon)||dateFromCmp_.equals(dateBis))){
						cmp_.setBorder(new Border(1,Color.BLACK,Border.STYLE_SOLID));
						cmp_.setForeground(Color.BLACK);
						cmp_.setFont(new E2_FontBoldItalic());
						cmp_.setBackground(new E2_ColorLLight());
					}else
					if(isGreaterAsDateVon && isLessAsDateBis){
						cmp_.setForeground(Color.BLACK);
						cmp_.setFont(new E2_FontItalic());
						cmp_.setBackground(new E2_ColorLLight());
						cmp_.setBorder(new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID));
					}
					else{
						cmp_.setForeground(new E2_ColorGray(128));
						cmp_.setFont(new E2_FontPlain());
						cmp.setBackground(new E2_ColorBase(-10));
						cmp_.setBorder(new Border(1,new E2_ColorDDark(),Border.STYLE_SOLID));
					}
				}
			}
		}
	}

	private void buildSaveCancelRow(){
		MyE2_Grid buttonGrid = new MyE2_Grid();
		cSaveButton = new MyE2_Button("OK");
		cSaveButton.add_oActionAgent(new ownSaveAction());
		buttonGrid.add(cSaveButton, 	1,	E2_INSETS.I(2,2,2,2));

		this.add(buttonGrid, 3,1, E2_INSETS.I(0,0,0,0),new Alignment(Alignment.RIGHT, Alignment.CENTER));
	}

	private class YearMinusAction extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			E2_CalendarComponent_Version2 oThis = E2_CalendarComponent_Version2.this;
			oThis.iYear--;
			int nYear = oThis.iYear;
			if (!E2_CalendarComponent_Version2.mapJahre.containsKey(nYear))
			{
				E2_CalendarComponent_Version2.mapJahre.put(nYear, Integer.toString(nYear));
			}

			try
			{
				oThis.baue_calender();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error navigation in Calendar !!"));
			}
		}
	}

	private class YearPlusAction extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			E2_CalendarComponent_Version2 oThis = E2_CalendarComponent_Version2.this;
			oThis.iYear++;

			int nYear = oThis.iYear;
			if (!E2_CalendarComponent_Version2.mapJahre.containsKey(nYear))
			{
				E2_CalendarComponent_Version2.mapJahre.put(nYear, Integer.toString(nYear));
			}

			try
			{
				oThis.baue_calender();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error navigation in Calendar !!"));
			}
		}
	}

	private class plusMonthAction extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			E2_CalendarComponent_Version2 oThis = E2_CalendarComponent_Version2.this;

			oThis.iMonth--;
			if (oThis.iMonth==0)
			{
				oThis.iYear--;
				oThis.iMonth=12;

				int nYear = oThis.iYear;
				if (!E2_CalendarComponent_Version2.mapJahre.containsKey(nYear))
				{
					E2_CalendarComponent_Version2.mapJahre.put(nYear, Integer.toString(nYear));
				}

			}
			try
			{
				oThis.baue_calender();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error navigation in Calendar !!"));
			}
		}

	}

	private class minusMonthAction extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			E2_CalendarComponent_Version2 oThis = E2_CalendarComponent_Version2.this;

			oThis.iMonth++;
			if (oThis.iMonth==13)
			{
				oThis.iYear++;
				oThis.iMonth=1;

				int nYear = oThis.iYear;
				if (!E2_CalendarComponent_Version2.mapJahre.containsKey(nYear))
				{
					E2_CalendarComponent_Version2.mapJahre.put(nYear, Integer.toString(nYear));
				}

			}
			try
			{
				oThis.baue_calender();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error navigation in Calendar !!"));
			}
		}

	}

	private class nowAction extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) 
		{
			E2_CalendarComponent_Version2 oThis = E2_CalendarComponent_Version2.this;

			myDateHelper oHelper= null;
			try {
				oHelper = new myDateHelper(new Date());
			} catch (myException e) {
				e.printStackTrace();
			}
			oThis.iMonth = oHelper.get_IMonth();
			oThis.iYear = oHelper.get_IYear();

			try
			{
				oThis.baue_calender();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error navigation in Calendar !!"));
			}
		}	
	}

	private class changeMonthAction extends XX_ActionAgent{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_CalendarComponent_Version2 oThis = E2_CalendarComponent_Version2.this;

			MyE2_SelectField ocomp = (MyE2_SelectField) oExecInfo.get_MyActionEvent().getSource();
			int monthIndex = ocomp.getSelectedIndex()+1;

			oThis.iMonth = monthIndex;

			try
			{
				oThis.baue_calender();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error navigation in Calendar !!"));
			}
		}
	}

	private class changeYearAction extends XX_ActionAgent{

		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			E2_CalendarComponent_Version2 oThis = E2_CalendarComponent_Version2.this;

			MyE2_SelectField ocomp = (MyE2_SelectField) oExecInfo.get_MyActionEvent().getSource();
			int year = Integer.parseInt((String) ocomp.getSelectedItem());

			oThis.iYear = year;

			try
			{
				oThis.baue_calender();
			}
			catch (myException ex)
			{
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error navigation in Calendar !!"));
			}

		}

	}

	public String get_cSelectedDayFormated()
	{
		return cSelectedDayFormated;
	}


	public void set_cSelectedDayFormated(String selectedDayFormated)
	{
		cSelectedDayFormated = selectedDayFormated;
	}

	/**
	 * Setzen des Styles für die Hervorgehobenen Datumswerte
	 * Z.B. nimmt man den Style MyE2_Button.StyleTextButton()
	 * und passt den Style an
	 * Standardmäßig wird null zurückgegeben
	 * 
	 * @param Style
	 */
	public E2_MutableStyle get_StyleForHighlightedButtons(){
		return null;
	}


	/**
	 * Setzt das Attribut bPassiveAction für alle Buttons des KalendersdoDayButtonAction
	 * @param bPassive
	 */
	public void set_bPassivAction(boolean bPassive){
		m_bPassiveAction = bPassive;
		set_bPassivAction(this,bPassive);
	}



	private void set_bPassivAction(Component comp,boolean bPassive){
		if (comp.getComponentCount() == 0) return;

		for (int i=0; i< comp.getComponentCount(); i++){
			Component  c = comp.getComponent(i);
			if (c instanceof MyE2_Button){
				((MyE2_Button)c).set_bPassivAction(bPassive);
			} else {
				// rekursion
				set_bPassivAction(c,bPassive);
			}
		}
	}

	public void refreshCalender(String newFocusedDatum) throws myException{
		set_iMonth(new myDateHelper(newFocusedDatum).get_IMonth());
		set_iYear(new myDateHelper(newFocusedDatum).get_IYear());
		set_cSelectedDayFormated(newFocusedDatum);
		baue_calender();
	}

	public ownCalendarGrid getMonateMinusComponent() {
		return monateMinusComponent;
	}



	public ownCalendarGrid getMonateComponent() {
		return monateComponent;
	}



	public ownCalendarGrid getMonatePlusComponent() {
		return monatePlusComponent;
	}



	public int get_iMonth() 
	{
		return iMonth;
	}


	public void set_iMonth(int iMonth) 
	{
		this.iMonth = iMonth;
	}


	public int get_iYear() 
	{
		return iYear;
	}


	public void set_iYear(int iYear) 
	{
		this.iYear = iYear;
	}

	public E2_TF_4_Date_Enum getMode() {
		return m_mode;
	}


	public void setMode(E2_TF_4_Date_Enum m_mode) throws myException {
		this.m_mode = m_mode;
		baue_calender();
	}

	protected class ownSaveAction extends XX_ActionAgent{

		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			saveAction(oExecInfo);

		}
	}

	public int getActiveMonthNumber(){
		return monateComponent.get_month();
	}

	private class ownCalendarGrid extends E2_calendar_grid{

		private boolean active = false;

		public ownCalendarGrid(int monat, int jahr, String c_date_actual_formated, boolean bActiveMonth) throws myException {
			super(monat, jahr, c_date_actual_formated, true);
			this.set_style_4_titel_month_year(MyE2_Label.STYLE_NORMAL_BOLD());
			this.re_build();
			this.active = bActiveMonth;
		}

		@Override
		public Component buildDayComponent(myDateHelper dateHelper) throws myException {
			boolean centralMonth = false;
			if(E2_CalendarComponent_Version2.this.m_mode==E2_TF_4_Date_Enum.SLIP_SELECTION_MODE){
				if(dateHelper.get_IMonth() == E2_CalendarComponent_Version2.this.iMonth){
					centralMonth = true;
				}
			}else{
				centralMonth=true;
			}
			return E2_CalendarComponent_Version2.this.buildDayComponent(
					E2_CalendarComponent_Version2.this, 
					dateHelper, 
					centralMonth
					);

		}

		@SuppressWarnings("unused")
		public boolean isActive() {
			return active;
		}

		public void setActive(boolean active) {
			this.active = active;
		}

		@Override
		public void doDayButtonAction(ExecINFO execInfo) throws myException {
			E2_CalendarComponent_Version2.this.saveAction(execInfo);
		}

		@Override
		public Vector<Date> get_DatesToHighlight() throws myException {
			return E2_CalendarComponent_Version2.this.get_DatesToHighlight();
		}

	}

}
