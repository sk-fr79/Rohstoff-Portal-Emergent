package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM;

import java.math.BigDecimal;

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
	public void CopyFrom(DataObject o) {
		this.setValue(o.ValuePlain());
	}

}
