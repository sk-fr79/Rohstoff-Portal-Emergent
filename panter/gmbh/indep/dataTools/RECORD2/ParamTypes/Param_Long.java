package panter.gmbh.indep.dataTools.RECORD2.ParamTypes;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import panter.gmbh.indep.dataTools.MyDBStatementPrepared;

public class Param_Long extends ParamDataObject {
	
	public Param_Long(String name, Long value) {
		super(name);
		this.lValue = value;
	}
	
	
	public Param_Long(String name){
		super(name);
	}
	
	public Param_Long (long value){
		this.lValue = new Long(value);
	}
	
	public Param_Long (int value){
		this.lValue = new Long(value);
	}
	
	public Param_Long(Long Value){
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
		// TODO Auto-generated method stub
		if (lValue != null){
			sRet = lValue.toString(); 
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
			if (this.lValue == null){
				stmt.getPreparedStatement().setNull(idx, java.sql.Types.BIGINT);
			} else {
				stmt.getPreparedStatement().setLong(idx, this.lValue );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idx+1;
	}

}
