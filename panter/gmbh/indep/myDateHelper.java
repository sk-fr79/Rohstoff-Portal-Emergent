/*
 * Created on 18.03.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package panter.gmbh.indep;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;
import panter.gmbh.indep.myVectors.HMAP;
import panter.gmbh.indep.myVectors.VEK;


/**
 * @author martin
 *
 */
public class myDateHelper 
{

    
    private Integer iDay 	= null;
    private Integer iMonth 	= null;
    private Integer iYear 	= null;
    
    private GregorianCalendar oCalDate = null;
    private GregorianCalendar oCalFirstDayInMonth = null;
    private GregorianCalendar oCalLastDayInMonth = null;
    
    private GregorianCalendar oCalFirstDayInYear = null; 
    private GregorianCalendar oCalLastDayInYear  = null;

    public myDateHelper(GregorianCalendar oCal) throws myException
    {
    	
    	int Day = oCal.get(Calendar.DAY_OF_MONTH);
		int Month = oCal.get(Calendar.MONTH)+1;	
		int Year = oCal.get(Calendar.YEAR);
		
		this.__do(Day,Month,Year);
		
    }

    
    public myDateHelper(Calendar oCal) throws myException
    {
    	int Day = oCal.get(Calendar.DAY_OF_MONTH);
		int Month = oCal.get(Calendar.MONTH)+1;	
		int Year = oCal.get(Calendar.YEAR);
		
		this.__do(Day,Month,Year);
		
    }


    public myDateHelper(Date oDate) throws myException
    {
    	GregorianCalendar oCal = new GregorianCalendar();
    	oCal.setTime(oDate);
    	
//    	int Day = oDate. getDate();
//		int Month = oDate.getMonth()+1;	
//		int Year = oDate.getYear()+1900;

	   	int Day = oCal.get(Calendar.DAY_OF_MONTH);
		int Month = oCal.get(Calendar.MONTH)+1;	
		int Year = oCal.get(Calendar.YEAR);

		
		this.__do(Day,Month,Year);
		
    }

    
    
    public boolean equals(myDateHelper oDateCompare)
    {
    	boolean bRueck = false;
    	
    	if (oDateCompare.get_IDay().intValue()==this.iDay.intValue() && 
    		oDateCompare.get_IMonth().intValue()==this.iMonth.intValue() &&
    		oDateCompare.get_IYear().intValue()==this.iYear.intValue())
    		bRueck = true;
    	
    	return bRueck;
    }
    
    
    /**
     * @param day (1-31)
     * @param month (1-12)
     * @param year ...
     */
    public myDateHelper(int day, int month, int year) throws myException
    {
    	this.__do(day,month,year);
    }
    
    
    /**
     * 
     * @param ccDateInStandardFormat
     * @throws myException
     */
    public myDateHelper(String ccDateInStandardFormat)  throws myException
    {
    	TestingDate oTest = new TestingDate(ccDateInStandardFormat);
    	if (!oTest.testing())
    		throw new myException("myDateHelper: false date-Format !!");
    	
    	String cDateInStandardFormat = oTest.get_FormatedDateString("dd.mm.yyyy");
    	
    	try
    	{
    		Integer day = new Integer(cDateInStandardFormat.substring(0,2));
    		Integer mon = new Integer(cDateInStandardFormat.substring(3,5));
    		Integer year = new Integer(cDateInStandardFormat.substring(6,10));
    		
    		this.__do(day.intValue(),mon.intValue(),year.intValue());
    	}
    	catch (Exception ex)
    	{
    		throw new myException("myDateHelper: Error Converting numbers in Constructors !!");
    	}
	
    	
    	
    }
    
    
    

    private void __do(int day, int month, int year) throws myException
    {
        try
        {
            this.iDay = new Integer(day);	
            this.iMonth =new Integer(month);
            this.iYear = new Integer(year);
         }
        catch (Exception ex)
        {
            throw new myException("myDateHelper:False numbers !");
        }
        
        
        try
        {
            this.oCalDate = new GregorianCalendar(this.iYear.intValue(),this.iMonth.intValue()-1,this.iDay.intValue());
            this.oCalFirstDayInYear = new GregorianCalendar(this.iYear.intValue(),0,1);
            this.oCalLastDayInYear = new GregorianCalendar(this.iYear.intValue(),11,31);
            
            
            /*
             * jetzt den letzten tag im monat finden
             */
            for (int i=31;i>=27;i--)
            {
                try
                {
                    oCalLastDayInMonth = new GregorianCalendar(this.iYear.intValue(),this.iMonth.intValue()-1,i);
                    oCalFirstDayInMonth = new GregorianCalendar(this.iYear.intValue(),this.iMonth.intValue()-1,1);
                    
                    if (oCalLastDayInMonth.get(Calendar.MONTH)==oCalDate.get(Calendar.MONTH)) 
                    {
	                    /*
	                     * wenn der gleiche monat, dann ok
	                     */
	                    break;
                    }
                }
                catch (Exception ex)
                {}
            }

            
            
        }
        catch (Exception ex)
        {
            throw new myException("myDateHelper:Date "+this.iDay.intValue()+"."+this.iMonth.intValue()+"."+this.iYear.intValue()+" false !");
        }

    }
    
    /**
     * @return yyyy-MM-dd
     * @throws myException
     */
    public String get_cDateFormat_ISO_FORMAT() throws myException
    {
        return this._get_cDateFormat("yyyy-MM-dd", this.oCalDate);
    }
    
    /**
     * @return dd.MM.yyyy
     * @throws myException
     */
    public String get_cDateFormatForMask() throws myException
    {
        return this._get_cDateFormat("dd.MM.yyyy", this.oCalDate);
    }

    /**
     * @return yyyy-MM-dd
     * @throws myException
     */
    public String get_cDateFormat_ISO_FORMAT_LastDayInMonth() throws myException
    {
        return this._get_cDateFormat("yyyy-MM-dd", this.oCalLastDayInMonth);
    }
 
     public String get_cDateFormatForMask_LastDayInMonth() throws myException
    {
        return this._get_cDateFormat("dd.MM.yyyy", this.oCalLastDayInMonth);
    }
    
    
     /**
      * @return yyyy-MM-dd
      * @throws myException
      */
    public String get_cDateFormat_ISO_FORMAT_FirstDayInMonth() throws myException
    {
    	String cHelp = this._get_cDateFormat("yyyy-MM-dd", this.oCalLastDayInMonth);
    	
        return cHelp.substring(0,8)+"01";
    }
    
    
    public String get_cDateFormatForMask_FirstDayInMonth() throws myException
    {
    	String cHelp = this._get_cDateFormat("dd.MM.yyyy", this.oCalLastDayInMonth);
        return "01"+cHelp.substring(2);
    }

    

    /**
     * @return yyyy-MM-dd
     * @throws myException
     */
    public String get_cDateFormat_ISO_FORMAT_FirstDayInYear() throws myException
    {
        return this._get_cDateFormat("yyyy-MM-dd", this.oCalFirstDayInYear);
    }
 
     public String get_cDateFormatForMask_FirstDayInYear() throws myException
    {
        return this._get_cDateFormat("dd.MM.yyyy", this.oCalFirstDayInYear);
    }
    

     /**
      * @return yyyy-MM-dd
      * @throws myException
      */
     public String get_cDateFormat_ISO_FORMAT_LastDayInYear() throws myException
     {
         return this._get_cDateFormat("yyyy-MM-dd", this.oCalLastDayInYear);
     }
  
      public String get_cDateFormatForMask_LastDayInYear() throws myException
     {
         return this._get_cDateFormat("dd.MM.yyyy", this.oCalLastDayInYear);
     }
     
    
    
    
    private String _get_cDateFormat(String cFormat, GregorianCalendar oCal) throws myException
    {
        String cRueck = null;
        
        try
        {
            SimpleDateFormat oSDF = new  SimpleDateFormat(cFormat);
            cRueck = oSDF.format(oCal.getTime());
        }
        catch (Exception ex)
        {
            throw new myException("myDateHelper:_get_cDateFormat:False Mask:"+cFormat);
        }
        
        return cRueck;
        
    }
 
    
    
    
    /**
     * @returns Calendar.SUNDAY .... Calendar.SATURDAY
     */
     public int get_OwnDayOfWeek()
    {
    	return this.oCalDate.get(Calendar.DAY_OF_WEEK);
    }
    
    
    
    



	public GregorianCalendar get_oCalFirstDayInMonth() 
	{
		return oCalFirstDayInMonth;
	}




	public GregorianCalendar get_oCalLastDayInMonth() 
	{
		return oCalLastDayInMonth;
	}
 
	
	public GregorianCalendar get_oCalFirstDayInYear() 
	{
		return oCalFirstDayInYear;
	}
 
	
	
	public GregorianCalendar get_oCalDate() 
	{
		return oCalDate;
	}

	
	
	/**
	 * @return  Day as Integer (1...31)
	 */
	public Integer get_IDay() 
	{
		return iDay;
	}

	/**
	 * @return  Month as Integer (1...12)
	 */
	public Integer get_IMonth() 
	{
		return iMonth;
	}

	
	/**
	 * @return  Year as Integer (1...31)
	 */
	public Integer get_IYear() 
	{
		return iYear;
	}

	
	
	
	
	/**
	 * 
	 * @return acutal date in format "DD.MM.YYYY"
	 * @throws myException
	 */
	public static String get_actual_date_formated() throws myException {
		GregorianCalendar oCal = new GregorianCalendar();
		return new myDateHelper(oCal).get_cDateFormatForMask();
	}
	
	
	
	
    public static int getActualDay()
    {
		GregorianCalendar oCal = new GregorianCalendar();
		return oCal.get(Calendar.DAY_OF_MONTH);
    }
    
    
    /**
     * @returns Calendar.SUNDAY .... Calendar.SATURDAY
     */
    public static int getActualDayOfWeek()
    {
		GregorianCalendar oCal = new GregorianCalendar();
		return oCal.get(Calendar.DAY_OF_WEEK);
    }
    
    
    
    public static int getActualMonth()
    {
		GregorianCalendar oCal = new GregorianCalendar();
		return oCal.get(Calendar.MONTH)+1;
    }
    
    
    public static int getActualYear()
    {
		GregorianCalendar oCal = new GregorianCalendar();
		return oCal.get(Calendar.YEAR);
    }



    public static int getActualDay(GregorianCalendar oCal)
    {
		return oCal.get(Calendar.DAY_OF_MONTH);
    }
    
    
    /**
     * @returns Calendar.SUNDAY .... Calendar.SATURDAY
     */
    public static int getActualDayOfWeek(GregorianCalendar oCal)
    {
		return oCal.get(Calendar.DAY_OF_WEEK);
    }
    
    
    
    public static int getActualMonth(GregorianCalendar oCal)
    {
		return oCal.get(Calendar.MONTH)+1;
    }
    
    
    public static int getActualYear(GregorianCalendar oCal)
    {
		return oCal.get(Calendar.YEAR);
    }


    
    /**
     * @param macht aus 31.12.2006  ->  2006-12-31 
     * @return
     */
    public static String ChangeNormalString2DBFormatString(String cDate)
    {
    	return cDate.substring(6,10)+"-"+cDate.substring(3,5)+"-"+cDate.substring(0,2);
    }
	
	
	
    
    /**
     * @param macht aus 2006-12-31  -> 31.12.2006
     * @return
     */
    public static String ChangeDBFormatStringToNormalString(String cDBDateString)
    {
    	return cDBDateString.substring(8,10)+"."+cDBDateString.substring(5,7)+"."+cDBDateString.substring(0,4);
    	
    }
    
    /**
     * @param macht aus 2006-12-31  -> 31.12.
     * @return
     */
    public static String ChangeDBFormatStringToShortDate(String cDBDateString)
    {
    	return cDBDateString.substring(8,10)+"."+cDBDateString.substring(5,7)+".";
    	
    }
    
  
    
    /**
     * @param macht aus 31.12.2011  -> 31.12.11
     * @return
     */
    public static String ChangeFormatStringToDateWithoutYear(String cDateString)
    {
    	return cDateString.substring(0,6)+cDateString.substring(8,10);
    }
    
    public static GregorianCalendar Add_Day_To_Calendar(int iDays,GregorianCalendar oCal)
    {
    	GregorianCalendar oCalRueck = (GregorianCalendar)oCal.clone();
    	
    	oCalRueck.add(GregorianCalendar.DATE,iDays);
    	
    	return oCalRueck;
    }
    

    /**
     * neue methode mit MyGregorianCalendar
     * @author martin
     * @date 02.12.2019
     *
     * @param iDays
     * @param oCal
     * @return
     */
    public static MyGregorianCalendar Add_Day_To_Calendar(int iDays,MyGregorianCalendar oCal)
    {
    	GregorianCalendar oCalRueck = (GregorianCalendar)oCal.clone();
    	
    	oCalRueck.add(GregorianCalendar.DATE,iDays);
    	
    	return new MyGregorianCalendar(oCalRueck);
    }
    
    
    public static GregorianCalendar Find_First_Day_NextMonth(GregorianCalendar oCal)
    {
    	GregorianCalendar oCalRueck = (GregorianCalendar)oCal.clone();
    	for (int i=0;i<31;i++)
    	{
    		oCalRueck.add(GregorianCalendar.DATE,1);
    		if (oCalRueck.get(Calendar.MONTH)>oCal.get(Calendar.MONTH) || oCalRueck.get(Calendar.YEAR)>oCal.get(Calendar.YEAR))
    			break;
    	}
    	return oCalRueck;
    }


    

    /**
     * neue methode mit MyGregorianCalendar
     * @author martin
     * @date 02.12.2019
     *
     * @param oCal
     * @return
     */
    public static MyGregorianCalendar Find_First_Day_NextMonth(MyGregorianCalendar oCal)
    {
    	GregorianCalendar oCalRueck = (GregorianCalendar)oCal.clone();
    	for (int i=0;i<31;i++)
    	{
    		oCalRueck.add(GregorianCalendar.DATE,1);
    		if (oCalRueck.get(Calendar.MONTH)>oCal.get(Calendar.MONTH) || oCalRueck.get(Calendar.YEAR)>oCal.get(Calendar.YEAR))
    			break;
    	}
    	return new MyGregorianCalendar(oCalRueck);
    }

    
    
    public static GregorianCalendar Find_First_Day_PreviousMonth(GregorianCalendar oCal)
    {
    	GregorianCalendar oCalRueck = (GregorianCalendar)oCal.clone();
    	for (int i=0;i<31;i++)
    	{
    		oCalRueck.add(GregorianCalendar.DATE,-1);
    		if (oCalRueck.get(Calendar.MONTH)<oCal.get(Calendar.MONTH) || oCalRueck.get(Calendar.YEAR)<oCal.get(Calendar.YEAR))
    			break;
    	}
    	return oCalRueck;
    }

    
    
    
    public static GregorianCalendar Find_First_Day_OfMonth(GregorianCalendar oCal)
    {
    	GregorianCalendar oCalRueck = (GregorianCalendar)oCal.clone();
    	if (oCalRueck.get(Calendar.DAY_OF_MONTH)==1)
    		return oCalRueck;
    	
    	for (int i=0;i<31;i++)
    	{
    		oCalRueck.add(GregorianCalendar.DATE,-1);
    		if (oCalRueck.get(Calendar.DAY_OF_MONTH)==1)
    			break;
    	}
    	return oCalRueck;
    }

    
    public static GregorianCalendar Find_Last_Day_OfMonth(GregorianCalendar oCal)
    {
    	GregorianCalendar oCalRueck = myDateHelper.Find_First_Day_NextMonth(oCal);
   		oCalRueck.add(GregorianCalendar.DATE,-1);
    	return oCalRueck;
    }

    
    
    /**
     * Bewegt ein Calendar-Objekt, das am Wochenende steht auf den folgenden montag 
     * @param oCal
     */
    public static void Move_Calendar_ToMondayIfWeekend(GregorianCalendar oCal)
    {
    	
    	//System.out.println(myDateHelper.FormatDateNormal(oCal.getTime()));
    	
		if (oCal.get(GregorianCalendar.DAY_OF_WEEK)==7)
		{
			oCal.add(GregorianCalendar.DATE,2);   // samstag ->montag
		}
		
		//System.out.println(myDateHelper.FormatDateNormal(oCal.getTime()));
		
		if (oCal.get(GregorianCalendar.DAY_OF_WEEK)==1)
		{
			oCal.add(GregorianCalendar.DATE,1);	// sonntag ->dienstag
		}
		
		//System.out.println(myDateHelper.FormatDateNormal(oCal.getTime()));
    }

    

    /**
     * Bewegt ein Calendar-Objekt, das am Wochenende steht auf den davorliegenden Freitag 
     * @param oCal
     */
    public static void Move_Calendar_To_Friday_before_ifWeekend(GregorianCalendar oCal)   {
    	
		if (oCal.get(GregorianCalendar.DAY_OF_WEEK)==7) {
			oCal.add(GregorianCalendar.DATE,-1);   // samstag ->freitag
		}
		
		//System.out.println(myDateHelper.FormatDateNormal(oCal.getTime()));
		
		if (oCal.get(GregorianCalendar.DAY_OF_WEEK)==1) {
			oCal.add(GregorianCalendar.DATE,-2);	// sonntag ->dienstag
		}
		
		//System.out.println(myDateHelper.FormatDateNormal(oCal.getTime()));
    }


    
    
    public static long get_DayDifference_Date2_MINUS_Date1(GregorianCalendar oDate1, GregorianCalendar oDate2)
    {
        Date d1 = oDate1.getTime();
        Date d2 = oDate2.getTime();
        long l1 = d1.getTime();
        long l2 = d2.getTime();
        long difference = l2 - l1;

        long divisor = 1000*60*60*24;
        
        double dHelp = ((double)difference)/((double)divisor);
        Double Dhelp = new Double(dHelp);
        return Dhelp.longValue();
    }
    
     
    
    /**
     * 
     * @param cDate1Formated 31.12.2008
     * @param cDate2Formated 31.12.2008
     * @return
     * @throws myException
     */
    public static boolean get_Date1_GreaterEqual_Date2(String cDate1Formated, String cDate2Formated) throws myException
    {
    	TestingDate oDate1 = new TestingDate(cDate1Formated);
    	TestingDate oDate2 = new TestingDate(cDate2Formated);
    	
    	if ((!oDate1.testing()) | (!oDate2.testing()) )
    	{
    		throw new myException("myDateHelper:get_Date1_GreaterEqual_Date2:date-Strings are not allowed !");
    	}
    	
    	String cDate1 = oDate1.get_ISO_DateFormat(false);
    	String cDate2 = oDate2.get_ISO_DateFormat(false);
    	return (cDate1.compareTo(cDate2)>0 || cDate1.equals(cDate2));
    }
    
    
    /**
     * 
     * @param cDate1Formated 31.12.2008
     * @param cDate2Formated 31.12.2008
     * @return
     * @throws myException
     */
    public static boolean get_Date1_Greater_Date2(String cDate1Formated, String cDate2Formated) throws myException
    {
    	TestingDate oDate1 = new TestingDate(cDate1Formated);
    	TestingDate oDate2 = new TestingDate(cDate2Formated);
    	
    	if ((!oDate1.testing()) | (!oDate2.testing()) )
    	{
    		throw new myException("myDateHelper:get_Date1_GreaterEqual_Date2:date-Strings are not allowed !");
    	}
    	
    	String cDate1 = oDate1.get_ISO_DateFormat(false);
    	String cDate2 = oDate2.get_ISO_DateFormat(false);
    	return (cDate1.compareTo(cDate2)>0);
    }
    
    
    /**
     * 
     * @param cDate1Formated 31.12.2008
     * @param cDate2Formated 31.12.2008
     * @return
     * @throws myException
     */
    public static boolean get_Date1_LessEqual_Date2(String cDate1Formated, String cDate2Formated) throws myException
    {
    	TestingDate oDate1 = new TestingDate(cDate1Formated);
    	TestingDate oDate2 = new TestingDate(cDate2Formated);
    	
    	if ((!oDate1.testing()) | (!oDate2.testing()) )
    	{
    		throw new myException("myDateHelper:get_Date1_GreaterEqual_Date2:date-Strings are not allowed !");
    	}
    	
    	String cDate1 = oDate1.get_ISO_DateFormat(false);
    	String cDate2 = oDate2.get_ISO_DateFormat(false);
    	return (cDate1.compareTo(cDate2)<0 || cDate1.equals(cDate2));
    }
    
    
    /**
     * 
     * @param cDate1Formated 31.12.2008
     * @param cDate2Formated 31.12.2008
     * @return
     * @throws myException
     */
    public static boolean get_Date1_Less_Date2(String cDate1Formated, String cDate2Formated) throws myException
    {
    	TestingDate oDate1 = new TestingDate(cDate1Formated);
    	TestingDate oDate2 = new TestingDate(cDate2Formated);
    	
    	if ((!oDate1.testing()) | (!oDate2.testing()) )
    	{
    		throw new myException("myDateHelper:get_Date1_GreaterEqual_Date2:date-Strings are not allowed !");
    	}
    	
    	String cDate1 = oDate1.get_ISO_DateFormat(false);
    	String cDate2 = oDate2.get_ISO_DateFormat(false);
    	return (cDate1.compareTo(cDate2)<0);
    }
    
    
    
    
    ///////////////////7
    /**
     * 
     * @param cDate1Formated 31.12.2008
     * @param cDate2Formated 31.12.2008
     * @return
     * @throws myException
     */
    public static boolean get_Date1_GreaterEqual_Date2(TestingDate oDate1, TestingDate oDate2) throws myException
    {
     	
    	if (  oDate1==null || oDate2==null ||  (!oDate1.get_bTestingWasOK()) || (!oDate2.get_bTestingWasOK())) 
    	{
    		throw new myException("myDateHelper:get_Date1_GreaterEqual_Date2:date-Strings are not allowed !");
    	}
    	
    	String cDate1 = oDate1.get_ISO_DateFormat(false);
    	String cDate2 = oDate2.get_ISO_DateFormat(false);
    	return (cDate1.compareTo(cDate2)>0 || cDate1.equals(cDate2));
    }
    
    
    /**
     * 
     * @param cDate1Formated 31.12.2008
     * @param cDate2Formated 31.12.2008
     * @return
     * @throws myException
     */
    public static boolean get_Date1_Greater_Date2(TestingDate oDate1, TestingDate oDate2) throws myException
    {
    	
       	if (  oDate1==null || oDate2==null ||  (!oDate1.get_bTestingWasOK()) || (!oDate2.get_bTestingWasOK())) 
    	{
    		throw new myException("myDateHelper:get_Date1_GreaterEqual_Date2:date-Strings are not allowed !");
    	}
    	
    	String cDate1 = oDate1.get_ISO_DateFormat(false);
    	String cDate2 = oDate2.get_ISO_DateFormat(false);
    	return (cDate1.compareTo(cDate2)>0);
    }
    
    
    /**
     * 
     * @param cDate1Formated 31.12.2008
     * @param cDate2Formated 31.12.2008
     * @return
     * @throws myException
     */
    public static boolean get_Date1_LessEqual_Date2(TestingDate oDate1, TestingDate oDate2) throws myException
    {
       	if (  oDate1==null || oDate2==null ||  (!oDate1.get_bTestingWasOK()) || (!oDate2.get_bTestingWasOK())) 
    	{
    		throw new myException("myDateHelper:get_Date1_GreaterEqual_Date2:date-Strings are not allowed !");
    	}
    	
    	String cDate1 = oDate1.get_ISO_DateFormat(false);
    	String cDate2 = oDate2.get_ISO_DateFormat(false);
    	return (cDate1.compareTo(cDate2)<0 || cDate1.equals(cDate2));
    }
    
    
    /**
     * 
     * @param cDate1Formated 31.12.2008
     * @param cDate2Formated 31.12.2008
     * @return
     * @throws myException
     */
    public static boolean get_Date1_Less_Date2(TestingDate oDate1, TestingDate oDate2) throws myException
    {
       	if (  oDate1==null || oDate2==null ||  (!oDate1.get_bTestingWasOK()) || (!oDate2.get_bTestingWasOK())) 
    	{
    		throw new myException("myDateHelper:get_Date1_GreaterEqual_Date2:date-Strings are not allowed !");
    	}
    	
    	String cDate1 = oDate1.get_ISO_DateFormat(false);
    	String cDate2 = oDate2.get_ISO_DateFormat(false);
    	return (cDate1.compareTo(cDate2)<0);
    }
    
    

    
    
    //////////////////7
    
    
    /**
     * ISO-Format yyyy-MM-dd
     * 
     * @author manfred
     * @date 22.03.2016
     *
     * @param dDate
     * @return
     */
    public static String FromatDateISO(java.util.Date dDate)
    {
    	String cRueck = "";
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    	cRueck = df.format(dDate);
    	return cRueck;
    }
    
    
    
    /**
     * kurzformat: "31.12.01"
     */
    public static String FormatDateKurz(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        cRueck = df.format(dDate);

        return (cRueck);
    }

    /**
     * mittelformat: "31.12.2001"
     */
    public static String FormatDateNormal(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        cRueck = df.format(dDate);

        return (cRueck);
    }

    /**
     * Langformat: "31. Dezember 2001"
     */
    public static String FormatDateLang(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        cRueck = df.format(dDate);

        return (cRueck);
    }

    // kurzformat: "31.12.01"
    public static String FormatDateTimeKurz(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);
        cRueck = df.format(dDate) + " " + tf.format(dDate);

        return (cRueck);
    }

    // mittelformat: "31.12.2001"
    public static String FormatDateTimeNormal(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        cRueck = df.format(dDate) + " " + tf.format(dDate);

        return (cRueck);
    }

    // Langformat: "31. Dezember 2001"
    public static String FormatDateTimeLang(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.LONG);
        cRueck = df.format(dDate) + " " + tf.format(dDate);

        return (cRueck);
    }

    // Langformat: "31. Dezember 2001"
    public static String FormatDateTimeLang(java.util.Date dDate, Locale oLocale)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, oLocale);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.LONG, oLocale);
        cRueck = df.format(dDate) + " " + tf.format(dDate);

        return (cRueck);
    }

    // mittelformat: "19:15"
    public static String FormatTimeNormal(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        cRueck = tf.format(dDate);

        return (cRueck);
    }

    
    /**
     * 
     * @param dDate
     * @return s string in form: 151231.193012  fuer 2015-12-31 19:30:12
     */
    public static String Zeitstempel(java.util.Date dDate) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd.HHmmss");
    	String uhrzeit = sdf.format(dDate);
    	
    	return uhrzeit;
    }
    
    
    /**
     * 
     * @param cDatumBereich1_Start
     * @param cDatumBereich1_Ende
     * @param cDatumBereich2_Start
     * @param cDatumBereich2_Ende
     * @return s False, wenn keine schnittmenge an tagen vorhanden ist, oder wenn ein datumsstring kein datum ist
     */
    public static boolean get_bDatumsBereicheHabenSchnittmengen(String cDatumBereich1_Start, String cDatumBereich1_Ende,String cDatumBereich2_Start, String cDatumBereich2_Ende) throws myException
    {
    	boolean bRueck = false;
    	
    	TestingDate oTest1_Start = new TestingDate(cDatumBereich1_Start);
    	TestingDate oTest1_Ende = new TestingDate(cDatumBereich1_Ende);
    	TestingDate oTest2_Start = new TestingDate(cDatumBereich2_Start);
    	TestingDate oTest2_Ende = new TestingDate(cDatumBereich2_Ende);
    	
    	if (oTest1_Start.testing() && 
    		oTest1_Ende.testing() &&
    		oTest2_Start.testing() &&
    		oTest2_Ende.testing())
    	{
    		if (  (myDateHelper.get_Date1_GreaterEqual_Date2(cDatumBereich2_Start, cDatumBereich1_Start) &&
    			  myDateHelper.get_Date1_LessEqual_Date2(cDatumBereich2_Start, cDatumBereich1_Ende)) ||
    			  (myDateHelper.get_Date1_GreaterEqual_Date2(cDatumBereich1_Start, cDatumBereich2_Start) &&
    			  myDateHelper.get_Date1_LessEqual_Date2(cDatumBereich1_Start, cDatumBereich2_Ende)))
    		{
    			bRueck = true;
    		}
    	}
    		
    	return bRueck;
    }
    

    
    /**
     * 
     * @param cDatumBereich1_Start
     * @param cDatumBereich1_Ende
     * @param cDatumBereich2_Start
     * @param cDatumBereich2_Ende
     * @return s False, wenn keine schnittmenge an tagen vorhanden ist, oder wenn ein datumsstring kein datum ist
     */
    public static boolean get_bDatumsBereicheHabenSchnittmengen(	TestingDate tdDatumBereich1_Start, 
    																TestingDate tdDatumBereich1_Ende,
    																TestingDate tdDatumBereich2_Start, 
    																TestingDate tdDatumBereich2_Ende) throws myException
    {
    	boolean bRueck = false;
    	
    	if (tdDatumBereich1_Start != null &&
    		tdDatumBereich1_Ende  != null &&
    		tdDatumBereich2_Start != null &&
    		tdDatumBereich2_Ende  != null)
    	{
    		if (  (myDateHelper.get_Date1_GreaterEqual_Date2(tdDatumBereich2_Start, tdDatumBereich1_Start) &&
    			  myDateHelper.get_Date1_LessEqual_Date2(tdDatumBereich2_Start, tdDatumBereich1_Ende)) ||
    			  (myDateHelper.get_Date1_GreaterEqual_Date2(tdDatumBereich1_Start, tdDatumBereich2_Start) &&
    			  myDateHelper.get_Date1_LessEqual_Date2(tdDatumBereich1_Start, tdDatumBereich2_Ende)))
    		{
    			bRueck = true;
    		}
    	}
    	else
    	{
    		throw new myException(myDateHelper.class.getName(),"No null values allowed !");
    	}
    		
    	return bRueck;
    }

    
    
    public static boolean isDate(String cDateGermanFormat)
    {
    	
    	try
		{
			new myDateHelper(cDateGermanFormat);
			return true;
		} 
    	catch (myException e)
		{
    		return false;
		}
    	
    }
    
   
    /**
     * 
     * @param cDateString in der Form ddmmyy oder dd.mm.yy oder dd.mm.yyyy wird geprueft, null wenn falsch, dd.mm.yyyy wenn ok
     * @return
     */
    public static String get_CheckedDate(String cDateString)
    {
    	
       	TestingDate oTest = new TestingDate(cDateString);
    	if (!oTest.testing())
    	{
    		return null;
    	}
    	
    	return oTest.get_FormatedDateString("dd.mm.yyyy");
    	
    }
    
    
    /**
     * 
     * @param dDate
     * @return 2012-12-31
     */
    public static String FormatDateISO(java.util.Date dDate)
    {
        String cRueck = "";
        
       	SimpleDateFormat oDF = new SimpleDateFormat("yyyy-MM-dd"); 
    	cRueck = oDF.format(dDate);
        return (cRueck);
    }
 
    
    
    public static GregorianCalendar get_CalendarActual(int iDiffDays)
    {
    	GregorianCalendar  oCal = new GregorianCalendar();
    	
    	if (iDiffDays!=0)
    	{
    		oCal.add(GregorianCalendar.DATE, iDiffDays);
    	}
    	
    	return oCal;
    }
    

    /**
     * 
     * @param difftage
     * @return 31.12.2011
     */
    public static String get_cCalendarActual(int iDiffDays)
    {
    	GregorianCalendar  oCal = new GregorianCalendar();
    	
    	if (iDiffDays!=0)
    	{
    		oCal.add(GregorianCalendar.DATE, iDiffDays);
    	}
    	
    	return myDateHelper.FormatDateNormal(oCal.getTime());
    }
    
    
    /**
     * returns actual Month (JAN = 1, possible iDiffDays form today)
     * @param iDiffDays
     * @return
     */
    public static int get_actualMonth(int iDiffDays) {
    	GregorianCalendar cal = myDateHelper.get_CalendarActual(iDiffDays);
    	return (cal.get(Calendar.MONTH)+1);
    }
    
    /**
     * returns actual Year ( possible iDiffDays form today)
     * @param iDiffDays
     * @return
     */
   public static int get_actualYear(int iDiffDays) {
    	GregorianCalendar cal = myDateHelper.get_CalendarActual(iDiffDays);
    	return (cal.get(Calendar.YEAR));
    }

   /**
    * returns actual Day in year (Starts with 1, possible iDiffDays form today)
    * @param iDiffDays
    * @return
    */
   public static int get_actualDayInYear(int iDiffDays) {
    	GregorianCalendar cal = myDateHelper.get_CalendarActual(iDiffDays);
    	return (cal.get(Calendar.DAY_OF_YEAR));
    }

   /**
    * returns actual Day in Month (Starts with 1, possible iDiffDays form today)
    * @param iDiffDays
    * @return
    */
   public static int get_actualDayOfMonth(int iDiffDays) {
    	GregorianCalendar cal = myDateHelper.get_CalendarActual(iDiffDays);
    	return (cal.get(Calendar.DAY_OF_MONTH));
    }

   /**
    * returns actual date-String i.e. 31.12.2015
    * @param iDiffDays
    * @return
    */
   public static String get_actualDateString(int iDiffDays) {
    	return (myDateHelper.get_cCalendarActual(iDiffDays));
    }
    
    

   /**
    * konvertierung eines java.sql.timestamp zu einem gregorian-calendar
    * @param stamp
    * @return
    */
   public static GregorianCalendar get_timeStampAsCalendar(Timestamp stamp) {
	   if (stamp!=null) {
	       GregorianCalendar calendar =   new GregorianCalendar();// Calendar.getInstance();
	       calendar.setTimeInMillis(stamp.getTime());
	       return calendar;
	   }
	   return null;
   }
   

   /**
    * Format-Platzhalter: yyyy oder yy fuer jahr (ergibt: 2017, 17)
    *                     MM , MMM , MMMM fuer monat  (ergibt: 09, Sep, September)
    *                     dd   fuer Tag des Monats (ergibt: 01)
    *                     HH   fuer Stunde am Tag (23)
    *                     hh   fuer Stunde am Tag (11)
    *                     mm   fuer Minute (59)
    *                     ss   fuer Sekunde (59)
    *                     SSSS fuer Millisekunden (9999)
    * @param cal 
    * @param formating_string
    * @return
    */
   public static String get_formated_date_time(GregorianCalendar cal, String formating_string) {
	    if (cal != null) {
	      	SimpleDateFormat oDF = new SimpleDateFormat(formating_string); 
	      	return oDF.format(cal.getTime());
	    } else {
	    	return null;
	    }
   }
  
   
   /**
    * Format-Platzhalter: yyyy oder yy fuer jahr (ergibt: 2017, 17)
    *                     MM , MMM , MMMM fuer monat  (ergibt: 09, Sep, September)
    *                     dd   fuer Tag des Monats (ergibt: 01)
    *                     HH   fuer Stunde am Tag (23)
    *                     hh   fuer Stunde am Tag (11)
    *                     mm   fuer Minute (59)
    *                     ss   fuer Sekunde (59)
    *                     SSSS fuer Millisekunden (9999)
    * @param cal 
    * @param formating_string
    * @param c_when_null
    * @return
    */
   public static String get_formated_date_time(GregorianCalendar cal, String formating_string, String c_when_null) {
	    if (cal != null) {
	      	SimpleDateFormat oDF = new SimpleDateFormat(formating_string); 
	      	return oDF.format(cal.getTime());
	    } else {
	    	return c_when_null;
	    }
   }

   
   /**
    * 
    * @author martin
    * @date 20.08.2021
    *
    * @param date1
    * @param date2
    * @param daysInWeek (
    * @return
    */
   public static Long getDifferenceBitweenDates(Date date1, Date date2, VEK<DayOfWeek> daysInWeek) {
	   
	   
	   // die nummerierung der funktion GregorianCalendar.get(Calendar.DAY_OF_WEEK) liefer 1(Sunday) bis 7(Saturday)
	   // um die ENUM DayOfWeek verwenden zu koennen muss hier umgesetzt werden
	   HMAP<DayOfWeek,Integer> dOWs = new HMAP<DayOfWeek, Integer>()._put(DayOfWeek.SATURDAY,  7)
																    ._put(DayOfWeek.SUNDAY,    1)
																    ._put(DayOfWeek.MONDAY,    2)
																    ._put(DayOfWeek.TUESDAY,   3)
																    ._put(DayOfWeek.WEDNESDAY, 4)
																    ._put(DayOfWeek.THURSDAY,  5)			   
																    ._put(DayOfWeek.FRIDAY,    6)			   
																    ;
	   VEK<Integer> dayOfWeekValues = new VEK<Integer>();
	   for (DayOfWeek dow: daysInWeek) {
		   dayOfWeekValues._a(dOWs.get(dow));
	   }
	   
	   
	   
	   GregorianCalendar gc = new GregorianCalendar();
	   gc.setTime(date1);
	   gc.get(Calendar.DAY_OF_WEEK);
	   
	   int vorzeichen = 1;
	   Date start = date1;
	   Date ende = date2;
	   if (ende.before(start)) {
		   start=date2;
		   ende = date1;
		   vorzeichen=-1;
	   }
	   
	   GregorianCalendar laufenderTag = new GregorianCalendar();
	   SimpleDateFormat  df = new SimpleDateFormat("yyyy-MM-dd");
	   laufenderTag.setTime(start);
	    
	   long counter = 0;
	   do {
		   if (df.format(laufenderTag.getTime()).equals(df.format(ende))) { 
			   break;
		   }
		   
		   laufenderTag.add(Calendar.DAY_OF_MONTH, 1);
		   if (dayOfWeekValues.contains(laufenderTag.get(Calendar.DAY_OF_WEEK))) {
			   counter++;
		   }

		   
	   } while (true);
	   
	   
	   
	   return vorzeichen*counter;
   }
   
   
   
   
}
