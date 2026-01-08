/**
 * rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_Zolltarifnummer
 * @author manfred
 * @date 26.07.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_TextFile;

import java.util.HashSet;
import java.util.Hashtable;

/**
 * Import-Zeile des Textimports.
 * die Zeile ist eine Hashtable, mit dem Spalten-Namen als Key und einem UTIL_Import_Col as Value.
 * @author manfred
 * @date 26.07.2016
 *
 */
public class Util_Import_Row_Import {
	
	
	Hashtable<String,Util_Import_Col> rowImport;
	 
	
	/**
	 * @author manfred
	 * @date 26.07.2016
	 *
	 */
	public Util_Import_Row_Import(Hashtable<String,Util_Import_Col> template) {
		rowImport = template;
	}
	
	
	/**
	 * Alle Spalten extrahieren
	 * @author manfred
	 * @date 26.07.2016
	 *
	 * @param Input
	 * @return
	 */
	public Util_Import_Row_Import importCompleteRow(String Input){
		for(Util_Import_Col col: rowImport.values()){
			col.ExtractFrom(Input);
		}
		return this;
	}
	
	
	public Util_Import_Col getCol(String ColName){
		return rowImport.get(ColName);
	}
	
	
	public Hashtable<String,Util_Import_Col> getRow(){
		return rowImport;
	}
}
