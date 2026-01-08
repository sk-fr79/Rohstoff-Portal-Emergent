package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.exceptions.myException;

public abstract class REC_Base {

	
	String 				 	_TableName = "";
	MySqlStatementBuilder 	_stmtBuilder = null;
	

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
	public String getSQLInsert(){
		
		_stmtBuilder = new MySqlStatementBuilder(_TableName);
		
		AddStatements();
			    
		String sInsert = null;
	    sInsert = _stmtBuilder.get_CompleteInsertString(_TableName, bibE2.cTO());
		return sInsert;
	}

	
	protected void AddStatement(DataObject data){
		try {
			_stmtBuilder.addSQL_Paar(data.Name(), data.Value());
		} catch (myException e) {
			// 
			DEBUG.System_println(_TableName + "::AddStatement Fehler beim erstellen des Wertes für " + data.Name(), DEBUG.DEBUG_FLAG_DIVERS1);
		}
	}
	
	
	/**
	 * fügt die einzelnen Spalten zum Gesamtstatement zusammen
	 * Beinhaltet nur eine Menge von AddStatement()-Aufrufen 
	 * @author manfred
	 * @date   25.11.2013
	 */
	public abstract void AddStatements();
	
	
	

	
}
