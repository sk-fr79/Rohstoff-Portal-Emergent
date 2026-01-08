package panter.gmbh.indep.dataTools;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import oracle.jdbc.internal.OraclePreparedStatement;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;

public class MyDBStatementPrepared 
{
	
//	public PreparedStatement STMT = null;
	public OraclePreparedStatement STMT = null;
	private String 			 m_sql = null;
	
	
	protected void finalize()
	{
		this.Close();
	}

	
	/**
	 * Prepared Statement initialisieren
	 * @author manfred
	 * @date 16.02.2018
	 *
	 * @param oConn
	 * @param sql
	 */
	public MyDBStatementPrepared(MyConnection oConn, String sql)
	{
		super();
		m_sql = sql;
		
		try 
		{
			this.STMT =(OraclePreparedStatement)oConn.get_oConnection().prepareStatement(sql);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			STMT = null;
		}
	}

	/**
	 * Prepared Statement initialisieren mit Standard-Connection aus der Toolbox
	 * @author manfred
	 * @date 26.09.2019
	 * @param sql
	 */
	public MyDBStatementPrepared(String sql)
	{
		this(bibALL.get_myDBToolBox().get_MyConnection(),sql);
	}

	
	
	
	/**
	 * execute des Prepared Statements
	 * @author manfred
	 * @date 16.02.2018
	 *
	 * @return
	 * @throws SQLException
	 */
	public ResultSet executeQuery() throws SQLException 
	{
		return this.STMT.executeQuery();
	}
	
	
	
	/**
	 * führt das INSERT/UPDATE/DELETE-Statement durch....
	 * @author manfred
	 * @date 16.02.2018
	 *
	 * @return
	 * @throws SQLException
	 */
	public int execute() throws SQLException
	{
		try
		{
			return this.STMT.executeUpdate();
		}
		catch (SQLException e)
		{
			//2015-09-17: rueckabwicklung andre-code
			DEBUG.System_println(" ");
			DEBUG.System_println(" ");
			DEBUG.System_println("========================================================================================================");
			DEBUG.System_println("Error executing Prepared Update SQL: "+S.NN(m_sql));
			DEBUG.System_println("========================================================================================================");
			e.printStackTrace();
			throw e;
		} 
	}
	
	
	
	/**
	 * Das PreparedStatement-Objekt
	 * @author manfred
	 * @date 16.02.2018
	 *
	 * @return
	 */
	public OraclePreparedStatement getPreparedStatement(){
		return STMT;
	}

	
	public int get_UpdateCount() throws SQLException
	{
		return this.STMT.getUpdateCount();
	}
	
	
	public void Close()
	{
		if (this.STMT != null)
		{
			try
			{
				this.STMT.close();
				this.STMT=null;
			}
			catch (Exception ex)
			{
				ex.printStackTrace();
			}
		}
	}


	public String get_sql() {
		return m_sql;
	}
	
}
