/*
 * Created on 25.02.2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package panter.gmbh.indep;

/**
 * @author martin transformiert strings in neue strings, die an jeder stelle, wo zeichencodes zwischen 33 und 127 vorkommen, einen unicode-platzhalter einfügt: u000a = 11 (000a sind hexcodes)
 */
public class UnicodeTransformer
{
	//~ Instance fields ====================================================================================================================================================================================================================================================================================

	String cQuelle = null;

	//~ Constructors =======================================================================================================================================================================================================================================================================================

	public UnicodeTransformer(String cquelle)
	{
		this.cQuelle = cquelle;
	}

	//~ Methods ============================================================================================================================================================================================================================================================================================

	/*
	 * eine einfache Mengenklammer wird immer in eine doppelte umgewandelt,
	 * d.h. wenn bei der rueckumwandlung dann eine einfache gefunden wird,
	 * muss es ein unicode-symbol sein z.B. {00133}
	 */
	public String get_Unicode()
	{
		StringBuffer cRueck = null;
		int			 iHelp = 0;

		if (this.cQuelle != null)
		{
			cRueck = new StringBuffer("");

			String cHelp = "";

			for (int i = 0; i < this.cQuelle.length(); i++)
			{
				if (this.cQuelle.substring(i, i + 1).equals("{"))
				{
					/*
					 * ein einfache {{ wird verdoppelt
					 */
					cRueck.append("{{");
				}
				else
				{
					iHelp = this.cQuelle.charAt(i);

					if (iHelp < 32 || iHelp > 122)
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
						cRueck.append(this.cQuelle.charAt(i));
					}
				}
			}
		}

		String ccRueck = null;

		if (cRueck != null)
		{
			ccRueck = cRueck.toString();
		}

		return ccRueck;
	}





	/*
	 *
	 * statische methode, die einen beliebigen string nach \\uxxxx durchsucht und,
	 * im falle, diese durch die passenden char-werte ersetzt
	 */
	public static String decodeAsciiToUnicode(String cQuelle)
	{
		if (cQuelle == null)
		{
			return null;
		}

		/*
		 * verlängern, damit substring auf unicodes funktioniert
		 */
		int			 iLen     = cQuelle.length();
		String		 ccQuelle = cQuelle + "       ";

		StringBuffer cbHelp = new StringBuffer("");

		String		 cHelp = "";

		boolean		 bIsUnicode = true;
		Integer		 iTest	    = null;

		for (int i = 0; i < iLen; i++)
		{
			/*
			 * zuerst verdoppelte klammern prüfen
			 */
			if (ccQuelle.substring(i, i + 2).equals("{{"))
			{
				cbHelp.append("{");
				i++; // zahler um eins erhöhen
			}
			else
			{
				if (ccQuelle.substring(i, i + 1).equals("{"))
				{
					/*
					 * checken, ob es ein unicode-symbol ist
					 */
					bIsUnicode = false;

					cHelp = ccQuelle.substring(i,i+7);
					
					if (cHelp.substring(0, 1).equals("{") && cHelp.substring(6, 7).equals("}") && Character.isDigit(cHelp.charAt(1)) && Character.isDigit(cHelp.charAt(2)) && Character.isDigit(cHelp.charAt(3)) && Character.isDigit(cHelp.charAt(4)) && Character.isDigit(cHelp.charAt(5)))
					{
						/*
						 * jetzt noch prüfen, ob die resultierende zahl <= 65535 ist
						 */
						try
						{
							iTest = new Integer(cHelp.substring(1, 6));

							if (iTest.intValue() >= 1 || iTest.intValue() <= 65535)
							{
								bIsUnicode = true;
							}
						}
						catch (Exception Ex) {}
					}

					if (bIsUnicode)
					{
						cbHelp.append((char) iTest.intValue());
						/*
						 * zaehler springt hinter das ende des unicode-zeichens
						 */
						i += 6;
					}
				}
				else
				{
					cbHelp.append(cQuelle.charAt(i));
				}
			}
		}

		return cbHelp.toString();
	}
}