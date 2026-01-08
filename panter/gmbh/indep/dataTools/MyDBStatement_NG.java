package panter.gmbh.indep.dataTools;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;

public class MyDBStatement_NG  {
	
	public Statement 				STMT = null;
	public PreparedStatement     	pSTMT = null;

	private MyConnection 			conn = null; 
	
	private int   					iUpdateCount = 0;
	
	protected void finalize() {
		this.Close();
	}

	
	public MyDBStatement_NG(MyConnection oConn)
	{
		super();
		this.conn = oConn;
		
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

	
	
	public ResultSet executeQuery(String cSQL) throws SQLException  {
		try {
			this.STMT =this.conn.get_oConnection().createStatement();
			ResultSet result = this.STMT.executeQuery(cSQL);
			this.iUpdateCount = this.STMT.getUpdateCount();
			this.killStatement(STMT);
			return result;
		} catch (SQLException e){
			SQLException e2 = new SQLException("Error executing SQL: "+S.NN(cSQL), e);
			e2.printStackTrace();
			this.killStatement(STMT);
			throw e2;
		} 
	}
	
	
	
	public boolean execute(String cSQL) throws SQLException {
		try {
			this.STMT =this.conn.get_oConnection().createStatement();
			boolean bRueck = this.STMT.execute(cSQL);
			this.iUpdateCount = this.STMT.getUpdateCount();
			this.killStatement(STMT);
			return bRueck;
		} catch (SQLException e){
			SQLException e2 = new SQLException("Error executing SQL: "+S.NN(cSQL), e);
			e2.printStackTrace();
			this.killStatement(STMT);
			throw e2;
		} 

	}


	public ResultSet executeQuery(SqlString4Prep oSQL) throws SQLException, myException  {
		try {
			this.pSTMT =this.conn.get_oConnection().prepareStatement(oSQL.get_SQL_plain());
			int iCounter = 1;
			for (Object ob: oSQL.get_arrayPlaceHolders()) {
				this.inject(iCounter, ob);
				iCounter++;
			}
			ResultSet result = this.pSTMT.getResultSet();
			this.iUpdateCount = this.pSTMT.getUpdateCount();
			this.killStatement(pSTMT);
			return result;
		} catch (SQLException e){
			SQLException e2 = new SQLException("Error executing SQL: "+S.NN(oSQL.get_SQL_plain()), e);
			e2.printStackTrace();
			this.killStatement(STMT);
			throw e2;
		} 
	}
	
	
	
	public boolean execute(SqlString4Prep oSQL) throws SQLException, myException {
		try {
			this.pSTMT =this.conn.get_oConnection().prepareStatement(oSQL.get_SQL_plain());
			int iCounter = 1;
			for (Object ob: oSQL.get_arrayPlaceHolders()) {
				this.inject(iCounter, ob);
				iCounter++;
			}
			boolean bRueck = this.pSTMT.execute();
			this.iUpdateCount = this.pSTMT.getUpdateCount();
			this.killStatement(pSTMT);
			return bRueck;
		} catch (SQLException e){
			SQLException e2 = new SQLException("Error executing SQL: "+S.NN(oSQL.get_SQL_plain()), e);
			e2.printStackTrace();
			this.killStatement(STMT);
			throw e2;
		} 
	}

	
	private void inject(int position, Object ob) throws SQLException, myException {
		if (ob instanceof String) {
			this.pSTMT.setNString(position, (String)ob);
		} else if (ob instanceof Integer) {
			this.pSTMT.setInt(position, (Integer)ob);
		} else if (ob instanceof Long) {
			this.pSTMT.setLong(position, (Long)ob);
		} else if (ob instanceof BigDecimal) {
			this.pSTMT.setBigDecimal(position, (BigDecimal)ob);
		} else {
			throw new myException(this,"allowed ObjectTypes:  String, Long, Integer, BigDecimal !!");
		}

	}
	
	
	private void killStatement(Statement stmt) {
		try {
			if (stmt!=null) {
				stmt.close();
				stmt=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void killStatement(PreparedStatement stmt) {
		try {
			if (stmt!=null) {
				stmt.close();
				stmt=null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public int get_UpdateCount() throws SQLException {
		return this.iUpdateCount;
	}
	
	
	public void Close() 	{
		this.killStatement(this.STMT);
		this.killStatement(this.pSTMT);
	}
	
}
