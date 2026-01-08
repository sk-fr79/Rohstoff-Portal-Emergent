package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.bibALL;

public class MyDBStatementFormer 
{
	
	/*
	 * methode uebernimmt ein SQL-statement und bringt es in eine normalform zur analyse
	 */
	public static String NormalizeStatement(String cStatement)
	{
		String cRueck = cStatement.trim();
		
		/*
		 * zuerst alle doppelten leerzeichen raus
		 */
		while (cRueck.indexOf("  ")>-1)
		{
			cRueck = bibALL.ReplaceTeilString(cRueck, "  ", " ");
		}
		
		/*
		 * dann werden alle = - Zeichen von fuehrenden und nachfolgenden leerzeichen befreit
		 */
		while (cRueck.indexOf(" = ")>-1)
		{
			cRueck = bibALL.ReplaceTeilString(cRueck, " = ", "=");
		}
		while (cRueck.indexOf(" =")>-1)
		{
			cRueck = bibALL.ReplaceTeilString(cRueck, " =", "=");
		}
		while (cRueck.indexOf("= ")>-1)
		{
			cRueck = bibALL.ReplaceTeilString(cRueck, "= ", "=");
		}
		
		/*
		 * dann werden kommas von leerzeichen befreit
		 */
		while (cRueck.indexOf(" , ")>-1)
		{
			cRueck = bibALL.ReplaceTeilString(cRueck, " , ", ",");
		}
		while (cRueck.indexOf(" ,")>-1)
		{
			cRueck = bibALL.ReplaceTeilString(cRueck, " ,", ",");
		}
		while (cRueck.indexOf(", ")>-1)
		{
			cRueck = bibALL.ReplaceTeilString(cRueck, ", ", ",");
		}
		
		
		return cRueck;
	}

	

}
