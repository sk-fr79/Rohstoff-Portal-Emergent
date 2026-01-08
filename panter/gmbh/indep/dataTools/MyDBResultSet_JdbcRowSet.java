package panter.gmbh.indep.dataTools;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.bibALL;

import com.sun.rowset.JdbcRowSetImpl;

/**
 * Test-Rowset
 * @author manfred
 * @date   18.04.2012
 */
@Deprecated
public class MyDBResultSet_JdbcRowSet 
{
	public  JdbcRowSet  m_RowSet = null;
	
	public ResultSet 		RS = null;
	MyDBStatement 	MySTMT = null;
	
	
	protected void finalize()
	{
		this.Close();
	}
	
	
	public MyDBResultSet_JdbcRowSet(MyConnection oConn, String cSQLQuery) throws SQLException
	{
		//falls nix anderes gesagt wird, dann automatisch ersetzungstabelle aus session holen
		this(oConn,cSQLQuery,(String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT"));
	}


	
	public MyDBResultSet_JdbcRowSet(MyConnection oConn, String cSQLQuery,String[][] cErsetzungsTabelle) throws SQLException
	{

		
		MySTMT = new MyDBStatement(oConn);
		
		if (MySTMT.STMT != null)
		{
			try 
			{
				RS = MySTMT.STMT.executeQuery(this.ErsetzeTableDurchView(cSQLQuery,cErsetzungsTabelle));
				m_RowSet = new JdbcRowSetImpl(RS);
				
				if (bibALL.get_bDebugMode())
				{
					DEBUG.System_println(this.ErsetzeTableDurchView(cSQLQuery,cErsetzungsTabelle), DEBUG.DEBUG_FLAG_SQL_QUERY);
				}
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
				System.out.println(cSQLQuery);
				RS = null;
				m_RowSet = null;
				MySTMT.Close();
			}
		}
		
		
		
//		m_RowSet = new JdbcRowSetImpl(oConn.get_oConnection());
//		
//		
//		try 
//		{
//			m_RowSet.setType(ResultSet.TYPE_FORWARD_ONLY);
//			
//			m_RowSet.setCommand(this.ErsetzeTableDurchView(cSQLQuery,cErsetzungsTabelle));
//			m_RowSet.execute();
//		
//		} 
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//			m_RowSet = null;
//		}
		
	}

	
	
	
    // methode, die auf das bestehendes recordset 
    // eine feldabfragen macht, wobei ein null-wert abgefangen wird
    // und als standard-wert zurückkommt
    public String GetStringRS(String cFeldName)
    {
        String cRetWert = "";

        try
        {
            cRetWert = m_RowSet.getString(cFeldName);

            if (m_RowSet.wasNull())
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
		if ( m_RowSet != null)
		{
			try
			{
				m_RowSet.close();
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}

	
	


	
	
    // methode tauscht (wenn vorhandene ersetzungstabelle) tablenamen durch viewnamen aus
    private String ErsetzeTableDurchView(String cQuerySQL, String[][] cErsetzungstabelle)
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


}
