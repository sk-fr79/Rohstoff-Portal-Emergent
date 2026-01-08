package rohstoff.businesslogic.bewegung2.convert_from_fuhre.types;

import java.sql.SQLException;

import panter.gmbh.indep.dataTools.IF_Field;
import panter.gmbh.indep.dataTools.MyDBStatementPrepared;

public class DOLong extends DataObject {
	
	public DOLong(IF_Field field, Long value) {
		super(field);
		lValue = value;
	}
	
	
	public DOLong(IF_Field field){
		super(field);
	}
	
	public DOLong (long value){
		lValue = new Long(value);
	}
	
	public DOLong (int value){
		lValue = new Long(value);
	}
	
	public DOLong(Long Value){
		lValue = Value;
	}
	
	@Override
	public DOLong setRaw(boolean isRaw){
		_isRaw = isRaw;
		return this;
	}

	@Override
	public boolean isRaw() {
		return _isRaw;
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
	public Long Value() {
		
		Long lRet = null;
		
		if (lValue != null){
			lRet = lValue; 
		}
		return lRet;
	}
	
	
	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#Value(java.lang.String)
	 */
	@Override
	public Long Value(Object ValueWhenEmpty) {
		Long lRet = null;
		if (lValue != null){
			lRet = lValue; 
		} else {
			if (ValueWhenEmpty instanceof Long){
				lRet = (Long) ValueWhenEmpty;
			}
		}
		return lRet;
	}
	
	
	
	@Override
	/*
	 * Entspricht Value()
	 */
	public String ValuePlain() {
		String sRet = "";
		if (lValue != null ){
			sRet = lValue.toString();
		}
		return sRet;
	}

	/* (non-Javadoc)
	 * @see rohstoff.businesslogic.bewegung.convert_from_fuhre.types.DataObject#ValuePlain(java.lang.String)
	 */
	@Override
	public String ValuePlain(String ValueWhenEmpty) {
			String sRet = "";
			if (lValue != null ){
				sRet = lValue.toString();
			} else {
				sRet = ValueWhenEmpty;
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




}
