package panter.gmbh.Echo2.components.E2_calendar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.S;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_calendar_grid extends MyE2_Grid {
	
	
	/*
	 * zustandsdefinition der buttons:
	 * Sonderzustande sind: Aktueller Tag (heutiges Datum): Rahmenfarbe der buttons
	 *                      Ausgewaehlter Tag (selektion): Hintergrundfarbe des Buttons
	 *                      Markierter Tag (z.B. uebergeben feiertage): Schriftfarbe                   
	*/
	private Border  		borderNormal = new Border(1,new E2_ColorDark(),Border.STYLE_SOLID);
	private Border  		borderToday  = new Border(1,Color.BLACK,Border.STYLE_SOLID);
	
	private Color   		colorBackgroundNormal = new E2_ColorBase(-15);
	private Color   		colorBackgroundSelected = new E2_ColorBase(30);
	
	private Color   		colorForegroundNormal =  Color.BLACK;
	private Color   		colorForegroundSpecial = Color.DARKGRAY;
	
	private Font      		fontDayButtons = new E2_FontPlain();
	//der standard-style fuer das grid
	private MutableStyle  	standardStyle4Grid = MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11();
	private Insets    		insetsForDayComponents = E2_INSETS.I_2_2_2_2;
	
	//der haeufigste style (standard) wird vordefiniert
	private MutableStyle  	style4Buttons = this.generate_style_4_day_button(false, false, false);
	

	private     int     	iMonth = myDateHelper.get_actualMonth(0);
	private     int 		iYear = myDateHelper.get_actualYear(0);
	
	private 	String      cSelectedDayFormated = null;

	//abstrakte methoden
	public abstract void doDayButtonAction(ExecINFO execInfo) throws myException;
	public abstract  Vector<Date> get_DatesToHighlight() throws myException;
	
	private boolean 		m_bPassiveAction = false;

	private boolean    		b_show_titel_month_year = true;
	private GridLayoutData  layout_4_titel_month_year = MyE2_Grid.LAYOUT_LEFT_CENTER(E2_INSETS.I(2,1,1,1),null,7,null);
	private MutableStyle    style_4_titel_month_year = MyE2_Label.STYLE_SMALL_BOLD();
	
	
	public E2_calendar_grid() throws myException {
		this(myDateHelper.getActualMonth(), myDateHelper.getActualYear(),null,true);
	}

	
	public E2_calendar_grid(String c_date_actual_formated) throws myException {
		this(	c_date_actual_formated,	true);
	}


	public E2_calendar_grid(String c_date_actual_formated, boolean build) throws myException {
		super(7);
		
		MyDate test = new MyDate(c_date_actual_formated);
		if (test.get_bOK()) {
			this.init(test.get_INT_MONAT().get_iValue(), test.get_INT_JAHR().get_iValue(), test.get_cDateStandardFormat(), build);
		} else {
			this.init(0, 0, null, build);
		}
	}


	
	public E2_calendar_grid(int i_month, int i_year, String c_actual_day_formated, boolean build) throws myException {
		super(7);
		this.init(i_month, i_year, c_actual_day_formated, build);
	}

	
	public void init(int i_month, int i_year, String c_actual_day_formated, boolean build) throws myException {
		this.setStyle(this.standardStyle4Grid);
		if (i_month>0 && i_month<13) { this.iMonth = i_month;}
		if (i_year>0) { this.iYear = i_year; }
		if (S.isFull(c_actual_day_formated)) { this.cSelectedDayFormated = c_actual_day_formated;}
		if (build) {
			this.re_build();
		}
	}
	

	/**
	 * 
	 * @param i_monate    1-12
	 * @param i_jahre    
	 * @param actual_day_formated  datum in der form "30.10.2015"
	 * @return
	 * @throws myException
	 */
	public E2_calendar_grid re_build() throws myException {
		
		this.removeAll();
		
		if (this.b_show_titel_month_year) {
			this.add(new MyE2_Label(MONAT.get_namen(this.iMonth)+" "+this.iYear,this.style_4_titel_month_year),this.layout_4_titel_month_year);
		}
		
		GridLayoutData oGLCalendar = new GridLayoutData();
		oGLCalendar.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		oGLCalendar.setInsets(this.insetsForDayComponents);
		
		GridLayoutData oGLTitle = new GridLayoutData();
		oGLTitle.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		oGLTitle.setInsets(this.insetsForDayComponents);

		MutableStyle  oStyle = MyE2_Label.STYLE_NORMAL_ITALLIC();
		
		myDateHelper 	oDateHelperFirstDayOfMonth = new myDateHelper(1,this.iMonth,this.iYear);  

		
		// ueberschrift schreiben (mo-so)
		this.add(new MyE2_Label(new MyE2_String("Mo"),oStyle),oGLTitle);
		this.add(new MyE2_Label(new MyE2_String("Di"),oStyle),oGLTitle);
		this.add(new MyE2_Label(new MyE2_String("Mi"),oStyle),oGLTitle);
		this.add(new MyE2_Label(new MyE2_String("Do"),oStyle),oGLTitle);
		this.add(new MyE2_Label(new MyE2_String("Fr"),oStyle),oGLTitle);
		this.add(new MyE2_Label(new MyE2_String("Sa"),oStyle),oGLTitle);
		this.add(new MyE2_Label(new MyE2_String("So"),oStyle),oGLTitle);		

	
		int iLeereTage = 0;
		if (oDateHelperFirstDayOfMonth.get_OwnDayOfWeek()==Calendar.TUESDAY)  	iLeereTage = 1;
		if (oDateHelperFirstDayOfMonth.get_OwnDayOfWeek()==Calendar.WEDNESDAY)  iLeereTage = 2;
		if (oDateHelperFirstDayOfMonth.get_OwnDayOfWeek()==Calendar.THURSDAY)  	iLeereTage = 3;
		if (oDateHelperFirstDayOfMonth.get_OwnDayOfWeek()==Calendar.FRIDAY)  	iLeereTage = 4;
		if (oDateHelperFirstDayOfMonth.get_OwnDayOfWeek()==Calendar.SATURDAY)  	iLeereTage = 5;
		if (oDateHelperFirstDayOfMonth.get_OwnDayOfWeek()==Calendar.SUNDAY)  	iLeereTage = 6;
		
		for (int i=0;i<iLeereTage;i++)
			this.add(new MyE2_Label(" "));
		
		/*
		 * jetzt die tagesbuttons eintragen
		 */
		
		GregorianCalendar oCalCounter = oDateHelperFirstDayOfMonth.get_oCalDate();
		
		for (int i=1;i<32;i++) 	{
			if (myDateHelper.getActualMonth(oCalCounter) == this.iMonth)	{
				this.add(this.buildDayComponent(new myDateHelper(i,this.iMonth,this.iYear)), oGLCalendar);
			} else	{
				break;
			}
			oCalCounter = myDateHelper.Add_Day_To_Calendar(1,oCalCounter);
		 }
		
		this.set_bPassivAction(m_bPassiveAction);

		
		return this;
	}


	/**
	 * erzeugt style, je nach den zustandvariblen des buttons
	 * @param is_today
	 * @param is_selected
	 * @param is_specialDay
	 * @return
	 */
	private MutableStyle generate_style_4_day_button(boolean is_today, boolean is_selected, boolean is_specialDay) {
		MutableStyle style = new MutableStyle();
		
		style.setProperty(Button.PROPERTY_FONT, 			this.fontDayButtons);
		style.setProperty(Button.PROPERTY_BACKGROUND, 		is_selected?this.colorBackgroundSelected:this.colorBackgroundNormal);
		style.setProperty(Button.PROPERTY_FOREGROUND, 		is_specialDay?this.colorForegroundSpecial:this.colorForegroundNormal);
		style.setProperty(Button.PROPERTY_BORDER, 			is_today?this.borderToday:this.borderNormal);
		style.setProperty(Button.PROPERTY_PRESSED_BORDER, 	new Border(1,Color.WHITE,Border.STYLE_SOLID)); 
		style.setProperty( Button.PROPERTY_INSETS, 			E2_INSETS.I(1,1,1,1)); 

		return style;
	}



	/**
	 * Setzt das Attribut bPassiveAction für alle Buttons des Kalenders
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


	public MutableStyle get_style4Grid() {
		return this.standardStyle4Grid;
	}





	public void set_style4Grid(MutableStyle p_standardStyle4Grid) {
		this.standardStyle4Grid = p_standardStyle4Grid;
		this.setStyle(this.standardStyle4Grid);
	}

	
	
	public Component buildDayComponent(myDateHelper dateHelper) throws myException 	{

		MyE2_Button oDateButton = new MyE2_Button("" + dateHelper.get_IDay());
		oDateButton.add_oActionAgent(new ownActionAgent());
		oDateButton.EXT().set_O_PLACE_FOR_EVERYTHING(dateHelper);
		 
		myDateHelper oDateLastUsedDateInField = null;

		if (S.isFull(this.cSelectedDayFormated)) {
			try
			{
				oDateLastUsedDateInField = new myDateHelper(this.cSelectedDayFormated);
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		
		boolean is__today = dateHelper.equals(new myDateHelper(new GregorianCalendar()));
		boolean is__selected = false;
		if (oDateLastUsedDateInField!=null) {
			is__selected = dateHelper.equals(oDateLastUsedDateInField);
		}
		boolean is__highlighted = isDateToHighlight(dateHelper);
		
		if (is__today || is__selected || is__highlighted) {
			oDateButton.setStyle(this.generate_style_4_day_button(is__today, is__selected, is__highlighted));
		} else {
			oDateButton.setStyle(this.style4Buttons);
		}
		
		
		return oDateButton;
	}

	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException	{
			E2_calendar_grid.this.doDayButtonAction(execInfo);
		}
	}

	
	/**
	 * true, wenn der Tag hervorgehoben werden muss.
	 * @param dateHelper
	 * @return
	 */
	private boolean isDateToHighlight(myDateHelper dateHelper) {
		// prüfen, ob der Tag hervorgehoben werden muss
		boolean bHighlight = false;
		
		String sDate;
		try {
			sDate = dateHelper.get_cDateFormat_ISO_FORMAT();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date dt = df.parse(sDate);
			
			Vector<Date> vDates = get_DatesToHighlight();
			if (vDates != null && vDates.size() > 0 && vDates.contains(dt)){
				bHighlight = true;
			}
		} catch (Exception e) {
			// bei einem Fehler keine Hervorhebung
			bHighlight = false;
		}
		
		
		return bHighlight;
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
			
			if (intMonat!=null && intJahr != null) {
				this.iMonth=intMonat.get_iValue();
				this.iYear=intJahr.get_iValue();
				this.cSelectedDayFormated=cDateFormated;
				
				this.re_build();
			}
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

		this.iMonth=i_monat;
		this.iYear = i_jahr;
		this.cSelectedDayFormated = null;
		this.re_build();
	}


	
	public Border get_borderNormal() {
		return borderNormal;
	}
	public void set_borderNormal(Border borderNormal) {
		this.borderNormal = borderNormal;
	}
	public Border get_borderToday() {
		return borderToday;
	}
	public void set_borderToday(Border borderToday) {
		this.borderToday = borderToday;
	}
	public Color get_colorBackgroundNormal() {
		return colorBackgroundNormal;
	}
	public void set_colorBackgroundNormal(Color colorBackgroundNormal) {
		this.colorBackgroundNormal = colorBackgroundNormal;
	}
	public Color get_colorBackgroundSelected() {
		return colorBackgroundSelected;
	}
	public void set_colorBackgroundSelected(Color colorBackgroundSelected) {
		this.colorBackgroundSelected = colorBackgroundSelected;
	}
	public Color get_colorForegroundNormal() {
		return colorForegroundNormal;
	}
	public void set_colorForegroundNormal(Color colorForegroundNormal) {
		this.colorForegroundNormal = colorForegroundNormal;
	}
	public Color get_colorForegroundSpecial() {
		return colorForegroundSpecial;
	}
	public void set_colorForegroundSpecial(Color colorForegroundSpecial) {
		this.colorForegroundSpecial = colorForegroundSpecial;
	}
	public Font get_fontDayButtons() {
		return fontDayButtons;
	}
	public void set_fontDayButtons(Font fontDayButtons) {
		this.fontDayButtons = fontDayButtons;
	}
	public MutableStyle get_standardStyle4Grid() {
		return standardStyle4Grid;
	}
	public void set_standardStyle4Grid(MutableStyle standardStyle4Grid) {
		this.standardStyle4Grid = standardStyle4Grid;
	}
	public Insets get_insetsForDayComponents() {
		return insetsForDayComponents;
	}
	public void set_insetsForDayComponents(Insets insetsForDayComponents) {
		this.insetsForDayComponents = insetsForDayComponents;
	}
	public int get_month() {
		return iMonth;
	}
	public void set_month(int p_month) {
		this.iMonth = p_month;
	}
	public int get_year() {
		return iYear;
	}
	public void set_year(int iYear) {
		this.iYear = iYear;
	}
	public String get_selectedDayFormated() {
		return cSelectedDayFormated;
	}
	
	public void set_selectedDayFormated(String p_selectedDayFormated) {
		this.cSelectedDayFormated = p_selectedDayFormated;
	}

	
	public E2_calendar_grid decrease_month(int i_count) throws myException {
		for (int i=0;i<i_count;i++) {
			this.iMonth--;
			if (this.iMonth==0) {
				this.iYear--;
				this.iMonth=12;
			}
		}
		return this.re_build();
	}
	
	
	public E2_calendar_grid increase_month(int i_count) throws myException {
		for (int i=0;i<i_count;i++) {
			this.iMonth++;
			if (this.iMonth==13) {
				this.iYear++;
				this.iMonth=1;
			}
		}
		return this.re_build();
	}
	
	public boolean is_show_titel_month_year() {
		return b_show_titel_month_year;
	}
	
	public void set_show_titel_month_year(boolean p_show_titel_month_year) {
		this.b_show_titel_month_year = p_show_titel_month_year;
	}
	
	public GridLayoutData get_layout_4_titel_month_year() {
		return layout_4_titel_month_year;
	}
	public void set_layout_4_titel_month_year(GridLayoutData p_layout_4_titel_month_year) {
		this.layout_4_titel_month_year = p_layout_4_titel_month_year;
	}
	public MutableStyle get_style_4_titel_month_year() {
		return style_4_titel_month_year;
	}
	public void set_style_4_titel_month_year(MutableStyle style_4_titel_month_year) {
		this.style_4_titel_month_year = style_4_titel_month_year;
	}
	
	
	
}
