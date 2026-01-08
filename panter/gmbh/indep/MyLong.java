package panter.gmbh.indep;

import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import panter.gmbh.indep.maggie.DotFormatterGermanFixed;

public class MyLong implements IF_BasicTypeWrapper
{

	public static String ALL_OK = 						"ALL_OK";
	public static String ERROR_NULLSTRING = 			"ERROR_NULLSTRING";
	public static String ERROR_FALSE_NUMBERSTRING = 	"ERROR_FALSE_NUMBERSTRING";
	public static String ERROR_GENERATING_INTEGER = 	"ERROR_GENERATING_INTEGER";
	
	private Long oLong = null;
	private String cOriginalString = null;
	private String cErrorCODE = MyLong.ALL_OK;
	
	private String cFormatedLongString = null;
	
	
	public MyLong(String originalString)
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
					this.oLong = new Long(oDF.getStringUnFormated());
				}
				catch (Exception ex)
				{
					this.oLong = null;
					this.cErrorCODE = MyLong.ERROR_GENERATING_INTEGER;
				}
			}
			else
			{
				this.oLong = null;
				this.cErrorCODE = MyLong.ERROR_FALSE_NUMBERSTRING;
			}
		}
		else
		{
			this.oLong = null;
			this.cErrorCODE = MyLong.ERROR_NULLSTRING;
		}
		
		if (this.oLong != null)
		{
			this.cFormatedLongString = MyNumberFormater.formatDez(this.oLong.longValue(), true);
		}

	}


	public MyLong(String originalString, Long NullValue, Long FalseValue)
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
					this.oLong = new Long(oDF.getStringUnFormated());
				}
				catch (Exception ex)
				{
					this.oLong = null;
					this.cErrorCODE = MyLong.ERROR_GENERATING_INTEGER;
				}
			}
			else
			{
				this.oLong = FalseValue;
				this.cErrorCODE = MyLong.ERROR_FALSE_NUMBERSTRING;
				
			}
		}
		else
		{
			this.oLong = NullValue;
			this.cErrorCODE = MyLong.ERROR_NULLSTRING;
		}
		
		if (this.oLong != null)
		{
			this.cFormatedLongString = MyNumberFormater.formatDez(this.oLong.longValue(), true);
		}
	}

	
	
	public MyLong(long l) {
		this(""+l);
	}
	
	

	public String get_cErrorCODE() 
	{
		return cErrorCODE;
	}



	public String get_cOriginalString() 
	{
		return cOriginalString;
	}



	public Long get_oLong() 
	{
		return oLong;
	}
	
	
	public Long getLong() {
		return oLong;
	}
	
	
	public Integer getInt() {
		return oLong.intValue();
	}
	
	
	public long get_lValue()
	{
		return oLong.longValue();
	}
	
	public int get_iValue()
	{
		return oLong.intValue();
	}

	
	public long get_lValue(long valueWhenNull)
	{
		if (this.oLong==null)
		{
			return valueWhenNull;
		}
		else
		{
			return this.oLong.longValue();
		}
	}
	
	
	/**
	 * 
	 * @return s "" wenn es kein long-string oder NULL war  ---- oder den unformatierten (kein tausender-punkt) long-wert
	 */
	public String get_cUF_LongString()
	{
		if (this.oLong==null)
		{
			return "";
		}
		else
		{
			return ""+this.oLong.longValue();
		}
	}
	
	/**
	 * 
	 * @return s "" wenn es kein long-string oder NULL war  ---- oder den formatierten (tausender-punkt) long-wert
	 */
	public String get_cF_LongString()
	{
		if (this.oLong==null)
		{
			return "";
		}
		else
		{
			return this.cFormatedLongString;
		}
	}

	
	public boolean get_bOK()
	{
		return this.cErrorCODE.equals(MyLong.ALL_OK);
	}

	public boolean isOK()	{
		return this.cErrorCODE.equals(MyBigDecimal.ALL_OK);
	}
	
	public boolean isNotOK()	{
		return !this.cErrorCODE.equals(MyBigDecimal.ALL_OK);
	}
	

	@Override
	public String get_formated_string(MyMetaFieldDEF def) throws myException {
		if (def == null) {
			throw new myException("MyMetaFieldDEF def MUST NOT BE NULL");
		}
		
		return (S.isFull(this.cFormatedLongString)?this.cFormatedLongString:null);
		
	}

	
	/**
	 * 
	 * @param lValue
	 * @return true, wenn lValue != null und <>0 ist
	 */
	public static boolean isNotNull(Long lValue) {
		return ! MyLong.isNull(lValue);
	}
	
	
	/**
	 * 
	 * @param lValue
	 * @return true, wenn lValue == null oder ==0 ist
	 */
	public static boolean isNull(Long lValue) {
		if (lValue == null || lValue.longValue()==0) {
			return true;
		}
		return false;
	}
	

	public Long add(int i) {
		Long lHelp = new Long(i);
		if (this.oLong!=null) {
			lHelp = new Long(this.oLong.longValue()+i);
		}
		this.oLong = lHelp;
		return this.oLong;
	}
	
	public Long add(long i) {
		Long lHelp = new Long(i);
		if (this.oLong!=null) {
			lHelp = new Long(this.oLong.longValue()+i);
		}
		this.oLong = lHelp;
		return this.oLong;
	}
	
	public static boolean areEquals(Long l1, Long l2) {
		if (l1==null && l2 == null) {
			return true;
		} else if  (l1==null && l2 != null) {
			return false;
		} else if  (l1!=null && l2 == null) {
			return false;
		} else {
			return l1.longValue()==l2.longValue();
		}
	}



	@Override
	public Object getBasicObject() throws DataTypeException {
		if (this.isOK()) {
			return oLong;
		} else {
			if (S.isFull(cErrorCODE)) {
				throw new DataTypeExceptionInterpreterError(cErrorCODE, "Error interpreting Long data !");
			} else {
				throw new DataTypeExceptionInterpreterError("UNKNOWN", "Error interpreting Long data !");
			}
		}
	}
	
} 
