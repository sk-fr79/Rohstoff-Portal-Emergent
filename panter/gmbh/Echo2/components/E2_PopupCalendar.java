package panter.gmbh.Echo2.components;

import java.util.Calendar;
import java.util.Vector;

import nextapp.echo2.app.TextField;
import panter.gmbh.Echo2.Container.E2_BasicModuleContainer;
import panter.gmbh.indep.exceptions.myException;

public class E2_PopupCalendar extends XX_PopUpCalendar
{

	//Basiscalendar mit update des Textfeldes
	public E2_PopupCalendar(Calendar date, TextField otf,E2_BasicModuleContainer container)
	{
		super(date, otf, container);
	}
	
	//Basiscalendar mit update des Textfeldes
	public E2_PopupCalendar(Calendar date, Vector<TextField> vtf,E2_BasicModuleContainer container)
	{
		super(date, vtf, container);
	}
	
	public E2_PopupCalendar(Calendar date, TextField otf)
	{
		super(date, otf, new E2_BasicModuleContainer());
	}

	@Override
	public void Do_SomethingOnOK_Button() throws myException
	{
	}

}
