/**
 * panter.gmbh.BasicInterfaces.Service
 * @author martin
 * @date 26.05.2020
 * 
 */
package panter.gmbh.BasicInterfaces.Service;

import panter.gmbh.indep.Replacer.bibReplacer;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 * @date 26.05.2020
 *
 */
public class PdServiceReplaceSysVariablesInString {

	/**
	 * @author martin
	 * @date 26.05.2020
	 *
	 */
	public PdServiceReplaceSysVariablesInString() {
	}

	
	public String replace(String stringWithPlaceHolders) throws myException {
		return bibReplacer.ReplaceSysvariablesInStrings(stringWithPlaceHolders);
	}
	
}
