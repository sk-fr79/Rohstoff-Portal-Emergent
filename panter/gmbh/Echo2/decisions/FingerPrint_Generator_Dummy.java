package panter.gmbh.Echo2.decisions;

import panter.gmbh.indep.exceptions.myException;

public class FingerPrint_Generator_Dummy extends FingerPrint_Generator {

	@Override
	public FingerPrint GenerateFingerprint() throws myException {
		return new FingerPrint_Dummy();
	}

}
