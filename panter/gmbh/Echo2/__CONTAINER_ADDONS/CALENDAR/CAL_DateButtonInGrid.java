package panter.gmbh.Echo2.__CONTAINER_ADDONS.CALENDAR;

import java.util.GregorianCalendar;
import java.util.Vector;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.Border;
import nextapp.echo2.app.Color;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorGray;
import panter.gmbh.Echo2.FontsAndColors.E2_FontBoldItalic;
import panter.gmbh.Echo2.FontsAndColors.E2_FontPlain;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public class CAL_DateButtonInGrid extends MyE2_Button 
{
	private myDateHelper oDateHelper = null;
	
	public CAL_DateButtonInGrid(myDateHelper dateHelper, Vector<String> vDaysWithTermin)  throws myException
	{
		super(""+dateHelper.get_IDay().intValue());
		oDateHelper = dateHelper;
		
		if (vDaysWithTermin.contains(oDateHelper.get_cDateFormat_ISO_FORMAT()))
			this.setFont(new E2_FontBoldItalic());
		else
			this.setFont(new E2_FontPlain());
		
		// der aktuelle tag
		if (dateHelper.equals(new myDateHelper(new GregorianCalendar())))
			this.setBorder(new Border(2,Color.BLACK,Border.STYLE_SOLID));
			
		this.setAlignment(new Alignment(Alignment.CENTER,Alignment.DEFAULT));
		
		
	}

	public myDateHelper get_oDateHelper() 
	{
		return oDateHelper;
	}
	
	
	public void markAsClicked(boolean bIsClicked) throws myException
	{
		if (bIsClicked)
			this.setBackground(new E2_ColorGray(200));
		else
			this.setBackground(new E2_ColorBase(-10));
		
	}
	
	
}
