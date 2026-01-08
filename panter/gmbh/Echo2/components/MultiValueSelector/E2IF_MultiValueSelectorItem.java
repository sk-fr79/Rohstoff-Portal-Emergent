package panter.gmbh.Echo2.components.MultiValueSelector;

import panter.gmbh.indep.exceptions.myException;

public interface E2IF_MultiValueSelectorItem {
	
	public void 	setValue ( Object o ) throws myException;
	public Object 	getValue ( ) throws myException;
	
}
