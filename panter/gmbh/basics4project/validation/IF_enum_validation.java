package panter.gmbh.basics4project.validation;

import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator;

public interface IF_enum_validation {

	public String   				getRange();
	public String  					getReadableText();
	public E2_ButtonAUTHValidator 	getValidator();
	public E2_ButtonAUTHValidator 	getValidatorWithoutSupervisorPersilschein();
	
}
