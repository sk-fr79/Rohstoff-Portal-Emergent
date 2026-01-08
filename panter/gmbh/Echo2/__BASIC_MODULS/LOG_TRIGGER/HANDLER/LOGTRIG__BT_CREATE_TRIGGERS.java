package panter.gmbh.Echo2.__BASIC_MODULS.LOG_TRIGGER.HANDLER;

import panter.gmbh.Echo2.E2_ResourceIcon;
import panter.gmbh.Echo2.ActionEventTools.ExecINFO;
import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.AgentsAndValidators.E2_ButtonAUTHValidator_AUTO;
import panter.gmbh.Echo2.Messaging.MyE2_Alarm_Message;
import panter.gmbh.Echo2.Messaging.MyE2_Info_Message;
import panter.gmbh.Echo2.Messaging.bibMSG;
import panter.gmbh.Echo2.components.MyE2_Button;
import panter.gmbh.indep.MyString;
import panter.gmbh.indep.exceptions.myException;

public class LOGTRIG__BT_CREATE_TRIGGERS extends MyE2_Button
{

	public LOGTRIG__BT_CREATE_TRIGGERS()
	{
		super(new MyString("Trigger generieren"), E2_ResourceIcon.get_RI("new.png") , E2_ResourceIcon.get_RI("leer.png"));
		this.add_oActionAgent(new ownActionAgent() );
		this.add_GlobalValidator(new E2_ButtonAUTHValidator_AUTO("NEUEINGABE_LOGTRIG_"));
	}

	
	
	private class ownActionAgent extends XX_ActionAgent
	{
		@Override
		public void executeAgentCode(ExecINFO oExecInfo) throws myException {
			LOGTRIG_Handler oTriggerHandler = new LOGTRIG_Handler();
			boolean bRet = oTriggerHandler.CreateTriggers();
			if (bRet){
				bibMSG.add_MESSAGE(new MyE2_Info_Message("Alle Trigger wurden generiert."));
			} else {
				bibMSG.add_MESSAGE(new MyE2_Alarm_Message("Fehler beim Trigger generieren." ));
			}
		}
	}
	
}
