package panter.gmbh.Echo2.RB.BASICS;

import panter.gmbh.indep.exceptions.myException;

public class RB_K_dummy extends RB_K {

	public RB_K_dummy(String fieldName) throws myException {
		super(fieldName);
	}

	public String KEY() {
		return this.get_HASHKEY();
	}
	
	
	
}
