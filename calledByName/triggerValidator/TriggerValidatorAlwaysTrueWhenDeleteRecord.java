package calledByName.triggerValidator;

import panter.gmbh.Echo2.Messaging.MyE2_MessageVector;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.AT_CONST;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.IF_trigger_validator;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.Rec20_trigger_action_def;
import panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER.Rec20_trigger_action_log;
import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_LOG;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;


/**
 * dummy-trigger
 * @author martin
 *
 */
public class TriggerValidatorAlwaysTrueWhenDeleteRecord implements IF_trigger_validator{

	@Override
	public boolean isValid(Rec20_trigger_action_log trigger_log, Rec20_trigger_action_def trigger_def, MyE2_MessageVector mv) throws myException {
		return trigger_log.get_ufs_dbVal(TRIGGER_ACTION_LOG.db_action_typ).equals(AT_CONST.TRIGGER_ACTION_DEF_TYPES.DELETE.name());
	}

}
