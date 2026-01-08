package panterdt.dataBase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyConnection;
import panterdt.common.pdBib;
import panterdt.exceptions.pdException;



public class pdDBToolBox extends java.lang.Object
{
	private MyConnection    oMyConnection = null;
    private Connection 		conn;

    private Statement 		stmt; 						// einzelne anweisungen, commit wird direckt ausgeführt
    private Statement 		rsStmt;
    private ResultSet 		rs; 						// resultset nr 1 
 
    private Statement 		rsStmtINT;
    private ResultSet 		rsINT; 						// internes resultset
    private String 			cSqlStatement = ""; 		// bekommt jedes sql-statement übergeben für die auswertung in der fehlermeldung
 

    /*
     * schalter, der bei einzelabfrageinarray- querys benutzt wird, falls eine beschränkung auf
     * eine bestimmte anzahl datensätze übergeben wird, falls diese beschränkung überschritten wird, dann wird dieser parameter true
     */
    boolean bMaxNumberOfRowsExceeded = false;
    

    pdDBFormater oDBFormat = new pdDBFormater();
    
    
    public pdDBToolBox(MyConnection pConn) throws pdException
    {
        this.oMyConnection 	= pConn;
        this.conn 			= this.oMyConnection.get_oConnection();
        
        /*
         * dbformater einstellen
         */
        this.oDBFormat.set_bThousendPoint(true);
        this.oDBFormat.set_DateFormat(pdDBFormater.FORMAT_DATE_MIDDLE);
        
        	
        try
        {
            // alle connections haben autocommit =
            // pConnMulti MUSS autocommit ) false sein !!!  
            conn.setAutoCommit(false);
            rsStmt 		= conn.createStatement(); // statement-object für result-set
            rsStmtINT 	= conn.createStatement(); // statement-object für result-set2
        }
        catch (SQLException e)
        {
             throw new pdException("MyDBService:Error in Constructor: -->"+e.getLocalizedMessage());
        }
    }

    

 




    public void OpenResultSet(String pSql) throws pdException
    {
        this.cSqlStatement = pSql;
        try
        {
            if (this.rs 	!= null)  	this.rs.close();
            if (this.rsStmt != null)    this.rsStmt.close();

            this.rs 		= null;
            this.rsStmt 	= null;
            
            this.rsStmt 	= conn.createStatement(); // statement-object für result-set
            this.rs 		= this.rsStmt.executeQuery(pSql);
        }
        catch (SQLException e)
        {
            throw new pdException("MyDBService:_OpenResultSet: -->"+e.getLocalizedMessage());
        }
    }

    public void OpenResultSetINT(String pSql) throws pdException
    {
        this.cSqlStatement = pSql;
        try
        {
            if (this.rsINT 	!= null)  		this.rsINT.close();
            if (this.rsStmtINT != null)    	this.rsStmtINT.close();

            this.rsINT 		= null;
            this.rsStmtINT 	= null;
            
            this.rsStmtINT 	= conn.createStatement(); // statement-object für result-set
            this.rsINT 		= this.rsStmtINT.executeQuery(pSql);
        }
        catch (SQLException e)
        {
            throw new pdException("MyDBService:_OpenResultSetINT: -->"+e.getLocalizedMessage());
        }
    }

    


    public void CloseResultSet() throws pdException
    {
        try
        {
            if (this.rs 	!= null)    	this.rs.close();
            if (this.rsStmt != null)  	this.rsStmt.close();

            this.rs 	= null;
            this.rsStmt	= null;
        }
        catch (Exception e)
        {
            throw new pdException("MyDBService:CloseResultSet: -->"+e.getLocalizedMessage());
        }
    }

 
    public void CloseResultSetINT() throws pdException
    {
        try
        {
            if (this.rsINT 		!= null)   	this.rsINT.close();
            if (this.rsStmtINT 	!= null)  	this.rsStmtINT.close();

            this.rsINT 		= null;
            this.rsStmtINT	= null;
        }
        catch (Exception e)
        {
            throw new pdException("MyDBService:CloseResultSetINT: -->"+e.getLocalizedMessage());
        }
    }

    
    

    public void CloseResultSetALL() throws pdException
    {
        String cErrorMessage = "";
        
        try        			{      this.CloseResultSet();    }
        catch (Exception e) {      cErrorMessage += "Standard-Resultset could not been closed !/n";  }
        
        try        			{      this.CloseResultSetINT();  }
        catch (Exception e) {      cErrorMessage += "Internal-Resultset could not been closed !";     }
        
        if (!cErrorMessage.equals(""))
        {
            throw new pdException("MyDBService:CloseResultSetALL: -->"+cErrorMessage);
        }
        
    }

    

    // ohne zusatz ausführen
    public void ExecSqlohneZusatz(String pSql) throws pdException
    {
         _ExecSql(pSql, true);
    }

    // ohne zusatz ausführen
    public void ExecSqlohneZusatz(String pSql, boolean bCommit) throws pdException
    {
         _ExecSql(pSql, bCommit);
    }

    
    // mit bZusatz werden die sql-strings umgewandelt mit den zusatzfeldern, sonst werden sie
    // ausgeführt wie übergeben
    private void _ExecSql(String pSql, boolean bMitCommit) throws pdException
    {
        String cSql = pSql;
        String cSqlError = "";
        
        
        this.cSqlStatement = cSql;

        try
        {
            stmt = null;
            stmt = this.conn.createStatement();
            stmt.execute(cSql);
            stmt.close();
        }
        catch (SQLException e)
        {
            cSqlError += "MyDBService:_ExecSql(1):"+e.getMessage()+"\n";
        }

        if (cSqlError.equals("") && bMitCommit)
        {
            try
            {
                this.ownCommit();
                
            }
            catch (Exception e)
            {
                // falls commit schiefgeht, dann muss ein rollback probiert werden
                cSqlError += "MyDBService:_ExecSql(2):"+e.getMessage()+"\n";
            }
        }

        // wenn commit schiefgeht, dann rollback
        if (!cSqlError.equals(""))
        {
            try
            {
                this.ownRollBack();
            }
            catch (Exception e)
            {
                cSqlError += "MyDBService:_ExecSql:(3)"+e.getMessage()+"\n";
            }
        }

        // jetzt das statement schliessen
        try
        {
            stmt.close();
        }
        catch (Exception e)
        {
            cSqlError += "MyDBService:_ExecSql(4):"+e.getMessage()+"\n";
        }

        /*
         * zuerst wird der kritischere System-error behandelt
         */
        if (! cSqlError.equals(""))
            throw new pdException("MyDBService:_ExecSql(5)"+cSqlError);
        
   }

    



    // mit einbau der zusatzfelder (wenn vorhanden)
    public void ExecMultiSqlVektor(Vector oSql) throws pdException
    {
         _ExecMultiSql(pdBib.makeStringArrayFromVector(oSql));
    }


 
    // mit bZusatz werden die sql-strings umgewandelt mit den zusatzfeldern, sonst werden sie
    // ausgeführt wie übergeben
    // ES WIRD IMMER EIN COMMIT AUSGEFÜHRT
    private void _ExecMultiSql(String[] pSql) throws pdException
    {

        String cSqlHelp = "";

        this.cSqlStatement = ""; // hier wird der komplette satz an statements mit protokolliert
        
        String cSQLError = "";
        
        try
        {
            if (stmt != null)  stmt.close();
            
            stmt = null;
            stmt = this.conn.createStatement();

            for (int i = 0; i < pSql.length; i++)
            {
                cSqlHelp = pSql[i];

                this.cSqlStatement += (cSqlHelp + "/n"); // der ganze statement-satz wird
                stmt.execute(cSqlHelp);
             }

            stmt.close();
        }
        catch (SQLException e)
        {
            cSQLError += "MyDBService:_ExecMultiSql: (1) " + e.getMessage()+"\n";
        }

        // jetzt prüfen, ob commit oder rollback
        try
        {
            if (!cSQLError.equals(""))
                this.ownCommit();
            else
                this.ownRollBack();
        }
        catch (SQLException e)
        {
            cSQLError += "MyDBService:_ExecMultiSql: (2) " + e.getMessage()+"\n";
        }

        try
        {
              stmt.close();
        }
        catch (Exception e)
        {
            cSQLError += "MyDBService:_ExecMultiSql: (3) " + e.getMessage()+"\n";
        }
        
        if (!cSQLError.equals(""))
            throw new pdException("MyDBService:_ExecMultiSql"+cSQLError);

    }

    // hiermit wird bei nicht autocommit-connections das commit ausgelößt
    public void Commit() throws pdException
    {
        try
        {
            this.ownCommit();
        }
        catch (SQLException e)
        {
             throw new pdException("MyDBService:Commit:"+e.getLocalizedMessage());
        }
    }

    // hiermit wird bei nicht autocommit-connections das commit ausgelößt
    public void RollBack() throws pdException
    {
        try
        {
            this.ownRollBack();
        }
        catch (SQLException e)
        {
              throw new pdException("MyDBService:RollBack:"+e.getLocalizedMessage());
        }

    }

    
      
    
    // methode macht eine komplette einzelabfrage
    // übernimmt den sql-string und gibt einen
    // ergebnis-string zurück
    public String EinzelAbfrage(String cSqlString) throws pdException
    {
        String[][] cRueck = this.EinzelAbfrageInArray(cSqlString);
        if (cRueck == null || cRueck.length != 1 || cRueck[0].length != 1)
            throw new pdException("MyDBService:EinzelAbfrage:Error Querying");
        
        return (cRueck[0][0]);
    }


    // public methoden für einzelabfrage mit arrayrückgabe
    public String[][] EinzelAbfrageInArray(String cSqlString)  throws pdException
    {
        return _EinzelAbfrageInArray(cSqlString, "", false,-1);
    }

    public String[][] EinzelAbfrageInArray(String cSqlString, String cNullWert)  throws pdException
    {
        return _EinzelAbfrageInArray(cSqlString, cNullWert, false,-1);
    }

    public String[][] EinzelAbfrageInArray(String cSqlString, String cNullWert, int iMaxRowNumbers)  throws pdException
    {
        return _EinzelAbfrageInArray(cSqlString, cNullWert, false,iMaxRowNumbers);
    }

    
    // public methoden für einzelabfrage mit arrayrückgabe
    public String[][] EinzelAbfrageInArrayFormatiert(String cSqlString)  throws pdException
    {
        return _EinzelAbfrageInArray(cSqlString, "", true,-1);
    }

    public String[][] EinzelAbfrageInArrayFormatiert(String cSqlString, String cNullWert)  throws pdException
    {
        return _EinzelAbfrageInArray(cSqlString, cNullWert, true,-1);
    }

    public String[][] EinzelAbfrageInArrayFormatiert(String cSqlString, String cNullWert, int iMaxRowNumbers)  throws pdException
    {
        return _EinzelAbfrageInArray(cSqlString, cNullWert, true,iMaxRowNumbers);
    }


    
    // methode macht eine komplette einzelabfrage
    // übernimmt den sql-string und gibt einen vektor zurück,
    // dessen elemente aus den zeilenarrays betehen
    // bei null wird cNullWert zurückgegeben
    // wenn iMaxRowNumbers = -1, dann wird dieser parameter ignoriert
    private String[][] _EinzelAbfrageInArray(String cSqlString, String cNullWert,  boolean bFormatiert, int iMaxRowNumbers) throws pdException
    {
        String[][] 			cRueck 		= null;
        Vector<String[]> 	oTemp 		= new Vector<String[]>();
        int 				i 			= 0;
        int 				j 			= 0;
        boolean 			bHatZeilen 	= false;
        String  			cFehler 	= "";
        
        this.bMaxNumberOfRowsExceeded = false;

        int iCounter = 0;
        
        this.OpenResultSetINT(cSqlString);
        
        
        // dann wert abfragen
        cRueck = new String[0][0]; // leer, aber nicht null

 
        try
        {
             int iAnzahlSpalten = rsINT.getMetaData().getColumnCount();

             if (iAnzahlSpalten > 0)
             {
                 String[] cZeile = new String[iAnzahlSpalten];

                 while (rsINT.next())
                 {
                      bHatZeilen = true;
                      for (i = 0; i < iAnzahlSpalten; i++)
                      {
                          cZeile[i] = this.oDBFormat.get_RSString(this.rsINT,i + 1, bFormatiert);
                          if (cZeile[i] == null)
                              cZeile[i] = cNullWert;
                       }

                       // jetzt rein in den vector
                       oTemp.addElement(cZeile.clone());
                            
                       /*
                        * jetzt maxrows checken
                        */
                        if (iMaxRowNumbers>0)
                        {
                            iCounter ++;
                            if (iCounter>=iMaxRowNumbers)
                            {
                                this.bMaxNumberOfRowsExceeded = true;
                                break;
                            }
                        }
                        /*
                         * done
                         */
                 }                            
                 if (bHatZeilen)
                 {
                     // dann die elemente des vector in das rückgabearray
                     cRueck = new String[oTemp.size()][iAnzahlSpalten];
                     for (i = 0; i < oTemp.size(); i++)
                     {
                         cZeile = (String[]) oTemp.elementAt(i);

                         for (j = 0; j < iAnzahlSpalten; j++)
                         {
                             cRueck[i][j] = cZeile[j];
                         }
                      }
                  }
                }
         }
         catch (Exception e)
         {
              cFehler = "MyDBService:_EinzelAbfrageInArray:Error "+e.getLocalizedMessage()+" -> "+cSqlString;
         }

          
         try
         {
             this.CloseResultSetINT();
         }
         catch (pdException ex)
         {
             cFehler += "MyDBService:_EinzelAbfrageInArray:Error "+ex.getLocalizedMessage()+" -> "+cSqlString;
         }
         
         if (!cFehler.equals(""))
             throw new pdException(cFehler);

        return (cRueck);
    }




    
    // jetzt die publics für beide recordsets
    public String GetStringRS(String cFeldName,boolean bFormated)  throws pdException
    {
        String cRueck = null;
        try
        {
           cRueck = this.oDBFormat.get_RSString(this.rs,cFeldName,bFormated);
        }
        catch (Exception ex)
        {
            throw new pdException(ex.getLocalizedMessage());
        }
        return cRueck;

    }



    protected void finalize()
    {
        rs = null;
        rsINT = null;
        stmt = null;
        rsStmt = null;
        rsStmtINT = null;
    }



    /**
     * @return Returns the rs.
     */
    public ResultSet get_oRS()
    {
        return this.rs;
    }



	public MyConnection get_oMyConnection() 
	{
		return oMyConnection;
	}
	
	
   public void ownCommit() throws SQLException
    {
    	this.conn.commit();
        bibALL.reset_Trigger_Counter();
    }
    

    public void ownRollBack() throws SQLException
    {
    	this.conn.rollback();
        bibALL.reset_Trigger_Counter();
    }


	
}
