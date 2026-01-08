package rohstoff.Echo2BusinessLogic.BEWEGUNG_NG.FUZE.AA_NEW;

import panter.gmbh.Echo2.MyE2_String;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.indep.exceptions.myException;
import rohstoff.Echo2BusinessLogic.BEWEGUNG_NG._ENUMS.ENUM_VEKTOR_TYP;

public class FZ_AA_NewUMB extends XX_ActionAgent {

	@Override
	public void executeAgentCode(ExecINFO oExecInfo) throws myException {
		bibMSG.add_MESSAGE(new MyE2_Alarm_Message(new MyE2_String(ENUM_VEKTOR_TYP.UMB.user_text_lang(),false," : ",false,"Implementierung nicht vorhanden !",true)));
	}

}
