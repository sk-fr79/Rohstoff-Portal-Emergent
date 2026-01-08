package panter.gmbh.Echo2.components.activeReport_NG;

import panter.gmbh.indep.exceptions.myException;


public class AR_StyleLabel2 extends AR_StyleLabel {

	
	public AR_StyleLabel2() throws myException {
		super(true,false,false,2);
	}

	
	public AR_StyleLabel2(boolean bold, boolean italic, boolean grey) throws myException {
		super(bold,italic,grey,2);
	}

}
