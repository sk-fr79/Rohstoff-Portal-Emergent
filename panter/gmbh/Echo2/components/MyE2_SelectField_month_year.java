package panter.gmbh.Echo2.components;

import java.util.GregorianCalendar;

import panter.gmbh.indep.myDateHelper;
import panter.gmbh.indep.exceptions.myException;

public class MyE2_SelectField_month_year extends MyE2_SelectField 
{

	public MyE2_SelectField_month_year(int iMonthsBack, int iMonthsInFuture, boolean add_emptyValueInFront) throws myException 
	{
		super();
		
		//fuer die zeitraeume:
		int iAnzahl = iMonthsBack+1+iMonthsInFuture;
		if (add_emptyValueInFront)
		{
			iAnzahl++;
		}
		
		String[][]  arrayString = new String[iAnzahl][2];
		GregorianCalendar  oCalStart = new GregorianCalendar();
		
		int iStart=0;
		if (add_emptyValueInFront)
		{
			arrayString[0][0]="-";
			arrayString[0][1]="";
			iStart++;
		}
		
		
		//wieviele Monate in die Zukunft?
		for (int i=0;i<iMonthsInFuture;i++)
		{
			oCalStart = myDateHelper.Find_First_Day_NextMonth(oCalStart);
		}
		
		for (int i=iStart;i<(iMonthsBack+1+iMonthsInFuture);i++)
		{
			arrayString[i][0]=myDateHelper.FormatDateNormal(oCalStart.getTime()).substring(3);
			arrayString[i][1]=myDateHelper.FormatDateISO(oCalStart.getTime()).substring(0,7);
			
			oCalStart = myDateHelper.Find_First_Day_PreviousMonth(oCalStart);
		}
		this.set_ListenInhalt(arrayString, false);
		this.setSelectedIndex(iStart+iMonthsInFuture);           //aktueller monat

		
	}
	
	
	
}
