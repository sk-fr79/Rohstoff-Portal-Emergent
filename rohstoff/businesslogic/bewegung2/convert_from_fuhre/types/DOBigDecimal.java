package rohstoff.businesslogic.bewegung2.convert_from_fuhre.types;

import java.math.BigDecimal;
import java.sql.SQLException;

import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyDBStatementPrepared;

public class DOBigDecimal extends DataObject {

	public DOBigDecimal(IF_Field field, BigDecimal Value) {
		super(field);
		this.bdValue = Value;
	}
	
	
	public DOBigDecimal(IF_Field field){
		super(field);
	}
	
	
	public DOBigDecimal (BigDecimal value){
		this.bdValue = value;
	}
	

	@Override
	public DOBigDecimal setRaw(boolean isRaw){
		_isRaw = isRaw;
		return this;
	}

	@Override
	public boolean isRaw() {
		return _isRaw;
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
				bd = null;
			} 
		}
		this.bdValue = bd;
	}
	
	
	
	@Override
	public BigDecimal Value() {
		BigDecimal bdRet = null;
		
		if (bdValue != null){
			bdRet = bdValue;
		}
		return bdRet;
	}
	
	
	
	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#Value(java.lang.String)
	 */
	@Override
	public BigDecimal Value(Object ValueWhenEmpty) {
		BigDecimal bdRet = null;
		if (bdValue != null){
			bdRet = bdValue;
		} else {
			if (ValueWhenEmpty instanceof BigDecimal){
				return bdRet = (BigDecimal)ValueWhenEmpty;
			} 
		}
		return bdRet;	
	}
	
	
	
	@Override
	/**
	 * Entspricht Value()
	 */
	public String ValuePlain() {
		String sRet = null;
		if (bdValue != null){
			sRet = bdValue.toPlainString();
		}
		return sRet;
	}

	
	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#ValuePlain(java.lang.String)
	 */
	@Override
	public String ValuePlain(String ValueWhenEmpty) {
		String sRet = null;
		if (bdValue != null){
			sRet = bdValue.toPlainString();
		} else {
			return sRet = ValueWhenEmpty;
		}
		return sRet;
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
//		try {
//			if (this.bdValue == null){
//				stmt.getPreparedStatement().setNull(idx, java.sql.Types.DECIMAL);
//			} else {
//				stmt.getPreparedStatement().setBigDecimal(idx, this.bdValue);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return idx+1;
//	}
}
