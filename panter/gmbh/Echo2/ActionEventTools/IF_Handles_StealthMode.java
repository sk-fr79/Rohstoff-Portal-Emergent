package panter.gmbh.Echo2.ActionEventTools;

import panter.gmbh.indep.exceptions.myException;

public interface IF_Handles_StealthMode {
	
	//wenn diese methode != null liefert, dann wird im E2_InnerActionListenerForActionAgents() die methode set_LAST_ACTIONEVENT() auf diesen wert gesetzt 
	public MyActionEvent   get_action_event_of_calling_klick() throws myException;
}
