package panter.gmbh.indep;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import panter.gmbh.indep.exceptions.myException;



public class MyNumberFormater
{
	

    /**
     * @param dZahl
     * @param iAnzahlDez
     * @param bTausender
     * @return
     */
    public static String formatDez(double dZahl, int iAnzahlDez, boolean bTausender)
    {
        String cFormat = "";
        String cHelp = "00000000000000000000000000000000000000";
        String cRueck = "" + dZahl;
        DecimalFormat df;

        if (iAnzahlDez > 0)
        {
            if (bTausender)
            {
                cFormat = "#,###,##0." + cHelp.substring(0, iAnzahlDez);
            }
            else
            {
                cFormat = "#0." + cHelp.substring(0, iAnzahlDez);
            }
        }
        else
        {
            if (bTausender)
            {
                cFormat = "#,###,##0";
            }
            else
            {
                cFormat = "#0";
            }
        }

        DecimalFormatSymbols oSym = new DecimalFormatSymbols();
        oSym.setDecimalSeparator(',');
        oSym.setGroupingSeparator('.');


        df = new DecimalFormat(cFormat,oSym);

        cRueck = df.format(dZahl);

        return (cRueck);
    }


    
    /**
     * @param dZahl
     * @param iAnzahlDez
     * @param bTausender
     * @return
     */
    public static String formatDezForDATABASE(double dZahl, int iAnzahlDez)
    {
        String cFormat = "";
        String cHelp = "00000000000000000000000000000000000000";
        String cRueck = "" + dZahl;
        DecimalFormat df;

        if (iAnzahlDez > 0)
        {
            cFormat = "#0." + cHelp.substring(0, iAnzahlDez);
        }
        else
        {
             cFormat = "#0";
        }

        DecimalFormatSymbols oSym = new DecimalFormatSymbols();
        oSym.setDecimalSeparator('.');
    
        df = new DecimalFormat(cFormat,oSym);

        cRueck = df.format(dZahl);

        return (cRueck);
    }

    
    
    
    /**
     * @param dZahl
     * @param iAnzahlDez
     * @param bTausender
     * @param DezimalSeparator (in Deutschland das Komma)
     * @param GruppenSeparator (in Deutschland der Punkt)
     * @return
     */
    public static String formatDez(double dZahl, int iAnzahlDez, boolean bTausender, char DezimalSeparator, char GruppenSeparator)
    {
    	return MyNumberFormater.formatDez( dZahl,  iAnzahlDez,  bTausender,  DezimalSeparator,  GruppenSeparator, false);
    }

    

    /**
     * @param dZahl
     * @param iAnzahlDez
     * @param bTausender
     * @param DezimalSeparator
     * @param GruppenSeparator
     * @param bRound
     * @return
     */
    public static String formatDez(double dZahl, int iAnzahlDez, boolean bTausender, char DezimalSeparator, char GruppenSeparator, boolean bRound)
    {
    	
    	double dHelp = dZahl;
    	if (bRound)
    	{
    		dHelp =bibALL.Round(dZahl, iAnzahlDez);
    	}
    	
        String cFormat = "";
        String cHelp = "00000000000000000000000000000000000000";
        String cRueck = "" + dHelp;
        DecimalFormat df;

        if (iAnzahlDez > 0)
        {
            if (bTausender)
            {
                cFormat = "#,###,##0." + cHelp.substring(0, iAnzahlDez);
            }
            else
            {
                cFormat = "#0." + cHelp.substring(0, iAnzahlDez);
            }
        }
        else
        {
            if (bTausender)
            {
                cFormat = "#,###,##0";
            }
            else
            {
                cFormat = "#0";
            }
        }

        DecimalFormatSymbols oSym = new DecimalFormatSymbols();
        oSym.setDecimalSeparator(DezimalSeparator);
        oSym.setGroupingSeparator(GruppenSeparator);


        df = new DecimalFormat(cFormat,oSym);

        cRueck = df.format(dHelp);

        return (cRueck);
    }


    
    
    /**
     * @param dZahl
     * @param iAnzahlDez
     * @param bTausender
     * @param DezimalSeparator
     * @param GruppenSeparator
     * @param bRound
     * @return
     */
    public static String formatDez(BigDecimal bdZahl, int iAnzahlDez, boolean bTausender, char DezimalSeparator, char GruppenSeparator, boolean bRound)
    {
    	
    	BigDecimal dHelp = bdZahl;
    	if (bRound)
    	{
    		dHelp =bdZahl.setScale(iAnzahlDez,BigDecimal.ROUND_HALF_UP);
    	}
    	
        String cFormat = "";
        String cHelp = "00000000000000000000000000000000000000";
        String cRueck = "" + dHelp;
        DecimalFormat df;

        if (iAnzahlDez > 0)
        {
            if (bTausender)
            {
                cFormat = "#,###,##0." + cHelp.substring(0, iAnzahlDez);
            }
            else
            {
                cFormat = "#0." + cHelp.substring(0, iAnzahlDez);
            }
        }
        else
        {
            if (bTausender)
            {
                cFormat = "#,###,##0";
            }
            else
            {
                cFormat = "#0";
            }
        }

        DecimalFormatSymbols oSym = new DecimalFormatSymbols();
        oSym.setDecimalSeparator(DezimalSeparator);
        oSym.setGroupingSeparator(GruppenSeparator);


        df = new DecimalFormat(cFormat,oSym);

        cRueck = df.format(dHelp);

        return (cRueck);
    }



    /**
     * 
     * @param bdZahl
     * @param iAnzahlDez
     * @return
     */
    public static String formatDezWithRound(BigDecimal bdZahl, int iAnzahlDez)
    {
    	
    	BigDecimal dHelp = bdZahl;
   		dHelp =bdZahl.setScale(iAnzahlDez,BigDecimal.ROUND_HALF_UP);
    	
        String cFormat = "";
        String cHelp = "00000000000000000000000000000000000000";
        String cRueck = "" + dHelp;
        DecimalFormat df;

        if (iAnzahlDez > 0)
        {
            cFormat = "#,###,##0." + cHelp.substring(0, iAnzahlDez);
        }
        else
        {
            cFormat = "#,###,##0";
        }

        DecimalFormatSymbols oSym = new DecimalFormatSymbols();
        oSym.setDecimalSeparator(',');
        oSym.setGroupingSeparator('.');

        df = new DecimalFormat(cFormat,oSym);

        cRueck = df.format(dHelp);

        return (cRueck);
    }

    

    
    /**
     * @param dZahl
     * @param iAnzahlDez
     * @param bTausender
     * @param DezimalSeparator
     * @param GruppenSeparator
     * @param bRound
     * @return
     */
    public static String formatDez(BigDecimal bdZahl, int iAnzahlDez, boolean bTausender, Character DezimalSeparator, Character GruppenSeparator, boolean bRound)
    {
    	
    	BigDecimal dHelp = bdZahl;
    	if (bRound)
    	{
    		dHelp =bdZahl.setScale(iAnzahlDez,BigDecimal.ROUND_HALF_UP);
    	}
    	
        String cFormat = "";
        String cHelp = "00000000000000000000000000000000000000";
        String cRueck = "" + dHelp;
        DecimalFormat df;

        if (iAnzahlDez > 0)
        {
            if (bTausender)
            {
                cFormat = "#,###,##0." + cHelp.substring(0, iAnzahlDez);
            }
            else
            {
                cFormat = "#0." + cHelp.substring(0, iAnzahlDez);
            }
        }
        else
        {
            if (bTausender)
            {
                cFormat = "#,###,##0";
            }
            else
            {
                cFormat = "#0";
            }
        }

        DecimalFormatSymbols oSym = new DecimalFormatSymbols();
        if (DezimalSeparator!=null)
        {
        	oSym.setDecimalSeparator(DezimalSeparator.charValue());
        }
        if (GruppenSeparator != null)
        {
        	oSym.setGroupingSeparator(GruppenSeparator.charValue());
        }


        df = new DecimalFormat(cFormat,oSym);

        cRueck = df.format(dHelp);

        return (cRueck);
    }


    
    
    
    
    /**
     * @param dZahl
     * @param iAnzahlDez
     * @param bTausender
     * @param DezimalSeparator
     * @param GruppenSeparator
     * @param bRound
     * @return
     */
    public static String formatDez(BigDecimal bdZahl, int iAnzahlDez, boolean bRound)
    {
       return (MyNumberFormater.formatDez(bdZahl, iAnzahlDez,true,',','.', bRound));
    }


    
    
    
    
    /**
     * @param lZahl
     * @param bTausender
     * @param GruppenSeparator (in Deutschland der Punkt)
     * @return
     */
    public static String formatDez(long lZahl,boolean bTausender, char GruppenSeparator)
    {
        String cFormat = "";
        String cRueck = "" + lZahl;
        DecimalFormat df;

        if (bTausender)
        {
           cFormat = "#,###,##0";
        }
        else
        {
            cFormat = "#0";
        }

        DecimalFormatSymbols oSym = new DecimalFormatSymbols();
        oSym.setGroupingSeparator(GruppenSeparator);

        df = new DecimalFormat(cFormat,oSym);

        cRueck = df.format(lZahl);

        return (cRueck);
    }


    
    
    
    /**
     * @param cDoubleValueUnformated
     * @param iAnzahlDez
     * @param bTausender
     * @return
     * @throws myException
     */
    public static String formatDez(String cDoubleValueUnformated, int iAnzahlDez, boolean bTausender) throws myException
    {
    	
    	if (cDoubleValueUnformated==null)  				return null;
    	if (cDoubleValueUnformated.trim().equals(""))	return "";
     	
    	Double dValue = null;
    	try
    	{
    		dValue = new Double(cDoubleValueUnformated);
    	}
    	catch (Exception ex)
    	{
    		throw new myException("MyNumberFormater:formatDez:Error building Double-Object !!");
    	}

    	return MyNumberFormater.formatDez(dValue.doubleValue(),iAnzahlDez,bTausender);
    	
    }

    
    
    /**
     * @param dZahl
     * @param bTausender
     * @return
     */
    public static  String formatDez(long dZahl, boolean bTausender)
    {
        String cFormat = "";
        String cRueck = "" + dZahl;
        DecimalFormat df;

        if (bTausender)
        {
            cFormat = "#,###,##0";
        }
        else
        {
            cFormat = "#0";
        }

        DecimalFormatSymbols oSym = new DecimalFormatSymbols();
        oSym.setDecimalSeparator(',');
        oSym.setGroupingSeparator('.');

        
        df = new DecimalFormat(cFormat,oSym);

        cRueck = df.format(dZahl);

        
        return (cRueck);
    }

    
    
    public static  String formatDez(String cLongValueUnformated, boolean bTausender) throws myException
    {
       	if (cLongValueUnformated==null)  				return null;
    	if (cLongValueUnformated.trim().equals(""))		return "";
     	
    	Long lValue = null;
    	try
    	{
    		lValue = new Long(cLongValueUnformated);
    	}
    	catch (Exception ex)
    	{
    		throw new myException("MyNumberFormater:formatDez:Error building Long-Object !!");
    	}
   	
    	return MyNumberFormater.formatDez(lValue.longValue(),bTausender);
    
    }

    

}
