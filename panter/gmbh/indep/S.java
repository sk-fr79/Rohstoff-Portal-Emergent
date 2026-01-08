package panter.gmbh.indep;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Vector;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_STD;
import panter.gmbh.Echo2.MAIL_AND_REPORT.MAILING.MailBlock_Vector;
import panter.gmbh.Echo2.__BASIC_MODULS.MASSENMAILER.MASM_ModuleContainer;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.VEK;
import panter.gmbh.indep.myVectors.vectorForSegmentation;

/*
 * statische String-methoden 
 */
/**
 * @author martin
 *
 */
/**
 * @author martin
 *
 */
public class S {
	
	public static final String ziffern = 							"0123456789";
	public static final String grossbuchstaben = 					"ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final HashMap<String,String> umlauteMapping = 	new HashMap<String,String>() {{	put("ä","ae");
																									put("Ä","AE");
																									put("ö","oe");
																									put("Ö","OE");
																									put("ü","ue");
																									put("Ü","UE");
																									put("ß","ss");
																									}};
	
	
	
	public static String NN(String cTestWert)
	{
		if (cTestWert == null)
		{
			return "";
		}
		else
		{
			return cTestWert;
		}
	}
	
	/**
	 * 
	 * @param cTestWert   pruefwert
	 * @param cWhenEmpty  rueckgabe falls null oder leer
	 * @return
	 */
	public static String NN(String cTestWert, String cWhenEmpty)
	{
		if (S.isEmpty(cTestWert))
		{
			return cWhenEmpty;
		}
		else
		{
			return cTestWert;
		}
	}

	
	/**
	 * 
	 * @param cTestWert   pruefwert
	 * @param cWhenEmpty  rueckgabe falls null oder leer
	 * @return
	 */
	public static MyString NN(MyString cTestWert, MyString cWhenEmpty)
	{
		if (cTestWert==null || cTestWert.CTrans().trim().equals(""))
		{
			return cWhenEmpty;
		}
		else
		{
			return cTestWert;
		}
	}

	/**
	 * 
	 * @param cTestWert   pruefwert
	 * @param cWhenEmpty  rueckgabe falls null oder leer
	 * @return
	 */
	public static MyE2_String NN(MyE2_String cTestWert, MyE2_String cWhenEmpty)
	{
		if (cTestWert==null || cTestWert.CTrans().trim().equals(""))
		{
			return cWhenEmpty;
		}
		else
		{
			return cTestWert;
		}
	}
	
	
	
	
	/**
	 * @param cTestWert
	 * @param iMaxLen
	 * @return
	 */
	public static String MAX(String cTestWert,int iMaxLen)
	{
		if (cTestWert==null)
			return cTestWert;
		
		String cRueck = new String(cTestWert);
		
		if (cRueck.length()>iMaxLen)
		{
			cRueck = cRueck.substring(0,iMaxLen);
		}
		return cRueck;
	}
	
	
	
	public static boolean isEmpty(String cTestWert)
	{
		return (cTestWert == null || cTestWert.trim().equals(""));
	}
	
	public static boolean isFull(String cTestWert)
	{
		return (!S.isEmpty(cTestWert));
	}


	public static boolean isAllFull(String... cTestWerte) 	{
		boolean b_all_full = true;
		
		for (String s: cTestWerte) {
			if (S.isEmpty(s)) {
				b_all_full = false;
			}
		}
		
		return b_all_full;
	}

	
	public static boolean isAllEmpty(String... cTestWerte) 	{
		boolean b_all_empty = true;
		
		for (String s: cTestWerte) {
			if (S.isFull(s)) {
				b_all_empty = false;
			}
		}
		
		return b_all_empty;
	}


	
	
	
	public static boolean isEmpty(MyString cTestWert)
	{
		return (cTestWert == null || S.isEmpty(cTestWert.COrig()));
	}
	
	public static boolean isFull(MyString cTestWert)
	{
		return (!S.isEmpty(cTestWert));
	}


	
	public static String[] SeparateLinesFromString(String cAusgangsstring)
	{
		return cAusgangsstring.split("\r\n|\r|\n");
	}
	

	
	
	public static Vector<String> V(String cElement1)
	{
		Vector<String> vRueck = new Vector<String>();
		vRueck.add(cElement1);
		return vRueck;
	}
	
	public static Vector<String> V(String cElement1,String cElement2)
	{
		Vector<String> vRueck = new Vector<String>();
		vRueck.add(cElement1);
		if (cElement2 != null) vRueck.add(cElement2);
		return vRueck;
	}

	public static Vector<String> V(String cElement1,String cElement2,String cElement3)
	{
		Vector<String> vRueck = new Vector<String>();
		vRueck.add(cElement1);
		if (cElement2 != null) vRueck.add(cElement2);
		if (cElement3 != null) vRueck.add(cElement3);
		return vRueck;
	}
	

	
	public static Vector<String> V(String cElement1,String cElement2,String cElement3,String cElement4)
	{
		Vector<String> vRueck = new Vector<String>();
		vRueck.add(cElement1);
		if (cElement2 != null) vRueck.add(cElement2);
		if (cElement3 != null) vRueck.add(cElement3);
		if (cElement4 != null) vRueck.add(cElement4);
		return vRueck;
	}
	public static Vector<String> V(String cElement1,String cElement2,String cElement3,String cElement4,String cElement5)
	{
		Vector<String> vRueck = new Vector<String>();
		vRueck.add(cElement1);
		if (cElement2 != null) vRueck.add(cElement2);
		if (cElement3 != null) vRueck.add(cElement3);
		if (cElement4 != null) vRueck.add(cElement4);
		if (cElement5 != null) vRueck.add(cElement5);
		return vRueck;
	}

	

	
	public static Vector<MyString> VT(String cElement1)
	{
		Vector<MyString> vRueck = new Vector<MyString>();
		vRueck.add(new MyE2_String(cElement1));
		return vRueck;
	}
	
	public static Vector<MyString> VT(String cElement1,String cElement2)
	{
		Vector<MyString> vRueck = new Vector<MyString>();
		vRueck.add(new MyE2_String(cElement1));
		if (cElement2 != null) vRueck.add(new MyE2_String(cElement2));
		return vRueck;
	}

	public static Vector<MyString> VT(String cElement1,String cElement2,String cElement3)
	{
		Vector<MyString> vRueck = new Vector<MyString>();
		vRueck.add(new MyE2_String(cElement1));
		if (cElement2 != null) vRueck.add(new MyE2_String(cElement2));
		if (cElement3 != null) vRueck.add(new MyE2_String(cElement3));
		return vRueck;
	}
	

	
	public static Vector<MyString> VT(String cElement1,String cElement2,String cElement3,String cElement4)
	{
		Vector<MyString> vRueck = new Vector<MyString>();
		vRueck.add(new MyE2_String(cElement1));
		if (cElement2 != null) vRueck.add(new MyE2_String(cElement2));
		if (cElement3 != null) vRueck.add(new MyE2_String(cElement3));
		if (cElement4 != null) vRueck.add(new MyE2_String(cElement4));
		return vRueck;
	}
	public static Vector<MyString> VT(String cElement1,String cElement2,String cElement3,String cElement4,String cElement5)
	{
		Vector<MyString> vRueck = new Vector<MyString>();
		vRueck.add(new MyE2_String(cElement1));
		if (cElement2 != null) vRueck.add(new MyE2_String(cElement2));
		if (cElement3 != null) vRueck.add(new MyE2_String(cElement3));
		if (cElement4 != null) vRueck.add(new MyE2_String(cElement4));
		if (cElement5 != null) vRueck.add(new MyE2_String(cElement5));
		return vRueck;
	}


	
	
	public static String get_StringWithLen(char Zeichen, int iLen)
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i=0;i<iLen;i++)
		{
			sb.append(Zeichen);
		}
		
		return sb.toString();
	}
	

	public static String extend(String cOriginal, int iLen) throws myException {
		return S.makeStringLonger(cOriginal, " ", iLen);
	}
	
	
	public static String makeStringLonger(String cOriginal, String FillChar, int iLen) throws myException
	{
		return makeStringLonger(cOriginal, FillChar, iLen, true);
	}
	
	
	public static String makeStringLonger(String cOriginal, String FillChar, int iLen, boolean bLeft) throws myException
	{
		String cStart = S.NN(cOriginal);
		
		if (S.NN(FillChar).length()!=1)
		{
			throw new myException("S.makeStringLonger(): fillChar MUST have length of 1 !!!");
		}
		
		String 	sNeu = 		cStart;
		int 	iStartLen = cStart.length();
		
		for (int i=0;i<iLen-iStartLen;i++)
		{
			if (bLeft)
			{
				sNeu = FillChar+sNeu;
			}
			else
			{
				sNeu = sNeu+FillChar;
			}
		}
		return sNeu;
	}
	
	
	
	/**
	 * 
	 * @param sb         zu analysierender String
	 * @param cCodeSign  Codeblock (ohne < und >)
	 * @return s nummerischen wert innerhalb der Zeichen (z.b. <L100L>  liefert 100,
	 *           im uebergebenen SB ist der gesaeuberte String
	 * @throws myException
	 */
	public static Integer get_InWertInStringCode(StringBuffer  sb, String cCodeSign) throws myException
	{
		String 	 cLabel = 			sb.toString();
		Integer  intValueRueck = 	null;
		
		int iLenCodeSignBlock = cCodeSign.length()+1;   //zb. "<DRUCKER" ergibt laenge 7+1=8
		
		String cStart = "<"+cCodeSign;
		String cEnde =  cCodeSign+">";
		
		if (cLabel.indexOf(cStart)>=0)
		{
			int iStartL = 	cLabel.indexOf(cStart);
			int iEndL = 	cLabel.indexOf(cEnde);
			
			if (iEndL<iStartL)
			{
				throw new myException("MaskComponentsFAB:addStandardComponentsToMAP:get_InWertInStringCode:Error in FieldLengthDef -> Must be like: <L100L>");
			}
			else
			{
				String cLenInfo = cLabel.substring(iStartL+iLenCodeSignBlock,iEndL);
				if (!bibALL.isInteger(cLenInfo))
				{
					throw new myException("MaskComponentsFAB:addStandardComponentsToMAP:get_InWertInStringCode:No Number in FieldLengthDef -> Must be like: <L100L>");
				}

				intValueRueck = new MyInteger(cLenInfo).get_oInteger();
				
				cLabel = cLabel.substring(0,iStartL)+cLabel.substring(iEndL+iLenCodeSignBlock);
				cLabel = cLabel.trim();
				
			}
		}
		//den stringbuffer mit dem neuen string fuellen
		sb.delete(0, sb.length());
		sb.append(cLabel);
		
		return intValueRueck;
	}

	
	
	/**
	 * replaces all singualar "'" in "''" and adds left and right ' 
	 * @param s
	 * @return
	 */
	public static String hk(String s) {
		if (s==null) {
			return null;
		}
		
		String s_r = s.replaceAll("'", "''");
		
		s_r = "'"+s_r+"'";
		
		return s_r;
	}
	
	
	
	
	
	
	/**
	 * 
	 * @param sb         zu analysierender String
	 * @param cCodeSign  Codeblock (ohne < und >)
	 * @return s nummerischen wert innerhalb der Zeichen (z.b. <L100L>  liefert 100,
	 *           im uebergebenen SB ist der gesaeuberte String
	 * @throws myException
	 */
	public static String get_cWertInStringCode(StringBuffer  sb, String cCodeSign) throws myException
	{
		String 	cLabel = sb.toString();
		String 	cInBlock=null;
		
		int iLenCodeSignBlock = cCodeSign.length()+1;   //zb. "<DRUCKER" ergibt laenge 7+1=8
		
		String cStart = "<"+cCodeSign;
		String cEnde =  cCodeSign+">";
		
		
		
		if (cLabel.indexOf(cStart)>=0 && cLabel.indexOf(cEnde)>=0)
		{
			int iStartL = 	cLabel.indexOf(cStart);
			int iEndL = 	cLabel.indexOf(cEnde);
			
			if (iEndL<iStartL)
			{
				
				throw new myException("MaskComponentsFAB:addStandardComponentsToMAP:get_InWertInStringCode:Error in FieldLengthDef -> Must be like: <L100L> (here: <"+cCodeSign+"value"+cCodeSign+">");
			}
			else
			{
				cInBlock = cLabel.substring(iStartL+iLenCodeSignBlock,iEndL).trim();

				//den gesaueberten string festlegen
				cLabel = cLabel.substring(0,iStartL)+cLabel.substring(iEndL+iLenCodeSignBlock);
				cLabel = cLabel.trim();
				
			}
		}
		//den stringbuffer mit dem neuen string fuellen
		sb.delete(0, sb.length());
		sb.append(cLabel);
		
		return cInBlock;
	}

	
	
	/**
	 * 
	 * @param TestedString
	 * @param ContaineStrings
	 * @return s true wenn nur buchstaben aus ContaineStrings in TestedString vorhanden sind, sonst false
	 */
	public static boolean ContainsOnly(String TestedString, String ContaineStrings)
	{
		boolean bRueck = true;
		
		for (int i=0;i<TestedString.length();i++)
		{
			CharSequence cBuchstaben = TestedString.subSequence(i,i+1);
			if (!(ContaineStrings.contains(cBuchstaben)))
			{
				bRueck=false;
			}
			
		}
		
		return bRueck;
		
	}
	
	
	
	
	public static class Tags
	{


		private String 	cCleanFeldName = null;
		private  HashMap<String,Integer>  hmINT = new HashMap<String, Integer>();
		
		/**
		 * 
		 * @param cOriginalFeldName ist der Name zusammen mit den <X10X> - Tags
		 * @param vTags   ist ein Vector mit Tags (WHLB)
		 * @throws myException
		 */
		public Tags(String cOriginalFeldName, Vector<String> vTags) throws myException 
		{
			super();
			
			StringBuffer strHelp = new StringBuffer(cOriginalFeldName);
			
			//jetzt die tags durchgehen
			for (int i=0;i<vTags.size();i++)
			{
				strHelp = this.readValue(strHelp, vTags.get(i));
			}
			this.cCleanFeldName = strHelp.toString();
		}
		
		
		private StringBuffer readValue(StringBuffer cTempString, String cHash) throws myException
		{
			Integer iLeft = S.get_InWertInStringCode(cTempString, cHash);
			if (iLeft!=null)
			{
				this.hmINT.put(cHash, iLeft);
			}
			else
			{
				this.hmINT.put(cHash, null);
			}

			return cTempString;
			
		}
		
		/**
		 * 
		 * @returns cleaned String
		 */
		public String get_cCleanFeldName()
		{
			return cCleanFeldName;
		}
		
		/**
		 * 
		 * @return s Hashmap with Taggs as key and Integers as Value
		 */
		public HashMap<String, Integer> getHmINT()
		{
			return hmINT;
		}
		
	}
	
	public static String padRight(String s, int n) {
	     return String.format("%1$-" + n + "s", s);  
	}

	public static String padLeft(String s, int n) {
	    return String.format("%1$" + n + "s", s);  
	}
	
	public static String toCamelCase(String s) {
	    StringBuffer sb = new StringBuffer();
	    String[] x = s.replaceAll("[^A-Za-z0-9]", " ").replaceAll("\\s+", " ")
	            .trim().split(" ");

	    for (int i = 0; i < x.length; i++) {
	        if (i == 0) {
	            x[i] = x[i].toLowerCase();
	        } else {
	            String r = x[i].substring(1);
	            x[i] = String.valueOf(x[i].charAt(0)).toUpperCase() + r;

	        }
	        sb.append(x[i]);
	    }
	    return sb.toString();
	}
	
	
	
	/*
	 * hilfsklassen zum einfacheren zusammenstellen von MyE2_String()
	 */
	public abstract static class TextAndTranslationInfo {
		public abstract boolean translate();
		public abstract String  text(); 
	};
	
	public static class T extends TextAndTranslationInfo {
		private String cText = null;
		private T(String text){
			this.cText=text;
		}
		
		@Override
		public boolean translate() {
			return true;
		}

		@Override
		public String text() {
			return this.cText;
		}
	}
	
	public static class UT extends TextAndTranslationInfo {
		private String cText = null;
		private UT(String text){
			this.cText=text;
		}
		
		@Override
		public boolean translate() {
			return false;
		}

		@Override
		public String text() {
			return this.cText;
		}
	}

	/**
	 * 
	 * @param text
	 * @return TextAndTranslationInfo which is translated
	 */
	public static TextAndTranslationInfo t(String text) {
		return new T(text);
	}
	
	/**
	 * 
	 * @param text
	 * @return TextAndTranslationInfo which is Untranslated
	 */
	public static TextAndTranslationInfo ut(String text) {
		return new UT(text);
	}
	
	
	public static ArrayList<String> Separat(String source, Character sep) throws myException{
		ArrayList<String>  alRueck = new ArrayList<String>();
		
		String ausschluss = "\\"+"\n"+"\t"+"*{}[]()?";  
		
		if (ausschluss.indexOf(sep)>=0) {
			throw new myException("S.java: Separat(): character not allowed as separator !");
		}
		
		StringBuffer help = new StringBuffer();
		help.append(sep);
		String[] split = source.split(help.toString());
		
		for (String s: split) {
			alRueck.add(s);
		}
		return alRueck;
	}
	
	
	
	
	/**
	 * 
	 * @param sb         zu analysierender String
	 * @param cCodeSign  is im unteren beispiel das L
	 * @return s nummerischen wert innerhalb der Zeichen (z.b. <L>100</L>  liefert 100,
	 *           im uebergebenen SB ist der gesaeuberte String
	 * @throws myException
	 */
	public static String get_cWertInStringCode_LIKE_XML(StringBuffer sb, String cCodeSign) throws myException
	{
		String 	cLabel = sb.toString();
		String 	cInBlock=null;
		
		
		String cStart = "<"+cCodeSign+">";
		String cEnde =  "</"+cCodeSign+">";
		
		int i_len_startblock = cStart.length();
		int i_len_endblock = cEnde.length();
		
		if (cLabel.indexOf(cStart)>=0 && cLabel.indexOf(cEnde)>=0)	{
			int iStartL = 	cLabel.indexOf(cStart);
			int iEndL = 	cLabel.indexOf(cEnde);
			
			if (iEndL<iStartL) {				
				throw new myException("MaskComponentsFAB:addStandardComponentsToMAP:get_InWertInStringCode:Error in FieldLengthDef -> Must be like: <L>100</L> (here: <"+cCodeSign+">value</"+cCodeSign+">");
			} else	{
				cInBlock = cLabel.substring(iStartL+i_len_startblock,iEndL).trim();

				//den gesaueberten string festlegen
				cLabel = cLabel.substring(0,iStartL)+cLabel.substring(iEndL+i_len_endblock);
				cLabel = cLabel.trim();
			}
		}
		//den stringbuffer mit dem neuen string fuellen
		sb.delete(0, sb.length());
		sb.append(cLabel);
		
		return cInBlock;
	}


	/**
	 * Factory-Methode um MyE2_String - klassen zu bauen
	 * @param s
	 * @return MyE2_String 
	 */
	public static MyE2_String mt(String s) {
		return new MyE2_String(s);
	}
	
	
	
	/**
	 * erzeugt einen String aus n mal c
	 * @param c
	 * @param muliplier
	 */
	public static String n_times(String c, int muliplier) {
		StringBuffer buff =  new StringBuffer();
		for (int i=0;i<muliplier;i++) {
			buff.append(c);
		}
		
		return c.toString();
			
		
		
	}
	
	
	/**
	 * Aufbau eines verbundenen String aus den strings eines vectors, anfang und
	 * ende enthalten keinen trenner, dazwischen ist der trenner
	 * @param v_to_concatenate
	 * @param separator
	 * @param val_when_empty 
	 * @return
	 */
	public static String Concatenate(Vector<String> v_to_concatenate, String separator, String val_when_empty) throws myException
	{
		String cRueck = null;
		if (separator==null) {
			separator=" ";
		}
		
		if (v_to_concatenate != null) {
			cRueck = "";
			for (String cString:v_to_concatenate) {
				cRueck = cRueck + separator+cString;
			}
		}
		else
			throw new myException("S:Concatenate:NULL-vector not allowed !!");

		if (cRueck.length()>0) {
			cRueck = cRueck.substring(separator.length());               // erster trenner weg
		}
		
		if (cRueck != null) {
			if (cRueck.trim().equals("")) {
				if (!bibALL.isEmpty(val_when_empty)) {
					cRueck = val_when_empty;
				}
			}
		}
		
		return cRueck;
	}
	
	

	/**
	 * Einfacher Concatenator
	 * @author manfred
	 * @date 22.01.2019
	 *
	 * @param separator
	 * @param val_when_empty
	 * @param entries
	 * @return
	 */
	public static String Concatenate(String separator,String val_when_empty, String ... entries){
		String sRet = "";
		StringBuilder sb = new StringBuilder();
		val_when_empty = val_when_empty + "";
		separator = separator + "";
		
		for (String entry: entries ){
			sb.append(S.isEmpty(entry) ? val_when_empty  : entry);
			sb.append(separator);
		}
		
		if (sb.length()>0){
			sRet = sb.substring(0, sb.length() - separator.length());
		}
		
		return sRet;
	}
	
	
	
	/**
	 * 
	 * @param s (translated)
	 * @return
	 */
	public static MyE2_String ms(String s) {
		return new MyE2_String(s);
	}
	/**
	 * 
	 * @param s (untranslated)
	 * @return
	 */
	public static MyE2_String msUt(String s) {
		return new MyE2_String(s,false);
	}


	
	
	/**
	 * doppelte leerzeichen innerhalb string raus
	 * @param c
	 * @return cleaned String or ""
	 */
	public static String clean(String c) {
		String ret = S.NN(c).trim();
		if (S.isFull(ret)) {
			while (ret.contains("  ")) {
				ret = ret.replace("  ", " ");
			}
		}
		return ret;
	}


	/**
	 * 
	 * @param s
	 * @return true, when only 0123456789 in string s
	 */
	public static boolean onlyContainsDigits(String s) {
		String gegenTest = "0123456789";
		for (char c: s.toCharArray()) {
			if (!gegenTest.contains(""+c)) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * 
	 * @param stringToAnalyze
	 * @param allowedSigns (range of allowed signs)
	 * @param replaceOthersWith (all not in allowedSigns are replaced with replaceOthersWith, when null removes this signs)
	 * @return
	 */
	public static String replaceSonderZeichen(String stringToAnalyze, String allowedSigns, Character replaceOthersWith) {
		StringBuffer ret = new StringBuffer(); 
		
		for (char c: stringToAnalyze.toCharArray()) {
			if (allowedSigns.contains(""+c)) {
				ret.append(c);
			} else {
				if (replaceOthersWith!=null) {
					ret.append(replaceOthersWith);
				}
			}
		}
		String cRet = S.NN(ret.toString());
		return cRet;
	}
	
	
	/**
	 * ersetzt umlaute im string durch die passenden kombinationen (z.B.ä -> ae oder Ö OE, ß allways is small: ss)
	 * @param stringToAnalyze
	 * @return
	 */
	public static String replaceUmlaute(String stringToAnalyze)  {
		String cRet = stringToAnalyze;
		for (String c: S.umlauteMapping.keySet()) {
			cRet = cRet.replace(c, S.umlauteMapping.get(c));
		}
		return cRet;
	}
	
	
	
	/**
	 * counts number of occurences of searchedString in containerString
	 * @param containerString
	 * @param searchedString
	 * @return
	 */
	public static int countNumberOfOccurences(String containerString, String searchedString) {
		int occurences = 0;
		if (S.isFull(containerString) && S.isFull(searchedString)) {
			if (0 != containerString.length()) {
				for (int index = containerString.indexOf(searchedString, 0); index != -1; index = containerString.indexOf(searchedString, index + 1)) {
					occurences++;
				}
			}
		}
		return occurences;
	}
	
	/**
	 * reads positions of occurences of searchedString in containerString
	 * @param containerString
	 * @param searchedString
	 * @return
	 */
	public static VEK<Long> getPositionsOfOccurences(String containerString, String searchedString) {
		VEK<Long>  v = new VEK<>();

		int oldIndex = 0; 
		if (S.isFull(containerString) && S.isFull(searchedString)) {
			if (0 != containerString.length()) {
				for (int index = containerString.indexOf(searchedString, 0); index != -1; index = containerString.indexOf(searchedString, index + 1)) {
					v._a(new Long(containerString.indexOf(searchedString, oldIndex)));
					oldIndex = index+1;
				}
			}
		}
		return v;
	}
	
	
	
	/**
	 * 
	 * @author martin
	 * @date 02.01.2019
	 *
	 * @param numberString
	 * @return s for example numberString=123,45 -> 000000000000000000000000000000000000000000123,45
	 */
	public static String getNumberSortable(String numberString) {
		String help = "000000000000000000000000000000000000000000000000";
		String ret = help;
		
		if (S.isFull(numberString)) {
			if (help.length()>=ret.length()) {
				ret = help.substring(0, help.length()-ret.length())+ret;
			}
		}
		return ret;
	}
	
	
	
	/**
	 * Trennt den String nach CR/LF und "," und ";" und " "
	 * @author manfred
	 * @date 03.08.2021
	 *
	 * @param stringToSplit
	 * @return
	 */
	public static Vector<String> tokenize(String stringToSplit){
		Vector<String> v = new Vector<String>();

		StringTokenizer textTrenner = new StringTokenizer(" \n\r" + (stringToSplit + " \n\r"), "\n\r");
		String		    cZeile = null;
		

		while (textTrenner.hasMoreElements())
		{
			cZeile = textTrenner.nextToken().trim();
			if (bibALL.isEmpty(cZeile))
				continue;
			
			/*
			 * jetzt die zeile noch trennen nach leerzeichen/kommas/semikolons
			 */
			cZeile = bibALL.ReplaceTeilString(cZeile," ",",");
			cZeile = bibALL.ReplaceTeilString(cZeile,";",",");
			cZeile = bibALL.ReplaceTeilString(cZeile,",,",",");
			cZeile = bibALL.ReplaceTeilString(cZeile,",,",",");
			cZeile = bibALL.ReplaceTeilString(cZeile,",,",",");
			
			Vector<String> vSeps = bibALL.TrenneZeile(cZeile,",");
			
			for (int i=0;i<vSeps.size();i++)
			{
				String sToken = ((String)vSeps.get(i)).trim();
				
				if (!bibALL.isEmpty(sToken))
				{
					v.add(sToken);
				}
			}
		}
		
		return v;
	}

}
