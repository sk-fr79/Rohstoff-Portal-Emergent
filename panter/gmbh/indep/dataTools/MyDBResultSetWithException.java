package panter.gmbh.indep.dataTools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.bibALL;

/**
 * Resultset which bubbles up Exception (instead of silently ignoring it in {@see MyDBResultSet}.
 * @author nils
 *
 */
public class MyDBResultSetWithException extends MyDBResultSet
{
	
	public MyDBResultSetWithException(MyConnection oConn, String cSQLQuery) throws SQLException {
		super(oConn, cSQLQuery);
		String[][] cErsetzungsTabelle = (String[][]) bibALL.getSessionValue("ERSETZUNGSTABELLE_SORT");
		try {
			initStatementNew(oConn, cSQLQuery, cErsetzungsTabelle);
		} catch (SQLException e) {
			throw new SQLException("Error executing: "+cSQLQuery, e);
		}
	}
	
	public MyDBResultSetWithException(MyConnection oConn, String cSQLQuery,String[][] cErsetzungsTabelle) throws SQLException
	{
		super(oConn, cSQLQuery, cErsetzungsTabelle);
		try {
			initStatementNew(oConn, cSQLQuery, cErsetzungsTabelle);
		} catch (SQLException e) {
			throw new SQLException("Error executing: "+cSQLQuery, e);
		}
	}


	/**
	 * Initialisiert das Statement nicht mehr, das macht nun die Methode {@link #initStatementNew()}, 
	 * die auch eine Exception wirft, die dann nach oben propagiert wird.
	 * @param oConn
	 * @param cSQLQuery
	 * @param cErsetzungsTabelle
	 * @throws SQLException 
	 */
	protected void initStatement(MyConnection oConn, String cSQLQuery,String[][] cErsetzungsTabelle) {

	}
	
	protected void initStatementNew(MyConnection oConn, String cSQLQuery,String[][] cErsetzungsTabelle) throws SQLException {
		MySTMT = new MyDBStatement(oConn);
		if (MySTMT.STMT != null)
		{
			String sQuery = this.ErsetzeTableDurchView(cSQLQuery,cErsetzungsTabelle);
			RS = MySTMT.STMT.executeQuery(sQuery);
			
			if (bibALL.get_bDebugMode())
			{
				DEBUG.System_println(sQuery, DEBUG.DEBUG_FLAG_SQL_QUERY);
			}
		}
	}
}
