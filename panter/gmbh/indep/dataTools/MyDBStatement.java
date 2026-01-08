package panter.gmbh.indep.dataTools;

import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;

public class MyDBStatement 
{
	public Statement STMT = null;

	protected void finalize()
	{
		this.Close();
	}

	
	public MyDBStatement(MyConnection oConn)
	{
		super();
		try 
		{
			this.STMT =oConn.get_oConnection().createStatement();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			STMT = null;
		}
	}

	
	
	public ResultSet executeQuery(String cSQL) throws SQLException 
	{
		return this.STMT.executeQuery(cSQL);
	}
	
	
	
	public boolean execute(String cSQL) throws SQLException
	{
		try
		{
//			if (S.isFull(bibALL.get_CharacterEncoding4Oracle()))
//			{
//				cSQL = new String (cSQL.getBytes(bibALL.get_CharacterEncoding4Oracle()));
//			}
			
			return this.STMT.execute(cSQL);
		}
		catch (SQLException e)
		{
			//2015-09-17: rueckabwicklung andre-code
			DEBUG.System_println(" ");
			DEBUG.System_println(" ");
			DEBUG.System_println("========================================================================================================");
			DEBUG.System_println("Error executing SQL: "+S.NN(cSQL));
			DEBUG.System_println("========================================================================================================");
			e.printStackTrace();
			throw e;
//
//			SQLException e2 = new SQLException("Error executing SQL: "+S.NN(cSQL), e);
//			e2.printStackTrace();
//			throw e2;
		} 
//		catch (UnsupportedEncodingException e)
//		{
//			e.printStackTrace();
//			throw new SQLException("False Oracle-Encoding:"+e.getLocalizedMessage());
//		}
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
	
}
