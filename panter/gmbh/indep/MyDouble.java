package panter.gmbh.indep;

import java.util.Locale;

import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;



public class MyDouble implements IF_BasicTypeWrapper
{
	
	public static int    VALUE1_EQUAL_VALUE2 = 0;
	public static int    VALUE1_GE_VALUE2 = 1;
	public static int    VALUE1_LE_VALUE2 = -1;
	
	
	public static String ALL_OK = 						"ALL_OK";
	public static String ERROR_NULLSTRING = 			"ERROR_NULLSTRING";
	public static String ERROR_FALSE_NUMBERSTRING = 	"ERROR_FALSE_NUMBERSTRING";
	public static String ERROR_GENERATING_DOUBLE = 		"ERROR_GENERATING_DOUBLE";
	
	private Double oDouble = null;
	private String cOriginalString = null;
	private String cErrorCODE = MyDouble.ALL_OK;
	
	
	/**
	 * constructor fuer zahlen vom typ:  2.345,45  (Formatierte eingabe)
	 * @param originalString
	 */
	public MyDouble(String originalString)
	{
		super();
		
		this.__create(originalString, null, null);
	}

	/**
	 * 
	 * @param originalString
	 * @param dNullValue
	 * @param dFalseValue
	 */
	public MyDouble(String originalString, Double dNullValue, Double dFalseValue)
	{
		super();
		
		this.__create(originalString, dNullValue, dFalseValue);
	}

	
	
	public MyDouble(String originalString,boolean bOriginalIsFormated)
	{
		if (bOriginalIsFormated)
		{
			this.__create(originalString, null, null);
		}
		else
		{
			this.__create(bibALL.ReplaceTeilString(originalString,".",","), null, null);
		}
	}

	
	private void __create(String originalString, Double dNullValue, Double dFalseValue)
	{
		this.cOriginalString = originalString;
		
		if (this.cOriginalString.startsWith("-,"))
		{
			this.cOriginalString="-0"+this.cOriginalString.substring(1);
		}
		else if (this.cOriginalString.startsWith(","))
		{
			this.cOriginalString="0"+this.cOriginalString;
		}
		
		if (!bibALL.isEmpty(this.cOriginalString))
		{
			DotFormatter oDF = new DotFormatter(this.cOriginalString,6,Locale.GERMAN,true,3);
			if (oDF.doFormat())
			{
				try
				{
					this.oDouble = new Double(oDF.getDoubleValue());
				}
				catch (Exception ex)
				{
					this.oDouble = null;
					this.cErrorCODE = MyDouble.ERROR_GENERATING_DOUBLE;
				}
			}
			else
			{
				this.oDouble = dFalseValue;
				this.cErrorCODE = MyDouble.ERROR_FALSE_NUMBERSTRING;
			}
		}
		else
		{
			this.oDouble = dNullValue;
			this.cErrorCODE = MyDouble.ERROR_NULLSTRING;
		}

	}
	
	
	

	public String get_cErrorCODE() 
	{
		return cErrorCODE;
	}



	public String get_cOriginalString() 
	{
		return cOriginalString;
	}



	public Double get_oDouble() 
	{
		return oDouble;
	}
	
	
	
	public static boolean equal(double d1, double d2, int iDecimalSize)
	{
		String c1 = MyNumberFormater.formatDez(bibALL.Round(d1, iDecimalSize), iDecimalSize, false);
		String c2 = MyNumberFormater.formatDez(bibALL.Round(d2, iDecimalSize), iDecimalSize, false);
		
		return c1.equals(c2);
	}
	
	
	public boolean get_bOK()
	{
		return this.cErrorCODE.equals(MyDouble.ALL_OK);
	}

	
	@Override
	public String get_formated_string(MyMetaFieldDEF def) throws myException {
		if (def == null) {
			throw new myException("MyMetaFieldDEF def MUST NOT BE NULL");
		}
		
		String c= MyNumberFormater.formatDez(bibALL.Round(this.oDouble.doubleValue(), def.get_FieldDecimals()), def.get_FieldDecimals(), true);
		
		return (S.isFull(c)?c:null);
		
	}

	
	@Override
	public Object getBasicObject() throws DataTypeException {
		if (this.isOK()) {
			return oDouble;
		} else {
			if (S.isFull(cErrorCODE)) {
				throw new DataTypeExceptionInterpreterError(cErrorCODE, "Error interpreting Double data !");
			} else {
				throw new DataTypeExceptionInterpreterError("UNKNOWN", "Error interpreting Double data !");
			}
		}
	}
	
	
}
