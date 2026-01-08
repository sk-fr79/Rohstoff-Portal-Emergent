package panter.gmbh.indep.dataTools.RECORD2.ParamTypes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import panter.gmbh.indep.dataTools.MyDBStatementPrepared;

public class Param_BigDecimal extends ParamDataObject {

	public Param_BigDecimal(String name, BigDecimal Value) {
		super(name);
		this.bdValue = Value;
	}
	
	
	public Param_BigDecimal(String name){
		super(name);
	}
	
	
	public Param_BigDecimal (BigDecimal value){
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
	

	@Override
	/**
	 * Entspricht Value()
	 */
	public String ValuePlain() {
		return this.Value();
	}


	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DataObject#CopyFrom(rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DataObject)
	 */
	@Override
	public void CopyFrom(ParamDataObject o) {
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
