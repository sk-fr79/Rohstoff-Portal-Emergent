package panter.gmbh.indep.dataTools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.bibALL;


public class MyDBResultSet 
{
	public 	ResultSet 		RS = null;
	protected MyDBStatement 	MySTMT = null;
	

	
	protected void finalize()
	{
		this.Close();
	}
	

	//neuer leerer constuctor
	public MyDBResultSet() {
	}
	
	

	/**
	 * nutzt die Standard-Ersetzungstabelle
	 * @param oConn
	 * @param cSQLQuery
	 */
	public MyDBResultSet(MyConnection oConn, String cSQLQuery)
	{
		//falls nix anderes gesagt wird, dann automatisch ersetzungstabelle aus session holen
		String[][] cErsetzungsTabelle = (String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT");
		initStatement(oConn, cSQLQuery, cErsetzungsTabelle);
	}

	
	/**
	 * 
	 * @param oConn
	 * @param cSQLQuery
	 * @param cErsetzungsTabelle
	 */
	public MyDBResultSet(MyConnection oConn, String cSQLQuery,String[][] cErsetzungsTabelle)
	{
		initStatement(oConn, cSQLQuery, cErsetzungsTabelle);
	}
	
	
	/**
	 * Initialisiert das Statement
	 * @param oConn
	 * @param cSQLQuery
	 * @param cErsetzungsTabelle
	 */
	protected void initStatement(MyConnection oConn, String cSQLQuery,String[][] cErsetzungsTabelle){
		MySTMT = new MyDBStatement(oConn);
		if (MySTMT.STMT != null)
		{
			try 
			{
				String sQuery = this.ErsetzeTableDurchView(cSQLQuery,cErsetzungsTabelle);
				RS = MySTMT.STMT.executeQuery(sQuery);
				
				if (bibALL.get_bDebugMode())
				{
					DEBUG.System_println(sQuery, DEBUG.DEBUG_FLAG_SQL_QUERY);
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				System.out.println(cSQLQuery);
				RS = null;
				MySTMT.Close();
			}
		}
	}
	
	
    // methode, die auf das bestehendes recordset 
    // eine feldabfragen macht, wobei ein null-wert abgefangen wird
    // und als standard-wert zurückkommt
    public String GetStringRS(String cFeldName)
    {
        String cRetWert = "";

        try
        {
            cRetWert = this.RS.getString(cFeldName);

            if (this.RS.wasNull())
            {
                cRetWert = "";
            }
        }
        catch (Exception e)
        {
            cRetWert = "@@@@ERROR READING";
        }
 
        return (cRetWert);
    }

    
	
	public void Close()
	{
		if (RS != null)
		{
			try
			{
				RS.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
			MySTMT.Close();
		}
	}

	
	
//	private String ErsetzeTableDurchView(String cQuerySQL, String[][] cErsetzungstabelle) {
//        String cRueck = cQuerySQL;
//        if (bibALL.get_RECORD_USER() != null){
//	        String cErsetzung = "V" + bibALL.get_ID_MANDANT() + "_";
//	        cRueck = bibALL.ReplaceTeilString(cQuerySQL, "JT_", cErsetzung, true);
//        }
//        return cRueck;
//	}

	
	
	// methode tauscht (wenn vorhandene ersetzungstabelle) tablenamen durch viewnamen aus
	private String ErsetzeTableDurchView_OLD(String cQuerySQL, String[][] cErsetzungstabelle)
    {
        String cRueck = cQuerySQL;
        
        if (cErsetzungstabelle != null)
        {
            for (int i = 0; i < cErsetzungstabelle.length; i++)
            {
                cRueck = bibALL.ReplaceTeilString(cRueck, cErsetzungstabelle[i][0], cErsetzungstabelle[i][1], true);
            }
        }
        return cRueck;
    }

    
	/**
	 * 
	 * @author manfred
	 * @date   21.01.2014
	 * @param cQuerySQL
	 * @param cErsetzungstabelle
	 * @return
	 */
    protected String ErsetzeTableDurchView(String cQuerySQL, String[][] cErsetzungstabelle)
    {
        String cRueck = cQuerySQL;
        
        if (cErsetzungstabelle != null)
        {
        	
        	// die REGEX-PATTERN laden bzw. neu aufbauen
        	Hashtable<String, Pattern> htPattern = null;// new Hashtable<String, Pattern>();
        	htPattern = (Hashtable<String, Pattern>) bibALL.getSessionValue("ERSETZUNGSTABELLE_TABLEPATTERN");
        	if (htPattern == null){
        		htPattern = new Hashtable<String, Pattern>();
        		// Pattern aufbauen
                for (int i = 0; i < cErsetzungstabelle.length; i++)
                {
                	Pattern pattern = Pattern.compile(cErsetzungstabelle[i][0],Pattern.CASE_INSENSITIVE | Pattern.LITERAL );
                	htPattern.put(cErsetzungstabelle[i][0], pattern);
                }
                bibALL.setSessionValue("ERSETZUNGSTABELLE_TABLEPATTERN", htPattern);
        	}
        	
        	
        	cRueck 		= cQuerySQL;
        	
            for (int i = 0; i < cErsetzungstabelle.length; i++)
            {
                String cSuchen		= cErsetzungstabelle[i][0] ;
                String cErsetzen	= cErsetzungstabelle[i][1] ;

                
                // In case you would like to ignore case sensitivity you could use this
                // statement
                // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
                if (cQuerySQL != null && cSuchen != null && cErsetzen != null){
            		Pattern pattern = htPattern.get(cSuchen);
            		Matcher matcher = pattern.matcher(cRueck);
            		if (matcher.find()){
            			cRueck = matcher.replaceAll(Matcher.quoteReplacement(cErsetzen));
            		}
                }
            }
        }
        
        //20171110: martin: debug-punkt eingefuegt
		if (bibALL.get_bDebugMode()) {
			DEBUG.System_println(cRueck, DEBUG.DEBUG_FLAGS.SHOW_VIEW_TRANSLATED_SQL_QUERYS.name());
		}

        
        return cRueck;
    }

    
    

}
