package rohstoff.businesslogic.bewegung.convert_from_fuhre.types;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBStatementPrepared;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;

public abstract class REC_Base {

	
	protected String 				 	_TableName 			= "";
	protected MySqlStatementBuilder 	_stmtBuilder 		= null;
	protected Vector<DataObject>    	m_vDataObjects 		= new Vector<>();
	MyDBStatementPrepared 				stmt 				= null;

	public REC_Base(){
		this("");
	}
	
	
	public REC_Base(String sTableName){
		_TableName = sTableName;
	}
	
	
	
	/**
	 * gibt das Insertstatement für dieses Bewegungsobjekt zurück
	 * @author manfred
	 * @date   07.08.2012
	 * @return
	 */
//	public String getSQLInsert(){
//		
//		_stmtBuilder = new MySqlStatementBuilder(_TableName);
//		
//		AddStatements();
//			    
//		String sInsert = null;
//	    sInsert = _stmtBuilder.get_CompleteInsertString(_TableName, bibE2.cTO());
//		return sInsert;
//	}

	
	
	
	public String getSQLInsert(){
		String sInsert = null;

		MySqlStatementBuilder m_stmtBuilder = new MySqlStatementBuilder(_TableName);
		
		for (DataObject o : m_vDataObjects){
			try {
				m_stmtBuilder.addSQL_Paar(o.Name(), o.Value());
			} catch (myException e) {
				e.printStackTrace();
			}
		}
				    
	    sInsert = m_stmtBuilder.get_CompleteInsertString(_TableName, bibE2.cTO());
		return sInsert;
	}
	
	
	
	
//	protected void AddStatement(DataObject data){
//		try {
//			_stmtBuilder.addSQL_Paar(data.Name(), data.Value());
//		} catch (myException e) {
//			// 
//			DEBUG.System_println(_TableName + "::AddStatement Fehler beim erstellen des Wertes für " + data.Name(), DEBUG.DEBUG_FLAG_DIVERS1);
//		}
//	}
	
	
	
	public MyDBStatementPrepared getPrepInsertStatement(){
		
		
		StringBuilder fields = new StringBuilder();
		for ( DataObject o: m_vDataObjects){
			fields.append(o.Name());
			fields.append(",");
		}
		int last = fields.lastIndexOf(",");
		fields.delete(last,last+1);
		
		
		StringBuilder values = new StringBuilder();
		for ( DataObject o: m_vDataObjects){
			if (o instanceof DOString){
				if (o.Value().toUpperCase().contains(".NEXTVAL") ||  o.Value().toUpperCase().contains(".CURRVAL")){
					values.append(o.Value());
				} else values.append("?");
			} else {
				values.append("?");
			}
			values.append(",");
		}
		
		last = values.lastIndexOf(",");
		values.delete(last, last+1);

		String sSql = "INSERT INTO " +  bibE2.cTO() + "." + _TableName + "( " + fields.toString() + ") values ( " + values.toString() + ")";
		try {
			stmt =  new MyDBStatementPrepared( bibALL.get_myDBToolBox().get_MyConnection(),sSql);
			
			setPrepStatementValues();
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		
		return stmt;
	}
	
	
	public void setPrepStatementValues() {
		int counter = 1;
		
		try {
			stmt.STMT.clearParameters();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (DataObject o: m_vDataObjects){
			counter = o.SetPreparedStatementValue(stmt, counter);
		}
	}
	
	
	/**
	 * fügt die einzelnen Spalten zum Gesamtstatement zusammen
	 * Beinhaltet nur eine Menge von AddStatement()-Aufrufen 
	 * @author manfred
	 * @date   25.11.2013
	 */
//	public abstract void AddStatements();
	
	protected abstract void initFieldList();
	
	

	
}
