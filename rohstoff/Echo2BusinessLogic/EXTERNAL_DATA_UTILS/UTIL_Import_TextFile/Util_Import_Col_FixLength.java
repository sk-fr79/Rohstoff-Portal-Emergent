/**
 * rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_TextFile
 * @author manfred
 * @date 26.07.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_TextFile;

import panter.gmbh.indep.bibALL;

/**
 * @author manfred
 * @date 26.07.2016
 *
 */
public class Util_Import_Col_FixLength extends Util_Import_Col implements Cloneable {
	private int _Start = 0;
	private int _End   = 0;

	/**
	 * @author manfred
	 * @date 26.07.2016
	 *
	 * @param Name
	 */
	public Util_Import_Col_FixLength(String Name) {
		super(Name);
	}

	public int get_Start() {
		return _Start;
	}

	public Util_Import_Col_FixLength set_Start(int _Start) {
		this._Start = _Start;
		return this;
	}

	public int get_End() {
		return _End;
	}

	public Util_Import_Col_FixLength set_End(int _End) {
		this._End = _End;
		return this;
	}
	

	public void ExtractFrom (String Input){
		if (bibALL.isEmpty(Input)){
			return;
		}
		
		int len = Input.length();
		
		if( len <= _Start) {
			return;
		}
		
		if (len > _End){
			set_value(Input.substring(_Start, _End));
		} else {
			set_value(Input.substring(_Start));
		}
		
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
}
