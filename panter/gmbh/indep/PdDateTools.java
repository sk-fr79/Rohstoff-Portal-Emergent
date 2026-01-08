/**
 * panter.gmbh.indep
 * @author martin
 * @date 18.08.2020
 * 
 */
package panter.gmbh.indep;

/**
 * @author martin
 * @date 18.08.2020
 *
 */
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class PdDateTools implements Serializable {

    private Integer iDay 	= null;
    private Integer iMonth 	= null;
    private Integer iYear 	= null;

    private GregorianCalendar oCalDate = null;
    private GregorianCalendar oCalFirstDayInMonth = null;
    private GregorianCalendar oCalLastDayInMonth = null;

    private GregorianCalendar oCalFirstDayInYear = null;
    private GregorianCalendar oCalLastDayInYear  = null;

    public PdDateTools(GregorianCalendar oCal)   {

        int Day = oCal.get(Calendar.DAY_OF_MONTH);
        int Month = oCal.get(Calendar.MONTH)+1;
        int Year = oCal.get(Calendar.YEAR);

        this.__do(Day,Month,Year);
    }


    public PdDateTools(Calendar oCal)     {
        int Day = oCal.get(Calendar.DAY_OF_MONTH);
        int Month = oCal.get(Calendar.MONTH)+1;
        int Year = oCal.get(Calendar.YEAR);

        this.__do(Day,Month,Year);
    }


    public PdDateTools(Date oDate)      {
        GregorianCalendar oCal = new GregorianCalendar();
        oCal.setTime(oDate);

        int Day = oCal.get(Calendar.DAY_OF_MONTH);
        int Month = oCal.get(Calendar.MONTH)+1;
        int Year = oCal.get(Calendar.YEAR);

        this.__do(Day,Month,Year);
    }




    /**
     * @param day (1-31)
     * @param month (1-12)
     * @param year ...
     */
    public PdDateTools(int day, int month, int year)  {
        this.__do(day,month,year);
    }




    private void __do(int day, int month, int year) {
        this.iDay = new Integer(day);
        this.iMonth =new Integer(month);
        this.iYear = new Integer(year);
        this.oCalDate = new GregorianCalendar(this.iYear.intValue(),this.iMonth.intValue()-1,this.iDay.intValue());
        this.oCalFirstDayInYear = new GregorianCalendar(this.iYear.intValue(),0,1);
        this.oCalLastDayInYear = new GregorianCalendar(this.iYear.intValue(),11,31);


        /*
         * jetzt den letzten tag im monat finden
         */
        for (int i=31;i>=27;i--) {
                oCalLastDayInMonth = new GregorianCalendar(this.iYear.intValue(),this.iMonth.intValue()-1,i);
                oCalFirstDayInMonth = new GregorianCalendar(this.iYear.intValue(),this.iMonth.intValue()-1,1);

                if (oCalLastDayInMonth.get(Calendar.MONTH)==oCalDate.get(Calendar.MONTH))  {
                    /*
                     * wenn der gleiche monat, dann ok
                     */
                    break;
                }
        }
    }


    /**
     * @returns Calendar.SUNDAY .... Calendar.SATURDAY
     */
    public int get_OwnDayOfWeek()   {
        return this.oCalDate.get(Calendar.DAY_OF_WEEK);
    }


    public GregorianCalendar getCalFirstDayInMonth()   {
        return oCalFirstDayInMonth;
    }


    public GregorianCalendar getCalLastDayInMonth()   {
        return oCalLastDayInMonth;
    }


    public GregorianCalendar get_oCalFirstDayInYear()   {
        return oCalFirstDayInYear;
    }


    public GregorianCalendar get_oCalDate()  {
        return oCalDate;
    }



    /**
     * @return  Day as Integer (1...31)
     */
    public Integer getDay()  {
        return iDay;
    }

    /**
     * @return  Month as Integer (1...12)
     */
    public Integer getMonth()  {
        return iMonth;
    }


    /**
     * @return  Year as Integer (1...31)
     */
    public Integer get_IYear()  {
        return iYear;
    }



    public static int getActualDay()   {
        GregorianCalendar oCal = new GregorianCalendar();
        return oCal.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * @returns Calendar.SUNDAY .... Calendar.SATURDAY
     */
    public static int getActualDayOfWeek()    {
        GregorianCalendar oCal = new GregorianCalendar();
        return oCal.get(Calendar.DAY_OF_WEEK);
    }



    public static int getActualMonth()  {
        GregorianCalendar oCal = new GregorianCalendar();
        return oCal.get(Calendar.MONTH)+1;
    }


    public static int getActualYear()  {
        GregorianCalendar oCal = new GregorianCalendar();
        return oCal.get(Calendar.YEAR);
    }

    public static int getActualDay(GregorianCalendar oCal)   {
        return oCal.get(Calendar.DAY_OF_MONTH);
    }


    /**
     * @returns Calendar.SUNDAY .... Calendar.SATURDAY
     */
    public static int getActualDayOfWeek(GregorianCalendar oCal)    {
        return oCal.get(Calendar.DAY_OF_WEEK);
    }



    public static int getActualMonth(GregorianCalendar oCal)     {
        return oCal.get(Calendar.MONTH)+1;
    }


    public static int getActualYear(GregorianCalendar oCal)     {
        return oCal.get(Calendar.YEAR);
    }




    public static GregorianCalendar addDayToCalendar(int iDays,GregorianCalendar oCal)    {
        GregorianCalendar oCalRueck = (GregorianCalendar)oCal.clone();

        oCalRueck.add(GregorianCalendar.DATE,iDays);

        return oCalRueck;
    }


    public static GregorianCalendar findFirstDayNextMonth(GregorianCalendar oCal)   {
        GregorianCalendar oCalRueck = (GregorianCalendar)oCal.clone();
        for (int i=0;i<31;i++)
        {
            oCalRueck.add(GregorianCalendar.DATE,1);
            if (oCalRueck.get(Calendar.MONTH)>oCal.get(Calendar.MONTH) || oCalRueck.get(Calendar.YEAR)>oCal.get(Calendar.YEAR))
                break;
        }
        return oCalRueck;
    }


    public static GregorianCalendar findFirstDayPreviousMonth(GregorianCalendar oCal)    {
        GregorianCalendar oCalRueck = (GregorianCalendar)oCal.clone();
        for (int i=0;i<31;i++)
        {
            oCalRueck.add(GregorianCalendar.DATE,-1);
            if (oCalRueck.get(Calendar.MONTH)<oCal.get(Calendar.MONTH) || oCalRueck.get(Calendar.YEAR)<oCal.get(Calendar.YEAR))
                break;
        }
        return oCalRueck;
    }




    public static GregorianCalendar findFirstDayOfMonth(GregorianCalendar oCal)    {
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


    public static GregorianCalendar findLastDayOfMonth(GregorianCalendar oCal)  {
        GregorianCalendar oCalRueck = PdDateTools.findFirstDayNextMonth(oCal);
        oCalRueck.add(GregorianCalendar.DATE,-1);
        return oCalRueck;
    }


    public static Date addDayToDate(int iDays,Date date)    {
        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(date);
        GregorianCalendar cal2 = PdDateTools.addDayToCalendar(iDays,cal);
        return cal2.getTime();
    }


    public static Long getDayDifference(Date date1, Date date2)  {
        if (O.isOneNull(date1,date2)) {
            return null;
        }
        long difference = date2.getTime() - date1.getTime();
        long divisor = 1000*60*60*24;
        BigDecimal bdHelp = new BigDecimal(difference).divide(new BigDecimal(divisor),MathContext.DECIMAL128);
        return bdHelp.longValue();
    }



    public static Boolean isDate2GreaterOrEqualDate1DayBase(Date date1, Date date2) {
        if (O.isOneNull(date1,date2)) {
            return null;
        } else {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (df.format(date2).compareTo(df.format(date1))>=0) {
                return true;
            } else {
                return false;
            }
        }
    }


    public static Boolean isDate2EqualDate1DayBase(Date date1, Date date2) {
        if (O.isOneNull(date1,date2)) {
            return null;
        } else {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (df.format(date2).compareTo(df.format(date1))==0) {
                return true;
            } else {
                return false;
            }
        }
    }


    public static Boolean isDate2GreaterDate1DayBase(Date date1, Date date2) {
        if (O.isOneNull(date1,date2)) {
            return null;
        } else {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            if (df.format(date2).compareTo(df.format(date1))>0) {
                return true;
            } else {
                return false;
            }
        }
    }



    /**
     * kurzformat: "31.12.01"
     */
    public static String formatDateKurz(java.util.Date dDate) {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        cRueck = df.format(dDate);

        return (cRueck);
    }

    /**
     * mittelformat: "31.12.2001"
     */
    public static String formatDateNormal(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        cRueck = df.format(dDate);

        return (cRueck);
    }

    /**
     * Langformat: "31. Dezember 2001"
     */
    public static String formatDateLang(Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        cRueck = df.format(dDate);

        return (cRueck);
    }

    // kurzformat: "31.12.01"
    public static String formatDateTimeKurz(Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.SHORT);
        cRueck = df.format(dDate) + " " + tf.format(dDate);

        return (cRueck);
    }

    // mittelformat: "31.12.2001"
    public static String formatDateTimeNormal(Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        cRueck = df.format(dDate) + " " + tf.format(dDate);

        return (cRueck);
    }

    // Langformat: "31. Dezember 2001"
    public static String formatDateTimeLang(Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.LONG);
        cRueck = df.format(dDate) + " " + tf.format(dDate);

        return (cRueck);
    }

    // Langformat: "31. Dezember 2001"
    public static String formatDateTimeLang(Date dDate, Locale oLocale)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG, oLocale);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.LONG, oLocale);
        cRueck = df.format(dDate) + " " + tf.format(dDate);

        return (cRueck);
    }

    // mittelformat: "19:15"
    public static String formatTimeNormal(Date dDate)
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
    public static String zeitstempel(Date dDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd.HHmmss");
        String uhrzeit = sdf.format(dDate);

        return uhrzeit;
    }



    /**
     *
     * @param dDate
     * @return 2012-12-31
     */
    public static String formatDateISO(Date dDate)
    {
        String cRueck = "";

        SimpleDateFormat oDF = new SimpleDateFormat("yyyy-MM-dd");
        cRueck = oDF.format(dDate);
        return (cRueck);
    }





	
}
