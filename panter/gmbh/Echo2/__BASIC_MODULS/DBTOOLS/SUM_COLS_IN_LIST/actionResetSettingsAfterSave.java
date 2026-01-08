package panter.gmbh.Echo2.__BASIC_MODULS.DBTOOLS.SUM_COLS_IN_LIST;

import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.bibSES;
import panter.gmbh.indep.exceptions.myException;

public class actionResetSettingsAfterSave extends XX_ActionAgent {

	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		bibSES.get_set_SUM_COLUMES_IN_LISTMODULES().clear();
	}

}
