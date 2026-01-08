package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.ACTIONTRIGGER;

import panter.gmbh.basics4project.DB_ENUMS.TRIGGER_ACTION_DEF;
import panter.gmbh.basics4project.DB_ENUMS._TAB;
import panter.gmbh.indep.dataTools.RECORD2.Rec20;
import panter.gmbh.indep.exceptions.myException;

public class Rec20_trigger_action_def extends Rec20 {

	/**
	 * @param p_tab
	 * @throws myException
	 */
	public Rec20_trigger_action_def() throws myException {
		super(_TAB.trigger_action_def);
	}


	/**
	 * @param baseRec
	 * @throws myException
	 */
	public Rec20_trigger_action_def(Rec20 baseRec) throws myException {
		super(baseRec);
	}

	
	public String get_validation_code() throws myException {
		return this.get_ufs_dbVal(TRIGGER_ACTION_DEF.execution_valid);
	}
	public String get_execution_code() throws myException {
		return this.get_ufs_dbVal(TRIGGER_ACTION_DEF.execution_code);
	}
	
	
}
