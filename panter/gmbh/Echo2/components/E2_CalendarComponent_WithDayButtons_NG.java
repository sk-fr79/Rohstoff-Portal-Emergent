package panter.gmbh.Echo2.components;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Button;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Font;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorDark;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.E2_ComponentGroupHorizontal;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Column;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.Echo2.components.MyE2_Row;
import panter.gmbh.Echo2.components.MyE2_SelectField;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

@Deprecated
public abstract class E2_CalendarComponent_WithDayButtons_NG extends MyE2_Column {

	private 	Insets    	oInsetsForDayComponents = E2_INSETS.I_2_2_2_2;
	private  	boolean 	bShowMonthAndYearInFirstLine = false;
	private  	boolean 	bshowNavigators = false;

	private     int     	iMonth = 0;
	private     int 		iYear = 0;
	
	/*
	 * zustandsdefinition der buttons:
	 * Sonderzustande sind: Aktueller Tag (heutiges Datum): Rahmenfarbe der buttons
	 *                      Ausgewaehlter Tag (selektion): Hintergrundfarbe des Buttons
	 *                      Markierter Tag (z.B. uebergeben feiertage): Schriftfarbe                   
	*/
	private Border  		borderNormal = new Border(1,new E2_ColorDark(),Border.STYLE_SOLID);
	private Border  		borderToday  = new Border(1,Color.BLACK,Border.STYLE_SOLID);
	
	private Color   		colorBackgroundNormal = new E2_ColorBase(-15);
	private Color   		colorBackgroundSelected = new E2_ColorBase(15);
	
	private Color   		colorForegroundNormal =  Color.BLACK;
	private Color   		colorForegroundSpecial = Color.DARKGRAY;
	
	private Font      		fontDayButtons = new E2_FontPlain();
	
	//der haeufigste style (standard) wird vordefiniert
	private MutableStyle  	standardStyle = this.generate_style_4_button(false, false, false);
	
	
	public static String[] MonatsNamen = {"Januar","Februar","März","April","Mai","Juni",
											"Juli","August","September","Oktober","November","Dezember"};
	static String  [][] ccMonate = {
			{new MyE2_String("Jan").CTrans(),"1"},
			{new MyE2_String("Feb").CTrans(),"2"},
			{new MyE2_String("März").CTrans(),"3"},
			{new MyE2_String("April").CTrans(),"4"},
			{new MyE2_String("Mai").CTrans(),"5"},
			{new MyE2_String("Juni").CTrans(),"6"},
			{new MyE2_String("Juli").CTrans(),"7"},
			{new MyE2_String("Aug").CTrans(),"8"},
			{new MyE2_String("Sept").CTrans(),"9"},
			{new MyE2_String("Okt").CTrans(),"10"},
			{new MyE2_String("Nov").CTrans(),"11"},
			{new MyE2_String("Dez").CTrans(),"12"}};
	
	static TreeMap<Integer, String> mapJahre = null;
	
	private ButtonMonthMinus      oButtonLeft = null;
	private ButtonMonthPlus     oButtonRight = null;
	

	private MyE2_Column    oColForBedienpanel = null;
	private MyE2_Grid      oGridForCalendar = null;
	
	private String         cSelectedDayFormated = null;
	
	//private 	Vector<Date> 	m_HighlightDates = new Vector<Date>();
	private 	E2_MutableStyle m_StyleForHighlights = null;
	
	private boolean 		m_bPassiveAction = false;
	


	//abstrakte methoden
	public abstract void doDayButtonAction(ExecINFO execInfo) throws myException;

	
	
	//	baut ein grid mit buttons, fuer jeden tag einen, in der klassischen kalender-ansicht 
	public E2_CalendarComponent_WithDayButtons_NG(int 			Monat, 	
								int 			Jahr, 	
								Insets 			InsetsForButtons, 
								boolean 		showMonthAndYearInFirstLine,
								boolean 		showNavigators,
								MutableStyle    oStyleForGrid, 
								String 			SelectedDayFormated) throws myException
	{		

		super(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
				
		this.iMonth = Monat;
		this.iYear  = Jahr;
		
		this.oInsetsForDayComponents = InsetsForButtons;
		this.bShowMonthAndYearInFirstLine = showMonthAndYearInFirstLine;
		this.bshowNavigators = showNavigators;
		
		this.oGridForCalendar = new MyE2_Grid(7,oStyleForGrid);
		this.oColForBedienpanel = new MyE2_Column(MyE2_Column.STYLE_NO_BORDER_NO_INSETS());
		
		this.cSelectedDayFormated = SelectedDayFormated;
		
		// Standard-Highlight
		m_StyleForHighlights = MyE2_Button.StyleTextButton();
		m_StyleForHighlights.setProperty(AbstractButton.PROPERTY_BACKGROUND, new E2_ColorLLight(), new E2_ColorLLight());
		m_StyleForHighlights.setProperty(AbstractButton.PROPERTY_FOREGROUND, Color.BLACK, Color.BLACK);

		E2_CalendarComponent_WithDayButtons_NG.mapJahre = new TreeMap<Integer, String>();
		
		this.baue_calender(); 
	 }
	 

	
	public E2_CalendarComponent_WithDayButtons_NG(String cActualDayFormated)	throws myException
	{
		this(	new myDateHelper(cActualDayFormated).get_IMonth(), 
				new myDateHelper(cActualDayFormated).get_IYear(), 
				E2_INSETS.I_2_2_2_2, 
				true,
				true, 
				MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11(),
				cActualDayFormated);
	}

	
	
	

	
	
	/**
	 * erzeugt style, je nach den zustandvariblen des buttons
	 * @param is_today
	 * @param is_selected
	 * @param is_specialDay
	 * @return
	 */
	private MutableStyle generate_style_4_button(boolean is_today, boolean is_selected, boolean is_specialDay) {
		MutableStyle style = new MutableStyle();
		
		style.setProperty(Button.PROPERTY_FONT, 			this.fontDayButtons);
		style.setProperty(Button.PROPERTY_BACKGROUND, 		is_selected?this.colorBackgroundSelected:this.colorBackgroundNormal);
		style.setProperty(Button.PROPERTY_FOREGROUND, 		is_specialDay?this.colorForegroundSpecial:this.colorForegroundNormal);
		style.setProperty(Button.PROPERTY_BORDER, 			is_today?this.borderToday:this.borderNormal);
		style.setProperty(Button.PROPERTY_PRESSED_BORDER, 	new Border(1,Color.WHITE,Border.STYLE_SOLID)); 
		style.setProperty( Button.PROPERTY_INSETS, 			E2_INSETS.I(1,1,1,1)); 

		return style;
	}

	
	public void baue_calender() throws myException
	{
		this.removeAll();
		this.oColForBedienpanel.removeAll();
		this.oGridForCalendar.removeAll();
		
		Insets oInsetsTitel = new Insets(10,2,2,5);
		Insets oInsetsButtonsNarrow = new Insets(0,2,0,2);
		
		//der haeufigste style (standard) wird vordefiniert
		this.standardStyle = this.generate_style_4_button(false, false, false);

		
		myDateHelper 	oDateHelperFirstDayOfMonth = new myDateHelper(1,this.iMonth,this.iYear);  
		
		this.oButtonLeft = new ButtonMonthMinus();
		this.oButtonRight = new ButtonMonthPlus();
		
		
		if (this.bShowMonthAndYearInFirstLine)
		{
			this.add(this.oColForBedienpanel,E2_INSETS.I_0_0_0_0);
			
			String cHelp = new MyE2_String(E2_CalendarComponent_WithDayButtons_NG.MonatsNamen[this.iMonth-1]).CTrans()+"  "+this.iYear;
			MyE2_Label oLabMonatJahr = new MyE2_Label(cHelp,MyE2_Label.STYLE_NORMAL_BOLD());
	
			
			if (this.bshowNavigators)
			{
				// neue Navigation mit SelectField
				MyE2_Row oRowNav1 = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
				
				oRowNav1.add(new ButtonToday(),oInsetsButtonsNarrow);
				
				oRowNav1.add(new ButtonMonthMinus(),oInsetsButtonsNarrow);
				oRowNav1.add(new cSelectFieldMonate(),oInsetsButtonsNarrow);
				oRowNav1.add(new ButtonMonthPlus(),oInsetsButtonsNarrow);
				
				oRowNav1.add(new ButtonYearMinus2(),oInsetsButtonsNarrow);
				oRowNav1.add(new cSelectFieldJahre(),oInsetsButtonsNarrow);
				oRowNav1.add(new ButtonYearPlus2(),oInsetsButtonsNarrow);
				
				this.oColForBedienpanel.add(oRowNav1, MyE2_Column.LAYOUT_LEFT(E2_INSETS.I_0_0_0_10));
				
				
				MyE2_Row oRowMonat = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
				oRowMonat.add(oLabMonatJahr, MyE2_Row.LAYOUT_CENTER(oInsetsTitel));
				this.oColForBedienpanel.add(oRowMonat, MyE2_Column.LAYOUT_LEFT(E2_INSETS.I_0_0_0_0));
			}
			else
			{
				this.oColForBedienpanel.add(new E2_ComponentGroupHorizontal(0,oLabMonatJahr,oInsetsTitel));
			}
		}
		
		this.add(this.oGridForCalendar, E2_INSETS.I_10_0_0_0);
		 
		GridLayoutData oGLCalendar = new GridLayoutData();
		oGLCalendar.setAlignment(new Alignment(Alignment.RIGHT,Alignment.CENTER));
		oGLCalendar.setInsets(this.oInsetsForDayComponents);
		
		GridLayoutData oGLTitle = new GridLayoutData();
		oGLTitle.setAlignment(new Alignment(Alignment.CENTER,Alignment.CENTER));
		oGLTitle.setInsets(this.oInsetsForDayComponents);

		MutableStyle  oStyle = MyE2_Label.STYLE_NORMAL_ITALLIC();
		// ueberschrift schreiben (mo-so)
		this.oGridForCalendar.add(new MyE2_Label(new MyE2_String("Mo"),oStyle),oGLTitle);
		this.oGridForCalendar.add(new MyE2_Label(new MyE2_String("Di"),oStyle),oGLTitle);
		this.oGridForCalendar.add(new MyE2_Label(new MyE2_String("Mi"),oStyle),oGLTitle);
		this.oGridForCalendar.add(new MyE2_Label(new MyE2_String("Do"),oStyle),oGLTitle);
		this.oGridForCalendar.add(new MyE2_Label(new MyE2_String("Fr"),oStyle),oGLTitle);
		this.oGridForCalendar.add(new MyE2_Label(new MyE2_String("Sa"),oStyle),oGLTitle);
		this.oGridForCalendar.add(new MyE2_Label(new MyE2_String("So"),oStyle),oGLTitle);		

	
		int iLeereTage = 0;
		if (oDateHelperFirstDayOfMonth.get_OwnDayOfWeek()==Calendar.TUESDAY)  	iLeereTage = 1;
		if (oDateHelperFirstDayOfMonth.get_OwnDayOfWeek()==Calendar.WEDNESDAY)  iLeereTage = 2;
		if (oDateHelperFirstDayOfMonth.get_OwnDayOfWeek()==Calendar.THURSDAY)  	iLeereTage = 3;
		if (oDateHelperFirstDayOfMonth.get_OwnDayOfWeek()==Calendar.FRIDAY)  	iLeereTage = 4;
		if (oDateHelperFirstDayOfMonth.get_OwnDayOfWeek()==Calendar.SATURDAY)  	iLeereTage = 5;
		if (oDateHelperFirstDayOfMonth.get_OwnDayOfWeek()==Calendar.SUNDAY)  	iLeereTage = 6;
		
		for (int i=0;i<iLeereTage;i++)
			this.oGridForCalendar.add(new MyE2_Label(" "));
		
		/*
		 * jetzt die tagesbuttons eintragen
		 */
		
		GregorianCalendar oCalCounter = oDateHelperFirstDayOfMonth.get_oCalDate();
		
		for (int i=1;i<32;i++)
		{
			if (myDateHelper.getActualMonth(oCalCounter) == this.iMonth)
			{
				this.oGridForCalendar.add(this.buildDayComponent(new myDateHelper(i,this.iMonth,this.iYear)), oGLCalendar);
			}
			else
			{
				break;
			}
			oCalCounter = myDateHelper.Add_Day_To_Calendar(1,oCalCounter);
			 
		 }
		
		this.set_bPassivAction(m_bPassiveAction);

	}
	
	
	
	/**
	 * Monats-Selektor
	 * @author manfred
	 * @date   23.01.2013
	 */
	private class cSelectFieldMonate extends MyE2_SelectField{

		public cSelectFieldMonate() throws myException {
			super();
			
			//this.setFont(new E2_FontPlain(-2));
			
			E2_CalendarComponent_WithDayButtons_NG oThis = E2_CalendarComponent_WithDayButtons_NG.this;
			
			this.set_ListenInhalt(E2_CalendarComponent_WithDayButtons_NG.ccMonate,false)  ;
			this.set_ActiveValue_OR_FirstValue( Integer.toString(oThis.iMonth) );
			
			this.add_oActionAgent( new aaMonateSelectField(this) ) ;
		}
		
		
		/**
		 * Eventhandler
		 * @author manfred
		 * @date   23.01.2013
		 */
		private class aaMonateSelectField extends XX_ActionAgent{
			cSelectFieldMonate m_sel = null;
			
			public aaMonateSelectField(cSelectFieldMonate sel) {
				super();
				m_sel = sel;
			}
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				E2_CalendarComponent_WithDayButtons_NG oThis = E2_CalendarComponent_WithDayButtons_NG.this;
				
				// Monat übernehmen 
				oThis.iMonth = Integer.parseInt( m_sel.get_ActualWert() );
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

		
	}
	
	
	
	/**
	 * SelectField der Jahre als DropDown
	 * 
	 */
	private class cSelectFieldJahre extends MyE2_SelectField{
		
		public cSelectFieldJahre() throws myException {
			super();
			
			//this.setFont(new E2_FontPlain(-2));
	
			E2_CalendarComponent_WithDayButtons_NG oThis = E2_CalendarComponent_WithDayButtons_NG.this;
			
			// initiales Füllen der Map
			if (mapJahre.size() == 0){
				// baue das Array für die Jahre 30 Jahre Zurück, 30 Jahre vor sollte reichen ;-)
				int Jahr = new GregorianCalendar().get(Calendar.YEAR);
				
				int Start = 2008;
				if (iYear < Start ){
					Start = iYear;
				}
				
				int Ziel = Jahr + 2;
				if (iYear > Ziel) {
					Ziel = iYear;
				}
				
				for (int i = Start; i <= Ziel; i++){
					mapJahre.put(i, Integer.toString(i));
				}
			}
			
			// übernehmen der Map in ein Array für das Selektfeld
			String[][] ccJahre  = new String [mapJahre.size()][2];
			
			int idx = 0;
			for(Map.Entry<Integer, String> entry : mapJahre.entrySet() ){
				ccJahre[idx][0] = entry.getKey().toString();
				ccJahre[idx++][1] = entry.getValue();
			}
			
			this.set_ListenInhalt(ccJahre,false)  ;
			this.set_ActiveInhalt_or_FirstInhalt(Integer.toString(oThis.iYear));
			
			this.add_oActionAgent( new aaJahreListbox(this) ) ;
		}

		/**
		 * Eventhandler
		 * @author manfred
		 * @date   23.01.2013
		 */
		private class aaJahreListbox extends XX_ActionAgent{
			cSelectFieldJahre m_sel = null;
			
			public aaJahreListbox(cSelectFieldJahre sel) {
				super();
				m_sel = sel;
			}
			
			@Override
			public void executeAgentCode(ExecINFO oExecInfo) throws myException {
				E2_CalendarComponent_WithDayButtons_NG oThis = E2_CalendarComponent_WithDayButtons_NG.this;
				oThis.iYear = Integer.parseInt( m_sel.get_ActualWert() );
				
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
	}
	
	

	private class ButtonYearMinus2 extends MyE2_Button
	{
		public ButtonYearMinus2(){
			super(E2_ResourceIcon.get_RI("to_left_narrow.png"), true);
			this.setToolTipText(new MyE2_String("1 Jahr zurück").CTrans());

			this.add_oActionAgent(new aaYearMinus2());
		}
		
		private class aaYearMinus2 extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) 
			{
				E2_CalendarComponent_WithDayButtons_NG oThis = E2_CalendarComponent_WithDayButtons_NG.this;
				oThis.iYear--;
				int nYear = oThis.iYear;
				if (!E2_CalendarComponent_WithDayButtons_NG.mapJahre.containsKey(nYear))
				{
					E2_CalendarComponent_WithDayButtons_NG.mapJahre.put(nYear, Integer.toString(nYear));
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
	}


	private class ButtonYearPlus2 extends MyE2_Button
	{
		public ButtonYearPlus2(){
			super(E2_ResourceIcon.get_RI("to_right_narrow.png"), true);
			this.setToolTipText(new MyE2_String("1 Jahr vor").CTrans());

			this.add_oActionAgent(new aaYearPlus2());
		}
		
		private class aaYearPlus2 extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) 
			{
				E2_CalendarComponent_WithDayButtons_NG oThis = E2_CalendarComponent_WithDayButtons_NG.this;
				oThis.iYear++;

				int nYear = oThis.iYear;
				if (!E2_CalendarComponent_WithDayButtons_NG.mapJahre.containsKey(nYear))
				{
					E2_CalendarComponent_WithDayButtons_NG.mapJahre.put(nYear, Integer.toString(nYear));
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
	}

	
	

	private class ButtonMonthMinus extends MyE2_Button
	{
		public ButtonMonthMinus() 
		{
			super(E2_ResourceIcon.get_RI("to_left_narrow.png"), true);
			this.setToolTipText(new MyE2_String("1 Monat zurück").CTrans());

			this.add_oActionAgent(new ownAction());
		}

		private class ownAction extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) 
			{
				E2_CalendarComponent_WithDayButtons_NG oThis = E2_CalendarComponent_WithDayButtons_NG.this;
				
				oThis.iMonth--;
				if (oThis.iMonth==0)
				{
					oThis.iYear--;
					oThis.iMonth=12;
					
					int nYear = oThis.iYear;
					if (!E2_CalendarComponent_WithDayButtons_NG.mapJahre.containsKey(nYear))
					{
						E2_CalendarComponent_WithDayButtons_NG.mapJahre.put(nYear, Integer.toString(nYear));
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
	}

	private class ButtonMonthPlus extends MyE2_Button
	{
		public ButtonMonthPlus() 
		{
			super(E2_ResourceIcon.get_RI("to_right_narrow.png"), true);
			this.setToolTipText(new MyE2_String("1 Monat vor").CTrans());

			this.add_oActionAgent(new ownAction());
		}

		private class ownAction extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) 
			{
				E2_CalendarComponent_WithDayButtons_NG oThis = E2_CalendarComponent_WithDayButtons_NG.this;
				
				oThis.iMonth++;
				if (oThis.iMonth==13)
				{
					oThis.iYear++;
					oThis.iMonth=1;
					
					int nYear = oThis.iYear;
					if (!E2_CalendarComponent_WithDayButtons_NG.mapJahre.containsKey(nYear))
					{
						E2_CalendarComponent_WithDayButtons_NG.mapJahre.put(nYear, Integer.toString(nYear));
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
	}

	
	private class ButtonToday extends MyE2_Button 	{
		public ButtonToday() 	{
			super(E2_ResourceIcon.get_RI("to_point.png"), true);
			this.setToolTipText(new MyE2_String("Springe zum aktuellen Monat").CTrans());

			this.add_oActionAgent(new ownAction());
		}

		private class ownAction extends XX_ActionAgent		{
			public void executeAgentCode(ExecINFO oExecInfo)	{
				E2_CalendarComponent_WithDayButtons_NG oThis = E2_CalendarComponent_WithDayButtons_NG.this;
				
				myDateHelper oHelper= null;
				try {
					oHelper = new myDateHelper(new Date());
				} catch (myException e) {
					e.printStackTrace();
				}
				oThis.iMonth = oHelper.get_IMonth();
				oThis.iYear = oHelper.get_IYear();
			
				try 	{
					oThis.baue_calender();
				} catch (myException ex) {
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Error navigation in Calendar !!"));
				}
			}
			
		}
	}


	
	


	public ButtonMonthMinus get_oButtonLeft() {
		return oButtonLeft;
	}


	public ButtonMonthPlus get_oButtonRight() {
		return oButtonRight;
	}


	public String get_cSelectedDayFormated() {
		return cSelectedDayFormated;
	}


	public void set_cSelectedDayFormated(String selectedDayFormated) {
		cSelectedDayFormated = selectedDayFormated;
	}


	/**
	 * Setzen der hervorzuhebenden Datumswerte
	 * Standardmäßig wird null zurückgegeben, 
	 * wenn Daten hervorgehoben werden sollen, dann
	 * muss man die Methode überschreiben
	 * @param vDates
	 */
	public Vector<Date> get_DatesToHighlight(){
		return null;
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


	public Component buildDayComponent(myDateHelper dateHelper) throws myException 	{

		MyE2_Button oDateButton = new MyE2_Button("" + dateHelper.get_IDay());
		oDateButton.add_oActionAgent(new ownActionAgent());
		oDateButton.EXT().set_O_PLACE_FOR_EVERYTHING(dateHelper);
		 
		myDateHelper oDateLastUsedDateInField = null;
	
		
		try
		{
			oDateLastUsedDateInField = new myDateHelper(this.cSelectedDayFormated);
		} catch (myException e) {
			e.printStackTrace();
		}
		
		boolean is__today = dateHelper.equals(new myDateHelper(new GregorianCalendar()));
		boolean is__selected = false;
		if (oDateLastUsedDateInField!=null) {
			is__selected = dateHelper.equals(oDateLastUsedDateInField);
		}
		boolean is__highlighted = isDateToHighlight(dateHelper);
		
		if (is__today || is__selected || is__highlighted) {
			oDateButton.setStyle(this.generate_style_4_button(is__today, is__selected, is__highlighted));
		} else {
			oDateButton.setStyle(this.standardStyle);
		}
		
		
		return oDateButton;
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

	
	
	private class ownActionAgent extends XX_ActionAgent {
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException	{
			E2_CalendarComponent_WithDayButtons_NG.this.doDayButtonAction(execInfo);
		}
	}
	
	
	
	/*
	 * sucht sich die richtige seite und baut die komponente neu auf
	 */
	public void SPRINGE_AUF_DATUM(String cDateFormated) throws myException 	{
		MyDate  oDate = new MyDate(cDateFormated);
		
		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK)) {
			MyInteger intMonat = oDate.get_INT_MONAT();
			MyInteger intJahr = oDate.get_INT_JAHR();
			
			if (intMonat!=null && intJahr != null) {
				this.set_iMonth(intMonat.get_iValue());
				this.set_iYear(intJahr.get_iValue());
				this.set_cSelectedDayFormated(cDateFormated);
				
				this.baue_calender();
			}
		}
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

	
	
}
