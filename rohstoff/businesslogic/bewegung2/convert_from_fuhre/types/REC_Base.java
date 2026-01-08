package rohstoff.businesslogic.bewegung2.convert_from_fuhre.types;

import java.sql.SQLException;
import java.util.Vector;

import panter.gmbh.Echo2.bibE2;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.S;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.dataTools.MyDBStatementPrepared;
import panter.gmbh.indep.dataTools.MySqlStatementBuilder;
import panter.gmbh.indep.dataTools.RECORD2.Rec21;
import panter.gmbh.indep.dataTools.RECORD2.SqlStringExtended;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.businesslogic.bewegung2.convert_from_fuhre.conv_helper;

public abstract class REC_Base {

	
	protected _TAB 						_table = null;
	
	protected MySqlStatementBuilder 	_stmtBuilder 		= null;
	protected Vector<DataObject>    	m_vDataObjects 		= new Vector<>();
	MyDBStatementPrepared 				stmt 				= null;

	Rec21 _record = null;

	protected conv_helper _helper = null;
	
	
	public REC_Base(){
		this(null);
	}
	
	
	public REC_Base(_TAB tab){
		_table = tab;

	}
	
	
	

	
	public void debugParamData(){
		DEBUG.System_println("Debug: " +_table.fullTableName(), DEBUG.DEBUG_FLAG_SQL_EXEC);

		for (DataObject o: m_vDataObjects){
			if (!S.isEmpty(o.ValuePlain())){
				DEBUG.System_println(o._field.tnfn() + ":"+o.ValuePlain("?"), DEBUG.DEBUG_FLAG_SQL_EXEC);
			}
		}
	}
	
	
	
	
	public SqlStringExtended getInsertStatement() throws myException{
		
		// neuen Record generieren
		_record = new Rec21(_table);
		SqlStringExtended _sqlExt = null;
		
		// Feldwerte des Records setzen
		for (DataObject o : m_vDataObjects){
			try {
				if (o.isRaw()){
					_record._put_raw_value(o._field,o.ValuePlain(), false);
				} else {
					_record._setNewVal(o.Field(), o.Value() , bibMSG.MV());
				}
				
			} catch (myException e) {
				e.printStackTrace();
			}
			
//			DEBUG.System_PRINT_MY_RECORD(_record);
			
			
		}
		
		
		// TEST mit SqlStringExtended
		try {
			_sqlExt = _record.createSaveString(_helper.getDBToolbox());
					
		} catch (myException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return _sqlExt;
		
		


	}
	
//	
//	public void setPrepStatementValues() {
//		int counter = 1;
//		
//		try {
//			stmt.STMT.clearParameters();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		for (DataObject o: m_vDataObjects){
//			counter = o.SetPreparedStatementValue(stmt, counter);
//		}
//	}
	
	
	/**
	 * fügt die einzelnen Spalten zum Gesamtstatement zusammen
	 * Beinhaltet nur eine Menge von AddStatement()-Aufrufen 
	 * @author manfred
	 * @date   25.11.2013
	 */
	
	protected abstract void initFieldList();
	
	

	
}
