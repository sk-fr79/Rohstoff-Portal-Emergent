package panter.gmbh.Echo2.components.E2_calendar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.E2_INSETS;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorLLight;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBold;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.Echo2.components.MyE2_Grid;
import panter.gmbh.indep.MyDate;
import panter.gmbh.indep.MyInteger;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public abstract class E2_CalendarComponent_WithDayButtons_Version2 extends E2_CalendarComponent_Version2
{

	
	public E2_CalendarComponent_WithDayButtons_Version2(String cActualDayFormated)	throws myException
	{
		super(	
				new myDateHelper(cActualDayFormated).get_IMonth(), 	//Monate
				new myDateHelper(cActualDayFormated).get_IYear(), 	//Jahr
				E2_INSETS.I_2_2_2_2, 								//Insets
				true, 												//Navigator
				MyE2_Grid.STYLE_GRID_NO_BORDER_INSETS_11(),			//Default Style
				cActualDayFormated 								//Die Default Datum
				);
	}

	public abstract void doDayButtonAction(ExecINFO execInfo) throws myException;
	
	
	@Override
	public Component buildDayComponent(E2_CalendarComponent_Version2 ownCalendarGrid, myDateHelper dateHelper, boolean activeMonth) throws myException
	{
		setActiveMonth(activeMonth);
		
		E2_TF_4_Date_Button oDateButton = new E2_TF_4_Date_Button("" + dateHelper.get_IDay());

		oDateButton.setActiveMonth(isActiveMonth());
		oDateButton.add_oActionAgent(new ownActionAgent());
		oDateButton.EXT().set_O_PLACE_FOR_EVERYTHING(dateHelper);
		
		myDateHelper oDateLastUsedDateInField = null;
		
		try
		{
			oDateLastUsedDateInField = new myDateHelper(this.get_cSelectedDayFormated());
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
		
		if (dateHelper.equals(new myDateHelper(new GregorianCalendar())))
		{
			//heute, markieren mit rahmen
			oDateButton.setBorder(new Border(1,Color.BLACK,Border.STYLE_SOLID));
		}
		else if (oDateLastUsedDateInField!=null && dateHelper.equals(oDateLastUsedDateInField))
		{
			//ubergebener aktueller tag
			oDateButton.setStyle(MyE2_Button.StyleTextButton(new E2_ColorLLight(), new E2_ColorLLight(), Color.BLACK, Color.BLACK));
			oDateButton.setFont( new E2_FontBold());
		}
		else if ( isDateToHighlight(dateHelper) ){
			oDateButton.setStyle(get_StyleForHighlightedButtons());
		}
		else
		{
			oDateButton.setStyle(MyE2_Button.StyleTextButton());
		}
		
		
		return oDateButton;
	}

	
	/**
	 * true, wenn der Tag hervorgehoben werden muss.
	 * @param dateHelper
	 * @return
	 */
	private boolean isDateToHighlight(myDateHelper dateHelper){
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

	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO execInfo) throws myException
		{
			E2_CalendarComponent_WithDayButtons_Version2.this.doDayButtonAction(execInfo);
		}
	}
	
	
	
	/*
	 * sucht sich die richtige seite und baut die komponente neu auf
	 */
	public void SPRINGE_AUF_DATUM(String cDateFormated) throws myException
	{
		MyDate  oDate = new MyDate(cDateFormated);
		
		if (oDate.get_cErrorCODE().equals(MyDate.ALL_OK))
		{
			MyInteger intMonat = oDate.get_INT_MONAT();
			MyInteger intJahr = oDate.get_INT_JAHR();
			
			if (intMonat!=null && intJahr != null)
			{
				this.set_iMonth(intMonat.get_iValue());
				this.set_iYear(intJahr.get_iValue());
				this.set_cSelectedDayFormated(cDateFormated);
				
				this.baue_calender();
			}
		}
	}
}
