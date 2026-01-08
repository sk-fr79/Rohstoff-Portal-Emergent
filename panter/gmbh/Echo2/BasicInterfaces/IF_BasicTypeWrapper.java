package panter.gmbh.Echo2.BasicInterfaces;

import panter.gmbh.indep.S;
import panter.gmbh.indep.dataTools.MyMetaFieldDEF;
import panter.gmbh.indep.exceptions.myException;

public interface IF_BasicTypeWrapper {
	
	public boolean get_bOK();
	
	public default boolean isOK() {
		return this.get_bOK();
	}

	public default boolean isNotOK() {
		return !this.get_bOK();
	}
	
	/**
	 * 
	 * @param def
	 * @return  formatierten wert oder null wenn leer
	 * @throws myException
	 */
	public String get_formated_string(MyMetaFieldDEF def) throws myException;
	
	
	public Object getBasicObject() throws DataTypeException;
	
	
	public static class DataTypeException extends Exception {
		public DataTypeException(String message) {
			super(message);
		}
	}
	
	public static class DataTypeExceptionInterpreterError extends DataTypeException {
		public DataTypeExceptionInterpreterError(String basicErrorTag, String message) {
			super(message+" ( Reason:"+S.NN(basicErrorTag)+")");
		}
	}
	
	
	
}
