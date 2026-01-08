package panter.gmbh.indep;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.TestingDate;

public class MyDate  implements IF_BasicTypeWrapper {
	
	
	//2016-08-05: erweiterte konstruktor-varianten
	public enum DATE_FORMAT {
		DD_MM_YYYY_POINT("DD.MM.YYYY")
		,YYYY_MM_DD_DASH("YYYY-MM-DD")
		
		;
		
		private String formatString = null;
		
		private DATE_FORMAT(String p_format_string) {
			this.formatString=p_format_string;
		}
		
		public String konvert_to_german_standard(String original_date)  throws myException {
			
			String c_rueck = null;
			
			if (original_date.trim().length()!=this.formatString.length()) {
				throw new myException("Date: "+S.NN(original_date,"-")+" does not fit to format-string: "+this.formatString);
			}
			
			switch (this) {
			case DD_MM_YYYY_POINT:
				c_rueck = original_date;
				break;
				
				
				
			case YYYY_MM_DD_DASH:
				c_rueck= original_date.substring(8,10)+"."+original_date.substring(5,7)+"."+original_date.substring(0,4);
				DEBUG.System_println(original_date+" -> "+c_rueck);
				
				break;
			}
			
			return c_rueck;
		}
		
		
	}
	
	
	private String cUmwandlungsergebnis = null;
	private String cDBFormatErgebnis = null;
	
	public static String ALL_OK = 						"ALL_OK";
	public static String ERROR_NULLSTRING = 			"ERROR_NULLSTRING";
	public static String ERROR_FALSE_DATESTRING = 		"ERROR_FALSE_DATESTRING";

	private String cErrorCODE = MyDate.ALL_OK;

	public MyDate(String originalString)
	{
		super();
		this.__create(originalString, null, null);
		
	}


	public MyDate(String originalString, DATE_FORMAT df) throws myException {
		super();
		this.__create(df.konvert_to_german_standard(originalString), null, null);
	}
	
	
	
	public MyDate(String originalString, String NullValue, String FalseValue)
	{
		super();
		this.__create(originalString, NullValue, FalseValue);
		
	}

	

	/**
	 * 2017-01-03: neuer konstruktor
	 * @param cal
	 * @throws myException
	 */
	public MyDate(GregorianCalendar cal) throws myException {
		myDateHelper dateHelper = new myDateHelper(cal);
		
		this.__create(dateHelper.get_cDateFormatForMask(), null, null);
	}
	

	private void __create(String originalString, String NullValue, String FalseValue)
	{
		TestingDate oTestDate = new TestingDate(originalString);
		
		if (S.isEmpty(originalString))
		{
			cUmwandlungsergebnis = NullValue;
			cDBFormatErgebnis = "";
			this.cErrorCODE = MyDate.ERROR_NULLSTRING;
		}
		else
		{
			if (oTestDate.testing())
			{
				cUmwandlungsergebnis = oTestDate.get_FormatedDateString("dd.mm.yyyy");
				cDBFormatErgebnis = oTestDate.get_ISO_DateFormat(false);
			}
			else
			{
				cUmwandlungsergebnis = FalseValue;
				cDBFormatErgebnis = "";
				this.cErrorCODE =MyDate.ERROR_FALSE_DATESTRING;
			}
		}
		
	}
	

	/**
	 * 
	 * @author martin
	 * @date 02.01.2019
	 *
	 * @param date
	 */
	public MyDate(Date date) {
		if (date == null) {
			this.__create("", null, null);
		} else {
			SimpleDateFormat f = new SimpleDateFormat("dd.MM.yyyy");
			this.__create(f.format(date), null,null);
		}
	}
	
	
	public String get_cErrorCODE() 
	{
		return cErrorCODE;
	}

	
	public boolean get_bOK() 
	{
		return cErrorCODE.equals(MyDate.ALL_OK);
	}

	
	
	
	/**
	 * 
	 * @return s Formatierten Wert wie 31.12.2010 oder die jeweiligen String fuer falsch/leer
	 */
	public String get_cUmwandlungsergebnis()
	{
		return cUmwandlungsergebnis;
	}

	
	
	/**
	 * 
	 * @return s Formatierten Wert wie 31.12.2010 oder null
	 */
	public String get_cDateStandardFormat()
	{
		if (this.cErrorCODE.equals(MyDate.ALL_OK))
		{
			return cUmwandlungsergebnis;
		}
		else
		{
			return null;
		}
	}

	

	
	
	/**
	 * 
	 * @return s Leerstring oder String wie:  2009-01-31
	 */
	public String get_cDBFormatErgebnis()
	{
		return cDBFormatErgebnis;
	}
	
	
	
	/**
	 * 
	 * @return s Leerstring oder String wie:  '2009-01-31'   (mit apostroph)
	 */
	public String get_cDBFormatErgebnis_4_SQLString()
	{
		if (S.isEmpty(this.get_cDBFormatErgebnis()))
		{
			return "NULL";
		}
		else
		{
			return "'"+cDBFormatErgebnis+"'";
		}
	}
	

	
	
	
	public MyInteger get_INT_TAG()
	{
		if (this.cErrorCODE.equals(MyDate.ALL_OK))
		{
			return new MyInteger(this.cUmwandlungsergebnis.substring(0,2));
		}
		return null; 
	}

	
	public MyInteger get_INT_MONAT()
	{
		if (this.cErrorCODE.equals(MyDate.ALL_OK))
		{
			return new MyInteger(this.cUmwandlungsergebnis.substring(3,5));
		}
		return null; 
	}

	public MyInteger get_INT_JAHR()
	{
		if (this.cErrorCODE.equals(MyDate.ALL_OK))
		{
			return new MyInteger(this.cUmwandlungsergebnis.substring(6,10));
		}
		return null; 
	}

	
	public GregorianCalendar  get_Calendar()
	{
		if (this.cErrorCODE.equals(MyDate.ALL_OK))
		{
			return new GregorianCalendar(this.get_INT_JAHR().get_iValue(), this.get_INT_MONAT().get_iValue()-1, this.get_INT_TAG().get_iValue());
		}
		return null; 
	
	}
	
	/**
	 * 
	 * @author martin
	 * @date 21.01.2019
	 *
	 * @return date when correct or null
	 */
	public Date  getDate() 	{
		Date d = null;
		
		if (this.cErrorCODE.equals(MyDate.ALL_OK)) {
			GregorianCalendar gc;
			try {
				gc = new GregorianCalendar(this.get_INT_JAHR().get_iValue(), this.get_INT_MONAT().get_iValue()-1, this.get_INT_TAG().get_iValue());
				d = gc.getTime();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return d; 
	}
	

	
	public void set_Date_to_Monday_if_Sat_or_Sun()
	{
		if (this.get_Calendar() != null)
		{
			GregorianCalendar  oCal = this.get_Calendar();
			
			
//			System.out.println(myDateHelper.FormatDateNormal(oCal.getTime()));
			
			
			myDateHelper.Move_Calendar_ToMondayIfWeekend(oCal);
			
			String cDateString = myDateHelper.FormatDateNormal(oCal.getTime());
			
			this.__create(cDateString, null, null);
		}
	}
	
	
	public void set_Date_to_Friday_before_if_Sat_or_Sun()
	{
		if (this.get_Calendar() != null) {
			GregorianCalendar  oCal = this.get_Calendar();
			
			myDateHelper.Move_Calendar_To_Friday_before_ifWeekend(oCal);
			
			String cDateString = myDateHelper.FormatDateNormal(oCal.getTime());
			
			this.__create(cDateString, null, null);
		}
	}
	
	
	
	
	
	
	public GregorianCalendar get_DatePlusDays(int iZahlTageDazu) throws myException {
		
		if (this.get_Calendar() != null)
		{
			GregorianCalendar  oCal = this.get_Calendar();
			oCal.add(GregorianCalendar.DATE,iZahlTageDazu);
			return oCal;
		} else {
			throw new myException(this,"Calculation (1) not valid ! Date is not defined");
		}
	}
	
	
	public String get_DatePlusDaysAsStringFormated(int iZahlTageDazu) throws myException {
		
		if (this.get_Calendar() != null)
		{
			GregorianCalendar  oCal = this.get_Calendar();
			oCal.add(GregorianCalendar.DATE,iZahlTageDazu);
			return myDateHelper.FormatDateNormal(oCal.getTime());
		} else {
			throw new myException(this,"Calculation (2) not valid ! Date is not defined");
		}
	}
	
	
	public MyDate get_DatePlusDaysAsMyDate(int iZahlTageDazu) throws myException {
		
		if (this.get_Calendar() != null)
		{
			GregorianCalendar  oCal = this.get_Calendar();
			oCal.add(GregorianCalendar.DATE,iZahlTageDazu);
			return new MyDate(myDateHelper.FormatDateNormal(oCal.getTime()));
		} else {
			throw new myException(this,"Calculation (2) not valid ! Date is not defined");
		}
	}

	
	/**
	 * 
	 * @author martin
	 * @date 01.03.2021
	 * @deprecated use {@link get_DayDifferenceDate2MINUSDate1()} instead
	 * @param date1
	 * @param date2
	 * @return
	 */
	@Deprecated
	public static Long get_DayDifference_Date2_MINUS_Date1(MyDate date1, MyDate date2) {
		if (date1==null || date2==null || !date1.get_bOK() || !date2.get_bOK() ) {
			return null;
		}
				
		return myDateHelper.get_DayDifference_Date2_MINUS_Date1(date2.get_Calendar(),date1.get_Calendar());
	}
	
	
	
	public static Long get_DayDifferenceDate2MINUSDate1(MyDate date1, MyDate date2) {
		if (date1==null || date2==null || !date1.get_bOK() || !date2.get_bOK() ) {
			return null;
		}
				
		return myDateHelper.get_DayDifference_Date2_MINUS_Date1(date1.get_Calendar(),date2.get_Calendar());
	}
	
	
	
	@Override
	public String get_formated_string(MyMetaFieldDEF def) throws myException {
		if (def == null) {
			throw new myException("MyMetaFieldDEF def MUST NOT BE NULL");
		}
		
		return (S.isFull(this.cUmwandlungsergebnis)?this.cUmwandlungsergebnis:null);
		
	}


	
	public boolean isGreaterThan(MyDate date2) throws myException{
		if (this.isOK() && date2.isOK()) {
			return this.get_cDBFormatErgebnis().compareTo(date2.cDBFormatErgebnis)>0;
		} else {
			throw new myException("Only compare correct myDate-objects !");
		}
	}
	
	
	public boolean isLessThan(MyDate date2) throws myException{
		if (this.isOK() && date2.isOK()) {
			return this.get_cDBFormatErgebnis().compareTo(date2.cDBFormatErgebnis)<0;
		} else {
			throw new myException("Only compare correct myDate-objects !");
		}
	}

	
	public boolean isEqual(MyDate date2) throws myException{
		if (this.isOK() && date2.isOK()) {
			return this.get_cDBFormatErgebnis().equals(date2.cDBFormatErgebnis);
		} else {
			throw new myException("Only compare correct myDate-objects !");
		}
	}


	public boolean isGreaterEqualThan(MyDate date2) throws myException{
		return this.isGreaterThan(date2) || this.isEqual(date2);
	}
	
	public boolean isLessEqualThan(MyDate date2) throws myException{
		return this.isLessThan(date2) || this.isEqual(date2);
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 25.06.2019
	 *
	 * @param date
	 * @param days
	 * @return  returns date+days or null when date==null
	 */
	public static Date getDatePlusDays(Date date, int days) {
		if (date==null) {
			return null;
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE,days);
		return cal.getTime();

	}
	
	
	public static Date getMondayWhenDateInWeekend(Date date) {
		if (date==null) {
			return null;
		}
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		myDateHelper.Move_Calendar_ToMondayIfWeekend(cal);
		return cal.getTime();
	}
	
	
	
	public static Date parse(String c_date)  {
		Date d = null;
		try {
			if (S.isFull(c_date)) {
				
				//jetzt alles zwischen den punkten 
				String[] parts = c_date.split("\\.");
				
				if (parts.length==3) {
					String formatingString = "";
					if (parts[0].length()==1 || parts[0].length()==2) {
						formatingString = formatingString+bibTEXT.repeat("d", parts[0].length())+".";
					}
					if (parts[1].length()==1 || parts[1].length()==2) {
						formatingString = formatingString+bibTEXT.repeat("M", parts[1].length())+".";
					}
				
					if (parts[2].length()==2 || parts[2].length()==4) {
						formatingString = formatingString+bibTEXT.repeat("y", parts[2].length());
					}

				
					d = new SimpleDateFormat(formatingString).parse(c_date);
				
				}
				
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		
		
		return d;
	}
	
	
	
	@Override
	public Object getBasicObject() throws DataTypeException {
		try {
			if (this.isOK()) {
				if (S.isEmpty(cDBFormatErgebnis)) {
					return null;
				} else {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					return df.parse(cDBFormatErgebnis);
				}
			} else {
				if (S.isFull(cErrorCODE)) {
					throw new DataTypeExceptionInterpreterError(cErrorCODE, "Error interpreting Date data !");
				} else {
					throw new DataTypeExceptionInterpreterError("UNKNOWN", "Error interpreting Date data !");
				}
			}
		} catch (DataTypeExceptionInterpreterError e) {
			e.printStackTrace();
			throw new DataTypeException("DataTypeExceptionInterpreterError interpreding date-String <"+S.NN(this.cDBFormatErgebnis)+">");
		} catch (ParseException e) {
			e.printStackTrace();
			throw new DataTypeException("ParseException parsing date-String <"+S.NN(this.cDBFormatErgebnis)+">");
		}
	}
}
