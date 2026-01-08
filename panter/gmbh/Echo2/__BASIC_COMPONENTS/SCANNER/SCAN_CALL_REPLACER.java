package panter.gmbh.Echo2.__BASIC_COMPONENTS.SCANNER;

import panter.gmbh.indep.bibALL;
import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.exceptions.myException;

public class SCAN_CALL_REPLACER {
	
	private String PARAMETER_CLEAN_STRING = null;

	/**
	 * 
	 * @param cORIG
	 * @param cFileName ohne endung, da als endung noch nummerierungen und .pdf drangehaengt werden
	 * @throws myException
	 */
	public SCAN_CALL_REPLACER(String cORIG, String cFileName, String cDPI) throws myException {
		super();
		
		//zuerst die ueblichen system-variable austauschen
		String cRueck = bibReplacer.ReplaceSysvariablesInStrings(cORIG);
		if (cFileName.toUpperCase().endsWith(".PDF")){
			cFileName=cFileName.substring(0,cFileName.length()-4);
		}
			
		
		//dann die Scanner-spezifischen werte austauschen
		cRueck = bibALL.ReplaceTeilString(cRueck, "#"+SCAN__ENUM.REPLAC_CONST.FILENAME.toString()+"#", cFileName);
		cRueck = bibALL.ReplaceTeilString(cRueck, "#"+SCAN__ENUM.REPLAC_CONST.OUTPUTPATH.toString()+"#", bibALL.get_OUTPUTPATH());
		cRueck = bibALL.ReplaceTeilString(cRueck, "#"+SCAN__ENUM.REPLAC_CONST.WEBROOTPATH.toString()+"#", bibALL.get_WEBROOTPATH());
		cRueck = bibALL.ReplaceTeilString(cRueck, "#"+SCAN__ENUM.REPLAC_CONST.COMPLETEPATHOUTPUT.toString()+"#", "/"+bibALL.get_WEBROOTPATH()+"/"+bibALL.get_OUTPUTPATH());
		cRueck = bibALL.ReplaceTeilString(cRueck, "#"+SCAN__ENUM.REPLAC_CONST.DPI.toString()+"#", cDPI==null?"150":cDPI);
		
		this.PARAMETER_CLEAN_STRING = cRueck;
	}

	/**
	 * gibt das ergebnis zurueck
	 * @return
	 */
	public String get_PARAMETER_CLEAN_STRING() {
		return PARAMETER_CLEAN_STRING;
	}
	
	
	
	
}
