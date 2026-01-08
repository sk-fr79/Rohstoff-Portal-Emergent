package panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER;

import java.util.Vector;

import panter.gmbh.basics4project.DB_RECORDS.RECORD_SCANNER_SETTINGS;
import panter.gmbh.indep.S;
import panter.gmbh.indep.exceptions.myException;
import panter.gmbh.indep.myVectors.bibVECTOR;


/**
 * 2014-12-09: scanner-settings fuer einen bestimmten user
 * @author martin
 *
 */
public class RECORD_SCANNER_SETTINGS_SPECIAL extends RECORD_SCANNER_SETTINGS {

	public RECORD_SCANNER_SETTINGS_SPECIAL(RECORD_SCANNER_SETTINGS recScannerSettings) throws myException {
		super(recScannerSettings);
	}

	public String get_SCANNER_AUFRUF1_PARAMETERFREE(String cFileName, String cDPI) throws myException {
		return this.replaceAll(this.get_SCANNER_AUFRUF1_cUF_NN(""), cFileName, cDPI); 
	}
	

	public String get_SCANNER_AUFRUF2_PARAMETERFREE(String cFileName, String cDPI) throws myException {
		return this.replaceAll(this.get_SCANNER_AUFRUF2_cUF_NN(""), cFileName, cDPI); 
	}

	
	public String get_SCANNER_AUFRUF3_PARAMETERFREE(String cFileName, String cDPI) throws myException {
		return this.replaceAll(this.get_SCANNER_AUFRUF3_cUF_NN(""), cFileName, cDPI); 
	}
	
	
	public String get_SCANNER_AUFRUF4_PARAMETERFREE(String cFileName, String cDPI) throws myException {
		return this.replaceAll(this.get_SCANNER_AUFRUF4_cUF_NN(""), cFileName, cDPI); 
	}

	
	private String replaceAll(String cOrig, String cFileName, String cDPI) throws myException {
		Vector<String>  vDPIs = bibVECTOR.get_Vector("150", "300", "600");
		if (S.isEmpty(cDPI) ) {
			cDPI="150";
		} else  if (!vDPIs.contains(cDPI)) {
			cDPI="150";
		}
		
		SCAN_CALL_REPLACER oReplacer = new SCAN_CALL_REPLACER(cOrig, cFileName, cDPI);
		return oReplacer.get_PARAMETER_CLEAN_STRING(); 
	}
	
}
