package panter.gmbh.Echo2.Messaging;

import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.indep.exceptions.myException;

public interface IF_Message_4_Decisions extends IF_Message_WithButtons, IF_Message_ForceIntoPopup {
	
	//hier wird der Bestaetigungsbutton gebaut
	public abstract         XX_ActionAgent   get_ActionAgent_4_SettingStamp_STEP2() throws myException;
	public abstract         XX_ActionAgent   get_ActionAgent_4_Executing_CallingComponent() throws myException;

}
