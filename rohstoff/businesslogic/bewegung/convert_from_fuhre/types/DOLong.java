package rohstoff.businesslogic.bewegung.convert_from_fuhre.types;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import panter.gmbh.indep.dataTools.MyDBStatementPrepared;

public class DOLong extends DataObject {
	
	public DOLong(String name, Long value) {
		super(name);
		this.lValue = value;
	}
	
	
	public DOLong(String name){
		super(name);
	}
	
	public DOLong (long value){
		this.lValue = new Long(value);
	}
	
	public DOLong (int value){
		this.lValue = new Long(value);
	}
	
	public DOLong(Long Value){
		this.lValue = Value;
	}
	
	public void setValue(Long Value){
		lValue = Value;
	}
	
	public void setValue(long Value){
		lValue = Value;
	}
	
	
	public void setValue(String Value){
		if (Value != null){
			lValue = Long.parseLong(Value);
		}
	}
	
	
	@Override
	public String Value() {
		String sRet = null;
		if (lValue != null){
			sRet = lValue.toString(); 
		}
		return sRet;
	}
	
	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#Value(java.lang.String)
	 */
	@Override
	public String Value(String ValueWhenEmpty) {
		String sRet = null;
		if (lValue != null){
			sRet = lValue.toString(); 
		} else {
			sRet = ValueWhenEmpty;
		}
		return sRet;
	}
	
	@Override
	/*
	 * Entspricht Value()
	 */
	public String ValuePlain() {
		return Value();
	}

	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#ValuePlain(java.lang.String)
	 */
	@Override
	public String ValuePlain(String ValueWhenEmpty) {
		return Value(ValueWhenEmpty);
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
		try {
			if (this.lValue == null){
				stmt.getPreparedStatement().setNull(idx, java.sql.Types.DOUBLE);
			} else {
				stmt.getPreparedStatement().setLong(idx, this.lValue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idx+1;
	}

}
