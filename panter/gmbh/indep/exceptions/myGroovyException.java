package panter.gmbh.indep.exceptions;

public class myGroovyException extends myException {
	
	public enum ENUM_GROOVY_ERRORS {
		ERROR_INTERPRETER
		,ERROR_OWN_FORBIDDEN_WORD
		,ERROR_OWN_NO_RETURN_VARIABLE_IN_RETURN_BINDING
		,ERROR_BASIC
	}

	
	
	private ENUM_GROOVY_ERRORS error_def = null;

	public myGroovyException(ENUM_GROOVY_ERRORS p_error_def, String errorMessage) {
		super(errorMessage);
		this.error_def = p_error_def;
	}
}
