package panter.gmbh.Echo2.decisions;

import panter.gmbh.Echo2.RecursiveSearch.E2_Recursive_Search_Generate_Fingerprint;
import panter.gmbh.indep.exceptions.myException;

public class FingerPrint_Screen extends FingerPrint {

	public FingerPrint_Screen() throws myException {
		super();
		E2_Recursive_Search_Generate_Fingerprint oRecursiveFingerPrint = new E2_Recursive_Search_Generate_Fingerprint();
		this.putAll(oRecursiveFingerPrint.get_hmFingerPrint());
	}

}
