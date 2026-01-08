package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Insets;
import nextapp.echo2.app.MutableStyle;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.RecursiveSearch.E2_RecursiveSearch_IF_Component;
import panter.gmbh.Echo2.components.MyE2IF__Component;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.Echo2.components.MyE2_Label;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;

public class CAL_CalendarGrid extends MyE2_Grid 
{

	private 	Insets    	oInsetsForButtons = E2_INSETS.I_2_2_2_2;
	private  	boolean 	bShowMonthAndYearInFirstLine = false;
	private  	boolean 	bshowNavigators = false;
	
	private     int     	iMonth = 0;
	private     int 		iYear = 0;
	
	public static String[] MonatsNamen = {"Januar","Februar","März","April","Mai","Juni",
											"Juli","August","September","Oktober","November","Dezember"};
	
	private     CAL_BasicModuleContainer  oCalendarBasicRow = null;
	 
	private     myDateHelper  oDateLastClicked = null;
	
	//	baut ein grid mit buttons, fuer jeden tag einen, in der klassischen kalender-ansicht 
	public CAL_CalendarGrid(int 					Monat, 	
							int 					Jahr, 	
							Insets 					InsetsForButtons, 
							boolean 				showMonthAndYearInFirstLine,
							boolean 				showNavigators,
							CAL_BasicModuleContainer   	CalendarBasicRow) throws myException
	{		

		super(7,MyE2_Grid.STYLE_GRID_NO_BORDER());
		
		this.iMonth = Monat;
		this.iYear  = Jahr;
		this.oInsetsForButtons = InsetsForButtons;
		this.bShowMonthAndYearInFirstLine = showMonthAndYearInFirstLine;
		this.bshowNavigators = showNavigators;
		this.oCalendarBasicRow = CalendarBasicRow;
		
		this.baue_calender(); 
	 }
	 

	public void baue_calender() throws myException
	{
		this.removeAll();
		
		myDateHelper 				oDateHelperFirstDayOfMonth = new myDateHelper(1,this.iMonth,this.iYear);  
		
		
		if (this.bShowMonthAndYearInFirstLine)
		{
			String cHelp = new MyE2_String(CAL_CalendarGrid.MonatsNamen[this.iMonth-1]).CTrans()+"  "+this.iYear;
			MyE2_Label oLabMonatJahr = new MyE2_Label(cHelp);
			
			if (this.bshowNavigators)
			{
				Insets oInsetsTitel = new Insets(2,2,2,20);
				this.add(oLabMonatJahr,5,oInsetsTitel);
				this.add(new ButtonLeft(),oInsetsTitel);
				this.add(new ButtonRight(),oInsetsTitel);
			}
			else
			{
				this.add(oLabMonatJahr,7,this.oInsetsForButtons);
			}
			
		}
		 
		MutableStyle oStyleItalic = MyE2_Label.STYLE_NORMAL_ITALLIC();
		 // ueberschrift schreiben (mo-so)
		this.add(new MyE2_Label(new MyE2_String("Mo"),oStyleItalic),this.oInsetsForButtons);
		this.add(new MyE2_Label(new MyE2_String("Di"),oStyleItalic),this.oInsetsForButtons);
		this.add(new MyE2_Label(new MyE2_String("Mi"),oStyleItalic),this.oInsetsForButtons);
		this.add(new MyE2_Label(new MyE2_String("Do"),oStyleItalic),this.oInsetsForButtons);
		this.add(new MyE2_Label(new MyE2_String("Fr"),oStyleItalic),this.oInsetsForButtons);
		this.add(new MyE2_Label(new MyE2_String("Sa"),oStyleItalic),this.oInsetsForButtons);
		this.add(new MyE2_Label(new MyE2_String("So"),oStyleItalic),this.oInsetsForButtons);		

		
		// Welche termine hat der benutzer
		String cSQL = "select distinct to_char(datum,'YYYY-MM-DD') from "+ bibE2.cTO()+".jt_termin_user,"+ bibE2.cTO()+".jt_termin where id_user = "+
		 					bibALL.get_ID_USER() +" and jt_termin.id_termin = jt_termin_user.id_termin";
		 
		String[][] cTermin = bibDB.EinzelAbfrageInArray(cSQL,"@@@@@@@");
		
		Vector<String> vBelegteTage = new Vector<String>();
		for (int i=0;i<cTermin.length;i++)
			vBelegteTage.add(cTermin [i][0]);
		
	
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
		
		for (int i=1;i<32;i++)
		{
			if (myDateHelper.getActualMonth(oCalCounter) == this.iMonth)
			{
				CAL_DateButtonInGrid oButHelp = new CAL_DateButtonInGrid(new myDateHelper(i,this.iMonth,this.iYear),vBelegteTage);
				oButHelp.add_oActionAgent(new actionClickOnDay());
				this.add(oButHelp, this.oInsetsForButtons);
				
				// schauen, ob bei neuaufbau einer liste ein button der letztgeklickte war
				if (this.oDateLastClicked != null)
				{
					if (oButHelp.get_oDateHelper().get_IDay().intValue()==oDateLastClicked.get_IDay().intValue() &&
						oButHelp.get_oDateHelper().get_IMonth().intValue()==oDateLastClicked.get_IMonth().intValue() &&
						oButHelp.get_oDateHelper().get_IYear().intValue()==oDateLastClicked.get_IYear().intValue())
					{
						oButHelp.markAsClicked(true);
					}
				}
				
			}
			else
			{
				break;
			}
			oCalCounter = myDateHelper.Add_Day_To_Calendar(1,oCalCounter);
			 
		 }

	}
	
	
	public void openDay(myDateHelper oDate) throws myException
	{
		// jetzt den zuletzt geklickten markieren
		Vector<MyE2IF__Component> vButtons = new E2_RecursiveSearch_IF_Component(CAL_CalendarGrid.this,true).get_vE2IF_Components();
		for (int i=0;i<vButtons.size();i++)
		{
			if (vButtons.get(i) instanceof CAL_DateButtonInGrid)
			{
				CAL_DateButtonInGrid oBut = (CAL_DateButtonInGrid)vButtons.get(i);
				if (oBut.get_oDateHelper().equals(oDate))
					oBut.doActionPassiv();
				
			}		
		}
	}
	
	
	private class actionClickOnDay extends XX_ActionAgent
	{
		public void executeAgentCode(ExecINFO oExecInfo) throws myException
		{
			if (oExecInfo.get_MyActionEvent()==null)
				throw new myException(this,": Error MUST NOT BE Called outside Event !");
			
			CAL_CalendarGrid.this.oCalendarBasicRow.get_oColumnDay().buildDay(
					((CAL_DateButtonInGrid)oExecInfo.get_MyActionEvent().getSource()).get_oDateHelper()
					);
			
			// jetzt den zuletzt geklickten markieren
			Vector<MyE2IF__Component> vButtons = new E2_RecursiveSearch_IF_Component(CAL_CalendarGrid.this,false).get_vE2IF_Components();
			for (int i=0;i<vButtons.size();i++)
			{
				if (vButtons.get(i) instanceof CAL_DateButtonInGrid)
				{
					if (vButtons.get(i)==oExecInfo.get_MyActionEvent())
					{
						((CAL_DateButtonInGrid)vButtons.get(i)).markAsClicked(true);
						CAL_CalendarGrid.this.oDateLastClicked = ((CAL_DateButtonInGrid)vButtons.get(i)).get_oDateHelper(); 
					}
					else
					{
						((CAL_DateButtonInGrid)vButtons.get(i)).markAsClicked(false);
					}
				}		
			}
		}
	}
	
	
	
	private class ButtonLeft extends MyE2_Button
	{
		public ButtonLeft() 
		{
			super(E2_ResourceIcon.get_RI("left.png"), true);
			this.add_oActionAgent(new ownAction());
		}

		private class ownAction extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) 
			{
				CAL_CalendarGrid oThis = CAL_CalendarGrid.this;
				oThis.oCalendarBasicRow.get_oColumnDay().removeAll();
				oThis.oDateLastClicked = null;
				
				oThis.iMonth--;
				if (oThis.iMonth==0)
				{
					oThis.iYear--;
					oThis.iMonth=12;
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

	private class ButtonRight extends MyE2_Button
	{
		public ButtonRight() 
		{
			super(E2_ResourceIcon.get_RI("right.png"), true);
			this.add_oActionAgent(new ownAction());
		}

		private class ownAction extends XX_ActionAgent
		{
			public void executeAgentCode(ExecINFO oExecInfo) 
			{
				CAL_CalendarGrid oThis = CAL_CalendarGrid.this;
				oThis.oCalendarBasicRow.get_oColumnDay().removeAll();
				oThis.oDateLastClicked = null;
				
				oThis.iMonth++;
				if (oThis.iMonth==13)
				{
					oThis.iYear++;
					oThis.iMonth=1;
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

	
	
}
