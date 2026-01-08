/**
 * 
 */
package panter.gmbh.Echo2.AgentsAndValidators;

import nextapp.echo2.app.Component;
import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

/**
 * @author martin
 *
 */
public interface IF_simpleValidator {
	
	public MyE2_MessageVector simpleValid() throws myException;
	
	
	public default XX_ActionValidator_NG getValidator() {
		return new XX_ActionValidator_NG() {

			@Override
			public MyE2_MessageVector isValid(Component oComponentWhichIsValidated) throws myException {
				return simpleValid();
			}
			
		};
	}
	
}
