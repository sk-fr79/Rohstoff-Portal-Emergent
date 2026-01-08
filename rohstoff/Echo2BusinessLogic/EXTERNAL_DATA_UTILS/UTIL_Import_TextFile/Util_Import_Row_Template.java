/**
 * rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_TextFile
 * @author manfred
 * @date 26.07.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_TextFile;

import java.util.Hashtable;
import java.util.Map;

/**
 * @author manfred
 * @date 26.07.2016
 *
 */
public class Util_Import_Row_Template {
	
	private Hashtable<String,Util_Import_Col> _htCols = new Hashtable<>();
	
	public void addColumn(String Name, Util_Import_Col col){
		_htCols.put(Name, col);
	}
	
	/**
	 * gibt die Spalte der Zeile zurück
	 * @author manfred
	 * @date 28.07.2016
	 *
	 * @param colname
	 * @return
	 */
	public Util_Import_Col getColumn(String colname){
		Util_Import_Col col = this._htCols.get(colname);
		return col;
	}
	
	
	/**
	 * gibt eine kopie des Templates zurück
	 * @author manfred
	 * @date 26.07.2016
	 *
	 * @return
	 */
	public Hashtable<String,Util_Import_Col> getRow(){
		Hashtable<String,Util_Import_Col> _htCopy = new Hashtable<>();
		for (Map.Entry<String, Util_Import_Col> entry: _htCols.entrySet()){
			try {
				
				_htCopy.put(entry.getKey(),(Util_Import_Col)entry.getValue().clone());
				
			} catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return _htCopy;
	}
	
	
}
