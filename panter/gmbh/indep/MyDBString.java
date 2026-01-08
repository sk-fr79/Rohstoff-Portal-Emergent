package panter.gmbh.indep;

import panter.gmbh.Echo2.BasicInterfaces.IF_BasicTypeWrapper;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;

public class MyDBString implements IF_BasicTypeWrapper{

	private String s = null;
	
	
	
	
	/**
	 * @param s
	 */
	public MyDBString(String p_string) {
		super();
		this.s = p_string;
	}




	@Override
	public String get_formated_string(MyMetaFieldDEF def) throws myException {
		if (def == null) {
			throw new myException("MyMetaFieldDEF def MUST NOT BE NULL");
		}
		return (S.isFull(s)?s:null);
	}



	/**
	 * 
	 */
	public MyDBString() {
		super();
	}


	
	
	


	@Override
	public boolean get_bOK() {
		return S.isFull(this.s);
	}




	public String get_s() {
		return s;
	}


	
	@Override
	public Object getBasicObject() throws DataTypeException {
		return s;
	}

}
