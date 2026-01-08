/**
 * rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_TextFile
 * @author manfred
 * @date 26.07.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_TextFile;

/**
 * @author manfred
 * @date 26.07.2016
 *
 */
public abstract class Util_Import_Col implements Cloneable {
	
	private String _name = null;
	private String _value = null;
	
	/**
	 * @author manfred
	 * @date 26.07.2016
	 *
	 */
	public Util_Import_Col(String Name) {
		_name = Name;
	}
	
	public String get_name() {
		return _name;
	}
	
	public Util_Import_Col set_name(String _name) {
		this._name = _name;
		return this;
	}
	
	public String get_value() {
		return _value;
	}
	
	public Util_Import_Col set_value(String _value) {
		this._value = _value;
		return this;
	}
	
	public abstract void ExtractFrom (String Input);
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
