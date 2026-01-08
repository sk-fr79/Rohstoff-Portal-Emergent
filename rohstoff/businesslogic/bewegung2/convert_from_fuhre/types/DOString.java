package rohstoff.businesslogic.bewegung2.convert_from_fuhre.types;

import java.sql.SQLException;

import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyDBStatementPrepared;

public class DOString extends DataObject {

	
	// wenn Raw, wird der String direkt übergeben (z.B. für SQL-Funktionen)
	boolean _isRaw = false;
	
	public DOString(IF_Field field, String value) {
		super(field);
		this.sValue = value;
	}
	
	public DOString(IF_Field field, String value, String delimiter) {
		super(field);
		this.sValue = value;
		this.sDelimiter = delimiter;
	}
	
	
	public DOString(IF_Field field){
		super(field);
	}
	
	
	public void setValue(String Value) {
		this.sValue = Value;
	}

	@Override
	public DOString setRaw(boolean isRaw){
		_isRaw = isRaw;
		return this;
	}
	@Override
	public boolean isRaw() {
		return _isRaw;
	}
	
	
	
	@Override
	/**
	 * gibt den Wert mit umgebenden Hochkomma (für SQL-Statements) zurück
	 * bei null wird der String "null" zurückgegeben.
	 */
	public Object Value() {
		// TODO Auto-generated method stub
		if (sValue != null){
			return sDelimiter + sValue.replaceAll("'", "''")+ sDelimiter;
		} else {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#Value(java.lang.String)
	 */
	@Override
	public Object Value(Object ValueWhenEmpty) {
		
		if (sValue != null){
			return sDelimiter + sValue.replaceAll("'", "''")+ sDelimiter;
		} else {
			if (ValueWhenEmpty instanceof String){
				return ValueWhenEmpty;
			} else {
				return null;
			}
			
		}
	}

	
	
	
	
	
	@Override
	/**
	 * Gibt den Wert ohne umgebende Hochkomma zurück
	 * Bein null wird der Wert (nicht der String) null zurückgegeben.
	 */
	public String ValuePlain() {
		return sValue;
	}

	
	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#ValuePlain(java.lang.String)
	 */
	@Override
	public String ValuePlain(String ValueWhenEmpty) {
		if (sValue != null ){
			return sValue;
		} else {
			return ValueWhenEmpty;
		}
	}
	
	
	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DataObject#CopyFrom(rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DataObject)
	 */
	@Override
	public void CopyFrom(DataObject o) {
		this.setValue(o.ValuePlain());
	}
	

	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#SetPreparedStatementValue(java.sql.PreparedStatement, int)
	 */
//	@Override
//	public int SetPreparedStatementValue(MyDBStatementPrepared stmt, int idx) {
//		if(sValue != null && (sValue.toUpperCase().contains(".NEXTVAL") || sValue.toUpperCase().contains(".CURRVAL")) ){
//			return idx;
//		} else {
//			try {
//				if (ValuePlain() == null){
//					stmt.getPreparedStatement().setNull(idx, java.sql.Types.NVARCHAR);
//				}else {
//					stmt.getPreparedStatement().setString(idx, ValuePlain());
//				}
//				
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		return idx+1;
//	}
}
