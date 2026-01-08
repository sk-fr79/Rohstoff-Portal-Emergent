package panter.gmbh.Echo2.components;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import nextapp.echo2.app.button.AbstractButton;
import nextapp.echo2.app.layout.GridLayoutData;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.staticStyles.E2_MutableStyle;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_CalendarComponent extends MyE2_Column 
{

	private 	Insets    	oInsetsForDayComponents = E2_INSETS.I_2_2_2_2;
	private  	boolean 	bShowMonthAndYearInFirstLine = false;
	private  	boolean 	bshowNavigators = false;
	

	private     int     	iMonth = 0;
	private     int 		iYear = 0;
	
	
	public static String[] MonatsNamen = {"Januar","Februar","März","April","Mai","Juni",
											"Juli","August","September","Oktober","November","Dezember"};
	static String  [][] ccMonate = {
			{new MyE2_String("Januar").CTrans(),"1"},
			{new MyE2_String("Februar").CTrans(),"2"},
			{new MyE2_String("März").CTrans(),"3"},
			{new MyE2_String("April").CTrans(),"4"},
			{new MyE2_String("Mai").CTrans(),"5"},
			{new MyE2_String("Juni").CTrans(),"6"},
			{new MyE2_String("Juli").CTrans(),"7"},
			{new MyE2_String("August").CTrans(),"8"},
			{new MyE2_String("September").CTrans(),"9"},
			{new MyE2_String("Oktober").CTrans(),"10"},
			{new MyE2_String("November").CTrans(),"11"},
			{new MyE2_String("Dezember").CTrans(),"12"}};
	
	static TreeMap<Integer, String> mapJahre = null;
	
	private ButtonMonthMinus      oButtonLeft = null;
	private ButtonMonthPlus     oButtonRight = null;
	

	private MyE2_Column    oColForBedienpanel = null;
	private MyE2_Grid      oGridForCalendar = null;
	
	private String         cSelectedDayFormated = null;
	
	//private 	Vector<Date> 	m_HighlightDates = new Vector<Date>();
	private 	E2_MutableStyle m_StyleForHighlights = null;
	
	private boolean 		m_bPassiveAction = false;
	

	//	baut ein grid mit buttons, fuer jeden tag einen, in der klassischen kalender-ansicht 
	public E2_CalendarComponent(int 			Monat, 	
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

		this.mapJahre = new TreeMap<Integer, String>();
		
		this.baue_calender(); 
	 }
	 

	public void baue_calender() throws myException
	{
		this.removeAll();
		this.oColForBedienpanel.removeAll();
		this.oGridForCalendar.removeAll();
		
		Insets oInsetsTitel = new Insets(10,2,2,5);
		Insets oInsetsButtons = new Insets(2,2,2,2);
		Insets oInsetsButtonsNarrow = new Insets(0,2,0,2);
		
		
		myDateHelper 	oDateHelperFirstDayOfMonth = new myDateHelper(1,this.iMonth,this.iYear);  
		
		this.oButtonLeft = new ButtonMonthMinus();
		this.oButtonRight = new ButtonMonthPlus();
		
		
		if (this.bShowMonthAndYearInFirstLine)
		{
			this.add(this.oColForBedienpanel,E2_INSETS.I_0_0_0_0);
			
			String cHelp = new MyE2_String(E2_CalendarComponent.MonatsNamen[this.iMonth-1]).CTrans()+"  "+this.iYear;
			MyE2_Label oLabMonatJahr = new MyE2_Label(cHelp,MyE2_Label.STYLE_TITEL_BIG());
	
			
			if (this.bshowNavigators)
			{
				

//				MyE2_Row oRowNav = new MyE2_Row(MyE2_Row.STYLE_NO_BORDER_NO_INSETS());
//				
//				oRowNav.add(new ButtonJanuary(), oInsetsButtons);
//				oRowNav.add(new ButtonYearMinus(), oInsetsButtons);
//				oRowNav.add(new ButtonMonthMinus(), oInsetsButtons);
//				oRowNav.add(new ButtonToday(),oInsetsButtons);
//				oRowNav.add(new ButtonMonthPlus(), oInsetsButtons);
//				oRowNav.add(new ButtonYearPlus(), oInsetsButtons);
//				oRowNav.add(new ButtonDecember(),oInsetsButtons);
//				this.oColForBedienpanel.add(oRowNav, MyE2_Column.LAYOUT_LEFT(E2_INSETS.I_0_0_0_10));

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

		
//		// Welche termine hat der benutzer
//		String cSQL = "select distinct to_char(datum,'YYYY-MM-DD') from "+ bibE2.cTO()+".jt_termin_user,"+ bibE2.cTO()+".jt_termin where id_user = "+
//		 					bibALL.get_ID_USER() +" and jt_termin.id_termin = jt_termin_user.id_termin";
//		 
//		String[][] cTermin = bibDB.EinzelAbfrageInArray(cSQL,"@@@@@@@");
		
//		Vector<String> vBelegteTage = new Vector<String>();
//		for (int i=0;i<cTermin.length;i++)
//			vBelegteTage.add(cTermin [i][0]);
		
	
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
				this.oGridForCalendar.add(this.buildDayComponent(this, new myDateHelper(i,this.iMonth,this.iYear)), oGLCalendar);
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
			
			E2_CalendarComponent oThis = E2_CalendarComponent.this;
			
			this.set_ListenInhalt(oThis.ccMonate,false)  ;
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
				E2_CalendarComponent oThis = E2_CalendarComponent.this;
				
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
			
			E2_CalendarComponent oThis = E2_CalendarComponent.this;
			
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
				E2_CalendarComponent oThis = E2_CalendarComponent.this;
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
				E2_CalendarComponent oThis = E2_CalendarComponent.this;
				oThis.iYear--;
				int nYear = oThis.iYear;
				if (!oThis.mapJahre.containsKey(nYear))
				{
					oThis.mapJahre.put(nYear, Integer.toString(nYear));
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
				E2_CalendarComponent oThis = E2_CalendarComponent.this;
				oThis.iYear++;

				int nYear = oThis.iYear;
				if (!oThis.mapJahre.containsKey(nYear))
				{
					oThis.mapJahre.put(nYear, Integer.toString(nYear));
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
				E2_CalendarComponent oThis = E2_CalendarComponent.this;
				
				oThis.iMonth--;
				if (oThis.iMonth==0)
				{
					oThis.iYear--;
					oThis.iMonth=12;
					
					int nYear = oThis.iYear;
					if (!E2_CalendarComponent.mapJahre.containsKey(nYear))
					{
						E2_CalendarComponent.mapJahre.put(nYear, Integer.toString(nYear));
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
				E2_CalendarComponent oThis = E2_CalendarComponent.this;
				
				oThis.iMonth++;
				if (oThis.iMonth==13)
				{
					oThis.iYear++;
					oThis.iMonth=1;
					
					int nYear = oThis.iYear;
					if (!E2_CalendarComponent.mapJahre.containsKey(nYear))
					{
						E2_CalendarComponent.mapJahre.put(nYear, Integer.toString(nYear));
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

	
	private class ButtonToday extends MyE2_Button
	{
		public ButtonToday() 
		{
			super(E2_ResourceIcon.get_RI("to_point.png"), true);
			this.setToolTipText(new MyE2_String("Springe zum aktuellen Monat").CTrans());

			this.add_oActionAgent(new ownAction());
		}

		private class ownAction extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) 
			{
				E2_CalendarComponent oThis = E2_CalendarComponent.this;
				
				myDateHelper oHelper= null;
				try {
					oHelper = new myDateHelper(new Date());
				} catch (myException e) {
					// TODO Auto-generated catch block
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
	}


	
	
	public abstract Component buildDayComponent(E2_CalendarComponent ownCalendarGrid, myDateHelper oDateHelper)  throws myException;


	public ButtonMonthMinus get_oButtonLeft()
	{
		return oButtonLeft;
	}


	public ButtonMonthPlus get_oButtonRight()
	{
		return oButtonRight;
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



	
	
}
