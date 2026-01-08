/**
 * rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_Zolltarifnummer_ALT
 * @author manfred
 * @date 26.07.2016
 * 
 */
package rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_Zolltarifnummer_ALT;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.io.FileUtils;

import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.basics4project.DEBUG;
import panter.gmbh.basics4project.DB_ENUMS.ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_RECORDS.RECLIST_ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_RECORDS.RECORDNEW_ZOLLTARIFNUMMER;
import panter.gmbh.basics4project.DB_RECORDS.RECORD_ZOLLTARIFNUMMER;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_TextFile.Util_Import_Col;
import rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_TextFile.Util_Import_Col_FixLength;
import rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_TextFile.Util_Import_Row_Import;
import rohstoff.Echo2BusinessLogic.EXTERNAL_DATA_UTILS.UTIL_Import_TextFile.Util_Import_Row_Template;



/**
 * Klasse hällt alle Zeilen des ZolltarifImports aus der Textdatei
 * 
 * @author manfred
 * @date 26.07.2016
 *
 */
public class ROWS_Zolltarif_Import {

	public enum ENUM_ZOLLTARIF_IMPORT_COLS {
		WA_NUMMER,
		TEXT1,
		TEXT2,
		TEXT3,
		EINHEIT_TXT,
		EINHEIT_NUM
	}
	
	private Util_Import_Row_Template rowTemplate = null;
	
	// Liste der neuen Import-Zeilen
	Hashtable<String, Util_Import_Row_Import>  ht_Rows = new Hashtable<>();
	


	// Liste der alten DB-Einträge
	Hashtable<String, RECORD_ZOLLTARIFNUMMER>  ht_Zolltarifnummern = new Hashtable<>();
	
	// Liste der Records die INAKTIV geschaltet werden müssen
	ArrayList<RECORD_ZOLLTARIFNUMMER>   		arrInaktiv = new ArrayList<>();
	
	
	



	ArrayList<RECORD_ZOLLTARIFNUMMER>			arrKorrektur = new ArrayList<>();
	ArrayList<Util_Import_Row_Import>			arrNew = new ArrayList<>();
	
	
	String 				_sFileName = null;
	ArrayList<String> 	_sImportFileLines = null;
	
	
	/**
	 * @author manfred
	 * @date 26.07.2016
	 *
	 */
	public ROWS_Zolltarif_Import() {
		initCols();
	}
	
	
	/**
	 * setzt die Import-Spaltenbreite der angegebenen Spalte, Basis = 1
	 * @author manfred
	 * @date 28.07.2016
	 *
	 * @param Column
	 * @param start
	 * @param len
	 */
	public void setColumSize(ENUM_ZOLLTARIF_IMPORT_COLS Column, int start, int len){
		Util_Import_Col col = rowTemplate.getColumn(Column.toString());
		if (col instanceof Util_Import_Col_FixLength){
			((Util_Import_Col_FixLength)col).set_Start(start-1).set_End(start + len -1);
		}
	}
	
	
	
	/**
	 * template der Import-Zeile aufbauen, das als Kopievorlage für die einzelnen Zeilen dient.
	 * @author manfred
	 * @date 26.07.2016
	 *
	 */
	private void initCols(){
		rowTemplate = new Util_Import_Row_Template();
		
		Util_Import_Col_FixLength col;
		
		col = new Util_Import_Col_FixLength(ENUM_ZOLLTARIF_IMPORT_COLS.WA_NUMMER.toString()).set_Start(0).set_End(8);
		rowTemplate.addColumn(col.get_name(),col);
		
		col = new Util_Import_Col_FixLength(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT1.toString()).set_Start(10).set_End(120);
		rowTemplate.addColumn(col.get_name(),col);
		
		col = new Util_Import_Col_FixLength(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT2.toString()).set_Start(120).set_End(240);
		rowTemplate.addColumn(col.get_name(),col);
		
		col = new Util_Import_Col_FixLength(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT3.toString()).set_Start(240).set_End(258);
		rowTemplate.addColumn(col.get_name(),col);
		
		col = new Util_Import_Col_FixLength(ENUM_ZOLLTARIF_IMPORT_COLS.EINHEIT_TXT.toString()).set_Start(258).set_End(274);
		rowTemplate.addColumn(col.get_name(),col);
		
		col = new Util_Import_Col_FixLength(ENUM_ZOLLTARIF_IMPORT_COLS.EINHEIT_NUM.toString()).set_Start(274).set_End(276);
		rowTemplate.addColumn(col.get_name(),col);
	}

	
	
	/**
	 * Datei einlesen und das String-Array füllen
	 * @author manfred
	 * @date 27.07.2016
	 *
	 * @param sFileName
	 * @param sEncoding
	 */
	public boolean readFile(String sFileName, String sEncoding){
		boolean bRet = true;
		
		GregorianCalendar calImport = new GregorianCalendar();
		Util_Import_Row_Import row = null;

		arrInaktiv.clear();
		arrKorrektur.clear();
		arrNew.clear();
		
		ht_Rows.clear();
		_sFileName = sFileName;
		
		
		try {
			_sImportFileLines =  (ArrayList<String>) FileUtils.readLines(new File(_sFileName),sEncoding /*"windows-1252"*/);
			if (bibALL.get_bDebugMode()) {
				DEBUG.System_print(_sImportFileLines);
			}
		} catch (IOException e) {
			e.printStackTrace();
			_sImportFileLines = null;
			bRet &= false;
		}
		
		if (_sImportFileLines == null){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler beim lesen der Import-Datei")));
			return false;
		}
		
			
		//
		// die hochgeladene Datei importieren
		//
		if (!fillDataStructure()){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler beim lesen der Import-Datei")));
			return false;
		}
		
		
		//
		//die aktuellen Zolltarifnummern lesen
		//
		bRet = ReadExistingZolltarifnummern();
		if (bRet == false){
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler beim lesen der bestehenden Zolltarifnummern")));
			return false;
		}

		
		//
		// ermitteln der Zolltarifnummern, die inaktiv gesetzt werden müssen
		//
		for (Map.Entry<String, RECORD_ZOLLTARIFNUMMER> entry: ht_Zolltarifnummern.entrySet()){
			// importliste enthält nicht die Nummer aus der DB
			String sKey = entry.getKey();
			if (!ht_Rows.containsKey(sKey)){
				RECORD_ZOLLTARIFNUMMER r = entry.getValue();
				try {
					r.set_NEW_VALUE_AKTIV("N");
					r.set_NEW_VALUE_LETZTER_IMPORT_(calImport);
					arrInaktiv.add(r);
				} catch (myException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		//
		// ermitteln der Zolltarifnummern, die korrigiert werden müssen
		//
		for (Map.Entry<String, RECORD_ZOLLTARIFNUMMER> entry: ht_Zolltarifnummern.entrySet()){
			// importliste enthält die Nummer aus der DB
			if (ht_Rows.containsKey(entry.getKey())){
				RECORD_ZOLLTARIFNUMMER recZoll = entry.getValue();
				Util_Import_Row_Import rowImport =  ht_Rows.get(entry.getKey());
				boolean bChange = false;
				String sNew = null;
				String sOld = null;
				try {
					sNew = rowImport.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT1.toString()).get_value().trim();
					sOld = recZoll.get_TEXT1_cUF_NN("").trim();
					if(!sNew.equals(sOld) && (!bibALL.isEmpty(sNew) || !bibALL.isEmpty(sOld) )){
							recZoll.set_NEW_VALUE_TEXT1(sNew );
							bChange = true;
					}
					
					sNew = rowImport.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT2.toString()).get_value().trim();
					sOld = recZoll.get_TEXT2_cUF_NN("").trim();
					if(!sNew.equals(sOld) && (!bibALL.isEmpty(sNew) || !bibALL.isEmpty(sOld) )){
							recZoll.set_NEW_VALUE_TEXT2(sNew );
							bChange = true;
					}
					
					sNew = rowImport.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT3.toString()).get_value().trim();
					sOld = recZoll.get_TEXT3_cUF_NN("").trim();
					if(!sNew.equals(sOld) && (!bibALL.isEmpty(sNew) || !bibALL.isEmpty(sOld) )){
							recZoll.set_NEW_VALUE_TEXT3(sNew );
							bChange = true;
					}
					
					// einheit num: im Text '00' im Feld '0', also als int prüfen
					sNew = rowImport.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.EINHEIT_NUM.toString()).get_value().trim();
					Long iNew = bibALL.makeLongfromNumberString(sNew, ".");
					if (iNew == null) iNew = -1L;
					Long iOld = recZoll.get_BM_NUMMER_lValue(-1L);
					if(!iNew.equals(iOld) ){
							recZoll.set_NEW_VALUE_BM_NUMMER(sNew );
							bChange = true;
					}
					
					
					sNew = rowImport.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.EINHEIT_TXT.toString()).get_value().trim();
					sOld = recZoll.get_BM_TEXT_cUF_NN("").trim();
					if(!sNew.equals(sOld) && (!bibALL.isEmpty(sNew) || !bibALL.isEmpty(sOld) )){
							recZoll.set_NEW_VALUE_BM_TEXT(sNew );
							bChange = true;
					}
					
					
					if (bChange){
						recZoll.set_NEW_VALUE_LETZTER_IMPORT_(calImport);
						arrKorrektur.add(recZoll);
					}
					
				} catch (myException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				
			}
		}
		
		//
		// ermitteln  der neuen Zolltarifnummern
		//
		for (Map.Entry<String, Util_Import_Row_Import> entryImport: ht_Rows.entrySet()){
			String key = entryImport.getKey();
			if (!ht_Zolltarifnummern.containsKey(key)){
				arrNew.add(entryImport.getValue());
			}
		}
		
		
//		
//		if (bibALL.get_bDebugMode()){
//			
//			try {
//				DEBUG.System_println("INAKTIVE");
//				
//				for (RECORD_ZOLLTARIFNUMMER r : arrInaktiv){
//					DEBUG.System_println(r.get_NUMMER_cUF());
//				}
//				DEBUG.System_println("Korrektur");
//				for (RECORD_ZOLLTARIFNUMMER r: arrKorrektur){
//					DEBUG.System_println(r.get_NUMMER_cUF());
//				}
//				
//				DEBUG.System_println("Import");
//				for (Util_Import_Row_Import r: arrNew){
//					DEBUG.System_println(r.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.WA_NUMMER.toString()).get_value());
//				}
//				
//				
//			} catch (myException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			bibALL.get_bDebugMode();
//		}
	
		
		
		return bRet;
		
	}
	
	
	
	private boolean fillDataStructure(){
		boolean bRet = true;
		
		// alle importierten Zeilen einlesen
		for (String sLine: _sImportFileLines){
			Util_Import_Row_Import  row = ImportLine(sLine) ;
			
			if (row == null){
					bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler beim lesen einer Zeile der Import-Datei: " + sLine )));
					bRet = false;
			} else if (	bibALL.isEmpty(row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.WA_NUMMER.toString()).get_value()) ||
						bibALL.isEmpty(row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT1.toString()).get_value() ) 	||
						bibALL.isEmpty(row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.EINHEIT_NUM.toString()).get_value())){
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehlende Daten in der Importdaten (geprüft: WA-Nummer, Text1, Einheit nummerisch): " + sLine )));
				bRet = false;
				
			}
			
			// Abbruch bei Fehler
			if (bRet == false){
				break;
			}
		}
		
		return bRet;
	}
	
	
	
	/**
	 * Importieren einer zeile in die Struktur der Importrow
	 * @author manfred
	 * @date 27.07.2016
	 *
	 * @param sLine
	 * @return
	 */
	private Util_Import_Row_Import ImportLine(String sLine){
		Util_Import_Row_Import row = new Util_Import_Row_Import(rowTemplate.getRow()).importCompleteRow(sLine);
		
		String sNUMMER = row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.WA_NUMMER.toString()).get_value();
		
		if (!bibALL.isEmpty(sNUMMER)){
			ht_Rows.put(row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.WA_NUMMER.toString()).get_value(), row);
		} else {
			row = null;
		}
		return row;
	}
	
	
	

	/**
	 * liest alle bestehenden Zolltarifnummer-Records ein
	 * @author manfred
	 * @date 27.07.2016
	 *
	 * @return
	 */
	private boolean ReadExistingZolltarifnummern(){
		boolean bRet = false;
		try {
			RECLIST_ZOLLTARIFNUMMER rl = new RECLIST_ZOLLTARIFNUMMER("nvl("+ZOLLTARIFNUMMER.aktiv.fn() + ",'Y') = 'Y'","");
			for (RECORD_ZOLLTARIFNUMMER rec: rl){
				ht_Zolltarifnummern.put(rec.get_NUMMER_cUF(), rec);
			}
			bRet = true;
		} catch (myException e) {
			e.printStackTrace();
			bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyString("Fehler beim Lesen der bestehenden Zolltarifnummern")));
		}
		return bRet;
	}

	
	
	
	
	
	public ArrayList<RECORD_ZOLLTARIFNUMMER> getArrInaktiv() {
		return arrInaktiv;
	}


	public ArrayList<RECORD_ZOLLTARIFNUMMER> getArrKorrektur() {
		return arrKorrektur;
	}


	public ArrayList<Util_Import_Row_Import> getArrNew() {
		return arrNew;
	}

	
	public Hashtable<String, Util_Import_Row_Import> getHt_Rows() {
		return ht_Rows;
	}


	public Hashtable<String, RECORD_ZOLLTARIFNUMMER> getHt_Zolltarifnummern() {
		return ht_Zolltarifnummern;
	}


	/**
	 * gibt alle SQL-Statements für die zu inaktivierenden Einträge zurück.
	 * @author manfred
	 * @date 28.07.2016
	 *
	 * @return
	 */
	public Vector<String>  getSQLForInactive(){
		Vector<String> vRet = new Vector<>(); 
		for (RECORD_ZOLLTARIFNUMMER rec: arrInaktiv){
			try {
				vRet.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		
		return vRet;
	}

	/**
	 * gibt alle SQL-Statements für die zu korrigierenden Einträge zurück
	 * @author manfred
	 * @date 28.07.2016
	 *
	 * @return
	 */
	public Vector<String>  getSQLForChanged(){
		Vector<String> vRet = new Vector<>(); 
		for (RECORD_ZOLLTARIFNUMMER rec: arrKorrektur){
			try {
				vRet.add(rec.get_SQL_UPDATE_STATEMENT(null, true));
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		return vRet;
	}
	
	
	public Vector<String>  getSQLForNew(){
		Vector<String> vRet = new Vector<>(); 
		GregorianCalendar calNow = new GregorianCalendar();
		for (Util_Import_Row_Import row: arrNew){
			try {
				RECORDNEW_ZOLLTARIFNUMMER rec = new RECORDNEW_ZOLLTARIFNUMMER();
				rec.set_NEW_VALUE_NUMMER(row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.WA_NUMMER.toString()).get_value());
				rec.set_NEW_VALUE_TEXT1(row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT1.toString()).get_value());
				rec.set_NEW_VALUE_TEXT2(row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT2.toString()).get_value());
				rec.set_NEW_VALUE_TEXT3(row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.TEXT3.toString()).get_value());
				rec.set_NEW_VALUE_BM_NUMMER(row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.EINHEIT_NUM.toString()).get_value());
				rec.set_NEW_VALUE_BM_TEXT(row.getCol(ENUM_ZOLLTARIF_IMPORT_COLS.EINHEIT_TXT.toString()).get_value());
				rec.set_NEW_VALUE_AKTIV("Y");
				rec.set_NEW_VALUE_LETZTER_IMPORT(calNow);
				vRet.add(rec.get_InsertSQLStatementWith_Id_Field(true, true));
				
			} catch (myException e) {
				e.printStackTrace();
			}
		}
		return vRet;
	}
	
}
