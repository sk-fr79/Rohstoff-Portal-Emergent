/*
 * bib.java
 * Allgemeine bibliothek mit methoden, die ohne klassengenerierung
 * aufrufbar sein sollen
 *
 * Created on 27. Juni 2002, 18:14
 */
package panter.gmbh.indep;

import java.io.File;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import nextapp.echo2.app.Alignment;
import nextapp.echo2.app.ApplicationInstance;
import nextapp.echo2.app.Color;
import nextapp.echo2.app.Component;
import nextapp.echo2.app.Insets;
import nextapp.echo2.app.layout.CellLayoutData;
import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.ActionEventTools.XX_SQL_STACK_DAEMON;
import panter.gmbh.Echo2.FontsAndColors.E2_ColorBase;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.myTranslator;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_MANDANT;
import panter.gmbh.basics4project.BasicRecords.BASIC_RECORD_USER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_TRIGGER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_MANDANT_ZUSATZ;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_TRIGGER_DEF;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_WAEHRUNG;
import panter.gmbh.indep.dataTools.DB_STATICS;
import panter.gmbh.indep.dataTools.MyConnection;
import panter.gmbh.indep.dataTools.MyDBToolBox;
import panter.gmbh.indep.dataTools.MyDBToolBox_FAB;
import panter.gmbh.indep.dataTools.XX_TransActionLock;
import panter.gmbh.indep.dataTools.bibDB;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.maggie.DotFormatter;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_MANDANT_ext;
import rohstoff.Echo2BusinessLogic.__SPECIALRECORDS.RECORD_USER_EXT;


 
/**
 * @author martin
 *
 */
public class bibALL
{
    /** Creates a new instance of bib */
    public bibALL()
    {
    }

    // übernimmt einen string und wandelt null in "" um
    public static String null2leer(String cWert)
    {
        String cRueck = "";

        if (cWert != null)
        {
            cRueck = cWert;
        }

        return cRueck;
    }

    
    

    // übernimmt einen string und wandelt null in "" um
    public static Vector<String> null2leerString(Vector<String> vWert)
    {
    	Vector<String> vRueck = new Vector<String>();

        if (vWert != null)
        {
            vRueck = vWert;
        }

        return vRueck;
    }

    
    
    
    public static String rtrim(String cInput)
    {
        int i;
        int iPos = cInput.length();

        for (i = cInput.length() - 1; i >= 0; i--)
        {
            if (cInput.substring(i, i + 1).equals(" "))
            {
                iPos = i;
            }
            else
            {
                break;
            }
        }

        return (cInput.substring(0, iPos));
    }

    // methode baut aus einem übergebenen nx2 - array einen url-string
    // für parameterlisten
    public static String BaueFieldURL(String[][] cWerte, boolean bUndAmAnfang)
    {
        int i = 0;
        String cRueck = "";

        if (!(cWerte == null))
        {
            for (i = 0; i < cWerte.length; i++)
            {
                if (!cWerte[i][1].equals(""))
                { // leere dürfen nicht angehängt werden
                    cRueck += ("&" + cWerte[i][0] + "=" + cWerte[i][1]);
                }
            }

            // erstes & weg
            if (!bUndAmAnfang)
            {
                cRueck = cRueck.substring(1);
            }
        }

        return (cRueck);
    }

    // methode such aus einem String[n][2] mit der Struktur
    // [n][0] = name eines wertes
    // [n][1] = dessen wert
    // den wert eines übergebenen namens heraus
    public static String getWert(String[][] WerteFeld, String cName)
    {
        String cWert = "";

        for (int i = 0; i < WerteFeld.length; i++)
        {
            if (WerteFeld[i][0].trim().toUpperCase().equals(cName.trim().toUpperCase()))
            {
                cWert = WerteFeld[i][1];
            }
        }

        return (cWert);
    }

    // bekommt ein array String[n][2], wo in [n][1] die namen der 
    // request-parameter stehen
    // liest in die 2. stellen dann die werte ein
    public static void leseRequestParameter(HttpServletRequest request, String[][] cNamenWerte)
    {
        for (int i = 0; i < cNamenWerte.length; i++)
        {
            cNamenWerte[i][1] = LeseValue(request, cNamenWerte[i][0]);
        }
    }

    // methode liest alles parameter durch, sucht sich den
    // und namen raus und liest dann den wert,
    // übergibt, falls leer oder parameter nicht vorhanden, einen Leerstring
    public static String LeseValue(HttpServletRequest oRequest, String pParameter)
    {
        Enumeration paramNames = oRequest.getParameterNames();

        String cRueck = "";
        String[] ParamValues;

        while (paramNames.hasMoreElements())
        {
            if (pParameter.equals(paramNames.nextElement()))
            {
                ParamValues = oRequest.getParameterValues(pParameter);

                if (ParamValues.length == 1)
                {
                    cRueck = ParamValues[0];
                }

                break;
            }
        }

        return (cRueck);
    }


    
    
    public static String ReplaceTeilString(String cQuelle, String cSuchen, String cErsetzen)
    {
        return bibALL.ReplaceTeilString(cQuelle,cSuchen,cErsetzen,false);
    }    
    
    
    
    public static String ReplaceTeilString(String cQuelle, Vector<String> vSuchen, Vector<String> vErsetzen)
    {
    	String cRueck = cQuelle;
    	
    	for (int i=0;i<vSuchen.size();i++)
    	{
    		cRueck = bibALL.ReplaceTeilString(cRueck, vSuchen.get(i), vErsetzen.get(i));
    	}
    	
        return cRueck;
    }    
    

    
    
    /**
     *  methode macht ein replace auf einen string
     *  hier wird der suchvorgang immer auf toLowerCase() durchgeführt
     * @param cQuelle
     * @param cSuchen
     * @param cErsetzen
     * @param vglLOWER
     * @return
     */
    public static String ReplaceTeilString_alt(String cQuelle, String cSuchen, String cErsetzen, boolean vglLOWER)
    {
        String 			cRueck 		= cQuelle;
        String			ccQuelle 	= cQuelle;
        String			ccSuchen  	= cSuchen;
        
        if (cQuelle == null || cSuchen == null || cErsetzen == null || cQuelle.equals("") || cSuchen.equals(""))
            return cRueck;
        
        if (vglLOWER) 
        {
            ccQuelle = cQuelle.toLowerCase();
            ccSuchen = cSuchen.toLowerCase();
        }
 
        int iStart 	= 	0;
        int iPos	=	ccQuelle.indexOf(ccSuchen,iStart);
        
        if (iPos == -1)                 // not found
            return cRueck;

        StringBuffer 	cNeu 	= new StringBuffer(cQuelle);
        
        do
        {
             iPos = ccQuelle.indexOf(ccSuchen,iStart);

            if (iPos >= 0)
            {
                cNeu = cNeu.replace(iPos, iPos + cSuchen.length(), cErsetzen);

                if (vglLOWER) 
                	ccQuelle = cNeu.toString().toLowerCase();
                else
                    ccQuelle = cNeu.toString();
                
                iStart = iPos+cErsetzen.length();
            }
            
        }
        while (iPos >= 0);

        return (cNeu.toString());
    }

    
    /**
     * Ersetzt den Teilstring durch reguläre Ausdrücke.
     * Sollte schneller gehen als der alte Suchalgorithmus.
     * @param cQuelle
     * @param cSuchen
     * @param cErsetzen
     * @param vglLOWER
     * @return
     */
    public static String ReplaceTeilString(String cQuelle, String cSuchen, String cErsetzen, boolean vglLOWER)
    {
        String 			cRueck 		= cQuelle;
        
        // In case you would like to ignore case sensitivity you could use this
        // statement
        // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
        if (cQuelle != null && cSuchen != null && cErsetzen != null){
	        Pattern pattern = null;
	        cSuchen = Matcher.quoteReplacement(cSuchen);
	        
	        if (vglLOWER){
	        	pattern = Pattern.compile(cSuchen,Pattern.CASE_INSENSITIVE | Pattern.LITERAL );
	        } else {
	        	pattern = Pattern.compile(cSuchen,  Pattern.LITERAL );
	        }
	        
	        Matcher matcher = pattern.matcher(cQuelle);
	
			if (matcher.find()){
				cRueck = matcher.replaceAll(Matcher.quoteReplacement(cErsetzen));
			}
        }
        return cRueck;
        
    }

    
    
    public static String RemoveTeilString(String cQuelle, String... remove)   {
    	
    	String 	cRueck = cQuelle;

    	for (String cSuchen: remove) {
        
	        // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
	        if (cQuelle != null && cSuchen != null){
		        Pattern pattern = null;
		        cSuchen = Matcher.quoteReplacement(cSuchen);
	        	pattern = Pattern.compile(cSuchen,  Pattern.LITERAL );
		        Matcher matcher = pattern.matcher(cQuelle);
				if (matcher.find()){
					cRueck = matcher.replaceAll(Matcher.quoteReplacement(""));
				}
	        }
    	}
        return cRueck;
    }

    
    
    
//    /**
//     * @param cText
//     * @return s toUpper des strings, ohne z.b. bei ß die laenge zu aendern
//     */
//    public static String UCase(String cText)
//    {
//    	StringBuffer cRueck = new StringBuffer("");
//    	for (int i=0;i<cText.length();i++)
//    	{
//    		cRueck.append(Character.toUpperCase(cText.charAt(i)));
//    	}
//    	
//    	return cRueck.toString();
//    }

    
    
    
    
	public static myTranslator get_TRANSLATOR()
	{
	    myTranslator oRueck = null;
	    myTranslator oTest = (myTranslator) bibALL.getSessionValue("TRANSLATOR");
	
	    if (oTest != null)
	    {
	        oRueck = oTest;
	    }
	
	    return oRueck;
	}

    
    
    
    
    
    
    
    
    
    // min-funktion nachgebaut
    public static int iMin(int i1, int i2)
    {
        int iRueck = i1;

        if (i2 < i1)
        {
            iRueck = i2;
        }

        return (iRueck);
    }

    // min-funktion nachgebaut
    public static int iMax(int i1, int i2)
    {
        int iRueck = i1;

        if (i2 > i1)
        {
            iRueck = i2;
        }

        return (iRueck);
    }

 
    // füllt mit vorgestellten "0" auf die länge
    public static String Fuelle000(String cWert, int MaxLen)
    {
        String cRueck = cWert;

        if (cWert.length() < MaxLen)
        {
            for (int i = cWert.length(); i < MaxLen; i++)
            {
                cRueck = ("0" + cRueck);
            }
        }

        return (cRueck);
    }

    
    
    //alte version , nur dann korrekt, wenn das datum im Format dd.mm.yyyy ist
    public static boolean isDatumOK(String DatumString)
    {
        boolean bRueck = false;

        GregorianCalendar cCal = new GregorianCalendar();
        String cTag = "";
        String cMonat = "";
        String cJahr = "";
        Integer iTag;
        Integer iMonat;
        Integer iJahr;

        if (DatumString.trim().length() == 10)
        {
            cTag = DatumString.trim().substring(0, 2);
            cMonat = DatumString.trim().substring(3, 5);
            cJahr = DatumString.trim().substring(6, 10);

            try
            {
                iTag = new Integer(cTag);
                iMonat = new Integer(cMonat);
                iJahr = new Integer(cJahr);

                // calender auf das testdatum setzen
                // !!! Monat wird von 0-11 gezählt !!!
                cCal.set(Calendar.DATE, iTag.intValue());
                cCal.set(Calendar.YEAR, iJahr.intValue());
                cCal.set(Calendar.MONTH, iMonat.intValue() - 1);

                if ((cCal.get(Calendar.DATE) == iTag.intValue()) && ((cCal.get(Calendar.MONTH) + 1) == iMonat.intValue()) && (cCal.get(Calendar.YEAR) == iJahr.intValue()))
                {
                    bRueck = true;
                }
            }
            catch (Exception e)
            {
            }
        }

        return (bRueck);
    }

    

    
    //neue Version, erkennt alle formate wie folgt als korrekt: ddmm, ddmmyy, ddmmyyyy, dd.mm.yyyy
    public static boolean isDatumOK_NG(String DatumString)
    {
        return new MyDate(DatumString).get_bOK();
    }
    
    
    // methode, die auch ein datum wie 31122001
    // ein format 31.12.2001 überführt
    public static String BaueDatumUm(String cDatum)
    {
        String cRueck = cDatum;

        if (cDatum.length() == 8)
        {
            cRueck = cDatum.substring(0, 2) + "." + cDatum.substring(2, 4) + "." + cDatum.substring(4);
        }

        return (cRueck);
    }

    // methode, die auch ein datum wie 31122001
    // ein format überführt, das in der datum-maske definiert wird
    // (es müssen die teilstrings dd mm yyyy enthalten sein)
    public static String BaueDatumSQL(String cDatum, String cDatumMaske)
    {
        String cRueck = "";
        String cTestDatum = bibALL.BaueDatumUm(cDatum);

        // ermittlung der positionen der datumsanteile in der maske
        int posDD = 0;
        int posMM = 0;
        int posYYYY = 0;

        posDD = cDatumMaske.toUpperCase().indexOf("DD");
        posMM = cDatumMaske.toUpperCase().indexOf("MM");
        
        posYYYY = cDatumMaske.toUpperCase().indexOf("YYYY");
        

        // anteile des echten datums
        String cTag = "";
        String cMonat = "";
        String cJahr = "";

        if (bibALL.isDatumOK(cTestDatum))
        {
            // dann die anteile neu positionieren   
            cTag = cTestDatum.substring(0, 2);
            cMonat = cTestDatum.substring(3, 5);
            cJahr = cTestDatum.substring(6, 10);

            // cRueck = cDatumMaske.
            StringBuffer buf = new StringBuffer(cDatumMaske);
            buf = buf.replace(posDD, posDD + 2, cTag);
            buf = buf.replace(posMM, posMM + 2, cMonat);
            buf = buf.replace(posYYYY, posYYYY + 4, cJahr);

            cRueck = buf.toString();
        }

        return (cRueck);
    }

    // übernimmt einen string 
    // und prüft od der inhalt eine integer ist
    public static boolean isInteger(String pIntegerString)
    {
        boolean bRueck = false;

        Integer iInt;

        if (pIntegerString.trim().length() > 0)
        { // sonst falsch

            try
            {
                iInt = new Integer(pIntegerString);

                if (iInt.toString().trim().equals(pIntegerString))
                {
                    bRueck = true;
                }
            }
            catch (Exception e)
            {
            }
        }

        return (bRueck);
    }


    // übernimmt einen string 
    // und prüft od der inhalt eine integer ist
    public static boolean isNumber(String pNumberString, boolean StringIsFormated)
    {
        boolean bRueck = false;
        String cNummer = pNumberString;
        
        if (StringIsFormated)
        {
        	DotFormatter df = new DotFormatter(pNumberString,5,Locale.GERMAN,true,3);
        	return df.doFormat();
        }
        

        if (pNumberString.trim().length() > 0)
        { // sonst falsch

            try
            {
                new Double(cNummer);
                bRueck = true;
            }
            catch (Exception e)
            {
            }
        }

        return (bRueck);
    }


    /**
     * prueft, ob ein string ein numerischer wert sein kann. Falls LEER -> false 
     * @param pNumberString
     * @param StringIsFormated
     * @return
     */
    public static boolean isNumber_empty_is_false(String pNumberString, boolean StringIsFormated)
    {
        boolean bRueck = false;
        if (pNumberString == null || pNumberString.trim().equals(""))
        {
        	return false;
        }
        
        
        String cNummer = pNumberString;
        
        if (StringIsFormated)
        {
        	DotFormatter df = new DotFormatter(pNumberString,5,Locale.GERMAN,true,3);
        	return df.doFormat();
        }
        

        if (pNumberString.trim().length() > 0)
        { // sonst falsch

            try
            {
                new Double(cNummer);
                bRueck = true;
            }
            catch (Exception e)
            {
            }
        }

        return (bRueck);
    }

    
    
    
    // übernimmt einen string 
    // und prüft od der inhalt eine integer ist
    public static boolean isLong(String pLongString)
    {
        boolean bRueck = false;

        Long iInt;

        if (pLongString!=null && pLongString.trim().length() > 0)
        { // sonst falsch

            try
            {
                iInt = new Long(pLongString);

                if (iInt.toString().trim().equals(pLongString))
                {
                    bRueck = true;
                }
            }
            catch (Exception e)
            {
            }
        }

        return (bRueck);
    }

    
    


    public static String NormalForm(String pAusgangsString)
    {
        String hStr = "";
        StringBuffer neuString = new StringBuffer();
        ;

        String TauschQuelle = "ä ö ü ß Ä Ö Ü ";
        String TauschZiel = "aeoeuessAEOEUE";
        int i;
        int TauschPos = 0;
        int Laenge = 0;

        hStr = pAusgangsString.toUpperCase().trim();
        Laenge = hStr.length();

        for (i = 0; i < Laenge; i++)
        {
            TauschPos = TauschQuelle.indexOf(hStr.substring(i, i + 1));

            if (TauschPos > 0)
            {
                neuString.append(TauschZiel.substring(TauschPos, TauschPos + 2));
            }
            else
            {
                neuString.append(hStr.substring(i, i + 1));
            }
        }

        return (neuString.toString());
    }

    public static String MindestLaenge(String pAusgangsString, int pMindestLaenge)
    {
        String LeerString = "                                                                                                           ";
        String RueckString = "";

        if (pAusgangsString.length() < pMindestLaenge)
        {
            RueckString = pAusgangsString + LeerString.substring(0, pMindestLaenge - pAusgangsString.length());
        }
        else
        {
            RueckString = pAusgangsString.substring(0, pMindestLaenge);
        }

        return (RueckString);
    }


    public static String HoechstLaenge(String pAusgangsString, int HoechtsLaenge)
    {
        String RueckString = pAusgangsString;

        if (pAusgangsString.length() >= HoechtsLaenge)
        {
            RueckString = pAusgangsString.substring(0, HoechtsLaenge);
        }

        return (RueckString);
    }

    
    /**
     * @param oDate
     * @param bTicks
     * @return s string like 1999-12-31 oder (bTicks=true) '1999-12-31'
     */
    public static String makeSQL(Date oDate,boolean bTicks)
    {
    	SimpleDateFormat oDF = new SimpleDateFormat("yyyy-MM-dd");
    	String cRueck = oDF.format(oDate);
    	if (bTicks)
    		cRueck = "'"+cRueck+"'";
    	
    	return cRueck;
    }
    
    
    
    // fügt in einem sql-string für ein vorhandenes ' ein zweites ein  
    public static String MakeSql(String pAusgangsString)
    {
        String RueckString = "";

        RueckString = "'" +bibALL.MakeSqlInnerString(pAusgangsString)  + "'";

        if (RueckString.equals("''"))
        {
            RueckString = "null";
        }
        return (RueckString);
    }



	// fügt in einem sql-string für ein vorhandenes ' ein zweites ein  
	// ohne die zwei ' am anfang und ende zu zeigen
	public static String MakeSqlInnerString(String pAusgangsString)
	{
		String hStr = "";
		StringBuffer neuString = new StringBuffer();
		String RueckString = "";
		int i;
		int Laenge = 0;

		hStr = pAusgangsString.trim();
		Laenge = hStr.length();

		// jetzt die ascii-160 gegen 32 tauschen
		hStr = bibALL.make32Leer(hStr);

		for (i = 0; i < Laenge; i++)
		{
			if (hStr.substring(i, i + 1).equals("'"))
			{
				neuString.append("''");
			}
			else
			{
				neuString.append(hStr.substring(i, i + 1));
			}
		}

		RueckString = neuString.toString();

		return (RueckString);
	}




    // fügt in einem sql-string für ein vorhandenes ' ein zweites ein  
    // variante macht kein trim()
    public static String MakeSql(String pAusgangsString, boolean bExactString)
    {
        String hStr = "";
        StringBuffer neuString = new StringBuffer();
        String RueckString = "";
        int i;
        int Laenge = 0;

        if (bExactString)
        {
            hStr = pAusgangsString;
        }
        else
        {
            hStr = pAusgangsString.trim();
        }

        // jetzt die ascii-160 gegen 32 tauschen
        hStr = bibALL.make32Leer(hStr);

        Laenge = hStr.length();

        for (i = 0; i < Laenge; i++)
        {
            if (hStr.substring(i, i + 1).equals("'"))
            {
                neuString.append("''");
            }
            else
            {
                neuString.append(hStr.substring(i, i + 1));
            }
        }

        RueckString = "'" + neuString.toString() + "'";

        if (RueckString.equals("''"))
        {
            RueckString = "null";
        }

        return (RueckString);
    }

    

    public static String MakeNUMSql(String cAusgangswert)
    {
        String cRueck = "NULL";

        if (!cAusgangswert.trim().equals(""))
        {
            cRueck = cAusgangswert.trim();
        }

        return (cRueck);
    }

    public static String MakeSql(int pAusgangswert)
    {
        return (new Integer(pAusgangswert).toString());
    }

    public static String MakeSql(double pAusgangswert)
    {
        return (new Double(pAusgangswert).toString());
    }

    public static String MakeSql(float pAusgangswert)
    {
        return (new Float(pAusgangswert).toString());
    }

    // methode macht aus einem  textstring folgender bauart 
    // @A|1@B|2@C|3@
    // einen 3 x 2-array mit den Textblöcken als inhalt
    // falls leererlaubt = false, dann muss die definition symmetrisch sein
    public static String[][] TrenneZeileIn_2_DIM(String cQuelle, String cTrennerAussen, String cTrennerInnen, String cLeerValue, boolean bLeerErlaubt)
    {
        String[][] cRueck = null;
        int iAnzahlDIM1 = 0; // dimension 1
        int iAnzahlDIM2 = 0; // dimension 2

        Vector<String> vHelp = new Vector<String>();
        Vector<String> vHelp2 = new Vector<String>();

        if (!cQuelle.equals(""))
        {
            vHelp = bibALL.TrenneZeile(cQuelle, cTrennerAussen);

            // vHelp enthält alle inneren blöcke
            if (vHelp.size() > 0)
            {
                iAnzahlDIM1 = vHelp.size();

                // zuerst die größte dimension der inneren blöcke finden
                for (int i = 0; i < vHelp.size(); i++)
                {
                    vHelp2 = bibALL.TrenneZeile((String) vHelp.get(i), cTrennerInnen);

                    if (vHelp2.size() > iAnzahlDIM2)
                    {
                        iAnzahlDIM2 = vHelp2.size();
                    }
                }

                // jetzt steht die dimension fest
                cRueck = new String[iAnzahlDIM1][iAnzahlDIM2];

                // jetzt wird gefüllt                   
                for (int k = 0; k < iAnzahlDIM1; k++)
                {
                    vHelp2 = bibALL.TrenneZeile((String) vHelp.get(k), cTrennerInnen);

                    // falls keine leeren zellen erlaubt sind, und der quellstring unsymmetrisch ist
                    // abbrechen und null zurückgeben
                    if (!bLeerErlaubt && (iAnzahlDIM2 != vHelp2.size()))
                    {
                        cRueck = null;

                        break;
                    }
                    else
                    {
                        // vorderfinieren, falls nicht alle dimensionen stimmen
                        for (int j = 0; j < iAnzahlDIM2; j++)
                        {
                            cRueck[k][j] = cLeerValue;
                        }

                        for (int j = 0; j < vHelp2.size(); j++)
                        {
                            cRueck[k][j] = (String) vHelp2.get(j);
                        }
                    }
                }
            }
        }

        return cRueck;
    }

    // liest eine zeile durch und trennt die einzelnen bestandteile in einen vector
    public static Vector<String> TrenneZeile(String pQuelle, String cTrenner)
    {
        /* methode trennt einen string mit variabler feldlänge in seine
        betandteile und legt diese in einen vector
        cQuelle = Quellstring
        cTrenner = trennzeichen
        */
        int iLen;

        Vector<String> vRueck = new Vector<String>();
        String cSegment = "";
        String cQuelle = new String(pQuelle);
        
        if (cQuelle.equals(cTrenner) || cQuelle.equals(cTrenner+cTrenner))
        {
        	return vRueck;
        }
        
        
        if (cQuelle.length() > 0 )     //falls die quelle genau einem trenner entspricht, dann kommt nix zurueck
        {
            // falls der trenner vorne oder hinten vorkommt, dann weg
            if (cQuelle.substring(0, 1).equals(cTrenner))
            {
                cQuelle = cQuelle.substring(1);
            }

            if (cQuelle.substring(cQuelle.length() - 1).equals(cTrenner))
            {
                cQuelle = cQuelle.substring(0, cQuelle.length() - 1);
            }

            iLen = cQuelle.length();

            for (int i = 0; i < iLen; i++)
            {
                if (cQuelle.substring(i, i + 1).equals(cTrenner))
                {
                    vRueck.add(cSegment);
                    cSegment = "";
                }
                else
                {
                    cSegment += cQuelle.substring(i, i + 1);
                }
            }

            vRueck.add(cSegment); // das letzte segment noch eintragen
        }

        return vRueck;
    }

    public static boolean TrenneZeile(String cQuelle, String cTrenner, String[] cRueck, int aLen)
    {
        /* methode trennt einen string mit variabler feldlänge in seine
        betandteile und legt diese in ein array

        cQuelle = Quellstring
        cTrenner = trennzeichen
        cRueck   = rückgabearray
        aLen     = länge des rückgabearray's
        */
        int i = 0;
        int j = 0;
        int iLen = cQuelle.length();
        boolean bRueck = true;

        for (i = 0; i < aLen; i++)
        {
            cRueck[i] = "";
        }

        ;

        for (i = 0; i < iLen; i++)
        {
            if (cQuelle.substring(i, i + 1).equals(cTrenner))
            {
                j++;

                if (j >= aLen)
                {
                    break; // sonst wird arraygrenze überschritten
                }
            }
            else
            {
                cRueck[j] = cRueck[j] + cQuelle.substring(i, i + 1);
            }
        }

        return bRueck;
    }

    // methode trennt einen string in eine bestimmte laenge und macht zeilentrenner rein (\n)
    public static String TrenneZeileAufMaxLen(String cQuelle, int iLang)
    {
        int i = 0;
        int iLen = cQuelle.length();

        String cRueck = "";

        try
        {
            if (iLang > 0)
            {
                for (i = 0; i < iLen; i++)
                {
                    cRueck += cQuelle.substring(i, i + 1);

                    if ((i > 0) && ((i % iLang) == 0))
                    {
                        cRueck += "\n";
                    }
                }
            }
            else
            {
                cRueck = cQuelle;
            }
        }
        catch (Exception e)
        {
            cRueck = e.getMessage();
        }

        return cRueck;
    }

    public static boolean TrenneZeile(String cQuelle, String cTrenner, String[] cRueck, int aLen, char cSuchZeichen, char cErsetzZeichen)
    {
        /* methode trennt einen string mit variabler feldlänge in seine
        betandteile und legt diese in ein array

        unterschied zu variante 1: es werden 2 char-konstanten per suche und ersetzt ausgetauscht

        cQuelle = Quellstring
        cTrenner = trennzeichen
        cRueck   = rückgabearray
        aLen     = länge des rückgabearray's
        cSuchZeichen
        cErsetzZeichen   =   Austausch auf Zeichen
        */
        int i = 0;
        int j = 0;
        int iLen = cQuelle.length();
        boolean bRueck = true;

        for (i = 0; i < aLen; i++)
        {
            cRueck[i] = "";
        }

        ;

        for (i = 0; i < iLen; i++)
        {
            if (cQuelle.substring(i, i + 1).equals(cTrenner))
            {
                j++;

                if (j >= aLen)
                {
                    break; // sonst wird arraygrenze überschritten
                }
            }
            else
            {
                cRueck[j] = cRueck[j] + cQuelle.substring(i, i + 1);
            }
        }

        for (i = 0; i < aLen; i++)
        {
            cRueck[i] = cRueck[i].replace(cSuchZeichen, cErsetzZeichen);
        }

        ;

        return bRueck;
    }

    // variante mit tausender-punkt
    public static String makeDez(double dZahl, int iAnzahlDez, boolean bTausender)
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

        df = new DecimalFormat(cFormat);

        cRueck = df.format(dZahl);

        return (cRueck);
    }

    // formatieren long mit uebergebener maske
    // 
    public static String makeDez(long dZahl, String cMaske)
    {
        String cRueck = "" + dZahl;
        DecimalFormat df;

        df = new DecimalFormat(cMaske);
        cRueck = df.format(dZahl);

        return (cRueck);
    }

    // variante mit tausender-punkt (long integer)
    public static String makeDez(long dZahl, boolean bTausender)
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

        df = new DecimalFormat(cFormat);

        cRueck = df.format(dZahl);

        return (cRueck);
    }

 
    // formatiert eine zahl auf eine bestimmte gesamtlänge rechtsbündig
    public static String FormatRechts(String pQuellStr, int pMindestLaenge)
    {
        String cRueck = "";
        String cQuellStr = "";
        String cLeer = "                                         ";

        cQuellStr = pQuellStr.trim();

        if (cQuellStr.length() > pMindestLaenge)
        {
            cRueck = cQuellStr;
        }
        else
        {
            cRueck = cLeer.substring(0, (pMindestLaenge - cQuellStr.length())) + cQuellStr;
        }

        return (cRueck);
    }

    // formatiert ein Datum (aus der darstellung "2001-12-31 00:00:00.0"
    // in 31.12.2001
    public static String FormatDatum(String pQuellStr)
    {
        String cRueck = "";
        String cQuellStr = "";

        cQuellStr = pQuellStr.trim();

        // sonderformat 1.1.1
        if (cQuellStr.startsWith("1-01-01"))
        {
            cRueck = "01.01.0001";
        }
        else
        {
            if (cQuellStr.length() >= 10)
            { // sonst stimmt was nicht
                cRueck = cQuellStr.substring(8, 10) + "." + cQuellStr.substring(5, 7) + "." + cQuellStr.substring(0, 4);
            }
            else
            {
                cRueck = cQuellStr;
            }
        }

        return (cRueck);
    }

    // formatiert ein Datum (aus der darstellung "2001-12-31 00:00:00.0"
    // in 31.12.01
    public static String FormatDatumKurz(String pQuellStr)
    {
        String cRueck = "";
        String cQuellStr = "";

        cQuellStr = pQuellStr.trim();

        // sonderformat 1.1.1
        if (cQuellStr.startsWith("1-01-01"))
        {
            cRueck = "01.01.01";
        }
        else
        {
            if (cQuellStr.length() >= 10)
            { // sonst stimmt was nicht
                cRueck = cQuellStr.substring(8, 10) + "." + cQuellStr.substring(5, 7) + "." + cQuellStr.substring(2, 4);
            }
            else
            {
                cRueck = cQuellStr;
            }
        }

        return (cRueck);
    }

    // methode übernimmt ein 2x2-array mit:
    // [0]=tabellennamen, [1]=feldname
    // und macht daraus den select-teil einer abfrage
    public static String makeSelectFeldBlock(String[][] cFelder)
    {
        int i = 0;

        StringBuffer cRueck = new StringBuffer("");

        cRueck.append(cFelder[i][0]);
        cRueck.append(".");
        cRueck.append(cFelder[i][1]);
        cRueck.append(" ");
        cRueck.append(cFelder[i][1]);

        for (i = 1; i < cFelder.length; i++)
        {
            cRueck.append(",");
            cRueck.append(cFelder[i][0]);
            cRueck.append(".");
            cRueck.append(cFelder[i][1]);
            cRueck.append(" ");
            cRueck.append(cFelder[i][1]);
        }

        return (cRueck.toString());
    }

    // methode baut einen string mit leerzeichen = ascii 32,
    // damit einheitliches behandeln möglich ist (aus html kommt standardmaessig ascii 160 für leer)
    public static String make32Leer(String cQuelle)
    {
        char c32 = 32;
        char c160 = 160;
        String cRueck = cQuelle.replace(c160, c32);

        return (cRueck);
    }

    public static Integer String2Integer(String cZahl)
    {
        Integer iRueck = null;

        if (cZahl.trim().length() > 0)
        { // sonst falsch

            try
            {
                Integer iInt = new Integer(cZahl.trim());

                if (iInt.toString().trim().equals(cZahl.trim()))
                {
                    iRueck = iInt;
                }
            }
            catch (Exception e)
            {
                iRueck = null;
            }
        }

        return (iRueck);
    }

    // einen zahlenstring umwandeln in Double-objekt,
    // wenn es schief geht, dann null-rückgabe
    public static Double makeDoublefromNumberString(String pWert, String sDecimalSeparator, String sThousandSeparator)
    {
        String cWert = pWert;
        Double dWert;

        cWert = bibALL.ReplaceTeilString(cWert, sThousandSeparator, "");
        cWert = bibALL.ReplaceTeilString(cWert, sDecimalSeparator, ".");

        try
        {
            dWert = new Double(cWert);
        }
        catch (Exception e)
        {
            dWert = null;
        }

        return dWert;
    }

    // einen zahlenstring umwandeln in Long-objekt,
    // wenn es schief geht, dann null-rückgabe
    public static Long makeLongfromNumberString(String pWert, String sThousandSeparator)
    {
        String cWert = pWert;
        Long lWert;

        cWert = bibALL.ReplaceTeilString(cWert, sThousandSeparator, "");

        try
        {
            lWert = new Long(cWert);
        }
        catch (Exception e)
        {
            lWert = null;
        }

        return lWert;
    }

    // methode gibt ein zeichen zurück, wenn string leer ist
    public static String MinLen(String cWert)
    {
        String cRueck = cWert;

        if (cWert.equals(""))
        {
            cRueck = " ";
        }

        return (cRueck);
    }

    // methode, die 2 strings ohne leerzeichen und nur mittels grossschreibung
    // vergleicht, es werden sowohl chr(32) als auch chr(160)-leerzeichen
    // entfernt
    public static boolean bStringVergleich(String pString1, String pString2)
    {
        boolean bRueck = false;
        char c32 = 32;
        char c160 = 160;
        char c0 = 0;
        String cString1 = pString1;
        String cString2 = pString2;

        cString1 = cString1.replace(c160, c0);
        cString1 = cString1.replace(c32, c0);
        cString2 = cString2.replace(c160, c0);
        cString2 = cString2.replace(c32, c0);

        if (cString2.toUpperCase().equals(cString1.toUpperCase()))
        {
            bRueck = true;
        }

        return (bRueck);
    }



    
    // wrapper-methoden um session einzupacken, damit bei mehreren servlet-applications
    // keine zufälligen namenskollisionen stattfinden
    // statische methode, um die 2 vectoren __JIL_NAMEN
    //                             und      __JIL_OBJECTS auszulesen
    @SuppressWarnings("unchecked")
	public static void setSessionValue(String cName, Object oObject)
    {

        HashMap<String, Object> __JIL_VALUES = (HashMap<String, Object>) bibE2.get_CurrSession().getAttribute("__JIL_VALUES");
        
        if (__JIL_VALUES.containsKey(cName.toUpperCase())){
        	__JIL_VALUES.remove(cName.toUpperCase());
        }
        __JIL_VALUES.put(cName.toUpperCase(), oObject);
    
    }

    
    
    
    // statische methode, um die 2 vectoren __JIL_NAMEN
    //                             und      __JIL_OBJECTS auszulesen
    //use instead: bibSES.getSessionValue()
    @SuppressWarnings("unchecked")
    public static Object getSessionValue(String cName)
    {
        Object oRueck = null;

        HashMap<String, Object> __JIL_VALUES =  (HashMap<String, Object>) bibE2.get_CurrSession().getAttribute("__JIL_VALUES");
       
        if (__JIL_VALUES.containsKey(cName.toUpperCase())){
        	oRueck = __JIL_VALUES.get(cName.toUpperCase());
        }
        
        return oRueck;
    }

    
 
    
    
    // statische methode, um die 2 vectoren __JIL_NAMEN
    //                             und      __JIL_OBJECTS auszulesen
    public static Object getSessionValue(String cName, HttpSession  oSES)
    {
        Object oRueck = null;

        HashMap<String, Object> __JIL_VALUES =  (HashMap<String, Object>)  oSES.getAttribute("__JIL_VALUES");
        
        if (__JIL_VALUES.containsKey(cName.toUpperCase())){
        	oRueck = __JIL_VALUES.get(cName.toUpperCase());
        }

        return oRueck;
    }

 

    
    
    
    // statische methode, um die 2 vectoren __JIL_NAMEN
    //                             und      __JIL_OBJECTS auszulesen
    public static Object deleteSessionValue(String cName)
    {
    	Object oRueck = null;
        HashMap<String, Object> __JIL_VALUES =  (HashMap<String, Object>) bibE2.get_CurrSession().getAttribute("__JIL_VALUES");
       
        if (__JIL_VALUES.containsKey(cName.toUpperCase())){
        	__JIL_VALUES.remove(cName.toUpperCase());
        }
        
        return oRueck;
    }
 
    
    
    
    
    
    
    
    
    
    
    
    
//
//    // erzeugt einen leeren vector, wenn der wert null kommt
//    public static Vector notNullVector(Vector vTest)
//    {
//        Vector vRueck = vTest;
//
//        if (vRueck == null)
//        {
//            vRueck = new Vector();
//        }
//
//        return vRueck;
//    }
//

    

    // erzeugt einen leeren vector, wenn der wert null kommt
    public static Vector<String> notNullVectorString(Vector<String> vTest)
    {
        Vector<String> vRueck = vTest;

        if (vRueck == null)
        {
            vRueck = new Vector<String>();
        }

        return vRueck;
    }


    
    
    
    /**
     * @return Datum imm Format: 31.12.2007
     */
    public static String get_cDateNOW()
    {
        return myDateHelper.FormatDateNormal(new GregorianCalendar().getTime());
    }

    
    /**
     * Gibt das aktuelle Systemdatum im ISO-Format yyyy-MM-dd zurück.
     * @author manfred
     * @date 22.03.2016
     *
     * @return Datum im Format 2016-03-22
     */
    public static String get_cDateNOW_ISO()
    {
    	return myDateHelper.FormatDateISO(new GregorianCalendar().getTime());
    }
   
    
    /**
     * @return Datum imm Format: 01.12.2007
     */
    public static String get_cDateFirstDateActualMonth()
    {
        return myDateHelper.FormatDateNormal(  myDateHelper.Find_First_Day_OfMonth(new GregorianCalendar()).getTime());
    }

    
    /**
     * @return Datum imm Format: 31.12.2007
     */
    public static String get_cDateLastDateActualMonth()
    {
        return myDateHelper.FormatDateNormal(  myDateHelper.Find_Last_Day_OfMonth(new GregorianCalendar()).getTime());
    }


    
    
    
    
    
    /**
     * @return actual systemtime in format hh:mm:ss
     */
    public static String get_cTimeNow()
    {
    	String cRueck= "";
    	
    	DateFormat df = DateFormat.getTimeInstance(DateFormat.MEDIUM);
    	cRueck = df.format(new GregorianCalendar().getTime());
    	
    	return cRueck;
    	
    }
    

    /**
     * @return actual systemtime in format hh:mm:ss
     */
    public static String get_cTimeNowExact()
    {
    	String cRueck= "";
    	SimpleDateFormat df = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss.S" );
    	
    	cRueck = df.format(new GregorianCalendar().getTime());
    	
    	return cRueck;
    	
    }

    
    /**
     * @param cTrenner
     * @return liefert das tagesdatum in der form YYYY.DD.MM, wobei der trenner
     *  beliebig sein kann (auch leer)
     */
    public static String get_cDateNOWInverse(String cTrenner)
    {
        String  cHelp =myDateHelper.FormatDateNormal(new GregorianCalendar().getTime());
        String  cRueck = cHelp.substring(6,10)+cTrenner+cHelp.substring(3,5)+cTrenner+cHelp.substring(0,2);
        return cRueck;
        
    }
    
    
    
    /**
     * @return liefert einen stempel yyyymmddhhmmss
     */
    public static String get_cDateTimeNOWInverse()
    {
        String  cHelp =bibALL.get_cDateNOWInverse("");
        String  cHelp2 = bibALL.get_cTimeNow();
        
        String  cRueck = cHelp+bibALL.ReplaceTeilString(cHelp2, ":","");
        return cRueck;
    }

  
    
    /**
     * @return Datum imm Format: 31.Dezember 2007
     */
    public static String get_cDateTimeNOW()
    {
        return myDateHelper.FormatDateTimeLang(new GregorianCalendar().getTime());
    }
    
    

    public static Vector<String> get_VectorAusArray(String[] cArray)
    {
        Vector<String> vRueck = new Vector<String>();

        for (int i = 0; i < cArray.length; i++)
        {
            vRueck.add(cArray[i]);
        }

        return vRueck;
    }

    public static Vector<String> get_VectorAusArrayColumn(String[][] cArray,int iCol)
    {
        Vector<String> vRueck = new Vector<String>();

        for (int i = 0; i < cArray.length; i++)
        {
            vRueck.add(cArray[i][iCol]);
        }

        return vRueck;
    }
    
    
    


    public static String fillString(String cOriginal, int iGesamtLaenge, char Fueller, boolean bLeft)
    {
        String cRueck = cOriginal;

        if (cOriginal != null)
        {
            if (cRueck.length() < iGesamtLaenge)
            {
                int iZusatzzeichen = (iGesamtLaenge - cRueck.length());

                for (int i = 0; i < iZusatzzeichen; i++)
                {
                    if (bLeft)
                    {
                        cRueck = "" + Fueller + cRueck;
                    }
                    else
                    {
                        cRueck = cRueck + Fueller;
                    }
                }
            }
        }

        return cRueck;
    }

    public static long get_usedMemory()
    {
        long lMEM = 0;

        lMEM = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        return lMEM;
    }
    
    
    public static long get_freeMemory()
    {
        long lMEM = 0;

        lMEM = Runtime.getRuntime().freeMemory();

        return lMEM;
    }
  
    
    
    // eigene implementierung der toUpperCase - methode, die ß nicht in SS umwandelt
    public static String UCASE(String cInput)
    {
    	 StringBuffer cOutput = new StringBuffer("");;
    	 
    	 if (cInput == null)
    	 {
    	 	return null;
    	 }
    	 
    	 
    	 
    	 for (int i=0;i<cInput.length();i++)
    	 {
    	 	 cOutput.append(Character.toUpperCase(cInput.charAt(i)));
    	 	
    	 }
    	 return cOutput.toString(); 
    }
    
    
    
    
    public static double Round(double dWert, int iDecimalSize)
    {
    	double dRet = 0;
		BigDecimal bdTemp = new BigDecimal(dWert);
		BigDecimal bdValue = bdTemp.setScale(iDecimalSize, BigDecimal.ROUND_HALF_UP);
		dRet = bdValue.doubleValue();
    	return dRet;
    }
    

    public static float Round(float dWert, int iDecimalSize)
    {
    	float fRet = 0;
		BigDecimal bdTemp = new BigDecimal(dWert);
		BigDecimal bdValue = bdTemp.setScale(iDecimalSize, BigDecimal.ROUND_HALF_UP);
		fRet = bdValue.floatValue();
    	return fRet;
    }
     
    
    
    /*
     * methode, die einen substring liefert, wenn der ursrpungsstring
     * lang genug ist, sonst den ursprungsstring (ohne Exception)
     */
    public static String get_LeftString(String cUrsprung,int iBis)
    {
       String cRueck = cUrsprung;
       
       if (cRueck != null)
       {
           if (cRueck.length()>=iBis)
           {
               cRueck = cRueck.substring(0,iBis);
           }
       }
       return cRueck; 
    }
    
    
    
    
    
    
    
    /*
     *  methode uebernimmt eine hashmap und macht aus dem key - teil einen vector 
     */
    public static Vector<String> get_vBuildKeyVectorFromHashmap(HashMap<String,String> oHash)
    {
		Vector<String>   vRueck = null;
		
		if (oHash != null)
		{
			Iterator it = oHash.entrySet().iterator(); 

			vRueck = new Vector<String>();
		    
			while (it.hasNext()) 
			{
			    Map.Entry entry = (Map.Entry)it.next();
			    vRueck.add((String)entry.getKey());
			} 			
		}
		
		return vRueck;
    }
    

    /*
     *  methode uebernimmt eine hashmap und macht aus dem Value - teil einen vector 
     */
    public static Vector<String> get_vBuildValueVectorFromHashmap(HashMap<String,String> oHash)
    {
		Vector<String>   vRueck = null;
		
		if (oHash != null)
		{
			Iterator it = oHash.entrySet().iterator(); 
			
		    vRueck = new Vector<String>();
		    
			while (it.hasNext()) 
			{
			    Map.Entry entry = (Map.Entry)it.next();
			    vRueck.add((String)entry.getValue());
			} 			
		}
		
		return vRueck;
    }

    
    
    
    
    public static String[][] get_cSelArrayMonate()
    {
		/*
		 * selektionskomponenten fuer die selektion von monaten
		 */
		String[][] cSELEKT = { {"*",""},       				
								{"Jan","1"},
								{"Feb","2"},
								{"Mär","3"},
								{"Apr","4"},
								{"Mai","5"},
								{"Jun","6"},
								{"Jul","7"},
								{"Aug","8"},
								{"Sep","9"},
								{"Okt","10"},
								{"Nov","11"},
								{"Dez","12"},
								};
		return cSELEKT;
		
    }
    
    
    public static String[][] get_cSelArrayMonate2Digits()
    {
		/*
		 * selektionskomponenten fuer die selektion von monaten
		 */
		String[][] cSELEKT = { {"*",""},       				
								{"Jan","01"},
								{"Feb","02"},
								{"Mär","03"},
								{"Apr","04"},
								{"Mai","05"},
								{"Jun","06"},
								{"Jul","07"},
								{"Aug","08"},
								{"Sep","09"},
								{"Okt","10"},
								{"Nov","11"},
								{"Dez","12"},
								};
		return cSELEKT;
		
    }
    
    
 
    public static String[][] get_cSelJahre()
    {
		/*
		 * das jahr ab 2000 bis jetzt
		 */
		Vector<String> vHelp = bibALL.TrenneZeile(bibALL.get_cDateNOW(),".");
		int iJetzt = 2020;
		try 
		{
			iJetzt = new Integer((String)vHelp.get(2)).intValue();
		}
		catch(Exception exx){}
		
		String[][] cSELEKT = new String[iJetzt+3-2000][2];
		cSELEKT[0][0]="*";
		cSELEKT[0][1]="";
		for (int i=0;i<iJetzt+2-2000;i++)
		{
		    cSELEKT[i+1][0]=""+(2000+i);
		    cSELEKT[i+1][1]=""+(2000+i);
		}
		
		return cSELEKT;
    }
    
    
    
    /**
     * @returns actual month
     */
    public static String get_cMonthNow() throws myException
    {
        String cRueck = "";
		Vector<String> vHelp = bibALL.TrenneZeile(bibALL.get_cDateNOW(),".");
		try 
		{
			int iMonat = new Integer((String)vHelp.get(1)).intValue();
			cRueck = (""+iMonat).trim();
		}
		catch(Exception exx)
		{
		    throw new myException("bib.get_cMonthNow:cannot calculate acutal month!");
		}
		
		return cRueck;
    }
    
    /**
     * @returns actual month
     */
    public static String get_cYearNow() throws myException
    {
        String cRueck = "";
		Vector<String> vHelp = bibALL.TrenneZeile(bibALL.get_cDateNOW(),".");
		try 
		{
			int iYear = new Integer((String)vHelp.get(2)).intValue();
			cRueck = (""+iYear).trim();
		}
		catch(Exception exx)
		{
		    throw new myException("bib.get_cYearNow:cannot calculate acutal year!");
		}
		
		return cRueck;
    }
 
    
   
    
    
    public static boolean isEmpty(String cTest)
    {
    	return (cTest == null || cTest.trim().equals(""));
    }

    
    
	public static int get_iBaseColorRED()
	{
		int iRueck = 200;
		try
		{
			if (bibALL.get_RECORD_MANDANT().get_COLOR_RED_lValue(new Long(0)).intValue()>0)
			{
				iRueck = bibALL.get_RECORD_MANDANT().get_COLOR_RED_lValue(new Long(0)).intValue(); 
			}
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
	    return iRueck;
	}


	public static int get_iBaseColorGREEN()
	{
		int iRueck = 200;
		try
		{
			if (bibALL.get_RECORD_MANDANT().get_COLOR_GREEN_lValue(new Long(0)).intValue()>0)
			{
				iRueck = bibALL.get_RECORD_MANDANT().get_COLOR_GREEN_lValue(new Long(0)).intValue(); 
			}
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
	    return iRueck;
	}

	public static int get_iBaseColorBLUE()
	{
		int iRueck = 200;
		try
		{
			if (bibALL.get_RECORD_MANDANT().get_COLOR_BLUE_lValue(new Long(0)).intValue()>0)
			{
				iRueck = bibALL.get_RECORD_MANDANT().get_COLOR_BLUE_lValue(new Long(0)).intValue(); 
			}
		} 
		catch (myException e)
		{
			e.printStackTrace();
		}
	    return iRueck;
	}


	
	
	
	public static int get_iBaseColorRED(int iDiff) 
	{
	    int iBase = bibALL.get_iBaseColorRED();
		iBase = iBase+iDiff;
		if (iDiff>0)
			if (iBase>255) iBase = 255;
		if (iDiff<0)
			if (iBase<0) iBase = 0;
	    return iBase;
	    
	}

	public static int get_iBaseColorGREEN(int iDiff)
	{
		int iBase = bibALL.get_iBaseColorGREEN();
		iBase = iBase+iDiff;
		if (iDiff>0)
			if (iBase>255) iBase = 255;
		if (iDiff<0)
			if (iBase<0) iBase = 0;
	    return iBase;
	}

	
	public static int get_iBaseColorBLUE(int iDiff)
	{
		int iBase = bibALL.get_iBaseColorBLUE();
		iBase = iBase+iDiff;
		if (iDiff>0)
			if (iBase>255) iBase = 255;
		if (iDiff<0)
			if (iBase<0) iBase = 0;
	    return iBase;
	}

	
	
	public static int get_iBaseColorDIFF()
	{
	    try
		{
			return bibALL.get_RECORD_MANDANT().get_COLOR_DIFF_lValue(new Long(5)).intValue();
		} 
	    catch (myException e)
		{
			e.printStackTrace();
		}
	    return 5;
	}



	
	/**
	 * 
	 * @return s Unformated ID of mandant in AdressTable
	 */
	public static String get_ID_ADRESS_MANDANT()
	{
		try
		{
			return bibALL.get_RECORD_MANDANT().get_EIGENE_ADRESS_ID_cUF();
		} 
		catch (myException e)
		{
			e.printStackTrace();
			return null;
		}

	}


	
	


	public static MyConnection get_oConnectionNormal()
	{
		return (MyConnection)getSessionValue("CONN_NORMAL"); // die autocommitconnection in die session
	}

	
	
	public static void CloseConnection_In_SESSION()
	{
		if(bibALL.getSessionValue("CONN_NORMAL") != null)
		{
			try
			{
				((MyConnection)getSessionValue("CONN_NORMAL")).get_oConnection().close();
			}
			catch (Exception ex)
			{}
		}

	}
	
	

	public static void CloseConnection_In_SESSION(HttpSession oSES)
	{
		if(bibALL.getSessionValue("CONN_NORMAL",oSES) != null)
		{
			try
			{
				((MyConnection)getSessionValue("CONN_NORMAL",oSES)).get_oConnection().close();
			}
			catch (Exception ex)
			{}
		}

	}
	

	
	
	
//	// erzeugen einer standard-MyDBToolBox
//	public static MyDBToolBox get_myDBToolBox(MyConnection oConn)
//	{
//	
//	    MyDBToolBox oOB = null;
//	    try
//	    {
//	        oOB = new MyDBToolBox(oConn);
//	        //2015-05-06: ersetzt durch direkten zugriff
//	        //oOB.setZusatzFelder((String[][]) bibALL.getSessionValue("ZUSATZFELDER"));
//	        oOB.setZusatzFelder(bibALL.get_DB_ZusatzFelder(true, true, true, 
//	        													bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF().trim(), 
//	        													bibALL.get_RECORD_USER().get_KUERZEL_cUF()));
//	        oOB.set_ErsetzungTableView((String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT"));
//	        oOB.set_bErsetzungTableView(true); // standardmässig wird ersetzt tables gegen views bei JT_tables
//	        
//	    }
//	    catch (myException ex)
//	    {
//	        // im fehlerfall kommt null zurueck
//	    }
//	    
//	    return oOB;
//	}

	
	//2015-05-06: auslagerung 
	public static MyDBToolBox get_myDBToolBox(MyConnection oConn) {
		return MyDBToolBox_FAB.generate_STANDARD_DBToolBox(oConn);
	}
	
	
	
	
	// methode gibt einen connection-eintrag aus dem CONNPOOL-Eintrag in der session zurück
	// oder baut einen neuen eintrag, wenn keiner mehr frei ist
	public static MyDBToolBox get_myDBToolBox()
	{
		//2015-05-07: auslagerung
		return MyDBToolBox_FAB.generate_STANDARD_DBToolBox(bibALL.get_oConnectionNormal());
//	    MyDBToolBox oOB = null;
//	    try
//	    {
//	        oOB = new MyDBToolBox(get_oConnectionNormal());
//	        oOB.setZusatzFelder((String[][]) bibALL.getSessionValue("ZUSATZFELDER"));
//	        oOB.set_ErsetzungTableView((String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT"));
//	        oOB.set_bErsetzungTableView(true); // standardmässig wird ersetzt tables gegen views bei JT_tables
//	        
//	    }
//	    catch (myException ex)
//	    {
//	        // im fehlerfall kommt null zurueck
//	    }
//	    
//	    return oOB;
	}

	
	
	/**
	 * 
	 * @param bMandant
	 * @param bLetzteAenderung
	 * @param bGeaendertVon
	 * @param cID_MANDANT
	 * @param cUserKuerzel
	 * @return
	 */
	public static String[][] get_DB_ZusatzFelder(boolean bMandant, boolean bLetzteAenderung, boolean bGeaendertVon, String cID_MANDANT, String cUserKuerzel)
	{
//		// ende ersetzungstabellen für die views 
//		// hier werden die zusatzfelder für das objekt oDB erzeugt und übergeben
//		
//		int iAnzahl = 0;
//		if (bMandant) iAnzahl++;
//		if (bLetzteAenderung) iAnzahl++;
//		if (bGeaendertVon) iAnzahl++;
//		
//		if (iAnzahl==0)
//			return null;
//			
//		
//		String[][] cZusatzFelder = new String[iAnzahl][2];
//		
//		int iZaehler = 0;
//		
//		if (bMandant)
//		{
//			cZusatzFelder [iZaehler] [0] = "ID_MANDANT";   	
//			cZusatzFelder [iZaehler] [1] = cID_MANDANT;
//			iZaehler++;
//		}
//		if (bGeaendertVon)
//		{
//			cZusatzFelder [iZaehler] [0] = "GEAENDERT_VON"; 	
//			cZusatzFelder [iZaehler] [1] = bibALL.MakeSql(cUserKuerzel);
//			iZaehler++;
//		}
//		if (bLetzteAenderung)
//		{
//			cZusatzFelder [iZaehler] [0] = "LETZTE_AENDERUNG";
//			cZusatzFelder [iZaehler] [1] = DB_META.get_tStampString((String)bibALL.getSessionValue("DBKENNUNG"));
//		}
//
//		return cZusatzFelder;
		
		return DB_STATICS.get_DB_ZusatzFelder(bMandant, bLetzteAenderung, bGeaendertVon, cID_MANDANT, cUserKuerzel);
		
	}
	
	
	
	
	
//	// methode gibt einen connection-eintrag aus dem CONNPOOL-Eintrag in der session zurück
//	// oder baut einen neuen eintrag, wenn keiner mehr frei ist
//	public static MyDBToolBox get_myDBToolBox(boolean bZusatzFelder, boolean bErsetzeTableView, MyConnection oConn)
//	{
//	
//	    MyDBToolBox oOB = null;
//	    try
//	    {
//	        oOB = new MyDBToolBox(oConn);
//	        if (bZusatzFelder)		oOB.setZusatzFelder((String[][]) bibALL.getSessionValue("ZUSATZFELDER"));
//	        if (bErsetzeTableView)	oOB.set_ErsetzungTableView((String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT"));
//	        if (bErsetzeTableView)	oOB.set_bErsetzungTableView(true); // standardmässig wird ersetzt tables gegen views bei JT_tables
//	        
//	    }
//	    catch (myException ex)
//	    {
//	        // im fehlerfall kommt null zurueck
//	    }
//	    
//	    return oOB;
//	}


	
	// methode gibt einen connection-eintrag aus dem CONNPOOL-Eintrag in der session zurück
	// oder baut einen neuen eintrag, wenn keiner mehr frei ist
	public static MyDBToolBox get_myDBToolBox(boolean bZusatzFelder, boolean bErsetzeTableView)
	{
		// 2015-05-07: ausgelagert
		return MyDBToolBox_FAB.get_myDBToolBox(bZusatzFelder, bErsetzeTableView);
//	    MyDBToolBox oOB = null;
//	    try
//	    {
//	        oOB = new MyDBToolBox(get_oConnectionNormal());
//	        if (bZusatzFelder)		oOB.setZusatzFelder((String[][]) bibALL.getSessionValue("ZUSATZFELDER"));
//	        if (bErsetzeTableView)	oOB.set_ErsetzungTableView((String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT"));
//	        
//	        oOB.set_bErsetzungTableView(bErsetzeTableView); // standardmässig wird ersetzt tables gegen views bei JT_tables
//	        
//	    }
//	    catch (myException ex)
//	    {
//	        // im fehlerfall kommt null zurueck
//	    }
//	    
//	    return oOB;
	}

	

	
	
	
	
	
	
	// wird hier nochmal geführt, damit logfile-beschreibungen möglich sind
	public static void destroy_myDBToolBox(MyDBToolBox oDB)
	{
	    if (oDB != null)
	    {
	        oDB = null;
	    }
	}

	/**
	 * 
	 * @return s unformated ID_MANDANT
	 */
	public static String get_ID_MANDANT()
	{
		try
		{
			return bibALL.get_RECORD_USER().get_ID_MANDANT_cUF();
		} 
		catch (myException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static String get_ID_USER()
	{
		
		try
		{
			return bibALL.get_RECORD_USER().get_ID_USER_cUF();
		} 
		catch (myException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * wird fuer dropdown-felder benoetigt,
	 * da dort immer formatierte ids stehen
	 */
	public static String get_ID_USER_FORMATTED()
	{
		try
		{
			return bibALL.get_RECORD_USER().get_ID_USER_cF();
		} 
		catch (myException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	
	public static String get_USERNAME()
	{
		try
		{
			return bibALL.get_RECORD_USER().get_NAME_cUF();
		} 
		catch (myException e)
		{
			e.printStackTrace();
			return null;
		}

	}

	public static String get_PASSWORD()
	{
		try
		{
			return bibALL.get_RECORD_USER().get_PASSWORT_cUF();
		} 
		catch (myException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	
	

	
	
	
	public static String get_ID_BASISWAEHRUNG()
	{
		try
		{
			return bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cUF();
		} 
		catch (myException e)
		{
			e.printStackTrace();
			return null;
		}

	}



	
	
	public static String get_ID_BASISWAEHRUNG_FORMATED()
	{
		try
		{
			return bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cF();
		} 
		catch (myException e)
		{
			e.printStackTrace();
			return null;
		}
	}


	public static String get_WAEHRUNG_BASISWAEHRUNG_KURZ()
	{
		try
		{
			return new RECORD_WAEHRUNG(bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cUF()).get_KURZBEZEICHNUNG_cUF_NN("<Währung>");
		} 
		catch (myException e)
		{
			e.printStackTrace();
			return null;
		}
	}


	
	
	
	
	public static String get_DBKENNUNG()
	{
	    String cRueck = null;
	    String ccRueck = ((String) getSessionValue("DBKENNUNG")).trim();
	
	    if (ccRueck != null)
	    {
	        cRueck = ccRueck;
	    }
	    return cRueck;
	}

	


	
	
	
	public static boolean get_bIST_SUPERVISOR()
	{
		try
		{
			return bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES();
		} 
		catch (myException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	

	/**
	 * 2016-06-27: martin
	 * @return
	 */
	public static boolean is_SUPERVISOR() {
		try {
			return bibALL.get_RECORD_USER().is_IST_SUPERVISOR_YES();
		}  catch (myException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 2016-06-27: martin
	 * @return
	 */
	public static boolean is_geschaeftsfuehrer() {
		try	{
			return new RECORD_USER_EXT(bibALL.get_RECORD_USER()).is_GESCHAEFTSFUEHRER_YES();
		} catch (myException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * 2016-06-27: martin
	 * @return
	 */
	public static boolean is_developer() {
		try	{
			return new RECORD_USER_EXT(bibALL.get_RECORD_USER()).is_DEVELOPER_YES();
		} catch (myException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public static String get_KUERZEL()
	{
		try
		{
			return bibALL.get_RECORD_USER().get_KUERZEL_cUF();
		} 
		catch (myException e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public static String get_WEBURL()
	{
	    String cRueck = null;
	    String ccRueck = ((String) getSessionValue("WEBNAME")).trim();
	
	    if (ccRueck != null)
	    {
	        cRueck = ccRueck;
	    }
	
	    return cRueck;
	}

	public static String get_JDBC_URL()
	{
		String cRueck = null;
		String ccRueck = ((String) getSessionValue("JDBC_STRING")).trim();
	
		if (ccRueck != null)
		{
			cRueck = ccRueck;
		}
	
		return cRueck;
	}

	public static String get_DATABASE_TABLEOWNER()
	{
		String cRueck = null;
		String ccRueck = ((String) getSessionValue("JAVA_LOGIN")).trim();
	
		if (ccRueck != null)
		{
			cRueck = ccRueck;
		}
	
		return cRueck;
	}

	public static String get_DATABASE_TABLEOWNER_PASSWORD()
	{
		String cRueck = null;
		String ccRueck = ((String) getSessionValue("JAVA_PASSWORT")).trim();
	
		if (ccRueck != null)
		{
			cRueck = ccRueck;
		}
	
		return cRueck;
	}

	public static String get_OUTPUTPATH()
	{
	    String cRueck = null;
	
	    if (bibALL.getSessionValue("OUTPUTPATH") != null)
	    {
	        cRueck = ((String) bibALL.getSessionValue("OUTPUTPATH")).trim();
	
	        if (!cRueck.equals(""))
	        {
	            // cRueck von den separatoren befreien
	            if (cRueck.substring(0, 1).equals(File.separator))
	            {
	                cRueck = cRueck.substring(1);
	            }
	
	            if (cRueck.substring(cRueck.length() - 1, cRueck.length()).equals(File.separator))
	            {
	                cRueck = cRueck.substring(0, cRueck.length() - 1);
	            }
	        }
	    }
	
	    return cRueck;
	}

	
	
	

	/**
	 * @return path ohne linke und rechte trenner
	 */
	public static String get_ARCHIVPATH()
	{
	    String cRueck = null;
	
	    if (getSessionValue("ARCHIVPATH") != null)
	    {
	        cRueck = ((String) getSessionValue("ARCHIVPATH")).trim();
	
	        if (!cRueck.equals(""))
	        {
	            // cRueck von den separatoren befreien
	            if (cRueck.substring(0, 1).equals(File.separator))
	            {
	                cRueck = cRueck.substring(1);
	            }
	
	            if (cRueck.substring(cRueck.length() - 1, cRueck.length()).equals(File.separator))
	            {
	                cRueck = cRueck.substring(0, cRueck.length() - 1);
	            }
	        }
	    }
	
	    return cRueck;
	}

	/**
	 * @liefert den basispfad des web ohne separatoren
	 */
	public static String get_WEBROOTPATH()
	{
	    String cRueck = null;
	
	    if (getSessionValue("WEBROOTPATH") != null)
	    {
	        cRueck = ((String) getSessionValue("WEBROOTPATH")).trim();
	
	        if (!cRueck.equals(""))
	        {
	            // cRueck von den separatoren befreien
	            if (cRueck.substring(0, 1).equals(File.separator))
	            {
	                cRueck = cRueck.substring(1);
	            }
	
	            if (cRueck.substring(cRueck.length() - 1, cRueck.length()).equals(File.separator))
	            {
	                cRueck = cRueck.substring(0, cRueck.length() - 1);
	            }
	        }
	    }
	
	    return cRueck;
	}


	
	public static int get_FONT_SIZE()
	{
		int iRueck = 12;
		
		try
		{
			iRueck = new Integer((String) getSessionValue("FONT_SIZE")).intValue();
		}
		catch (Exception ex) {}
		return iRueck;
	}

	
	
	
	/*
	 * erzeugt die url der ende-seite
	 */
	public static String get_ENDPAGE()
	{
		String cRueck = "";
		
		cRueck += ((String) getSessionValue("ENDPAGE")).trim();
	
		return cRueck;
	}

	public static String get_ADRESSZUSATZPATH()
	{
	    String cRueck = null;
	
	    if (getSessionValue("ADRESSZUSATZPATH") != null)
	    {
	        cRueck = ((String) getSessionValue("ADRESSZUSATZPATH")).trim();
	
	        if (!cRueck.equals(""))
	        {
	            // cRueck von den separatoren befreien
	            if (cRueck.substring(0, 1).equals(File.separator))
	            {
	                cRueck = cRueck.substring(1);
	            }
	
	            if (cRueck.substring(cRueck.length() - 1, cRueck.length()).equals(File.separator))
	            {
	                cRueck = cRueck.substring(0, cRueck.length() - 1);
	            }
	        }
	    }
	
	    return cRueck;
	}

	/*
	 * jeder mandant hat einen eigenen report-pfad
	 * wird nur als wort ohne path-separatoren angegeben
	 */
	public static String get_REPORTBASEPATH()
	{
		String cRueck = null;
	
		if (getSessionValue("REPORTBASEPATH") != null)
		{
			cRueck = ((String) getSessionValue("REPORTBASEPATH")).trim();
	
			if (!cRueck.equals(""))
			{
				// cRueck von den separatoren befreien
				if (cRueck.substring(0, 1).equals(File.separator))
				{
					cRueck = cRueck.substring(1);
				}
	
				if (cRueck.substring(cRueck.length() - 1, cRueck.length()).equals(File.separator))
				{
					cRueck = cRueck.substring(0, cRueck.length() - 1);
				}
			}
		}
	
		return cRueck;
	}



	/**
	 * 
	 * @return complete path to reportbase-path (/daten/..../reports/)  incl. fileSeparator 
	 */
	public static String get_ReportPathInFileSystem() {
		return File.separator + bibALL.get_WEBROOTPATH() + File.separator  + bibALL.get_REPORTBASEPATH()+ File.separator;
	}
	
	
	
//	/*
//	 * jeder mandant hat einen eigenen report-pfad (neue reports waehrend der umstellung)
//	 * wird nur als wort ohne pafd-separatoren angegeben
//	 */
//	public static String get_REPORTBASEPATH_NEU()
//	{
//		String cRueck = null;
//	
//		if (getSessionValue("REPORTBASEPATH") != null)
//		{
//			cRueck = ((String) getSessionValue("REPORTBASEPATH")).trim();
//	
//			if (!cRueck.equals(""))
//			{
//				// cRueck von den separatoren befreien
//				if (cRueck.substring(0, 1).equals(File.separator))
//				{
//					cRueck = cRueck.substring(1);
//				}
//	
//				if (cRueck.substring(cRueck.length() - 1, cRueck.length()).equals(File.separator))
//				{
//					cRueck = cRueck.substring(0, cRueck.length() - 1);
//				}
//				
//				cRueck += "_neu";
//			}
//		}
//	
//		return cRueck;
//	}

	
	/*
		  * liefert den gesamten namen eines mandanten
		  * ANREDE
	VORNAME
	NAME1
	NAME2
	NAME3
	STRASSE
	LANDKURZ
	PLZ
	ORT
		  */
		 public static String get_MANDANT_NAME(	boolean bANREDE, 
		         								boolean bVORNAME,
		         								boolean bNAME1, 
		         								boolean bNAME2, 
		         								boolean bNAME3, 
		         								boolean bSTRASSE, 
		         								boolean bLANDKURZ,
		         								boolean bPLZ,
		         								boolean bORT,
		         								String cTrenner
		         								)
		 {
			 
		     
		     String cRueck = "";
		     String cSQL_QUERY = "SELECT ANREDE, VORNAME,NAME1,NAME2,NAME3,STRASSE,LANDKURZ,PLZ,ORT FROM "+bibALL.get_TABLEOWNER()+".JD_MANDANT "+
		     					 " WHERE ID_MANDANT="+get_ID_MANDANT();
		     
		     String[][] cErgebnis = bibDB.EinzelAbfrageInArray(cSQL_QUERY,"");
		     
		     String ccTrenner = " ";
		     if (! null2leer(cTrenner).equals("")) ccTrenner = cTrenner;
		     
		     if (! (cErgebnis == null || cErgebnis.length != 1))
		     {
		         if (bANREDE && 	!cErgebnis[0][0].equals("")) cRueck += cErgebnis[0][0]+ccTrenner;
		         if (bVORNAME && 	!cErgebnis[0][1].equals("")) cRueck += cErgebnis[0][1]+ccTrenner;
	             if (bNAME1 && 		!cErgebnis[0][2].equals("")) cRueck += cErgebnis[0][2]+ccTrenner;
	             if (bNAME2 && 		!cErgebnis[0][3].equals("")) cRueck += cErgebnis[0][3]+ccTrenner;
	             if (bNAME3 && 		!cErgebnis[0][4].equals("")) cRueck += cErgebnis[0][4]+ccTrenner;
	             if (bSTRASSE && 	!cErgebnis[0][5].equals("")) cRueck += cErgebnis[0][5]+ccTrenner;
	             if (bLANDKURZ && 	!cErgebnis[0][6].equals("")) cRueck += cErgebnis[0][6]+ccTrenner;
	             if (bPLZ && 		!cErgebnis[0][7].equals("")) cRueck += cErgebnis[0][7]+ccTrenner;
	             if (bORT && 		!cErgebnis[0][8].equals("")) cRueck += cErgebnis[0][8]+ccTrenner;
		     }
		     
		     if (cRueck.length()>cTrenner.length())
		     {
		         cRueck = cRueck.substring(0,cRueck.length()-cTrenner.length());
		     }
		     
			 return  cRueck;
	
		 }

		 //2011-12-07: abfrage, ob es einen umdefinierten report-pfad gibt (um z.b. im datenbestand des mandanten 1 reports des mandenten 3 zu testen)
		/*
		 * falls die applikation in einem Testmodus ist, kann eine alternative mailadresse geladen werden, die 
		 * alle mails entgegennimmt
		 */
		public static String get_TEST_REPORT_MANDANT()
		{
			return (String)bibALL.getSessionValue("TEST-REPORT-MANDANT");
		}

		
		
		/**
		 * Schalter zum disablen der System.gc() Aufrufe im Programmablauf
		 * @author manfred
		 * @date   09.12.2011
		 * @return
		 */
		public static boolean get_DISABLE_EXPLICIT_GARBAGE_COLLECTION(){
			String sValue = (String)bibALL.getSessionValue("DISABLE_GARBAGE_COLLECTION");
			if (sValue != null && sValue.equalsIgnoreCase("Y")){
				return true;
			}
			return false;
		}
		

		
		
		/**
		  * liefert den gesamten report-pfad eines mandanten mit fileseparatoren
		  * 
		  */
		 public static String get_REPORTPATH_MANDANT()
		 {
			 if (S.isFull(bibALL.get_TEST_REPORT_MANDANT()))
			 {
				 return  File.separator+get_WEBROOTPATH()+
				 			File.separator+
				 			get_REPORTBASEPATH()+
				 			File.separator+
				 			bibALL.get_TEST_REPORT_MANDANT().trim()+
				 			File.separator;
			 }
			 else
			 {
				 return  File.separator+get_WEBROOTPATH()+
				 			File.separator+
				 			get_REPORTBASEPATH()+
				 			File.separator+
				 			get_ID_MANDANT().trim()+
				 			File.separator;
				 
			 }
		 }


		 public static String get_REPORTPATH_ADDONS_MANDANT()
		 {
			 if (S.isFull(bibALL.get_TEST_REPORT_MANDANT()))
			 {
				 return  File.separator+get_WEBROOTPATH()+
				 			File.separator+
				 			get_REPORTBASEPATH()+
				 			File.separator+
				 			bibALL.get_TEST_REPORT_MANDANT().trim()+
				 			File.separator+
				 			"ADDONS"+
				 			File.separator;
			 }
			 else
			 {
				 return  File.separator+get_WEBROOTPATH()+
				 			File.separator+
				 			get_REPORTBASEPATH()+
				 			File.separator+
				 			get_ID_MANDANT().trim()+
				 			File.separator+
				 			"ADDONS"+
				 			File.separator;
			 }

		 }


		 public static String get_REPORTPATH_IMAGES_MANDANT()
		 {
			 if (S.isFull(bibALL.get_TEST_REPORT_MANDANT()))
			 {
				 return  File.separator+get_WEBROOTPATH()+
					 			File.separator+
					 			get_REPORTBASEPATH()+
					 			File.separator+
					 			bibALL.get_TEST_REPORT_MANDANT().trim()+
					 			File.separator+
					 			"IMAGES"+
					 			File.separator;
			 }
			 else
			 {
				 return  File.separator+get_WEBROOTPATH()+
					 			File.separator+
					 			get_REPORTBASEPATH()+
					 			File.separator+
					 			get_ID_MANDANT().trim()+
					 			File.separator+
					 			"IMAGES"+
					 			File.separator;
			 }
		 }


			/**
		  * liefert den gesamten report-pfad zu den Listen-Reports eines mandanten mit fileseparatoren
		  * 
		  */
		 public static String get_REPORTPATH_LISTEN_MANDANT()
		 {
			 if (S.isFull(bibALL.get_TEST_REPORT_MANDANT()))
			 {
				 return  File.separator+get_WEBROOTPATH()+
				 			File.separator+
				 			get_REPORTBASEPATH()+
				 			File.separator+
				 			bibALL.get_TEST_REPORT_MANDANT().trim()+
				 			File.separator+
				 			"LISTEN"+
				 			File.separator;
			 }
			 else
			 {
				 return  File.separator+get_WEBROOTPATH()+
				 			File.separator+
				 			get_REPORTBASEPATH()+
				 			File.separator+
				 			get_ID_MANDANT().trim()+
				 			File.separator+
				 			"LISTEN"+
				 			File.separator;
			 }		
		 }


		 
		 
		 

		 
			/**
		  * liefert den gesamten report-pfad zu den Listen-Reports eines mandanten mit fileseparatoren
		  * 
		  */
		 public static String get_REPORTPATH_BASE()
		 {
			 return  File.separator+get_WEBROOTPATH()+
			 			File.separator+
			 			get_REPORTBASEPATH()+
			 			File.separator;
		
		 }
		 
		 
		 
		 
		 
		 /**
		  * liefert den gesamten report-pfad zu den Listen-Reports eines mandanten mit fileseparatoren
		  * 
		  */
		 public static String get_REPORTPATH_LISTEN_ALLE()
		 {
			 return  File.separator+get_WEBROOTPATH()+
			 			File.separator+
			 			get_REPORTBASEPATH()+
			 			File.separator+
					   "ALLE"+
			 			File.separator+
			 			"LISTEN"+
			 			File.separator;
		
		 }

 
		 
		 
		/**
		 * liefert den allgemeinen report-pfad mit fileseparatoren
		 * 
		 */
		public static String get_REPORTPATH_ALLE()
		{
			return  File.separator+get_WEBROOTPATH()+
					   File.separator+
					   get_REPORTBASEPATH()+
					   File.separator+
					   "ALLE"+
					   File.separator;
		
		}


		
		 public static String get_REPORTPATH_ADDONS_ALLE()
		 {
			 return  File.separator+get_WEBROOTPATH()+
			 			File.separator+
			 			get_REPORTBASEPATH()+
			 			File.separator+
			 			"ALLE"+
			 			File.separator+
			 			"ADDONS"+
			 			File.separator;
		 }


		 public static String get_REPORTPATH_IMAGES_ALLE()
		 {
			 return  File.separator+get_WEBROOTPATH()+
			 			File.separator+
			 			get_REPORTBASEPATH()+
			 			File.separator+
			 			"ALLE"+
			 			File.separator+
			 			"IMAGES"+
			 			File.separator;
		 }


		
		
		
		/**
		 * @param cXmlFileName (ohne endung)
		 * @return path with filename or null
		 * methode, die den pfad zu den xml-definitionsdateien sucht,
		 * zuerst im mandanteneigenen, dann in ALLE
		 */
		public static String get_XML_LISTDEF_PATH(String cXmlFileName)
		{
			String cRueck = null;
			
			String cWebRootPath = bibALL.get_WEBROOTPATH();
			String cDirNameXML = (String)bibALL.getSessionValue("XMLDEFPATH");
			String cMandant 	= bibALL.get_ID_MANDANT();
			
			if (!bibALL.isEmpty(cWebRootPath) && !bibALL.isEmpty(cDirNameXML) && !bibALL.isEmpty(cMandant))
			{
				String cTestHome = File.separator+cWebRootPath+File.separator+cDirNameXML+File.separator+cMandant+File.separator+cXmlFileName+".xml";
				String cTestAll = File.separator+cWebRootPath+File.separator+cDirNameXML+File.separator+"ALLE"+File.separator+cXmlFileName+".xml";

				if (new File(cTestHome).exists())
				{
					cRueck = cTestHome;
				}
				else
				{
					if (new File(cTestAll).exists())
					{
						cRueck=cTestAll;
					}
				}
			}
			return cRueck;
		}
		

		
		/**
		 * 
		 * @return s Pfad zu den XML-Defs fuer aktiven mandanten (vorn und hinten mit separatoren)
		 */
		public static String get_XML_PATH_MANDANT()
		{
			
			String cWebRootPath = bibALL.get_WEBROOTPATH();
			String cDirNameXML = (String)bibALL.getSessionValue("XMLDEFPATH");
			String cMandant 	= bibALL.get_ID_MANDANT();
			
			return File.separator+cWebRootPath+File.separator+cDirNameXML+File.separator+cMandant+File.separator;
		}

		/**
		 * 
		 * @return s Pfad zu den XML-Defs fuer ALLE Mandanten (vorn und hinten mit separatoren)
		 */
		public static String get_XML_PATH_ALL()
		{
			String cWebRootPath = bibALL.get_WEBROOTPATH();
			String cDirNameXML = (String)bibALL.getSessionValue("XMLDEFPATH");
			
			return File.separator+cWebRootPath+File.separator+cDirNameXML+File.separator+"ALLE"+File.separator;

		}
	
		
		
		
		public static String get_TABLEOWNER()
		{
		    String cRueck = null;
		    String ccRueck = (String) getSessionValue("JAVA_LOGIN");
		
		    if (ccRueck != null)
		    {
		        cRueck = ccRueck.trim();
		    }
		
		    return cRueck;
		}

		/**
		 * liefert den gesamten report-pfad zu einem reportnamen, sucht zuerst im pfad des mandanten
		 * dann im pfad für alle  
		 * übergabe des reinen pfade-namens, mit abschliessenden \ 
		 *  !!! es wird nicht der komplette dateiname übergeben
		 */
		public static String get_REPORTPATH(String cReportName)
		{
			
			String cReportPath = null;
			String ccReportName = cReportName;
			
			/*
			 * prüfe, ob test.jasper dabeisteht, wenn ja, weg
			 */
			if (ccReportName.length()>7){
				if (ccReportName.substring(ccReportName.length()-7).toUpperCase().equals(".JASPER"))
				{
					ccReportName = ccReportName.substring(0,ccReportName.length()-7);
				}
			}
			
			if ((new File(get_REPORTPATH_MANDANT()+ccReportName+".jasper")).exists())
			{
				cReportPath = get_REPORTPATH_MANDANT();
				
			}
			else if ((new File(get_REPORTPATH_ALLE()+ccReportName+".jasper")).exists())
			{
				cReportPath = get_REPORTPATH_ALLE();
			}
			else
			{
				cReportPath = null; 
			}
			
			return  cReportPath;
		
		}

		/**
		 * liefert den gesamten report-pfad zu einem reportnamen, sucht zuerst im pfad des mandanten
		 * dann im pfad für alle  
		 *  @param cReportNameWithoutPath = dateiname ohne pfad und endung
		 */
		public static String get_REPORTNAME_AND_PATH(String cReportNameWithoutPath)
		{
			
			String cReportPath = null;
			String ccReportName = cReportNameWithoutPath;
			
			/*
			 * prüfe, ob test.jasper dabeisteht, wenn ja, weg
			 */
			if (ccReportName.length()>7){
				if (ccReportName.substring(ccReportName.length()-7).toUpperCase().equals(".JASPER"))
				{
					ccReportName = ccReportName.substring(0,ccReportName.length()-7);
				}
			}
			
			
			if ((new File(get_REPORTPATH_MANDANT()+ccReportName+".jasper")).exists())
			{
				cReportPath = get_REPORTPATH_MANDANT()+ccReportName+".jasper";
			}
			else if ((new File(get_REPORTPATH_ALLE()+ccReportName+".jasper")).exists())
			{
				cReportPath = get_REPORTPATH_ALLE()+ccReportName+".jasper";
			}
			else
			{
				cReportPath = null; 
			}
			
			return  cReportPath;
		
		}


		
		
		

		public static String get_SESSIONID(Integer maxLen)
		{
		    String cRueck = null;
		    cRueck = ((String) getSessionValue("SESSIONID")).trim();
		
		    if (maxLen != null && maxLen.intValue()>0)
		    	if (cRueck.length()>maxLen.intValue())
		    		cRueck = cRueck.substring(0,maxLen.intValue());
		    		
		    
		    return cRueck;
		}

		public static String get_TEMPPATH()
		{
		    String cRueck = null;
		    cRueck = ((String) getSessionValue("TEMPPATH")).trim();
		
		    return cRueck;
		}


		public static String get_TEMPPATH_COMPLETE()
		{
		    String cRueck = null;
		    
		    cRueck =  File.separator+get_WEBROOTPATH()+
		    			File.separator+
		    				((String) getSessionValue("TEMPPATH")).trim();
		
		    return cRueck;
		}

		
		
		/**
		 * @return pfad im Temp-Path mit angehaengtem uvz-gebildet aus timestamp 
		 */
		public static String get_TEMPPATH_COMPLETE_WITH_TIMESTAMP()
		{
		    String cRueck = null;
		    
		    cRueck =  File.separator+get_WEBROOTPATH()+
		    			File.separator+
		    				((String) getSessionValue("TEMPPATH")).trim();
		
			String cHelp = bibALL.get_cDateTimeNOW();
			cHelp = bibALL.ReplaceTeilString(cHelp,":","");
			cHelp = bibALL.ReplaceTeilString(cHelp," ","");
			cHelp = bibALL.ReplaceTeilString(cHelp,".","");
			cHelp = bibALL.ReplaceTeilString(cHelp,"-","");
			
		    return cRueck+File.separator+cHelp;
		}

		

		
		/*
		 * oracle-zugangscodes
		 */
		public static String get_oracle_classname()
		{
		    String cRueck = null;
		    cRueck = ((String) getSessionValue("ORACLE_CLASSNAME")).trim();
		    return cRueck;
		}
		
		public static String get_oracle_jdbcstring()
		{
		    String cRueck = null;
		    cRueck = ((String) getSessionValue("ORACLE_JDBCSTRING")).trim();
		    return cRueck;
		}

		public static String get_oracle_login()
		{
		    String cRueck = null;
		    cRueck = ((String) getSessionValue("ORACLE_LOGIN")).trim();
		    return cRueck;
		}
	
		public static String get_oracle_password()
		{
		    String cRueck = null;
		    cRueck = ((String) getSessionValue("ORACLE_PASSWORD")).trim();
		    return cRueck;
		}

		public static String get_oracle_tableowner()
		{
		    String cRueck = null;
		    cRueck = ((String) getSessionValue("ORACLE_TABLEOWNER")).trim();
		    return cRueck;
		}
		
		
		
		public static String get_CompleteOutPutPath(boolean bSeparatorAmEnde)
		{
		    String cRueck = null;
		
		    String cWebRootPath = ((String) getSessionValue("WEBROOTPATH")).trim();
		    String cOutPutPath = ((String) getSessionValue("OUTPUTPATH")).trim();
		
		    if ((cWebRootPath.length() != 0) && // in beiden muss was drinstehen
		            (cOutPutPath.length() != 0))
		    {
		        if (!cWebRootPath.substring(0, 1).equals(File.separator))
		        {
		            cWebRootPath = File.separator + cWebRootPath;
		        }
		
		        if (!cWebRootPath.substring(cWebRootPath.length() - 1, cWebRootPath.length()).equals(File.separator))
		        {
		            cWebRootPath = cWebRootPath + File.separator;
		        }
		
		        // cOutputpath von den separatoren befreien
		        if (cOutPutPath.substring(0, 1).equals(File.separator))
		        {
		            cOutPutPath = cOutPutPath.substring(1);
		        }
		
		        if (cOutPutPath.substring(cOutPutPath.length() - 1, cOutPutPath.length()).equals(File.separator))
		        {
		            cOutPutPath = cOutPutPath.substring(0, cOutPutPath.length() - 1);
		        }
		
		        cRueck = cWebRootPath + cOutPutPath;
		
		        if (bSeparatorAmEnde)
		        {
		            cRueck += File.separator;
		        }
		    }
		
		    return cRueck;
		}

		
		

		
		
		/**
		 * @param bSeparatorAmEnde
		 * @return s the complete, mandantspecific protokoll-path of the system
		 * @throws myException
		 */
		public static String get_CompleteProtokollPath(boolean bSeparatorAmEnde) throws myException
		{
		    String cRueck = null;
		
		    String cWebRootPath = ((String) getSessionValue("WEBROOTPATH")).trim();
		    String cProtokollPath = ((String) getSessionValue("PROTOKOLLPATH")).trim();
		
		    if ((cWebRootPath.length() != 0) && // in beiden muss was drinstehen
		            (cProtokollPath.length() != 0))
		    {
		        if (!cWebRootPath.substring(0, 1).equals(File.separator))
		            cWebRootPath = File.separator + cWebRootPath;
		
		        if (!cWebRootPath.substring(cWebRootPath.length() - 1, cWebRootPath.length()).equals(File.separator))
		            cWebRootPath = cWebRootPath + File.separator;
		
		        // cOutputpath von den separatoren befreien
		        if (cProtokollPath.substring(0, 1).equals(File.separator))
		            cProtokollPath = cProtokollPath.substring(1);
		
		        if (cProtokollPath.substring(cProtokollPath.length() - 1, cProtokollPath.length()).equals(File.separator))
		            cProtokollPath = cProtokollPath.substring(0, cProtokollPath.length() - 1);

		        
		        cRueck = cWebRootPath + cProtokollPath;
		
		        
		        /*
		         * jetzt noch die mandantennummer anhaengen
		         */
		        String cString_Mandant = "MANDANT_"+bibALL.fillString(bibALL.get_ID_MANDANT(),10,'0',true);
		        
		        cRueck = cRueck+File.separator+cString_Mandant;
		        
		        File oFile = new File(cRueck);
		        
		        if (oFile.exists())
		            if (!oFile.isDirectory())
		                throw new myException("Archiver:buildPathInArchivPath:"+cRueck+" exists, but NO DIRECTORY !!!");
		            
		       if (!oFile.exists())
		           if (!oFile.mkdirs())
		               throw new myException("Archiver:buildPathInArchivPath:"+cRueck+" not existing, but CANNOT CREATE !!!");

		        if (bSeparatorAmEnde)
		        {
		            cRueck += File.separator;
		        }
		    }
		
		    return cRueck;
		}


		
		
		
		
		
		
		public static String get_CompleteTempPath(boolean bSeparatorAmEnde)
		{
			String cRueck = null;
		
			String cWebRootPath = ((String) getSessionValue("WEBROOTPATH")).trim();
			String cTempPath = ((String) getSessionValue("TEMPPATH")).trim();
		
			if ((cWebRootPath.length() != 0) && // in beiden muss was drinstehen
					(cTempPath.length() != 0))
			{
				if (!cWebRootPath.substring(0, 1).equals(File.separator))
				{
					cWebRootPath = File.separator + cWebRootPath;
				}
		
				if (!cWebRootPath.substring(cWebRootPath.length() - 1, cWebRootPath.length()).equals(File.separator))
				{
					cWebRootPath = cWebRootPath + File.separator;
				}
		
				// cOutputpath von den separatoren befreien
				if (cTempPath.substring(0, 1).equals(File.separator))
				{
					cTempPath = cTempPath.substring(1);
				}
		
				if (cTempPath.substring(cTempPath.length() - 1, cTempPath.length()).equals(File.separator))
				{
					cTempPath = cTempPath.substring(0, cTempPath.length() - 1);
				}
		
				cRueck = cWebRootPath + cTempPath;
		
				if (bSeparatorAmEnde)
				{
					cRueck += File.separator;
				}
			}
		
			return cRueck;
		}



		public static String get_cMAILSERVER_FOR_SMTP_RELAYING()
		{
			try
			{
				return bibALL.get_RECORD_MANDANT().get_MAILSERVER_cUF();
			} 
			catch (myException e)
			{
				e.printStackTrace();
				return null;
			}
		}

		public static String get_cMAILUSERNAME_FOR_SMTP_RELAYING()
		{
			try
			{
				return bibALL.get_RECORD_MANDANT().get_MAILUSERNAME_cUF();
			} 
			catch (myException e)
			{
				e.printStackTrace();
				return null;
			}
		}

		public static String get_cMAILPASSWORD_FOR_SMTP_RELAYING()
		{
			try
			{
				return bibALL.get_RECORD_MANDANT().get_MAILPASSWORD_cUF();
			} 
			catch (myException e)
			{
				e.printStackTrace();
				return null;
			}
		}

		public static ServletContext get_Context()
		{
		    return (ServletContext) getSessionValue("CONTEXT");
		}

		// in dieses logfile werden benutzerfehlerneldungen reingeschrieben
		public static boolean WriteLogInfoLine(String cMessage)
		{
			DEBUG.System_println(cMessage, "");
		    return true;
		}

		// spezielle variante der fehlerprotokollierung
		// protokoll eines errorhandlers
		public static boolean WriteLogEceptionInfo(Exception e)
		{
			e.printStackTrace();
			return true;
		}

	
		public static boolean WriteLogExceptionTrace(Exception e)
		{
			e.printStackTrace();
		    return true;
		}



		
		/**
		 * Schalter zum lesen des Debug-Flags: 
		 * 	DEBUG_FLAG_SQL_EXEC = 		"SQL_EXEC";
		 *	DEBUG_FLAG_SQL_QUERY = 		"SQL_QUERYS";
		 *	DEBUG_FLAG_SQL_ERROR = 		"SQL_ERROR";
		 *	DEBUG_FLAG_SQL_TIMESTAMP = 	"DEBUG_FLAG_SQL_TIMESTAMP";
		 *	DEBUG_FLAG_DIVERS1 =	"DIVERS1";
		 *	DEBUG_FLAG_DIVERS2 =	"DIVERS2";
		 *	DEBUG_FLAG_DIVERS3 =	"DIVERS3";
		 * 
		 * @author manfred
		 * @date   19.01.2012
		 * @return
		 */
		public static String get_DEBUG_FLAGS(){
			return S.NN((String)bibALL.getSessionValue(bibSES_ENUM.DEBUG_FLAGS.name()));
		}

		
		public static void set_DEBUG_FLAGS(String flags){
			bibALL.setSessionValue(bibSES_ENUM.DEBUG_FLAGS.name(), flags);
		}


		
		/**
		 * Aufbau eines verbundenen String aus den strings eines vectors, anfang und
		 * ende enthalten keinen trenner, dazwischen ist der trenner
		 * @param vStrings
		 * @param cSeparator
		 * @param cValueWhenEmpty 
		 * @return
		 */
		public static String Concatenate(Vector<String> vStrings, String cSeparator, String cValueWhenEmpty) throws myException
		{
			String cRueck = null;
			
			if (vStrings != null && !bibALL.isEmpty(cSeparator))
			{
				cRueck = "";
				for (String cString:vStrings)
				{
					cRueck = cRueck + cSeparator+cString;
				}
			}
			else
				throw new myException("bibALL:Concatenate:emtpy vector or separator not allowed !!");

			if (cRueck.length()>0)
				cRueck = cRueck.substring(cSeparator.length());               // erster trenner weg
			
			if (cRueck != null)
				if (cRueck.trim().equals(""))
					if (!bibALL.isEmpty(cValueWhenEmpty))
						cRueck = cValueWhenEmpty;
			
			return cRueck;
		}
		
		
		
		
		/**
		 * Aufbau eines verbundenen String aus den strings eines vectors, anfang und
		 * ende enthalten keinen trenner, dazwischen ist der trenner
		 * @param vStrings
		 * @param cSeparator
		 * @param cValueWhenEmpty
		 * @return
		 */
		public static String ConcatenateWithoutException(Vector<String> vStrings, String cSeparator, String cValueWhenEmpty)
		{
			String cRueck = null;
			
			if (vStrings != null && !bibALL.isEmpty(cSeparator))
			{
				cRueck = "";
				for (String cString:vStrings)
				{
					cRueck = cRueck + cSeparator+cString;
				}
			}

			if (cRueck != null && cRueck.length()>0)
				cRueck = cRueck.substring(cSeparator.length());               // erster trenner weg
			
			if (cRueck != null)
				if (cRueck.trim().equals(""))
					if (!bibALL.isEmpty(cValueWhenEmpty))
						cRueck = cValueWhenEmpty;

			
			return cRueck;
		}
		



		
		
		public static String ChangeUmlaute_CleanString(String cSource)
		{
			String cRueck = bibALL.ChangeUmlaute(cSource);
			return bibALL.CleanStringKeepEscapeCodes(cRueck);
		}
		
		
		
		/**
		 * @param cSource
		 * @return
		 */
		public static String ChangeUmlaute(String cSource)
		{
			String cRueck = "";

			StringBuffer oSammler = new StringBuffer();
			for (int i=0;i<cSource.length();i++)
			{
				String cTest = cSource.substring(i,i+1);
				
				if 		(cTest.equals("ä"))
					oSammler.append("ae");
				else if (cTest.equals("ö"))
					oSammler.append("oe");
				else if (cTest.equals("ü"))
					oSammler.append("ue");
				else if (cTest.equals("Ä"))
					oSammler.append("Ae");
				else if (cTest.equals("Ö"))
					oSammler.append("Oe");
				else if (cTest.equals("Ü"))
					oSammler.append("Ue");
				else if (cTest.equals("ß"))
					oSammler.append("ss");
				else
					oSammler.append(cTest);
				
			}
			cRueck = oSammler.toString();
			
			return cRueck;
		}
		
		
		/**
		 * @param cSource
		 * @return s String with only standard-signes
		 */
		public static String CleanStringKeepEscapeCodes(String cSource)
		{
			String cRueck = cSource;
			if (cRueck != null)
			{
				String Compare = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZöäüÖÄÜß?1234567890 !/\"\\(){}[]+-_*#'.,;:<>=~@¤\n\t\f\r";
				
				StringBuffer oSammler = new StringBuffer();
				for (int i=0;i<cSource.length();i++)
				{
					String cTest = cSource.substring(i,i+1);
					if (Compare.indexOf(cTest)>=0)
						oSammler.append(cTest);
				}
				cRueck = oSammler.toString();
			}

			return cRueck;
		}


		
		
		
		
		/**
		 * @param cSource
		 * @return s String with only standard-signes
		 */
		public static String CleanString(String cSource)
		{
			String cRueck = cSource;
			if (cRueck != null)
			{
				String Compare = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZöäüÖÄÜß?1234567890 !/\"\\(){}[]+-_*#'.,;:<>=~@¤";
				
				StringBuffer oSammler = new StringBuffer();
				for (int i=0;i<cSource.length();i++)
				{
					String cTest = cSource.substring(i,i+1);
					if (Compare.indexOf(cTest)>=0)
						oSammler.append(cTest);
				}
				cRueck = oSammler.toString();
			}

			return cRueck;
		}


		/**
		 * @param cSource
		 * @return s String with only standard-signes
		 */
		public static String CleanString4Filename(String cSource)
		{
			String cRueck = cSource;
			if (cRueck != null)
			{
				String Compare = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-.";
				
				StringBuffer oSammler = new StringBuffer();
				for (int i=0;i<cSource.length();i++)
				{
					String cTest = cSource.substring(i,i+1);
					if (Compare.indexOf(cTest)>=0)
					{
						oSammler.append(cTest);
					}
					else
					{
						oSammler.append("_");
					}
				}
				cRueck = oSammler.toString();
			}

			return cRueck;
		}


		/**
		 * 2014-03-26: neue version mit zahlen
		 * @param cSource
		 * @return s String with only standard-signes
		 */
		public static String CleanString4Filename_NG(String cSource)
		{
			String cRueck = cSource;
			if (cRueck != null)
			{
				String Compare = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_-.1234567890";
				
				StringBuffer oSammler = new StringBuffer();
				for (int i=0;i<cSource.length();i++)
				{
					String cTest = cSource.substring(i,i+1);
					if (Compare.indexOf(cTest)>=0)
					{
						oSammler.append(cTest);
					}
					else
					{
						oSammler.append("_");
					}
				}
				cRueck = oSammler.toString();
			}

			return cRueck;
		}


		/**
		 * 2014-03-26: neue version mit zahlen
		 * @param cSource
		 * @return s String with only standard-signes
		 */
		public static String CleanString4DBName_NG(String cSource)
		{
			String cRueck = cSource;
			if (cRueck != null)
			{
				String Compare = "ABCDEFGHIJKLMNOPQRSTUVWXYZ_1234567890";
				
				StringBuffer oSammler = new StringBuffer();
				for (int i=0;i<cSource.length();i++)
				{
					String cTest = cSource.substring(i,i+1);
					if (Compare.indexOf(cTest)>=0)
					{
						oSammler.append(cTest);
					}
					else
					{
						oSammler.append("_");
					}
				}
				cRueck = oSammler.toString();
			}

			return cRueck;
		}

		
		
		
		public static String get_EMAIL_SIGNATUR()
		{
			String cRueck = "";
			MyDBToolBox oDB = bibALL.get_myDBToolBox();
			cRueck = oDB.EinzelAbfrage("SELECT   MAIL_SIGNATUR FROM "+bibALL.get_TABLEOWNER()+".JD_USER WHERE ID_USER="+bibALL.get_ID_USER(),"","","");
			bibALL.destroy_myDBToolBox(oDB);
			return cRueck;
		}
		
		
		
		
		public static Vector<String> get_Vector(String cFirstElement)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(cFirstElement);
			return vRueck;
		}
		
		public static Vector<String> get_Vector(String cElement1,String cElement2)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			return vRueck;
		}
	
		public static Vector<String> get_Vector(String cElement1,String cElement2,String cElement3)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			return vRueck;
		}
		

		
		public static Vector<String> get_Vector(String cElement1,String cElement2,String cElement3,String cElement4)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			return vRueck;
		}
		public static Vector<String> get_Vector(String cElement1,String cElement2,String cElement3,String cElement4,String cElement5)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			return vRueck;
		}
		
		public static Vector<String> get_Vector(String cElement1,String cElement2,String cElement3,String cElement4,String cElement5,String cElement6)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			if (cElement6 != null) vRueck.add(cElement6);
			return vRueck;
		}

		
		
		public static Vector<Object> get_Vector(Object cFirstElement)
		{
			Vector<Object> vRueck = new Vector<Object>();
			vRueck.add(cFirstElement);
			return vRueck;
		}
		
		public static Vector<Object> get_Vector(Object cElement1,Object cElement2,Object cElement3,Object cElement4)
		{
			Vector<Object> vRueck = new Vector<Object>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			return vRueck;
		}
		
		
		
		public static Vector<Object> get_Vector(Object cElement1,Object cElement2,Object cElement3,Object cElement4,Object cElement5,Object cElement6,Object cElement7,Object cElement8)
		{
			Vector<Object> vRueck = new Vector<Object>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			if (cElement6 != null) vRueck.add(cElement6);
			if (cElement7 != null) vRueck.add(cElement7);
			if (cElement8 != null) vRueck.add(cElement8);
			return vRueck;
		}
		

		public static Vector<String> get_Vector(String cElement1,String cElement2,String cElement3,String cElement4,String cElement5,String cElement6,String cElement7,String cElement8)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			if (cElement6 != null) vRueck.add(cElement6);
			if (cElement7 != null) vRueck.add(cElement7);
			if (cElement8 != null) vRueck.add(cElement8);
			return vRueck;
		}


		public static Vector<String> get_Vector(String cElement1,String cElement2,String cElement3,String cElement4,String cElement5,String cElement6,String cElement7,String cElement8,String cElement9)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			if (cElement6 != null) vRueck.add(cElement6);
			if (cElement7 != null) vRueck.add(cElement7);
			if (cElement8 != null) vRueck.add(cElement8);
			if (cElement9 != null) vRueck.add(cElement9);
			
			return vRueck;
		}

		public static Vector<String> get_Vector(		String cElement1,String cElement2,String cElement3,String cElement4,String cElement5,
														String cElement6,String cElement7,	String cElement8,	String cElement9, String cElement10)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			if (cElement6 != null) vRueck.add(cElement6);
			if (cElement7 != null) vRueck.add(cElement7);
			if (cElement8 != null) vRueck.add(cElement8);
			if (cElement9 != null) vRueck.add(cElement9);
			if (cElement10 != null) vRueck.add(cElement10);
			
			return vRueck;
		}


		
		public static Vector<String> get_Vector(		String cElement1,String cElement2,String cElement3,String cElement4,String cElement5,
														String cElement6,String cElement7,	String cElement8,	String cElement9, String cElement10, String cElement11)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			if (cElement6 != null) vRueck.add(cElement6);
			if (cElement7 != null) vRueck.add(cElement7);
			if (cElement8 != null) vRueck.add(cElement8);
			if (cElement9 != null) vRueck.add(cElement9);
			if (cElement10 != null) vRueck.add(cElement10);
			if (cElement11 != null) vRueck.add(cElement11);
			
			return vRueck;
		}

		
		public static Vector<String> get_Vector(		String cElement1,String cElement2,String cElement3,String cElement4,String cElement5,
														String cElement6,String cElement7,	String cElement8,	String cElement9, String cElement10, String cElement11,
														String cElement12, String cElement13)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			if (cElement6 != null) vRueck.add(cElement6);
			if (cElement7 != null) vRueck.add(cElement7);
			if (cElement8 != null) vRueck.add(cElement8);
			if (cElement9 != null) vRueck.add(cElement9);
			if (cElement10 != null) vRueck.add(cElement10);
			if (cElement11 != null) vRueck.add(cElement11);
			if (cElement12 != null) vRueck.add(cElement12);
			if (cElement13 != null) vRueck.add(cElement13);
			
			return vRueck;
		}

		
		public static Vector<String> get_Vector( 	String cElement1,	String cElement2,	String cElement3,	String cElement4,	String cElement5,
													String cElement6,	String cElement7,	String cElement8,	String cElement9, 	String cElement10, 
													String cElement11,	String cElement12, 	String cElement13, 	String cElement14)
		{
			Vector<String> vRueck = new Vector<String>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			if (cElement6 != null) vRueck.add(cElement6);
			if (cElement7 != null) vRueck.add(cElement7);
			if (cElement8 != null) vRueck.add(cElement8);
			if (cElement9 != null) vRueck.add(cElement9);
			if (cElement10 != null) vRueck.add(cElement10);
			if (cElement11 != null) vRueck.add(cElement11);
			if (cElement12 != null) vRueck.add(cElement12);
			if (cElement13 != null) vRueck.add(cElement13);
			if (cElement14 != null) vRueck.add(cElement14);
			
			return vRueck;
		}

		
		//////////////// -------------
		
		
		public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4,MyString cElement5,MyString cElement6,MyString cElement7,MyString cElement8,MyString cElement9)
		{
			Vector<MyString> vRueck = new Vector<MyString>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			if (cElement6 != null) vRueck.add(cElement6);
			if (cElement7 != null) vRueck.add(cElement7);
			if (cElement8 != null) vRueck.add(cElement8);
			if (cElement9 != null) vRueck.add(cElement9);
			
			return vRueck;
		}

		public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4,MyString cElement5,MyString cElement6,MyString cElement7,MyString cElement8)
		{
			Vector<MyString> vRueck = new Vector<MyString>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			if (cElement6 != null) vRueck.add(cElement6);
			if (cElement7 != null) vRueck.add(cElement7);
			if (cElement8 != null) vRueck.add(cElement8);
			
			return vRueck;
		}

		public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4,MyString cElement5,MyString cElement6,MyString cElement7)
		{
			Vector<MyString> vRueck = new Vector<MyString>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			if (cElement6 != null) vRueck.add(cElement6);
			if (cElement7 != null) vRueck.add(cElement7);
			
			return vRueck;
		}

		public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4,MyString cElement5,MyString cElement6)
		{
			Vector<MyString> vRueck = new Vector<MyString>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			if (cElement6 != null) vRueck.add(cElement6);
			
			return vRueck;
		}

		public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4,MyString cElement5)
		{
			Vector<MyString> vRueck = new Vector<MyString>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			if (cElement5 != null) vRueck.add(cElement5);
			
			return vRueck;
		}


		public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3,MyString cElement4)
		{
			Vector<MyString> vRueck = new Vector<MyString>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			
			return vRueck;
		}


		public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2,MyString cElement3)
		{
			Vector<MyString> vRueck = new Vector<MyString>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			
			return vRueck;
		}


		public static Vector<MyString> get_Vector(MyString cElement1,MyString cElement2)
		{
			Vector<MyString> vRueck = new Vector<MyString>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			return vRueck;
		}


		public static Vector<MyString> get_Vector(MyString cElement1)
		{
			Vector<MyString> vRueck = new Vector<MyString>();
			vRueck.add(cElement1);
			return vRueck;
		}

		
		
		
		public static Vector<Component> get_Vector(Component cElement1,Component cElement2,Component cElement3,Component cElement4)
		{
			Vector<Component> vRueck = new Vector<Component>();
			vRueck.add(cElement1);
			if (cElement2 != null) vRueck.add(cElement2);
			if (cElement3 != null) vRueck.add(cElement3);
			if (cElement4 != null) vRueck.add(cElement4);
			return vRueck;
		}
		

		
//		public static Vector> get_Vector(Component cElement1,Component cElement2,Component cElement3,Component cElement4)
//		{
//			Vector<Component> vRueck = new Vector<Component>();
//			vRueck.add(cElement1);
//			if (cElement2 != null) vRueck.add(cElement2);
//			if (cElement3 != null) vRueck.add(cElement3);
//			if (cElement4 != null) vRueck.add(cElement4);
//			return vRueck;
//		}
//
//		
		
		/*
		 * hilfsfunktion fuer dropdown-felder, um ein leerer paar am Anfang eines Anzeige/daten-Arrays zu platzieren
		 */
		public static String[][] add_EmptyPairInFrontOfArray(String[][] cArray, String cVisibleSignForEmpty) throws myException
		{
			if (cArray == null)
				throw new myException("bibALL:add_EmptyPairInFrontOfArray: NULL-Array not allowed !!");

			if (cArray.length==0)
				throw new myException("bibALL:add_EmptyPairInFrontOfArray: Empty-Array not allowed !!");

			if (cArray[0].length != 2)
				throw new myException("bibALL:add_EmptyPairInFrontOfArray: Second dimension must be 2 !!");
					
			
			String[][] cRueck = new String[cArray.length+1][2];

			cRueck[0][0]=cVisibleSignForEmpty;
			cRueck[0][1]="";
			for (int i=0;i<cArray.length;i++)
			{
				cRueck[i+1][0]=cArray[i][0];
				cRueck[i+1][1]=cArray[i][1];
			}
			
			
			return cRueck;
			
			
		}
		
		
		public static String get_ID_SPRACHE_USER()
		{
			try
			{
				return bibALL.get_RECORD_USER().get_ID_SPRACHE_cUF();
			} 
			catch (myException e)
			{
				e.printStackTrace();
				return null;
			}
		}

		
		
		public static String[] get_Array(String c1)
		{
			String[] cRueck = new String[1];
			cRueck[0]=c1;
			return cRueck;
		}
		public static String[] get_Array(String c1,String c2)
		{
			String[] cRueck = new String[2];
			cRueck[0]=c1;
			cRueck[1]=c2;
			return cRueck;
		}
		public static String[] get_Array(String c1,String c2,String c3)
		{
			String[] cRueck = new String[3];
			cRueck[0]=c1;
			cRueck[1]=c2;
			cRueck[2]=c3;
			return cRueck;
		}
		public static String[] get_Array(String c1,String c2,String c3,String c4)
		{
			String[] cRueck = new String[4];
			cRueck[0]=c1;
			cRueck[1]=c2;
			cRueck[2]=c3;
			cRueck[3]=c4;
			return cRueck;
		}
		
		
		public static String[][] concat_arrays(String[][] Array1, String[][] Array2)
		{
			String[][] cRueck = null;
			
			if (Array1==null || Array2==null || Array1.length == 0 || Array2.length == 0)
			{
				return null;
			}
			
			if (Array1[0].length != Array2[0].length)
			{
				return null;
			}
			
			cRueck = new String[Array1.length+Array2.length][Array1[0].length];
			
			int ix = 0;
			for (int i=0;i<Array1.length;i++)
			{
				cRueck[ix++]=Array1[i];
			}
			for (int i=0;i<Array2.length;i++)
			{
				cRueck[ix++]=Array2[i];
			}
			
			return cRueck;
		}
		
		
		
		/*
		 * globale SQL-trigger, die in allen MySQL_Statements beruecksichtigt werden
		 */
		public static Vector<XX_SQL_STACK_DAEMON> get_vGlobal_SQL_STACK_DAEMON()
		{
		    return (Vector<XX_SQL_STACK_DAEMON>) getSessionValue("SQL_TRIGGER_AND_VALIDATOR");
		}

		
		public static void ADD_Global_TRIGGER_AND_VALIDATOR(XX_SQL_STACK_DAEMON oTriggerAndValidator)
		{
			bibALL.get_vGlobal_SQL_STACK_DAEMON().add(oTriggerAndValidator);
		}
		

		
		

		

		// methode zaehlt den zindex von E2_ContentPane.add()-aufrufen mit und erhoeht immer um 1
		public static int get_iZIndexNEXT()
		{
			int iRueck = 10;
			// ALLE methoden OHNE TRANSLATOR
			// liest das session-weite message-objekt aus und hängt eine message dran
		    Integer oTest = (Integer) bibALL.getSessionValue("ZINDEXVALUE_FOR_ALL_POPUPS");
		    if (oTest==null)
		    {
		    	bibALL.setSessionValue("ZINDEXVALUE_FOR_ALL_POPUPS",new Integer(iRueck));
		    }
		    else
		    {
		    	iRueck = oTest.intValue()+1; 
		    	bibALL.setSessionValue("ZINDEXVALUE_FOR_ALL_POPUPS",new Integer(iRueck));
		    }
			return iRueck;
		}
		
		
		
		public static boolean get_RoundAbzuegeMengen() throws myException
		{
			return bibALL.get_RECORD_MANDANT().is_RUNDEN_ABZUGS_MENGEN_YES();
		}
		
		
		
		
		public static boolean get_bVectorsHaveOneSameString(Vector<String> v1,Vector<String> v2)
		{
			boolean bRueck = false;
			for (int i=0;i<v1.size();i++)
			{
				if (v2.contains((String)v1.get(i)))
				{
					bRueck = true;
					break;
				}
			}
			return bRueck;
		}
		
		
		
		public static XX_TransActionLock get_oTransactionLock()
		{
			return (XX_TransActionLock)bibALL.getSessionValue("TRANSAKTIONSLOCK");
		}

		
		
//2015-05-07: verlagert in MyDBToolBox_FAB (martin)		
//		/**
//		 * @return s Vector ID_MANDANT,LETZTE_AENDERUNG,GEAENDERT_VON,ERZEUGT_VON,ERZEUGT_AM
//		 */
//		public static Vector<String> get_vSonderFelder()
//		{
//			//2015-05-07: umstellung, nur noch aus den MyDBToolBox-methoden
//			Vector<String> vRueck = new Vector<String>();
//			
//			MyDBToolBox tb_temp = MyDBToolBox_FAB.generate_STANDARD_DBToolBox();
//			vRueck.addAll(tb_temp.get_AutomaticWrittenFields());
//			bibALL.destroy_myDBToolBox(tb_temp);
//
//			return vRueck;
////
////			
////			
////			Vector<String> vRueck = new Vector<String>();
////			
////			vRueck.add("ID_MANDANT");
////			vRueck.add("LETZTE_AENDERUNG");
////			vRueck.add("GEAENDERT_VON");
////			vRueck.add("ERZEUGT_VON");
////			vRueck.add("ERZEUGT_AM");
////			
////			return vRueck;
//		}
//		
		
		
		
		public static void set_LayoutParts(CellLayoutData oLayout, Alignment oAline, Insets oInsets, Color oBackground)
		{
			oLayout.setAlignment(oAline);
			oLayout.setInsets(oInsets);
			oLayout.setBackground(oBackground);
		}
		
		
		public static BASIC_RECORD_USER get_RECORD_USER()
		{
			return (BASIC_RECORD_USER)bibALL.getSessionValue("RECORD_USER");
		}
		
		public static BASIC_RECORD_MANDANT get_RECORD_MANDANT()
		{
			return (BASIC_RECORD_MANDANT)bibALL.getSessionValue("RECORD_MANDANT");
		}
		

		
		
		
		
		
		
		
		public static HashMap<String, String> get_HashMap(	String Key1,String Value1,
															String Key2,String Value2,
															String Key3,String Value3,
															String Key4,String Value4,
															String Key5,String Value5,
															String Key6,String Value6,
															String Key7,String Value7,
															String Key8,String Value8,
															String Key9,String Value9,
															String Key10,String Value10)
		{
			HashMap<String, String> hmRueck = new HashMap<String, String>();
			if (Key1 != null) hmRueck.put(Key1, Value1);
			if (Key2 != null) hmRueck.put(Key2, Value2);
			if (Key3 != null) hmRueck.put(Key3, Value3);
			if (Key4 != null) hmRueck.put(Key4, Value4);
			if (Key5 != null) hmRueck.put(Key5, Value5);
			if (Key6 != null) hmRueck.put(Key6, Value6);
			if (Key7 != null) hmRueck.put(Key7, Value7);
			if (Key8 != null) hmRueck.put(Key8, Value8);
			if (Key9 != null) hmRueck.put(Key9, Value9);
			if (Key10 != null) hmRueck.put(Key10, Value10);
			return hmRueck;
		}

		
		public static HashMap<String, String> get_HashMap(	String Key1,String Value1,
															String Key2,String Value2,
															String Key3,String Value3,
															String Key4,String Value4,
															String Key5,String Value5,
															String Key6,String Value6,
															String Key7,String Value7,
															String Key8,String Value8,
															String Key9,String Value9)
		{
			return bibALL.get_HashMap(Key1, Value1, Key2, Value2, Key3, Value3, Key4, Value4, Key5, Value5, Key6, Value6, Key7, Value7, Key8, Value8, Key9, Value9, null, null);
		}

		public static HashMap<String, String> get_HashMap(	String Key1,String Value1,
															String Key2,String Value2,
															String Key3,String Value3,
															String Key4,String Value4,
															String Key5,String Value5,
															String Key6,String Value6,
															String Key7,String Value7,
															String Key8,String Value8)
		{
			return bibALL.get_HashMap(Key1, Value1, Key2, Value2, Key3, Value3, Key4, Value4, Key5, Value5, Key6, Value6, Key7, Value7, Key8, Value8, null, null, null, null);
		}

		public static HashMap<String, String> get_HashMap(	String Key1,String Value1,
															String Key2,String Value2,
															String Key3,String Value3,
															String Key4,String Value4,
															String Key5,String Value5,
															String Key6,String Value6,
															String Key7,String Value7)
		{
			return bibALL.get_HashMap(Key1, Value1, Key2, Value2, Key3, Value3, Key4, Value4, Key5, Value5, Key6, Value6, Key7, Value7, null, null, null, null, null, null);
		}

		public static HashMap<String, String> get_HashMap(	String Key1,String Value1,
															String Key2,String Value2,
															String Key3,String Value3,
															String Key4,String Value4,
															String Key5,String Value5,
															String Key6,String Value6)
		{
			return bibALL.get_HashMap(Key1, Value1, Key2, Value2, Key3, Value3, Key4, Value4, Key5, Value5, Key6, Value6, null, null, null, null, null, null, null, null);
		}

		public static HashMap<String, String> get_HashMap(	String Key1,String Value1,
															String Key2,String Value2,
															String Key3,String Value3,
															String Key4,String Value4,
															String Key5,String Value5)
		{
			return bibALL.get_HashMap(Key1, Value1, Key2, Value2, Key3, Value3, Key4, Value4, Key5, Value5, null, null, null, null, null, null, null, null, null, null);
		}
		
		

		public static HashMap<String, String> get_HashMap(	String Key1,String Value1,
																String Key2,String Value2,
																String Key3,String Value3,
																String Key4,String Value4)
		{
			return bibALL.get_HashMap(Key1, Value1, Key2, Value2, Key3, Value3, Key4, Value4, null, null, null, null, null, null, null, null, null, null, null, null);
		}

		
		public static HashMap<String, String> get_HashMap(	String Key1,String Value1,
															String Key2,String Value2,
															String Key3,String Value3)
		{
			return bibALL.get_HashMap(Key1, Value1, Key2, Value2, Key3, Value3, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		}

		public static HashMap<String, String> get_HashMap(	String Key1,String Value1,
															String Key2,String Value2)
		{
			return bibALL.get_HashMap(Key1, Value1, Key2, Value2, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		}

		public static HashMap<String, String> get_HashMap(	String Key1,String Value1)
		{
			return bibALL.get_HashMap(Key1, Value1, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
		}

		

		/**
		 * true, wenn im Web.xml die Option DEBUG == 1 ist, sonst false
		 * @author manfred
		 * @date   19.01.2012
		 * @return
		 */
		public static boolean get_bDebugMode()
		{
		    boolean bRueck = false;
		    String cTest = (String) getSessionValue("DEBUG");
		    if (cTest != null && cTest.equals("1"))
		    {
		        bRueck = true;
		    }
		    return bRueck;
		}
		

		/**
		 * 
		 * @param debugMode
		 */
		public static void set_bDebugMode(boolean debugMode)		{
		    bibALL.setSessionValue("DEBUG", debugMode?"1":"0");
		    bibMSG.add_MESSAGE(new MyE2_Info_Message(new MyE2_String("Debug-Mode ist jetzt (für die aktuelle Sitzung): ").t(debugMode?"An":"Aus")));
		}
		

		
		/*
		 * falls die applikation in einem Testmodus ist, kann eine alternative mailadresse geladen werden, die 
		 * alle mails entgegennimmt
		 */
		public static String get_cTestMailAdresse()
		{
			return (String)bibALL.getSessionValue("TESTMAIL");
		}
		
		
		/*
		 * weitere testadressen fuer cc- und bcc-tests 
		 * 
		 */
		public static String get_cTestMailAdresse_cc1()
		{
			return (String)bibALL.getSessionValue("TESTMAIL_CC1");
		}

		/*
		 * weitere testadressen fuer cc- und bcc-tests 
		 * 
		 */
		public static String get_cTestMailAdresse_cc2()
		{
			return (String)bibALL.getSessionValue("TESTMAIL_CC2");
		}

		/*
		 * weitere testadressen fuer cc- und bcc-tests 
		 * 
		 */
		public static String get_cTestMailAdresse_cc3()
		{
			return (String)bibALL.getSessionValue("TESTMAIL_CC3");
		}
		
		
        /*
         * titelhinweis (gross, rot) fuer die oberste zeile
         */
        public static String get_cTitelHinweis()
        {
            return (String)bibALL.getSessionValue("TITELHINWEIS");
        }

        
        
        public static String get_MINMAL_TABLE_SEQUENZ(String cTABLE_NAME)
        {
        	String cRueck = (String) bibALL.getSessionValue("MINIMALTABLESEQ");
        	
        	if (cTABLE_NAME.toUpperCase().equals("JD_MANDANT"))   //nur der mandant bekommt eine sequenz, die im einstelligen bereich erlaubt ist
        	{
        		cRueck = "1";
        	}
        	
        	return cRueck;
        }
        
		
        public static Vector<String>  get_SEQUENCE_VECT_VALUES(String cSequenceName, int iNumberOfSeqValues) throws myException
        {
           	Vector<String> vRueck = new Vector<String>();
			for (int k=0;k<iNumberOfSeqValues;k++)
			{
				String cWert =bibDB.EinzelAbfrage("SELECT "+cSequenceName+".NEXTVAL FROM DUAL"); 
				if (cWert.startsWith("@") || S.isEmpty(cWert))
				{
					throw new myException("Sequence: "+cSequenceName+" not found !!!");
				}
				vRueck.add(cWert);
			}

			return vRueck;
        }

        
        /**
         * 
         * @param cSequenceName
         * @return s next sequence - Value
         * @throws myException
         */
        public static String get_SEQUENCE_NEXT_VALUE(String cSequenceName) throws myException
        {
        	return bibALL.get_SEQUENCE_VECT_VALUES(cSequenceName,1).get(0);
        }
        
        
        
        
        /**
         * ORA-Session-ID zum pruefen, wieviele locks vorhanden sind
         * @return s Oracle-Session-ID (aus:SELECT MAX(SID) FROM  v$session where username='LEBER' and status = 'ACTIVE')
         * 
         */
        public static String get_DBSESSION_4_LOCK_Check()
        {
            return (String)bibALL.getSessionValue("DBSESSION");
        }
      
        /**
         * ORA-Session-ID zum pruefen, wieviele triggereintrage in der session erfolgt sind
         * @return s Oracle-Session-ID (aus:SELECT SYS_CONTEXT('USERENV','SESSIONID') FROM DUAL)
         * 
         */
        public static String get_DBSESSION_4_Trigger_Rows()
        {
            return (String)bibALL.getSessionValue("DBSESSIONNUMBER");
        }
      

        
        
        public static int increase_Trigger_Counter()
        {
        	Integer iTriggerCount = (Integer)bibALL.getSessionValue("TRIGGER_COUNTER");
        	if (iTriggerCount == null)
        	{
        		iTriggerCount = new Integer(0);
        		bibALL.setSessionValue("TRIGGER_COUNTER", iTriggerCount);
        	}
        	
        	bibALL.setSessionValue("TRIGGER_COUNTER", new Integer(++iTriggerCount));
        	return ((Integer)bibALL.getSessionValue("TRIGGER_COUNTER")).intValue();
        }
        

        public static int reset_Trigger_Counter()
        {
        	Integer iTriggerCount = (Integer)bibALL.getSessionValue("TRIGGER_COUNTER");
        	if (iTriggerCount == null)
        	{
        		iTriggerCount = new Integer(0);
        		bibALL.setSessionValue("TRIGGER_COUNTER", iTriggerCount);
        	}
        	
        	bibALL.setSessionValue("TRIGGER_COUNTER", new Integer(0));
        	return ((Integer)bibALL.getSessionValue("TRIGGER_COUNTER")).intValue();
        }
        
        public static int get_Trigger_Counter()
        {
        	Integer iTriggerCount = (Integer)bibALL.getSessionValue("TRIGGER_COUNTER");
        	if (iTriggerCount == null)
        	{
        		iTriggerCount = new Integer(0);
        		bibALL.setSessionValue("TRIGGER_COUNTER", iTriggerCount);
        	}
        	return iTriggerCount.intValue();
        }
        
        
        
        public static String get_CharacterEncoding4Oracle()
        {
        	return (String)bibALL.getSessionValue("CHARACTER_ENCODING");
        }
        
        
        
        
        
		public static RECLIST_MANDANT_ZUSATZ get_RECLIST_MANDANTEN_ZUSATZ() throws myException
		{
			RECLIST_MANDANT_ZUSATZ reclistRueck = null;
			
			if (bibALL.getSessionValue("__RECLIST_MANDANTEN_ZUSATZ")==null)
			{
				
				RECORD_MANDANT  recMandant = new RECORD_MANDANT(bibALL.get_RECORD_MANDANT().get_ID_MANDANT_cUF());
				reclistRueck = recMandant.get_DOWN_RECORD_LIST_MANDANT_ZUSATZ_id_mandant(null,null,true);

				bibALL.setSessionValue("__RECLIST_MANDANTEN_ZUSATZ",reclistRueck);
			}
			
			reclistRueck = (RECLIST_MANDANT_ZUSATZ)bibALL.getSessionValue("__RECLIST_MANDANTEN_ZUSATZ");
			return reclistRueck;
		}
		
		
		public static String get_TEXTVAL_FROM_RECLIST_MANDANTEN_ZUSATZ(String FELDNAME) throws myException
		{
			RECLIST_MANDANT_ZUSATZ reclistZusatz = bibALL.get_RECLIST_MANDANTEN_ZUSATZ();
			
			Iterator<RECORD_MANDANT_ZUSATZ> oIter = reclistZusatz.values().iterator();
			
			while (oIter.hasNext())
			{
				RECORD_MANDANT_ZUSATZ recZusatz = oIter.next();
				
				if (recZusatz.get_FIELDNAME_cUF_NN("@@@").equals(FELDNAME))
				{
					return recZusatz.get_TEXT_VALUE_cUF_NN("");
				}
				
			}
			
			return null;
		}

		
		/**
		 * Konvertiert einen String des Format 999.999,00 (Deutsches Nummernformat, wie in Maske formatiert) in ein BigDecimal-Wert
		 * Der Scale wird automatisch ermittelt, anhand der Komma-Positioin
		 * 
		 * Author: manfred
		 *
		 * @param sNumber
		 * @return
		 * @throws ParseException
		 */
		public static BigDecimal convertTextToBigDecimal(String sNumber){
			if (sNumber == null) return null;

			// tausender-Punkte weg
			sNumber = sNumber.replace(".", "");
			
			// scale ermitteln
			int scale = sNumber.length() - sNumber.lastIndexOf(",");
			
			
			DotFormatter df = new DotFormatter(sNumber,scale,Locale.GERMANY,true,3);
			
			if (df.doFormat()){
				BigDecimal bdec = new BigDecimal(df.getDoubleValue());
				bdec = bdec.setScale(scale,BigDecimal.ROUND_HALF_UP);
				return bdec;
			} 
			return null;
		}
		

		/**
		 * Konvertiert einen String des Format 999,999.99 (DB-Nummernformat) in ein BigDecimal-Wert
		 * 
		 * Author: manfred
		 *
		 * @param sNumber
		 * @return
		 * @throws ParseException
		 */
		public static BigDecimal convertDBTextToBigDecimal(String sNumber){
			if ( S.isEmpty(sNumber )) return null;
			BigDecimal bdRet = null;
			
			// tausender-Punkte weg
			sNumber = sNumber.replace(",", "");
			
			Double dblNum;
			try {
				dblNum = Double.parseDouble(sNumber);
				bdRet = new BigDecimal(dblNum);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			 
			return bdRet;
		}
		

		
		/**
		 * Konvertiert eine bigDecimal-
		 * @param bd
		 * @param dezimalstellen
		 * @return
		 */
		public static String convertBigDecimalToString(BigDecimal bd, int dezimalstellen ){
			String sRet = null;
			if (bd != null){
				sRet = bibALL.makeDez(bd.doubleValue(), dezimalstellen, true);
			}
			return sRet;
			
		}

		
		
		
		/**
		 * Konvertiert eine ID (formatiert oder nicht), in eine Formatierte ID mit tausender - Punkt
		 * @author manfred
		 * @date   03.11.2011
		 * @param ID
		 * @return
		 */
		public static String convertID2FormattedID(String ID){
			BigDecimal bd = convertTextToBigDecimal(ID);
			String sRet = convertBigDecimalToString(bd, 0);
			return sRet;
		}
		
		
		/**
		 * Konvertiert eine ID (formatiert oder nicht), in eine unformatierte ID mit tausender - Punkt
		 * @author manfred
		 * @date   03.11.2011
		 * @param ID
		 * @return
		 */
		public static String convertID2UnformattedID(String ID){
			BigDecimal bd = convertTextToBigDecimal(ID);
			String sRet = null;
			if (bd != null){
				DecimalFormat df = new DecimalFormat("#");
				sRet = df.format(bd.doubleValue());
			}
			return sRet;

		}
		

		
		
		
		public static HashMap<String, String> get_hmFromArray(String[][] cArray, int iVon, int iBis, int iIndexKey, int iIndexValue)
		{
			HashMap<String, String> hmRueck = new HashMap<String, String>();
			
			for (int i=iVon;i<iBis;i++)
			{
				if (cArray.length>i && i>=0)
				{
					if (cArray[i].length>iIndexKey && cArray[i].length>iIndexValue)
					hmRueck.put(cArray[i][iIndexKey], cArray[i][iIndexValue]);
				}
				
			}
			
			
			return hmRueck;
		}
		
		


		/**
		 * 
		 * @param hm
		 * @param c_Key_InFront
		 * @param c_Val_inFront
		 * @return
		 */
		public static String[][]  get_ArrayFromHashMap(HashMap<String, String> hm, String c_Key_InFront, String c_Val_inFront)
		{
			boolean bZusatzInFront = (c_Key_InFront!=null && c_Val_inFront!=null);
			
			String[][]  cRueck = new String[hm.size()+(bZusatzInFront?1:0)][2];
			
			Iterator<String> oIter = hm.keySet().iterator();
			
			int i=0;
			if (bZusatzInFront)
			{
				i=1;
				cRueck[0][0]=c_Key_InFront;
				cRueck[0][1]=c_Val_inFront;
			}
			
			while (oIter.hasNext())
			{
				String cKey = oIter.next();
				cRueck[i][0] = cKey;
				cRueck[i++][1] = hm.get(cKey);
			}
			
			return cRueck;
		}
		

		/**
		 * 
		 * @param hm
		 * @param cKey_InFront
		 * @param cVal_inFront
		 * @return
		 */
		public static String[][]  get_ArrayFromHashMap_INVERSE(HashMap<String, String> hm, String cKey_InFront, String cVal_inFront)
		{
			boolean bZusatzInFront = (cKey_InFront!=null && cVal_inFront!=null);
			
			String[][]  cRueck = new String[hm.size()+(bZusatzInFront?1:0)][2];
			
			Iterator<String> oIter = hm.keySet().iterator();
			
			int i=0;
			if (bZusatzInFront)
			{
				i=1;
				cRueck[0][1]=cKey_InFront;
				cRueck[0][0]=cVal_inFront;
			}
			
			while (oIter.hasNext())
			{
				String cKey = oIter.next();
				cRueck[i][1] = cKey;
				cRueck[i++][0] = hm.get(cKey);
			}
			
			return cRueck;
		}
		
	

		




		
		public static boolean get_bIstFremdAdresse(String cID_ADRESSE_UF)
		{
			return !(S.NN(cID_ADRESSE_UF).equals(bibALL.get_ID_ADRESS_MANDANT()));
		}
		
		
		 
		/**
		 * ermittlung und rueckgabe einer hashmap mit key: Tabellenname, Vector mit Feldnamen, bestimmt welche
		 * Felder im TRIGGER_DEF stehen 
		 * @throws myException 
		 */
		public static HashMap<String,HashMap<String,RECORD_TRIGGER_DEF>>  get_hmLogTriggerFields() throws myException
		{
			String cKey = "HM_LOG_TIGGER_FIELDS__@@SESSION";

			@SuppressWarnings("unchecked")
			HashMap<String,HashMap<String,RECORD_TRIGGER_DEF>>  hmRueck = (HashMap<String,HashMap<String,RECORD_TRIGGER_DEF>>)bibALL.getSessionValue(cKey);
			
			if (hmRueck==null)
			{
				hmRueck = new HashMap<String,HashMap<String,RECORD_TRIGGER_DEF>>();
				bibALL.setSessionValue(cKey, hmRueck);
				
				RECLIST_TRIGGER_DEF rlTrigDef = new RECLIST_TRIGGER_DEF("SELECT * FROM "+bibE2.cTO()+".JD_TRIGGER_DEF");
				
				for (int i=0;i<rlTrigDef.get_vKeyValues().size();i++)
				{
					RECORD_TRIGGER_DEF  recTrig = rlTrigDef.get(i);
					HashMap<String,RECORD_TRIGGER_DEF>  hmTableFields = hmRueck.get(recTrig.get_TABELLE_cUF_NN("@@@ZUSATZ@@@"));
					if (hmTableFields==null)
					{
						hmTableFields = new HashMap<String,RECORD_TRIGGER_DEF>();
						hmRueck.put(recTrig.get_TABELLE_cUF_NN("@@@ZUSATZ@@@"),hmTableFields);
					}
					hmTableFields.put(recTrig.get_SPALTE_cUF_NN("@@@ZUSATZ@@@"),recTrig);
				}
				 
			}
			
			
			return hmRueck;
		}
		
		
		public static void set_hmLogTriggerFields(HashMap<String,Vector<String>> hmFieldRules)
		{
			String cKey = "HM_LOG_TIGGER_FIELDS__@@SESSION";
			bibALL.setSessionValue(cKey, hmFieldRules);
		}

		
		/**
		 * prueft auf verbotene worte innerhalb scriptings
		 * @param cPruefString
		 * @return
		 */
		public static String Check_forbidden_words(String cPruefString)
		{
			String[] cVerboten = {"INSERT ","UPDATE ","DELETE ","DROP ","MODIFY ","CREATE "};

			String cRueck = "";
			String ccPruefString = S.NN(cPruefString).toUpperCase();
			
			 for (String cForbidden: cVerboten)
			 {
				 if (ccPruefString.contains(cForbidden))
				 {
					 cRueck = cForbidden;
					 break;
				 }
			 }
			 
			 return cRueck;
			
		}
		
		
		

		/**
		 * erfragt in einer session einmal die basiswaehrung des mandanten
		 * @return
		 */
		public static String get_BASIS_WAEHRUNG_KURZ()
		{
			String cRUECK = (String) bibALL.getSessionValue(bibCONST.BIB_KEY_BASISWAEHRUNG);
			if (S.isEmpty(cRUECK)) {
				try {
					if (bibALL.get_RECORD_MANDANT()!=null && S.isFull(bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cUF_NN(""))) {
						RECORD_WAEHRUNG recW = new RECORD_WAEHRUNG(bibALL.get_RECORD_MANDANT().get_ID_WAEHRUNG_cUF_NN(""));
						cRUECK = recW.get_WAEHRUNGSSYMBOL_cUF_NN(recW.get_KURZBEZEICHNUNG_cUF_NN("<waehrung>"));
						bibALL.setSessionValue(bibCONST.BIB_KEY_BASISWAEHRUNG, cRUECK);
					}
				} catch (myException e) {
					e.printStackTrace();
				}
			}
			
			return S.NN(cRUECK, "<waehrung>");
		}
		

		/**
		 * focus auf eine komponente setzen
		 * @param comp
		 */
		public static void setFocus(Component comp) {
			ApplicationInstance.getActive().setFocusedComponent(comp);
		}
		

		/**
		 * 
		 * @return s maskHighlight-colorfrom mandant
		 */
		public static Color get_colorMaskHighLight() {
			
			try {
				return new RECORD_MANDANT_ext(bibSES.RECORD_MANDANT()).get_COLOR_MASK_HIGHLIGHT();
			} catch (myException e) {
				e.printStackTrace();
				return new E2_ColorBase();
			}
			
		}
		
		
}