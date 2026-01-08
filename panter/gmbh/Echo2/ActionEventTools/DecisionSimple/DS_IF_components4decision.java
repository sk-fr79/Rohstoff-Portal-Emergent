package panter.gmbh.Echo2.ActionEventTools.DecisionSimple;

import java.util.HashMap;
import java.util.Vector;

import panter.gmbh.Echo2.ActionEventTools.XX_ActionAgent;
import panter.gmbh.Echo2.components.E2_IF_Handles_ActionAgents;
import panter.gmbh.indep.exceptions.myException;

public interface DS_IF_components4decision extends E2_IF_Handles_ActionAgents{
	
	public Vector<XX_ActionAgent> 	get_storage_vector_4_all_agents() throws myException;
	public Vector<XX_ActionAgent> 	get_storage_vector_4_standard_agents() throws myException;
	public Vector<DS_ActionAgent> 	get_storage_vector_4_decision_agents() throws myException;
	
	/*
	 * fuer jede notwendige entscheidung wird ein popup gestartet, wenn die entscheidung dort lautet trotzdem weiter,
	 * wird der container geschlossen, der entscheidungstatus steht im container
	 * 
	 * Wird der letzte entscheidungscontainer ueberstimmt und keiner mit nein bleibt uebrig, dann wird die komponente mit den
	 * vorhandenen Actionagenten (ausser den decision-agents) ausgefuehrt
	 */
	public HashMap<DS_ActionAgent,DS_PopupContainer4Decision>  get_hm_descision_containers() throws myException;
	
	//beispiele
//	private Vector<XX_ActionAgent> storage_vector_4_all_agents = new Vector<>();
//	private Vector<XX_ActionAgent> storage_vector_4_standard_agents = new Vector<>();
//	private Vector<DS_ActionAgent> storage_vector_4_decision_agents = new Vector<>();
//	private HashMap<DS_ActionAgent, DS_PopupContainer4Decision> hm_descision_containers = new HashMap<>();
//	
//	@Override
//	public Vector<XX_ActionAgent> get_storage_vector_4_all_agents() throws myException {
//		return this.storage_vector_4_all_agents;
//	}
//
//	@Override
//	public Vector<XX_ActionAgent> get_storage_vector_4_standard_agents() throws myException {
//		return this.storage_vector_4_standard_agents;
//	}
//
//	@Override
//	public Vector<DS_ActionAgent> get_storage_vector_4_decision_agents() throws myException {
//		return this.storage_vector_4_decision_agents;
//	}
//
//	@Override
//	public HashMap<DS_ActionAgent, DS_PopupContainer4Decision> get_hm_descision_containers() throws myException {
//		return this.hm_descision_containers;
//	}

	
}
