package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.indep.exceptions.myException;

public interface IF_trigger_validator {
	
	public boolean isValid(Rec20_trigger_action_log trigger_log, Rec20_trigger_action_def trigger_def, MyE2_MessageVector mv) throws myException;
	
}
