package panter.gmbh.indep;

import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;

public class MyInteger  implements IF_BasicTypeWrapper
{

	public static String ALL_OK = 						"ALL_OK";
	public static String ERROR_NULLSTRING = 			"ERROR_NULLSTRING";
	public static String ERROR_FALSE_NUMBERSTRING = 	"ERROR_FALSE_NUMBERSTRING";
	public static String ERROR_GENERATING_INTEGER = 	"ERROR_GENERATING_INTEGER";
	
	private Integer oInteger = null;
	private String cOriginalString = null;
	private String cErrorCODE = MyInteger.ALL_OK;
	
	private String cFormatedIntegerString = null;
	
	
	public MyInteger(String originalString)
	{
		super();
		
		cOriginalString = originalString;
		
		if (!bibALL.isEmpty(this.cOriginalString))
		{
			DotFormatter oDF = new DotFormatterGermanFixed(originalString);
			if (oDF.doFormat())
			{
				try
				{
					this.oInteger = new Integer(oDF.getStringUnFormated());
				}
				catch (Exception ex)
				{
					this.oInteger = null;
					this.cErrorCODE = MyInteger.ERROR_GENERATING_INTEGER;
				}
			}
			else
			{
				this.oInteger = null;
				this.cErrorCODE = MyInteger.ERROR_FALSE_NUMBERSTRING;
			}
		}
		else
		{
			this.oInteger = null;
			this.cErrorCODE = MyInteger.ERROR_NULLSTRING;
		}
		
		if (this.oInteger != null)
		{
			this.cFormatedIntegerString = MyNumberFormater.formatDez(this.oInteger.intValue(), true);
		}

	}


	public MyInteger(String originalString, Integer NullValue, Integer FalseValue)
	{
		super();
		
		cOriginalString = originalString;
		
		if (!bibALL.isEmpty(this.cOriginalString))
		{
			DotFormatter oDF = new DotFormatterGermanFixed(originalString);
			if (oDF.doFormat())
			{
				try
				{
					this.oInteger = new Integer(oDF.getStringUnFormated());
				}
				catch (Exception ex)
				{
					this.oInteger = null;
					this.cErrorCODE = MyInteger.ERROR_GENERATING_INTEGER;
				}
			}
			else
			{
				this.oInteger = FalseValue;
				this.cErrorCODE = MyInteger.ERROR_FALSE_NUMBERSTRING;
				
			}
		}
		else
		{
			this.oInteger = NullValue;
			this.cErrorCODE = MyInteger.ERROR_NULLSTRING;
		}
		
		if (this.oInteger != null)
		{
			this.cFormatedIntegerString = MyNumberFormater.formatDez(this.oInteger.intValue(), true);
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



	public Integer get_oInteger() 
	{
		return oInteger;
	}
	
	
	public int get_iValue()
	{
		return oInteger.intValue();
	}
	
	
	public boolean get_bOK()
	{
		return this.cErrorCODE.equals(MyInteger.ALL_OK);
	}
	
	
	/**
	 * 
	 * @return s "" wenn es kein Integer-string oder NULL war  ---- oder den unformatierten (kein tausender-punkt) Integer-wert
	 */
	public String get_cUF_IntegerString()
	{
		if (this.oInteger==null)
		{
			return "";
		}
		else
		{
			return ""+this.oInteger.intValue();
		}
	}
	
	/**
	 * 
	 * @return s "" wenn es kein Integer-string oder NULL war  ---- oder den formatierten (tausender-punkt) Integer-wert
	 */
	public String get_cF_IntegerString()
	{
		if (this.oInteger==null)
		{
			return "";
		}
		else
		{
			return this.cFormatedIntegerString;
		}
	}

	
	@Override
	public String get_formated_string(MyMetaFieldDEF def) throws myException {
		if (def == null) {
			throw new myException("MyMetaFieldDEF def MUST NOT BE NULL");
		}
		
		return (S.isFull(this.cFormatedIntegerString)?this.cFormatedIntegerString:null);
		
	}


	
	/**
	 * 
	 * @param lValue
	 * @return true, wenn lValue != null und <>0 ist
	 */
	public static boolean isNotNull(Integer lValue) {
		return ! MyInteger.isNull(lValue);
	}
	
	
	/**
	 * 
	 * @param lValue
	 * @return true, wenn lValue == null oder ==0 ist
	 */
	public static boolean isNull(Integer lValue) {
		if (lValue == null || lValue.intValue()==0) {
			return true;
		}
		return false;
	}
	

	@Override
	public Object getBasicObject() throws DataTypeException {
		if (this.isOK()) {
			return oInteger;
		} else {
			if (S.isFull(cErrorCODE)) {
				throw new DataTypeExceptionInterpreterError(cErrorCODE, "Error interpreting Integer data !");
			} else {
				throw new DataTypeExceptionInterpreterError("UNKNOWN", "Error interpreting Integer data !");
			}
		}
	}
	
	
}
