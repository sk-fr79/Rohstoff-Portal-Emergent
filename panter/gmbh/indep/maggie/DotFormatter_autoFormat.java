package panter.gmbh.indep.maggie;

import java.util.Locale;

public class DotFormatter_autoFormat extends DotFormatter
{

	boolean bIsOK = false;
	
	public boolean get_bIsOK()
	{
		return bIsOK;
	}

	public DotFormatter_autoFormat(String sOriginalString, int iDecimalSize, Locale sLocale, boolean bAround, int iGroupSize, boolean bStartEvalInConstructor)
	{
		super(sOriginalString, iDecimalSize, sLocale, bAround, iGroupSize, bStartEvalInConstructor);
		this.bIsOK = this.doFormat();
	}

	public DotFormatter_autoFormat(String sOriginalString, int iDecimalSize, Locale sLocale, boolean bAround, int iGroupSize)
	{
		super(sOriginalString, iDecimalSize, sLocale, bAround, iGroupSize);
		this.bIsOK = this.doFormat();
	}

	public DotFormatter_autoFormat(String sOriginalString)
	{
		super(sOriginalString, 3, Locale.GERMAN, true, 3);
		this.bIsOK = this.doFormat();
	}
	
	/**
	 * 2016-05-19
	 * @param sOriginalString
	 */
	public DotFormatter_autoFormat(String sOriginalString,  int iDecimalSize)
	{
		super(sOriginalString, iDecimalSize, Locale.GERMAN, true, 3);
		this.bIsOK = this.doFormat();
	}


}
