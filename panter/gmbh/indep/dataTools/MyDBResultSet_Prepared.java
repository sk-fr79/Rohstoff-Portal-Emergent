package panter.gmbh.indep.dataTools;

import java.sql.SQLException;
import java.util.Vector;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.RECORD2.ParamTypes.ParamDataObject;
import rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject;

/**
 * Resultset für Prepared Statements
 * @author manfred
 * @date 23.02.2018
 *
 */
public class MyDBResultSet_Prepared extends MyDBResultSet
{
	//public 	ResultSet 					RS = null;
	protected MyDBStatementPrepared 	MySTMT = null;
	Vector<DataObject>        			_Parameters = null; 
	
//	protected void finalize() {
//		this.Close();
//	}
	

	/**
	 * nutzt die Standard-Ersetzungstabelle
	 * @param oConn
	 * @param cSQLQuery
	 */
	public MyDBResultSet_Prepared(MyConnection oConn, String cSQLQuery, Vector<ParamDataObject> parameters) {
		//falls nix anderes gesagt wird, dann automatisch ersetzungstabelle aus session holen
		String[][] cErsetzungsTabelle = (String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT");
		initStatement(oConn, cSQLQuery,parameters, cErsetzungsTabelle);
	}

	
	/**
	 * 
	 * @param oConn
	 * @param cSQLQuery
	 * @param cErsetzungsTabelle
	 */
	public MyDBResultSet_Prepared(MyConnection oConn, String cSQLQuery,Vector<ParamDataObject> parameters, String[][] cErsetzungsTabelle)	{
		initStatement(oConn, cSQLQuery, parameters, cErsetzungsTabelle);
	}
	
	
	/**
	 * Initialisiert das Statement
	 * @param oConn
	 * @param cSQLQuery
	 * @param cErsetzungsTabelle
	 */
	protected void initStatement(MyConnection oConn, String cSQLQuery,Vector<ParamDataObject> parameters,String[][] cErsetzungsTabelle) {
		
		String sQuery = this.ErsetzeTableDurchView(cSQLQuery,cErsetzungsTabelle);
		//**BUG: MySTMT = new MyDBStatementPrepared(oConn,cSQLQuery);      cSQLQuery hat keine ersetzungen
		MySTMT = new MyDBStatementPrepared(oConn,sQuery);

		if (MySTMT.STMT != null)
		{
			try 
			{
				// Parameter setzen
				int idx = 1;
				for (ParamDataObject o : parameters){
					o.SetPreparedStatementValue(MySTMT, idx++);
				}
				
				RS = MySTMT.executeQuery();
				
				if (bibALL.get_bDebugMode())
				{
					DEBUG.System_println(sQuery, DEBUG.DEBUG_FLAG_SQL_QUERY);
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				System.out.println(sQuery);
				RS = null;
				MySTMT.Close();
			}
		}
	}
	
//	
//    // methode, die auf das bestehendes recordset 
//    // eine feldabfragen macht, wobei ein null-wert abgefangen wird
//    // und als standard-wert zurückkommt
//    public String GetStringRS(String cFeldName)
//    {
//        String cRetWert = "";
//
//        try
//        {
//            cRetWert = this.RS.getString(cFeldName);
//
//            if (this.RS.wasNull())
//            {
//                cRetWert = "";
//            }
//        }
//        catch (Exception e)
//        {
//            cRetWert = "@@@@ERROR READING";
//        }
// 
//        return (cRetWert);
//    }
//
//    
	
	@Override
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


    
//	/**
//	 * 
//	 * @author manfred
//	 * @date   21.01.2014
//	 * @param cQuerySQL
//	 * @param cErsetzungstabelle
//	 * @return
//	 */
//    protected String ErsetzeTableDurchView(String cQuerySQL, String[][] cErsetzungstabelle)
//    {
//        String cRueck = cQuerySQL;
//        
//        if (cErsetzungstabelle != null)
//        {
//        	
//        	// die REGEX-PATTERN laden bzw. neu aufbauen
//        	Hashtable<String, Pattern> htPattern = null;// new Hashtable<String, Pattern>();
//        	htPattern = (Hashtable<String, Pattern>) bibALL.getSessionValue("ERSETZUNGSTABELLE_TABLEPATTERN");
//        	if (htPattern == null){
//        		htPattern = new Hashtable<String, Pattern>();
//        		// Pattern aufbauen
//                for (int i = 0; i < cErsetzungstabelle.length; i++)
//                {
//                	Pattern pattern = Pattern.compile(cErsetzungstabelle[i][0],Pattern.CASE_INSENSITIVE | Pattern.LITERAL );
//                	htPattern.put(cErsetzungstabelle[i][0], pattern);
//                }
//                bibALL.setSessionValue("ERSETZUNGSTABELLE_TABLEPATTERN", htPattern);
//        	}
//        	
//        	
//        	cRueck 		= cQuerySQL;
//        	
//            for (int i = 0; i < cErsetzungstabelle.length; i++)
//            {
//                String cSuchen		= cErsetzungstabelle[i][0] ;
//                String cErsetzen	= cErsetzungstabelle[i][1] ;
//
//                
//                // In case you would like to ignore case sensitivity you could use this
//                // statement
//                // Pattern pattern = Pattern.compile("\\s+", Pattern.CASE_INSENSITIVE);
//                if (cQuerySQL != null && cSuchen != null && cErsetzen != null){
//            		Pattern pattern = htPattern.get(cSuchen);
//            		Matcher matcher = pattern.matcher(cRueck);
//            		if (matcher.find()){
//            			cRueck = matcher.replaceAll(Matcher.quoteReplacement(cErsetzen));
//            		}
//                }
//            }
//        }
//        
//        //20171110: martin: debug-punkt eingefuegt
//		if (bibALL.get_bDebugMode()) {
//			DEBUG.System_println(cRueck, DEBUG.DEBUG_FLAGS.SHOW_VIEW_TRANSLATED_SQL_QUERYS.name());
//		}
//
//        
//        return cRueck;
//    }

    
    

}
