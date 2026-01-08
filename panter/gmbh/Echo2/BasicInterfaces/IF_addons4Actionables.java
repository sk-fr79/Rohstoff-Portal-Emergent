/**
 * 
 */
package panter.gmbh.Echo2.BasicInterfaces;

import panter.gmbh.BasicInterfaces.IF__Reflections;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER_OR_DEVELOPER;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator;
import panter.gmbh.Echo2.AgentsAndValidators.XX_ActionValidator_NG;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
@SuppressWarnings("unchecked")
public interface IF_addons4Actionables<T> extends IF__Reflections{
	
	/**
	 * adds a validator to executional object
	 * @param validator
	 * @return 
	 * @throws myException
	 */
	public default T _addGlobalValidator(XX_ActionValidator_NG validator ) throws myException{
		if (this instanceof E2_IF_Handles_ActionAgents) {
			((E2_IF_Handles_ActionAgents) this).add_GlobalValidator(validator);
		}
		return (T)this;
	}
	

	/**
	 * adds a validator to executional object
	 * @param validator
	 * @return 
	 * @throws myException
	 */
	public default T _addGlobalValidator(XX_ActionValidator validator ) throws myException{
		if (this instanceof E2_IF_Handles_ActionAgents) {
			((E2_IF_Handles_ActionAgents) this).add_GlobalValidator(validator);
		}
		return (T)this;
	}

	
	
	/**
	 * add validation only CEO-allowed
	 * @return
	 * @throws myException
	 */
	public default T _addValidatorOnlyGF() throws myException{
		if (this instanceof E2_IF_Handles_ActionAgents) {
			((E2_IF_Handles_ActionAgents) this).add_GlobalValidator(new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER());
		}
		return (T)this;
	}

	/**
	 * add validation only CEO or devleloper allowed
	 * @return
	 * @throws myException
	 */
	public default T _addValidatorOnlyGfOrDev() throws myException{
		if (this instanceof E2_IF_Handles_ActionAgents) {
			((E2_IF_Handles_ActionAgents) this).add_GlobalValidator(new E2_ButtonAUTH_ONLY_GESCHAEFTSFUEHRER_OR_DEVELOPER());
		}
		return (T)this;
	}

}
