/*
 * myExportTable.java
 *
 * Created on 11. April 2003, 13:35
 */
package panterdt.dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Vector;

import panter.gmbh.indep.dataTools.DB_META;
import panterdt.common.pdBib;
import panterdt.common.pdFilerForNewFile;
import panterdt.common.pdUnicodeTransformer;
import panterdt.exceptions.pdException;


public class pdTableExporter
{

    
    private Vector<String>  	vTabellenNamen = 		new Vector<String>(); // hier kommen tabellennamen rein    (String)
    private Vector<String> 		vTabellenID_Felder = 	new Vector<String>(); // hier kommen die Feldid's-namen rein (String)
    private Vector<String> 		vTabellenSEQ_Name = 	new Vector<String>(); // hier kommen die Sequence-namen rein (String)
    private Vector<String> 		vWHEREString = 			new Vector<String>(); // hier die wheres
    private Vector<String[]> 	vAusnahmeFelder = 		new Vector<String[]>(); // hier kommen die Feldausnahmen rein (String[]-ARRAY)
    private Vector<Boolean> 	vSequenceOR_ID 	= 		new Vector<Boolean>(); // wird als string uebergeben
    private Vector<String[][]> 	vZusatzFelder = 		new Vector<String[][]>(); // hier werden zusatz-feldnamen/ausdrücke übergeben (String[n][2] - Array)
    private pdDBToolBox 		oDB = 					null;


	/*
	 * zwei vectoren, die einen vergleich zulassen, wieviele datensätze vorhanden waren
	 * und wieviele exportiert wurden
	 * vZuExportieren über select count() from
	 * vWurdeExportiert über einen zähler
	 */
	private Vector<String> 		vZuExportierenTabUndZahl = 		new Vector<String>();
	private Vector<String> 		vWurdeExportiertTabUndZahl = 	new Vector<String>();
	
	private Vector<String> 		vZuExportierenZahl = 			new Vector<String>();
	private Vector<String> 		vWurdeExportiertZahl = 			new Vector<String>();
	

	/*
	 * wenn bTransFormUnicode = true, dann werden alle zeichen zwischen 18 und 122 maskiert über {00000} mit ihrem unicode-wert
	 */
	private boolean bTransFormUnicode = false;


    /** Creates a new instance of myExportTable */
    public pdTableExporter(pdDBToolBox oDB, boolean bTransFormUnicode)
    {
        this.oDB = oDB;
        this.bTransFormUnicode=bTransFormUnicode;
    }

    

    /**
     * @param cTabelle is name of a table
     */
    public void addExportTabelle(	String cTabelle) 
	{
		vTabellenNamen.add(cTabelle);
		vTabellenID_Felder.add("");
		vTabellenSEQ_Name.add("");
		vWHEREString.add("");
		vAusnahmeFelder.add(null);
		vZusatzFelder.add(null);
        vSequenceOR_ID.add(new Boolean(false));
	}
    
    /**
     * @param vTables = vector mit tabellennamen
     */
    public void addExportTable( Vector vTables)
    {
        for (int i=0;i<vTables.size();i++)
        {
            this.addExportTabelle((String)vTables.get(i));
        }

    }
    
    
    
    // definiert eine Tabellenabfrage für den Export
    public void addExportTabelle(	String 		cTabelle, 
            						String 		cID_Feld, 
            						String 		cSEQ_NAME, 
            						String 		cWhere, 
            						String[] 	cAusnahmeFelder, 
            						boolean 	bSequence, 
            						String[][] 	cZusatzFelder_und_werte)
    {
        vTabellenNamen.add(cTabelle);
        vTabellenID_Felder.add(pdBib.null2leer(cID_Feld));
        vTabellenSEQ_Name.add(pdBib.null2leer(cSEQ_NAME));
        vWHEREString.add(pdBib.null2leer(cWhere));
        vAusnahmeFelder.add(cAusnahmeFelder);
        vZusatzFelder.add(cZusatzFelder_und_werte);
        vSequenceOR_ID.add(new Boolean(bSequence));
    }

    // methode baut einen export auf und gibt einen kompletten insert zurück
    // wenn bMitSequence = true, dann gibt er SEQ_XXX.nextval() in den string statt der  ID
    // die feldnamen in cAusnahmen werden nicht berücksichtigt
    // cWHERESQL ist ein wherestring
    // wenn bExportInTempFile, dann wird ein temp-file generiert und die rueckgabe enthält
    // nicht den string, sondern den Dateipfad
    public void doExportToFile(pdFilerForNewFile oFile) throws pdException
    {

        String 		cSql 					= "";
        String 		cSql_Count 				= "";
        String 		cINSERT_ZEILE 			= "";

        // werte aus den definitions-vektoren
        String 		cTabellenNamen 			= "";
        String 		cTabellenID_Felder 		= "";
        String 		cTabellenSEQ_Name 		= "";
        String 		cWHEREString 			= "";
        String[] 	cAusnahmeFelder 		= null;
        String[][] 	cZusatzFelder 			= null;

        boolean 	bAusnahme 		= false;
        boolean 	bMitSequence 	= false;

        int iRowCount = 0; // falls alles gut geht wird als kommentar
                           // am ende der sql-zeile die info eingeblendet, wieviele zeile rausgeschrieben wurden

		String cHelpCount = "";
		
		/*
		 * aeusere try-anweisung, faengt alle exceptions ab und gibt die info weiter, sorgt dafuer, dass tempfile geschlossen wird 
		 */
		try
		{
	        if (this.vTabellenNamen.size() == 0)
	        {
	            throw new pdException("myExportTable:doExportToFile:Empty Exportlist !");
	        }

	        for (int iTAB = 0; iTAB < this.vTabellenNamen.size(); iTAB++)
	        {
	            // jetzt die tabellenparameter aus den vectoren lesen
	            cTabellenNamen 		= (String) vTabellenNamen.get(iTAB);
	            cTabellenID_Felder 	= (String) vTabellenID_Felder.get(iTAB);
	            cTabellenSEQ_Name 	= (String) vTabellenSEQ_Name.get(iTAB);
	            cWHEREString 		= (String) vWHEREString.get(iTAB);
	            cAusnahmeFelder 	= (String[]) vAusnahmeFelder.get(iTAB);
	            bMitSequence 		= ((Boolean) vSequenceOR_ID.get(iTAB)).booleanValue();
	            cZusatzFelder 		= (String[][]) vZusatzFelder.get(iTAB);


	            if (cWHEREString.equals(""))
	            {
	                cSql = "SELECT * FROM " + cTabellenNamen;
	                cSql_Count = "SELECT COUNT(*) FROM " + cTabellenNamen;
	            }
	            else
	            {
	                cSql = "SELECT * FROM " + cTabellenNamen + " WHERE " + cWHEREString;
					cSql_Count = "SELECT COUNT(*) FROM " + cTabellenNamen + " WHERE " + cWHEREString;
	            }

				/*
				 * satzanzahl prüfen
				 */
	            String cCount = this.oDB.EinzelAbfrage(cSql_Count);
				this.vZuExportierenTabUndZahl.add(cTabellenNamen+":"+new String(cCount));
				this.vZuExportierenZahl.add(new String(cCount));
				
	            iRowCount 	= 0;
				cHelpCount 	= ""+iRowCount;

	            this.oDB.OpenResultSet(cSql);
	                
	            while (oDB.get_oRS().next())
	            {
	                cINSERT_ZEILE = "INSERT INTO " + cTabellenNamen + " (";

	                for (int i = 0; i < oDB.get_oRS().getMetaData().getColumnCount(); i++)
	                {
	                    // prüfen, ob feld geliefert wird
	                    bAusnahme = false;

	                    if (cAusnahmeFelder != null)
	                    {
	                        for (int k = 0; k < cAusnahmeFelder.length; k++)
	                        {
	                            if (oDB.get_oRS().getMetaData().getColumnName(i + 1).toUpperCase().equals(cAusnahmeFelder[k].toUpperCase()))
	                            {
	                                bAusnahme = true;
	                            }
	                        }
	                    }

	                    if (!bAusnahme)
	                    {
	                        cINSERT_ZEILE += "\""+(oDB.get_oRS().getMetaData().getColumnName(i + 1) + "\",");
	                    }
	                }

	                // jetzt die zusatzfelder definieren
	                if (cZusatzFelder != null)
	                {
	                    for (int iZ = 0; iZ < cZusatzFelder.length; iZ++)
	                    {
	                        cINSERT_ZEILE += (cZusatzFelder[iZ][0] + ",");
	                    }
	                }

	                cINSERT_ZEILE = cINSERT_ZEILE.substring(0, cINSERT_ZEILE.length() - 1); // rechtes komma weg
	                cINSERT_ZEILE += ")  VALUES (";

	                for (int i = 0; i < oDB.get_oRS().getMetaData().getColumnCount(); i++)
	                {
	                    // prüfen, ob feld geliefert wird
	                    bAusnahme = false;

	                    if (cAusnahmeFelder != null)
	                    {
	                        for (int k = 0; k < cAusnahmeFelder.length; k++)
	                        {
	                            if (oDB.get_oRS().getMetaData().getColumnName(i + 1).toUpperCase().equals(cAusnahmeFelder[k].toUpperCase()))
	                            {
	                                bAusnahme = true;
	                            }
	                        }
	                    }

	                    if (!bAusnahme)
	                    {
	                        // hier prüfen, ob id-feld füe sequence
	                        if (oDB.get_oRS().getMetaData().getColumnName(i + 1).toUpperCase().equals(cTabellenID_Felder.toUpperCase()) && bMitSequence)
	                        {
	                            cINSERT_ZEILE += (cTabellenSEQ_Name + ".NEXTVAL,");
	                        }
	                        else
	                        {
								cINSERT_ZEILE += (this.getRSValueSQL(oDB.get_oRS(),i+1,this.bTransFormUnicode) + ",");
	                        }
	                    }
	                }

	                // jetzt die zusatzfelder definieren
	                if (cZusatzFelder != null)
	                {
	                    for (int iZ = 0; iZ < cZusatzFelder.length; iZ++)
	                    {
	                        cINSERT_ZEILE += (cZusatzFelder[iZ][1] + ",");
	                    }
	                }

	                cINSERT_ZEILE = cINSERT_ZEILE.substring(0, cINSERT_ZEILE.length() - 1); // rechtes komma weg
	                cINSERT_ZEILE += ")";

	                // wenn export in file, dann an datei dranhängen,
	                // sonst an cRueck anhängen
	                try
	                {
	                    oFile.WriteLine(cINSERT_ZEILE);
		                iRowCount++;
	                }
	                catch (pdException ex)
	                {
	                    throw new pdException("myExportTable:doExportToFile:Erroir writing exportfile:"+ex.getLocalizedMessage());
	                }

					cHelpCount = ""+iRowCount;
	            }  // while
	                    

	           // abshlusszeile als kommentar
	            oFile.WriteLine("// Export von " + cTabellenNamen + " beendet !");
	            oFile.WriteLine("// Es wurden  " + iRowCount + " Datensätze exportiert !");
	            oFile.WriteLine("// ---------------------------------------------------");

	            /*
	             * jetzt den ergebnis-vector füllen
	             */
	            this.vWurdeExportiertTabUndZahl.add(cTabellenNamen+":"+cHelpCount);
	            this.vWurdeExportiertZahl.add(cHelpCount);
	            
	        }
		}
		catch (Exception ex)
		{
		    throw new pdException("myExportTable:doExportToFile:"+ex.getLocalizedMessage());
		}

    }


	public Vector get_vWurdeExportiertTabUndZahl() {
		return vWurdeExportiertTabUndZahl;
	}

	public Vector get_vZuExportierenTabUndZahl() {
		return vZuExportierenTabUndZahl;
	}

	public Vector get_vWurdeExportiertZahl() {
		return vWurdeExportiertZahl;
	}

	public Vector get_vZuExportierenZahl() {
		return vZuExportierenZahl;
	}

	
	public Vector get_vTabellen()
	{
		return this.vTabellenNamen;
	}
	
    /*
     * result in maschinenlesbarer form für einbau in sql-statements (doppelte '')
     * wenn bUnicode = true, dann werden alle ascii-zeichen ausser >=33 und <=127 in \\uxxxx-format angegeben werden  
     */ 
    private String getRSValueSQL(ResultSet rs,int iColumnNumber,boolean bUnicode) throws pdException
    {
        String 			cRueck 			= "";
        long 			lHelp 			= 0;
        double 			dHelp 			= 0;
        String 			cHelp 			= "";
        String 			cFeldTyp 		= "";
        int				iFeldNachkomma 	= -1;
        java.util.Date 	dateHelp;

        
        if (iColumnNumber <=0 ) throw new pdException("myExportTable:__getRSValueSQL:Columnnumber must be >= 1");
        
        try
        {
            cFeldTyp = rs.getMetaData().getColumnTypeName(iColumnNumber).toUpperCase();
            iFeldNachkomma 	= rs.getMetaData().getScale(iColumnNumber );

        }
        catch (SQLException ex)
        {
            throw new pdException("myExportTable:__getRSValueSQL: Column Number:" +iColumnNumber+" seems to be undefined !");
        }

        
        SimpleDateFormat sDateF = new SimpleDateFormat("yyyy-MM-dd", Locale.GERMAN);
        SimpleDateFormat sDateTimeF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.GERMAN);

        try
        {
		    // ganzzahlen
		    if (DB_META.vDB_NUMBER_TYPES.contains(cFeldTyp) && (iFeldNachkomma <= 0))
		    {
		        lHelp = rs.getLong(iColumnNumber);
		
		        if (!rs.wasNull())
		        {
		            cRueck = "" + lHelp;
		        }
		        else
		        {
		            cRueck = "NULL";
		        }
		    }
		    else if (DB_META.vDB_NUMBER_TYPES.contains(cFeldTyp) && (iFeldNachkomma > 0))
		    {
		        dHelp = rs.getDouble(iColumnNumber);
		
		        if (!rs.wasNull())
		        {
		            cRueck = "" + dHelp;
		        }
		        else
		        {
		            cRueck = "NULL";
		        }
		    }
		    else if (DB_META.vDB_TEXT_TYPES.contains(cFeldTyp))
		    {
		        cHelp = rs.getString(iColumnNumber);
		
		        if (!rs.wasNull())
		        {
		            cRueck = pdBib.MakeSql(cHelp); // prüft die ''- geschichte
					if (bUnicode)
					{
						cRueck = pdUnicodeTransformer.get_UnicodeFromAscii(cRueck);           	
					}
		        }
		        else
		        {
		            cRueck = "NULL";
		        }
		        
		    }
		    else if (cFeldTyp.equals("TIMESTAMP"))           // kommt nur in sapdb vor
		    {
		        dateHelp = rs.getDate(iColumnNumber);
		
		        if (!rs.wasNull())
		        {
		            cRueck = "'" + sDateTimeF.format(dateHelp) + "'";
		        }
		        else
		        {
		            cRueck = "NULL";
		        }
		    }
		    // falls date aus SAPDB exportiert wird ohne zeit
		    else if (DB_META.vDB_DATE_TYPES.contains(cFeldTyp) && this.oDB.get_oMyConnection().get_cDBKENNUNG().equals(DB_META.DB_SAP))
		    {
		        dateHelp = rs.getDate(iColumnNumber);
		
		        if (!rs.wasNull())
		        {
		            cRueck = "'" + sDateF.format(dateHelp) + "'";
		        }
		        else
		        {
		            cRueck = "NULL";
		        }
		    }
		    // falls date aus ORACLE exportiert wird, dann mit zeit
		    else if (DB_META.vDB_DATE_TYPES.contains(cFeldTyp) && this.oDB.get_oMyConnection().get_cDBKENNUNG().equals(DB_META.DB_ORA))
		    {
		        dateHelp = rs.getDate(iColumnNumber);
		
		        if (!rs.wasNull())
		        {
		            cRueck = "'" + sDateTimeF.format(dateHelp) + "'";
		        }
		        else
		        {
		            cRueck = "NULL";
		        }
		    }
		    else
		    {
		        // alle anderen typen
		        cRueck = rs.getString(iColumnNumber);
		
		        if (rs.wasNull())
		        {
		            cRueck = "";
		        }
		    }
        }
        catch (SQLException ex)
        {
            throw new pdException("myExportTable:__getRSValueSQL: Column Number:" +iColumnNumber+" cannnot be read !");
        }
        return (cRueck);
    }
	
	
	
	
	
}
