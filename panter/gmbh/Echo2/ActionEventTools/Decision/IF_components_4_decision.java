package panter.gmbh.Echo2.ActionEventTools.Decision;

import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.indep.exceptions.myException;

public interface IF_components_4_decision extends E2_IF_Handles_ActionAgents{
	
	public Vector<XX_ActionAgent> 	get_storage_vector_4_action_agents() throws myException;
	public Vector<XX_ActionAgent> 	get_storage_vector_4_status_at_start() throws myException;
	
}
