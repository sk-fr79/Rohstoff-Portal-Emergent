package rohstoff.businesslogic.bewegung.convert_from_fuhre.types;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import panter.gmbh.indep.dataTools.MyDBStatementPrepared;

public class DOBigDecimal extends DataObject {

	public DOBigDecimal(String name, BigDecimal Value) {
		super(name);
		this.bdValue = Value;
	}
	
	
	public DOBigDecimal(String name){
		super(name);
	}
	
	
	public DOBigDecimal (BigDecimal value){
		this.bdValue = value;
	}
	

	
	public void setValue(BigDecimal value)  {
		this.bdValue = value;
	}
	
	public void setValue(String value) {
		BigDecimal bd = null;
		if (value != null){
			try {
				bd = new BigDecimal(value);
			} catch (NumberFormatException e) {
//				throw new myException("BigDecimal konnte nicht ermittelt werden.", e);
				bd = null;
			} 
		}
		this.bdValue = bd;
	}
	
	
	
	@Override
	public String Value() {
		String sRet = null;
		if (bdValue != null){
			sRet = bdValue.toPlainString();
		}
		return sRet;
	}
	
	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#Value(java.lang.String)
	 */
	@Override
	public String Value(String ValueWhenEmpty) {
		String sRet = null;
		if (bdValue != null){
			sRet = bdValue.toPlainString();
		} else {
			return sRet = ValueWhenEmpty;
		}
		return sRet;	
	}
	
	
	
	@Override
	/**
	 * Entspricht Value()
	 */
	public String ValuePlain() {
		return this.Value();
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
			if (this.bdValue == null){
				stmt.getPreparedStatement().setNull(idx, java.sql.Types.DECIMAL);
			} else {
				stmt.getPreparedStatement().setBigDecimal(idx, this.bdValue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idx+1;
	}
}
