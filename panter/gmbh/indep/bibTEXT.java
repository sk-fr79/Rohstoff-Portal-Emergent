package panter.gmbh.indep;

import java.util.StringTokenizer;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import panter.gmbh.indep.exceptions.myException;

public class bibTEXT {

	public static Vector<String> get_ZeilenAusTextblock(String cTextBlock) {
		Vector<String>  vRueck = new Vector<String>();

		if (S.isEmpty(cTextBlock)) {
			return vRueck;
		}
		// jetzt die zieladressen in einen adress-vector legen
		StringTokenizer textTrenner = new StringTokenizer(" \n\r" + (cTextBlock + " \n\r"), "\n\r");
		String		    cZeile = null;
		

		
		
		while (textTrenner.hasMoreElements())
		{
			cZeile = textTrenner.nextToken().trim();
			if (bibALL.isEmpty(cZeile)) {
				continue;
			}
				
			vRueck.add(cZeile);
		}
		
		return vRueck;
	}
	
	
	/**
	 * 
	 * @param cTextBlock
	 * @param emptyLinesAdded  wenn true, dann werden leere zeilen auch integriert
	 * @param trim   (wenn true, dann werden die zeilen getrimmt)
	 * @return
	 */
	public static Vector<String> get_ZeilenAusTextblock(String cTextBlock, boolean emptyLinesAdded, boolean trim) {
		Vector<String>  vRueck = new Vector<String>();

		if (S.isEmpty(cTextBlock)) {
			return vRueck;
		}
		// jetzt die zieladressen in einen adress-vector legen
		StringTokenizer textTrenner = new StringTokenizer(" \n\r" + (cTextBlock + " \n\r"), "\n\r");
		String		    cZeile = null;
		

		
		
		while (textTrenner.hasMoreElements())
		{
			cZeile = trim?textTrenner.nextToken().trim():textTrenner.nextToken();
//			DEBUG.System_println("<"+cZeile+">");
			if (bibALL.isEmpty(cZeile) && !emptyLinesAdded) {
				continue;
			}
				
			vRueck.add(cZeile);
		}
		
		return vRueck;
	}
	

	
	
	
	

	/**
	 * 
	 * @param cSource     zu untersuchender Text
	 * @param cChrListToKeep
	 * @param bCompareUppers
	 * @return
	 */
	public static String CleanStringKeepOnlyCharList(String cSource, String cChrListToKeep, boolean bCompareUppers)
	{
		String cRueck = cSource;
		
		if (cRueck != null)
		{
			String Compare = bCompareUppers?cChrListToKeep:cChrListToKeep.toUpperCase();
			
			StringBuffer oSammler = new StringBuffer();
			for (int i=0;i<cSource.length();i++)
			{
				String cTest = bCompareUppers?cSource.substring(i,i+1):cSource.substring(i,i+1).toUpperCase();
				
				if (Compare.indexOf(cTest)>=0)
					
					oSammler.append(cTest);
			}
			cRueck = oSammler.toString();
		}

		return cRueck;
	}

	
	/**
	 * Aufbau eines verbundenen String aus den strings eines vectors, anfang und
	 * ende enthalten keinen trenner, dazwischen ist der trenner
	 * @param v_to_concat
	 * @param c_separator
	 * @param c_val_when_empty 
	 * @return
	 */
	public static String concat(Vector<String> v_to_concat, String c_separator, String c_val_when_empty) throws myException	{
		String ret = 	c_val_when_empty;
		String sep = 		c_separator;
		
		if (S.isEmpty(sep)) {
			sep = " ";
		}
	
		if (v_to_concat != null) {
			ret = "";
			for (String c:v_to_concat) {
				ret = ret + sep + c;
			}
			if (ret.length()>0) {
				ret = ret.substring(sep.length());               // erster trenner weg
			} 
			if (S.isEmpty(ret)) {
				ret = c_val_when_empty;
			}
		} else {
			throw new myException("bibALL:Concatenate:emtpy null-vector forbidden !!");
		}
		
		return ret;
	}
	
	/**
	 * Aufbau eines verbundenen String aus den strings eines vectors, anfang und
	 * ende enthalten keinen trenner, dazwischen ist der trenner
	 * @param v_to_concat
	 * @param c_separator
	 * @return
	 */
	public static String concat(Vector<String> v_to_concat, String c_separator) throws myException	{
		return bibTEXT.concat(v_to_concat, c_separator,"");
	}
	
	/**
	 * Aufbau eines verbundenen String aus den strings eines vectors, anfang und
	 * ende enthalten keinen trenner, dazwischen ist der trenner
	 * @param v_to_concat
	 * @return
	 */
	public static String concat(Vector<String> v_to_concat) throws myException	{
		return bibTEXT.concat(v_to_concat, " ","");
	}
	


	/**
	 * baut einen string aus count wiederholungen des base-strings
	 * @author martin
	 * @date 01.07.2019
	 *
	 * @param base
	 * @param count
	 * @return
	 */
	public static String repeat(String base, int count) {
		String ret = "";
		for (int i=0;i<count;i++) {
			ret = ret+S.NN(base);
		}
		return ret;
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 16.12.2019
	 *
	 * @param e
	 * @return s string of stacktrace
	 */
	public static String getExceptionToString(Exception e) {
		String s="";
		try {
			if (e!=null) {
				for (StackTraceElement ste: e.getStackTrace()) {
					s=s+((S.NN(ste.getClassName(),"<class>")+" .. "+S.NN(ste.getMethodName(),"<method>")+" .. "+ste.getLineNumber())+"\n");
				}
			}
		} catch (Exception e1) {
			s=s+"Systemerror !!";
		}
		
		return s;
	}
	
	
}
