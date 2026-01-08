package panter.gmbh.indep;

import java.math.BigDecimal;
import java.math.RoundingMode;

import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter_autoFormat;



public class MyBigDecimal  implements IF_BasicTypeWrapper
{
	
	
	public static String ALL_OK = 						"ALL_OK";
	public static String ERROR_NULLSTRING = 			"ERROR_NULLSTRING";
	public static String ERROR_FALSE_NUMBERSTRING = 	"ERROR_FALSE_NUMBERSTRING";
	public static String ERROR_GENERATING_DOUBLE = 		"ERROR_GENERATING_DOUBLE";
	
	private BigDecimal bdWert = null;
	private String cErrorCODE = MyBigDecimal.ALL_OK;
	
	
	/**
	 * constructor fuer zahlen vom typ:  2.345,45  (Formatierte eingabe)
	 * @param originalString
	 */
	public MyBigDecimal(String originalString)
	{
		super();
		
		this.__create(this.changeFormatedToUnformated(originalString), null, null);
	}

	
	/**
	 * @param originalString
	 */
	public MyBigDecimal(BigDecimal bd) {
		super();
		this.bdWert=bd;
	}
	
	
	
	/**
	 * 2012-10-30: neuer konstruktor fuer MyBigDecimal 	
	 * @param originalString
	 * @param bdNullValue
	 * @param bdFalseValue
	 * @param StringWasFormated
	 */
	public MyBigDecimal(String originalString, BigDecimal bdNullValue,  BigDecimal bdFalseValue, boolean StringWasFormated)
	{
		super();
		
		if (StringWasFormated)
		{
			this.__create(this.changeFormatedToUnformated(originalString), bdNullValue, bdFalseValue);
		}
		else
		{
			this.__create(originalString, bdNullValue, bdFalseValue);
		}
	}
	

	
	
	//2011-09-28: neuer MyBigDecimal-konstuktor mit einem dotformatter: 
	public MyBigDecimal(DotFormatter_autoFormat oDotFormatter)
	{
		super();
		
		if (oDotFormatter.get_bIsOK())
		{
			try
			{
				this.bdWert = new BigDecimal(oDotFormatter.get_oDouble());
			}
			catch (Exception ex)
			{
				this.bdWert = null;
				this.cErrorCODE = MyBigDecimal.ERROR_GENERATING_DOUBLE;
			}
		}
		else
		{
			this.cErrorCODE = MyBigDecimal.ERROR_FALSE_NUMBERSTRING;
		}
		
	}
	
	
	
	
	public MyBigDecimal(double dWert, Integer iRoundStellen)
	{
		super();
		
		this.bdWert = new BigDecimal(dWert);
		
		if (iRoundStellen != null && iRoundStellen.intValue()>=0)
		{
			this.bdWert = bdWert.setScale(iRoundStellen.intValue(), BigDecimal.ROUND_HALF_UP);
		}
	}
	
	
	
	public MyBigDecimal(long lWert)
	{
		super();
		this.bdWert = new BigDecimal(lWert);
	}
	

	
	
	
	public MyBigDecimal(BigDecimal bd_Wert, Integer iRoundStellen)
	{
		super();
		
		this.bdWert = bd_Wert;
		
		if (this.bdWert!=null && iRoundStellen != null && iRoundStellen.intValue()>=0)
		{
			this.bdWert = bdWert.setScale(iRoundStellen.intValue(), BigDecimal.ROUND_HALF_UP);
		}
		else
		{
			this.cErrorCODE=MyBigDecimal.ERROR_NULLSTRING;
		}
	}
	
	
	
	
	/**
	 * 
	 * @param originalString formated (typ: 2.345,567)
	 * @param dNullValue
	 * @param dFalseValue
	 */
	public MyBigDecimal(String originalString, BigDecimal bdNullValue, BigDecimal bdFalseValue)
	{
		super();
		
		this.__create(this.changeFormatedToUnformated(originalString), bdNullValue, bdFalseValue);
	}

	
	
	public MyBigDecimal(String originalString,  boolean bOriginalIsFormated)
	{
		if (bOriginalIsFormated)
		{
			this.__create(this.changeFormatedToUnformated(originalString), null, null);
		}
		else
		{
			this.__create(originalString, null, null);
		}
	}

	
	/**
	 * 
	 * @return Long -value or null
	 */
	public Long get_longValue() {
		if (this.isOK()) {
			return this.bdWert.longValue();
		}
		return null;
	}
	
	
	private String changeFormatedToUnformated(String pFormated)
	{
		String cFormated = pFormated;
		
		//2016-05-19: zuerst die korrekte eingabe sicherstellen, damit nicht sowas wie 148.4 als 1484 interpretiert wird
		DotFormatter_autoFormat df = new DotFormatter_autoFormat(cFormated, 10);
		if (df.doFormat()) {
			cFormated = df.getStringFormated();
		} else {
			cFormated = "";
		}
		//ende neu 
		
		String cRueck = bibALL.ReplaceTeilString(cFormated, ".", "");           //tausenderpunkt raus
		cRueck = bibALL.ReplaceTeilString(cRueck, ",", ".");                 //dezimalkomma ersetzen
		return cRueck;
	}
	
	private void __create(String originalString, BigDecimal bdNullValue, BigDecimal bdFalseValue)
	{
		
		if (!bibALL.isEmpty(originalString))
		{
			try
			{
				this.bdWert = new BigDecimal(originalString);
			}
			catch (Exception ex)
			{
				this.bdWert = bdFalseValue;
				this.cErrorCODE = MyBigDecimal.ERROR_GENERATING_DOUBLE;
			}
		}
		else
		{
			this.bdWert = bdNullValue;
			this.cErrorCODE = MyBigDecimal.ERROR_NULLSTRING;
		}

	}
	
	
	

	public String get_cErrorCODE() 
	{
		return cErrorCODE;
	}




	public BigDecimal get_bdWert() 
	{
		return this.bdWert;
	}
	
	
	/**
	 * 
	 * @param iAnzahlDez
	 * @return fuer die Zahl 1001.34 wird String: 1.001,34 zurueckgegeben
	 */
	public String get_FormatedRoundedNumber(int iAnzahlDez)
	{
		String cRueck = "";
		
		if (this.bdWert != null)
		{
			return MyNumberFormater.formatDezWithRound(this.bdWert, iAnzahlDez);
		}
		
		return cRueck;
	}

	
	/**
	 * 
	 * @param iAnzahlDez
	 * @return fuer die Zahl 1001.34 wird String: 1.001,34 zurueckgegeben
	 */
	public String getFRounded(int iAnzahlDez) 	{
		return this.get_FormatedRoundedNumber(iAnzahlDez);
	}

	
	
	/**
	 * 
	 * @param iAnzahlDez
	 * @return fuer die Zahl 1001.34 wird String: 1001.34 zurueckgegeben
	 */
	public String get_UnFormatedRoundedNumber(int iAnzahlDez)
	{
		String cRueck = "";
		
		if (this.bdWert != null)
		{
			return MyNumberFormater.formatDez(this.bdWert, iAnzahlDez, false, '.', null, false);
		}
		
		return cRueck;
	}

	
	/**
	 * 
	 * @param iAnzahlDez
	 * @return fuer die Zahl 1001.34 wird String: 1001.34 zurueckgegeben
	 */
	public String getUfRounded(int iAnzahlDez) 	{
		return this.get_UnFormatedRoundedNumber(iAnzahlDez);
	}

	
	
	public boolean get_bOK()	{
		return this.cErrorCODE.equals(MyBigDecimal.ALL_OK);
	}

	
	public boolean isOK()	{
		return this.cErrorCODE.equals(MyBigDecimal.ALL_OK);
	}
	
	public boolean isNotOK()	{
		return !this.cErrorCODE.equals(MyBigDecimal.ALL_OK);
	}
	
	
	
	/**
	 * 2016-06-13: intrinsische methode um einen wert zu subtrahieren
	 */
	public MyBigDecimal subtract_from_me(BigDecimal  bd_sub) throws myException {
		if (this.bdWert==null) {throw new myException(this,"not initialised yet"); }
		this.bdWert = this.bdWert.subtract(bd_sub);
		return this;
	}




	/**
	 * 
	 * @param add_value
	 * @return
	 */
	public BigDecimal add_to_me(BigDecimal  add_value) throws myException  {
		if (this.bdWert==null) {throw new myException(this,"not initialised yet"); }
		this.bdWert = this.bdWert.add(add_value);
		return this.bdWert;
	}

	
	
	/**
	 * 2016-06-13: intrinsische methode um einen wert zu subtrahieren
	 */
	public MyBigDecimal subtract_from_me(MyBigDecimal  bd_sub) throws myException {
		if (this.bdWert==null) {throw new myException(this,"not initialised yet"); }
		if (bd_sub==null || bd_sub.isNotOK()) {throw new myException(this,"parameter not initialised yet"); }
		this.bdWert = this.bdWert.subtract(bd_sub.bdWert);
		return this;
	}




	/**
	 * 
	 * @param add_value
	 * @return
	 */
	public BigDecimal add_to_me(MyBigDecimal  add_value) throws myException  {
		if (this.bdWert==null) {throw new myException(this,"not initialised yet"); }
		if (add_value==null || add_value.isNotOK()) {throw new myException(this,"parameter not initialised yet"); }
		this.bdWert = this.bdWert.add(add_value.bdWert);
		return this.bdWert;
	}

	
	
	
	@Override
	public String get_formated_string(MyMetaFieldDEF def) throws myException {
		if (def == null) {
			throw new myException("MyMetaFieldDEF def MUST NOT BE NULL");
		}
		
		String c= this.get_FormatedRoundedNumber(def.get_FieldDecimals());
		
		return (S.isFull(c)?c:null);
		
	}



	public static BigDecimal multiplicate(BigDecimal bd1, BigDecimal bd2, int iRound) {
		BigDecimal bd_rueck = bd1.multiply(bd2);
		return MyBigDecimal.doRound(bd_rueck, iRound);
	}
	

	/**
	 * 
	 * @param divident
	 * @param divisor
	 * @param iRound
	 * @return  divident/divisor
	 */
	public static BigDecimal divide(BigDecimal divident, BigDecimal divisor, int iRound) {
		BigDecimal bd_rueck = divident.divide(divisor,iRound, RoundingMode.HALF_UP);
		return MyBigDecimal.doRound(bd_rueck, iRound);
	}
	
	
	
	public static BigDecimal doRound(BigDecimal bs, int roundDigits){
		return bs.setScale(roundDigits,BigDecimal.ROUND_HALF_UP);
	}


	
	public static boolean areEquals(BigDecimal bd1, BigDecimal bd2) {
		if (bd1==null && bd2 == null) {
			return true;
		} else if  (bd1==null && bd2 != null) {
			return false;
		} else if  (bd1!=null && bd2 == null) {
			return false;
		} else {
			return bd1.compareTo(bd2) == 0;
		}
	}
	
	
	
	
	@Override
	public Object getBasicObject() throws DataTypeException {
		if (this.isOK()) {
			return this.bdWert;
		} else {
			if (S.isFull(cErrorCODE)) {
				throw new DataTypeExceptionInterpreterError(cErrorCODE, "Error interpreting Double data !");
			} else {
				throw new DataTypeExceptionInterpreterError("UNKNOWN", "Error interpreting Double data !");
			}
		}
	}
	
	
}
