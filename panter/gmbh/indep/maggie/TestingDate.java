/*
 * TestingDate.java
 *
 * Created on July 25, 2003, 7:26 AM
 */

/**
 *
 * @author  maggie
 */
package panter.gmbh.indep.maggie;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class TestingDate
{
    /** Creates a new instance of TestingDate */
    private String sDate;
    private GregorianCalendar greCalendar;
    private String initDateS;
    private String sConverted;

    private boolean  bTestingIsDone = false;
    private boolean  bTestingWasOK  = false;
    
    
    //private Date dDate=null;
    public TestingDate(String cTest)
    {
        initDateS = cTest;
    }



    public Calendar get_date()
    {
        return greCalendar;
    }

    public GregorianCalendar get_Calendar()
    {
        return greCalendar;
    }

    
    // take out the slash and dot from the user input string and inital the initDateS
    public boolean filterString()
    {
    	boolean bRueck = true;
    	
        String s = new String();
        int i;
        i = initDateS.length();

        for (int j = 0; j < i; j++)
        {
            if ((initDateS.charAt(j) >= '0') && (initDateS.charAt(j) <= '9'))
            {
                s = s + initDateS.charAt(j);
            }
            else if (initDateS.charAt(j)!='.' && initDateS.charAt(j)!='/' && initDateS.charAt(j)!='-')   		//aenderung MP am 2009-10-12
            {																							   		//aenderung MP am 2009-10-12
            	//dann fehler !!!																			 	//aenderung MP am 2009-10-12
            	bRueck = false;																					//aenderung MP am 2009-10-12
            }																									//aenderung MP am 2009-10-12
        }

        initDateS = s;
        
        return bRueck;
    }

    public boolean testing()
    { // input a string and set the date string value with dot and date format value

        boolean flag = false;
        String sTemp;
        Integer iYear;
        Integer iYearHead;
        String sYearHead;
        if (!filterString())
        {
        	this.bTestingIsDone = false;
        	return flag;
        }

        this.bTestingIsDone = true;
        
        if ((initDateS.length() != 4) && (initDateS.length() != 6) && (initDateS.length() != 8))
        {
            return (flag);
        }

        sTemp = initDateS.substring(2, 4);

        Integer iMonth = new Integer(sTemp);
        sDate = sTemp + ".";
        sTemp = initDateS.substring(0, 2);

        Integer iDay = new Integer(sTemp);
        sDate = sDate + sTemp + ".";

        if (initDateS.length() == 4) // input date without year then get the current year 
        {
            iYearHead = new Integer(GregorianCalendar.getInstance().get(1));
            sYearHead = iYearHead.toString().substring(0, 4);
            iYear = new Integer(sYearHead);
            sDate = sDate + sYearHead;
        }
        else
        {
            sTemp = initDateS.substring(4, initDateS.length());
            iYear = new Integer(sTemp);

            if (initDateS.length() == 6) // get the rightnow year the fisrt two digit 
            {
                iYearHead = new Integer(GregorianCalendar.getInstance().get(1));
                sYearHead = iYearHead.toString().substring(0, 2);
                sYearHead = sYearHead + sTemp;
                iYear = new Integer(sYearHead);
                sDate = sDate + sYearHead;
            }
            else
            {
                sDate = sDate + sTemp;
            }
        }

        int iY = iYear.intValue();
        int iM = iMonth.intValue() - 1;
        int iD = iDay.intValue();

        try
        {
            greCalendar = new GregorianCalendar(iY, iM, iD);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if ((iY == greCalendar.get(1)) && (iM == greCalendar.get(2)))
        {
            flag = true;
        }

        this.bTestingWasOK = flag;
        
        return (flag);
    }

    public String get_FormatedDateString(String pFormat)
    {
        String sFormat = pFormat.toLowerCase();

        if (sFormat.equals("mmddyyyy") || sFormat.equals("mm.dd.yyyy") || sFormat.equals("mm/dd/yyyy"))
        {
            sConverted = sDate;
        }

        else if (sFormat.equals("ddmmyyyy") || sFormat.equals("dd.mm.yyyy") || sFormat.equals("dd/mm/yyyy"))
        {
            sConverted = sDate.substring(3, 6) + sDate.substring(0, 3) + sDate.substring(6, 10);
        }

        else if (sFormat.equals("yyyymmdd") || sFormat.equals("yyyy.mm.dd") || sFormat.equals("yyyy/mm/dd"))
        {
            sConverted = sDate.substring(6, 10) + "." + sDate.substring(0, 3) + sDate.substring(3, 5);
        }
        else
        {
            sConverted = null;
        }

        return (sConverted);
    }


    /**
     * @param bWithApostroph
     * @return s Datum like:  2008-03-24
     */
    public String get_ISO_DateFormat(boolean bWithApostroph)
    {
 
    	
       	SimpleDateFormat oDF = new SimpleDateFormat("yyyy-MM-dd"); 
    	String cRueck = oDF.format(this.greCalendar.getTime());
       	if (bWithApostroph)
       		cRueck = "'"+cRueck+"'";
 
        return (cRueck);
    	
    	
    }



	public boolean get_bTestingIsDone()
	{
		return bTestingIsDone;
	}
	public boolean get_bTestingWasOK()
	{
		return bTestingWasOK;
	}


    

}
