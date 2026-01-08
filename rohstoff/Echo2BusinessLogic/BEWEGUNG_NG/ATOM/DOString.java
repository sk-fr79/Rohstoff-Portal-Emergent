package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM;

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

	@Override
	/**
	 * Gibt den Wert ohne umgebende Hochkomma zurück
	 * Bein null wird der Wert (nicht der String) null zurückgegeben.
	 */
	public String ValuePlain() {
		return sValue;
	}

	/* (non-Javadoc)
	 * @see rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DataObject#CopyFrom(rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.ATOM.DataObject)
	 */
	@Override
	public void CopyFrom(DataObject o) {
		this.setValue(o.ValuePlain());
	}
	
	

}
