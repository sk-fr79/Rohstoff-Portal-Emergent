/*
 * bib.java
 * Allgemeine bibliothek mit methoden, die ohne klassengenerierung
 * aufrufbar sein sollen
 *
 * Created on 27. Juni 2002, 18:14
 */
package panterdt.common;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;




/**
 *
 * @author  martin
 */
public class pdBib
{
    /** Creates a new instance of bib */
    public pdBib()
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



    public static boolean IstDatumOK(String DatumString)
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

  



    // fügt in einem sql-string für ein vorhandenes ' ein zweites ein  
    public static String MakeSql(String pAusgangsString)
    {
        String RueckString = "";

        RueckString = "'" +pdBib.MakeSqlInnerString(pAusgangsString)  + "'";

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
		hStr = pdBib.make32Leer(hStr);

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






    public static String MakeNUMSql(String cAusgangswert)
    {
        String cRueck = "NULL";

        if (!cAusgangswert.trim().equals(""))
        {
            cRueck = cAusgangswert.trim();
        }

        return (cRueck);
    }


  

  

 



    // mittelformat: "31.12.2001"
    public static String FormatDateNormal(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        cRueck = df.format(dDate);

        return (cRueck);
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




    // methode nimmt einen vector und macht ein array aus strings daraus
    // geht nur mit string-vectoren
    public static String[] makeStringArrayFromVector(Vector oVector)
    {
        String[] cRueck = new String[oVector.size()];

        for (int i = 0; i < oVector.size(); i++)
        {
            cRueck[i] = new String((String) oVector.get(i));
        }

        return cRueck;
    }

  
}
