package panterdt.dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;

import panterdt.exceptions.pdException;

 
// formatierklasse für datenbankspalten
// übergibt ein resultset und liefert nötige methoden
// um diese zu formatieren
public class pdDBFormater extends java.lang.Object
{
    public static int FORMAT_DATE_SHORT 	= 100;
    public static int FORMAT_DATE_MIDDLE 	= 200;
    public static int FORMAT_DATE_LONG	 	= 300;
    
    private int iDateFormat = 100;
    
    /*
     * soll ein tausenderpunkt eingebaut werden
     */
    private boolean bThousendPoint = true;
    
    
    public pdDBFormater()
    {
    }

    public void set_DateFormat(int iDateFormat) throws pdException
    {
        if (iDateFormat==pdDBFormater.FORMAT_DATE_SHORT ||
            iDateFormat==pdDBFormater.FORMAT_DATE_MIDDLE ||
            iDateFormat==pdDBFormater.FORMAT_DATE_LONG)
        {
            this.iDateFormat = iDateFormat;
        }
        else
        {
            throw new pdException("myDBFormater:set_DateFormat: not allowed date-format");
        }
    }
    
    
    
    
    public String get_RSString(ResultSet oRs,String cColumn, boolean bFormated) throws pdException,SQLException
    {
        
        String cRueck = "";
        
        if (oRs == null || cColumn == null || cColumn.trim().equals("")) 
           throw new pdException("myDBFormater:get_RSString: null-resultset / empty fieldname");
        
        /*
         * jetzt die spalte feststellen, die der name hat
         */
        int iColumn = 0;   // columns must be >= 1
        try
        {
            String cHelp = cColumn.toUpperCase().trim();
            for (int i=1,iZahl=oRs.getMetaData().getColumnCount();i<=iZahl;i++)
            {
                if (oRs.getMetaData().getColumnName(i).toUpperCase().equals(cHelp))
                {
                    iColumn = i;
                    break;
                }
            }
        }
        catch (Exception ex)
        {
            throw new pdException("myDBFormater:get_RSString: Error finding column-number of "+cColumn);
        }
        if (iColumn == 0)
            throw new pdException("myDBFormater:get_RSString: (2) Error finding column-number of "+cColumn);
        
        cRueck = get_RSString(oRs,iColumn,bFormated);
        
        return cRueck;
        
    }
    
    
    // hilfsmethode für das formatierte auslesen eines resultset in der methode
    // _EinzelAbfrageInArray
    // liest einen string aus einem resultset und formatiert diesen dann 
    // wenn es eine zahl oder ein datum ist
    public String get_RSString(ResultSet oRs,int iColumn, boolean bFormated) throws SQLException
    {
        String cRueck = null;
        double dRueck = 0;
        long lRueck = 0;
        java.util.Date dateWert;

        String cTyp = "";
        int iNachKomma = 0;

        if (bFormated)
        {
            cTyp = oRs.getMetaData().getColumnTypeName(iColumn).toUpperCase();
            iNachKomma = oRs.getMetaData().getScale(iColumn);
			/*
			 * falls CTYP = Float oder double kommt kein nachkommawert mit, deshalb auf 2 setzen
			 */
			if (cTyp.equals("FLOAT") || cTyp.equals("DOUBLE"))
			{
				if (iNachKomma == 0) iNachKomma = 2;
			}


            if (cTyp.equals("FIXED") || cTyp.equals("FLOAT") || cTyp.equals("DOUBLE"))
            {
                dRueck = oRs.getDouble(iColumn);

                if (oRs.wasNull())
                {
                    cRueck = null;
                }
                else
                {
                    cRueck = this.formatDez(dRueck, iNachKomma, this.bThousendPoint);
                }
            }
            else if (cTyp.equals("INTEGER") || cTyp.equals("SMALLINT"))
            {
                lRueck = oRs.getLong(iColumn);

                if (oRs.wasNull())
                {
                    cRueck = null;
                }
                else
                {
                    cRueck = this.formatDez(lRueck, this.bThousendPoint);
                }
            }
            else if (cTyp.equals("DATE"))
            {
                dateWert = oRs.getDate(iColumn);

                if (oRs.wasNull())
                {
                    cRueck = null;
                }
                else
                {
                    
                    if    		(this.iDateFormat == pdDBFormater.FORMAT_DATE_SHORT)
                       cRueck = this.formatDateKurz(dateWert);
                    else if 	(this.iDateFormat == pdDBFormater.FORMAT_DATE_MIDDLE)
                        cRueck = this.formatDateNormal(dateWert);
                    else if 	(this.iDateFormat == pdDBFormater.FORMAT_DATE_LONG)
                        cRueck = this.formatDateLang(dateWert);
                    
                        
                }
            }
            else if (cTyp.equals("DATETIME") || cTyp.equals("TIMESTAMP"))
            {
                dateWert = oRs.getDate(iColumn);

                if (oRs.wasNull())
                {
                    cRueck = null;
                }
                else
                {
                    cRueck = this.formatDateTimeNormal(dateWert);
                }
            }
            else if (cTyp.equals("TIME"))
            {
                dateWert = oRs.getDate(iColumn);

                if (oRs.wasNull())
                {
                    cRueck = null;
                }
                else
                {
                    cRueck = this.formatTimeNormal(dateWert);
                }
            }
            else
            {
                cRueck = oRs.getString(iColumn);

                if (oRs.wasNull())
                {
                    cRueck = null;
                }
            }
        }
        else
        {
            cRueck = oRs.getString(iColumn);

            if (oRs.wasNull())
            {
                cRueck = null;
            }
        }
         // bFormated

        return cRueck;
    }

    // variante mit tausender-punkt
    private String formatDez(double dZahl, int iAnzahlDez, boolean bTausender)
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



    // variante mit tausender-punkt (long integer)
    private String formatDez(long dZahl, boolean bTausender)
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

    // kurzformat: "31.12.01" 
    private String formatDateKurz(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
        cRueck = df.format(dDate);

        return (cRueck);
    }

    // mittelformat: "31.12.2001"
    private String formatDateNormal(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        cRueck = df.format(dDate);

        return (cRueck);
    }

    // Langformat: "31. Dezember 2001"
    private String formatDateLang(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.LONG);
        cRueck = df.format(dDate);

        return (cRueck);
    }

    // mittelformat: "31.12.2001"
    private String formatDateTimeNormal(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        cRueck = df.format(dDate) + " " + tf.format(dDate);

        return (cRueck);
    }

    // mittelformat: "31.12.2001"
    private String formatTimeNormal(java.util.Date dDate)
    {
        String cRueck = "";
        DateFormat tf = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        cRueck = tf.format(dDate);

        return (cRueck);
    }


    /**
     * @param thousendPoint The bThousendPoint to set.
     */
    public void set_bThousendPoint(boolean thousendPoint)
    {
        bThousendPoint = thousendPoint;
    }
}
