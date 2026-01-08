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
public class DotFormatter_maggie_orig
{
    /** Creates a new instance of DotFormater */
    private String sFormating = new String();
    private String sFormated = new String();
    private int iDecimalSize;
    private boolean bAround = true;
    private Locale sLocale;
    private int iGroupSize;
    private double dValue;
    private String cPatternForFormatedString = "";
    private boolean bNeg = false;

    // constructor
    public DotFormatter_maggie_orig(String sFormating, int iDecimalSize, Locale sLocale, boolean bAround, int iGroupSize)
    {
        this.bAround = bAround;
        this.iDecimalSize = iDecimalSize;

		if (sFormating==null || sFormating.equals(""))
		{
			this.sFormating ="";
		}
		else
		{
			if (sFormating.charAt(0) == '-')
			{
				bNeg = true;
				this.sFormating = sFormating.substring(1);
			}
			else
			{
				this.sFormating = sFormating;
			}
		}

        this.sLocale = sLocale;
        this.iGroupSize = iGroupSize;

        this.cPatternForFormatedString = "#,##0";

        if (iDecimalSize > 0)
        {
            this.cPatternForFormatedString += ("." + new String("0000000000000000000000000").substring(0, iDecimalSize));
        }
    }

    private boolean checkChar()
    {
        boolean flag = true;
        int iDotNum = 0;

		if (this.sFormating.equals(""))
		{
			return false;
		}
		
        if ((sFormating.equals(".")) || (sFormating.equals(",")))
        {
            flag = false;
        }

        if ((sFormating.charAt(0) == ',') || (sFormating.charAt(0) == '.'))
        {
            flag = false;
        }

        for (int i = 0; i < sFormating.length(); i++)
        {
            if ((sFormating.charAt(i) == ',') || (sFormating.charAt(i) == '.') || ((sFormating.charAt(i) <= '9') && (sFormating.charAt(i) >= '0')))
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
		
        for (int i = 0; i < sFormating.length(); i++)
        {
            if (sFormating.charAt(i) == cSep)
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
                sTempArray = sFormating.split(",");
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
                sTemp = sFormating.replace('.', ':'); // deal with the numer without decimal
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

    private String getFormatString(String s)
    {
        String sTemp;
        String sFinal = new String();
        String[] sArray;
        String[] sArray2;
        String sFinalTemp = new String();

        if (sLocale == Locale.GERMANY)
        {
            sArray = s.split(",");

            if (sArray[0].indexOf('.') != -1)
            {
                sTemp = sArray[0].replace('.', ':');
                sArray2 = sTemp.split(":");

                for (int i = 0; i < sArray2.length; i++)
                {
                    sFinalTemp += sArray2[i];
                }

                if (sArray.length == 1)
                {
                    sFinal = sFinalTemp;
                }
            }
            else
            {
                sFinal = sArray[0];
            }

            if (sArray.length == 2)
            {
                sFinal += (sFinalTemp + '.' + sArray[1]);
            }
        }
        else
        {
            sTemp = s.replace('.', ':');
            sArray = sTemp.split(":");

            if (sArray[0].indexOf(',') != -1)
            {
                sArray2 = sTemp.split(",");

                for (int i = 0; i < sArray2.length; i++)
                {
                    sFinal += sArray2[i];
                }
            }

            if (sArray.length == 2)
            {
                sFinal += ('.' + sArray[1]);
            }
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
            sFormated = getFormatString(sFormating);
            dValue = Double.valueOf(sFormated).doubleValue();

            return true;
        }
        else
        {
            return false;
        }
    }

    public String getStringValueForDatabase()
    {
        return (sFormated);
    }

    // gibt eine formatierte 
    public String getStringValue()
    {
        return (DotFormatter_maggie_orig.localizedFormat(this.dValue, this.cPatternForFormatedString, this.sLocale));
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
}
