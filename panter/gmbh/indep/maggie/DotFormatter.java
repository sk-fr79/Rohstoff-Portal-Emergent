/*
 * DotFormater.java
 *
 * Created on August 25, 2003, 2:47 PM
 */
package panter.gmbh.indep.maggie;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;


/**
 *
 * @author  maggie
 */

//digits
//thousands separators
//decimal separators
// grouping separator 3 or 4(thousands seperator)
public class DotFormatter
{
    /** Creates a new instance of DotFormater */
    private String sOriginalString = new String();
    private String sFormated = new String();
    private int iDecimalSize;
    private boolean bAround = true;
    private Locale sLocale;
    private int iGroupSize;
    private double dValue;
    private String cPatternForFormatedString = "";
    private boolean bNeg = false;

    private Double oDouble = null;
    private Long   oLong   = null;
    
    
    /**
     * 
     * @param sOriginalString
     * @param iDecimalSize
     * @param sLocale
     * @param bAround
     * @param iGroupSize
     */
    public DotFormatter(String sOriginalString, int iDecimalSize, Locale sLocale, boolean bAround, int iGroupSize)
    {
        this.bAround = bAround;
        this.iDecimalSize = iDecimalSize;

		if (sOriginalString==null || sOriginalString.equals(""))
		{
			this.sOriginalString ="";
		}
		else
		{
			if (sOriginalString.charAt(0) == '-')
			{
				bNeg = true;
				this.sOriginalString = sOriginalString.substring(1);
			}
			else
			{
				this.sOriginalString = sOriginalString;
			}
		}

		/*
		 * german und germany werden als germany verwaltet
		 */
        this.sLocale = sLocale;
        if (this.sLocale == Locale.GERMAN) this.sLocale=Locale.GERMANY;
        	
        this.iGroupSize = iGroupSize;

        this.cPatternForFormatedString = "#,##0";

        if (iDecimalSize > 0)
        {
            this.cPatternForFormatedString += ("." + new String("0000000000000000000000000").substring(0, iDecimalSize));
        }
    }

    
    
    // constructor
    public DotFormatter(String sOriginalString, int iDecimalSize, Locale sLocale, boolean bAround, int iGroupSize,boolean bStartEvalInConstructor)
    {
        this.bAround = bAround;
        this.iDecimalSize = iDecimalSize;

		if (sOriginalString==null || sOriginalString.equals(""))
		{
			this.sOriginalString ="";
		}
		else
		{
			if (sOriginalString.charAt(0) == '-')
			{
				bNeg = true;
				this.sOriginalString = sOriginalString.substring(1);
			}
			else
			{
				this.sOriginalString = sOriginalString;
			}
		}

		/*
		 * german und germany werden als germany verwaltet
		 */
        this.sLocale = sLocale;
        if (this.sLocale == Locale.GERMAN) this.sLocale=Locale.GERMANY;
        	
        this.iGroupSize = iGroupSize;

        this.cPatternForFormatedString = "#,##0";

        if (iDecimalSize > 0)
        {
            this.cPatternForFormatedString += ("." + new String("0000000000000000000000000").substring(0, iDecimalSize));
        }
        
        if (bStartEvalInConstructor)
        {
	        this.doFormat();
        }
    }

    
    
    
    private boolean checkChar()
    {
        boolean flag = true;
        int iDotNum = 0;

		if (this.sOriginalString.equals(""))
		{
			return false;
		}
		
        if ((sOriginalString.equals(".")) || (sOriginalString.equals(",")))
        {
            flag = false;
        }

        if ((sOriginalString.charAt(0) == ',') || (sOriginalString.charAt(0) == '.'))
        {
            flag = false;
        }

        for (int i = 0; i < sOriginalString.length(); i++)
        {
            if ((sOriginalString.charAt(i) == ',') || (sOriginalString.charAt(i) == '.') || ((sOriginalString.charAt(i) <= '9') && (sOriginalString.charAt(i) >= '0')))
            {
                continue;
            }
            else
            {
                flag = false;

                break;
            }
        }

		char cSep = '.';
		if (sLocale == Locale.GERMANY) cSep =',';
		
        for (int i = 0; i < sOriginalString.length(); i++)
        {
            if (sOriginalString.charAt(i) == cSep)
            {
                iDotNum++;
            }

            if (iDotNum > 1)
            {
                flag = false;

                break;
            }
        }
 
 
        return flag;
    }

    private boolean isCorrect()
    {
        String sTemp = new String();
        boolean flag = true;
        String[] sTempArray;
        String[] sTempArray2;

        if (checkChar())
        {
            if (sLocale == Locale.GERMANY)
            {
                sTempArray = sOriginalString.split(",");
                sTemp = sTempArray[0].replace('.', ':'); // deal with the numer without decimal
                sTempArray2 = sTemp.split(":");
                sTemp = new String();

                if (sTempArray2.length > 1)
                {
                    if (sTempArray2[0].length() > iGroupSize)
                    {
                        flag = false;
                    }

                    for (int i = 1; i < sTempArray2.length; i++)
                    {
                        if (sTempArray2[i].length() != iGroupSize)
                        {
                            flag = false;

                            break;
                        }
                    }
                }

                // check decimal part
                if (sTempArray.length == 2)
                {
                    if (sTempArray[1].indexOf(".") != -1)
                    {
                        flag = false;
                    }

                    if ((sTempArray[1].length() > iDecimalSize) && (bAround == false))
                    {
                        flag = false;
                    }
                }
            }

            // not in germany format
            if (sLocale != Locale.GERMANY)
            {
                sTemp = sOriginalString.replace('.', ':'); // deal with the numer without decimal
                sTempArray = sTemp.split(":");
                sTempArray2 = sTempArray[0].split(",");
                sTemp = new String();

                if (sTempArray2.length > 1)
                {
                    if (sTempArray2[0].length() > iGroupSize)
                    {
                        flag = false;
                    }

                    for (int i = 1; i < sTempArray.length; i++)
                    {
                        if (sTempArray2[i].length() != iGroupSize)
                        {
                            flag = false;

                            break;
                        }
                    }
                }

                // check decimal part
                if (sTempArray.length == 2)
                {
                    if (sTempArray[1].indexOf(",") != -1)
                    {
                        flag = false;
                    }

                    if ((sTempArray[1].length() > iDecimalSize) && (bAround == false))
                    {
                        flag = false;
                    }
                }
            }
        }
        else
        {
            flag = false;
        }

        return flag;
    }

    
    /*
     * methode baut das format im klassischen sinne, was eine datenbank lesen kann:
     * keine tausender-trenner und dezimal-punkt
     */
    private String getFormatString(String sOriginal)
    {
        String sFinal = new String();
        String[] sArray;
        String[] sArray2;

        Character cDecimalSeparator = new Character(',');
        Character cThousandSeparator = new Character('.');
        
        /*
         * falls nicht deutsch, dann umdrehen
         */
        if (sLocale != Locale.GERMANY)
        {
            cDecimalSeparator = new Character('.');
            cThousandSeparator = new Character(',');
        }
         
        /*
         * info: the replacing of the charvalues to ":" is necessary because
         * the split-method is using reglar expression, and the '.' is not working !  
         */
        
        
    	/*
    	 * trennen vorkomma / nachkomma
    	 */
        sArray = sOriginal.replace(cDecimalSeparator.charValue(), ':').split(":");
        sFinal = "";
        
        /*
         * sArray[0] = vorkomma von tausender-separator befreien
         */
        if (sArray[0].indexOf(cThousandSeparator.charValue()) != -1)
        {
            sArray2 = sArray[0].replace(cThousandSeparator.charValue(),':').split(":");

            for (int i = 0; i < sArray2.length; i++)
            {
                sFinal += sArray2[i];
            }
        }
        else
        {
        	sFinal = sArray[0];
        }

        if (sArray.length == 2)
        {
            sFinal += ('.' + sArray[1]);
        }

            
        if (bAround)
        {
            BigDecimal bdTemp = new BigDecimal(sFinal);
            BigDecimal bdValue = bdTemp.setScale(iDecimalSize, BigDecimal.ROUND_HALF_UP);
            sFinal = bdValue.toString();
        }

        if (bNeg == true)
        {
            sFinal = '-' + sFinal;
        }

        return (sFinal);
    }

    public boolean doFormat()
    {
        if (isCorrect())
        {
            sFormated = getFormatString(sOriginalString);
            dValue = Double.valueOf(sFormated).doubleValue();
            this.oDouble = new Double(sFormated);
            this.oLong = new Long(Math.round(dValue));
            
            return true;
        }
        else
        {
            return false;
        }
    }

    public String getStringUnFormated()
    {
        return (sFormated);
    }

    // gibt eine formatierte 
    public String getStringFormated()
    {
        return (DotFormatter.localizedFormat(this.dValue, this.cPatternForFormatedString, this.sLocale));
    }

    public double getDoubleValue()
    {
        return (dValue);
    }

    public static String localizedFormat(double value, String pattern, Locale loc)
    {
        NumberFormat nf = NumberFormat.getNumberInstance(loc);
        DecimalFormat df = (DecimalFormat) nf;
        df.applyPattern(pattern);

        String output = df.format(value);

        return output;
    }



	/**
	 * @return Double-Value if doFormat()=false, then null 
	 */
	public Double get_oDouble() 
	{
		return oDouble;
	}
	
	/**
	 * @return Long-Value if doFormat()=false, then null 
	 */
	public Long get_oLong() 
	{
		return oLong;
	}
	
}
