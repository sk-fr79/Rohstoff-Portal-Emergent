package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM;

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
	public void CopyFrom(DataObject o) {
		this.setValue(o.ValuePlain());
		
	}
	

}
