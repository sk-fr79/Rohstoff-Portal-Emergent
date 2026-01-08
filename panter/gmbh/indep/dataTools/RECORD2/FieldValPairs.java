package panter.gmbh.indep.dataTools.RECORD2;

import java.util.HashMap;

import panter.gmbh.indep.dataTools.IF_Field;

public class FieldValPairs extends HashMap<String,String> {

	public FieldValPairs() {
		super();
	}

	public FieldValPairs _a(IF_Field f, String val) {
		this.put(f.fieldName(), val);
		return this;
	}
	
	
}
