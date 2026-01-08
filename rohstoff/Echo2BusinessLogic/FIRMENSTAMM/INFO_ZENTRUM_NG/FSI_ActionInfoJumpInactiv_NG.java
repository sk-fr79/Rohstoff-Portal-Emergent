package rohstoff.Echo2BusinessLogic.FIRMENSTAMM.INFO_ZENTRUM_NG;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;

public class FSI_ActionInfoJumpInactiv_NG extends XX_ActionAgent {

	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String("An dieser Stelle sind keine Sprünge möglich !")));
	}

}
