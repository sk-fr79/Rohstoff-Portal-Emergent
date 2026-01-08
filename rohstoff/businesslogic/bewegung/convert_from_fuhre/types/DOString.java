package rohstoff.businesslogic.bewegung.convert_from_fuhre.types;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import panter.gmbh.indep.dataTools.MyDBStatementPrepared;

public class DOString extends DataObject {

	public DOString(String name, String value) {
		super(name);
		this.sValue = value;
	}
	
	public DOString(String name, String value, String delimiter) {
		super(name);
		this.sValue = value;
		this.sDelimiter = delimiter;
	}
	
	
	public DOString(String name){
		super(name);
	}
	
	
	public void setValue(String Value) {
		this.sValue = Value;
	}

	
	@Override
	/**
	 * gibt den Wert mit umgebenden Hochkomma (für SQL-Statements) zurück
	 * bei null wird der String "null" zurückgegeben.
	 */
	public String Value() {
		// TODO Auto-generated method stub
		if (sValue != null){
			return sDelimiter + sValue.replaceAll("'", "''")+ sDelimiter;
		} else {
			return "null";
		}
	}

	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#Value(java.lang.String)
	 */
	@Override
	public String Value(String ValueWhenEmpty) {
		if (sValue != null){
			return sDelimiter + sValue.replaceAll("'", "''")+ sDelimiter;
		} else {
			return ValueWhenEmpty;
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
	@Override
	public int SetPreparedStatementValue(MyDBStatementPrepared stmt, int idx) {
		if(sValue != null && (sValue.toUpperCase().contains(".NEXTVAL") || sValue.toUpperCase().contains(".CURRVAL")) ){
			return idx;
		} else {
			try {
				if (ValuePlain() == null){
					stmt.getPreparedStatement().setNull(idx, java.sql.Types.NVARCHAR);
				}else {
					stmt.getPreparedStatement().setString(idx, ValuePlain());
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return idx+1;
	}
}
