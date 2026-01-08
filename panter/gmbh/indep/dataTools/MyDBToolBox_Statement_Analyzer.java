package panter.gmbh.indep.dataTools;

import panter.gmbh.indep.bibALL;

public class MyDBToolBox_Statement_Analyzer
{
	
	private String cPlainSQL_Statement = null;
	private boolean bStatementCallsDeamons = true;
	

    /**
     * @param cSqlString (prueft die merker der sqlStatements und gibt die Info und bereinigte SQL-Strings zurueck)
     * @param pInsertFields
     * @param pInsertValues
     * @param pUpdateFields
     * @return
     */
	public MyDBToolBox_Statement_Analyzer(String cSQLString, String pInsertFields, String pInsertValues, String pUpdateFields)
	{
		super();
		
        //String cRueck = "";
        String cTestInsert_Update = "";
        
        String cSqlString = cSQLString.trim();
        
        //zuerst pruefen, ob die SQL_Daemonen geprueft werden
        if (cSQLString.indexOf(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION)>-1)
        {
        	this.bStatementCallsDeamons=false;
        }
        
        cSqlString = bibALL.ReplaceTeilString(cSqlString, MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_DAEMON_EXECUTION, "");
        
        
        if (cSqlString.startsWith(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS))
        {
        	cSqlString=cSqlString.substring(MyDBToolBox.MARKER_FOR_STATEMENTS_WITHOUT_ADDON_FIELDS.length());
        }
        else
        {
	        if (cSqlString.trim().length() > 6)
	        {
	            // keiner der werte darf = "" sein
	            if (!pInsertFields.equals("") && !pInsertValues.equals("") && !pUpdateFields.equals(""))
	            {
	                cSqlString = cSqlString.trim();
	
	                // jetzt von links auf insert / update prufen
	                cTestInsert_Update = cSqlString.substring(0, 6).toUpperCase();
	
	                if (cTestInsert_Update.equals("INSERT"))
	                {
	                    // postion erste klammer-auf suchen und 2. klammer auf
	                    // und nur die ersten 2 klammern berücksichtigen
	                    int iPos1 = -1;
	                    int iPos2 = -1;
	
	                    for (int i = 0; i < cSqlString.length(); i++)
	                    {
	                        if (cSqlString.substring(i, i + 1).equals("("))
	                        {
	                            if (iPos1 == -1)
	                            {
	                                iPos1 = i;
	                            }
	                            else
	                            {
	                                if (iPos2 == -1)
	                                {
	                                    iPos2 = i;
	                                }
	                            }
	                        }
	                    }
	
	                    cSqlString = cSqlString.substring(0, iPos1 + 1) + pInsertFields + "," + cSqlString.substring(iPos1 + 1, iPos2 + 1) + pInsertValues + "," + cSqlString.substring(iPos2 + 1);
	                }
	                else if (cTestInsert_Update.equals("UPDATE"))
	                {
	                    // position des "set"
	                    int iPosSet = -1;
	                    iPosSet = cSqlString.toUpperCase().indexOf(" SET ") + 5;
	
	                    cSqlString = cSqlString.substring(0, iPosSet) + " " + pUpdateFields + "," + cSqlString.substring(iPosSet);
	                }
	            }
	        }
        }
        this.cPlainSQL_Statement = cSqlString;
		
	}


	public String get_PlainSQL_Statement()
	{
		return cPlainSQL_Statement;
	}


	public boolean is_StatementCallsDeamons()
	{
		return bStatementCallsDeamons;
	}

}
