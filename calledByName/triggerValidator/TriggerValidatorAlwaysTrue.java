package calledByName.triggerValidator;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.IF_trigger_validator;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.Rec20_trigger_action_def;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.Rec20_trigger_action_log;
import panter.gmbh.indep.exceptions.myException;


/**
 * dummy-trigger
 * @author martin
 *
 */
public class TriggerValidatorAlwaysTrue implements IF_trigger_validator{

	@Override
	public boolean isValid(Rec20_trigger_action_log trigger_log, Rec20_trigger_action_def trigger_def, MyE2_MessageVector mv) throws myException {
		return true;
	}

}
