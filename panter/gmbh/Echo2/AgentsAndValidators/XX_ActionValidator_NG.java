package panter.gmbh.Echo2.AgentsAndValidators;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

/**
 * neu, nur globalvalidator
 * @author martin
 *
 */
public abstract class XX_ActionValidator_NG extends XX_ActionValidator {

	@Override
	protected MyE2_MessageVector isValid(String cID_Unformated) throws myException {
		return null;
	}

}
