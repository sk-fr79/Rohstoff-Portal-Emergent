package panter.gmbh.Echo2.decisions;

import java.util.HashMap;

public class FingerPrint extends HashMap<String, String> {

	public FingerPrint() {
		super();
	}
	
	public void add_FingerPrint(FingerPrint  oFP) {
		this.putAll(oFP);
	}
	
}
