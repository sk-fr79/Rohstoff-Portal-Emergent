/*
 * Created on 25.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panterdt.common;

import panterdt.exceptions.pdException;

/** 
 * @author martin transformiert strings in neue strings, die an jeder stelle, wo zeichencodes zwischen 33 und 127 vorkommen, einen unicode-platzhalter einfügt: u000a = 11 (000a sind hexcodes)
 */
public class pdUnicodeTransformer
{
	//~ Instance fields ====================================================================================================================================================================================================================================================================================

	
	/*
	 * zeichen, die nicht kodiert werden, alle anderen werden kodiert in strings als nummer
	 */
	public static String cSimpleSign = " 1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ.:-";
	
	//~ Constructors =======================================================================================================================================================================================================================================================================================

	public pdUnicodeTransformer() 
	{
	}

	//~ Methods ============================================================================================================================================================================================================================================================================================

	/*
	 * es werden alle zeichen, die nicht in cSimpleSign enthalten sind, kodiert
	 * z.B. {00133}
	 */
	public static String get_UnicodeFromAscii(String cQuelle)  throws pdException 
	{
		StringBuffer cRueck = null;
		char		 iHelp = 0;

		if (cQuelle == null)
	        throw new pdException("UnicodeTransformer:Null-value not allowed !");
	
		
		cRueck = new StringBuffer("");

		String cHelp = "";

		for (int i = 0; i < cQuelle.length(); i++)
		{
		    iHelp = cQuelle.charAt(i);
			if (pdUnicodeTransformer.cSimpleSign.indexOf(iHelp)==-1)   // nicht gefunden: kodieren
			{
				cHelp = Integer.toString(iHelp);

				if (cHelp.length() < 5)
				{
					cHelp = new String("00000").substring(0, 5 - cHelp.length()) + cHelp;
				}

				cHelp = "{" + cHelp + "}";
				cRueck.append(cHelp);
			}
			else
			{
				cRueck.append(iHelp);
			}
		}
		return cRueck.toString();
	}





	/*
	 *
	 * statische methode, die einen beliebigen string nach \\uxxxx durchsucht und,
	 * im falle, diese durch die passenden char-werte ersetzt
	 */
	public static String get_AsciiFromUnicode(String cQuelle) throws pdException
	{
		/*
		 * verlängern, damit substring auf unicodes funktioniert
		 */
		int			 iLen     = cQuelle.length();
		String		 ccQuelle = cQuelle + "       ";

		StringBuffer cbHelp = new StringBuffer("");

		String		 cHelp = "";

		Integer		 iTest	    = null;

		
		for (int i = 0; i < iLen; i++)
		{

		    if (ccQuelle.substring(i, i + 1).equals("{"))
			{

		        cHelp = ccQuelle.substring(i,i+7);

				try
				{
					iTest = new Integer(cHelp.substring(1, 6));   // muss integer sein
				}
				catch (Exception Ex) 
				{
				    throw new pdException("UnicodeTransformer:decodeAsciiToUnicode:Not allowed signs in string !!!");
				}

				if (cHelp.startsWith("{") && cHelp.endsWith("}"))   // muss mit {} eingeklammert sein
				{
					if (iTest.intValue() <= 1 || iTest.intValue() >= 65535)
					{
					    throw new pdException("UnicodeTransformer:decodeAsciiToUnicode: unicode number too big!!!");
					}
				}
				else
				{
				    throw new pdException("UnicodeTransformer:decodeAsciiToUnicode:Not } as end of unicode-block!!!");
				}

				cbHelp.append((char) iTest.intValue());
				/*
				 * zaehler springt hinter das ende des unicode-zeichens
				 */
				i += 6;
				
			}
			else
			{
				cbHelp.append(cQuelle.charAt(i));
			}
		}

		return cbHelp.toString();
	}
}